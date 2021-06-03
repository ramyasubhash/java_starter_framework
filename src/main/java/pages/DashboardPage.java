package pages;

import java.time.Duration;

import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.base.Function;

public class DashboardPage extends BasePage{

	
	public DashboardPage(WebDriver driver)
	{
		super(driver);
		PageFactory.initElements(driver, this);
	}
	
	Function<WebDriver, WebElement> function = new Function<WebDriver, WebElement>()
	 {
	 public WebElement apply(WebDriver arg0) {
	 System.out.println("Checking for the element!!");
	 WebElement element =dataMenu;
	 if(element != null)
	 {
	 System.out.println("Target element found");
	 }
	 return element;
	 }
	 };
	 
	 Function<WebDriver, WebElement> function1 = new Function<WebDriver, WebElement>()
	 {
	 public WebElement apply(WebDriver arg0) {
	 System.out.println("Checking for the element!!");
	 WebElement element =stopWordsMenu;
	 if(element != null)
	 {
	 System.out.println("Target element found");
	 }
	 return element;
	 }
	 };
	
	/**
	 * This section covers web elements definition 
	 */
	
	@FindBy(xpath="//h1[contains(text(), 'Dashboard')]")
	public WebElement dashboard;
	
		
	@FindBy(xpath="//ul[@class='nav']//li[2]")
	public WebElement dataMenu;
	
	@FindBy(xpath="//a[@href='/data/stop-words']")
	public WebElement stopWordsMenu;
	
	
	
	
	/**
	 * Navigation from Dashbord to Stopwords menu
	 */
	public void landOnStopWords() throws InterruptedException
	{
		Thread.sleep(3000);
		WaitForElementToBeClickable(dataMenu);
		try {
			waiter.until(function);
			dataMenu.click();
		} catch (ElementClickInterceptedException e) {
			// TODO Auto-generated catch block

		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		WaitForElementToBeClickable(stopWordsMenu);
		try {
			waiter.until(function1);
			stopWordsMenu.click();
		} catch (ElementClickInterceptedException e) {
			// TODO Auto-generated catch block

		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Thread.sleep(3000);
	}

}
