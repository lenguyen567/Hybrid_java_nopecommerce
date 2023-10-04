package com.nopcommerce.user;

import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import pageFactoryObjects.HomePageObjectsFactory;
import pageFactoryObjects.RegisterPageObjectsFactory;
import pageObjects.LoginPageObjects;
import pageObjects.RegisterPageObjects;

public class Level_5_Page_Factory extends BaseTest {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	RegisterPageObjectsFactory registerPage;
	HomePageObjectsFactory homePage;
	private int randNumber = new Random().nextInt(9999);
	private String firstName = "Le Nguyen + " + randNumber;
	private String lastName = "Nguyen";
	private String emailAddress = "ngthile5625+" + randNumber + "@gmail.com";
	private String password = "Abcd1234";

	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		driver=getBrowserDriver(browserName);
		driver.get("https://demo.nopcommerce.com/");
		
		homePage = new HomePageObjectsFactory(driver);
	}

	@Test
	public void TC_05_Register_Success() {
		homePage.clickToRegisterLink();
		registerPage = new RegisterPageObjectsFactory(driver);
		registerPage.inputToFirstNameTextbox(firstName);
		registerPage.inputToLastNameTextbox(lastName);
		registerPage.inputToEmailTextbox(emailAddress);
		registerPage.inputToPasswordTextbox(password);
		registerPage.inputToConfirmPassTextbox(password);
		registerPage.clickToRegisterButton();
		Assert.assertEquals(registerPage.getSuccessfulRegisterMessage(), "Your registration completed");
	}

	@AfterClass
	public void afterClass() {
	}

}
