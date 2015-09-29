
package TestCases;

import AutomationFramework.*;
import PageObjects.CcePage;
import PageObjects.ExportDownloadPage_CCE;
import PageObjects.OrderCycleTimePage_CCE;
import PageObjects.OrderViewPage_CCE;
import PageObjects.TotalOrdersPage_CCE;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class Cce_TotalOrders {
    
    @Test
    public void TOR1() {
        //New driver object
        WebDriver driver = new ChromeDriver();
        
        //New base object to handle log-in and set up
        Cce_SOC_Base base = new Cce_SOC_Base(driver);
        
        //Set up returns a CCE Page and outputs test details
        CcePage ccePage = base.SUSST_SetUp("Total Orders: Page and filter checks, field selection, export, and reset", "C_CCE_TOR_1-4");
        
        System.out.println("Navigating to Total Orders Page...");
        
        TotalOrdersPage_CCE toPage = ccePage.pressTotalOrders();
        
        System.out.println("Total Orders reached. Checking title...");
        
        Assert.assertTrue("Total Orders Page: Title not displayed as expected",toPage.getBreadcrumb().getText().equals("Reports | Total Orders"));
        
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
        
        toPage.setCustName(TestSuite.custDetails[0]);
        toPage.setRequester(TestSuite.custDetails[2]);
        toPage.setMUMType(TestSuite.coneMUM);
        
        System.out.println("Filter criteria entered. Printing records...");
        
        OrderViewPage_CCE viewPage = toPage.pressPrint();
        
        System.out.println("View displayed. Closing view...");
        
        viewPage.waitForLoad();
        viewPage.closeView();
        
        System.out.println("View closed. Exporting to excel...");
        
        ExportDownloadPage_CCE dlPage = toPage.pressExport();               
        dlPage.waitForDownloadCompletion();
        
        System.out.println("Export complete. Resetting filter...");
        
        toPage.pressReset();
        
        System.out.println("Filter reset.");
        
        System.out.println("----------------------------------------------------");
        
        driver.quit();
    }
    
}
