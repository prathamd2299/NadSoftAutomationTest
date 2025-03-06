package com.nadsoft.www.tests;

import static org.testng.Assert.*;

import java.text.NumberFormat;
import java.util.Locale;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.Test;

import com.nadsoft.www.pages.CartPage;
import com.nadsoft.www.pages.HomePage;

public class AddToCartTests extends BaseTest {
	@Test
	public void verifyAddToCart() throws InterruptedException {
		HomePage homePage = new HomePage(driver);
		JavascriptExecutor js = (JavascriptExecutor) driver;

		// Scrolling until bottom of page
		js.executeScript("window.scrollTo(0, document.body.scrollHeight);");

		// Taking mackbook unit price
		String expectedUnitPrice = homePage.getProductUnitPrice().split("\n")[0];
		System.out.println("Expected unit price: " + expectedUnitPrice.split("\n")[0]);

		int count = 2;

		// hitting add to cart button as per requirement
		for (int i = 0; i < count; i++) {
			homePage.addProductToCart();
		}

		// Validating success message after add to cart product
		String expectedMessageAfterProductAddedToCart = "Success: You have added MacBook to your shopping cart!";
		String actualMessageAfterProductAddedToCart = homePage.getMessageAfterProductAddedToCart();

		assertTrue(actualMessageAfterProductAddedToCart.contains(expectedMessageAfterProductAddedToCart),
				"incorrect message");

		// Went to cart page
		homePage.clickOnCartMenu();

		// Validating url
		String expectedUrl = "https://tutorialsninja.com/demo/index.php?route=checkout/cart";
		assertEquals(driver.getCurrentUrl(), expectedUrl);

		CartPage cartPage = new CartPage(driver);

		// Validating unit price of product available on cart page
		String actualUnitPriceOnCartPage = cartPage.getUnitPriceOfProduct();
		assertEquals(actualUnitPriceOnCartPage, expectedUnitPrice);

		// Calculating total price
		String actualUnitPriceAfterModify = expectedUnitPrice.replace("$", "");

		Double unitPrice = Double.parseDouble(actualUnitPriceAfterModify);
		Double totalPrice = unitPrice * count;

		// Formatting total price to currency
		NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(Locale.US);
		String formattedTotal = currencyFormat.format(totalPrice);

		// Validating unit price of product available on cart page
		String actualTotalPriceOnCartPage = cartPage.getTotalPriceOfProduct();
		assertEquals(actualTotalPriceOnCartPage, formattedTotal);
	}
}
