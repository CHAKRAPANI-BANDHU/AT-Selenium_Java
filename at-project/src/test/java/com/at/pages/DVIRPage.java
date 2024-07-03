package com.at.pages;


import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;

public class DVIRPage {
	WebDriver dpdriver;
	ExtentTest test;
	Logger logger;


	public DVIRPage(WebDriver ddriver, ExtentTest test, Logger logger) {
		dpdriver = ddriver;
		this.test = test;
		this.logger = logger;
		PageFactory.initElements(ddriver, this);
}

	// Click on the DVIR
	@FindBy(xpath = "//*[@id=\"/dvir\"]")
	@CacheLookup
	WebElement dvirURL;
	
	// Click on the DVIR Report
	@FindBy(xpath = "//*[@href=\"/dvir\"]")
	@CacheLookup
	WebElement dvirReportURL;
	
	// Click on the Manage DVIR
	@FindBy(xpath = "//*[@href=\"/dvir-manage\"]")
	@CacheLookup
	WebElement manageDVIRURL;

    // View the DVIR page
	public void getdvirURL() {
		dvirURL.click();
	}
	
    // View the DVIR Report page
	public void getdvirReportURL() {
		dvirReportURL.click();
	}
	
    // View the Manage DVIR page
	public void getmanageDVIR() {
		manageDVIRURL.click();
	}
}
