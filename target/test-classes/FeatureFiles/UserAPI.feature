Feature: Validating User Module

  Background: Authorization to Access the Request
    Given The user enters the basic auth information
    
   @GetAll @Allmodule
  Scenario: Retrieve all users
    Given The user has the BaseURL
    When The user sends the "<Endpoint>" for the GET request to retrieve all the users
    Then The user validated the status code "<Status_Code>" Content-type Schema and DataType for GetAllUsers Request   
     
    
Scenario Outline: Validating the Create user request with Valid and Invalid inputs

   Given The user have the Request body "<RequestBody>" to create new user
   When  The user sends the post request
   Then  The user validated the the "<StatusCode>" 

Examples:
|RequestBody|StatusCode|
|CU01|400|
|CU02|400|
|CU03|400|
|CU04|400|
|CU05|400|
|CU06|400|
|CU07|400|
|CU08|400|
|CU09|400|
|CU10|400|
|CU11|400|
|CU12|409|
|CU13|400|
|CU14|400|
|CU15|409|
|CU16|400|
|CU17|201|



@GetByID @Allmodule
  Scenario Outline: Validating the user by User ID using GETByID
    Given The user has the BaseURL
    When The user provides the "<Endpoint>" with userId from post and sends a GET request
    Then The user validated the status code "<Status_Code>" Content-type Schema and DataType for GetByID Request   
   
 
       
@GetByName  @Allmodule
  Scenario Outline: Validating the user by User First Name
    Given The user has the BaseURL
    When The user provides the "<Endpoint>" for the Get By Name request
    Then The user validated the status code "<Status_Code>" Content-type Schema and DataType for GetByFirstName Request  
    
   
      
   @DeleteByName
  Scenario Outline: Deleting the the user by User by their First Name 
    Given The user has the BaseURL
    When The user provides the "<Endpoint>" for the Get By Name request for the delete user
    Then The user validated the status code "<Status_Code>" Content-type Schema and DataType for DeleteByFirstName Request 
    
     
      
