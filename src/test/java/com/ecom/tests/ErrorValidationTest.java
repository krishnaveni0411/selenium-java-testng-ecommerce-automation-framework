package com.ecom.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.ecom.base.BaseTest;
import com.ecom.listeners.RetryAnalyzer;
import com.ecom.pages.LoginPage;

public class ErrorValidationTest extends BaseTest {

    @Test(
        retryAnalyzer = RetryAnalyzer.class,
        groups = {"error-validation", "regression"}
    )
    public void verifyInvalidLoginError() {
        log.info("Starting verifyInvalidLoginError test execution.");
        LoginPage loginPage = launchApplication();

        log.info("Attempting login with invalid credentials...");
        loginPage.login("invalid@test.com", "WrongPassword");

        String errorMessage = loginPage.getLoginErrorMessage();
        log.info("Captured error message: " + errorMessage);

        Assert.assertEquals(errorMessage, "Incorrect email or password.");
        log.info("Error message validation passed.");
    }
}
