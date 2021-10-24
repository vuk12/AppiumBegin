package com.javaforApium;

import org.junit.Assert;
import org.junit.Test;

import java.net.MalformedURLException;

public class UiAutomatorTest extends  StartingPoint{

    @Test
    public void anotherTestLesson18() throws MalformedURLException {
        //Select pattern with Ui automator
       // driver.findElementByAndroidUIAutomator("attribute(\"value\")");

        driver.findElementByAndroidUIAutomator("text(\"Views\")").click();

        //This is in case when
        Assert.assertEquals(driver.findElementsByAndroidUIAutomator("new UiSelector().clickable(true)").size(),0);

    }
}
