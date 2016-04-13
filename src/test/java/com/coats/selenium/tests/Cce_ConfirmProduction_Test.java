
package com.coats.selenium.tests;

import AutomationFramework.DataItems;
import AutomationFramework.PreFlows;
import AutomationFramework.Wait;
import PageObjects.CCE_AddOrderPage;
import PageObjects.CCE_MainPage;
import PageObjects.CCE_ConfirmProductionPage;
import PageObjects.CCE_EnrichOrderPage;
import PageObjects.CCE_ManualEnrichPage;
import PageObjects.CCE_OrderSamplesPage;
import PageObjects.CCE_OrderStatusPage;
import PageObjects.CCE_OrderViewPage;
import com.coats.selenium.DriverFactory;
import static com.coats.selenium.DriverFactory.getDriver;
import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.testng.AssertJUnit;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;


public class Cce_ConfirmProduction_Test extends DriverFactory {
    
    @Test //Confirm Production Page :: Page and filer checks, view and confirm
    (groups = {"CCE", "QuickTest"})
    public void CP1() throws InterruptedException, IOException, Exception {
        //New driver object to control browser
        WebDriver driver = getDriver();
        
        //New base object to handle log-in and set up
        Cce_Base base = new Cce_Base(driver);
        
        //Set up returns a CCE Page and outputs test details
        CCE_MainPage ccePage = base.setUp("Confirm Production CP1: Page checks, filter function, view button, confirm selection", "C_CCE_CP_1-2");
        
        System.out.println("Navigating to Confirm ProductiRon...");
        
        CCE_ConfirmProductionPage cpPage = ccePage.pressConfirmProduction();
        cpPage.waitForLoad();
        
        //Take a screenshot
        File scrFile1 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile1,new File(DataItems.screenshotsFilepath+"\\CCE\\Confirm Production\\1Confirm production page.png"));
        
        System.out.println("Confirm Production loaded.");
        
        //Assert base elements
        cpPage.assertBaseElements();
        
        System.out.println("Asserting page title is as expected...");
        
        AssertJUnit.assertTrue("Confirm Production Page: Title not displayed as expected.", cpPage.getBreadcrumb().getText().equals("Orders | Confirm Production"));
        
        System.out.println("Page title correct. Checking fields...");
        
        cpPage.checkFields();
        
        System.out.println("Fields checked. Entering filter criteria...");
        
        cpPage.setCustName(DataItems.custDetails[0]);
        cpPage.setRequestType("Sewing");
        
        //Take a screenshot
        File scrFile2 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile2,new File(DataItems.screenshotsFilepath+"\\CCE\\Confirm Production\\2Filter criteria entered.png"));
        
        System.out.println("Criteria entered. Listing orders...");
        
        cpPage.pressListOrders();
        cpPage.waitForLoad();
        
        //Take a screenshot
        File scrFile3 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile3,new File(DataItems.screenshotsFilepath+"\\CCE\\Confirm Production\\3Orders Listed.png"));
        
        System.out.println("Orders listed. Pressing confirm...");
        
        cpPage.pressConfirm();
        
        //Take a screenshot
        File scrFile4 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile4,new File(DataItems.screenshotsFilepath+"\\CCE\\Confirm Production\\4Confirm selected.png"));


        System.out.println("Confirm selected. Pressing DN Print...");
        
        CCE_OrderViewPage viewPage = cpPage.pressDnPrint();

        boolean errorDisplayed;

    /*    try {
            Alert alert = Wait.alert(driver);
            alert.accept();

            System.out.println("Error received: "+alert.getText());
            errorDisplayed = true;
        } catch (Exception e) {
            System.out.println("No error displayed");
            errorDisplayed = false;
        }
        */
        //Take a screenshot
        File scrFile5 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile5,new File(DataItems.screenshotsFilepath+"\\CCE\\Confirm Production\\5DN view.png"));
        
        System.out.println("View displayed. Pressing print...");
        
        viewPage.pressPrint();
        
        System.out.println("Closing view...");
        
        viewPage.closeView();
        viewPage.waitForInvisibility();
        
        try {
            Alert alert = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.alertIsPresent());
            String alertMsg = alert.getText();
        
            System.out.println("View closed. Alert appeared: "+alertMsg +". Dismissing alert...");
        
            alert.dismiss();
            
            System.out.println("Alert dismissed.");
        } catch (Exception e) {
            System.out.println("No additional alerts upon close.");
        }
        
        
        
        System.out.println("Selecting MUM Type...");
        
        cpPage.setMUMType(DataItems.copMUM);
        
        //Take a screenshot
        File scrFile7 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile7,new File(DataItems.screenshotsFilepath+"\\CCE\\Confirm Production\\7MUM type set.png"));
        
        System.out.println("MUM Type set. Setting quantity produced...");
        
        cpPage.setQtyProd(String.valueOf(DataItems.quantity));
        
        //Take a screenshot
        File scrFile8 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile8,new File(DataItems.screenshotsFilepath+"\\CCE\\Confirm Production\\8Quantity produced set.png"));
        
        System.out.println("Quantity produced set. Setting 'Send To' to 'Deliver to customer'...");
        
        cpPage.setSendTo("Deliver to Customer");
        
        //Take a screenshot
        File scrFile9 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile9,new File(DataItems.screenshotsFilepath+"\\CCE\\Confirm Production\\9Send to set.png"));
        
        System.out.println("Send To set. Saving...");
        
        cpPage.pressSave();
        cpPage.waitForLoad();
        
        //Take a screenshot
        File scrFile10 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile10,new File(DataItems.screenshotsFilepath+"\\CCE\\Confirm Production\\10Changes saved.png"));
       
        
    }
    
    @Test //Confirm Production Page :: Reset and Cancel
    (groups = {"CCE"})
    public void CP2() throws IOException, Exception {
        //New driver object to control browser
        WebDriver driver = getDriver();
        
        //New base object to handle log-in and set up
        Cce_Base base = new Cce_Base(driver);
        
        //Set up returns a CCE Page and outputs test details
        CCE_MainPage ccePage = base.setUp("Confirm Production CP2: Reset and cancel", "C_CCE_CP_x");
        
        System.out.println("Navigating to Confirm Production...");
        
        CCE_ConfirmProductionPage cpPage = ccePage.pressConfirmProduction();
        
        System.out.println("Confirm Production loaded. Entering filter criteria...");
        
        cpPage.setCustName(DataItems.custDetails[0]);
        cpPage.setShipToName(DataItems.custDetails[1]);
        
        //Take a screenshot
        File scrFile10 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile10,new File(DataItems.screenshotsFilepath+"\\CCE\\Confirm Production\\10Filter criteria entered.png"));
        
        System.out.println("Criteria entered. Pressing reset...");
        
        CCE_ConfirmProductionPage cpPage2 = cpPage.pressReset();
        cpPage2.waitForLoad();
        
        //Take a screenshot
        File scrFile11 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile11,new File(DataItems.screenshotsFilepath+"\\CCE\\Confirm Production\\11Reset pressed.png"));
        
        System.out.println("Filter reset. Pressing cancel...");
        
        cpPage2.pressCancel();
        cpPage2.waitForLoad();
        
        //Take a screenshot
        File scrFile12 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile12,new File(DataItems.screenshotsFilepath+"\\CCE\\Confirm Production\\12Page cancelled.png"));
        
        System.out.println("Cancel pressed.");

    }

    @Test //Confirm Production Page :: SUMST :: Lab SOS can be confirmed and status changes to Delivered
    (groups = {"Solo"})
    public void CP3() throws Exception {
        WebDriver driver = getDriver();
        
        Cce_Base base = new Cce_Base(driver);
        CCE_MainPage mainPage = base.setUp("Confirm Production CP3: Lab SOS status update", "G_CCE_SOC_16");

        PreFlows pf = new PreFlows();
        pf.deActivateAutoEnrichForCustomer(driver, DataItems.lifeEasyCustomer);

        System.out.println("Navigating to Order Samples Page...");
        
        CCE_OrderSamplesPage orderPage = mainPage.pressOrderSamples();
        orderPage.waitForElement();
        
        System.out.println("Order Samples page reached. Entering Customer details...");
        
        orderPage.setCustName(DataItems.custDetails[0]);
        orderPage.setRequestor(DataItems.custDetails[2]);
        
        System.out.println("Customer details entered. Submitting prompt...");
        
        CCE_AddOrderPage aoPage = orderPage.pressSubmit();
        aoPage.waitForElement();
        
        System.out.println("Prompt submitted. Setting ship to and line details...");
        
        aoPage.setShipToParty(DataItems.custDetails[1]);
        aoPage.setArticle(DataItems.article,0);
        aoPage.setShadeCode(DataItems.shadeCode, 0);
        aoPage.setMUMType(DataItems.copMUM,0);
        aoPage.setCustomerRef(0);
        aoPage.setRequestType(DataItems.sewing,0);
        aoPage.setPurposeType(DataItems.bulkPurpose,0);
        aoPage.setQuantity(1,0);
        
        System.out.println("Line details entered. Submitting order...");
        
        CCE_OrderStatusPage statusPage = aoPage.pressSubmit();
        statusPage.waitForElement();
        
        System.out.println("Order Status Page loaded. Retrieving Order No. for order...");
        
        String orderNo = statusPage.getOrderNo(DataItems.lastUsedPO);

        driver.navigate().refresh();
        
        System.out.println("Order No.: " + orderNo+". Navigating to Manual Enrich Page...");
        
        CCE_ManualEnrichPage mePage = statusPage.pressManualEnrich();
        mePage.waitForElement();
        
        System.out.println("Manual Enrich Page reached. Checking for records...");
        
        if (mePage.checkForRecords()) {
            System.out.println("Records found. Finding order with above Order No. and viewing...");
            
            int row = mePage.getRow(orderNo);
            
            AssertJUnit.assertTrue("Manual Enrich Page: Order (OrderNo.: "+orderNo+") not displayed after submission",row!=-1);
            
            CCE_OrderViewPage viewPage = mePage.pressView(row);
            viewPage.waitForContentAlt2();
            
            System.out.println("View displayed. Order No.: "+orderNo+". Closing view...");
            
            viewPage.closeView();
            viewPage.waitForInvisibility();
            
            System.out.println("View closed. Enriching order...");
            
            CCE_EnrichOrderPage enrichPage = mePage.pressEnrich(2);
            enrichPage.waitForElement();
            
            System.out.println("Enrich Order page reached. Setting SOS to Lab...");

            enrichPage.pressLab();
            
            System.out.println("SOS set. Enriching All Order lines...");
            
            CCE_ManualEnrichPage mePage2 = enrichPage.pressEnrichAll();
            mePage2.waitForElement();
            
            System.out.println("Order enriched. Navigating to Confirm Production Page...");
            
            CCE_ConfirmProductionPage cpPage = mePage2.pressConfirmProduction();
            cpPage.waitForElement();
            
            System.out.println("Confirm Production page reached. Checking order is shown...");
            
            AssertJUnit.assertTrue("Confirm Production Page: Order (Order No.: "+orderNo+") does not appear after Lab SOS selected in enrichment",cpPage.findOrder(orderNo));
            
            System.out.println("Order found in table. Pressing confirm...");
            
            cpPage.pressConfirm();
            
            System.out.println("Confirm selected. Selecting Deliver to Customer...");
            
            cpPage.setSendTo("Deliver to Customer");


            System.out.println("Send To set. Printing DN...");
            
            CCE_OrderViewPage viewPage2 = cpPage.pressDnPrint();
            viewPage2.waitForContentAlt2();
            
            System.out.println("DN Printed. Closing view and saving...");
            
            viewPage2.closeView();
            
            CCE_ConfirmProductionPage cpPage2 = cpPage.acceptSave();
            cpPage2.waitForElement();
            
            System.out.println("Saved. Checking order status...");
            
            CCE_OrderStatusPage statusPage2 = cpPage2.pressOrderStatus();
            statusPage2.waitForElement();
            
            String stage = statusPage2.getOrderStage(orderNo);
            
            AssertJUnit.assertTrue("Order Status Page: Order stage not as expected for order "+orderNo+", should be Delivered but is: " + stage+".",stage.equals("Delivered"));
            
            System.out.println("Order status as expected.");
            
            System.out.println("Order No.: " + orderNo);
            System.out.println("Customer PO: " + DataItems.lastUsedPO);
            System.out.println("Order Stage: " + stage);
            
            
        } else {
            System.out.println("No records found. Test was not completed");
        }
    } 
}
