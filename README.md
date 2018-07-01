# Fusion-Engine
An implementation of the Fusion Engine for Cyber 560

The Fusion Engine, developed by GROUP 1 Inc., is a web service that accepts RESTful interactions to process coordinate metadata. 
It accepts coordinates in standard format, translates them into decimal format, and persists them in memory (database storage was not required for this iteration). 
### Contributors
Austin Miller,
Kathrine Lavieri
### How to Build
First Time Building: From root, run "gradlew build" </br>
Subsequent Builds: run "gradle clean build" 
### How to Run
From /build/libs, run "java -jar fusion-engine-rest-0.0.1-SNAPSHOT.jar" 
### The RESTful API
The Fusion Engine currently supports two endpoints: one that submits coordinates, and one that queries for coordinates previously submitted. 
#### Submitting Coordinates 
Storing coordinates in the Fusion Engine requires a POST request. </br>
The endpoint is: "/submit" and requires two input parameters: latitude and longitude, which accepts strings as inputs </br>
If successful, a JSON response body will be returned with a UUID and coordinates in the response body. </br>
#### Querying for Coordinates
All submitted coordinates are stored in the Fusion Engine Data Store, and referenced by a UUID primary key. 
External services can query for coordinates by UUID. </br>
The endpoint is "/query" and requires one input parameter, a UUID string 
If successful, the JSON response will contain the UUID and associated coordinates. 
If no UUID was found, a "404 Bad Request" is returned. 
