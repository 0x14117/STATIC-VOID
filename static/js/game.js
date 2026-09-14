(function () {
  "use strict";

  var missions = [];        // [{id, title, stage, tier, concept, briefing}]
  var completed = [];       // mission ids
  var currentMissionId = null;

  var registerScreen = document.getElementById("register-screen");
  var gameScreen = document.getElementById("game-screen");
  var codenameInput = document.getElementById("codename-input");
  var registerBtn = document.getElementById("register-btn");
  var agentName = document.getElementById("agent-name");
  var missionTitle = document.getElementById("mission-title");
  var briefing = document.getElementById("briefing");
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
    var tiers = ["easy", "medium", "hard"];
    var byTier = { easy: [], medium: [], hard: [] };
    missions.forEach(function (m) { byTier[m.tier].push(m); });

    tiers.forEach(function (tier) {
      if (byTier[tier].length === 0) return;
      var heading = document.createElement("div");
      heading.className = "tier-heading";
      heading.textContent = tier.toUpperCase();
      missionListEl.appendChild(heading);

      byTier[tier].forEach(function (m) {
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

  function loadMission(id) {
    var mission = missionById(id);
    if (!mission) return;
    currentMissionId = id;
    missionTitle.textContent = "MISSION: " + mission.title;
    if (mission.inputs && mission.inputs.length > 0) {
      briefing.textContent = mission.briefing + "\n\n[TEST INPUT VALUES, IN ORDER: " + mission.inputs.join(", ") + "]";
    } else {
      briefing.textContent = mission.briefing;
    }
    codeEditor.value = "";
    outputEl.textContent = "";
    cipherReaction.textContent = "";
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
