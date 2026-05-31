package com.ecom.listeners;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.ecom.utils.ScreenshotUtils;
import com.ecom.driver.DriverManager;
import com.ecom.reports.ExtentReportManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TestListener implements ITestListener {

    private static final Logger log = LogManager.getLogger(TestListener.class);
    private final ExtentReports extent = ExtentReportManager.getReportInstance();
    private static final ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();

    @Override
    public void onTestStart(ITestResult result) {
        log.info("Starting execution of test: " + result.getMethod().getMethodName());
        ExtentTest test = extent.createTest(result.getMethod().getMethodName());
        extentTest.set(test);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        log.info("Test passed: " + result.getMethod().getMethodName());
        extentTest.get().log(Status.PASS, "Test Passed Successfully");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        log.error("Test failed: " + result.getMethod().getMethodName(), result.getThrowable());
        extentTest.get().log(Status.FAIL, "Test Failed");
        extentTest.get().fail(result.getThrowable());

        WebDriver driver = DriverManager.getDriver();
        if (driver != null) {
            try {
                String screenshotPath = ScreenshotUtils.captureScreenshot(
                        result.getMethod().getMethodName(),
                        driver
                );
                extentTest.get().addScreenCaptureFromPath(screenshotPath, result.getMethod().getMethodName());
                log.info("Failure screenshot captured at: " + screenshotPath);
            } catch (Exception e) {
                log.error("Failed to capture failure screenshot", e);
            }
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        log.warn("Test skipped: " + result.getMethod().getMethodName());
        extentTest.get().log(Status.SKIP, "Test Skipped");
        if (result.getThrowable() != null) {
            extentTest.get().skip(result.getThrowable());
        }
    }

    @Override
    public void onFinish(ITestContext context) {
        log.info("Test execution context finished. Flushing extent reports.");
        extent.flush();
    }
}
