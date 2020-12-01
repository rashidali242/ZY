import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class Demo {
    //static Config config;
    static String BASE = "io.neets.hercules";
    static String BASE_ID = "io.neets.hercules:id/";
    public WebDriverWait wait;
    static Config config;

    private static AndroidDriver driver;
    @BeforeClass
    public void setUp() throws MalformedURLException {

        driver = Config.getInstance().getDriver();
        DesiredCapabilities cap = new DesiredCapabilities();

        cap.setCapability("deviceName", "Android Emulator");
        cap.setCapability("platformName", "Android");
        cap.setCapability("platformVersion","11.0");
        cap.setCapability("appPackage", BASE);
        cap.setCapability("appActivity", "io.neets.hercules.MainActivity");
        cap.setCapability("app" , System.getProperty("user.dir")+"/apps/Hercules.apk");
        //URL url = new URL("http://127.0.0.1:4723/wd/hub");
        driver = new AndroidDriver(new URL("http://localhost:4723/wd/hub"), cap);
        System.out.println("landed to Splash screen");
        Utils.waitforSeconds(5);
        System.out.println("Landed to Home Screen");
        System.out.println("Configuration : OK");
        driver.findElement(By.id(Config.BASE_ID + "cvControlSystem")).click();
        Utils.waitforSeconds(2);
    }

    @Test
    void TPCSNext() {
        driver.findElement(By.id(Config.BASE_ID + "swIp")).click();
        Utils.waitforSeconds(3);
        driver.findElementByXPath("//android.widget.EditText[@text='IP address']").sendKeys("192.168.3.5");
        driver.findElementByXPath("//android.widget.EditText[@text='Subnet']").sendKeys("255.255.254.0");
        driver.findElementByXPath("//android.widget.EditText[@text='Gateway']").sendKeys("192.168.3.10");
        driver.findElementByXPath("//android.widget.EditText[@text='DNS']").sendKeys("8.8.8.8");
        Utils.waitforSeconds(3);
        driver.findElement(By.id(Config.BASE_ID + "btnNext")).click();
        System.out.println("success cases");

        //Back to Splash Screen and then Home Button
        driver.findElement(By.id(Config.BASE_ID + "ivToolbarLogo")).click();
        Utils.waitforSeconds(2);
        driver.findElement(By.id(Config.BASE_ID + "btnNext")).click();
        Utils.waitforSeconds(3);
    }

    @AfterClass
    public void tearDown()
    {
        driver.quit();
    }
}