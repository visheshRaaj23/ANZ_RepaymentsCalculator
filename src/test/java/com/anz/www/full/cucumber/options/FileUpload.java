package com.anz.www.full.cucumber.options;

import java.io.File;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.anz.www.full.utilities.BusinessMethods;

public class FileUpload {

	public static void main(String[] args)throws Exception {
		HashMap<String,Object> chromePrefs = new HashMap<String,Object>();
		chromePrefs.put("profile.default_content_settings.popups", 0);
		String downloadPath = System.getProperty("user.dir")+"\\result";
		chromePrefs.put("download.default_directory", downloadPath);
		System.out.println(downloadPath);
		//ChromeOptions options = new ChromeOptions();
	//	options.setExperimentalOption("prefs", chromePrefs);
		//System.setProperty("webdriver.chrome.driver", "./server/chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		//FirefoxOptions options = new FirefoxOptions();
		options.addArguments("--headless");
		options.addArguments("--no-sandbox");
		options.addArguments("disable-dev-shm-usage");
		URL url = new URL("http://localhost:4444/wd/hub");
		RemoteWebDriver driver = new RemoteWebDriver(url,options);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.navigate().to("https://altoconvertpdftojpg.com/");
		driver.findElement(By.xpath("//span[contains(text(),'Choose File')]/parent::button")).click();
		BusinessMethods.waitForElement();
		Runtime.getRuntime().exec("./script/FileUploadAutoItScript.exe");
		BusinessMethods.waitForElement();
		WebDriverWait wait = new WebDriverWait(driver, 17);
		JavascriptExecutor js = (JavascriptExecutor)driver;
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(text(),'Convert Now')]")));
		js.executeScript("window.scrollBy(0,250);", "");
		driver.findElement(By.xpath("//button[contains(text(),'Convert Now')]")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'Download')]")));
		driver.findElement(By.xpath("//a[contains(text(),'Download')]")).click();
		BusinessMethods.waitForElement();
		Thread.sleep(5000);
		File file = new File(downloadPath+"/1.jpg");
		if(file.exists()) {
			System.out.println("File Found");
			Assert.assertTrue(file.exists());
			if(file.delete()) {
				System.out.println("File is deleted");
			}
		}
		driver.quit();
		
		

	}

}
