package pages;

import java.time.Duration;

import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.base.Function;

public class LoginPage extends BasePage{
	

	public LoginPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
		

	} 
	
	
	
	/**
	 * This section covers web elements definition 
	 */
	
	@FindBy(xpath = "//h5[contains(text(), 'Login')]")
	public WebElement loginLayout;

	@FindBy(xpath = "//input[@id='Username']")
	public WebElement username;

	@FindBy(xpath = "//input[@id='Password']")
	public WebElement password;

	@FindBy(xpath = "//button[contains(text(), 'Log In')]")
	public WebElement loginButton;

	@FindBy(className = "dropdown")
	public WebElement dropdown;

	@FindBy(xpath = "//div[@class='profile-menu dropdown-menu show']//button[4]")
	public WebElement logout;

	@FindBy(xpath = "//a[contains(text(), 'Back to Log In')]")
	public WebElement backtologin;

	
	
	
	
	/**
	 * login function
	 */
	public void login(String inputUsername, String inputPassword) throws InterruptedException {
		Thread.sleep(3000);
		WaitForElement(username);
		sendKeys(username, inputUsername);
		sendKeys(password, inputPassword);
		loginButton.click();
		Thread.sleep(3000);
	}
	
	/**
	 * logout function
	 */
	public void logout() throws InterruptedException {
		Thread.sleep(3000);
		WaitForElementToBeClickable(dropdown);
		try {
			dropdown.click();
		} catch (ElementClickInterceptedException e) {
			// TODO Auto-generated catch block

		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		WaitForElementToBeClickable(logout);
		Thread.sleep(3000);
		try {
			
			logout.click();
		} catch (ElementClickInterceptedException e) {
			// TODO Auto-generated catch block

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		WaitForElement(backtologin);

	}

}
