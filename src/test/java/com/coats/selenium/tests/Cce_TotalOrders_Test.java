
package com.coats.selenium.tests;

import AutomationFramework.*;
import PageObjects.CCE_MainPage;
import PageObjects.Ecomm_ExportDownloadPage;
import PageObjects.CCE_OrderViewPage;
import PageObjects.CCE_TotalOrdersPage;
import com.coats.selenium.DriverFactory;
import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.testng.AssertJUnit;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Cce_TotalOrders_Test extends DriverFactory {
    
    @Test //Total Orders Page :: Page and filter checks, field selection, export and reset
    (groups = {"CCE"})
    public void TOR1() throws IOException, Exception {
        //New driver object
        WebDriver driver = getDriver();
        
        //New base object to handle log-in and set up
        Cce_Base base = new Cce_Base(driver);
        
        //Set up returns a CCE Page and outputs test details
        CCE_MainPage ccePage = base.SUMST_SetUp("Total Orders: Page and filter checks, field selection, export, and reset", "C_CCE_TOR_1-4");
        
        System.out.println("Navigating to Total Orders Page...");
        
        CCE_TotalOrdersPage toPage = ccePage.pressTotalOrders();
        toPage.waitForLoad();
        
        //Take a screenshot
        File scrFile1 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile1,new File(DataItems.screenshotsFilepath+"\\CCE\\Reports\\Total Orders\\1Total Orders page.png"));
        
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
        
        //Take a screenshot
        File scrFile2 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile2,new File(DataItems.screenshotsFilepath+"\\CCE\\Reports\\Total Orders\\2Fields set.png"));
        
        System.out.println("Fields set. Entering filter criteria...");
        
        toPage.setCustName(DataItems.custDetails[0]);
        toPage.setRequester(DataItems.custDetails[2]);
        toPage.setMUMType(DataItems.coneMUM);
        
        //Take a screenshot
        File scrFile3 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile3,new File(DataItems.screenshotsFilepath+"\\CCE\\Reports\\Total Orders\\3Filter criteria entered.png"));
        
        System.out.println("Filter criteria entered. Printing records...");
        
        CCE_OrderViewPage viewPage = toPage.pressPrint();
        viewPage.waitForLoad();
        
        //Take a screenshot
        File scrFile4 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile4,new File(DataItems.screenshotsFilepath+"\\CCE\\Reports\\Total Orders\\4Records displayed.png"));
        
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
