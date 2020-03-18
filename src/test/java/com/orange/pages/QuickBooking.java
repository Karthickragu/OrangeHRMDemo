package com.orange.pages;

import java.util.List;
import java.util.concurrent.TimeUnit;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.testng.asserts.SoftAssert;
import com.orange.pageobject.POM;
import com.orange.utilities.Util;

public class QuickBooking extends POM {

	public WebDriver driver;
	public SoftAssert assertion;
	public Select select;
	public Util util;

	public QuickBooking(WebDriver driver) {
		super(driver);
		this.driver = driver;
		util = new Util(driver);
	}

	@FindBy(xpath = "//div//span[@class = 'panel-title pull-left' and text() = 'Quick Booking']")
	WebElement qb_header;

	@FindBy(xpath = "//*[@class = 'panel-heading']//*[text() = 'General']")
	WebElement qb_General_header;

	@FindBy(xpath = "//select[@name = 'title' and @id = 'servicetype']")
	WebElement qb_General_Service_ddown;

	@FindBy(xpath = "//*[@class = 'panel-heading']//*[text() = 'Customer']")
	WebElement qb_Customer_header;

	@FindBy(xpath = "//select[@id = 'selusertype' and @name = 'usertype']")
	WebElement qb_General_Customer_ddown;

	@FindBy(xpath = "//select[@name='customer' and @class='chosen-select select2-offscreen']")
	WebElement qb_General_CustomerName_ddown;

	@FindBy(xpath = "//*[@class = 'panel-heading']//*[text() = 'Item']")
	WebElement qb_Item_header;

	@FindBy(xpath = "//input[@class ='form-control dpd1' and @name = 'checkin']")
	WebElement qb_Item_checkin_date;

	@FindBy(xpath = "//input[@class ='form-control dpd2' and @name = 'checkout']")
	WebElement qb_Item_checkout_date;

	@FindBy(xpath = "//input[@id='stay' and @name='stay']")
	WebElement qb_Item_totalNights_value;

	@FindBy(xpath = "//input[@id = 'totaltopay' and @name = 'quickDeposit']")
	WebElement qb_Item_quickDeposit_text;

	@FindBy(xpath = "//td[@id = 'topaytotal']")
	WebElement qb_DepositAmount;

	@FindBy(xpath = "//div[4][@class='datepicker dropdown-menu']//div[@class='datepicker-days']//*[@class='switch']")
	WebElement qb_Item_checkin_datepicker;
	@FindBy(xpath = "//div[4][@class='datepicker dropdown-menu']//div[@class='datepicker-days']//*[@class='next']")
	WebElement qb_Item_checkin_datepicker_next;
	@FindBy(xpath = "//div[4][@class='datepicker dropdown-menu']//div[@class='datepicker-days']//*[@class='prev']")
	WebElement qb_Item_checkin_datepicker_prev;
	@FindBy(xpath = "//div[5][@class='datepicker dropdown-menu']//div[@class='datepicker-days']//*[@class='switch']")
	WebElement qb_Item_checkout_datepicker;
	@FindBy(xpath = "//div[5][@class='datepicker dropdown-menu']//div[@class='datepicker-days']//*[@class='next']")
	WebElement qb_Item_checkout_datepicker_next;
	@FindBy(xpath = "//div[5][@class='datepicker dropdown-menu']//div[@class='datepicker-days']//*[@class='prev']")
	WebElement qb_Item_checkout_datepicker_prev;

	public void verifyUI_QuickBooking() {
		assertion = new SoftAssert();
		String currentUrl = driver.getCurrentUrl();
		assertion.assertTrue(currentUrl.contains("service=Tours"));
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		assertion.assertTrue(qb_General_header.isDisplayed());
		assertion.assertTrue(qb_Customer_header.isDisplayed());
		assertion.assertTrue(qb_Item_header.isDisplayed());
		assertion.assertAll();
	}

	public void verify_qb_General() {
		assertion = new SoftAssert();
		assertion.assertTrue(qb_General_Service_ddown.isDisplayed());
		select = new Select(qb_General_Service_ddown);
		select.selectByVisibleText("Cartrawler");
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		util.waitforLoad(qb_Item_checkin_date, 20);
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertion.assertAll();
	}

	public void verify_qb_Customer() {
		assertion = new SoftAssert();
		assertion.assertTrue(qb_General_Customer_ddown.isDisplayed());
		select = new Select(qb_General_Customer_ddown);
		select.selectByVisibleText("Registered");
		// assertion.assertTrue(qb_General_CustomerName_ddown.isDisplayed());
		// qb_General_CustomerName_ddown.click();
		// select = new Select(qb_General_CustomerName_ddown);
		// select.selectByVisibleText("Demo User - user@phptravels.com");
		assertion.assertAll();
	}

	public void verify_qb_Item(String checkInFrom, String date_checkin, String checkOutTill, String date_checkout)
			throws Exception {
		assertion = new SoftAssert();
		assertion.assertTrue(qb_Item_checkin_date.isDisplayed());
		Thread.sleep(5000);
		qb_Item_checkin_date.click();
		String month = null;

		for (int i = 0; i < 12; i++) {
			month = StringUtils.substring(qb_Item_checkin_datepicker.getText(), 0, -4).trim();
			System.out.println(month);
			if (month.equalsIgnoreCase(checkInFrom)) {
				System.out.println("Matched");
				break;
			} else {
				qb_Item_checkin_datepicker_next.click();
				month = StringUtils.substring(qb_Item_checkin_datepicker.getText(), 0, -4);
			}
		}
		List<WebElement> active_dates = driver.findElements(By.xpath(
				"//div[4][@class='datepicker dropdown-menu']//div[@class='datepicker-days']//*[@class = 'day ']"));
		try {
			for (int i = 0; i < active_dates.size(); i++) {
				if (active_dates.get(i).getText().toString().equals(date_checkin)) {
					active_dates.get(i).click();
				}
			}
		} catch (Exception e) {
			System.out.println("Unable to find the Number");
		}
		
		
		
		for (int i = 0; i < 12; i++) {
			month = StringUtils.substring(qb_Item_checkout_datepicker.getText(), 0, -4).trim();
			if (month.equalsIgnoreCase(checkOutTill)) {
				System.out.println("Matched");
				break;
			} else {
				qb_Item_checkout_datepicker_next.click();
				month = StringUtils.substring(qb_Item_checkout_datepicker.getText(), 0, -4);
			}
		}
		active_dates = driver.findElements(By.xpath(
				"//div[5][@class='datepicker dropdown-menu']//div[@class='datepicker-days']//*[@class = 'day ']"));
		try {
			for (int i = 0; i < active_dates.size(); i++) {
				if (active_dates.get(i).getText().toString().equals(date_checkout)) {
					active_dates.get(i).click();
				}
			}
		} catch (Exception e) {
			System.out.println("Unable to find the Number");
		}
		
		
		qb_DepositAmount.click();
		//assertion.assertTrue(qb_Item_checkout_date.isDisplayed());
		Thread.sleep(2000);
		qb_DepositAmount.click();
		
		qb_Item_quickDeposit_text.clear();
		Thread.sleep(2000);
		qb_Item_quickDeposit_text.sendKeys("900");
		Thread.sleep(2000);
		
		qb_Item_totalNights_value.click();
		Thread.sleep(2000);
		qb_Item_totalNights_value.click();
		
		String depositAmount = qb_DepositAmount.getText();
		System.out.println("Deposit Amount  : " +depositAmount);
		Thread.sleep(2000);
		String totalStay = qb_Item_totalNights_value.getText();
		System.out.println("Total Stay in Days : "+totalStay);
		
		
		
		/*
		
		
		String depositAmount = qb_DepositAmount.getText();
		System.out.println(depositAmount);
		Thread.sleep(2000);
		//assertion.assertTrue(depositAmount.contains(qb_Item_quickDeposit_text.getText()));
		System.out.println(depositAmount);
		System.out.println(qb_Item_quickDeposit_text.getText());
		String totalStay = qb_Item_totalNights_value.getText();
		System.out.println(totalStay);
		assertion.assertTrue(totalStay.equals('5'));
		assertion.assertAll();
		
		*/
	}

}
