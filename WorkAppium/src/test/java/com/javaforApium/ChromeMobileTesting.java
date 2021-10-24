package com.javaforApium;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;

import java.net.MalformedURLException;

public class ChromeMobileTesting extends  StartingPoint{

    @Test
    public void checkRahulSiteFromMobileChrome() throws MalformedURLException {
        driver =setDesiredCapCHROME();

        driver.get("https://rahulshettyacademy.com/angularAppdemo/");
        driver.findElement(By.cssSelector(".navbar-toggler")).click();
        driver.findElement(By.cssSelector("a[href*='products']")).click();

        JavascriptExecutor js= (JavascriptExecutor)driver;
        js.executeScript("window.scrollBy(0,1000)", "");

        String text =driver.findElement(By.xpath("(//li[@class='list-group-item'])[3]/div/div/a")).getText();

        Assert.assertEquals(text, "Devops");
    }
}
