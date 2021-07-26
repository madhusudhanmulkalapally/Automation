package ui.core.pageactions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import ui.core.driver.Driver;
import ui.core.pageobjects.LoginPage;

public class LoginPageActions extends LoginPage {
    public LoginPageActions(WebDriver driver) {
        super(driver);
    }

    public void login(String userName, String passWord) {
        PageFactory.initElements(Driver.getDriver(), this);
        userNameField.sendKeys("fsd");
    }

    public void clickOnGmail() {
        smallExplitwait(gmailText);
        gmailText.click();
        System.out.println("clicked on the gmail");
    }
}
