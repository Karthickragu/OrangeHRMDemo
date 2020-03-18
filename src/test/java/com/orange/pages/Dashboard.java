package com.orange.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import com.orange.pageobject.POM;
import com.orange.utilities.Util;

public class Dashboard extends POM {
	
	public WebDriver driver;
	public Util util;
	public Select select;
	
	public Dashboard(WebDriver driver) {
		super(driver);
		this.driver = driver;
		util = new Util(driver);
	}
	
	
	@FindBy(xpath = "//a[@href='https://www.phptravels.net/admin']")
	WebElement sidemenu_Dashboard;
	
	@FindBy(xpath = "//div//p[@class = 'serverHeader__title']") 
	WebElement dashboard_content;
	
	
	@FindBy(xpath = "//button[@class = 'btn btn-danger btn-block']") 
	WebElement dashboard_content_QuickBook;
	
	@FindBy(xpath = "//button[@class = 'btn btn-primary btn-block']") 
	WebElement dashboard_content_Bookings;
	
	@FindBy(xpath = "//button[@class = 'btn btn-info btn-block']") 
	WebElement dashboard_content_CMS$Pages;
	
	@FindBy(xpath = "//button[@class = 'btn btn-success btn-block']") 
	WebElement dashboard_content_Blog;
	
	@FindBy(xpath = "//form[@action = 'https://www.phptravels.net/admin/quickbooking/']") 
	WebElement dashboard_content_QuickBook_frame;
	
	@FindBy(id = "apply") 
	WebElement dashboard_content_QuickBook_frame_ApplyTax_ddown;
	
	@FindBy(id = "servicetype") 
	WebElement dashboard_content_QuickBook_frame_Service_ddown;
	
	@FindBy(xpath = "//button[@class = 'btn btn-default' and text() = 'Close']") 
	WebElement dashboard_content_QuickBook_frame_Close_btn;
	
	@FindBy(xpath = "//button[@class = 'btn btn-primary' and text() = 'Next']") 
	WebElement dashboard_content_QuickBook_frame_Next_btn;
	
	public void verifyDashboard() throws InterruptedException {
		util.waitforLoad(dashboard_content, 10);
		util.waitforLoad(dashboard_content_QuickBook, 5);
		dashboard_content_QuickBook.click();
		//driver.switchTo().frame(dashboard_content_QuickBook_frame);
		util.waitforLoad(dashboard_content_QuickBook_frame_ApplyTax_ddown, 5);
		//dashboard_content_QuickBook_frame_ApplyTax_ddown.click();
		select = new Select(dashboard_content_QuickBook_frame_ApplyTax_ddown);
		select.selectByValue("no");
		dashboard_content_QuickBook_frame_Service_ddown.click();
		//select = new Select(dashboard_content_QuickBook_frame_Service_ddown);
		//select.selectByVisibleText("Tours");
		//dashboard_content_QuickBook_frame_Next_btn.click();
	}
	
	public void verifyToursPage_Directly_Temp() throws InterruptedException {
		Thread.sleep(2000);
		try {
			driver.get("https://www.phptravels.net/admin/quickbooking/?applytax=no&service=Tours");
		}catch(Exception e) {
			System.out.println();
		} finally {
			driver.get("https://www.phptravels.net/admin/quickbooking/?applytax=no&service=Tours");
		}
		Thread.sleep(2000);
		
	}

}
