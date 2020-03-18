package com.orange.utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import com.orange.pageobject.POM;

public class Reuseable extends POM {
	
	public Util util;
	
	public Reuseable(WebDriver driver) {
		super(driver);
		this.driver = driver;
		util = new Util(driver);
	}
	
	@FindBy(xpath = "//*[@class = 'ui-pnotify-title' and contains(text(), 'Changes Saved')]")
	WebElement messageSaved;
	
	
	
	public void verifySuccessMessage() {
		util.waitforLoad(messageSaved, 10);
		Assert.assertTrue(messageSaved.isDisplayed());
		System.out.println(messageSaved.getText());
		Assert.assertTrue(messageSaved.getText().equals(GlobalConstants.saved_message));
	}

}
