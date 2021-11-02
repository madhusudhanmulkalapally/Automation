package ui.core.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignUpPageObjects {

    WebDriver driver;

    public SignUpPageObjects(WebDriver driver) {
        this.driver = driver;

        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//a[text()='Sign up']")
    protected WebElement signUpLink;

    @FindBy(xpath = "//h1[text()='Create a FREE account']")
    protected  WebElement freeAccountText;

    public void clickOnSignUp() {
        signUpLink.click();
    }

    public String getTextFreeAccount() {
        return freeAccountText.getText();
    }
}
