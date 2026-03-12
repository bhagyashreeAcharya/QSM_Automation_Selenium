# QSM Automation – Selenium TestNG

## Overview

This project contains automated UI tests for the **QMS (Quality Management System)** application using **Selenium WebDriver with Java and TestNG**.

The automation currently covers:

* Login functionality
* Navigation to **Quality Events**
* Opening **Deviations**
* Launching **New Deviation / Non-conformance form**

The goal of this project is to demonstrate **basic UI automation workflow and Selenium skills**.

---

# Tech Stack

* Java
* Selenium WebDriver
* TestNG
* Maven
* IntelliJ IDEA
* Chrome Browser

---

# Project Structure

```
src
 ├─ main
 │   └─ java
 │       └─ org.example
 └─ test
     └─ java
         ├─ LoginTest.java
         └─ DeviationTest.java
```

---

# Prerequisites

Make sure the following are installed:

* Java 17+
* Maven
* Google Chrome
* IntelliJ IDEA (recommended)

Selenium Manager automatically handles the ChromeDriver.

---

# How to Run the Tests

### 1. Clone the repository

```
git clone https://github.com/bhagyashreeAcharya/QSM_Automation_Selenium.git
```

### 2. Open the project

Open the project in **IntelliJ IDEA**.

### 3. Install dependencies

Maven will automatically download required dependencies from `pom.xml`.

If needed run:

```
mvn clean install
```

### 4. Run the tests

Run using TestNG from IntelliJ:

Right click the test class

```
DeviationTest.java
```

Then click

```
Run DeviationTest
```

Chrome browser will launch and execute the automated test.

---

# Test Scenario Automated

1. Open login page
2. Enter valid credentials
3. Login to dashboard
4. Open sidebar menu
5. Navigate to **Quality Events**
6. Click **Deviations**
7. Click **New Deviation / Non-conformance**

---


# Author

Bhagyashree Acharya
QA Automation Enthusiast
