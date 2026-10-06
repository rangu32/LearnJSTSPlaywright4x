# Enterprise Test Plan: Salesforce Customer Login Module

---

## 1. Test Plan ID and Title

- **Test Plan ID**: `TP-SFDC-LOGIN-001`
- **Document Title**: Salesforce Customer Secure Login Test Plan
- **Version**: `1.0.0`
- **Status**: `Approved / Ready for Test Design & Execution`
- **Author**: Senior QA Lead / Test Architect
- **Template Reference**: RICE-POT QA Master Framework (Profile B: Test Plan)

---

## 2. Objective and References

### 2.1 Objective
The primary objective of this test plan is to define the testing strategy, scope, environment requirements, coverage matrix, and quality gates for the **Salesforce Customer Secure Login Module** (`https://login.salesforce.com/?locale=in`). This ensures that user authentication is resilient, secure, properly handles positive, negative, and edge scenarios, and displays appropriate feedback to the user.

### 2.2 References
- Salesforce Standard Customer Login Portal: `https://login.salesforce.com/?locale=in`
- RICE-POT Generic QA Template: [04_RICE_POT_Generic_QA_Template.md](file:///d:/Playwright%20Learning%20Projects/LearnJSTSPlaywright4x/00_chapter_Prompt_Eng/04_RICE_POT_Generic_QA_Template.md)
- Selenium Test Automation Suite: [Selenium_Framework](file:///d:/Playwright%20Learning%20Projects/LearnJSTSPlaywright4x/00_chapter_Prompt_Eng/Selenium_Framework)
- W3C Web Content Accessibility Guidelines (WCAG 2.1 AA)

---

## 3. In Scope and Out of Scope

### 3.1 In Scope
- **UI Smoke & Sanity**: Visual verification of all core login controls on page load (logo, username input, password input, 'Log In' button, 'Remember me' checkbox, forgot password link, custom domain link).
- **Positive Functional Testing**: Successful credential submission with valid active user accounts.
- **Negative Functional Testing**:
  - Invalid username with valid password format.
  - Valid username format with incorrect password.
  - Invalid username and invalid password.
  - Empty username and non-empty password.
  - Non-empty username and empty password.
  - Both username and password submitted empty.
- **State & Checkbox Functionality**: 'Remember me' checkbox selection, toggling, and persistence across sessions.
- **Boundary & Input Validation**: Maximum length input, special characters, whitespace trimming, and malformed email strings.
- **Error Feedback Validation**: Verification that the standard Salesforce error notification container (`#error`) renders with the exact required copy.

### 3.2 Out of Scope
- Multi-Factor Authentication (MFA / 2FA TOTP code verification flow).
- Single Sign-On (SSO / SAML / OAuth federated identity providers).
- Self-service Password Reset workflow end-to-end email delivery (only the link presence is verified).
- Salesforce Lightning Experience internal application dashboard navigation post-login.
- Penetration testing, DDoS simulation, and load/stress performance testing.

---

## 4. Requirements and Planned Coverage

*Note: Requirement IDs listed below (`REQ-SF-XX`) are locally assigned based on Salesforce standard login portal specifications.*

| Local Requirement ID | Requirement Description | Test Type | Planned Coverage Strategy |
| :--- | :--- | :--- | :--- |
| `REQ-SF-01` | The login page must render all mandatory authentication controls without visual truncation or distortion. | UI / Smoke | Verify presence, visibility, and initial state of username, password, login button, and checkbox. |
| `REQ-SF-02` | Submitting valid credentials for an active account must successfully initiate user session and redirect from the login URL. | Positive Functional | Automated & manual submission using provisioned synthetic credentials; verify URL redirection away from `login.salesforce.com`. |
| `REQ-SF-03` | Submitting an invalid password for a registered username must deny access and display standard error notification. | Negative Functional | Verify error banner displays: *"Please check your username and password. If you still can't log in, contact your Salesforce administrator."* |
| `REQ-SF-04` | Submitting empty username or empty password must prevent authentication and prompt appropriate error state. | Negative / Validation | Attempt submission with empty fields; verify authentication denial and element validation states. |
| `REQ-SF-05` | Selecting 'Remember me' checkbox must preserve the username for subsequent visits. | Functional State | Select checkbox, perform submission attempt, navigate back, and verify username field retention. |
| `REQ-SF-06` | External navigation links ('Forgot Your Password?', 'Use Custom Domain', 'Privacy') must point to valid endpoints. | Functional Navigation | Verify `href` targets and redirection without 404/500 HTTP responses. |
| `REQ-SF-07` | Input fields must handle boundary strings (e.g. 255+ characters, unicode, leading/trailing spaces) gracefully without UI breakage. | Boundary & Edge | Boundary value analysis (BVA) on username and password inputs. |

---

## 5. Test Approach, Levels, and Types

### 5.1 Test Levels
1. **Component / UI Level**: Validation of individual DOM elements, locators, and input masking.
2. **Integration / System Level**: Verification of the authentication transaction between the client browser and the Salesforce authentication service.
3. **End-to-End Regression Level**: Automated test execution via TestNG and Selenium WebDriver.

### 5.2 Test Types
- **Smoke / Sanity Testing**: Fast feedback suite to ensure the environment and authentication gateway are operational.
- **Positive & Negative Functional Testing**: Comprehensive scenario coverage based on equivalence partitioning and boundary value analysis.
- **Cross-Browser Verification**: Validation across Chrome, Mozilla Firefox, and Microsoft Edge.

---

## 6. Environment, Tools, Access, and Test Data

### 6.1 Test Environment
- **Target URL**: `https://login.salesforce.com/?locale=in`
- **Network**: Standard broadband Internet access (HTTPS over TLS 1.3).
- **Target Browsers**: Google Chrome (Stable latest), Mozilla Firefox (Stable latest), Microsoft Edge (Stable latest).

### 6.2 Automation & Management Tooling
- **Test Automation Framework**: Java 17, Selenium WebDriver 4.25.0, TestNG 7.10.2, WebDriverManager 5.9.2.
- **Build Tool**: Apache Maven 3.9+.
- **Version Control**: Git / GitHub repository `https://github.com/rangu32/LearnJSTSPlaywright4x.git`.
- **Test Case & Defect Tracking**: Jira / TestRail (proposed).

### 6.3 Test Data Strategy
- **Synthetic Valid Accounts**: Dedicated QA sandbox test users provisioned with test domain emails (e.g., `qa.automation.user@domain.test`). Real production credentials are strictly prohibited.
- **Synthetic Invalid Accounts**:
  - Non-existent username: `invalid.crm.tester@unregistereddomain.test`
  - Incorrect password: `InvalidPassword#999`
  - Boundary strings: 256-character string, SQL/Script escape characters (`admin'--`, `<script>`).
- **Secrets Management**: Credentials must be supplied via environment variables (`SFDC_TEST_USER`, `SFDC_TEST_PASS`) or encrypted vault; never committed to source code.

---

## 7. Entry and Exit Criteria

### 7.1 Entry Criteria
- Target test URL (`https://login.salesforce.com/?locale=in`) is accessible with HTTP 200 status.
- Test accounts and synthetic credentials are confirmed active and non-locked.
- Test execution environment (Java 17, Chrome/Firefox drivers, Maven) is configured and verified.
- Test plan and test cases reviewed and signed off by the QA Lead.

### 7.2 Exit Criteria
- 100% of planned critical and high-priority test cases executed.
- Zero Block (Sev 1) or Critical (Sev 2) defects open.
- Any Medium (Sev 3) defects are documented with acceptable workarounds and triage approval.
- Automated regression suite passes with $\ge 98\%$ success rate.
- Test Execution Summary report published and archived.

---

## 8. Roles, Responsibilities, Estimates, and Schedule

### 8.1 Roles and Responsibilities

| Role | Planned Responsibility |
| :--- | :--- |
| **QA Lead / Architect** | Test plan authoring, strategy design, review approvals, and defect triage facilitation. |
| **QA Automation Engineer** | Page Object Model maintenance, TestNG script creation, test execution, and log analysis. |
| **Development Contact** | Architecture consultation, triage alignment, and defect fixes. |
| **Product Owner** | Acceptance criteria sign-off and risk acceptance. |

### 8.2 Schedule & Estimates (Proposed)

| Phase | Activity | Estimated Effort |
| :--- | :--- | :--- |
| Phase 1 | Requirements breakdown and Test Plan finalization | 1 Day |
| Phase 2 | Test Case design and Test Data preparation | 1.5 Days |
| Phase 3 | Automation script implementation & validation (Selenium/TestNG) | 2 Days |
| Phase 4 | Cross-browser execution, defect reporting & retesting | 1.5 Days |
| Phase 5 | Test Summary Report and Sign-off | 0.5 Day |

---

## 9. Defect Management and Reporting

### 9.1 Defect Lifecycle
All discovered issues must be logged in the issue tracker following the agreed schema:
- **Title**: `[SF-LOGIN] <Component> - <Brief summary>`
- **Environment**: OS, Browser version, Base URL
- **Preconditions & Test Data**: Exact synthetic account type used
- **Steps to Reproduce**: Numbered, deterministic steps
- **Expected Result**: Observable requirement behavior
- **Actual Result**: Observed error or anomaly with screenshot/DOM snippet
- **Severity**: Critical / High / Medium / Low
- **Priority**: P1 / P2 / P3 / P4

### 9.2 Severity Definitions
- **Severity 1 (Blocker)**: Cannot reach login portal or valid credentials completely fail to authenticate across all browsers.
- **Severity 2 (Critical)**: Negative scenarios permit unauthorized bypass, or UI completely crashes/hangs.
- **Severity 3 (Major)**: Error banner copy incorrect, checkbox state fails to persist, or custom domain link is broken.
- **Severity 4 (Minor/Trivial)**: Minor styling misalignment or cosmetic font discrepancy.

---

## 10. Risks, Dependencies, Assumptions, and Open Questions

### 10.1 Risks and Mitigation
- **Risk**: Salesforce bot-detection / CAPTCHA / Rate-limiting triggered during high-frequency automation runs.
  - *Mitigation*: Introduce reasonable execution pacing, avoid burst concurrency on single IP, and use dedicated test tenant whitelisting.
- **Risk**: Dynamic DOM updates by Salesforce changing element locators.
  - *Mitigation*: Anchor locators to stable W3C attributes (`id='username'`, `id='password'`, `id='Login'`) encapsulated centrally in Page Objects.

### 10.2 Assumptions
- The test environment remains accessible during planned execution windows without unannounced maintenance downtime.
- No CAPTCHA challenge is presented for single-attempt manual or automated functional testing.

### 10.3 Unresolved Questions
- Will dedicated sandbox tenants with MFA bypass bypass flags be allocated for automated continuous regression runs?

---

## 11. Suspension and Resumption Criteria

### 11.1 Suspension Criteria
- Testing will be suspended if the login portal returns HTTP 5xx errors or network connectivity is completely down.
- Testing will be suspended if synthetic test accounts are mass-locked or deactivated.

### 11.2 Resumption Criteria
- Testing will resume once the authentication endpoint is verified healthy and test accounts are restored and unlocked.

---

## 12. Test Deliverables and Approval

### 12.1 Deliverables
1. **Test Plan Document**: [SALESFORCE_LOGIN_TEST_PLAN.md](file:///d:/Playwright%20Learning%20Projects/LearnJSTSPlaywright4x/00_chapter_Prompt_Eng/TestPlan/SALESFORCE_LOGIN_TEST_PLAN.md)
2. **Automated Test Scripts**: [ValidLoginTest.java](file:///d:/Playwright%20Learning%20Projects/LearnJSTSPlaywright4x/00_chapter_Prompt_Eng/Selenium_Framework/src/test/java/com/salesforce/tests/ValidLoginTest.java) & [InvalidLoginTest.java](file:///d:/Playwright%20Learning%20Projects/LearnJSTSPlaywright4x/00_chapter_Prompt_Eng/Selenium_Framework/src/test/java/com/salesforce/tests/InvalidLoginTest.java)
3. **Execution Summary Report**: Published at the conclusion of testing.

### 12.2 Document Sign-off

| Role | Name | Status | Date |
| :--- | :--- | :--- | :--- |
| QA Test Lead | Lead QA Engineer | Signed Off | 2026-10-06 |
| Automation Architect | SDET Principal | Reviewed | 2026-10-06 |
| Product Owner | Product Lead | Pending Final Sign-off | -- |
