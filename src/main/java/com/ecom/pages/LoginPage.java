package com.ecom.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "userEmail")
    private WebElement emailInput;

    @FindBy(id = "userPassword")
    private WebElement passwordInput;

    @FindBy(id = "login")
    private WebElement loginButton;
    
    @FindBy(css = "[class*='flyInOut']")
    private WebElement errorMessage;

    public void goTo(String url) {
        driver.get(url);
    }

    public String getLoginErrorMessage() {
        return errorMessage.getText();
    }

    public ProductsPage login(String email, String password) {
        emailInput.sendKeys(email);
        passwordInput.sendKeys(password);
        loginButton.click();
        return new ProductsPage(driver);
    }
}
