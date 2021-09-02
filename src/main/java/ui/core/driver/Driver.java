package ui.core.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Driver {

    WebDriver driver;

    public Driver() {

    }

    public Driver(WebDriver driver) {
        PageFactory.initElements(driver,this);
        this.driver = driver;
    }

    private static ThreadLocal<WebDriver> webDriver = new ThreadLocal<WebDriver>();

    public static WebDriver getDriver(){
        return webDriver.get();
    }

    public static void setWebDriver(WebDriver driver) {
        webDriver.set(driver);
    }


    protected  void smallExplitwait(WebElement element){
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOf(element));

    }

}
