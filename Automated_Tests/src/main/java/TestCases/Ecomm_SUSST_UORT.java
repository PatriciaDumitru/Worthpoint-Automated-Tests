
package TestCases;

import AutomationFramework.TestSuite;
import PageObjects.EcommPage;
import PageObjects.MappingAlert;
import PageObjects.MappingPage;
import PageObjects.OrderConfirmationPage;
import PageObjects.OutstandingOrdersPage;
import PageObjects.UploadConfirmationPage;
import PageObjects.UploadOrderPage;
import java.awt.AWTException;
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
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class Ecomm_SUSST_UORT {
    
    @Test //Upload Order Page :: Page checks and realtime upload order of <100 lines
    public void UORT1() throws AWTException, IOException {
        //new chrome driver
        WebDriver driver = new ChromeDriver();
        
        //new base test to set up
        Ecomm_SUSST_Base uortTest1 = new Ecomm_SUSST_Base(driver);
        //Set up returns an eComm page
        EcommPage eCommPage = uortTest1.SUSST_SetUp("UPLOAD ORDER TEST 1: File of <100 lines, realtime upload", "G_OOC_UORT_SUSST");
        
        System.out.println("Navigating to Upload Order...");
        
        //new upload order page
        UploadOrderPage uploadPage = eCommPage.clickUploadOrder();
        
        System.out.println("Upload Order loaded.");
        
        System.out.println("Asserting elements...");
        //make assertions for base page elements and upload page elements
        uploadPage.assertBaseElements();
        //Wait for page to load before asserting the other elements
        WebElement waitForLoad = new WebDriverWait(driver,10).until(ExpectedConditions.visibilityOf(uploadPage.getUploadButton()));
        Assert.assertTrue("Upload Order page: File name field not displayed",uploadPage.getFileNameOutputField().isDisplayed());
        Assert.assertTrue("Upload Order page: Realtime upload radio button not displayed",uploadPage.getRealtimeRadio().isDisplayed());
        Assert.assertTrue("Upload Order page: Backend upload radio button not displayed",uploadPage.getBackendRadio().isDisplayed());
        Assert.assertTrue("Upload Order page: Upload button not displayed",uploadPage.getUploadButton().isDisplayed());    
        
        //Take a screenshot
        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile,new File(TestSuite.screenshotsFilepath+"\\EComm\\Upload Order\\1Upload Order page.png"));
        
        System.out.println("Assertions successful. Sending file path...");
        
        //Send file path to field
        uploadPage.setFilePath(TestSuite.uploadOrderFilepath);
        //Select realtime upload
        uploadPage.pressRealtime();
        
        //Press upload
        MappingAlert alert = uploadPage.pressUpload();
        
        System.out.println("Upload pressed. Choosing new mapping...");
        
        //Press "no" to alert, continuing to mapping page
        MappingPage mapPage = alert.pressNo();
        
        System.out.println("Mapping page loaded. Setting mapping...");
        
        //Mapping details
        //Element 0 of each array holds the field name. Element 1 of each array holds the corresponding header used in the file.
        //If there is no corresponding header in the file, use "N/A" 
        String[][] mapping = {  {"Customer Name","Customer Name"},
                                {"Article","N/A"},
                                {"Ticket","Ticket"},
                                {"Finish","Finish"},
                                {"Shade Code","Shade Code"},
                                {"Required Date","Required Date"},
                                {"Qty","Qty"},
                                {"Style","N/A"},
                                {"Style No./Production No.","N/A"},
                                {"Sub Account","N/A"},
                                {"Ship to Party Name","Ship to Party Name"},
                                {"Your Material No.","N/A"},
                                {"Brand","Brand"},
                                {"Length","Length"},
                                {"Buyers","N/A"},
                                {"Customer PO No","Customer PO No"},
                                {"Requestor Name","Requestor Name"},
                                {"Warehouse Instruction","N/A"},
                                {"Buyer Sales Order Number","N/A"},
                                {"Other Information","N/A"},
                                {"Customer Price","N/A"}
                                };
        
        MappingPage mappedPage = mapPage.setMapping(mapping);
        
        System.out.println("Mapping set. Confirming map...");
        
        //Take a screenshot
        File scrFile3 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile3,new File(TestSuite.screenshotsFilepath+"\\EComm\\Upload Order\\2Mapping set.png")); 
        
        OrderConfirmationPage orderConf = mappedPage.pressConfirm();
        
        System.out.println("Map confirmed.");
        
        //Set requester name
        orderConf.setRequestor(TestSuite.UORTrequestor);
        
        //Take a screenshot
        File scrFile2 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile2,new File(TestSuite.screenshotsFilepath+"\\EComm\\Upload Order\\3Upload Confirmation page.png"));                    
        
        OutstandingOrdersPage outstOrders = orderConf.pressSubmit();
        
        System.out.println("Order submitted.");
        
        System.out.println("Order number: "+outstOrders.getOrderNumber(0));
        
        //Take a screenshot
        File scrFile4 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile4,new File(TestSuite.screenshotsFilepath+"\\EComm\\Upload Order\\4Oustanding upload orders.png"));
        
        driver.close();
        
        System.out.println("----------------------------------------------------");
        
    }
    
    /*@Test
    public void UORT2() {
        //new chrome driver
        WebDriver driver = new ChromeDriver();
        
        //new base test to set up
        SUSST_Base uortTest2 = new SUSST_Base(driver);
        //Set up returns an eComm page
        EcommPage eCommPage = uortTest2.SUSST_SetUp("UPLOAD ORDER TEST 2: File of <100 lines, realtime upload, multiple sold to", "G_OOC_UORT_SUSST");
        
        System.out.println("Navigating to Upload Order...");
        
        //new upload order page
        UploadOrderPage uploadPage = eCommPage.clickUploadOrder();
        
        System.out.println("Upload Order loaded.");
    }*/
    
    
    
}
    

