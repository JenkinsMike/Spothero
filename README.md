# Spotty Sidekick API

## What is my purpose?
This is a little SpringBoot API that quotes Parking Rates for given data.
It allows a user to enter a date-time range and get back a price that their park would cost.
A user can also put new Parking Rate data to alter rates or pull the current Parking Rate data.

## Endpoints
- /v1/rates (PUT)
  - Request is a JSON element comprised of predetermined "Rate" information.
- /v1/rates (GET)
  - The Request has no Request Params.
  - It returns the current Parking Rate data.
- /v1/price (GET)
  - The Request has two Request Params, "start" and "end".
  - It returns the current Price that the date-time range based on Parking Rate data.
    - Shortly, the only way to get a valid price is to make sure your request is for 1) one day, 2) less than 24h, and 3) must only resolve to one Parking Rate

All particulars can be found on the API's Swagger page.
There is also a lot of data you can find on the actuator, including Health Data and Metrics.
- /explorer/index.html#uri=/actuator 
- /explorer/index.html#uri=/actuator/health
- /explorer/index.html#uri=http://localhost:5000/actuator/metrics
  - I mean there is like, a ton of Metrics...
  
## Getting Started
All that is necessary to get the app spinning up is, once you load this application into the IDE editor of choice (Choose IntelliJ), just hit play.
Boot up the API, and navigate your browser of choice (no strong opinions there) to http://localhost:5000/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config#/ and fire away.
You may also test with your API Testing tool of choice (Postman Canary).  For articulate setup instructions, email the address found on the Swagger site.


### Reference Documentation
For further reference, please consider the following sections:
* [Official Gradle documentation](https://docs.gradle.org)
* [Spring Boot Gradle Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.5.6/gradle-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.5.6/gradle-plugin/reference/html/#build-image)
* [Spring Boot DevTools](https://docs.spring.io/spring-boot/docs/2.5.6/reference/htmlsingle/#using-boot-devtools)
* [Spring Configuration Processor](https://docs.spring.io/spring-boot/docs/2.5.6/reference/htmlsingle/#configuration-metadata-annotation-processor)
* [Spring Web](https://docs.spring.io/spring-boot/docs/2.5.6/reference/htmlsingle/#boot-features-developing-web-applications)
* [Rest Repositories](https://docs.spring.io/spring-boot/docs/2.5.6/reference/htmlsingle/#howto-use-exposing-spring-data-repositories-rest-endpoint)
* [Spring Data JPA](https://docs.spring.io/spring-boot/docs/2.5.6/reference/htmlsingle/#boot-features-jpa-and-spring-data)
* [Flyway Migration](https://docs.spring.io/spring-boot/docs/2.5.6/reference/htmlsingle/#howto-execute-flyway-database-migrations-on-startup)
* [Validation](https://docs.spring.io/spring-boot/docs/2.5.6/reference/htmlsingle/#boot-features-validation)
* [Spring Boot Actuator](https://docs.spring.io/spring-boot/docs/2.5.6/reference/htmlsingle/#production-ready)

### Guides
The following guides illustrate how to use some features concretely:
* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/bookmarks/)
* [Accessing JPA Data with REST](https://spring.io/guides/gs/accessing-data-rest/)
* [Accessing Neo4j Data with REST](https://spring.io/guides/gs/accessing-neo4j-data-rest/)
* [Accessing MongoDB Data with REST](https://spring.io/guides/gs/accessing-mongodb-data-rest/)
* [Accessing Data with JPA](https://spring.io/guides/gs/accessing-data-jpa/)
* [Building a RESTful Web Service with Spring Boot Actuator](https://spring.io/guides/gs/actuator-service/)

### Additional Links
These additional references should also help you:
* [Gradle Build Scans – insights for your project's build](https://scans.gradle.com#gradle)

### Key Components
    | SpringBoot | Gradle    |  Kotlin   | RESTful API |
    | Swagger    | Hibernate |  IntelliJ | H2 Database |

<br><br><br>
###### (overly) Informational
Some tests are present, but test coverage is not where it ought to be.  I would say Test Suite enhancement is something 
that should be taken as a constant refactoring idea.  

Personally, projects I have been on used JUnit4.  Here, I tried to write them in JUnit5.  Just in case the need was 
there, though, JUnit4 WAS available.

All the ways I thought to set up some mocking gave me some pushback.  I got the set-up to a point where I was no longer 
encountering errors, but not much farther.  The testing here needs work, and I can acknowledge that.  However, at this 
point with a Holiday coming up and me about to be the only Dev on my team for about a month, I will not have a lot of 
time after this to focus on Tests.  At this point, I am turning in what I have and, if this were live code, I would 
have asked more tenured people on the team how they would set up the tests and take guidance.

Setups are there.  Comments are everywhere in the code.

# Thank you for your time and consideration!
## Cheers!
### Mike Jenkins