package com.qestit.projects.ecommerce.pages;

import static com.qestit.keywords.WebAction.clickElement;

import org.openqa.selenium.By;

import com.qestit.projects.ecommerce.BasePage;


public class ProductPage extends BasePage{
	
	public ProductPage() {
		super();
	}
	private By addToCartBTN = By.xpath("//button[normalize-space()='Add to cart']");
	private By shoppingCartBTN = By.xpath("//a[normalize-space()='shopping cart']");
	
	
	public ShoppingCartPage userCanAddProduct() {
		clickElement(addToCartBTN);
		clickElement(shoppingCartBTN);
		return new ShoppingCartPage();
	}
	
}
