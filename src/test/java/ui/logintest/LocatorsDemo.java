package ui.logintest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class LocatorsDemo {

    public static void main(String args[]) {
        System.setProperty("webdriver.chrome.driver", "driver/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://google.com");
        driver.findElement(By.xpath("//a[text()='Gmail']")).click();
        driver.findElement(By.xpath("//a[@data-action='signin']")).click();


        //driver.findElement(By.id("input")).sendKeys("selenium");

    }
}
