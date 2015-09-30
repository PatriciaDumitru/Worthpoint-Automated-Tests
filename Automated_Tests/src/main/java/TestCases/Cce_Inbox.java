
package TestCases;

import AutomationFramework.TestSuite;
import PageObjects.CcePage;
import PageObjects.InboxPage_CCE;
import PageObjects.InboxSAPPage_CCE;
import PageObjects.OrderViewPage_CCE;
import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class Cce_Inbox {
    
    @Test
    public void I1() throws IOException {
        //New driver object to control browser
        WebDriver driver = new ChromeDriver();
        
        //New base object to handle log-in and set up
        Cce_SOC_Base base = new Cce_SOC_Base(driver);
        
        //Set up returns a CCE Page and outputs test details
        CcePage ccePage = base.SUSST_SetUp("Inbox I1: Page checks, filter function, list orders, saving, re-assigning SOS", "C_CCE_I_1,2,3,4,6");
        
        System.out.println("Navigating to Inbox...");
        
        InboxPage_CCE ibPage = ccePage.pressInbox();
        ibPage.waitForLoad();
        
        //Take a screenshot
        File scrFile1 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile1,new File(TestSuite.screenshotFolder+"\\CCE\\Inbox\\Inbox\\1Inbox page.png"));
        
        System.out.println("Inbox reached.");
        
        //Assert base elements
        ibPage.assertBaseElements();
        
        System.out.println("Asserting page title is as expected...");
        
        Assert.assertTrue("Inbox Page: Title not as expected",ibPage.getBreadcrumb().getText().equals("Orders | Inbox"));
        
        System.out.println("Title as expected. Checking fields...");
        
        ibPage.checkFields();
        
        System.out.println("Fields checked. Entering filter criteria...");
        
        ibPage.setCustName(TestSuite.custDetails[0]);
        
        //Take a screenshot
        File scrFile2 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile2,new File(TestSuite.screenshotFolder+"\\CCE\\Inbox\\Inbox\\2Filter criteria entered.png"));
        
        System.out.println("Criteria entered. Listing orders...");
        
        ibPage.pressListOrders();
        ibPage.waitForLoad();
        
        //Take a screenshot
        File scrFile3 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile3,new File(TestSuite.screenshotFolder+"\\CCE\\Inbox\\Inbox\\3Orders listed.png"));
        
        System.out.println("Orders listed. Viewing order...");
        
        OrderViewPage_CCE viewPage = ibPage.pressView();
        viewPage.waitForContent();
        
        //Take a screenshot
        File scrFile4 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile4,new File(TestSuite.screenshotFolder+"\\CCE\\Inbox\\Inbox\\4Order view.png"));
        
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
        
        //Take a screenshot
        File scrFile5 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile5,new File(TestSuite.screenshotFolder+"\\CCE\\Inbox\\Inbox\\5Send to selected.png"));
        
        ibPage.pressSave();     
        ibPage.waitForLoad();
        
        //Take a screenshot
        File scrFile6 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile6,new File(TestSuite.screenshotFolder+"\\CCE\\Inbox\\Inbox\\6Changes saved.png"));
        
        System.out.println("----------------------------------------------------");
        driver.quit();
        
    } //Page checks, filter functions, list orders, view, select send to, save
    
    @Test 
    public void I2() throws IOException {
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
        
        //Take a screenshot
        File scrFile7 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile7,new File(TestSuite.screenshotFolder+"\\CCE\\Inbox\\Inbox\\7Filter criteria entered.png"));
        
        System.out.println("Criteria entered. Pressing reset...");
        
        ibPage.pressReset();
        ibPage.waitForLoad();
        
        //Take a screenshot
        File scrFile8 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile8,new File(TestSuite.screenshotFolder+"\\CCE\\Inbox\\Inbox\\8Filter reset.png"));
        
        System.out.println("Filter reset. Pressing 'send to SAP'...");
        
        ibPage.pressSAP();
        
        //Take a screenshot
        File scrFile9 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile9,new File(TestSuite.screenshotFolder+"\\CCE\\Inbox\\Inbox\\9Send to SAP selected.png"));
        
        System.out.println("SAP selected. Pressing cancel...");
        
        ibPage.pressCancel();
        ibPage.waitForLoad();
        
        //Take a screenshot
        File scrFile10 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile10,new File(TestSuite.screenshotFolder+"\\CCE\\Inbox\\Inbox\\10Order cancelled.png"));
        
        System.out.println("Cancel pressed. Pressing print...");
        
        ibPage.pressPrint();
        
        System.out.println("Test complete.");
        System.out.println("----------------------------------------------------");
        driver.quit();
        
    } //Reset filter, cancel, and print
    
    @Ignore @Test public void IS1() throws IOException {
        //New driver object to control browser
        WebDriver driver = new ChromeDriver();
        
        //New base object to handle log-in and set up
        Cce_SOC_Base base = new Cce_SOC_Base(driver);
        
        //Set up returns a CCE Page and outputs test details
        CcePage ccePage = base.SUSST_SetUp("Inbox SAP IS2: Page check, filter functions, list orders, reset", "C_CCE_I_5");
        
        System.out.println("Navigating to Inbox...");
        
        InboxSAPPage_CCE isPage = ccePage.pressInboxSAP();
        isPage.waitForLoad();
        
        //Take a screenshot
        File scrFile1 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile1,new File(TestSuite.screenshotFolder+"\\CCE\\Inbox\\Inbox SAP\\1Inbox SAP page.png"));
        
        System.out.println("Inbox loaded. Entering filter criteria...");
        
        isPage.setHub(TestSuite.hub);
        
        //Take a screenshot
        File scrFile2 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile2,new File(TestSuite.screenshotFolder+"\\CCE\\Inbox\\Inbox SAP\\2Filter criteria entered.png"));
        
        System.out.println("Criteria entered. Listing orders...");
        
        isPage.pressListOrders();
        isPage.waitForLoad();
        
        //Take a screenshot
        File scrFile3 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile3,new File(TestSuite.screenshotFolder+"\\CCE\\Inbox\\Inbox SAP\\3Orders listed.png"));
        
        System.out.println("Orders listed. Entering search criteria...");
        
        isPage.setSalesOrg(TestSuite.salesOrganisation);
        isPage.setHub(TestSuite.hub);
        
        //Take a screenshot
        File scrFile4 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile4,new File(TestSuite.screenshotFolder+"\\CCE\\Inbox\\Inbox SAP\\4Filter criteria entered.png"));
        
        System.out.println("Criteria entered. Pressing reset...");
        
        isPage.pressReset();
        isPage.waitForLoad();
        
        //Take a screenshot
        File scrFile5 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile5,new File(TestSuite.screenshotFolder+"\\CCE\\Inbox\\Inbox SAP\\5Filter reset.png"));
        
        System.out.println("Reset pressed.");
        
        System.out.println("----------------------------------------------------");
        
        driver.quit();
    }
    
}
