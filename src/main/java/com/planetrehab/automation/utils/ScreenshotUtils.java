package com.planetrehab.automation.utils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.planetrehab.automation.base.DriverFactory;
import com.planetrehab.automation.constants.FrameworkConstants;

public class ScreenshotUtils {

    public static String captureScreenshot(String testName) {

        WebDriver driver = DriverFactory.getDriver();

        if (driver == null) {
            throw new RuntimeException("❌ Driver is null. Cannot capture screenshot.");
        }

        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());

        String directoryPath = FrameworkConstants.SCREENSHOT_PATH;

        // Ensure directory exists
        new File(directoryPath).mkdirs();

        String filePath = directoryPath + testName + "_" + timestamp + ".png";

        try {
            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(src, new File(filePath));

            System.out.println("📸 Screenshot captured: " + filePath);

        } catch (IOException e) {
            throw new RuntimeException("❌ Failed to capture screenshot", e);
        }

        return filePath;
    }
}