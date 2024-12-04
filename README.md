# Getting Started

### Web Client Invocation Instructions
* Executing application
    * > ./gradlew clean bootRun 

* Stopping application
    * Press CTRL+C to stop the server

* [Invoke client from browser](http://localhost:8085/swagger-ui.html)

* create a new user
    * POST /users with input JSON: {"email": "new@user.com", "firstName": "Dummy", "lastName": "User", "birthYear": 1990, "roles": ["dummies"]}
* get first 10 users
    * GET /users?page=0&size=10
* get user with email: jill@demo.com
    * GET /users/jill@demo.com
* get first 3 users with last name: Janes
    * GET /users/search/byLastName/Janes?page=0&size=3
* get first 7 users born in the 90s
    * GET /users/search/byBirthYear/1990/1999?page=0&size=7
* update first name of user with email: jane@demo.com
    * PUT /users/jane@demo.com with input JSON: {"firstName":"NewName"}
* delete all users
    * DELETE /users

### Reference Documentation
For further reference, please consider the following sections:

* [Official Gradle documentation](https://docs.gradle.org)
* [Spring Boot Gradle Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/3.2.1/gradle-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/3.2.1/gradle-plugin/reference/html/#build-image)
* [Spring Web](https://docs.spring.io/spring-boot/docs/3.2.1/reference/htmlsingle/index.html#web)

### Guides
The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/rest/)

### Additional Links
These additional references should also help you:

* [Gradle Build Scans – insights for your project's build](https://scans.gradle.com#gradle)

