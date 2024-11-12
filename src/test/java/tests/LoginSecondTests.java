package tests;

import config.AppiumConfig;
import models.Auth;
import org.testng.annotations.Test;
import screens.AuthScreen;

public class LoginSecondTests extends AppiumConfig {



    @Test
    public void loginSuccess(){
        new AuthScreen(driver)
                .fillEmail("mara@gmail.com")
                .fillPassword("Mmar123456$")
                .submitLogin()
                .isAccountOpened()
                .logout();
    }

    @Test
    public void loginSuccessModel(){
        new AuthScreen(driver)
                .fillLoginRegistrationForm(Auth.builder().email("mara@gmail.com").password("Mmar123456$").build())
                .submitLogin()
                .isAccountOpened()
                .logout();
    }

    @Test
    public void loginWrongEmail(){
        new AuthScreen(driver)
                .fillLoginRegistrationForm(Auth.builder().email("maragmail.com").password("Mmar123456$").build())
                .submitLoginNegative()
                .isErrorMessageContainsText("Login or Password incorrect");
    }
}
