package ui.core.driver;

import com.saucelabs.saucerest.SauceREST;
import com.saucelabs.saucerest.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.SessionId;
import utils.ConfigProperties;

import java.net.MalformedURLException;
import java.net.URL;

public class DriverManager {
    static SauceREST sauceREST;
    static String sessionID;
   // public static final String USERNAME = "oauth-madhus.te7-aeaf9";
    public static final String USERNAME = "oauth-pavanimulkalapally-83b21";
    //public static final String ACCESS_KEY = "44a7e556-4493-4bbb-845b-c3b3391c3637";
    public static final String ACCESS_KEY = "c8deacc4-90ef-4a20-8d2e-320a0c1917b2";
    public static final String URL = "https://" + USERNAME + ":" + ACCESS_KEY + "@ondemand.eu-central-1.saucelabs.com:443/wd/hub";

    private static final String HEADLESS = "headless";
    public static WebDriver createInstance(String browserName, String appUrl, String methodName)throws MalformedURLException  {
        {
            ConfigProperties configProperties = ConfigProperties.getInstance();
            String gridId = configProperties.getConfig("SELENIUM_GRID");
            final String browserMode = System.getProperty("mode");
            //String text = System.getProperty("browser");
            WebDriver driver = null;
            if(browserName.toLowerCase().equalsIgnoreCase("chrome")) {
                System.setProperty("webdriver.chrome.driver", "driver/chromedriver.exe");
                if(browserMode!=null && browserMode.equals(HEADLESS)) {
                    ChromeOptions chromeOptions = new ChromeOptions();
                    chromeOptions.addArguments("--headless");
                    driver = new ChromeDriver(chromeOptions);
                }  else {
                    driver = new ChromeDriver();
                }
            }
            if(browserName.toLowerCase().equalsIgnoreCase("grid-zalenium")) {
                DesiredCapabilities cap = DesiredCapabilities.chrome();
                cap.setCapability("name", methodName);
                driver = new RemoteWebDriver(new URL("http://"+gridId+":80/wd/hub"), cap);
            }
            if(System.getProperty("browser").equalsIgnoreCase("saucechrome")) {
                System.out.println("sauce lab execution started==============================");
                DesiredCapabilities caps = DesiredCapabilities.chrome();
                caps.setCapability("platform", "Windows 10");
                caps.setCapability("version", "latest");
                caps.setCapability("name", methodName);
                caps.setCapability("extendedDebugging", "true");
                //caps.setCapability("tunnelIdentifier", "oauth-pavanimulkalapally-83b21_tunnel_id");

                driver = new RemoteWebDriver(new URL(URL), caps);
                sessionID = ((RemoteWebDriver) driver).getSessionId().toString();
                System.out.println("Session Id is: " + sessionID);
            }

            if(System.getProperty("browser").equalsIgnoreCase("devicelab")) {
                DesiredCapabilities caps = DesiredCapabilities.android();
                caps.setCapability("appiumVersion", "1.9.1");
                caps.setCapability("deviceName","Samsung Galaxy Tab S3 GoogleAPI Emulator");
                caps.setCapability("deviceOrientation", "portrait");
                caps.setCapability("browserName", "");
                caps.setCapability("platformVersion","8.1");
                caps.setCapability("platformName","Android");
                caps.setCapability("app","storage:filename=Android.SauceLabs.Mobile.Sample.app.2.7.1.apk");
                driver = new RemoteWebDriver(new URL(URL), caps);
            }
            if(System.getProperty("browser").equalsIgnoreCase("emulator")) {
                DesiredCapabilities caps = DesiredCapabilities.android();
                caps.setCapability("appiumVersion", "1.9.1");
                caps.setCapability("deviceName","Samsung Galaxy S9 FHD GoogleAPI Emulator");
                caps.setCapability("deviceOrientation", "portrait");
                caps.setCapability("browserName", "Chrome");
                caps.setCapability("platformVersion", "9.0");
                caps.setCapability("platformName","Android");
                driver = new RemoteWebDriver(new URL(URL), caps);
            }
           driver.navigate().to("https://qgo.carecredit.com/consumer/providersearch");
            //driver.navigate().to("https://www.google.com");
            return driver;
        }
    }

}
