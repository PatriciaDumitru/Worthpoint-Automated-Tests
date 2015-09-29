
package TestCases;

import AutomationFramework.*;
import PageObjects.CcePage;
import PageObjects.ExportDownloadPage_CCE;
import PageObjects.FCETaskStatusPage_CCE;
import PageObjects.OrderCycleTimePage_CCE;
import PageObjects.OrderViewPage_CCE;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class Cce_OrderCycleTime {
    
    @Test
    public void OCTR1() {
        //New driver object
        WebDriver driver = new ChromeDriver();
        
        //New base object to handle log-in and set up
        Cce_SOC_Base base = new Cce_SOC_Base(driver);
        
        //Set up returns a CCE Page and outputs test details
        CcePage ccePage = base.SUSST_SetUp("Order Cycle Time: Page and filter checks, print, export, and reset", "C_CCE_OCTR_1-5");
        
        System.out.println("Navigating to Order Cycle Time...");
        
        OrderCycleTimePage_CCE octPage = ccePage.pressOrderCycleTime();
        
        System.out.println("Order Cycle Time reached. Checking title...");
        
        Assert.assertTrue("Order Cycle Time Page: Title not displayed as expected",octPage.getBreadcrumb().getText().equals("Reports | Order Cycle Time"));
        
        System.out.println("Title checked.");
        
        octPage.assertBaseElements();
        
        System.out.println("Base elements asseted. Checking fields...");
        
        octPage.checkFields();
        
        System.out.println("Fields checked. Entering filter criteria...");
        
        octPage.setShipToPartyName(TestSuite.custDetails[1]);
        
        System.out.println("Criteria entered. Printing records...");
        
        OrderViewPage_CCE viewPage = octPage.pressPrint();
        
        System.out.println("Record view displayed. Closing view...");
        
        viewPage.closeView();
        
        System.out.println("View closed. Exporting to excel...");
        
        ExportDownloadPage_CCE dlPage = octPage.pressExport();
        
        dlPage.waitForDownloadCompletion();
        
        System.out.println("Exported. Resetting filter...");
        
        octPage.pressReset();
        
        System.out.println("Filter reset.");
        
        System.out.println("----------------------------------------------------");
        
        driver.quit();
    }
    
}
