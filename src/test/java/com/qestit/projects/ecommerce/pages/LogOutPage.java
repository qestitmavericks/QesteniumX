package com.qestit.projects.ecommerce.pages;

import static com.qestit.keywords.WebAction.clickElement;

import org.openqa.selenium.By;

public class LogOutPage {
	
	private By logOutBTN= By.xpath("//a[@class=\"ico-logout\"]");
	
	public LogOutPage userCanLogOut() {
		clickElement(logOutBTN);
		return this;
	 }
}
