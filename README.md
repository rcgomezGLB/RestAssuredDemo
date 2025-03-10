
# PerfDog API Testing Project

## Project Overview
This project implements automated testing for the PerfDog pet store API using Java, TestNG, and RestAssured. The goal is to ensure that key functionalities of the API are working as expected.

## Technologies Used
- **Java**: Programming language for writing tests.
- **TestNG**: Testing framework for managing and executing tests.
- **RestAssured**: Library for testing RESTful APIs.

## API Documentation
Refer to the [PerfDog API Documentation](https://petstore.swagger.io) for details on available endpoints and request/response formats.

## Functionalities Tested
1. **Create a User**
2. **Login with the Newly Created User**
3. **List All Pets with Status "available"**
4. **Get Details of a Specific Pet**
5. **Create an Order for a Pet**
6. **Logout from the Application**

## Project Structure
```
───src
   └───test
       ├───java
       │   └───com
       │       └───rcgomez
       │           ├───config
       │           ├───model
       │           ├───request
       │           └───tests
       └───resources
```
## Directories
config: Contains configuration files and classes for setting up the test environment.

model: Contains data models representing the API entities such as User, Pet, and Order.

request: Contains classes responsible for making API requests.

tests: Contains the TestNG test classes that execute the API tests.

resources: Contains any additional resources needed for the tests, such as JSON schema files, config properties and TestNG suite.

## Test Execution
Run the tests using Maven:
```bash
mvn test
```
## Notes
Some test fails randomly due to the API nature, run until tests pass.