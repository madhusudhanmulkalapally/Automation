package ui.logintest;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import ui.base.AbstractTest;
import ui.core.driver.Driver;
import ui.core.listeners.Listener;
import ui.core.pageactions.LoginPageActions;

@Listeners({Listener.class})
public class MobileDemo extends AbstractTest {

    LoginPageActions login;

    @Test(enabled = true, description="loginTestDemo")
    public void loginTestdemo() {
        login = new LoginPageActions(Driver.getDriver());
        login.clickOnGmail();
        System.out.print("test passed");

    }
}
