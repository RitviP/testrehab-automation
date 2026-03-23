package com.planetrehab.automation.constants;

public final class FrameworkConstants {

	private FrameworkConstants() {
		// Prevent instantiation
	}

	// 🔹 Application
	public static final String HOME_PAGE_TITLE = "Body Elite PT";

	// 🔹 Waits (in seconds)
	public static final int EXPLICIT_WAIT_TIME = 20;
	public static final int IMPLICIT_WAIT_TIME = 5;
	public static final int PAGE_LOAD_TIMEOUT = 30;

	// 🔹 File Paths
	public static final String CONFIG_FILE_PATH =
	        System.getProperty("user.dir") + "/src/main/resources/config/config.properties";

	public static final String TESTDATA_FILE_PATH = System.getProperty("user.dir")
			+ "/src/main/resources/testdata/planetrehab-testdata.xlsx";

	// 🔹 Browser Defaults
	public static final String DEFAULT_BROWSER = "chrome";

	// 🔹 Reports / Screenshots
	public static final String SCREENSHOT_PATH = System.getProperty("user.dir") + "/test-output/screenshots/";

	public static final String REPORT_PATH = System.getProperty("user.dir") + "/test-output/reports/";

}