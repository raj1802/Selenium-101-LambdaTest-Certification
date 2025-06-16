package com.selenium;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration; // Import for Duration
import java.util.HashMap;

public class InputFormSubmit {

    private RemoteWebDriver driver;
    private String Status = "passed";

    @BeforeMethod
    @Parameters("browser")
    public void setup(String browser) throws MalformedURLException {

        String hubURL = "https://hub.lambdatest.com/wd/hub";

        String[] Tags = new String[] { "Feature", "Falcon", "Severe" };

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("browserName", browser);
        capabilities.setCapability("browserVersion", "latest");

        HashMap<String, Object> ltOptions = new HashMap<String, Object>();
        ltOptions.put("user", "dheeraj1854");
        ltOptions.put("accessKey", "LT_zr9uqvmuvIlKDV4tY5SfVaxOWA0AuJiQf7nVZEqDMiBYC67");
        ltOptions.put("build", "Test Scenario 3");
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
    public void TestScenario3() throws InterruptedException {

        driver.get("https://www.lambdatest.com/selenium-playground/");
        driver.manage().window().maximize();
        // No need for Thread.sleep(2000) here, let WebDriver manage load.

        WebElement InputFormLink = driver
                .findElement(By.xpath("//a[@href='https://www.lambdatest.com/selenium-playground/input-form-demo']"));
        InputFormLink.click();

        // Wait for the form to load after clicking the link
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Max wait of 10 seconds
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='text-right mt-20']/button")));


        WebElement submit = driver.findElement(By.xpath("//div[@class='text-right mt-20']/button"));
        submit.click();

        // No need for Thread.sleep(1000) here, we'll use waits.

        WebElement name = driver.findElement(By.xpath(
                "//div[@class='form-group w-4/12 smtablet:w-full text-section pr-20 smtablet:pr-0']/input[@type='text']"));
        String Expected_validation = name.getAttribute("validationMessage");
        String Actual_validation = "Please fill out this field.";
        Assert.assertEquals(Actual_validation, Expected_validation);

        if (Expected_validation.equals(Actual_validation)) {
            System.out.println("Validation is properly appear.");
        } else {
            System.out.println("Validation is not properly appear.");
        }

        name.sendKeys("TestName");

        WebElement email = driver.findElement(By.xpath(
                "//div[@class='form-group w-4/12 smtablet:w-full text-section pr-20 smtablet:pr-0']/input[@type='email']"));
        email.sendKeys("Test123@gmail.com");

        WebElement password = driver
                .findElement(By.xpath("//div[@class='form-group w-4/12 smtablet:w-full']/input[@type='password']"));
        password.sendKeys("Test@1234");

        WebElement company = driver.findElement(By.xpath("//*[@id=\"company\"]"));
        company.sendKeys("TestCompany");

        WebElement website = driver
                .findElement(By.xpath("//div[@class='form-group w-6/12 smtablet:w-full']/input[@id=\"websitename\"]"));
        website.sendKeys("Testdomain.com");

        WebElement country = driver.findElement(By.xpath(
                "//div[@class='form-group w-6/12 smtablet:w-full pr-20 smtablet:pr-0']/select[@name='country']"));
        Select select = new Select(country);
        select.selectByVisibleText("United States");

        WebElement city = driver
                .findElement(By.xpath("//div[@class='form-group w-6/12 smtablet:w-full']/input[@id='inputCity']"));
        city.sendKeys("TestCity");

        WebElement address1 = driver.findElement(By.xpath(
                "//div[@class='form-group w-6/12 smtablet:w-full pr-20 smtablet:pr-0']/input[@id='inputAddress1']"));
        address1.sendKeys("TestAddress1");

        WebElement address2 = driver
                .findElement(By.xpath("//div[@class='form-group w-6/12 smtablet:w-full']/input[@id='inputAddress2']"));
        address2.sendKeys("TestAddress2");

        WebElement state = driver.findElement(By.xpath(
                "//div[@class='form-group w-6/12 smtablet:w-full pr-20 smtablet:pr-0']/input[@id='inputState']"));
        state.sendKeys("TestState");

        WebElement zipcode = driver
                .findElement(By.xpath("//div[@class='form-group w-6/12 smtablet:w-full']/input[@id='inputZip']"));
        zipcode.sendKeys("360002");

        submit.click();


        WebElement successmessage = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[@class='loginform mt-20 p-20']/p[@class='success-msg hidden']")));

        String Actualmessage = successmessage.getText();
        String Expectedmessage = "Thanks for contacting us, we will get back to you shortly.";

        if (Actualmessage.equals(Expectedmessage)) {
            System.out.println("Success message is properly appear.");
        } else {
            System.out.println("Success message is not properly appear. Actual: " + Actualmessage);
        }
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}