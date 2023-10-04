package pageFactoryObjects;

import javax.xml.xpath.XPath;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import commons.BasePage;
import commons.BasePageFactory;
import pageUIs.HomePageUI;
import pageUIs.RegisterPageUI;

public class RegisterPageObjectsFactory extends BasePageFactory {
	private WebDriver driver;

	public RegisterPageObjectsFactory(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "FirstName-error")
	WebElement firstNameErrorMessage;

	@FindBy(id = "LastName-error")
	WebElement lastNameErrorMessage;
	@FindBy(id = "Email-error")
	WebElement emailErrorMessage;
	@FindBy(id = "Password-error")
	WebElement passwordErrorMessage;
	@FindBy(id = "ConfirmPassword-error")
	WebElement confirmPasswordErrorMessage;
	@FindBy(id = "FirstName")
	WebElement firstNameTextbox;
	@FindBy(id = "LastName")
	WebElement lastNameTextbox;
	@FindBy(id = "Email")
	WebElement emailTextbox;
	@FindBy(id = "Password")
	WebElement passwordTextbox;
	@FindBy(id = "ConfirmPassword")
	WebElement confirmPasswordTextbox;
	@FindBy(id = "register-button")
	WebElement registerButton;
	@FindBy(xpath = "//div[@class='result']")
	WebElement registerSuccessMesssage;

	public void inputToFirstNameTextbox(String firstName) {
		waitForElementVisible(driver, firstNameTextbox);
		sendkeyToElement(driver, firstNameTextbox, firstName);
	}

	public void inputToLastNameTextbox(String lastName) {
		waitForElementVisible(driver, lastNameTextbox);
		sendkeyToElement(driver, lastNameTextbox, lastName);
	}

	public void inputToEmailTextbox(String emailAddress) {
		waitForElementVisible(driver, emailTextbox);
		sendkeyToElement(driver, emailTextbox, emailAddress);
	}

	public void inputToPasswordTextbox(String password) {
		waitForElementVisible(driver, passwordTextbox);
		sendkeyToElement(driver, passwordTextbox, password);
	}

	public void inputToConfirmPassTextbox(String confirmPassword) {
		waitForElementVisible(driver, confirmPasswordTextbox);
		sendkeyToElement(driver, confirmPasswordTextbox, confirmPassword);
	}

	public String getSuccessfulRegisterMessage() {
		waitForElementVisible(driver, registerSuccessMesssage);
		return getElementText(driver, registerSuccessMesssage);
	}

	public void clickToRegisterButton() {
		clickToElement(driver, registerButton);
	}

}
