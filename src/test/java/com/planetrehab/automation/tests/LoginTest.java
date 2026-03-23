package com.planetrehab.automation.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.planetrehab.automation.base.BaseTest;
import com.planetrehab.automation.constants.FrameworkConstants;
import com.planetrehab.automation.pages.DashboardPage;
import com.planetrehab.automation.pages.LoginPage;
import com.planetrehab.automation.pages.OtpPage;
import com.planetrehab.automation.utils.ConfigReader;
import com.planetrehab.automation.utils.WebDriverUtils;

public class LoginTest extends BaseTest {

		@Test(dataProvider = "loginData", dataProviderClass = com.planetrehab.automation.dataproviders.LoginDataProvider.class)
		public void verifyLogin(String username, String password) {

		    LoginPage loginPage = new LoginPage(driver);
		    loginPage.login(username, password);

		    // OTP Handling
		    OtpPage otpPage = new OtpPage(driver);
		    DashboardPage dashboardPage = otpPage.verifyOtp();

		    // Wait for Dashboard title
		    WebDriverUtils.waitForTitleToBe(
		            FrameworkConstants.HOME_PAGE_TITLE,
		            FrameworkConstants.EXPLICIT_WAIT_TIME
		    );

		    Assert.assertEquals(driver.getTitle(), FrameworkConstants.HOME_PAGE_TITLE);
		    
		    //LOGO Verify
		    boolean logo = dashboardPage.isHeaderLogoDisplayed();
		    System.out.println("Header logo is displayed: " + logo);
		    Assert.assertTrue(logo, "Header logo is not displayed");
		    
		    //Visit List
		    boolean visitList = dashboardPage.isVisitListDisplayed();
		    System.out.println("Visit List is displayed: " + visitList);
		    Assert.assertTrue(visitList, "Visit List is not displayed");
		   
		    //Address Book
		    boolean addressBook = dashboardPage.isAddressBookDisplayed();
		    System.out.println("addressBook is displayed: " + addressBook);
		    Assert.assertTrue(addressBook, "addressBook is not displayed");
		    
		    //Office Notes
		    boolean officeNotes = dashboardPage.isOfficeNotesDisplayed();
		    System.out.println("officeNotes is displayed: " + officeNotes);
		    Assert.assertTrue(officeNotes, "officeNotes is not displayed");
		    
		  //calendarIcon
		    boolean calendarIcon = dashboardPage.isCalendarIconDisplayed();
		    System.out.println("calendarIcon is displayed: " + calendarIcon);
		    Assert.assertTrue(calendarIcon, "calendarIcon is not displayed");
		    
		    
		  //patientQueue
		    boolean patientQueue = dashboardPage.isPatientQueueDisplayed();
		    System.out.println("patientQueue is displayed: " + patientQueue);
		    Assert.assertTrue(patientQueue, "patientQueue is not displayed");
		    
		  //Message Icon
		    boolean messageIcon = dashboardPage.isMessageIconDisplayed();
		    System.out.println("messageIcon is displayed: " + messageIcon);
		    Assert.assertTrue(messageIcon, "messageIcon is not displayed");
		    
		  //Notifications Icon
		    boolean notificationsIcon = dashboardPage.isNotificationsIconDisplayed();
		    System.out.println("notificationsIcon is displayed: " + notificationsIcon);
		    Assert.assertTrue(notificationsIcon, "notificationsIcon is not displayed");
		    
		  //User Icon
		    boolean usericon = dashboardPage.isUserIconDisplayed();
		    System.out.println("usericon is displayed: " + usericon);
		    Assert.assertTrue(usericon, "usericon is not displayed");
		    
		    
		   
		}
}