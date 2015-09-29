
package TestCases;

import AutomationFramework.TestSuite;
import PageObjects.CcePage;
import PageObjects.ExportDownloadPage_CCE;
import PageObjects.FCETaskStatusPage_CCE;
import PageObjects.OrderViewPage_CCE;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Cce_FCETaskStatus {
    
    @Test
    public void TS1() {
        //New driver object
        WebDriver driver = new ChromeDriver();
        
        //New base object to handle log-in and set up
        Cce_SOC_Base base = new Cce_SOC_Base(driver);
        
        //Set up returns a CCE Page and outputs test details
        CcePage ccePage = base.SUSST_SetUp("FCE Task Status: Page and filter checks, print, and export", "C_CCE_FCETR_1-4");
        
        System.out.println("Navigating to FCE Task Status...");
        
        FCETaskStatusPage_CCE fcePage = ccePage.pressFCETaskStatus();
        
        System.out.println("FCE Task Status reached. Checking title...");
        
        Assert.assertTrue("FCE Task Status Page: Title not displayed as expected",fcePage.getBreadcrumb().getText().equals("Reports | FCE Task Status"));
        
        System.out.println("Title checked. Asserting base elements...");
        
        fcePage.assertBaseElements();
        
        System.out.println("Base elements asseted. Checking fields...");
        
        fcePage.checkFields();
        
        System.out.println("Fields checked. Entering filter criteria...");
        
        fcePage.setSalesOrg("ID51");
        fcePage.setTaskType("Visit");
        fcePage.setTaskStatus("Completed");
        fcePage.setHub("IDH004");
        
        System.out.println("Criteria entered. Printing records...");
        
        OrderViewPage_CCE viewPage = fcePage.pressPrint();
        viewPage.waitForLoad();
        
        System.out.println("Records displayed. Closing view...");

        viewPage.closeView();
        //wait for frame to completely disappear
        viewPage.waitForInvisibility();
        
        System.out.println("View closed. Exporting records to excel...");
        
        ExportDownloadPage_CCE exportPage = fcePage.pressExport();
        
        exportPage.waitForDownloadCompletion();
        
        System.out.println("Export pressed, download completed.");
        
        System.out.println("----------------------------------------------------");
        driver.quit();
    }//Page and filter checks, print records, export (NEEDS WORK ON EXPORT)
    
    @Ignore @Test
    public void TS2() {
        //New driver object
        WebDriver driver = new ChromeDriver();
        
        //New base object to handle log-in and set up
        Cce_SOC_Base base = new Cce_SOC_Base(driver);
        
        //Set up returns a CCE Page and outputs test details
        CcePage ccePage = base.SUSST_SetUp("FCE Task Status: Page and filter checks, print, and export", "C_CCE_FCETR_1-4");
        
        System.out.println("Navigating to FCE Task Status...");
        
        FCETaskStatusPage_CCE fcePage = ccePage.pressFCETaskStatus();
        
        System.out.println("FCE Task Status reached. Entering filter criteria...");
        
        fcePage.setSalesOrg("ID51");
        fcePage.setTaskStatus("Completed");
        
        System.out.println("Criteria entered. Pressing reset...");
        
        fcePage.pressReset();
        
        System.out.println("Reset pressed.");
                
        System.out.println("----------------------------------------------------");
        
        driver.quit();               
        
    } //reset filter
    
}
