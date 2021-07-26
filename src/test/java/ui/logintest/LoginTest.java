package ui.logintest;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import ui.base.AbstractTest;
import ui.core.driver.Driver;
import ui.core.listeners.Listener;
import ui.core.pageactions.LoginPageActions;

@Listeners({Listener.class})
public class LoginTest extends AbstractTest {

    LoginPageActions login;

    @Test(enabled = true, description="testdescriptipn")
    public void loginTest() {
        login = new LoginPageActions(Driver.getDriver());
        System.out.print("test passed");
        //login.login("mad", "sdf");
        login.clickOnGmail();

    }


}
