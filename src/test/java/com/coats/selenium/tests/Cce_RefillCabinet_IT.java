package com.coats.selenium.tests;

import AutomationFramework.DataItems;
import PageObjects.CCE_MainPage;
import PageObjects.CCE_RefillCabinetPage;
import com.coats.selenium.DriverFactory;
import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.testng.AssertJUnit;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Cce_RefillCabinet_IT extends DriverFactory {
    
    @Test //Refill Cabinet Page :: Page and filter checks
    (groups = {"CCE"})
    public void RC1() throws IOException, Exception {
        //New driver object to control browser
        WebDriver driver = new ChromeDriver();
        
        //New base object to handle log-in and set up
        Cce_SOC_Base base = new Cce_SOC_Base(driver);
        
        //Set up returns a CCE Page and outputs test details
        CCE_MainPage ccePage = base.SUSST_SetUp("Refill Cabinet RC1: Page checks, filter function", "C_CCE_RC_1-2");
        
        System.out.println("Navigating to Refill Cabinet...");
        
        CCE_RefillCabinetPage rcPage = ccePage.pressRefillCabinet();
        rcPage.waitForLoad();
        
        //Take a screenshot
        File scrFile1 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile1,new File(DataItems.screenshotsFilepath+"\\CCE\\Refill Cabinet\\1Refill cabinet page.png"));
        
        System.out.println("Refill Cabinet loaded.");
        
        //Assert base elements
        rcPage.assertBaseElements();
        
        System.out.println("Asserting page title is as expected...");
        
        AssertJUnit.assertTrue("Refill Cabinet Page: Title not as expected",rcPage.getBreadcrumb().getText().equals("Refill Cabinet"));
        
        System.out.println("Title correct. Checking fields...");
        
        rcPage.checkFields();
        
        System.out.println("Fields checked. Entering filter crtieria...");
        
        rcPage.setShipTo(DataItems.custDetails[1]);
        
        //Take a screenshot
        File scrFile2 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile2,new File(DataItems.screenshotsFilepath+"\\CCE\\Refill Cabinet\\2Filter criteria entered.png"));
        
        System.out.println("Criteria entered. Pressing cancel...");
        
        rcPage.pressCancel();
        rcPage.waitForLoad();
        
        //Take a screenshot
        File scrFile3 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile3,new File(DataItems.screenshotsFilepath+"\\CCE\\Refill Cabinet\\3Refill cancelled.png"));
        
        System.out.println("Cancel pressed.");
        
    }

}
