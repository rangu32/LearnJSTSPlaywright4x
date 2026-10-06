package com.salesforce.pages;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    @FindBy(xpath = "//input[@id='username']")
    private WebElement usernameInput;

    @FindBy(xpath = "//input[@id='password']")
    private WebElement passwordInput;

    @FindBy(xpath = "//input[@id='Login']")
    private WebElement loginButton;

    @FindBy(xpath = "//input[@id='rememberUn']")
    private WebElement rememberMeCheckbox;

    @FindBy(xpath = "//div[@id='error']")
    private WebElement errorMessage;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public boolean isLoginPageLoaded() {
        try {
            wait.until(ExpectedConditions.visibilityOf(usernameInput));
            return loginButton.isDisplayed();
        } catch (TimeoutException | NoSuchElementException e) {
            return false;
        } catch (Exception e) {
            throw new RuntimeException("Error verifying login page load: " + e.getMessage(), e);
        }
    }

    public void enterUsername(String user) {
        try {
            wait.until(ExpectedConditions.visibilityOf(usernameInput));
            usernameInput.clear();
            usernameInput.sendKeys(user);
        } catch (TimeoutException | NoSuchElementException e) {
            throw new RuntimeException("Failed to interact with username input field: " + e.getMessage(), e);
        } catch (Exception e) {
            throw new RuntimeException("Unexpected error entering username: " + e.getMessage(), e);
        }
    }

    public void enterPassword(String pass) {
        try {
            wait.until(ExpectedConditions.visibilityOf(passwordInput));
            passwordInput.clear();
            passwordInput.sendKeys(pass);
        } catch (TimeoutException | NoSuchElementException e) {
            throw new RuntimeException("Failed to interact with password input field: " + e.getMessage(), e);
        } catch (Exception e) {
            throw new RuntimeException("Unexpected error entering password: " + e.getMessage(), e);
        }
    }

    public void clickLogin() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(loginButton));
            loginButton.click();
        } catch (TimeoutException | NoSuchElementException e) {
            throw new RuntimeException("Failed to click login button: " + e.getMessage(), e);
        } catch (Exception e) {
            throw new RuntimeException("Unexpected error clicking login button: " + e.getMessage(), e);
        }
    }

    public void setRememberMe(boolean select) {
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(org.openqa.selenium.By.xpath("//input[@id='rememberUn']")));
            if (rememberMeCheckbox.isSelected() != select) {
                rememberMeCheckbox.click();
            }
        } catch (TimeoutException | NoSuchElementException e) {
            throw new RuntimeException("Failed to toggle remember me checkbox: " + e.getMessage(), e);
        } catch (Exception e) {
            throw new RuntimeException("Unexpected error setting remember me: " + e.getMessage(), e);
        }
    }

    public boolean isRememberMeSelected() {
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(org.openqa.selenium.By.xpath("//input[@id='rememberUn']")));
            return rememberMeCheckbox.isSelected();
        } catch (Exception e) {
            throw new RuntimeException("Error retrieving remember me state: " + e.getMessage(), e);
        }
    }

    public void doLogin(String user, String pass) {
        enterUsername(user);
        enterPassword(pass);
        clickLogin();
    }

    public void doLogin(String user, String pass, boolean rememberMe) {
        enterUsername(user);
        enterPassword(pass);
        setRememberMe(rememberMe);
        clickLogin();
    }

    public String getErrorMessage() {
        try {
            wait.until(ExpectedConditions.visibilityOf(errorMessage));
            return errorMessage.getText().trim();
        } catch (TimeoutException | NoSuchElementException e) {
            throw new RuntimeException("Error message element not displayed within timeout: " + e.getMessage(), e);
        } catch (Exception e) {
            throw new RuntimeException("Unexpected error retrieving error message: " + e.getMessage(), e);
        }
    }

    public boolean isErrorMessageDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOf(errorMessage));
            return errorMessage.isDisplayed();
        } catch (TimeoutException | NoSuchElementException e) {
            return false;
        } catch (Exception e) {
            throw new RuntimeException("Error checking error message visibility: " + e.getMessage(), e);
        }
    }
}
