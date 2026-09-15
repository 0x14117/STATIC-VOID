"""
STATIC VOID — server.

Pure Python standard library: no Flask, nothing to pip install. Learner code is
compiled and run by the real javac/java toolchain via java_sandbox.py; this
server only orchestrates. Needs a JDK on PATH in addition to Python 3.11+.

  POST /register  {name}                        -> save the learner's name
  GET  /progress                                -> which tasks are done
  GET  /labs                                    -> all labs and their tasks
  POST /run       {lab_id, task_id, code}       -> compile, run, grade, explain

Run: python3 server.py [port]      (default 5000)
     py server.py [port]           (Windows)
"""

import json
import os
import sys
import traceback
from http.server import BaseHTTPRequestHandler, ThreadingHTTPServer

from java_sandbox import missing_toolchain, run_java
from labs import LABS, get_task

BASE_DIR = os.path.dirname(os.path.abspath(__file__))
STATIC_DIR = os.path.join(BASE_DIR, "static")
SAVES_DIR = os.path.join(BASE_DIR, "saves")
SAVE_FILE = os.path.join(SAVES_DIR, "progress.json")

MAX_CODE_LENGTH = 20_000  # generous for any task here; stops pathological pastes

CONTENT_TYPES = {
    ".html": "text/html; charset=utf-8",
    ".css": "text/css; charset=utf-8",
    ".js": "application/javascript; charset=utf-8",
    ".json": "application/json; charset=utf-8",
}


def _load_progress():
    """Always return the current shape, whatever is actually on disk.

    A save file that merely parses is not a save file in the right shape. This
    project's earlier version wrote {"codename", "completed_missions"}, so
    anyone who ran that version has a file whose keys are all wrong — and
    reading it straight through meant the first successful submission died
    with KeyError: 'completed' AFTER the task had been graded and passed. The
    learner solved it and got a broken connection instead of their
    explanation.

    So every field is rebuilt here rather than trusted: missing file, corrupt
    JSON, JSON that is not an object, an older schema, or a value of the wrong
    type all come back as a usable default.
    """
    data = {}
    if os.path.exists(SAVE_FILE):
        try:
            with open(SAVE_FILE, "r", encoding="utf-8") as f:
                loaded = json.load(f)
            if isinstance(loaded, dict):
                data = loaded
        except (json.JSONDecodeError, OSError, UnicodeDecodeError):
            pass

    completed = data.get("completed")
    if not isinstance(completed, list):
        # Old saves recorded mission ids like "t3m2". Those name nothing in the
        # lab format, so they are dropped rather than migrated into ids that
        # would mark unrelated tasks as done.
        completed = []

    return {
        "name": data.get("name") or data.get("codename"),
        "completed": [entry for entry in completed if isinstance(entry, str)],
    }


def _save_progress(data):
    os.makedirs(SAVES_DIR, exist_ok=True)
    with open(SAVE_FILE, "w", encoding="utf-8") as f:
        json.dump(data, f, indent=2)


def _public_labs():
    """Build the browser's view of the labs field by field.

    Deliberately not a dict comprehension over the lab: `solution` and
    `explanation` must never reach the browser before they are earned, and
    listing the safe fields explicitly means a field added to a lab later
    cannot leak by default.
    """
    out = []
    for lab_id, lab in LABS.items():
        out.append({
            "id": lab_id,
            "chapter": lab["chapter"],
            "chapter_title": lab["chapter_title"],
            "title": lab["title"],
            "idea": lab["idea"],
            "learn": lab["learn"],
            "matters": lab["matters"],
            "explain": lab["explain"],
            "example": lab["example"],
            "recap": lab["recap"],
            "tasks": [
                {
                    "id": task["id"],
                    "role": task["role"],
                    "brief": task["brief"],
                    "starter": task["starter"],
                    "hints": task["hints"],
                    "inputs": task.get("inputs") or [],
                }
                for task in lab["tasks"]
            ],
        })
    out.sort(key=lambda lab: (lab["chapter"], lab["id"]))
    return out


class Handler(BaseHTTPRequestHandler):
    server_version = "StaticVoid/2.0"

    def _send_json(self, status, payload):
        body = json.dumps(payload).encode("utf-8")
        self.send_response(status)
        self.send_header("Content-Type", "application/json; charset=utf-8")
        self.send_header("Content-Length", str(len(body)))
        self.end_headers()
        self.wfile.write(body)

    def _read_json_body(self):
        length = int(self.headers.get("Content-Length", 0))
        if length == 0:
            return {}
        return json.loads(self.rfile.read(length).decode("utf-8"))

    def _serve_static(self, path):
        if path == "/":
            path = "/index.html"

        # Resolve under STATIC_DIR and refuse to leave it (path traversal guard).
        full_path = os.path.realpath(os.path.join(STATIC_DIR, path.lstrip("/")))
        root = os.path.realpath(STATIC_DIR)
        if not full_path.startswith(root + os.sep) and full_path != root:
            self.send_error(403, "Forbidden")
            return
        if not os.path.isfile(full_path):
            self.send_error(404, "Not Found")
            return

        with open(full_path, "rb") as f:
            body = f.read()
        self.send_response(200)
        self.send_header("Content-Type",
                         CONTENT_TYPES.get(os.path.splitext(full_path)[1],
                                           "application/octet-stream"))
        self.send_header("Content-Length", str(len(body)))
        self.end_headers()
        self.wfile.write(body)

    def do_GET(self):
        if self.path.startswith("/.well-known/"):
            self.send_response(404)
            self.send_header("Content-Length", "0")
            self.end_headers()
            return
        if self.path == "/progress":
            self._send_json(200, _load_progress())
        elif self.path == "/labs":
            self._send_json(200, _public_labs())
        else:
            self._serve_static(self.path)

    def do_POST(self):
        try:
            body = self._read_json_body()
        except (json.JSONDecodeError, UnicodeDecodeError):
            self._send_json(400, {"error": "Invalid JSON body."})
            return

        # A bug in here used to kill the connection and print a traceback, so
        # the browser said only "could not reach the server" and the real
        # message was buried in the terminal. Answer with something readable
        # instead, and still log the detail for whoever is debugging.
        try:
            if self.path == "/register":
                self._handle_register(body)
            elif self.path == "/run":
                self._handle_run(body)
            else:
                self.send_error(404, "Not Found")
        except Exception:                                   # noqa: BLE001
            detail = traceback.format_exc()
            sys.stderr.write(detail)
            self._send_json(500, {
                "ok": False,
                "passed": False,
                "output": "",
                "error": {"type": "ServerError", "message": "Something broke in the server."},
                "feedback": (
                    "Something broke in the server, not in your code. The details are "
                    "in the terminal window running the server. This is a bug worth "
                    "reporting."
                ),
                "explanation": None,
            })

    def _handle_register(self, body):
        name = str(body.get("name", "")).strip()
        if not name:
            self._send_json(400, {"error": "Name required."})
            return
        progress = _load_progress()
        progress["name"] = name
        _save_progress(progress)
        self._send_json(200, {"name": name})

    def _handle_run(self, body):
        lab_id = body.get("lab_id")
        task_id = body.get("task_id")
        code = body.get("code", "")

        if not isinstance(code, str):
            self._send_json(400, {"error": "code must be a string."})
            return
        if len(code) > MAX_CODE_LENGTH:
            self._send_json(400, {
                "error": "Code too long ({} characters, limit {}).".format(
                    len(code), MAX_CODE_LENGTH)})
            return

        lab, task = get_task(lab_id, task_id)
        if task is None:
            self._send_json(404, {"error": "That task does not exist."})
            return

        result = run_java(code,
                          input_values=task.get("inputs"),
                          seed_files=task.get("seed_files"))

        if not result["ok"]:
            self._send_json(200, {
                "ok": False,
                "passed": False,
                "output": result["output"],
                "error": result["error"],
                "feedback": result["error"]["message"],
                "explanation": None,
            })
            return

        passed, feedback = task["check"](code, result["output"], result.get("files"))

        if passed:
            progress = _load_progress()
            key = "{}/{}".format(lab_id, task_id)
            if key not in progress["completed"]:
                progress["completed"].append(key)
                _save_progress(progress)

        self._send_json(200, {
            "ok": True,
            "passed": passed,
            "output": result["output"],
            "error": None,
            "feedback": feedback,
            # Withheld until earned — sending it early would give the answer away.
            "explanation": task["explanation"] if passed else None,
        })

    def log_message(self, fmt, *args):
        sys.stderr.write("{} - {}\n".format(self.address_string(), fmt % args))


def main():
    port = int(sys.argv[1]) if len(sys.argv) > 1 else 5000
    tasks = sum(len(lab["tasks"]) for lab in LABS.values())
    print("STATIC VOID — {} labs, {} tasks".format(len(LABS), tasks))

    # Say this at startup rather than letting the first submission fail. The
    # server is still worth starting — the explanations and examples are
    # readable without a JDK — but nothing can be RUN until Java is installed,
    # and finding that out after writing your first answer is a waste of time.
    toolchain_error = missing_toolchain()
    if toolchain_error is not None:
        print("")
        print("!" * 68)
        print(toolchain_error["message"])
        print("")
        print("The site will still open and you can read the labs, but Running")
        print("your code will not work until this is fixed.")
        print("!" * 68)
        print("")

    print("Open http://localhost:{}".format(port))
    server = ThreadingHTTPServer(("127.0.0.1", port), Handler)
    try:
        server.serve_forever()
    except KeyboardInterrupt:
        print("\nStopped.")
        server.shutdown()


if __name__ == "__main__":
    main()
