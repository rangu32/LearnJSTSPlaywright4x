# Learn JS, TS, & Playwright 4x Automation

Repository for JavaScript, TypeScript, Playwright automation learning, Prompt Engineering for QA, and Enterprise Test Automation frameworks.

---

## Repository Structure

```text
LearnJSTSPlaywright4x/
├── 00_chapter_Prompt_Eng/
│   ├── 00_RICE_POT_FullForm.md
│   ├── 01_RICE_POT_Prompt.md
│   ├── 02_Problem_Statement.md
│   ├── 03_Anti_Hallucinations.md
│   ├── 04_RICE_POT_Generic_QA_Template.md
│   └── Selenium_Framework/
│       ├── pom.xml
│       ├── testng.xml
│       └── src/
│           ├── main/java/com/salesforce/pages/LoginPage.java
│           └── test/java/com/salesforce/
│               ├── base/BaseTest.java
│               └── tests/
│                   ├── ValidLoginTest.java
│                   └── InvalidLoginTest.java
├── 01_chapter_JS_Basics/
└── 02_chapter_JS_Keywrods_Literals/
```

---

## Modules Overview

### 1. `00_chapter_Prompt_Eng`
- **RICE-POT Framework**: Role, Instructions, Context, Examples, Parameters, Output, and Tone methodology for SDET prompt engineering.
- **Anti-Hallucination Guidelines**: Strategies to avoid AI hallucinations in automated test generation.
- **Enterprise Selenium Framework**:
  - Selenium 4 + Java 17 + TestNG + WebDriverManager.
  - Page Object Model with PageFactory and strictly XPath locators.
  - Structured exception handling and dynamic `WebDriverWait`.
  - Valid and invalid login test suites for Salesforce CRM.

### 2. `01_chapter_JS_Basics`
- Core JavaScript fundamentals for automation testing: variables, data types, functions, and control flow.

### 3. `02_chapter_JS_Keywrods_Literals`
- JavaScript keywords (`let`, `const`, `var`), literals, scope, hoisting, and object/array patterns.

---

## Running the Selenium Automation Framework

Navigate to the framework directory:
```bash
cd 00_chapter_Prompt_Eng/Selenium_Framework
```

Run test suite via Maven:
```bash
mvn clean test
```
