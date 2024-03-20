/*
 * Copyright (c) 2023 QESTIT
 * QesteniumX
 */

package com.qestit.projects.ecommerce.testcases;

import com.qestit.constants.FrameworkConstants;
import com.qestit.data.IJsonDataManager;
import com.qestit.helpers.*;
import com.qestit.keywords.WebAction;
import com.qestit.report.TelegramManager;
import com.qestit.utils.*;
import org.testng.annotations.Test;

public class TestSimpleCode {
	IJsonDataManager jsonManager;
	
	public TestSimpleCode() {
		jsonManager = new JsonHelpers();
	}
    @Test
    public void testReadDataFromJSON_01() {
        //JSONPath Online Evaluator - https://jsonpath.com/

        //Get book Firstly
        System.out.println(JsonUtils.getData("$.store.book[0]"));
        System.out.println(JsonUtils.getData("$['store']['book'][0]"));


        //Get category of the first Book
        System.out.println(JsonUtils.getData("$.store.book[0].category"));
        System.out.println(JsonUtils.getData("$['store']['book'][0].category"));

        //Get bicycle
        System.out.println(JsonUtils.getData("$.store.bicycle"));
        System.out.println(JsonUtils.getData("$['store']['bicycle']"));
    }

    @Test
    public void testReadDataFromJSON_02() {
        JsonUtils.setJsonFile("src/test/resources/datajson/tools.json");

        //Get name
        System.out.println(JsonUtils.getData("$.tool.jsonpath.creator.name"));

        //Get location
        System.out.println(JsonUtils.getData("$.tool.jsonpath.creator.name"));
    }

    @Test
    public void testReadDataFromJSON_03() {
        //Create JsonHelpers to run parallel
        JsonHelpers jsonHelpers = new JsonHelpers();

        //Set Json file
        jsonManager.setJsonFile("src/test/resources/datajson/book.json");

        //Get title of book
        System.out.println(jsonManager.getData("$.book[1].title"));

        //Get cheap
        System.out.println(jsonManager.getData("$.['price range'].cheap"));
    }


    @Test
    public void testDataFaker() {
        //DataFakerUtils.setLocate("de");
        System.out.println(DataFakerUtils.getFaker().address().fullAddress());
        System.out.println(DataFakerUtils.getFaker().job().title());
    }

    @Test
    public void testZipFolder() {
        ZipUtils.zipFolder("reports/ExtentReports", "ExtentReports");
    }

    @Test
    public void testZipFile() {
        ZipUtils.zipFile("src/test/resources/pdf-config.json", "pdf-config");
    }

    @Test
    public void testUnZipFile() {
        //ZipUtils.unZip("pdf-config.zip", "target/pdf-config");
        ZipUtils.unZipFile("pdf-config.zip", "target/abc/pdf-config");
    }

    @Test
    public void testTelegramBotMessage() {
        TelegramManager.sendReportPath();
        //TelegramManager.sendFilePath("logs/applog.log");
    }

    @Test
    public void testGetOSInfo() {
        System.out.println(BrowserInfoUtils.getOSInfo());
        System.out.println(BrowserInfoUtils.isWindows());
        System.out.println(BrowserInfoUtils.isMac());
    }

    @Test
    public void testGetXpathDynamic() {
        String xpath1 = ObjectUtils.getXpathDynamic("//button[normalize-space()='%s']", "Login");
        WebAction.logConsole(xpath1);

        String xpath2 = ObjectUtils.getXpathDynamic("//button[normalize-space()='%s']//div[%d]//span[%d]", "Login", 2, 10);
        WebAction.logConsole(xpath2);

        PropertiesHelpers.loadAllFiles();
        String xpath3 = ObjectUtils.getXpathDynamic(ObjectUtils.getXpathValue("buttonDynamicXpath"), "Login");
        WebAction.logConsole(xpath3);
    }

    @Test
    public void testRemoveAccent() {
    	String word="Köln";
    	String newWord=LanguageUtils.removeAccent(word);
        WebAction.logConsole(LanguageUtils.removeAccent(word));
        System.out.println("The word after removing accent is "+ newWord);
    }

    @Test
    public void testMakeSlug() {
        WebAction.logConsole(Helpers.makeSlug("QESTIT Automation Testing"));
    }

    @Test
    public void testReadFileJSON() {
        WebAction.logConsole(JsonUtils.get("url"));
        WebAction.logConsole(JsonUtils.get("BROWSER"));
        WebAction.logConsole(JsonUtils.get("button"));
    }

    @Test
    public void testGetCurrentDirectory() {
        WebAction.logConsole(Helpers.getCurrentDir());
    }

    @Test
    public void testSplitString() {
        String s1 = "Automation, Testing, Selenium, Java";

        for (String arr : Helpers.splitString(s1, ", ")) {
            WebAction.logConsole(arr);
        }
    }

    @Test
    public void testEncryptDecryptData() {
        String pass = "riseDemo";
        //Encrypt password
        WebAction.logConsole(DecodeUtils.encrypt(pass));
        //Decrypt password
        WebAction.logConsole(DecodeUtils.decrypt(DecodeUtils.encrypt(pass)));
    }

    @Test
    public void testCreateFolder() {
        Helpers.createFolder("src/test/resources/TestCreateNewFolder");
    }

    @Test
    public void testPropertiesFile() {
        PropertiesHelpers.loadAllFiles();
        //  Handle Properties file
        WebAction.logConsole(PropertiesHelpers.getValue("BROWSER"));
        WebAction.logConsole(PropertiesHelpers.getValue("URL_ECOMMERCE"));
        WebAction.logConsole(PropertiesHelpers.getValue("AUTHOR"));
        WebAction.logConsole(FrameworkConstants.EXCEL_DATA_FILE_PATH);
        WebAction.logConsole(Helpers.getCurrentDir() + PropertiesHelpers.getValue("EXCEL_DATA_FILE_PATH"));
//        PropertiesHelpers.setFile("src/test/resources/config/data.properties");
//        PropertiesHelpers.setValue("base_url", "https://qestit.com/");
    }

    @Test
    public void testGetCurrentDateTime() {
        WebAction.logConsole(DateUtils.getCurrentDateTime());
    }

    @Test
    public void testReadAndWriteTxtFile() {
        PropertiesHelpers.loadAllFiles();
        //Read all data
        FileHelpers.readTxtFile(PropertiesHelpers.getValue("TXT_FILE_PATH"));
        //Read data by line number
        WebAction.logConsole(FileHelpers.readLineTxtFile(PropertiesHelpers.getValue("TXT_FILE_PATH"), 0));
    }

    @Test
    public void testExcelFile1() {
        PropertiesHelpers.loadAllFiles();
        WebAction.logConsole(Helpers.getCurrentDir() + PropertiesHelpers.getValue("EXCEL_DATA_FILE_PATH"));
        //  Handle Excel file
        ExcelHelpers excelHelpers = new ExcelHelpers();
        excelHelpers.setExcelFile(Helpers.getCurrentDir() + PropertiesHelpers.getValue("EXCEL_DATA_FILE_PATH"), "SignIn");
        WebAction.logConsole(excelHelpers.getCellData(1, "EMAIL"));
        WebAction.logConsole(excelHelpers.getCellData(1, "PASSWORD"));
        excelHelpers.setCellData("pass", 1, "EXPECTED_TITLE");
    }

    @Test()
    public void testExcelFile2() throws Exception {
        PropertiesHelpers.loadAllFiles();
        ExcelHelpers excelHelpers = new ExcelHelpers();
        WebAction.logConsole(excelHelpers.getDataHashTable(Helpers.getCurrentDir() + FrameworkConstants.EXCEL_DATA_FILE_PATH, "SignIn", 1, 2));
    }

//    @Test
//    public void connectDBMySQL() throws SQLException, ClassNotFoundException {
//
//        Connection connection = DatabaseHelpers.getMySQLConnection("sql6.freesqldatabase.com", "sql6464696", "sql6464696", "LIAGIkgd44");
//
//        // Create Statement object.
//        Statement statement = connection.createStatement();
//
//        String sql = "SELECT * FROM `company`";
//
//        // Execute the SQL statement that returns the ResultSet object.
//        ResultSet rs = statement.executeQuery(sql);
//
//        WebAction.logConsole(rs);
//
//        // Use while to Loop the returned results.
//        while (rs.next()) {// Move the cursor down to the next record.
//            int Id = rs.getInt(1);
//            String COMPANY_ID = rs.getString("COMPANY_ID");
//            String COMPANY_NAME = rs.getString("COMPANY_NAME");
//            String COMPANY_CITY = rs.getString("COMPANY_CITY");
//            WebAction.logConsole("--------------------");
//            WebAction.logConsole("COMPANY_ID:" + COMPANY_ID);
//            WebAction.logConsole("COMPANY_NAME:" + COMPANY_NAME);
//            WebAction.logConsole("COMPANY_CITY:" + COMPANY_CITY);
//        }
//
//        // Close connection
//        connection.close();
//    }

}
