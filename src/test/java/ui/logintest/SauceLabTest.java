package ui.logintest;

import org.openqa.selenium.By;
import org.openqa.selenium.InvalidElementStateException;
import ui.core.BaseTest;
import java.net.MalformedURLException;
import java.rmi.UnexpectedException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import ui.core.pageobjects.SaucelabPage;

public class SauceLabTest extends BaseTest {

    @Test()
    public void verifyLinkTest()
            throws MalformedURLException, InvalidElementStateException, UnexpectedException, InterruptedException {
        //create webdriver session
        this.createDriver();
        WebDriver driver = this.getWebDriver();
        SaucelabPage saucelabPage = new SaucelabPage(driver);
        driver.get("https://qgo.carecredit.com/consumer/providersearch");
        Thread.sleep(9000);
       Assert.assertTrue(driver.findElement(By.xpath("//a[text()='Pay']")).isDisplayed());


    }
}
