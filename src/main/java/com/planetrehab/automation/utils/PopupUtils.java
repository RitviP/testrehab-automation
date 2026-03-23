package com.planetrehab.automation.utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class PopupUtils {

    /**
     * Handle OpenEMR dialog popup using JS
     * @param driver WebDriver instance
     * @param dialogId e.g. "srcConfirmSave"
     */
    public static void handleDialog(WebDriver driver, String dialogId) {

        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;

            // 🔥 Wait until JS dialog function is available
            new org.openqa.selenium.support.ui.WebDriverWait(driver, java.time.Duration.ofSeconds(10))
                    .until(d -> ((JavascriptExecutor) d)
                            .executeScript("return typeof dlgclose === 'function'"));

            // 🔥 Execute dialog close
            js.executeScript("dlgclose(arguments[0], false);", dialogId);

            System.out.println("✅ Dialog handled for: " + dialogId);

        } catch (Exception e) {
            throw new RuntimeException("❌ Failed to handle dialog: " + dialogId, e);
        }
    }
}