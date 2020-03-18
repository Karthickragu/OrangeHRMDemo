package com.orange.utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Util {
	
	private WebDriver driver;
	public static WebDriverWait wait;

	public Util(WebDriver driver) {
		this.driver = driver;
	}
	
	public void waitforLoad(WebElement element, int time) {
		wait = new WebDriverWait(driver, time);
		wait.until(ExpectedConditions.visibilityOf(element));
	}
}
