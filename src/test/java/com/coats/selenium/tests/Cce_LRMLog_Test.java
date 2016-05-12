
package com.coats.selenium.tests;

import AutomationFramework.DataItems;
import PageObjects.CCE_MainPage;
import PageObjects.Ecomm_ExportDownloadPage;
import PageObjects.CCE_LRMLogPage;
import PageObjects.CCE_OrderViewPage;
import com.coats.selenium.DriverFactory;
import org.testng.AssertJUnit;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

public class Cce_LRMLog_Test extends DriverFactory {
    
    @Test
    (groups = {"CCE", "QuickTest"})
    public void LRM1() throws Exception {
        //New driver object
        WebDriver driver = getDriver();
        
        //New base object to handle log-in and set up
        Cce_Base base = new Cce_Base(driver);
        
        //Set up returns a CCE Page and outputs test details
        CCE_MainPage ccePage = base.setUp("LRM Log: Page and filter checks, view, resend, export", "C_CCE_LRM_1-5");
        
        System.out.println("Navigating to LRM Log Page...");
        
        CCE_LRMLogPage lrmPage = ccePage.pressLRMLog();
        lrmPage.waitForLoad();
        
        System.out.println("LRM Log reached. Checking title...");
        
        AssertJUnit.assertTrue("LRM Log Page: Title not displayed as expected",lrmPage.getBreadcrumb().getText().equals("Lrm Log"));
        
        System.out.println("Title checked.");
        
        lrmPage.assertBaseElements();
        
        System.out.println("Checking fields...");
        
        lrmPage.checkFields();
        
        System.out.println("Fields checked. Entering filter criteria...");
        
        lrmPage.setShadeCode(DataItems.expShadeCode2);
        lrmPage.setErrorMessage("Yes");
        
        System.out.println("Criteria entered. List orders....");

        lrmPage.pressSearch();
        lrmPage.waitForLoad();
        
         System.out.println("Orders listed. Pressing view...");
        
        CCE_OrderViewPage viewPage = lrmPage.pressView();
        viewPage.switchTo();
        viewPage.waitForContent();
        
        System.out.println("View displayed. Closing view...");
        
        viewPage.closeView();
        viewPage.waitForInvisibility();

        driver.navigate().refresh();
        
        System.out.println("View closed. Pressing re-send...");
        
        lrmPage.pressResend();
        lrmPage.waitForLoad();
        
        System.out.println("Re-sent. Re-entering critera and listing orders...");
        
        lrmPage.setShadeCode(DataItems.expShadeCode2);
        lrmPage.setErrorMessage("Yes");
        lrmPage.pressSearch();
        
        System.out.println("Orders listed. Exporting records...");
        
        Ecomm_ExportDownloadPage dlPage = lrmPage.pressExport();
        dlPage.waitForDownloadCompletion();
        
        System.out.println("Export complete. Resetting filter...");
        
        lrmPage.pressReset();
        lrmPage.waitForLoad();

        System.out.println("Filter reset.");
              
    }
    
}
