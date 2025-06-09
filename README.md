# Selenium Automation Framework

This is a **Java-based Selenium test automation framework** with integrated TestNG support. It is designed for scalable, maintainable, and readable test execution for web applications like SauceDemo.

---

##  Features

* Selenium WebDriver (Chrome, Firefox, Edge)
* TestNG test lifecycle management
* Utility-driven architecture
* Properties-based test data configuration
* Resilient `PageBase` with retry logic
* Modular `TestBase` for browser setup/teardown
* Logging, custom reports, and optional email hooks

---

##  Project Structure
.
├── BaseClasses
│   ├── PageBase.java
│   └── TestBase.java
│
├── Utils
│   ├── extentReports/
│   ├── HelperClasses/
│   ├── listeners/
│   ├── logs/
│   ├── sendEmails/
│   ├── ReportUtils.java
│   └── testdata.properties
│
├── SauceDemoTest.java
├── SauceDemoOfficeTest1.java
└── README.md
```

---

## 🧪 How Tests Work

* All test classes extend `TestBase`.
* WebDriver setup is based on `browser` and `baseURL` from `testdata.properties`.
* Test data is pulled dynamically using `getTestData(String key)`.
* `PageBase` provides generic actions like:

  * `click`, `enterText`, `scrollToElement`
  * Dynamic wait handling
  * Element retry logic for flaky elements

---

## 🛠 Setup Instructions

### Prerequisites

* Java 8+
* Maven
* TestNG (configured in IDE or POM)
* Chrome, Firefox, or Edge browsers

### 1. Clone the repo

```bash
git clone https://github.com/Tonykohh/Enyata_Interview_Repository.git
cd selenium-framework
```

### 2. Configure test data

Edit `Utils/testdata.properties`:

```
browser=chrome
baseURL=https://www.saucedemo.com
```

### 3. Run a test manually

From your IDE, run:

* `SauceDemoTest.java` or
* `SauceDemoOfficeTest1.java`

To run from terminal (optional TestNG XML support needed):

```bash
mvn test
```

---

##  Highlights in `PageBase`

* `waitForClickAbility()` and `waitForVisibility()` to sync tests
* Retry-safe `click()` and `enterText()` to handle stale elements
* `scrollToElement()` using JavaScript for dynamic UI scrolling
* `clickBuyDataCard()` - custom utility for dynamic card-based UIs

##  Highlights in `TestBase`

* Loads properties from `testdata.properties`
* Sets up driver per specified browser
* Tears down cleanly after tests

---

##  Example Test Usage

```java
public class SauceDemoTest extends TestBase {
    @Test
    public void loginTest() {
        LoginPage login = new LoginPage(driver);
        login.login(getTestData("username"), getTestData("password"));
        Assert.assertTrue(login.isLoginSuccessful());
    }
}
```

---

##  Optional Utilities

You can expand into:

* `sendEmails` to notify stakeholders post-run
* `extentReports` for styled test reports
* `listeners` for custom test lifecycle hooks

---

##  Contributing

Want to add features or improve stability?
Feel free to fork and PR.

---

## 👨‍💻 Author

Your Name — [tonykohh](https://github.com/tonykohh)

---

# Report Generation Steps for Selenium Project

## Overview

This document details the steps required to generate a test report in your Selenium project, including dependencies and file setups.

## Steps to Generate a Report

1. **Set Up Extent Reports Dependencies**
   - Add the following dependencies to your `pom.xml` file for Maven projects or include the necessary JAR files for other build tools.

     ```xml
     <dependency>
         <groupId>com.aventstack</groupId>
         <artifactId>extentreports</artifactId>
         <version>5.0.9</version>
     </dependency>
     <dependency>
                 <groupId>org.testng</groupId>
                 <artifactId>testng</artifactId>
                 <version>7.8.0</version>
                 <scope>test</scope>
             </dependency>
       <dependency>
                 <groupId>org.apache.logging.log4j</groupId>
                 <artifactId>log4j-api</artifactId>
                 <version>2.23.1</version>
             </dependency>

      <dependency>
                 <groupId>org.apache.logging.log4j</groupId>
                 <artifactId>log4j-core</artifactId>
                 <version>2.23.1</version>
             </dependency>

             <dependency>
                 <groupId>org.slf4j</groupId>
                 <artifactId>slf4j-api</artifactId>
                 <version>2.0.13</version>
             </dependency>
     ```

2. **Create ExtentManager Class**
   - This class is responsible for configuring the ExtentReports object.

   **Files to Create:**
   - `ExtentManager.java`

   **Steps:**
   - Create a singleton pattern to ensure only one instance of ExtentReports is created.
   - Set up the report file path.
   - Configure the report (e.g., adding system info, report theme, etc.).

3. **Create ExtentTestManager Class**
   - This class manages individual test instances and logs test steps.

   **Files to Create:**
   - `ExtentTestManager.java`

   **Steps:**
   - Use a ThreadLocal object to handle parallel execution.
   - Create methods to start and get the current test.

4. **Initialize the Report in TestNG Listeners**
   - Use TestNG listeners to initialize and flush the report.

   **Files to Modify/Create:**
   - `TestListener.java` (implementing `ITestListener`)

   **Steps:**
   - In the `onStart` method, initialize the ExtentReports object.
   - In the `onFinish` method, flush the report.
   - Use the `onTestStart`, `onTestSuccess`, `onTestFailure`, `onTestSkipped`, and `onTestFailedButWithinSuccessPercentage` methods to log test events.

5. **Integrate Report Generation in Tests**
   - In your test classes, use the `ExtentTestManager` to log test steps and statuses.

   **Steps:**
   - At the start of each test, create a new test instance using `ExtentTestManager.startTest`.
   - Log the test steps using `log` methods.
   - Mark test status (pass, fail, skip) in the `ITestListener` methods.

6. **Configure TestNG XML**
   - Ensure your TestNG XML file includes the listener.

   **Files to Modify:**
   - `testng.xml`

   **Steps:**
   - Add the listener class in the `<listeners>` section of your `testng.xml`.

     ```xml
     <listeners>
         <listener class-name="path.to.your.TestListener"/>
     </listeners>
     ```

