(function () {
  "use strict";

  var labs = [];        // [{id, chapter, title, learn, matters, explain, example, recap, tasks}]
  var done = [];        // ["ch03-lab02/t1", ...]
  var labId = null;
  var taskId = null;
  var hintsShown = 0;

  function $(id) { return document.getElementById(id); }

  function getJSON(url) {
    return fetch(url).then(function (r) { return r.json(); });
  }

  function postJSON(url, data) {
    return fetch(url, {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(data)
    }).then(function (r) { return r.json(); });
  }

  function lab() {
    for (var i = 0; i < labs.length; i++) if (labs[i].id === labId) return labs[i];
    return null;
  }

  function task() {
    var l = lab();
    if (!l) return null;
    for (var i = 0; i < l.tasks.length; i++) if (l.tasks[i].id === taskId) return l.tasks[i];
    return null;
  }

  function key(lid, tid) { return lid + "/" + tid; }
  function isDone(lid, tid) { return done.indexOf(key(lid, tid)) !== -1; }

  /* ---------------- navigation ---------------- */

  function renderNav() {
    var nav = $("nav");
    nav.innerHTML = "";
    var lastChapter = null;

    labs.forEach(function (l) {
      if (l.chapter !== lastChapter) {
        var h = document.createElement("div");
        h.className = "nav-chapter";
        h.textContent = "Chapter " + l.chapter + " — " + l.chapter_title;
        nav.appendChild(h);
        lastChapter = l.chapter;
      }

      var labHeading = document.createElement("div");
      labHeading.className = "nav-lab";
      var doneCount = l.tasks.filter(function (t) { return isDone(l.id, t.id); }).length;
      labHeading.textContent = l.title + "  (" + doneCount + "/" + l.tasks.length + ")";
      nav.appendChild(labHeading);

      l.tasks.forEach(function (t, i) {
        var btn = document.createElement("button");
        btn.className = "nav-task" + (l.id === labId && t.id === taskId ? " active" : "");
        btn.innerHTML = (isDone(l.id, t.id) ? '<span class="tick">&#10003;</span> ' : "&nbsp;&nbsp; ")
                      + "Task " + (i + 1) + " — " + t.role;
        btn.addEventListener("click", function () { open(l.id, t.id); });
        nav.appendChild(btn);
      });
    });
  }

  /* ---------------- rendering ---------------- */

  function open(newLabId, newTaskId) {
    var switchingLab = newLabId !== labId;
    labId = newLabId;
    taskId = newTaskId;
    hintsShown = 0;

    var l = lab(), t = task();
    if (!l || !t) return;

    if (switchingLab) {
      $("lab-chapter").textContent = "Chapter " + l.chapter + " — " + l.chapter_title;
      $("lab-title").textContent = l.title;
      $("lab-idea").textContent = l.idea;
      $("lab-learn").textContent = l.learn;
      $("lab-matters").textContent = l.matters;
      $("lab-explain").textContent = l.explain;
      $("lab-example").textContent = l.example;

      var recap = $("lab-recap");
      recap.innerHTML = "";
      l.recap.forEach(function (line) {
        var li = document.createElement("li");
        li.textContent = line;
        recap.appendChild(li);
      });
    }

    var index = l.tasks.indexOf(t) + 1;
    $("task-title").textContent = "Task " + index + " of " + l.tasks.length;
    $("task-role").textContent = t.role;
    $("task-brief").textContent = t.brief;

    if (t.inputs && t.inputs.length) {
      $("task-inputs").textContent = "Test input fed to your program, in order: " + t.inputs.join("  |  ");
      $("task-inputs").hidden = false;
    } else {
      $("task-inputs").hidden = true;
    }

    $("editor").value = t.starter;
    $("output").textContent = "";
    $("hints").innerHTML = "";
    $("feedback").innerHTML = "";
    $("explanation").hidden = true;
    $("hint").disabled = false;
    $("hint").textContent = "Show a hint";

    renderNav();
    window.scrollTo(0, 0);
  }

  function nextTask() {
    var l = lab();
    var i = l.tasks.indexOf(task());
    if (i + 1 < l.tasks.length) {
      open(labId, l.tasks[i + 1].id);
      return;
    }
    var labIndex = labs.indexOf(l);
    if (labIndex + 1 < labs.length) {
      var nl = labs[labIndex + 1];
      open(nl.id, nl.tasks[0].id);
    }
  }

  /* ---------------- running ---------------- */

  function run() {
    var t = task();
    if (!t) return;

    $("run").disabled = true;
    $("output").textContent = "Compiling and running...";
    $("feedback").innerHTML = "";
    $("explanation").hidden = true;

    postJSON("/run", { lab_id: labId, task_id: taskId, code: $("editor").value })
      .then(function (data) {
        $("output").textContent = data.output || "(no output)";

        var box = document.createElement("div");
        box.className = "result " + (data.passed ? "pass" : "fail");
        box.textContent = data.passed ? "Correct." : data.feedback;
        $("feedback").appendChild(box);

        if (data.passed) {
          if (!isDone(labId, taskId)) done.push(key(labId, taskId));
          $("explanation-text").textContent = data.explanation || "";
          $("explanation").hidden = false;
          renderNav();
        }
      })
      .catch(function () {
        $("output").textContent = "Could not reach the server. Is it still running?";
      })
      .finally(function () { $("run").disabled = false; });
  }

  /* ---------------- events ---------------- */

  $("hint").addEventListener("click", function () {
    var t = task();
    if (!t || hintsShown >= t.hints.length) return;
    var div = document.createElement("div");
    div.className = "hint";
    div.textContent = "Hint " + (hintsShown + 1) + ": " + t.hints[hintsShown];
    $("hints").appendChild(div);
    hintsShown++;
    if (hintsShown >= t.hints.length) {
      $("hint").disabled = true;
      $("hint").textContent = "No more hints";
    } else {
      $("hint").textContent = "Show the next hint";
    }
  });

  $("reset").addEventListener("click", function () {
    var t = task();
    if (t) $("editor").value = t.starter;
  });

  $("run").addEventListener("click", run);
  $("next").addEventListener("click", nextTask);

  $("editor").addEventListener("keydown", function (e) {
    if (e.ctrlKey && e.key === "Enter") { e.preventDefault(); run(); }
    // A tab in a code editor should indent, not jump to the next control.
    if (e.key === "Tab") {
      e.preventDefault();
      var start = this.selectionStart;
      this.value = this.value.slice(0, start) + "    " + this.value.slice(this.selectionEnd);
      this.selectionStart = this.selectionEnd = start + 4;
    }
  });

  $("start-btn").addEventListener("click", function () {
    var name = $("name").value.trim();
    if (!name) return;
    postJSON("/register", { name: name }).then(function () {
      $("who").textContent = name;
      $("start").hidden = true;
      $("app").hidden = false;

      Promise.all([getJSON("/labs"), getJSON("/progress")]).then(function (r) {
        labs = r[0];
        done = r[1].completed || [];
        if (!labs.length) {
          $("nav").textContent = "No labs are installed yet.";
          return;
        }
        // Resume at the first unfinished task.
        for (var i = 0; i < labs.length; i++) {
          for (var j = 0; j < labs[i].tasks.length; j++) {
            if (!isDone(labs[i].id, labs[i].tasks[j].id)) {
              open(labs[i].id, labs[i].tasks[j].id);
              return;
            }
          }
        }
        open(labs[0].id, labs[0].tasks[0].id);
      });
    });
  });

  $("name").addEventListener("keydown", function (e) {
    if (e.key === "Enter") $("start-btn").click();
  });
})();
