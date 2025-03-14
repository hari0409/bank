# Creating the Docker Image
docker build -t bank/accounts:latest .

# Running the Docker Containers
docker run -d -p 8080:8080 IMAGE_NAME