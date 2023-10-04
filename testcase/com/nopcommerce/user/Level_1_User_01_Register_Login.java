package com.nopcommerce.user;

import org.testng.annotations.Test;

import commons.BasePage;

import org.testng.annotations.BeforeClass;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.server.handler.GetCurrentUrl;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Level_1_User_01_Register_Login extends BasePage {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	BasePage basePage = BasePage.getBasePageObject();
	int randNumber = new Random().nextInt(9999);

	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
//			System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		} else {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
//				System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver");
		}

		driver = new FirefoxDriver();
//		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://demo.nopcommerce.com/");
		
	}
	
	String registerLocator = "//a[@class='ico-register']";
	String loginLocator = "//a[@class='ico-login']";
	String registerButtonLocator = "//button[@id='register-button']";
	String loginButtonLocator = "//button[@class='button-1 login-button']";
	String firstNameErrorLocator = "//span[@id='FirstName-error']";
	String lastNameErrorLocator = "//span[@id='LastName-error']";
	String emailErrorLocator = "//span[@id='Email-error']";
	String passwordErrorLocator = "//span[@id='Password-error']";
	String confirmPasswordErrorLocator = "//span[@id='ConfirmPassword-error']";
	String firstName = "Le Nguyen + " + randNumber;
	String lastName = "Nguyen";
	String emailAddress = "ngthile5625+" + randNumber +"@gmail.com";
	String password = "Abcd1234";
	String firstNameLocator = "//input[@id='FirstName']";
	String lastNameLocator = "//input[@id='LastName']";
	String emailLocator = "//input[@id='Email']";
	String passwordLocator = "//input[@id='Password']";
	String confirmPasswordLocator = "//input[@id='ConfirmPassword']";
	

	@Test
	public void TC_01_Register_Empty_Data() {
		waitForElementClickable(driver, registerLocator);
		clickToElement(driver, registerLocator);
		waitForElementClickable(driver, registerButtonLocator);
		clickToElement(driver, registerButtonLocator);
		
		Assert.assertEquals(getElementText(driver, firstNameErrorLocator), "First name is required.");
		Assert.assertEquals(getElementText(driver, lastNameErrorLocator), "Last name is required.");
		Assert.assertEquals(getElementText(driver, emailErrorLocator), "Email is required.");
		Assert.assertEquals(getElementText(driver, passwordErrorLocator), "Password is required.");
		Assert.assertEquals(getElementText(driver, confirmPasswordErrorLocator), "Password is required.");
	}
	@Test
	public void TC_02_Register_Wrong_Email() {
		waitForElementClickable(driver, registerLocator);
		clickToElement(driver, registerLocator);
		
		sendkeyToElement(driver, firstNameLocator, firstName);
		sendkeyToElement(driver, lastNameLocator, lastName);
		sendkeyToElement(driver, emailLocator, "1234567@g");
		sendkeyToElement(driver, passwordLocator, password);
		sendkeyToElement(driver, confirmPasswordLocator, password);
		
		waitForElementClickable(driver, registerButtonLocator);
		clickToElement(driver, registerButtonLocator);
		
		
		Assert.assertEquals(getElementText(driver,"//div[contains(@class,'message-error')]//li" ), "Wrong email");
	}
	@Test
	public void TC_03_Register_ShortPass() {
		waitForElementClickable(driver, registerLocator);
		clickToElement(driver, registerLocator);
		
		sendkeyToElement(driver, firstNameLocator, firstName);
		sendkeyToElement(driver, lastNameLocator, lastName);
		sendkeyToElement(driver, emailLocator, emailAddress);
		sendkeyToElement(driver, passwordLocator, "123");
		sendkeyToElement(driver, confirmPasswordLocator, "123");
		
		waitForElementClickable(driver, registerButtonLocator);
		clickToElement(driver, registerButtonLocator);
		
		Assert.assertEquals(getElementText(driver, passwordErrorLocator), "Password must meet the following rules:\nmust have at least 6 characters");
	}
	@Test
	public void TC_04_Register_Pass_ConfirmPass_Differ() {
		waitForElementClickable(driver, registerLocator);
		clickToElement(driver, registerLocator);
		
		sendkeyToElement(driver, firstNameLocator, firstName);
		sendkeyToElement(driver, lastNameLocator, lastName);
		sendkeyToElement(driver, emailLocator, emailAddress);
		sendkeyToElement(driver, passwordLocator, password);
		sendkeyToElement(driver, confirmPasswordLocator, "1234");
		
		waitForElementClickable(driver, registerButtonLocator);
		clickToElement(driver, registerButtonLocator);
		
		Assert.assertEquals(getElementText(driver, confirmPasswordErrorLocator), "The password and confirmation password do not match.");
	}
	@Test
	public void TC_05_Register_Success() {
		waitForElementClickable(driver, registerLocator);
		clickToElement(driver, registerLocator);
		
		sendkeyToElement(driver, firstNameLocator, firstName);
		sendkeyToElement(driver, lastNameLocator, lastName);
		sendkeyToElement(driver, emailLocator, emailAddress);
		sendkeyToElement(driver, passwordLocator, password);
		sendkeyToElement(driver, confirmPasswordLocator, password);
		
		waitForElementClickable(driver, registerButtonLocator);
		clickToElement(driver, registerButtonLocator);
		
		Assert.assertEquals(getElementText(driver, "//div[@class='result']"), "Your registration completed");
		
	}
	@Test
	public void TC_06_Register_With_Existing_Email() {
		waitForElementClickable(driver, registerLocator);
		clickToElement(driver, registerLocator);
		
		sendkeyToElement(driver, firstNameLocator, firstName);
		sendkeyToElement(driver, lastNameLocator, lastName);
		sendkeyToElement(driver, emailLocator, emailAddress);
		sendkeyToElement(driver, passwordLocator, password);
		sendkeyToElement(driver, confirmPasswordLocator, password);
		
		waitForElementClickable(driver, registerButtonLocator);
		clickToElement(driver, registerButtonLocator);
		
		Assert.assertEquals(getElementText(driver, "//div[contains(@class,'message-error')]//li"), "The specified email already exists");
		
	}	
	@Test
	public void TC_07_Login_Empty_Data() {
		waitForElementClickable(driver, loginLocator);
		clickToElement(driver, loginLocator);
		
		waitForElementClickable(driver, loginButtonLocator);
		clickToElement(driver, loginButtonLocator);
		
		Assert.assertEquals(getElementText(driver, emailErrorLocator), "Please enter your email");
		
	}
	@Test
	public void TC_08_Login_With_Wrong_Email() {
		Actions action=new Actions(driver);
		waitForElementClickable(driver, loginLocator);
		clickToElement(driver, loginLocator);
		sendkeyToElement(driver,emailLocator , "12345@");
		action.sendKeys(Keys.ENTER).perform();
		Assert.assertEquals(getElementText(driver, emailErrorLocator), "Wrong email");
	}
	@Test
	public void TC_09_Login_With_Unregister_Email() {
		waitForElementClickable(driver, loginLocator);
		clickToElement(driver, loginLocator);
		sendkeyToElement(driver, emailLocator, "pvtan123@gmail.com");
		sendkeyToElement(driver,passwordLocator , password);
		clickToElement(driver, loginButtonLocator);
		Assert.assertEquals(getElementText(driver, "//div[contains(@class,'message-error')]"), "Login was unsuccessful. Please correct the errors and try again.\nNo customer account found");
	}
	@Test
	public void TC_10_Login_With_Empty_Pass() {
		waitForElementClickable(driver, loginLocator);
		clickToElement(driver, loginLocator);
		sendkeyToElement(driver, emailLocator, emailAddress);
		clickToElement(driver, loginButtonLocator);
		Assert.assertEquals(getElementText(driver, "//div[contains(@class,'message-error')]"), "Login was unsuccessful. Please correct the errors and try again.\nThe credentials provided are incorrect");
	}
	@Test
	public void TC_11_Login_With_Wrong_Pass() {
		waitForElementClickable(driver, loginLocator);
		clickToElement(driver, loginLocator);
		sendkeyToElement(driver, emailLocator, emailAddress);
		sendkeyToElement(driver, passwordLocator, "12345678");
		clickToElement(driver, loginButtonLocator);
		Assert.assertEquals(getElementText(driver, "//div[contains(@class,'message-error')]"), "Login was unsuccessful. Please correct the errors and try again.\nThe credentials provided are incorrect");
	}
	@Test
	public void TC_12_Login_Sucess() {
		waitForElementClickable(driver, loginLocator);
		clickToElement(driver, loginLocator);
		sendkeyToElement(driver, emailLocator, emailAddress);
		sendkeyToElement(driver, passwordLocator, password);
		clickToElement(driver, loginButtonLocator);
		areJQueryAndJSLoadedSuccess(driver);
		Assert.assertEquals(getCurentUrl(driver),"https://demo.nopcommerce.com/");
	}
	
	
	
	

	@AfterClass
     public void afterClass() {
	}

}
