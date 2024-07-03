package com.at.testcases;

import org.testng.annotations.*;
import org.testng.Assert;
import java.io.IOException;
import java.time.Duration;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import com.at.pages.ManagePage;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;

@Test(dependsOnMethods = { "com.at.testcases.Login.login" })
public class Manage extends Login {

    @Test(priority = 2)
    void manage() throws InterruptedException, IOException {
        ExtentTest test = extent.createTest("Manage_Testcases").assignCategory("Manage").assignDevice("Chrome");
        logger.info("Navigating to the Manage page");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        ManagePage mp = new ManagePage(driver, test, logger);

        // Manage Module Page
        try {
            mp.getmanageURL();
            test.pass("Manage module is displayed on the left navigation bar");
            test.info("Executing Manage Module Features Testcases");
            Thread.sleep(5000);
            test.info("Executing Carrier Testcases");
            // Click on carrier dropdown
            mp.selectCarrier();

            // Initialize JavaScript Executor
            JavascriptExecutor js = (JavascriptExecutor) driver;
            Set<String> optionTexts = new HashSet<String>();
            int previousOptionsSize = 0;

            while (true) {
                // Locate all the options within the dropdown
                List<WebElement> options = mp.getCarriersList();

                // Add visible options to the set (to avoid duplicates)
                for (WebElement option : options) {
                    optionTexts.add(option.getText());
                }

                // Check if new options are loaded
                if (optionTexts.size() == previousOptionsSize) {
                    break;
                }

                previousOptionsSize = optionTexts.size();

                // Scroll the last element into view
                WebElement lastOption = options.get(options.size() - 1);
                js.executeScript("arguments[0].scrollIntoView(true);", lastOption);
                Thread.sleep(500); // Wait for the scroll to take effect
            }

            // Select the first option from the dropdown list
            if (!optionTexts.isEmpty()) {
                String firstOption = optionTexts.iterator().next(); // Get the first option
                test.info("Selecting the first option from the dropdown: " + firstOption);
                mp.selectCarrierOption(firstOption);

                // Print the selected item
                test.info("Selected carrier: " + firstOption);
                System.out.println("Selected carrier: " + firstOption);
            } else {
                test.fail("No options found in the dropdown list");
                Assert.fail("No options found in the dropdown list");
            }

            // Concatenate the contents of the set into a single line separated by commas
            StringBuilder concatenatedOptions = new StringBuilder();
            for (String text : optionTexts) {
                concatenatedOptions.append(text).append(", ");
            }

            // Remove the last comma and space if the StringBuilder is not empty
            if (concatenatedOptions.length() > 0) {
                concatenatedOptions.setLength(concatenatedOptions.length() - 2);
            }

            // Print the concatenated options as a single pass entry
            test.pass(concatenatedOptions.toString());

            // Print the count of the dropdown list items
            test.info("Total carriers: " + optionTexts.size());
            System.out.println("Total carriers: " + optionTexts.size());

        } catch (NoSuchElementException e) {
            test.info(e);
            String screenshotPath = getScreenshot(driver, "manageFailedScreenshot");
            test.fail("Manage module is not displayed on the left navigation bar in this account",
                    MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());

            Assert.fail("Manage module is not displayed on the left navigation bar in this account");
        }
    }
}
