package com.qestit.projects.ecommerce.pages;

import static com.qestit.keywords.WebAction.clickElement;

import org.openqa.selenium.By;

import com.qestit.projects.ecommerce.BasePage;

public class ShoppingCartPage extends BasePage {
	
	public ShoppingCartPage() {
		super();
	}
	
	private By termOfServiceCheck= By.xpath("//input[@id='termsofservice']");
	private By checkOutBTN= By.xpath("//button[@id='checkout']");
	
	public CheckOutPage userCanCheckOutshoppingCart() {
		clickElement(termOfServiceCheck);
		clickElement(checkOutBTN);
		return new CheckOutPage();
	}
	
}
