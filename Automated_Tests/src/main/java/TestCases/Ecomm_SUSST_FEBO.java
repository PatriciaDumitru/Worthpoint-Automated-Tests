
package TestCases;

import AutomationFramework.Categories;
import AutomationFramework.DataItems;
import AutomationFramework.TestSuite;
import PageObjects.Ecomm_MainPage;
import PageObjects.Ecomm_FromExistingPage;
import PageObjects.Ecomm_ManualEntryPage;
import PageObjects.Ecomm_OrderConfirmationPage;
import PageObjects.Ecomm_OrderListPage;
import PageObjects.Ecomm_OutstandingOrdersPage;
import PageObjects.Ecomm_PendingApprovalListPage;
import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

@Category(Categories.eComm.class)
public class Ecomm_SUSST_FEBO {
      
@Category(Categories.Unstable.class)
@Test //From Existing Bulk Order Page :: Page checks, create order 
public void FEBO1() throws IOException {
    //New driver
    WebDriver driver = new ChromeDriver();
  
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
    
    driver.close();
    driver.quit();
    */
    System.out.println("----------------------------------------------------");
    
    }
}
