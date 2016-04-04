package com.cucumber.selenium.NaverPayNotification;

import java.util.Map;
import org.junit.runner.RunWith;


import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.gurock.testrail.APIClient;

import cucumber.api.CucumberOptions;
import cucumber.api.java.Before;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
		format = {"json:target/cucumber.json"},features = "src/test/resource")
public class Runner {
	
	

}
