package com.example.setup;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.net.URI;
import java.util.List;
import java.util.Properties;

public class DriverManager {

    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static WebDriver initializeDriver(Properties properties) {
        if (driver.get() == null) {
            String executionMode = System.getProperty("executionMode", "local"); // Default to local
            String gridUrl = System.getProperty("gridUrl", "http://localhost:4444/wd/hub"); // Default Grid URL

            ChromeOptions options = getChromeOptions(properties);

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

    public static WebDriver getDriver() {
        return driver.get();
    }

    public static void quitDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }

    public static ChromeOptions getChromeOptions(Properties seleniumProps) {
        ChromeOptions options = new ChromeOptions();
        seleniumProps.forEach((key, value) -> {
            String k = key.toString();
            String v = value != null ? value.toString() : "";
            if (k.startsWith("chrome.arg.")) {
                String arg = k.substring("chrome.arg.".length());
                if ("headless".equals(arg)) {
                    if (v.isEmpty() || v.equalsIgnoreCase("true")) {
                        options.addArguments("--headless");
                    }
            }   else if (!v.isEmpty()) {
                    options.addArguments("--" + arg + "=" + v);
                } else {
                    options.addArguments("--" + arg);
                }
            }
        });
        return options;
    }
}