package com.planetrehab.automation.base;

import java.lang.reflect.Method;
import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

import com.planetrehab.automation.listeners.TestListener;
import com.planetrehab.automation.pages.LoginPage;
import com.planetrehab.automation.pages.OtpPage;
import com.planetrehab.automation.utils.ConfigReader;

@Listeners(TestListener.class)
public class BaseTest {

	protected WebDriver driver;

	@BeforeMethod
	public void setUp(Method method) {

		System.out.println("\n===== STARTING TEST: " + method.getName() + " =====");

		String browser = System.getProperty("browser");

		if (browser == null) {
			browser = ConfigReader.getProperty("browser");
		}

		if (browser == null) {
			browser = "chrome";
		}

		DriverFactory.initDriver(browser);
		driver = DriverFactory.getDriver();

		String url = ConfigReader.getProperty("url");
		driver.get(url);

		new WebDriverWait(driver, Duration.ofSeconds(10))
				.until(d -> ((JavascriptExecutor) d).executeScript("return document.readyState").equals("complete"));

		System.out.println("🚀 Browser: " + browser);
		System.out.println("🌐 URL: " + url);
	}

	// 🔥 DEFAULT LOGIN
	protected void doLogin() {
		doLogin(ConfigReader.getProperty("username"), ConfigReader.getProperty("password"));
	}

	// 🔥 CUSTOM LOGIN
	protected void doLogin(String username, String password) {

		LoginPage loginPage = new LoginPage(driver);
		loginPage.login(username, password);

		OtpPage otpPage = new OtpPage(driver);
		otpPage.verifyOtp();

		System.out.println("✅ Login successful for user: " + username);
	}

	// 🔥 ROLE BASED LOGIN (NEW)
	protected void doLoginByRole(String role) {

		String username = ConfigReader.getProperty(role + ".username");
		String password = ConfigReader.getProperty(role + ".password");

		doLogin(username, password);
	}

	@AfterMethod
	public void tearDown(Method method) {

		System.out.println("===== ENDING TEST: " + method.getName() + " =====\n");

		if (driver != null) {
			DriverFactory.quitDriver();
		}
	}
}