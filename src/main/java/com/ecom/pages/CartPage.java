package com.ecom.pages;

import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CartPage extends BasePage {

    public CartPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = ".cartSection h3")
    private List<WebElement> cartProducts;

    @FindBy(xpath = "//button[text()='Checkout']")
    private WebElement checkoutButton;

    public List<WebElement> getCartProducts() {
        return cartProducts;
    }

    public boolean verifyProductInCart(String productName) {
        wait.waitForVisibilityOfAllElements(cartProducts);
        return cartProducts.stream()
                .anyMatch(cartProduct ->
                        cartProduct.getText()
                                .equalsIgnoreCase(productName));
    }

    public CheckoutPage goToCheckout() {
        checkoutButton.click();
        return new CheckoutPage(driver);
    }
}
