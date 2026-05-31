package com.ecom.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class CheckoutPage extends BasePage {

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//input[@placeholder='Select Country']")
    private WebElement countryInput;

    @FindBy(css = ".ta-item:nth-of-type(2)")
    private WebElement countryOption;

    @FindBy(css = ".action__submit")
    private WebElement placeOrderButton;

    private final By countryResults = By.cssSelector(".ta-results");

    public void selectCountry(String countryName) {
        Actions actions = new Actions(driver);
        actions.sendKeys(countryInput, countryName).build().perform();
        wait.waitForElementToAppear(countryResults);
        countryOption.click();
    }

    public ConfirmationPage placeOrder() {
        org.openqa.selenium.JavascriptExecutor js = (org.openqa.selenium.JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", placeOrderButton);
        return new ConfirmationPage(driver);
    }
}
