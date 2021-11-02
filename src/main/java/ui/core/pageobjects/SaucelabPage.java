package ui.core.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ui.core.driver.Driver;

public class SaucelabPage {

    @FindBy(xpath = "")
    protected WebElement userNameField;

    @FindBy(xpath = "//a[text()='Gmail']")
    protected WebElement gmailText;

    @FindBy(xpath = "//*[@id='input']")
    protected WebElement inputText;

    @FindBy(xpath = "//a[text()='Pay']")
    protected  WebElement payButton;
    public WebDriver driver;

    public SaucelabPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean verifyPayButton() {
        //PageFactory.initElements(Driver.getDriver(), this);
        //smallExplitwait(payButton);
        return payButton.isDisplayed();

    }
}
