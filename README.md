# POM Java Project

## Description
This project implements the Page Object Model (POM) design pattern for a Java-based automation testing framework. It is designed to facilitate easy maintenance, scalability, and reusability of test automation scripts. The project uses Selenium, Cucumber, and JUnit for test execution and reporting.

---

## Folder Structure
```
pom-java-project
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com
│   │   │       └── example
│   │   │           ├── helpers
│   │   │           │   └── JsonReader.java
│   │   │           ├── locators
│   │   │           │   ├── HomeLocators.java
│   │   │           │   └── appa
│   │   │           │       └── HomeLocatorsAppa.java
│   │   │           ├── pages
│   │   │           │   ├── HomePage.java
│   │   │           │   └── appa
│   │   │           │       └── HomePageAppa.java
│   │   │           ├── pojos
│   │   │           │   └── User.java
│   │   │           ├── managerpojo
│   │   │           │   └── UserManager.java
│   │   │           ├── LocatorLoader.java
│   │   │           └── PageFactory.java
│   │   └── resources
│   │       └── config.properties
│   ├── test
│   │   ├── java
│   │   │   └── com
│   │   │       └── example
│   │   │           ├── runner
│   │   │           │   └── TestRunner.java
│   │   │           ├── setup
│   │   │           │   ├── DriverManager.java
│   │   │           │   └── TestContext.java
│   │   │           └── stepdefinitions
│   │   │               ├── Hooks.java
│   │   │               └── HomeSteps.java
│   └── resources
│       ├── features
│       │   └── home.feature
│       ├── junit-platform.properties
│       ├── test-config.properties
│       └── testdata
│           └── appa
│               └── user.json
├── pom.xml
├── Dockerfile
├── docker-compose.yml
└── README.md
```
Certainly! Here’s a brief description for each main folder in your structure. You can add this to your README.md under a **"Folder Descriptions"** section:

---

## Folder Descriptions

- **src/main/java/com/example/helpers**  
  Utility and helper classes used throughout the project (e.g., JSON readers, string utilities).

- **src/main/java/com/example/locators**  
  Contains locator classes that define how to find elements on web pages. Subfolders (e.g., `appa`) hold application-specific locators.

- **src/main/java/com/example/pages**  
  Page Object Model classes representing web pages and their behaviors. Subfolders (e.g., `appa`) hold application-specific page objects.

- **src/main/java/com/example/pojos**  
  Plain Old Java Objects (POJOs) used for data representation in tests (e.g., `User.java`).

- **src/main/java/com/example/managerpojo**  
  Manager classes for handling and providing logic around POJOs (e.g., `UserManager.java`).

- **LocatorLoader.java**  
  Utility class for loading locator definitions dynamically.

- **PageFactory.java**  
  Factory class for creating and managing page object instances.

- **src/main/resources**  
  Application configuration files and other resources used by the main codebase.

- **src/test/java/com/example/runner**  
  Test runner classes for executing test suites (e.g., `TestRunner.java`).

- **src/test/java/com/example/setup**  
  Test setup and context management classes (e.g., `DriverManager.java`, `TestContext.java`).

- **src/test/java/com/example/stepdefinitions**  
  Step definition classes for Cucumber feature files (e.g., `Hooks.java`, `HomeSteps.java`).

- **src/test/java/com/example/testdata**  
  Java classes for managing or representing test data (if any).

- **src/test/resources/features**  
  Cucumber feature files describing test scenarios in Gherkin syntax.

- **src/test/resources/testdata**  
  Static test data files (e.g., JSON files for users) used during test execution.

- **src/test/resources/junit-platform.properties, test-config.properties**  
  Configuration files for test execution and environment setup.

---

## Setup Instructions

### Prerequisites
- Install [Java 21](https://openjdk.org/projects/jdk/21/).
- Install [Maven](https://maven.apache.org/).
- Install [Docker](https://www.docker.com/).

### Maven actions - not required

- mvn clean - Deletes the target directory (removes compiled files and artifacts).
- mvn compile - Compiles source code (src/main/java).
- mvn test - Compiles and runs unit tests (src/test/java).
- mvn package - Compiles, tests, and packages the project (e.g., JAR/WAR in target/).
- mvn install - Installs the built artifact into the local Maven repository.
- mvn deploy -	Deploys the artifact to a remote repository (requires distributionManagement in pom.xml).
- mvn verify -	Runs integration tests and checks if the project is valid.
- mvn dependency:tree -	Displays the dependency tree (useful for conflict resolution).

### Steps
1. **Clone the repository**:
   ```bash
   git clone <repository-url>
   ```

2. **Navigate to the project directory**:
   ```bash
   cd pom-java-project
   ```

3. **Build the project**:
   ```bash
   mvn clean install
   ```

4. **Run the tests**:
   ```bash
   mvn test
   Or
   mvn clean test -DexecutionMode=local -Dproject=ApplicationA
   ```

---

## Running Local using Selenium Grid on localhost

### Steps
1. **Build the Docker image**:
   ```bash
   docker compose build --no-cache
   ```

2. **Start Selenium Grid services**:
   ```bash
   docker compose up -d selenium-hub session_browser_one session_browser_two
   ```

3. **Verify Selenium Grid is ready**:
   ```bash
   docker compose run --rm project-img curl -v http://selenium-hub:4444/wd/hub/status
   ```

4. **Run the tests**:
   ```bash
   mvn clean test -DexecutionMode=grid -DgridUrl=http://localhost:4444/wd/hub


## Running with Selenium Grid Using Docker Compose

### Steps
1. **Build the Docker image**:
   ```bash
   docker compose build --no-cache
   ```

2. **Start Selenium Grid services**:
   ```bash
   docker compose up -d selenium-hub session_browser_one session_browser_two
   ```

3. **Verify Selenium Grid is ready**:
   ```bash
   docker compose run --rm project-img curl -v http://selenium-hub:4444/wd/hub/status
   ```

4. **Run the tests**:
   ```bash
   docker compose run --rm project-img mvn clean test -DexecutionMode=grid -DgridUrl=http://selenium-hub:4444/wd/hub
   ```

5. **Access the test reports**:
   - The test reports will be saved in the `tmp/reports` directory inside the container.
   - To access them on your host machine, mount the `reports` directory as a volume:
     ```yaml
     volumes:
       - ./tmp/reports:/app/target/reports
     ```

---

## Usage
- **Test Cases**: Located in `src/test/java/com/example/stepdefinitions`.
- **Feature Files**: Located in `src/test/resources/features`.
- **Page Objects**: Located in `src/main/java/com/example/pages`.

---

## Contributing
Contributions are welcome! Please create a pull request or open an issue for any enhancements or bug fixes.

---
