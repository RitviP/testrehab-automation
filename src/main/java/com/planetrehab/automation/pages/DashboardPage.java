package com.planetrehab.automation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.planetrehab.automation.utils.WebDriverUtils;
import com.planetrehab.automation.constants.FrameworkConstants;

public class DashboardPage {

    private WebDriver driver;

    public DashboardPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // 🔹 Header Logo
    @FindBy(xpath = "//img[contains(@src,'logo')]")
    private WebElement headerLogo;

    // 🔹 Visit List
    @FindBy(xpath = "//i[contains(@class,'fa-list-alt')]")
    private WebElement visitList;

    // 🔹 Address Book
    @FindBy(xpath = "//i[contains(@class,'fa-address-book')]")
    private WebElement addressBook;

    // 🔹 Office Notes
    @FindBy(id = "officeNotesIcon")
    private WebElement officeNotes;

    // 🔹 Calendar
    @FindBy(id = "calendarIcon")
    private WebElement calendarIcon;

    // 🔹 Patient Queue
    @FindBy(xpath = "//button[contains(@class,'btn-primary')]")
    private WebElement patientQueue;

    // 🔹 Message Icon
    @FindBy(id = "messageIconContainer")
    private WebElement messageIcon;

    // 🔹 Notifications Icon
    @FindBy(xpath = "//button[contains(@class,'sse-bell-btn')]")
    private WebElement notificationsIcon;

    // 🔹 User Icon
    @FindBy(id = "user_icon")
    private WebElement userIcon;

    // 🔹 Add New Patient
    @FindBy(xpath = "//a[contains(@class,'btn-add')]")
    private WebElement addNewPatientBtn;

    // ===================== VALIDATIONS =====================

    public boolean isHeaderLogoDisplayed() {
        return WebDriverUtils.isElementDisplayed(
                headerLogo,
                FrameworkConstants.EXPLICIT_WAIT_TIME
        );
    }

    public boolean isVisitListDisplayed() {
        return WebDriverUtils.isElementDisplayed(
                visitList,
                FrameworkConstants.EXPLICIT_WAIT_TIME
        );
    }

    public boolean isAddressBookDisplayed() {
        return WebDriverUtils.isElementDisplayed(
                addressBook,
                FrameworkConstants.EXPLICIT_WAIT_TIME
        );
    }

    public boolean isOfficeNotesDisplayed() {
        return WebDriverUtils.isElementDisplayed(
                officeNotes,
                FrameworkConstants.EXPLICIT_WAIT_TIME
        );
    }

    public boolean isCalendarIconDisplayed() {
        return WebDriverUtils.isElementDisplayed(
                calendarIcon,
                FrameworkConstants.EXPLICIT_WAIT_TIME
        );
    }

    public boolean isPatientQueueDisplayed() {
        return WebDriverUtils.isElementDisplayed(
                patientQueue,
                FrameworkConstants.EXPLICIT_WAIT_TIME
        );
    }

    public boolean isMessageIconDisplayed() {
        return WebDriverUtils.isElementDisplayed(
                messageIcon,
                FrameworkConstants.EXPLICIT_WAIT_TIME
        );
    }

    public boolean isNotificationsIconDisplayed() {
        return WebDriverUtils.isElementDisplayed(
                notificationsIcon,
                FrameworkConstants.EXPLICIT_WAIT_TIME
        );
    }

    public boolean isUserIconDisplayed() {
        return WebDriverUtils.isElementDisplayed(
                userIcon,
                FrameworkConstants.EXPLICIT_WAIT_TIME
        );
    }

    // ===================== ACTION =====================

    public RegisterPatientPage clickAddNewPatient() {

        driver.switchTo().defaultContent();

        // 🔥 Correct frame for dashboard
        WebDriverUtils.switchToFinFrame();

        WebDriverUtils.click(addNewPatientBtn);

        return new RegisterPatientPage(driver);
    }
}