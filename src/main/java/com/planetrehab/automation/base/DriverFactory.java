package com.planetrehab.automation.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {

	private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

	public static void initDriver(String browser) {

		if (browser == null) {
			browser = "chrome";
		}

		switch (browser.toLowerCase()) {

		case "chrome":

			WebDriverManager.chromedriver().setup();

			ChromeOptions chromeOptions = new ChromeOptions();
			chromeOptions.addArguments("--start-maximized");

			// 🔥 Optional (CI / headless support)
			if (Boolean.parseBoolean(System.getProperty("headless", "false"))) {
				chromeOptions.addArguments("--headless=new");
				chromeOptions.addArguments("--window-size=1920,1080");
			}

			driver.set(new ChromeDriver(chromeOptions));
			break;

		case "edge":

			WebDriverManager.edgedriver().setup();

			EdgeOptions edgeOptions = new EdgeOptions();
			edgeOptions.addArguments("--start-maximized");

			driver.set(new EdgeDriver(edgeOptions));
			break;

		default:
			throw new RuntimeException("❌ Browser not supported: " + browser);
		}
	}

	public static WebDriver getDriver() {
		return driver.get();
	}

	public static void quitDriver() {
		if (driver.get() != null) {
			driver.get().quit();
			driver.remove();
		}
	}
}