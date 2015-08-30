# Development Enviroment

* Eclipse Juno
* JDK 1.8.0_45
* Apache Maven 3.3.3
* Apache Tomcat 8.0.26

**Note:** The JDK version is very importante, since there are used features only available on Java 8.

# Database

The database used is SQLite.

On first deploy the application automatically creates the database, but is important that the folder where the database is created exists.

On Linux:
`/opt/db/`

On Windows:
`C:\opt\db\`

#Compile

To compile the application, use the following command on Apache Maven, inside the project folder.

`mvn clean install`

# Deploy

Copy the generated **jar** from the **target** folder created by the Apache Maven, after compile, to:

`<tomcat-installation-folder>/webapps`

Start Apache Tomcat:

Windows : `<tomcat-installation-folder>/bin/startup.bat`

Linux : `<tomcat-installation-folder>/bin/startup.sh`

Now the application is up and running.

# Webservice Documentation
All webservices are provided as RESTful webservices.

## Trace
A trace represent the distance between two points.

* Base URL:
`http://localhost:8080/router/rs/trace/`

#### Insert
* HTTP Mehotd: PUT
* Parameters:
  * origin: Name of the initial point for the Trace, type: **String**.
  * destination: Name of the end point for the Trace, type: **String**.
  * distance: Distance between the two informed points, type: **Double**.

Example:
`http://localhost:8080/trace/rs/trace?origin=A&destination=B&distance=10`

#### Update
* HTTP Mehotd: POST
* Parameters:
  * origin: Name of the initial point for the Trace, type: **String**.
  * destination: Name of the end point for the Trace, type: **String**.
  * distance: Distance between the two informed points, type: **Double**.

Example:
`http://localhost:8080/trace/rs/trace?origin=A&destination=B&distance=12`

## Route
A route represents a way to connect two points.

* Base URL:
`http://localhost:8080/router/rs/trace/`

#### Calculate best route
* HTTP Mehotd: GET
* Parameters:
  * origin: Name of the initial point for the Route, type: **String**.
  * destination: Name of the destination point for the Route, type: **String**.
  * autonomy: Vehicle autonomy, kilometers per liter, type: **Double**.
  * cost: Fuel cost, type: **Double**.
* Response Message: JSON representation of the best route and the fuel cost, by the informed vehicle.

Example:
`http://localhost:8080/router/rs/route?origin=A&destination=D&autonomy=10&cost=2.5`

## Response
The webservices responde the following HTTP status:
* 200: OK. A message in the reponse body if a return is expected by the operation.
* 406: Not Acceptable. A message explaining the error. Usually caused by an invalid operation inside the service.
* 500: Internal Server Error. A problem occurs and was not catched by the application. 

**Note**: All URLs examples, considers that the user is running the client on the same machine as the web application server.

# Motivations
## SQLite and JPA
Simple database schema and schema automatic ddl creation.
Easy to run on any other database.

## Spring
Responsible for the Depency Injection and JPA transactions.

## Apache Tomcat
Simple web server, avoiding the necessity to install an applciation server.
