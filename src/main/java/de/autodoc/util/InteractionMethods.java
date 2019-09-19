package de.autodoc.util;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.Point;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class InteractionMethods {

    private static final int SECONDS_TO_WAIT_FOR_BECOME_CLICKABLE = 20;

    public static void waitForPresence(AndroidDriver driver, AndroidElement element) {

        WebDriverWait wait = new WebDriverWait(driver, SECONDS_TO_WAIT_FOR_BECOME_CLICKABLE);
        wait.until(ExpectedConditions.elementToBeClickable(element));

    }

    public static void clickOnTextView(AndroidDriver driver, String text) {
        driver.findElementByXPath("//android.widget.TextView[@text='" + text + "']").click();
    }


    public static void scrollElementLeftByNPercents(AndroidDriver driver, AndroidElement androidElement, int percents) {
        Point p = androidElement.getCenter();
        int x = p.getX();
        int y = p.getY();
        int shiftedX = x * (100 - percents) / 100;
        int shiftedY = y;
        TouchAction t = new TouchAction(driver);
        t.press(PointOption.point(x, y))
                .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1))).moveTo(PointOption.point(shiftedX, shiftedY))
                .release().perform();
    }

    //Use this method for some imageButtons for those click method doesn't work from time to time.
    public static void clickSimulation(AndroidDriver driver, AndroidElement androidElement) {
        Point p = androidElement.getCenter();
        TouchAction t = new TouchAction(driver);
        t.press(PointOption.point(p.getX(), p.getY()))
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(200)))
                .release().perform();
    }

    public static boolean elementIsPresent(AndroidElement androidElement) {
        return androidElement.isDisplayed();
    }
}

