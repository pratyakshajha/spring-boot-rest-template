# Spring Boot Rest Template
A spring boot based REST API to use as a template for new projects. This is not intended to use as-is but rather is a starting point for a project needing REST APIs, some business logic and interacting with a SQL database.

There are following endpoints:
- `/actors`: A GET endpoint to fetch all actors paginated by limit and offset parameters 
- `/actors`: A POST endpoint to create a new actor
- `/actors/{id}`: A GET endpoint to search an actor by id
- `/actors/{id}`: A DELETE endpoint to delete an actor by id
- `/actors/{id}`: A PUT endpoint to update actor by id
- `/actors/{id}`: A PATCH endpoint to update only some fields of an actor by id

### Note
- More details of each endpoint can be found in the postman collection in the repo.
- OpenAPI spec is avaialable at `http://localhost:8080/v3/api-docs`
- Swagger UI is avaialable at `http://localhost:8080/swagger-ui/index.html`

# Prerequisites
- Java 8+
- Maven
- MySQL

# Tools
- IDE like eclipse, intellij idea, etc
- API testing tool like postman, soap ui, etc

# Running locally
1. Clone this repository and open it in the IDE of your choice.
2. [Install](https://dev.mysql.com/doc/sakila/en/sakila-installation.html) mySQL sample database having the actor table, if it doesn't exist
3. Build the project with maven `mvn clean package`
4. Run the `DemoApplication.java` with  `mvnw spring-boot:run`. 
	- This class can be run via an IDE
	- The war file in target folder can be deployed to a server

# Project structure
This project has layer architecture.
## What does a class do?
- `Actor`: POJO class representing an actor
- `ErrorResponse`: POJO class representing response in case of errors
- `ActorController`: RestController class with all the endpoints
- `GlobalControllerExceptionHandler`: Exception handler for all endpoints in controllers
- `ActorService`: Interface with business logic
- `ActorServiceImpl`: Service class implementing `ActorService`
- `ActorDao`: Interface for interacting with SQL databases
- `ActorDaoJdbc`: Service class implementing `ActorDao`
- `ActorRowMapper`: Maps the resulset of read operations from database
- `ActorDaoJdbcTest`: junit tests for `ActorDaoJdbc`

## What does a dependency do?
### Logic
These dependencies are essential for creating an API app which can do CRUD operations with a SQL database
- spring-boot-starter-web: Spring MVC and associated dependencies needed for a REST API
- spring-boot-starter-data-jdbc: Spring jdbc and related dependencies to connect and interact with SQL databases using JDBC
- mysql-connector-java: MySQL driver
- spring-boot-starter-test: Junit, Mockito and testing related dependencies to write unit tests
### Developer ease
These dependencies are optional but help with saving time and reducing repetitive work.
- spring-boot-starter-tomcat: Embedded tomcat server for quickly running app locally
- spring-boot-devtools: Developer tools with features like live reload, auto restart, etc 
- lombok: Auto generate getters, setters, tostring etc to reduce boilerplate from code. Also, [install](https://projectlombok.org/) extension for your IDE
- springdoc-openapi-ui: Auto generate open API spec and swagger UI to reduce efforts of documenting an API

