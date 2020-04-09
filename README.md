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

## How to run
It is designed to run in a Linux system

### Prerequisites
- To have a jre installed and java command in the classpath

### Run
- cd into the root directory where is mvnw and obs_test scripts
- run mvnw clean install to genereate the jar binary executable artifact
- run obs_test either with input and output file to run in console mode or without arguments to start the server
