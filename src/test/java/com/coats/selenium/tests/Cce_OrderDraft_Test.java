
package com.coats.selenium.tests;

import AutomationFramework.DataItems;
import PageObjects.CCE_AddOrderPage;
import PageObjects.CCE_CancelDraftPage;
import PageObjects.CCE_MainPage;
import PageObjects.CCE_OrderSamplesPage;
import PageObjects.CCE_OrderStatusPage;
import PageObjects.CCE_OrderViewPage;
import PageObjects.CCE_OutstandingDraftPage;
import com.coats.selenium.DriverFactory;
import static com.coats.selenium.DriverFactory.getDriver;
import com.google.common.base.Verify;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;


public class Cce_OrderDraft_Test extends DriverFactory {
    
    @Test //Outstanding Draft Page :: Page and filter checks, create and cancel (from outstanding draft table) draft
    (groups = {"CCE"})
    public void OD1() throws Exception {
        
        WebDriver driver = getDriver();
        
        Cce_Base base = new Cce_Base(driver);
        CCE_MainPage ccePage = base.setUp("Outstanding Draft Page: Page and filter checks", "G_CCE_Unknown");
        ccePage.waitForLoad();
        
        System.out.println("Navigating to Order Samples...");
        
        CCE_OrderSamplesPage osPage = ccePage.pressOrderSamples();
        osPage.waitForLoad();
        
        System.out.println("Order Samples Page reached. Entering customer details...");
        
        osPage.setCustName(DataItems.custDetails[0]);
        osPage.setRequestor(DataItems.custDetails[2]);
        
        System.out.println("Customer details entered. Submitting...");
        
        CCE_AddOrderPage orderPage = osPage.pressSubmit();
        orderPage.waitForElement();
        
        System.out.println("Submitted. Entering product details...");
        
        orderPage.setShipToParty(DataItems.custDetails[1]);
        orderPage.setArticle(DataItems.article, 0);
        orderPage.setMUMType(DataItems.coneMUM, 0);
        orderPage.setRequestType(DataItems.sewing, 0);
        orderPage.setPurposeType(DataItems.salesSamp, 0);
        orderPage.setQuantity(1,0);
        
        String creationDate = orderPage.getCreationDate();
        System.out.println(creationDate);
        
        System.out.println("Product details entered. Pending order...");
        
        CCE_OrderStatusPage pendPage = orderPage.pressPendOrder();
        pendPage.waitForLoad();
        
        System.out.println("Order pended. Navigating to Outstanding Drafts...");
        
        CCE_OutstandingDraftPage draftPage = pendPage.pressOutstandingDraft();
        draftPage.waitForLoad();
        
        System.out.println("Outstanding Drafts Page reached. Locating draft...");
        
        int row = draftPage.findDraft(creationDate);
        String orderNo = null;
        
        if (row == -1) {
            System.out.println("***No draft found on draft page***");
        } else {
            System.out.println("Viewing draft...");
            
            CCE_OrderViewPage viewPage = draftPage.pressView(row);
            viewPage.switchTo();
            viewPage.waitForContent();
            
            System.out.println("View displayed. Closing view...");
            orderNo = viewPage.getOrderNumber();
            
            viewPage.closeView();
            viewPage.waitForInvisibility();
            
            System.out.println("View closed.");
        }
        
        System.out.println("Cancelling draft...");
        
        CCE_CancelDraftPage cancelPage = draftPage.pressCancel(row);
        cancelPage.switchTo();
        cancelPage.waitForElement();
        
        System.out.println("Cancel view displayed. Setting reason...");
        
        cancelPage.setReason(DataItems.wrongEntryCust);
        
        System.out.println("Reason set. Saving...");
        
        cancelPage.pressSave();
        
        /*
        System.out.println("Response saved. Closing view...");
        
        cancelPage.closeView();
        cancelPage.waitForInvisibility();
        
        System.out.println("View closed");
        */

        System.out.println("Saved. Checking draft was removed...");
        
        CCE_OutstandingDraftPage draftPage2 = cancelPage.pressOutstandingDraft();
        draftPage2.waitForLoad();
        
        AssertJUnit.assertFalse("Outstanding Draft Page: Draft persists in table although cancelled",draftPage2.findDraftByOrderNo(orderNo));
        
        System.out.println("Draft removed.");
    }
    
    @Test //Outstanding Draft Page :: Edit draft
    (groups = {"CCE"})
    public void OD2() throws Exception {
        
        WebDriver driver = getDriver();
        
        Cce_Base base = new Cce_Base(driver);
        CCE_MainPage ccePage = base.setUp("Outstanding Draft Page: Edit Draft", "G_CCE_Unknown");
        ccePage.waitForLoad();
        
        System.out.println("Navigating to Order Samples...");
        
        CCE_OrderSamplesPage osPage = ccePage.pressOrderSamples();
        osPage.waitForLoad();
        
        System.out.println("Order Samples Page reached. Entering customer details...");
        
        osPage.setCustName(DataItems.custDetails[0]);
        osPage.setRequestor(DataItems.custDetails[2]);
        
        System.out.println("Customer details entered. Submitting...");
        
        CCE_AddOrderPage orderPage = osPage.pressSubmit();
        orderPage.waitForLoad();
        
        System.out.println("Submitted. Entering product details...");
        
        orderPage.setShipToParty(DataItems.custDetails[1]);
        orderPage.setArticle(DataItems.article, 0);
        orderPage.setShadeCode(DataItems.shadeCode, 0);
        orderPage.setMUMType(DataItems.coneMUM, 0);
        orderPage.setRequestType(DataItems.sewing, 0);
        orderPage.setPurposeType(DataItems.salesSamp, 0);
        orderPage.setQuantity(1,0);
        
        System.out.println("Product details entered. Saving as draft...");
        
        CCE_OrderStatusPage statusPage = orderPage.pressPendOrder();
        statusPage.waitForLoad();
        
        System.out.println("Draft saved. Navigating to outstanding drafts...");
        
        CCE_OutstandingDraftPage draftPage = statusPage.pressOutstandingDraft();
        draftPage.waitForLoad();
        
        System.out.println("Outstanding Drafts page reached. Editing top item...");
        
        CCE_AddOrderPage entryPage = draftPage.pressEdit(0);
        entryPage.waitForLoad();
        
        System.out.println("Order entry page reached. Verifying values are the consistent (excludes MUM Type, Request Type, Shade code)...");
        
        Verify.verify(entryPage.getCustomerName().equals(DataItems.custDetails[0]),"Order Samples Draft: Customer name not consistent with input");
        Verify.verify(entryPage.getRequesterName().equals(DataItems.custDetails[2]),"Order Samples Draft: Requester Name not consistent with input");
        Verify.verify(entryPage.getArticle().equals(DataItems.article),"Order Samples Draft: Article not consistent with input");
        Verify.verify(entryPage.getShadeCodeField().getText().trim().equals(DataItems.shadeCode),"Order Samples Draft: Shade code not consistent with input");
        
        Select purposeType = new Select(entryPage.getPurposeField());
        String selectedPurpose = purposeType.getFirstSelectedOption().getText().trim();
        Verify.verify(selectedPurpose.equals(DataItems.salesSamp),"Order Samples Draft: Purpose type not consistent with input");
        
        Verify.verify(entryPage.getQuantityField().getAttribute("value").equals("1"),"Order Samples Draft: Quantity not consistent with input");
        
        System.out.println("Vaues verified. Submitting order...");
        
        CCE_OrderStatusPage statusPage2 = entryPage.pressSubmit();
        statusPage2.waitForLoad();
        
        System.out.println("Order submitted. Checking order status...");

        CCE_OrderViewPage viewPage = statusPage2.pressView(2);
        viewPage.switchTo();
        viewPage.waitForContent();
        
        String orderNo = viewPage.getOrderNumber();
        System.out.println("Order No: " + orderNo);
        
        viewPage.closeView();
        viewPage.waitForInvisibility();
        driver.switchTo().defaultContent();
        
        String orderStage = statusPage2.getOrderStage(orderNo);
        
        System.out.println("Order stage: " + orderStage);
        
        if (orderStage.contains("Drafted")) {
            System.out.println("***Unexpected Stage detected***");
        }
    }
    
    @Test //Outstanding Draft Page :: Cancelling draft (from order entry page)
    (groups = {"CCE"})
    public void OD3() throws Exception {
        
        WebDriver driver = getDriver();
        
        Cce_Base base = new Cce_Base(driver);
        CCE_MainPage ccePage = base.setUp("Outstanding Draft Page: Cancelling draft from order entry page", "G_CCE_Unknown");
        ccePage.waitForLoad();
        
        System.out.println("Navigating to Order Draft...");
        
        CCE_OutstandingDraftPage draftPage = ccePage.pressOutstandingDraft();
        draftPage.waitForLoad();
        
        System.out.println("Outstanding Draft Page reached. Retrieving Order No for top item...");
        
        String orderNo = draftPage.getOrderNo(2);
        
        CCE_AddOrderPage entryPage = draftPage.pressEdit(2);
        entryPage.waitForLoad();
        
        System.out.println("Entry page reached. Cancelling draft...");
        
        CCE_OutstandingDraftPage draftPage2 = entryPage.pressCancelToDrafts();
        draftPage2.waitForElement();
        
        System.out.println("Order cancelled. Removing draft...");
        
        CCE_CancelDraftPage cancelPage = draftPage2.pressCancel(2);
        cancelPage.switchTo();  
        cancelPage.setReason("Wrong Entry by Customer");
        
        CCE_OutstandingDraftPage draftPage3 = cancelPage.pressSave();
        draftPage3.waitForElement();

        AssertJUnit.assertFalse("Outstanding Draft Page: Draft persists although cancelled",draftPage3.findDraftByOrderNo(orderNo));
        
        System.out.println("No draft found, as expected");
        
    }
    
    @Test //Outstanding Draft Page :: Re-save draft
    (groups = {"CCE"})
    public void OD4() throws Exception {
        
        WebDriver driver = getDriver();
        
        Cce_Base base = new Cce_Base(driver);
        CCE_MainPage ccePage = base.setUp("Outstanding Draft Page: Re-save draft", "G_CCE_Unknown");
        ccePage.waitForLoad();
        
        System.out.println("Navigating to Order Draft...");
        
        CCE_OutstandingDraftPage draftPage = ccePage.pressOutstandingDraft();
        draftPage.waitForLoad();
        
        System.out.println("Draft page reached. Retrieving order number and editing top item...");
        
        String orderNo = draftPage.getOrderNo(2);
        
        CCE_AddOrderPage entryPage = draftPage.pressEdit(2);
        entryPage.waitForLoad();
        
        System.out.println("Order entry page reached. Pressing pend order...");
        
        CCE_OrderStatusPage statusPage = entryPage.pressPendOrder();
        statusPage.waitForLoad();
        
        System.out.println("Order draft saved. Checking draft appears in table...");
        
        String orderStage = statusPage.getOrderStage(orderNo);
        
        AssertJUnit.assertFalse("Order Status Page: Re-saved draft does not appear in table",orderStage.equals("not found"));
        
        System.out.println("Draft found.");
        
        System.out.println("Order stage: " + orderStage);
        System.out.println("Order No.: " + orderNo);
    }

}
