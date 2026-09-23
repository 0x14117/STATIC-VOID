@echo off
REM CYBER//OPS - compile and play, in one step.
REM
REM Java's own wildcard handling is inconsistent across Windows shells, so the
REM source files are listed into a file and handed to javac that way. It looks
REM like more work and it removes a whole class of "it works in cmd but not in
REM PowerShell" problems.

setlocal

echo Compiling...
dir /b /s "%~dp0src\*.java" > "%TEMP%\cyberops-sources.txt"
javac -d "%~dp0out" "@%TEMP%\cyberops-sources.txt"

if errorlevel 1 (
    echo.
    echo Compile failed. Nothing was run.
    echo If javac was not found, you need a JDK: https://adoptium.net/
    echo.
    pause
    exit /b 1
)

echo.
java -cp "%~dp0out" Main

endlocal
