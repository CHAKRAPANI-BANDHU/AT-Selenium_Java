package com.at.pages;

import java.time.Duration;
import java.util.List;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentTest;

public class ManagePage {
    WebDriver dpdriver;
    ExtentTest test;
    Logger logger;

    public ManagePage(WebDriver ddriver, ExtentTest test, Logger logger) {
        dpdriver = ddriver;
        this.test = test;
        this.logger = logger;
        PageFactory.initElements(ddriver, this);
    }

    // Click on manage module
    @FindBy(xpath = "//*[@href=\"/manage\"]")
    @CacheLookup
    WebElement manageURL;

    // Click on carrier dropdown
    @FindBy(xpath = "//span[@id='select2-carrierSelector-container']")
    @CacheLookup
    WebElement carrierDropdown;

    // Carriers list elements
    @FindBy(xpath = "//li[contains(@class, 'select2-results__option')]")
    List<WebElement> carriersList;

    public void getmanageURL() {
        manageURL.click();
    }

    public void selectCarrier() {
        carrierDropdown.click();
    }

    public List<WebElement> getCarriersList() {
        return carriersList;
    }

    public void selectCarrierOption(String optionText) {
        for (WebElement option : carriersList) {
            if (option.getText().equals(optionText)) {
                option.click();
                // Wait for the dropdown option to be selected and any resulting actions to complete
                new WebDriverWait(dpdriver, Duration.ofSeconds(30)).until(ExpectedConditions.stalenessOf(option));
                break;
            }
        }
    }
}
