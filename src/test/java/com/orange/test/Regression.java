package com.orange.test;

import java.util.concurrent.TimeUnit;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.orange.pages.Accounts;
import com.orange.pages.Cartrawler;
import com.orange.pages.Dashboard;
import com.orange.pages.Login;
import com.orange.pages.QuickBooking;
import com.orange.utilities.Util;
import com.phptravels.base.Testengine;

@Listeners(com.orange.test.Regression.class)
public class Regression extends Testengine implements ITestListener {
	
	public static Util util;
	public static Login login;
	public Dashboard dashboard;
	public QuickBooking quickbooking;
	public Cartrawler cartrawler;
	public Accounts accounts;
	
	@BeforeClass
	public void passDriver() {
		util = new Util(driver);
		login = new Login(driver);
		dashboard = new Dashboard(driver); 
		quickbooking = new QuickBooking(driver);
		cartrawler = new Cartrawler(driver);
		accounts = new Accounts(driver);
	}
	
	@BeforeMethod()
	public void startUp() {
		driver.manage().window().maximize();
		try {
			Thread.sleep(3000);
			driver.get("https://www.phptravels.net/admin/");
			driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@AfterMethod
	public void tearDown() {
		
	}
	
	@Test(priority = 1)
	public void test_TC001() {
		login.verifyLoginUI();
	}
	
	@Test(priority = 2)
	public void test_TC002() {
		try {
			driver.manage().deleteAllCookies();
			login.verifyinvalidLogin();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
		
	@Test(priority = 3)
	public void test_TC003() throws Exception {
		driver.manage().deleteAllCookies();
		login.verifyValidLogin("admin@phptravels.com", "demoadmin");
	}
	
	@Test(priority = 4)
	public void test_TC004() throws InterruptedException {
		dashboard.verifyDashboard();
	}
	
	@Test(priority = 5)
	public void test_TC005() throws Exception {
		dashboard.verifyToursPage_Directly_Temp();
		quickbooking.verifyUI_QuickBooking();
		quickbooking.verify_qb_General();
		quickbooking.verify_qb_Customer();
		quickbooking.verify_qb_Item("May", "15", "May", "20");
	}
	
	@Test(priority = 6)
	public void test_TC006() throws Exception {
		cartrawler.open_Cartrawler_settings();
		cartrawler.getIcons("glass");
		cartrawler.submitRequest();
	}
	
	@Test(priority = 7)
	public void test_TC007() throws Exception {
		accounts.open_Admin();
		accounts.adding_New_Admin(30);
	}
	
	@Test(priority = 8)
	public void test_TC008() throws Exception {
		accounts.open_Admin();
		accounts.pagination_Select();
		accounts.select_Records("Lilly21", "Samanta21", "hotmail21@hotmail.com");
		accounts.select_Records("Lilly15", "Samanta15", "hotmail15@hotmail.com");
		accounts.select_Records("Lilly2", "Samanta2", "hotmail2@hotmail.com");
		//accounts.delete_Records();
		//accounts.verify_deleted_Records("Lilly1", "Samanta1", "hotmail1@hotmail.com");
		//accounts.verify_deleted_Records("Lilly3", "Samanta3", "hotmail3@hotmail.com");
	}
	
	@Test(priority = 9)
	public void test_TC009() throws Exception {
		accounts.open_Admin();
		accounts.verify_deleted_Records("Lilly2", "Samanta2", "hotmail2@hotmail.com");
	}
	
//***************************************************** // LISTENERS // **********************************************************************************//
	public void onTestStart(ITestResult result) {
		System.out.println("Test case started Successfully...");
		totalTCs = (short) (totalTCs + 1);
	}

	public void onTestSuccess(ITestResult result) {
		passedTCs = (short) (passedTCs + 1);
	}

	public void onTestFailure(ITestResult result) {
		failedTCs = (short) (failedTCs + 1);
	}

	public void onTestSkipped(ITestResult result) {
		skippedTCs = (short) (skippedTCs + 1);
	}

	public void onStart(ITestContext context) {
		System.out.println("On Start Context");
	}

	public void onFinish(ITestContext context) {
		System.out.println("Test case completed...");
	}


}
