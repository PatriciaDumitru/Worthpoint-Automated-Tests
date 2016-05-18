
package com.coats.selenium.tests;

import AutomationFramework.DataItems;
import PageObjects.CCE_MainPage;
import PageObjects.Ecomm_ExportDownloadPage;
import PageObjects.CCE_OrderViewPage;
import PageObjects.CCE_SAPLogPage;
import com.coats.selenium.DriverFactory;
import org.testng.AssertJUnit;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

public class Cce_SAPLog_Test extends DriverFactory {
    
    @Test //SAP Log Page :: Page and filter checks, search, reset, and export
    (groups = {"CCE"})
    public void SAP1() throws Exception {
        //New driver object
        WebDriver driver = getDriver();
        
        //New base object to handle log-in and set up
        Cce_Base base = new Cce_Base(driver);
        
        //Set up returns a CCE Page and outputs test details
        CCE_MainPage ccePage = base.setUp("SAP Log: Page and filter checks, search, reset, export", "C_CCE_A_SAP_1-5");
        
        System.out.println("Navigating to SAP Log Page...");
        
        CCE_SAPLogPage sapPage = ccePage.pressSAPLog();
        sapPage.waitForLoad();
        
        System.out.println("SAP Log reached. Checking title...");
        
        AssertJUnit.assertTrue("SAP Log Page: Title not displayed as expected",sapPage.getBreadcrumb().getText().equals("SAP Log"));
        
        System.out.println("Title checked.");
        
        sapPage.assertBaseElements();
        
        System.out.println("Checking fields...");
        
        sapPage.checkFields();
        
        System.out.println("Fields checked. Entering filter criteria...");
        
        sapPage.setCustName(DataItems.custDetails[0]);
        sapPage.setMUMType(DataItems.copMUM);
        sapPage.setSapStatus("Created in SAP");
        
        System.out.println("Filter criteria entered. Pressing search...");
        
        sapPage.pressSearch();
        sapPage.waitForLoad();
        
        System.out.println("Records listed. Viewing top item...");
        
        CCE_OrderViewPage viewPage = sapPage.pressView();
        viewPage.switchTo();
        viewPage.waitForContent();
        
        System.out.println("View displayed. Closing view...");
        
        viewPage.closeView();
        viewPage.waitForInvisibility();
        
        System.out.println("View closed. Exporting records...");

        driver.navigate().refresh();
        
        Ecomm_ExportDownloadPage dlPage = sapPage.pressExport();
        dlPage.waitForDownloadCompletion();
        
        System.out.println("Records exported. Resetting filter...");
        
        sapPage.pressReset();
        sapPage.waitForLoad();
        
        System.out.println("Filter reset.");
        
    }

}
