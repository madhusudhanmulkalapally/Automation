package ui.core.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import utils.ConfigProperties;

import java.net.MalformedURLException;
import java.net.URL;

public class DriverManager {
    public static final String USERNAME = "oauth-madhus.te7-aeaf9";
    public static final String ACCESS_KEY = "44a7e556-4493-4bbb-845b-c3b3391c3637";
    public static final String URL = "https://" + USERNAME + ":" + ACCESS_KEY + "@ondemand.eu-central-1.saucelabs.com:443/wd/hub";

    private static final String HEADLESS = "headless";
    public static WebDriver createInstance(String browserName, String appUrl, String methodName)throws MalformedURLException  {
        {
            ConfigProperties configProperties = ConfigProperties.getInstance();
            String gridId = configProperties.getConfig("SELENIUM_GRID");
            final String browserMode = System.getProperty("mode");
            WebDriver driver = null;
            if(browserName.toLowerCase().contains("chrome")) {
                System.setProperty("webdriver.chrome.driver", "driver/chromedriver.exe");
                if(browserMode!=null && browserMode.equals(HEADLESS)) {
                    ChromeOptions chromeOptions = new ChromeOptions();
                    chromeOptions.addArguments("--headless");
                    driver = new ChromeDriver(chromeOptions);
                }  else {
                    driver = new ChromeDriver();
                }
            }
            if(browserName.toLowerCase().contains("grid-zalenium")) {
                DesiredCapabilities cap = DesiredCapabilities.chrome();
                cap.setCapability("name", methodName);
                driver = new RemoteWebDriver(new URL("http://"+gridId+":80/wd/hub"), cap);
            }
            if(browserName.toLowerCase().contains("sauce")) {
                DesiredCapabilities caps = DesiredCapabilities.chrome();
                caps.setCapability("platform", "Windows 10");
                caps.setCapability("version", "latest");
                caps.setCapability("name", "Test1");
                caps.setCapability("extendedDebugging", "true");
                //caps.setCapability("tunnelIdentifier", "be60954fd81249779be34b58c67fd1bd");
                System.out.println("sauce lab execution started");

                driver = new RemoteWebDriver(new URL(URL), caps);
            }
            driver.navigate().to("https://www.google.com");
            return driver;
        }
    }
}
