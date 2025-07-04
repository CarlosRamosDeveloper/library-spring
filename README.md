# Library-spring: Dockerized Version

This project is still in development

## Setup

1. Create .jar
```
    ./mvnw clean package -DskipTests
```
2. Clean old containers
```
    docker-compose down
```
3. Build containers
```
    docker-compose build
```
4. Start Virtualization
```
    docker-compose up
```

## Stack
### Backend: Spring boot
### Database: MySQL
### Virtualization: Docker Compose