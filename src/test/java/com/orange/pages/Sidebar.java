package com.orange.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.orange.pageobject.POM;
import com.orange.utilities.Util;

public class Sidebar extends POM {

	public WebDriver driver;
	public Util util;

	public Sidebar(WebDriver driver) {
		super(driver);
		this.driver = driver;
		util = new Util(driver);
	}

	@FindBy(xpath = "//a[@href='https://www.phptravels.net/admin']//*[text()=('Dashboard')]")
	WebElement dashboard;

	@FindBy(xpath = "//a[@href='https://www.phptravels.net/admin/updates/']//*[text()='Updates']")
	WebElement updates;

	@FindBy(xpath = "//a[@href='#Cartrawler' and @data-toggle='collapse' and contains(text(), 'Cartrawler')]")
	WebElement cartrawler;
	
	@FindBy(xpath = "//a[@href='https://www.phptravels.net/admin/cartrawler/settings' and text() = 'Cartrawler']")
	WebElement cartrawler_subMenu_cartrawler;
	
	@FindBy(xpath = "//a[@href = '#ACCOUNTS']//*[@class = 'fa fa-user-circle']")
	WebElement subMenu_Accounts;
	

	public void open_Sidebar_Menu(String menu_name) {
		switch (menu_name) {
			case ("Dashboard"):
				dashboard.click();
				break;
	
			case ("Updates"):
				updates.click();
				break;
				
			case "Cartrawler":
				cartrawler.click();
				util.waitforLoad(cartrawler_subMenu_cartrawler, 5);
				cartrawler_subMenu_cartrawler.click();
				break;
				
			case "Accounts":
				util.waitforLoad(subMenu_Accounts, 5);
				subMenu_Accounts.click();
				break;
		}
	}

}
