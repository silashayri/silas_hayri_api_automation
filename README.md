# Pet Store API Test Automation

This project contains automated tests for the Pet endpoints of the Swagger Petstore API (https://petstore.swagger.io/).

## Technologies & Tools
- Java 11
- Maven
- TestNG - Testing framework
- REST Assured - API testing library
- Jackson - JSON processing
- IntelliJ IDEA (recommended IDE)

## Prerequisites
1. Java JDK 11 or higher installed
2. Maven installed
3. IDE (IntelliJ IDEA recommended)
4. Internet connection (to access the Petstore API)

## Installation & Setup
1. Clone the repository:
   bash
   git clone <repository-url>
   cd pet-store-api-tests
2. Install dependencies:
   bash
   mvn clean install
## Running Tests
### Run all tests
bash
mvn test
### Run specific test class
bash
mvn test -Dtest=PetApiTest
### Run specific test method
bash
mvn test -Dtest=PetApiTest#testCreatePetSuccessful

## Test Cases
The test suite includes the following API test scenarios:

### Create Operations
- Create a new pet with valid data
- Attempt to create a pet with invalid data

### Read Operations
- Get pet by valid ID
- Get pet by non-existent ID
- Find pets by status
- Find pets using invalid status

### Update Operations
- Update existing pet with new data
- Attempt to update with invalid data

### Delete Operations
- Delete existing pet
- Attempt to delete non-existent pet

## Project Features
- Modular test structure
- Data-driven approach
- Comprehensive CRUD operation coverage
- Both positive and negative test scenarios
- Clear test reporting

## Test Reports
After running the tests, you can find the reports in:
- `target/surefire-reports/index.html` - Detailed HTML report
- `target/surefire-reports/emailable-report.html` - Simple emailable report

## Authors
- Silas Hayri
