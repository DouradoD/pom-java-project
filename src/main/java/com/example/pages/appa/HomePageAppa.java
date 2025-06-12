// CustomHomePage.java
package com.example.pages.appa;

import com.example.LocatorLoader;
import com.example.locators.HomeLocators;
import com.example.pages.HomePage;
import org.openqa.selenium.WebDriver;

public class HomePageAppa extends HomePage {
    private final HomeLocators locators;

    public HomePageAppa(WebDriver driver, String projectName) {
        super(driver, projectName);
        this.locators = LocatorLoader.getLocators(
            HomeLocators.class, 
            projectName
        );
    }
    
    @Override
    public void navigate() {
        System.out.println("Application A -> Locator: "+this.locators.getLogo());
        System.out.println("ApplicationA-specific HomePage navigation");
    }
}