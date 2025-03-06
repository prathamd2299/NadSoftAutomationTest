package com.nadsoft.www.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CartPage {
	private WebDriver driver;
	private WebDriverWait wait;

	private By cartPageTitle = By.xpath("//h1[contains(text(),'Shopping Cart')]");

	private By unitPriceText = By.xpath("(//table[@class='table table-bordered'])[2]/tbody//td[@class='text-right']");
	private By totalPriceText = By
			.xpath("(//table[@class='table table-bordered'])[2]/tbody//td[@class='text-right'][2]");

	public CartPage(WebDriver driver1) {
		this.driver = driver1;
		wait = new WebDriverWait(driver1, Duration.ofSeconds(10));
	}

	public boolean isCartPageTitleDisplayed() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(cartPageTitle));
		return driver.findElement(cartPageTitle).isDisplayed();
	}

	public String getUnitPriceOfProduct() {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(unitPriceText)).getText();
	}

	public String getTotalPriceOfProduct() {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(totalPriceText)).getText();
	}
}
