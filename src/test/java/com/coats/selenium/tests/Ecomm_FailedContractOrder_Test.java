
package com.coats.selenium.tests;

import AutomationFramework.DataItems;
import PageObjects.Ecomm_FailedContractOrderPage;
import PageObjects.Ecomm_MainPage;
import PageObjects.Ecomm_OrderConfirmationPage;
import PageObjects.Ecomm_OrderViewPage;
import com.coats.selenium.DriverFactory;
import static com.coats.selenium.DriverFactory.getDriver;
import java.io.File;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.AssertJUnit;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class Ecomm_FailedContractOrder_Test extends DriverFactory {
    
    @Test //Failed Contract Order Page :: Page and filter checks, search, reset, view and edit
    (groups = {"eComm"})
    public void FCO1() throws Exception {
        
        //new driver instance
        WebDriver driver = getDriver();
		
        //New eComm base test to handle log-in and navigation
        Ecomm_SUSST_Base baseTest = new Ecomm_SUSST_Base(driver);
        Ecomm_MainPage eCommPage = baseTest.SUSST_SetUp("Failed Contract Order Page FCO1: Page and filter checks, search, reset, view, and edit", "Unknown",DataItems.validCustUsername,DataItems.validCustPassword);
    
        System.out.println("Navigating to Failed Contract Order Page...");
        
        Ecomm_FailedContractOrderPage fcoPage = eCommPage.clickFailedContractOrder();
        fcoPage.waitForLoad();
        
        System.out.println("Page reached. Checking title...");
        
        AssertJUnit.assertTrue("Failed Contract Order Page: Title not as expected", fcoPage.getBreadcrumb().getText().equals("Orders | Failed Contract Orders"));
        
        System.out.println("Title checked.");
        
        //Take a screenshot
        File scrFile1 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile1,new File(DataItems.screenshotsFilepath+"\\EComm\\Dashboard\\Failed Contract Order\\1Failed Contract Order Page.png"));
        
        fcoPage.assertBaseElements();
        
        System.out.println("Checking fields...");
        
        fcoPage.checkFields();
        
        System.out.println("Fields checked. Entering filter criteria...");
        
        fcoPage.setSalesOrg("LK53");
        
        System.out.println("Filter criteria entered. Listing records...");
        
        Ecomm_FailedContractOrderPage fcoPage2 = fcoPage.pressSearch();
        fcoPage2.waitForLoad();
        
        //Take a screenshot
        File scrFile2 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile2,new File(DataItems.screenshotsFilepath+"\\EComm\\Dashboard\\Failed Contract Order\\2Filter tested.png"));
        
        System.out.println("Records listed. Resetting filter...");
        
        Ecomm_FailedContractOrderPage fcoPage3 = fcoPage2.pressReset();
        fcoPage3.waitForLoad();
        
        //Take a screenshot
        File scrFile3 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile3,new File(DataItems.screenshotsFilepath+"\\EComm\\Dashboard\\Failed Contract Order\\3Filter reset.png"));
        
        System.out.println("Filter reset. Checking for records...");
        
        if (fcoPage3.checkForRecords()) {
            System.out.println("Records found. Viewing top record...");
            
            Ecomm_OrderViewPage viewPage = fcoPage3.pressView(1);
            viewPage.waitForContent();
            
            System.out.println("View displayed. Error message: " + viewPage.getCOError());
            
            //Take a screenshot
            File scrFile4 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile4,new File(DataItems.screenshotsFilepath+"\\EComm\\Dashboard\\Failed Contract Order\\4View displayed.png"));
            
            System.out.println("Closing view...");
            
            viewPage.closeView();
            viewPage.waitForInvisibility();
            //driver.switchTo().defaultContent();
            
            System.out.println("View closed. Editing top record...");
            
            Ecomm_OrderConfirmationPage orderConf = fcoPage3.pressEdit(1);
            
            try {
                Alert alert = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.alertIsPresent());
                System.out.println("Alert appeared: " + alert.getText());
                alert.accept();
            } catch (Exception e) {
                System.out.println("No alert open");
            }
            
            orderConf.waitForLoad();
            
            //Take a screenshot
            File scrFile5 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile5,new File(DataItems.screenshotsFilepath+"\\EComm\\Dashboard\\Failed Contract Order\\5Order confirmation page reached.png"));
            
            System.out.println("Order Confirmation Page reached.");
    
        } else {
            System.out.println("No records found.");
        }
        
        
    }
    
}
