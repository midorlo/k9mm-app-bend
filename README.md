# Wolkenbruch

Wolkenbruch is a basic file management service. It offers an access layer to the local file system as a RESTful api.

## Getting Started

### Obtain a copy

As there is currently no release management present, the recommenced way to obtain wolkenbruch is to use git:

```git clone https://github.com/midorlo/wolkenbruch.git```

### Building for Production

Wolkenbruch was mainly developed to acclimate the author with the Spring Boot ecosystem. It is **highly advised** not to
depend any productive environment on this service!

To build an artifact, simply run the default goal configured in the ``production``
profile.

```mvn -PProduction [-f pom.xml]```

### Setting up for development

Install the required dependencies and execute the annotation processor. (You can add the``clean`` goal to purge any
orphaned copies)

``mvn [clean] package``

Run the service by executing the spring-boot-maven plugin

``mvn spring-boot:run``

or simply the running the default goal

``mvn``

## Reference Information

### Dependency Stack

* Maven
    * Lombok
    * JSONWebToken
    * PostgreSQL
    * Spring Boot
        * Spring Data JPA
        * Spring Boot Actuator
        * Spring Boot DevTools
        * Spring Configuration Processor
        * Spring Web
        * Spring REST Repositories

### Compliance

* [Autowiring is bad!](https://dzone.com/articles/spring-di-patterns-the-good-the-bad-and-the-ugly)
* [How to not fuck up an Entity's .equals(),.hashCode()](https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/)
* [Semantic Versioning](https://devhints.io/semver)
* [Conventional Commits](https://www.conventionalcommits.org/en/v1.0.0/)

### Dependencies' Documentations

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.5.3/maven-plugin/reference/html/)
* [Spring Web](https://docs.spring.io/spring-boot/docs/2.5.3/reference/htmlsingle/#boot-features-developing-web-applications)
* [Spring Data JPA](https://docs.spring.io/spring-boot/docs/2.5.3/reference/htmlsingle/#boot-features-jpa-and-spring-data)
* [Spring Boot DevTools](https://docs.spring.io/spring-boot/docs/2.5.3/reference/htmlsingle/#using-boot-devtools)
* [Rest Repositories](https://docs.spring.io/spring-boot/docs/2.5.3/reference/htmlsingle/#howto-use-exposing-spring-data-repositories-rest-restMeta)
* [Spring Boot Actuator](https://docs.spring.io/spring-boot/docs/2.5.3/reference/htmlsingle/#production-ready)
* [Spring Configuration Processor](https://docs.spring.io/spring-boot/docs/2.5.3/reference/htmlsingle/#configuration-metadata-annotation-processor)

### Dependencies' Guides

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/bookmarks/)
* [Accessing Data with JPA](https://spring.io/guides/gs/accessing-data-jpa/)
* [Accessing JPA Data with REST](https://spring.io/guides/gs/accessing-data-rest/)
* [Building a RESTful Web Service with Spring Boot Actuator](https://spring.io/guides/gs/actuator-service/)

