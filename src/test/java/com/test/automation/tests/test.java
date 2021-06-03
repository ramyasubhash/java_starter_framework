package com.test.automation.tests;

import static driverfactory.DriverHolder.getDriver;
import static driverfactory.DriverHolder.setDriver;

import java.awt.AWTException;
import java.io.File;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.DashboardPage;
import pages.LoginPage;
import pages.StopWordsPage;
import testBase.BaseTest;



public class test extends BaseTest {
	public int THIRD_STOP_WORD = 3;
	public int FOURTH_STOP_WORD = 5;

	@DataProvider(name="personas")
	public Object[][] data()
	{
		return new Object[][] {
			{"sw_only","Sw@2021", "user"}
		};

	}
	
	@BeforeMethod
	public void loginAndLandOnStopWordsPage() throws InterruptedException {
		setDriver(initDriver());
		getDriver();
		LoginPage loginpage = new LoginPage(getDriver());
		DashboardPage dashboardPage = new pages.DashboardPage(getDriver());
		StopWordsPage stopwordspage = new StopWordsPage(getDriver());

		loginpage.login(prop.getProperty("username"), prop.getProperty("password"));
		Assert.assertTrue(dashboardPage.dashboard.isDisplayed());

		dashboardPage.landOnStopWords();
		Assert.assertTrue(stopwordspage.stopWordsLibraryTitle.isDisplayed());

	}

	@Test(priority = 1, description = "Test creation of new stopword")
	public void createStopWord() throws InterruptedException, AWTException {
		StopWordsPage stopwordspage = new StopWordsPage(getDriver());

		Random randomGenerator = new Random();
		Boolean stopwordadded = stopwordspage.addingStopWord("test" + randomGenerator.nextInt(9),
				prop.getProperty("stopwordsInput"));
		Assert.assertTrue(stopwordadded);

	}

	@Test(priority = 2, description = "Test edit/update existing stopword")
	public void updateExistingStopWord() throws InterruptedException, AWTException {
		StopWordsPage stopwordspage = new StopWordsPage(getDriver());

		Random randomGenerator = new Random();
		String stopwordupdated = stopwordspage.updateStopWord(THIRD_STOP_WORD,
				"editstopword" + randomGenerator.nextInt(9));
		stopwordspage.validateStopWordUpdate(THIRD_STOP_WORD, stopwordupdated);
		Assert.assertTrue(stopwordspage.comboBoxValues.getText().contains(stopwordupdated));

	}

	@Test(priority = 3, description = "Test deletion of single stopword")
	public void deleteSingleStopWord() throws InterruptedException, AWTException {
		StopWordsPage stopwordspage = new StopWordsPage(getDriver());

		stopwordspage.deleteStopWord(FOURTH_STOP_WORD);
		Assert.assertFalse(stopwordspage.validateStopWordPresent(FOURTH_STOP_WORD));
	}

	@Test(priority = 4, description = "Test deletion of multiple stopwords")
	public void deleteMultipleStopWords() throws InterruptedException, AWTException {
		StopWordsPage stopwordspage = new StopWordsPage(getDriver());

		stopwordspage.deleteStopWords();
	}

	@AfterMethod
	public void logoutAndCloseDriver() throws InterruptedException {

		LoginPage loginpage = new LoginPage(getDriver());

		loginpage.logout();
		getDriver().close();
		System.out.println("Test complete - Driver Instance closed");
	}

}


	
/*
 * Sample test method for Dataprovider implementation
 */
//	@Test(dataProvider="personas", description="Testing different personas")
//	public void loginAndLandOnStopWordsPage(String username, String password, String persona) throws InterruptedException {
//		setDriver(initDriver());
//		getDriver();
//		LoginPage loginpage = new LoginPage(getDriver());
//
//		loginpage.login(username, password);
//		loginpage.logout();
//
//	}
//}
