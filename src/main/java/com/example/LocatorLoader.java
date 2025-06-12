// src/main/java/com/example/pages/LocatorLoader.java
package com.example;

import com.example.helpers.StringHelper;

public class LocatorLoader {
    private static final String BASE_PACKAGE = "com.example.locators.";

    public static <T> T getLocators(Class<T> defaultLocatorClass, String applicationName) {
        try {
            // Try to load custom locators (e.g., "HomePageLocatorsApplicationA")
            String customClassName = BASE_PACKAGE + applicationName.toLowerCase() + "." + 
                                   defaultLocatorClass.getSimpleName() + StringHelper.capitalizeString(applicationName);
            Class<?> customClass = Class.forName(customClassName);
            return defaultLocatorClass.cast(customClass.getDeclaredConstructor().newInstance());
        } catch (Exception e) {
            // Fall back to default locators
            try {
                return defaultLocatorClass.getDeclaredConstructor().newInstance();
            } catch (Exception ex) {
                throw new RuntimeException("Failed to load locators", ex);
            }
        }
    }
}