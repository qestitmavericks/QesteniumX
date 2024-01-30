package com.qestit.projects.ecommerce.pages;

import static com.qestit.keywords.WebAction.clickElement;
import static com.qestit.keywords.WebAction.setText;

import org.openqa.selenium.By;

import com.qestit.projects.ecommerce.CommonPage;

public class LoginPage extends CommonPage{
	
	public LoginPage() {
		super();
	}
	
	private By emailText= By.cssSelector("#Email");
	private By password= By.cssSelector("#Password");
	private By loginButton= By.xpath("//button[normalize-space()='Log in']");
	private By myAccountbtn= By.xpath("//a[@class='ico-account']");
	
	
	
	public LoginPage loginWithCorrectCreditials(String email, String pass) {
		setText(emailText, email);
		setText(password, pass);
		clickElement(loginButton);
		return this;
	}
	
	
	
	public MyAccountPage goToMyAccountPage() {
		clickElement(myAccountbtn);
		return new MyAccountPage();
	}
	
	
}
