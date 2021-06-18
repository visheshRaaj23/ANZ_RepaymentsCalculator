package com.anz.www.full.cucumber.options;

import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class ChromeTest {

	@BeforeSuite
	public void startDockerGrid() {
		try {
			Runtime.getRuntime().exec("cmd /c start start_dockergrid.bat");
			Thread.sleep(40000);
			Runtime.getRuntime().exec("cmd /c start scaleup_dockergrid.bat");
			Thread.sleep(10000);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@Test
	public void Test1() throws Exception {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--headless");
		options.addArguments("--no-sandbox");
		options.addArguments("disable-dev-shm-usage");
		URL url = new URL("http://localhost:4444/wd/hub");
		RemoteWebDriver driver = new RemoteWebDriver(url, options);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.navigate().to("https://www.volvocars.com");
		String title = driver.getTitle();
		System.out.println(title);
		driver.quit();
	}

	@Test
	public void Test2() throws Exception {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--headless");
		options.addArguments("--no-sandbox");
		options.addArguments("disable-dev-shm-usage");
		URL url = new URL("http://localhost:4444/wd/hub");
		RemoteWebDriver driver = new RemoteWebDriver(url, options);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.navigate().to("https://www.google.com/");
		String title = driver.getTitle();
		System.out.println(title);
		driver.quit();
	}

	@Test
	public void Test3() throws Exception {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--headless");
		options.addArguments("--no-sandbox");
		options.addArguments("disable-dev-shm-usage");
		URL url = new URL("http://localhost:4444/wd/hub");
		RemoteWebDriver driver = new RemoteWebDriver(url, options);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.navigate().to("https://www.mercedes-benz.co.in");
		String title = driver.getTitle();
		System.out.println(title);
		driver.quit();
	}

	@Test
	public void Test4() throws Exception {
		FirefoxOptions options = new FirefoxOptions();
		options.addArguments("--headless");
		options.addArguments("--no-sandbox");
		options.addArguments("disable-dev-shm-usage");
		URL url = new URL("http://localhost:4444/wd/hub");
		RemoteWebDriver driver = new RemoteWebDriver(url, options);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.navigate().to("https://www.hyundai.com/");
		String title = driver.getTitle();
		System.out.println(title);
		driver.quit();
	}

	@AfterSuite
	public void stopDockerGrid() throws Exception {
		Thread.sleep(15000);
		Runtime.getRuntime().exec("cmd /c start stop_dockergrid.bat");
		Thread.sleep(17000);
		Runtime.getRuntime().exec("taskkill /f /im cmd.exe");// Closes command prompts
		Thread.sleep(10000);
	}

}
