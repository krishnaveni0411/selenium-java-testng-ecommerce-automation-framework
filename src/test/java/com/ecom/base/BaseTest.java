package com.ecom.base;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import com.ecom.config.ConfigReader;
import com.ecom.driver.DriverManager;
import com.ecom.pages.LoginPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BaseTest {

    protected static final Logger log = LogManager.getLogger(BaseTest.class);

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        String browser = System.getProperty("browser");
        if (browser == null || browser.isEmpty()) {
            browser = ConfigReader.getProperty("browser");
        }
        log.info("Initializing WebDriver instance for browser: " + browser);
        WebDriver driver = DriverManager.createInstance(browser);
        DriverManager.setDriver(driver);
    }

    public LoginPage launchApplication() {
        WebDriver driver = DriverManager.getDriver();
        String url = System.getProperty("url");
        if (url == null || url.isEmpty()) {
            url = ConfigReader.getProperty("url");
        }
        log.info("Launching application URL: " + url);
        LoginPage loginPage = new LoginPage(driver);
        loginPage.goTo(url);
        return loginPage;
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        WebDriver driver = DriverManager.getDriver();
        if (driver != null) {
            log.info("Quitting WebDriver and cleaning up thread-local reference.");
            driver.quit();
            DriverManager.unload();
        }
    }
}
