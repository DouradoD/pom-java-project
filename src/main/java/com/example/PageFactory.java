// src/main/java/com/example/PageFactory.java
package com.example;

import com.example.pages.*;
import org.openqa.selenium.WebDriver;
import java.lang.reflect.Constructor;
import java.util.Map;
import java.util.HashMap;

public class PageFactory {
    private final WebDriver driver;
    private final String projectName;
    private final Map<Class<?>, Object> pageCache = new HashMap<>();

    public PageFactory(WebDriver driver) {
        this.driver = driver;
        // Try system property first, then environment variable, fallback to empty string
        String project = System.getProperty("project");
        if (project == null || project.isEmpty()) {
            project = System.getenv("project");
        }
        this.projectName = (project != null) ? project : "";
    }

    @SuppressWarnings("unchecked")
    public <T extends BasePage> T getPage(Class<T> pageClass) {
        try {
            // Return cached instance if available
            if (pageCache.containsKey(pageClass)) {
                return (T) pageCache.get(pageClass);
            }

            // Try to find project-specific implementation first
            if (projectName != null && !projectName.isEmpty()) {
                // Construct the custom class name and package
                String defaultPackage = pageClass.getPackage().getName();
                String customClassName = defaultPackage + "." + projectName + "." + 
                                      pageClass.getSimpleName() + projectName;
                
                try {
                    Class<?> customClass = Class.forName(customClassName);
                    Constructor<?> constructor = customClass.getConstructor(WebDriver.class);
                    T instance = (T) constructor.newInstance(driver);
                    pageCache.put(pageClass, instance);
                    System.out.println("Created custom page: " + customClassName); // Debug
                    return instance;
                } catch (ClassNotFoundException e) {
                    System.out.println("Custom page not found: " + customClassName + 
                                    ", falling back to default"); // Debug
                    // Fall through to default implementation
                }
            }

            // Use default implementation
            Constructor<T> constructor = pageClass.getConstructor(WebDriver.class);
            T instance = constructor.newInstance(driver);
            pageCache.put(pageClass, instance);
            System.out.println("Created default page: " + pageClass.getName()); // Debug
            return instance;
        } catch (Exception e) {
            throw new RuntimeException("Failed to create page instance: " + pageClass.getSimpleName(), e);
        }
    }
}