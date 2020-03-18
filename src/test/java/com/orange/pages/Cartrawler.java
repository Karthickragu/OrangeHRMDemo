package com.orange.pages;

import static org.testng.Assert.assertTrue;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import com.orange.pageobject.POM;
import com.orange.utilities.GlobalConstants;
import com.orange.utilities.Reuseable;
import com.orange.utilities.Util;

public class Cartrawler extends POM {
	
	public WebDriver driver;
	public Sidebar sidebar;
	public Util util;
	public Reuseable reuseable;
	
	public Cartrawler(WebDriver driver) {
		super(driver);
		this.driver = driver;
		sidebar = new Sidebar(driver);
		util = new Util(driver);
		reuseable = new Reuseable(driver);
	}
	
	//// Save Record:
	
	
	
	@FindBy(xpath = "//*[@class = 'glyphicon  glyphicon-picture']")
	WebElement mainIcon;
	
	@FindBy(xpath = "//input[@class = 'icon-picker form-control' and @name = 'page_icon']")
	WebElement iconname_text;
	
	@FindBy(xpath = "//button[@class = 'btn btn-primary' and @type = 'submit']")
	WebElement submit_Button;
	
			
	
	public void open_Cartrawler_settings() {
		sidebar.open_Sidebar_Menu("Cartrawler");
		//getIcons();
	}
	
	public void modify_Cartrawler_Record() {
		
	}

	public void getIcons(String icon_name) {
		util.waitforLoad(mainIcon, 5);
		mainIcon.click();
		List<WebElement> icons = driver.findElements(By.xpath("//*[@class = 'icon-list']//li//a[@href = '#']"));
		//System.out.println(icons.size());
		for(WebElement e: icons) {
			if(e.getAttribute("title").equalsIgnoreCase(icon_name)) {
				e.click();
				break;
			}
		}
	}
	
	public void submitRequest() {
		util.waitforLoad(submit_Button, 5);
		submit_Button.click();
		reuseable.verifySuccessMessage();
	}	
}
