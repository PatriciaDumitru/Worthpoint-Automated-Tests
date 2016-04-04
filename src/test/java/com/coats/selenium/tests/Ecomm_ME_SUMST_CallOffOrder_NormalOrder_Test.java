package com.coats.selenium.tests;

import AutomationFramework.CommonTask;
import AutomationFramework.DataItems;
import AutomationFramework.PreFlows;
import AutomationFramework.Wait;
import PageObjects.CCE_MainPage;
import PageObjects.Ecomm_MainPage;
import PageObjects.Ecomm_ManualEntryPage;
import PageObjects.Ecomm_OrderConfirmationPage;
import PageObjects.Ecomm_OrderViewPage;
import PageObjects.Ecomm_OutstandingOrderDraftPage;
import PageObjects.Ecomm_OutstandingOrdersPage;
import PageObjects.Ecomm_PendingApprovalListPage;
import PageObjects.Ecomm_SAPInterfaceLogPage;
import PageObjects.Ecomm_UploadProcessPage;
import PageObjects.Mst_CustomersPage;
import PageObjects.Mst_EditCustomerPage;
import PageObjects.WBA_ContinuePage;
import PageObjects.WBA_LoginPage;
import PageObjects.WBA_SelectionPage;
import com.coats.selenium.DriverFactory;
import com.google.common.base.Verify;
import java.io.File;
import java.io.IOException;

import com.sun.jna.platform.unix.X11;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.testng.AssertJUnit;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

//CONO CALL OFF NORMAL ORDER
public class Ecomm_ME_SUMST_CallOffOrder_NormalOrder_Test extends DriverFactory {


    @Test //Manual Entry Page :: SUMST :: Page checks, single line order using YMN and shade code from master data
            (groups = {"Solo, Solo"})
    public void SUMST1_CONO() throws IOException, InterruptedException, Exception {
        //New chrome driver
        WebDriver driver = getDriver();

        //new base test to handle set up
        Ecomm_Base susstTest4 = new Ecomm_Base(driver);
        //Set up returns a manual entry page to begin data entry

        Ecomm_MainPage eCommPage = susstTest4.setUp("MANUAL ENTRY SUMST1_CONO: Single line, Your Material Number with master data shade code","G_OOC_ME_SUMST_1");

        System.out.println("Setup MasterData...");
        //Activate CallOffOrder for customer

        PreFlows pf = new PreFlows();
        pf.chooseTheOtherProfile(driver); //choose CCE for Master Data setup
        pf.activateCallOffOrderForSalesOrg(driver, DataItems.salesOrgID);
        pf.activateCallOffOrderForCustomer(driver, DataItems.lifeEasyCustomer);
        pf.chooseTheOtherProfile(driver); //choose Ecomm for tests

        System.out.println("Navigating to Manual Entry...");

        //press manual entry
        Ecomm_ManualEntryPage manualEntryPage = eCommPage.clickManualEntry();
        manualEntryPage.waitForElement();

        System.out.println("Manual Entry loaded.");

        //Make assertions
        manualEntryPage.assertBaseElements();

        System.out.println("Checking title...");

        AssertJUnit.assertTrue("Manual Entry Page: Title not displayed as expected",manualEntryPage.getBreadcrumb().getText().equals("Orders | Manual Entry"));

        System.out.println("Title checked. Checking fields...");

        manualEntryPage.checkFields();

        System.out.println("Fields checked. ");

        System.out.println("Entering customer details...");

        //Input Customer Details
        manualEntryPage.setCustomerName(DataItems.custDetails[0]);
        manualEntryPage.setShipToParty(DataItems.custDetails[1]);
        manualEntryPage.setRequestor(DataItems.custDetails[2]);
        manualEntryPage.setBuyers(DataItems.custDetails[3]);
        manualEntryPage.setPONumber(DataItems.custDetails[4]);

        pf.selectNormalOrderRadioButton(driver);

        System.out.println("Customer details entered. Entering product details...");

        //Number of order lines
        int numberOfLines = 1;

        //Order details to be entered
        String[][] details = {
                //line 1 details
                {DataItems.yourMatNum,String.valueOf(DataItems.quantity)}
                //Further line details, each in their own array
        };

        //Input details for each line
        for (int i = 0; i < numberOfLines; i++) {
            manualEntryPage = manualEntryPage.setOrderDetailsYMN(details[i], i);
        }

        System.out.println("Product details entered. Pressing next...");

        //Press next
        Ecomm_OrderConfirmationPage orderConf = manualEntryPage.pressNext();
        orderConf.waitForElement();

        System.out.println("Order confirmation page reached. Submitting order...");

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

        Verify.verify(orderView.getYourMatNumCell().getText().equals(DataItems.yourMatNum),"Order view: Your Material Number does not match input");
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

        driver.navigate().refresh();

        //Deactivate call off order for customer
        pf.chooseTheOtherProfile(driver);
        pf.deActivateCallOffOrderForCustomer(driver, DataItems.lifeEasyCustomer);

    }

    @Test //Manual Entry Page :: SUMST :: Multiple line order, YMN with master shade code
            (groups = {"eComm","eComm_Orders"})
    public void SUSMT2_CONO() throws InterruptedException, Exception {
        //New chrome driver
        WebDriver driver = getDriver();

        //new base test to handle set up
        Ecomm_Base susstTest4 = new Ecomm_Base(driver);
        //Set up returns a manual entry page to begin data entry
        Ecomm_MainPage eCommPage = susstTest4.setUp("MANUAL ENTRY SUMST2: Multiple lines, Your Material Number with master data shade code","G_OOC_ME_SUSST_2");

        System.out.println("Setup MasterData...");
        //Activate CallOffOrder for customer

        PreFlows pf = new PreFlows();
        pf.chooseTheOtherProfile(driver); //choose CCE for Master Data setup
        pf.activateCallOffOrderForSalesOrg(driver, DataItems.salesOrgID);
        pf.activateCallOffOrderForSalesOrg(driver, DataItems.salesOrgID);
        pf.activateCallOffOrderAndDeactivateApprovalForCustomer(driver, DataItems.lifeEasyCustomer);
        pf.chooseTheOtherProfile(driver); //choose Ecomm for tests

        System.out.println("Navigating to Manual Entry...");

        Ecomm_ManualEntryPage manualEntryPage = eCommPage.clickManualEntry();
        manualEntryPage.waitForElement();

        System.out.println("Manual Entry page loaded. Entering customer details...");

        //Input Customer Details
        manualEntryPage.setCustomerName(DataItems.custDetails[0]);
        manualEntryPage.setShipToParty(DataItems.custDetails[1]);
        manualEntryPage.setRequestor(DataItems.custDetails[2]);
        manualEntryPage.setBuyers(DataItems.custDetails[3]);
        manualEntryPage.setPONumber(DataItems.custDetails[4]);

        pf.selectNormalOrderRadioButton(driver);

        System.out.println("Customer details entered. Entering product details...");

        //Number of order lines
        int numberOfLines = 2;

        //Order details to be entered
        String[][] details = {
                //line 1 details
                {DataItems.yourMatNum,String.valueOf(DataItems.quantity)},
                //line 2 details
                {DataItems.yourMatNum2,String.valueOf(DataItems.quantity)}
        };

        //Input details for each line
        for (int i = 0; i < numberOfLines; i++) {
            manualEntryPage = manualEntryPage.setOrderDetailsYMN(details[i], i);
        }

        System.out.println("Product details entered. Pressing next...");

        //Press next
        Ecomm_OrderConfirmationPage orderConf = manualEntryPage.pressNext();
        orderConf.waitForElement();

        System.out.println("Order confirmation page reached. Submitting order...");

        //Press Submit
        Ecomm_OutstandingOrdersPage outOrdersPage = orderConf.pressSubmit();
        outOrdersPage.waitForElement();

        System.out.println("Order submitted. Verifying values in outstanding order against inputs...");

        //Verify values in outstanding orders tab
        //Get the row number of the order in the table and press view
        int rowNumber = outOrdersPage.getRowAlt(DataItems.lastUsedPO);
        Ecomm_OrderViewPage orderView = outOrdersPage.pressView(rowNumber);
        orderView.waitForContent();
        orderView.switchTo();

        Verify.verify(orderView.getYourMatNumCell().getText().equals(DataItems.yourMatNum),"Order view: Your Material Number does not match input");
        Verify.verify(orderView.getArticleCell().getText().equals(DataItems.expArticle),"Order view: Article does not match expected input");
        Verify.verify(orderView.getBrandCell().getText().equals(DataItems.expBrand),"Order view: Brand does not match expected input");
        Verify.verify(orderView.getTicketCell().getText().equals(DataItems.expTicket),"Order view: Ticket does not match expected input");
        Verify.verify(orderView.getShadeCodeCell().getText().equals(DataItems.expShadeCode),"Order view: Shade code does not match expected input");

        System.out.println("Values verified.");

        //Exit view
        orderView.exitView();
        orderView.waitForInvisibility();
        driver.switchTo().defaultContent();

        //Output order number for test reference
        String orderNumber = outOrdersPage.getOrderNumber(rowNumber);
        System.out.println("Order Number: " + orderNumber);

    }

    @Test //Manual Entry Page :: SUMST :: Single line order, YMN without master shade code
            (groups = {"eComm","eComm_Orders"})
    public void SUMST3_CONO() throws InterruptedException, Exception {
        //New chrome driver
        WebDriver driver = getDriver();

        //new base test to handle set up
        Ecomm_Base susstTest3 = new Ecomm_Base(driver);
        //Set up returns a manual entry page to begin data entry
        Ecomm_MainPage eCommPage = susstTest3.setUp("MANUAL ENTRY SUMST3_CONO: Single line, Your Material Number without master data shade code","G_OOC_ME_SUMST_3");


        System.out.println("Setup MasterData...");
        //Activate CallOffOrder for customer

        PreFlows pf = new PreFlows();
        pf.chooseTheOtherProfile(driver); //choose CCE for Master Data setup
        pf.activateCallOffOrderForSalesOrg(driver, DataItems.salesOrgID);
        pf.activateCallOffOrderAndDeactivateApprovalForCustomer(driver, DataItems.lifeEasyCustomer);
        pf.chooseTheOtherProfile(driver); //choose Ecomm for tests

        System.out.println("Navigating to Manual Entry...");

        Ecomm_ManualEntryPage manualEntryPage = eCommPage.clickManualEntry();

        System.out.println("Manual Entry page loaded. Entering customer details...");

        //Input Customer Details
        manualEntryPage.setCustomerName(DataItems.custDetails[0]);
        manualEntryPage.setShipToParty(DataItems.custDetails[1]);
        manualEntryPage.setRequestor(DataItems.custDetails[2]);
        manualEntryPage.setBuyers(DataItems.custDetails[3]);
        manualEntryPage.setPONumber(DataItems.custDetails[4]);

        pf.selectNormalOrderRadioButton(driver);

        System.out.println("Customer details entered. Entering product details...");

        //Number of order lines
        int numberOfLines = 1;

        //Order details to be entered
        String[][] details = {
                //line 1 details
                {DataItems.yourMatNum,DataItems.shadeCode,String.valueOf(DataItems.quantity)},
        };

        //Input details for each line
        for (int i = 0; i < numberOfLines; i++) {
            manualEntryPage = manualEntryPage.setOrderDetailsYMNShadeCode(details[i], i);
        }

        System.out.println("Product details entered. Pressing next...");

        //Press next
        Ecomm_OrderConfirmationPage orderConf = manualEntryPage.pressNext();

        System.out.println("Order confirmation page reached. Submitting order...");

        //Press Submit
        Ecomm_OutstandingOrdersPage outOrdersPage = orderConf.pressSubmit();

        System.out.println("Order submitted. Verifying values in outstanding order against inputs...");

        outOrdersPage.waitForElement();

        //Verify values in outstanding orders tab
        //Get the row number of the order in the table and press view
        int rowNumber = outOrdersPage.getRowAlt(DataItems.lastUsedPO);
        Ecomm_OrderViewPage orderView = outOrdersPage.pressView(rowNumber);
        orderView.waitForContent();
        orderView.switchTo();

        Verify.verify(orderView.getYourMatNumCell().getText().equals(DataItems.yourMatNum),"Order view: Your Material Number does not match input");
        Verify.verify(orderView.getArticleCell().getText().equals(DataItems.expArticle),"Order view: Article does not match expected input");
        Verify.verify(orderView.getBrandCell().getText().equals(DataItems.expBrand),"Order view: Brand does not match expected input");
        Verify.verify(orderView.getTicketCell().getText().equals(DataItems.expTicket),"Order view: Ticket does not match expected input");
        Verify.verify(orderView.getShadeCodeCell().getText().equals(DataItems.shadeCode),"Order view: Shade code does not match expected input");

        System.out.println("Values verified.");

        //Exit view
        orderView.exitView();
        orderView.waitForInvisibility();
        driver.switchTo().defaultContent();

        //Output order number for test reference
        String orderNumber = outOrdersPage.getOrderNumber(rowNumber);
        System.out.println("Order Number: " + orderNumber);

        driver.navigate().refresh();

        //Deactivate call off order for customer
        pf.chooseTheOtherProfile(driver);
        pf.deActivateCallOffOrderForCustomer(driver, DataItems.lifeEasyCustomer);

    }

    @Test //Manual Entry Page :: SUMST :: Multi-line order, YMN without master shade code
            (groups = {"eComm","eComm_Orders"})
    public void SUMST4_CONO() throws InterruptedException, Exception {
        //New driver
        WebDriver driver = getDriver();

        //new base test to handle set up
        Ecomm_Base susstTest4 = new Ecomm_Base(driver);
        //Set up returns a manual entry page to begin data entry
        Ecomm_MainPage eCommPage = susstTest4.setUp("MANUAL ENTRY SUMST4_CONO: Multiple lines, Your Material Number without master data shade code","G_OOC_ME_SUMST_4");

        System.out.println("Setup MasterData...");
        //Activate CallOffOrder for customer

        PreFlows pf = new PreFlows();
        pf.chooseTheOtherProfile(driver); //choose CCE for Master Data setup
        pf.activateCallOffOrderForSalesOrg(driver, DataItems.salesOrgID);
        pf.activateCallOffOrderAndDeactivateApprovalForCustomer(driver, DataItems.lifeEasyCustomer);
        pf.chooseTheOtherProfile(driver); //choose Ecomm for tests
        System.out.println("Navigating to Manual Entry...");

        Ecomm_ManualEntryPage manualEntryPage = eCommPage.clickManualEntry();

        //Input Customer Details
        manualEntryPage.setCustomerName(DataItems.custDetails[0]);
        manualEntryPage.setShipToParty(DataItems.custDetails[1]);
        manualEntryPage.setRequestor(DataItems.custDetails[2]);
        manualEntryPage.setBuyers(DataItems.custDetails[3]);
        manualEntryPage.setPONumber(DataItems.custDetails[4]);

        pf.selectNormalOrderRadioButton(driver);

        System.out.println("Customer details entered. Entering product details...");

        //Number of order lines
        int numberOfLines = 2;

        //Order details to be entered
        String[][] details = {
                //line 1 details
                {DataItems.yourMatNum,DataItems.shadeCode,String.valueOf(DataItems.quantity)},
                {DataItems.yourMatNum2,DataItems.shadeCode,String.valueOf(DataItems.quantity)}
        };

        //Input details for each line
        for (int i = 0; i < numberOfLines; i++) {
            manualEntryPage = manualEntryPage.setOrderDetailsYMNShadeCode(details[i], i);
        }

        System.out.println("Product details entered. Pressing next...");

        //Press next
        Ecomm_OrderConfirmationPage orderConf = manualEntryPage.pressNext();
        orderConf.waitForElement();

        System.out.println("Order confirmation page reached. Submitting order...");

        //Press Submit
        Ecomm_OutstandingOrdersPage outOrdersPage = orderConf.pressSubmit();
        outOrdersPage.waitForElement();

        System.out.println("Order submitted. Verifying values in outstanding order against inputs...");

        //Verify values in outstanding orders tab
        //Get the row number of the order in the table and press view
        int rowNumber = outOrdersPage.getRowAlt(DataItems.lastUsedPO);
        Ecomm_OrderViewPage orderView = outOrdersPage.pressView(rowNumber);
        orderView.waitForContent();
        orderView.switchTo();

        Verify.verify(orderView.getYourMatNumCell().getText().equals(DataItems.yourMatNum),"Order view: Your Material Number does not match input");
        Verify.verify(orderView.getArticleCell().getText().equals(DataItems.expArticle),"Order view: Article does not match expected input");
        Verify.verify(orderView.getBrandCell().getText().equals(DataItems.expBrand),"Order view: Brand does not match expected input");
        Verify.verify(orderView.getTicketCell().getText().equals(DataItems.expTicket),"Order view: Ticket does not match expected input");
        Verify.verify(orderView.getShadeCodeCell().getText().equals(DataItems.shadeCode),"Order view: Shade code does not match expected input");

        System.out.println("Values verified.");

        //Exit view
        orderView.exitView();
        orderView.waitForInvisibility();
        driver.switchTo().defaultContent();

        //Output order number for test reference
        String orderNumber = outOrdersPage.getOrderNumber(rowNumber);
        System.out.println("Order Number: " + orderNumber);

        driver.navigate().refresh();

        //Deactivate call off order for customer
        pf.chooseTheOtherProfile(driver);
        pf.deActivateCallOffOrderForCustomer(driver, DataItems.lifeEasyCustomer);

    }

    @Test //Manual Entry Page :: SUMST :: Single line order, article and shade code
            (groups = {"eComm","eComm_Orders"})
    public void SUMST5_CONO() throws InterruptedException, Exception {
        //New driver
        WebDriver driver = getDriver();

        //new base test to handle set up
        Ecomm_Base susstTest5 = new Ecomm_Base(driver);
        //Set up returns an eComm main page
        Ecomm_MainPage eCommPage = susstTest5.setUp("MANUAL ENTRY SUMST5_CONO: Single line, using Article and shade code","G_OOC_ME_SUMST_5");

        System.out.println("Setup MasterData...");
        //Activate CallOffOrder for customer

        PreFlows pf = new PreFlows();
        pf.chooseTheOtherProfile(driver); //choose CCE for Master Data setup
        pf.activateCallOffOrderForSalesOrg(driver, DataItems.salesOrgID);
        pf.activateCallOffOrderAndDeactivateApprovalForCustomer(driver, DataItems.lifeEasyCustomer);
        pf.chooseTheOtherProfile(driver); //choose Ecomm for tests

        System.out.println("Navigating to Manual Entry...");

        Ecomm_ManualEntryPage manualEntryPage = eCommPage.clickManualEntry();

        //Input customer details
        manualEntryPage.setCustomerName(DataItems.custDetails[0]);
        manualEntryPage.setShipToParty(DataItems.custDetails[1]);
        manualEntryPage.setRequestor(DataItems.custDetails[2]);
        manualEntryPage.setBuyers(DataItems.custDetails[3]);
        manualEntryPage.setPONumber(DataItems.custDetails[4]);

        pf.selectNormalOrderRadioButton(driver);

        System.out.println("Customer details entered. Entering product details...");

        //Number of order lines
        int numberOfLines = 1;

        //Order details to be entered
        String[][] details = {
                //line 1 details
                {DataItems.expArticle,DataItems.shadeCode,String.valueOf(DataItems.quantity)},
        };

        //Input details for each line
        for (int i = 0; i < numberOfLines; i++) {
            manualEntryPage = manualEntryPage.setOrderDetailsArticle(details[i], i);
        }

        System.out.println("Product details entered. Pressing next...");

        //Press next
        Ecomm_OrderConfirmationPage orderConf = manualEntryPage.pressNext();
        orderConf.waitForElement();

        System.out.println("Order confirmation page reached. Submitting order...");

        //Press Submit
        Ecomm_OutstandingOrdersPage outOrdersPage = orderConf.pressSubmit();
        outOrdersPage.waitForElement();

        System.out.println("Order submitted. Verifying values in outstanding order against inputs...");

        //Verify values in outstanding orders tab
        //Get the row number of the order in the table and press view
        int rowNumber = outOrdersPage.getRowAlt(DataItems.lastUsedPO);
        Ecomm_OrderViewPage orderView = outOrdersPage.pressView(rowNumber);
        orderView.waitForContent();
        orderView.switchTo();

        Verify.verify(orderView.getArticleCell().getText().equals(DataItems.expArticle),"Order view: Article does not match expected input");
        Verify.verify(orderView.getBrandCell().getText().equals(DataItems.expBrand),"Order view: Brand does not match expected input");
        Verify.verify(orderView.getTicketCell().getText().equals(DataItems.expTicket),"Order view: Ticket does not match expected input");
        Verify.verify(orderView.getShadeCodeCell().getText().equals(DataItems.shadeCode),"Order view: Shade code does not match expected input");

        System.out.println("Values verified.");

        //Exit view
        orderView.exitView();
        orderView.waitForInvisibility();
        driver.switchTo().defaultContent();

        //Output order number for test reference
        String orderNumber = outOrdersPage.getOrderNumber(rowNumber);
        System.out.println("Order Number: " + orderNumber);

        driver.navigate().refresh();

        //Deactivate call off order for customer
        pf.chooseTheOtherProfile(driver);
        pf.deActivateCallOffOrderForCustomer(driver, DataItems.lifeEasyCustomer);

    }

    @Test //Manual Entry Page :: SUMST :: Multi-line order, using Article and shade code
            (groups = {"eComm","eComm_Orders"})
    public void SUMST6_CONO() throws InterruptedException, Exception {
        //New driver
        WebDriver driver = getDriver();

        //new base test to handle set up
        Ecomm_Base susstTest6 = new Ecomm_Base(driver);
        //Set up returns an eComm main page
        Ecomm_MainPage eCommPage = susstTest6.setUp("MANUAL ENTRY SUMST6_CONO: Multiple lines, using Article and shade code","G_OOC_ME_SUMST_6");

        System.out.println("Setup MasterData...");
        //Activate CallOffOrder for customer

        PreFlows pf = new PreFlows();
        pf.chooseTheOtherProfile(driver); //choose CCE for Master Data setup
        pf.activateCallOffOrderForSalesOrg(driver, DataItems.salesOrgID);
        pf.activateCallOffOrderAndDeactivateApprovalForCustomer(driver, DataItems.lifeEasyCustomer);
        pf.chooseTheOtherProfile(driver); //choose Ecomm for tests

        System.out.println("Navigating to Manual Entry...");

        Ecomm_ManualEntryPage manualEntryPage = eCommPage.clickManualEntry();

        //Input customer details
        manualEntryPage.setCustomerName(DataItems.custDetails[0]);
        manualEntryPage.setShipToParty(DataItems.custDetails[1]);
        manualEntryPage.setRequestor(DataItems.custDetails[2]);
        manualEntryPage.setBuyers(DataItems.custDetails[3]);
        manualEntryPage.setPONumber(DataItems.custDetails[4]);

        pf.selectNormalOrderRadioButton(driver);

        System.out.println("Customer details entered. Entering product details...");

        //Number of order lines
        int numberOfLines = 2;

        //Order details to be entered
        String[][] details = {
                //line 1 details
                {DataItems.expArticle,DataItems.expShadeCode,String.valueOf(DataItems.quantity)},
                //line 2 details
                {DataItems.expArticle2,DataItems.expShadeCode2,String.valueOf(DataItems.quantity)}
        };

        //Input details for each line
        for (int i = 0; i < numberOfLines; i++) {
            manualEntryPage = manualEntryPage.setOrderDetailsArticle(details[i], i);
        }

        System.out.println("Product details entered. Pressing next...");

        //Press next
        Ecomm_OrderConfirmationPage orderConf = manualEntryPage.pressNext();
        orderConf.waitForElement();

        System.out.println("Order confirmation page reached. Submitting order...");

        //Press Submit
        Ecomm_OutstandingOrdersPage outOrdersPage = orderConf.pressSubmit();
        outOrdersPage.waitForElement();

        System.out.println("Order submitted. Verifying values in outstanding order against inputs...");

        //Verify values in outstanding orders tab
        //Get the row number of the order in the table and press view
        int rowNumber = outOrdersPage.getRowAlt(DataItems.lastUsedPO);
        Ecomm_OrderViewPage orderView = outOrdersPage.pressView(rowNumber);
        orderView.waitForContent();
        orderView.switchTo();

        Verify.verify(orderView.getArticleCell().getText().equals(DataItems.expArticle),"Order view: Article does not match expected input");
        Verify.verify(orderView.getBrandCell().getText().equals(DataItems.expBrand),"Order view: Brand does not match expected input");
        Verify.verify(orderView.getTicketCell().getText().equals(DataItems.expTicket),"Order view: Ticket does not match expected input");
        Verify.verify(orderView.getShadeCodeCell().getText().equals(DataItems.expShadeCode),"Order view: Shade code does not match expected input");

        System.out.println("Values verified.");

        //Exit view
        orderView.exitView();
        orderView.waitForInvisibility();
        driver.switchTo().defaultContent();

        //Output order number for test reference
        String orderNumber = outOrdersPage.getOrderNumber(rowNumber);
        System.out.println("Order Number: " + orderNumber);

        driver.navigate().refresh();

        //Deactivate call off order for customer
        pf.chooseTheOtherProfile(driver);
        pf.deActivateCallOffOrderForCustomer(driver, DataItems.lifeEasyCustomer);

    }

    @Test //Manual Entry Page :: SUMST :: Single line order, using brand/ticket/length/finish and shade code
            (groups = {"eComm","eComm_Orders"})
    public void SUMST7_CONO() throws InterruptedException, Exception {
        //New driver
        WebDriver driver = getDriver();

        //new base test to handle set up
        Ecomm_Base susstTest7 = new Ecomm_Base(driver);
        //Set up returns an eComm main page
        Ecomm_MainPage eCommPage = susstTest7.setUp("MANUAL ENTRY SUMST7_CONO: Single line, using brand, ticket, length, finish, and shade code","G_OOC_ME_SUMST_7");

        System.out.println("Setup MasterData...");
        //Activate CallOffOrder for customer

        PreFlows pf = new PreFlows();
        pf.chooseTheOtherProfile(driver); //choose CCE for Master Data setup
        pf.activateCallOffOrderForSalesOrg(driver, DataItems.salesOrgID);
        pf.activateCallOffOrderAndDeactivateApprovalForCustomer(driver, DataItems.lifeEasyCustomer);
        pf.chooseTheOtherProfile(driver); //choose Ecomm for tests
        System.out.println("Navigating to Manual Entry...");

        Ecomm_ManualEntryPage manualEntryPage = eCommPage.clickManualEntry();
        manualEntryPage.waitForElement();

        //Input customer details
        manualEntryPage.setCustomerName(DataItems.custDetails[0]);
        manualEntryPage.setShipToParty(DataItems.custDetails[1]);
        manualEntryPage.setRequestor(DataItems.custDetails[2]);
        manualEntryPage.setBuyers(DataItems.custDetails[3]);
        manualEntryPage.setPONumber(DataItems.custDetails[4]);

        pf.selectNormalOrderRadioButton(driver);

        System.out.println("Customer details entered. Entering product details...");

        //Number of order lines
        int numberOfLines = 1;

        manualEntryPage.setBrand(DataItems.brand, 0);
        manualEntryPage.setTicket(DataItems.ticket,0);
        manualEntryPage.setLength(DataItems.length,0);
        manualEntryPage.setFinish(DataItems.finish,0);
        manualEntryPage.setShadeCode(DataItems.shadeCode,0);
        manualEntryPage.setQty(DataItems.quantity,0);
        manualEntryPage.setDate(0);

        System.out.println("Product details entered. Pressing next...");

        //Press next
        Ecomm_OrderConfirmationPage orderConf = manualEntryPage.pressNext();
        orderConf.waitForElement();

        System.out.println("Order confirmation page reached. Submitting order...");

        //Press Submit
        Ecomm_OutstandingOrdersPage outOrdersPage = orderConf.pressSubmit();
        outOrdersPage.waitForElement();

        System.out.println("Order submitted. Verifying values in outstanding order against inputs...");

        //Verify values in outstanding orders tab
        //Get the row number of the order in the table and press view
        int rowNumber = outOrdersPage.getRowAlt(DataItems.lastUsedPO);
        Ecomm_OrderViewPage orderView = outOrdersPage.pressView(rowNumber);
        orderView.waitForContent();
        orderView.switchTo();

        Verify.verify(orderView.getBrandCell().getText().equals(DataItems.brand),"Order view: Brand does not match expected input");
        Verify.verify(orderView.getTicketCell().getText().equals(DataItems.ticket),"Order view: Ticket does not match expected input");
        Verify.verify(orderView.getShadeCodeCell().getText().equals(DataItems.shadeCode),"Order view: Shade code does not match expected input");

        System.out.println("Values verified.");

        //Exit view
        orderView.exitView();
        orderView.waitForInvisibility();
        driver.switchTo().defaultContent();

        //Output order number for test reference
        String orderNumber = outOrdersPage.getOrderNumber(rowNumber);
        System.out.println("Order Number: " + orderNumber);

        driver.navigate().refresh();

        //Deactivate call off order for customer
        pf.chooseTheOtherProfile(driver);
        pf.deActivateCallOffOrderForCustomer(driver, DataItems.lifeEasyCustomer);

    }

    @Test //Manual Entry Page :: SUMST :: Multi-line order, using brand/ticket/length/finish and shade code
            (groups = {"eComm","eComm_Orders"})
    public void SUMST8_CONO() throws InterruptedException, Exception {
        //New driver
        WebDriver driver = getDriver();

        //new base test to handle set up
        Ecomm_Base susstTest8 = new Ecomm_Base(driver);
        //Set up returns an eComm main page
        Ecomm_MainPage eCommPage = susstTest8.setUp("MANUAL ENTRY SUMST8_CONO: Multiple lines, using brand, ticket, length, finish, and shade code","G_OOC_ME_SUMST_8");

        System.out.println("Setup MasterData...");
        //Activate CallOffOrder for customer

        PreFlows pf = new PreFlows();
        pf.chooseTheOtherProfile(driver); //choose CCE for Master Data setup
        pf.activateCallOffOrderForSalesOrg(driver, DataItems.salesOrgID);
        pf.activateCallOffOrderAndDeactivateApprovalForCustomer(driver, DataItems.lifeEasyCustomer);
        pf.chooseTheOtherProfile(driver); //choose Ecomm for tests
        System.out.println("Navigating to Manual Entry...");

        Ecomm_ManualEntryPage manualEntryPage = eCommPage.clickManualEntry();
        manualEntryPage.waitForElement();

        System.out.println("Manual Entry page reached. Entering customer details...");

        //Input customer details
        manualEntryPage.setCustomerName(DataItems.custDetails[0]);
        manualEntryPage.setShipToParty(DataItems.custDetails[1]);
        manualEntryPage.setRequestor(DataItems.custDetails[2]);
        manualEntryPage.setBuyers(DataItems.custDetails[3]);
        manualEntryPage.setPONumber(DataItems.custDetails[4]);

        pf.selectNormalOrderRadioButton(driver);

        System.out.println("Customer details entered. Entering product details...");

        //Number of order lines
        int numberOfLines = 2;

        //Order details to be entered
        String[][] details = {
                //line 1 details
                {DataItems.brand,DataItems.ticket,DataItems.length,DataItems.finish,DataItems.shadeCode,String.valueOf(DataItems.quantity)},
                //line 2 details
                {DataItems.brand2,DataItems.ticket2,DataItems.length2,DataItems.finish2,DataItems.shadeCode2,String.valueOf(DataItems.quantity2)}
        };

        //Input details for each line
        for (int i = 0; i < numberOfLines; i++) {
            manualEntryPage = manualEntryPage.setOrderDetailsCombination(details[i], i);
        }

        System.out.println("Product details entered. Pressing next...");

        //Press next
        Ecomm_OrderConfirmationPage orderConf = manualEntryPage.pressNext();
        orderConf.waitForElement();

        System.out.println("Order confirmation page reached. Submitting order...");

        //Press Submit
        Ecomm_OutstandingOrdersPage outOrdersPage = orderConf.pressSubmit();
        outOrdersPage.waitForElement();

        System.out.println("Order submitted. Verifying values in outstanding order against inputs...");

        //Verify values in outstanding orders tab
        //Get the row number of the order in the table and press view
        int rowNumber = outOrdersPage.getRowAlt(DataItems.lastUsedPO);
        System.out.println(rowNumber);
        Ecomm_OrderViewPage orderView = outOrdersPage.pressView(rowNumber);
        orderView.waitForContent();
        orderView.switchTo();

        Verify.verify(orderView.getBrandCell().getText().equals(DataItems.brand),"Order view: Brand does not match expected input");
        Verify.verify(orderView.getTicketCell().getText().equals(DataItems.ticket),"Order view: Ticket does not match expected input");
        Verify.verify(orderView.getShadeCodeCell().getText().equals(DataItems.shadeCode),"Order view: Shade code does not match expected input");

        System.out.println("Values verified.");

        //Exit view
        orderView.exitView();
        orderView.waitForInvisibility();
        driver.switchTo().defaultContent();

        //Output order number for test reference
        String orderNumber = outOrdersPage.getOrderNumberSUMST(rowNumber);
        System.out.println("Order Number: " + orderNumber);

        driver.navigate().refresh();

        //Deactivate call off order for customer
        pf.chooseTheOtherProfile(driver);
        pf.deActivateCallOffOrderForCustomer(driver, DataItems.lifeEasyCustomer);

    }

    @Test //Manual Entry Page :: SUMST :: Validation tests, no requester at manual entry page
            (groups = {"eComm","eComm_Orders","QuickTest"})
    public void SUMST9_CONO() throws InterruptedException, IOException, Exception {
        //New driver
        WebDriver driver = getDriver();

        //new base test to handle set up
        Ecomm_Base susstTest8 = new Ecomm_Base(driver);
        //Set up returns an eComm main page
        Ecomm_MainPage eCommPage = susstTest8.setUp("MANUAL ENTRY SUMST9_CONO: Check validation at Manual Entry Page (no requester)","G_OOC_ME_SUSST_Unknown");

        System.out.println("Setup MasterData...");
        //Activate CallOffOrder for customer

        PreFlows pf = new PreFlows();
        pf.chooseTheOtherProfile(driver); //choose CCE for Master Data setup
        pf.activateCallOffOrderForSalesOrg(driver, DataItems.salesOrgID);
        pf.activateCallOffOrderForCustomer(driver, DataItems.lifeEasyCustomer);
        pf.chooseTheOtherProfile(driver); //choose Ecomm for tests
        System.out.println("Navigating to Manual Entry...");

        Ecomm_ManualEntryPage manualEntryPage = eCommPage.clickManualEntry();
        manualEntryPage.waitForElement();

        System.out.println("Manual Entry page reached. Entering customer details...");

        manualEntryPage.setCustomerName(DataItems.custDetails[0]);
        manualEntryPage.setShipToParty(DataItems.custDetails[1]);
        manualEntryPage.setBuyers(DataItems.custDetails[3]);
        //manualEntryPage.setPONumber(DataItems.custDetails[4]);

        pf.selectNormalOrderRadioButton(driver);

        //Take a screenshot
        File scrFile5 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile5,new File(DataItems.screenshotsFilepath+"\\EComm\\Orders\\Manual Entry\\7All but requester entered.png"));

        System.out.println("Customer details entered. Entering line details...");

        manualEntryPage.setArticle(DataItems.conOrdArticle, 0);
        manualEntryPage.setShadeCode(DataItems.conOrdShadeCode,0);
        manualEntryPage.setQty(3, 0);
        manualEntryPage.setDate(0);

        System.out.println("Line details entered. Pressing next expecting failure...");

        Ecomm_ManualEntryPage mePage = manualEntryPage.pressNextExpectingFailure();

        System.out.println("Next pressed");

        boolean errorDisplayed = false;

        try {
            WebElement flashMessage = mePage.waitForError();
            System.out.println("Error received: " + flashMessage.getText());
            errorDisplayed = true;
            //Take a screenshot
            File scrFile4 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile4,new File(DataItems.screenshotsFilepath+"\\EComm\\Orders\\Manual Entry\\8Error received as expected.png"));
        } catch (Exception e) {
            System.out.println("No error dispayed");
            //Take a screenshot
            File scrFile4 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile4,new File(DataItems.screenshotsFilepath+"\\EComm\\Orders\\Manual Entry\\8Error - unexpected result.png"));
        }

        AssertJUnit.assertTrue("Manual Entry Page: No error displayed despite missing mandatory field(s)",errorDisplayed);

        System.out.println("Checking no order has been created...");

        Ecomm_OutstandingOrdersPage outOrder = mePage.clickOutstandingOrders();
        outOrder.waitForLoad();

        System.out.println("Outstanding Orders Page reached. Searching for record...");

        int row = outOrder.getRowAlt(DataItems.lastUsedPO);

        AssertJUnit.assertTrue("Outstanding Orders Page: Order created despite missing mandatory field(s)"
                + "\n Table Row: " + row
                + "\n Customer PO No.: " + DataItems.lastUsedPO,row==-1);

        System.out.println("No order found, as expected. Checking no draft was created...");

        Ecomm_OutstandingOrderDraftPage draftPage = outOrder.clickOutstandingDraft();
        draftPage.waitForLoad();

        System.out.println("Outstanding Order Draft Page reached. Searching for record...");

        String orderNo = draftPage.findDraft(DataItems.lastUsedPO);

        AssertJUnit.assertTrue("Outstanding Order Draft Page: Draft created after order submission failed due to missing mandatory field(s)"
                + "Customer PO No.: "+DataItems.lastUsedPO
                + "Order No.: " + orderNo,orderNo.equals(""));

        //Take a screenshot
        File scrFile4 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile4,new File(DataItems.screenshotsFilepath+"\\EComm\\Orders\\Manual Entry\\9No Draft created.png"));

        System.out.println("No draft created");

        driver.navigate().refresh();

        //Deactivate call off order for customer
        pf.chooseTheOtherProfile(driver);
        pf.deActivateCallOffOrderForCustomer(driver, DataItems.lifeEasyCustomer);

    }

    @Test //Manual Entry Page :: SUMST :: Validation tests, no ship to party name at manual entry page
            (groups = {"eComm","eComm_Orders"})
    public void SUMST10_CONO() throws InterruptedException, IOException, Exception {
        //New driver
        WebDriver driver = getDriver();

        //new base test to handle set up
        Ecomm_Base susstTest8 = new Ecomm_Base(driver);
        //Set up returns an eComm main page
        Ecomm_MainPage eCommPage = susstTest8.setUp("MANUAL ENTRY SUMST10: Check validation at Manual Entry Page (no ship to)","G_OOC_ME_SUSST_Unknown");

        System.out.println("Setup MasterData...");
        //Activate CallOffOrder for customer

        PreFlows pf = new PreFlows();
        pf.chooseTheOtherProfile(driver); //choose CCE for Master Data setup
        pf.activateCallOffOrderForSalesOrg(driver, DataItems.salesOrgID);
        pf.activateCallOffOrderForCustomer(driver, DataItems.lifeEasyCustomer);
        pf.chooseTheOtherProfile(driver); //choose Ecomm for tests
        System.out.println("Navigating to Manual Entry...");

        Ecomm_ManualEntryPage manualEntryPage = eCommPage.clickManualEntry();
        manualEntryPage.waitForElement();

        System.out.println("Manual Entry page reached. Entering customer details...");

        manualEntryPage.setCustomerName(DataItems.custDetails[0]);
        manualEntryPage.setRequestor("Select");
        manualEntryPage.setPONumber(DataItems.custDetails[4]);

        pf.selectNormalOrderRadioButton(driver);

        //Take a screenshot
        File scrFile5 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile5,new File(DataItems.screenshotsFilepath+"\\EComm\\Orders\\Manual Entry\\9All but Ship To entered.png"));

        System.out.println("Customer details entered. Entering line details...");

        manualEntryPage.setArticle(DataItems.conOrdArticle, 0);
        manualEntryPage.setShadeCode(DataItems.conOrdShadeCode,0);
        manualEntryPage.setQty(3, 0);
        manualEntryPage.setDate(0);

        System.out.println("Line details entered. Pressing next expecting failure...");

        Ecomm_ManualEntryPage mePage2 = manualEntryPage.pressNextExpectingFailure();

        boolean errorDisplayed;

        try {
            WebElement flashMessage = mePage2.waitForError();
            System.out.println("Error received: " + flashMessage.getText());

            errorDisplayed = true;

            //Take a screenshot
            File scrFile4 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile4,new File(DataItems.screenshotsFilepath+"\\EComm\\Orders\\Manual Entry\\10Error received as expected.png"));

        } catch (Exception e) {
            System.out.println("No error displayed");
            errorDisplayed = false;

            //Take a screenshot
            File scrFile4 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile4,new File(DataItems.screenshotsFilepath+"\\EComm\\Orders\\Manual Entry\\11Error - unexpected result.png"));

        }

        AssertJUnit.assertTrue("Manual Entry Page: No error displayed despite missing mandatory field(s)", errorDisplayed);

        System.out.println("Checking no order has been created...");

        Ecomm_OutstandingOrdersPage outPage = mePage2.clickOutstandingOrders();
        outPage.waitForElement();

        System.out.println("Outstanding orders page reached. Searching for record...");

        int row = outPage.getRowAlt(DataItems.lastUsedPO);

        AssertJUnit.assertTrue("Outstanding Orders Page: Order created despite missing mandatory field(s)",row==-1);

        System.out.println("No order created, as expected. Checking no draft was created...");

        Ecomm_OutstandingOrderDraftPage draftPage = outPage.clickOutstandingDraft();
        draftPage.waitForLoad();

        System.out.println("Draft page reached. Searching for draft...");

        String orderNo = draftPage.findDraft(DataItems.lastUsedPO);

        AssertJUnit.assertTrue("Outstanding Draft Page: Draft created despite missing mandatory field(s)"
                +"\nCustomer PO No.: " + DataItems.lastUsedPO
                +"\nOrder No.: " + orderNo,orderNo.equals(""));

        System.out.println("No draft found, as expected");

        //Take a screenshot
        File scrFile4 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile4,new File(DataItems.screenshotsFilepath+"\\EComm\\Orders\\Manual Entry\\12Error - order created without shipto.png"));

        driver.navigate().refresh();

        //Deactivate call off order for customer
        pf.chooseTheOtherProfile(driver);
        pf.deActivateCallOffOrderForCustomer(driver, DataItems.lifeEasyCustomer);
    }

    @Test //Manual Entry Page :: SUMST :: Validation tests, no buyer at manual entry page
            (groups = {"eComm","eComm_Orders",})
    public void SUMST11_CONO() throws InterruptedException, IOException, Exception {
        //New driver
        WebDriver driver = getDriver();

        //new base test to handle set up
        Ecomm_Base susstTest8 = new Ecomm_Base(driver);
        //Set up returns an eComm main page
        Ecomm_MainPage eCommPage = susstTest8.setUp("MANUAL ENTRY SUMST1_CONO: Check validation at Manual Entry Page (no buyer)","G_OOC_ME_SUMST_Unknown");

        System.out.println("Setup MasterData...");
        //Activate CallOffOrder for customer

        PreFlows pf = new PreFlows();
        pf.chooseTheOtherProfile(driver); //choose CCE for Master Data setup
        pf.activateCallOffOrderForSalesOrg(driver, DataItems.salesOrgID);
        pf.activateCallOffOrderForCustomer(driver, DataItems.lifeEasyCustomer);
        pf.chooseTheOtherProfile(driver); //choose Ecomm for tests
        System.out.println("Navigating to Manual Entry...");

        Ecomm_ManualEntryPage manualEntryPage = eCommPage.clickManualEntry();

        System.out.println("Manual Entry page reached. Entering customer details...");

        manualEntryPage.setCustomerName(DataItems.custDetails[0]);
        manualEntryPage.setShipToParty(DataItems.custDetails[1]);
        manualEntryPage.setRequestor(DataItems.custDetails[2]);
        //CommonTask.resetSearchField(driver, "s2id_BuyerId");
        manualEntryPage.setPONumber(DataItems.custDetails[4]);

        pf.selectNormalOrderRadioButton(driver);

        //Remove buyers for assert
        pf.removeBuyers(driver);

        //Take a screenshot
        File scrFile5 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile5,new File(DataItems.screenshotsFilepath+"\\EComm\\Orders\\Manual Entry\\13All but buyer entered.png"));

        System.out.println("Customer details entered. Entering line details...");

        manualEntryPage.setArticle(DataItems.conOrdArticle, 0);
        manualEntryPage.setShadeCode(DataItems.conOrdShadeCode,0);
        manualEntryPage.setQty(3, 0);
        manualEntryPage.setDate(0);


        System.out.println("Line details entered. Pressing next expecting failure...");

        Ecomm_ManualEntryPage mePage = manualEntryPage.pressNextExpectingFailure();

        boolean errorDisplayed;

        try {
            WebElement flashMessage = mePage.waitForError();
            errorDisplayed = true;

            System.out.println("Error received: "+flashMessage.getText());

            //Take a screenshot
            File scrFile4 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile4,new File(DataItems.screenshotsFilepath+"\\EComm\\Orders\\Manual Entry\\14Error received as expected.png"));
        } catch (Exception e) {
            errorDisplayed = false;
            System.out.println("No error displayed");
        }

        AssertJUnit.assertTrue("Manual Entry Page: No error displayed despite missing mandatory field(s)", errorDisplayed);

        System.out.println("Checking no order has been created...");

        Ecomm_OutstandingOrdersPage outPage = mePage.clickOutstandingOrders();
        outPage.waitForElement();

        System.out.println("Outstanding orders page reached. Searching for record...");

        int row = outPage.getRowAlt(DataItems.lastUsedPO);

        AssertJUnit.assertTrue("Outstanding Orders Page: Order created despite missing mandatory field(s)",row==-1);

        System.out.println("No order created, as expected. Checking no draft was created...");

        Ecomm_OutstandingOrderDraftPage draftPage = outPage.clickOutstandingDraft();
        draftPage.waitForLoad();

        System.out.println("Draft page reached. Searching for draft...");

        String orderNo = draftPage.findDraft(DataItems.lastUsedPO);

        AssertJUnit.assertTrue("Outstanding Draft Page: Draft created despite missing mandatory field(s)"
                +"\nCustomer PO No.: " + DataItems.lastUsedPO
                +"\nOrder No.: " + orderNo,orderNo.equals(""));

        System.out.println("No draft found, as expected");

    }

    @Test //Manual Entry Page :: SUMST :: Validation tests, no requester at confirmation page
            (groups = {"eComm","eComm_Orders"})
    public void SUMST12_CONO() throws InterruptedException, IOException, Exception {
        //New driver
        WebDriver driver = getDriver();

        //new base test to handle set up
        Ecomm_Base susstTest8 = new Ecomm_Base(driver);
        //Set up returns an eComm main page
        Ecomm_MainPage eCommPage = susstTest8.setUp("MANUAL ENTRY SUMST12: Check validation at Confirmation Page (no requester)","G_OOC_ME_SUSST_Unknown");

        System.out.println("Setup MasterData...");
        //Activate CallOffOrder for customer

        PreFlows pf = new PreFlows();
        pf.chooseTheOtherProfile(driver); //choose CCE for Master Data setup
        pf.activateCallOffOrderForSalesOrg(driver, DataItems.salesOrgID);
        pf.activateCallOffOrderForCustomer(driver, DataItems.lifeEasyCustomer);
        pf.disableApprovelCheckBoxForSalesOrgAndCust(driver, "ID51", DataItems.lifeEasyCustomer);
        pf.chooseTheOtherProfile(driver); //choose Ecomm for tests

        System.out.println("Navigating to Manual Entry...");

        Ecomm_ManualEntryPage manualEntryPage = eCommPage.clickManualEntry();
        manualEntryPage.waitForElement();

        System.out.println("Manual Entry page reached. Entering customer details...");

        manualEntryPage.setCustomerName(DataItems.custDetails[0]);
        manualEntryPage.setShipToParty(DataItems.custDetails[1]);
        manualEntryPage.setRequestor(DataItems.custDetails[2]);
        manualEntryPage.setBuyers(DataItems.custDetails[3]);
        manualEntryPage.setPONumber(DataItems.custDetails[4]);

        pf.selectNormalOrderRadioButton(driver);

        System.out.println("Customer details entered. Entering line details...");

        manualEntryPage.setArticle(DataItems.conOrdArticle, 0);
        manualEntryPage.setShadeCode(DataItems.conOrdShadeCode,0);
        manualEntryPage.setQty(3, 0);
        manualEntryPage.setDate(0);

        System.out.println("Line details entered. Pressing next...");

        Ecomm_OrderConfirmationPage orderConf = manualEntryPage.pressNext();
        orderConf.waitForElement();

        System.out.println("Confirmation page reached, removing requestor...");

        orderConf.setRequestor("Select");

        //Take a screenshot
        File scrFile4 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile4,new File(DataItems.screenshotsFilepath+"\\EComm\\Orders\\Manual Entry\\16Requestor blank.png"));

        System.out.println("Requestor removed. Confirming...");

        Ecomm_UploadProcessPage errorPage = orderConf.pressSubmitExpectingFailure();


        boolean errorDisplayed;

        try {
            Alert alert = Wait.alert(driver);
            alert.accept();

            WebElement flashMessage = errorPage.waitForError();
            System.out.println("Error received: "+flashMessage.getText());
            errorDisplayed = true;
        } catch (Exception e) {
            System.out.println("No error displayed");
            errorDisplayed = false;
        }



        AssertJUnit.assertTrue("Order Confirmation Page: No error displayed despite missing mandatory field(s)",errorDisplayed);

        System.out.println("Checking no order was created...");

        Ecomm_OutstandingOrdersPage outPage = errorPage.clickOutstandingOrders();
        outPage.waitForLoad();

        System.out.println("Outstanding orders page reached. Searching for record...");

        int row = outPage.getRowAlt(DataItems.lastUsedPO);

        AssertJUnit.assertTrue("Outstanding Orders Page: Order created despite missing mandatory field(s)",row==-1);

        System.out.println("No order created, as expected. Checking no draft was created...");

        Ecomm_OutstandingOrderDraftPage draftPage = outPage.clickOutstandingDraft();
        draftPage.waitForLoad();

        System.out.println("Draft page reached. Searching for draft...");

        String orderNo = draftPage.findDraft(DataItems.lastUsedPO);

        AssertJUnit.assertTrue("Outstanding Draft Page: Draft not created despite confirmation page reached"
                +"\nCustomer PO No.: " + DataItems.lastUsedPO
                +"\nOrder No.: " + orderNo,(!orderNo.equals("")));

        System.out.println("Draft found, as expected");

        driver.navigate().refresh();

        //Deactivate call off order for customer
        pf.chooseTheOtherProfile(driver);
        pf.deActivateCallOffOrderForCustomer(driver, DataItems.lifeEasyCustomer);
    }

    @Test //Manual Entry Page :: SUMST :: Validation tests, no Ship To Party Name at confirmation page
            (groups = {"eComm","eComm_Orders","QuickTest"})
    public void SUMST13_CONO() throws InterruptedException, IOException, Exception {

        //New driver
        WebDriver driver = getDriver();

        //new base test to handle set up
        Ecomm_Base susstTest8 = new Ecomm_Base(driver);
        //Set up returns an eComm main page
        Ecomm_MainPage eCommPage = susstTest8.setUp("MANUAL ENTRY SUMST13_CONO: Check validation at Confirmation Page (no ship to)","G_OOC_ME_SUSST_Unknown");

        System.out.println("Setup MasterData...");
        //Activate CallOffOrder for customer

        PreFlows pf = new PreFlows();
        pf.chooseTheOtherProfile(driver); //choose CCE for Master Data setup
        pf.activateCallOffOrderForSalesOrg(driver, DataItems.salesOrgID);
        pf.activateCallOffOrderForCustomer(driver, DataItems.lifeEasyCustomer);
        pf.chooseTheOtherProfile(driver); //choose Ecomm for tests

        System.out.println("Navigating to Manual Entry...");

        Ecomm_ManualEntryPage manualEntryPage = eCommPage.clickManualEntry();
        manualEntryPage.waitForElement();

        System.out.println("Manual Entry page reached. Entering customer details...");

        manualEntryPage.setCustomerName(DataItems.custDetails[0]);
        manualEntryPage.setShipToParty(DataItems.custDetails[1]);
        manualEntryPage.setRequestor(DataItems.custDetails[2]);
        manualEntryPage.setBuyers(DataItems.custDetails[3]);
        manualEntryPage.setPONumber(DataItems.custDetails[4]);

        pf.selectNormalOrderRadioButton(driver);
        
        System.out.println("Customer PO used: "+DataItems.lastUsedPO);

        System.out.println("Customer details entered. Entering line details...");

        manualEntryPage.setArticle(DataItems.conOrdArticle, 0);
        manualEntryPage.setShadeCode(DataItems.conOrdShadeCode,0);
        manualEntryPage.setQty(3, 0);
        manualEntryPage.setDate(0);

        System.out.println("Line details entered. Pressing next...");

        Ecomm_OrderConfirmationPage orderConf = manualEntryPage.pressNext();

//        Alert alert = Wait.alert(driver);
        //      alert.accept();

        orderConf.waitForElement();

        System.out.println("Confirmation page reached, removing Ship To Party Name...");

        orderConf.setShipToParty("Select");

        //Take a screenshot
        File scrFile4 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile4,new File(DataItems.screenshotsFilepath+"\\EComm\\Orders\\Manual Entry\\19Ship To Party blank.png"));

        System.out.println("Ship To Party removed. Confirming...");

        Ecomm_UploadProcessPage errorPage = orderConf.pressSubmitExpectingFailure();

        boolean errorDisplayed;

        try {
            Alert alert2 = Wait.alert(driver);
            alert2.accept();
            WebElement flashMessage = errorPage.waitForError();


            System.out.println("Error received: "+flashMessage.getText());
            errorDisplayed = true;
        } catch (Exception e) {
            System.out.println("No error displayed");
            errorDisplayed = false;
        }

        AssertJUnit.assertTrue("Order Confirmation Page: No error displayed despite missing mandatory field(s)",errorDisplayed);

        System.out.println("Checking no order was created...");

        Ecomm_OutstandingOrdersPage outPage = errorPage.clickOutstandingOrders();
        outPage.waitForLoad();

        System.out.println("Outstanding orders page reached. Searching for record...");

        int row = outPage.getRowAlt(DataItems.lastUsedPO);

        AssertJUnit.assertTrue("Outstanding Orders Page: Order created despite missing mandatory field(s)",row==-1);

        System.out.println("No order created, as expected. Checking no draft was created...");

        Ecomm_OutstandingOrderDraftPage draftPage = outPage.clickOutstandingDraft();
        draftPage.waitForLoad();

        System.out.println("Draft page reached. Searching for draft...");

        String orderNo = draftPage.findDraft(DataItems.lastUsedPO);

        AssertJUnit.assertTrue("Outstanding Draft Page: Draft not created despite confirmation page reached"
                +"\nCustomer PO No.: " + DataItems.lastUsedPO
                +"\nOrder No.: " + orderNo,(!orderNo.equals("")));

        System.out.println("Draft found, as expected");

        driver.navigate().refresh();

        //Deactivate call off order for customer
        pf.chooseTheOtherProfile(driver);
        pf.deActivateCallOffOrderForCustomer(driver, DataItems.lifeEasyCustomer);
    }

    @Test //Manual Entry Page :: SUMST :: Validation tests, no Buyer at confirmation page
            (groups = {"eComm","eComm_Orders"})
    public void SUMST14_CONO() throws InterruptedException, IOException, Exception {
        //New driver
        WebDriver driver = getDriver();

        //new base test to handle set up
        Ecomm_Base susstTest8 = new Ecomm_Base(driver);
        //Set up returns an eComm main page
        Ecomm_MainPage eCommPage = susstTest8.setUp("MANUAL ENTRY SUMST14_CONO: Check validation at Confirmation Page (no buyer)","G_OOC_ME_SUMST_Unknown");

        System.out.println("Setup MasterData...");
        //Activate CallOffOrder for customer

        PreFlows pf = new PreFlows();
        pf.chooseTheOtherProfile(driver); //choose CCE for Master Data setup
        pf.activateCallOffOrderForSalesOrg(driver, DataItems.salesOrgID);
        pf.activateCallOffOrderForCustomer(driver, DataItems.lifeEasyCustomer);
        pf.chooseTheOtherProfile(driver); //choose Ecomm for tests
        System.out.println("Navigating to Manual Entry...");

        Ecomm_ManualEntryPage manualEntryPage = eCommPage.clickManualEntry();
        manualEntryPage.waitForElement();

        System.out.println("Manual Entry page reached. Entering customer details...");

        manualEntryPage.setCustomerName(DataItems.custDetails[0]);
        manualEntryPage.setShipToParty(DataItems.custDetails[1]);
        manualEntryPage.setRequestor(DataItems.custDetails[2]);
        manualEntryPage.setBuyers(DataItems.custDetails[3]);
        manualEntryPage.setPONumber(DataItems.custDetails[4]);

        pf.selectNormalOrderRadioButton(driver);
        
        System.out.println("Customer PO used: "+DataItems.lastUsedPO);

        System.out.println("Customer details entered. Entering line details...");

        manualEntryPage.setArticle(DataItems.conOrdArticle, 0);
        manualEntryPage.setShadeCode(DataItems.conOrdShadeCode,0);
        manualEntryPage.setQty(3, 0);
        manualEntryPage.setDate(0);

        System.out.println("Line details entered. Pressing next...");

        Ecomm_OrderConfirmationPage orderConf = manualEntryPage.pressNext();
        orderConf.waitForElement();

        System.out.println("Confirmation page reached, removing Buyer...");

        CommonTask.resetSearchField(driver, "s2id_BuyerId_0");

        //Take a screenshot
        File scrFile4 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile4,new File(DataItems.screenshotsFilepath+"\\EComm\\Orders\\Manual Entry\\22Buyer blank.png"));

        System.out.println("Buyer removed. Confirming...");

        Ecomm_UploadProcessPage errorPage = orderConf.pressSubmitExpectingFailure();

        boolean errorDisplayed;

        try {
            Alert alert2 = Wait.alert(driver);
            alert2.accept();
            WebElement flashMessage = errorPage.waitForError();


            System.out.println("Error received: "+flashMessage.getText());
            errorDisplayed = true;
        } catch (Exception e) {
            System.out.println("No error displayed");
            errorDisplayed = false;
        }

        AssertJUnit.assertTrue("Order Confirmation Page: No error displayed despite missing mandatory field(s)",errorDisplayed);

        System.out.println("Checking no order was created...");

        Ecomm_OutstandingOrdersPage outPage = errorPage.clickOutstandingOrders();
        outPage.waitForLoad();

        System.out.println("Outstanding orders page reached. Searching for record...");

        int row = outPage.getRowAlt(DataItems.lastUsedPO);

        AssertJUnit.assertTrue("Outstanding Orders Page: Order created despite missing mandatory field(s)",row==-1);

        System.out.println("No order created, as expected. Checking no draft was created...");

        Ecomm_OutstandingOrderDraftPage draftPage = outPage.clickOutstandingDraft();
        draftPage.waitForLoad();

        System.out.println("Draft page reached. Searching for draft...");

        String orderNo = draftPage.findDraft(DataItems.lastUsedPO);

        AssertJUnit.assertTrue("Outstanding Draft Page: Draft not created despite confirmation page reached"
                +"\nCustomer PO No.: " + DataItems.lastUsedPO
                +"\nOrder No.: " + orderNo,(!orderNo.equals("")));

        System.out.println("Draft found, as expected");

        driver.navigate().refresh();

        //Deactivate call off order for customer
        pf.chooseTheOtherProfile(driver);
        pf.deActivateCallOffOrderForCustomer(driver, DataItems.lifeEasyCustomer);
    }

    @Test //Manual Entry Page :: SUMST :: Create order and cancel from confirmation page
            (groups = {"eComm","eComm_Orders"})
    public void SUMST15_CONO() throws InterruptedException, IOException, Exception {

        //New driver
        WebDriver driver = getDriver();

        //new base test to handle set up
        Ecomm_Base susstTest8 = new Ecomm_Base(driver);
        //Set up returns an eComm main page
        Ecomm_MainPage eCommPage = susstTest8.setUp("MANUAL ENTRY SUMST15_CONO: Draft creation/order simulation - check draft details and cancel","G_OOC_ME_SUSST_Unknown");


        System.out.println("Activating MOQ...");

        //Activate MOQ for customer and CallOffOrder
        PreFlows pf = new PreFlows();
        pf.chooseTheOtherProfile(driver);           //choose CCE page
        pf.activateCallOffOrderForSalesOrg(driver, DataItems.salesOrgID);
        pf.enableMOQForCustomer(driver, DataItems.lifeEasyCustomer);
        pf.activateCallOffOrderForCustomer(driver, DataItems.lifeEasyCustomer);
        pf.chooseTheOtherProfile(driver);           //choose ecomm page

        System.out.println("Navigating to Manual Entry...");

        Ecomm_ManualEntryPage manualEntryPage = eCommPage.clickManualEntry();
        manualEntryPage.waitForElement();

        System.out.println("Manual Entry page reached. Entering customer details...");

        manualEntryPage.setCustomerName(DataItems.custDetails[0]);
        manualEntryPage.setShipToParty(DataItems.custDetails[1]);
        manualEntryPage.setRequestor(DataItems.custDetails[2]);
        manualEntryPage.setBuyers(DataItems.custDetails[3]);
        manualEntryPage.setPONumber(DataItems.custDetails[4]);

        pf.selectNormalOrderRadioButton(driver);
        
        System.out.println("Customer PO used: "+DataItems.lastUsedPO);

        System.out.println("Customer details entered. Entering line details...");

        manualEntryPage.setYourMaterialNumber(DataItems.yourMatNum, 0);
        manualEntryPage.setQty(3, 0);
        manualEntryPage.setDate(0);

        //Take a screenshot
        File scrFile7 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile7,new File(DataItems.screenshotsFilepath+"\\EComm\\Orders\\Manual Entry\\28Order details entered.png"));

        String date = manualEntryPage.getDate(0);

        System.out.println("Line details entered. Pressing next...");

        Ecomm_OrderConfirmationPage orderConf = manualEntryPage.pressNext();
        orderConf.waitForElement();

        System.out.println("Confirmation page reached. Checking details...");

        Verify.verify(orderConf.getYourMatNum().equals(DataItems.yourMatNum),"Order Confirmation Page: Your Material Number does not match input");
        Verify.verify(orderConf.getOrderedQty() == 3,"Order Confirmation Page: Ordered Quantity does not match input");
        Verify.verify(orderConf.getRequiredDate().equals(date),"Order Confirmation Page: Required Date does not match input");

        //Take a screenshot
        File scrFile8 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile8,new File(DataItems.screenshotsFilepath+"\\EComm\\Orders\\Manual Entry\\29Confirmation page reached.png"));

        Actions scroller = new Actions(driver);
        scroller.moveToElement(orderConf.getCancelButton()).build().perform();

        WebElement waitForVis = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.visibilityOf(orderConf.getCancelButton()));

        //Take a screenshot
        File scrFile9 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile9,new File(DataItems.screenshotsFilepath+"\\EComm\\Orders\\Manual Entry\\30Confirmation page reached scrolled.png"));


        System.out.println("Details checked. Cancelling order...");

        Ecomm_ManualEntryPage mePage = orderConf.pressCancel();
        mePage.waitForElement();

        //Take a screenshot
        File scrFile10 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile10,new File(DataItems.screenshotsFilepath+"\\EComm\\Orders\\Manual Entry\\31Order cancelled.png"));

        System.out.println("Order cancelled. Checking no draft was saved...");

        Ecomm_OutstandingOrderDraftPage draftPage = mePage.clickOutstandingDraft();
        draftPage.waitForElement();

        System.out.println("Draft page reached.");

        //Take a screenshot
        File scrFile11 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile11,new File(DataItems.screenshotsFilepath+"\\EComm\\Orders\\Manual Entry\\32Draft page (no draft expected).png"));

        String orderNo = draftPage.findDraft(DataItems.lastUsedPO);

        if (orderNo.equals("")) {
            System.out.println("Draft not created, as expected.");
        } else {
            System.out.println("***ORDER DRAFT UNEXPECTEDLY CREATED***");
        }

        driver.navigate().refresh();

        //Deactivate call off order for customer
        pf.chooseTheOtherProfile(driver);
        pf.deActivateCallOffOrderForCustomer(driver, DataItems.lifeEasyCustomer);
    }

    @Test //Manual Entry Page :: SUMST :: Order Draft continuation and cancellation
            (groups = {"eComm","eComm_Orders"})
    public void SUMST16_CONO() throws InterruptedException, IOException, Exception {
        //New driver
        WebDriver driver = getDriver();

        //new base test to handle set up
        Ecomm_Base susstTest8 = new Ecomm_Base(driver);
        //Set up returns an eComm main page
        Ecomm_MainPage eCommPage = susstTest8.setUp("MANUAL ENTRY SUMST1_CONO6_CONO: Draft creation/order simulation - cancelling saved draft","G_OOC_ME_SUMST_Unknown");

        PreFlows pf = new PreFlows();
        pf.chooseTheOtherProfile(driver); //choose CCE for Master Data setup
        pf.activateCallOffOrderForSalesOrg(driver, DataItems.salesOrgID);
        pf.activateCallOffOrderForCustomer(driver, DataItems.lifeEasyCustomer);
        pf.chooseTheOtherProfile(driver); //choose Ecomm for tests

        System.out.println("Navigating to Manual Entry Page...");

        Ecomm_ManualEntryPage manualEntryPage = eCommPage.clickManualEntry();
        manualEntryPage.waitForElement();

        System.out.println("Manual Entry page reached. Entering customer details...");

        manualEntryPage.setCustomerName(DataItems.custDetails[0]);
        manualEntryPage.setShipToParty(DataItems.custDetails[1]);
        manualEntryPage.setRequestor(DataItems.custDetails[2]);
        manualEntryPage.setBuyers(DataItems.custDetails[3]);
        manualEntryPage.setPONumber(DataItems.custDetails[4]);

        pf.selectNormalOrderRadioButton(driver);
        
        System.out.println("Customer PO used: "+DataItems.lastUsedPO);

        System.out.println("Customer details entered. Entering line details...");

        manualEntryPage.setYourMaterialNumber(DataItems.yourMatNum, 0);
        manualEntryPage.setQty(3, 0);
        manualEntryPage.setDate(0);

        System.out.println("Line details entered. Pressing next...");

        Ecomm_OrderConfirmationPage orderConf3 = manualEntryPage.pressNext();
        orderConf3.waitForElement();

        System.out.println("Order confirmation reached. Saving draft...");

        Ecomm_OutstandingOrderDraftPage draftPage = orderConf3.pressSaveDraft();
        draftPage.waitForElement();

        System.out.println("Outstanding Order Draft page reached. Editing top draft...");

        Ecomm_ManualEntryPage mePage2 = draftPage.pressEdit();
        mePage2.waitForElement();

        //Take a screenshot
        File scrFile11 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile11,new File(DataItems.screenshotsFilepath+"\\EComm\\Orders\\Manual Entry\\33Draft open.png"));

        DataItems.lastUsedPO = mePage2.getCustPONo();

        Ecomm_OrderConfirmationPage orderConf = mePage2.pressNext();
        orderConf.waitForElement();

        System.out.println("Order confirmation page reached. Cancelling order draft...");

        Ecomm_ManualEntryPage mePage3 = orderConf.pressCancel();
        mePage3.waitForElement();

        //Take a screenshot
        File scrFile12 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile12,new File(DataItems.screenshotsFilepath+"\\EComm\\Orders\\Manual Entry\\34Draft cancelled.png"));

        System.out.println("Draft cancelled. Checking draft is deleted...");

        Ecomm_OutstandingOrderDraftPage draftPage2 = mePage3.clickOutstandingDraft();
        draftPage2.waitForElement();

        //Take a screenshot
        File scrFile13 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile13,new File(DataItems.screenshotsFilepath+"\\EComm\\Orders\\Manual Entry\\35Draft removed.png"));

        String orderNo = draftPage2.findDraft(DataItems.lastUsedPO);

        if (orderNo.equals("")) {
            System.out.println("No draft found, as expected");
        } else {
            System.out.println("***ORDER DRAFT NOT DELETED***");
        }

        driver.navigate().refresh();

        //Deactivate call off order for customer
        pf.chooseTheOtherProfile(driver);
        pf.deActivateCallOffOrderForCustomer(driver, DataItems.lifeEasyCustomer);
    }

    @Test //Manual Entry Page :: SUMST :: Order Draft continuation
            (groups = {"eComm","eComm_Orders","QuickTest"})
    public void SUMST17_CONO() throws InterruptedException, Exception {
        //New driver
        WebDriver driver = getDriver();

        //new base test to handle set up
        Ecomm_Base susstTest8 = new Ecomm_Base(driver);
        //Set up returns an eComm main page
        Ecomm_MainPage eCommPage = susstTest8.setUp("MANUAL ENTRY SUMST17: Draft creation/order simulation","G_OOC_ME_SUMST_Unknown");

        PreFlows pf = new PreFlows();
        pf.chooseTheOtherProfile(driver); //choose CCE for Master Data setup
        pf.activateCallOffOrderForSalesOrg(driver, DataItems.salesOrgID);
        pf.activateCallOffOrderForCustomer(driver, DataItems.lifeEasyCustomer);
        pf.chooseTheOtherProfile(driver); //choose Ecomm for tests

        System.out.println("Navigating to Manual Entry...");

        Ecomm_ManualEntryPage manualEntryPage = eCommPage.clickManualEntry();
        manualEntryPage.waitForElement();

        System.out.println("Manual Entry page reached. Entering customer details...");

        manualEntryPage.setCustomerName(DataItems.custDetails[0]);
        manualEntryPage.setShipToParty(DataItems.custDetails[1]);
        manualEntryPage.setRequestor(DataItems.custDetails[2]);
        manualEntryPage.setBuyers(DataItems.custDetails[3]);
        manualEntryPage.setPONumber(DataItems.custDetails[4]);

        pf.selectNormalOrderRadioButton(driver);
        
        System.out.println("Customer PO used: "+DataItems.lastUsedPO);

        System.out.println("Customer details entered. Entering line details...");

        manualEntryPage.setYourMaterialNumber(DataItems.yourMatNum, 0);
        manualEntryPage.setQty(3, 0);
        manualEntryPage.setDate(0);

        String date = manualEntryPage.getDate(0);

        System.out.println("Line details entered. Pressing next...");

        Ecomm_OrderConfirmationPage orderConf = manualEntryPage.pressNext();
        orderConf.waitForElement();

        System.out.println("Confirmation page reached. Saving as draft...");

        Actions scroller = new Actions(driver);
        scroller.moveToElement(orderConf.getCancelButton()).build().perform();

        WebElement waitForVis = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.visibilityOf(orderConf.getCancelButton()));

        Ecomm_OutstandingOrderDraftPage draftPage = orderConf.pressSaveDraft();
        draftPage.waitForElement();

        System.out.println("Draft saved. Continuing draft...");

        Ecomm_ManualEntryPage mePage = draftPage.pressEdit();
        mePage.waitForElement();

        System.out.println("Manual Entry Page reached. Checking details are consistent with input...");

        Verify.verify(mePage.getCustomerName().equals(DataItems.custDetails[0]),"Order Draft Edit: Customer name not consistent with input");
        Verify.verify(mePage.getShipToName().equals(DataItems.custDetails[1]),"Order Draft Edit: Ship To Name not consistent with input");
        Verify.verify(mePage.getRequestorName().equals(DataItems.custDetails[2]),"Order Draft Edit: Requester Name not consistent with input");
        Verify.verify(mePage.getBuyer().equals(DataItems.custDetails[3]),"Order Draft Edit: Buyers not consistent with input");
        Verify.verify(mePage.getCustPONo().equals(DataItems.lastUsedPO),"Order Draft Edit: Customer PO No. not consistent with input");
        Verify.verify(mePage.getYourMatNum(0).equals(DataItems.yourMatNum),"Order Draft Edit: Your Material Number not consistent with input");
        Verify.verify(mePage.getQuantity(0).equals("3"),"Order Draft Edit: Quantity not consistent with input");
        Verify.verify(mePage.getDate(0).equals(date),"Order Draft Edit: Date not consistent with input");

        System.out.println("Details checked.");

        driver.navigate().refresh();

        //Deactivate call off order for customer
        pf.chooseTheOtherProfile(driver);
        pf.deActivateCallOffOrderForCustomer(driver, DataItems.lifeEasyCustomer);
    }

    @Test //Manual Entry Page :: SUMST :: Sub-account test. Field appears and data included in Flat File
            (groups ={"eComm","eComm_Orders"}) //Konwn issue: order does not appear in pending approval page as it takes a while to be processed. Fix: wait and reload page, then check for order again
    public void SUMST18_CONO() throws Exception {
        //New driver
        WebDriver driver = getDriver();

        //new base test to handle set up
        Ecomm_Base susstTest8 = new Ecomm_Base(driver);
        //Set up returns an eComm main page
        Ecomm_MainPage eCommPage = susstTest8.setUp("MANUAL ENTRY SUSST18: Sub-account availability and flat file inclusion","ME_SA_01");

        System.out.println("Set Delivery Plant to select and activate approver checkbox...");
        PreFlows pf = new PreFlows();
        pf.chooseTheOtherProfile(driver); //change to CCE page
        pf.setDeliveryPlantAndEnableApprovelCheckboxForSalesOrgAndCust(driver,DataItems.salesOrgID, "Angler Test Indonesia", "Select");
        pf.activateCallOffOrderForCustomer(driver, "Angler Test Indonesia");
        pf.chooseTheOtherProfile(driver); //change to ecomm page


        System.out.println("Navigating to Manual Entry...");

        Ecomm_ManualEntryPage manualEntryPage = eCommPage.clickManualEntry();
        manualEntryPage.waitForElement();

        System.out.println("Manual Entry page reached. Entering customer details...");

        manualEntryPage.setCustomerName(DataItems.subCustDetails[0]);
        manualEntryPage.setShipToParty(DataItems.subCustDetails[1]);
        manualEntryPage.setRequestor(DataItems.subCustDetails[2]);
        manualEntryPage.setBuyers(DataItems.subCustDetails[3]);
        manualEntryPage.setPONumber(DataItems.custDetails[4]);

        pf.selectNormalOrderRadioButton(driver);
        
        System.out.println("Customer PO used: "+DataItems.lastUsedPO);

        System.out.println("Customer details entered. Checking for sub-account field...");

        AssertJUnit.assertTrue("Manual Entry Page: Sub-account field not displayed",manualEntryPage.getSubAccountField().isDisplayed());

        System.out.println("Sub-account field displayed. Entering sub-account...");

        manualEntryPage.setSubAccount(DataItems.subAccount);

        System.out.println("Sub-account entered. Entering line details...");

        manualEntryPage.setArticle(DataItems.article, 0);
        manualEntryPage.setShadeCode(DataItems.shadeCode,0);
        manualEntryPage.setQty(3, 0);
        manualEntryPage.setDate(0);

        String date = manualEntryPage.getDate(0);

        System.out.println("Line details entered. Pressing next...");

        Ecomm_OrderConfirmationPage orderConf = manualEntryPage.pressNext();
        orderConf.waitForElement();

        System.out.println("Order confirmation page reached. Checking sub-account field appears and is consistent...");

        AssertJUnit.assertTrue("Order Confirmation Page: Sub-account field does not appear",orderConf.getSubAccountField().isDisplayed());

        AssertJUnit.assertTrue("Order Confirmation Page: Sub-account field does not maintain value after simulation",orderConf.getSubAccount().equals(DataItems.subAccount));

        System.out.println("Sub-account field consistent. Sending for Approval...");

        Ecomm_PendingApprovalListPage appPage = orderConf.pressSendForApproval();
        appPage.waitForElement();

        System.out.println("Pending Approval Page reached. Checking order appears...");

        int row = appPage.getRow(DataItems.lastUsedPO);
        AssertJUnit.assertFalse("Pending Approval List Page: Order does not appear after Send for Approval pressed",row==-1);

        String orderNo = appPage.getOrderNoSUMST(row);

        System.out.println("Order appears. Order No.: " + orderNo);

        System.out.println("Logging in to approver account to approve order...");

        WBA_LoginPage liPage = appPage.pressLogout();
        liPage.waitForElement();

        WBA_ContinuePage contPage = liPage.loginAs(DataItems.approverUsername, DataItems.approverPassword);
        WBA_SelectionPage selectPage = contPage.pressContinue();
        Ecomm_MainPage mainPage = selectPage.pressEcomm();
        mainPage.waitForLoad();

        System.out.println("Logged in. Navigating to Pending Approval List Page...");

        Ecomm_PendingApprovalListPage pendPage = mainPage.clickPendingApprovalListPageApprover();
        pendPage.waitForElement();

        System.out.println("Pending Approval page reached. Finding order...");

        AssertJUnit.assertTrue("Pending Approval Page: Order (Order No.: "+orderNo+") not approved.",pendPage.approveOrder(orderNo));

        System.out.println("Order approved. Logging into other account to view SAP Log...");

        WBA_LoginPage liPage2 = pendPage.pressLogout();
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

        driver.navigate().refresh();

        //Deactivate call off order for customer
        pf.chooseTheOtherProfile(driver);
        pf.deActivateCallOffOrderForCustomer(driver, DataItems.lifeEasyCustomer);
    }

    @Test //Manual Entry Page :: SUMST :: Send for approval feature activated
            (groups = {"eComm","eComm_Orders"})
    public void SUMST19_CONO() throws Exception {
        //New driver
        WebDriver driver = getDriver();

        //new base test to handle set up
        Ecomm_Base susstTest8 = new Ecomm_Base(driver);
        //Set up returns an eComm main page
        Ecomm_MainPage eCommPage = susstTest8.setUp("MANUAL ENTRY SUMST19_CONO: Send for approval feature","G_OOC_ME_SUSST_MOQ_1");

        System.out.println("Set Delivery Plant to select and activate approver checkbox...");
        PreFlows pf = new PreFlows();
        pf.chooseTheOtherProfile(driver); //change to CCE page
        pf.activateCallOffOrderForSalesOrg(driver, DataItems.salesOrgID);
        pf.setDeliveryPlantAndEnableApprovelCheckboxForSalesOrgAndCust(driver,DataItems.salesOrgID,"Angler Test Indonesia", "Select");
        pf.activateCallOffOrderForCustomer(driver, DataItems.lifeEasyCustomer);
        pf.chooseTheOtherProfile(driver); //change to ecomm page

        System.out.println("Navigating to Manual Entry...");

        Ecomm_ManualEntryPage manualEntryPage = eCommPage.clickManualEntry();
        manualEntryPage.waitForElement();

        System.out.println("Manual Entry page reached. Entering customer details...");

        manualEntryPage.setCustomerName(DataItems.subCustDetails[0]);
        manualEntryPage.setShipToParty(DataItems.subCustDetails[1]);
        manualEntryPage.setBuyers(DataItems.subCustDetails[3]);
        manualEntryPage.setRequestor(DataItems.subCustDetails[2]);
        manualEntryPage.setPONumber(DataItems.custDetails[4]);

        pf.selectNormalOrderRadioButton(driver);

        System.out.println("Details entered. Entering line details...");

        manualEntryPage.setArticle(DataItems.article, 0);
        manualEntryPage.setShadeCode(DataItems.expShadeCode, 0);
        manualEntryPage.setQty(6, 0);
        manualEntryPage.setDate(0);

        System.out.println("Details entered. Pressing next...");

        Ecomm_OrderConfirmationPage orderConf = manualEntryPage.pressNext();
        orderConf.waitForElement();

        System.out.println("Order confirmation page reached. Checking 'Send for Approval' button appears...");

        AssertJUnit.assertTrue("Order Confirmation Page: Send for Approval button not displayed as expected",orderConf.checkSendForApproval());

        System.out.println("Button correctly displayed.");

    }

    @Test //Manual Entry Page :: SUMST :: Single-line, order using YMN with master shade (MOQ ACTIVE)
            (groups = {"eComm","eComm_Orders"})
    public void SUMST20_CONO() throws Exception {
        //New chrome driver
        WebDriver driver = getDriver();

        //new base test to handle set up
        Ecomm_Base base = new Ecomm_Base(driver);
        //Set up returns a manual entry page to begin data entry
        Ecomm_MainPage eCommPage = base.setUp("MANUAL ENTRY SUMST20_CONO: Single line, Your Material Number and master shade(MOQ ACTIVE)","G_OOC_ME_SUMST_MOQ_1");

        PreFlows pf = new PreFlows();
        pf.chooseTheOtherProfile(driver); //choose CCE for Master Data setup
        pf.activateCallOffOrderForSalesOrg(driver, DataItems.salesOrgID);
        pf.enableMOQForCustomer(driver, DataItems.lifeEasyCustomer);
        pf.activateCallOffOrderForCustomer(driver, DataItems.lifeEasyCustomer);
        pf.chooseTheOtherProfile(driver); //choose Ecomm for tests

        System.out.println("Navigating to Manual Entry...");

        //press manual entry
        Ecomm_ManualEntryPage manualEntryPage = eCommPage.clickManualEntry();
        manualEntryPage.waitForElement();

        System.out.println("Manual Entry Page reached. Entering customer details...");

        //Input Customer Details
        manualEntryPage.setCustomerName(DataItems.custDetails[0]);
        manualEntryPage.setShipToPartyWithWait(DataItems.custDetails[1]);
        manualEntryPage.setRequestor(DataItems.custDetails[2]);
        manualEntryPage.setBuyers(DataItems.custDetails[3]);
        manualEntryPage.setPONumber(DataItems.custDetails[4]);

        pf.selectNormalOrderRadioButton(driver);

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
        Ecomm_OrderConfirmationPage orderConf = manualEntryPage.pressNextMOQ();
        orderConf.waitForElement();

        System.out.println("MOQ Alert appeared as expected.");

        System.out.println("Order confirmation page reached. Checking details are maintained...");

        AssertJUnit.assertTrue("Order Confirmation Page: Customer PO Number not maintained after manual entry page",orderConf.getUploadPONumber().equals(DataItems.lastUsedPO));

        AssertJUnit.assertTrue("Order Confirmation Page: Customer Name not maintained after manual entry page",orderConf.getCustomerName().equals(DataItems.custDetails[0]));
        AssertJUnit.assertTrue("Order Confirmation Page: Ship To Party not maintained after manual entry page",orderConf.getShipToParty().equals(DataItems.custDetails[1]));
        AssertJUnit.assertTrue("Order Confirmation Page: Requester not maintained after manual entry page",orderConf.getRequester().equals(DataItems.custDetails[2]));
        AssertJUnit.assertTrue("Order Confirmation Page: Buyers not maintained after manual entry page",orderConf.getBuyers().equals(DataItems.custDetails[3]));

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

        driver.navigate().refresh();

        //Deactivate call off order for customer
        pf.chooseTheOtherProfile(driver);
        pf.deActivateCallOffOrderForCustomer(driver, DataItems.lifeEasyCustomer);
    }

    @Test //Manual Entry Page :: SUMST :: Multi-line, order using YMN with master shade (MOQ ACTIVE)
            (groups = {"eComm","eComm_Orders"})
    public void SUMST21_CONO() throws Exception {
        //New chrome driver
        WebDriver driver = getDriver();

        //new base test to handle set up
        Ecomm_Base base = new Ecomm_Base(driver);
        //Set up returns a manual entry page to begin data entry
        Ecomm_MainPage eCommPage = base.setUp("MANUAL ENTRY SUMST21_CONO: Multi line, Your Material Number and master shade(MOQ ACTIVE)","G_OOC_ME_SUMST_MOQ_2");

        PreFlows pf = new PreFlows();
        pf.chooseTheOtherProfile(driver); //choose CCE for Master Data setup
        pf.activateCallOffOrderForSalesOrg(driver, DataItems.salesOrgID);
        pf.enableMOQForCustomer(driver, DataItems.lifeEasyCustomer);
        pf.activateCallOffOrderForCustomer(driver, DataItems.lifeEasyCustomer);
        pf.chooseTheOtherProfile(driver); //choose Ecomm for tests

        System.out.println("Navigating to Manual Entry...");

        //press manual entry
        Ecomm_ManualEntryPage manualEntryPage = eCommPage.clickManualEntry();
        manualEntryPage.waitForElement();

        System.out.println("Manual Entry Page reached. Entering customer details...");

        //Input Customer Details
        manualEntryPage.setCustomerName(DataItems.custDetails[0]);
        manualEntryPage.setShipToPartyWithWait(DataItems.custDetails[1]);
        manualEntryPage.setRequestor(DataItems.custDetails[2]);
        manualEntryPage.setBuyers(DataItems.custDetails[3]);
        manualEntryPage.setPONumber(DataItems.custDetails[4]);

        pf.selectNormalOrderRadioButton(driver);

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
        Ecomm_OrderConfirmationPage orderConf = manualEntryPage.pressNextMOQ();
        orderConf.waitForElement();

        System.out.println("MOQ Alert appeared as expected.");

        System.out.println("Order confirmation page reached. Checking details are maintained...");

        AssertJUnit.assertTrue("Order Confirmation Page: Customer PO Number not maintained after manual entry page",orderConf.getUploadPONumber().equals(DataItems.lastUsedPO));

        AssertJUnit.assertTrue("Order Confirmation Page: Customer Name not maintained after manual entry page",orderConf.getCustomerName().equals(DataItems.custDetails[0]));
        AssertJUnit.assertTrue("Order Confirmation Page: Ship To Party not maintained after manual entry page",orderConf.getShipToParty().equals(DataItems.custDetails[1]));
        AssertJUnit.assertTrue("Order Confirmation Page: Requester not maintained after manual entry page",orderConf.getRequester().equals(DataItems.custDetails[2]));
        AssertJUnit.assertTrue("Order Confirmation Page: Buyers not maintained after manual entry page",orderConf.getBuyers().equals(DataItems.custDetails[3]));

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

    @Test //Manual Entry Page :: SUMST :: Single-line, order using YMN without master shade (MOQ ACTIVE)
            (groups = {"eComm","eComm_Orders"})
    public void SUMST22_CONO() throws Exception {
        //New chrome driver
        WebDriver driver = getDriver();

        //new base test to handle set up
        Ecomm_Base base = new Ecomm_Base(driver);
        //Set up returns a manual entry page to begin data entry
        Ecomm_MainPage eCommPage = base.setUp("MANUAL ENTRY SUMST22_CONO: Single line, Your Material Number without master shade(MOQ ACTIVE)","G_OOC_ME_SUMST_MOQ_3");

        PreFlows pf = new PreFlows();
        pf.chooseTheOtherProfile(driver); //choose CCE for Master Data setup
        pf.activateCallOffOrderForSalesOrg(driver, DataItems.salesOrgID);
        pf.enableMOQForCustomer(driver, DataItems.lifeEasyCustomer);
        pf.activateCallOffOrderForCustomer(driver, DataItems.lifeEasyCustomer);
        pf.chooseTheOtherProfile(driver); //choose Ecomm for tests

        System.out.println("Navigating to Manual Entry...");

        //press manual entry
        Ecomm_ManualEntryPage manualEntryPage = eCommPage.clickManualEntry();
        manualEntryPage.waitForElement();

        System.out.println("Manual Entry Page reached. Entering customer details...");

        //Input Customer Details
        manualEntryPage.setCustomerName(DataItems.custDetails[0]);
        manualEntryPage.setShipToPartyWithWait(DataItems.custDetails[1]);
        manualEntryPage.setRequestor(DataItems.custDetails[2]);
        manualEntryPage.setBuyers(DataItems.custDetails[3]);
        manualEntryPage.setPONumber(DataItems.custDetails[4]);

        pf.selectNormalOrderRadioButton(driver);

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
        Ecomm_OrderConfirmationPage orderConf = manualEntryPage.pressNextMOQ();
        orderConf.waitForElement();

        System.out.println("MOQ Alert appeared as expected.");

        System.out.println("Order confirmation page reached. Checking details are maintained...");

        AssertJUnit.assertTrue("Order Confirmation Page: Customer PO Number not maintained after manual entry page",orderConf.getUploadPONumber().equals(DataItems.lastUsedPO));

        AssertJUnit.assertTrue("Order Confirmation Page: Customer Name not maintained after manual entry page",orderConf.getCustomerName().equals(DataItems.custDetails[0]));
        AssertJUnit.assertTrue("Order Confirmation Page: Ship To Party not maintained after manual entry page",orderConf.getShipToParty().equals(DataItems.custDetails[1]));
        AssertJUnit.assertTrue("Order Confirmation Page: Requester not maintained after manual entry page",orderConf.getRequester().equals(DataItems.custDetails[2]));
        AssertJUnit.assertTrue("Order Confirmation Page: Buyers not maintained after manual entry page",orderConf.getBuyers().equals(DataItems.custDetails[3]));

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

    @Test //Manual Entry Page :: SUMST :: Multi-line, order using YMN without master shade (MOQ ACTIVE)
            (groups = {"eComm","eComm_Orders"})
    public void SUMST23_CONO() throws Exception {
        //New chrome driver
        WebDriver driver = getDriver();

        //new base test to handle set up
        Ecomm_Base base = new Ecomm_Base(driver);
        //Set up returns a manual entry page to begin data entry
        Ecomm_MainPage eCommPage = base.setUp("MANUAL ENTRY SUMST23_CONO: Multi line, Your Material Number without master shade(MOQ ACTIVE)","G_OOC_ME_SUMST_MOQ_4");


        PreFlows pf = new PreFlows();
        pf.chooseTheOtherProfile(driver); //choose CCE for Master Data setup
        pf.activateCallOffOrderForSalesOrg(driver, DataItems.salesOrgID);
        pf.enableMOQForCustomer(driver, DataItems.lifeEasyCustomer);
        pf.activateCallOffOrderForCustomer(driver, DataItems.lifeEasyCustomer);
        pf.chooseTheOtherProfile(driver); //choose Ecomm for tests

        System.out.println("Navigating to Manual Entry...");

        //press manual entry
        Ecomm_ManualEntryPage manualEntryPage = eCommPage.clickManualEntry();
        manualEntryPage.waitForElement();

        System.out.println("Manual Entry Page reached. Entering customer details...");

        //Input Customer Details
        manualEntryPage.setCustomerName(DataItems.custDetails[0]);
        manualEntryPage.setShipToPartyWithWait(DataItems.custDetails[1]);
        manualEntryPage.setRequestor(DataItems.custDetails[2]);
        manualEntryPage.setBuyers(DataItems.custDetails[3]);
        manualEntryPage.setPONumber(DataItems.custDetails[4]);

        pf.selectNormalOrderRadioButton(driver);

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
        Ecomm_OrderConfirmationPage orderConf = manualEntryPage.pressNextMOQ();
        orderConf.waitForElement();

        System.out.println("MOQ Alert appeared as expected.");

        System.out.println("Order confirmation page reached. Checking details are maintained...");

        AssertJUnit.assertTrue("Order Confirmation Page: Customer PO Number not maintained after manual entry page",orderConf.getUploadPONumber().equals(DataItems.lastUsedPO));

        AssertJUnit.assertTrue("Order Confirmation Page: Customer Name not maintained after manual entry page",orderConf.getCustomerName().equals(DataItems.custDetails[0]));
        AssertJUnit.assertTrue("Order Confirmation Page: Ship To Party not maintained after manual entry page",orderConf.getShipToParty().equals(DataItems.custDetails[1]));
        AssertJUnit.assertTrue("Order Confirmation Page: Requester not maintained after manual entry page",orderConf.getRequester().equals(DataItems.custDetails[2]));
        AssertJUnit.assertTrue("Order Confirmation Page: Buyers not maintained after manual entry page",orderConf.getBuyers().equals(DataItems.custDetails[3]));

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

        driver.navigate().refresh();

        //Deactivate call off order for customer
        pf.chooseTheOtherProfile(driver);
        pf.deActivateCallOffOrderForCustomer(driver, DataItems.lifeEasyCustomer);
    }

    @Test //Manual Entry Page :: SUMST :: Single-line, order using Article and shadecode (MOQ ACTIVE)
            (groups = {"eComm","eComm_Orders"})
    public void SUMST24_CONO() throws Exception {
        //New chrome driver
        WebDriver driver = getDriver();

        //new base test to handle set up
        Ecomm_Base base = new Ecomm_Base(driver);
        //Set up returns a manual entry page to begin data entry
        Ecomm_MainPage eCommPage = base.setUp("MANUAL ENTRY SUMST23_CONO: Single line, Article and shade(MOQ ACTIVE)","G_OOC_ME_SUMST_MOQ_5");

        PreFlows pf = new PreFlows();
        pf.chooseTheOtherProfile(driver); //choose CCE for Master Data setup
        pf.activateCallOffOrderForSalesOrg(driver, DataItems.salesOrgID);
        pf.enableMOQForCustomer(driver, DataItems.lifeEasyCustomer);
        pf.activateCallOffOrderForCustomer(driver, DataItems.lifeEasyCustomer);
        pf.chooseTheOtherProfile(driver); //choose Ecomm for tests

        System.out.println("Navigating to Manual Entry...");

        //press manual entry
        Ecomm_ManualEntryPage manualEntryPage = eCommPage.clickManualEntry();
        manualEntryPage.waitForElement();

        System.out.println("Manual Entry Page reached. Entering customer details...");

        //Input Customer Details
        manualEntryPage.setCustomerName(DataItems.custDetails[0]);
        manualEntryPage.setShipToPartyWithWait(DataItems.custDetails[1]);
        manualEntryPage.setRequestor(DataItems.custDetails[2]);
        manualEntryPage.setBuyers(DataItems.custDetails[3]);
        manualEntryPage.setPONumber(DataItems.custDetails[4]);

        pf.selectNormalOrderRadioButton(driver);

        System.out.println("Customer details entered. Entering product details...");

        int numberOfLines = 1;

        //Order details to be entered
        String[][] details = {
                //line 1 details
                {DataItems.expArticle,DataItems.expShadeCode,String.valueOf(1)}
        };

        //Input details for each line
        for (int i = 0; i < numberOfLines; i++) {
            manualEntryPage = manualEntryPage.setOrderDetailsArticle(details[i], i);
        }

        String date = manualEntryPage.getDate(0);

        System.out.println("Product details entered. Ensuring auto-fill is correct...");

        AssertJUnit.assertTrue("Manual Entry Page: Auto-fill article not as expected in master data",manualEntryPage.getArticle(0).equals(DataItems.expArticle));
        AssertJUnit.assertTrue("Manual Entry Page: Auto-fill brand not as expected in master data",manualEntryPage.getSetBrand(0).equals(DataItems.expBrand));
        AssertJUnit.assertTrue("Manual Entry Page: Auto-fill ticket not as expected in master data",manualEntryPage.getSetTicket(0).equals(DataItems.expTicket));
        AssertJUnit.assertTrue("Manual Entry Page: Auto-fill length not as expected in master data",manualEntryPage.getSetLength(0).equals(DataItems.expLength));
        AssertJUnit.assertTrue("Manual Entry Page: Auto-fill finish not as expected in master data",manualEntryPage.getSetFinish(0).equals(DataItems.expFinish));
        AssertJUnit.assertTrue("Manual Entry Page: Shade code not as expected after being changed from material Master shade",manualEntryPage.getShadeCode(0).equals(DataItems.expShadeCode));

        System.out.println("Auto-fill correct. Pressing next...");

        //Press next
        Ecomm_OrderConfirmationPage orderConf = manualEntryPage.pressNextMOQ();
        orderConf.waitForElement();

        System.out.println("MOQ Alert appeared as expected.");

        System.out.println("Order confirmation page reached. Checking details are maintained...");

        AssertJUnit.assertTrue("Order Confirmation Page: Customer PO Number not maintained after manual entry page",orderConf.getUploadPONumber().equals(DataItems.lastUsedPO));

        AssertJUnit.assertTrue("Order Confirmation Page: Customer Name not maintained after manual entry page",orderConf.getCustomerName().equals(DataItems.custDetails[0]));
        AssertJUnit.assertTrue("Order Confirmation Page: Ship To Party not maintained after manual entry page",orderConf.getShipToParty().equals(DataItems.custDetails[1]));
        AssertJUnit.assertTrue("Order Confirmation Page: Requester not maintained after manual entry page",orderConf.getRequester().equals(DataItems.custDetails[2]));
        AssertJUnit.assertTrue("Order Confirmation Page: Buyers not maintained after manual entry page",orderConf.getBuyers().equals(DataItems.custDetails[3]));

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

        driver.navigate().refresh();

        //Deactivate call off order for customer
        pf.chooseTheOtherProfile(driver);
        pf.deActivateCallOffOrderForCustomer(driver, DataItems.lifeEasyCustomer);
    }

    @Test //Manual Entry Page :: SUMST :: Multi-line, order using Article and shadecode (MOQ ACTIVE)
            (groups = {"eComm","eComm_Orders"})
    public void SUMST25_CONO() throws Exception {
        //New chrome driver
        WebDriver driver = getDriver();

        //new base test to handle set up
        Ecomm_Base base = new Ecomm_Base(driver);
        //Set up returns a manual entry page to begin data entry
        Ecomm_MainPage eCommPage = base.setUp("MANUAL ENTRY SUMST25_CONO: Multi line, Article and shade code(MOQ ACTIVE)","G_OOC_ME_SUMST_MOQ_6");

        PreFlows pf = new PreFlows();
        pf.chooseTheOtherProfile(driver); //choose CCE for Master Data setup
        pf.activateCallOffOrderForSalesOrg(driver, DataItems.salesOrgID);
        pf.enableMOQForCustomer(driver, DataItems.lifeEasyCustomer);
        pf.activateCallOffOrderForCustomer(driver, DataItems.lifeEasyCustomer);
        pf.chooseTheOtherProfile(driver); //choose Ecomm for tests

        System.out.println("Navigating to Manual Entry...");

        //press manual entry
        Ecomm_ManualEntryPage manualEntryPage = eCommPage.clickManualEntry();
        manualEntryPage.waitForElement();

        System.out.println("Manual Entry Page reached. Entering customer details...");

        //Input Customer Details
        manualEntryPage.setCustomerName(DataItems.custDetails[0]);
        manualEntryPage.setShipToPartyWithWait(DataItems.custDetails[1]);
        manualEntryPage.setRequestor(DataItems.custDetails[2]);
        manualEntryPage.setBuyers(DataItems.custDetails[3]);
        manualEntryPage.setPONumber(DataItems.custDetails[4]);

        pf.selectNormalOrderRadioButton(driver);

        System.out.println("Customer details entered. Entering product details...");

        int numberOfLines = 2;

        //Order details to be entered
        String[][] details = {
                //line 1 details
                {DataItems.expArticle,DataItems.expShadeCode,String.valueOf(1)},
                {DataItems.article3,DataItems.shadeCode,String.valueOf(DataItems.quantity)}
        };

        //Input details for each line
        for (int i = 0; i < numberOfLines; i++) {
            manualEntryPage = manualEntryPage.setOrderDetailsArticle(details[i], i);
        }

        String date = manualEntryPage.getDate(0);

        System.out.println("Product details entered. Ensuring auto-fill is correct...");

        AssertJUnit.assertTrue("Manual Entry Page: Auto-fill article not as expected in master data",manualEntryPage.getArticle(0).equals(DataItems.expArticle));
        AssertJUnit.assertTrue("Manual Entry Page: Auto-fill brand not as expected in master data",manualEntryPage.getSetBrand(0).equals(DataItems.expBrand));
        AssertJUnit.assertTrue("Manual Entry Page: Auto-fill ticket not as expected in master data",manualEntryPage.getSetTicket(0).equals(DataItems.expTicket));
        AssertJUnit.assertTrue("Manual Entry Page: Auto-fill length not as expected in master data",manualEntryPage.getSetLength(0).equals(DataItems.expLength));
        AssertJUnit.assertTrue("Manual Entry Page: Auto-fill finish not as expected in master data",manualEntryPage.getSetFinish(0).equals(DataItems.expFinish));
        AssertJUnit.assertTrue("Manual Entry Page: Shade code not as expected after being changed from material Master shade",manualEntryPage.getShadeCode(0).equals(DataItems.expShadeCode));

        System.out.println("Auto-fill correct. Pressing next...");

        //Press next
        Ecomm_OrderConfirmationPage orderConf = manualEntryPage.pressNextMOQ();
        orderConf.waitForElement();

        System.out.println("MOQ Alert appeared as expected.");

        System.out.println("Order confirmation page reached. Checking details are maintained...");

        AssertJUnit.assertTrue("Order Confirmation Page: Customer PO Number not maintained after manual entry page",orderConf.getUploadPONumber().equals(DataItems.lastUsedPO));

        AssertJUnit.assertTrue("Order Confirmation Page: Customer Name not maintained after manual entry page",orderConf.getCustomerName().equals(DataItems.custDetails[0]));
        AssertJUnit.assertTrue("Order Confirmation Page: Ship To Party not maintained after manual entry page",orderConf.getShipToParty().equals(DataItems.custDetails[1]));
        AssertJUnit.assertTrue("Order Confirmation Page: Requester not maintained after manual entry page",orderConf.getRequester().equals(DataItems.custDetails[2]));
        AssertJUnit.assertTrue("Order Confirmation Page: Buyers not maintained after manual entry page",orderConf.getBuyers().equals(DataItems.custDetails[3]));

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

        driver.navigate().refresh();

        //Deactivate call off order for customer
        pf.chooseTheOtherProfile(driver);
        pf.deActivateCallOffOrderForCustomer(driver, DataItems.lifeEasyCustomer);
    }

    @Test //Manual Entry Page :: SUMST :: Single-line, order using Brand/Ticket/Length/Finish/Shade combination (MOQ ACTIVE)
            (groups = {"eComm","eComm_Orders"})
    public void SUMST26_CONO() throws Exception {
        //New chrome driver
        WebDriver driver = getDriver();

        //new base test to handle set up
        Ecomm_Base base = new Ecomm_Base(driver);
        //Set up returns a manual entry page to begin data entry
        Ecomm_MainPage eCommPage = base.setUp("MANUAL ENTRY SUMST26_CONO: Single line, Brand/Ticket/Length/Finish combination(MOQ ACTIVE)","G_OOC_ME_SUMST_MOQ_7");

        PreFlows pf = new PreFlows();
        pf.chooseTheOtherProfile(driver); //choose CCE for Master Data setup
        pf.activateCallOffOrderForSalesOrg(driver, DataItems.salesOrgID);
        pf.enableMOQForCustomer(driver, DataItems.lifeEasyCustomer);
        pf.activateCallOffOrderForCustomer(driver, DataItems.lifeEasyCustomer);
        pf.chooseTheOtherProfile(driver); //choose Ecomm for tests

        System.out.println("Navigating to Manual Entry...");

        //press manual entry
        Ecomm_ManualEntryPage manualEntryPage = eCommPage.clickManualEntry();
        manualEntryPage.waitForElement();

        System.out.println("Manual Entry Page reached. Entering customer details...");

        //Input Customer Details
        manualEntryPage.setCustomerName(DataItems.custDetails[0]);
        manualEntryPage.setShipToPartyWithWait(DataItems.custDetails[1]);
        manualEntryPage.setRequestor(DataItems.custDetails[2]);
        manualEntryPage.setBuyers(DataItems.custDetails[3]);
        manualEntryPage.setPONumber(DataItems.custDetails[4]);

        pf.selectNormalOrderRadioButton(driver);

        System.out.println("Customer details entered. Entering product details...");

        int numberOfLines = 1;

        //Order details to be entered
        String[][] details = {
                //line 1 details
                {DataItems.expBrand,DataItems.expTicket,DataItems.expLength,DataItems.expFinish,DataItems.expShadeCode,String.valueOf(1)},
        };

        //Input details for each line
        for (int i = 0; i < numberOfLines; i++) {
            manualEntryPage = manualEntryPage.setOrderDetailsCombination(details[i], i);
        }

        String date = manualEntryPage.getDate(0);

        System.out.println("Product details entered. Ensuring auto-fill is correct...");

        AssertJUnit.assertTrue("Manual Entry Page: Auto-fill brand not as expected in master data",manualEntryPage.getBrand(0).equals(DataItems.expBrand));
        AssertJUnit.assertTrue("Manual Entry Page: Auto-fill ticket not as expected in master data",manualEntryPage.getTicket(0).equals(DataItems.expTicket));
        AssertJUnit.assertTrue("Manual Entry Page: Auto-fill length not as expected in master data",manualEntryPage.getLength(0).equals(DataItems.expLength));
        AssertJUnit.assertTrue("Manual Entry Page: Auto-fill finish not as expected in master data",manualEntryPage.getFinish(0).equals(DataItems.expFinish));
        AssertJUnit.assertTrue("Manual Entry Page: Shade code not as expected after being changed from material Master shade",manualEntryPage.getShadeCode(0).equals(DataItems.expShadeCode));

        System.out.println("Auto-fill correct. Pressing next...");

        //Press next
        Ecomm_OrderConfirmationPage orderConf = manualEntryPage.pressNextMOQ();
        orderConf.waitForElement();

        System.out.println("MOQ Alert appeared as expected.");

        System.out.println("Order confirmation page reached. Checking details are maintained...");

        AssertJUnit.assertTrue("Order Confirmation Page: Customer PO Number not maintained after manual entry page",orderConf.getUploadPONumber().equals(DataItems.lastUsedPO));

        AssertJUnit.assertTrue("Order Confirmation Page: Customer Name not maintained after manual entry page",orderConf.getCustomerName().equals(DataItems.custDetails[0]));
        AssertJUnit.assertTrue("Order Confirmation Page: Ship To Party not maintained after manual entry page",orderConf.getShipToParty().equals(DataItems.custDetails[1]));
        AssertJUnit.assertTrue("Order Confirmation Page: Requester not maintained after manual entry page",orderConf.getRequester().equals(DataItems.custDetails[2]));
        AssertJUnit.assertTrue("Order Confirmation Page: Buyers not maintained after manual entry page",orderConf.getBuyers().equals(DataItems.custDetails[3]));

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

    @Test //Manual Entry Page :: SUMST :: Multi-line, order using Brand/Ticket/Length/Finish/Shade combination (MOQ ACTIVE)
            (groups = {"eComm","eComm_Orders"})
    public void SUMST27_CONO() throws Exception {
        //New chrome driver
        WebDriver driver = getDriver();

        //new base test to handle set up
        Ecomm_Base base = new Ecomm_Base(driver);
        //Set up returns a manual entry page to begin data entry
        Ecomm_MainPage eCommPage = base.setUp("MANUAL ENTRY SUMST27_CONO: Multi-line, Brand/Ticket/Length/Finish combination(MOQ ACTIVE)","G_OOC_ME_SUMST_MOQ_8");

        PreFlows pf = new PreFlows();
        pf.chooseTheOtherProfile(driver); //choose CCE for Master Data setup
        pf.activateCallOffOrderForSalesOrg(driver, DataItems.salesOrgID);
        pf.enableMOQForCustomer(driver, DataItems.lifeEasyCustomer);
        pf.activateCallOffOrderForCustomer(driver, DataItems.lifeEasyCustomer);
        pf.chooseTheOtherProfile(driver); //choose Ecomm for tests

        System.out.println("Navigating to Manual Entry...");

        //press manual entry
        Ecomm_ManualEntryPage manualEntryPage = eCommPage.clickManualEntry();
        manualEntryPage.waitForElement();

        System.out.println("Manual Entry Page reached. Entering customer details...");

        //Input Customer Details
        manualEntryPage.setCustomerName(DataItems.custDetails[0]);
        manualEntryPage.setShipToPartyWithWait(DataItems.custDetails[1]);
        manualEntryPage.setRequestor(DataItems.custDetails[2]);
        manualEntryPage.setBuyers(DataItems.custDetails[3]);
        manualEntryPage.setPONumber(DataItems.custDetails[4]);

        pf.selectNormalOrderRadioButton(driver);

        System.out.println("Customer details entered. Entering product details...");

        int numberOfLines = 2;

        //Order details to be entered
        String[][] details = {
                //line 1 details
                {DataItems.expBrand,DataItems.expTicket,DataItems.expLength,DataItems.expFinish,DataItems.expShadeCode,String.valueOf(1)},
                {DataItems.brand3,DataItems.ticket3,DataItems.length3,DataItems.finish3,DataItems.shadeCode,String.valueOf(DataItems.quantity)}
        };

        //Input details for each line
        for (int i = 0; i < numberOfLines; i++) {
            manualEntryPage = manualEntryPage.setOrderDetailsCombination(details[i], i);
        }

        String date = manualEntryPage.getDate(0);

        System.out.println("Product details entered. Ensuring auto-fill is correct...");

        AssertJUnit.assertTrue("Manual Entry Page: Auto-fill brand not as expected in master data",manualEntryPage.getBrand(0).equals(DataItems.expBrand));
        AssertJUnit.assertTrue("Manual Entry Page: Auto-fill ticket not as expected in master data",manualEntryPage.getTicket(0).equals(DataItems.expTicket));
        AssertJUnit.assertTrue("Manual Entry Page: Auto-fill length not as expected in master data",manualEntryPage.getLength(0).equals(DataItems.expLength));
        AssertJUnit.assertTrue("Manual Entry Page: Auto-fill finish not as expected in master data",manualEntryPage.getFinish(0).equals(DataItems.expFinish));
        AssertJUnit.assertTrue("Manual Entry Page: Shade code not as expected after being changed from material Master shade",manualEntryPage.getShadeCode(0).equals(DataItems.expShadeCode));

        System.out.println("Auto-fill correct. Pressing next...");

        //Press next
        Ecomm_OrderConfirmationPage orderConf = manualEntryPage.pressNextMOQ();

        Alert alert = Wait.alert(driver);
        alert.accept();

        orderConf.waitForElement();

        System.out.println("MOQ Alert appeared as expected.");

        System.out.println("Order confirmation page reached. Checking details are maintained...");

        AssertJUnit.assertTrue("Order Confirmation Page: Customer PO Number not maintained after manual entry page",orderConf.getUploadPONumber().equals(DataItems.lastUsedPO));

        AssertJUnit.assertTrue("Order Confirmation Page: Customer Name not maintained after manual entry page",orderConf.getCustomerName().equals(DataItems.custDetails[0]));
        AssertJUnit.assertTrue("Order Confirmation Page: Ship To Party not maintained after manual entry page",orderConf.getShipToParty().equals(DataItems.custDetails[1]));
        AssertJUnit.assertTrue("Order Confirmation Page: Requester not maintained after manual entry page",orderConf.getRequester().equals(DataItems.custDetails[2]));
        AssertJUnit.assertTrue("Order Confirmation Page: Buyers not maintained after manual entry page",orderConf.getBuyers().equals(DataItems.custDetails[3]));

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

        driver.navigate().refresh();

        //Deactivate call off order for customer
        pf.chooseTheOtherProfile(driver);
        pf.deActivateCallOffOrderForCustomer(driver, DataItems.lifeEasyCustomer);
    }

    @Test //Manual Entry Page :: SUMST :: Approver workflow enabled
            (groups = {"eComm","eComm_Orders"}) //CHANGES MASTER DATA
    public void SUMST28_CONO() throws Exception {
        WebDriver driver = getDriver();

        Cce_Base base = new Cce_Base(driver);
        CCE_MainPage mainPage = base.setUp("Manual Entry Page SUMST28_CONO: Approver workflow enabled", "OA_OAP_SUSS_ME_2");

        PreFlows pf = new PreFlows();
        pf.activateCallOffOrderForCustomer(driver, DataItems.lifeEasyCustomer);


        System.out.println("Navigating to Masters...");

        Mst_CustomersPage custPage = mainPage.selectCustomers();
        custPage.waitForElement();

        System.out.println("Customers Master reached. Finding 'Life Easy Customer' and turning approval workflow on...");

        custPage.setCustomerName(DataItems.custDetails[0]);
        custPage.pressSearch();
        int row = custPage.findCustomer(DataItems.custDetails[0]);

        Mst_EditCustomerPage editPage = custPage.pressEdit(row);
        editPage.waitForElement();

        AssertJUnit.assertTrue("Edit Customer Page: Customer name differs in edit page from Customers Table",editPage.getCustomerName().equals(DataItems.custDetails[0]));

        editPage.setApprovalWorkflow();
        editPage.pressSave();

        System.out.println("Approval workflow enabled. Creating order...");

        Ecomm_MainPage eMainPage = editPage.clickEcomm();
        eMainPage.waitForLoad();

        Ecomm_ManualEntryPage mePage = eMainPage.clickManualEntry();
        mePage.waitForElement();

        System.out.println("Manual Entry Page reached. Entering customer details...");

        mePage.setCustomerName(DataItems.subCustDetails[0]);
        mePage.setShipToParty(DataItems.subCustDetails[1]);
        mePage.setRequestor(DataItems.subCustDetails[2]);
        mePage.setBuyers(DataItems.subCustDetails[3]);
        mePage.setPONumber(DataItems.custDetails[4]);

        pf.selectNormalOrderRadioButton(driver);

        System.out.println("Customer details entered. Entering line details...");

        mePage.setArticle("8754120",0);
        mePage.setShadeCode("C1202", 0);
        mePage.setQty(3, 0);
        mePage.setDate(0);

        System.out.println("Line details entered. Pressing next...");

        Ecomm_OrderConfirmationPage orderConf = mePage.pressNext();
        orderConf.waitForElement();

        System.out.println("Order Confirmation Page reached. Check 'Send for Approval' button is displayed...");

        AssertJUnit.assertTrue("Order Confirmation Page: Send for approval button not displayed despite Approval Workflow enabled",orderConf.getSendForApprovalButton().isDisplayed());

        System.out.println("Button displayed. Sending...");

        Ecomm_PendingApprovalListPage pendPage = orderConf.pressSendForApproval();
        pendPage.waitForElement();

        System.out.println("Item sent for approval. Asserting Pending Approval Page appears...");

        AssertJUnit.assertTrue("Pending Approval Page: Not correctly linked after confirmation/title incorrect",pendPage.getBreadcrumb().getText().equals("Orders | Pending Approval List"));

        System.out.println("Pending Approval Page reached. Finding order...");

        int orderRow = pendPage.getRow(DataItems.lastUsedPO);

        AssertJUnit.assertFalse("Pending Approval Page: Order (PO: "+DataItems.lastUsedPO+") not found after being sent for approval",orderRow == -1);

        System.out.println("Order found. Logging into approver account...");

        WBA_LoginPage liPage = pendPage.pressLogout();
        liPage.waitForElement();

        Ecomm_Base base2 = new Ecomm_Base(driver);
        Ecomm_MainPage mainPage2 = base2.setUp("", "",DataItems.approverUsername,DataItems.approverPassword);
        mainPage2.waitForLoad();

        Ecomm_PendingApprovalListPage pendPage2 = mainPage2.clickPendingApprovalListPageApprover();
        pendPage2.waitForElement();

        System.out.println("Pending Approval List Page reached. Finding order...");

        int orderRow2 = pendPage2.getRowAlt(DataItems.lastUsedPO);

        AssertJUnit.assertFalse("Pending Approval List Page: Order (PO: "+DataItems.lastUsedPO+") not found in table for Approver account",orderRow2==-1);

        System.out.println("Order found. Approving...");

        String orderNo = pendPage2.getOrderNo(orderRow2);
        System.out.println(orderNo);
        pendPage2.approveOrder(orderNo);
        pendPage2.waitForElement();

        System.out.println("Approved. Checking Outstanding Order Page for order (logging into abc test account)...");

        WBA_LoginPage liPage2 = pendPage2.pressLogout();
        liPage2.waitForElement();

        Ecomm_Base base3 = new Ecomm_Base(driver);
        Ecomm_MainPage mainPage3 = base3.setUp("", "",DataItems.anglerRequesterUsername,DataItems.anglerRequesterPassword);
        mainPage2.waitForLoad();

        Ecomm_OutstandingOrdersPage outOrds = pendPage2.clickOutstandingOrders();
        outOrds.waitForElement();

        System.out.println("Outstanding Orders page reached. Searching for order...");

        int row3 = outOrds.getRowAlt(DataItems.lastUsedPO);

        AssertJUnit.assertFalse("Outstanding Orders Page: Order does not appear after approval",row3==-1);

        System.out.println("Order found. Checking order status...");

        String status = outOrds.getOrderStatus(row3);
        System.out.println("Order Status: " + status);

        AssertJUnit.assertTrue("Outstanding Orders Page: Order (Order No.: "+orderNo+") does not have expected In Progress/Open status",status.equals("In Progress") | status.equals("Open"));

        WBA_LoginPage liPage3 = outOrds.pressLogout();
        liPage3.waitForElement();

        System.out.println("Disabling approval workflow...");

        Cce_Base base4 = new Cce_Base(driver);
        CCE_MainPage mainPage4 = base4.setUp("", "");

        Mst_CustomersPage custPage2 = mainPage4.selectCustomers();
        custPage2.waitForElement();

        custPage2.setCustomerName(DataItems.custDetails[0]);
        custPage2.pressSearch();
        int row2 = custPage2.findCustomer(DataItems.custDetails[0]);

        Mst_EditCustomerPage editPage2 = custPage.pressEdit(row2);
        editPage2.waitForElement();

        AssertJUnit.assertTrue("Edit Customer Page: Customer name differs in edit page from Customers Table",editPage2.getCustomerName().equals(DataItems.custDetails[0]));

        editPage2.unsetApprovalWorkflow();
        editPage2.pressSave();
        custPage2.waitForElement();

        System.out.println("Approval workflow disabled");

        driver.navigate().refresh();

        //Deactivate call off order for customer
        pf.chooseTheOtherProfile(driver);
        pf.deActivateCallOffOrderForCustomer(driver, DataItems.lifeEasyCustomer);

        driver.navigate().refresh();

        //Deactivate call off order for customer
        pf.chooseTheOtherProfile(driver);
        pf.deActivateCallOffOrderForCustomer(driver, DataItems.lifeEasyCustomer);

    }


}
