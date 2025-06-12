package com.example.setup;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.Map;
import java.util.HashMap;

public class ArgumentValidator {
    private static final Logger logger = LogManager.getLogger(ArgumentValidator.class);
    
    // Define required arguments and their descriptions
    private static final Map<String, String> REQUIRED_ARGS = Map.of(
        "project", "Target project (e.g., 'appa', 'appb')"
    );

    public static Boolean hasValidArgs() {
        Boolean status = true;
        Map<String, String> missingArgs = new HashMap<>();
        REQUIRED_ARGS.forEach((arg, description) -> {
            String value = System.getProperty(arg); // Fetch from -Dargs
            if (value == null || value.trim().isEmpty()) {
                missingArgs.put(arg, description);
            }
        });

        if (!missingArgs.isEmpty()) {
            missingArgs.forEach((arg, description) -> 
                logger.error("[ERROR] Missing required argument: '-D{}' ({})", arg, description)
            );
            logger.info("[INFO] Example usage: mvn clean test -DexecutionMode=local -Dproject=appa \n"
            + "For more information, check the README.md or all validation will be inside the ArgumentValidator.java");
            status = false;
            throw new RuntimeException("Missing required arguments. Stopping test execution.");
        }
        return status;
    }
}