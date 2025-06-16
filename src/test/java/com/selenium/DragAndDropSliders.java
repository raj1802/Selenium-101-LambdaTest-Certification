package com.selenium;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.*;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

public class DragAndDropSliders {

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
        ltOptions.put("build", "Test Scenario 2");
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
    public void TestScenario2() throws InterruptedException {

        driver.get("https://www.lambdatest.com/selenium-playground/");
        driver.manage().window().maximize();
        Thread.sleep(2000);

        WebElement DrageAndDroplink = driver.findElement(
                By.xpath("//a[@href='https://www.lambdatest.com/selenium-playground/drag-drop-range-sliders-demo']"));
        DrageAndDroplink.click();

        Thread.sleep(1000);
        WebElement slider3 = driver.findElement(By.xpath(".//*[@id='slider3']/div/input"));
        // js.executeScript("arguments[0].scrollIntoView(true);", slider3);
        Thread.sleep(1000);
        Actions move = new Actions(driver);
        Actions action = (Actions) move.dragAndDropBy(slider3, 99, 0);
        action.perform();

        WebElement Expected_Range = driver.findElement(By.xpath(".//*[@id='slider3']/div/output"));
        String Expe_range = Expected_Range.getText();
        String Actual_Range = "95";

        if (Expe_range.contains(Actual_Range)) {
            System.out.println("Range is matched");
        } else {
            System.out.println("Range is not matched!");
        }


    }

    @AfterMethod

    public void tearDown() {
        // driver.executeScript("lambda-status=" + Status);
        driver.quit();
    }



}
