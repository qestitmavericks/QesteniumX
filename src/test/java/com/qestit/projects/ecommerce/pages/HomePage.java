package com.qestit.projects.ecommerce.pages;

import static com.qestit.keywords.WebAction.clickElement;

import org.openqa.selenium.By;

import com.qestit.projects.ecommerce.CommonPage;

public class HomePage extends CommonPage{
	
	public HomePage() {
		super();
	}
	public By RegisterLink= By.xpath("//a[@class='ico-register']");
	public By loginLink= By.cssSelector(".ico-login"); 
	private By searchBox= By.cssSelector("#small-searchterms");
	private By searchBTN = By.xpath("//button[@class=\"button-1 search-box-button\"]");
	
	
	public RegisterationPage ClickRegisterLink() {
		clickElement(RegisterLink);
		return new RegisterationPage();
	}
	
	public LoginPage ClickLoginLink() {
		clickElement(loginLink);
		return new LoginPage();
	}
	
	
	
	
}

 
