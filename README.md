Spring Boot Coding Dojo
---
Hello!

I have added a system to check how old is a WeatherEntity from the database and update the data only if the interval of time has passed. In this way I believe we can make the app more scalable by limiting the number of external requests we make. I have set it by default to 10 minutes, but it is configurable from application-prod.yml *weather.update.interval*

I have used lombok to simplify the Objects

I have removed the schema.sql because hibernate can generate the db on its own.
Could have implemented a flyway mechanism to migrate the database and update it based on version.
Also, could have implemented H2 in memory database for tests.

used @ControllerAdvice and ResponseEntityExceptionHandler to handle ugly 5XX errors and throw nice ResponseEntity 4XX errors

used @Validated to validate the "city" input

also created a TimeProvider for decoupling of code and easier testing, this is not tested, but I could have tested it with PowerMock

How to run:
---
### 1. provide your own appid from [OpenWeatherMap](https://home.openweathermap.org/api_keys) should be set in application-prod.yml
...\coding-dojo-spring-boot-master\src\main\resources\application-prod.yml


### 1.1 set properties according to prod environment in docker-compose.yml i just left them there for convenience
      - SPRING_DATASOURCE_URL=
      - SPRING_DATASOURCE_USERNAME= same_user
      - SPRING_DATASOURCE_PASSWORD= same_pwd
      - POSTGRES_USER= same_user
      - POSTGRES_PASSWORD= same_pwd

### 2. Go to root of the app
cd ...\coding-dojo-spring-boot-master\
### 3. create the .jar
mvn clean install
### 4. run the app + db
docker-compose up
### 5. test the app on port 8080
http://localhost:8080/weather?city=Bucharest
RestResponseEntityExceptionHandler for some reasons works fine on local and throws only 4XX errors but on docker for some reason throws 5XX errors. I believe it is some docker config, but not sure.