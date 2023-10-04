package com.nopcommerce.user;

import org.testng.annotations.Test;

import commons.BasePage;
import pageObjects.HomePageObjects;
import pageObjects.LoginPageObjects;
import pageObjects.RegisterPageObjects;
import pageUIs.RegisterPageUI;

import org.testng.annotations.BeforeClass;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.server.handler.GetCurrentUrl;
import org.openqa.selenium.safari.SafariTechPreviewDriverInfo;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Level_3_User_01_Register extends BasePage {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	RegisterPageObjects registerPage;
	HomePageObjects homePage;
	LoginPageObjects loginPage;
	private int randNumber = new Random().nextInt(9999);
	private String firstName = "Le Nguyen + " + randNumber;
	private String lastName = "Nguyen";
	private String emailAddress = "ngthile5625+" + randNumber + "@gmail.com";
	private String password = "Abcd1234";

	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
//			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
			System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		} else {
//			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
				System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver");
		}

//		driver = new FirefoxDriver();
		driver = new ChromeDriver();
		registerPage = new RegisterPageObjects(driver);
		homePage = new HomePageObjects(driver);
		loginPage = new LoginPageObjects(driver);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://demo.nopcommerce.com/");

	}

	@Test
	public void Register_01_Empty_Data() {
		System.out.println("Register_01 - Step 01: Click to the register link");
		homePage.clickToRegisterLink();
		System.out.println("Register_02 - Step 02: Click to the register button");
		registerPage.clickToRegisterButton();
		System.out.println("Register_02 - Step 03: Verify the error message");
		Assert.assertEquals(registerPage.getErrorMessageAtFirstNameTextbox(), "First name is required.");
		Assert.assertEquals(registerPage.getErrorMessageAtLastNameTextbox(), "Last name is required.");
		Assert.assertEquals(registerPage.getErrorMessageAtEmailTextbox(), "Email is required.");
		Assert.assertEquals(registerPage.getErrorMessageAtPasswordTextbox(), "Password is required.");
		Assert.assertEquals(registerPage.getErrorMessageAtConfirmPasswordTextbox(), "Password is required.");
	}

	@Test
	public void Register_02_Wrong_Email() {
		System.out.println("Register_02 - Step 01: Click to the register link");
		homePage.clickToRegisterLink();
		System.out.println("Register_02 - Step 02: Input the require field");
		registerPage.inputToFirstNameTextbox(firstName);
		registerPage.inputToLastNameTextbox(lastName);
		registerPage.inputToEmailTextbox("123456@5667");
		registerPage.inputToPasswordTextbox(password);
		registerPage.inputToConfirmPassTextbox(password);
		System.out.println("Register_02 - Step 03: Click to the register button");
		registerPage.clickToRegisterButton();
		Assert.assertEquals(getElementText(driver, "//div[contains(@class,'message-error')]//li"), "Wrong email");

	}

	@Test
	public void TC_03_Register_ShortPass() {
		homePage.clickToRegisterLink();
		registerPage.inputToFirstNameTextbox(firstName);
		registerPage.inputToLastNameTextbox(lastName);
		registerPage.inputToEmailTextbox(emailAddress);
		registerPage.inputToPasswordTextbox("12345");
		registerPage.inputToConfirmPassTextbox("12345");
		registerPage.clickToRegisterButton();
		Assert.assertEquals(registerPage.getErrorMessageAtPasswordTextbox(),
				"Password must meet the following rules:\nmust have at least 6 characters");
	}

	@Test
	public void TC_04_Register_Pass_ConfirmPass_Differ() {
		homePage.clickToRegisterLink();
		registerPage.inputToFirstNameTextbox(firstName);
		registerPage.inputToLastNameTextbox(lastName);
		registerPage.inputToEmailTextbox(emailAddress);
		registerPage.inputToPasswordTextbox(password);
		registerPage.inputToConfirmPassTextbox("12345");
		registerPage.clickToRegisterButton();
		Assert.assertEquals(registerPage.getErrorMessageAtConfirmPasswordTextbox(),
				"The password and confirmation password do not match.");
	}

	@Test
	public void TC_05_Register_Success() {
		homePage.clickToRegisterLink();
		registerPage.inputToFirstNameTextbox(firstName);
		registerPage.inputToLastNameTextbox(lastName);
		registerPage.inputToEmailTextbox(emailAddress);
		registerPage.inputToPasswordTextbox(password);
		registerPage.inputToConfirmPassTextbox(password);
		registerPage.clickToRegisterButton();
		Assert.assertEquals(registerPage.getSuccessfulRegisterMessage(), "Your registration completed");
	}

	@Test
	public void TC_06_Register_With_Existing_Email() {
		if (getElementText(driver, "//div[@class='header-links']//li[1]/a").equals("My account")) {
			registerPage.clickToLogOutLink();
		}
		homePage.clickToRegisterLink();
		registerPage.inputToFirstNameTextbox(firstName);
		registerPage.inputToLastNameTextbox(lastName);
		registerPage.inputToEmailTextbox(emailAddress);
		registerPage.inputToPasswordTextbox(password);
		registerPage.inputToConfirmPassTextbox(password);
		registerPage.clickToRegisterButton();
		Assert.assertEquals(registerPage.getErrorExistingEmailMessage(), "The specified email already exists");
	}

	@Test
	public void TC_07_Login_Empty_Data() {
		homePage.clickToLoginLink();
		loginPage.clickToLoginButton();
		Assert.assertEquals(loginPage.getErrorMessageAtEmailTextbox(), "Please enter your email");
	}

	@Test
	public void TC_08_Login_With_Wrong_Email() {
		homePage.clickToLoginLink();
		loginPage.inputToEmailTextbox("12345@55");
		loginPage.inputToPasswordTextbox(password);
		loginPage.clickToLoginButton();
		Assert.assertEquals(loginPage.getErrorMessage(),
				"Login was unsuccessful. Please correct the errors and try again.\nNo customer account found");

	}

	@Test
	public void TC_09_Login_With_Unregister_Email() {
		homePage.clickToLoginLink();
		loginPage.inputToEmailTextbox("pvtan123@gmail.com");
		loginPage.inputToPasswordTextbox(password);
		loginPage.clickToLoginButton();
		Assert.assertEquals(loginPage.getErrorMessage(),
				"Login was unsuccessful. Please correct the errors and try again.\nNo customer account found");
	}

	@Test
	public void TC_10_Login_With_Empty_Pass() {
		homePage.clickToLoginLink();
		loginPage.inputToEmailTextbox(emailAddress);
		loginPage.clickToLoginButton();
		Assert.assertEquals(loginPage.getErrorMessage(),
				"Login was unsuccessful. Please correct the errors and try again.\nThe credentials provided are incorrect");
	}

	@Test
	public void TC_11_Login_With_Wrong_Pass() {
		homePage.clickToLoginLink();
		loginPage.inputToEmailTextbox(emailAddress);
		loginPage.inputToPasswordTextbox("123456789");
		loginPage.clickToLoginButton();
		Assert.assertEquals(loginPage.getErrorMessage(),
				"Login was unsuccessful. Please correct the errors and try again.\nThe credentials provided are incorrect");

	}

	@Test
	public void TC_12_Login_Sucess() {
		homePage.clickToLoginLink();
		loginPage.inputToEmailTextbox(emailAddress);
		loginPage.inputToPasswordTextbox(password);
		loginPage.clickToLoginButton();
		areJQueryAndJSLoadedSuccess(driver);
		Assert.assertEquals(getCurentUrl(driver), "https://demo.nopcommerce.com/");
	}

	@AfterClass
	public void afterClass() {
	}

}
