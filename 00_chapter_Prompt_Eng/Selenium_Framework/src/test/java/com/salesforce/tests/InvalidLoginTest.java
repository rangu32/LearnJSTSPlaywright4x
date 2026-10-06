package com.salesforce.tests;

import com.salesforce.base.BaseTest;
import com.salesforce.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class InvalidLoginTest extends BaseTest {

    @Test
    public void testInvalidLoginDisplaysErrorMessage() {
        try {
            LoginPage loginPage = new LoginPage(driver);
            Assert.assertTrue(loginPage.isLoginPageLoaded(), "Login page failed to load properly.");

            loginPage.doLogin("invalid_crm_tester@testcompany.com", "WrongPassword123!");

            Assert.assertTrue(loginPage.isErrorMessageDisplayed(), "Error message was not displayed for invalid login attempt.");

            String actualError = loginPage.getErrorMessage();
            String expectedError = "Please check your username and password. If you still still can't log in, contact your Salesforce administrator.";
            Assert.assertTrue(actualError.contains("Please check your username and password"), "Error message content mismatch. Actual: " + actualError);
        } catch (AssertionError e) {
            throw e;
        } catch (Exception e) {
            Assert.fail("Invalid login execution failed due to an exception: " + e.getMessage(), e);
        }
    }
}
