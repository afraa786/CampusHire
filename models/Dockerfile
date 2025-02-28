# Use a base Python image
FROM python:3.12-slim

# Install system dependencies required for building Python packages
RUN apt-get update && apt-get install -y \
    gcc \
    g++ \
    build-essential \
    && rm -rf /var/lib/apt/lists/*

# Set working directory inside the container
WORKDIR /app

# Copy the requirements file and install Python dependencies
COPY requirements.txt /app/requirements.txt
RUN pip install --no-cache-dir -r /app/requirements.txt

# Copy the rest of the project files into the container
COPY . /app/

# Expose the FastAPI port
EXPOSE 8000

# Command to run the FastAPI app
CMD ["uvicorn", "my_api:app", "--host", "0.0.0.0", "--port", "8000", "--reload"]
