@echo off
:: Update: Windows doesn't have a direct `apt update` equivalent. Ensure system is updated via Windows Update manually.
echo "Ensure your system is up-to-date via Windows Update."

:: Check for Python installation
python --version >nul 2>&1
if %ERRORLEVEL% neq 0 (
    echo "Python is not installed. Please install Python (https://www.python.org/downloads/) and ensure it is added to PATH."
    exit /b
)
echo "Python is already installed."

:: Check for pip installation
pip --version >nul 2>&1
if %ERRORLEVEL% neq 0 (
    echo "Pip is not installed. Please install Pip."
    exit /b
)
echo "Pip is already installed."

:: Prompt for virtual environment name
:prompt_virtualenv
set /p virtualenv="Enter Virtual environment name: "
if "%virtualenv%"=="" (
    echo "Virtualenv name can't be empty. Please try again."
    goto :prompt_virtualenv
)
echo "Virtual environment name set to: %virtualenv%"

:: Create the Python virtual environment
echo "Creating a Python virtual environment..."
python -m venv "%virtualenv%"
call "%virtualenv%\Scripts\activate.bat"


:: Add the virtual environment directory to .gitignore
echo "Adding %virtualenv% to .gitignore..."
if not exist .gitignore (
    echo ".gitignore file does not exist. Creating one..."
    echo %virtualenv% > .gitignore
) else (
    findstr /x /c:"%virtualenv%" .gitignore >nul 2>&1
    if %ERRORLEVEL% neq 0 (
        echo.>> .gitignore
        echo %virtualenv%>> .gitignore
        echo "%virtualenv% has been added to .gitignore."
    ) else (
        echo "%virtualenv% is already in .gitignore."
    )
)

:: Install requirements
pip install -r requirements.txt

:: Run the application
echo "Starting the application..."
uvicorn my_api:app --reload
pause
