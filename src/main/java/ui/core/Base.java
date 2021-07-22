package ui.core;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Listeners;
import ui.core.listeners.Listener;
import utils.ConfigProperties;

@Listeners(Listener.class)
@Slf4j
public abstract class Base extends CoreUtils {

    public static ConfigProperties configProperties = new ConfigProperties();
    //public static String baseURI = configProperties.getConfig("Bassuri");
    public static String baseURI = "https://www.google.com";
    public static WebDriver driver;
    public static String basePath = "/";

    static {
        if(System.getProperty("env.type")==null){
            System.setProperty("env.type", "QA");
        } else if(System.getProperty("env.type").equalsIgnoreCase("QA")) {
            baseURI = configProperties.getConfig("Base_URI_QA");
            baseURI = basePath + "#/";
        }
    }

}
