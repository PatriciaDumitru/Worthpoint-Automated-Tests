
package TestCases;

import AutomationFramework.TestSuite;
import PageObjects.CcePage;
import PageObjects.ExportDownloadPage_CCE;
import PageObjects.LRMLogPage_CCE;
import PageObjects.OrderViewPage_CCE;
import PageObjects.SAPLogPage_CCE;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Cce_SAPLog {
    
    @Test
    public void SAP1() {
        //New driver object
        WebDriver driver = new ChromeDriver();
        
        //New base object to handle log-in and set up
        Cce_SOC_Base base = new Cce_SOC_Base(driver);
        
        //Set up returns a CCE Page and outputs test details
        CcePage ccePage = base.SUSST_SetUp("SAP Log: Page and filter checks, search, reset, export", "C_CCE_A_SAP_1-5");
        
        System.out.println("Navigating to SAP Log Page...");
        
        SAPLogPage_CCE sapPage = ccePage.pressSAPLog();
        
        System.out.println("SAP Log reached. Checking title...");
        
        Assert.assertTrue("SAP Log Page: Title not displayed as expected",sapPage.getBreadcrumb().getText().equals("SAP Log"));
        
        System.out.println("Title checked.");
        
        sapPage.assertBaseElements();
        
        System.out.println("Checking fields...");
        
        sapPage.checkFields();
        
        System.out.println("Fields checked. Entering filter criteria...");
        
        sapPage.setCustName(TestSuite.custDetails[0]);
        sapPage.setMUMType(TestSuite.copMUM);
        sapPage.setSapStatus("Created in SAP");
        
        System.out.println("Filter criteria entered. Pressing search...");
        
        sapPage.pressSearch();
        
        System.out.println("Records listed. Viewing top item...");
        
        OrderViewPage_CCE viewPage = sapPage.pressView();
        viewPage.waitForContent();
        
        System.out.println("View displayed. Closing view...");
        
        viewPage.closeView();
        
        System.out.println("View closed. Exporting records...");
        
        ExportDownloadPage_CCE dlPage = sapPage.pressExport();
        dlPage.waitForDownloadCompletion();
        
        System.out.println("Records exported. Resetting filter...");
        
        sapPage.pressReset();
        
        System.out.println("Filter reset.");
        
        System.out.println("----------------------------------------------------");
        driver.quit();

    }
}
