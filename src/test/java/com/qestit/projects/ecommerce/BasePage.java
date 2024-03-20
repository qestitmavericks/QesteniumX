package com.qestit.projects.ecommerce;

import static com.qestit.keywords.WebAction.*;

import org.openqa.selenium.By;


import com.qestit.projects.ecommerce.pages.CheckOutPage;
import com.qestit.projects.ecommerce.pages.HomePage;
import com.qestit.projects.ecommerce.pages.LogOutPage;
import com.qestit.projects.ecommerce.pages.LoginPage;
import com.qestit.projects.ecommerce.pages.MyAccountPage;
import com.qestit.projects.ecommerce.pages.ProductPage;
import com.qestit.projects.ecommerce.pages.RegisterationPage;
import com.qestit.projects.ecommerce.pages.ShoppingCartPage;


public class BasePage{
	private HomePage homePage;
	private RegisterationPage registerationPage;
	private LoginPage loginPage;
	private MyAccountPage myAccountPage;
	private ProductPage productPage;
	private ShoppingCartPage shoppingCartPage;
	private CheckOutPage checkOutPage;
	private LogOutPage logOutPage;
	
	
	
	public HomePage getHomePage() {
		if(homePage== null) {
			homePage= new HomePage();
		}
		return homePage;
	}
	
	public RegisterationPage getRegisterationPage() {
		if(registerationPage== null) {
			registerationPage= new RegisterationPage();
		}
		return registerationPage;
	}
	
	public LoginPage getLoginPage() {
		if(loginPage== null) {
			loginPage= new LoginPage();
		}
		return loginPage;
	}
	
	public MyAccountPage getMyAccountPage() {
		if(myAccountPage== null) {
			myAccountPage= new MyAccountPage();
		}
		return myAccountPage;
	}
	
	public ProductPage getProductPage() {
		if(productPage== null) {
			productPage= new ProductPage();
		}
		return productPage;
	}
	
	public ShoppingCartPage getShoppingCartPage() {
		if (shoppingCartPage== null) {
			shoppingCartPage= new ShoppingCartPage();
		}
		return shoppingCartPage;
	}
	
	public CheckOutPage getCheckOutPage() {
		if (checkOutPage== null) {
			checkOutPage= new CheckOutPage();
		}
		return checkOutPage;
	}
	
	public LogOutPage getLogOutPage() {
		if(logOutPage== null) {
			logOutPage= new LogOutPage();
		}
		return logOutPage;
	}
	
	
	private By messageNotify= By.xpath("//h2[normalize-space()='Welcome to our store']");
	public By loginBTN= By.cssSelector(".ico-login");
	private By selectCurrency= By.id("customerCurrency");
	private By searchBox= By.cssSelector("#small-searchterms");
	private By searchBTN = By.xpath("//button[@type='submit']");
	private By searchProduct= By.xpath("//span[normalize-space()='Asus N551JK-XO076H Laptop']");
	
	//Xpath Templates (Where all Xpath Templates which used to create custom dynamic Xpaths)
	public By xpathMenuTemplate= By.xpath("//ul[@class='top-menu notmobile']//a[normalize-space()='%s']");
	public By xpathLinkPagesTemplate= By.xpath("//a[normalize-space()='s%']");
	public By xpathCheckOutTemplate= By.xpath("//a[normalize-space()='BillingNewAddress.s%']");
	
	public String getMessageNotify(By by) {
        return getTextElement(by);
    }
	
	public BasePage clickMenuProducts() {
        clickElement(createDynamicXPath("FinalCustomLocator", "replacementText"));
        return this;
    }
	
	public BasePage UserCanSearchProduct(String productName) {
		setText(searchBox, productName);
		moveToElement(searchProduct);
		clickElement(searchBTN);
		return this;
		
	 }
	
	/**
     * Creates a dynamic XPath by replacing a placeholder in the XPath expression with the specified text.
     * 
     * @param xpathTemplate The XPath template with a placeholder (e.g., "//tag[@attribute='%s']").
     * @param replacementText The text to replace the placeholder with.
     * @return A dynamic XPath string.
     */
    public static By createDynamicXPath(String xpathTemplate, String replacementText) {
    	String dynamicLocator= String.format(xpathTemplate, replacementText);
        By dynamicElement= By.xpath(dynamicLocator);
        return dynamicElement;
    }
	
	public void clickCustomLocator() {
		String FinalCustomLocator=xpathMenuTemplate.toString();
		clickElement(createDynamicXPath(FinalCustomLocator, "Computers"));
	}
	
	
		
}
