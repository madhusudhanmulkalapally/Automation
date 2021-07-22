package ui.base;

import org.testng.annotations.BeforeSuite;
import ui.core.Base;
import utils.ConfigProperties;

public class AbstractTest extends Base {
    @BeforeSuite
    public void setup()
    {
        String defaultAccount = configProperties.getConfig("defaultaccount");
    }}
