package com.javaforApium;

import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.LongPressOptions;
import io.appium.java_client.touch.TapOptions;
import io.appium.java_client.touch.offset.ElementOption;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebElement;

import java.net.MalformedURLException;
import java.time.Duration;

public class MobileGestures extends StartingPoint{

    @Test
    public void testGesturesTap() throws MalformedURLException {
        driver = setDesiredCapabilities("VukadinEmulator");

        driver.findElementByXPath("//android.widget.TextView[@text='Views']").click();

        TouchAction t = new TouchAction(driver);
        WebElement expandList = driver.findElementByXPath("//android.widget.TextView[@text='Expandable Lists']");
        t.tap(TapOptions.tapOptions().withElement(ElementOption.element(expandList))).perform();
        driver.findElementByXPath("//android.widget.TextView[@text='1. Custom Adapter']").click();

        WebElement peopleView = driver.findElementByXPath("//android.widget.TextView[@text='People Names']");
        t.longPress(LongPressOptions.longPressOptions().
                withElement(ElementOption.element(peopleView)).withDuration(Duration.ofSeconds(2))).
                release().perform();
        Assert.assertTrue(driver.findElementById("android:id/title").isDisplayed());
    }

    @Test public void testDateWidgetsSwipe() throws MalformedURLException {
        driver = setDesiredCapabilities("VukadinEmulator");

        driver.findElementByXPath("//android.widget.TextView[@text='Views']").click();
        driver.findElementByXPath("//android.widget.TextView[@text='Date Widgets']").click();
        driver.findElementByXPath("//android.widget.TextView[@text='2. Inline']").click();

        driver.findElementByXPath("//*[@content-desc= '9']").click();
        //driver.findElementByAndroidUIAutomator("")

        TouchAction t = new TouchAction(driver);
        //long pres one element then switch to another element
        WebElement clockMinuteFirstPosition = driver.findElementByXPath("//*[@content-desc= '15']");
        WebElement clockMinuteSecondPosition = driver.findElementByXPath("//*[@content-desc= '45']");
        t.longPress(LongPressOptions.longPressOptions().
                        withElement(ElementOption.element(clockMinuteFirstPosition)).withDuration(Duration.ofSeconds(2)))
                        .moveTo(ElementOption.element(clockMinuteSecondPosition)).
                        release().perform();
    }

    @Test public void scroolingText() throws MalformedURLException {
        driver = setDesiredCapabilities("VukadinEmulator");

        driver.findElementByXPath("//android.widget.TextView[@text='Views']").click();

        //For Scrool
        driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"WebView\"))");

    }

    @Test public void DragDrop() throws MalformedURLException {
        driver = setDesiredCapabilities("Android Device");

        driver.findElementByXPath("//android.widget.TextView[@text='Views']").click();
        driver.findElementByXPath("//android.widget.TextView[@text='Drag and Drop']").click();

        //For Drag and Drop
        TouchAction t = new TouchAction(driver);

        WebElement dragElement = driver.findElementsByClassName("android.view.View").get(0);
        WebElement dropElement = driver.findElementsByClassName("android.view.View").get(1);

        t.longPress(LongPressOptions.longPressOptions().
                        withElement(ElementOption.element(dragElement)))
                .moveTo(ElementOption.element(dropElement)).
                release().perform();
    }
}
