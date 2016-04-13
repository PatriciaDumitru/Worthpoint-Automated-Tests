
package com.coats.selenium.tests;

import AutomationFramework.DataItems;
import AutomationFramework.PreFlows;
import AutomationFramework.Wait;
import PageObjects.*;
import com.coats.selenium.DriverFactory;
import static com.coats.selenium.DriverFactory.getDriver;
import com.google.common.base.Verify;
import java.io.File;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;


public class Ecomm_ME_SUSST_Test extends DriverFactory {
    
    @Test //Manual Entry Page :: SUSST :: Page checks and single line order using YMN and master shade
    (groups = {"eComm","eComm_Orders", "QuickTest"})
    public void SUSST1() throws Exception {
        //New chrome driver
        WebDriver driver = getDriver();

        //Login as global admin to setup master data
        Cce_Base baseSetupMaster = new Cce_Base(driver);
        baseSetupMaster.setUp("Set Master", "Data");

        //Set Master Data
        System.out.println("Set Master Data...");
        PreFlows pf = new PreFlows();
        pf.deActivateCallOffOrderForCustomer(driver, DataItems.lifeEasyCustomer);
        pf.disableMOQForCustomer(driver, DataItems.lifeEasyCustomer);
        pf.chooseTheOtherProfile(driver);
        pf.logoutAction(driver);

        //new base test to handle set up
        Ecomm_Base base = new Ecomm_Base(driver);
        //Set up returns a manual entry page to begin data entry
        Ecomm_MainPage eCommPage = base.setUp("MANUAL ENTRY SUSST1: Single line, Your Material Number with master data shade code","G_OOC_ME_SUSST_1",DataItems.validCustUsername,DataItems.validCustPassword);

        System.out.println("Navigating to Manual Entry...");

        //press manual entry
        Ecomm_ManualEntryPage manualEntryPage = eCommPage.clickManualEntry();
        manualEntryPage.waitForElement();

        System.out.println("Manual Entry loaded.");

        manualEntryPage.assertBaseElements();

        System.out.println("Checking title...");
        
        AssertJUnit.assertTrue("Manual Entry Page: Title not displayed as expected",manualEntryPage.getBreadcrumb().getText().equals("Orders | Manual Entry"));
        
        System.out.println("Title checked. Checking fields...");
        
        manualEntryPage.checkSUSSTFields();
        
        System.out.println("Fields checked. Entering customer details...");

        //Input Customer Details    
        manualEntryPage.setShipToPartyWithWait(DataItems.conOrdDetails[1]);
        manualEntryPage.setBuyers(DataItems.conOrdDetails[3]);
        manualEntryPage.setPONumber(DataItems.conOrdDetails[4]);

        System.out.println("Customer details entered. Entering product details...");

        //Number of order lines
        int numberOfLines = 1;

        //Order details to be entered
        String[][] details = {
            //line 1 details
            {DataItems.yourMatNumSUSST,String.valueOf(DataItems.quantity)}
            //Further line details, each in their own array
        };

        //Input details for each line
        for (int i = 0; i < numberOfLines; i++) {
            manualEntryPage = manualEntryPage.setOrderDetailsYMN(details[i], i);
        }
        
        String date = manualEntryPage.getDate(0);
        
        System.out.println("Product details entered. Ensuring auto-fill is correct...");

        /*
        AssertJUnit.assertTrue("Manual Entry Page: Auto-fill brand not as expected in master data",manualEntryPage.getBrand(0).equals(DataItems.brandSUSST));
        AssertJUnit.assertTrue("Manual Entry Page: Auto-fill ticket not as expected in master data",manualEntryPage.getTicket(0).equals(DataItems.ticketSUSST));
        AssertJUnit.assertTrue("Manual Entry Page: Auto-fill length not as expected in master data",manualEntryPage.getLength(0).equals(DataItems.lengthSUSST));
        AssertJUnit.assertTrue("Manual Entry Page: Auto-fill finish not as expected in master data",manualEntryPage.getFinish(0).equals(DataItems.finishSUSST));
        AssertJUnit.assertTrue("Manual Entry Page: Auto-fill shade code not as expected in master data",manualEntryPage.getShadeCode(0).equals(DataItems.shadeSUSST));
        */

        System.out.println("Auto-fill correct. Pressing next...");
        
        //Press next
        Ecomm_OrderConfirmationPage orderConf = manualEntryPage.pressNext();

        // manualEntryPage.susstTwik();

        orderConf.waitForElement();

        System.out.println("Order confirmation page reached. Checking details are maintained...");
        
        AssertJUnit.assertTrue("Order Confirmation Page: Customer PO Number not maintained after manual entry page",orderConf.getUploadPONumber().equals(DataItems.lastUsedPO));
        AssertJUnit.assertTrue("Order Confirmation Page: Ship To Party not maintained after manual entry page",orderConf.getShipToParty().equals(DataItems.conOrdDetails[1]));
        AssertJUnit.assertTrue("Order Confirmation Page: Buyers not maintained after manual entry page",orderConf.getBuyers().equals(DataItems.conOrdDetails[3]));
        AssertJUnit.assertTrue("Order Confirmation Page: Your Material Number not maintained after manual entry page",orderConf.getYourMatNum().equals(DataItems.yourMatNumSUSST));
        
        String[] parts = orderConf.getCoatsMaterial().split("-");
        System.out.println(parts[1]);
        AssertJUnit.assertTrue("Order Confirmation Page: Shade Code (from Coats Mat Num) not maintained after manual entry page",parts[1].equals(DataItems.shadeSUSST));
        
        AssertJUnit.assertTrue("Order Confirmation Page: Ordered Quantity not maintained after manual entry page",orderConf.getOrderedQty()==DataItems.quantity);
        AssertJUnit.assertTrue("Order Confirmation Page: Required Date not maintained after manual entry page",orderConf.getRequiredDate().equals(date));
        
        //Press Submit
        Ecomm_OutstandingOrdersPage outOrders = orderConf.pressSubmit();

        outOrders.waitForElement();

        System.out.println("Order submitted. Viewing order...");
        
        //Verify values in outstanding orders tab
        //Get the row number of the order in the table and press view
        int rowNumber = outOrders.getRow(DataItems.lastUsedPO);
        System.out.println(rowNumber);
        Ecomm_OrderViewPage orderView = outOrders.pressView(rowNumber);       
        //wait for overlay to load
        orderView.waitForContent();
        orderView.switchTo();
        
        System.out.println("Order view displayed. Verifying values...");
        
        Verify.verify(orderView.getYourMatNumCell().getText().equals(DataItems.yourMatNumSUSST),"Order view: Your Material Number does not match input");
        Verify.verify(orderView.getArticleCell().getText().equals(DataItems.articleSUSST),"Order view: Article does not match expected input");
        Verify.verify(orderView.getBrandCell().getText().equals(DataItems.brandSUSST),"Order view: Brand does not match expected input");
        Verify.verify(orderView.getTicketCell().getText().equals(DataItems.ticketSUSST),"Order view: Ticket does not match expected input");
        Verify.verify(orderView.getShadeCodeCell().getText().equals(DataItems.shadeSUSST),"Order view: Shade code does not match expected input");

        System.out.println("Values verified. Closing view...");
        
        //Exit view
        orderView.exitView();
        orderView.waitForInvisibility();
        driver.switchTo().defaultContent();
        
        System.out.println("View closed.");
        
        //Output order number for test reference
        String orderNumber = outOrders.getOrderNumber(rowNumber);
        System.out.println("Order Number: " + orderNumber);
    }
    
    @Test //Manual Entry Page :: SUSST :: Multiple line order using YMN and master shade
    (groups = {"eComm","eComm_Orders"})
    public void SUSST2() throws Exception {
        //New chrome driver
        WebDriver driver = getDriver();

        //new base test to handle set up
        Ecomm_Base base = new Ecomm_Base(driver);
        //Set up returns a manual entry page to begin data entry
        Ecomm_MainPage eCommPage = base.setUp("MANUAL ENTRY SUSST2: Multi-line, Your Material Number with master data shade code","G_OOC_ME_SUSST_2",DataItems.validCustUsername,DataItems.validCustPassword);

        System.out.println("Navigating to Manual Entry...");

        //press manual entry
        Ecomm_ManualEntryPage manualEntryPage = eCommPage.clickManualEntry();
        manualEntryPage.waitForElement();

        System.out.println("Manual Entry Page reached. Entering customer details...");

        //Input Customer Details    
        manualEntryPage.setShipToPartyWithWait(DataItems.conOrdDetails[1]);
        manualEntryPage.setBuyers(DataItems.conOrdDetails[3]);
        manualEntryPage.setPONumber(DataItems.conOrdDetails[4]);

        System.out.println("Customer details entered. Entering product details...");

        int numberOfLines = 2;

        //Order details to be entered
        String[][] details = {
            //line 1 details
            {DataItems.yourMatNumSUSST,String.valueOf(DataItems.quantity)},
            //line 2 details
            {DataItems.yourMatNum2SUSST,String.valueOf(DataItems.quantity)}
        };

        //Input details for each line
        for (int i = 0; i < numberOfLines; i++) {
            manualEntryPage = manualEntryPage.setOrderDetailsYMN(details[i], i);
        }
        
        String date = manualEntryPage.getDate(0);
        
        System.out.println("Product details entered. Ensuring auto-fill is correct...");
        
        AssertJUnit.assertTrue("Manual Entry Page: Auto-fill brand not as expected in master data",manualEntryPage.getBrand(1).equals(DataItems.brand2SUSST));
        AssertJUnit.assertTrue("Manual Entry Page: Auto-fill ticket not as expected in master data",manualEntryPage.getTicket(1).equals(DataItems.ticket2SUSST));
        AssertJUnit.assertTrue("Manual Entry Page: Auto-fill length not as expected in master data",manualEntryPage.getLength(1).equals(DataItems.length2SUSST));
        AssertJUnit.assertTrue("Manual Entry Page: Auto-fill finish not as expected in master data",manualEntryPage.getFinish(1).equals(DataItems.finish2SUSST));
        AssertJUnit.assertTrue("Manual Entry Page: Auto-fill shade code not as expected in master data",manualEntryPage.getShadeCode(1).equals(DataItems.shade2SUSST));
        
        System.out.println("Auto-fill correct. Pressing next...");
        
        //Press next
        Ecomm_OrderConfirmationPage orderConf = manualEntryPage.pressNext();

        orderConf.waitForElement();

        System.out.println("Order confirmation page reached. Checking details are maintained...");
        
        AssertJUnit.assertTrue("Order Confirmation Page: Customer PO Number not maintained after manual entry page",orderConf.getUploadPONumber().equals(DataItems.lastUsedPO));
        AssertJUnit.assertTrue("Order Confirmation Page: Ship To Party not maintained after manual entry page",orderConf.getShipToParty().equals(DataItems.conOrdDetails[1]));
        AssertJUnit.assertTrue("Order Confirmation Page: Buyers not maintained after manual entry page",orderConf.getBuyers().equals(DataItems.conOrdDetails[3]));
        AssertJUnit.assertTrue("Order Confirmation Page: Your Material Number not maintained after manual entry page",orderConf.getYourMatNum().equals(DataItems.yourMatNumSUSST));
        
        String[] parts = orderConf.getCoatsMaterial().split("-");
        System.out.println(parts[1]);
        AssertJUnit.assertTrue("Order Confirmation Page: Shade Code (from Coats Mat Num) not maintained after manual entry page",parts[1].equals(DataItems.shadeSUSST));
        
        AssertJUnit.assertTrue("Order Confirmation Page: Ordered Quantity not maintained after manual entry page",orderConf.getOrderedQty()==DataItems.quantity);
        AssertJUnit.assertTrue("Order Confirmation Page: Required Date not maintained after manual entry page",orderConf.getRequiredDate().equals(date));
        
        //Press Submit
        Ecomm_OutstandingOrdersPage outOrders = orderConf.pressSubmit(); 
        outOrders.waitForElement();

        System.out.println("Order submitted. Viewing order...");
        
        //Verify values in outstanding orders tab
        //Get the row number of the order in the table and press view
        int rowNumber = outOrders.getRow(DataItems.lastUsedPO);
        Ecomm_OrderViewPage orderView = outOrders.pressView(rowNumber);       
        //wait for overlay to load
        orderView.waitForContent();
        orderView.switchTo();
        
        System.out.println("Order view displayed. Verifying values...");
        
        Verify.verify(orderView.getYourMatNumCell().getText().equals(DataItems.yourMatNumSUSST),"Order view: Your Material Number does not match input");
        Verify.verify(orderView.getArticleCell().getText().equals(DataItems.articleSUSST),"Order view: Article does not match expected input");
        Verify.verify(orderView.getBrandCell().getText().equals(DataItems.brandSUSST),"Order view: Brand does not match expected input");
        Verify.verify(orderView.getTicketCell().getText().equals(DataItems.ticketSUSST),"Order view: Ticket does not match expected input");
        Verify.verify(orderView.getShadeCodeCell().getText().equals(DataItems.shadeSUSST),"Order view: Shade code does not match expected input");

        System.out.println("Values verified. Closing view...");
        
        //Exit view
        orderView.exitView();
        orderView.waitForInvisibility();
        driver.switchTo().defaultContent();
        
        System.out.println("View closed.");
        
        //Output order number for test reference
        String orderNumber = outOrders.getOrderNumber(rowNumber);
        System.out.println("Order Number: " + orderNumber);
    }
    
    @Test //Manual Entry Page :: SUSST :: Single line order using YMN without master shade
    (groups = {"eComm","eComm_Orders"})
    public void SUSST3() throws Exception {
        //New chrome driver
        WebDriver driver = getDriver();

        //Login as global admin to setup master data
        Cce_Base baseSetupMaster = new Cce_Base(driver);
        baseSetupMaster.setUp("Set Master", "Data");

        //Set Master Data
        System.out.println("Set Master Data...");
        PreFlows pf = new PreFlows();
        pf.deActivateCallOffOrderForCustomer(driver, DataItems.lifeEasyCustomer);
        pf.disableMOQForCustomer(driver, DataItems.lifeEasyCustomer);
        pf.chooseTheOtherProfile(driver);
        pf.logoutAction(driver);

        //new base test to handle set up
        Ecomm_Base base = new Ecomm_Base(driver);
        //Set up returns a manual entry page to begin data entry
        Ecomm_MainPage eCommPage = base.setUp("MANUAL ENTRY SUSST3: Single line, Your Material Number without master data shade code","G_OOC_ME_SUSST_3",DataItems.validCustUsername,DataItems.validCustPassword);

        System.out.println("Navigating to Manual Entry...");

        //press manual entry
        Ecomm_ManualEntryPage manualEntryPage = eCommPage.clickManualEntry();
        manualEntryPage.waitForElement();

        System.out.println("Manual Entry Page reached. Entering customer details...");

        //Input Customer Details    
        manualEntryPage.setShipToPartyWithWait(DataItems.conOrdDetails[1]);
        manualEntryPage.setBuyers(DataItems.conOrdDetails[3]);
        manualEntryPage.setPONumber(DataItems.conOrdDetails[4]);

        System.out.println("Customer details entered. Entering product details...");

        int numberOfLines = 1;

        //Order details to be entered
        String[][] details = {
            //line 1 details
            {DataItems.yourMatNumSUSST,DataItems.shadeCode,String.valueOf(DataItems.quantity)},
        };

        //Input details for each line
        for (int i = 0; i < numberOfLines; i++) {
            manualEntryPage = manualEntryPage.setOrderDetailsYMNShadeCode(details[i], i);
        }
        
        String date = manualEntryPage.getDate(0);
        
        System.out.println("Product details entered. Ensuring auto-fill is correct...");
        
        AssertJUnit.assertTrue("Manual Entry Page: Auto-fill brand not as expected in master data",manualEntryPage.getBrand(0).equals(DataItems.brand2SUSST));
        AssertJUnit.assertTrue("Manual Entry Page: Auto-fill ticket not as expected in master data",manualEntryPage.getTicket(0).equals(DataItems.ticket2SUSST));
        AssertJUnit.assertTrue("Manual Entry Page: Auto-fill length not as expected in master data",manualEntryPage.getLength(0).equals(DataItems.length2SUSST));
        AssertJUnit.assertTrue("Manual Entry Page: Auto-fill finish not as expected in master data",manualEntryPage.getFinish(0).equals(DataItems.finish2SUSST));
        AssertJUnit.assertTrue("Manual Entry Page: Shade code not as expected after being changed from material Master shade",manualEntryPage.getShadeCode(0).equals(DataItems.shadeCode));
        
        System.out.println("Auto-fill correct. Pressing next...");
        
        //Press next
        Ecomm_OrderConfirmationPage orderConf = manualEntryPage.pressNext();

        orderConf.waitForElement();

        System.out.println("Order confirmation page reached. Checking details are maintained...");
        
        AssertJUnit.assertTrue("Order Confirmation Page: Customer PO Number not maintained after manual entry page",orderConf.getUploadPONumber().equals(DataItems.lastUsedPO));
        AssertJUnit.assertTrue("Order Confirmation Page: Ship To Party not maintained after manual entry page",orderConf.getShipToParty().equals(DataItems.conOrdDetails[1]));
        AssertJUnit.assertTrue("Order Confirmation Page: Buyers not maintained after manual entry page",orderConf.getBuyers().equals(DataItems.conOrdDetails[3]));
        AssertJUnit.assertTrue("Order Confirmation Page: Your Material Number not maintained after manual entry page",orderConf.getYourMatNum().equals(DataItems.yourMatNumSUSST));
        
        String[] parts = orderConf.getCoatsMaterial().split("-");
        System.out.println(parts[1]);
        AssertJUnit.assertTrue("Order Confirmation Page: Shade Code (from Coats Mat Num) not maintained after manual entry page",parts[1].equals(DataItems.shadeCode));
        
        AssertJUnit.assertTrue("Order Confirmation Page: Ordered Quantity not maintained after manual entry page",orderConf.getOrderedQty()==DataItems.quantity);
        AssertJUnit.assertTrue("Order Confirmation Page: Required Date not maintained after manual entry page",orderConf.getRequiredDate().equals(date));
        
        //Press Submit
        Ecomm_OutstandingOrdersPage outOrders = orderConf.pressSubmit(); 
        outOrders.waitForElement();

        System.out.println("Order submitted. Viewing order...");
        
        //Verify values in outstanding orders tab
        //Get the row number of the order in the table and press view
        int rowNumber = outOrders.getRow(DataItems.lastUsedPO);
        Ecomm_OrderViewPage orderView = outOrders.pressView(rowNumber);       
        //wait for overlay to load
        orderView.waitForContent();
        orderView.switchTo();
        
        System.out.println("Order view displayed. Verifying values...");
        
        Verify.verify(orderView.getYourMatNumCell().getText().equals(DataItems.yourMatNumSUSST),"Order view: Your Material Number does not match input");
        Verify.verify(orderView.getArticleCell().getText().equals(DataItems.articleSUSST),"Order view: Article does not match expected input");
        Verify.verify(orderView.getBrandCell().getText().equals(DataItems.brandSUSST),"Order view: Brand does not match expected input");
        Verify.verify(orderView.getTicketCell().getText().equals(DataItems.ticketSUSST),"Order view: Ticket does not match expected input");
        Verify.verify(orderView.getShadeCodeCell().getText().equals(DataItems.shadeCode),"Order view: Shade code does not match expected input");

        System.out.println("Values verified. Closing view...");
        
        //Exit view
        orderView.exitView();
        orderView.waitForInvisibility();
        driver.switchTo().defaultContent();
        
        System.out.println("View closed.");
        
        //Output order number for test reference
        String orderNumber = outOrders.getOrderNumber(rowNumber);
        System.out.println("Order Number: " + orderNumber);
    }
    
    @Test //Manual Entry Page :: SUSST :: Multi-line order using YMN without master shade
    (groups = {"eComm","eComm_Orders"})
    public void SUSST4() throws Exception {
        //New chrome driver
        WebDriver driver = getDriver();

        //Login as global admin to setup master data
        Cce_Base baseSetupMaster = new Cce_Base(driver);
        baseSetupMaster.setUp("Set Master", "Data");

        //Set Master Data
        System.out.println("Set Master Data...");
        PreFlows pf = new PreFlows();
        pf.deActivateCallOffOrderForCustomer(driver, DataItems.lifeEasyCustomer);
        pf.disableMOQForCustomer(driver, DataItems.lifeEasyCustomer);
        pf.chooseTheOtherProfile(driver);
        pf.logoutAction(driver);

        //new base test to handle set up
        Ecomm_Base base = new Ecomm_Base(driver);
        //Set up returns a manual entry page to begin data entry
        Ecomm_MainPage eCommPage = base.setUp("MANUAL ENTRY SUSST4: Multi-line, Your Material Number without master data shade code","G_OOC_ME_SUSST_4",DataItems.validCustUsername,DataItems.validCustPassword);

        System.out.println("Navigating to Manual Entry...");

        //press manual entry
        Ecomm_ManualEntryPage manualEntryPage = eCommPage.clickManualEntry();
        manualEntryPage.waitForElement();

        System.out.println("Manual Entry Page reached. Entering customer details...");

        //Input Customer Details    
        manualEntryPage.setShipToPartyWithWait(DataItems.conOrdDetails[1]);
        manualEntryPage.setBuyers(DataItems.conOrdDetails[3]);
        manualEntryPage.setPONumber(DataItems.conOrdDetails[4]);

        System.out.println("Customer details entered. Entering product details...");

        int numberOfLines = 2;

        //Order details to be entered
        String[][] details = {
            //line 1 details
            {DataItems.yourMatNumSUSST,DataItems.shadeCode,String.valueOf(DataItems.quantity)},
            //line 2 details
            {DataItems.yourMatNum2SUSST,DataItems.shadeCode2,String.valueOf(DataItems.quantity)}
        };

        //Input details for each line
        for (int i = 0; i < numberOfLines; i++) {
            manualEntryPage = manualEntryPage.setOrderDetailsYMNShadeCode(details[i], i);
        }
        
        String date = manualEntryPage.getDate(0);
        
        System.out.println("Product details entered. Ensuring auto-fill is correct...");
        
        AssertJUnit.assertTrue("Manual Entry Page: Auto-fill brand not as expected in master data",manualEntryPage.getBrand(1).equals(DataItems.brand2SUSST));
        AssertJUnit.assertTrue("Manual Entry Page: Auto-fill ticket not as expected in master data",manualEntryPage.getTicket(1).equals(DataItems.ticket2SUSST));
        AssertJUnit.assertTrue("Manual Entry Page: Auto-fill length not as expected in master data",manualEntryPage.getLength(1).equals(DataItems.length2SUSST));
        AssertJUnit.assertTrue("Manual Entry Page: Auto-fill finish not as expected in master data",manualEntryPage.getFinish(1).equals(DataItems.finish2SUSST));
        AssertJUnit.assertTrue("Manual Entry Page: Auto-fill shade code not as expected in master data",manualEntryPage.getShadeCode(1).equals(DataItems.shadeCode2));
        
        System.out.println("Auto-fill correct. Pressing next...");
        
        //Press next
        Ecomm_OrderConfirmationPage orderConf = manualEntryPage.pressNext();

        orderConf.waitForElement();

        System.out.println("Order confirmation page reached. Checking details are maintained...");
        
        AssertJUnit.assertTrue("Order Confirmation Page: Customer PO Number not maintained after manual entry page",orderConf.getUploadPONumber().equals(DataItems.lastUsedPO));
        AssertJUnit.assertTrue("Order Confirmation Page: Ship To Party not maintained after manual entry page",orderConf.getShipToParty().equals(DataItems.conOrdDetails[1]));
        AssertJUnit.assertTrue("Order Confirmation Page: Buyers not maintained after manual entry page",orderConf.getBuyers().equals(DataItems.conOrdDetails[3]));
        AssertJUnit.assertTrue("Order Confirmation Page: Your Material Number not maintained after manual entry page",orderConf.getYourMatNum().equals(DataItems.yourMatNumSUSST));
        
        String[] parts = orderConf.getCoatsMaterial().split("-");
        System.out.println(parts[1]);
        AssertJUnit.assertTrue("Order Confirmation Page: Shade Code (from Coats Mat Num) not maintained after manual entry page",parts[1].equals(DataItems.shadeCode));
        
        AssertJUnit.assertTrue("Order Confirmation Page: Ordered Quantity not maintained after manual entry page",orderConf.getOrderedQty()==DataItems.quantity);
        AssertJUnit.assertTrue("Order Confirmation Page: Required Date not maintained after manual entry page",orderConf.getRequiredDate().equals(date));
        
        //Press Submit
        Ecomm_OutstandingOrdersPage outOrders = orderConf.pressSubmit(); 
        outOrders.waitForElement();

        System.out.println("Order submitted. Viewing order...");
        
        //Verify values in outstanding orders tab
        //Get the row number of the order in the table and press view
        int rowNumber = outOrders.getRow(DataItems.lastUsedPO);
        Ecomm_OrderViewPage orderView = outOrders.pressView(rowNumber);       
        //wait for overlay to load
        orderView.waitForContent();
        orderView.switchTo();
        
        System.out.println("Order view displayed. Verifying values...");
        
        Verify.verify(orderView.getYourMatNumCell().getText().equals(DataItems.yourMatNumSUSST),"Order view: Your Material Number does not match input");
        Verify.verify(orderView.getArticleCell().getText().equals(DataItems.articleSUSST),"Order view: Article does not match expected input");
        Verify.verify(orderView.getBrandCell().getText().equals(DataItems.brandSUSST),"Order view: Brand does not match expected input");
        Verify.verify(orderView.getTicketCell().getText().equals(DataItems.ticketSUSST),"Order view: Ticket does not match expected input");
        Verify.verify(orderView.getShadeCodeCell().getText().equals(DataItems.shadeCode),"Order view: Shade code does not match expected input");

        System.out.println("Values verified. Closing view...");
        
        //Exit view
        orderView.exitView();
        orderView.waitForInvisibility();
        driver.switchTo().defaultContent();
        
        System.out.println("View closed.");
        
        //Output order number for test reference
        String orderNumber = outOrders.getOrderNumber(rowNumber);
        System.out.println("Order Number: " + orderNumber);
    }
    
    @Test //Manual Entry Page :: SUSST :: Single line order using Article and shade code
    (groups = {"eComm","eComm_Orders"})
    public void SUSST5() throws Exception {
        //New chrome driver
        WebDriver driver = getDriver();

        //Login as global admin to setup master data
        Cce_Base baseSetupMaster = new Cce_Base(driver);
        baseSetupMaster.setUp("Set Master", "Data");

        //Set Master Data
        System.out.println("Set Master Data...");
        PreFlows pf = new PreFlows();
        pf.deActivateCallOffOrderForCustomer(driver, DataItems.lifeEasyCustomer);
        pf.disableMOQForCustomer(driver, DataItems.lifeEasyCustomer);
        pf.chooseTheOtherProfile(driver);
        pf.logoutAction(driver);

        //new base test to handle set up
        Ecomm_Base base = new Ecomm_Base(driver);
        //Set up returns a manual entry page to begin data entry
        Ecomm_MainPage eCommPage = base.setUp("MANUAL ENTRY SUSST5: Single line, Article and shade code","G_OOC_ME_SUSST_5",DataItems.validCustUsername,DataItems.validCustPassword);

        System.out.println("Navigating to Manual Entry...");

        //press manual entry
        Ecomm_ManualEntryPage manualEntryPage = eCommPage.clickManualEntry();
        manualEntryPage.waitForElement();

        System.out.println("Manual Entry Page reached. Entering customer details...");

        //Input Customer Details    
        manualEntryPage.setShipToPartyWithWait(DataItems.conOrdDetails[1]);
        manualEntryPage.setBuyers(DataItems.conOrdDetails[3]);
        manualEntryPage.setPONumber(DataItems.conOrdDetails[4]);

        System.out.println("Customer details entered. Entering product details...");

        int numberOfLines = 1;

        //Order details to be entered
        String[][] details = {
            //line 1 details
            {DataItems.conOrdArticle,DataItems.conOrdShadeCode,String.valueOf(DataItems.quantity)},
        };

        //Input details for each line
        for (int i = 0; i < numberOfLines; i++) {
            manualEntryPage = manualEntryPage.setOrderDetailsArticle(details[i], i);
        }
        
        String date = manualEntryPage.getDate(0);
        
        System.out.println("Product details entered. Ensuring auto-fill is correct...");
        
        AssertJUnit.assertTrue("Manual Entry Page: Auto-fill brand not as expected in master data",manualEntryPage.getSetBrand(0).equals(DataItems.conOrdBrand));
        AssertJUnit.assertTrue("Manual Entry Page: Auto-fill ticket not as expected in master data",manualEntryPage.getSetTicket(0).equals(DataItems.conOrdTicket));
        AssertJUnit.assertTrue("Manual Entry Page: Auto-fill length not as expected in master data",manualEntryPage.getSetLength(0).equals(DataItems.conOrdLength));
        AssertJUnit.assertTrue("Manual Entry Page: Auto-fill finish not as expected in master data",manualEntryPage.getSetFinish(0).equals(DataItems.conOrdFinish));
        AssertJUnit.assertTrue("Manual Entry Page: Shade code not as expected after being changed from material Master shade",manualEntryPage.getShadeCode(0).equals(DataItems.conOrdShadeCode));
        
        System.out.println("Auto-fill correct. Pressing next...");
        
        //Press next
        Ecomm_OrderConfirmationPage orderConf = manualEntryPage.pressNext();

        orderConf.waitForElement();

        System.out.println("Order confirmation page reached. Checking details are maintained...");
        
        AssertJUnit.assertTrue("Order Confirmation Page: Customer PO Number not maintained after manual entry page",orderConf.getUploadPONumber().equals(DataItems.lastUsedPO));
        AssertJUnit.assertTrue("Order Confirmation Page: Ship To Party not maintained after manual entry page",orderConf.getShipToParty().equals(DataItems.conOrdDetails[1]));
        AssertJUnit.assertTrue("Order Confirmation Page: Buyers not maintained after manual entry page",orderConf.getBuyers().equals(DataItems.conOrdDetails[3]));

        String[] parts = orderConf.getCoatsMaterial().split("-");
        System.out.println(parts[1]);
        AssertJUnit.assertTrue("Order Confirmation Page: Shade Code (from Coats Mat Num) not maintained after manual entry page",parts[1].equals(DataItems.conOrdShadeCode));
        
        AssertJUnit.assertTrue("Order Confirmation Page: Ordered Quantity not maintained after manual entry page",orderConf.getOrderedQty()==DataItems.quantity);
        AssertJUnit.assertTrue("Order Confirmation Page: Required Date not maintained after manual entry page",orderConf.getRequiredDate().equals(date));
        
        //Press Submit
        Ecomm_OutstandingOrdersPage outOrders = orderConf.pressSubmit(); 
        outOrders.waitForElement();

        System.out.println("Order submitted. Viewing order...");
        
        //Verify values in outstanding orders tab
        //Get the row number of the order in the table and press view
        int rowNumber = outOrders.getRow(DataItems.lastUsedPO);
        Ecomm_OrderViewPage orderView = outOrders.pressView(rowNumber);       
        //wait for overlay to load
        orderView.waitForContent();
        orderView.switchTo();
        
        System.out.println("Order view displayed. Verifying values...");

        Verify.verify(orderView.getArticleCell().getText().equals(DataItems.conOrdArticle),"Order view: Article does not match expected input");
        Verify.verify(orderView.getBrandCell().getText().equals(DataItems.conOrdBrand),"Order view: Brand does not match expected input");
        Verify.verify(orderView.getTicketCell().getText().equals(DataItems.conOrdTicket),"Order view: Ticket does not match expected input");
        Verify.verify(orderView.getShadeCodeCell().getText().equals(DataItems.conOrdShadeCode),"Order view: Shade code does not match expected input");

        System.out.println("Values verified. Closing view...");
        
        //Exit view
        orderView.exitView();
        orderView.waitForInvisibility();
        driver.switchTo().defaultContent();
        
        System.out.println("View closed.");
        
        //Output order number for test reference
        String orderNumber = outOrders.getOrderNumber(rowNumber);
        System.out.println("Order Number: " + orderNumber);
    }
    
    @Test //Manual Entry Page :: SUSST :: Multi-line order using Article and shade code
    (groups = {"eComm","eComm_Orders"})
    public void SUSST6() throws Exception {
        //New chrome driver
        WebDriver driver = getDriver();

        //Login as global admin to setup master data
        Cce_Base baseSetupMaster = new Cce_Base(driver);
        baseSetupMaster.setUp("Set Master", "Data");

        //Set Master Data
        System.out.println("Set Master Data...");
        PreFlows pf = new PreFlows();
        pf.deActivateCallOffOrderForCustomer(driver, DataItems.lifeEasyCustomer);
        pf.disableMOQForCustomer(driver, DataItems.lifeEasyCustomer);
        pf.chooseTheOtherProfile(driver);
        pf.logoutAction(driver);

        //new base test to handle set up
        Ecomm_Base base = new Ecomm_Base(driver);
        //Set up returns a manual entry page to begin data entry
        Ecomm_MainPage eCommPage = base.setUp("MANUAL ENTRY SUSST6: Multiple line, Article and shade code","G_OOC_ME_SUSST_6",DataItems.validCustUsername,DataItems.validCustPassword);

        System.out.println("Navigating to Manual Entry...");

        //press manual entry
        Ecomm_ManualEntryPage manualEntryPage = eCommPage.clickManualEntry();
        manualEntryPage.waitForElement();

        System.out.println("Manual Entry Page reached. Entering customer details...");

        //Input Customer Details    
        manualEntryPage.setShipToPartyWithWait(DataItems.conOrdDetails[1]);
        manualEntryPage.setBuyers(DataItems.conOrdDetails[3]);
        manualEntryPage.setPONumber(DataItems.conOrdDetails[4]);

        System.out.println("Customer details entered. Entering product details...");

        int numberOfLines = 2;

        //Order details to be entered
        String[][] details = {
            //line 1 details
            {DataItems.conOrdArticle,DataItems.conOrdShadeCode,String.valueOf(DataItems.quantity)},
            //line 2 details
            {DataItems.conOrdArticle,DataItems.shadeCode,String.valueOf(DataItems.quantity)}
        };

        //Input details for each line
        for (int i = 0; i < numberOfLines; i++) {
            manualEntryPage = manualEntryPage.setOrderDetailsArticle(details[i], i);
        }
        
        String date = manualEntryPage.getDate(0);
        
        System.out.println("Product details entered. Ensuring auto-fill is correct...");
        
        AssertJUnit.assertTrue("Manual Entry Page: Auto-fill brand not as expected",manualEntryPage.getSetBrand(1).equals(DataItems.conOrdBrand));
        AssertJUnit.assertTrue("Manual Entry Page: Auto-fill ticket not as expected",manualEntryPage.getSetTicket(1).equals(DataItems.conOrdTicket));
        AssertJUnit.assertTrue("Manual Entry Page: Auto-fill length not as expected",manualEntryPage.getSetLength(1).equals(DataItems.conOrdLength));
        AssertJUnit.assertTrue("Manual Entry Page: Auto-fill finish not as expected",manualEntryPage.getSetFinish(1).equals(DataItems.conOrdFinish));
        AssertJUnit.assertTrue("Manual Entry Page: Shade code not as expected",manualEntryPage.getShadeCode(1).equals(DataItems.shadeCode));
        
        System.out.println("Auto-fill correct. Pressing next...");
        
        //Press next
        Ecomm_OrderConfirmationPage orderConf = manualEntryPage.pressNext();

         

        orderConf.waitForElement();

        System.out.println("Order confirmation page reached. Checking details are maintained...");
        
        AssertJUnit.assertTrue("Order Confirmation Page: Customer PO Number not maintained after manual entry page",orderConf.getUploadPONumber().equals(DataItems.lastUsedPO));
        AssertJUnit.assertTrue("Order Confirmation Page: Ship To Party not maintained after manual entry page",orderConf.getShipToParty().equals(DataItems.conOrdDetails[1]));
        AssertJUnit.assertTrue("Order Confirmation Page: Buyers not maintained after manual entry page",orderConf.getBuyers().equals(DataItems.conOrdDetails[3]));

        String[] parts = orderConf.getCoatsMaterial().split("-");
        System.out.println(parts[1]);
        AssertJUnit.assertTrue("Order Confirmation Page: Shade Code (from Coats Mat Num) not maintained after manual entry page",parts[1].equals(DataItems.conOrdShadeCode));
        
        AssertJUnit.assertTrue("Order Confirmation Page: Ordered Quantity not maintained after manual entry page",orderConf.getOrderedQty()==DataItems.quantity);
        AssertJUnit.assertTrue("Order Confirmation Page: Required Date not maintained after manual entry page",orderConf.getRequiredDate().equals(date));
        
        //Press Submit
        Ecomm_OutstandingOrdersPage outOrders = orderConf.pressSubmit(); 
        outOrders.waitForElement();

        System.out.println("Order submitted. Viewing order...");
        
        //Verify values in outstanding orders tab
        //Get the row number of the order in the table and press view
        int rowNumber = outOrders.getRow(DataItems.lastUsedPO);
        Ecomm_OrderViewPage orderView = outOrders.pressView(rowNumber);       
        //wait for overlay to load
        orderView.waitForContent();
        orderView.switchTo();
        
        System.out.println("Order view displayed. Verifying values...");

        Verify.verify(orderView.getArticleCell().getText().equals(DataItems.conOrdArticle),"Order view: Article does not match expected input");
        Verify.verify(orderView.getBrandCell().getText().equals(DataItems.conOrdBrand),"Order view: Brand does not match expected input");
        Verify.verify(orderView.getTicketCell().getText().equals(DataItems.conOrdTicket),"Order view: Ticket does not match expected input");
        Verify.verify(orderView.getShadeCodeCell().getText().equals(DataItems.conOrdShadeCode),"Order view: Shade code does not match expected input");

        System.out.println("Values verified. Closing view...");
        
        //Exit view
        orderView.exitView();
        orderView.waitForInvisibility();
        driver.switchTo().defaultContent();
        
        System.out.println("View closed.");
        
        //Output order number for test reference
        String orderNumber = outOrders.getOrderNumber(rowNumber);
        System.out.println("Order Number: " + orderNumber);
    }
    
    @Test //Manual Entry Page :: SUSST :: Single line order using Brand/Ticket/Etc combination
    (groups = {"eComm","eComm_Orders"})
    public void SUSST7() throws Exception {
        //New chrome driver
        WebDriver driver = getDriver();

        //Login as global admin to setup master data
        Cce_Base baseSetupMaster = new Cce_Base(driver);
        baseSetupMaster.setUp("Set Master", "Data");

        //Set Master Data
        System.out.println("Set Master Data...");
        PreFlows pf = new PreFlows();
        pf.deActivateCallOffOrderForCustomer(driver, DataItems.lifeEasyCustomer);
        pf.disableMOQForCustomer(driver, DataItems.lifeEasyCustomer);
        pf.chooseTheOtherProfile(driver);
        pf.logoutAction(driver);

        //new base test to handle set up
        Ecomm_Base base = new Ecomm_Base(driver);
        //Set up returns a manual entry page to begin data entry
        Ecomm_MainPage eCommPage = base.setUp("MANUAL ENTRY SUSST7: Single line, using Brand/Ticket/Length/Finish/Shade","G_OOC_ME_SUSST_7",DataItems.validCustUsername,DataItems.validCustPassword);

        System.out.println("Navigating to Manual Entry...");

        //press manual entry
        Ecomm_ManualEntryPage manualEntryPage = eCommPage.clickManualEntry();
        manualEntryPage.waitForElement();

        System.out.println("Manual Entry Page reached. Entering customer details...");

        //Input Customer Details    
        manualEntryPage.setShipToPartyWithWait(DataItems.conOrdDetails[1]);
        manualEntryPage.setBuyers(DataItems.conOrdDetails[3]);
        manualEntryPage.setPONumber(DataItems.conOrdDetails[4]);

        System.out.println("Customer details entered. Entering product details...");

        int numberOfLines = 1;

        //Order details to be entered
        String[][] details = {
            //line 1 details
            {DataItems.conOrdBrand,DataItems.conOrdTicket,DataItems.conOrdLength,DataItems.conOrdFinish,DataItems.conOrdShadeCode,String.valueOf(DataItems.quantity)},
        };

        //Input details for each line
        for (int i = 0; i < numberOfLines; i++) {
            manualEntryPage = manualEntryPage.setOrderDetailsCombination(details[i], i);
        }
        
        String date = manualEntryPage.getDate(0);
        
        System.out.println("Product details entered. Ensuring auto-fill is correct...");
        
        AssertJUnit.assertTrue("Manual Entry Page: Brand not as expected",manualEntryPage.getBrand(0).equals(DataItems.conOrdBrand));
        AssertJUnit.assertTrue("Manual Entry Page: Ticket not as expected",manualEntryPage.getTicket(0).equals(DataItems.conOrdTicket));
        AssertJUnit.assertTrue("Manual Entry Page: Length not as expected",manualEntryPage.getLength(0).equals(DataItems.conOrdLength));
        AssertJUnit.assertTrue("Manual Entry Page: Finish not as expected",manualEntryPage.getFinish(0).equals(DataItems.conOrdFinish));
        AssertJUnit.assertTrue("Manual Entry Page: Shade code not as expected",manualEntryPage.getShadeCode(0).equals(DataItems.conOrdShadeCode));
        
        System.out.println("Auto-fill correct. Pressing next...");
        
        //Press next
        Ecomm_OrderConfirmationPage orderConf = manualEntryPage.pressNext();

         
        orderConf.waitForElement();

        System.out.println("Order confirmation page reached. Checking details are maintained...");
        
        AssertJUnit.assertTrue("Order Confirmation Page: Customer PO Number not maintained after manual entry page",orderConf.getUploadPONumber().equals(DataItems.lastUsedPO));
        AssertJUnit.assertTrue("Order Confirmation Page: Ship To Party not maintained after manual entry page",orderConf.getShipToParty().equals(DataItems.conOrdDetails[1]));
        AssertJUnit.assertTrue("Order Confirmation Page: Buyers not maintained after manual entry page",orderConf.getBuyers().equals(DataItems.conOrdDetails[3]));

        String[] parts = orderConf.getCoatsMaterial().split("-");
        System.out.println(parts[1]);
        AssertJUnit.assertTrue("Order Confirmation Page: Shade Code (from Coats Mat Num) not maintained after manual entry page",parts[1].equals(DataItems.conOrdShadeCode));
        
        AssertJUnit.assertTrue("Order Confirmation Page: Ordered Quantity not maintained after manual entry page",orderConf.getOrderedQty()==DataItems.quantity);
        AssertJUnit.assertTrue("Order Confirmation Page: Required Date not maintained after manual entry page",orderConf.getRequiredDate().equals(date));
        
        //Press Submit
        Ecomm_OutstandingOrdersPage outOrders = orderConf.pressSubmit(); 
        outOrders.waitForElement();

        System.out.println("Order submitted. Viewing order...");
        
        //Verify values in outstanding orders tab
        //Get the row number of the order in the table and press view
        int rowNumber = outOrders.getRow(DataItems.lastUsedPO);
        Ecomm_OrderViewPage orderView = outOrders.pressView(rowNumber);       
        //wait for overlay to load
        orderView.waitForContent();
        orderView.switchTo();
        
        System.out.println("Order view displayed. Verifying values...");

        Verify.verify(orderView.getArticleCell().getText().equals(DataItems.conOrdArticle),"Order view: Article does not match expected input");
        Verify.verify(orderView.getBrandCell().getText().equals(DataItems.conOrdBrand),"Order view: Brand does not match expected input");
        Verify.verify(orderView.getTicketCell().getText().equals(DataItems.conOrdTicket),"Order view: Ticket does not match expected input");
        Verify.verify(orderView.getShadeCodeCell().getText().equals(DataItems.conOrdShadeCode),"Order view: Shade code does not match expected input");

        System.out.println("Values verified. Closing view...");
        
        //Exit view
        orderView.exitView();
        orderView.waitForInvisibility();
        driver.switchTo().defaultContent();
        
        System.out.println("View closed.");
        
        //Output order number for test reference
        String orderNumber = outOrders.getOrderNumber(rowNumber);
        System.out.println("Order Number: " + orderNumber);
    }
    
    @Test //Manual Entry Page :: SUSST :: Multi-line order using Brand/Ticket/Etc combination
    (groups = {"eComm","eComm_Orders"})
    public void SUSST8() throws Exception {
        //New chrome driver
        WebDriver driver = getDriver();

        //Login as global admin to setup master data
        Cce_Base baseSetupMaster = new Cce_Base(driver);
        baseSetupMaster.setUp("Set Master", "Data");

        //Set Master Data
        System.out.println("Set Master Data...");
        PreFlows pf = new PreFlows();
        pf.deActivateCallOffOrderForCustomer(driver, DataItems.lifeEasyCustomer);
        pf.disableMOQForCustomer(driver, DataItems.lifeEasyCustomer);
        pf.chooseTheOtherProfile(driver);
        pf.logoutAction(driver);

        //new base test to handle set up
        Ecomm_Base base = new Ecomm_Base(driver);
        //Set up returns a manual entry page to begin data entry
        Ecomm_MainPage eCommPage = base.setUp("MANUAL ENTRY SUSST8: Multi-line, using Brand/Ticket/Length/Finish/Shade","G_OOC_ME_SUSST_8",DataItems.validCustUsername,DataItems.validCustPassword);

        System.out.println("Navigating to Manual Entry...");

        //press manual entry
        Ecomm_ManualEntryPage manualEntryPage = eCommPage.clickManualEntry();
        manualEntryPage.waitForElement();

        System.out.println("Manual Entry Page reached. Entering customer details...");

        //Input Customer Details    
        manualEntryPage.setShipToPartyWithWait(DataItems.conOrdDetails[1]);
        manualEntryPage.setBuyers(DataItems.conOrdDetails[3]);
        manualEntryPage.setPONumber(DataItems.conOrdDetails[4]);

        System.out.println("Customer details entered. Entering product details...");

        int numberOfLines = 2;

        //Order details to be entered
        String[][] details = {
            //line 1 details
            {DataItems.conOrdBrand,DataItems.conOrdTicket,DataItems.conOrdLength,DataItems.conOrdFinish,DataItems.conOrdShadeCode,String.valueOf(DataItems.quantity)},
            {DataItems.brandSUSST,DataItems.ticketSUSST,DataItems.lengthSUSST,DataItems.finishSUSST,DataItems.shadeSUSST,String.valueOf(DataItems.quantity)}
        };

        //Input details for each line
        for (int i = 0; i < numberOfLines; i++) {
            manualEntryPage = manualEntryPage.setOrderDetailsCombination(details[i], i);
        }
        
        String date = manualEntryPage.getDate(0);
        
        System.out.println("Product details entered. Ensuring details are correct...");
        
        AssertJUnit.assertTrue("Manual Entry Page: Brand not as expected",manualEntryPage.getBrand(1).equals(DataItems.brandSUSST));
        AssertJUnit.assertTrue("Manual Entry Page: Ticket not as expected",manualEntryPage.getTicket(1).equals(DataItems.ticketSUSST));
        AssertJUnit.assertTrue("Manual Entry Page: Length not as expected",manualEntryPage.getLength(1).equals(DataItems.lengthSUSST));
        AssertJUnit.assertTrue("Manual Entry Page: Finish not as expected",manualEntryPage.getFinish(1).equals(DataItems.finishSUSST));
        AssertJUnit.assertTrue("Manual Entry Page: Shade code not as expected",manualEntryPage.getShadeCode(1).equals(DataItems.shadeSUSST));
        
        System.out.println("Auto-fill correct. Pressing next...");
        
        //Press next
        Ecomm_OrderConfirmationPage orderConf = manualEntryPage.pressNext();


         
        orderConf.waitForElement();

        System.out.println("Order confirmation page reached. Checking details are maintained...");
        
        AssertJUnit.assertTrue("Order Confirmation Page: Customer PO Number not maintained after manual entry page",orderConf.getUploadPONumber().equals(DataItems.lastUsedPO));
        AssertJUnit.assertTrue("Order Confirmation Page: Ship To Party not maintained after manual entry page",orderConf.getShipToParty().equals(DataItems.conOrdDetails[1]));
        AssertJUnit.assertTrue("Order Confirmation Page: Buyers not maintained after manual entry page",orderConf.getBuyers().equals(DataItems.conOrdDetails[3]));

        String[] parts = orderConf.getCoatsMaterial().split("-");
        AssertJUnit.assertTrue("Order Confirmation Page: Shade Code (from Coats Mat Num) not maintained after manual entry page",parts[1].equals(DataItems.conOrdShadeCode));
        
        AssertJUnit.assertTrue("Order Confirmation Page: Ordered Quantity not maintained after manual entry page",orderConf.getOrderedQty()==DataItems.quantity);
        AssertJUnit.assertTrue("Order Confirmation Page: Required Date not maintained after manual entry page",orderConf.getRequiredDate().equals(date));
        
        //Press Submit
        Ecomm_OutstandingOrdersPage outOrders = orderConf.pressSubmit(); 
        outOrders.waitForElement();

        System.out.println("Order submitted. Viewing order...");
        
        //Verify values in outstanding orders tab
        //Get the row number of the order in the table and press view
        int rowNumber = outOrders.getRow(DataItems.lastUsedPO);
        Ecomm_OrderViewPage orderView = outOrders.pressView(rowNumber);       
        //wait for overlay to load
        orderView.waitForContent();
        orderView.switchTo();
        
        System.out.println("Order view displayed. Verifying values...");

        Verify.verify(orderView.getBrandCell().getText().equals(DataItems.conOrdBrand),"Order view: Brand does not match expected input");
        Verify.verify(orderView.getTicketCell().getText().equals(DataItems.conOrdTicket),"Order view: Ticket does not match expected input");
        Verify.verify(orderView.getShadeCodeCell().getText().equals(DataItems.conOrdShadeCode),"Order view: Shade code does not match expected input");

        System.out.println("Values verified. Closing view...");
        
        //Exit view
        orderView.exitView();
        orderView.waitForInvisibility();
        driver.switchTo().defaultContent();
        
        System.out.println("View closed.");
        
        //Output order number for test reference
        String orderNumber = outOrders.getOrderNumber(rowNumber);
        System.out.println("Order Number: " + orderNumber);
    }
    
    @Test //Manual Entry Page :: SUSST :: Single-line order using Article and shade code (MOQ ACTIVE)
    (groups = {"eComm","eComm_Orders"})
    public void SUSST9() throws Exception {
        //New chrome driver
        WebDriver driver = getDriver();

        //Login as global admin to setup master data
        Cce_Base baseSetupMaster = new Cce_Base(driver);
        baseSetupMaster.setUp("Set Master", "Data");

        //Set Master Data
        System.out.println("Set Master Data...");
        PreFlows pf = new PreFlows();
        pf.deActivateCallOffOrderForCustomer(driver, DataItems.lifeEasyCustomer);
        pf.enableMOQForCustomer(driver, DataItems.lifeEasyCustomer);
        pf.chooseTheOtherProfile(driver);
        pf.logoutAction(driver);



        //new base test to handle set up
        Ecomm_Base base = new Ecomm_Base(driver);
        //Set up returns a manual entry page to begin data entry
        Ecomm_MainPage eCommPage = base.setUp("MANUAL ENTRY SUSST9: Single line, Article and shade code (MOQ ACTIVE)","G_OOC_ME_SUSST_MOQ_5",DataItems.susstUsername,DataItems.susstPassword);


        System.out.println("Navigating to Manual Entry...");

        //press manual entry
        Ecomm_ManualEntryPage manualEntryPage = eCommPage.clickManualEntry();
        manualEntryPage.waitForElement();

        System.out.println("Manual Entry Page reached. Entering customer details...");

        //Input Customer Details    
        manualEntryPage.setShipToPartyWithWait(DataItems.testCustDetails[1]);
        manualEntryPage.setBuyers(DataItems.testCustDetails[3]);
        manualEntryPage.setPONumber(DataItems.testCustDetails[4]);

        System.out.println("Customer details entered. Entering product details...");

        int numberOfLines = 1;

        //Order details to be entered
        String[][] details = {
            //line 1 details
            {DataItems.MOQArticle,DataItems.expShadeCode,String.valueOf(1)},
        };

        //Input details for each line
        for (int i = 0; i < numberOfLines; i++) {
            manualEntryPage = manualEntryPage.setOrderDetailsArticle(details[i], i);
        }
        
        String date = manualEntryPage.getDate(0);
        
        System.out.println("Product details entered. Ensuring auto-fill is correct...");
        
        AssertJUnit.assertTrue("Manual Entry Page: Auto-fill brand not as expected in master data",manualEntryPage.getSetBrand(0).equals(DataItems.MOQBrand));
        AssertJUnit.assertTrue("Manual Entry Page: Auto-fill ticket not as expected in master data",manualEntryPage.getSetTicket(0).equals(DataItems.MOQTicket));
        AssertJUnit.assertTrue("Manual Entry Page: Auto-fill length not as expected in master data",manualEntryPage.getSetLength(0).equals(DataItems.MOQLength));
        AssertJUnit.assertTrue("Manual Entry Page: Auto-fill finish not as expected in master data",manualEntryPage.getSetFinish(0).equals(DataItems.MOQFinish));
        AssertJUnit.assertTrue("Manual Entry Page: Shade code not as expected after being changed from material Master shade",manualEntryPage.getShadeCode(0).equals(DataItems.expShadeCode));
        
        System.out.println("Auto-fill correct. Pressing next...");
        
        //Press next
        Ecomm_OrderConfirmationPage orderConf = manualEntryPage.pressNextMOQDoubleAlert();
         
        orderConf.waitForElement();
        
        System.out.println("MOQ Alert appeared as expected.");

        System.out.println("Order confirmation page reached. Checking details are maintained...");
        
        AssertJUnit.assertTrue("Order Confirmation Page: Customer PO Number not maintained after manual entry page",orderConf.getUploadPONumber().equals(DataItems.lastUsedPO));
        AssertJUnit.assertTrue("Order Confirmation Page: Ship To Party not maintained after manual entry page",orderConf.getShipToParty().equals(DataItems.testCustDetails[1]));
        AssertJUnit.assertTrue("Order Confirmation Page: Buyers not maintained after manual entry page",orderConf.getBuyers().equals(DataItems.testCustDetails[3]));

        String[] parts = orderConf.getCoatsMaterial().split("-");
        System.out.println(parts[1]);
        AssertJUnit.assertTrue("Order Confirmation Page: Shade Code (from Coats Mat Num) not maintained after manual entry page",parts[1].equals(DataItems.expShadeCode));
        
        AssertJUnit.assertTrue("Order Confirmation Page: Ordered Quantity not maintained after manual entry page",orderConf.getOrderedQty()==1);
        AssertJUnit.assertTrue("Order Confirmation Page: Required Date not maintained after manual entry page",orderConf.getRequiredDate().equals(date));
        
        System.out.println("Details maintained. Check adjusted quantity appears...");
        
        int adjQty = orderConf.getAdjustedQty();
        
        System.out.println("Adjusted quantity retrieved. Checking value...");

        AssertJUnit.assertTrue("Order Confirmation Page: Adjusted Quantity less than or equal to the ordered quantity",adjQty>1);
        
        System.out.println("Value greater than ordered qty, as expected. Submitting order...");
        
        //Press Submit
        Ecomm_OutstandingOrdersPage outOrders = orderConf.pressSubmit(); 
        outOrders.waitForElement();

        System.out.println("Order submitted. Viewing order...");
        
        //Verify values in outstanding orders tab
        //Get the row number of the order in the table and press view
        int rowNumber = outOrders.getRowAlt(DataItems.lastUsedPO);
        Ecomm_OrderViewPage orderView = outOrders.pressView(rowNumber);       
        //wait for overlay to load
        orderView.waitForContent();
        orderView.switchTo();
        
        System.out.println("Order view displayed. Verifying values...");

        Verify.verify(orderView.getArticleCell().getText().equals(DataItems.MOQArticle),"Order view: Article does not match expected input");
        Verify.verify(orderView.getBrandCell().getText().equals(DataItems.MOQBrand),"Order view: Brand does not match expected input");
        Verify.verify(orderView.getTicketCell().getText().equals(DataItems.MOQTicket),"Order view: Ticket does not match expected input");
        Verify.verify(orderView.getShadeCodeCell().getText().equals(DataItems.expShadeCode),"Order view: Shade code does not match expected input");

        System.out.println("Values verified. Closing view...");
        
        //Exit view
        orderView.exitView();
        orderView.waitForInvisibility();
        driver.switchTo().defaultContent();
        
        System.out.println("View closed.");
        
        //Output order number for test reference
        String orderNumber = outOrders.getOrderNumberOffset(rowNumber,1);
        System.out.println("Order Number: " + orderNumber);
    }
    
    @Test //Manual Entry Page :: SUSST :: Multi-line order using Article and shade code (MOQ ACTIVE)
    (groups = {"eComm","eComm_Orders", "QuickTest"})
    public void SUSST10() throws Exception {
        //New chrome driver
        WebDriver driver = getDriver();

        //Login as global admin to setup master data
        Cce_Base baseSetupMaster = new Cce_Base(driver);
        baseSetupMaster.setUp("Set Master", "Data");

        //Set Master Data
        System.out.println("Set Master Data...");
        PreFlows pf = new PreFlows();
        pf.deActivateCallOffOrderForCustomer(driver, DataItems.lifeEasyCustomer);
        pf.enableMOQForCustomer(driver, DataItems.lifeEasyCustomer);
        pf.chooseTheOtherProfile(driver);
        pf.logoutAction(driver);

        //new base test to handle set up
        Ecomm_Base base = new Ecomm_Base(driver);

        //Set up returns a manual entry page to begin data entry
        Ecomm_MainPage eCommPage = base.setUp("MANUAL ENTRY SUSST10: Multi-line, Article and shade code (MOQ ACTIVE)","G_OOC_ME_SUSST_MOQ_6",DataItems.susstUsername,DataItems.susstPassword);

        System.out.println("Navigating to Manual Entry...");

        //press manual entry
        Ecomm_ManualEntryPage manualEntryPage = eCommPage.clickManualEntry();
        manualEntryPage.waitForElement();

        System.out.println("Manual Entry Page reached. Entering customer details...");

        //Input Customer Details    
        manualEntryPage.setShipToPartyWithWait(DataItems.testCustDetails[1]);
        manualEntryPage.setBuyers(DataItems.testCustDetails[3]);
        manualEntryPage.setPONumber(DataItems.testCustDetails[4]);

        System.out.println("Customer details entered. Entering product details...");

        int numberOfLines = 2;

        //Order details to be entered
        String[][] details = {
            //line 1 details
            {DataItems.MOQArticle,DataItems.expShadeCode,String.valueOf(1)},
            {DataItems.article,DataItems.shadeCode,String.valueOf(DataItems.quantity)}
        };

        //Input details for each line
        for (int i = 0; i < numberOfLines; i++) {
            manualEntryPage = manualEntryPage.setOrderDetailsArticle(details[i], i);
        }
        
        String date = manualEntryPage.getDate(0);
        
        System.out.println("Product details entered. Ensuring auto-fill is correct...");

        AssertJUnit.assertTrue("Manual Entry Page: Auto-fill brand not as expected in master data",manualEntryPage.getSetBrand(0).equals(DataItems.MOQBrand));
        AssertJUnit.assertTrue("Manual Entry Page: Auto-fill ticket not as expected in master data",manualEntryPage.getSetTicket(0).equals(DataItems.MOQTicket));
        AssertJUnit.assertTrue("Manual Entry Page: Auto-fill length not as expected in master data",manualEntryPage.getSetLength(0).equals(DataItems.MOQLength));
        AssertJUnit.assertTrue("Manual Entry Page: Auto-fill finish not as expected in master data",manualEntryPage.getSetFinish(0).equals(DataItems.MOQFinish));
        AssertJUnit.assertTrue("Manual Entry Page: Shade code not as expected after being changed from material Master shade",manualEntryPage.getShadeCode(0).equals(DataItems.expShadeCode));

        System.out.println("Auto-fill correct. Pressing next...");
        
        //Press next
        Ecomm_OrderConfirmationPage orderConf = manualEntryPage.pressNextMOQDoubleAlert();

        orderConf.waitForElement();
        
        System.out.println("MOQ Alert appeared as expected.");

        System.out.println("Order confirmation page reached. Checking details are maintained...");
        
        AssertJUnit.assertTrue("Order Confirmation Page: Customer PO Number not maintained after manual entry page",orderConf.getUploadPONumber().equals(DataItems.lastUsedPO));
        AssertJUnit.assertTrue("Order Confirmation Page: Ship To Party not maintained after manual entry page",orderConf.getShipToParty().equals(DataItems.testCustDetails[1]));
        AssertJUnit.assertTrue("Order Confirmation Page: Buyers not maintained after manual entry page",orderConf.getBuyers().equals(DataItems.testCustDetails[3]));

        String[] parts = orderConf.getCoatsMaterial().split("-");
        System.out.println(parts[1]);
        AssertJUnit.assertTrue("Order Confirmation Page: Shade Code (from Coats Mat Num) not maintained after manual entry page",parts[1].equals(DataItems.expShadeCode));
        
        AssertJUnit.assertTrue("Order Confirmation Page: Ordered Quantity not maintained after manual entry page",orderConf.getOrderedQty()==1);
        AssertJUnit.assertTrue("Order Confirmation Page: Required Date not maintained after manual entry page",orderConf.getRequiredDate().equals(date));
        
        System.out.println("Details maintained. Check adjusted quantity appears...");
        
        int adjQty = orderConf.getAdjustedQty();
        
        System.out.println("Adjusted quantity retrieved. Checking value...");

        AssertJUnit.assertTrue("Order Confirmation Page: Adjusted Quantity less than or equal to the ordered quantity",adjQty>1);
        
        System.out.println("Value greater than ordered qty, as expected. Submitting order...");
        
        //Press Submit
        Ecomm_OutstandingOrdersPage outOrders = orderConf.pressSubmit(); 
        outOrders.waitForElement();

        System.out.println("Order submitted. Viewing order...");
        
        //Verify values in outstanding orders tab
        //Get the row number of the order in the table and press view
        int rowNumber = outOrders.getRowAlt(DataItems.lastUsedPO);
        Ecomm_OrderViewPage orderView = outOrders.pressView(rowNumber);       
        //wait for overlay to load
        orderView.waitForContent();
        orderView.switchTo();
        
        System.out.println("Order view displayed. Verifying values...");

        Verify.verify(orderView.getArticleCell().getText().equals(DataItems.MOQArticle),"Order view: Article does not match expected input");
        Verify.verify(orderView.getBrandCell().getText().equals(DataItems.MOQBrand),"Order view: Brand does not match expected input");
        Verify.verify(orderView.getTicketCell().getText().equals(DataItems.MOQTicket),"Order view: Ticket does not match expected input");
        Verify.verify(orderView.getShadeCodeCell().getText().equals(DataItems.expShadeCode),"Order view: Shade code does not match expected input");

        System.out.println("Values verified. Closing view...");
        
        //Exit view
        orderView.exitView();
        orderView.waitForInvisibility();
        driver.switchTo().defaultContent();
        
        System.out.println("View closed.");
        
        //Output order number for test reference
        String orderNumber = outOrders.getOrderNumber(rowNumber);
        System.out.println("Order Number: " + orderNumber);
    }
    
    @Test //Manual Entry Page :: SUSST :: Single-line order using Brand/Ticket/Length/Finish/Shade (MOQ ACTIVE)
    (groups = {"eComm","eComm_Orders"})
    public void SUSST11() throws Exception {
        //New chrome driver
        WebDriver driver = getDriver();

        //Login as global admin to setup master data
        Cce_Base baseSetupMaster = new Cce_Base(driver);
        baseSetupMaster.setUp("Set Master", "Data");

        //Set Master Data
        System.out.println("Set Master Data...");
        PreFlows pf = new PreFlows();
        pf.deActivateCallOffOrderForCustomer(driver, DataItems.lifeEasyCustomer);
        pf.enableMOQForCustomer(driver, DataItems.lifeEasyCustomer);
        pf.chooseTheOtherProfile(driver);
        pf.logoutAction(driver);

        //new base test to handle set up
        Ecomm_Base base = new Ecomm_Base(driver);
        //Set up returns a manual entry page to begin data entry
        Ecomm_MainPage eCommPage = base.setUp("MANUAL ENTRY SUSST11: Single line, Brand/Ticket/Length/Finish/Shade combination (MOQ ACTIVE)","G_OOC_ME_SUSST_MOQ_7",DataItems.susstUsername,DataItems.susstPassword);

        System.out.println("Navigating to Manual Entry...");

        //press manual entry
        Ecomm_ManualEntryPage manualEntryPage = eCommPage.clickManualEntry();
        manualEntryPage.waitForElement();

        System.out.println("Manual Entry Page reached. Entering customer details...");

        //Input Customer Details    
        manualEntryPage.setShipToPartyWithWait(DataItems.testCustDetails[1]);
        manualEntryPage.setBuyers(DataItems.testCustDetails[3]);
        manualEntryPage.setPONumber(DataItems.testCustDetails[4]);

        System.out.println("Customer details entered. Entering product details...");

        int numberOfLines = 1;

        //Order details to be entered
        String[][] details = {
            //line 1 details
            {DataItems.MOQBrand,DataItems.MOQTicket,DataItems.MOQLength,DataItems.MOQFinish,DataItems.expShadeCode,String.valueOf(1)},
        };

        //Input details for each line
        for (int i = 0; i < numberOfLines; i++) {
            manualEntryPage = manualEntryPage.setOrderDetailsCombination(details[i], i);
        }
        
        String date = manualEntryPage.getDate(0);
        
        System.out.println("Product details entered. Ensuring auto-fill is correct...");
        
        AssertJUnit.assertTrue("Manual Entry Page: Auto-fill brand not as expected in master data",manualEntryPage.getBrand(0).equals(DataItems.MOQBrand));
        AssertJUnit.assertTrue("Manual Entry Page: Auto-fill ticket not as expected in master data",manualEntryPage.getTicket(0).equals(DataItems.MOQTicket));
        AssertJUnit.assertTrue("Manual Entry Page: Auto-fill length not as expected in master data",manualEntryPage.getLength(0).equals(DataItems.MOQLength));
        AssertJUnit.assertTrue("Manual Entry Page: Auto-fill finish not as expected in master data",manualEntryPage.getFinish(0).equals(DataItems.MOQFinish));
        AssertJUnit.assertTrue("Manual Entry Page: Shade code not as expected after being changed from material Master shade",manualEntryPage.getShadeCode(0).equals(DataItems.expShadeCode));
        
        System.out.println("Auto-fill correct. Pressing next...");
        
        //Press next
        Ecomm_OrderConfirmationPage orderConf = manualEntryPage.pressNextMOQDoubleAlert();

        orderConf.waitForElement();
        
        System.out.println("MOQ Alert appeared as expected.");

        System.out.println("Order confirmation page reached. Checking details are maintained...");
        
        AssertJUnit.assertTrue("Order Confirmation Page: Customer PO Number not maintained after manual entry page",orderConf.getUploadPONumber().equals(DataItems.lastUsedPO));
        AssertJUnit.assertTrue("Order Confirmation Page: Ship To Party not maintained after manual entry page",orderConf.getShipToParty().equals(DataItems.testCustDetails[1]));
        AssertJUnit.assertTrue("Order Confirmation Page: Buyers not maintained after manual entry page",orderConf.getBuyers().equals(DataItems.testCustDetails[3]));

        String[] parts = orderConf.getCoatsMaterial().split("-");
        System.out.println(parts[1]);
        AssertJUnit.assertTrue("Order Confirmation Page: Shade Code (from Coats Mat Num) not maintained after manual entry page",parts[1].equals(DataItems.expShadeCode));
        
        AssertJUnit.assertTrue("Order Confirmation Page: Ordered Quantity not maintained after manual entry page",orderConf.getOrderedQty()==1);
        AssertJUnit.assertTrue("Order Confirmation Page: Required Date not maintained after manual entry page",orderConf.getRequiredDate().equals(date));
        
        System.out.println("Details maintained. Check adjusted quantity appears...");
        
        int adjQty = orderConf.getAdjustedQty();
        
        System.out.println("Adjusted quantity retrieved. Checking value...");

        AssertJUnit.assertTrue("Order Confirmation Page: Adjusted Quantity less than or equal to the ordered quantity",adjQty>1);
        
        System.out.println("Value greater than ordered qty, as expected. Submitting order...");
        
        //Press Submit
        Ecomm_OutstandingOrdersPage outOrders = orderConf.pressSubmit(); 
        outOrders.waitForElement();

        System.out.println("Order submitted. Viewing order...");
        
        //Verify values in outstanding orders tab
        //Get the row number of the order in the table and press view
        int rowNumber = outOrders.getRowAlt(DataItems.lastUsedPO);
        Ecomm_OrderViewPage orderView = outOrders.pressView(rowNumber);       
        //wait for overlay to load
        orderView.waitForContent();
        orderView.switchTo();
        
        System.out.println("Order view displayed. Verifying values...");

        Verify.verify(orderView.getArticleCell().getText().equals(DataItems.MOQArticle),"Order view: Article does not match expected input");
        Verify.verify(orderView.getBrandCell().getText().equals(DataItems.MOQBrand),"Order view: Brand does not match expected input");
        Verify.verify(orderView.getTicketCell().getText().equals(DataItems.MOQTicket),"Order view: Ticket does not match expected input");
        Verify.verify(orderView.getShadeCodeCell().getText().equals(DataItems.expShadeCode),"Order view: Shade code does not match expected input");

        System.out.println("Values verified. Closing view...");
        
        //Exit view
        orderView.exitView();
        orderView.waitForInvisibility();
        driver.switchTo().defaultContent();
        
        System.out.println("View closed.");
        
        //Output order number for test reference
        String orderNumber = outOrders.getOrderNumber(rowNumber);
        System.out.println("Order Number: " + orderNumber);
    }
    
    @Test //Manual Entry Page :: SUSST :: Multi-line order using Brand/Ticket/Length/Finish/Shade (MOQ ACTIVE)
    (groups = {"eComm","eComm_Orders"})
    public void SUSST12() throws Exception {
        //New chrome driver
        WebDriver driver = getDriver();

        //Login as global admin to setup master data
        Cce_Base baseSetupMaster = new Cce_Base(driver);
        baseSetupMaster.setUp("Set Master", "Data");

        //Set Master Data
        System.out.println("Set Master Data...");
        PreFlows pf = new PreFlows();
        pf.deActivateCallOffOrderForCustomer(driver, DataItems.lifeEasyCustomer);
        pf.enableMOQForCustomer(driver, DataItems.lifeEasyCustomer);
        pf.chooseTheOtherProfile(driver);
        pf.logoutAction(driver);

        //new base test to handle set up
        Ecomm_Base base = new Ecomm_Base(driver);
        //Set up returns a manual entry page to begin data entry
        Ecomm_MainPage eCommPage = base.setUp("MANUAL ENTRY SUSST12: Multiple line, Brand/Ticket/Length/Finish/Shade combination (MOQ ACTIVE)","G_OOC_ME_SUSST_MOQ_8",DataItems.susstUsername,DataItems.susstPassword);

        System.out.println("Navigating to Manual Entry...");

        //press manual entry
        Ecomm_ManualEntryPage manualEntryPage = eCommPage.clickManualEntry();
        manualEntryPage.waitForElement();

        System.out.println("Manual Entry Page reached. Entering customer details...");

        //Input Customer Details    
        manualEntryPage.setShipToPartyWithWait(DataItems.testCustDetails[1]);
        manualEntryPage.setBuyers(DataItems.testCustDetails[3]);
        manualEntryPage.setPONumber(DataItems.testCustDetails[4]);

        System.out.println("Customer details entered. Entering product details...");

        int numberOfLines = 2;

        //Order details to be entered
        String[][] details = {
            //line 1 details
            {DataItems.MOQBrand,DataItems.MOQTicket,DataItems.MOQLength,DataItems.MOQFinish,DataItems.expShadeCode,String.valueOf(1)},
            {DataItems.MOQBrand,DataItems.ticket3,DataItems.MOQLength,DataItems.MOQFinish,DataItems.shadeCode,String.valueOf(2)}
        };

        //Input details for each line
        for (int i = 0; i < numberOfLines; i++) {
            manualEntryPage = manualEntryPage.setOrderDetailsCombination(details[i], i);
        }
        
        String date = manualEntryPage.getDate(0);
        
        System.out.println("Product details entered. Ensuring auto-fill is correct...");
        
        AssertJUnit.assertTrue("Manual Entry Page: Auto-fill brand not as expected in master data",manualEntryPage.getBrand(0).equals(DataItems.MOQBrand));
        AssertJUnit.assertTrue("Manual Entry Page: Auto-fill ticket not as expected in master data",manualEntryPage.getTicket(0).equals(DataItems.MOQTicket));
        AssertJUnit.assertTrue("Manual Entry Page: Auto-fill length not as expected in master data",manualEntryPage.getLength(0).equals(DataItems.MOQLength));
        AssertJUnit.assertTrue("Manual Entry Page: Auto-fill finish not as expected in master data",manualEntryPage.getFinish(0).equals(DataItems.MOQFinish));
        AssertJUnit.assertTrue("Manual Entry Page: Shade code not as expected after being changed from material Master shade",manualEntryPage.getShadeCode(0).equals(DataItems.expShadeCode));
        
        System.out.println("Auto-fill correct. Pressing next...");
        
        //Press next
        Ecomm_OrderConfirmationPage orderConf = manualEntryPage.pressNextMOQDoubleAlert();

        orderConf.waitForElement();
        
        System.out.println("MOQ Alert appeared as expected.");

        System.out.println("Order confirmation page reached. Checking details are maintained...");
        
        AssertJUnit.assertTrue("Order Confirmation Page: Customer PO Number not maintained after manual entry page",orderConf.getUploadPONumber().equals(DataItems.lastUsedPO));
        AssertJUnit.assertTrue("Order Confirmation Page: Ship To Party not maintained after manual entry page",orderConf.getShipToParty().equals(DataItems.testCustDetails[1]));
        AssertJUnit.assertTrue("Order Confirmation Page: Buyers not maintained after manual entry page",orderConf.getBuyers().equals(DataItems.testCustDetails[3]));

        String[] parts = orderConf.getCoatsMaterial().split("-");
        System.out.println(parts[1]);
        AssertJUnit.assertTrue("Order Confirmation Page: Shade Code (from Coats Mat Num) not maintained after manual entry page",parts[1].equals(DataItems.expShadeCode));
        
        AssertJUnit.assertTrue("Order Confirmation Page: Ordered Quantity not maintained after manual entry page",orderConf.getOrderedQty()==1);
        AssertJUnit.assertTrue("Order Confirmation Page: Required Date not maintained after manual entry page",orderConf.getRequiredDate().equals(date));
        
        System.out.println("Details maintained. Check adjusted quantity appears...");
        
        int adjQty = orderConf.getAdjustedQty();
        
        System.out.println("Adjusted quantity retrieved. Checking value...");

        AssertJUnit.assertTrue("Order Confirmation Page: Adjusted Quantity less than or equal to the ordered quantity",adjQty>1);
        
        System.out.println("Value greater than ordered qty, as expected. Submitting order...");
        
        //Press Submit
        Ecomm_OutstandingOrdersPage outOrders = orderConf.pressSubmit(); 
        outOrders.waitForElement();

        System.out.println("Order submitted. Viewing order...");
        
        //Verify values in outstanding orders tab
        //Get the row number of the order in the table and press view
        int rowNumber = outOrders.getRowAlt(DataItems.lastUsedPO);
        Ecomm_OrderViewPage orderView = outOrders.pressView(rowNumber);       
        //wait for overlay to load
        orderView.waitForContent();
        orderView.switchTo();
        
        System.out.println("Order view displayed. Verifying values...");

        Verify.verify(orderView.getArticleCell().getText().equals(DataItems.MOQArticle),"Order view: Article does not match expected input");
        Verify.verify(orderView.getBrandCell().getText().equals(DataItems.MOQBrand),"Order view: Brand does not match expected input");
        Verify.verify(orderView.getTicketCell().getText().equals(DataItems.MOQTicket),"Order view: Ticket does not match expected input");
        Verify.verify(orderView.getShadeCodeCell().getText().equals(DataItems.expShadeCode),"Order view: Shade code does not match expected input");

        System.out.println("Values verified. Closing view...");
        
        //Exit view
        orderView.exitView();
        orderView.waitForInvisibility();
        driver.switchTo().defaultContent();
        
        System.out.println("View closed.");
        
        //Output order number for test reference
        String orderNumber = outOrders.getOrderNumber(rowNumber);
        System.out.println("Order Number: " + orderNumber);
    }
    
    @Test //Manual Entry Page :: SUSST :: Single-line order using YMN and master shade (MOQ ACTIVE)
    (groups = {"eComm","eComm_Orders"})
    public void SUSST13() throws Exception {
        //New chrome driver
        WebDriver driver = getDriver();

        //Login as global admin to setup master data
        Cce_Base baseSetupMaster = new Cce_Base(driver);
        baseSetupMaster.setUp("Set Master", "Data");

        //Set Master Data
        System.out.println("Set Master Data...");
        PreFlows pf = new PreFlows();
        pf.deActivateCallOffOrderForCustomer(driver, DataItems.lifeEasyCustomer);
        pf.enableMOQForCustomer(driver, DataItems.lifeEasyCustomer);
        pf.chooseTheOtherProfile(driver);
        pf.logoutAction(driver);

        //new base test to handle set up
        Ecomm_Base base = new Ecomm_Base(driver);
        //Set up returns a manual entry page to begin data entry
        Ecomm_MainPage eCommPage = base.setUp("MANUAL ENTRY SUSST13: Single line, Your Material Number and master shade(MOQ ACTIVE)","G_OOC_ME_SUSST_MOQ_1",DataItems.susstUsername,DataItems.susstPassword);

        System.out.println("Navigating to Manual Entry...");

        //press manual entry
        Ecomm_ManualEntryPage manualEntryPage = eCommPage.clickManualEntry();
        manualEntryPage.waitForElement();

        System.out.println("Manual Entry Page reached. Entering customer details...");

        //Input Customer Details    
        manualEntryPage.setShipToPartyWithWait(DataItems.testCustDetails[1]);
        manualEntryPage.setBuyers(DataItems.testCustDetails[3]);
        manualEntryPage.setPONumber(DataItems.testCustDetails[4]);

        System.out.println("Customer details entered. Entering product details...");

        int numberOfLines = 1;

        //Order details to be entered
        String[][] details = {
            //line 1 details
            {DataItems.yourMatNum,String.valueOf(1)},
        };

        //Input details for each line
        for (int i = 0; i < numberOfLines; i++) {
            manualEntryPage = manualEntryPage.setOrderDetailsYMN(details[i], i);
        }
        
        String date = manualEntryPage.getDate(0);
        
        System.out.println("Product details entered. Ensuring auto-fill is correct...");
        
        AssertJUnit.assertTrue("Manual Entry Page: Auto-fill article not as expected in master data",manualEntryPage.getArticle(0).equals(DataItems.expArticle));
        AssertJUnit.assertTrue("Manual Entry Page: Auto-fill brand not as expected in master data",manualEntryPage.getBrand(0).equals(DataItems.expBrand));
        AssertJUnit.assertTrue("Manual Entry Page: Auto-fill ticket not as expected in master data",manualEntryPage.getTicket(0).equals(DataItems.expTicket));
        AssertJUnit.assertTrue("Manual Entry Page: Auto-fill length not as expected in master data",manualEntryPage.getLength(0).equals(DataItems.expLength));
        AssertJUnit.assertTrue("Manual Entry Page: Auto-fill finish not as expected in master data",manualEntryPage.getFinish(0).equals(DataItems.expFinish));
        AssertJUnit.assertTrue("Manual Entry Page: Shade code not as expected after being changed from material Master shade",manualEntryPage.getShadeCode(0).equals(DataItems.expShadeCode));
        
        System.out.println("Auto-fill correct. Pressing next...");
        
        //Press next
        Ecomm_OrderConfirmationPage orderConf = manualEntryPage.pressNextMOQDoubleAlert();

        orderConf.waitForElement();
        
        System.out.println("MOQ Alert appeared as expected.");

        System.out.println("Order confirmation page reached. Checking details are maintained...");
        
        AssertJUnit.assertTrue("Order Confirmation Page: Customer PO Number not maintained after manual entry page",orderConf.getUploadPONumber().equals(DataItems.lastUsedPO));
        AssertJUnit.assertTrue("Order Confirmation Page: Ship To Party not maintained after manual entry page",orderConf.getShipToParty().equals(DataItems.testCustDetails[1]));
        AssertJUnit.assertTrue("Order Confirmation Page: Buyers not maintained after manual entry page",orderConf.getBuyers().equals(DataItems.testCustDetails[3]));

        String[] parts = orderConf.getCoatsMaterial().split("-");
        System.out.println(parts[1]);
        AssertJUnit.assertTrue("Order Confirmation Page: Shade Code (from Coats Mat Num) not maintained after manual entry page",parts[1].equals(DataItems.expShadeCode));
        
        AssertJUnit.assertTrue("Order Confirmation Page: Ordered Quantity not maintained after manual entry page",orderConf.getOrderedQty()==1);
        AssertJUnit.assertTrue("Order Confirmation Page: Required Date not maintained after manual entry page",orderConf.getRequiredDate().equals(date));
        
        System.out.println("Details maintained. Check adjusted quantity appears...");
        
        int adjQty = orderConf.getAdjustedQty();
        
        System.out.println("Adjusted quantity retrieved. Checking value...");

        AssertJUnit.assertTrue("Order Confirmation Page: Adjusted Quantity less than or equal to the ordered quantity",adjQty>1);
        
        System.out.println("Value greater than ordered qty, as expected. Submitting order...");
        
        //Press Submit
        Ecomm_OutstandingOrdersPage outOrders = orderConf.pressSubmit(); 
        outOrders.waitForElement();

        System.out.println("Order submitted. Viewing order...");
        
        //Verify values in outstanding orders tab
        //Get the row number of the order in the table and press view
        int rowNumber = outOrders.getRowAlt(DataItems.lastUsedPO);
        Ecomm_OrderViewPage orderView = outOrders.pressView(rowNumber);       
        //wait for overlay to load
        orderView.waitForContent();
        orderView.switchTo();
        
        System.out.println("Order view displayed. Verifying values...");

        Verify.verify(orderView.getArticleCell().getText().equals(DataItems.expArticle),"Order view: Article does not match expected input");
        Verify.verify(orderView.getBrandCell().getText().equals(DataItems.expBrand),"Order view: Brand does not match expected input");
        Verify.verify(orderView.getTicketCell().getText().equals(DataItems.expTicket),"Order view: Ticket does not match expected input");
        Verify.verify(orderView.getShadeCodeCell().getText().equals(DataItems.expShadeCode),"Order view: Shade code does not match expected input");

        System.out.println("Values verified. Closing view...");
        
        //Exit view
        orderView.exitView();
        orderView.waitForInvisibility();
        driver.switchTo().defaultContent();
        
        System.out.println("View closed.");
        
        //Output order number for test reference
        String orderNumber = outOrders.getOrderNumber(rowNumber);
        System.out.println("Order Number: " + orderNumber);
    }
    
    @Test //Manual Entry Page :: SUSST :: Multi-line order using YMN and master shade (MOQ ACTIVE)
    (groups = {"eComm","eComm_Orders"})
    public void SUSST14() throws Exception {
        //New chrome driver
        WebDriver driver = getDriver();

        //Login as global admin to setup master data
        Cce_Base baseSetupMaster = new Cce_Base(driver);
        baseSetupMaster.setUp("Set Master", "Data");

        //Set Master Data
        System.out.println("Set Master Data...");
        PreFlows pf = new PreFlows();
        pf.deActivateCallOffOrderForCustomer(driver, DataItems.lifeEasyCustomer);
        pf.enableMOQForCustomer(driver, DataItems.lifeEasyCustomer);
        pf.chooseTheOtherProfile(driver);
        pf.logoutAction(driver);

        //new base test to handle set up
        Ecomm_Base base = new Ecomm_Base(driver);
        //Set up returns a manual entry page to begin data entry
        Ecomm_MainPage eCommPage = base.setUp("MANUAL ENTRY SUSST14: Multi line, Your Material Number and master shade(MOQ ACTIVE)","G_OOC_ME_SUSST_MOQ_2",DataItems.susstUsername,DataItems.susstPassword);

        System.out.println("Navigating to Manual Entry...");

        //press manual entry
        Ecomm_ManualEntryPage manualEntryPage = eCommPage.clickManualEntry();
        manualEntryPage.waitForElement();

        System.out.println("Manual Entry Page reached. Entering customer details...");

        //Input Customer Details    
        manualEntryPage.setShipToPartyWithWait(DataItems.testCustDetails[1]);
        manualEntryPage.setBuyers(DataItems.testCustDetails[3]);
        manualEntryPage.setPONumber(DataItems.testCustDetails[4]);

        System.out.println("Customer details entered. Entering product details...");

        int numberOfLines = 2;

        //Order details to be entered
        String[][] details = {
            //line 1 details
            {DataItems.yourMatNum,String.valueOf(1)},
            {DataItems.yourMatNum2,String.valueOf(DataItems.quantity)}
        };

        //Input details for each line
        for (int i = 0; i < numberOfLines; i++) {
            manualEntryPage = manualEntryPage.setOrderDetailsYMN(details[i], i);
        }
        
        String date = manualEntryPage.getDate(0);
        
        System.out.println("Product details entered. Ensuring auto-fill is correct...");
        
        AssertJUnit.assertTrue("Manual Entry Page: Auto-fill article not as expected in master data",manualEntryPage.getArticle(0).equals(DataItems.expArticle));
        AssertJUnit.assertTrue("Manual Entry Page: Auto-fill brand not as expected in master data",manualEntryPage.getBrand(0).equals(DataItems.expBrand));
        AssertJUnit.assertTrue("Manual Entry Page: Auto-fill ticket not as expected in master data",manualEntryPage.getTicket(0).equals(DataItems.expTicket));
        AssertJUnit.assertTrue("Manual Entry Page: Auto-fill length not as expected in master data",manualEntryPage.getLength(0).equals(DataItems.expLength));
        AssertJUnit.assertTrue("Manual Entry Page: Auto-fill finish not as expected in master data",manualEntryPage.getFinish(0).equals(DataItems.expFinish));
        AssertJUnit.assertTrue("Manual Entry Page: Shade code not as expected after being changed from material Master shade",manualEntryPage.getShadeCode(0).equals(DataItems.expShadeCode));
        
        System.out.println("Auto-fill correct. Pressing next...");
        
        //Press next
        Ecomm_OrderConfirmationPage orderConf = manualEntryPage.pressNextMOQDoubleAlert();

        orderConf.waitForElement();
        
        System.out.println("MOQ Alert appeared as expected.");

        System.out.println("Order confirmation page reached. Checking details are maintained...");
        
        AssertJUnit.assertTrue("Order Confirmation Page: Customer PO Number not maintained after manual entry page",orderConf.getUploadPONumber().equals(DataItems.lastUsedPO));
        AssertJUnit.assertTrue("Order Confirmation Page: Ship To Party not maintained after manual entry page",orderConf.getShipToParty().equals(DataItems.testCustDetails[1]));
        AssertJUnit.assertTrue("Order Confirmation Page: Buyers not maintained after manual entry page",orderConf.getBuyers().equals(DataItems.testCustDetails[3]));

        String[] parts = orderConf.getCoatsMaterial().split("-");
        System.out.println(parts[1]);
        AssertJUnit.assertTrue("Order Confirmation Page: Shade Code (from Coats Mat Num) not maintained after manual entry page",parts[1].equals(DataItems.expShadeCode));
        
        AssertJUnit.assertTrue("Order Confirmation Page: Ordered Quantity not maintained after manual entry page",orderConf.getOrderedQty()==1);
        AssertJUnit.assertTrue("Order Confirmation Page: Required Date not maintained after manual entry page",orderConf.getRequiredDate().equals(date));
        
        System.out.println("Details maintained. Check adjusted quantity appears...");
        
        int adjQty = orderConf.getAdjustedQty();
        
        System.out.println("Adjusted quantity retrieved. Checking value...");

        AssertJUnit.assertTrue("Order Confirmation Page: Adjusted Quantity less than or equal to the ordered quantity",adjQty>1);
        
        System.out.println("Value greater than ordered qty, as expected. Submitting order...");
        
        //Press Submit
        Ecomm_OutstandingOrdersPage outOrders = orderConf.pressSubmit(); 
        outOrders.waitForElement();

        System.out.println("Order submitted. Viewing order...");
        
        //Verify values in outstanding orders tab
        //Get the row number of the order in the table and press view
        int rowNumber = outOrders.getRowAlt(DataItems.lastUsedPO);
        Ecomm_OrderViewPage orderView = outOrders.pressView(rowNumber);       
        //wait for overlay to load
        orderView.waitForContent();
        orderView.switchTo();
        
        System.out.println("Order view displayed. Verifying values...");

        Verify.verify(orderView.getArticleCell().getText().equals(DataItems.expArticle),"Order view: Article does not match expected input");
        Verify.verify(orderView.getBrandCell().getText().equals(DataItems.expBrand),"Order view: Brand does not match expected input");
        Verify.verify(orderView.getTicketCell().getText().equals(DataItems.expTicket),"Order view: Ticket does not match expected input");
        Verify.verify(orderView.getShadeCodeCell().getText().equals(DataItems.expShadeCode),"Order view: Shade code does not match expected input");

        System.out.println("Values verified. Closing view...");
        
        //Exit view
        orderView.exitView();
        orderView.waitForInvisibility();
        driver.switchTo().defaultContent();
        
        System.out.println("View closed.");
        
        //Output order number for test reference
        String orderNumber = outOrders.getOrderNumber(rowNumber);
        System.out.println("Order Number: " + orderNumber);
    }
    
    @Test //Manual Entry Page :: SUSST :: Single-line order using YMN and master shade (MOQ ACTIVE)
    (groups = {"eComm","eComm_Orders"})
    public void SUSST15() throws Exception {
        //New chrome driver
        WebDriver driver = getDriver();

        //Login as global admin to setup master data
        Cce_Base baseSetupMaster = new Cce_Base(driver);
        baseSetupMaster.setUp("Set Master", "Data");

        //Set Master Data
        System.out.println("Set Master Data...");
        PreFlows pf = new PreFlows();
        pf.deActivateCallOffOrderForCustomer(driver, DataItems.lifeEasyCustomer);
        pf.enableMOQForCustomer(driver, DataItems.lifeEasyCustomer);
        pf.chooseTheOtherProfile(driver);
        pf.logoutAction(driver);

        //new base test to handle set up
        Ecomm_Base base = new Ecomm_Base(driver);
        //Set up returns a manual entry page to begin data entry
        Ecomm_MainPage eCommPage = base.setUp("MANUAL ENTRY SUSST15: Single line, Your Material Number and master shade(MOQ ACTIVE)","G_OOC_ME_SUSST_MOQ_3",DataItems.susstUsername,DataItems.susstPassword);

        System.out.println("Navigating to Manual Entry...");

        //press manual entry
        Ecomm_ManualEntryPage manualEntryPage = eCommPage.clickManualEntry();
        manualEntryPage.waitForElement();

        System.out.println("Manual Entry Page reached. Entering customer details...");

        //Input Customer Details    
        manualEntryPage.setShipToPartyWithWait(DataItems.testCustDetails[1]);
        manualEntryPage.setBuyers(DataItems.testCustDetails[3]);
        manualEntryPage.setPONumber(DataItems.testCustDetails[4]);

        System.out.println("Customer details entered. Entering product details...");

        int numberOfLines = 1;

        //Order details to be entered
        String[][] details = {
            //line 1 details
            {DataItems.yourMatNum2,DataItems.expShadeCode,String.valueOf(1)},
        };

        //Input details for each line
        for (int i = 0; i < numberOfLines; i++) {
            manualEntryPage = manualEntryPage.setOrderDetailsYMNShadeCode(details[i], i);
        }
        
        String date = manualEntryPage.getDate(0);
        
        System.out.println("Product details entered. Ensuring auto-fill is correct...");
        
        AssertJUnit.assertTrue("Manual Entry Page: Auto-fill article not as expected in master data",manualEntryPage.getArticle(0).equals(DataItems.expArticle));
        AssertJUnit.assertTrue("Manual Entry Page: Auto-fill brand not as expected in master data",manualEntryPage.getBrand(0).equals(DataItems.expBrand));
        AssertJUnit.assertTrue("Manual Entry Page: Auto-fill ticket not as expected in master data",manualEntryPage.getTicket(0).equals(DataItems.expTicket));
        AssertJUnit.assertTrue("Manual Entry Page: Auto-fill length not as expected in master data",manualEntryPage.getLength(0).equals(DataItems.expLength));
        AssertJUnit.assertTrue("Manual Entry Page: Auto-fill finish not as expected in master data",manualEntryPage.getFinish(0).equals(DataItems.expFinish));
        AssertJUnit.assertTrue("Manual Entry Page: Shade code not as expected after being changed from material Master shade",manualEntryPage.getShadeCode(0).equals(DataItems.expShadeCode));
        
        System.out.println("Auto-fill correct. Pressing next...");
        
        //Press next
        Ecomm_OrderConfirmationPage orderConf = manualEntryPage.pressNextMOQDoubleAlert();

        orderConf.waitForElement();
        
        System.out.println("MOQ Alert appeared as expected.");

        System.out.println("Order confirmation page reached. Checking details are maintained...");
        
        AssertJUnit.assertTrue("Order Confirmation Page: Customer PO Number not maintained after manual entry page",orderConf.getUploadPONumber().equals(DataItems.lastUsedPO));
        AssertJUnit.assertTrue("Order Confirmation Page: Ship To Party not maintained after manual entry page",orderConf.getShipToParty().equals(DataItems.testCustDetails[1]));
        AssertJUnit.assertTrue("Order Confirmation Page: Buyers not maintained after manual entry page",orderConf.getBuyers().equals(DataItems.testCustDetails[3]));

        String[] parts = orderConf.getCoatsMaterial().split("-");
        System.out.println(parts[1]);
        AssertJUnit.assertTrue("Order Confirmation Page: Shade Code (from Coats Mat Num) not maintained after manual entry page",parts[1].equals(DataItems.expShadeCode));
        
        AssertJUnit.assertTrue("Order Confirmation Page: Ordered Quantity not maintained after manual entry page",orderConf.getOrderedQty()==1);
        AssertJUnit.assertTrue("Order Confirmation Page: Required Date not maintained after manual entry page",orderConf.getRequiredDate().equals(date));
        
        System.out.println("Details maintained. Check adjusted quantity appears...");
        
        int adjQty = orderConf.getAdjustedQty();
        
        System.out.println("Adjusted quantity retrieved. Checking value...");

        AssertJUnit.assertTrue("Order Confirmation Page: Adjusted Quantity less than or equal to the ordered quantity",adjQty>1);
        
        System.out.println("Value greater than ordered qty, as expected. Submitting order...");
        
        //Press Submit
        Ecomm_OutstandingOrdersPage outOrders = orderConf.pressSubmit(); 
        outOrders.waitForElement();

        System.out.println("Order submitted. Viewing order...");
        
        //Verify values in outstanding orders tab
        //Get the row number of the order in the table and press view
        int rowNumber = outOrders.getRowAlt(DataItems.lastUsedPO);
        Ecomm_OrderViewPage orderView = outOrders.pressView(rowNumber);       
        //wait for overlay to load
        orderView.waitForContent();
        orderView.switchTo();
        
        System.out.println("Order view displayed. Verifying values...");

        Verify.verify(orderView.getArticleCell().getText().equals(DataItems.expArticle),"Order view: Article does not match expected input");
        Verify.verify(orderView.getBrandCell().getText().equals(DataItems.expBrand),"Order view: Brand does not match expected input");
        Verify.verify(orderView.getTicketCell().getText().equals(DataItems.expTicket),"Order view: Ticket does not match expected input");
        Verify.verify(orderView.getShadeCodeCell().getText().equals(DataItems.expShadeCode),"Order view: Shade code does not match expected input");

        System.out.println("Values verified. Closing view...");
        
        //Exit view
        orderView.exitView();
        orderView.waitForInvisibility();
        driver.switchTo().defaultContent();
        
        System.out.println("View closed.");
        
        //Output order number for test reference
        String orderNumber = outOrders.getOrderNumber(rowNumber);
        System.out.println("Order Number: " + orderNumber);
    }
    
    @Test //Manual Entry Page :: SUSST :: Single-line order using YMN without master shade (MOQ ACTIVE)
    (groups = {"eComm","eComm_Orders"})
    public void SUSST16() throws Exception {
        //New chrome driver
        WebDriver driver = getDriver();

        //Login as global admin to setup master data
        Cce_Base baseSetupMaster = new Cce_Base(driver);
        baseSetupMaster.setUp("Set Master", "Data");

        //Set Master Data
        System.out.println("Set Master Data...");
        PreFlows pf = new PreFlows();
        pf.deActivateCallOffOrderForCustomer(driver, DataItems.lifeEasyCustomer);
        pf.enableMOQForCustomer(driver, DataItems.lifeEasyCustomer);
        pf.chooseTheOtherProfile(driver);
        pf.logoutAction(driver);

        //new base test to handle set up
        Ecomm_Base base = new Ecomm_Base(driver);
        //Set up returns a manual entry page to begin data entry
        Ecomm_MainPage eCommPage = base.setUp("MANUAL ENTRY SUSST16: Multi-line, Your Material Number without master shade(MOQ ACTIVE)","G_OOC_ME_SUSST_MOQ_4",DataItems.susstUsername,DataItems.susstPassword);

        System.out.println("Navigating to Manual Entry...");

        //press manual entry
        Ecomm_ManualEntryPage manualEntryPage = eCommPage.clickManualEntry();
        manualEntryPage.waitForElement();

        System.out.println("Manual Entry Page reached. Entering customer details...");

        //Input Customer Details    
        manualEntryPage.setShipToPartyWithWait(DataItems.testCustDetails[1]);
        manualEntryPage.setBuyers(DataItems.testCustDetails[3]);
        manualEntryPage.setPONumber(DataItems.testCustDetails[4]);

        System.out.println("Customer details entered. Entering product details...");

        int numberOfLines = 2;

        //Order details to be entered
        String[][] details = {
            //line 1 details
            {DataItems.yourMatNum2,DataItems.expShadeCode,String.valueOf(1)},
            {DataItems.yourMatNum,DataItems.shadeCode,String.valueOf(DataItems.quantity)}
        };

        //Input details for each line
        for (int i = 0; i < numberOfLines; i++) {
            manualEntryPage = manualEntryPage.setOrderDetailsYMNShadeCode(details[i], i);
        }
        
        String date = manualEntryPage.getDate(0);
        
        System.out.println("Product details entered. Ensuring auto-fill is correct...");
        
        AssertJUnit.assertTrue("Manual Entry Page: Auto-fill article not as expected in master data",manualEntryPage.getArticle(0).equals(DataItems.expArticle));
        AssertJUnit.assertTrue("Manual Entry Page: Auto-fill brand not as expected in master data",manualEntryPage.getBrand(0).equals(DataItems.expBrand));
        AssertJUnit.assertTrue("Manual Entry Page: Auto-fill ticket not as expected in master data",manualEntryPage.getTicket(0).equals(DataItems.expTicket));
        AssertJUnit.assertTrue("Manual Entry Page: Auto-fill length not as expected in master data",manualEntryPage.getLength(0).equals(DataItems.expLength));
        AssertJUnit.assertTrue("Manual Entry Page: Auto-fill finish not as expected in master data",manualEntryPage.getFinish(0).equals(DataItems.expFinish));
        AssertJUnit.assertTrue("Manual Entry Page: Shade code not as expected after being changed from material Master shade",manualEntryPage.getShadeCode(0).equals(DataItems.expShadeCode));
        
        System.out.println("Auto-fill correct. Pressing next...");
        
        //Press next
        Ecomm_OrderConfirmationPage orderConf = manualEntryPage.pressNextMOQDoubleAlert();

        orderConf.waitForElement();
        
        System.out.println("MOQ Alert appeared as expected.");

        System.out.println("Order confirmation page reached. Checking details are maintained...");
        
        AssertJUnit.assertTrue("Order Confirmation Page: Customer PO Number not maintained after manual entry page",orderConf.getUploadPONumber().equals(DataItems.lastUsedPO));
        AssertJUnit.assertTrue("Order Confirmation Page: Ship To Party not maintained after manual entry page",orderConf.getShipToParty().equals(DataItems.testCustDetails[1]));
        AssertJUnit.assertTrue("Order Confirmation Page: Buyers not maintained after manual entry page",orderConf.getBuyers().equals(DataItems.testCustDetails[3]));

        String[] parts = orderConf.getCoatsMaterial().split("-");
        System.out.println(parts[1]);
        AssertJUnit.assertTrue("Order Confirmation Page: Shade Code (from Coats Mat Num) not maintained after manual entry page",parts[1].equals(DataItems.expShadeCode));
        
        AssertJUnit.assertTrue("Order Confirmation Page: Ordered Quantity not maintained after manual entry page",orderConf.getOrderedQty()==1);
        AssertJUnit.assertTrue("Order Confirmation Page: Required Date not maintained after manual entry page",orderConf.getRequiredDate().equals(date));
        
        System.out.println("Details maintained. Check adjusted quantity appears...");
        
        int adjQty = orderConf.getAdjustedQty();
        
        System.out.println("Adjusted quantity retrieved. Checking value...");

        AssertJUnit.assertTrue("Order Confirmation Page: Adjusted Quantity less than or equal to the ordered quantity",adjQty>1);
        
        System.out.println("Value greater than ordered qty, as expected. Submitting order...");
        
        //Press Submit
        Ecomm_OutstandingOrdersPage outOrders = orderConf.pressSubmit(); 
        outOrders.waitForElement();

        System.out.println("Order submitted. Viewing order...");
        
        //Verify values in outstanding orders tab
        //Get the row number of the order in the table and press view
        int rowNumber = outOrders.getRowAlt(DataItems.lastUsedPO);
        Ecomm_OrderViewPage orderView = outOrders.pressView(rowNumber);       
        //wait for overlay to load
        orderView.waitForContent();
        orderView.switchTo();
        
        System.out.println("Order view displayed. Verifying values...");

        Verify.verify(orderView.getArticleCell().getText().equals(DataItems.expArticle),"Order view: Article does not match expected input");
        Verify.verify(orderView.getBrandCell().getText().equals(DataItems.expBrand),"Order view: Brand does not match expected input");
        Verify.verify(orderView.getTicketCell().getText().equals(DataItems.expTicket),"Order view: Ticket does not match expected input");
        Verify.verify(orderView.getShadeCodeCell().getText().equals(DataItems.expShadeCode),"Order view: Shade code does not match expected input");

        System.out.println("Values verified. Closing view...");
        
        //Exit view
        orderView.exitView();
        orderView.waitForInvisibility();
        driver.switchTo().defaultContent();
        
        System.out.println("View closed.");
        
        //Output order number for test reference
        String orderNumber = outOrders.getOrderNumber(rowNumber);
        System.out.println("Order Number: " + orderNumber);
    }

}
