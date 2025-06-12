package com.example.pages;
import org.openqa.selenium.WebDriver;
import com.example.locators.HomeLocators;
import com.example.LocatorLoader;

public class HomePage extends BasePage {
     private final HomeLocators locators;

    public HomePage(WebDriver driver, String projectName) {
        super(driver);
        this.locators = LocatorLoader.getLocators(
            HomeLocators.class, 
            projectName
        );
    }
    
    public void navigate() {
        System.out.println("Default -> Locator: "+this.locators.getLogo());
        System.out.println("Default HomePage navigation");
    }
}
