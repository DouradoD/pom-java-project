package com.example.stepdefinitions;

import org.openqa.selenium.WebDriver;

import com.example.setup.ArgumentValidator;
import com.example.setup.DriverManager;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Hooks {
    private WebDriver driver;
    
    @Before
    public void setup(Scenario scenario) {
        if (ArgumentValidator.hasValidArgs()){
            System.out.println("Starting scenario: " + scenario.getName());
            DriverManager.initializeDriver();
            driver = DriverManager.getDriver();
            driver.manage().window().maximize();
        } else {
            throw new RuntimeException("Missing required arguments. Stopping test execution.");
        }
    }
    
    @After
    public void tearDown(Scenario scenario) {
        System.out.println("Finished scenario: " + scenario.getName() + 
                         " Status: " + scenario.getStatus());
        if (scenario.isFailed()) {
            // Take screenshot logic here
        }
        
        DriverManager.quitDriver();
    }
}