package screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import java.util.List;


public class ContactListScreen extends BaseScreen {
    public ContactListScreen(AppiumDriver<AndroidElement> driver) {
        super(driver);
    }

    @FindBy(xpath = "//*[@resource-id = 'com.sheygam.contactapp:id/action_bar']/android.widget.TextView")
    AndroidElement activityTextView;

    @FindBy(xpath = "//*[@content-desc='More options']")
    AndroidElement menuOptions;

    @FindBy(xpath = "//*[@text='Logout']")
    AndroidElement logoutButton;

    @FindBy(xpath = "//*[@content-desc='add']")
    AndroidElement plusBtn;

    @FindBy(xpath = "//*[@resource-id='com.sheygam.contactapp:id/rowName']")
    List<AndroidElement> contactNameList;

    @FindBy(xpath = "//*[@content-desc='More options']")
    List<AndroidElement> menuOptionsList;

    @FindBy(xpath = "//*[@resource-id='com.sheygam.contactapp:id/rowContainer']")
    List<AndroidElement> contactList;

    @FindBy(id = "android:id/button1")
    AndroidElement yesBtn;

    @FindBy(id = "com.sheygam.contactapp:id/emptyTxt")
    AndroidElement noContactHereTextView;

    int countBefore;
    int countAfter;

    public boolean isActivityTitleDisplayed(String text) {
        // return activityTextView.getText().contains("Contact list");
        return isShouldHave(activityTextView, text, 8);
    }

    public AuthScreen logout() {
        if (activityTextView.getText().equals("Contact list")) {
            menuOptions.click();
            logoutButton.click();
        }
        return new AuthScreen(driver);
    }

    public AuthScreen logout2() {
        if (isElementDisplayed(menuOptions)) {
            menuOptions.click();
            logoutButton.click();
        }
        return new AuthScreen(driver);
    }

    public AuthScreen logout3() {
        if (isElementPresentInList(menuOptionsList)) {
            menuOptions.click();
            logoutButton.click();
        }
        return new AuthScreen(driver);
    }


    public ContactListScreen isAccountOpened() {
        Assert.assertTrue(isActivityTitleDisplayed("Contact list"));
        return this;
    }

    public AddNewContactScreen openContactForm() {
        if (activityTextView.getText().equals("Contact list"))
            plusBtn.click();
        return new AddNewContactScreen(driver);
    }

    public ContactListScreen isContactAddedByName(String name, String lastName) {
        isShouldHave(activityTextView, "Contact list", 5);
        System.out.println("size of list" + contactNameList.size());
        boolean isPresent = false;

        for (AndroidElement element : contactNameList)
            if (element.getText().equals(name + " " + lastName)) {
                isPresent = true;
                break;
            }
        Assert.assertTrue(isPresent);
        return this;

    }

    public ContactListScreen deleteFirstContact() {
        isActivityTitleDisplayed("Contact list");

        countBefore = contactList.size();
        System.out.println(countBefore);

        AndroidElement first = contactList.get(0);
        Rectangle rectangle = first.getRect();
        int xFrom = rectangle.getX() + rectangle.getWidth() / 8;
        int y = rectangle.getY() + rectangle.getHeight() / 2;
        int xTo = rectangle.getX() + (rectangle.getWidth() / 8) * 7;

        TouchAction<?> touchAction = new TouchAction<>(driver);
        touchAction.longPress(PointOption.point(xFrom, y))
                .moveTo(PointOption.point(xTo, y))
                .release().perform();
        should(yesBtn, 8);
        yesBtn.click();
        pause(3000);
        countAfter = contactList.size();
        System.out.println(countAfter);

        return this;
    }

    public ContactListScreen isListSizeLessOnOne() {
        Assert.assertEquals(countBefore - countAfter, 1);
        return this;
    }

    public ContactListScreen removeAllContacts() {
        pause(1000);
        while (contactList.size() > 0) {
            deleteFirstContact();
        }
        return this;
    }

    public void isNoContactsHere() {
        isShouldHave(noContactHereTextView,"No Contacts. Add One more!",10);
    }
}
