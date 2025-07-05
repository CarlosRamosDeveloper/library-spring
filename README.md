# Library-spring: Dockerized Version

This project is still in development

## * Setup

1. Create .jar

    ``` bash

        ./mvnw clean package -DskipTests
    ```

2. Clean old containers

    ``` bash
        docker-compose down
    ```

3. Build containers

    ``` bash
        docker-compose build
    ```

4. Start Virtualization

    ``` bash
        docker-compose up
    ```

## Stack

- Backend: Spring Boot 3
- Database: MySQL
- Virtualization: Docker

## TODOs

- Backend
  - Add validations to endpoints
  - Add tests
  - Add endpoint documentation
  - Add security
  - Add rate limiting
- Frontend: React
