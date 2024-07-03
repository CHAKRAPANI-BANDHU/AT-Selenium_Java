package com.at.testcases;

import org.testng.annotations.*;
import org.testng.Assert;
import java.io.IOException;
import java.time.Duration;
import org.openqa.selenium.NoSuchElementException;
import com.at.pages.DashboardPage;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;

@Test(dependsOnMethods = {"com.at.testcases.Login.login"})
public class Dashboard extends Login {

    @Test(priority = 2)
    void dashboard() throws InterruptedException, IOException {
        ExtentTest test = extent.createTest("Dashboard_Testcases").assignCategory("Dashboard_Testcases").assignDevice("Chrome");
        logger.info("Navigating to the Dashboard page");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        DashboardPage db = new DashboardPage(driver, test, logger);

        // Dashboard Module Page
        try {
            db.getdashboardURL();       
            test.pass("Dashboard module is displayed on the left navigation bar");
            test.info("Executing Dashboard Module Features Testcases");
            Thread.sleep(5000);
            
            // Click on Compliance module
            db.getcomplianceURL();
            test.pass("Compliance module is displayed on the left navigation bar");
            test.info("Executing Compliance Module Features Testcases");
            Thread.sleep(5000);
            
            // Click on Hours of Service module
            db.gethosDashboard();
            test.pass("Hours of Service module is displayed on the left navigation bar");
            test.info("Executing Hours of Service Module Features Testcases");
            Thread.sleep(5000);

            // If no exception, mark test as passed
            Assert.assertTrue(true, "Dashboard module is displayed as expected");

        } catch (NoSuchElementException e) {
            test.info(e);
            String screenshotPath = getScreenshot(driver, "dashboardFailedScreenshot");
            test.fail("Dashboard module is not displayed on the left navigation bar in this account", MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
            
            // Fail the test in TestNG
            Assert.fail("Dashboard module is not displayed on the left navigation bar in this account");
        }
    }
}
