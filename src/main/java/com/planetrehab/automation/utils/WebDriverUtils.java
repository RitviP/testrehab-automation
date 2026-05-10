package com.planetrehab.automation.utils;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.planetrehab.automation.base.DriverFactory;

public class WebDriverUtils {

    private static WebDriver getDriver() {
        return DriverFactory.getDriver();
    }

    // ===================== WAITS =====================

    public static void waitForElementToBeVisible(WebElement element, int timeout) {
        new WebDriverWait(getDriver(), Duration.ofSeconds(timeout))
                .until(ExpectedConditions.visibilityOf(element));
    }

    public static void waitForElementToBeClickable(WebElement element, int timeout) {
        new WebDriverWait(getDriver(), Duration.ofSeconds(timeout))
                .until(ExpectedConditions.elementToBeClickable(element));
    }

    public static void waitForAttributeToBe(WebElement element,
                                            String attribute,
                                            String value,
                                            int timeout) {
        new WebDriverWait(getDriver(), Duration.ofSeconds(timeout))
                .until(ExpectedConditions.attributeToBe(element, attribute, value));
    }

    public static void waitForTitleToBe(String title, int timeout) {
        new WebDriverWait(getDriver(), Duration.ofSeconds(timeout))
                .until(ExpectedConditions.titleIs(title));
    }

    public static void waitForElementPresent(By locator, int timeout) {
        new WebDriverWait(getDriver(), Duration.ofSeconds(timeout))
                .until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    public static void waitForInvisibility(By locator, int timeout) {
        new WebDriverWait(getDriver(), Duration.ofSeconds(timeout))
                .until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }
    public static boolean isElementDisplayed(WebElement element, int timeout) {
        try {
            waitForElementToBeVisible(element, timeout);
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    // ===================== ACTIONS =====================

    public static void click(WebElement element) {
        waitForElementToBeClickable(element, 10);
        element.click();
    }

    public static void clickWithJS(WebElement element) {
        try {
            click(element);
        } catch (Exception e) {
            ((JavascriptExecutor) getDriver())
                    .executeScript("arguments[0].click();", element);
        }
    }

    public static void sendKeys(WebElement element, String value) {
        waitForElementToBeVisible(element, 10);
        element.clear();
        element.sendKeys(value);
    }

    public static void clearAndType(WebElement element, String value) {
        waitForElementToBeVisible(element, 10);
        element.clear();
        element.sendKeys(value);
    }

    public static void scrollToElement(WebElement element) {
        ((JavascriptExecutor) getDriver())
                .executeScript("arguments[0].scrollIntoView({block:'center'});", element);
    }

    // ===================== FRAME HANDLING =====================

    // 🔥 Dashboard frame
    public static void switchToFinFrame() {

        WebDriver driver = getDriver();
        driver.switchTo().defaultContent();

        WebElement frame = new WebDriverWait(driver, Duration.ofSeconds(20))
                .until(ExpectedConditions.presenceOfElementLocated(
                        By.cssSelector("iframe[name='fin']")
                ));

        driver.switchTo().frame(frame);

        System.out.println("✅ Switched to FIN frame");
    }

    // 🔥 Patient frame (handles reload properly)
    public static void switchToPatientFrameStable() {

        WebDriver driver = getDriver();
        driver.switchTo().defaultContent();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        try {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(
                    By.cssSelector("iframe[name='pat']")
            ));
        } catch (Exception ignored) {}

        WebElement frame = wait.until(
                ExpectedConditions.presenceOfElementLocated(
                        By.cssSelector("iframe[name='pat']")
                )
        );

        driver.switchTo().frame(frame);

        System.out.println("✅ Switched to PAT frame");
    }

    public static void switchToDefaultContent() {
        getDriver().switchTo().defaultContent();
    }
    public static void switchToFrameContainingElement(By locator) {

        WebDriver driver = DriverFactory.getDriver();
        driver.switchTo().defaultContent();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        List<WebElement> frames = driver.findElements(By.cssSelector("iframe"));

        for (WebElement frame : frames) {
            try {
                driver.switchTo().frame(frame);

                if (driver.findElements(locator).size() > 0) {
                    System.out.println("✅ Switched to correct frame");
                    return;
                }

                driver.switchTo().defaultContent();
            } catch (Exception e) {
                driver.switchTo().defaultContent();
            }
        }

        throw new RuntimeException("❌ Element not found in any iframe: " + locator);
    }
    public static WebElement waitForVisible(By locator) {
        WebDriverWait wait = new WebDriverWait(DriverFactory.getDriver(), Duration.ofSeconds(20));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
}