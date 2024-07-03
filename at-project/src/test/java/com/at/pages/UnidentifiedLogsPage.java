package com.at.pages;


import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;

public class UnidentifiedLogsPage {
	WebDriver dpdriver;
	ExtentTest test;
	Logger logger;


	public UnidentifiedLogsPage(WebDriver ddriver, ExtentTest test, Logger logger) {
		dpdriver = ddriver;
		this.test = test;
		this.logger = logger;
		PageFactory.initElements(ddriver, this);
}

	@FindBy(xpath = "//*[@href=\"/unidentifiedlogs\"]")
	@CacheLookup
	WebElement unidentifiedLogsURL;


	public void getunidentifiedLogsURL() {
		unidentifiedLogsURL.click();
	}
}
