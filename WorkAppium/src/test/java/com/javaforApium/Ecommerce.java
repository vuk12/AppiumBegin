package com.javaforApium;

import io.appium.java_client.MobileBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.touch.LongPressOptions;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import java.net.MalformedURLException;
import java.time.Duration;
import java.util.Set;

import static io.appium.java_client.touch.offset.ElementOption.element;

public class Ecommerce extends  StartingPoint{

    @Test
    public void HappyPath_Lesson32() throws MalformedURLException, InterruptedException {

        driver = setDesiredCapabilitiesApkFile();

        driver.findElementById("com.androidsample.generalstore:id/nameField").sendKeys("Hallo");

        driver.hideKeyboard();
        driver.findElementByXPath("//android.widget.RadioButton[@text='Female']").click();

        driver.findElementById("com.androidsample.generalstore:id/spinnerCountry").click();
        //scroll into view
        driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Aruba\"))");
        //Ova zakomentarisana je drugi primjer scrolla koji mora da radi ako ne radi ovaj gornji
        //   driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textMatches(\"" + containedText + "\").instance(0))"));

        driver.findElementByXPath("//*[@text='Aruba']").click();
        driver.findElementById("com.androidsample.generalstore:id/btnLetsShop").click();

        ///------------------------------------
        //JAKO BITNO KADA TREBA DA SE SCROLLUJE TAKO DA CIJELI ELEMENT BUDE VIDLJEIV NA EKRANU
        //U resource Id ide parent id, a u textMatches ide text od elemnta do kog zelimo da skrolujemo
        driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().resourceId(\"com.androidsample.generalstore:id/rvProductList\")).scrollIntoView(new UiSelector().textMatches(\"Jordan 6 Rings\").instance(0))"));

        int count= driver.findElements(By.id("com.androidsample.generalstore:id/productName")).size();
        for(int i=0;i<count;i++) {

            String text=driver.findElements(By.id("com.androidsample.generalstore:id/productName")).get(i).getText();
            if(text.equalsIgnoreCase("Jordan 6 Rings")) {
                driver.findElements(By.id("com.androidsample.generalstore:id/productAddCart")).get(i).click();
                break;
            }
        }

        driver.findElement(By.id("com.androidsample.generalstore:id/appbar_btn_cart")).click();
        Thread.sleep(3000);

        String lastpageText = driver.findElement(By.id("com.androidsample.generalstore:id/productName")).getText();
        //Assert.assertEquals("Jordan 6 Rings", lastpageText);

        driver.findElementByClassName("android.widget.CheckBox").click();


        WebElement tc = driver.findElement(By.xpath("//*[@text='Please read our terms of conditions']"));
        new TouchAction(driver).longPress(LongPressOptions.longPressOptions()
                .withElement(element(tc)).withDuration(Duration.ofSeconds(3))).release().perform();
        driver.findElement(By.id("android:id/button1")).click();
        driver.findElement(By.id("com.androidsample.generalstore:id/btnProceed")).click();

        //Treba neko vrijeme da se prebaci na web app sa nativa
        Thread.sleep(7000);
        //------------------------------
        //OVAJ DIO JE NAMJENJEN ZA PREBACIVANJE SA NATIVA NA CHROME
        Set<String> contexts = driver.getContextHandles();
        for (String contextName : contexts) {
            System.out.println(contextName);
        }

        driver.context("WEBVIEW_com.androidsample.generalstore");
        Thread.sleep(4000);
        driver.findElement(By.xpath("//*[@name='q']")).sendKeys("Hello");
        driver.findElement(By.xpath("//*[@name='q']")).sendKeys(Keys.ENTER);

        ((AndroidDriver)driver).pressKey(new KeyEvent(AndroidKey.BACK));
        driver.context("NATIVE_APP");
    }

    @Test
    public void tostMessage() throws MalformedURLException {

        driver = setDesiredCapabilitiesApkFile();

        driver.findElementById("com.androidsample.generalstore:id/btnLetsShop").click();

        //Ovo je nacin na koji se dolazi do tost poruka, i on je standard, problem je ako ih ima vise da ih identifikujemo
        //I svaki tost ima svoj message koji je u atributu name
        String tostMessage = driver.findElementByXPath("//android.widget.Toast[1]").getAttribute("name");

        Assert.assertTrue(tostMessage.contains("Please enter your name"));
    }


}
