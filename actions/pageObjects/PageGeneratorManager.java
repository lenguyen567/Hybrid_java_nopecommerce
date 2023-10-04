package pageObjects;

import org.openqa.selenium.WebDriver;

public class PageGeneratorManager {
	public static RegisterPageObjects getRegisterPage(WebDriver driver) {
		return new RegisterPageObjects(driver);
	}

	public static LoginPageObjects getLoginPage(WebDriver driver) {
		return new LoginPageObjects(driver);
	}

	public static HomePageObjects getHomePage(WebDriver driver) {
		return new HomePageObjects(driver);
	}

	public static CustomerInfoObjects getCustomerInfoPage(WebDriver driver) {
		return new CustomerInfoObjects(driver);
	}

	public static AddressObjects getAddressPage(WebDriver driver) {
		return new AddressObjects(driver);
	}

	public static OrderObjects getOrderPage(WebDriver driver) {
		return new OrderObjects(driver);
	}

	public static RewardPointObjects getRewardPointPage(WebDriver driver) {
		return new RewardPointObjects(driver);
	}

	public static ChangePasswordObjects getChangePasswordPage(WebDriver driver) {
		return new ChangePasswordObjects(driver);
	}

	public static MyProductReviewObjects getMyProductReviewPage(WebDriver driver) {
		return new MyProductReviewObjects(driver);
	}

}
