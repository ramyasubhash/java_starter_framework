package pages;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.time.Duration;

import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
	
	public static WebDriver driver;
	public FluentWait<WebDriver> waiter;

    public BasePage(WebDriver driver) {
        BasePage.driver = driver;
        waiter = new FluentWait<WebDriver>(driver)
				.ignoring(ElementClickInterceptedException.class, WebDriverException.class)
				.withTimeout(Duration.ofSeconds(30)).pollingEvery(Duration.ofSeconds(5));
    }
	
	public void WaitForElement(WebElement element) {

		WebDriverWait wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.visibilityOf(element));

	}

	public void WaitForElementToBeClickable(WebElement element) {

		WebDriverWait wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.elementToBeClickable(element));

	}
	
	public void sendKeys(WebElement element, String inputtext) {
		element.sendKeys(inputtext);
	}

	public void clickEnter(WebElement element) {
		element.sendKeys(Keys.ENTER);
	}

	public void clickEnter() throws AWTException {
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_ENTER);

	}

	public void clickTab(WebElement element) {
		element.sendKeys(Keys.TAB);
	}

}

