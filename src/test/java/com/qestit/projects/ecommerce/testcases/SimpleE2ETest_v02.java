package com.qestit.projects.ecommerce.testcases;

import static com.qestit.keywords.WebAction.clickElement;
import static com.qestit.keywords.WebAction.getLocator;
import static com.qestit.keywords.WebAction.navigateToUrl;
import static org.testng.Assert.assertEquals;

import java.util.Hashtable;

import org.testng.annotations.Test;

import com.qestit.annotations.FrameworkAnnotation;
import com.qestit.common.BaseTest;
import com.qestit.config.ConfigFactory;
import com.qestit.config.Configuration;
import com.qestit.constants.FrameworkConstants;
import com.qestit.dataprovider.DataProviderManager;
import com.qestit.enums.AuthorType;
import com.qestit.enums.CategoryType;
import com.qestit.helpers.ExcelHelpers;
import com.qestit.helpers.PropertiesHelpers;
import com.qestit.projects.ecommerce.BasePage;
import com.qestit.utils.DataFakerUtils;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
@Epic("Regression Test ECOMMERCE")
@Feature("E2E Shopping")
public class SimpleE2ETest_v02 extends BaseTest {
	BasePage basePage;
	
	public SimpleE2ETest_v02() {
		basePage= new BasePage();
	}
	
//	HomePage homePage;
//	RegisterationPage registerPage;
//	LoginPage loginPage;
//	MyAccountPage myAccountPage;
//	ProductPage ProductPage;
//	ShoppingCartPage shoppingCartPage;
//	CheckOutPage checkOutPage;
	ExcelHelpers excel= new ExcelHelpers();
	Configuration config = ConfigFactory.getConfigs();
	String urlEcommerce = config.URL_ECOMMERCE();

	@FrameworkAnnotation(author = {AuthorType.Rami, AuthorType.Hany}, category = {CategoryType.REGRESSION})
	@Test(dataProvider = "getECommerceClientTestData", description = "End To End Shopping", dataProviderClass = DataProviderManager.class)
	
	public void oneTest(Hashtable<String, String> data) {
		String email= DataFakerUtils.generateFakeEmail();
		//Here we use the approach of using OWNER Library in ConfigFactory 
		//to retrieve the values from properties using OWNER library
		navigateToUrl(urlEcommerce);
		getHomePage().ClickRegisterLink();
		//Here we use the traditional approach the values from properties Files
		excel.setExcelFile(FrameworkConstants.EXCEL_ECOMMERCE_LOGIN, "Register");
		getRegisterationPage().userCanRegister(excel.getCellData(1, "firstName"), excel.getCellData(1, "lastName"), email, excel.getCellData(1, "password"));
		String RegMessage=getMessageNotify(getRegisterationPage().registerMessage);
		assertEquals(RegMessage, "Your registration completed");
		clickElement(getRegisterationPage().continueBTN);
		clickElement(basePage.loginBTN);
		getLoginPage().loginWithCorrectCreditials(email, excel.getCellData(1, "password"));
		//getLoginPage().UserCanSearchProduct("laptop");
		//getMyAccountPage().SearchProduct();
		UserCanSearchProduct(PropertiesHelpers.getValue("product_P01"));
		getProductPage().userCanAddProduct();
		getShoppingCartPage().userCanCheckOutshoppingCart();
		getCheckOutPage().userCanCheckOut(data);
		clickElement(getCheckOutPage().saveOrder);
		clickElement(getCheckOutPage().shippingBTN);
		clickElement(getCheckOutPage().paymentBTN);
		clickElement(getCheckOutPage().paymentInfoBTN);
		//clickElement(getCheckOutPage().confirmBTN);
		//here another approach to use the locators.properties file
		clickElement(getLocator("confirmBTN"));
		String CompleteMsg=getMessageNotify(getCheckOutPage().OrderCompletedMsg);
		assertEquals(CompleteMsg, "Your order has been successfully processed!");
		getMyAccountPage().userCanLogOut();
	}
}
