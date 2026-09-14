"""
NULL SECTOR — game server.

Pure stdlib, per the Master Document's open-source constraint (no Flask,
no pip requirements). Serves the static frontend and exposes:

  POST /register   {codename}            -> save agent, return greeting
  GET  /progress                         -> current save
  POST /run         {mission_id, code}   -> sandboxed execution + mission check

Run: python3 server.py [port]   (default port 5000)
"""

import json
import os
import sys
from http.server import BaseHTTPRequestHandler, ThreadingHTTPServer

from missions import check_mission
from sandbox import run_sandboxed

BASE_DIR = os.path.dirname(os.path.abspath(__file__))
STATIC_DIR = os.path.join(BASE_DIR, "static")
SAVES_DIR = os.path.join(BASE_DIR, "saves")
SAVE_FILE = os.path.join(SAVES_DIR, "progress.json")

CONTENT_TYPES = {
    ".html": "text/html; charset=utf-8",
    ".css": "text/css; charset=utf-8",
    ".js": "application/javascript; charset=utf-8",
    ".json": "application/json; charset=utf-8",
}


def _load_progress():
    if os.path.exists(SAVE_FILE):
        try:
            with open(SAVE_FILE, "r") as f:
                return json.load(f)
        except (json.JSONDecodeError, OSError):
            pass
    return {"codename": None, "completed_missions": []}


def _save_progress(data):
    os.makedirs(SAVES_DIR, exist_ok=True)
    with open(SAVE_FILE, "w") as f:
        json.dump(data, f, indent=2)


class Handler(BaseHTTPRequestHandler):
    server_version = "NullSector/0.1"

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
        raw = self.rfile.read(length)
        return json.loads(raw.decode("utf-8"))

    def _serve_static(self, path):
        if path == "/":
            path = "/index.html"

        # Resolve against STATIC_DIR and refuse to leave it (path traversal guard).
        safe_rel = path.lstrip("/")
        full_path = os.path.realpath(os.path.join(STATIC_DIR, safe_rel))
        if not full_path.startswith(os.path.realpath(STATIC_DIR) + os.sep) and full_path != os.path.realpath(STATIC_DIR):
            self.send_error(403, "Forbidden")
            return
        if not os.path.isfile(full_path):
            self.send_error(404, "Not Found")
            return

        ext = os.path.splitext(full_path)[1]
        content_type = CONTENT_TYPES.get(ext, "application/octet-stream")
        with open(full_path, "rb") as f:
            body = f.read()
        self.send_response(200)
        self.send_header("Content-Type", content_type)
        self.send_header("Content-Length", str(len(body)))
        self.end_headers()
        self.wfile.write(body)

    def do_GET(self):
        if self.path == "/progress":
            self._send_json(200, _load_progress())
            return
        self._serve_static(self.path)

    def do_POST(self):
        try:
            body = self._read_json_body()
        except (json.JSONDecodeError, UnicodeDecodeError):
            self._send_json(400, {"error": "Invalid JSON body."})
            return

        if self.path == "/register":
            self._handle_register(body)
        elif self.path == "/run":
            self._handle_run(body)
        else:
            self.send_error(404, "Not Found")

    def _handle_register(self, body):
        codename = str(body.get("codename", "")).strip()
        if not codename:
            self._send_json(400, {"error": "Codename required."})
            return
        progress = _load_progress()
        progress["codename"] = codename
        _save_progress(progress)
        self._send_json(200, {
            "codename": codename,
            "cipher": "Agent {} — NeoCorp database has no record of you. Good. Let's keep it that way.".format(codename),
        })

    def _handle_run(self, body):
        mission_id = body.get("mission_id")
        code = body.get("code", "")

        if not mission_id:
            self._send_json(400, {"error": "mission_id required."})
            return
        if not isinstance(code, str):
            self._send_json(400, {"error": "code must be a string."})
            return

        result = run_sandboxed(code)

        if result["blocked"]:
            self._send_json(200, {
                "ok": False,
                "passed": False,
                "output": "",
                "error": result["error"],
                "cipher": result["error"]["message"],
            })
            return

        if result["timeout"]:
            self._send_json(200, {
                "ok": False,
                "passed": False,
                "output": "",
                "error": result["error"],
                "cipher": result["error"]["message"],
            })
            return

        if not result["ok"]:
            self._send_json(200, {
                "ok": False,
                "passed": False,
                "output": result["output"],
                "error": result["error"],
                "cipher": "{}: {}".format(result["error"]["type"], result["error"]["message"]),
            })
            return

        passed, reason = check_mission(mission_id, code, result["output"])
        if passed:
            progress = _load_progress()
            if mission_id not in progress["completed_missions"]:
                progress["completed_missions"].append(mission_id)
                _save_progress(progress)

        self._send_json(200, {
            "ok": True,
            "passed": passed,
            "output": result["output"],
            "error": None,
            "cipher": reason,
        })

    def log_message(self, fmt, *args):
        sys.stderr.write("{} - {}\n".format(self.address_string(), fmt % args))


def main():
    port = int(sys.argv[1]) if len(sys.argv) > 1 else 5000
    server = ThreadingHTTPServer(("127.0.0.1", port), Handler)
    print("NULL SECTOR server running at http://localhost:{}".format(port))
    try:
        server.serve_forever()
    except KeyboardInterrupt:
        server.shutdown()


if __name__ == "__main__":
    main()
