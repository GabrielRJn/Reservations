# Reservations
Reservation System

This project is a Spring Boot REST API that can perform CRUD operations in order to store and manipulate reservation information. This project has no front end so it is recommended that you use either Postman or Swagger to interact with this API. 

The API requires JDK 17 and a MySQL connection to carry out all functionalities, however, there are some SQL resources attached to generate test schemas with data.


**Example reservation data:**

![image](https://user-images.githubusercontent.com/88378835/187108608-23013c2e-a9b5-4e40-8937-993a53414161.png)

The Reservation System uses custom SQL queries (e.g. findByCustomerName & findByDateCreated) to help search through the table and custom exception to provide user friendly error messages.

**Example error message:**

![image](https://user-images.githubusercontent.com/88378835/187109377-49890a68-49b8-428e-ab9b-4bfbd144ac03.png)

### Challenges faced:
There were some issues adding JUnit tests for all methods in this project and so coverage is not at its best.

### Future development:

* Improved JUnit tests will be added to improve coverage
* DTOs will be added to reduce requests
* A front end will be added to make this more user friendly


### How to run the project with your MySql Workbench (Not using attached SQL resources)
Step 1: Go to applications.properties

Step 2: Modify the below spring datasource properties to your SQL connection

*ant path matcher is needed if you are using Swagger to interact with the API*

spring.mvc.pathmatch.matching-strategy=ant-path-matcher

spring.datasource.url=jdbc:{sql connection here}

spring.datasource.username={username here}

spring.datasource.password={password here}

spring.jpa.hibernate.ddl-auto=create-drop

Step 3: Run as spring boot application

