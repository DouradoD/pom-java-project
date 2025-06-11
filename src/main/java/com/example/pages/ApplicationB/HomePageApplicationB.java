package com.example.pages.ApplicationB;

import com.example.pages.HomePage;
import org.openqa.selenium.WebDriver;

// @Page(name = "home") // Same name as base
public class HomePageApplicationB extends HomePage {
    public HomePageApplicationB(WebDriver driver) {
        super(driver);
    }
    
    @Override
    public void navigate() {
        System.out.println("ApplicationB-specific HomePage navigation");
    }
}