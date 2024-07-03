package com.at.testcases;

import org.testng.annotations.*;
import org.testng.Assert;
import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.NoSuchElementException;

import com.at.pages.ViolationsPage;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;


@Test(dependsOnMethods = { "com.at.testcases.Login.login" })
public class Violations extends Login {

	@Test(priority = 7)
	void violations() throws InterruptedException, IOException
	{
		ExtentTest test = extent.createTest("Violations_Testcases").assignCategory("Violations").assignDevice("Chrome");
		logger.info("Navigating to the Violations page");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		ViolationsPage vp = new ViolationsPage(driver, test, logger);

		// Violations Module Page
		try {
		    vp.getviolationsURL();
			test.pass("Violations module is displayed on the left navigation bar");
		    test.info("Executing Violations Module Features Testcases");
		    Thread.sleep(5000);
		    

	} catch (NoSuchElementException e) {
		test.info(e);
		String screenshotPath = getScreenshot(driver, "violationsFailedScreenshot");
		test.fail("Violations module is not displayed on the left navigation bar in this account", MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
		Assert.fail("Violations module is not displayed on the left navigation bar in this account");
	}
  }
}