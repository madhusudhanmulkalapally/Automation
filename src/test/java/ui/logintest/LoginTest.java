package ui.logintest;

import com.saucelabs.saucerest.SauceREST;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.internal.annotations.ITest;
import ui.base.AbstractTest;
import ui.core.driver.Driver;
import ui.core.listeners.Listener;
import ui.core.pageactions.LoginPageActions;

import java.util.HashMap;
import java.util.Map;

@Listeners({Listener.class})
public class LoginTest extends AbstractTest {

    LoginPageActions login;

    @Test()
    public void loginTest() {
        login = new LoginPageActions(Driver.getDriver());
        boolean paybuttondisplayed = login.verifyPayButton();
        Assert.assertTrue(paybuttondisplayed);
    }


}
