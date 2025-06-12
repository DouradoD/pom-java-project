package com.example.pages.appb;

import com.example.LocatorLoader;
import com.example.locators.HomeLocators;
import com.example.pages.HomePage;
import org.openqa.selenium.WebDriver;

public class HomePageAppb extends HomePage {
    private final HomeLocators locators;

    public HomePageAppb(WebDriver driver, String projectName) {
        super(driver, projectName);
        this.locators = LocatorLoader.getLocators(
            HomeLocators.class, 
            projectName
        );
    }
    
    @Override
    public void navigate() {
        System.out.println("Application B -> Locator: "+this.locators.getLogo());
        System.out.println("ApplicationB-specific HomePage navigation");
    }
}