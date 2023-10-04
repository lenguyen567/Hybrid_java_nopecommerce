package com.nopcommerce.user;

import org.testng.annotations.Test;

import com.nopcommerce.common.Common_Register_Cookies;

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

public class Level_29_Sharing_login_Cookies extends BaseTest {
	private WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	RegisterPageObjects registerPage;
	HomePageObjects homePage;
	LoginPageObjects loginPage;
	CustomerInfoObjects customerInfoPage;
	

	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = getBrowserDriver(browserName);
		driver.get("https://demo.nopcommerce.com/");
		homePage = PageGeneratorManager.getHomePage(driver);		
	}

	
	
	@Test
	public void TC_03_Customer_Info() {
		homePage.setCookies(driver, Common_Register_Cookies.loginCookies);
		homePage.reloadCurrentPage(driver);
		
		
	}
	
	
	
	@AfterClass
	public void afterClass() {
//		driver.quit();
	}

}
