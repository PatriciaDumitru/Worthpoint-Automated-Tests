package com.coats.selenium.tests;

import AutomationFramework.DataItems;
import PageObjects.CCE_MainPage;
import PageObjects.CCE_DNPrintPage;
import PageObjects.CCE_DNReprintPage;
import com.coats.selenium.DriverFactory;
import com.google.common.base.Verify;
import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

public class Cce_DNReprint_IT extends DriverFactory {
    
    @Test //DN Reprint Page :: Page checks
    (groups = {"CCE"})
    public void DR1() throws IOException,Exception {
        //New driver object to control browser
        WebDriver driver = getDriver();
        
        //New base object to handle log-in and set up
        Cce_SOC_Base base = new Cce_SOC_Base(driver);
        
        //Set up returns a CCE Page and outputs test details
        CCE_MainPage ccePage = base.SUSST_SetUp("DN Reprint DR1: User can select all fields", "G_CCE_DR_1");
        
        System.out.println("Navigating to DN Reprint...");
        
        CCE_DNReprintPage dnReprint = ccePage.pressDNReprint();
        dnReprint.waitForLoad();
        
        //Take a screenshot
        File scrFile1 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile1,new File(DataItems.screenshotsFilepath+"\\CCE\\Orders\\DN Reprint\\1DN Reprint page.png"));
        
        System.out.println("DN Reprint loaded.");
        
        //Assert base elements
        dnReprint.assertBaseElements();
        
        System.out.println("Asserting page title is as expected...");
        
        //Assert page title is correct
        Verify.verify(dnReprint.getBreadcrumb().getText().equals("Orders | Delivery Notes"));
        
        System.out.println("Page title correct. Checking fields are displayed as expected...");
        
        dnReprint.checkFields();
        
        System.out.println("Test complete.");
        
    }
    
    @Test //DN Reprint Page :: Filter checks and confirm
    (groups = {"CCE"})
    public void DR2() throws IOException,Exception {
                
        //New driver object to control browser
        WebDriver driver = getDriver();
        
        //New base object to handle log-in and set up
        Cce_SOC_Base base = new Cce_SOC_Base(driver);
        
        //Set up returns a CCE Page and outputs test details
        CCE_MainPage ccePage = base.SUSST_SetUp("DN Reprint DR1: User can select all fields", "G_CCE_DR_2");
        
        System.out.println("Navigating to DN Reprint...");
        
        CCE_DNReprintPage dnReprint = ccePage.pressDNReprint();
        
        System.out.println("DN Reprint loaded. Entering filter criteria...");
        
        dnReprint.setShipToPartyName(DataItems.custDetails[1]);
        
        //Take a screenshot
        File scrFile2 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile2,new File(DataItems.screenshotsFilepath+"\\CCE\\Orders\\DN Reprint\\2Filter criteria entered.png"));
        
        System.out.println("Criteria entered. Listing orders...");
        
        dnReprint.pressList();
        dnReprint.waitForLoad();
        
        //Take a screenshot
        File scrFile3 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile3,new File(DataItems.screenshotsFilepath+"\\CCE\\Orders\\DN Reprint\\3Orders listed.png"));
        
        System.out.println("Orders listed. Pressing confirm...");
        
        dnReprint.pressConfirm();
        
        //Take a screenshot
        File scrFile4 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile4,new File(DataItems.screenshotsFilepath+"\\CCE\\Orders\\DN Reprint\\4Confirm pressed.png"));
        
        System.out.println("Confirm pressed. Pressing print...");
        
        CCE_DNPrintPage printPage = dnReprint.pressPrint();
        
        System.out.println("Print pressed. Displaying delivery notes...");
        
        //Ensure frame is selected
        printPage.switchTo();
        
        System.out.println("Delivery notes displayed.");
        
        //Take a screenshot
        File scrFile5 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile5,new File(DataItems.screenshotsFilepath+"\\CCE\\Orders\\DN Reprint\\5DN displayed.png"));
        
        if (DataItems.printingEnabled) {           
            dnReprint = printPage.pressPrint();
            System.out.println("Delivery notes sent to printer.");
        } else {
            dnReprint = printPage.pressClose();
            System.out.println("Deliveyr notes view closed.");
        }
        
        System.out.println("Test complete.");

    }
    
}
