
package TestCases;

import AutomationFramework.*;
import PageObjects.CcePage;
import PageObjects.ExportDownloadPage;
import PageObjects.OrderCycleTimePage_CCE;
import PageObjects.OrderViewPage_CCE;
import PageObjects.TotalOrdersPage_CCE;
import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class Cce_TotalOrders {
    
    @Test //Total Orders Page :: Page and filter checks, field selection, export and reset
    public void TOR1() throws IOException {
        //New driver object
        WebDriver driver = new ChromeDriver();
        
        //New base object to handle log-in and set up
        Cce_SOC_Base base = new Cce_SOC_Base(driver);
        
        //Set up returns a CCE Page and outputs test details
        CcePage ccePage = base.SUSST_SetUp("Total Orders: Page and filter checks, field selection, export, and reset", "C_CCE_TOR_1-4");
        
        System.out.println("Navigating to Total Orders Page...");
        
        TotalOrdersPage_CCE toPage = ccePage.pressTotalOrders();
        toPage.waitForLoad();
        
        //Take a screenshot
        File scrFile1 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile1,new File(TestSuite.screenshotsFilepath+"\\CCE\\Reports\\Total Orders\\1Total Orders page.png"));
        
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
        
        //Take a screenshot
        File scrFile2 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile2,new File(TestSuite.screenshotsFilepath+"\\CCE\\Reports\\Total Orders\\2Fields set.png"));
        
        System.out.println("Fields set. Entering filter criteria...");
        
        toPage.setCustName(TestSuite.custDetails[0]);
        toPage.setRequester(TestSuite.custDetails[2]);
        toPage.setMUMType(TestSuite.coneMUM);
        
        //Take a screenshot
        File scrFile3 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile3,new File(TestSuite.screenshotsFilepath+"\\CCE\\Reports\\Total Orders\\3Filter criteria entered.png"));
        
        System.out.println("Filter criteria entered. Printing records...");
        
        OrderViewPage_CCE viewPage = toPage.pressPrint();
        viewPage.waitForLoad();
        
        //Take a screenshot
        File scrFile4 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile4,new File(TestSuite.screenshotsFilepath+"\\CCE\\Reports\\Total Orders\\4Records displayed.png"));
        
        System.out.println("View displayed. Closing view...");
        
        viewPage.closeView();
        
        System.out.println("View closed. Exporting to excel...");
        
        ExportDownloadPage dlPage = toPage.pressExport();               
        dlPage.waitForDownloadCompletion();
        
        System.out.println("Export complete. Resetting filter...");
        
        toPage.pressReset();
        
        System.out.println("Filter reset.");
        
        System.out.println("----------------------------------------------------");
        
        driver.close();
    }
    
}
