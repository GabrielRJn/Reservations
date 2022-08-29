# Reservations
###

### Why are we doing this?
This Spring boot API was made using MVC architecture to handle details of customer reservations able to perform CRUD operations on MySQL database. This was a 3-day challenge given by QA's Software Development Bootcamp. The progress of the project was tracked using Jira.

### How I expected the challenge to go
I expected to implement the main functionalities of the API quite quickly, however, there were some aspects I was not entirely confident on such as adding DTOs, SQL queries, custom exceptions and Junit tests.

### What went well? 
  The API is able to successfuly carry out CRUD operations on a managed database and use custom SQL queries to improve flexibility the Reservation's service class     and throw user-friendly error messages from custom exceptions given by entering the wrong input over HTTP GET, POST, PUT and DELETE requests.
  
### What didn't go as planned? 
* Within the given time frame I was not able to implement DTOs in my API - my undstanding of how to implement DTOs was not concrete for this project as it was a new concept to me. There was an attempt to implement this, however my lack of understanding led to this being very time consuming. Seeing that I was facing issues with this, I decided to    leave this feature and focus on other aspects of the API that were more important to complete the project.

* JUnit tests and coverage - Also being new to understanding JUnit tests and Mockito, I was not able to get a high amount of coverage for this project (it is currently 75%) however I do plan to practice my software testing skills for the future.

<img width="236" alt="image" src="https://user-images.githubusercontent.com/88378835/187288661-5550af97-ad36-4c86-bbe8-1dc33c0cc5bf.png">

<img width="269" alt="image" src="https://user-images.githubusercontent.com/88378835/187289398-2b37a702-60e8-4331-be59-57620998f062.png">



 # Possible improvements for future revisions of the project
 There a number of things that I would like to do to improve this API such as:
 * Add more Models and implement DTOs
 * Improve Junit Mockito Tests to improve coverage
 * Add a cloud managed database
 * Clean up code formatting
 * Add a front end form using Thyme Leaf and Bootstrap
 * Refactoring
 
 (I think theres an endless amount of ways this could be improved)
 
 
 
 # Example uses
 **Searching for customers from the controller**
Given these entries (although there could be much more):

 ![image](https://user-images.githubusercontent.com/88378835/187283171-9de33909-62d4-4a45-b710-818508576eac.png)

We can search for specific customers by Name; For example Teresa:

![image](https://user-images.githubusercontent.com/88378835/187283644-73887b5e-60eb-4587-b698-5754bb7f77fb.png)

We can also search for specific dates a reservation was created; For example, searching for reservations at 2012-08-29 (yyyy-mm-dd): 

<img width="239" alt="image" src="https://user-images.githubusercontent.com/88378835/187286615-f84f504e-4add-4c1f-a5b2-d2de58dec604.png">

Result:

<img width="343" alt="image" src="https://user-images.githubusercontent.com/88378835/187287144-bb16b430-a212-4d08-ba18-2524a98e7f71.png">

 
**Example error message:**

![image](https://user-images.githubusercontent.com/88378835/187109377-49890a68-49b8-428e-ab9b-4bfbd144ac03.png)

<img width="316" alt="image" src="https://user-images.githubusercontent.com/88378835/187287333-d6941b7d-4ae2-41ac-b562-26822c16e701.png">


**Persisted data**

<img width="431" alt="image" src="https://user-images.githubusercontent.com/88378835/187288248-ab8b9bd7-a409-489b-811f-8eb0de940472.png">




JIRA: https://gabrieljohnson.atlassian.net/jira/software/projects/TDL/boards/1/roadmap

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

Step 4 (If using Swagger): type http://localhost:8080/swagger-ui/index.html#/ into your browser

**Swagger interface:**

<img width="856" alt="image" src="https://user-images.githubusercontent.com/88378835/187118803-a8514f6f-e1a3-4f51-b168-b40e3149734a.png">


