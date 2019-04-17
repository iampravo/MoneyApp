# MoneyApp using SparkJava

This project implements Money transfer between to bank accounts using 
REST Api, Java 8, Spark Java.

The best part about SparkJava : 
```sh [Thread-0] INFO
org.eclipse.jetty.server.Server - Started @713ms
```
###### The application starts in 713ms.


 *The technologies used in details :*
 
 | Feature | Technology |
| ------ | ------ |
| Language | Java 8  |
| REST Api | Spark Java, other options are Restlet, LightRest 4j, Jersey, Play, Rest Easy|
| Serialization & Desrz| Google GSON |
| Logging | slf4j |
| In memory Database | H2Database|
| Connection Pooling  | Hikari |
| Build Tool  | Maven |
| SCM   | Github |
| Integration Testing    | Test |
| Unit Testing    | Mockito |


## How to start 

Fetch this application from this git and build it with apache maven

```sh
mvn clean install
```
This will clean the target directory, validates the code, complies the
code, runs the integration test cases, fetches the dependent jars and
builds the jar file with all dependencies.

```sh
java -jar /target/MoneyApp-UsingSpark-1.0-SNAPSHOT-jar-with-dependencies.jar
```

The application will start on the localhost and will be listening to the
port **8182**

## Code Highlights 

**Spark Java Main Application** : This is the Main class of the this
SparkJava project.
 
#### Controller 

  **RouteController** : This RouteController class initializes and
  manages all the routes of this project.
 
 **AccountController** : This AccountController class manages all
 account activity.
 
 **UserController** : This UserController class manages all user
 activity.
 
  **TransactionController** : This TransactionController class manages all user
 activity.
 
#### Static Data
 
 **Currency** :
 ```sh
 public enum Currency {
    US_DOLLAR("USD"),
    RUPEE("INR"),
    POUND("GBP");
 ```
 **TransactionStatus** :
 ```sh 
 public enum TransactionStatus {
 SCHEDULED("Scheduled"), IN_PROGRESS("In Progress"), FAILED("Failed"),
 PASSED("Passed");
 ```
 
#### In Memory Database
 
 **H2Database** : In memory database, i.e. H2Database, has its
 schema('resources/db/database_schema.sql') and initial data
 ('resources/db/initial_data.sql')
 ```sh
 public class H2Database { 

 private static HikariDataSource dataSource; 
    public static void initializeDatabase() {
        dataSource = new HikariDataSource();
        dataSource.setJdbcUrl("jdbc:h2:mem:test;" +
                "INIT=RUNSCRIPT FROM 'classpath:db/database_schema.sql'\\;RUNSCRIPT FROM 'classpath:db/initial_data.sql';" +
                "TRACE_LEVEL_FILE=4");
        dataSource.setUsername("username");
        dataSource.setPassword("password");
    }
   ```
   
#### Exception
- ApiException extends RuntimeException
- EntityNotFoundException extends ApiException
- MalformedRequestException extends ApiException 
- TransactionException extends ApiException


## Endpoint definitions

### /moneyapp/v1/* 
This endpoint is used to log the API call. This can be used to
authenticate the user.


### GET /moneyapp/v1/users
This endpoint gets all the user available in database.

Request 
```sh
localhost:8182/moneyapp/v1/users
```
Response
```sh
{
    "resultStatus": "SUCCESS",
    "message": "OK",
    "jsonElement": [
        {
            "userId": 1,
            "userName": "Pravinkumar Singh"
        },
        {
            "userId": 2,
            "userName": "Paul"
        },
        {
            "userId": 3,
            "userName": "Adam"
        },
        {
            "userId": 4,
            "userName": "Robin"
        },
        {
            "userId": 5,
            "userName": "Rachel"
        }
    ]
}
```


### GET /moneyapp/v1/users/:userId
This endpoint gets the user available in database for given 'userId'.

Request 
```sh
localhost:8182/moneyapp/v1/users/1
```
Response
```sh
{
    "resultStatus": "SUCCESS",
    "message": "OK",
    "jsonElement": {
        "value": {
            "userId": 1,
            "userName": "Pravinkumar Singh"
        }
    }
}
```


### GET /moneyapp/v1/users/:userId/accounts
This endpoint gets all the accounts linked to user for given 'userId',
available in database.

Request 
```sh
localhost:8182/moneyapp/v1/users/1/accounts
```
Response
```sh
{
    "resultStatus": "SUCCESS",
    "message": "OK",
    "jsonElement": [
        {
            "accountNumber": 1,
            "accountType": "Saving",
            "balance": 100.25,
            "localCurrency": "USD"
        }
    ]
}
```


### GET /moneyapp/v1/transactions
This endpoint gets all the transaction available in database.
Request 
```sh
localhost:8182/moneyapp/v1/transactions
```
Response
```sh
{
    "resultStatus": "SUCCESS",
    "message": "OK",
    "jsonElement": [
        {
            "transactionId": 1,
            "fromAccountNumber": 1,
            "toAccountNumber": 2,
            "amount": 12.2,
            "currency": "US_DOLLAR",
            "creationDate": "Apr 17, 2019 12:43:57 PM",
            "updateDate": "Apr 17, 2019 12:43:57 PM",
            "transactionStatus": "SCHEDULED",
            "comments": "Initiated"
        }
    ]
}
```


### POST /moneyapp/v1/transfer
This endpoint transfer the amount from 'fromAccountNumber' to
'toAccountNumber' via a post call.

Request 
```sh
localhost:8182/moneyapp/v1/transfer
``` 

Request body for post call,
```sh
 { 
    "fromAccountNumber"= 1,
    "toAccountNumber" = 3, 
    "amount" = 25, 
    "currency" = 'USD'
 }
```

Response 
```sh
{
    "resultStatus": "SUCCESS",
    "message": "OK",
    "jsonElement": {
        "transactionId": 0,
        "fromAccountNumber": 1,
        "toAccountNumber": 3,
        "amount": 25,
        "currency": "US_DOLLAR",
        "creationDate": "Apr 17, 2019 12:51:13 PM",
        "updateDate": "Apr 17, 2019 12:51:13 PM",
        "transactionStatus": "PASSED",
        "comments": "Initiated"
    }
}
```

### Exception
Request 
```sh
localhost:8182/moneyapp/v1/users/123
```
Response
```sh
{
    "resultStatus": "ERROR",
    "message": "The given User Id '123' could not be retrieved"
}
```
