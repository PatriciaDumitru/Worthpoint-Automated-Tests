
package com.coats.selenium.tests;

import AutomationFramework.DataItems;
import PageObjects.Ecomm_MainPage;
import PageObjects.Ecomm_FromExistingPage;
import com.coats.selenium.DriverFactory;
import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class Ecomm_SUSST_FEBO_IT extends DriverFactory {

    @Test //From Existing Bulk Order Page :: Page checks, create order 
    public void FEBO1() throws IOException, Exception {
    //New driver
    WebDriver driver = getDriver();
  
    //new base test to handle set up
    Ecomm_SUSST_Base susstTest8 = new Ecomm_SUSST_Base(driver);
    //Set up returns an eComm main page
    Ecomm_MainPage eCommPage = susstTest8.SUSST_SetUp("FROM EXISTING BULK ORDER FEBO1: Page checks and creates order using existing order","G_OOC_FWBL_1-5");
        
    
    System.out.println("Navigating to From Existing Bulk Order page...");
    
    //Navigate to From Existing Bulk Order page
    Ecomm_FromExistingPage fromExistingPage = eCommPage.clickFromExisting();
    fromExistingPage.waitForLoad();
    
    System.out.println("From Existing page reached.");

    //Take a screenshot
    File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    FileUtils.copyFile(scrFile,new File(DataItems.screenshotsFilepath+"\\EComm\\Orders\\From Ex Bulk\\1From Ex Bulk Page.png"));
    
    //make assertions for base page elements and upload page elements
    fromExistingPage.assertBaseElements();
    
    System.out.println("Entering criteria...");
    
    //Enter criteria
    fromExistingPage.setBrand("astra");
    
    //Take a screenshot
    File scrFile2 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    FileUtils.copyFile(scrFile2,new File(DataItems.screenshotsFilepath+"\\EComm\\Orders\\From Ex Bulk\\2Order number entered.png"));
    /*
    //Submit search
    Ecomm_ManualEntryPage manualEntryPage = fromExistingPage.pressLoad();
    manualEntryPage.waitForLoad();
    
    //Wait for items to load
    Boolean waitForItems = new WebDriverWait(driver,5).until(ExpectedConditions.textToBePresentInElement(manualEntryPage.getQty(), "3"));
    
    //Take a screenshot
    File scrFile1 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    FileUtils.copyFile(scrFile1,new File(TestSuiteOLD.screenshotsFilepath+"\\EComm\\Orders\\From Ex Bulk\\3Order loaded.png"));
    
    Ecomm_OrderConfirmationPage orderConf = manualEntryPage.pressNext();
    orderConf.waitForLoad();
    
    //Take a screenshot
    File scrFile3 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    FileUtils.copyFile(scrFile3,new File(TestSuiteOLD.screenshotsFilepath+"\\EComm\\Orders\\From Ex Bulk\\4Next pressed.png"));
    
    Ecomm_OutstandingOrdersPage outOrdersPage = orderConf.pressSubmit();
    
    //Take a screenshot
    File scrFile4 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    FileUtils.copyFile(scrFile4,new File(TestSuiteOLD.screenshotsFilepath+"\\EComm\\Orders\\From Ex Bulk\\5Order confirmed.png"));
    
    */
    
    }

}
