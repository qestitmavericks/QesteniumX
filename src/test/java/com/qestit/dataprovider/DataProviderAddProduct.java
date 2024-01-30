package com.qestit.dataprovider;

import com.qestit.constants.FrameworkConstants;
import com.qestit.helpers.ExcelHelpers;
import com.qestit.helpers.Helpers;
import org.testng.annotations.DataProvider;

public class DataProviderAddProduct {
    @DataProvider(name = "data_provider_add_product")
    public Object[][] dataAddProduct() {
        ExcelHelpers excelHelpers = new ExcelHelpers();
        Object[][] data = excelHelpers.getDataHashTable(Helpers.getCurrentDir() + FrameworkConstants.EXCEL_ECOMMERCE_DATA, "AddProduct", 2, 2);
        return data;
    }
}
