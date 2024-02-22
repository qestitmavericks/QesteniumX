package com.qestit.projects;

import org.testng.annotations.Test;

import com.qestit.config.ConfigFactory;
import com.qestit.config.Configuration;
import com.qestit.constants.FrameworkConstants;
import com.qestit.helpers.Helpers;
import com.qestit.helpers.PropertiesHelpers;
import com.qestit.utils.JsonUtils;

public class App {
	public static void main(String[] args) {
		System.out.println("Build success !!");
		 PropertiesHelpers.loadAllFiles();
		 System.out.println("Data From FrameworkConstants:");
		 //PropertiesHelpers.getProperties();

		System.out.println("Before JsonPath read");
		JsonUtils.setJsonFile(FrameworkConstants.JSON_LOCALIZATION_DE);
		System.out.println(FrameworkConstants.JSON_LOCALIZATION_DE);
		System.out.println(JsonUtils.getData("$['site language']"));
		System.out.println("After JsonPath read");
		System.out.println("JSON Content: " + JsonUtils.getJsonDataSourceString());

		// System.out.println(PropertiesHelpers.getProperties());
		// System.out.println(PropertiesHelpers.loadAllFiles());
		Configuration config = ConfigFactory.getConfigs();
		System.out.println("TARGET: " + config.TARGET());
		System.out.println("BROWSER: " + config.BROWSER());
        System.out.println("HEADLESS: " + config.HEADLESS());
        System.out.println("URL_ECOMMERCE: " + config.URL_ECOMMERCE());
	}
}
