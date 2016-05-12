package com.coats.selenium.tests;

import AutomationFramework.DataItems;
import PageObjects.CCE_MainPage;
import PageObjects.CCE_InboxPage;
import PageObjects.CCE_InboxSAPPage;
import PageObjects.CCE_OrderViewPage;
import com.coats.selenium.DriverFactory;
import org.testng.AssertJUnit;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

public class Cce_Inbox_Test extends DriverFactory {
    
    @Test //Inbox Page :: Page and filter checks, list orders, save, and re-assign SOS
    (groups = {"CCE", "QuickTest"})
    public void I1() throws Exception {
        //New driver object to control browser
        WebDriver driver = getDriver();
        
        //New base object to handle log-in and set up
        Cce_Base base = new Cce_Base(driver);
        
        //Set up returns a CCE Page and outputs test details
        CCE_MainPage ccePage = base.setUp("Inbox I1: Page checks, filter function, list orders, saving, re-assigning SOS", "C_CCE_I_1,2,3,4,6");
        
        System.out.println("Navigating to Inbox...");
        
        CCE_InboxPage ibPage = ccePage.pressInbox();
        ibPage.waitForLoad();

        System.out.println("Inbox reached.");
        
        //Assert base elements
        ibPage.assertBaseElements();
        
        System.out.println("Asserting page title is as expected...");
        
        AssertJUnit.assertTrue("Inbox Page: Title not as expected",ibPage.getBreadcrumb().getText().equals("Orders | Inbox"));
        
        System.out.println("Title as expected. Checking fields...");
        
        ibPage.checkFields();
        
        System.out.println("Fields checked. Entering filter criteria...");
        
        ibPage.setCustName(DataItems.custDetails[0]);

        System.out.println("Criteria entered. Listing orders...");
        
        ibPage.pressListOrders();
        ibPage.waitForLoad();

        System.out.println("Orders listed. Viewing order...");
        
        CCE_OrderViewPage viewPage = ibPage.pressView();
        viewPage.waitForContent();

        System.out.println("Order view displayed. Closing order...");
        
        viewPage.closeView();

        driver.navigate().refresh();

        System.out.println("View closed. Selecting 'Send to' option depending on status...");


        String orderStatus = ibPage.getOrderStatus();

        System.out.println(orderStatus);


        if (orderStatus.equals("Sent to GIS")) {
            ibPage.pressSAP();        
            System.out.println("SAP selected. Saving...");
        } else {
            ibPage.pressLRM();
            System.out.println("LRM selected. Saving...");
        }

        ibPage.pressSave();     
        ibPage.waitForLoad();

    }
    
    @Test //Inbox Page : Reset, cancel, and print
    (groups = {"CCE", "QuickTest"})
    public void I2() throws Exception {
        //New driver object to control browser
        WebDriver driver = getDriver();
        
        //New base object to handle log-in and set up
        Cce_Base base = new Cce_Base(driver);
        
        //Set up returns a CCE Page and outputs test details
        CCE_MainPage ccePage = base.setUp("Inbox I2: Reset, cancel, and print", "C_CCE_I_5");
        
        System.out.println("Navigating to Inbox...");
        
        CCE_InboxPage ibPage = ccePage.pressInbox();
        
        System.out.println("Inbox loaded. Entering filter criteria...");
        
        ibPage.setCustName(DataItems.custDetails[0]);
        ibPage.setRequester(DataItems.custDetails[2]);
        
        System.out.println("Criteria entered. Pressing reset...");
        
        ibPage.pressReset();
        ibPage.waitForLoad();
        
        System.out.println("Filter reset. Pressing 'send to SAP'...");
        
        ibPage.pressSAP();
        
        System.out.println("SAP selected. Pressing cancel...");
        
        ibPage.pressCancel();
        ibPage.waitForLoad();
        
        System.out.println("Cancel pressed. Pressing print...");
        
        ibPage.pressPrint();
        
        System.out.println("Print pressed.");
        
    } 
    
    @Test 
    (groups = {"CCE", "QuickTest"})
    public void IS1() throws Exception {
        //New driver object to control browser
        WebDriver driver = getDriver();
        
        //New base object to handle log-in and set up
        Cce_Base base = new Cce_Base(driver);
        
        //Set up returns a CCE Page and outputs test details
        CCE_MainPage ccePage = base.setUp("Inbox SAP IS1: Page check, filter functions, list orders, reset", "C_CCE_I_5");
        
        System.out.println("Navigating to Inbox...");
        
        CCE_InboxSAPPage isPage = ccePage.pressInboxSAP();
        isPage.waitForLoad();
        
        System.out.println("Inbox loaded. Entering filter criteria...");
        
        isPage.setHub("IDH001");//DataItems.hub
        
        System.out.println("Criteria entered. Listing orders...");
        
        isPage.pressListOrders();
        isPage.waitForLoad();
        
        System.out.println("Orders listed. Entering search criteria...");
        
        isPage.setSalesOrg(DataItems.salesOrganisation);
        isPage.setHub("IDH001");//DataItems.hub
        
        System.out.println("Criteria entered. Pressing reset...");
        
        isPage.pressReset();
        isPage.waitForLoad();
        
        System.out.println("Reset pressed.");
        
    }
    
}
