import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Arrays;

public class GapMethods {

    static void swipeUp(AppiumDriver driver) {
        int startX = driver.manage().window().getSize().width / 2; // Middle of screen width
        int startY = (int) (driver.manage().window().getSize().height * 0.8); // Start swipe 80% from top
        int endY = (int) (driver.manage().window().getSize().height * 0.2); // End swipe 20% from top

        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
        Sequence swipe = new Sequence(finger, 1);

        // Move finger to starting position
        swipe.addAction(finger.createPointerMove(Duration.ofMillis(0), PointerInput.Origin.viewport(), startX, startY));
        // Press and move up
        swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        swipe.addAction(finger.createPointerMove(Duration.ofMillis(800), PointerInput.Origin.viewport(), startX, endY));
        // Release finger
        swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        driver.perform(Arrays.asList(swipe));
        System.out.println("Swipe up performed successfully.");
    }

static void closeButton(AppiumDriver driver){
    try {
        driver.findElement(new AppiumBy.ByAndroidUIAutomator("new UiSelector().resourceId(\"com.gap.mobile.gap:id/com_braze_inappmessage_modal_close_button\")")).click();
    } catch (Exception e) {
        System.out.println(e.getMessage());
    }
}
}
