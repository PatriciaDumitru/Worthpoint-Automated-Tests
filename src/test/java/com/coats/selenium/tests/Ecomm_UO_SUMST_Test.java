
package com.coats.selenium.tests;

import AutomationFramework.DataItems;
import AutomationFramework.FileFactory;
import AutomationFramework.PreFlows;
import AutomationFramework.Wait;
import PageObjects.*;
import com.coats.selenium.DriverFactory;
import static com.coats.selenium.DriverFactory.getDriver;
import com.google.common.base.Verify;
import java.awt.AWTException;
import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.testng.AssertJUnit;
import org.openqa.selenium.Alert;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class Ecomm_UO_SUMST_Test extends DriverFactory {
    
    @Test //Upload Order Page :: SUMST :: Page checks and realtime upload order of <100 lines
    (groups = {"eComm","eComm_Orders","Upload_Order"})
    public void UORT1() throws AWTException, IOException, Exception {
        //new chrome driver
        WebDriver driver = getDriver();
        
        //new base test to set up
        Ecomm_Base uortTest1 = new Ecomm_Base(driver);
        //Set up returns an eComm page
        Ecomm_MainPage eCommPage = uortTest1.setUp("UPLOAD ORDER TEST 1: File of <100 lines, realtime upload", "G_OOC_UORT_SUMST");

        driver.get(DataItems.cceURL);
        Mst_CustomersPage custPage = eCommPage.selectCustomers();
        custPage.waitForElement();

        custPage.setCustomerName("Life Easy Customer");
        custPage.pressSearch();
        custPage.waitForElement();

        int row2 = custPage.getRow("Life Easy Customer");
        System.out.println("Record found. Editing record...");

        Mst_EditCustomerPage editPage2 = custPage.pressEdit(row2);
        editPage2.waitForElement();

        System.out.println("Edit page reached.");

        editPage2.disableApprovalCheckBoxForCust();

        System.out.println("'Disable CCE order upload' flag checked. Saving...");
        editPage2.pressSave();
        //editPage2.waitForElement();

        PreFlows pf = new PreFlows();
        pf.chooseTheOtherProfile(driver);

        driver.get(DataItems.manualEntryEcommURL);
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
        
        //Take a screenshot
        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile,new File(DataItems.screenshotsFilepath+"\\EComm\\Orders\\Upload Order\\1Upload Order page.png"));
        
        System.out.println("Assertions successful. Sending file path...");
        
        //Send file path to field
        uploadPage.setFilePath(FileFactory.createFile("SUMST", 2, "Basic", "", true));
        //Select realtime upload
        uploadPage.pressRealtime();
        
        //Take a screenshot
        File scrFile2 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile2,new File(DataItems.screenshotsFilepath+"\\EComm\\Orders\\Upload Order\\2Filepath set.png"));
        
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
        
        Ecomm_MappingPage mappedPage = mapPage.setMappingWithoutLineRef(mapping);
        
        System.out.println("Mapping set. Confirming map...");
        
        //Take a screenshot
        File scrFile3 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile3,new File(DataItems.screenshotsFilepath+"\\EComm\\Orders\\Upload Order\\2Mapping set.png")); 
        
        Ecomm_OrderConfirmationPage orderConf = mappedPage.pressConfirm();
        Alert alert2 = Wait.alert(driver);
        alert2.accept();
        orderConf.waitForElement();
        
        orderConf.setRequestor(DataItems.custDetails[2]);
        
        System.out.println("Map confirmed. Submitting order...");
             
        //Take a screenshot
        File scrFile4 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile4,new File(DataItems.screenshotsFilepath+"\\EComm\\Orders\\Upload Order\\3Upload Confirmation page.png"));                    
        
        Ecomm_OutstandingOrdersPage outOrdersPage = orderConf.pressSubmit();
        outOrdersPage.waitForElement();
        
        System.out.println("Order submitted. Navigating to Outstanding Upload Order...");
        
        //Take a screenshot
        File scrFile5 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile5,new File(DataItems.screenshotsFilepath+"\\EComm\\Orders\\Upload Order\\4Outstanding Order Page.png"));
                
        String orderNo = outOrdersPage.getOrderNumber(0);
        
        System.out.println("Order number: "+orderNo);

    }    

    @Test //Upload Order Page :: SUMST :: Realtime upload order of <100 lines, removing mandatory fields at before submission
    (groups = {"eComm","eComm_Orders","Upload_Order"})
    public void UORT2() throws AWTException, IOException, InterruptedException, Exception {
        //new chrome driver
        WebDriver driver = getDriver();
        
        //new base test to set up
        Ecomm_Base uortTest1 = new Ecomm_Base(driver);
        //Set up returns an eComm page
        Ecomm_MainPage eCommPage = uortTest1.setUp("UPLOAD ORDER TEST UORT2: File of <100 lines, realtime upload, validation check", "G_OOC_UORT_SUSST");


        driver.get(DataItems.cceURL);
        Mst_CustomersPage custPage = eCommPage.selectCustomers();
        custPage.waitForElement();

        custPage.setCustomerName("Life Easy Customer");
        custPage.pressSearch();
        custPage.waitForElement();

        int row2 = custPage.getRow("Life Easy Customer");
        System.out.println("Record found. Editing record...");

        Mst_EditCustomerPage editPage2 = custPage.pressEdit(row2);
        editPage2.waitForElement();

        System.out.println("Edit page reached.");

        editPage2.disableApprovalCheckBoxForCust();

        System.out.println("'Disable CCE order upload' flag checked. Saving...");
        editPage2.pressSave();
        //editPage2.waitForElement();

        driver.get(DataItems.ecommURL);

        System.out.println("Navigating to Upload Order...");
        
        //new upload order page
        Ecomm_UploadOrderPage uploadPage = eCommPage.clickUploadOrder();
        uploadPage.waitForElement();
        
        System.out.println("Upload Order page loaded. Setting filepath and uploading...");
        
        //Send file path to field
        uploadPage.setFilePath(FileFactory.createFile("SUMST", 2, "Basic", "", true));
        //Select realtime upload
        uploadPage.pressRealtime();
        
        //Press upload
        Ecomm_MappingAlert alert = uploadPage.pressUpload();
        
        System.out.println("Upload pressed. Choosing current mapping...");
        
        //Press "no" to alert, continuing to mapping page
        Ecomm_MappingPage mapPage = alert.pressYes();
        mapPage.waitForElement();
        
        System.out.println("Mapping page reached. Entering Sales Org and Customer Name...");
        
        mapPage.setSalesOrg(DataItems.salesOrganisation);
        mapPage.setCustomerName(DataItems.custDetails[0]);
        
        System.out.println("Details entered. Confirming map...");
        
        Ecomm_OrderConfirmationPage orderConf = mapPage.pressConfirm();
        boolean errorDisplayed;
        try {
            Alert alert2 = Wait.alert(driver);
            alert2.accept();

            System.out.println("Error received: "+alert2.getText());
            errorDisplayed = true;
        } catch (Exception e) {
            System.out.println("No error displayed");
            errorDisplayed = false;
        }
        orderConf.waitForElement();
        orderConf.waitForLoad();
        orderConf.waitForElement();
        
        System.out.println("Map confirmed. Removing requester and Ship To Party...");
        
        orderConf.setShipToParty("Select");
        orderConf.setRequestor("Select");
        
        DataItems.lastUsedPO = orderConf.getCustUploadPOField().getText();

        System.out.println("Requeser removed. Submitting, expecting failure...");
        
        Ecomm_UploadProcessPage errorPage = orderConf.pressSubmitExpectingFailure();
        
        try {
            Alert alert2 = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.alertIsPresent());
            System.out.println("Alert appeared: " + alert2.getText());
            alert2.accept();
        } catch (Exception e) {
            System.out.println("No alert appeared upon submission.");
        }
        
        try {
            
            WebElement error = errorPage.waitForError();
            
            System.out.println("Order confirmation page returned.");
            
            System.out.println("Error received: " + error.getText());
            
        } catch (Exception e) {
            System.out.println("***Order confirmation page not returned***");
            
        }
        
        System.out.println("Checking no order was created...");
        Ecomm_OutstandingOrdersPage outOrders = eCommPage.clickOutstandingOrders();
        outOrders.waitForElement();
        int row = outOrders.getRow(DataItems.lastUsedPO);
        if (row == -1) {
            System.out.println("No order created as expected");
        } else {
            System.out.println("***ERROR: ORDER CREATED WITHOUT REQUESTER/SHIP TO PARTY***");
            System.out.println("Order PO: " + DataItems.lastUsedPO);
            System.out.println("Order No: " + outOrders.getOrderNumber(row));
            System.out.println("Table row: " + row);
        }
    }

    @Test //Upload Order Page :: SUMST :: Upload draft creation and cancellation
    (groups = {"eComm","eComm_Orders","Upload_Order",})
    public void UORT3() throws AWTException, InterruptedException, IOException, Exception {
        //new chrome driver
        WebDriver driver = getDriver();
        
        //new base test to set up
        Ecomm_Base uortTest1 = new Ecomm_Base(driver);
        //Set up returns an eComm page
        Ecomm_MainPage eCommPage = uortTest1.setUp("UPLOAD ORDER TEST UORT3: Draft simulation/draft creation test", "G_OOC_UORT_Unknown");
        
        System.out.println("Navigating to Outstanding Order Upload Page...");
        
        //new upload order page
        Ecomm_UploadOrderPage uploadPage = eCommPage.clickUploadOrder();
        uploadPage.waitForElement();
        
        System.out.println("Upload Order page loaded. Setting filepath and uploading...");
        
        //Send file path to field
        uploadPage.setFilePath(FileFactory.createFile("SUMST", 2, "Basic", "", true));
        //Select realtime upload
        uploadPage.pressRealtime();
        
        //Take a screenshot
        File scrFile5 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile5,new File(DataItems.screenshotsFilepath+"\\EComm\\Orders\\Upload Order\\5Filepath set.png"));
        
        //Press upload
        Ecomm_MappingAlert alert = uploadPage.pressUpload();
        
        System.out.println("Upload pressed. Choosing current mapping...");
        
        //Press "no" to alert, continuing to mapping page
        Ecomm_MappingPage mapPage = alert.pressYes();
        mapPage.waitForElement();
        
        System.out.println("Mapping page reached. Entering Sales Org and Customer Name...");
        
        mapPage.setSalesOrg(DataItems.salesOrganisation);
        mapPage.setCustomerName(DataItems.custDetails[0]);
        
        System.out.println("Details entered. Confirming map...");
        
        Ecomm_OrderConfirmationPage orderConf = mapPage.pressConfirm();
        boolean errorDisplayed;
        try {
            Alert alert2 = Wait.alert(driver);
            alert2.accept();

            System.out.println("Error received: "+alert2.getText());
            errorDisplayed = true;
        } catch (Exception e) {
            System.out.println("No error displayed");
            errorDisplayed = false;
        }
        orderConf.waitForElement();
        
        DataItems.lastUsedPO = orderConf.getUploadCustPOField().getText();
        
        //Take a screenshot
        File scrFile6 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile6,new File(DataItems.screenshotsFilepath+"\\EComm\\Orders\\Upload Order\\6Order confirmation page.png"));
        
        Actions scroller = new Actions(driver);
        scroller.moveToElement(orderConf.getCancelButton()).build().perform();
        WebElement waitForScroll = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.visibilityOf(orderConf.getCancelButton()));
        
        //Take a screenshot
        File scrFile7 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile7,new File(DataItems.screenshotsFilepath+"\\EComm\\Orders\\Upload Order\\7Order confirmation page scrolled.png"));
        
        System.out.println("Map confirmed. Cancelling order...");
    
        Ecomm_UploadOrderPage uoPage = orderConf.pressCancelUpload();
        uoPage.waitForElement();
        
        System.out.println("Order cancelled. Checking no draft was created...");
        
        Ecomm_OutstandingUploadDraftPage draftPage = uoPage.clickOutstandingUploadDraft();
        boolean found = draftPage.findDraft(DataItems.lastUsedPO);
        
        AssertJUnit.assertFalse("Outstanding Upload Draft Page: Draft created despite cancellation",found);
        
        System.out.println("No draft created, as expected");
        
    }
    
    @Test //Upload Order Page :: SUMST :: Upload draft continuation and cancellation
    (groups = {"eComm","eComm_Orders","Upload_Order"})
    public void UORT4() throws IOException, InterruptedException, AWTException, Exception {
        //new chrome driver
        WebDriver driver = getDriver();
        
        //new base test to set up
        Ecomm_Base uortTest1 = new Ecomm_Base(driver);
        //Set up returns an eComm page
        Ecomm_MainPage eCommPage = uortTest1.setUp("UPLOAD ORDER TEST UORT4: Upload Draft continuation and cancellation", "G_OOC_UORT_Unknown");
        
        System.out.println("Navigating to upload order page...");
        
        Ecomm_UploadOrderPage uoPage = eCommPage.clickUploadOrder();
        uoPage.waitForElement();
        
        //Send file path to field
        uoPage.setFilePath(FileFactory.createFile("SUMST", 2, "Basic", "", true));
        //Select realtime upload
        uoPage.pressRealtime();
        
        //Press upload
        Ecomm_MappingAlert alert = uoPage.pressUpload();
        
        System.out.println("Upload pressed. Choosing current mapping...");
        
        //Press "no" to alert, continuing to mapping page
        Ecomm_MappingPage mapPage = alert.pressYes();
        mapPage.waitForElement();
        
        System.out.println("Mapping page reached. Entering Sales Org and Customer Name...");
        
        mapPage.setSalesOrg(DataItems.salesOrganisation);
        mapPage.setCustomerName(DataItems.custDetails[0]);
        
        System.out.println("Details entered. Confirming map...");
        
        Ecomm_OrderConfirmationPage orderConf = mapPage.pressConfirm();

        boolean errorDisplayed;
        try {
            Alert alert2 = Wait.alert(driver);
            alert2.accept();

            System.out.println("Error received: "+alert2.getText());
            errorDisplayed = true;
        } catch (Exception e) {
            System.out.println("No error displayed");
            errorDisplayed = false;
        }

        orderConf.waitForElement();
        
        DataItems.lastUsedPO = orderConf.getUploadCustPOField().getText();
        
        System.out.println("Map confirmed. Saving as draft...");
        
        Ecomm_OutstandingUploadDraftPage draftsPage = orderConf.pressSaveUploadDraft();
        draftsPage.waitForElement();
        
        System.out.println("Upload Draft Page reached. Editing top item...");
        
        Ecomm_OrderConfirmationPage orderConf2 = draftsPage.pressEdit();
        orderConf.waitForElement();
        
        //Take a screenshot
        File scrFile9 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile9,new File(DataItems.screenshotsFilepath+"\\EComm\\Orders\\Upload Order\\9Draft open.png"));
        
        System.out.println("Order Confirmation Page reached. Cancelling draft...");
        
        DataItems.lastUsedPO = orderConf2.getUploadCustPOField().getText();
        
        Ecomm_UploadOrderPage uoPage2 = orderConf2.pressCancelUpload();
        uoPage2.waitForElement();
        
        //Take a screenshot
        File scrFile10 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile10,new File(DataItems.screenshotsFilepath+"\\EComm\\Orders\\Upload Order\\10Draft cancelled.png"));
        
        System.out.println("Draft cancelled. Checking draft is deleted...");
        
        Ecomm_OutstandingUploadDraftPage uoDraft2 = uoPage2.clickOutstandingUploadDraft();
        uoDraft2.waitForElement();
        
        //Take a screenshot
        File scrFile11 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile11,new File(DataItems.screenshotsFilepath+"\\EComm\\Orders\\Upload Order\\11Draft removed.png"));
        
        boolean found = uoDraft2.findDraft(DataItems.lastUsedPO);
        
        AssertJUnit.assertFalse("Outstanding Upload Draft Page: Draft not deleted despite cancellation",found);   
        
    }
    
    @Test //Upload Order Page :: SUMST :: Upload draft continuation
    (groups = {"eComm","eComm_Orders","Upload_Order"})
    public void UORT5() throws IOException, AWTException, InterruptedException, Exception {
        //new chrome driver
        WebDriver driver = getDriver();
        
        //new base test to set up
        Ecomm_Base uortTest1 = new Ecomm_Base(driver);
        //Set up returns an eComm page
        Ecomm_MainPage eCommPage = uortTest1.setUp("UPLOAD ORDER TEST UORT5: Upload Draft continuation", "G_OOC_UORT_Unknown");
        
        System.out.println("Navigating to Upload Order Page...");
        
        Ecomm_UploadOrderPage uoPage = eCommPage.clickUploadOrder();
        uoPage.waitForElement();
        
        System.out.println("Upload Order page reached. Creating draft...");
        
        //Send file path to field
        uoPage.setFilePath(FileFactory.createFile("SUMST", 2, "Basic", "", true));
        //Select realtime upload
        uoPage.pressRealtime(); 
        
        //Press upload
        Ecomm_MappingAlert alert = uoPage.pressUpload();
        
        System.out.println("Upload pressed. Choosing current mapping...");
        
        //Press "no" to alert, continuing to mapping page
        Ecomm_MappingPage mapPage = alert.pressYes();
        mapPage.waitForElement();
        
        System.out.println("Mapping page reached. Entering Sales Org and Customer Name...");
        
        mapPage.setSalesOrg(DataItems.salesOrganisation);
        mapPage.setCustomerName(DataItems.custDetails[0]);
        
        System.out.println("Details entered. Confirming map...");
        
        Ecomm_OrderConfirmationPage orderConf = mapPage.pressConfirm();
        boolean errorDisplayed;
        try {
            Alert alert2 = Wait.alert(driver);
            alert2.accept();

            System.out.println("Error received: "+alert2.getText());
            errorDisplayed = true;
        } catch (Exception e) {
            System.out.println("No error displayed");
            errorDisplayed = false;
        }
        orderConf.waitForElement();
        
        orderConf.setRequestor(DataItems.custDetails[2]);
        
        DataItems.lastUsedPO = orderConf.getUploadCustPOField().getText();
        String date = orderConf.getRequiredDate();
        
        Actions scroller = new Actions(driver);
        scroller.moveToElement(orderConf.getCancelButton()).build().perform();
        WebElement waitForScroll = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.visibilityOf(orderConf.getCancelButton()));
        
        //Take a screenshot
        File scrFile13 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile13,new File(DataItems.screenshotsFilepath+"\\EComm\\Orders\\Upload Order\\12Details entered.png"));
        
        System.out.println("Map confirmed. Saving draft...");
        
        orderConf.pressSaveDraft();
        
        Ecomm_OutstandingUploadDraftPage uoDraft = new Ecomm_OutstandingUploadDraftPage(driver);
        uoDraft.waitForElement();
        
        System.out.println("Draft saved. Editing draft...");
        
        Ecomm_OrderConfirmationPage orderConf2 = uoDraft.pressEdit();
        orderConf2.waitForElement();
        
        System.out.println("Order confirmation page reached. Checking details...");
        
        Actions scroller2 = new Actions(driver);
        scroller2.moveToElement(orderConf.getCancelButton()).build().perform();
        WebElement waitForScroll2 = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.visibilityOf(orderConf.getCancelButton()));
        
        //Take a screenshot
        File scrFile15 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile15,new File(DataItems.screenshotsFilepath+"\\EComm\\Orders\\Upload Order\\13Draft reopened.png"));
         
        Verify.verify(orderConf2.getUploadCustPOField().getText().equals(DataItems.lastUsedPO),"Order Confirmation Page: Customer PO not consistent with input");
        Verify.verify(orderConf2.getRequester().equals(DataItems.custDetails[2]),"Order Confirmation Page: Requester not consistent with input");
        Verify.verify(orderConf2.getShipToParty().equals(DataItems.custDetails[1]),"Order Confirmation Page: Ship to Party Name not consistent with input");
        Verify.verify(orderConf2.getBuyers().equals(DataItems.custDetails[3]),"Order Confirmation Page: Buyers not consistent with input");
        Verify.verify(orderConf2.getRequiredDate().equals(date),"Order Confirmation Page: Required Date not consistent with input");
        
        System.out.println("Details checked.");
        
    }
    
    @Test //Upload Order Page :: SUMST :: Sub-account
    (groups = {"eComm","eComm_Orders","Upload_Orders"})
    public void UORT6() throws Exception {
        //new chrome driver
        WebDriver driver = getDriver();
        
        //new base test to set up
        Ecomm_Base uortTest1 = new Ecomm_Base(driver);
        //Set up returns an eComm page
        Ecomm_MainPage eCommPage = uortTest1.setUp("UPLOAD ORDER TEST UORT5: Upload Draft continuation", "G_OOC_UORT_Unknown");
        
        System.out.println("Navigating to Upload Order Page...");
        
        Ecomm_UploadOrderPage uoPage = eCommPage.clickUploadOrder();
        uoPage.waitForElement();
        
        System.out.println("Upload Order page reached. Setting filepath...");
        
        uoPage.setFilePath(FileFactory.createFile("SUMST", 2, "SA", "", true));
        
        System.out.println("Filepath set. Uploading...");
        
        Ecomm_MappingAlert mapAlert = uoPage.pressUpload();
        Ecomm_MappingPage mapPage = mapAlert.pressYes();
        mapPage.waitForElement();
        
        System.out.println("Uploaded. Setting mapping...");
        
        mapPage.setSalesOrg("ID51");
        mapPage.setCustomerName(DataItems.subCustDetails[0]);
        
        String[][] mapping = {  {"Customer Name","Customer Name"},
                                {"Article","N/A"},
                                {"Ticket","Ticket"},
                                {"Finish","Finish"},
                                {"Shade Code","Shade Code"},
                                {"Required Date","Required Date"},
                                {"Qty","Qty"},
                                {"Style","N/A"},
                                {"Style No./Production No.","N/A"},
                                {"Sub Account","Sub Account"},
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
        
        mapPage.setMappingNotCustomer(mapping);
        
        System.out.println("Mapping set. Confirming...");
        
        Ecomm_OrderConfirmationPage orderConf = mapPage.pressConfirm();
        boolean errorDisplayed;
        try {
            Alert alert2 = Wait.alert(driver);
            alert2.accept();

            System.out.println("Error received: "+alert2.getText());
            errorDisplayed = true;
        } catch (Exception e) {
            System.out.println("No error displayed");
            errorDisplayed = false;
        }
        orderConf.waitForElement();
        
        orderConf.setRequestor(DataItems.subCustDetails[2]);
        
        String poNumber = orderConf.getUploadPONumber();
        
        System.out.println("Order Confirmation Page reached. Checking sub-account field appears with correct value...");
        
        AssertJUnit.assertTrue("Order Confirmation Page: Sub-account field does not appear for upload order confirmation",orderConf.getSubAccountField().isDisplayed());
        
        AssertJUnit.assertTrue("Order Confirmation Page: Sub-account field does not hold correct value from spreadsheet",orderConf.getSubAccount().equals(DataItems.subAccount));
    
        System.out.println("Sub-account field present, with correct value. Submitting order...");
        
        Ecomm_PendingApprovalListPage pendPage = orderConf.pressSendForApproval();
        pendPage.waitForLoad();
        
        System.out.println("Order submitted. Checking order is in Pending Approval table...");
        
        int row = pendPage.getRow(poNumber);
        
        AssertJUnit.assertTrue("Pending Approval Page: Upload Order (Customer PO: "+poNumber+") not displayed in Pending Approval after confirmation",row!=-1);
        
        String orderNo = pendPage.getOrderNoSUMST(row);
        
        System.out.println("Order found.");
        System.out.println("Order No.: " + orderNo);
        System.out.println("Customer PO: " + poNumber);
        
        System.out.println("Logging out to approve order...");
        
        WBA_LoginPage liPage = pendPage.pressLogout();
        liPage.waitForElement();
        
        WBA_ContinuePage contPage = liPage.loginAs(DataItems.approverUsername, DataItems.approverPassword);
        WBA_SelectionPage selectPage = contPage.pressContinue();
        Ecomm_MainPage mainPage = selectPage.pressEcomm();
        mainPage.waitForLoad();
        
        System.out.println("Logged in. Navigating to Pending Approval List Page...");

        Ecomm_PendingApprovalListPage pendPage2 = mainPage.clickPendingApprovalListPageApprover();
        pendPage2.waitForElement();
        
        System.out.println("Pending Approval page reached. Finding order...");
        
        AssertJUnit.assertTrue("Pending Approval Page: Order (Order No.: "+orderNo+") not approved.",pendPage2.approveOrder(orderNo));
        
        System.out.println("Order approved. Logging into other account to view SAP Log...");
        
        WBA_LoginPage liPage2 = pendPage2.pressLogout();
        liPage2.waitForElement();
        
        WBA_ContinuePage contPage2 = liPage.loginAs(DataItems.validCoatsUsername, DataItems.validCoatsPassword);
        WBA_SelectionPage selectPage2 = contPage.pressContinue();
        Ecomm_MainPage mainPage2 = selectPage.pressEcomm();
        
        mainPage2.waitForLoad();
        
        System.out.println("Logged in. Navigating to SAP Log Page...");
        
        Ecomm_SAPInterfaceLogPage sapPage = mainPage2.clickSAPInterfaceLog();
        sapPage.waitForElement();
        
        System.out.println("SAP Log Page reached. Finding order and viewing Flat File...");
        
        sapPage.getFlatFile(orderNo);
        
    }
    
    @Test //Upload Order Page :: SUMST :: Realtime upload (MOQ Active)
    (groups = {"eComm","eComm_Orders","Upload_Order"})
    public void UORT7() throws AWTException, IOException, Exception {
        //new chrome driver
        WebDriver driver = getDriver();
        
        //new base test to set up
        Ecomm_Base uortTest1 = new Ecomm_Base(driver);
        //Set up returns an eComm page
        Ecomm_MainPage eCommPage = uortTest1.setUp("UPLOAD ORDER TEST UORT7: File of <100 lines, realtime upload (MOQ Active)", "G_OOC_UORT_SUMST_MOQ");
        
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
        uploadPage.setFilePath(FileFactory.createFile("SUMST",2,"MOQ","",true));
        //Select realtime upload
        uploadPage.pressRealtime();
        
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
        
        Ecomm_MappingPage mappedPage = mapPage.setMappingWithoutLineRef(mapping);
        
        System.out.println("Mapping set. Confirming map...");
        
        Ecomm_OrderConfirmationPage orderConf = mappedPage.pressConfirmMOQ();
        orderConf.waitForElement();
        
        orderConf.setRequestor(DataItems.custDetails[2]);
        
        System.out.println("Map confirmed. Checking adjusted quantity is correct...");

        AssertJUnit.assertTrue("Order Confirmation Page: Adjusted Quantity less than or equal to ordered quantity/not displayed as expected",orderConf.getAdjustedQty()>1);
        
        System.out.println("Adjusted Quantity checked. Submitting order...");                 
        
        Ecomm_OutstandingOrdersPage outOrdersPage = orderConf.pressSubmit();
        outOrdersPage.waitForElement();
        
        System.out.println("Order submitted. Navigating to Outstanding Upload Order...");
                
        String orderNo = outOrdersPage.getOrderNumber(0);
        
        System.out.println("Order number: "+orderNo);
        
    }  
    
    @Test //Upload Order Page :: SUMST :: Backend Upload order (<100 lines)
    (groups = {"eComm","eComm_Orders","Upload_Order"})
    public void BE1() throws Exception {
        //new chrome driver
        WebDriver driver = getDriver();
        
        //new base test to set up
        Ecomm_Base uortTest1 = new Ecomm_Base(driver);
        //Set up returns an eComm page
        Ecomm_MainPage eCommPage = uortTest1.setUp("UPLOAD ORDER SUMST TEST: File of <100 lines, backend upload", "G_OOC_UOBE_SUMST");
        
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
        uploadPage.setFilePath(FileFactory.createFile("SUMST", 2, "Basic", "", true));
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
        
        Ecomm_MappingPage mappedPage = mapPage.setMappingWithoutLineRef(mapping);
        
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
    
    @Test //Upload Order Page :: SUMST :: Backend Upload order (<100 lines) (MOQ Active)
    (groups = {"eComm","eComm_Orders","Upload_Order"})
    public void BE2() throws Exception {
        //new chrome driver
        WebDriver driver = getDriver();
        
        //new base test to set up
        Ecomm_Base uortTest1 = new Ecomm_Base(driver);
        //Set up returns an eComm page
        Ecomm_MainPage eCommPage = uortTest1.setUp("UPLOAD ORDER SUMST TEST: File of <100 lines, backend upload (MOQ Active)", "G_OOC_UOBE_SUMST_MOQ");
        
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
        uploadPage.setFilePath(FileFactory.createFile("SUMST", 2, "Basic", "", true));
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
        
        Ecomm_MappingPage mappedPage = mapPage.setMappingWithoutLineRef(mapping);
        
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

    

