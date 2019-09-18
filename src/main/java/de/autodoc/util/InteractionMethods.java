package de.autodoc.util;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class InteractionMethods {

    private static final int SECONDS_TO_WAIT_FOR_BECOME_CLICKABLE = 20;

    public static void waitForPresence(AndroidDriver driver, AndroidElement element) {

        WebDriverWait wait = new WebDriverWait(driver, SECONDS_TO_WAIT_FOR_BECOME_CLICKABLE);
        wait.until(ExpectedConditions.elementToBeClickable(element));

    }

    public static boolean pageContainsText(AndroidDriver driver, String text) {
        return driver.getPageSource().contains(text);
    }

    public static void clickOnTextView(AndroidDriver driver, String text) {
        driver.findElementByXPath("//android.widget.TextView[@text='" + text + "']").click();
    }

    private static int[] getCoordinatesOfMiddleRightEdgeOfElement(AndroidElement androidElement) {
        int x = (androidElement.getLocation().getX() + androidElement.getSize().width) - 1;
        int y = (androidElement.getLocation().getY() * 2 + androidElement.getSize().height) / 2;

        return new int[]{x, y};
    }

    public static void scrollElementLeftByNPercents(AndroidDriver driver, AndroidElement androidElement, int percents) {
        int[] coordinates = getCoordinatesOfMiddleRightEdgeOfElement(androidElement);
        int x = coordinates[0];
        int y = coordinates[1];
        int shiftedX = x * percents / 100;
        int shiftedY = y;
        TouchAction t = new TouchAction(driver);
        t.press(PointOption.point(x, y))
                .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1))).moveTo(PointOption.point(shiftedX, shiftedY))
                .release().perform();
    }

    public static boolean elementIsPresent(AndroidElement androidElement) {
        return androidElement.isDisplayed();
    }
}

