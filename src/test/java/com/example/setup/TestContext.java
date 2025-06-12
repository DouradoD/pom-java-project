// src/main/java/com/example/setup/TestContext.java
package com.example.setup;

import com.example.PageFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.openqa.selenium.WebDriver;

public class TestContext {
    private final WebDriver driver;
    private final PageFactory pageFactory;
    private final Map<String, String> sessionInfo;
    
    public TestContext() {
        this.driver = DriverManager.getDriver();
        this.sessionInfo = getSystemPropertiesAsMap();
        this.pageFactory = new PageFactory(this.driver, this.sessionInfo);
    }

    public PageFactory getPages() {
        return pageFactory;
    }
    
    public WebDriver getDriver() {
        return driver;
    }
    
    public Map<String, String> getSystemPropertiesAsMap() {
        Properties props = System.getProperties();
        Map<String, String> map = new HashMap<>();
        for (String name : props.stringPropertyNames()) {
            map.put(name, props.getProperty(name));
        }
        return map;
    }
}