
package TestCases;

import AutomationFramework.TestSuite;
import PageObjects.CcePage;
import PageObjects.InboxPage_CCE;
import PageObjects.InboxSAPPage_CCE;
import PageObjects.OrderViewPage_CCE;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class Cce_Inbox {
    
    @Test
    public void I1() {
        //New driver object to control browser
        WebDriver driver = new ChromeDriver();
        
        //New base object to handle log-in and set up
        Cce_SOC_Base base = new Cce_SOC_Base(driver);
        
        //Set up returns a CCE Page and outputs test details
        CcePage ccePage = base.SUSST_SetUp("Inbox I1: Page checks, filter function, list orders, saving, re-assigning SOS", "C_CCE_I_1,2,3,4,6");
        
        System.out.println("Navigating to Inbox...");
        
        InboxPage_CCE ibPage = ccePage.pressInbox();
        
        System.out.println("Inbox loaded. Asserting base elements...");
        
        //Assert base elements
        ibPage.assertBaseElements();
        
        System.out.println("Base elements asserted. Asserting page title is as expected...");
        
        Assert.assertTrue("Inbox Page: Title not as expected",ibPage.getBreadcrumb().getText().equals("Orders | Inbox"));
        
        System.out.println("Title as expected. Checking fields...");
        
        ibPage.checkFields();
        
        System.out.println("Fields checked. Entering filter criteria...");
        
        ibPage.setCustName(TestSuite.custDetails[0]);
        
        System.out.println("Criteria entered. Listing orders...");
        
        ibPage.pressListOrders();
        
        System.out.println("Orders listed. Viewing order...");
        
        OrderViewPage_CCE viewPage = ibPage.pressView();
        
        System.out.println("Order view displayed. Closing order...");
        
        viewPage.closeView();
        
        System.out.println("View closed. Selecting 'Send to' option depending on status...");
        
        String orderStatus = ibPage.getOrderStatus();
        
        if (orderStatus.equals("Sent to GIS")) {
            ibPage.pressSAP();        
            System.out.println("SAP selected. Saving...");
        } else {
            ibPage.pressLRM();
            System.out.println("LRM selected. Saving...");
        }
        
        ibPage.pressSave();     
        
        System.out.println("----------------------------------------------------");
        driver.quit();
        
    } //Page checks, filter functions, list orders, view, select send to, save
    
    @Test 
    public void I2() {
        //New driver object to control browser
        WebDriver driver = new ChromeDriver();
        
        //New base object to handle log-in and set up
        Cce_SOC_Base base = new Cce_SOC_Base(driver);
        
        //Set up returns a CCE Page and outputs test details
        CcePage ccePage = base.SUSST_SetUp("Inbox I2: Reset, cancel, and print", "C_CCE_I_5");
        
        System.out.println("Navigating to Inbox...");
        
        InboxPage_CCE ibPage = ccePage.pressInbox();
        
        System.out.println("Inbox loaded. Entering filter criteria...");
        
        ibPage.setCustName(TestSuite.custDetails[0]);
        ibPage.setRequester(TestSuite.custDetails[2]);
        
        System.out.println("Criteria entered. Pressing reset...");
        
        ibPage.pressReset();
        
        System.out.println("Filter reset. Pressing 'send to SAP'...");
        
        ibPage.pressSAP();
        
        System.out.println("SAP selected. Pressing cancel...");
        
        ibPage.pressCancel();
        
        System.out.println("Cancel pressed. Pressing print...");
        
        ibPage.pressPrint();
        
        System.out.println("Test complete.");
        System.out.println("----------------------------------------------------");
        driver.quit();
        
    } //Reset filter, cancel, and print
    
    @Test public void IS1() {
        //New driver object to control browser
        WebDriver driver = new ChromeDriver();
        
        //New base object to handle log-in and set up
        Cce_SOC_Base base = new Cce_SOC_Base(driver);
        
        //Set up returns a CCE Page and outputs test details
        CcePage ccePage = base.SUSST_SetUp("Inbox SAP IS2: Page check, filter functions, list orders, reset", "C_CCE_I_5");
        
        System.out.println("Navigating to Inbox...");
        
        InboxSAPPage_CCE isPage = ccePage.pressInboxSAP();
        
        System.out.println("Inbox loaded. Entering filter criteria...");
        
        isPage.setHub(TestSuite.hub);
        
        System.out.println("Criteria entered. Listing orders...");
        
        isPage.pressListOrders();
        
        System.out.println("Orders listed. Entering search criteria...");
        
        isPage.setSalesOrg(TestSuite.salesOrganisation);
        isPage.setHub(TestSuite.hub);
        
        System.out.println("Criteria entered. Pressin reset...");
        
        isPage.pressReset();
        
        System.out.println("Reset pressed.");
        
        System.out.println("----------------------------------------------------");
        
        driver.quit();
    }
    
}
