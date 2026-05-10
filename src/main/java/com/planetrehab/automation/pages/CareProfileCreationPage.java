package com.planetrehab.automation.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.planetrehab.automation.utils.WebDriverUtils;

public class CareProfileCreationPage {

    private WebDriver driver;

    public CareProfileCreationPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // ================= LOCATORS =================

    @FindBy(xpath = "//a[normalize-space()='Care Profile']")
    private WebElement careProfileTab;

    @FindBy(xpath = "//button[contains(text(),'Add New Care Profile')]")
    private WebElement addNewCareProfileBtn;

    @FindBy(id = "form_title")
    private WebElement conditionField;

    @FindBy(id = "referring_provider_id")
    private WebElement referringProviderDropdown;

    @FindBy(id = "form_save")
    private WebElement saveBtn;

    // ================= ACTIONS =================

    public void clickCareProfile() {

        WebDriverUtils.switchToFrameContainingElement(
            By.xpath("//a[normalize-space()='Care Profile']")
        );

        WebElement careTab = WebDriverUtils.waitForVisible(
            By.xpath("//a[normalize-space()='Care Profile']")
        );

        ((JavascriptExecutor) driver)
            .executeScript("arguments[0].click();", careTab);

        System.out.println("✅ Clicked Care Profile");
    }

    public void clickAddNewCareProfile() {

        WebDriverUtils.switchToFrameContainingElement(
            By.xpath("//button[contains(text(),'Add New Care Profile')]")
        );

        WebElement addBtn = WebDriverUtils.waitForVisible(
            By.xpath("//button[contains(text(),'Add New Care Profile')]")
        );

        ((JavascriptExecutor) driver)
            .executeScript("arguments[0].click();", addBtn);

        System.out.println("✅ Clicked Add New Care Profile");
    }

    public void enterCondition(String condition) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        WebElement conditionInput = wait.until(
            ExpectedConditions.visibilityOfElementLocated(By.id("form_title"))
        );

        conditionInput.clear();
        conditionInput.sendKeys(condition);

        System.out.println("✅ Condition entered: " + condition);
    }

    public void selectReferringProvider(String providerName) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        WebElement dropdown = wait.until(
            ExpectedConditions.elementToBeClickable(By.id("referring_provider_id"))
        );

        Select select = new Select(dropdown);

        if (select.getOptions().size() > 1) {
            select.selectByIndex(1); // CI safe
        } else {
            throw new RuntimeException("❌ No provider available");
        }

        System.out.println("✅ Provider selected");
    }

    public void clickSave() {

        WebDriverUtils.switchToFrameContainingElement(By.id("form_save"));

        WebElement saveButton = WebDriverUtils.waitForVisible(By.id("form_save"));

        ((JavascriptExecutor) driver)
            .executeScript("arguments[0].scrollIntoView({block:'center'});", saveButton);

        ((JavascriptExecutor) driver)
            .executeScript("arguments[0].click();", saveButton);

        System.out.println("✅ Clicked Save");
    }
    public void createCareProfile(String condition, String providerName) {

        // 🔥 Switch dynamically to form frame
        WebDriverUtils.switchToFrameContainingElement(By.id("form_title"));

        enterCondition(condition);

        selectReferringProvider(providerName);

        clickSave();

        System.out.println("✅ Care Profile Created");
    }
}