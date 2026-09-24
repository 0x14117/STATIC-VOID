@echo off
REM CYBER//OPS - compile and play, in one step.
setlocal

REM Work from the project folder, so every path below can be RELATIVE.
REM That is the whole trick, and it exists because of two separate problems
REM with javac argument files on Windows:
REM
REM   1. Unquoted lines are split on spaces, and this project usually lives
REM      somewhere like "OneDrive - Liverpool John Moores University".
REM   2. A backslash in an argument file is an ESCAPE character, so an
REM      absolute path like D:\New folder\STATIC-VOID\src\Main.java arrives
REM      as D:New folderSTATIC-VOIDsrcMain.java with the separators eaten.
REM
REM Relative paths written with forward slashes have neither problem: no
REM drive letter, no spaces, no backslashes. Windows accepts forward slashes
REM in paths perfectly well.
cd /d "%~dp0"

echo Compiling...

set "SOURCES=%TEMP%\cyberops-sources.txt"
if exist "%SOURCES%" del "%SOURCES%"
for %%f in (src\*.java) do echo src/%%~nxf>> "%SOURCES%"

if not exist "%SOURCES%" (
    echo.
    echo No .java files found in the src folder.
    echo.
    echo If this folder only contains README.md, it was cloned before the
    echo game was published. Update it with:
    echo.
    echo     git pull
    echo.
    pause
    exit /b 1
)

javac -d out "@%SOURCES%"

if errorlevel 1 (
    echo.
    echo Compile failed. Nothing was run.
    echo If javac was not found, you need a JDK: https://adoptium.net/
    echo.
    pause
    exit /b 1
)

echo.
java -cp out Main

endlocal
