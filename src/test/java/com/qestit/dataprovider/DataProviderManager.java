/*
 * Copyright (c) 2023 QESTIT. QESTIT
 * QesteniumX
 */

package com.qestit.dataprovider;

import java.util.Hashtable;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qestit.constants.FrameworkConstants;
import com.qestit.helpers.ExcelHelpers;
import com.qestit.helpers.Helpers;
import com.qestit.helpers.PropertiesHelpers;
import com.qestit.projects.ecommerce.models.CustomerModel;
import com.qestit.projects.ecommerce.models.SignInModel;

public final class DataProviderManager {

    private DataProviderManager() {
        super();
        PropertiesHelpers.loadAllFiles();
    }

    @Test(dataProvider = "getSignInDataHashTable")
    public void testGetSignInData(Hashtable<String, String> data) {
        System.out.println("signInData.testCaseName = " + data.get(SignInModel.getTestCaseName()));
        System.out.println("signInData.username = " + data.get(SignInModel.getEmail()));
        System.out.println("signInData.password = " + data.get(SignInModel.getPassword()));
        System.out.println("signInData.expectedTitle = " + data.get(SignInModel.getExpectedTitle()));
        System.out.println("signInData.expectedError = " + data.get(SignInModel.getExpectedError()));

    }

    @Test(dataProvider = "getClientDataHashTable")
    public void testGetClientData(Hashtable<String, String> data) {
        System.out.println("clientData.TestCaseName = " + data.get(CustomerModel.getTestCaseName()));
        System.out.println("clientData.CompanyName = " + data.get(CustomerModel.getCompanyName()));
        System.out.println("clientData.OWNER = " + data.get(CustomerModel.getOwner()));
        System.out.println("clientData.Address = " + data.get(CustomerModel.getAddress()));
        System.out.println("clientData.CITY = " + data.get(CustomerModel.getCity()));
        System.out.println("clientData.STATE = " + data.get(CustomerModel.getState()));

    }

    @DataProvider(name = "getSignInDataHashTable", parallel = true)
    public static Object[][] getSignInData() {
        ExcelHelpers excelHelpers = new ExcelHelpers();
        Object[][] data = excelHelpers.getDataHashTable(Helpers.getCurrentDir() + FrameworkConstants.EXCEL_DATA_FILE_PATH, "SignIn", 1, 2);
        System.out.println("getSignInData: " + data);
        return data;
    }

    @DataProvider(name = "getClientDataHashTable", parallel = true)
    public static Object[][] getClientData() {
        ExcelHelpers excelHelpers = new ExcelHelpers();
        Object[][] data = excelHelpers.getDataHashTable(Helpers.getCurrentDir() + FrameworkConstants.EXCEL_DATA_FILE_PATH, "Client", 1, 2);
        System.out.println("getClientData: " + data);
        return data;
    }
    
    @DataProvider(name = "getECommerceClientTestData", parallel = true)
    public static Object[][] getECommerceClientTestData() {
        Hashtable<String, String> clientData = new Hashtable<>();
        clientData.put("city", CustomerModel.getCity());
        clientData.put("address", CustomerModel.getAddress());
        clientData.put("zip", CustomerModel.getZip());
        clientData.put("phone", CustomerModel.getPhone());

        return new Object[][] { { clientData } };
    }

}
