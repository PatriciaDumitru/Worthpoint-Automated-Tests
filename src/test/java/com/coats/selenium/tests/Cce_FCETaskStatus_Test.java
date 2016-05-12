package com.coats.selenium.tests;

import AutomationFramework.DataItems;
import PageObjects.CCE_MainPage;
import PageObjects.Ecomm_ExportDownloadPage;
import PageObjects.CCE_FCETaskStatusPage;
import PageObjects.CCE_OrderViewPage;
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

public class Cce_FCETaskStatus_Test extends DriverFactory {
    
    @Test //FCE Task Status Page :: Page and filter checks, print records and export
    (groups = {"Solo"})
    public void TS1() throws Exception {
        //New driver object
        WebDriver driver = getDriver();
        
        //New base object to handle log-in and set up
        Cce_Base base = new Cce_Base(driver);
        
        //Set up returns a CCE Page and outputs test details
        CCE_MainPage ccePage = base.setUp("FCE Task Status: Page and filter checks, print, and export", "C_CCE_FCETR_1-4");
        
        System.out.println("Navigating to FCE Task Status...");
        
        CCE_FCETaskStatusPage fcePage = ccePage.pressFCETaskStatus();
        fcePage.waitForLoad();
        
        System.out.println("FCE Task Status reached. Checking title...");
        
        AssertJUnit.assertTrue("FCE Task Status Page: Title not displayed as expected",fcePage.getBreadcrumb().getText().equals("Reports | FCE Task Status"));
        
        System.out.println("Title checked.");
        
        fcePage.assertBaseElements();
        
        System.out.println("Checking fields...");
        
        fcePage.checkFields();
        
        System.out.println("Fields checked. Entering filter criteria...");
        
        fcePage.setSalesOrg("ID51");
        fcePage.setTaskType("Visit");
        fcePage.setTaskStatus("Completed");
        fcePage.setHub("IDH004");
        
        System.out.println("Criteria entered. Printing records...");
        
        CCE_OrderViewPage viewPage = fcePage.pressPrint();
        viewPage.waitForLoad();
        
        System.out.println("Records displayed. Closing view...");

        viewPage.closeView();
        //wait for frame to completely disappear
        viewPage.waitForInvisibility();
        
        System.out.println("View closed. Exporting records to excel...");
        
        Ecomm_ExportDownloadPage exportPage = fcePage.pressExport();
        exportPage.waitForDownloadCompletion();
        
        System.out.println("Export pressed, download completed.");
        
    }
    
    @Test //FCE Task Status Page :: Filter reset
    (groups = {"CCE", "QuickTest"})
    public void TS2() throws Exception {
        //New driver object
        WebDriver driver = getDriver();
        
        //New base object to handle log-in and set up
        Cce_Base base = new Cce_Base(driver);
        
        //Set up returns a CCE Page and outputs test details
        CCE_MainPage ccePage = base.setUp("FCE Task Status: Page and filter checks, print, and export", "C_CCE_FCETR_1-4");
        
        System.out.println("Navigating to FCE Task Status...");
        
        CCE_FCETaskStatusPage fcePage = ccePage.pressFCETaskStatus();
        
        System.out.println("FCE Task Status reached. Entering filter criteria...");
        
        fcePage.setSalesOrg("ID51");
        fcePage.setTaskStatus("Completed");

        System.out.println("Criteria entered. Pressing reset...");
        
        fcePage.pressReset();
        fcePage.waitForLoad();

        System.out.println("Filter reset.");             
        
    }
    
}
