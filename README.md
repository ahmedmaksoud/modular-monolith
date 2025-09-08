# Monolithic Modular (Spring Boot Modulith)

A pragmatic **modular monolith** built with **Spring Boot 3.5.5**, **Spring Modulith 1.4.1**, **Java 21**, **PostgreSQL**, and **Flyway**.

## ✨ Tech stack
- Java 21
- Spring Boot 3.5.5
- Spring Modulith 1.4.1
- PostgreSQL + Flyway
- JPA/Hibernate
- Packaging: WAR

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

## 🧾 License
MIT (if present).
