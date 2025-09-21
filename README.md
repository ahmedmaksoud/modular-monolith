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
<img width="1525" height="992" alt="image" src="https://github.com/user-attachments/assets/0db440e9-9198-42d9-a702-70840987be27" />


## Physical Design
##Domain

** Model: Pure business objects (POJOs in Java) that are independent of technical concerns. They represent the core domain concepts.
  
** Services: Define and implement business use cases that orchestrate domain logic.
  
** Events: Represent significant occurrences within the domain (“something happened”).
  
** Contracts: Interfaces that specify rules and expectations, ensuring that implementers adhere to defined behaviors.

** Buisness Rules Engin: validate business actoions

##Infrastructure

** Database Entities: Persistence-oriented representations of domain objects, optimized for relational databases.

** Mappers: Converters between domain models and database entities .

** JPA Repositories: Abstractions that expose domain-friendly access methods, hiding persistence complexity.

** Repositories:  Spring Data JPA interfaces for basic CRUD persistence operations 

** Gateways: Interfaces to external systems (e.g., APIs, ..).

##Application Services (Orchestrators): 

** Coordinate domain operations across aggregates and services.

** Persist database entities through repositories.

** Manage transactions consistently.

** Publish events to message queues or other external systems.

** Act as a bridge between domain logic and infrastructure.

<img width="994" height="1192" alt="image" src="https://github.com/user-attachments/assets/7ca1d4d2-caa1-44e7-a788-356081be57e6" />







<img width="5304" height="4240" alt="image" src="https://github.com/user-attachments/assets/8ad1d2ed-a4bd-4010-b341-946d722fe4f1" />

