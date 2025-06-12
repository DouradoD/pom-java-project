// src/main/java/com/example/managers/ConfigManager.java
package com.example.managers;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class ConfigManager {
    private static final String SELENIUM_PROPERTIES = "selenium.properties";
    private final Properties properties = new Properties();

    public ConfigManager() {
        loadDefaultProperties();
        overrideWithSystemProperties();
    }

    public Properties getProperties(){
        return properties; 
    }

    private void loadDefaultProperties() {
        try (InputStream input = getClass().getClassLoader().getResourceAsStream(SELENIUM_PROPERTIES)) {
            if (input == null) {
                throw new RuntimeException("Unable to find " + SELENIUM_PROPERTIES);
            }
            properties.load(input);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load properties file", e);
        }
    }

    private void overrideWithSystemProperties() {
        System.getProperties().stringPropertyNames()
            .forEach(key -> {
                if (properties.containsKey(key)) {
                    properties.setProperty(key, System.getProperty(key));
                }
            });
    }

    public Map<String, String> getAllPropertiesAsMap() {
        Map<String, String> map = new HashMap<>();
        for (String name : properties.stringPropertyNames()) {
            map.put(name, properties.getProperty(name));
        }
        return map;
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }

}