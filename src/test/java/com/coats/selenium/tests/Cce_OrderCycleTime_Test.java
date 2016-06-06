package com.coats.selenium.tests;

import AutomationFramework.*;
import PageObjects.CCE_MainPage;
import PageObjects.Ecomm_ExportDownloadPage;
import PageObjects.CCE_OrderCycleTimePage;
import PageObjects.CCE_OrderViewPage;
import com.coats.selenium.DriverFactory;
import org.testng.AssertJUnit;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

public class Cce_OrderCycleTime_Test extends DriverFactory { 
    
    @Test //Order Cycle Time Page :: Page and filter checks, print, export, and reset
    (groups = {"CCE", "QuickTest"})
    public void OCTR1() throws Exception {
        //New driver object
        WebDriver driver = getDriver();
        
        //New base object to handle log-in and set up
        Cce_Base base = new Cce_Base(driver);
        
        //Set up returns a CCE Page and outputs test details
        CCE_MainPage ccePage = base.setUp("Order Cycle Time: Page and filter checks, print, export, and reset", "C_CCE_OCTR_1-5");
        
        System.out.println("Navigating to Order Cycle Time...");
        
        CCE_OrderCycleTimePage octPage = ccePage.pressOrderCycleTime();
        octPage.waitForLoad();
        
        System.out.println("Order Cycle Time reached. Checking title...");
        
        AssertJUnit.assertTrue("Order Cycle Time Page: Title not displayed as expected",octPage.getBreadcrumb().getText().equals("Reports | Order Cycle Time"));
        
        System.out.println("Title checked.");
        
        octPage.assertBaseElements();
        
        System.out.println("Checking fields...");
        
        octPage.checkFields();
        
        System.out.println("Fields checked. Entering filter criteria...");
        
        octPage.setShipToPartyName(DataItems.custDetails[1]);
        
        System.out.println("Criteria entered. Printing records...");
        
        CCE_OrderViewPage viewPage = octPage.pressPrint();
        viewPage.waitForContentAlt2();

        System.out.println("Record view displayed. Closing view...");
        
        viewPage.closeView();
        viewPage.waitForInvisibility();
        
        System.out.println("View closed. Exporting to excel...");
        
        Ecomm_ExportDownloadPage dlPage = octPage.pressExport();      
        dlPage.waitForDownloadCompletion();
        
        System.out.println("Exported. Resetting filter...");
        
        octPage.pressReset();
        octPage.waitForLoad();
        
        System.out.println("Filter reset.");
    }
    
}
