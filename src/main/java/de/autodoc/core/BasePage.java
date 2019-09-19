package de.autodoc.core;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;
import org.apache.log4j.Logger;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class BasePage {
    private static Logger logger = Logger.getLogger(BasePage.class);
    private DesiredCapabilities cap;
    private static final String APP_PACKAGE = "de.autodoc.gmbh";
    private static final String APP_ACTIVITY = "de.autodoc.gmbh.ui.activity.SplashActivity";
    private static final String URL = "http://127.0.0.1:4723/wd/hub";
    protected AndroidDriver driver;
    private static final String SLASH = File.separator;
    private Properties prop;

    public BasePage(AndroidDriver driver) {
        this.driver = driver;
    }

    private void initDriver() {
        String pathToFolder = String.format("src%1$smain%1$sresources%1$s", SLASH);
        String pathToPropertiesFile = String.format("src%1$smain%1$sresources%1$stest.properties", SLASH);
        File f = new File(pathToFolder);
        File fs = new File(f, "AutoDoc.apk");

        try (InputStream input = new FileInputStream(pathToPropertiesFile)) {
            prop = new Properties();
            prop.load(input);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            logger.error("Can't find the properties file");
        }

        cap = new DesiredCapabilities();
        cap.setCapability(MobileCapabilityType.DEVICE_NAME, prop.getProperty("device.name"));
        cap.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.ANDROID);
        cap.setCapability(MobileCapabilityType.APP, fs.getAbsolutePath());
        cap.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, APP_PACKAGE);
        cap.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, APP_ACTIVITY);
        try {
            driver = new AndroidDriver(new URL(URL), cap);
        } catch (MalformedURLException e) {
            throw new RuntimeException("There are some problems with Appium driver initialization");
        }
    }

    private void setTimeout() {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    public AndroidDriver build() {
        initDriver();
        setTimeout();
        return driver;
    }
}
