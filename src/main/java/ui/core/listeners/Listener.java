package ui.core.listeners;


import io.qameta.allure.Attachment;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.poi.util.IOUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;
import ui.core.driver.Driver;
import ui.core.driver.DriverManager;

import java.io.*;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import static ui.core.Base.baseURI;
import static ui.core.Base.driver;

@Slf4j
public class Listener implements IInvokedMethodListener {
    @Override
    public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
        if(method.isConfigurationMethod()) {
            //String browserName = System.getProperty("mode");
            String browserName = "sauce";
            if(browserName==null) {
                browserName = "local-zelenium";
            }
            try {
                driver = DriverManager.createInstance(browserName, baseURI, method.getTestMethod().getMethodName());
                System.out.println("Initializing webdriver browser session ---> Thread ID: "+Thread.currentThread().getId());
                System.out.println("running test ---> "+method.getTestMethod().getMethodName());
                Driver.setWebDriver(driver);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }


    }
    @Override
    public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
        if(method.isTestMethod())
        {
            driver = Driver.getDriver();
            if(driver !=null) {
                try {
                    takeScreenShotOnFailure(testResult);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                System.out.println("closing webdriver session: "+Thread.currentThread().getId());
                driver.quit();
            }
        }
    }

    public void takeScreenShotOnFailure(ITestResult testResult) throws IOException {
        if(testResult.getStatus() == ITestResult.FAILURE) {
            File screenShot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            File destination = new File("target/failure-screenshots/"+testResult.getName()+"_"+
                    new SimpleDateFormat("dd-mm-yyy HH-mm-ss").format(new Date()) + ".png");
            FileUtils.copyFile(screenShot, destination);
            InputStream screenShotStream  = new FileInputStream(destination);
            byte[] screen = IOUtils.toByteArray(screenShotStream);


            saveScreenShot(screen);

        }
    }

    @Attachment(value="screen shot of the failure", type = "image/png")
    public byte[] saveScreenShot(byte[] screenShot) {
        return screenShot;
    }
}
