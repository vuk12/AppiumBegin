package com.javaforApium;

import io.appium.java_client.AppiumDriver;
import org.junit.Test;
import org.openqa.selenium.By;

import java.net.MalformedURLException;

public class TestFile extends  StartingPoint{

    @Test
    public void FirstSimpleClick() throws MalformedURLException {
        driver = setDesiredCapabilities("VukadinEmulator");

        // xpath selector example
        //*   //tagName[@atribute='value']
        // In android classname is tagName, but not always
        driver.findElement(By.xpath("//android.widget.TextView[@text='Preference']")).click();
        driver.findElement(By.xpath("//android.widget.TextView[@text='3. Preference dependencies']")).click();
        driver.findElement(By.id("android:id/checkbox")).click();
        driver.findElement(By.xpath("(//android.widget.RelativeLayout)[2]")).click();
        driver.findElement(By.className("android.widget.EditText")).sendKeys("Hallo");
        driver.findElement(By.xpath("(//android.widget.Button)[2]")).click();
        //driver.findElementsByClassName("android.widget.Button").get(1).click();


    }
}
