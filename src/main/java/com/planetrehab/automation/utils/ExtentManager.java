package com.planetrehab.automation.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.planetrehab.automation.constants.FrameworkConstants;

public class ExtentManager {

    private static ExtentReports extent;

    public static ExtentReports getInstance() {

        if (extent == null) {

        	String timestamp = new java.text.SimpleDateFormat("yyyyMMdd_HHmmss")
        	        .format(new java.util.Date());

        	String path = FrameworkConstants.REPORT_PATH + "ExtentReport_" + timestamp + ".html";

            ExtentSparkReporter spark = new ExtentSparkReporter(path);
            spark.config().setReportName("PlanetRehab Automation Report");
            spark.config().setDocumentTitle("Automation Execution Report");

            extent = new ExtentReports();
            extent.attachReporter(spark);
        }

        return extent;
    }
}