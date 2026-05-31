package com.ecom.pages;

import java.util.Random;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class RegisterPage extends BasePage {

    public RegisterPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = ".text-reset")
    private WebElement registerHereButton;

    @FindBy(id = "firstName")
    private WebElement firstNameInput;

    @FindBy(id = "lastName")
    private WebElement lastNameInput;

    @FindBy(id = "userEmail")
    private WebElement emailInput;

    @FindBy(id = "userMobile")
    private WebElement mobileInput;

    @FindBy(css = ".custom-select")
    private WebElement occupationDropdown;

    @FindBy(xpath = "//input[@value='Female']")
    private WebElement femaleGenderOption;

    @FindBy(id = "userPassword")
    private WebElement passwordInput;

    @FindBy(id = "confirmPassword")
    private WebElement confirmPasswordInput;

    @FindBy(css = "input[type='checkbox']")
    private WebElement ageConfirmationCheckbox;

    @FindBy(id = "login")
    private WebElement registerButton;

    @FindBy(css = ".headcolor")
    private WebElement confirmationMessage;

    @FindBy(css = ".btn-primary")
    private WebElement backToLoginButton;

    public void goTo(String url) {
        driver.get(url);
    }

    private String generateRandomEmail() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder emailPrefix = new StringBuilder();
        Random random = new Random();
        int length = 8;
        for (int i = 0; i < length; i++) {
            emailPrefix.append(characters.charAt(random.nextInt(characters.length())));
        }
        return emailPrefix + "@krish.com";
    }

    public String registerUser(String firstName, String lastName, String mobileNumber, String password) {
        registerHereButton.click();
        firstNameInput.sendKeys(firstName);
        lastNameInput.sendKeys(lastName);
        String generatedEmail = generateRandomEmail();
        emailInput.sendKeys(generatedEmail);
        mobileInput.sendKeys(mobileNumber);
        selectOccupation();
        femaleGenderOption.click();
        passwordInput.sendKeys(password);
        confirmPasswordInput.sendKeys(password);
        ageConfirmationCheckbox.click();
        registerButton.click();
        return generatedEmail;
    }

    public String getConfirmationMessage() {
        return confirmationMessage.getText();
    }

    public LoginPage goToLoginPage() {
        backToLoginButton.click();
        return new LoginPage(driver);
    }

    private void selectOccupation() {
        Select occupationSelect = new Select(occupationDropdown);
        occupationSelect.selectByVisibleText("Engineer");
    }
}
