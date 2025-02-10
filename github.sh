#!/bin/bash
############################################
# AUTHOR : FAREED SAYED
# DATE : 01 OCTOBER 2024
# TO EXECUTE PROPER GITHUB
############################################

# Prompt for commit message
read -p "Enter commit message: " commit_message

# Check if the user provided a commit message
if [ -z "$commit_message" ]; then
    echo "Commit message cannot be empty. Exiting."
    exit 1
fi

# Prompt for branch name
read -p "Enter branch name: " branch_name

# Switch to the branch or create it if it doesn't exist
git checkout "$branch_name" || git checkout -b "$branch_name"

# Add changes to staging
git add .

# Commit changes with user-provided message
git commit -m "$commit_message"

# Pull latest changes from origin branch
git pull origin "$branch_name"

# Add changes to staging again (in case there were updates from pull)
git add .

# Commit changes again with user-provided message
git commit -m "$commit_message"

# Run detect-secrets scan
# echo "Running secret check..."
# if detect-secrets scan; then
#     echo "No secrets found."
# else
#     echo "Secrets detected! Please resolve the issues before pushing."
#     exit 1
# fi

# Push changes to origin branch
git push origin "$branch_name"