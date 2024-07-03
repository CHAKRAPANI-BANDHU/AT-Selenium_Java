package com.at.testcases;

import org.testng.annotations.*;
import org.testng.Assert;
import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.NoSuchElementException;

import com.at.pages.DVIRPage;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;

@Test(dependsOnMethods = { "com.at.testcases.Login.login" })
public class DVIR extends Login {

	@Test(priority = 8)
	void dvir() throws InterruptedException, IOException {
		ExtentTest test = extent.createTest("DVIR_Testcases").assignCategory("DVIR").assignDevice("Chrome");
		logger.info("Navigating to the DVIR page");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		DVIRPage dvp = new DVIRPage(driver, test, logger);

		// DVIR Module Page
		try {
			dvp.getdvirURL();
			test.pass("DVIR module is displayed on the left navigation bar");
			test.info("Executing DVIR Module Features Testcases");
			Thread.sleep(10000);

			// Click on DVIR Report module
			dvp.getdvirReportURL();
			test.pass("DVIR Report module is displayed on the left navigation bar");
			test.info("Executing DVIR Report Module Features Testcases");
			Thread.sleep(5000);

			// Click on Manage DVIR module
			dvp.getmanageDVIR();
			test.pass("Manage DVIR module is displayed on the left navigation bar");
			test.info("Executing Manage DVIR Module Features Testcases");
			Thread.sleep(5000);

		} catch (NoSuchElementException e) {
			test.info(e);
			String screenshotPath = getScreenshot(driver, "dvirFailedScreenshot");
			test.fail("DVIR module is not displayed on the left navigation bar in this account",
					MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
            // Fail the test in TestNG
            Assert.fail("DVIR module is not displayed on the left navigation bar in this account");
		} 
	}
}