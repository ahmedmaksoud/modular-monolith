# Monolithic Modular (Spring Boot Modulith)

A pragmatic **modular monolith** built with **Spring Boot 3.5.5**, **Spring Modulith 1.4.1**, **Java 21**, **PostgreSQL**, and **Flyway**.
Using DDD, clean Archticure, technology-agnostic and framework-agnostic 

## ✨ Tech stack
- Java 21
- Spring Boot 3.5.5
- Spring Modulith 1.4.1
- PostgreSQL + Flyway
- JPA/Hibernate
- Packaging: jar

## 🚀 Getting started
1. Clone:
```bash
git clone https://github.com/ahmedmaksoud/monolithic-modular.git
cd monolithic-modular
```
2. Run Postgres (see docker-compose.yml)
3. Configure `src/main/resources/application.yml` (default schema `school`, port `8085`)
4. Run migrations:
```bash
mvn -DskipTests flyway:migrate
```
5. Build & run:
```bash
mvn clean package
mvn spring-boot:run
# or
java -jar target/monolithic-modular-*.war
```


## 🧪 Testing
```bash
mvn test
```

## 📁 Migrations
Place Flyway SQL files under `src/main/resources/db/migration`.

## Components
<img width="878" height="569" alt="image" src="https://github.com/user-attachments/assets/51c8a9cb-c232-4de4-81fb-46acede999f7" />


<img width="5304" height="4240" alt="image" src="https://github.com/user-attachments/assets/8ad1d2ed-a4bd-4010-b341-946d722fe4f1" />

