/*
 * Copyright (c) 2023 QESTIT
 * QesteniumX
 */

package com.qestit.utils;

import org.openqa.selenium.WebDriver;

import com.qestit.constants.FrameworkConstants;
import com.qestit.driver.DriverManager;
import com.qestit.enums.SitePage;

public class NavigationUtil {

	public static void navigateTo(SitePage page) {
		WebDriver driver = DriverManager.getDriver(); // Use DriverManager to get the WebDriver
		switch (page) {
		case HOME:
			driver.get(FrameworkConstants.URL_ECOMMERCE);
			break;
		case LOGIN:
			driver.get(FrameworkConstants.URL_ECOMMERCE + "/login");
			break;
		case REGISTER:
			driver.get(FrameworkConstants.URL_ECOMMERCE + "/register");
			break;
		case CHECKOUT:
			driver.get(FrameworkConstants.URL_ECOMMERCE + "/checkout/completed");
			break; 
		default:
			throw new IllegalArgumentException("Undefined page");
		}
	}
}
