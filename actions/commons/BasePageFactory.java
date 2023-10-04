package commons;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePageFactory {
	private int longTime = 30;
	private int shortTime = 15;

	public static BasePageFactory getBasePageObject() {
		return new BasePageFactory();
	}

	protected Object jsExecutor(WebDriver driver, String javaScript, WebElement element) {
		return ((JavascriptExecutor) driver).executeScript(javaScript, element);
	}

	protected WebDriverWait explicitWaitLongTime(WebDriver driver) {
		return new WebDriverWait(driver, this.longTime);
	}

	protected WebDriverWait explicitWaitShortTime(WebDriver driver) {
		return new WebDriverWait(driver, this.shortTime);
	}

	protected void clickToElement(WebDriver driver, WebElement element) {
		element.click();
	}

	protected void sendkeyToElement(WebDriver driver, WebElement element, String textValue) {
		element.clear();
		element.sendKeys(textValue);
	}

	protected String getElementText(WebDriver driver, WebElement element) {
		return element.getText();
	}

	protected void waitForElementVisible(WebDriver driver, WebElement element) {
		explicitWaitLongTime(driver).until(ExpectedConditions.visibilityOf(element));
	}

	protected void waitForElementClickable(WebDriver driver, WebElement element) {
		explicitWaitLongTime(driver).until(ExpectedConditions.elementToBeClickable(element));
	}
	

}
