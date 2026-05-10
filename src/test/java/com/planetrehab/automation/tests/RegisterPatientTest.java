package com.planetrehab.automation.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.planetrehab.automation.base.BaseTest;
import com.planetrehab.automation.pages.DashboardPage;
import com.planetrehab.automation.pages.RegisterPatientPage;

public class RegisterPatientTest extends BaseTest {

	@Test(dataProvider = "registerPatientData", dataProviderClass = com.planetrehab.automation.dataproviders.RegisterPatientDataProvider.class)
	public void verifyPatientRegistration(String firstName, String lastName, String dob, String gender,
			String portalAccess) {

		// 🔥 Login
		doLogin();

		// 🔥 Navigate
		DashboardPage dashboardPage = new DashboardPage(driver);
		RegisterPatientPage registerPage = dashboardPage.clickAddNewPatient();

		// 🔥 Fill form
		registerPage.enterFirstName(firstName);
		registerPage.enterLastName(lastName);
		registerPage.enterDOB(dob);
		registerPage.selectGender(gender);
		registerPage.selectPatientPortalAccess(portalAccess);

		// 🔥 Submit
		registerPage.clickCreatePatient();
		registerPage.confirmCreatePatient();

		// 🔥 VALIDATION
		boolean isCreated = registerPage.verifyPatientCreated(firstName, lastName);

		Assert.assertTrue(isCreated, "❌ Patient registration failed");
	}
}