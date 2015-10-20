package com.coats.selenium.tests;

import AutomationFramework.*;
import PageObjects.CCE_MainPage;
import PageObjects.Ecomm_ExportDownloadPage;
import PageObjects.CCE_OrderCycleTimePage;
import PageObjects.CCE_OrderViewPage;
import com.coats.selenium.DriverFactory;
import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.testng.AssertJUnit;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class Cce_OrderCycleTime_IT extends DriverFactory {
    
    @Test //Order Cycle Time Page :: Page and filter checks, print, export, and reset
    public void OCTR1() throws IOException, Exception {
        //New driver object
        WebDriver driver = getDriver();
        
        //New base object to handle log-in and set up
        Cce_SOC_Base base = new Cce_SOC_Base(driver);
        
        //Set up returns a CCE Page and outputs test details
        CCE_MainPage ccePage = base.SUSST_SetUp("Order Cycle Time: Page and filter checks, print, export, and reset", "C_CCE_OCTR_1-5");
        
        System.out.println("Navigating to Order Cycle Time...");
        
        CCE_OrderCycleTimePage octPage = ccePage.pressOrderCycleTime();
        octPage.waitForLoad();
        
        //Take a screenshot
        File scrFile1 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile1,new File(DataItems.screenshotsFilepath+"\\CCE\\Reports\\Order Cycle Time\\1Order Cycle Time Page.png"));
        
        System.out.println("Order Cycle Time reached. Checking title...");
        
        AssertJUnit.assertTrue("Order Cycle Time Page: Title not displayed as expected",octPage.getBreadcrumb().getText().equals("Reports | Order Cycle Time"));
        
        System.out.println("Title checked.");
        
        octPage.assertBaseElements();
        
        System.out.println("Checking fields...");
        
        octPage.checkFields();
        
        System.out.println("Fields checked. Entering filter criteria...");
        
        octPage.setShipToPartyName(DataItems.custDetails[1]);
        
        //Take a screenshot
        File scrFile2 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile2,new File(DataItems.screenshotsFilepath+"\\CCE\\Reports\\Order Cycle Time\\2Filter Criteria entered.png"));
        
        System.out.println("Criteria entered. Printing records...");
        
        CCE_OrderViewPage viewPage = octPage.pressPrint();
        viewPage.waitForContentAlt2();
        
        //Take a screenshot
        File scrFile3 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile3,new File(DataItems.screenshotsFilepath+"\\CCE\\Reports\\Order Cycle Time\\3Orders displayed.png"));
        
        System.out.println("Record view displayed. Closing view...");
        
        viewPage.closeView();
        viewPage.waitForInvisibility();
        
        System.out.println("View closed. Exporting to excel...");
        
        Ecomm_ExportDownloadPage dlPage = octPage.pressExport();      
        dlPage.waitForDownloadCompletion();
        
        System.out.println("Exported. Resetting filter...");
        
        octPage.pressReset();
        octPage.waitForLoad();
        
        //Take a screenshot
        File scrFile4 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile4,new File(DataItems.screenshotsFilepath+"\\CCE\\Reports\\Order Cycle Time\\4Filter reset.png"));
        
        System.out.println("Filter reset.");
        
    }
    
}
