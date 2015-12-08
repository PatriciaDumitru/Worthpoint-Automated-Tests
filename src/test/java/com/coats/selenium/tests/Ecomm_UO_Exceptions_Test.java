
package com.coats.selenium.tests;

import AutomationFramework.DataItems;
import AutomationFramework.FileFactory;
import PageObjects.Ecomm_BackendProcessPage;
import PageObjects.Ecomm_ErrorPage;
import PageObjects.Ecomm_MainPage;
import PageObjects.Ecomm_MappingAlert;
import PageObjects.Ecomm_MappingPage;
import PageObjects.Ecomm_OrderConfirmationPage;
import PageObjects.Ecomm_OutstandingOrdersPage;
import PageObjects.Ecomm_UploadOrderPage;
import com.coats.selenium.DriverFactory;
import static com.coats.selenium.DriverFactory.getDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

public class Ecomm_UO_Exceptions_Test extends DriverFactory {
    
    @Test //Upload Order Realtime :: SUMST :: Incomplete material in spreadsheet exception
    (groups = {"eComm","eComm_Orders","Upload_Order"})
    public void UORTex1() throws Exception{
        //new chrome driver
        WebDriver driver = getDriver();
        
        //new base test to set up
        Ecomm_Base uortTest1 = new Ecomm_Base(driver);
        //Set up returns an eComm page
        Ecomm_MainPage eCommPage = uortTest1.setUp("UPLOAD ORDER Exceptions UORTex1: Invalid material number in spreadsheet", "GE_O_OC_URT_Ex1");
        
        System.out.println("Navigating to Upload Order...");
        
        //new upload order page
        Ecomm_UploadOrderPage uploadPage = eCommPage.clickUploadOrder();
        uploadPage.waitForElement();
        
        System.out.println("Upload Order page loaded. Setting filepath...");
        
        //Create a file with 2 rows of invalid values to test the Your Material Number format
        uploadPage.setFilePath(FileFactory.createFile("SUMST", 2, "Basic", "YMN", false));
        
        System.out.println("File path set. Uploading...");
        
        //Select existing mapping
        Ecomm_MappingAlert mapAlert = uploadPage.pressUpload();
        Ecomm_MappingPage mapPage = mapAlert.pressYes();
        mapPage.waitForElement();
        
        System.out.println("Mapping page reached. Setting sales org and customer name...");
        
        //Set sales organisation and customer name. This is only required because a SUMST account is used
        mapPage.setSalesOrg("ID51");
        mapPage.setCustomerName(DataItems.custDetails[0]);
        
        System.out.println("Details set. Setting mapping...");
        
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
        
        mapPage.setMappingNew(mapping,false,false,true);
        
        System.out.println("Mapping set. Confirming...");
        
        Ecomm_OrderConfirmationPage orderConf = mapPage.pressConfirm();
        orderConf.waitForElement();
        
        System.out.println("Order confirmation reached. Asserting errors appear...");
        
        AssertJUnit.assertTrue("Order Confirmation Page: No errors found despite invalid material in spreadsheet",orderConf.viewErrorsNew());
        
        System.out.println("Errors found.");
    }
    
    @Test //Upload Order Realtime :: SUMST :: Invalid brand in spreadsheet exception
    (groups = {"eComm","eComm_Orders","Upload_Order"})
    public void UORTex2() throws Exception {
        //new chrome driver
        WebDriver driver = getDriver();
        
        //new base test to set up
        Ecomm_Base uortTest1 = new Ecomm_Base(driver);
        //Set up returns an eComm page
        Ecomm_MainPage eCommPage = uortTest1.setUp("UPLOAD ORDER Exceptions UORTex2: Invalid brand in spreadsheet", "GE_O_OC_URT_Ex3");
        
        System.out.println("Navigating to Upload Order...");
        
        //new upload order page
        Ecomm_UploadOrderPage uploadPage = eCommPage.clickUploadOrder();
        uploadPage.waitForElement();
        
        System.out.println("Upload Order page loaded. Setting filepath...");
        
        uploadPage.setFilePath(FileFactory.createFile("SUMST", 2, "Basic", "Brand", false));
        
        System.out.println("File path set. Uploading...");
        
        Ecomm_MappingAlert mapAlert = uploadPage.pressUpload();
        Ecomm_MappingPage mapPage = mapAlert.pressYes();
        mapPage.waitForElement();
        
        System.out.println("Mapping page reached. Setting sales org and customer name...");
        
        mapPage.setSalesOrg("ID51");
        mapPage.setCustomerName(DataItems.custDetails[0]);
        
        System.out.println("Details set. Setting mapping...");
        
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
        
        mapPage.setMappingNew(mapping,false,false,true);
        
        System.out.println("Mapping set. Confirming expecting error...");
        
        Ecomm_ErrorPage errorPage = mapPage.pressConfirmExpectingError();
        errorPage.waitForError();
        
        AssertJUnit.assertTrue("Mapping page: Error page not displayed/unexpected error after mapping confirmed with erroneous file. Error: " + errorPage.getError(),
                errorPage.ensureErrorPage("cannot be processed"));
        
        System.out.println("Error Page reached. Error displayed: " + errorPage.getError());

    }
    
    @Test //Upload Order Realtime :: SUMST :: Invalid article in spreadsheet exception
    (groups = {"eComm","eComm_Orders","Upload_Order"})
    public void UORTex3() throws Exception {
        //new chrome driver
        WebDriver driver = getDriver();
        
        //new base test to set up
        Ecomm_Base uortTest1 = new Ecomm_Base(driver);
        //Set up returns an eComm page
        Ecomm_MainPage eCommPage = uortTest1.setUp("UPLOAD ORDER Exceptions UORTex3: Invalid article in spreadsheet", "GE_O_OC_URT_Ex2");
        
        System.out.println("Navigating to Upload Order...");
        
        //new upload order page
        Ecomm_UploadOrderPage uploadPage = eCommPage.clickUploadOrder();
        uploadPage.waitForElement();
        
        System.out.println("Upload Order page loaded. Setting filepath...");
        
        uploadPage.setFilePath(FileFactory.createFile("SUMST", 2, "Basic", "Article", false));
        
        System.out.println("File path set. Uploading...");
        
        Ecomm_MappingAlert mapAlert = uploadPage.pressUpload();
        Ecomm_MappingPage mapPage = mapAlert.pressYes();
        mapPage.waitForElement();
        
        System.out.println("Mapping page reached. Setting sales org and customer name...");
        
        mapPage.setSalesOrg("ID51");
        mapPage.setCustomerName(DataItems.custDetails[0]);
        
        System.out.println("Details set. Setting mapping...");
        
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
        
        mapPage.setMappingNew(mapping,false,false,true);
        
        System.out.println("Mapping set. Confirming expecting error...");
        
        Ecomm_ErrorPage errorPage = mapPage.pressConfirmExpectingError();
        errorPage.waitForError();
        
        AssertJUnit.assertTrue("Mapping page: Error page not displayed/unexpected error after mapping confirmed with erroneous file. Error: " + errorPage.getError(),
                errorPage.ensureErrorPage("incomplete material"));
        
        System.out.println("Error Page reached. Error displayed: " + errorPage.getError());

    }
    
    @Test //Upload Order Realtime :: SUMST :: Invalid ticket in spreadsheet exception
    (groups = {"eComm","eComm_Orders","Upload_Order"})
    public void UORTex4() throws Exception {
        //new chrome driver
        WebDriver driver = getDriver();
        
        //new base test to set up
        Ecomm_Base uortTest1 = new Ecomm_Base(driver);
        //Set up returns an eComm page
        Ecomm_MainPage eCommPage = uortTest1.setUp("UPLOAD ORDER Exceptions UORTex4: Invalid ticket in spreadsheet", "GE_O_OC_URT_Ex4");
        
        System.out.println("Navigating to Upload Order...");
        
        //new upload order page
        Ecomm_UploadOrderPage uploadPage = eCommPage.clickUploadOrder();
        uploadPage.waitForElement();
        
        System.out.println("Upload Order page loaded. Setting filepath...");
        
        uploadPage.setFilePath(FileFactory.createFile("SUMST", 2, "Basic", "Ticket", false));
        
        System.out.println("File path set. Uploading...");
        
        Ecomm_MappingAlert mapAlert = uploadPage.pressUpload();
        Ecomm_MappingPage mapPage = mapAlert.pressYes();
        mapPage.waitForElement();
        
        System.out.println("Mapping page reached. Setting sales org and customer name...");
        
        mapPage.setSalesOrg("ID51");
        mapPage.setCustomerName(DataItems.custDetails[0]);
        
        System.out.println("Details set. Setting mapping...");
        
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
        
        mapPage.setMappingNew(mapping,false,false,true);
        
        System.out.println("Mapping set. Confirming expecting error...");
        
        Ecomm_ErrorPage errorPage = mapPage.pressConfirmExpectingError();
        errorPage.waitForError();
        
        AssertJUnit.assertTrue("Mapping page: Error page not displayed/unexpected error after mapping confirmed with erroneous file. Error: " + errorPage.getError(),
                errorPage.ensureErrorPage("cannot be processed"));
        
        System.out.println("Error Page reached. Error displayed: " + errorPage.getError());

    }
    
    @Test //Upload Order Realtime :: SUMST :: Invalid length in spreadsheet exception
    (groups = {"eComm","eComm_Orders","Upload_Order"})
    public void UORTex5() throws Exception {
        //new chrome driver
        WebDriver driver = getDriver();
        
        //new base test to set up
        Ecomm_Base uortTest1 = new Ecomm_Base(driver);
        //Set up returns an eComm page
        Ecomm_MainPage eCommPage = uortTest1.setUp("UPLOAD ORDER Exceptions UORTex5: Invalid length in spreadsheet", "GE_O_OC_URT_Ex5");
        
        System.out.println("Navigating to Upload Order...");
        
        //new upload order page
        Ecomm_UploadOrderPage uploadPage = eCommPage.clickUploadOrder();
        uploadPage.waitForElement();
        
        System.out.println("Upload Order page loaded. Setting filepath...");
        
        uploadPage.setFilePath(FileFactory.createFile("SUMST", 2, "Basic", "Length", false));
        
        System.out.println("File path set. Uploading...");
        
        Ecomm_MappingAlert mapAlert = uploadPage.pressUpload();
        Ecomm_MappingPage mapPage = mapAlert.pressYes();
        mapPage.waitForElement();
        
        System.out.println("Mapping page reached. Setting sales org and customer name...");
        
        mapPage.setSalesOrg("ID51");
        mapPage.setCustomerName(DataItems.custDetails[0]);
        
        System.out.println("Details set. Setting mapping...");
        
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
        
        mapPage.setMappingNew(mapping,false,false,true);
        
        System.out.println("Mapping set. Confirming expecting error...");
        
        Ecomm_ErrorPage errorPage = mapPage.pressConfirmExpectingError();
        errorPage.waitForError();
        
        AssertJUnit.assertTrue("Mapping page: Error page not displayed/unexpected error after mapping confirmed with erroneous file. Error: " + errorPage.getError(),
                errorPage.ensureErrorPage("cannot be processed"));
        
        System.out.println("Error Page reached. Error displayed: " + errorPage.getError());

    }
    
    @Test //Upload Order Realtime :: SUMST :: Invalid finish in spreadsheet exception. Disabled as logic is incorrect. 
            //Invalid Finish will trigger the default "STANDARD" to be used, and so no errors will appear. This method needs to be updated/removed
    (groups = {"eComm","eComm_Orders","Upload_Order"},enabled=false) 
    public void UORTex6() throws Exception {
        //new chrome driver
        WebDriver driver = getDriver();
        
        //new base test to set up
        Ecomm_Base uortTest1 = new Ecomm_Base(driver);
        //Set up returns an eComm page
        Ecomm_MainPage eCommPage = uortTest1.setUp("UPLOAD ORDER Exceptions UORTex6: Invalid finish in spreadsheet", "GE_O_OC_URT_Ex6");
        
        System.out.println("Navigating to Upload Order...");
        
        //new upload order page
        Ecomm_UploadOrderPage uploadPage = eCommPage.clickUploadOrder();
        uploadPage.waitForElement();
        
        System.out.println("Upload Order page loaded. Setting filepath...");
        
        uploadPage.setFilePath(FileFactory.createFile("SUMST", 2, "Basic", "Finish", false));
        
        System.out.println("File path set. Uploading...");
        
        Ecomm_MappingAlert mapAlert = uploadPage.pressUpload();
        Ecomm_MappingPage mapPage = mapAlert.pressYes();
        mapPage.waitForElement();
        
        System.out.println("Mapping page reached. Setting sales org and customer name...");
        
        mapPage.setSalesOrg("ID51");
        mapPage.setCustomerName(DataItems.custDetails[0]);
        
        System.out.println("Details set. Setting mapping...");
        
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
            {"Customer PO No","Customer PO Number"},
            {"Requestor Name","Requestor Name"},
            {"Warehouse Instruction","N/A"},
            {"Buyer Sales Order Number","N/A"},
            {"Other Information","N/A"},
            {"Customer Price","N/A"}
        };
        
        mapPage.setMappingNew(mapping,false,false,true);
        
        System.out.println("Mapping set. Confirming expecting error...");
        
        Ecomm_OrderConfirmationPage orderConf = mapPage.pressConfirm();
        orderConf.waitForError();
        
        System.out.println("Confirmation Page reached. Asserting errors appear...");
        
        AssertJUnit.assertTrue("Order Confirmation Page: No errors found despite invalid material in spreadsheet",orderConf.viewErrors());
        
        System.out.println("Errors found.");

    }
    
    @Test //Upload Order Realtime :: SUMST :: Invalid shade in spreadsheet exception
    (groups = {"eComm","eComm_Orders","Upload_Order"})
    public void UORTex7() throws Exception {
        //new chrome driver
        WebDriver driver = getDriver();
        
        //new base test to set up
        Ecomm_Base uortTest1 = new Ecomm_Base(driver);
        //Set up returns an eComm page
        Ecomm_MainPage eCommPage = uortTest1.setUp("UPLOAD ORDER Exceptions UORTex7: Invalid Shade code in spreadsheet", "GE_O_OC_URT_Ex7");
        
        System.out.println("Navigating to Upload Order...");
        
        //new upload order page
        Ecomm_UploadOrderPage uploadPage = eCommPage.clickUploadOrder();
        uploadPage.waitForElement();
        
        System.out.println("Upload Order page loaded. Setting filepath...");
        
        uploadPage.setFilePath(FileFactory.createFile("SUMST", 2, "Basic", "Shade", false));
        
        System.out.println("File path set. Uploading...");
        
        Ecomm_MappingAlert mapAlert = uploadPage.pressUpload();
        Ecomm_MappingPage mapPage = mapAlert.pressYes();
        mapPage.waitForElement();
        
        System.out.println("Mapping page reached. Setting sales org and customer name...");
        
        mapPage.setSalesOrg("ID51");
        mapPage.setCustomerName(DataItems.custDetails[0]);
        
        System.out.println("Details set. Setting mapping...");
        
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
        
        mapPage.setMappingNew(mapping,false,false,true);
        
        System.out.println("Mapping set. Confirming expecting error...");
        
        Ecomm_ErrorPage errorPage = mapPage.pressConfirmExpectingError();
        errorPage.waitForError();
        
        AssertJUnit.assertTrue("Mapping page: Error page not displayed/unexpected error after mapping confirmed with erroneous file. Error: " + errorPage.getError(),
                errorPage.ensureOrderSplit());
        
        System.out.println("Error Page reached. Error displayed: " + errorPage.getError());
    }
    
    @Test //Upload Order Realtime :: SUMST :: Invalid ship to party in spreadsheet exception
    (groups = {"eComm","eComm_Orders","Upload_Order"})
    public void UORTex8() throws Exception {
        //new chrome driver
        WebDriver driver = getDriver();
        
        //new base test to set up
        Ecomm_Base uortTest1 = new Ecomm_Base(driver);
        //Set up returns an eComm page
        Ecomm_MainPage eCommPage = uortTest1.setUp("UPLOAD ORDER Exceptions UORTex8: Invalid Ship to party in spreadsheet", "GE_O_OC_URT_Ex8");
        
        System.out.println("Navigating to Upload Order...");
        
        //new upload order page
        Ecomm_UploadOrderPage uploadPage = eCommPage.clickUploadOrder();
        uploadPage.waitForElement();
        
        System.out.println("Upload Order page loaded. Setting filepath...");
        
        uploadPage.setFilePath(FileFactory.createFile("SUMST", 2, "Basic", "Ship", false));
        
        System.out.println("File path set. Uploading...");
        
        Ecomm_MappingAlert mapAlert = uploadPage.pressUpload();
        Ecomm_MappingPage mapPage = mapAlert.pressYes();
        mapPage.waitForElement();
        
        System.out.println("Mapping page reached. Setting sales org and customer name...");
        
        mapPage.setSalesOrg("ID51");
        mapPage.setCustomerName(DataItems.custDetails[0]);
        
        System.out.println("Details set. Setting mapping...");
        
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
        
        mapPage.setMappingNew(mapping,false,false,true);
        
        System.out.println("Mapping set. Confirming mapping...");
        
        Ecomm_OrderConfirmationPage orderConf = mapPage.pressConfirm();
        orderConf.waitForElement();
        
        System.out.println("Order confirmation page reached. Checking Ship-to party is blank...");
        
        AssertJUnit.assertTrue("Order Confirmation Page: Ship-to party set despite error in spreadsheet",orderConf.getShipToParty().equals("Select"));
        
        System.out.println("Ship-to party blank, as expected. Attempting confirm without ship-to...");
        
        Ecomm_OrderConfirmationPage orderConf2 = orderConf.pressSubmitExpectingReturn();
        orderConf2.waitForElement();
        
        AssertJUnit.assertTrue("Order Confirmation Page: Error not displayed after missing mandatory fields used to submit",orderConf2.errorAppears());
        
        System.out.println("Order not submitted, as expected. Entering details...");
        
        orderConf.setRequestor(DataItems.custDetails[2]);
        orderConf.setShipToParty(DataItems.custDetails[1]);
        
        System.out.println("Details entered. Retrieving PO Number...");
        
        String poNumber = orderConf.getUploadPONumber();

        Ecomm_OutstandingOrdersPage ordersPage = orderConf.pressSubmit();
        ordersPage.waitForElement();
        
        System.out.println("Outstanding orders page reached. Checking order appears in table...");
        
        int row = ordersPage.getRowAlt(poNumber);
        
        AssertJUnit.assertTrue("Outstanding Orders Page: Order not found despite confirmation",row!=-1);
        
        String orderNo = ordersPage.getOrderNumberSUMST(row);
        
        System.out.println("Order found.");
        System.out.println("Order No.: " + orderNo);
        System.out.println("Customer PO: " + poNumber);
        System.out.println("Table row: " + row);
        
    }
    
    @Test //Upload Order Backend :: SUMST :: Invalid YMN in spreadsheet exception
    (groups = {"eComm","eComm_Orders","Upload_Order"})
    public void UOBEex1() throws Exception {
        //new chrome driver
        WebDriver driver = getDriver();
        
        //new base test to set up
        Ecomm_Base uortTest1 = new Ecomm_Base(driver);
        //Set up returns an eComm page
        Ecomm_MainPage eCommPage = uortTest1.setUp("UPLOAD ORDER Exceptions UOBEex1: Invalid YMN in spreadsheet", "GE_O_OC_UBE_Ex1");
        
        System.out.println("Navigating to Upload Order...");
        
        //new upload order page
        Ecomm_UploadOrderPage uploadPage = eCommPage.clickUploadOrder();
        uploadPage.waitForElement();
        
        System.out.println("Upload Order page loaded. Setting filepath...");
        
        uploadPage.setFilePath(FileFactory.createFile("SUMST", 2, "Basic", "YMN", false));
        
        System.out.println("Filepath set. Selecting backend upload...");
        
        uploadPage.pressBackend();
        
        System.out.println("Backend selected. Uploading...");
        
        Ecomm_MappingAlert mapAlert = uploadPage.pressUpload();
        
        System.out.println("Uploaded. Selecting current mapping...");
        
        Ecomm_MappingPage mapPage = mapAlert.pressYes();
        mapPage.waitForElement();
        
        System.out.println("Mapping page reached. Entering details...");
        
        mapPage.setSalesOrg("ID51");
        mapPage.setCustomerName(DataItems.custDetails[0]);
        
        System.out.println("Details set. Setting mapping...");
        
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
        
        mapPage.setMappingNew(mapping,false,false,true);
        
        System.out.println("Mapping set. Confirming mapping...");
        
        Ecomm_BackendProcessPage backendAlertPage = mapPage.pressConfirmForBackend();
        
        System.out.println("Mapping confirmed. Checking alert page appears...");
        
        AssertJUnit.assertTrue("Mapping Page: For Backend upload, after map is confirmed Backend Process' page is not displayed",backendAlertPage.getBreadcrumb().getText().equals("Backend Process"));
        
        System.out.println("");
        
    }
    
}
