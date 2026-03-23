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

import com.planetrehab.automation.constants.FrameworkConstants;
import com.planetrehab.automation.utils.PopupUtils;
import com.planetrehab.automation.utils.WebDriverUtils;

public class RegisterPatientPage {

    private WebDriver driver;

    public RegisterPatientPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);

        // Wait until page loads
        WebDriverUtils.waitForElementToBeVisible(
                firstNameField,
                FrameworkConstants.EXPLICIT_WAIT_TIME
        );
    }

    // 🔹 First Name
    @FindBy(id = "form_fname")
    private WebElement firstNameField;

    // 🔹 Last Name
    @FindBy(id = "form_lname")
    private WebElement lastNameField;

    // 🔹 DOB
    @FindBy(id = "form_DOB")
    private WebElement dateOfBirthField;

    // 🔹 Gender
    @FindBy(id = "form_sex")
    private WebElement genderDropdown;

    // 🔹 Create Patient Button
    @FindBy(id = "create")
    private WebElement createNewPatientBtn;

    // 🔹 Patient Portal Access NO
    @FindBy(id = "form_patient_portal_access[NO]")
    private WebElement patientPortalAccessNo;
    
    // ===================== ACTIONS =====================

    public void enterFirstName(String firstName) {
        WebDriverUtils.sendKeys(firstNameField, firstName);
    }

    public void enterLastName(String lastName) {
        WebDriverUtils.sendKeys(lastNameField, lastName);
    }

    public void enterDOB(String dob) {
        WebDriverUtils.sendKeys(dateOfBirthField, dob);
    }

    public void selectGender(String gender) {
        Select select = new Select(genderDropdown);
        select.selectByVisibleText(gender);
    }

    public void selectPatientPortalAccess(String value) {

        JavascriptExecutor js = (JavascriptExecutor) driver;

        // 🔥 Dynamic locator (YES / NO)
        By locator = By.id("form_patient_portal_access[" + value.toUpperCase() + "]");

        WebElement radio = driver.findElement(locator);

        // 🔥 Scroll
        js.executeScript("arguments[0].scrollIntoView({block:'center'});", radio);

        // 🔥 JS click (important)
        js.executeScript("arguments[0].click();", radio);

        System.out.println("✅ Patient Portal Access = " + value + " selected");
    }
    public void clickCreatePatient() {
        WebDriverUtils.click(createNewPatientBtn);
    }

    public void confirmCreatePatient() {
        PopupUtils.handleDialog(driver, "srcConfirmSave");
    }

    // 🔥 Full Flow (optional helper)
    public void registerPatient(String firstName, String lastName, String dob, String gender, String PatientPortalAccess) {
        enterFirstName(firstName);
        enterLastName(lastName);
        enterDOB(dob);
        selectGender(gender);
        selectPatientPortalAccess(PatientPortalAccess);
        clickCreatePatient();
        confirmCreatePatient();
    }
 // ===================== VALIDATION =====================

    public boolean verifyPatientCreated(String firstName, String lastName) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        String fullName = firstName + " " + lastName;

        // 🔥 Wait for dashboard OR name (whichever appears first)
        wait.until(ExpectedConditions.or(
                ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(),'Medical Record Dashboard')]")),
                ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(),'" + fullName + "')]"))
        ));

        // 🔥 Now validate
        boolean namePresent = driver.findElements(
                By.xpath("//*[contains(text(),'" + fullName + "')]")
        ).size() > 0;

        boolean dashboardLoaded = driver.findElements(
                By.xpath("//*[contains(text(),'Medical Record Dashboard')]")
        ).size() > 0;

        boolean patientIdPresent = driver.findElements(
                By.xpath("//*[contains(text(),'Patient ID')]")
        ).size() > 0;

        System.out.println("🔍 Name present: " + namePresent);
        System.out.println("🔍 Dashboard loaded: " + dashboardLoaded);
        System.out.println("🔍 Patient ID present: " + patientIdPresent);

        return namePresent && dashboardLoaded && patientIdPresent;
    }
}