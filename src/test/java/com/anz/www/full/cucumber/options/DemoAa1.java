package com.anz.www.full.cucumber.options;

import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Platform;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class DemoAa1 {

	public static void main(String[] args)throws Exception {
		//ChromeOptions options = new ChromeOptions();
		FirefoxOptions options = new FirefoxOptions();
		options.addArguments("--headless");
		options.addArguments("--no-sandbox");
		options.addArguments("disable-dev-shm-usage");
		URL url = new URL("http://localhost:4444/wd/hub");
		RemoteWebDriver driver = new RemoteWebDriver(url,options);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("https://www.google.com");
		String title = driver.getTitle();
		System.out.println(title);
		driver.quit();
	}

}
