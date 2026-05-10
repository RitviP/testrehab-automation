package com.planetrehab.automation.tests;

import org.testng.annotations.Test;

import com.planetrehab.automation.base.BaseTest;
import com.planetrehab.automation.pages.CareProfileCreationPage;
import com.planetrehab.automation.pages.DashboardPage;
import com.planetrehab.automation.pages.RegisterPatientPage;
import com.planetrehab.automation.utils.WebDriverUtils;

public class CareProfileCreationTest extends BaseTest {
	
	@Test
	public void createCareProfileTest() {

	    // Step 1: Login
	    doLogin();

	    // Step 2: Create Patient
	    DashboardPage dashboard = new DashboardPage(driver);
	    RegisterPatientPage registerPage = dashboard.clickAddNewPatient();

	    registerPage.registerPatient("John", "Test3", "01/01/1992", "Male", "NO");
	  
	    // Step 3: Now inside patient → switch POM
	    CareProfileCreationPage carePage = new CareProfileCreationPage(driver);

	    // Step 4: Click Care Profile
	    carePage.clickCareProfile();
	    carePage.clickAddNewCareProfile();
	    
	    // Step 5: Create new Care Profile
	    
	    carePage.createCareProfile("AutomationTest", "Admin, Ski Town");
	    
	}
}
	
