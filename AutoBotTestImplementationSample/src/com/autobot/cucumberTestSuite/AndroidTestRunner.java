package com.autobot.cucumberTestSuite;
import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
  format = {"json:/reports/android_cucumber.json","pretty"}
  ,features = {"src/com/autobot/features/android_new_demo.feature"}
  ,glue={"com/autobot/step_definitions"}
  )

public class AndroidTestRunner {
}