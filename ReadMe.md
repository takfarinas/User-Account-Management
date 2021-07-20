# User Account Management

#Building project
To build project run the command: **mvn clean install** in the root project directory

## The module user-account-management-api :
- It is the entry point of the API.
- To launch it in run mode : 
    * **mvn spring-boot:run** (The command line should be run inside user-account-management-api directory)
# The tests
- the tests can be run with :
    * **mvn clean test**
- all the tests can be excluded during mvn builds with the flag **-DskipTests**
## The module user-account-management-service :
Contains all the logic related to user account management.

## Swagger 
To expose api contract to client, this Api is using Swagger.
http://localhost:8080/user-account-management/swagger-ui.html#




