package com.coats.selenium.tests;

import AutomationFramework.CommonTask;
import AutomationFramework.PreFlows;
import PageObjects.*;
import com.coats.selenium.DriverFactory;
import com.google.common.base.Verify;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import AutomationFramework.DataItems;

/**
 * Created by dion on 23.03.2016.
 */

public class Ecomm_ContractCallOffOrders_Test extends DriverFactory {


    @Test(groups = {"eComm"})
    public void CCO_GC_01() throws Exception {

        //New driver
        WebDriver driver = getDriver();
        Cce_Base base = new Cce_Base(driver);

        CCE_MainPage ccePage = base.setUp("Contract Call Off Orders: Page and filter checks", "CCO_GC_01", DataItems.validCoatsUsername2, DataItems.validCoatsPassword);
        ccePage.waitForLoad();

        //Go Masters -> Sales Org and edit Sales Org (LK53)
        System.out.println("Navigating to Sales Organisations Page...");
        Mst_SalesOrgPage soPage = ccePage.selectSalesOrg();
        soPage.waitForElement();

        System.out.println("Sales Organisations page reached. Checking title...");
        AssertJUnit.assertTrue("Sales Organisations Page: Title not as expected", soPage.getBreadcrumb().getText().equals("Sales Organisations"));
        System.out.println("Title checked");
        soPage.assertBaseElements();

        System.out.println("Checking fields...");
        soPage.checkFields();
        System.out.println("Fields checked. Checking record appears...");

        soPage.setSalesOrg("LK53");
        soPage.pressSearch();
        soPage.waitForElement();

        int row = soPage.getRow("LK53");
        AssertJUnit.assertFalse("Sales Organisations Page: Sales Organisation not present in table after creation", row == -1);

        System.out.println("Record found. Editing record...");
        Mst_EditSalesOrgPage editPage = soPage.pressEdit(row);
        editPage.waitForElement();
        System.out.println("Edit page reached. Checking title...");

        AssertJUnit.assertTrue("Edit Sales Organisation Page: Title not as expected", editPage.getBreadcrumb().getText().equals("Sales Organisations | Edit Sales Organisation"));
        System.out.println("Title checked");

        editPage.assertBaseElements();

        System.out.println("Checking fields...");
        editPage.checkFields();

        ///Check "Enable Contract CALL OFF Order" flag
        System.out.println("Fields checked. Editing Sales Organisation...");
        editPage.enableCallOffOrderCheckBox();

        //Save
        System.out.println("'Enable Contract CALL OFF Order' flag checked. Saving...");
        editPage.pressSave();
        soPage.waitForElement();

        System.out.println("Saved. Navigating to Customer master data...");


        //Go Masters -> Customers and edit Customer(Star Garments Ltd.)
        Mst_CustomersPage custPage = ccePage.selectCustomers();
        custPage.waitForElement();

        custPage.setCustomerName("Star Garments Ltd.");
        custPage.pressSearch();
        custPage.waitForElement();

        int row2 = custPage.getRow("Star Garments Ltd.");
        System.out.println("Record found. Editing record...");

        Mst_EditCustomerPage editPage2 = custPage.pressEdit(row2);
        editPage2.waitForElement();

        System.out.println("Edit page reached.");

        //Check "Enable Contract CALL OFF Order" flag
        System.out.println("Fields checked. Check 'Enable Contract CALL OFF Order'...");
        editPage2.enableCallOffOrderCheckBox();
        editPage2.pressSave();
        custPage.waitForElement();

        System.out.println("Customers page reached. The Customer has been updated");
        AssertJUnit.assertTrue("Customers Page: Flash Message not as expected", custPage.getFlashMessage().getText().equals("The Customer has been updated"));


    }

    @Test(groups = {"eComm"})
    public void CCO_GC_02() throws Exception {

        //New driver
        WebDriver driver = getDriver();
        Cce_Base base = new Cce_Base(driver);

        CCE_MainPage ccePage = base.setUp("Contract Call Off Orders: Page and filter checks", "CCO_GC_02", DataItems.validCoatsUsername2, DataItems.validCoatsPassword);
        ccePage.waitForLoad();

        //Go Masters -> Sales Org and edit Sales Org (LK53)
        System.out.println("Navigating to Sales Organisations Page...");
        Mst_SalesOrgPage soPage = ccePage.selectSalesOrg();
        soPage.waitForElement();

        System.out.println("Sales Organisations page reached. Checking title...");
        AssertJUnit.assertTrue("Sales Organisations Page: Title not as expected", soPage.getBreadcrumb().getText().equals("Sales Organisations"));
        System.out.println("Title checked");
        soPage.assertBaseElements();

        System.out.println("Checking fields...");
        soPage.checkFields();
        System.out.println("Fields checked. Checking record appears...");

        soPage.setSalesOrg("LK53");
        soPage.pressSearch();
        soPage.waitForElement();

        int row = soPage.getRow("LK53");
        AssertJUnit.assertFalse("Sales Organisations Page: Sales Organisation not present in table after creation", row == -1);

        System.out.println("Record found. Editing record...");
        Mst_EditSalesOrgPage editPage = soPage.pressEdit(row);
        editPage.waitForElement();
        System.out.println("Edit page reached. Checking title...");

        AssertJUnit.assertTrue("Edit Sales Organisation Page: Title not as expected", editPage.getBreadcrumb().getText().equals("Sales Organisations | Edit Sales Organisation"));
        System.out.println("Title checked");

        editPage.assertBaseElements();

        System.out.println("Checking fields...");
        editPage.checkFields();

        ///UnCheck "Enable Contract CALL OFF Order" flag
        System.out.println("Fields checked. Editing Sales Organisation...");
        editPage.disableCallOffOrderCheckBox();

        //Save
        System.out.println("'Enable Contract CALL OFF Order' flag unchecked. Saving...");
        editPage.pressSave();
        soPage.waitForElement();

        System.out.println("Saved. Navigating to Customer master data...");

        //Go Masters -> Customers and edit Customer(Star Garments Ltd.)
        Mst_CustomersPage custPage = ccePage.selectCustomers();
        custPage.waitForElement();

        custPage.setCustomerName("Star Garments Ltd.");
        custPage.pressSearch();
        custPage.waitForElement();

        int row2 = custPage.getRow("Star Garments Ltd.");
        System.out.println("Record found. Editing record...");

        Mst_EditCustomerPage editPage2 = custPage.pressEdit(row2);
        editPage2.waitForElement();

        System.out.println("Edit page reached.");

        System.out.println("Customers Edit page page reached. Verify that Flag 'Enable Contract CALL OFF Order' is not present");
        AssertJUnit.assertTrue(!driver.findElement(By.id("CustomerOffOrder")).isDisplayed());
        System.out.println("Flag 'Enable Contract CALL OFF Order' is not present");

    }

    @Test(groups = {"eComm"})
    public void CCO_GC_03() throws Exception {
        CCO_GC_01();

        WebDriver driver = getDriver();

        WebElement logout = driver.findElement(By.cssSelector("html body div#container div#header div.top span.right span.logout a"));
        logout.click();

        Ecomm_Base sTest = new Ecomm_Base(driver);
        Ecomm_MainPage eCommPage = sTest.setUp("()", "Unknown", DataItems.validCoatsUsername2, DataItems.validCoatsPassword);

        System.out.println("Navigating to Manual Entry...");
        Ecomm_ManualEntryPage manualEntryPage = eCommPage.clickManualEntry();
        manualEntryPage.waitForElement();

        System.out.println("Manual Entry page reached. Entering customer details...");
        //Input Customer Details
        manualEntryPage.setCustomerName(DataItems.custDetails3[0]);

        PreFlows pf = new PreFlows();
        pf.selectNormalOrderRadioButton(driver);

        AssertJUnit.assertEquals(manualEntryPage.getFlashMessage().getText(), "NORMAL ORDER");
        pf.selectContractOrderRadioButton(driver);
        AssertJUnit.assertEquals(manualEntryPage.getFlashMessage().getText(), "CONTRACT ORDER");
        AssertJUnit.assertTrue(manualEntryPage.findContractPOField());
        AssertJUnit.assertTrue(manualEntryPage.findLineRefField());
        AssertJUnit.assertTrue(manualEntryPage.findContractOrdButton());
        AssertJUnit.assertTrue(manualEntryPage.findNormalOrdButton());

    }

    @Test(groups = {"eComm"})
    public void CCO_GC_04() throws Exception {

        CCO_GC_02();

        WebDriver driver = getDriver();

        WebElement logout = driver.findElement(By.cssSelector("html body div#container div#header div.top span.right span.logout a"));
        logout.click();

        Ecomm_Base sTest = new Ecomm_Base(driver);
        Ecomm_MainPage eCommPage = sTest.setUp("()", "Unknown", DataItems.validCoatsUsername2, DataItems.validCoatsPassword);

        System.out.println("Navigating to Manual Entry...");
        Ecomm_ManualEntryPage manualEntryPage = eCommPage.clickManualEntry();
        manualEntryPage.waitForElement();

        System.out.println("Manual Entry page reached. Check that elements for Call Off Order are not present");
        //Input Customer Details
        manualEntryPage.setCustomerName(DataItems.custDetails3[0]);
        AssertJUnit.assertFalse(manualEntryPage.findContractOrdButton());
        AssertJUnit.assertFalse(manualEntryPage.findNormalOrdButton());
        System.out.println("Elements for Call Off Order are not present");
    }

    @Test(groups = {"eComm"})
    public void CCO_GC_05() throws Exception {
        CCO_GC_01();

        WebDriver driver = getDriver();

        WebElement logout = driver.findElement(By.cssSelector("html body div#container div#header div.top span.right span.logout a"));
        logout.click();

        Ecomm_Base baseTest = new Ecomm_Base(driver);
        Ecomm_MainPage eCommPage = baseTest.setUp("()", "Unknown", DataItems.validCustUsername, DataItems.validCustPassword);

        System.out.println("Navigating to Manual Entry...");
        Ecomm_ManualEntryPage manualEntryPage = eCommPage.clickManualEntry();
        manualEntryPage.waitForElement();

        System.out.println("Manual Entry page reached. Check that elements for Call Off Order are present");
        AssertJUnit.assertTrue(manualEntryPage.findContractOrdButton());
        AssertJUnit.assertTrue(manualEntryPage.findNormalOrdButton());
        System.out.println("Elements for Call Off Order are present");

    }

    @Test(groups = {"eComm"})
    public void CCO_GC_06() throws Exception {
        CCO_GC_02();

        WebDriver driver = getDriver();

        WebElement logout = driver.findElement(By.cssSelector("html body div#container div#header div.top span.right span.logout a"));
        logout.click();

        Ecomm_Base baseTest = new Ecomm_Base(driver);
        Ecomm_MainPage eCommPage = baseTest.setUp("()", "Unknown", DataItems.validCustUsername, DataItems.validCustPassword);

        System.out.println("Navigating to Manual Entry...");
        Ecomm_ManualEntryPage manualEntryPage = eCommPage.clickManualEntry();
        manualEntryPage.waitForElement();

        System.out.println("Manual Entry page reached. Check that elements for Call Off Order are not present");
        AssertJUnit.assertFalse(manualEntryPage.findContractOrdButton());
        AssertJUnit.assertFalse(manualEntryPage.findNormalOrdButton());
        System.out.println("Elements for Call Off Order are not present");

    }

    @Test(groups = {"eComm"})
    public void CCO_GC_07() throws Exception {
        CCO_GC_05();

        WebDriver driver = getDriver();

        WebElement logout = driver.findElement(By.cssSelector("html body div#container div#header div.top span.right span.logout a"));
        logout.click();

        CCO_GC_04();

    }


    @Test(groups = {"eComm"})
    public void CCO_CO_01() throws Exception {
        CCO_GC_01();

        System.out.println("Navigating to Manual Entry...");
        WebDriver driver = getDriver();

        WebElement logout = driver.findElement(By.cssSelector("html body div#container div#header div.top span.right span.logout a"));
        logout.click();

        Ecomm_Base sTest = new Ecomm_Base(driver);
        Ecomm_MainPage eCommPage = sTest.setUp("()", "Unknown", DataItems.validCoatsUsername2, DataItems.validCoatsPassword);

        Ecomm_ManualEntryPage manualEntryPage = eCommPage.clickManualEntry();
        manualEntryPage.waitForElement();

        System.out.println("Manual Entry page reached. Entering customer details...");
        //Input Customer Details
        manualEntryPage.setCustomerName(DataItems.custDetails3[0]);
        manualEntryPage.setShipToParty(DataItems.custDetails3[1]);
        manualEntryPage.setRequestor(DataItems.custDetails3[2]);
        manualEntryPage.setBuyers(DataItems.custDetails3[3]);
        manualEntryPage.setPONumber(DataItems.custDetails3[4]);

        System.out.println("Customer details entered. Entering line details...");
        manualEntryPage.setDate(0);
        manualEntryPage.setContractPO("TEST ZCQ BE POSITIV 03", 0);
        manualEntryPage.setLineRef("000030", 0);

        System.out.println("Product details entered. Pressing next...");

        //Press next
        Ecomm_OrderConfirmationPage orderConf = manualEntryPage.pressNext();
        orderConf.waitForElement();
        System.out.println("Order confirmation page reached.");

        Verify.verify(orderConf.getOrderedQty2().getText().equals(DataItems.orderedQty2), "Order view: Ordered Qty does not match ");
        Verify.verify(orderConf.getAdjustedQty2().getText().equals(DataItems.adjustedQty2), "Order view: Adjusted Qty does not match expected input");
        Verify.verify(orderConf.getUOM().getText().equals(DataItems.UOM), "Order view: UOM does not match expected input");
        Verify.verify(orderConf.getUnitPrice().getText().equals(DataItems.unitPrice), "Order view: Unit Price does not match expected input");
        Verify.verify(orderConf.getValue2().getText().equals(DataItems.value3), "Order view: Value does not match expected input");
        System.out.println("Order is created with correct fields");
    }

    @Test(groups = {"eComm"})
    public void CCO_CO_02() throws Exception {
        CCO_GC_01();

        System.out.println("Navigating to Manual Entry...");
        WebDriver driver = getDriver();

        WebElement logout = driver.findElement(By.cssSelector("html body div#container div#header div.top span.right span.logout a"));
        logout.click();

        Ecomm_Base sTest = new Ecomm_Base(driver);
        Ecomm_MainPage eCommPage = sTest.setUp("()", "Unknown", DataItems.validCoatsUsername2, DataItems.validCoatsPassword);

        Ecomm_ManualEntryPage manualEntryPage = eCommPage.clickManualEntry();
        manualEntryPage.waitForElement();

        System.out.println("Manual Entry page reached. Entering customer details...");
        //Input Customer Details
        manualEntryPage.setCustomerName(DataItems.custDetails3[0]);
        manualEntryPage.setShipToParty(DataItems.custDetails3[1]);
        manualEntryPage.setRequestor(DataItems.custDetails3[2]);
        manualEntryPage.setBuyers(DataItems.custDetails3[3]);
        manualEntryPage.setPONumber(DataItems.custDetails3[4]);

        System.out.println("Customer details entered. Entering line details...");
        manualEntryPage.setDate(0);
        manualEntryPage.setContractPO("40000799", 0);
        manualEntryPage.setLineRef("000030", 0);

        System.out.println("Product details entered. Pressing next...");

        //Press next
        Ecomm_OrderConfirmationPage orderConf = manualEntryPage.pressNext();
        orderConf.waitForElement();
        System.out.println("Order confirmation page reached.");

        Verify.verify(orderConf.getOrderedQty2().getText().equals(DataItems.orderedQty2), "Order view: Ordered Qty does not match ");
        Verify.verify(orderConf.getAdjustedQty2().getText().equals(DataItems.adjustedQty2), "Order view: Adjusted Qty does not match expected input");
        Verify.verify(orderConf.getUOM().getText().equals(DataItems.UOM), "Order view: UOM does not match expected input");
        Verify.verify(orderConf.getUnitPrice().getText().equals(DataItems.unitPrice), "Order view: Unit Price does not match expected input");
        Verify.verify(orderConf.getValue2().getText().equals(DataItems.value3), "Order view: Value does not match expected input");
        System.out.println("Order is created with correct fields");
    }

    @Test(groups = {"eComm"})
    public void CCO_CO_03() throws Exception {
        CCO_GC_01();

        System.out.println("Navigating to Manual Entry...");
        WebDriver driver = getDriver();

        WebElement logout = driver.findElement(By.cssSelector("html body div#container div#header div.top span.right span.logout a"));
        logout.click();

        Ecomm_Base sTest = new Ecomm_Base(driver);
        Ecomm_MainPage eCommPage = sTest.setUp("()", "Unknown", DataItems.validCoatsUsername2, DataItems.validCoatsPassword);

        Ecomm_ManualEntryPage manualEntryPage = eCommPage.clickManualEntry();
        manualEntryPage.waitForElement();

        System.out.println("Manual Entry page reached. Entering customer details...");
        //Input Customer Details
        manualEntryPage.setCustomerName(DataItems.custDetails3[0]);
        manualEntryPage.setShipToParty(DataItems.custDetails3[1]);
        manualEntryPage.setRequestor(DataItems.custDetails3[2]);
        manualEntryPage.setBuyers(DataItems.custDetails3[3]);
        manualEntryPage.setPONumber(DataItems.custDetails3[4]);

        System.out.println("Customer details entered. Entering line details...");
        manualEntryPage.setDate(0);
        manualEntryPage.setContractPO("TEST ZCQ SFTP POSITIV 01", 0);
        manualEntryPage.setArticle(DataItems.conOrdArticle, 0);
        manualEntryPage.setShadeCode(DataItems.conOrdShadeCode, 0);

        System.out.println("Product details entered. Pressing next...");

        //Press next
        Ecomm_OrderConfirmationPage orderConf = manualEntryPage.pressNext();
        orderConf.waitForElement();
        System.out.println("Order confirmation page reached.");

        Verify.verify(orderConf.getOrderedQty2().getText().equals(DataItems.orderedQty), "Order view: Ordered Qty does not match ");
        Verify.verify(orderConf.getAdjustedQty2().getText().equals(DataItems.adjustedQty), "Order view: Adjusted Qty does not match expected input");
        Verify.verify(orderConf.getUOM().getText().equals(DataItems.UOM), "Order view: UOM does not match expected input");
        Verify.verify(orderConf.getUnitPrice().getText().equals(DataItems.unitPrice), "Order view: Unit Price does not match expected input");
        Verify.verify(orderConf.getValue2().getText().equals(DataItems.value2), "Order view: Value does not match expected input");
        System.out.println("Order is created with correct fields");

/*
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

        //Verify.verify(orderView.getYourMatNumCell().getText().equals(DataItems.yourMatNum),"Order view: Your Material Number does not match input");
        Verify.verify(orderView.getArticleCell().getText().equals(DataItems.expConOrdArticle),"Order view: Article does not match expected input");
        Verify.verify(orderView.getBrandCell().getText().equals(DataItems.expConOrdBrand),"Order view: Brand does not match expected input");
        Verify.verify(orderView.getTicketCell().getText().equals(DataItems.expConOrdTicket),"Order view: Ticket does not match expected input");
        Verify.verify(orderView.getShadeCodeCell().getText().equals(DataItems.expConOrdShadeCode),"Order view: Shade code does not match expected input");

        System.out.println("Values verified. Closing view...");
        //Exit view
        orderView.exitView();
        orderView.waitForInvisibility();
        driver.switchTo().defaultContent();

        System.out.println("View closed.");

        //Output order number for test reference
        String orderNumber = outOrders.getOrderNumber(rowNumber);
        System.out.println("Order Number: " + orderNumber);*/

    }

    @Test(groups = {"eComm"})
    public void CCO_CO_04() throws Exception {
        CCO_GC_01();

        System.out.println("Navigating to Manual Entry...");
        WebDriver driver = getDriver();

        WebElement logout = driver.findElement(By.cssSelector("html body div#container div#header div.top span.right span.logout a"));
        logout.click();

        Ecomm_Base sTest = new Ecomm_Base(driver);
        Ecomm_MainPage eCommPage = sTest.setUp("()", "Unknown", DataItems.validCoatsUsername2, DataItems.validCoatsPassword);

        Ecomm_ManualEntryPage manualEntryPage = eCommPage.clickManualEntry();
        manualEntryPage.waitForElement();

        System.out.println("Manual Entry page reached. Entering customer details...");
        //Input Customer Details
        manualEntryPage.setCustomerName(DataItems.custDetails3[0]);
        manualEntryPage.setShipToParty(DataItems.custDetails3[1]);
        manualEntryPage.setRequestor(DataItems.custDetails3[2]);
        manualEntryPage.setBuyers(DataItems.custDetails3[3]);
        manualEntryPage.setPONumber(DataItems.custDetails3[4]);

        System.out.println("Customer details entered. Entering line details...");
        //line 1
        manualEntryPage.setDate(0);
        manualEntryPage.setContractPO("TEST ZCQ BE POSITIV 03", 0);
        manualEntryPage.setLineRef("000030", 0);

        //line 2
        manualEntryPage.setDate(1);
        manualEntryPage.setContractPO("40000799", 1);
        manualEntryPage.setLineRef("000030", 1);

        //line 3
        manualEntryPage.setDate(2);
        manualEntryPage.setContractPO("TEST ZCQ SFTP POSITIV 01", 2);
        manualEntryPage.setArticle(DataItems.conOrdArticle, 2);
        manualEntryPage.setShadeCode(DataItems.conOrdShadeCode, 2);


        System.out.println("Product details entered. Pressing next...");

        //Press next
        Ecomm_OrderConfirmationPage orderConf = manualEntryPage.pressNext();
        orderConf.waitForElement();
        System.out.println("Order confirmation page reached.");

        //verify line 1
        Verify.verify(orderConf.getOrderedQty2().getText().equals(DataItems.orderedQty2), "Order view: Ordered Qty does not match ");
        Verify.verify(orderConf.getAdjustedQty2().getText().equals(DataItems.adjustedQty2), "Order view: Adjusted Qty does not match expected input");
        Verify.verify(orderConf.getUOM().getText().equals(DataItems.UOM), "Order view: UOM does not match expected input");
        Verify.verify(orderConf.getUnitPrice().getText().equals(DataItems.unitPrice), "Order view: Unit Price does not match expected input");
        Verify.verify(orderConf.getValue2().getText().equals(DataItems.value3), "Order view: Value does not match expected input");

        //verify line 2
        AssertJUnit.assertEquals(orderConf.getOrderedQty3().getText(), DataItems.orderedQtyNull);
        AssertJUnit.assertEquals(orderConf.getOrderedQty3().getText(), DataItems.adjustedQtyNull);
        Verify.verify(orderConf.getUOM3().getText().equals(DataItems.UOM), "Order view: UOM does not match expected input");
        Verify.verify(orderConf.getUnitPrice3().getText().equals(DataItems.unitPriceNull), "Order view: Unit Price does not match expected input");
        Verify.verify(orderConf.getValue3().getText().equals(DataItems.valueNull), "Order view: Value does not match expected input");

        //verify line 3
        Verify.verify(orderConf.getOrderedQty4().getText().equals(DataItems.orderedQty), "Order view: Ordered Qty does not match ");
        Verify.verify(orderConf.getAdjustedQty4().getText().equals(DataItems.adjustedQty), "Order view: Adjusted Qty does not match expected input");
        Verify.verify(orderConf.getUOM4().getText().equals(DataItems.UOM), "Order view: UOM does not match expected input");
        Verify.verify(orderConf.getUnitPrice4().getText().equals(DataItems.unitPrice), "Order view: Unit Price does not match expected input");
        Verify.verify(orderConf.getValue4().getText().equals(DataItems.value2), "Order view: Value does not match expected input");
        System.out.println("Order is created with correct fields");
    }

    @Test(groups = {"eComm"})
    public void CCO_CO_05() throws Exception {
        CCO_GC_01();

        System.out.println("Navigating to Manual Entry...");
        WebDriver driver = getDriver();

        WebElement logout = driver.findElement(By.cssSelector("html body div#container div#header div.top span.right span.logout a"));
        logout.click();

        Ecomm_Base sTest = new Ecomm_Base(driver);
        Ecomm_MainPage eCommPage = sTest.setUp("()", "Unknown", DataItems.validCoatsUsername2, DataItems.validCoatsPassword);

        Ecomm_ManualEntryPage manualEntryPage = eCommPage.clickManualEntry();
        manualEntryPage.waitForElement();

        System.out.println("Manual Entry page reached. Entering customer details...");
        //Input Customer Details
        manualEntryPage.setCustomerName(DataItems.custDetails3[0]);
        manualEntryPage.setShipToParty(DataItems.custDetails3[1]);
        manualEntryPage.setRequestor(DataItems.custDetails3[2]);
        manualEntryPage.setBuyers(DataItems.custDetails3[3]);
        manualEntryPage.setPONumber(DataItems.custDetails3[4]);

        System.out.println("Customer details entered. Entering line details...");
        manualEntryPage.setDate(0);
        manualEntryPage.setContractPO("test ZCQ qty check 01", 0);
        manualEntryPage.setArticle(DataItems.conOrdArticle, 0);
        manualEntryPage.setShadeCode(DataItems.conOrdShadeCode, 0);

        System.out.println("Product details entered. Pressing next...");

        //Press next
        Ecomm_OrderConfirmationPage orderConf = manualEntryPage.pressNext();
        orderConf.waitForElement();
        System.out.println("Order confirmation page reached.");

        boolean success;
        try {
            By lineWithErrorsButton = By.cssSelector("#BulkOrderOrderConfirmForm > div:nth-child(4) > div.grid_12 > a");
            WebElement button = new WebDriverWait(driver, DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(lineWithErrorsButton));
            button.click();

            CommonTask.waitForOverlay(driver);
            //System.out.println(CommonTask.getErrorDescription(driver));
            CommonTask.closeView(driver);
            success = false;
        } catch (Exception e) {
            System.out.println("No lines with errors");
            success = true;
        }
        AssertJUnit.assertFalse("Order Confirmation Page: Lines with errors button appears but test expected to fail", success);
        AssertJUnit.assertEquals(CommonTask.getErrorDescription(driver), DataItems.notUniqMsg);

        System.out.println("CONTRACT REFERENCE IS NOT UNIQUE as expected");
    }

    @Test(groups = {"eComm"})
    public void CCO_CO_06() throws Exception {
        CCO_GC_01();

        System.out.println("Navigating to Manual Entry...");
        WebDriver driver = getDriver();

        WebElement logout = driver.findElement(By.cssSelector("html body div#container div#header div.top span.right span.logout a"));
        logout.click();

        Ecomm_Base sTest = new Ecomm_Base(driver);
        Ecomm_MainPage eCommPage = sTest.setUp("()", "Unknown", DataItems.validCoatsUsername2, DataItems.validCoatsPassword);

        Ecomm_ManualEntryPage manualEntryPage = eCommPage.clickManualEntry();
        manualEntryPage.waitForElement();

        System.out.println("Manual Entry page reached. Entering customer details...");
        //Input Customer Details
        manualEntryPage.setCustomerName(DataItems.custDetails3[0]);
        manualEntryPage.setShipToParty(DataItems.custDetails3[1]);
        manualEntryPage.setRequestor(DataItems.custDetails3[2]);
        manualEntryPage.setBuyers(DataItems.custDetails3[3]);
        manualEntryPage.setPONumber(DataItems.custDetails3[4]);

        System.out.println("Customer details entered. Entering line details...");
        manualEntryPage.setDate(0);
        manualEntryPage.setContractPO("test ZCQ qty check 01", 0);

        System.out.println("Product details entered. Pressing next...");

        //Press next
        Ecomm_OrderConfirmationPage orderConf = manualEntryPage.pressNext();
        orderConf.waitForElement();
        System.out.println("Order confirmation page reached.");

        boolean success;
        try {
            By lineWithErrorsButton = By.cssSelector("#BulkOrderOrderConfirmForm > div:nth-child(4) > div.grid_12 > a");
            WebElement btn = new WebDriverWait(driver, DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(lineWithErrorsButton));
            btn.click();

            CommonTask.waitForOverlay(driver);
            //System.out.println(CommonTask.getErrorDescription(driver));
            CommonTask.closeView(driver);
            success = false;
        } catch (Exception e) {
            System.out.println("No lines with errors.");
            success = true;
        }
        AssertJUnit.assertFalse("Order Confirmation Page: Lines with errors button appears but test expected to fail", success);
        AssertJUnit.assertEquals(CommonTask.getErrorDescription(driver), DataItems.invalidDataMsg);

        System.out.println("INVALID COMBINATION OF INPUT DATA as expected");
    }

    @Test(groups = {"eComm"})
    public void CCO_CO_07() throws Exception {
        CCO_GC_01();

        System.out.println("Navigating to Manual Entry...");
        WebDriver driver = getDriver();

        WebElement logout = driver.findElement(By.cssSelector("html body div#container div#header div.top span.right span.logout a"));
        logout.click();

        Ecomm_Base sTest = new Ecomm_Base(driver);
        Ecomm_MainPage eCommPage = sTest.setUp("()", "Unknown", DataItems.validCoatsUsername2, DataItems.validCoatsPassword);

        Ecomm_ManualEntryPage manualEntryPage = eCommPage.clickManualEntry();
        manualEntryPage.waitForElement();

        System.out.println("Manual Entry page reached. Entering customer details...");
        //Input Customer Details
        manualEntryPage.setCustomerName(DataItems.custDetails3[0]);
        manualEntryPage.setShipToParty(DataItems.custDetails3[1]);
        manualEntryPage.setRequestor(DataItems.custDetails3[2]);
        manualEntryPage.setBuyers(DataItems.custDetails3[3]);
        manualEntryPage.setPONumber(DataItems.custDetails3[4]);

        System.out.println("Customer details entered. Entering line details...");
        manualEntryPage.setDate(0);
        manualEntryPage.setContractPO("TEST ZCQ ARUN 02", 0);
        manualEntryPage.setArticle(DataItems.conOrdArticle, 0);
        manualEntryPage.setShadeCode(DataItems.conOrdShadeCode2, 0);
        manualEntryPage.setLineRef("000010",0);

        System.out.println("Product details entered. Pressing next...");

        //Press next
        Ecomm_OrderConfirmationPage orderConf = manualEntryPage.pressNext();
        orderConf.waitForElement();
        System.out.println("Order confirmation page reached.");

        boolean success;
        try {
            By lineWithErrorsButton = By.cssSelector("#BulkOrderOrderConfirmForm > div:nth-child(4) > div.grid_12 > a");
            WebElement btn = new WebDriverWait(driver, DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(lineWithErrorsButton));
            btn.click();

            CommonTask.waitForOverlay(driver);
            //System.out.println(CommonTask.getErrorDescription(driver));
            CommonTask.closeView(driver);
            success = false;
        } catch (Exception e) {
            System.out.println("No lines with errors.");
            success = true;
        }
        AssertJUnit.assertFalse("Order Confirmation Page: Lines with errors button appears but test expected to fail", success);
        AssertJUnit.assertEquals(CommonTask.getErrorDescription(driver), DataItems.sapCodeMsg);

        System.out.println("CONTRACT SAP MATERIAL CODE DOES NOT MATCH INPUT SAP MATERIAL CODE as expected");
    }

}
