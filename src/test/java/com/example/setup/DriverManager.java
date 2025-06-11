package com.example.setup;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.net.URI;
import java.util.List;

public class DriverManager {

    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static WebDriver getDriver() {
        if (driver.get() == null) {
            String executionMode = System.getProperty("executionMode", "local"); // Default to local
            String gridUrl = System.getProperty("gridUrl", "http://localhost:4444/wd/hub"); // Default Grid URL

            ChromeOptions options = getChromeOptions(getArgs());

            if ("grid".equalsIgnoreCase(executionMode)) {
                // Run tests on Selenium Grid
                try {
                    driver.set(new RemoteWebDriver(new URI(gridUrl).toURL(), options));
                } catch (Exception e) {
                    throw new RuntimeException("Failed to connect to Selenium Grid at: " + gridUrl, e);
                }
            } else {
                // Run tests locally
                WebDriverManager.chromedriver().setup();
                driver.set(new ChromeDriver(options));
            }
        }
        return driver.get();
    }

    public static void quitDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }

    public static List<String> getArgs() {
        String argLine = System.getProperty("argLine", "");
        return argLine.isEmpty() ? List.of() : List.of(argLine.split(","));
    }

    public static ChromeOptions getChromeOptions(List<String> args) {
        ChromeOptions options = new ChromeOptions();
        for (String arg : args) {
            options.addArguments(arg);
        }
        return options;
    }
}