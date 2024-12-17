import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import java.net.URL;
import java.time.Duration;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class GapAppTest extends GapMethods {

    static AppiumDriver driver;

    @BeforeTest
    public void setUp() throws Exception {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("appium:deviceName", "Nothing Phone (2)");
        caps.setCapability("appium:udid", "be77e3ff");
        caps.setCapability("platformName", "Android");
        caps.setCapability("appium:platformVersion", "14");
        caps.setCapability("appium:automationName", "uiAutomator2");
        caps.setCapability("appium:appPackage", "com.gap.mobile.gap");
        caps.setCapability("appium:appActivity", "com.gap.bronga.presentation.welcome.WelcomeFlowActivity");

        URL url = new URL("http://127.0.0.1:4723/");
        driver = new AppiumDriver(url, caps);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        System.out.println("Application Started");
    }

    @AfterTest
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void gapAddToCartTest() throws Exception {

        driver.findElement(new AppiumBy.ByAndroidUIAutomator("new UiSelector().resourceId(\"com.gap.mobile.gap:id/text_continue\")")).click();
        driver.findElement(new AppiumBy.ByAndroidUIAutomator("new UiSelector().resourceId(\"com.gap.mobile.gap:id/text_skip\")")).click();
        driver.findElement(new AppiumBy.ByAndroidUIAutomator("new UiSelector().resourceId(\"com.gap.mobile.gap:id/button_next\")")).click();
        driver.findElement(new AppiumBy.ByAndroidUIAutomator("new UiSelector().resourceId(\"com.android.permissioncontroller:id/permission_allow_foreground_only_button\")")).click();
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
        try{
            wait.until(ExpectedConditions.presenceOfElementLocated(new AppiumBy.ByAndroidUIAutomator("new UiSelector().resourceId(\"com.gap.mobile.gap:id/com_braze_inappmessage_modal_close_button\")"))).click();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        WebElement dashboardProduct = driver.findElement(new AppiumBy.ByAndroidUIAutomator("new UiSelector().description(\"The gift of cozy. Warm sherpa, cozy fleece, and our bestselling CashSoft knit.  Made for gifting, wearing, and staying in — together.\").instance(1)"));
        try {
            if (dashboardProduct.isDisplayed()) {
                dashboardProduct.click();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
            driver.findElement(new AppiumBy.ByAndroidUIAutomator("new UiSelector().text(\"Kids CashSoft Cable-Knit Sweater Hoodie\")")).click();
            closeButton(driver);
            Thread.sleep(3000);
            swipeUp(driver);
            wait.until(ExpectedConditions.presenceOfElementLocated(new AppiumBy.ByAndroidUIAutomator("new UiSelector().text(\"S (6/7)\")"))).click();
            closeButton(driver);
            driver.findElement(new AppiumBy.ByAndroidUIAutomator("new UiSelector().resourceId(\"com.gap.mobile.gap:id/btn_add_to_bag\")")).click();
            closeButton(driver);
            driver.findElement(new AppiumBy.ByAndroidUIAutomator("new UiSelector().resourceId(\"com.gap.mobile.gap:id/navigation_bar_item_icon_view\").instance(3)")).click();
            closeButton(driver);
            swipeUp(driver);

            WebElement shipping = driver.findElement(new AppiumBy.ByAndroidUIAutomator("new UiSelector().text(\"Shipping\")"));
            String txt = shipping.getText();
            System.out.println(txt);

            Assert.assertEquals("Shipping", txt, "Both are no matching");
            }
        }




