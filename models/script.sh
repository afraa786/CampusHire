#!/bin/bash 
# Update and upgrade system packages
echo "Updating system packages..."
sudo apt update && sudo apt upgrade -y

# Install Python, pip, and virtual environment
if command_exists python3 && command_exists pip3; then
    echo "Python and pip are already installed."
else
    echo "Installing Python, pip, and virtual environment..."
    sudo apt install python3
    sudo apt install python3-pip
fi

# creating the virtual environment
# Prompt for virtual environment name
while true; do
    read -p "Enter Virtual environment name: " virtualenv
    if [ -n "$virtualenv" ]; then
        echo "Virtual environment name set to: $virtualenv"
        break
    else
        echo "Virtualenv name can't be empty. Please try again."
    fi
done

# Create the Python virtual environment
echo "Creating a Python virtual environment..."
python3 -m venv "$virtualenv"
. $virtualenv/bin/activate

# Add the virtual environment directory to .gitignore
echo "Adding $virtualenv to .gitignore..."
if ! grep -qxF "$virtualenv" .gitignore; then
    echo "" >> .gitignore  # Add a blank line for separation (optional)
    echo "$virtualenv" >> .gitignore
    echo "$virtualenv has been added as a new line in .gitignore."
else
    echo "$virtualenv is already in .gitignore."
fi

# downloading the project requirements 
pip install -r requirements.txt
uvicorn my_api:app --reload
