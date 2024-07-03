package com.at.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	WebDriver ldriver;

	public LoginPage(WebDriver rdriver) {
		ldriver = rdriver;
		PageFactory.initElements(rdriver, this);
	}

	@FindBy(xpath = "//*[@id=\"username\"]")
	@CacheLookup
	WebElement UserId;

	@FindBy(xpath = "//*[@id=\"password\"]")
	@CacheLookup
	WebElement Password;

	@FindBy(xpath = "//*[@id=\"login\"]")
	@CacheLookup
	WebElement loginButton;

	@FindBy(xpath = "//*[@id=\"version\"]/small")
	@CacheLookup
	WebElement PortalVersion;

	public void setUserId(String uid) {
		UserId.sendKeys(uid);
	}

	public void setPassword(String pwd) {
		Password.sendKeys(pwd);
	}

	public void clickLogin() {
		loginButton.click();
	}

	public String portalVersion() {
		String value = PortalVersion.getText();
		return value;
	}
}
