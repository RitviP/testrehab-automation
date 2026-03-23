package com.planetrehab.automation.utils;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.planetrehab.automation.base.DriverFactory;

public class WebDriverUtils {

    private static WebDriverWait wait;

    private static WebDriver getDriver() {
        return DriverFactory.getDriver();
    }

    // ===================== WAITS =====================

    public static void waitForElementToBeVisible(WebElement element, int timeout) {
        wait = new WebDriverWait(getDriver(), Duration.ofSeconds(timeout));
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public static void waitForElementToBeClickable(WebElement element, int timeout) {
        wait = new WebDriverWait(getDriver(), Duration.ofSeconds(timeout));
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public static void waitForAttributeToBe(WebElement element,
                                            String attribute,
                                            String value,
                                            int timeout) {
        wait = new WebDriverWait(getDriver(), Duration.ofSeconds(timeout));
        wait.until(ExpectedConditions.attributeToBe(element, attribute, value));
    }

    public static void waitForTitleToBe(String expectedTitle, int timeout) {
        wait = new WebDriverWait(getDriver(), Duration.ofSeconds(timeout));
        wait.until(ExpectedConditions.titleIs(expectedTitle));
    }

    public static void waitForTitleContains(String expectedTitle, int timeout) {
        wait = new WebDriverWait(getDriver(), Duration.ofSeconds(timeout));
        wait.until(ExpectedConditions.titleContains(expectedTitle));
    }

    public static void waitForUrlContains(String partialUrl, int timeout) {
        wait = new WebDriverWait(getDriver(), Duration.ofSeconds(timeout));
        wait.until(ExpectedConditions.urlContains(partialUrl));
    }

    // ===================== ACTIONS =====================

    public static void click(WebElement element) {
        waitForElementToBeClickable(element, 10);
        element.click();
    }

    public static void sendKeys(WebElement element, String value) {
        waitForElementToBeVisible(element, 10);
        element.clear();
        element.sendKeys(value);
    }

    // ===================== GETTERS =====================

    public static String getPageTitle() {
        return getDriver().getTitle();
    }

    public static String getCurrentUrl() {
        return getDriver().getCurrentUrl();
    }
    
    //Frames
    public static void waitForFrameAndSwitch(String frameName, int time) {

        WebDriverWait wait = new WebDriverWait(DriverFactory.getDriver(), Duration.ofSeconds(time));

        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameName));
    }
 // ===================== VALIDATIONS =====================

    public static boolean isElementDisplayed(WebElement element, int timeout) {
        try {
            waitForElementToBeVisible(element, timeout);
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean isElementClickable(WebElement element, int timeout) {
        try {
            waitForElementToBeClickable(element, timeout);
            return element.isEnabled();
        } catch (Exception e) {
            return false;
        }
    }
 // ===================== EXTRA UTILS =====================

    public static void waitForElementPresent(org.openqa.selenium.By locator, int timeout) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(timeout));
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    public static void clickWithJS(WebElement element) {
        try {
            click(element);
        } catch (Exception e) {
            ((org.openqa.selenium.JavascriptExecutor) getDriver())
                    .executeScript("arguments[0].click();", element);
        }
    }

    public static void scrollToElement(WebElement element) {
        ((org.openqa.selenium.JavascriptExecutor) getDriver())
                .executeScript("arguments[0].scrollIntoView({block:'center'});", element);
    }

    public static void waitForInvisibility(WebElement element, int timeout) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(timeout));
        wait.until(ExpectedConditions.invisibilityOf(element));
    }

    public static void selectByVisibleText(WebElement element, String value) {
        new org.openqa.selenium.support.ui.Select(element).selectByVisibleText(value);
    }

    public static String getText(WebElement element) {
        waitForElementToBeVisible(element, 10);
        return element.getText().trim();
    }

    public static void clearAndType(WebElement element, String value) {
        waitForElementToBeVisible(element, 10);
        element.clear();
        element.sendKeys(value);
    }
}