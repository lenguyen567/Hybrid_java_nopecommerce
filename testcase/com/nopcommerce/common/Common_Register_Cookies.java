package com.nopcommerce.common;

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
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.hc.core5.http.message.StatusLine.StatusClass;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.server.handler.GetCurrentUrl;
import org.openqa.selenium.safari.SafariTechPreviewDriverInfo;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;

public class Common_Register_Cookies extends BaseTest {
	public  WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	RegisterPageObjects registerPage;
	public  HomePageObjects homePage;
	LoginPageObjects loginPage;
	private int randNumber = new Random().nextInt(9999);
	private String firstName = "Le Nguyen + " + randNumber;
	private String lastName = "Nguyen";
	private String emailAddress = "ngthile5625+" + randNumber + "@gmail.com";
	private String password = "Abcd1234";
	public static Set<Cookie> loginCookies;

	@Parameters("browser")
	@BeforeTest
	public void beforeClass(String browserName) {
		driver = getBrowserDriver(browserName);
		driver.get("https://demo.nopcommerce.com/");
		homePage = PageGeneratorManager.getHomePage(driver);
		homePage.clickToRegisterLink();
		registerPage = PageGeneratorManager.getRegisterPage(driver);
		registerPage.inputToFirstNameTextbox(firstName);
		registerPage.inputToLastNameTextbox(lastName);
		registerPage.inputToEmailTextbox(emailAddress);
		registerPage.inputToPasswordTextbox(password);
		registerPage.inputToConfirmPassTextbox(password);
		registerPage.clickToRegisterButton();
		homePage = registerPage.clickToLogOutLink();
		loginPage = homePage.clickToLoginLink();
		loginPage.inputToEmailTextbox(emailAddress);
		loginPage.inputToPasswordTextbox(password);
		homePage = loginPage.clickToLoginButton();
		loginCookies = homePage.getAllCookies(driver);
		driver.quit();

	}

	@AfterTest
	public void afterTest() {

	}

}
