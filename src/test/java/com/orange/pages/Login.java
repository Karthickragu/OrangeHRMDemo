package com.orange.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.asserts.SoftAssert;

import com.orange.pageobject.POM;
import com.orange.utilities.GlobalConstants;
import com.orange.utilities.Util;

public class Login extends POM {
	
	public WebDriver driver;
	public SoftAssert assertion;
	public static Util util;
	public static Dashboard dashboard;
	public static Boolean isselected = false;
	
	public Login(WebDriver driver) {
		super(driver);
		this.driver = driver;
		util = new Util(driver);
		dashboard = new Dashboard(driver); 
	}
	
	
	/* Page Factory */
	
	@FindBy(xpath = "//input[@name = 'email' and @type = 'text']")
	WebElement eMail_Text;
	
	@FindBy(xpath = "//input[@name = 'password' and @type = 'password']")
	WebElement password_Text;
	
	//@FindBy(xpath = "//input[@type = 'checkbox' and @name = 'remember']")
	@FindBy(xpath = "//input[@type = 'checkbox']")
	WebElement rememberMe_Checkbox;
	
	@FindBy(id = "link-forgot")
	WebElement forgotPassword_link;
	
	@FindBy(xpath = "//button[@class = 'btn btn-primary btn-block ladda-button fadeIn animated btn-lg']")
	WebElement login_Btn;
	
	@FindBy(xpath = "//div[@class = 'alert alert-danger loading wow fadeIn animated animated']")
	WebElement login_Error_Message;
	
	@FindBy(xpath = "//div[@class = 'alert alert-success login wow fadeIn animated animated']")
	WebElement login_success_Message;
			
	
	public void verifyLoginUI() {
		assertion = new SoftAssert();
		util.waitforLoad(eMail_Text, 5);
		assertion.assertTrue(eMail_Text.isDisplayed());
		assertion.assertTrue(password_Text.isDisplayed());
		//assertion.assertTrue(rememberMe_Checkbox.isDisplayed());
		//assertion.assertTrue(login_Btn.isDisplayed());
		//assertion.assertTrue(forgotPassword_link.isDisplayed());
		assertion.assertAll();
	}
	
	public void verifyinvalidLogin() throws InterruptedException {
		util.waitforLoad(eMail_Text, 5);
		assertion = new SoftAssert();
		eMail_Text.sendKeys("Ragav");
		password_Text.sendKeys("Password");
		rememberMe_Checkbox.click();
		login_Btn.click();
		util.waitforLoad(login_Error_Message, 5);
		System.out.println("1."+ login_Error_Message.getText());
		System.out.println("2."+GlobalConstants.login_Error_Message);
		assertion.assertEquals(login_Error_Message.getText(), GlobalConstants.login_Error_Message);
		assertion.assertAll();
	}
	
	public void verifyValidLogin(String username, String password) throws InterruptedException {
		util.waitforLoad(eMail_Text, 5);
		assertion = new SoftAssert();
		eMail_Text.sendKeys(username);
		password_Text.sendKeys(password);
		//Select select = new Select(rememberMe_Checkbox);
		login_Btn.click();
		Thread.sleep(1200);
		String pageSource = driver.getPageSource(); 
		//System.out.println(pageSource);
		try {
			pageSource.contains(GlobalConstants.login_success_Message);
		}catch(Exception e) {
			System.out.println("SUCCESS MESSAGE NOT FOUND....................................");
		}
		util.waitforLoad(dashboard.sidemenu_Dashboard, 10);
		assertion.assertTrue(dashboard.sidemenu_Dashboard.isDisplayed());
		assertion.assertAll();
	}
	
}
