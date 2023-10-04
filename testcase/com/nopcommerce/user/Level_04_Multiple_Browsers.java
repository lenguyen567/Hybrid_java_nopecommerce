package com.nopcommerce.user;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BasePage;
import commons.BaseTest;
import pageObjects.HomePageObjects;
import pageObjects.LoginPageObjects;
import pageObjects.RegisterPageObjects;

public class Level_04_Multiple_Browsers extends BaseTest {
	WebDriver driver;
	HomePageObjects homePage;
	LoginPageObjects loginPage;
	RegisterPageObjects registerPage;
	private int randNumber = new Random().nextInt(9999);
	private String firstName = "Le Nguyen + " + randNumber;
	private String lastName = "Nguyen";
	private String emailAddress = "ngthile5625+" + randNumber + "@gmail.com";
	private String password = "Abcd1234";

	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = getBrowserDriver(browserName);

		driver.get("https://demo.nopcommerce.com/");
		homePage = new HomePageObjects(driver);

		System.out.println("Pre-condition - Step 1 : Click to Register Link ");
		homePage.clickToRegisterLink();
		registerPage = new RegisterPageObjects(driver);
		System.out.println("Pre-condition - Step 2 : Input the required fields ");
		registerPage.inputToFirstNameTextbox(firstName);
		registerPage.inputToLastNameTextbox(lastName);
		registerPage.inputToEmailTextbox(emailAddress);
		registerPage.inputToPasswordTextbox(password);
		registerPage.inputToConfirmPassTextbox(password);
		System.out.println("Pre-condition - Step 3 : Click to Register Button ");
		registerPage.clickToRegisterButton();
		System.out.println("Pre-condition - Step 4 : Verify the Success Message");
		Assert.assertEquals(registerPage.getSuccessfulRegisterMessage(), "Your registration completed");
		if (homePage.checkMyAccountLinkNotDisplayed()) {
			System.out.println("Pre-condition - Step 5 : Click log out if any");
			registerPage.clickToLogOutLink();
		}
	}

//	@Test
	public void Login_01_Empty_Data() {
		homePage.clickToLoginLink();
		loginPage = new LoginPageObjects(driver);
		loginPage.clickToLoginButton();
		Assert.assertEquals(loginPage.getErrorMessageAtEmailTextbox().trim(), "Please enter your email");
	}

//	@Test
	public void Login_02_Invalid_Email() {
		homePage.clickToLoginLink();
		loginPage = new LoginPageObjects(driver);
		loginPage = new LoginPageObjects(driver);
		loginPage.inputToEmailTextbox("12345@55");
		loginPage.inputToPasswordTextbox(password);
		loginPage.clickToLoginButton();
		Assert.assertEquals(loginPage.getErrorMessage().trim(),
				"Login was unsuccessful. Please correct the errors and try again.\nNo customer account found");
	}

//	@Test
	public void Login_03_Unregister_Email() {
		homePage.clickToLoginLink();
		loginPage = new LoginPageObjects(driver);
		loginPage.inputToEmailTextbox("pvtan123@gmail.com");
		loginPage.inputToPasswordTextbox(password);
		loginPage.clickToLoginButton();
		Assert.assertEquals(loginPage.getErrorMessage().trim(),
				"Login was unsuccessful. Please correct the errors and try again.\nNo customer account found");
	}

//	@Test
	public void Login_04_Empty_Pass() {
		homePage.clickToLoginLink();
		loginPage = new LoginPageObjects(driver);
		loginPage.inputToEmailTextbox(emailAddress);
		loginPage.clickToLoginButton();
		Assert.assertEquals(loginPage.getErrorMessage().trim(),
				"Login was unsuccessful. Please correct the errors and try again.\nThe credentials provided are incorrect");
	}

	@Test
	public void Login_05_Wrong_Pass() {
		homePage.clickToLoginLink();
		loginPage = new LoginPageObjects(driver);
		loginPage.inputToEmailTextbox(emailAddress);
		loginPage.inputToPasswordTextbox("123456789");
		loginPage.clickToLoginButton();
		Assert.assertEquals(loginPage.getErrorMessage().trim(),
				"Login was unsuccessful. Please correct the errors and try again.\nThe credentials provided are incorrect");

	}

	@Test
	public void Login_06_Sucess() {
		homePage.clickToLoginLink();
		loginPage = new LoginPageObjects(driver);
		loginPage.inputToEmailTextbox(emailAddress);
		loginPage.inputToPasswordTextbox(password);
		loginPage.clickToLoginButton();
		Assert.assertTrue(homePage.isMyAccountLinkDisplayed());
		homePage.clickToMyAccountLink();
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
