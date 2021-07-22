package ui.core.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ui.core.driver.Driver;

public class LoginPage extends Driver {

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "")
    protected WebElement userNameField;
}
