package com.anz.www.full.stepDefinations;

import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;

import com.anz.www.full.utilities.BusinessMethods;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class HL_BorrowerTest {

	//private static WebDriver driver = null;
	private static RemoteWebDriver driver=null;
	private String url;

	@Given("User is on Home Loan calculator Page launched with {string} browser")
	public void user_is_on_Home_Loan_calculator_Page_launched_with_browser(String browser) {
		try {
			if (browser.equalsIgnoreCase("chrome")) {
				DesiredCapabilities dc = DesiredCapabilities.chrome();
				//System.setProperty("webdriver.chrome.driver", "./server/chromedriver.exe");
				//driver = new ChromeDriver();
				URL url = new URL("http://localhost:4444/wd/hub");
				 driver = new RemoteWebDriver(url,dc);

			} else if (browser.equalsIgnoreCase("edge")) {
				System.setProperty("webdriver.edge.driver", "./driver/msedgedriver.exe");
				driver = new EdgeDriver();
			} else if (browser.equalsIgnoreCase("ie")) {
				System.setProperty("webdriver.ie.driver", "./driver/IEDriverServer.exe");
				driver = new InternetExplorerDriver();
			}
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Long.parseLong(BusinessMethods.getPropertyValue("timeOut")),
					TimeUnit.SECONDS);
			this.url = BusinessMethods.getPropertyValue("application_url");
			driver.navigate().to(this.url);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	@When("User enters the following details to get the borrowing estimate")
	public void user_enters_the_following_details_to_get_the_borrowing_estimate(DataTable dataTable) {
		try {
			List<Map<String, String>> borrowersData = dataTable.asMaps();
			String applicationType = borrowersData.get(0).get("ApplicationType");
			System.out.println(applicationType);
			String dependantsInfo = borrowersData.get(0).get("Dependents");
			System.out.println(dependantsInfo);
			String propertyType = borrowersData.get(0).get("PropertyType");
			System.out.println(propertyType);
			String yourIncome = borrowersData.get(0).get("YourIncome");
			System.out.println(yourIncome);
			String yourOtherIncome = borrowersData.get(0).get("YourOtherIncome");
			System.out.println(yourOtherIncome);
			String livingExpenses = borrowersData.get(0).get("LivingExpenses");
			System.out.println(livingExpenses);
			String currentHomeLoanExpenses = borrowersData.get(0).get("CurrentHomeLoanExpenses");
			System.out.println(currentHomeLoanExpenses);
			String otherLoanRepayments = borrowersData.get(0).get("OtherLoanRepayments");
			System.out.println(otherLoanRepayments);
			String committments = borrowersData.get(0).get("Committments");
			System.out.println(committments);
			String totalCreditCardLimits = borrowersData.get(0).get("TotalCreditCardLimits");
			System.out.println(totalCreditCardLimits);

			if (applicationType.equalsIgnoreCase("Single")) {
				driver.findElement(By.xpath(
						"//label[contains(text(),'Application type')]/parent::div/child::ul/li/label[contains(text(),'Single')]/input"))
						.click();
			} else if (applicationType.equalsIgnoreCase("Joint")) {
				driver.findElement(By.xpath(
						"//label[contains(text(),'Application type')]/parent::div/child::ul/li/label[contains(text(),'Joint')]/input"))
						.click();
			}

			// Dependents Info
			Select selectDependents = new Select(
					driver.findElement(By.xpath("//select[contains(@title,'Number of dependants')]")));
			if (Integer.parseInt(dependantsInfo) >= 5) {
				selectDependents.selectByVisibleText("5+");
			} else {
				selectDependents.selectByVisibleText(dependantsInfo);
			}
			// Property you would like to buy
			if (propertyType.equalsIgnoreCase("Home to live in")) {
				driver.findElement(By.xpath(
						"//label[contains(text(),'Property you would like to buy')]/parent::div/ul/li/label[contains(text(),'Home to live in')]"))
						.click();
			} else if (propertyType.equalsIgnoreCase("Residential investment")) {
				driver.findElement(By.xpath(
						"//label[contains(text(),'Property you would like to buy')]/parent::div/ul/li/label[contains(text(),'Residential investment')]"))
						.click();
			}

			// Your Income
			driver.findElement(
					By.xpath("//label[contains(text(),'Your income (before tax)')]/following-sibling::div/input"))
					.click();
			driver.findElement(
					By.xpath("//label[contains(text(),'Your income (before tax)')]/following-sibling::div/input"))
					.sendKeys(yourIncome);

			// Your Other Income
			driver.findElement(By.xpath("//label[contains(text(),'Your other income')]/following-sibling::div/input"))
					.click();
			driver.findElement(By.xpath("//label[contains(text(),'Your other income')]/following-sibling::div/input"))
					.sendKeys(yourOtherIncome);

			// Living Expenses
			driver.findElement(By.xpath("//label[contains(text(),'Living expenses')]/following-sibling::div/input"))
					.click();
			driver.findElement(By.xpath("//label[contains(text(),'Living expenses')]/following-sibling::div/input"))
					.sendKeys(livingExpenses);

			// Current Home Loan Repayments
			driver.findElement(
					By.xpath("//label[contains(text(),'Current home loan repayments')]/following-sibling::div/input"))
					.click();
			driver.findElement(
					By.xpath("//label[contains(text(),'Current home loan repayments')]/following-sibling::div/input"))
					.sendKeys(currentHomeLoanExpenses);

			// Other Loan Repayments
			driver.findElement(
					By.xpath("//label[contains(text(),'Other loan repayments')]/following-sibling::div/input")).click();
			driver.findElement(
					By.xpath("//label[contains(text(),'Other loan repayments')]/following-sibling::div/input"))
					.sendKeys(otherLoanRepayments);

			// Other committments
			driver.findElement(By.xpath("//label[contains(text(),'Other commitments')]/following-sibling::div/input"))
					.click();
			driver.findElement(By.xpath("//label[contains(text(),'Other commitments')]/following-sibling::div/input"))
					.sendKeys(committments);

			// Total Credit Limits

			driver.findElement(
					By.xpath("//label[contains(text(),'Total credit card limits')]/following-sibling::div/input"))
					.click();
			driver.findElement(
					By.xpath("//label[contains(text(),'Total credit card limits')]/following-sibling::div/input"))
					.sendKeys(totalCreditCardLimits);

			driver.findElement(By.xpath("//button[contains(text(),'Work out')]")).click();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@Then("Borrowing estimate is displayed as {string}")
	public void borrowing_estimate_is_displayed_as(String borrowingAmountExpected) {

		try {
			BusinessMethods.waitForElement();
			WebElement borrowingAmount = driver.findElement(By.xpath("//span[@id='borrowResultTextAmount']"));
			System.out.println("Actual:"+borrowingAmount.getText().replaceAll("[$,]",""));
			System.out.println("Expected:"+borrowingAmountExpected.toString().replaceAll("[$,]",""));
			Assert.assertEquals(borrowingAmountExpected.toString().replaceAll("[$,]",""), borrowingAmount.getText().replaceAll("[$,]",""));
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	@When("The user clicks start-over button")
	public void the_user_clicks_start_over_button() throws Exception {
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(0,350);", "");
		WebElement startOverButton = driver.findElement(By.xpath("(//button[@class='start-over']/span)[1]"));
		BusinessMethods.waitForVisibilityOfElement(driver, startOverButton);
		startOverButton.click();
		System.out.println("Clicked");

	}

	@Then("Borrowing estimate form is cleared")
	public void borrowing_estimate_form_is_cleared() {
		try {
			Assert.assertEquals("0",
					driver.findElement(By
							.xpath("//label[contains(text(),'Your income (before tax)')]/following-sibling::div/input"))
							.getAttribute("value"));
			Assert.assertEquals("0",
					driver.findElement(
							By.xpath("//label[contains(text(),'Your other income')]/following-sibling::div/input"))
							.getAttribute("value"));
			Assert.assertEquals("0",
					driver.findElement(
							By.xpath("//label[contains(text(),'Living expenses')]/following-sibling::div/input"))
							.getAttribute("value"));
			Assert.assertEquals("0",
					driver.findElement(By.xpath(
							"//label[contains(text(),'Current home loan repayments')]/following-sibling::div/input"))
							.getAttribute("value"));
			Assert.assertEquals("0",
					driver.findElement(
							By.xpath("//label[contains(text(),'Other loan repayments')]/following-sibling::div/input"))
							.getAttribute("value"));
			Assert.assertEquals("0",
					driver.findElement(
							By.xpath("//label[contains(text(),'Other commitments')]/following-sibling::div/input"))
							.getAttribute("value"));
			Assert.assertEquals("0",
					driver.findElement(By
							.xpath("//label[contains(text(),'Total credit card limits')]/following-sibling::div/input"))
							.getAttribute("value"));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@When("User enters only {string} for living expenses")
	public void user_enters_only_for_living_expenses(String livingExpensesAmount) {
		try {
			livingExpensesAmount = livingExpensesAmount.replace("$", "").trim();
			System.out.println(livingExpensesAmount);
			driver.findElement(By.xpath("//label[contains(text(),'Living expenses')]/following-sibling::div/input"))
					.click();
			driver.findElement(By.xpath("//label[contains(text(),'Living expenses')]/following-sibling::div/input"))
					.sendKeys(livingExpensesAmount);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@When("Clicks on Work out how much I could borrow button")
	public void clicks_on_Work_out_how_much_I_could_borrow_button()throws Exception{
		WebElement workOutButton = driver.findElement(By.xpath("//button[contains(text(),'Work out')]"));
		BusinessMethods.waitForVisibilityOfElement(driver, workOutButton);
		workOutButton.click();
	}

	@Then("Error message is displayed like {string}")
	public void error_message_is_displayed_like(String errorMessage) {
		try {
			System.out.println(errorMessage);
			String errorMessageActual = driver.findElement(By.xpath("//b[contains(text(),'1800 035 500')]/parent::span")).getText();
			System.out.println(errorMessageActual);
			Assert.assertEquals(errorMessage,errorMessageActual);
			driver.quit();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	

}
