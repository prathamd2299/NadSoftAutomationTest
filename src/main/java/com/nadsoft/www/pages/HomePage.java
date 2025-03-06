package com.nadsoft.www.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {
	private WebDriver driver;
	private WebDriverWait wait;
	private JavascriptExecutor js;

	private By firstAddToCartButton = By.xpath("(//div[@class='button-group'])[1]//span");

	private By unitPrice = By.xpath("(//p[@class='price'])[1]");

	private By messageAfterProductAddedToCart = By.cssSelector(".alert.alert-success.alert-dismissible");

	private By cartMenu = By.cssSelector("a[title='Shopping Cart']");

	public HomePage(WebDriver driver1) {
		this.driver = driver1;
		js = (JavascriptExecutor) driver;
		wait = new WebDriverWait(driver1, Duration.ofSeconds(10));
	}

	public String getProductUnitPrice() {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(unitPrice)).getText();
	}

	public void addProductToCart() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(firstAddToCartButton)).click();
	}

	public String getMessageAfterProductAddedToCart() {
		js.executeScript("arguments[0].scrollIntoView(true);",
				wait.until(ExpectedConditions.visibilityOfElementLocated(messageAfterProductAddedToCart)));
		return wait.until(ExpectedConditions.visibilityOfElementLocated(messageAfterProductAddedToCart)).getText();
	}

	public void clickOnCartMenu() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(cartMenu)).click();
	}
}
