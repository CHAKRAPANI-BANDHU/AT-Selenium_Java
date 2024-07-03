package com.at.testcases;

import org.testng.annotations.*;
import org.testng.Assert;
import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.NoSuchElementException;

import com.at.pages.LogBooksPage;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;

@Test(dependsOnMethods = {"com.at.testcases.Login.login"})
public class LogBooks extends Login {

	@Test(priority = 3)
	void logbooks() throws InterruptedException, IOException
	{
		ExtentTest test = extent.createTest("Log_Books_Testcases").assignCategory("Log Books").assignDevice("Chrome");
		logger.info("Navigating to the Log Books page");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		LogBooksPage lbp = new LogBooksPage(driver, test, logger);

		// Log Books Module Page
		try {
		    lbp.getlogBooksURL();
			test.pass("Log Books module is displayed on the left navigation bar");
		    test.info("Executing Log Books Module Features Testcases");
		    Thread.sleep(5000);

	} catch (NoSuchElementException e) {
		test.info(e);
		String screenshotPath = getScreenshot(driver, "logBooksFailedScreenshot");
		test.fail("Log Books module is not displayed on the left navigation bar in this account",MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
		Assert.fail("Log Books module is not displayed on the left navigation bar in this account");
	}
  }
}