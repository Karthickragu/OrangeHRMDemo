package com.orange.pages;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.orange.pageobject.POM;
import com.orange.utilities.Reuseable;
import com.orange.utilities.Util;

public class Accounts extends POM {

	public WebDriver driver;
	public Util util;
	public Select select;
	public Reuseable reuseable;
	public Sidebar sidebar;
	public List<WebElement> pagination;
	public int page_incrementer;

	public Accounts(WebDriver driver) {
		super(driver);
		this.driver = driver;
		util = new Util(driver);
		reuseable = new Reuseable(driver);
		sidebar = new Sidebar(driver);
	}

	@FindBy(xpath = "//ul[@id='ACCOUNTS']//a[@href = 'https://www.phptravels.net/admin/accounts/admins/' and contains(text(), 'Admins')]")
	WebElement subMenu_Accounts_Admin;

	@FindBy(xpath = "//form[@class = 'add_button']//button[@class='btn btn-success' and contains(text(), 'Add')]")
	WebElement accounts_Admin_Add;

	@FindBy(xpath = "//button[@class='btn btn-danger' and @id = 'deleteAll']")
	WebElement accounts_Admin_DeleteAll;

	// * Locators on Add Admin page

	@FindBy(xpath = "//input[@name='fname' and @type='text']")
	WebElement accounts_AddAdmin_FirstName;

	@FindBy(xpath = "//input[@name='lname' and @type='text']")
	WebElement accounts_AddAdmin_LastName;

	@FindBy(xpath = "//input[@name='email' and @type='email']")
	WebElement accounts_AddAdmin_Email;

	@FindBy(xpath = "//input[@name='password' and @type='password']")
	WebElement accounts_AddAdmin_Password;

	@FindBy(xpath = "//input[@name='mobile' and @type='text']")
	WebElement accounts_AddAdmin_Mobilenumber;

	@FindBy(xpath = "//select[@name='country']")
	WebElement accounts_AddAdmin_Country;

	@FindBy(xpath = "//div//input[@name='address1']")
	WebElement accounts_AddAdmin_Address1;

	@FindBy(xpath = "//div//input[@name='address2']")
	WebElement accounts_AddAdmin_Address2;

	@FindBy(xpath = "//input[@class='checkbox' and @name='newssub']")
	WebElement accounts_AddAdmin_SubscribeNLetter;

	@FindBy(xpath = "//button[@class = 'btn btn-primary btn-block btn-lg' and contains(text(), 'Submit')]")
	WebElement accounts_AddAdmin_Submit;

	@FindBy(xpath = "//select[@name='status']")
	WebElement accounts_AddAdmin_MainSetting_Status;

	@FindBy(xpath = "//input[@name='commission']")
	WebElement accounts_AddAdmin_MainSetting_Comission;

	public static String accounts_Pagination = "//div[@class='xcrud-nav']//ul[@class='pagination']//li";

	// Checkboxes for the Add Section

	public void open_Admin() {
		sidebar.open_Sidebar_Menu("Accounts");
		util.waitforLoad(subMenu_Accounts_Admin, 10);
		subMenu_Accounts_Admin.click();
	}

	public void open_Admin_add() {
		util.waitforLoad(accounts_Admin_Add, 10);
		accounts_Admin_Add.click();
	}

	public void adding_New_Admin(int iteration) {
		for (int i = 1; i <= iteration; i++) {
			try {
				open_Admin_add();
			} catch (Exception e) {
				System.out.println("Add button is already selected, hence quit");
			}
			util.waitforLoad(accounts_AddAdmin_FirstName, 10);
			accounts_AddAdmin_FirstName.clear();
			accounts_AddAdmin_FirstName.sendKeys("Lilly" + i);
			accounts_AddAdmin_LastName.clear();
			accounts_AddAdmin_LastName.sendKeys("Samanta" + i);
			accounts_AddAdmin_Email.clear();
			accounts_AddAdmin_Email.sendKeys("hotmail" + i + "@hotmail.com");
			accounts_AddAdmin_Password.clear();
			accounts_AddAdmin_Password.sendKeys("Password");
			accounts_AddAdmin_Mobilenumber.clear();
			accounts_AddAdmin_Mobilenumber.sendKeys("631014567" + i);
			select = new Select(accounts_AddAdmin_Country);
			select.selectByVisibleText("India");
			accounts_AddAdmin_Address1.clear();
			accounts_AddAdmin_Address1.sendKeys("321, Mains st, California");
			accounts_AddAdmin_Address2.clear();
			accounts_AddAdmin_Address2.sendKeys("SaltLake city");
			accounts_AddAdmin_SubscribeNLetter.click();
			select = new Select(accounts_AddAdmin_MainSetting_Status);
			select.selectByVisibleText("Enabled");
			accounts_AddAdmin_MainSetting_Comission.clear();
			accounts_AddAdmin_MainSetting_Comission.sendKeys("0.5");
			accounts_AddAdmin_Submit.click();
			reuseable.verifySuccessMessage();
			Assert.assertEquals(driver
					.findElement(
							By.xpath("//*[@class='xcrud-list table table-striped table-hover']//tbody//tr[1]/td[3]"))
					.getText(), "Lilly" + i);
			System.out.println(driver
					.findElement(
							By.xpath("//*[@class='xcrud-list table table-striped table-hover']//tbody//tr[1]/td[3]"))
					.getText());
			Assert.assertEquals(driver
					.findElement(
							By.xpath("//*[@class='xcrud-list table table-striped table-hover']//tbody//tr[1]/td[4]"))
					.getText(), "Samanta" + i);
			System.out.println(driver
					.findElement(
							By.xpath("//*[@class='xcrud-list table table-striped table-hover']//tbody//tr[1]/td[4]"))
					.getText());
			System.out.println(driver
					.findElement(
							By.xpath("//*[@class='xcrud-list table table-striped table-hover']//tbody//tr[1]/td[5]//a"))
					.getText());
			Assert.assertEquals(driver
					.findElement(
							By.xpath("//*[@class='xcrud-list table table-striped table-hover']//tbody//tr[1]/td[5]//a"))
					.getText(), "hotmail" + i + "@hotmail.com");
		}

	}

	public void pagination_Select() throws InterruptedException {
		util.waitforLoad(accounts_Admin_DeleteAll, 10);
		Thread.sleep(5000);
		pagination = driver.findElements(By.xpath(accounts_Pagination));
		System.out.println("Size of the Pagination: " + pagination.size());
	}

	public void select_Records(String firstname, String lastname, String email) throws InterruptedException {
		page_incrementer = 1;
		do {
			try {
				driver.findElement(By.xpath(
						"//div[@class='xcrud-nav']//ul[@class='pagination']//li/a[(text()= '" + page_incrementer + "')]"))
						.click();
			} catch (Exception e) {
				System.out.println("Unable to click on page :" + page_incrementer);
			}
			Thread.sleep(5000);
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.scrollBy(0,500)", "");
			List<WebElement> noofRecords = driver
					.findElements(By.xpath("//*[@class='xcrud-list table table-striped table-hover']//tbody//tr"));
			System.out.println("Total No.of Rows avaiable: " + noofRecords.size());
			// *********************************** Selecting for a record //
			// ******************************
			for (int i = 1; i <= noofRecords.size(); i++) {
				if (driver
						.findElement(By.xpath(
								"//*[@class='xcrud-list table table-striped table-hover']//tbody//tr[" + i + "]/td[3]"))
						.getText().equals(firstname)) {
					if (driver.findElement(By.xpath(
							"//*[@class='xcrud-list table table-striped table-hover']//tbody//tr[" + i + "]/td[4]"))
							.getText().equals(lastname)) {
						if (driver.findElement(
								By.xpath("//*[@class='xcrud-list table table-striped table-hover']//tbody//tr[" + i
										+ "]//td[5]/a"))
								.getText().equals(email)) {
							driver.findElement(
									By.xpath("//*[@class='xcrud-list table table-striped table-hover']//tbody//tr[" + i
											+ "]//input[@class='checkboxcls']"))
									.click();
							break;
						}
					}
				}
			}
			page_incrementer++;
			System.out.println("Value of page_incrementer :" + page_incrementer);
			//driver.findElement(By.xpath("//div[@class='xcrud-nav']//ul[@class='pagination']//li/a[(text()= '" + page_incrementer + "')]")).click();
		} while (page_incrementer < pagination.size());

	}

	public void delete_Records() {
		util.waitforLoad(accounts_Admin_DeleteAll, 10);
		accounts_Admin_DeleteAll.click();
		driver.switchTo().alert().accept();
		driver.switchTo().defaultContent();
	}

	public void verify_deleted_Records(String firstname, String lastname, String email) {
		List<WebElement> noofRecords = driver
				.findElements(By.xpath("//*[@class='xcrud-list table table-striped table-hover']//tbody//tr"));
		System.out.println("Verifying the Deleted Records");
		// *********************************** Selecting for a record //
		// ******************************
		for (int i = 1; i <= noofRecords.size(); i++) {
			if (driver
					.findElement(By.xpath(
							"//*[@class='xcrud-list table table-striped table-hover']//tbody//tr[" + i + "]/td[3]"))
					.getText().equals(firstname)) {
				if (driver
						.findElement(By.xpath(
								"//*[@class='xcrud-list table table-striped table-hover']//tbody//tr[" + i + "]/td[4]"))
						.getText().equals(lastname)) {
					if (driver.findElement(By.xpath(
							"//*[@class='xcrud-list table table-striped table-hover']//tbody//tr[" + i + "]//td[5]/a"))
							.getText().equals(email)) {
						System.out.println("Record not Deleted!!!... Hence Failed");
						break;
					} else {
						System.out.println("Record Deleted Successfully");
					}
				}
			}
		}
	}
}

/*

*/