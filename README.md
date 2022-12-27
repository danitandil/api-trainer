# trainer-api
Trainer API using Spring Boot

This API has 3 endpoints:
* Create trainer
* List a trainer according to the received id parameter .
* List all the created trainers.

In order to use this API as local way, you have to follow these steps:

1) Install: JDK 17, Maven and MySQL

2) Create an empty MySql data base called "Trainer". The data base has to be available in the 3306 port.
For any other configuration, we can edit the pi-trainer/src/main/resources/application.properties file.

3) Run the next commands from the api-trainer root folder:
 * mvn clean install
 * java -jar target/trainer-0.0.1-SNAPSHOT.jar

Finally, we can test the API calling to the next endpoints (using POSTMAN for example):

* GET localhost:8080/trainers
  It returns a list with all created trainers.

* GET localhost:8080/trainers/id/{id} 
  it returns a requested trainer according to the sent id.
  The id is required. And if the id does not exist, the API responses with the next message:
    "Trainer not found with id {id}"

* POST localhost:8080/trainers 
  It creates a new trainer according to the sent body parameters:
    Body example:
    {
      "email" : "juanperez@campgladiator.com",
      "phone" : "5125125120",
      "firstName": "Juan",
      "lastName": "Perez"
    }

   
  All the body fields are requirement. The next messages can be sent if some field is empty or is missing:
    {
      "lastName": "Last name is Required",
      "firstName": "First name is Required",
      "phone": "no debe estar vacío",
      "email": "Email is Required"
    }

   Moreover, the email cannot be duplicated. If it occurs, API throws this message:
     "A trainer with email address juanperez@campgladiator.com already exists."

Swagger API documentation can be used going to /swagger-ui/index.html