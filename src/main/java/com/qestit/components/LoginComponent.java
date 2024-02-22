package com.qestit.components;

import static com.qestit.keywords.WebAction.clickElement;
import static com.qestit.keywords.WebAction.getLocator;
import static com.qestit.keywords.WebAction.setText;
import static com.qestit.keywords.WebAction.verifyElementVisible;

import org.openqa.selenium.By;

public class LoginComponent {
	// Assuming you have locators for the username, password fields, and the login button
    private By usernameLocator = By.id("username");
    private By passwordLocator = By.id("password");
    private By loginButtonLocator = By.id("loginButton");
    

    
    public void enterUsername(String username) {
        setText(usernameLocator, username);
    }

    public void enterPassword(String password) {
    	setText(passwordLocator, password);
    }

    public void clickLogin() {
        clickElement(loginButtonLocator);
    }

    public boolean isLoginSuccessful() {
        // Logic to determine if the login was successful
        // For example, checking if a logout button is present
    	//here another approach to use the locators.properties file
        return verifyElementVisible(getLocator("logoutButtonLocator"));
    }
}
