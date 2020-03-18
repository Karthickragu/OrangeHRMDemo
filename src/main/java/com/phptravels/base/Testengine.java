package com.phptravels.base;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Testengine {

	public static Logger orangelog;
	public static ExtentReports report;
	public static ExtentTest test;
	public static WebDriver driver;
	public static Short totalTCs = 0;
	public static Short failedTCs = 0;
	public static Short passedTCs = 0;
	public static Short skippedTCs = 0;
	public static Boolean assersion = false;
	public static SoftAssert verify;

	@BeforeSuite
	public void initiate() {
		try {
			orangelog = Logger.getLogger("log4j.properties");
		} catch (Exception e) {
			System.out.println("Log4j configuration not found in the system");
		}

		try {
			report = new ExtentReports(System.getProperty("user.dir") + "\\ExtentReportResults.html");
			test = report.startTest("ExtentDemo");
		} catch (Exception e) {
			System.out.println("User Directory is not reachable");
		}
	}

	@BeforeTest
	@Parameters({ "value" })
	public void setUp(String value) {
		System.out.println("Selected Browser is " + value);

		if (value.equalsIgnoreCase("Chrome")) {
			/*
			try {
				DesiredCapabilities cap = new DesiredCapabilities();
				cap.setBrowserName("chrome");
				cap.setPlatform(Platform.WINDOWS);

				ChromeOptions options = new ChromeOptions();
				options.merge(cap);

				String hubURL = "http://192.168.0.5:4444/wd/hub";
				try {
					driver = new RemoteWebDriver(new URL(hubURL), options);
				} catch (MalformedURLException e) {
					e.printStackTrace();
				}
			} catch (Exception e) {
				System.out.println("Failed to connect to Node.. hence executing on Hub");
				WebDriverManager.chromedriver().setup();
				driver = new ChromeDriver();
			}
			*/
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		}

		if (value.equalsIgnoreCase("Firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}

		if (value.equalsIgnoreCase("Edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		}
	}

	@AfterTest
	public void terminate() {
		//driver.quit();

	}

	@AfterSuite
	public void generateReports() {
		System.out.println("Total Number of Test Cases are : " + totalTCs + " in which ");
		System.out.println("Total Test Cases Passed :" + passedTCs);
		System.out.println("Total Test Cases Failed :" + failedTCs);
		System.out.println("Total Test Cases Skipped :" + skippedTCs);

	}


}
