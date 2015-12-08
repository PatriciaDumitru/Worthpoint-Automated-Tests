
package com.coats.selenium.tests;

import AutomationFramework.DataItems;
import AutomationFramework.FileFactory;
import PageObjects.Ecomm_BackendProcessPage;
import PageObjects.Ecomm_BackendInProcessPage;
import PageObjects.Ecomm_MainPage;
import PageObjects.Ecomm_MappingAlert;
import PageObjects.Ecomm_MappingPage;
import PageObjects.Ecomm_OrderConfirmationPage;
import PageObjects.Ecomm_OutstandingOrdersPage;
import PageObjects.Ecomm_UploadOrderPage;
import static com.coats.selenium.DriverFactory.getDriver;
import java.io.File;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

public class Ecomm_UO_SUSST_Test {
    
    @Test //Upload Order Page :: SUSST :: Realtime Upload order (<100 lines) (MOQ ACTIVE)
    (groups = {"eComm","eComm_Orders","Upload_Order"})
    public void RT1() throws Exception {
        //new chrome driver
        WebDriver driver = getDriver();
        
        //new base test to set up
        Ecomm_Base uortTest1 = new Ecomm_Base(driver);
        //Set up returns an eComm page
        Ecomm_MainPage eCommPage = uortTest1.setUp("UPLOAD ORDER SUSST TEST: File of <100 lines, realtime upload, MOQ active", "G_OOC_UORT_SUSST_MOQ",DataItems.susstUsername,DataItems.susstPassword);
        
        System.out.println("Navigating to Upload Order...");
        
        //new upload order page
        Ecomm_UploadOrderPage uploadPage = eCommPage.clickUploadOrder();
        uploadPage.waitForElement();
        
        System.out.println("Upload Order page loaded.");
        
        //make assertions for base page elements and upload page elements
        uploadPage.assertBaseElements();
        System.out.println("Asserting other elements...");
        //Wait for page to load before asserting the other elements
        WebElement waitForLoad = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.visibilityOf(uploadPage.getUploadButton()));
        AssertJUnit.assertTrue("Upload Order page: File name field not displayed",uploadPage.getFileNameOutputField().isDisplayed());
        AssertJUnit.assertTrue("Upload Order page: Realtime upload radio button not displayed",uploadPage.getRealtimeRadio().isDisplayed());
        AssertJUnit.assertTrue("Upload Order page: Backend upload radio button not displayed",uploadPage.getBackendRadio().isDisplayed());
        AssertJUnit.assertTrue("Upload Order page: Upload button not displayed",uploadPage.getUploadButton().isDisplayed());    
        
        System.out.println("Assertions successful. Sending file path...");
        
        //Send file path to field
        uploadPage.setFilePath(FileFactory.createFile("SUSST", 2, "MOQ", "", true));
        //Select realtime upload
        uploadPage.pressRealtime();
        
        //Press upload
        Ecomm_MappingAlert alert = uploadPage.pressUpload();
        
        System.out.println("Upload pressed. Choosing new mapping...");
        
        //Press "no" to alert, continuing to mapping page
        Ecomm_MappingPage mapPage = alert.pressYes();
        
        System.out.println("Mapping page loaded. Setting mapping...");
        
        String[][] mapping = {
            {"Article","Article"},
            {"Ticket","Ticket"},
            {"Finish","Finish"},
            {"Shade Code","Shade Code"},
            {"Required Date","Required Date"},
            {"Qty","Qty"},
            {"Style","N/A"},
            {"Style No./Production No.","N/A"},
            {"Contract PO No.","N/A"},
            {"Customer Price","N/A"},
            {"Sub Account","N/A"},
            {"Ship to Party Name","Ship to Party Name"},
            {"Your Material No.","Your Material Number"},
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
        
        Ecomm_MappingPage mappedPage = mapPage.setMappingNew(mapping,false,false,false);
        
        System.out.println("Mapping set. Confirming map..."); 
        
        Ecomm_OrderConfirmationPage orderConf = mappedPage.pressConfirmMOQ();
        orderConf.waitForElement();
        
        System.out.println("Map confirmed. Checking adjusted quantity...");

        AssertJUnit.assertTrue("Order Confirmation Page: Adjusted quantity less than or equal to ordered quantity",orderConf.getAdjustedQty()>1);
        
        System.out.println("Adjusted Quantity correct. Submitting order...");               
        
        Ecomm_OutstandingOrdersPage outOrdersPage = orderConf.pressSubmit();
        outOrdersPage.waitForElement();
        
        System.out.println("Order submitted. Navigating to Outstanding Upload Order...");
                
        String orderNo = outOrdersPage.getOrderNumberSUMST(0);
        System.out.println("Order number: "+orderNo);
    }
    
    @Test //Upload Order Page :: SUSST :: Realtime Upload Order (<100 lines)
    (groups = {"eComm","eComm_Orders","Upload_Order"})
    public void RT2() throws Exception {
        //new chrome driver
        WebDriver driver = getDriver();
        
        //new base test to set up
        Ecomm_Base uortTest1 = new Ecomm_Base(driver);
        //Set up returns an eComm page
        Ecomm_MainPage eCommPage = uortTest1.setUp("UPLOAD ORDER SUSST TEST: File of <100 lines, realtime upload", "G_OOC_UORT_SUSST",DataItems.requesterUsername,DataItems.requesterPassword);
        
        System.out.println("Navigating to Upload Order...");
        
        //new upload order page
        Ecomm_UploadOrderPage uploadPage = eCommPage.clickUploadOrder();
        uploadPage.waitForElement();
        
        System.out.println("Upload Order page loaded.");
        
        //make assertions for base page elements and upload page elements
        uploadPage.assertBaseElements();
        System.out.println("Asserting other elements...");
        //Wait for page to load before asserting the other elements
        WebElement waitForLoad = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.visibilityOf(uploadPage.getUploadButton()));
        AssertJUnit.assertTrue("Upload Order page: File name field not displayed",uploadPage.getFileNameOutputField().isDisplayed());
        AssertJUnit.assertTrue("Upload Order page: Realtime upload radio button not displayed",uploadPage.getRealtimeRadio().isDisplayed());
        AssertJUnit.assertTrue("Upload Order page: Backend upload radio button not displayed",uploadPage.getBackendRadio().isDisplayed());
        AssertJUnit.assertTrue("Upload Order page: Upload button not displayed",uploadPage.getUploadButton().isDisplayed());    
        
        System.out.println("Assertions successful. Sending file path...");
        
        //Send file path to field
        uploadPage.setFilePath(FileFactory.createFile("SUSST", 2, "Basic", "YMN", true));
        //Select realtime upload
        uploadPage.pressRealtime();
        
        //Press upload
        Ecomm_MappingAlert alert = uploadPage.pressUpload();
        
        System.out.println("Upload pressed. Choosing new mapping...");
        
        //Press "yes" to alert, continuing to mapping page
        Ecomm_MappingPage mapPage = alert.pressYes();
        
        System.out.println("Mapping page loaded. Setting mapping...");
        
        //Mapping details
        //Element 0 of each array holds the field name. Element 1 of each array holds the corresponding header used in the file.
        //If there is no corresponding header in the file, use "N/A" 
        String[][] mapping = {
            {"Article","Article"},
            {"Ticket","Ticket"},
            {"Finish","Finish"},
            {"Shade Code","Shade Code"},
            {"Required Date","Required Date"},
            {"Qty","Qty"},
            {"Style","N/A"},
            {"Style No./Production No.","N/A"},
            {"Contract PO No.","N/A"},
            {"Customer Price","N/A"},
            {"Sub Account","N/A"},
            {"Ship to Party Name","Ship to Party Name"},
            {"Your Material No.","Your Material Number"},
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
        
        Ecomm_MappingPage mappedPage = mapPage.setMappingNew(mapping,false,false,false);
        
        System.out.println("Mapping set. Confirming map..."); 
        
        Ecomm_OrderConfirmationPage orderConf = mappedPage.pressConfirm();
        orderConf.waitForElement();
        
        System.out.println("Map confirmed. Checking details (quantity) are input as expected...");

        int qty = orderConf.getOrderedQty();
        
        AssertJUnit.assertTrue("Order Confirmation Page: Ordered Quantity not maintained from upload file",String.valueOf(qty).equals(FileFactory.susstBasicData[0][11]));
        
        System.out.println("Input correct. Submitting...");
        
        Ecomm_OutstandingOrdersPage outOrdersPage = orderConf.pressSubmit();
        outOrdersPage.waitForElement();
        
        System.out.println("Order submitted. Navigating to Outstanding Upload Order...");
                
        String orderNo = outOrdersPage.getOrderNumberSUMST(0);
        System.out.println("Order number: "+orderNo);
    }
    
    @Test //Upload Order Page :: SUSST :: Backend Upload order (<100 lines)
    (groups = {"eComm","eComm_Orders","Upload_Order"})
    public void BE1() throws Exception {
        //new chrome driver
        WebDriver driver = getDriver();
        
        //new base test to set up
        Ecomm_Base uortTest1 = new Ecomm_Base(driver);
        //Set up returns an eComm page
        Ecomm_MainPage eCommPage = uortTest1.setUp("UPLOAD ORDER SUSST TEST: File of <100 lines, backend upload", "G_OOC_UOBE_SUSST",DataItems.susstUsername,DataItems.susstPassword);
        
        System.out.println("Navigating to Upload Order...");
        
        //new upload order page
        Ecomm_UploadOrderPage uploadPage = eCommPage.clickUploadOrder();
        uploadPage.waitForElement();
        
        System.out.println("Upload Order page loaded.");
        
        //make assertions for base page elements and upload page elements
        uploadPage.assertBaseElements();
        System.out.println("Asserting other elements...");
        //Wait for page to load before asserting the other elements
        WebElement waitForLoad = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.visibilityOf(uploadPage.getUploadButton()));
        AssertJUnit.assertTrue("Upload Order page: File name field not displayed",uploadPage.getFileNameOutputField().isDisplayed());
        AssertJUnit.assertTrue("Upload Order page: Realtime upload radio button not displayed",uploadPage.getRealtimeRadio().isDisplayed());
        AssertJUnit.assertTrue("Upload Order page: Backend upload radio button not displayed",uploadPage.getBackendRadio().isDisplayed());
        AssertJUnit.assertTrue("Upload Order page: Upload button not displayed",uploadPage.getUploadButton().isDisplayed());    
        
        System.out.println("Assertions successful. Sending file path...");
        
        //Send file path to field
        uploadPage.setFilePath(FileFactory.createFile("SUSST",102,"BE","",true));
        //Select backend upload
        uploadPage.pressBackend();
        
        //Press upload
        Ecomm_MappingAlert alert = uploadPage.pressUpload();
        
        System.out.println("Upload pressed. Choosing new mapping...");
        
        //Press "no" to alert, continuing to mapping page
        Ecomm_MappingPage mapPage = alert.pressYes();
        
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
        
        Ecomm_MappingPage mappedPage = mapPage.setMappingNotCustomer(mapping);
        
        System.out.println("Mapping set. Confirming map..."); 
        
        Ecomm_BackendProcessPage backendMessagePage = mappedPage.pressConfirmForBackend();
        backendMessagePage.waitForElement();
        
        System.out.println("Mapping confirmed. Checking message received...");
        
        AssertJUnit.assertTrue("Backend Process Page: Title not displayed correctly or wrong page reached after confirmation",backendMessagePage.getBreadcrumb().getText().equals("Backend Process"));
        AssertJUnit.assertTrue("Backend Process Page: Backend message not displayed as expected",backendMessagePage.getMessage().contains("Your order is submitted"));
        
        System.out.println("Message received correctly. Navigating to Backend In Process Page...");
        
        Ecomm_BackendInProcessPage bipPage = backendMessagePage.clickBackendInProcess();
        bipPage.waitForElement();
        
        System.out.println("Page reached. Checking file is present...");
        
        String[] parts = DataItems.lastUsedFilepath.split("\\\\");
        String filepath = parts[2];
        
        AssertJUnit.assertTrue("Backend In Process Page: Table does not display the file",bipPage.getFileNameCell().getText().equals(filepath));
        
        System.out.println("File found. Checking status...");
        
        AssertJUnit.assertTrue("Backend In Process Page: File not in expected status (in process)",bipPage.getStatusCell().getText().equals("In Process"));
        
        System.out.println("As expected, status: " + bipPage.getStatusCell().getText());
        
    }
    
}
