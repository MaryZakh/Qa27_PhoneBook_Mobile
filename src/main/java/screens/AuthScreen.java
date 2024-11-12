package screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidElement;
import models.Auth;
import org.openqa.selenium.Alert;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;


public class AuthScreen extends BaseScreen {
    public AuthScreen(AppiumDriver<AndroidElement> driver) {
        super(driver);
    }

    @FindBy(xpath = "//*[@resource-id='com.sheygam.contactapp:id/inputEmail']")
    AndroidElement emailEditText;

    @FindBy(id = "com.sheygam.contactapp:id/inputPassword")
    AndroidElement passwordEditText;

    @FindBy(xpath = "//*[@text='LOGIN']")
    AndroidElement loginBtn;



    public AuthScreen fillEmail(String email) {
        //pause(4000);
        should(emailEditText, 10);
        type(emailEditText, email);
        return this;
    }

    public AuthScreen fillPassword(String password) {
        type(passwordEditText, password);
        return this;
    }

    public ContactListScreen submitLogin() {
        loginBtn.click();
        return new ContactListScreen(driver);
    }


    public AuthScreen fillLoginRegistrationForm(Auth auth) {
        should(emailEditText, 10);
        type(emailEditText,auth.getEmail());
        type(passwordEditText, auth.getPassword());

        return this;
    }

    public AuthScreen submitLoginNegative() {
        loginBtn.click();
        return this;
    }

    public AuthScreen isErrorMessageContainsText(String text) {
        checkAlertText(text);
        return this;
    }
}
