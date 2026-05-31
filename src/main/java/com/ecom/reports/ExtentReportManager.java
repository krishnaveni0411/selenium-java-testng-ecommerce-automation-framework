package com.ecom.reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public final class ExtentReportManager {
    private static ExtentReports extent;

    private ExtentReportManager() {}

    public static synchronized ExtentReports getReportInstance() {
        if (extent == null) {
            String reporterPath = System.getProperty("user.dir") + "/reports/index.html";
            ExtentSparkReporter reporter = new ExtentSparkReporter(reporterPath);
            reporter.config().setReportName("E-Commerce Web Automation Test Report");
            reporter.config().setDocumentTitle("Test Results");

            extent = new ExtentReports();
            extent.attachReporter(reporter);
            extent.setSystemInfo("Tester", "Krishnaveni");
            extent.setSystemInfo("Environment", "QA");
        }
        return extent;
    }
}
