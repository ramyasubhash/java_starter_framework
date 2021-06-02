package testBase;

import java.awt.AWTException;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.SessionId;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Listeners;

import listeners.TestNgAllureReportListener;

@Listeners(TestNgAllureReportListener.class)
public class BaseTest {

	public static WebDriver driver = null;
	public SessionId session = null;
	public static Properties prop = new Properties();
	public static DesiredCapabilities capability = null;
	public static String classpath = System.getProperty("user.dir");

	public BaseTest() {

		try {
			prop.load(new FileInputStream(classpath + "/src/test/resources/library/application.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}


	/**
	 * Initialising the driver
	 */
	public static WebDriver initDriver() {
		String browser = prop.getProperty("browser");
		System.out.println(browser);
		if (browser == null) {
			browser = "firefox";
		}

		if (browser.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", "./src/main/resources/lib/chromedriver.exe");
			ChromeOptions chromeOptions = new ChromeOptions();
			chromeOptions.addArguments("start-maximized");
			driver = new ChromeDriver(chromeOptions);
			driver.get(prop.getProperty("url"));
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(30000, TimeUnit.MILLISECONDS);
			
			return driver;

		}

		if (browser.equalsIgnoreCase("firefox")) {
			if (OperatingSystem.isLinux()) {

				System.setProperty("webdriver.gecko.driver", classpath + "/src/test/resources/drivers/geckodriver");
			}

			if (OperatingSystem.isWindows()) {

				System.setProperty("webdriver.gecko.driver", classpath + "/src/test/resources/drivers/geckodriver.exe");
			}
			driver = new FirefoxDriver();
			DesiredCapabilities capabilities = new DesiredCapabilities();
			capabilities.setCapability(browser, capabilities);
			driver.get(prop.getProperty("url"));
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(30000, TimeUnit.MILLISECONDS);
			return driver;

		} else {

			throw new IllegalArgumentException("Browser \"" + browser + "\" isn't supported.");
		}
		
	}

	/**
	 * This section has generic functions
	 */
	

	
	public boolean isPresentInTable(List<WebElement> element, String value) {

		System.out.println(element.size());
		for (WebElement rows : element) {
			System.out.println(rows.toString());
			if (rows.equals(value)) {
				System.out.println(rows.getText());
				return true;
			}

		}

		return false;
	}

	

}
