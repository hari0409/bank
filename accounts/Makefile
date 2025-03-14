# Makefile for bank accounts service

# Variables
DOCKER_IMAGE=bank/account:latest

# Targets
.PHONY: run clean-install build-image

start:
	@echo "Running Spring Boot application..."
	@mvn spring-boot:run

clean-install:
	@echo "Running Maven clean install..."
	@mvn clean install

build:
	@echo "Building Docker image for the account service..."
	@docker build -t $(DOCKER_IMAGE) .

run:
	@echo "Running the Account Service"
	@docker run -d -p 8080:8080 bank/account:latest