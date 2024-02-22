package com.qestit.common;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ThreadGuard;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.qestit.driver.DriverManager;
import com.qestit.driver.TargetFactory;
import com.qestit.helpers.PropertiesHelpers;
import com.qestit.listeners.TestListener;
import com.qestit.projects.ecommerce.BasePage;

@Listeners({TestListener.class})
public class BaseTest extends BasePage {

	@BeforeMethod
    public void createDriver() {
        WebDriver driver = ThreadGuard.protect(new TargetFactory().createInstance());
        DriverManager.setDriver(driver);
        driver.manage().window().maximize();
    }

    @AfterMethod(alwaysRun = true)
    public void closeDriver() {
        DriverManager.quit();
    }

    public WebDriver createBrowser() {
        PropertiesHelpers.loadAllFiles();
        WebDriver driver = ThreadGuard.protect(new TargetFactory().createInstance());
        driver.manage().window().maximize();
        DriverManager.setDriver(driver);
        return DriverManager.getDriver();
    }


}
