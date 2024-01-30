package com.qestit.projects.ecommerce.models;

import lombok.Data;

@Data
public class CustomerModel {

    public static int row;

    public static String testCaseName = "TESTCASENAME";

    public static String companyName = "COMPANY_NAME";

    public static String owner = "OWNER";

    public static String city = "Cologne";
    
    public static String address = "Tierestrasse 10";

    public static String state = "STATE";

    public static String zip = "50676";

    public static String country = "COUNTRY";

    public static String phone = "015202518776";

    public static String website = "WEBSITE";

    public static String vat = "VAT";

    public static String customerGroup = "CUSTOMER_GROUP";

    public static String status = "STATUS";

    public static String application = "APPLICATION";

    public static String getApplication() {
        return application;
    }

    public static int getRow() {
        return row;
    }

    public static String getTestCaseName() {
        return testCaseName;
    }

    public static String getCompanyName() {
        return companyName;
    }

    public static String getOwner() {
        return owner;
    }

    public static String getAddress() {
        return address;
    }

    public static String getCity() {
        return city;
    }

    public static String getState() {
        return state;
    }

    public static String getZip() {
        return zip;
    }

    public static String getCountry() {
        return country;
    }

    public static String getPhone() {
        return phone;
    }

    public static String getWebsite() {
        return website;
    }

    public static String getVat() {
        return vat;
    }

    public static String getCustomerGroup() {
        return customerGroup;
    }

    public static String getStatus() {
        return status;
    }
}
