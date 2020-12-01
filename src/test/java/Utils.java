import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class Utils {
    private static Config AppDriver;

    public static void clickAndWait(String button, String waitFor, String title) {
        Config.getInstance().getDriver().findElement(By.id(Config.BASE_ID + button)).click();
        Config.getInstance().wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.id(Config.BASE_ID + waitFor)));
        System.out.println("landed to " + title + " page");
    }

    public static void waitforSeconds(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }




}
