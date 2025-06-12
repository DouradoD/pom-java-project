package com.example.helpers;

import com.example.pojos.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

public class JsonReader {

    // Get the project name from system properties (set in pom.xml or environment)
    private static final String PROJECT_NAME = System.getProperty("project").toLowerCase();
    private static final String USERS_JSON_PATH = "testdata/" + PROJECT_NAME + "/user.json";
   
    public static List<User> loadUsersFromJson() throws IOException {
        System.out.println("Looking for resource: " + USERS_JSON_PATH);
        System.out.println("Resource URL: " + JsonReader.class.getClassLoader().getResource(USERS_JSON_PATH));
        ObjectMapper mapper = new ObjectMapper();
        try (InputStream inputStream = JsonReader.class
                .getClassLoader()
                .getResourceAsStream(USERS_JSON_PATH)) {
            
            if (inputStream == null) {
                throw new IOException("JSON file not found at: " + USERS_JSON_PATH);
            }

            User[] users = mapper.readValue(inputStream, User[].class);
            return Arrays.asList(users);
        }
    }

    public static User getUserByTier(String tier) throws IOException {
        return loadUsersFromJson().stream()
            .filter(user -> user.getTier().equalsIgnoreCase(tier))
            .findFirst()
            .orElseThrow(() -> new RuntimeException("User not found for tier: " + tier));
    }

    // New method to get the resolved path (for debugging/logging)
    public static String getResolvedUsersJsonPath() {
        return USERS_JSON_PATH;
    }

    public static List<User> getAllUsers() throws IOException {
        return loadUsersFromJson(); // Reuses your existing load method
    }
}