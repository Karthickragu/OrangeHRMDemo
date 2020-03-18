package com.orange.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class POM {
	
	public WebDriver driver;
	
	public POM(WebDriver driver) {		
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
}
