
# 🚀 QesteniumX

Das maßgeschneiderte Testautomatisierungs-Framework wurde für das Testing Center of Excellence (TCoE) von Qestit entworfen und kann flexibel für unterschiedliche Projekte angepasst werden. Es zielt darauf ab, einzigartige Funktionen zu bieten, die häufige Probleme in existierenden Frameworks beheben​

QesteniumX is a robust and scalable Selenium framework designed to simplify the process of web application testing. Built using Java, and integrated with testNG and Maven, this framework aims to provide an efficient and effective approach to automated testing, making it easier for developers and QA engineers to write, manage, and execute tests.


## 👨‍💻 Some Features
[![Docker](https://img.shields.io/badge/Docker-2496ED?style=flat-square&logo=docker)](https://www.docker.com/)[![Jenkins](https://img.shields.io/badge/Jenkins-D24939?style=flat-square&logo=jenkins)](https://www.jenkins.io/)![GitHub Actions](https://img.shields.io/badge/GitHub_Actions-2088FF?style=flat-square&logo=github-actions)[![Jira](https://img.shields.io/badge/Jira-0052CC?style=flat-square&logo=jira)](https://www.atlassian.com/software/jira)[![Allure](https://img.shields.io/badge/Allure-FF4500?style=flat-square&logo=allure)](https://allure.qatools.ru/)![SSH (Bash)](https://img.shields.io/badge/SSH_(Bash)-000000?style=flat-square&logo=gnu-bash)

### Core Features

Selenium Integration: Leverages Selenium for robust browser automation and testing.
Java-Based: Utilizes Java for flexible and powerful test script development.
testNG Framework: Employs testNG for organized testing with annotations, grouping, and dependencies.
Maven Support: Simplifies project management and build processes with Maven.

### Advanced Features

Parallel Test Execution: Supports running tests in parallel, reducing the time required for extensive test suites.
Video Recording: Automatically records videos of test executions, aiding in debugging and documentation.
Test Data Management: Efficiently manage and utilize test data from various sources.
Data Handling

JSON Integration: Facilitates reading test data directly from JSON files, enhancing data-driven testing approaches.
Excel Support: Enables the extraction of test data from Excel files, offering versatility in test data management.

### Notifications and Reporting

Email Notifications: Configurable to send email summaries post-test execution, keeping teams informed.
Telegram Integration: Supports sending real-time test execution alerts and reports through Telegram.
Comprehensive Reporting: Generates detailed reports via Allure and Extent Reports, providing insights into test results.

### Tool Integrations

JIRA Integration: Links test cases and results with JIRA issues for seamless tracking and management.
CI/CD Pipelines: Integrates with CI/CD tools like GitHub Actions, enabling automated test execution within deployment pipelines.
Docker Support: Containerizes tests for consistent execution environments and simplifies test setup.
Jenkins Integration: Seamlessly runs within Jenkins pipelines, facilitating continuous testing.
Selenium Grid: Utilizes Selenium Grid for distributed test execution across various environments.

### Testing Strategies

Keyword-Driven Testing: Supports keyword-driven approaches, allowing for flexible test case creation without extensive coding.
Data-Driven Testing: Enhances test coverage with data-driven tests, easily scaling with varied test data.

## 💥 Tech Stack

Selenium, Java, TestNG, Maven
## ![Static Badge](https://img.shields.io/badge/Selenium-dv?logo=selenium&labelColor=black&link=https%3A%2F%2Fwww.selenium.dev%2F)[![TestNG](https://img.shields.io/badge/TestNG-7.9.0-007ACC?style=flat-square&logo=testng)](https://testng.org/)[![Maven](https://img.shields.io/badge/Maven-3.9.4-8CC84B?style=flat-square&logo=apache-maven)](https://maven.apache.org/)[![Java](https://img.shields.io/badge/Java-17-ED8B00?style=flat-square&logo=java)](https://www.oracle.com/java/technologies/)

## 💥 Prerequisites

Before setting up the QesteniumX framework, ensure you have the following installed:

Java Development Kit (JDK) 8 or newer
Maven 3.6.0 or newer
Latest version of Selenium WebDriver

## 💥 Installation

Clone the QesteniumX repository from GitHub:

```bash
  git clone https://github.com/qestitmavericks/QesteniumX.git

  cd cd QesteniumX
```
Use Maven to install dependencies and build the project:

```bash
 mvn clean install
```
    
## 💥 Configuration

Configure the framework to suit your project's requirements:

Edit the config.properties file to set up your base URL, browser preferences, and other configurations.
Customize logging levels and outputs in the log4j.properties file.

## 💥 Writing Tests

Write your test cases in the src/test/java directory.
Use the @Test annotation to denote test methods, and employ testNG features like data providers for data-driven testing.

## 💥 Running Tests
[![Static Badge](https://img.shields.io/badge/Tests-Passed-green?style=flat-square)](https://github.com/qestitmavericks/QesteniumX/actions)

To run tests, run the following command

```bash
  mvn clean test
```


## 💥 Reporting

QesteniumX supports comprehensive reporting via Allure and Extent Reports, enhancing the visibility and analysis of test results.

### Allure Reports
Generates interactive and detailed reports for test execution.
To generate Allure reports, ensure Allure is configured in your pom.xml, and execute:
```bash
  mvn allure:serve
```
This command will generate the report and open it in your default browser.

### Extent Reports
Provides rich HTML reports with extensive details about test execution.
The framework is pre-configured to generate Extent reports in the test-output/ directory after test execution.
Open the HTML file in a browser to view the report.


## 💥 Selenium Contributors

testNG Community
Maven Project
All contributors and supporters of the QesteniumX project

## 💥 License
![Static Badge](https://img.shields.io/badge/License-MIT-red)