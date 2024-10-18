package StepDefinitions;

import java.io.IOException;
import java.util.function.BiFunction;

import org.testng.Assert;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import BaseClass.User_Payload;
import BaseClass.baseMethods;

import org.apache.logging.log4j.Logger;
import Pojos_User.AddressPojo;
import Pojos_User.User_Pojos;
import Utlities.ExcelUtil;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.authentication.PreemptiveBasicAuthScheme;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.module.jsv.JsonSchemaValidator;

public class UserAPI_Steps {

	public Response response;
	static CreateUser crtuse = new CreateUser();
	Logger logger = baseMethods.getLogger();
	baseMethods baseclass = new baseMethods();
	String DataRequestBody;
	ObjectMapper objectMapper = new ObjectMapper();
	ExcelUtil excelRead = new ExcelUtil("C:\\Numby_Ninja\\NN_Project_DsAlgo\\TestData\\UserModule.xlsx");

	///// ************ Background

	@Given("The user enters the basic auth information")
	public void the_user_enters_the_basic_auth_information() throws IOException {
		PreemptiveBasicAuthScheme authScheme = new PreemptiveBasicAuthScheme();
		authScheme.setUserName(baseMethods.loadProperties().getProperty("username"));
		authScheme.setPassword(baseMethods.loadProperties().getProperty("password"));
		RestAssured.authentication = authScheme;
	}

	// *********************** GET ALL USERS

	@When("The user sends the {string} for the GET request to retrieve all the users")
	public void the_user_sends_the_for_the_get_request_to_retrieve_all_the_users(String Endpoint) throws IOException {

		response = baseclass.requestBody(baseMethods.loadProperties().getProperty("GetAllUsers"), null, "Get");

	}

	@Then("The user validated the status code {string} Content-type Schema and DataType for GetAllUsers Request")
	public void the_user_validated_the_status_code_content_type_schema_and_data_type_for_get_all_users_request(
			String statusCode) {

		logger.info("GetAlluser Actual Status Code" + response.getStatusCode());
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("Validating Schema after creating the User --->");
		// response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("GetByNameSchema.json"));
		logger.info("Validating the Content-Type ---- >");
		response.then().assertThat().header("Content-Type", "application/json");

	}

	// ***************GET By ID

	@Given("The user has the BaseURL")
	public void the_user_has_the_base_url() throws IOException {
		RestAssured.baseURI = baseMethods.loadProperties().getProperty("BaseURL");

	}

	@When("The user provides the {string} with userId from post and sends a GET request")
	public void the_user_provides_the_with_user_id_from_post_and_sends_a_get_request(String Endpoint)
			throws IOException {

		response = baseclass.requestBody(baseMethods.loadProperties().getProperty("GetByID") + crtuse.getUser_Id(),
				null, "Get");

	}

	@Then("The user validated the status code {string} Content-type Schema and DataType for GetByID Request")
	public void the_user_validated_the_status_code_content_type_schema_and_data_type_for_get_by_id_request(
			String expcode) {

		logger.info("Validating the GetByID status Code ---- ");
		int actCode = response.getStatusCode();
		logger.info("GetByID Status Code --->" + actCode);
		Assert.assertEquals(actCode, 200);

		String responseBody = response.getBody().asString();

		JsonPath jsonPath = new JsonPath(responseBody);

		String userID = jsonPath.getString("user_id");

		Assert.assertEquals(crtuse.getUser_Id(), Integer.parseInt(userID));

		logger.info("Validating the Schema after getting the User----");
		response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("CreateUserSchema.json"));
	}

	@Given("The user have the Request body {string} to create new user")
	public void the_user_have_the_request_body_to_create_new_user(String RequestBody)
			throws JsonProcessingException, IOException {

		DataRequestBody = objectMapper.writeValueAsString(excelRead.getTestData("CreateUser", RequestBody));

	}

	@When("The user sends the post request")
	public void the_user_sends_the_post_request() throws IOException {

		response = baseclass.requestBody(baseMethods.loadProperties().getProperty("CreateUser"), DataRequestBody,
				"Post");

	}

	@Then("The user validated the the {string}")
	public void the_user_validated_the_the(String ExpStatusCode) {

		Assert.assertEquals(response.getStatusCode(), Integer.parseInt(ExpStatusCode));

		if (response.getStatusCode() == 201) {
			String responseBody = response.getBody().asString();
			JsonPath jsonPath = new JsonPath(responseBody);
			String userID = jsonPath.getString("user_id");

			String userFirstName = jsonPath.getString("user_first_name");

			crtuse.setUser_Id(Integer.parseInt(userID));

			crtuse.setUser_first_name(userFirstName);

			logger.info(" UserID for the newly created user --> " + userID);

			logger.info(" User First Name for the newly created the user -->" + userFirstName);

			response.then().assertThat()
					.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("CreateUserSchema.json"));
			baseclass.CreateUser_DataTypeValidation();

		}

	}

	// *************** GET BY NAME REQUEST

	@When("The user provides the {string} for the Get By Name request")
	public void the_user_provides_the_for_the_get_by_name_request(String Endpoint) throws IOException {

		response = baseclass.requestBody(
				baseMethods.loadProperties().getProperty("GetByName") + crtuse.getUser_first_name(), null, "Get");
	}

	@Then("The user validated the status code {string} Content-type Schema and DataType for GetByFirstName Request")
	public void the_user_validated_the_status_code_content_type_schema_and_data_type_for_get_by_first_name_request(
			String StatusCode) {
		logger.info("Validating the GetByName Status Code --- ");
		Assert.assertEquals(response.getStatusCode(), 200);

		String responseBody = response.getBody().asString();

		JsonPath jsonPath = new JsonPath(responseBody);

		String userFirstName = jsonPath.getString("user_first_name");

		Assert.assertEquals(userFirstName.contains(crtuse.getUser_first_name()), true);

		response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("CreateUserSchema.json"));

	}

	// ****************** Deleting the User

	@When("The user provides the {string} for the Get By Name request for the delete user")
	public void the_user_provides_the_for_the_get_by_name_request_for_the_delete_user(String Endpoint)
			throws IOException {

		response = baseclass.requestBody(
				baseMethods.loadProperties().getProperty("DeleteByName") + crtuse.getUser_first_name(), null, "Delete");

	}

	@Then("The user validated the status code {string} Content-type Schema and DataType for DeleteByFirstName Request")
	public void the_user_validated_the_status_code_content_type_schema_and_data_type_for_delete_by_first_name_request(
			String Status_Code) {

		logger.info("Validating the Delete Status Code --- ");
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("Delete Status Code ---> " + response.getStatusCode());
		int actCode = response.getStatusCode();
		logger.info("Validating the Delete Schema --- ");
		if (actCode == 200) {
			response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("DeleteSchema.json"));

		}
	}

}
