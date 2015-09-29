
package TestCases;

import AutomationFramework.TestSuite;
import PageObjects.CcePage;
import PageObjects.ExportDownloadPage_CCE;
import PageObjects.LRMLogPage_CCE;
import PageObjects.OrderViewPage_CCE;
import PageObjects.TotalOrdersPage_CCE;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class Cce_LRMLog {
    
    @Test
    public void LRM1() {
        //New driver object
        WebDriver driver = new ChromeDriver();
        
        //New base object to handle log-in and set up
        Cce_SOC_Base base = new Cce_SOC_Base(driver);
        
        //Set up returns a CCE Page and outputs test details
        CcePage ccePage = base.SUSST_SetUp("LRM Log: Page and filter checks, view, resend, export", "C_CCE_LRM_1-5");
        
        System.out.println("Navigating to LRM Log Page...");
        
        LRMLogPage_CCE lrmPage = ccePage.pressLRMLog();
        
        System.out.println("LRM Log reached. Checking title...");
        
        Assert.assertTrue("LRM Log Page: Title not displayed as expected",lrmPage.getBreadcrumb().getText().equals("Lrm Log"));
        
        System.out.println("Title checked.");
        
        lrmPage.assertBaseElements();
        
        System.out.println("Checking fields...");
        
        lrmPage.checkFields();
        
        System.out.println("Fields checked. Entering filter criteria...");
        
        lrmPage.setShadeCode(TestSuite.expShadeCode2);
        lrmPage.setErrorMessage("Yes");
        
        System.out.println("Criteria entered. List orders....");

        lrmPage.pressSearch();
        
         System.out.println("Orders listed. Pressing view...");
        
        OrderViewPage_CCE viewPage = lrmPage.pressView();
        viewPage.waitForContent();
        
        System.out.println("View displayed. Closing view...");
        
        viewPage.closeView();
        
        System.out.println("View closed. Pressing re-send...");
        
        lrmPage.pressResend();
        
        System.out.println("Re-sent. Re-entering critera and listing orders...");
        
        lrmPage.setShadeCode(TestSuite.expShadeCode2);
        lrmPage.setErrorMessage("Yes");
        lrmPage.pressSearch();
        
        System.out.println("Orders listed. Exporting records...");
        
        ExportDownloadPage_CCE dlPage = lrmPage.pressExport();
        dlPage.waitForDownloadCompletion();
        
        System.out.println("Export complete. Resetting filter...");
        
        lrmPage.pressReset();
        
        System.out.println("Filter reset.");
        
        System.out.println("----------------------------------------------------");
        
        driver.quit();
              
    }
    
}
