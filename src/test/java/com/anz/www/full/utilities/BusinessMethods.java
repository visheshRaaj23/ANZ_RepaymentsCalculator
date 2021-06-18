package com.anz.www.full.utilities;

import java.io.FileInputStream;
import java.time.Instant;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BusinessMethods {
	
	public static String getPropertyValue(String parameter) throws Exception {
		Properties property = new Properties();
		FileInputStream fileInputStream = new FileInputStream("./resources/application.properties");
		property.load(fileInputStream);
		String parameterValue = (String) property.get(parameter);
		System.out.println(parameterValue);
		return parameterValue;
	}
	
	public static void waitForVisibilityOfElement(WebDriver driver,WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}
	
	public static void waitForElement() {
		long end=System.currentTimeMillis()+3500;
		while(System.currentTimeMillis()<=end) {
			System.out.println("Wait for element to load");
		}
		
	}
	
	public static void main(String[]args) {
		long end=System.currentTimeMillis()+3000;
		while(System.currentTimeMillis()<end) {
			System.out.println("Wait for System to load");
		}
		
	}
	
	

}
