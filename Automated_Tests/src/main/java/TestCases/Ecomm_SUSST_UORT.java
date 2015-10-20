
package TestCases;

import AutomationFramework.Categories;
import AutomationFramework.DataItems;
import PageObjects.Ecomm_MainPage;
import PageObjects.Ecomm_MappingAlert;
import PageObjects.Ecomm_MappingPage;
import PageObjects.Ecomm_OrderConfirmationPage;
import PageObjects.Ecomm_OutstandingOrderDraftPage;
import PageObjects.Ecomm_OutstandingOrdersPage;
import PageObjects.Ecomm_OutstandingUploadDraftPage;
import PageObjects.Ecomm_PendingApprovalListPage;
import PageObjects.Ecomm_UploadConfirmationPage;
import PageObjects.Ecomm_UploadOrderPage;
import com.google.common.base.Verify;
import java.awt.AWTException;
import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

@Category(Categories.eComm.class)
public class Ecomm_SUSST_UORT {
    
    @Category({Categories.eComm_Orders_UploadOrder.class,Categories.QuickSuite.class})
    @Test //Upload Order Page :: Page checks and realtime upload order of <100 lines
    public void UORT1() throws AWTException, IOException {
        //new chrome driver
        WebDriver driver = new ChromeDriver();
        
        //new base test to set up
        Ecomm_SUSST_Base uortTest1 = new Ecomm_SUSST_Base(driver);
        //Set up returns an eComm page
        Ecomm_MainPage eCommPage = uortTest1.SUSST_SetUp("UPLOAD ORDER TEST 1: File of <100 lines, realtime upload", "G_OOC_UORT_SUSST");
        
        System.out.println("Navigating to Upload Order...");
        
        //new upload order page
        Ecomm_UploadOrderPage uploadPage = eCommPage.clickUploadOrder();
        uploadPage.waitForLoad();
        
        System.out.println("Upload Order page loaded.");
        
        //make assertions for base page elements and upload page elements
        uploadPage.assertBaseElements();
        System.out.println("Asserting other elements...");
        //Wait for page to load before asserting the other elements
        WebElement waitForLoad = new WebDriverWait(driver,5).until(ExpectedConditions.visibilityOf(uploadPage.getUploadButton()));
        Assert.assertTrue("Upload Order page: File name field not displayed",uploadPage.getFileNameOutputField().isDisplayed());
        Assert.assertTrue("Upload Order page: Realtime upload radio button not displayed",uploadPage.getRealtimeRadio().isDisplayed());
        Assert.assertTrue("Upload Order page: Backend upload radio button not displayed",uploadPage.getBackendRadio().isDisplayed());
        Assert.assertTrue("Upload Order page: Upload button not displayed",uploadPage.getUploadButton().isDisplayed());    
        
        //Take a screenshot
        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile,new File(DataItems.screenshotsFilepath+"\\EComm\\Orders\\Upload Order\\1Upload Order page.png"));
        
        System.out.println("Assertions successful. Sending file path...");
        
        //Send file path to field
        uploadPage.setFilePath(DataItems.uploadOrderFilepath);
        //Select realtime upload
        uploadPage.pressRealtime();
        
        //Take a screenshot
        File scrFile2 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile2,new File(DataItems.screenshotsFilepath+"\\EComm\\Orders\\Upload Order\\2Filepath set.png"));
        
        //Press upload
        Ecomm_MappingAlert alert = uploadPage.pressUpload();
        
        System.out.println("Upload pressed. Choosing new mapping...");
        
        //Press "no" to alert, continuing to mapping page
        Ecomm_MappingPage mapPage = alert.pressNo();
        
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
        
        Ecomm_MappingPage mappedPage = mapPage.setMapping(mapping);
        
        System.out.println("Mapping set. Confirming map...");
        
        //Take a screenshot
        File scrFile3 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile3,new File(DataItems.screenshotsFilepath+"\\EComm\\Orders\\Upload Order\\2Mapping set.png")); 
        
        Ecomm_OrderConfirmationPage orderConf = mappedPage.pressConfirm();
        orderConf.waitForLoad();
        
        orderConf.setRequestor(DataItems.custDetails[2]);
        
        System.out.println("Map confirmed. Submitting order...");
             
        //Take a screenshot
        File scrFile4 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile4,new File(DataItems.screenshotsFilepath+"\\EComm\\Orders\\Upload Order\\3Upload Confirmation page.png"));                    
        
        Ecomm_OutstandingOrdersPage outOrdersPage = orderConf.pressSubmit();
        outOrdersPage.waitForLoad();
        
        System.out.println("Order submitted. Navigating to Outstanding Upload Order...");
        
        //Take a screenshot
        File scrFile5 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile5,new File(DataItems.screenshotsFilepath+"\\EComm\\Orders\\Upload Order\\4Outstanding Order Page.png"));
                
        String orderNo = outOrdersPage.getOrderNumber(0);
        
        System.out.println("Order number: "+orderNo);
        
        
        
        driver.close();
        driver.quit();
        
        System.out.println("----------------------------------------------------");
        
    }    
    
    @Category({Categories.eComm_Orders_UploadOrder_Validation.class,Categories.QuickSuite.class})
    @Test //Upload Order Page :: Realtime upload order of <100 lines, removing mandatory fields at before submission
    public void UORT2() throws AWTException, IOException, InterruptedException {
        //new chrome driver
        WebDriver driver = new ChromeDriver();
        
        //new base test to set up
        Ecomm_SUSST_Base uortTest1 = new Ecomm_SUSST_Base(driver);
        //Set up returns an eComm page
        Ecomm_MainPage eCommPage = uortTest1.SUSST_SetUp("UPLOAD ORDER TEST UORT2: File of <100 lines, realtime upload, validation check", "G_OOC_UORT_SUSST");
        
        System.out.println("Navigating to Upload Order...");
        
        //new upload order page
        Ecomm_UploadOrderPage uploadPage = eCommPage.clickUploadOrder();
        uploadPage.waitForLoad();
        
        System.out.println("Upload Order page loaded. Setting filepath and uploading...");
        
        //Send file path to field
        uploadPage.setFilePath(DataItems.uploadOrderFilepath);
        //Select realtime upload
        uploadPage.pressRealtime();
        
        //Press upload
        Ecomm_MappingAlert alert = uploadPage.pressUpload();
        
        System.out.println("Upload pressed. Choosing current mapping...");
        
        //Press "no" to alert, continuing to mapping page
        Ecomm_MappingPage mapPage = alert.pressYes();
        mapPage.waitForLoad();
        
        System.out.println("Mapping page reached. Entering Sales Org and Customer Name...");
        
        mapPage.setSalesOrg(DataItems.salesOrganisation);
        mapPage.setCustomerName(DataItems.custDetails[0]);
        
        System.out.println("Details entered. Confirming map...");
        
        Ecomm_OrderConfirmationPage orderConf = mapPage.pressConfirm();
        
        System.out.println("Map confirmed. Removing requester and Ship To Party...");
        
        orderConf.setShipToParty("Select");
        orderConf.setRequestor("Select");
        
        DataItems.lastUsedPO = orderConf.getCustPoField().getText();

        System.out.println("Requeser removed. Submitting, expecting failure...");
        
        Ecomm_OrderConfirmationPage orderConf2 = orderConf.pressSubmitExpectingFailure();
        
        try {
            Alert alert2 = new WebDriverWait(driver,5).until(ExpectedConditions.alertIsPresent());
            System.out.println("Alert appeared: " + alert2.getText());
            alert2.accept();
        } catch (Exception e) {
            System.out.println("No alert appeared upon submission.");
        }
        
        try {
            
            orderConf2.waitForLoad();
            WebElement error = orderConf2.waitForError();
            
            System.out.println("Order confirmation page returned.");
            
            System.out.println("Error received: " + error.getText());
            
        } catch (Exception e) {
            System.out.println("***Order confirmation page not returned***");
            
        }
        
        System.out.println("Checking no order was created...");
        Ecomm_OutstandingOrdersPage outOrders = eCommPage.clickOutstandingOrders();
        outOrders.waitForLoad();
        int row = outOrders.getRow(DataItems.lastUsedPO);
        if (row == -1) {
            System.out.println("No order created as expected");
        } else {
            System.out.println("***ERROR: ORDER CREATED WITHOUT REQUESTER/SHIP TO PARTY***");
            System.out.println("Order PO: " + DataItems.lastUsedPO);
            System.out.println("Order No: " + outOrders.getOrderNumber(row));
            System.out.println("Table row: " + row);
        }
        
        driver.close();
        driver.quit();
        
        System.out.println("----------------------------------------------------");
    }
    
    @Category({Categories.eComm_Orders_UploadOrder.class,Categories.DraftCreation.class}) 
    @Test //Upload Order Page :: Upload draft creation and cancellation
    public void UORT3() throws AWTException, InterruptedException, IOException {
        //new chrome driver
        WebDriver driver = new ChromeDriver();
        
        //new base test to set up
        Ecomm_SUSST_Base uortTest1 = new Ecomm_SUSST_Base(driver);
        //Set up returns an eComm page
        Ecomm_MainPage eCommPage = uortTest1.SUSST_SetUp("UPLOAD ORDER TEST UORT3: Draft simulation/draft creation test", "G_OOC_UORT_Unknown");
        
        System.out.println("Navigating to Outstanding Order Upload Page...");
        
        //new upload order page
        Ecomm_UploadOrderPage uploadPage = eCommPage.clickUploadOrder();
        uploadPage.waitForLoad();
        
        System.out.println("Upload Order page loaded. Setting filepath and uploading...");
        
        //Send file path to field
        uploadPage.setFilePath(DataItems.uploadDraftFilepath);
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
        mapPage.waitForLoad();
        
        System.out.println("Mapping page reached. Entering Sales Org and Customer Name...");
        
        mapPage.setSalesOrg(DataItems.salesOrganisation);
        mapPage.setCustomerName(DataItems.custDetails[0]);
        
        System.out.println("Details entered. Confirming map...");
        
        Ecomm_OrderConfirmationPage orderConf = mapPage.pressConfirm();
        orderConf.waitForLoad();
        
        DataItems.lastUsedPO = orderConf.getUploadCustPOField().getText();
        
        //Take a screenshot
        File scrFile6 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile6,new File(DataItems.screenshotsFilepath+"\\EComm\\Orders\\Upload Order\\6Order confirmation page.png"));
        
        Actions scroller = new Actions(driver);
        scroller.moveToElement(orderConf.getCancelButton()).build().perform();
        WebElement waitForScroll = new WebDriverWait(driver,5).until(ExpectedConditions.visibilityOf(orderConf.getCancelButton()));
        
        //Take a screenshot
        File scrFile7 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile7,new File(DataItems.screenshotsFilepath+"\\EComm\\Orders\\Upload Order\\7Order confirmation page scrolled.png"));
        
        System.out.println("Map confirmed. Cancelling order...");
        
        orderConf.pressCancel();
        Ecomm_UploadOrderPage uoPage = new Ecomm_UploadOrderPage(driver);
        uoPage.waitForElements();
        uoPage.waitForLoad();
        
        System.out.println("Order cancelled. Checking no draft was created...");
        
        Ecomm_MainPage eComm = new Ecomm_MainPage(driver);
        Ecomm_OutstandingUploadDraftPage draftPage = eComm.clickOutstandingUploadDraft();
        draftPage.waitForLoad();
        
        //Take a screenshot
        File scrFile8 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile8,new File(DataItems.screenshotsFilepath+"\\EComm\\Orders\\Upload Order\\8Outstanding drafts page (no draft expected).png"));
    
        boolean draftFound = draftPage.findDraft(DataItems.lastUsedPO);
        
        if (!draftFound) {
            System.out.println("Draft not created, as expected");
        }
        
        driver.close();
        driver.quit();
        
        System.out.println("----------------------------------------------------");
        
    }
    
    @Category({Categories.eComm_Orders_UploadOrder.class,Categories.DraftCreation.class})
    @Test //Upload Order Page :: Upload draft continuation and cancellation
    public void UORT4() throws IOException, InterruptedException, AWTException {
        //new chrome driver
        WebDriver driver = new ChromeDriver();
        
        //new base test to set up
        Ecomm_SUSST_Base uortTest1 = new Ecomm_SUSST_Base(driver);
        //Set up returns an eComm page
        Ecomm_MainPage eCommPage = uortTest1.SUSST_SetUp("UPLOAD ORDER TEST UORT4: Upload Draft continuation and cancellation", "G_OOC_UORT_Unknown");
        
        System.out.println("Navigating to upload order page...");
        
        Ecomm_UploadOrderPage uoPage = eCommPage.clickUploadOrder();
        uoPage.waitForLoad();
        
        //Send file path to field
        uoPage.setFilePath(DataItems.uploadDraftFilepath);
        //Select realtime upload
        uoPage.pressRealtime();
        
        //Press upload
        Ecomm_MappingAlert alert = uoPage.pressUpload();
        
        System.out.println("Upload pressed. Choosing current mapping...");
        
        //Press "no" to alert, continuing to mapping page
        Ecomm_MappingPage mapPage = alert.pressYes();
        mapPage.waitForLoad();
        
        System.out.println("Mapping page reached. Entering Sales Org and Customer Name...");
        
        mapPage.setSalesOrg(DataItems.salesOrganisation);
        mapPage.setCustomerName(DataItems.custDetails[0]);
        
        System.out.println("Details entered. Confirming map...");
        
        Ecomm_OrderConfirmationPage orderConf = mapPage.pressConfirm();
        orderConf.waitForLoad();
        
        DataItems.lastUsedPO = orderConf.getUploadCustPOField().getText();
        
        System.out.println("Map confirmed. Saving as draft...");
        
        Ecomm_OutstandingUploadDraftPage draftsPage = orderConf.pressSaveUploadDraft();
        draftsPage.waitForLoad();
        
        System.out.println("Upload Draft Page reached. Editing top item...");
        
        Ecomm_OrderConfirmationPage orderConf2 = draftsPage.pressEdit();
        orderConf.waitForLoad();
        
        //Take a screenshot
        File scrFile9 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile9,new File(DataItems.screenshotsFilepath+"\\EComm\\Orders\\Upload Order\\9Draft open.png"));
        
        System.out.println("Order Confirmation Page reached. Cancelling draft...");
        
        DataItems.lastUsedPO = orderConf2.getUploadCustPOField().getText();
        
        orderConf2.pressCancel();
        Ecomm_UploadOrderPage uoPage2 = new Ecomm_UploadOrderPage(driver);
        uoPage.waitForElements();
        
        //Take a screenshot
        File scrFile10 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile10,new File(DataItems.screenshotsFilepath+"\\EComm\\Orders\\Upload Order\\10Draft cancelled.png"));
        
        System.out.println("Draft cancelled. Checking draft is deleted...");
        
        Ecomm_MainPage eComm = new Ecomm_MainPage(driver);
        Ecomm_OutstandingUploadDraftPage uoDraft2 = eComm.clickOutstandingUploadDraft();
        uoDraft2.waitForLoad();
        
        //Take a screenshot
        File scrFile11 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile11,new File(DataItems.screenshotsFilepath+"\\EComm\\Orders\\Upload Order\\11Draft removed.png"));
        
        boolean found = uoDraft2.findDraft(DataItems.lastUsedPO);
        
        if (!found) {
            System.out.println("No draft found, as expected");
        }
        
        driver.close();
        driver.quit();
        
        System.out.println("----------------------------------------------------");        
        
    }
    
    @Category({Categories.eComm_Orders_UploadOrder.class,Categories.DraftCreation.class})
    @Test //Upload Order Page :: Upload draft continuation
    public void UORT5() throws IOException, AWTException, InterruptedException {
        //new chrome driver
        WebDriver driver = new ChromeDriver();
        
        //new base test to set up
        Ecomm_SUSST_Base uortTest1 = new Ecomm_SUSST_Base(driver);
        //Set up returns an eComm page
        Ecomm_MainPage eCommPage = uortTest1.SUSST_SetUp("UPLOAD ORDER TEST UORT5: Upload Draft continuation", "G_OOC_UORT_Unknown");
        
        System.out.println("Navigating to Upload Order Page...");
        
        Ecomm_UploadOrderPage uoPage = eCommPage.clickUploadOrder();
        uoPage.waitForLoad();
        
        System.out.println("Upload Order page reached. Creating draft...");
        
        //Send file path to field
        uoPage.setFilePath(DataItems.uploadDraftFilepath);
        //Select realtime upload
        uoPage.pressRealtime(); 
        
        //Press upload
        Ecomm_MappingAlert alert = uoPage.pressUpload();
        
        System.out.println("Upload pressed. Choosing current mapping...");
        
        //Press "no" to alert, continuing to mapping page
        Ecomm_MappingPage mapPage = alert.pressYes();
        mapPage.waitForLoad();
        
        System.out.println("Mapping page reached. Entering Sales Org and Customer Name...");
        
        mapPage.setSalesOrg(DataItems.salesOrganisation);
        mapPage.setCustomerName(DataItems.custDetails[0]);
        
        System.out.println("Details entered. Confirming map...");
        
        Ecomm_OrderConfirmationPage orderConf = mapPage.pressConfirm();
        orderConf.waitForLoad();
        
        orderConf.setRequestor(DataItems.custDetails[2]);
        
        DataItems.lastUsedPO = orderConf.getUploadCustPOField().getText();
        String date = orderConf.getRequiredDate();
        
        Actions scroller = new Actions(driver);
        scroller.moveToElement(orderConf.getCancelButton()).build().perform();
        WebElement waitForScroll = new WebDriverWait(driver,5).until(ExpectedConditions.visibilityOf(orderConf.getCancelButton()));
        
        //Take a screenshot
        File scrFile13 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile13,new File(DataItems.screenshotsFilepath+"\\EComm\\Orders\\Upload Order\\12Details entered.png"));
        
        System.out.println("Map confirmed. Saving draft...");
        
        orderConf.pressSaveDraft();
        
        Ecomm_OutstandingUploadDraftPage uoDraft = new Ecomm_OutstandingUploadDraftPage(driver);
        uoDraft.waitForLoad();
        
        System.out.println("Draft saved. Editing draft...");
        
        Ecomm_OrderConfirmationPage orderConf2 = uoDraft.pressEdit();
        orderConf2.waitForLoad();
        
        System.out.println("Order confirmation page reached. Checking details...");
        
        Actions scroller2 = new Actions(driver);
        scroller2.moveToElement(orderConf.getCancelButton()).build().perform();
        WebElement waitForScroll2 = new WebDriverWait(driver,5).until(ExpectedConditions.visibilityOf(orderConf.getCancelButton()));
        
        //Take a screenshot
        File scrFile15 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile15,new File(DataItems.screenshotsFilepath+"\\EComm\\Orders\\Upload Order\\13Draft reopened.png"));
         
        Verify.verify(orderConf2.getUploadCustPOField().getText().equals(DataItems.lastUsedPO),"Order Confirmation Page: Customer PO not consistent with input");
        Verify.verify(orderConf2.getRequester().equals(DataItems.custDetails[2]),"Order Confirmation Page: Requester not consistent with input");
        Verify.verify(orderConf2.getShipToParty().equals(DataItems.custDetails[1]),"Order Confirmation Page: Ship to Party Name not consistent with input");
        Verify.verify(orderConf2.getBuyers().equals(DataItems.custDetails[3]),"Order Confirmation Page: Buyers not consistent with input");
        Verify.verify(orderConf2.getRequiredDate().equals(date),"Order Confirmation Page: Required Date not consistent with input");
        
        System.out.println("Details checked.");
        
        driver.close();
        driver.quit();
        
        System.out.println("----------------------------------------------------");
        
    }
    
}
    

