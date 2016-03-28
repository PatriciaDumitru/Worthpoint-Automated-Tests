package com.coats.selenium;

import AutomationFramework.CommonTask;
import com.coats.selenium.config.WebDriverThread;
import com.coats.selenium.listeners.ScreenshotListener;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.Dimension;
import org.testng.annotations.BeforeTest;

@Listeners(ScreenshotListener.class)
public class DriverFactory {

    private static List<WebDriverThread> webDriverThreadPool = Collections.synchronizedList(new ArrayList<WebDriverThread>());
    private static ThreadLocal<WebDriverThread> driverThread;

    @BeforeMethod (alwaysRun=true)
    public static void instantiateDriverObject() {
        driverThread = new ThreadLocal<WebDriverThread>() {
            @Override
            protected WebDriverThread initialValue() {
                WebDriverThread webDriverThread = new WebDriverThread();
                webDriverThreadPool.add(webDriverThread);
                return webDriverThread;
            }
        };
    }

    public static WebDriver getDriver() throws Exception {
        return driverThread.get().getDriver();
    }

    // @AfterMethod (alwaysRun=true)
    /*
    public static void clearCookies() throws Exception {
        getDriver().manage().deleteAllCookies();
        System.out.println("----------------------------------------------------");
    }


    @AfterMethod (alwaysRun=true)
    public static void closeDriverObjects() {
        for (WebDriverThread webDriverThread : webDriverThreadPool) {
            webDriverThread.quitDriver();
        }
        System.out.println("----------------------------------------------------");

    }
*/

}