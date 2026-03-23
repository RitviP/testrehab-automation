package com.planetrehab.automation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.planetrehab.automation.utils.WebDriverUtils;
import com.planetrehab.automation.constants.FrameworkConstants;

public class LoginPage {

    private WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // 🔹 Username
    @FindBy(id = "authUser")
    private WebElement usernameField;

    // 🔹 Password
    @FindBy(id = "clearPass")
    private WebElement passwordField;

    // 🔹 Login Button
    @FindBy(id = "login-button")
    private WebElement loginBtn;

    // ===================== ACTIONS =====================

    public void enterUsername(String username) {
        WebDriverUtils.sendKeys(usernameField, username);
    }

    public void enterPassword(String password) {
        WebDriverUtils.sendKeys(passwordField, password);
    }

    public void clickLogin() {
        WebDriverUtils.click(loginBtn);
    }

    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLogin();
    }

    // ===================== VALIDATION =====================

    public boolean isLoginButtonDisplayed() {
        return WebDriverUtils.isElementDisplayed(
                loginBtn,
                FrameworkConstants.EXPLICIT_WAIT_TIME
        );
    }
}