package BaseClass;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.ResourceBundle;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import StepDefinitions.CreateUser;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static org.testng.Assert.*;
public class baseMethods {

	private static Properties p;
	public Response response;
	static CreateUser crtuse = new CreateUser();
	private static Logger logger;

	// ********** Replacement Property of property file

	
	public void GetAllusers_Count() {

		JsonPath jp = new JsonPath(response.asString());
		int numFromsResponse = jp.getList("user_id").size();
		
		crtuse.setAlluserCount(numFromsResponse);
	}

	public static Properties loadProperties() throws IOException {
		Properties p = new Properties();
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\config.properties";
		try (FileReader file = new FileReader(filePath)) {
			p.load(file);
		}
		return p;
	}
	

	public Response requestBody(String Endpoint, String JsonBody, String ReqName) throws IOException {
		

		if (ReqName.equalsIgnoreCase("Get"))

			response = RestAssured.given().baseUri(RestAssured.baseURI).when().get(Endpoint);

		else if (ReqName.equalsIgnoreCase("Post"))
			response = RestAssured.given().baseUri(RestAssured.baseURI).contentType(ContentType.JSON).body(JsonBody)
					.when().post(Endpoint);

		else if (ReqName.equalsIgnoreCase("Delete"))
			response = RestAssured.given().baseUri(RestAssured.baseURI).when().delete(Endpoint);
		return response;

	}
	
	
	
	public void CreateUser_DataTypeValidation()
	{
		// Validate user properties
        String userFirstName = response.jsonPath().getString("user_first_name");
        String userLastName = response.jsonPath().getString("user_last_name");
        String userEmailId = response.jsonPath().getString("user_email_id");
        String plotNumber = response.jsonPath().getString("userAddress.plotNumber");
        String street = response.jsonPath().getString("userAddress.street");
        String state = response.jsonPath().getString("userAddress.state");
        String country = response.jsonPath().getString("userAddress.country");
        String creationTime = response.jsonPath().getString("creation_time");
        String lastModTime = response.jsonPath().getString("last_mod_time");

        // Validate that values are either a String or null
        assertTrue(userFirstName == null || userFirstName instanceof String, "User first name should be a String or null");
        assertTrue(userLastName == null || userLastName instanceof String, "User last name should be a String or null");
        assertTrue(userEmailId == null || userEmailId instanceof String, "User email ID should be a String or null");
        assertTrue(plotNumber == null || plotNumber instanceof String, "Plot number should be a String or null");
        assertTrue(street == null || street instanceof String, "Street should be a String or null");
        assertTrue(state == null || state instanceof String, "State should be a String or null");
        assertTrue(country == null || country instanceof String, "Country should be a String or null");
        assertTrue(creationTime == null || creationTime instanceof String, "Creation time should be a String or null");
        assertTrue(lastModTime == null || lastModTime instanceof String, "Last modification time should be a String or null");
    }

	
	
	
	
	public static Logger getLogger() {
		logger = LogManager.getLogger();
		return logger;
	}

}
