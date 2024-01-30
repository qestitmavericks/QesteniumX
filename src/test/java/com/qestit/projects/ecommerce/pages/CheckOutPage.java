package com.qestit.projects.ecommerce.pages;

import static com.qestit.keywords.WebAction.*;
import static com.qestit.keywords.WebAction.selectOptionByIndex;
import static com.qestit.keywords.WebAction.setText;

import java.util.Hashtable;

import org.openqa.selenium.By;

import com.qestit.projects.ecommerce.CommonPage;
import com.qestit.projects.ecommerce.models.CustomerModel;

public class CheckOutPage extends CommonPage{
	
	public CheckOutPage() {
		super();
	}
	
	CommonPage commonPage;
	private By countrySelect= By.xpath("//select[@id='BillingNewAddress_CountryId']");
	private By cityText= By.xpath("//input[@id='BillingNewAddress_City']");
	private By addreeText= By.xpath("//input[@id='BillingNewAddress_Address1']");
	private By ZipCodeText= By.xpath("//input[@id='BillingNewAddress_ZipPostalCode']");
	private By phoneText= By.xpath("//input[@id='BillingNewAddress_PhoneNumber']");
	public By saveOrder= By.xpath("//button[@name='save']");
	public By shippingBTN= By.xpath("//button[@class='button-1 shipping-method-next-step-button']");
	public By paymentBTN= By.xpath("//button[@class='button-1 payment-method-next-step-button']");
	public By paymentInfoBTN= By.xpath("//button[@class='button-1 payment-info-next-step-button']");
	public By confirmBTN= By.xpath("//button[normalize-space()='Confirm']");
	public By OrderCompletedMsg= By.xpath("//strong[normalize-space()='Your order has been successfully processed!']");
	
	
	public void userCanCheckOut(Hashtable<String, String> data) {
		selectOptionByIndex(countrySelect, 4);
		if (CustomerModel.getCity() != null) {
            setText(cityText, CustomerModel.getCity());
        }
        if (CustomerModel.getAddress() != null) {
            setText(addreeText, CustomerModel.getAddress());
        }
        if (CustomerModel.getZip() != null) {
            setText(ZipCodeText, CustomerModel.getZip());
        }
        if (CustomerModel.getPhone() != null) {
            setText(phoneText, CustomerModel.getPhone());
        }
//		setText(cityText, data.get(CustomerModel.getCity()));
//		setText(addreeText, data.get(CustomerModel.getAddress()));
//		setText(ZipCodeText, data.get(CustomerModel.getZip()));
//		setText(phoneText, data.get(CustomerModel.getPhone()));
//		clickElement(continueBTN);
	}
	
}
