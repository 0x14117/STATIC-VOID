(function () {
  "use strict";

  var missions = [];        // [{id, title, topic, topic_name, concept, teach, briefing, hints, boilerplate, inputs}]
  var completed = [];       // mission ids
  var currentMissionId = null;
  var hintsShown = 0;       // how many hints revealed for the current mission

  var registerScreen = document.getElementById("register-screen");
  var gameScreen = document.getElementById("game-screen");
  var codenameInput = document.getElementById("codename-input");
  var registerBtn = document.getElementById("register-btn");
  var agentName = document.getElementById("agent-name");
  var missionTitle = document.getElementById("mission-title");
  var teachText = document.getElementById("teach-text");
  var briefing = document.getElementById("briefing");
  var hintBtn = document.getElementById("hint-btn");
  var hintText = document.getElementById("hint-text");
  var codeEditor = document.getElementById("code-editor");
  var runBtn = document.getElementById("run-btn");
  var outputEl = document.getElementById("output");
  var cipherReaction = document.getElementById("cipher-reaction");
  var cipherAvatar = document.getElementById("cipher-avatar");
  var missionListEl = document.getElementById("mission-list");

  function getJSON(url) {
    return fetch(url).then(function (res) { return res.json(); });
  }

  function postJSON(url, data) {
    return fetch(url, {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(data),
    }).then(function (res) { return res.json(); });
  }

  function setMood(mood) {
    cipherAvatar.className = "mood-" + mood;
  }

  function missionById(id) {
    for (var i = 0; i < missions.length; i++) {
      if (missions[i].id === id) return missions[i];
    }
    return null;
  }

  function renderMissionList() {
    missionListEl.innerHTML = "";
    var topics = [];
    var byTopic = {};
    missions.forEach(function (m) {
      if (!byTopic[m.topic]) { byTopic[m.topic] = []; topics.push(m.topic); }
      byTopic[m.topic].push(m);
    });
    topics.sort(function (a, b) { return a - b; });

    topics.forEach(function (topicNum) {
      var group = byTopic[topicNum];
      var heading = document.createElement("div");
      heading.className = "tier-heading";
      heading.textContent = "TOPIC " + topicNum + ": " + group[0].topic_name.toUpperCase();
      missionListEl.appendChild(heading);

      group.forEach(function (m) {
        var btn = document.createElement("button");
        btn.className = "mission-btn";
        btn.textContent = m.title;
        if (m.id === currentMissionId) btn.classList.add("active");
        if (completed.indexOf(m.id) !== -1) btn.classList.add("completed");
        btn.addEventListener("click", function () { loadMission(m.id); });
        missionListEl.appendChild(btn);
      });
    });
  }

  function updateHintButton() {
    var mission = missionById(currentMissionId);
    var hints = (mission && mission.hints) || [];
    if (hintsShown >= hints.length) {
      hintBtn.disabled = true;
      hintBtn.textContent = hints.length === 0 ? "NO HINTS FOR THIS ONE" : "NO MORE HINTS";
    } else {
      hintBtn.disabled = false;
      hintBtn.textContent = hintsShown === 0 ? "SHOW HINT" : "SHOW NEXT HINT";
    }
  }

  hintBtn.addEventListener("click", function () {
    var mission = missionById(currentMissionId);
    if (!mission || hintsShown >= mission.hints.length) return;
    hintText.textContent += (hintsShown > 0 ? "\n" : "") + "HINT " + (hintsShown + 1) + ": " + mission.hints[hintsShown];
    hintsShown++;
    updateHintButton();
  });

  function loadMission(id) {
    var mission = missionById(id);
    if (!mission) return;
    currentMissionId = id;
    missionTitle.textContent = "MISSION: " + mission.title;
    teachText.textContent = mission.teach || "";
    if (mission.inputs && mission.inputs.length > 0) {
      briefing.textContent = mission.briefing + "\n\n[TEST INPUT VALUES, IN ORDER: " + mission.inputs.join(", ") + "]";
    } else {
      briefing.textContent = mission.briefing;
    }
    codeEditor.value = mission.boilerplate || "";
    outputEl.textContent = "";
    cipherReaction.textContent = "";
    hintsShown = 0;
    hintText.textContent = "";
    updateHintButton();
    setMood("calm");
    renderMissionList();
    codeEditor.focus();
  }

  registerBtn.addEventListener("click", function () {
    var codename = codenameInput.value.trim();
    if (!codename) return;
    postJSON("/register", { codename: codename }).then(function (data) {
      agentName.textContent = "AGENT " + data.codename;
      registerScreen.hidden = true;
      gameScreen.hidden = false;

      Promise.all([getJSON("/missions"), getJSON("/progress")]).then(function (results) {
        missions = results[0];
        completed = results[1].completed_missions || [];
        var firstIncomplete = missions.find(function (m) { return completed.indexOf(m.id) === -1; });
        loadMission((firstIncomplete || missions[0]).id);
      });
    });
  });

  codenameInput.addEventListener("keydown", function (e) {
    if (e.key === "Enter") registerBtn.click();
  });

  function runCode() {
    if (!currentMissionId) return;
    var code = codeEditor.value;
    runBtn.disabled = true;
    outputEl.textContent = "";
    cipherReaction.textContent = "Cipher: running...";
    setMood("calm");

    postJSON("/run", { mission_id: currentMissionId, code: code })
      .then(function (data) {
        outputEl.textContent = data.output || "";
        cipherReaction.textContent = "Cipher: " + data.cipher;
        setMood(data.passed ? "proud" : (data.ok ? "calm" : "angry"));

        if (data.passed && completed.indexOf(currentMissionId) === -1) {
          completed.push(currentMissionId);
          renderMissionList();
        }
      })
      .catch(function () {
        cipherReaction.textContent = "Cipher: connection to NeoCorp lost. Try again.";
        setMood("angry");
      })
      .finally(function () {
        runBtn.disabled = false;
      });
  }

  runBtn.addEventListener("click", runCode);
  codeEditor.addEventListener("keydown", function (e) {
    if (e.ctrlKey && e.key === "Enter") runCode();
  });
})();
