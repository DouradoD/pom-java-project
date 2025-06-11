package com.example.pages;
import org.openqa.selenium.WebDriver;

import com.example.mappings.HomeMapping;

//@Page(name = "home")
public class HomePage extends BasePage {
    public HomePage(WebDriver driver) {
        super(driver);
    }
    
    public void navigate() {
        System.out.println("Default HomePage navigation");
    }
}
