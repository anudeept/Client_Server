# Client_Server
* Application_Server - Server application which runs on 4000 port and accepts 5 concurrent Clients to process data
* Application_Client - Client application which connects to server and accepts data through Standard input.
* Application_Client_Simulator - Client application which connects to server and simulates 5 clients data (random numbers).

### Expected Output
* numbers.log file with list of unique numbers

### Prerequisites
````
- Java 8
- Maven

````

## Assumptions
* Storing unique numbers in HashSet to avoid duplicates this leads to continuous increase of memory on heavy load. to overcome this we can increase memory by "-Xmx" parameter while running or periodic flushing of data will help
* Default port is 4000 but user can change in config.properties file

## Code Flow
* CodingChallengeMain -  Main Class which reads property files and starts Server and Report Threads
* Server Thread - Creates a thread pool of 5 threads and accepts clients requests continuously on port 4000
* Report Thread - Runs for every 10 seconds and prints report stats
* Client_Handler - Runnable class which accepts data continuously from client and validates. if data is valid it inserts into log files
* DataStore - Acts as a static data store for server Application which store list of unique numbers for validation

## How to Run
Download Client_Server project from GitHub

````
- Application_Server
  $ cd Application_Server/target
  $ java -jar Application_Server-jar-with-dependencies.jar

- Application_Client
  $ cd Application_Client/target
  $ java -jar Application_Client-jar-with-dependencies.jar

- Application_Client_Simulator
  $ cd Application_Client_Simulator/target
  $ java -jar Application_Client_Simulator-jar-with-dependencies.jar

````
