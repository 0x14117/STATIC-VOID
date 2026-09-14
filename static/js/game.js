(function () {
  "use strict";

  var CURRENT_MISSION = "s1e1";
  var BRIEFINGS = {
    s1e1: "NeoCorp's outer gate checks for one exact signal. Print ACCESS GRANTED to pass through.",
  };

  var registerScreen = document.getElementById("register-screen");
  var gameScreen = document.getElementById("game-screen");
  var codenameInput = document.getElementById("codename-input");
  var registerBtn = document.getElementById("register-btn");
  var cipherLine = document.getElementById("cipher-line");
  var agentName = document.getElementById("agent-name");
  var missionTitle = document.getElementById("mission-title");
  var briefing = document.getElementById("briefing");
  var codeEditor = document.getElementById("code-editor");
  var runBtn = document.getElementById("run-btn");
  var outputEl = document.getElementById("output");
  var cipherReaction = document.getElementById("cipher-reaction");
  var cipherAvatar = document.getElementById("cipher-avatar");

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

  registerBtn.addEventListener("click", function () {
    var codename = codenameInput.value.trim();
    if (!codename) return;
    postJSON("/register", { codename: codename }).then(function (data) {
      agentName.textContent = "AGENT " + data.codename;
      missionTitle.textContent = "MISSION: " + CURRENT_MISSION;
      briefing.textContent = BRIEFINGS[CURRENT_MISSION];
      registerScreen.hidden = true;
      gameScreen.hidden = false;
      codeEditor.focus();
    });
  });

  codenameInput.addEventListener("keydown", function (e) {
    if (e.key === "Enter") registerBtn.click();
  });

  function runCode() {
    var code = codeEditor.value;
    runBtn.disabled = true;
    outputEl.textContent = "";
    cipherReaction.textContent = "Cipher: running...";
    setMood("calm");

    postJSON("/run", { mission_id: CURRENT_MISSION, code: code })
      .then(function (data) {
        outputEl.textContent = data.output || "";
        cipherReaction.textContent = "Cipher: " + data.cipher;
        setMood(data.passed ? "proud" : (data.ok ? "calm" : "angry"));
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
