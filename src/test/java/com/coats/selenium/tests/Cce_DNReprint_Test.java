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

public class Cce_DNReprint_Test extends DriverFactory {
    
    @Test //DN Reprint Page :: Page checks
    (groups = {"CCE", "QuickTest"})
    public void DR1() throws Exception {
        //New driver object to control browser
        WebDriver driver = getDriver();
        
        //New base object to handle log-in and set up
        Cce_Base base = new Cce_Base(driver);
        
        //Set up returns a CCE Page and outputs test details
        CCE_MainPage ccePage = base.setUp("DN Reprint DR1: User can select all fields", "G_CCE_DR_1");
        
        System.out.println("Navigating to DN Reprint...");
        
        CCE_DNReprintPage dnReprint = ccePage.pressDNReprint();
        dnReprint.waitForLoad();
        
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
    public void DR2() throws Exception {
                
        //New driver object to control browser
        WebDriver driver = getDriver();
        
        //New base object to handle log-in and set up
        Cce_Base base = new Cce_Base(driver);
        
        //Set up returns a CCE Page and outputs test details
        CCE_MainPage ccePage = base.setUp("DN Reprint DR1: User can select all fields", "G_CCE_DR_2");
        
        System.out.println("Navigating to DN Reprint...");
        
        CCE_DNReprintPage dnReprint = ccePage.pressDNReprint();
        
        System.out.println("DN Reprint loaded. Entering filter criteria...");
        
        dnReprint.setShipToPartyName(DataItems.custDetails[1]);
        
        System.out.println("Criteria entered. Listing orders...");
        
        dnReprint.pressList();
        dnReprint.waitForLoad();
        
        System.out.println("Orders listed. Pressing confirm...");
        
        dnReprint.pressConfirm();
        
        System.out.println("Confirm pressed. Pressing print...");
        
        CCE_DNPrintPage printPage = dnReprint.pressPrint();
        
        System.out.println("Print pressed. Displaying delivery notes...");
        
        //Ensure frame is selected
        printPage.switchTo();
        
        System.out.println("Delivery notes displayed.");
        
        if (DataItems.printingEnabled) {           
            dnReprint = printPage.pressPrint();
            System.out.println("Delivery notes sent to printer.");
        } else {
            dnReprint = printPage.pressClose();
            System.out.println("Delivery notes view closed.");
        }
        
        System.out.println("Test complete.");

    }
    
}
