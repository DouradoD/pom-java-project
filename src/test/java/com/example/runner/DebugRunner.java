package com.example.runner;

import io.cucumber.core.cli.Main;

public class DebugRunner {
    
    public static void main(String[] args) {
        // System.setProperty("project", "appa");
        System.setProperty("executionMode", "local");
        Main.run(
            "--glue", "com.example.stepdefinitions",
            "--plugin", "pretty",
            "--plugin", "html:tmp/reports/cucumber-report.html",
            "--threads", "4",
            "--tags", "@pojo_test",  // Optional: only if you want to filter for smoke tests
            "classpath:features"
        );
    }
}