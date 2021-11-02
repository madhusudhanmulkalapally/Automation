package ui.logintest;

import com.saucelabs.saucerest.SauceREST;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;
import org.testng.internal.annotations.ITest;
import ui.base.AbstractTest;
import ui.core.driver.Driver;
import ui.core.listeners.Listener;
import ui.core.pageactions.LoginPageActions;
import ui.core.pageobjects.Monkeyloginpage;
import ui.core.pageobjects.SignUpPageObjects;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class LoginTest {

//    LoginPageActions login;
//
//    @Test()
//    public void loginTest() {
//        login = new LoginPageActions(Driver.getDriver());
//        boolean paybuttondisplayed = login.verifyPayButton();
//        Assert.assertTrue(paybuttondisplayed);
//    }


    WebDriver driver;
    String expetedText = "Log in to your account";
    String expectedresult = "Create a FREE account";
    @BeforeTest
    public void comonForAllTest() {
        System.out.println("this is common for all the test");
    }

    @BeforeMethod
    public void openURL() throws MalformedURLException {
//        System.setProperty("webdriver.chrome.driver", "driver/chromedriver.exe");
//        driver = new ChromeDriver();
        String USERNAME = "oauth-rudhira.katragadda-4673f";
        //public static final String ACCESS_KEY = "44a7e556-4493-4bbb-845b-c3b3391c3637";
        String ACCESS_KEY = "bbf3a26d-ac8a-4a69-a63f-577065766969";
        String URL1 = "https://" + USERNAME + ":" + ACCESS_KEY + "@ondemand.eu-central-1.saucelabs.com:443/wd/hub";
        String URL = "https://oauth-rudhira.katragadda-4673f:bbf3a26d-ac8a-4a69-a63f-577065766969@ondemand.us-west-1.saucelabs.com:443/wd/hub";

//        ChromeOptions browserOptions = new ChromeOptions();
//        browserOptions.setCapability("platformName", "Windows 10");
//        browserOptions.setCapability("browserVersion", "latest");
//        browserOptions.setCapability("name", "demo name");
//       Map<String, Object> sauceOptions = new HashMap<>();
//        sauceOptions.s("name", "demo name");
//        browserOptions.setCapability("sauce:options", sauceOptions);
//
//        driver = new RemoteWebDriver(new URL(URL), browserOptions);
//        String sessionID = ((RemoteWebDriver) driver).getSessionId().toString();
        DesiredCapabilities caps = DesiredCapabilities.chrome();
        caps.setCapability("platform", "Windows 10");
        caps.setCapability("version", "latest");
        caps.setCapability("name", "Demo Test");
        caps.setCapability("extendedDebugging", "true");
        //caps.setCapability("tunnelIdentifier", "oauth-pavanimulkalapally-83b21_tunnel_id");

        driver = new RemoteWebDriver(new URL(URL), caps);
        String sessionID = ((RemoteWebDriver) driver).getSessionId().toString();
        System.out.println("sessionID: "+sessionID);
        driver.get("https://www.surveymonkey.com/");

    }

    @Test()
    public void verifyLoginToYourAccountTextOnSinInPageDemo1() throws InterruptedException {
        Monkeyloginpage mpage = new Monkeyloginpage(driver);
        Thread.sleep(5000);
        mpage.clikOnLoginLink();
        Thread.sleep(5000);
        String actualResult = mpage.getAccountText();
        Thread.sleep(5000);
        Assert.assertEquals(expetedText,actualResult);
    }

    @Test(enabled = false)
    public void verifyCreateFreeAccountOnLoginPage() throws InterruptedException {
        SignUpPageObjects mpage = new SignUpPageObjects(driver);
        Thread.sleep(5000);
        mpage.clickOnSignUp();
        Thread.sleep(5000);
        String actualtext = mpage.getTextFreeAccount();
        Thread.sleep(5000);
        Assert.assertEquals(actualtext,expectedresult);
    }

    @AfterMethod
    public void closeBrowser() {
        driver.quit();
    }



}
