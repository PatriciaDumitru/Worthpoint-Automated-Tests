
package TestCases;

import AutomationFramework.TestSuite;
import PageObjects.EcommPage;
import PageObjects.FromExistingPage_Ecomm;
import PageObjects.ManualEntryPage;
import PageObjects.OrderConfirmationPage;
import PageObjects.OrderListPage;
import PageObjects.OutstandingOrdersPage;
import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class Ecomm_SUSST_FEBO {
      
@Test //From Existing Bulk Order Page :: Page checks, create order 
public void FEBO1() throws IOException {
    //New driver
    WebDriver driver = new ChromeDriver();
  
    //new base test to handle set up
    Ecomm_SUSST_Base susstTest8 = new Ecomm_SUSST_Base(driver);
    //Set up returns an eComm main page
    EcommPage eCommPage = susstTest8.SUSST_SetUp("FROM EXISTING BULK ORDER FEBO1: Page checks and creates order using existing order","G_OOC_FWBL_1-5");
        
    System.out.println("Navigating to From Existing Bulk Order page...");
    
    //Navigate to From Existing Bulk Order page
    FromExistingPage_Ecomm fromExistingPage = eCommPage.clickFromExisting();
    
    System.out.println("From Existing page reached. Making assertions...");

    //Take a screenshot
    File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    FileUtils.copyFile(scrFile,new File(TestSuite.screenshotsFilepath+"\\EComm\\From Ex Bulk\\1From Ex Bulk Page.png"));
    
    //make assertions for base page elements and upload page elements
    fromExistingPage.assertBaseElements();
    
    System.out.println("Assertions successful. Entering criteria...");
    
    //Enter criteria
    fromExistingPage.setOrderNo(TestSuite.bulkOrderNo);
    
    //Take a screenshot
    File scrFile2 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    FileUtils.copyFile(scrFile2,new File(TestSuite.screenshotsFilepath+"\\EComm\\From Ex Bulk\\2Order number entered.png"));
    
    //Submit search
    ManualEntryPage manualEntryPage = fromExistingPage.pressLoad();
    
    //Wait for items to load
    Boolean waitForItems = new WebDriverWait(driver,10).until(ExpectedConditions.textToBePresentInElement(manualEntryPage.getQty(), "3"));
    
    //Take a screenshot
    File scrFile1 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    FileUtils.copyFile(scrFile1,new File(TestSuite.screenshotsFilepath+"\\EComm\\From Ex Bulk\\3Order loaded.png"));
    
    OrderConfirmationPage orderConf = manualEntryPage.pressNext();
    
    //Take a screenshot
    File scrFile3 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    FileUtils.copyFile(scrFile3,new File(TestSuite.screenshotsFilepath+"\\EComm\\From Ex Bulk\\4Next pressed.png"));
    
    OutstandingOrdersPage outstOrders = orderConf.pressSubmit();
    
    //Take a screenshot
    File scrFile4 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    FileUtils.copyFile(scrFile4,new File(TestSuite.screenshotsFilepath+"\\EComm\\From Ex Bulk\\5Order confirmed.png"));
    
    driver.close();
    
    System.out.println("----------------------------------------------------");
    
    }
}
