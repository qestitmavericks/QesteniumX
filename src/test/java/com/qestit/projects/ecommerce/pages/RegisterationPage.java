package com.qestit.projects.ecommerce.pages;

import static com.qestit.keywords.WebAction.clickElement;
import static com.qestit.keywords.WebAction.setText;

import org.openqa.selenium.By;

public class RegisterationPage {
	
	private By radioButton= By.cssSelector("#gender-male");
	private By firstName= By.cssSelector("#FirstName");
	private By lastName= By.cssSelector("#LastName");
	private By emailText= By.cssSelector("#Email");
	private By password= By.cssSelector("#Password");
	private By confirmPassword= By.cssSelector("#ConfirmPassword");
	private By RegisterBtn= By.cssSelector("#register-button");
	public By registerMessage= By.cssSelector(".result");
	public By continueBTN= By.xpath("//a[@class=\"button-1 register-continue-button\"]");
	
	public void userCanRegister(String fName, String lName, String email, String pass ) {
		clickElement(radioButton);
		setText(firstName, fName);
		setText(lastName, lName);
		setText(emailText, email);
		setText(password, pass);
		setText(confirmPassword, pass);
		clickElement(RegisterBtn);
	}
	
	
	
   
	
}
