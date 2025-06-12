package com.example.runner;

import io.cucumber.core.cli.Main;

public class DebugRunner {
    public static void main(String[] args) {
        System.setProperty("project", "appb");
        System.setProperty("executionMode", "local");
        Main.run(
            "--glue", "com.example.stepdefinitions",
            "--plugin", "pretty",
            "--plugin", "html:tmp/reports/cucumber-report.html",
            "--threads", "4",
            "--tags", "@smoke",  // Optional: only if you want to filter for smoke tests
            "classpath:features"
        );
    }
}