package com.autobot.step_definitions;

import com.afour.autobot.AndroidDriver1;
import com.afour.autobot.CommonInterface;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class AndroidDemoSteps {
	
	AndroidDriver1 androidDriver1 = new AndroidDriver1();
	//CommonInterface androidDriver1 = new AndroidDriver1();
	
	@Given("^I Launch APP$")
	public void i_Launch_APP() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		Thread.sleep(6000);
		androidDriver1.launchApplication();
		System.out.println("Inside Launch");
	}

	@Then("^I Tap on HomeScreen$")
	public void i_Tap_on_HomeScreen() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		Thread.sleep(6000);
		androidDriver1.navigateHome();
		System.out.println("Inside homescreen");
	}
	
	@Then("^I Increase Volume$")
	public void i_Increase_Volume() throws Throwable {
		 // Write code here that turns the phrase above into concrete actions
		Thread.sleep(4000);
		androidDriver1.increaseVolume();
	}

	@Then("^I Decrease Volume$")
	public void i_Decrease_Volume() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		Thread.sleep(4000);
		androidDriver1.decreaseVolume();
	}



}