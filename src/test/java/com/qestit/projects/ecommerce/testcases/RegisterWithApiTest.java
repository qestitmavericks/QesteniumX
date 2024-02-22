package com.qestit.projects.ecommerce.testcases;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

import com.qestit.constants.FrameworkConstants;
import com.qestit.helpers.ExcelHelpers;
import com.qestit.projects.ecommerce.BasePage;
import com.qestit.utils.ApiUtils;
import com.qestit.utils.DataFakerUtils;

public class RegisterWithApiTest {
	@Test
	public static void testRegisterApi() {
		ApiUtils apiUtils = new ApiUtils();
		BasePage basePage = new BasePage();
		ExcelHelpers excel = new ExcelHelpers();
		String email = DataFakerUtils.generateFakeEmail();
		Map<String, String> registrationParameters = new HashMap<>();
		excel.setExcelFile(FrameworkConstants.EXCEL_ECOMMERCE_LOGIN, "Register");
		registrationParameters.put("firstName", excel.getCellData(1, "firstName"));
		registrationParameters.put("lastName", excel.getCellData(1, "lastName"));
		registrationParameters.put("email", email);
		registrationParameters.put("password", excel.getCellData(1, "password"));

		// Register a new user using the API
		String apiUserCredentials = apiUtils.registerUser(registrationParameters);

	}
}
