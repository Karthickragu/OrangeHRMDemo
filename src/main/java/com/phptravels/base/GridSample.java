package com.phptravels.base;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class GridSample {
	
	public static WebDriver driver;

	public static void main(String args[]) {
		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setBrowserName("chrome");
		cap.setPlatform(Platform.WINDOWS);
		
		
		ChromeOptions options = new ChromeOptions();
		options.merge(cap);
		
		String hubURL = "http://192.168.0.5:4444/wd/hub";
		try {
			driver = new RemoteWebDriver(new URL(hubURL), options);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		driver.get("https://opensource-demo.orangehrmlive.com/");
		
		
	}
}