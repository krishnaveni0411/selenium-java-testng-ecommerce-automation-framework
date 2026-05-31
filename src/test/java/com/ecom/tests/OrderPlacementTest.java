package com.ecom.tests;

import java.util.HashMap;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.ecom.base.BaseTest;
import com.ecom.dataprovider.TestDataProvider;
import com.ecom.listeners.RetryAnalyzer;
import com.ecom.pages.CartPage;
import com.ecom.pages.CheckoutPage;
import com.ecom.pages.ConfirmationPage;
import com.ecom.pages.LoginPage;
import com.ecom.pages.ProductsPage;

public class OrderPlacementTest extends BaseTest {

    @Test(
        dataProvider = "getJsonData",
        dataProviderClass = TestDataProvider.class,
        retryAnalyzer = RetryAnalyzer.class,
        groups = {"smoke", "regression"}
    )
    public void submitOrder(HashMap<String, String> input) throws Exception {
        log.info("Starting submitOrder test execution with user: " + input.get("email"));
        
        LoginPage loginPage = launchApplication();
        
        log.info("Logging in with credentials...");
        ProductsPage productsPage = loginPage.login(input.get("email"), input.get("password"));

        log.info("Adding product to cart: " + input.get("productName"));
        productsPage.addProductToCart(input.get("productName"));

        log.info("Navigating to Cart Page...");
        CartPage cartPage = productsPage.goToCartPage();

        log.info("Verifying product is in the cart...");
        Assert.assertTrue(cartPage.verifyProductInCart(input.get("productName")), 
                "Product was not found in the cart!");

        log.info("Navigating to Checkout Page...");
        CheckoutPage checkoutPage = cartPage.goToCheckout();

        String country = input.getOrDefault("country", "India");
        log.info("Selecting shipping country: " + country);
        checkoutPage.selectCountry(country);

        log.info("Placing order...");
        ConfirmationPage confirmationPage = checkoutPage.placeOrder();

        String confirmationMsg = confirmationPage.getConfirmationMessage();
        log.info("Verifying order confirmation message: " + confirmationMsg);
        Assert.assertEquals(confirmationMsg, "THANKYOU FOR THE ORDER.");
        log.info("Order placed successfully!");
    }
}
