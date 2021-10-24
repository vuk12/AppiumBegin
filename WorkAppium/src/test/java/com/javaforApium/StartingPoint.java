package com.javaforApium;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidElement;
import org.junit.Test;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class StartingPoint {
    static AndroidDriver<AndroidElement> driver;

    public static AndroidDriver<AndroidElement> setDesiredCapabilities(String deviceName) throws MalformedURLException {
        //  File appDir = new File("src/main/resources");
        // File app= new File(appDir,"ApiDemos-debug.apk");
        String apkSource = "https://github.com/appium/appium/raw/master/sample-code/apps/ApiDemos-debug.apk";
        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        //cap.setCapability(MobileCapabilityType.VERSION, "10.0");

        if (deviceName == "VukadinEmulator") {
            cap.setCapability(MobileCapabilityType.DEVICE_NAME, "VukadinEmulator");
        } else if (deviceName == "Android Device") {
            cap.setCapability(MobileCapabilityType.DEVICE_NAME, "Android Device");
        }

        //cap.setCapability(MobileCapabilityType.UDID, "emulator-5554");
        cap.setCapability(MobileCapabilityType.AUTOMATION_NAME, "Uiautomator2");
        // cap.setCapability(MobileCapabilityType.APP, fs.getAbsolutePath());
        cap.setCapability(MobileCapabilityType.APP, apkSource);

        driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), cap);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        return driver;
        //driver.findElement(By.xpath("//android.widget.TextView[@text='Views']")).click();
    }

    public static AndroidDriver<AndroidElement> setDesiredCapabilitiesApkFile() throws MalformedURLException {
        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");

        cap.setCapability(MobileCapabilityType.DEVICE_NAME, "VukadinEmulator");
        cap.setCapability(MobileCapabilityType.AUTOMATION_NAME, "Uiautomator2");
        cap.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT,14);

        File appDir = new File("src/main/resources");
        File app= new File(appDir,"General-Store.apk");
        cap.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());

        driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), cap);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        return driver;
    }

    public static AndroidDriver<AndroidElement> setDesiredCapCHROME() throws MalformedURLException {

        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "VukadinEmulator");

        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");

        capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, "Chrome");

        driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        return driver;
    }
}
