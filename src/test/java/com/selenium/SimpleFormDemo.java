package com.selenium;

import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class SimpleFormDemo {

    private RemoteWebDriver driver;
    private String Status = "passed";

    @BeforeMethod  @Parameters("browser")
    public void setup(String browser) throws MalformedURLException {

        String hubURL = "https://hub.lambdatest.com/wd/hub";

        String[] Tags = new String[] { "Feature", "Falcon", "Severe" };

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("browserName", browser);
        capabilities.setCapability("browserVersion", "latest");

        HashMap<String, Object> ltOptions = new HashMap<String, Object>();
        ltOptions.put("user", "dheeraj1854");
        ltOptions.put("accessKey", "LT_zr9uqvmuvIlKDV4tY5SfVaxOWA0AuJiQf7nVZEqDMiBYC67");
        ltOptions.put("build", "Test Scenario 1");
        ltOptions.put("name", this.getClass().getName());
        ltOptions.put("platformName", "Windows 10");
        ltOptions.put("seCdp", true);
        ltOptions.put("selenium_version", "4.0.0");
        ltOptions.put("tags", Tags);
        capabilities.setCapability("LT:Options", ltOptions);

        driver = new RemoteWebDriver(new URL(hubURL), capabilities);
        System.out.println(driver);
    }

    @Test

    public void TestScenario1() throws InterruptedException {

        driver.get("https://www.lambdatest.com/selenium-playground/");
        driver.manage().window().maximize();
        Thread.sleep(2000);

        WebElement SimpleFormDemoLink = driver
                .findElement(By.xpath("//a[@href='https://www.lambdatest.com/selenium-playground/simple-form-demo']"));
        SimpleFormDemoLink.click();

        String Expectedurl = driver.getCurrentUrl();
        String Actualurl = "simple-form-demo";

        if (Expectedurl.contains(Actualurl)) {
            System.out.println("URL matched");
        } else {
            System.out.println("URL does not matched!");
        }

        String message = "Welcome to LambdaTest.";
        WebElement mess_send = driver
                .findElement(By.xpath("//div/input[@class='border border-gray-550 w-full h-35 rounded px-10']"));
        Thread.sleep(1000);
        mess_send.sendKeys(message);

        Thread.sleep(1000);
        WebElement button = driver.findElement(By.xpath(
                "//div/button[@class='mt-20 mb-10 bg-lambda-900 hover:bg-transparent hover:text-lambda-900 border border-lambda-900 text-white p-10 rounded focus:outline-none w-180']"));
        button.click();

        WebElement your_mess = driver
                .findElement(By.xpath("//div[@class='w-4/12 smtablet:w-full rigth-input']/div/p[1]"));
        String print_mess = your_mess.getText();

        if (print_mess.contains(message)) {
            System.out.println("Message is matched");
        } else {
            System.out.println("Message is not matched!");
        }

    }

    @AfterMethod
    public void tearDown() {
        driver.executeScript("lambda-status=" + Status);
        driver.quit();
    }

}
