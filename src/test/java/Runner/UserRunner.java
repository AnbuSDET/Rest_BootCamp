package Runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions (
    	   features={"./src/test/resources/FeatureFiles/UserAPI.feature"}, 
    	   glue = "StepDefinitions",
    	   //tags="@Allmodule",
    				  plugin = {"pretty", "html:reports/myreport.html", 
    						  "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"}, //----Cucumber Report
    				  dryRun = false,
    				  monochrome = true,
    				  publish = true   						
    						  
    	   )
public class UserRunner extends AbstractTestNGCucumberTests {
	
	

}
