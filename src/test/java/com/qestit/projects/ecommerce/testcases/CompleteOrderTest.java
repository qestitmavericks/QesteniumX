package com.qestit.projects.ecommerce.testcases;

import static com.qestit.keywords.WebAction.*;
import static org.testng.Assert.assertEquals;

import java.util.Hashtable;

import org.testng.annotations.Test;

import com.qestit.annotations.FrameworkAnnotation;
import com.qestit.common.BaseTest;
import com.qestit.constants.FrameworkConstants;
import com.qestit.dataprovider.DataProviderManager;
import com.qestit.enums.AuthorType;
import com.qestit.enums.CategoryType;
import com.qestit.helpers.ExcelHelpers;
import com.qestit.projects.ecommerce.CommonPage;
import com.qestit.utils.DataFakerUtils;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
@Epic("Regression Test ECOMMERCE")
@Feature("E2E Shopping")
public class CompleteOrderTest extends BaseTest {
	CommonPage commonPage;
	
	public CompleteOrderTest() {
		commonPage= new CommonPage();
	}
	
//	HomePage homePage;
//	RegisterationPage registerPage;
//	LoginPage loginPage;
//	MyAccountPage myAccountPage;
//	ProductPage ProductPage;
//	ShoppingCartPage shoppingCartPage;
//	CheckOutPage checkOutPage;
	ExcelHelpers excel= new ExcelHelpers();
	@FrameworkAnnotation(author = {AuthorType.Rami, AuthorType.Hany}, category = {CategoryType.REGRESSION})
	@Test(dataProvider = "getECommerceClientTestData", description = "End To End Shopping", dataProviderClass = DataProviderManager.class)
	
	public void end2EndTest(Hashtable<String, String> data) {
		String email= DataFakerUtils.generateFakeEmail();
		navigateToUrl(FrameworkConstants.URL_ECOMMERCE);
		getHomePage().ClickRegisterLink();
		excel.setExcelFile(FrameworkConstants.EXCEL_ECOMMERCE_LOGIN, "Register");
		getRegisterationPage().userCanRegister(excel.getCellData(1, "firstName"), excel.getCellData(1, "lastName"), email, excel.getCellData(1, "password"));
		String RegMessage=getMessageNotify(getRegisterationPage().registerMessage);
		assertEquals(RegMessage, "Your registration completed");
		clickElement(getRegisterationPage().continueBTN);
		clickElement(commonPage.loginBTN);
		getLoginPage().loginWithCorrectCreditials(email, excel.getCellData(1, "password"));
		getLoginPage().UserCanSearchProduct("laptop");
		getProductPage().userCanAddProduct();
		getShoppingCartPage().userCanCheckOutshoppingCart();
		getCheckOutPage().userCanCheckOut(data);
		clickElement(getCheckOutPage().saveOrder);
		clickElement(getCheckOutPage().shippingBTN);
		clickElement(getCheckOutPage().paymentBTN);
		clickElement(getCheckOutPage().paymentInfoBTN);
		clickElement(getCheckOutPage().confirmBTN);
		String CompleteMsg=getMessageNotify(getCheckOutPage().OrderCompletedMsg);
		assertEquals(CompleteMsg, "Your order has been successfully processednnn!");
		getMyAccountPage().userCanLogOut();
	}
}
