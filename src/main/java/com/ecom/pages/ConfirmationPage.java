package com.ecom.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ConfirmationPage extends BasePage {

    public ConfirmationPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = ".hero-primary")
    private WebElement confirmationMessage;

    private final org.openqa.selenium.By confirmationMessageBy = org.openqa.selenium.By.cssSelector(".hero-primary");

    public String getConfirmationMessage() {
        wait.waitForElementToAppear(confirmationMessageBy);
        return confirmationMessage.getText();
    }
}
