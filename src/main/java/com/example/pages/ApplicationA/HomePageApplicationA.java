// CustomHomePage.java
package com.example.pages.ApplicationA;

import com.example.pages.HomePage;
import org.openqa.selenium.WebDriver;

public class HomePageApplicationA extends HomePage {
    public HomePageApplicationA(WebDriver driver) {
        super(driver);
    }
    
    @Override
    public void navigate() {
        System.out.println("ApplicationA-specific HomePage navigation");
    }
}