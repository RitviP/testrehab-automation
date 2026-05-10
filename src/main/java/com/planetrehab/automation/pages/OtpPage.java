package com.planetrehab.automation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.planetrehab.automation.utils.WebDriverUtils;
import com.planetrehab.automation.constants.FrameworkConstants;

public class OtpPage {

    WebDriver driver;

    public OtpPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    
    @FindBy(xpath = "//div[@class='hint-text']")
    private WebElement otpHint;

    @FindBy(id = "otp")
    private WebElement otpInput;

    @FindBy(xpath = "//button[@class='btn btn-primary w-100']")
    private WebElement verifyBtn;

    // Fetch OTP from hint text
    public String fetchOtp() {
        WebDriverUtils.waitForElementToBeVisible(
                otpHint,
                FrameworkConstants.EXPLICIT_WAIT_TIME
        );

        String text = otpHint.getText();
        return text.replaceAll("\\D+", "");
    }

    // Enter OTP and click verify
    public DashboardPage verifyOtp() {

        String otp = fetchOtp();

        WebDriverUtils.waitForElementToBeVisible(
                otpInput,
                FrameworkConstants.EXPLICIT_WAIT_TIME
        );

        WebDriverUtils.waitForElementToBeClickable(
                otpInput,
                FrameworkConstants.EXPLICIT_WAIT_TIME
        );

        otpInput.clear();
        otpInput.sendKeys(otp);

        WebDriverUtils.waitForAttributeToBe(
                otpInput,
                "value",
                otp,
                FrameworkConstants.EXPLICIT_WAIT_TIME
        );

        WebDriverUtils.waitForElementToBeClickable(
                verifyBtn,
                FrameworkConstants.EXPLICIT_WAIT_TIME
        );

        verifyBtn.click();

        return new DashboardPage(driver);
    }

    public String getPageTitle() {
        return driver.getTitle();
    }
}