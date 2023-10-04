package com.nopcommerce.user;

import org.testng.annotations.Test;

import commons.BasePage;
import commons.BaseTest;
import pageObjects.CustomerInfoObjects;
import pageObjects.HomePageObjects;
import pageObjects.LoginPageObjects;
import pageObjects.PageGeneratorManager;
import pageObjects.PageGeneratorManager;
import pageObjects.RegisterPageObjects;
import pageUIs.RegisterPageUI;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

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

public class Level_7_Switch_Page_UI extends BaseTest {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	RegisterPageObjects registerPage;
	HomePageObjects homePage;
	LoginPageObjects loginPage;
	CustomerInfoObjects customerInfoPage;
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
		homePage = PageGeneratorManager.getHomePage(driver);		
	}

	
	@Test
	public void TC_01_Register_Success() {
		homePage.clickToRegisterLink();
		registerPage = PageGeneratorManager.getRegisterPage(driver);
		registerPage.inputToFirstNameTextbox(firstName);
		registerPage.inputToLastNameTextbox(lastName);
		registerPage.inputToEmailTextbox(emailAddress);
		registerPage.inputToPasswordTextbox(password);
		registerPage.inputToConfirmPassTextbox(password);
		registerPage.clickToRegisterButton();
		Assert.assertEquals(registerPage.getSuccessfulRegisterMessage(), "Your registration completed");
	}
	@Test
	public void TC_02_Login() {
		loginPage = homePage.clickToLoginLink();
		loginPage.inputToEmailTextbox(emailAddress);
		loginPage.inputToPasswordTextbox(password);
		homePage = loginPage.clickToLoginButton();
		Assert.assertFalse(homePage.checkMyAccountLinkNotDisplayed());
	}
	@Test
	public void TC_03_Customer_Info() {
	 	customerInfoPage = homePage.clickToMyAccountLink();
		
		
	}
	
	
	
	@AfterClass
	public void afterClass() {
//		driver.quit();
	}

}
