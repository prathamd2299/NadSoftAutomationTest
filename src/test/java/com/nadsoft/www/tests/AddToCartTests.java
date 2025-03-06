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

		js.executeScript("window.scrollTo(0, document.body.scrollHeight);");

		String expectedUnitPrice = homePage.getProductUnitPrice().split("\n")[0];
		System.out.println("Expected unit price: " + expectedUnitPrice.split("\n")[0]);

		int count = 2;

		for (int i = 0; i < count; i++) {
			homePage.addProductToCart();
		}

		String expectedMessageAfterProductAddedToCart = "Success: You have added MacBook to your shopping cart!";
		String actualMessageAfterProductAddedToCart = homePage.getMessageAfterProductAddedToCart();
		System.out.println(actualMessageAfterProductAddedToCart);

		assertTrue(actualMessageAfterProductAddedToCart.contains(expectedMessageAfterProductAddedToCart),
				"incorrect message");

		homePage.clickOnCartMenu();

		String expectedUrl = "https://tutorialsninja.com/demo/index.php?route=checkout/cart";

		assertEquals(driver.getCurrentUrl(), expectedUrl);

		CartPage cartPage = new CartPage(driver);

		String actualUnitPriceOnCartPage = cartPage.getUnitPriceOfProduct();
		System.out.println("Actual unit price: " + actualUnitPriceOnCartPage);

		assertEquals(actualUnitPriceOnCartPage, expectedUnitPrice);

		String actualTotalPriceOnCartPage = cartPage.getTotalPriceOfProduct();
		String actualUnitPriceAfterModify = expectedUnitPrice.replace("$", "");

		Double unitPrice = Double.parseDouble(actualUnitPriceAfterModify);
		Double totalPrice = unitPrice * count;

		System.out.println("Actual total price: " + actualTotalPriceOnCartPage);
		// Format result to 2 decimal places with a dollar sign
		NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(Locale.US);
		String formattedTotal = currencyFormat.format(totalPrice);

		assertEquals(actualTotalPriceOnCartPage, formattedTotal);
	}
}
