package com.anz.www.full.cucumber.options;


import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class PSR_Scenario1 {
	
	private static RemoteWebDriver driver;
	
	//docker run --name sel-hub -p 4446:4444 selenium/hub:latest
	//docker run -d --name chrome-node --link sel-hub:hub -P selenium/node-chrome
	//docker run -d --name firefox-node --link sel-hub:hub -P selenium/node-firefox
	
	
	@Parameters({"browser","hubURL"})
	@BeforeSuite
	public static void setUp(@Optional("chrome")String browserName,String hubUrl)throws Exception {
		
		DesiredCapabilities ds = null;
		if(browserName.equalsIgnoreCase("chrome")) {
			ds = DesiredCapabilities.chrome();
			ds.setBrowserName("chrome");
			ds.setPlatform(Platform.LINUX);
		}else if(browserName.equalsIgnoreCase("firefox")) {
			ds = DesiredCapabilities.firefox();
			ds.setBrowserName("firefox");
			ds.setPlatform(Platform.LINUX);
		}else {
		Assert.fail("Invalid Browser Name:Browser can be firefox or chrome");
		}
		driver=new RemoteWebDriver(new URL(hubUrl),ds);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	@Test
	@Parameters({"applicationURL"})
	public void launchApplication(String applicationURL) {
		driver.navigate().to(applicationURL);
		if(!driver.getTitle().equals("Google")) {
			Assert.fail("Invalid Application URL:Enter the valid URL in XML file");
		}
	}
	
	@Test(dependsOnMethods = "launchApplication")
	public void launch1() {
		driver.navigate().refresh();
		driver.navigate().back();
		driver.navigate().to("https://www.audi.in");
		System.out.println(driver.getTitle());
		
	}
	
	@Test(dependsOnMethods = "launch1")
	public void launch2() {
		driver.navigate().refresh();
		driver.navigate().back();
		driver.navigate().to("https://www.bmw.in");
		System.out.println(driver.getTitle());
	}
	
	@AfterSuite
	public void tearDown() {
		driver.quit();
	}

}
