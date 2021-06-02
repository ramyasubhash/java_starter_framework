package listeners;

import io.qameta.allure.Attachment;

import java.time.LocalTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import static driverfactory.DriverHolder.getDriver;

public class TestNgAllureReportListener extends TestListenerAdapter {

	private static String getTestMethodName(ITestResult iTestResult) {
		return iTestResult.getMethod().getConstructorOrMethod().getName();
	}

	// Text attachments for Allure
	@Attachment(value = "Page screenshot", type = "image/png")
	public byte[] saveScreenshotPNG(WebDriver driver) {
		return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
	}

	// Text attachments for Allure
	@Attachment(value = "{0}", type = "text/plain")
	public static String saveTextLog(String message) {
		return message;
	}

	// HTML attachments for Allure
	@Attachment(value = "{0}", type = "text/html")
	public static String attachHtml(String html) {
		return html;
	}

	private static String getLocalAestTime() {
		ZoneId z = ZoneId.of("Australia/Sydney");
		LocalTime lt = LocalTime.now(z).truncatedTo(ChronoUnit.SECONDS);
		return lt.toString();

	}

		
		@Override
	    public void onTestSuccess(ITestResult iTestResult) {
	        System.out.println("Test Method '" + getTestMethodName(iTestResult) + "' is Passed");
	        if (getDriver() != null) {
	            System.out.println("Screenshot has captured for the Test Method '" + getTestMethodName(iTestResult) + "'");
	            saveScreenshotPNG(getDriver());
	        }
	    }
		
		
		
		@Override
	    public void onTestFailure(ITestResult iTestResult) {
	        System.out.println("Test Method '" + getTestMethodName(iTestResult) + "' is Failed");
	        if (getDriver() != null) {
	            System.out.println("Screenshot has captured for the Test Method '" + getTestMethodName(iTestResult) + "'");
	            saveScreenshotPNG(getDriver());
	        }
	    }


	@Override
	public void onTestSkipped(ITestResult iTestResult) {
		System.out.println("I am in onTestSkipped method " + getTestMethodName(iTestResult) + " skipped");
	}

}