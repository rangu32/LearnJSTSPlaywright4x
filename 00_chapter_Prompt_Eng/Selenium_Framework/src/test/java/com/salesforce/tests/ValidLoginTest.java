package com.salesforce.tests;

import com.salesforce.base.BaseTest;
import com.salesforce.pages.LoginPage;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class ValidLoginTest extends BaseTest {

    @Test
    public void testValidLoginSubmission() {
        try {
            LoginPage loginPage = new LoginPage(driver);
            Assert.assertTrue(loginPage.isLoginPageLoaded(), "Login page failed to load properly.");

            loginPage.setRememberMe(true);
            Assert.assertTrue(loginPage.isRememberMeSelected(), "Remember me checkbox was not selected.");

            loginPage.doLogin("automation_qa_user@company.com", "SecurePassword@123");

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            boolean redirected = wait.until(ExpectedConditions.not(ExpectedConditions.urlToBe(baseUrl)));
            Assert.assertTrue(redirected, "User was not redirected after submitting valid credentials.");
        } catch (AssertionError e) {
            throw e;
        } catch (Exception e) {
            Assert.fail("Valid login execution failed due to an exception: " + e.getMessage(), e);
        }
    }
}
