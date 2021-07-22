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
            driver.navigate().to("https://www.google.com");
            return driver;
        }
    }
}
