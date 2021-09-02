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

    @FindBy(xpath = "//a[text()='Gmail']")
    protected WebElement gmailText;

    @FindBy(xpath = "//*[@id='input']")
    protected WebElement inputText;

    @FindBy(xpath = "//a[text()='Pay']")
    protected  WebElement payButton;
}
