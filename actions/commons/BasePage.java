package commons;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.server.handler.GetAllCookies;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.github.dockerjava.api.model.Driver;

import pageObjects.AddressObjects;
import pageObjects.ChangePasswordObjects;
import pageObjects.CustomerInfoObjects;
import pageObjects.MyProductReviewObjects;
import pageObjects.OrderObjects;
import pageObjects.PageGeneratorManager;
import pageObjects.RewardPointObjects;
import pageUIs.BasePageUI;
import pageUIs.HomePageUI;

public class BasePage {
	private int longTime = 30;
	private int shortTime = 15;

	public static BasePage getBasePageObject() {
		return new BasePage();
	}

	protected Object jsExecutor(WebDriver driver, String javaScript, String xpathLocator) {
		return ((JavascriptExecutor) driver).executeScript(javaScript, getElement(driver, xpathLocator));
	}

	protected WebDriverWait explicitWaitLongTime(WebDriver driver) {
		return new WebDriverWait(driver, this.longTime);
	}

	protected WebDriverWait explicitWaitShortTime(WebDriver driver) {
		return new WebDriverWait(driver, this.shortTime);
	}

	protected void openPageUrl(WebDriver driver, String pageUrl) {
		driver.get(pageUrl);
	}

	protected String getPageTitle(WebDriver driver) {
		return driver.getTitle();
	}

	protected String getCurentUrl(WebDriver driver) {
		return driver.getCurrentUrl();
	}

	protected String getPageSource(WebDriver driver) {
		return driver.getPageSource();
	}

	protected void backToPage(WebDriver driver) {
		driver.navigate().back();
	}

	protected void forwardToPage(WebDriver driver) {
		driver.navigate().forward();
	}

	protected void refreshCurrentPage(WebDriver driver) {
		driver.navigate().refresh();
	}

	protected Alert waitForAlertPresence(WebDriver driver) {
//		WebDriverWait explicitWait = new WebDriverWait(driver, longTime);
		return explicitWaitLongTime(driver).until(ExpectedConditions.alertIsPresent());
	}

	protected void acceptAlert(WebDriver driver) {
		waitForAlertPresence(driver).accept();
	}

	protected void cancelAlert(WebDriver driver) {
		waitForAlertPresence(driver).dismiss();
	}

	protected String getAlertText(WebDriver driver) {
		return waitForAlertPresence(driver).getText();
	}

	protected void sendkeyToAlert(WebDriver driver, String textValue) {
		waitForAlertPresence(driver).sendKeys(textValue);
	}

	protected void switchToWindowByID(WebDriver driver, String parentID) {
		Set<String> allWindows = driver.getWindowHandles();
		for (String runWindow : allWindows) {
			if (!runWindow.equals(parentID)) {
				driver.switchTo().window(runWindow);
				break;
			}
		}
	}

	protected void switchToWindowByTitle(WebDriver driver, String parentPageTitle) {
		Set<String> allWindows = driver.getWindowHandles();
		for (String runWindow : allWindows) {
			driver.switchTo().window(runWindow);
			if (driver.getTitle().equals(parentPageTitle)) {
				break;
			}
		}
	}

	protected void closeWindowsWithoutParent(WebDriver driver, String parentID) {
		Set<String> allWindows = driver.getWindowHandles();
		for (String runWindow : allWindows) {
			if (!runWindow.equals(parentID)) {
				driver.switchTo().window(runWindow);
				driver.close();
			}
		}
		driver.switchTo().window(parentID);
	}

	private By getByXpath(String xpathLocator) {
		return By.xpath(xpathLocator);
	}

	protected WebElement getElement(WebDriver driver, String xpathLocator) {
		return driver.findElement(getByXpath(xpathLocator));
	}

	protected List<WebElement> getListElements(WebDriver driver, String xpathLocator) {
		return driver.findElements(getByXpath(xpathLocator));
	}

	protected void clickToElement(WebDriver driver, String xpathLocator) {
		getElement(driver, xpathLocator).click();
	}

	protected void sendkeyToElement(WebDriver driver, String xpathLocator, String textValue) {
		WebElement element = getElement(driver, xpathLocator);
		element.clear();
		element.sendKeys(textValue);
	}

	protected String getElementText(WebDriver driver, String xpathLocator) {
		return getElement(driver, xpathLocator).getText();
	}

	protected void selectItemInDefaultDropdown(WebDriver driver, String xpathLocator, String textItem) {
		new Select(getElement(driver, xpathLocator)).selectByVisibleText(textItem);
	}

	protected String getSelectedItemInDropdownString(WebDriver driver, String xpathLocator) {
		return new Select(getElement(driver, xpathLocator)).getFirstSelectedOption().getText();
	}

	protected Boolean isDropdownMultiple(WebDriver driver, String xpathLocator) {
		return new Select(getElement(driver, xpathLocator)).isMultiple();
	}

	protected void selectIteminCustomDropdown(WebDriver driver, String xpathLocator, String expectedItem) {
		getElement(driver, xpathLocator).click();
		sleepInsecond(2);
		List<WebElement> allDropdownListItems = explicitWaitLongTime(driver)
				.until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByXpath(xpathLocator)));

		for (WebElement item : allDropdownListItems) {
			if (item.getText().trim().equals(expectedItem)) {
				JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
				jsExecutor.executeScript("arguments[0].scrollIntoView(false);", item);
				sleepInsecond(2);
				item.click();
				sleepInsecond(2);
				break;
			}

		}
	}

	protected String getAttributeValue(WebDriver driver, String xpathLocator, String attributeName) {
		return getElement(driver, xpathLocator).getAttribute(attributeName);
	}

	protected String getElementCssValue(WebDriver driver, String xpathLocator, String propertyName) {
		return getElement(driver, xpathLocator).getCssValue(propertyName);
	}

	protected String convertRgbaToHex(String rgbaValue) {
		return Color.fromString(rgbaValue).asHex();
	}

	protected int GetElementSize(WebDriver driver, String xpathLocator) {
		return getListElements(driver, xpathLocator).size();
	}

	protected void checkToClickDefaultRadioCheckbox(WebDriver driver, String xpathLocator) {
		WebElement element = getElement(driver, xpathLocator);
		if (!element.isSelected()) {
			element.click();
		}
	}

	protected void uncheckToClickDefaultCheckbox(WebDriver driver, String xpathLocator) {
		WebElement element = getElement(driver, xpathLocator);
		if (element.isSelected()) {
			element.click();
		}
	}

	protected void checkToClickCustomRadioCheckbox(WebDriver driver, String xpathLocator) {
		WebElement element = getElement(driver, xpathLocator);
		if (!element.isSelected()) {
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
		}
	}

	protected Boolean isElementDisplayed(WebDriver driver, String xpathLocator) {
		return getElement(driver, xpathLocator).isDisplayed();
	}

	protected Boolean isElementEnabled(WebDriver driver, String xpathLocator) {
		return getElement(driver, xpathLocator).isEnabled();
	}

	protected Boolean isElementSelected(WebDriver driver, String xpathLocator) {
		return getElement(driver, xpathLocator).isSelected();
	}

	protected void switchToFrameIframe(WebDriver driver, String xpathLocator) {
		driver.switchTo().frame(getElement(driver, xpathLocator));
	}

	protected void switchToDefaultContent(WebDriver driver) {
		driver.switchTo().defaultContent();
	}

	protected void hoverMouseToElement(WebDriver driver, String xpathLocator) {
		Actions action = new Actions(driver);
		action.moveToElement(getElement(driver, xpathLocator)).perform();
	}

	protected void scrollToBottomPage(WebDriver driver) {
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,document.body.scrollHeight)");

	}

	protected void highlightElement(WebDriver driver, String xpathLocator) {
		WebElement element = getElement(driver, xpathLocator);
		String originalStyle = element.getAttribute("style");
		((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element,
				"style", "border: 2px solid red; border-style: dashed;");
		sleepInsecond(1);
		((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element,
				"style", originalStyle);
	}

	protected void clickToElementByJS(WebDriver driver, String xpathLocator) {
		jsExecutor(driver, "arguments[0].click();", xpathLocator);
	}

	protected void scrollToElement(WebDriver driver, String xpathLocator) {
		jsExecutor(driver, "arguments[0].scrollIntoView(true);", xpathLocator);
	}

	protected void removeAttributeInDOM(WebDriver driver, String xpathLocator, String attributeRemove) {
		((JavascriptExecutor) driver).executeScript("arguments[0].removeAttribute('" + attributeRemove + "');",
				getElement(driver, xpathLocator));
	}

	protected boolean areJQueryAndJSLoadedSuccess(WebDriver driver) {

		ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				try {
					return ((Boolean) ((JavascriptExecutor) driver)
							.executeScript("return (window.jQuery !=null) && (jQuery.active==0);"));
				} catch (Exception e) {
					return true;
				}
			}
		};

		ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString()
						.equals("complete");
			}
		};

		return explicitWaitLongTime(driver).until(jQueryLoad) && explicitWaitLongTime(driver).until(jsLoad);
	}

	protected String valiationMessage(WebDriver driver, String xpathLocator) {
		return (String) ((JavascriptExecutor) driver).executeScript("return arguments[0].validationMessage",
				getElement(driver, xpathLocator));
	}

	protected Boolean isImageLoaded(WebDriver driver, String xpathLocator) {
		return (Boolean) ((JavascriptExecutor) driver).executeScript(
				"return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0",
				getElement(driver, xpathLocator));
	}

	protected void waitForElementVisible(WebDriver driver, String xpathLocator) {
		explicitWaitLongTime(driver).until(ExpectedConditions.visibilityOfElementLocated(getByXpath(xpathLocator)));
	}

	protected void waitAllForElementVisible(WebDriver driver, String xpathLocator) {
		explicitWaitLongTime(driver)
				.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByXpath(xpathLocator)));
	}

	protected void waitForElementInvisible(WebDriver driver, String xpathLocator) {
		explicitWaitLongTime(driver).until(ExpectedConditions.invisibilityOfElementLocated(getByXpath(xpathLocator)));
	}

	protected void waitAllForElementInvisible(WebDriver driver, String xpathLocator) {
		explicitWaitLongTime(driver)
				.until(ExpectedConditions.invisibilityOfAllElements(getListElements(driver, xpathLocator)));
	}

	protected void waitForElementClickable(WebDriver driver, String xpathLocator) {
		explicitWaitLongTime(driver).until(ExpectedConditions.elementToBeClickable(getByXpath(xpathLocator)));
	}

	protected List<String> itemListSorting(WebDriver driver, String xpathLocator) {
		List<String> itemList = new ArrayList<>();
		for (WebElement element : getListElements(driver, xpathLocator)) {
			itemList.add(element.getText());
		}
		return itemList;
	}

	

	public CustomerInfoObjects clickToCustomerInfoLink(WebDriver driver) {
		waitForElementClickable(driver, BasePageUI.CUSTOMER_INFO_LINK);
		clickToElement(driver, BasePageUI.CUSTOMER_INFO_LINK);
		return PageGeneratorManager.getCustomerInfoPage(driver);
	}

	public AddressObjects clickToAddressLink(WebDriver driver) {
		waitForElementClickable(driver, BasePageUI.ADDRESS_LINK);
		clickToElement(driver, BasePageUI.ADDRESS_LINK);
		return PageGeneratorManager.getAddressPage(driver);
	}

	public OrderObjects clickToOrderLink(WebDriver driver) {
		waitForElementClickable(driver, BasePageUI.ORDER_LINK);
		clickToElement(driver, BasePageUI.ORDER_LINK);
		return PageGeneratorManager.getOrderPage(driver);
	}

	public RewardPointObjects clickToRewardPointLink(WebDriver driver) {
		waitForElementClickable(driver, BasePageUI.REWARD_POINT_LINK);
		clickToElement(driver, BasePageUI.REWARD_POINT_LINK);
		return PageGeneratorManager.getRewardPointPage(driver);
	}

	public ChangePasswordObjects clickToChangePasswordLink(WebDriver driver) {
		waitForElementClickable(driver, BasePageUI.CHANGE_PASSWORD_LINK);
		clickToElement(driver, BasePageUI.CHANGE_PASSWORD_LINK);
		return PageGeneratorManager.getChangePasswordPage(driver);
	}

	public MyProductReviewObjects clickToMyProductReviewLink(WebDriver driver) {
		waitForElementClickable(driver, BasePageUI.MY_PRODUCT_REVIEWS_LINK);
		clickToElement(driver, BasePageUI.MY_PRODUCT_REVIEWS_LINK);
		return PageGeneratorManager.getMyProductReviewPage(driver);
	}
	
	public Set<Cookie> getAllCookies( WebDriver driver) {
		return driver.manage().getCookies();
		
	}
	
	public void setCookies ( WebDriver driver, Set<Cookie> cookies) {
		for (Cookie cookie : cookies) {
			driver.manage().addCookie(cookie);
		}
		sleepInsecond(5);
	}
	public void reloadCurrentPage(WebDriver driver) {
		driver.get(driver.getCurrentUrl());
	}

	private void sleepInsecond(long timeinSecond) {
		try {
			Thread.sleep(timeinSecond * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
