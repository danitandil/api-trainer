# trainer-api
Trainer API using Spring Boot

Esta API consiste en 3 enpoints:
* Crear trainer
* Listar un trainer determinado según el id recibido por parámetro.
* Listar todos los trainers creados.

Para poder utilizar la api de forma local tenemos que seguir los siguientes pasos:

1) Instalar: JDK 17, maven y MySQL

2) Crear una base de datos MySql vacía llamada Trainer. La base de datos debe estar disponible en el puerto 3306.
Para cualquier otra configuración se debe editar el archivo api-trainer/src/main/resources/application.properties

3) Correr los siguientes comandos desde el root de la carpeta api-trainer:
 * mvn clean install
 * java -jar target/trainer-0.0.1-SNAPSHOT.jar

Finalmente, podemos probar la API de la siguiente manera (usando POSTMAN por ejemplo):

* GET localhost:8080/trainers
   Devuelve una lista con todos los trainers creados.

* GET localhost:8080/trainers/id/{id} Devuelve el trainer solicitado según el id enviado.
   Se chequea que exista el id enviado como parámetro, de lo contrario se devuelve el siguiente mensaje:
   "Trainer not found with id {id}"

* POST localhost:8080/trainers Crea un nuevo trainer según los parámetros enviados en el body.
   Ejemplo de body:
   {
    "email" : "juanperez@campgladiator.com",
    "phone" : "5125125120",
    "firstName": "Juan",
    "lastName": "Perez"
   }

   Se chequea que los parámetros enviados en el body no sean vacíos, de lo contrario envía los mensajes correspondientes:
   {
    "lastName": "Last name is Required",
    "firstName": "First name is Required",
    "phone": "no debe estar vacío",
    "email": "Email is Required"
   }

   También chequea que el email no esté repetido. Si esto ocurre, el siguiente mensaje es enviado:
   "A trainer with email address juanperez@campgladiator.com already exists."
