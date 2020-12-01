import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.FileInputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

public class Config {
    static Config config;
    static String BASE = "io.neets.hercules";
    static String BASE_ID = "io.neets.hercules:id/";
    public WebDriverWait wait;

    private static AndroidDriver driver;

    public static void main(String[] args) throws MalformedURLException, InterruptedException {
        try {
            FileInputStream _fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\resources\\global.properties");
            Properties _prop = new Properties();
            _prop.load(_fis);
            String _appName = (String) _prop.get("HerculesApp");

            File _fileFolderPath = new File("src");
            File _filepath = new File(_fileFolderPath, _appName);


            driver = Config.getInstance().getDriver();
            DesiredCapabilities cap = new DesiredCapabilities();

            /* ANDROID DEPENDENCIES START*/

            cap.setCapability("deviceName", "Android Emulator");
            cap.setCapability("platformName", "Android");
            cap.setCapability("platformVersion","11.0");
            cap.setCapability("automationName", "UiAutomator2");
            cap.setCapability(MobileCapabilityType.APP, _filepath.getAbsolutePath());
            URL url = new URL("http://127.0.0.1:4723/wd/hub");
            driver = new AndroidDriver(new URL("http://localhost:4723/wd/hub"), cap);
            System.out.println("landed to Splash screen");
            Utils.waitforSeconds(5);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Config getInstance() {
        if (config == null) {
            config = new Config();
        }
        return config;
    }

    public AndroidDriver getDriver() {
        return driver;
    }

}