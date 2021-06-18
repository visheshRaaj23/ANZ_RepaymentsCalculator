package com.anz.www.full.cucumber.options;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WindowPopUp {

	public static void main(String[] args)throws Exception {
		System.setProperty("webdriver.chrome.driver", "./server/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.navigate().to("http://admin:admin@the-internet.herokuapp.com/");
		driver.findElement(By.linkText("Basic Auth")).click();
		new WebDriverWait(driver, 10).until(ExpectedConditions
				.visibilityOf(driver.findElement(By.xpath("//p[contains(text(),'Congratulations!')]"))));
		driver.quit();
	}
	
	//Shift Focus to the upload window
	//Set text/path into the file name edit box.
	//Click Open to upload a file.

}
