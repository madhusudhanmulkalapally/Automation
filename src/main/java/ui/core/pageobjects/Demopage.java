package ui.core.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Demopage {
    WebDriver driver;
    public Demopage(WebDriver driver){

        this.driver = driver;
        PageFactory.initElements(driver, this);

    }

    @FindBy(xpath="//a[text()='Log in']")
    public WebElement loginLink;

    @FindBy(xpath="//div[@class='two-column-header']//h1")
    public WebElement loginToYourAccountText;

    public void clickOnloginLink() {

        loginLink.click();
    }

    public String getTextOfAccount() {
        return loginToYourAccountText.getText();

    }
}
