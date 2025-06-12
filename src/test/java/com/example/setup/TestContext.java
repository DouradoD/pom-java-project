// src/main/java/com/example/setup/TestContext.java
package com.example.setup;

import com.example.PageFactory;
import com.example.managers.UserManager;
import com.example.managers.ConfigManager;

import java.util.Map;

import org.openqa.selenium.WebDriver;

public class TestContext {
    private WebDriver driver;
    private PageFactory pageFactory;
    private Map<String, String> sessionInfo;
    private UserManager userManager;
    private ConfigManager configManager;

    public void setConfigManager(ConfigManager configManager) {
        this.configManager = configManager;
        this.sessionInfo = configManager.getAllPropertiesAsMap();
    }

    public void initializeDriver(ConfigManager configManager) {
        this.driver = DriverManager.initializeDriver(configManager.getProperties());
    }

    public void initializePageFactory() {
        this.pageFactory = new PageFactory(this.driver, this.sessionInfo);
    }

    public void initializePOJOData(){
         this.userManager = new UserManager();
    }

    public PageFactory getPages() {
        return pageFactory;
    }
    
    public WebDriver getDriver() {
        return driver;
    }

    public UserManager getUserManager() {
        return userManager;
    }

    public ConfigManager getConfiManager() {
        return configManager;
    }
}