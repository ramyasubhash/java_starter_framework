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
import org.testng.annotations.Test;


import pages.LoginPage;
import testBase.BaseTest;



public class test extends BaseTest {
	public int THIRD_STOP_WORD = 3;
	public int FOURTH_STOP_WORD = 5;

	@Test
	public void loginAndLandOnStopWordsPage() throws InterruptedException {
		setDriver(initDriver());
		getDriver();
		LoginPage loginpage = new LoginPage(getDriver());
		

		loginpage.login(prop.getProperty("username"), prop.getProperty("password"));
		

	

	}

	
}
