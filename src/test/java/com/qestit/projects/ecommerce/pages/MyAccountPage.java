package com.qestit.projects.ecommerce.pages;

import org.openqa.selenium.By;

import com.qestit.constants.FrameworkConstants;
import com.qestit.helpers.ExcelHelpers;
import com.qestit.projects.ecommerce.BasePage;

public class MyAccountPage extends BasePage {
	
	public MyAccountPage(){
		super();
	}
	
	ExcelHelpers excelHelpers= new ExcelHelpers();
	private By myAccountMessage= By.xpath("//h1[normalize-space()='My account - Customer info']");
	
	public ProductPage SearchProduct() {
		excelHelpers.setExcelFile(FrameworkConstants.EXCEL_ECOMMERCE_DATA, "AddProduct");
		String product=excelHelpers.getCellData(2, "productName");
		UserCanSearchProduct(product);
		return new ProductPage();
	}

}
