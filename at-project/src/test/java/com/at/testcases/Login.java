package com.at.testcases;

import org.testng.annotations.*;
import org.testng.Assert;
import java.io.IOException;
import com.at.pages.LoginPage;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;

public class Login extends BaseClass {

    @Test(priority = 1, groups = "login")
    public void login() throws InterruptedException, IOException {
        ExtentTest test = extent.createTest("Login_Testcases").assignCategory("Login").assignDevice("Chrome");
        // Accessing the AT URL
        driver.get("https://qa1-cloud.apolloeld.com/userlogin");
        
        logger.info("Navigating to AT Login page");
        logger.info("AT URL is launched");
        test.info("Executing Login Testcase");
        test.info("Title of the page: " + driver.getTitle());
        LoginPage lp = new LoginPage(driver);

        // portal version
        String pv = lp.portalVersion();
        test.info(pv);
        
        lp.setUserId("QA_MasterReseller");
        test.info("Entered User ID");
        lp.setPassword("YbJEFvU$WfDbt4kG");
        test.info("Entered Password");
        lp.clickLogin();
        // Maximize the browser window
        driver.manage().window().maximize();
        Thread.sleep(8000);
        if (driver.getCurrentUrl().equals("https://qa1-cloud.apolloeld.com/manage")) {
            test.pass("Successfully Logged In.");
            logger.info("Successfully Logged In.");
        } else {
            test.info("Page is loading");
            logger.info("Page is loading");
            String screenshotPath = getScreenshot(driver, "LoginisnotnavigatedFailedScreenshot");
            test.fail("Failed to Login", MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
            Assert.fail("Failed to Login");
            driver.quit();
        }
    }
}
