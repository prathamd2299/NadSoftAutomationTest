package com.nadsoft.www.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

public class BaseTest {
	public WebDriver driver;

	@BeforeMethod
	@Parameters("browser")
	public void setupDriver(String browser) {
		if (browser.toLowerCase().equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();
		} else if (browser.toLowerCase().equalsIgnoreCase("edge")) {
			driver = new EdgeDriver();
		} else {
			System.out.println("Invalid browser name");
		}

		driver.manage().window().maximize();
		driver.get("https://tutorialsninja.com/demo/index.php?route=common/home");
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
}
