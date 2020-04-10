# OCS_CodingTest
Coding challenge for Olympic challenge

## Description
It is a project developed using Spring boot, kotlin and maven as build tool.

Spring has been used mainly to develop the project as a web application,
and expose rest endpoint.

I have developed it using hexagonal architecture and SOLID principles.
Because of this I have divided the packages structure in infrastructure, application and domain.

- In infrastructure are all the functionality related to the inputs and outputs of the application as well as the dependencies of frameworks, (like spring)
- In application are the services in charge of the orchestration of the different steps of a use case
- In domain are the core business functionality of the application, in this case all the functionality of the mars rover

As hexagonal architecture says, all the dependencies are from outer layers to inner ones, so the application layer only depends on the domain and the infrastructure may depends on application or domain.

This way it is possible to change the way the application is related with the external world without modify the core business functionality.

Because I haven't had enough time I can't make unit test, but They must be a must

## How to run
It is designed to run in a Linux system

### Prerequisites
- To have a jre 11 installed and java command in the classpath

### Run
- cd into the root directory where is mvnw and obs_test scripts
- run ```./mvnw clean install``` to genereate the jar binary executable artifact
- run ```./obs_test``` either with input and output file to run in console mode or without arguments to start the server.
Console mode will run only if you pass two arguments, if any other number of arguments are passed it will run in server mode

### Test
To test the applicaton, there is a folder named test in root directory where there are two files input_test.json and expect_output.json to test console mode and a postman collection to test API rest
#### Console
in project root directory run 

```./obs_test ./test/input_test.json ./test/output_test.json``` 

and compare output_test.json with expect_output.json

### Rest API
run the server ```./obs_test```
load postman collection Movement plan.postman_collection.json located in test folder and send Post movement plan request or execute the following curl and compare the output with the content of test/expect_output.json
```curl --location --request POST 'http://localhost:8080/rover/v1/plans' \
   --header 'Content-Type: application/json' \
   --header 'Cookie: JSESSIONID=36D0B5056D05A65307A86E230F30B96E' \
   --data-raw '{ 
     "terrain": [ 
       ["Fe", "Fe", "Se"], 
       ["W", "Si", "Obs"], 
       ["W", "Obs", "Zn"] 
     ], 
     "battery": 50, 
     "commands": [ 
       "F", "S", "R", "F", "S", "R", "F", "L", "F", "S" 
       ], 
     "initialPosition": { 
       "location" : { 
         "x" : 0, 
         "y" : 0 
       }, 
       "facing" : "East" 
     } 
   } ' ```
