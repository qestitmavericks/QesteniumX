package com.qestit.projects.ecommerce.testcases;

import static com.qestit.keywords.WebAction.*;
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
import com.qestit.enums.SitePage;
import com.qestit.helpers.ExcelHelpers;
import com.qestit.helpers.PropertiesHelpers;
import com.qestit.projects.ecommerce.BasePage;
import com.qestit.projects.ecommerce.pages.MyAccountPage;
import com.qestit.utils.DataFakerUtils;
import com.qestit.utils.NavigationUtil;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;

@Epic("Regression Test ECOMMERCE")
@Feature("E2E Shopping")
public class SimpleE2ETest extends BaseTest {
	BasePage commonPage;

	public SimpleE2ETest() {
		commonPage = new BasePage();
	}

//	HomePage homePage;
//	RegisterationPage registerPage;
//	LoginPage loginPage;
//	MyAccountPage myAccountPage;
//	ProductPage ProductPage;
//	ShoppingCartPage shoppingCartPage;
//	CheckOutPage checkOutPage;
	ExcelHelpers excel = new ExcelHelpers();
	Configuration config = ConfigFactory.getConfigs();
	String email = DataFakerUtils.generateFakeEmail();
	// String urlEcommerce = config.URL_ECOMMERCE();

	@FrameworkAnnotation(author = { AuthorType.Rami, AuthorType.Hany }, category = { CategoryType.REGRESSION })
	@Test(priority = 1, description = "User can register and login in the application")

	public void registerAndLogin() {
		// Here we use the approach of using OWNER Library in ConfigFactory
		// to retrieve the values from properties using OWNER library
		// navigateToUrl(urlEcommerce);
		NavigationUtil.navigateTo(SitePage.HOME);
		getHomePage().ClickRegisterLink();
		// Here we use the traditional approach the values from properties Files
		excel.setExcelFile(FrameworkConstants.EXCEL_ECOMMERCE_LOGIN, "Register");
		getRegisterationPage().userCanRegister(excel.getCellData(1, "firstName"), excel.getCellData(1, "lastName"),
				email, excel.getCellData(1, "password"));
		String RegMessage = getMessageNotify(getRegisterationPage().registerMessage);
		assertEquals(RegMessage, "Your registration completed");
		clickElement(getRegisterationPage().continueBTN);
		clickElement(commonPage.loginBTN);
		getLoginPage().loginWithCorrectCreditials(email, excel.getCellData(1, "password"));
		// getLoginPage().UserCanSearchProduct("laptop");
		// getMyAccountPage().SearchProduct();
	}

	@FrameworkAnnotation(author = { AuthorType.Hany }, category = { CategoryType.REGRESSION })
	@Test(priority = 2, dataProvider = "getECommerceClientTestData", description = "Search Product and Checkout", dataProviderClass = DataProviderManager.class)
	public void searchAndCheckout(Hashtable<String, String> data) {
		NavigationUtil.navigateTo(SitePage.LOGIN);
		UserCanSearchProduct(PropertiesHelpers.getValue("product_P01"));
		getProductPage().userCanAddProduct();
		getShoppingCartPage().userCanCheckOutshoppingCart();
		getLoginPage().loginWithCorrectCreditials(email, excel.getCellData(1, "password"));
		getShoppingCartPage().userCanCheckOutshoppingCart();
		getCheckOutPage().userCanCheckOut(data);
		clickElement(getCheckOutPage().saveOrder);
		clickElement(getCheckOutPage().shippingBTN);
		clickElement(getCheckOutPage().paymentBTN);
		clickElement(getCheckOutPage().paymentInfoBTN);
		// clickElement(getCheckOutPage().confirmBTN);
		// here another approach to use the locators.properties file
		clickElement(getLocator("confirmBTN"));
		String CompleteMsg = getMessageNotify(getCheckOutPage().OrderCompletedMsg);
		assertEquals(CompleteMsg, "Your order has been successfully processed!");
	}

	@FrameworkAnnotation(author = { AuthorType.Hany }, category = { CategoryType.REGRESSION })
	@Test(priority = 3, description = "Loging Out and back to the HomePage")
	public void logOut() {
		NavigationUtil.navigateTo(SitePage.HOME);
		clickElement(commonPage.loginBTN);
		getLoginPage().loginWithCorrectCreditials(email, excel.getCellData(1, "password"));
		getLogOutPage().userCanLogOut();
	}
}
