package com.autobot.cucumberTestSuite;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
  format = {"json:src/reports/embedded_cucumber.json","pretty"}
  ,features = {"src/com/autobot/features/embedded_add_contact_scenario.feature"}
  ,glue={"com/autobot/step_definitions"}
  )

public class EmbeddedTestRunner {

	@BeforeClass
	public static void setUp(){

	}

	@AfterClass
	public static void tearDown(){
		
	}
	
}