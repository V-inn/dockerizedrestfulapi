# This is a docker-ready RESTFul API Boilerplate for easy backend and database setup

## Note: This software was built based on [MotoCode's tutorials on Youtube](https://www.youtube.com/@MotoCode)

## REQUIREMENTS

This app is built on Java JDK 17 with Spring framework

1. `Maven` installed with `mvn` cmdlet on console
2. `Docker` installed and running
3. `Java JDK 17`

## SETUP

For setup you are gonna want to change some things in the code for security concerns.
1. In the `compose.yaml` file you will see under the java_db section and environment sub-section variables related to `USER, PASSWORD and DB`. USER and PASSWORD will be your login credentials for the database (Changing DB name is optional).
2. Change DATABASE_USERNAME and DATABASE_PASSWORD to the same values in the rest_api/environment section.
3. Under rest_api section you will find `image` this should be changed to preferably your docker username followed by `/rest-api:1.0.0`.

## BUILD

For building the app you will run a set of commands to build the application and the docker image:
```
mvn clean package -DskipTests
docker compose build rest_api
docker compose up rest_api
```

Note: It may happen that running the application with `docker compose up` might fail the first time, as it relies on the database connection, re-running it should work

## CONSUMING THE API

As this is a boilerplate and a functional app, you can change the functions to suit your purpose the best.

To use the default application, you will first need to do some changes on SecurityConfig class:

```
.requestMatchers(HttpMethod.POST, "/users/login").permitAll()
.anyRequest().authenticated());
```

Will be changed temporarily to:

```
.anyRequest().permitAll();
```

The purpose of this is adding a starter User to the database, because you need to be logged in and be in possession of your **Bearer Token**
After changing this, you can build a frontend for the API, or for testing purposes use **POSTMAN** to send Http Requests, and so edit the users in the database

