package com.coats.selenium.tests;

import AutomationFramework.DataItems;
import PageObjects.Ecomm_ExportDownloadPage;
import PageObjects.Ecomm_MainPage;
import PageObjects.Ecomm_ManualEntryPage;
import PageObjects.Ecomm_OrderConfirmationPage;
import PageObjects.Ecomm_OrderViewPage;
import PageObjects.Ecomm_OutstandingOrderDraftPage;
import PageObjects.Ecomm_OutstandingOrdersPage;
import PageObjects.Ecomm_OutstandingUploadDraftPage;
import com.coats.selenium.DriverFactory;
import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Ecomm_OO_Test extends DriverFactory {

    @Test //Outstanding Orders Page :: Page and filter checks
    (groups ={"eComm"})
    public void OP1() throws Exception{
        WebDriver driver = getDriver();
        
        //new base test to handle set up
        Ecomm_Base susstTest4 = new Ecomm_Base(driver);
        //Set up returns a manual entry page to begin data entry
        Ecomm_MainPage eCommPage = susstTest4.setUp("OUTSTANDING ORDERS OP1: Complete order from draft","G_OP_F_1");

        System.out.println("Navigating to Outstanding Orders Page...");
        
        Ecomm_OutstandingOrdersPage outOrders = eCommPage.clickOutstandingOrders();
        outOrders.waitForElement();
        
        System.out.println("Outstanding Orders Page reached. Checking title...");
        
        AssertJUnit.assertTrue("Outstanding Orders Page: Title not displayed as expected",outOrders.getBreadcrumb().getText().equals("Orders | Outstanding"));
        
        System.out.println("Title checked.");
        
        outOrders.assertBaseElements();
        
        System.out.println("Checking fields...");
        
        outOrders.checkFields();
        
        System.out.println("Fields checked. Entering filter criteria...");
        
        outOrders.setCustomerName(DataItems.custDetails[0]);
        outOrders.setOrderStatus("Open");
        
        System.out.println("Criteria entered. Press search...");
        
        outOrders.pressSearch();
        outOrders.waitForElement();
        
        System.out.println("Orders listed. Checking for records...");
        
        if (outOrders.checkForRecords()) {
            
            System.out.println("Viewing top item...");
            
            Ecomm_OrderViewPage viewPage = outOrders.pressView(2);
            viewPage.waitForContent();
            
            System.out.println("View displayed. Closing view...");
            
            viewPage.closeView();
            viewPage.waitForInvisibility();
            
            System.out.println("View closed. Pressing print...");
            
            Ecomm_OrderViewPage printPage = outOrders.pressPrint(2);
            printPage.waitForContent();
            
            System.out.println("Print page displayed. Closing page...");
            
            printPage.closeView();
            printPage.waitForInvisibility();
            
            System.out.println("Print view closed.");
            
        } else {
            System.out.println("No records found, cannot test View/Print");
        }
        
        System.out.println("Resetting filter...");
            
        outOrders.pressReset();
        outOrders.waitForElement();
            
        boolean reset;
        
        try {
            Boolean wait = new WebDriverWait(driver,DataItems.shorterWait).until(ExpectedConditions.textToBePresentInElement(outOrders.getCustNameField(), ""));
            reset = true;
        } catch (Exception e) {
            reset = false;
        }
        
        AssertJUnit.assertTrue("Outstanding Order Page: Filter did not reset upon click",reset);
        
        System.out.println("Filter reset. Re-entering filter criteria, preparing to export...");
        
        outOrders.setCustomerName(DataItems.custDetails[0]);
        outOrders.setOrderStatus("Picking In Warehouse");
        
        System.out.println("Criteria set. Exporting records...");
        
        Ecomm_ExportDownloadPage dlPage = outOrders.pressExport();
        dlPage.waitForDownloadCompletion();
        
        System.out.println("Export completed, file downloaded.");
        
    }
    
    @Test //Outstanding Order Drafts Page :: Complete an order from draft
    (groups = {"eComm"})
    public void ODP2() throws IOException, Exception {
        WebDriver driver = getDriver();
        
        //new base test to handle set up
        Ecomm_Base susstTest4 = new Ecomm_Base(driver);
        //Set up returns a manual entry page to begin data entry
        Ecomm_MainPage eCommPage = susstTest4.setUp("OUTSTANDING ORDER DRAFTS ODP2: Complete order from draft","G_OP_ODP_3");

        System.out.println("Navigating to Outstanding Order Draft Page...");
        
        Ecomm_OutstandingOrderDraftPage draftPage = eCommPage.clickOutstandingDraft();
        
        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile,new File(DataItems.screenshotsFilepath+"\\EComm\\Outstanding Orders\\Oustanding Orders Draft\\1Outstanding Orders Draft Page.png"));
                
        System.out.println("Draft page reached. Pressing edit draft...");
        
        String poNo = draftPage.getPONumber();
        
        Ecomm_ManualEntryPage manualEntry = draftPage.pressEdit();
        manualEntry.waitForLoad();
        
        File scrFile2 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile2,new File(DataItems.screenshotsFilepath+"\\EComm\\Outstanding Orders\\Oustanding Orders Draft\\2Edit pressed.png"));
        
        System.out.println("Edit draft pressed. Pressing next...");
        
        //Add checks
        
        Ecomm_OrderConfirmationPage orderConf = manualEntry.pressNext();
        orderConf.waitForElement();
        
        System.out.println("Order confirmation page reached.");
        
        File scrFile6 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile6,new File(DataItems.screenshotsFilepath+"\\EComm\\Outstanding Orders\\Oustanding Orders Draft\\3Order confirmation.png"));
        
        try {
            WebElement message = driver.findElement(By.id("flashMessage"));
            if (message.getText().contains("could not")) {
                System.out.println("***Draft submission failed due to missing fields. Please create at least 2 drafts with all mandatory fields and re-run the test.***");
                System.out.println("Test will now terminate.");
            }
            File scrFile4 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile4,new File(DataItems.screenshotsFilepath+"\\EComm\\Outstanding Orders\\Oustanding Orders Draft\\4Error reached.png"));
        } catch (Exception e) {
            System.out.println("Submitting order...");
            
            Ecomm_OutstandingOrdersPage outOrdersPage = orderConf.pressSubmit();
            outOrdersPage.waitForLoad();
            
            System.out.println("Order submitted.");
            
            File scrFile4 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile4,new File(DataItems.screenshotsFilepath+"\\EComm\\Outstanding Orders\\Oustanding Orders Draft\\4Submit pressed.png"));
            
            Ecomm_OrderViewPage orderView = outOrdersPage.pressView(outOrdersPage.getRow(poNo));
            orderView.waitForContent();
            
            File scrFile5 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile5,new File(DataItems.screenshotsFilepath+"\\EComm\\Outstanding Orders\\Oustanding Orders Draft\\5Order view.png"));
            
            System.out.println("PO Number: "+poNo);
        }
    }
    
    @Test //Outstanding Order Drafts Page :: Page and filter checks, view, edit, and cancel
    (groups = {"eComm"})
    public void ODP1() throws IOException, Exception {
        WebDriver driver = getDriver();
        
        //new base test to handle set up
        Ecomm_Base susstTest4 = new Ecomm_Base(driver);
        //Set up returns a manual entry page to begin data entry
        Ecomm_MainPage eCommPage = susstTest4.setUp("OUTSTANDING ORDER DRAFTS ODP1: Page check, search, view, edit, cancel draft","G_OP_ODP_1 to 5");

        System.out.println("Navigating to Outstanding Order Draft Page...");

        System.out.println("eComm page loaded. Navigating to Outstanding Draft Page...");
        
        Ecomm_OutstandingOrderDraftPage draftPage = eCommPage.clickOutstandingDraft();
        draftPage.waitForElement();
        
        System.out.println("Outstanding Draft Page reached.");
                
        draftPage.assertBaseElements();
        
        System.out.println("Entering PO number for search criteria...");
        
        draftPage.setPONumber("AutoTestPO");
        
        //Take a screenshot
        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile,new File(DataItems.screenshotsFilepath+"\\EComm\\Outstanding Orders\\Oustanding Orders Draft\\6PO entered.png"));
        
        draftPage.pressSearch();
        draftPage.waitForLoad();
        
        //Take a screenshot
        File scrFile2 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile2,new File(DataItems.screenshotsFilepath+"\\EComm\\Outstanding Orders\\Oustanding Orders Draft\\7Search pressed.png"));
        
        System.out.println("Search complete. Viewing draft...");
        
        draftPage.pressView();
        
        //Take a screenshot
        File scrFile3 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile3,new File(DataItems.screenshotsFilepath+"\\EComm\\Outstanding Orders\\Oustanding Orders Draft\\8View pressed.png"));
        
        //Close view
        Ecomm_OutstandingOrderDraftPage draftPage2 = draftPage.closeView();
        
        System.out.println("Draft view closed. Pressing edit draft...");
        
        Ecomm_ManualEntryPage manualEntry = draftPage2.pressEdit();
        
        System.out.println("Edit draft pressed. Navigating back to draft search page...");
        
        driver.navigate().back();
        
        System.out.println("Search page reached. Pressing cancel draft...");
        
        //Cancel a draft
        draftPage2.pressCancel();
        
        //Take a screenshot
        File scrFile4 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile4,new File(DataItems.screenshotsFilepath+"\\EComm\\Outstanding Orders\\Oustanding Orders Draft\\9Cancel pressed.png"));
        
        System.out.println("Draft cancelled.");

    }
       
    @Test //Upload Order Drafts Page :: Page checks, edit, and cancel upload order draft
    (groups = {"eComm"})
    public void UODP1() throws IOException, Exception {
        WebDriver driver = getDriver();
        
        //new base test to handle set up
        Ecomm_Base susstTest4 = new Ecomm_Base(driver);
        //Set up returns a manual entry page to begin data entry
        Ecomm_MainPage eCommPage = susstTest4.setUp("OUTSTANDING UPLOAD DRAFTS UODP1: Page check, search, view, edit, cancel draft","G_OP_UODP_1 to 5");

        System.out.println("Navigating to Outstanding Upload Drafts Page...");
        
        Ecomm_OutstandingUploadDraftPage upDraftPage = eCommPage.clickOutstandingUploadDraft();
        upDraftPage.waitForLoad();
        
        System.out.println("Outstanding Upload Drafts reached.");
        
        //Take a screenshot
        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile,new File(DataItems.screenshotsFilepath+"\\EComm\\Outstanding Orders\\Oustanding Upload Draft\\1Outstanding Upload Draft Page.png"));
        
        upDraftPage.assertBaseElements();
        
        System.out.println("Entering Customer Name for search criteria...");
        
        upDraftPage.setCustomerName(DataItems.custDetails[0]);
        
        //Take a screenshot
        File scrFile1 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile1,new File(DataItems.screenshotsFilepath+"\\EComm\\Outstanding Orders\\Oustanding Upload Draft\\2Customer Name entered.png"));
        
        upDraftPage.pressSearch();
        upDraftPage.waitForLoad();
        
        //Take a screenshot
        File scrFile2 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile2,new File(DataItems.screenshotsFilepath+"\\EComm\\Outstanding Orders\\Oustanding Upload Draft\\3Search pressed.png"));
        
        System.out.println("Search complete. Pressing edit...");
        
        Ecomm_OrderConfirmationPage upConf = upDraftPage.pressEdit();
        upConf.waitForLoad();
        
        //Take a screenshot
        File scrFile3 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile3,new File(DataItems.screenshotsFilepath+"\\EComm\\Outstanding Orders\\Oustanding Upload Draft\\4Edit pressed.png"));
        
        System.out.println("Order confirmation page reached. Asserting base elements...");
        
        //Take a screenshot
        File scrFile4 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile4,new File(DataItems.screenshotsFilepath+"\\EComm\\Outstanding Orders\\Oustanding Upload Draft\\5Confirmation page.png"));
        
        upConf.assertBaseElements();
        
        System.out.println("Assertions successful. Navigating back to Outstanding Upload Drafts...");
        
        driver.navigate().back();
        
        System.out.println("Outstanding Upload Drafts page reached. Pressing delete draft...");
        
        upDraftPage.pressDelete();
                
        //Take a screenshot
        File scrFile5 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile5,new File(DataItems.screenshotsFilepath+"\\EComm\\Outstanding Orders\\Oustanding Upload Draft\\5Draft deleted.png"));
        
        System.out.println("Draft deleted.");
        
    }
    
}
