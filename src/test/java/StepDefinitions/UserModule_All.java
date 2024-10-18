package StepDefinitions;

import java.io.IOException;

import org.apache.logging.log4j.Logger;
import org.testng.Assert;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import BaseClass.User_Payload;
import BaseClass.baseMethods;
import Pojos_User.AddressPojo;
import Utlities.ExcelUtil;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.authentication.PreemptiveBasicAuthScheme;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UserModule_All {
	
	/*ExcelUtil excelRead= new ExcelUtil("C:\\Numby_Ninja\\NN_Project_DsAlgo\\TestData\\UserModule.xlsx");
	User_Payload userPayload= new User_Payload();
	Response response;
	Logger logger = baseMethods.getLogger();
	baseMethods baseclass= new baseMethods();
	String DataRequestBody;
	 ObjectMapper objectMapper = new ObjectMapper();
	 
	// ************* Background
	
	@Given("User sets the Basic Authorizaion for Valid Username and Password")
	public void user_sets_the_basic_authorizaion_for_valid_username_and_password() throws IOException {
	    
		PreemptiveBasicAuthScheme authScheme = new PreemptiveBasicAuthScheme();		
		authScheme.setUserName(baseMethods.loadProperties().getProperty("username"));
		authScheme.setPassword(baseMethods.loadProperties().getProperty("password"));
		RestAssured.authentication = authScheme;
	}

	@Then("enters the Base URL")
	public void enters_the_base_url() throws IOException {
		RestAssured.baseURI = baseMethods.loadProperties().getProperty("BaseURL");
	}

	
	// ******* CREATE USER   
	
	 * @Given("The user send a creates request body from the {string} and {string}")
	 * public void the_user_send_a_creates_request_body_from_the_and(String Sheet,
	 * String RowNum) throws NumberFormatException, IOException { User_Payload
	 * ReqsBody = new User_Payload();
	 * 
	 * 
	 * }
	 * 
	 * 
	 * @Given("The user provides the {string} and request body {string} and {string}"
	 * ) public void the_user_provides_the_and_request_body_and(String Endpoint,
	 * String Sheet, String RowNum) throws NumberFormatException, IOException {
	 * 
	 * ObjectMapper objectMapper = new ObjectMapper();
	 * 
	 * System.out.println("Request Body --> "+objectMapper.writeValueAsString(
	 * userPayload.RequestBody(Sheet,Integer.parseInt(RowNum))));
	 * 
	 * response =
	 * RestAssured.given().baseUri(RestAssured.baseURI).contentType(ContentType.JSON
	 * ).body(objectMapper.writeValueAsString(userPayload.RequestBody(Sheet,Integer.
	 * parseInt(RowNum)))).when() .post("createusers");
	 * 
	 * System.out.println("response::"+response.getStatusCode()+",response body::"
	 * +response.getBody().asString());
	 * 
	 * }
	 * 
	 * 
	 * 
	 * @Then("The user validated the Response code with the {string} Excel Sheet {string}"
	 * ) public void
	 * the_user_validated_the_response_code_with_the_excel_sheet(String Sheet,
	 * String RowNum) throws NumberFormatException, IOException { int actCode =
	 * response.getStatusCode();
	 * 
	 * Assert.assertEquals(actCode,Integer.parseInt(excelRead.getCellData(Sheet,
	 * Integer.parseInt(RowNum), 10)));
	 * 
	 * }
	 
	
	
	
	// ******************** New Code 
	
	
	
	
	
	 * @Given("The user have the Request body {string} to create new user") public
	 * void the_user_have_the_request_body_to_create_new_user(String RequestBody)
	 * throws JsonProcessingException, IOException {
	 * 
	 * // System.out.println("Request Body --> "+objectMapper.writeValueAsString(
	 * userPayload.RequestBody("CreateUser",1))); DataRequestBody=
	 * objectMapper.writeValueAsString(excelRead.getTestData("CreateUser",
	 * RequestBody));
	 * 
	 * }
	 * 
	 * @When("The user sends the post request") public void
	 * the_user_sends_the_post_request() {
	 * 
	 * response =
	 * RestAssured.given().baseUri(RestAssured.baseURI).contentType(ContentType.JSON
	 * ).body(DataRequestBody).when() .post("createusers");
	 * 
	 * System.out.println("response::"+response.getStatusCode()+",response body::"
	 * +response.getBody().asString()); }
	 * 
	 * @Then("The user validated the the {string}") public void
	 * the_user_validated_the_the(String ExpStatusCode) {
	 * Assert.assertEquals(response.getStatusCode(),Integer.parseInt(ExpStatusCode)
	 * ); } }
	 * 
	 * 
	 * 
	 * 
	/*
	 * // ****************** CREATE USER
	 * 
	 * @When("The user sends a POST request to {string} with Request Body") public
	 * void the_user_sends_a_post_request_to_with_request_body(String Endpoint)
	 * throws IOException {
	 * 
	 * AddressPojo address = new AddressPojo();
	 * 
	 * address.setPlotNumber("72-A"); address.setStreet("Swackhammer Way");
	 * address.setState("NJ"); address.setCountry("USA");
	 * address.setZipCode("3243");
	 * 
	 * User_Pojos newUser = new User_Pojos(); newUser.setUser_first_name("Aru");
	 * newUser.setUser_last_name("Pons");
	 * newUser.setUser_contact_number("2347856003");
	 * newUser.setUser_email_id("aru@gmail.com"); newUser.setUserAddress(address);
	 * 
	 * ObjectMapper objectmapper = new ObjectMapper();
	 * objectmapper.writeValueAsString(newUser);
	 * 
	 * response=
	 * baseclass.requestBody(baseMethods.loadProperties().getProperty("CreateUser"),
	 * objectmapper.writeValueAsString(newUser),"Post");
	 * 
	 * //response =
	 * RestAssured.given().baseUri(RestAssured.baseURI).contentType(ContentType.JSON
	 * ).body(newUser).when() //.post(Endpoint);
	 * //System.out.println("response::"+response.getStatusCode()+",response body::"
	 * +response.getBody().asString());
	 * 
	 * }
	 * 
	 * 
	 * @Then("The user validated the status code {string} content-type schema and data type for CreateUser Request"
	 * ) public void
	 * the_user_validated_the_status_code_content_type_schema_and_data_type_for_create_user_request
	 * (String string) { Assert.assertEquals(response.getStatusCode(), 201);
	 * 
	 * String responseBody = response.getBody().asString(); JsonPath jsonPath = new
	 * JsonPath(responseBody); String userID = jsonPath.getString("user_id");
	 * 
	 * String userFirstName = jsonPath.getString("user_first_name");
	 * 
	 * crtuse.setUser_Id(Integer.parseInt(userID));
	 * 
	 * crtuse.setUser_first_name(userFirstName);
	 * 
	 * logger.info(" UserID for the newly created user --> " + userID);
	 * 
	 * logger.info(" User First Name for the newly created the user -->"
	 * +userFirstName);
	 * 
	 * response.then().assertThat().body(JsonSchemaValidator.
	 * matchesJsonSchemaInClasspath("CreateUserSchema.json"));
	 * baseclass.CreateUser_DataTypeValidation(); }
	 
	 */
}
