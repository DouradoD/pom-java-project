package com.example.stepdefinitions;

import com.example.setup.ArgumentValidator;

import com.example.managers.ConfigManager;
import com.example.setup.DriverManager;
import com.example.setup.TestContext;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Hooks {
    private TestContext testContext;
    private ConfigManager configManager;

    public Hooks(TestContext testContext) {
        this.testContext = testContext;
    }

    @Before(order = 0) // Highest priority
    public void initConfiguration() {
        // Argument validation
        ArgumentValidator.hasValidArgs();

        // Initialize config manager first
        configManager = new ConfigManager();
        testContext.setConfigManager(configManager);
        
        // Now initialize driver with the configuration
        testContext.initializeDriver(configManager);
        
        // Now initialize Page Factory
        testContext.initializePageFactory();
    }

    @Before(order = 1) 
    public void initPOJOS() {
        testContext.initializePOJOData();
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