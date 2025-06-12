Feature: Access the Selenium initial Screen

    @pojo_test
    Scenario: Access the Selenium initial Screen - Smoke
        When "Felipe" accesses the URL "https://www.selenium.dev/"
        Then he should see the title "SeleniumHQ Browser Automation"
        And the "Felipe" should be the POJO User
    
    
    @regression
    Scenario: Access the Selenium initial Screen - Regression
        When he accesses the URL "https://www.selenium.dev/"
        Then he should see the title "SeleniumHQ Browser Automation"

    @test
    Scenario: Access the Selenium initial Screen - Tets
        When he accesses the URL "https://www.selenium.dev/"
        Then he should see the title "SeleniumHQ Browser Automation"