package com.at.pages;

import java.time.Duration;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.aventstack.extentreports.ExtentTest;

public class DashboardPage {
    WebDriver dpdriver;
    ExtentTest test;
    Logger logger;

    @FindBy(css = "#dashboard > p")
    @CacheLookup
    WebElement dashboardURL;

    @FindBy(xpath = "//*[@href=\"/compliance\"]")
    @CacheLookup
    WebElement complianceURL;

    @FindBy(xpath = "//*[@href=\"/hosdashboard\"]")
    @CacheLookup
    WebElement hosDashboardURL;

    public DashboardPage(WebDriver ddriver, ExtentTest test, Logger logger) {
        dpdriver = ddriver;
        this.test = test;
        this.logger = logger;
        PageFactory.initElements(ddriver, this);
    }

    // View the Dashboard page
    public void getdashboardURL() {
        WebDriverWait wait = new WebDriverWait(dpdriver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOf(dashboardURL)).click();
    }

    // View the Compliance page
    public void getcomplianceURL() {
    	WebDriverWait wait = new WebDriverWait(dpdriver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOf(complianceURL)).click();
    }

    // View the Hours of Service page
    public void gethosDashboard() {
    	WebDriverWait wait = new WebDriverWait(dpdriver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOf(hosDashboardURL)).click();
    }
}
