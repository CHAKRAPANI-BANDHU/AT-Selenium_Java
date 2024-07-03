package com.at.testcases;

import org.testng.annotations.Test;
import org.testng.Assert;
import java.io.IOException;
import java.time.Duration;
import org.openqa.selenium.NoSuchElementException;
import com.at.pages.UnidentifiedLogsPage;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;

@Test(dependsOnMethods = { "com.at.testcases.Login.login" })
public class UnidentifiedLogs extends Login {

    @Test(priority = 4)
    void unidentifiedlogs() throws InterruptedException, IOException {
        ExtentTest test = extent.createTest("Unidentified_Logs_Testcases").assignCategory("Unidentified Logs").assignDevice("Chrome");
        logger.info("Navigating to the Unidentified Logs page");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        UnidentifiedLogsPage ulp = new UnidentifiedLogsPage(driver, test, logger);

        // Unidentified Logs Module Page
        try {
            ulp.getunidentifiedLogsURL();
            test.pass("Unidentified Logs module is displayed on the left navigation bar");
            test.info("Executing Unidentified Logs Module Features Testcases");
            Thread.sleep(5000);

            // If no exception, mark test as passed
            Assert.assertTrue(true, "Unidentified Logs module is displayed as expected");

        } catch (NoSuchElementException e) {
            test.info(e);
            String screenshotPath = getScreenshot(driver, "unidentifiedLogsFailedScreenshot");
            // Attach screenshot to Extent report on failure
            test.fail("Unidentified Logs module is not displayed on the left navigation bar in this account",
                      MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
            // Fail the test in TestNG
            Assert.fail("Unidentified Logs module is not displayed on the left navigation bar in this account");
        }
    }
}
