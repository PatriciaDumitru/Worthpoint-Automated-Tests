
package com.coats.selenium.tests;

import AutomationFramework.*;
import PageObjects.CCE_MainPage;
import PageObjects.Ecomm_ExportDownloadPage;
import PageObjects.CCE_OrderViewPage;
import PageObjects.CCE_TotalOrdersPage;
import com.coats.selenium.DriverFactory;
import org.testng.AssertJUnit;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

public class Cce_TotalOrders_Test extends DriverFactory {
    
    @Test //Total Orders Page :: Page and filter checks, field selection, export and reset
    (groups = {"Solo"})
    public void TOR1() throws Exception {
        //New driver object
        WebDriver driver = getDriver();
        
        //New base object to handle log-in and set up
        Cce_Base base = new Cce_Base(driver);
        
        //Set up returns a CCE Page and outputs test details
        CCE_MainPage ccePage = base.setUp("Total Orders: Page and filter checks, field selection, export, and reset", "C_CCE_TOR_1-4");
        
        System.out.println("Navigating to Total Orders Page...");
        
        CCE_TotalOrdersPage toPage = ccePage.pressTotalOrders();
        toPage.waitForLoad();
        
        System.out.println("Total Orders reached. Checking title...");
        
        AssertJUnit.assertTrue("Total Orders Page: Title not displayed as expected",toPage.getBreadcrumb().getText().equals("Reports | Total Orders"));
        
        System.out.println("Title checked.");
        
        toPage.assertBaseElements();
        
        System.out.println("Checking fields...");
        
        toPage.checkFields();
        
        System.out.println("Fields checked. Setting fields...");
        
        toPage.tickMUMType();
        toPage.tickArticle();
        toPage.tickBrand();
        toPage.tickRequesterFirst();
        toPage.tickRequesterSecond();
        toPage.tickBusPrinc();
        toPage.tickQtyOrd();
        toPage.tickQtyProd();
        toPage.tickCustName();
        
        System.out.println("Fields set. Entering filter criteria...");
        
        toPage.setCustName(DataItems.custDetails[0]);
        toPage.setRequester(DataItems.custDetails[2]);
        toPage.setMUMType(DataItems.coneMUM);
        
        System.out.println("Filter criteria entered. Printing records...");
        
        CCE_OrderViewPage viewPage = toPage.pressPrint();
        viewPage.waitForLoad();
        
        System.out.println("View displayed. Closing view...");
        
        viewPage.closeView();
        
        System.out.println("View closed. Exporting to excel...");
        
        Ecomm_ExportDownloadPage dlPage = toPage.pressExport();               
        dlPage.waitForDownloadCompletion();
        
        System.out.println("Export complete. Resetting filter...");
        
        toPage.pressReset();
        
        System.out.println("Filter reset.");

    }
    
}
