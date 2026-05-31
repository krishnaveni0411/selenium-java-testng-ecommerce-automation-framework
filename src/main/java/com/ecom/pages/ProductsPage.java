package com.ecom.pages;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProductsPage extends BasePage {

    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = ".col-lg-4")
    private List<WebElement> products;

    @FindBy(css = "[routerlink*='cart']")
    private WebElement cartButton;

    private final By productsBy = By.cssSelector(".col-lg-4");
    private final By addToCartButton = By.cssSelector(".card-body button:last-of-type");
    private final By toastMessage = By.cssSelector(".toast-success");

    public List<WebElement> getProductList() {
        wait.waitForElementToAppear(productsBy);
        return products;
    }

    public WebElement getProductByName(String productName) {
        return getProductList().stream()
                .filter(product ->
                        product.findElement(By.cssSelector("b"))
                                .getText()
                                .equalsIgnoreCase(productName))
                .findFirst()
                .orElse(null);
    }

    private final By spinner = By.cssSelector(".ngx-spinner-overlay");

    public void addProductToCart(String productName) {
        WebElement product = getProductByName(productName);
        if (product != null) {
            product.findElement(addToCartButton).click();
            wait.waitForElementToAppear(toastMessage);
            wait.waitForElementToDisappear(spinner);
        } else {
            throw new RuntimeException("Product '" + productName + "' not found on products page.");
        }
    }

    public CartPage goToCartPage() {
        cartButton.click();
        return new CartPage(driver);
    }
}
