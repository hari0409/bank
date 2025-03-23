.PHONY: db stop_db clean_db
# Default target: runs the Spring Boot application

# Target to start the MySQL Docker container
db:
	docker run -d \
	--name springboot-mysql \
	-e MYSQL_ROOT_PASSWORD=admin \
	-e MYSQL_USER=admin \
	-e MYSQL_PASSWORD=admin \
	-p 3306:3306 \
	-v ./data:/var/lib/mysql \
	mysql:latest
	@echo "Waiting for MySQL to start..."
	@until docker exec springboot-mysql mysqladmin -u root -padmin ping >/dev/null 2>&1; do \
		echo "Waiting for MySQL to be ready..."; \
		sleep 2; \
	done
	docker exec -it springboot-mysql mysql -u root -padmin -e "CREATE DATABASE accountsDB; CREATE DATABASE loansDB; CREATE DATABASE cardsDB;"

# Target to stop the Docker container
stop_db:
	docker stop springboot-mysql && docker rm springboot-mysql

# Target to clean up database data
clean_db:
	rm -rf /Users/haribaskars/Development/Java/revise/data/*