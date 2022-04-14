# Breweries_US

This app contains statistics on breweries in the USA

### How to run application

1. Make sure you have installed JDK 17 and Docker;
2. Clone this project;
3. Make sure you have a free port ``` 3307 ``` because in the next step we will use it;
4. In your terminal run this command. It will start MySQL instance, that will be available on port 3307:
``` 
docker run -p 0.0.0.0:3307:3306 --name breweries -e MYSQL_ROOT_PASSWORD=toor -e MYSQL_DATABASE=breweries_db -d mysql:8.0.25
```
5. Go to application folder, and run this command in your terminal:
``` 
./mvnw spring-boot:run -DskipTests
```
<br></br>
**Please check logs -  they will contain answers from the task.** 

*\***The interface BreweriesRepo contains different ways to get data from DB. This was done intentionally to show the skill and knowledge of writing native SQL queries, JPQL and JPA as well.*

<br></br>
### In case of problem with running

1. Check if your Maven can see and is attached to JDK 17:


![2022_04_14_0ei_Kleki](https://user-images.githubusercontent.com/87810252/163328669-2c1f4ee4-6aaf-46fa-80de-25a2eb0a0bd5.png)

If you see other version than 17, it may cause problem with running.
<br></br>
In this situation the simpliest way to run, is start application from the development environment (e. g. IntelliJ IDEA).
Don't forget to start the database instance (step 4) before starting the application.

![image](https://user-images.githubusercontent.com/87810252/163330854-eda3ba9e-96e8-46e6-991c-cfa84a092827.png)
