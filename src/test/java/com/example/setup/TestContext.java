// src/main/java/com/example/setup/TestContext.java
package com.example.setup;

import com.example.PageFactory;
import org.openqa.selenium.WebDriver;

public class TestContext {
    private final WebDriver driver;
    private final PageFactory pageFactory;

    public TestContext() {
        this.driver = DriverManager.getDriver();
        this.pageFactory = new PageFactory(this.driver);
    }

    public PageFactory getPages() {
        return pageFactory;
    }
    
    public WebDriver getDriver() {
        return driver;
    }
}