@echo off
REM CYBER//OPS - compile and play, in one step.
setlocal

echo Compiling...

REM Build the list of source files, one per line, EACH ONE IN QUOTES.
REM
REM The quotes are the important part. This project is usually kept somewhere
REM like "OneDrive - Liverpool John Moores University", and javac's argument
REM files split every unquoted line on spaces - so that path would arrive as
REM three separate arguments and javac would reject the first one with
REM "invalid flag". Quoting each path keeps it as one argument.
set "SOURCES=%TEMP%\cyberops-sources.txt"
if exist "%SOURCES%" del "%SOURCES%"
for /r "%~dp0src" %%f in (*.java) do echo "%%f">> "%SOURCES%"

if not exist "%SOURCES%" (
    echo.
    echo No .java files found under the src folder.
    echo Are you running this from the project folder?
    echo.
    pause
    exit /b 1
)

javac -d "%~dp0out" "@%SOURCES%"

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
