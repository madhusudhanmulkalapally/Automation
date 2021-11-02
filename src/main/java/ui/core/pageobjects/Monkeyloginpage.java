package ui.core.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Monkeyloginpage {
    WebDriver driver;
    public Monkeyloginpage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//a[text()='Log in']")
    protected WebElement loginLink;

    @FindBy(xpath = "//div[@class='two-column-header']//h1")
    protected WebElement accountText;

    @FindBy(id="username")
    protected WebElement userNameTextBox;

    public void clikOnLoginLink(){
        loginLink.click();
    }

    public String getAccountText() {
        String text = accountText.getText();
        return text;
    }

    public void enterUserName() {
        accountText.sendKeys("madhu");
    }
}
