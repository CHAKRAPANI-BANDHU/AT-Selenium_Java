package com.at.testcases;

import org.testng.annotations.*;
import org.testng.Assert;
import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.NoSuchElementException;

import com.at.pages.LogEditsPage;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;


@Test(dependsOnMethods = { "com.at.testcases.Login.login" })
public class LogEdits extends Login {

	@Test(priority = 5)
	void logedits() throws InterruptedException, IOException
	{
		ExtentTest test = extent.createTest("Log_Edits_Testcases").assignCategory("Log Edits").assignDevice("Chrome");
		logger.info("Navigating to the Log Edits page");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		LogEditsPage lep = new LogEditsPage(driver, test, logger);

		// Log Edits Module Page
		try {
		    lep.getlogEditsURL();
			test.pass("Log Edits module is displayed on the left navigation bar");
		    test.info("Executing Log Edits Module Features Testcases");
		    Thread.sleep(5000);

	} catch (NoSuchElementException e) {
		test.info(e);
		String screenshotPath = getScreenshot(driver, "LogEditsFailedScreenshot");
		test.fail("Log Edits module is not displayed on the left navigation bar in this account", MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
		Assert.fail("Log Edits module is not displayed on the left navigation bar in this account");
	}
  }
}