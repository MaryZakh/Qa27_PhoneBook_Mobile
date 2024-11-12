package tests;

import config.AppiumConfig;
import models.Auth;
import models.Contact;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import screens.AuthScreen;
import screens.ContactListScreen;

public class AddNewContactTests extends AppiumConfig {

    @BeforeClass
    public void preCondition(){
        new AuthScreen(driver)
                .fillLoginRegistrationForm(Auth.builder().email("mara@gmail.com")
                        .password("Mmar123456$").build())
                .submitLogin()
                .isActivityTitleDisplayed("Contact list");
    }

    @Test
    public void createNewContactSuccess(){
        int i = (int) (System.currentTimeMillis()/1000)%3600;

        Contact contact = Contact.builder()
                .name("Simon")
                .lastName("Wow"+i)
                .email("wow"+i+"@gmail.com")
                .phone("6978455"+i)
                .address("NY")
                .description("Friend")
                .build();
        new ContactListScreen(driver)
                .openContactForm()
                .fillContactForm(contact)
                .submitContactForm()
                .isContactAddedByName(contact.getName(), contact.getLastName());

    }

    @Test
    public void createNewContactSuccessReq(){

    }

    @AfterClass
    public void postCondition(){
       new ContactListScreen(driver).logout();

    }
}
