package com.planetrehab.automation.listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.planetrehab.automation.utils.ExtentManager;
import com.planetrehab.automation.utils.ScreenshotUtils;

public class TestListener implements ITestListener {

    private static ExtentReports extent = ExtentManager.getInstance();
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    @Override
    public void onTestStart(ITestResult result) {

        String testName = result.getMethod().getMethodName();

        // 🔥 Add parameters (very useful for DataProvider)
        if (result.getParameters().length > 0) {
            testName += " - " + java.util.Arrays.toString(result.getParameters());
        }

        ExtentTest extentTest = extent.createTest(testName);
        test.set(extentTest);

        System.out.println("🚀 STARTED: " + testName);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        test.get().pass("✅ Test Passed");
        System.out.println("✅ PASSED: " + result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {

        test.get().fail(result.getThrowable());

        try {
            String path = ScreenshotUtils.captureScreenshot(result.getName());
            test.get().addScreenCaptureFromPath(path);
        } catch (Exception e) {
            System.out.println("⚠️ Screenshot capture failed");
        }

        System.out.println("❌ FAILED: " + result.getName());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        test.get().skip("⚠️ Test Skipped");
        System.out.println("⚠️ SKIPPED: " + result.getName());
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
        System.out.println("📊 Report Generated");
    }
}