package com.coats.selenium.tests;

import AutomationFramework.*;
import PageObjects.*;
import com.coats.selenium.DriverFactory;
import com.google.common.base.Verify;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
public class Ecomm_ContractCallOffOrders_Test extends DriverFactory {


    @Test(groups = {"eComm", "QuickTest"})
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
        System.out.println("Check 'Enable Contract CALL OFF Order'...");
        editPage2.enableCallOffOrderCheckBox();
        System.out.println("Field checked. Un-Check 'SAP Contract Validity (Exclude Contracts Outside Validity Period)'...");
        editPage2.disableSAPContractValidityCheckBox();
        Mst_CustomersPage custPage2 = editPage2.clickSave();
        custPage2.waitForElement();

        System.out.println("Customers page reached. The Customer has been updated");
        AssertJUnit.assertTrue("Customers Page: Flash Message not as expected", custPage2.getFlashMessage().getText().equals("The Customer has been updated"));


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
        Mst_SalesOrgPage soPage2 = editPage.pressSave();
        soPage2.waitForElement();

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
        //System.out.println("Field checked. Un-Check 'SAP Contract Validity (Exclude Contracts Outside Validity Period)'...");
        editPage2.disableSAPContractValidityCheckBox();
        System.out.println("Customers Edit page page reached. Verify that Flag 'Enable Contract CALL OFF Order' is not present");
        synchronized (driver) {
            driver.wait(2000);
        }
        AssertJUnit.assertFalse("Customer Call Off Order checkbox is displayed!",editPage2.getCallOffOrderCheckBox().isDisplayed());

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

        CommonTask.setCheckBox(driver, PreFlows.normalOrderRadioButton);

        AssertJUnit.assertEquals(manualEntryPage.getFlashMessage().getText(), "NORMAL ORDER");
        CommonTask.setCheckBox(driver, PreFlows.contractOrderRadioButton);
        AssertJUnit.assertEquals(manualEntryPage.getFlashMessage().getText(), "CONTRACT ORDER");
        AssertJUnit.assertTrue(manualEntryPage.findContractPOField());
        AssertJUnit.assertTrue(manualEntryPage.findLineRefField());

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
        AssertJUnit.assertTrue(manualEntryPage.findContractPOField());
        AssertJUnit.assertTrue(manualEntryPage.findLineRefField());
        //AssertJUnit.assertTrue(manualEntryPage.findContractOrdButton());
        //AssertJUnit.assertTrue(manualEntryPage.findNormalOrdButton());
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


    @Test(groups = {"eComm", "QuickTest"})
    public void CCO_CO_01() throws Exception {
        CCO_GC_01();


        WebDriver driver = getDriver();

        PreFlows pf = new PreFlows();
        pf.logoutAction(driver);

        Ecomm_Base sTest = new Ecomm_Base(driver);
        Ecomm_MainPage eCommPage = sTest.setUp("()", "Unknown", DataItems.validCoatsUsername2, DataItems.validCoatsPassword);

        System.out.println("Navigating to Manual Entry...");
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
        orderConf.waitForElement2();
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

        WebDriver driver = getDriver();

        WebElement logout = driver.findElement(By.cssSelector("html body div#container div#header div.top span.right span.logout a"));
        logout.click();

        System.out.println("Navigating to Manual Entry...");
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
        orderConf.waitForElement2();
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

        WebDriver driver = getDriver();

        WebElement logout = driver.findElement(By.cssSelector("html body div#container div#header div.top span.right span.logout a"));
        logout.click();

        System.out.println("Navigating to Manual Entry...");
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
        orderConf.waitForElement2();
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

        WebDriver driver = getDriver();

        WebElement logout = driver.findElement(By.cssSelector("html body div#container div#header div.top span.right span.logout a"));
        logout.click();

        System.out.println("Navigating to Manual Entry...");
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
        orderConf.waitForElement2();
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

        WebDriver driver = getDriver();

        PreFlows pf = new PreFlows();
        pf.logoutAction(driver);

        System.out.println("Navigating to Manual Entry...");
        Ecomm_Base sTest = new Ecomm_Base(driver);
        Ecomm_MainPage eCommPage = sTest.setUp("()", "Unknown", DataItems.validCoatsUsername2, DataItems.validCoatsPassword);

        eCommPage.waitForElement();

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
        orderConf.waitForElement2();
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

        WebDriver driver = getDriver();

        WebElement logout = driver.findElement(By.cssSelector("html body div#container div#header div.top span.right span.logout a"));
        logout.click();

        System.out.println("Navigating to Manual Entry...");
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
        orderConf.waitForElement2();
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

        WebDriver driver = getDriver();

        WebElement logout = driver.findElement(By.cssSelector("html body div#container div#header div.top span.right span.logout a"));
        logout.click();

        System.out.println("Navigating to Manual Entry...");
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
        orderConf.waitForElement2();
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



    @Test(groups = {"eComm", "QuickTest"})
    public void CCO_UO_01() throws Exception {
        CCO_GC_01();

        System.out.println("Navigating to Manual Entry...");
        WebDriver driver = getDriver();

        WebElement logout = driver.findElement(By.cssSelector("html body div#container div#header div.top span.right span.logout a"));
        logout.click();

        Ecomm_Base sTest = new Ecomm_Base(driver);
        Ecomm_MainPage eCommPage = sTest.setUp("()", "Unknown", DataItems.validCustUsername, DataItems.validCustPassword);

        //new upload order page
        Ecomm_UploadOrderPage uploadPage = eCommPage.clickUploadOrder();
        uploadPage.waitForElement();

        System.out.println("Upload Order page loaded. Setting filepath...");

        uploadPage.setFilePath(FileFactory.createFile2("SUSST",1,"UO","",true));

        Ecomm_MappingAlert mapAlert = uploadPage.pressUpload();
        Ecomm_MappingPage mapPage = mapAlert.pressYes();
        mapPage.waitForElement();

        System.out.println("Mapping page reached. Setting sales org and customer name...");

        //mapPage.setSalesOrg("LK53");
        //mapPage.setCustomerName(DataItems.custDetails3[0]);

        System.out.println("Details set. Setting mapping...");

        String[][] mapping = {
                {"Article","Article"},
                {"Ticket","N/A"},
                {"Finish","Select"},
                {"Shade Code","Shade Code"},
                {"Required Date","Required Date"},
                {"Qty","Qty"},
                {"Style","N/A"},
                {"Style No./Production No.","Select"},
                {"Contract PO No.","Contract PO No."},
                {"Customer Price","N/A"},
                {"Sub Account","N/A"},
                {"Ship to Party Name","Ship to Party Name"},
                {"Your Material No.","N/A"},
                {"Brand","N/A"},
                {"Length","N/A"},
                {"Buyers","N/A"},
                {"Customer PO No","Customer PO No"},
                {"Requestor Name","Requestor"},
                {"Warehouse Instruction","N/A"},
                {"Buyer Sales Order Number","N/A"},
                {"Other Information","N/A"},
                //{"Customer Price","N/A"},
                {"Line Reference","Line Reference"}
        };

        mapPage.setMappingNew3(mapping,false,false,false,false,false);
        System.out.println("Mapping set. Confirming map...");

        Ecomm_OrderConfirmationPage orderConf = mapPage.pressConfirm();

        try {
            Alert alert = driver.switchTo().alert();
            alert.getText();
            alert.accept();
        } catch (Exception e) {
            System.out.println("No error displayed");
        }
        try {
            Alert alert = driver.switchTo().alert();
            alert.getText();
            alert.accept();
        } catch (Exception e) {
            System.out.println("No alert displayed");
        }
        orderConf.waitForElement();


        System.out.println("Order confirmation page reached. Asserting errors appear...");
        Verify.verify(orderConf.getOrderedQty2().getText().equals(DataItems.orderedQty2), "Order view: Ordered Qty does not match ");
        Verify.verify(orderConf.getAdjustedQty2().getText().equals(DataItems.adjustedQty2), "Order view: Adjusted Qty does not match expected input");
        Verify.verify(orderConf.getUOM().getText().equals(DataItems.UOM), "Order view: UOM does not match expected input");
        Verify.verify(orderConf.getUnitPrice().getText().equals(DataItems.unitPrice), "Order view: Unit Price does not match expected input");
        Verify.verify(orderConf.getValue2().getText().equals(DataItems.value3), "Order view: Value does not match expected input");
        System.out.println("Order is created with correct fields");

    }

    @Test(groups = {"eComm"})
    public void CCO_UO_02() throws Exception {
        CCO_GC_01();

        System.out.println("Navigating to Manual Entry...");
        WebDriver driver = getDriver();

        WebElement logout = driver.findElement(By.cssSelector("html body div#container div#header div.top span.right span.logout a"));
        logout.click();

        Ecomm_Base sTest = new Ecomm_Base(driver);
        Ecomm_MainPage eCommPage = sTest.setUp("()", "Unknown", DataItems.validCustUsername, DataItems.validCustPassword);

        //new upload order page
        Ecomm_UploadOrderPage uploadPage = eCommPage.clickUploadOrder();
        uploadPage.waitForElement();

        System.out.println("Upload Order page loaded. Setting filepath...");

        uploadPage.setFilePath(FileFactory.createFile2("SUSST",1,"UO2","",true));

        Ecomm_MappingAlert mapAlert = uploadPage.pressUpload();
        Ecomm_MappingPage mapPage = mapAlert.pressYes();
        mapPage.waitForElement();

        System.out.println("Mapping page reached. Setting sales org and customer name...");

        //mapPage.setSalesOrg("LK53");
        //mapPage.setCustomerName(DataItems.custDetails3[0]);

        System.out.println("Details set. Setting mapping...");

        String[][] mapping = {
                {"Article","Article"},
                {"Ticket","N/A"},
                {"Finish","Select"},
                {"Shade Code","Shade Code"},
                {"Required Date","Required Date"},
                {"Qty","Qty"},
                {"Style","N/A"},
                {"Style No./Production No.","Select"},
                {"Contract PO No.","Contract"},
                {"Customer Price","N/A"},
                {"Sub Account","N/A"},
                {"Ship to Party Name","Ship to Party Name"},
                {"Your Material No.","N/A"},
                {"Brand","N/A"},
                {"Length","N/A"},
                {"Buyers","N/A"},
                {"Customer PO No","Customer PO No"},
                {"Requestor Name","Requestor"},
                {"Warehouse Instruction","N/A"},
                {"Buyer Sales Order Number","N/A"},
                {"Other Information","N/A"},
                //{"Customer Price","N/A"},
                {"Line Reference","Line Reference"}
        };

        mapPage.setMappingNew3(mapping,false,false,false,false,false);
        System.out.println("Mapping set. Confirming map...");

        Ecomm_OrderConfirmationPage orderConf = mapPage.pressConfirm();

        try {
            Alert alert = driver.switchTo().alert();
            alert.getText();
            alert.accept();
        } catch (Exception e) {
            System.out.println("No error displayed");
        }
        try {
            Alert alert = driver.switchTo().alert();
            alert.getText();
            alert.accept();
        } catch (Exception e) {
            System.out.println("No alert displayed");
        }
        orderConf.waitForElement();

        System.out.println("Order confirmation page reached. Asserting errors appear...");
        Verify.verify(orderConf.getOrderedQty2().getText().equals(DataItems.orderedQty2), "Order view: Ordered Qty does not match ");
        Verify.verify(orderConf.getAdjustedQty2().getText().equals(DataItems.adjustedQty2), "Order view: Adjusted Qty does not match expected input");
        Verify.verify(orderConf.getUOM().getText().equals(DataItems.UOM), "Order view: UOM does not match expected input");
        Verify.verify(orderConf.getUnitPrice().getText().equals(DataItems.unitPrice), "Order view: Unit Price does not match expected input");
        Verify.verify(orderConf.getValue2().getText().equals(DataItems.value3), "Order view: Value does not match expected input");
        System.out.println("Order is created with correct fields");

    }

    @Test(groups = {"eComm"})
    public void CCO_UO_03() throws Exception {
        CCO_GC_01();

        System.out.println("Navigating to Manual Entry...");
        WebDriver driver = getDriver();

        WebElement logout = driver.findElement(By.cssSelector("html body div#container div#header div.top span.right span.logout a"));
        logout.click();

        Ecomm_Base sTest = new Ecomm_Base(driver);
        Ecomm_MainPage eCommPage = sTest.setUp("()", "Unknown", DataItems.validCustUsername, DataItems.validCustPassword);

        //new upload order page
        Ecomm_UploadOrderPage uploadPage = eCommPage.clickUploadOrder();
        uploadPage.waitForElement();

        System.out.println("Upload Order page loaded. Setting filepath...");

        uploadPage.setFilePath(FileFactory.createFile2("SUSST",1,"UO3","",true));

        Ecomm_MappingAlert mapAlert = uploadPage.pressUpload();
        Ecomm_MappingPage mapPage = mapAlert.pressYes();
        mapPage.waitForElement();

        System.out.println("Mapping page reached. Setting sales org and customer name...");

        //mapPage.setSalesOrg("LK53");
       //mapPage.setCustomerName(DataItems.custDetails3[0]);

        System.out.println("Details set. Setting mapping...");

        String[][] mapping = {
                {"Article","Article"},
                {"Ticket","N/A"},
                {"Finish","Select"},
                {"Shade Code","Shade Code"},
                {"Required Date","Required Date"},
                {"Qty","Qty"},
                {"Style","N/A"},
                {"Style No./Production No.","Select"},
                {"Contract PO No.","Contract"},
                {"Customer Price","N/A"},
                {"Sub Account","N/A"},
                {"Ship to Party Name","Ship to Party Name"},
                {"Your Material No.","N/A"},
                {"Brand","N/A"},
                {"Length","N/A"},
                {"Buyers","N/A"},
                {"Customer PO No","Customer PO No"},
                {"Requestor Name","Requestor"},
                {"Warehouse Instruction","N/A"},
                {"Buyer Sales Order Number","N/A"},
                {"Other Information","N/A"},
                //{"Customer Price","N/A"},
                {"Line Reference","Line Reference"}
        };
        mapPage.setMappingNew3(mapping,false,false,false,false,false);
        System.out.println("Mapping set. Confirming map...");

        Ecomm_OrderConfirmationPage orderConf = mapPage.pressConfirm();

        try {
            Alert alert = driver.switchTo().alert();
            alert.getText();
            alert.accept();
        } catch (Exception e) {
            System.out.println("No error displayed");
        }
        try {
            Alert alert = driver.switchTo().alert();
            alert.getText();
            alert.accept();
        } catch (Exception e) {
            System.out.println("No alert displayed");
        }
        orderConf.waitForElement();

        System.out.println("Order confirmation page reached. Asserting errors appear...");
        Verify.verify(orderConf.getOrderedQty2().getText().equals(DataItems.orderedQty2), "Order view: Ordered Qty does not match ");
        Verify.verify(orderConf.getAdjustedQty2().getText().equals(DataItems.adjustedQty2), "Order view: Adjusted Qty does not match expected input");
        Verify.verify(orderConf.getUOM().getText().equals(DataItems.UOM), "Order view: UOM does not match expected input");
        Verify.verify(orderConf.getUnitPrice().getText().equals(DataItems.unitPrice), "Order view: Unit Price does not match expected input");
        Verify.verify(orderConf.getValue2().getText().equals(DataItems.value3), "Order view: Value does not match expected input");
        System.out.println("Order is created with correct fields");

    }

    @Test(groups = {"eComm"})
    public void CCO_UO_04() throws Exception {
        CCO_GC_01();

        System.out.println("Navigating to Manual Entry...");
        WebDriver driver = getDriver();

        WebElement logout = driver.findElement(By.cssSelector("html body div#container div#header div.top span.right span.logout a"));
        logout.click();

        Ecomm_Base sTest = new Ecomm_Base(driver);
        Ecomm_MainPage eCommPage = sTest.setUp("()", "Unknown", DataItems.validCustUsername, DataItems.validCustPassword);

        //new upload order page
        Ecomm_UploadOrderPage uploadPage = eCommPage.clickUploadOrder();
        uploadPage.waitForElement();

        System.out.println("Upload Order page loaded. Setting filepath...");

        uploadPage.setFilePath(FileFactory.createFile2("SUSST",3,"UO4","",true));

        Ecomm_MappingAlert mapAlert = uploadPage.pressUpload();
        Ecomm_MappingPage mapPage = mapAlert.pressYes();
        mapPage.waitForElement();

        System.out.println("Mapping page reached. Setting sales org and customer name...");

        //mapPage.setSalesOrg("LK53");
        //mapPage.setCustomerName(DataItems.custDetails3[0]);

        System.out.println("Details set. Setting mapping...");

        String[][] mapping = {
                {"Article","Article"},
                {"Ticket","N/A"},
                {"Finish","Select"},
                {"Shade Code","Shade Code"},
                {"Required Date","Required Date"},
                {"Qty","Qty"},
                {"Style","N/A"},
                {"Style No./Production No.","Select"},
                {"Contract PO No.","Contract"},
                {"Customer Price","N/A"},
                {"Sub Account","N/A"},
                {"Ship to Party Name","Ship to Party Name"},
                {"Your Material No.","N/A"},
                {"Brand","N/A"},
                {"Length","N/A"},
                {"Buyers","N/A"},
                {"Customer PO No","Customer PO No"},
                {"Requestor Name","Requestor"},
                {"Warehouse Instruction","N/A"},
                {"Buyer Sales Order Number","N/A"},
                {"Other Information","N/A"},
                //{"Customer Price","N/A"},
                {"Line Reference","Line Reference"}
        };

        mapPage.setMappingNew3(mapping,false,false,false,false,false);
        System.out.println("Mapping set. Confirming map...");

        Ecomm_OrderConfirmationPage orderConf = mapPage.pressConfirm();

        try {
            Alert alert = driver.switchTo().alert();
            alert.getText();
            alert.accept();
        } catch (Exception e) {
            System.out.println("No error displayed");
        }
        try {
            Alert alert = driver.switchTo().alert();
            alert.getText();
            alert.accept();
        } catch (Exception e) {
            System.out.println("No alert displayed");
        }
        try {
            Alert alert = driver.switchTo().alert();
            alert.getText();
            alert.accept();
        } catch (Exception e) {
            System.out.println("No alert displayed");
        }
        try {
            Alert alert = driver.switchTo().alert();
            alert.getText();
            alert.accept();
        } catch (Exception e) {
            System.out.println("No alert displayed");
        }
        try {
            By lineWithErrorsButton = By.cssSelector("#BulkOrderOrderConfirmForm > div:nth-child(4) > div.grid_12 > a");
            WebElement button = new WebDriverWait(driver, DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(lineWithErrorsButton));
            button.click();

            CommonTask.waitForOverlay(driver);
            //System.out.println(CommonTask.getErrorDescription(driver));
            CommonTask.closeView(driver);
        } catch (Exception e) {
            System.out.println("No lines with errors");
        }

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
    public void CCO_UO_05() throws Exception {
        CCO_GC_01();

        System.out.println("Navigating to Manual Entry...");
        WebDriver driver = getDriver();

        WebElement logout = driver.findElement(By.cssSelector("html body div#container div#header div.top span.right span.logout a"));
        logout.click();

        Ecomm_Base sTest = new Ecomm_Base(driver);
        Ecomm_MainPage eCommPage = sTest.setUp("()", "Unknown", DataItems.validCustUsername, DataItems.validCustPassword);

        //new upload order page
        Ecomm_UploadOrderPage uploadPage = eCommPage.clickUploadOrder();
        uploadPage.waitForElement();

        System.out.println("Upload Order page loaded. Setting filepath...");

        uploadPage.setFilePath(FileFactory.createFile2("SUSST",1,"UO5","",true));

        Ecomm_MappingAlert mapAlert = uploadPage.pressUpload();
        Ecomm_MappingPage mapPage = mapAlert.pressYes();
        mapPage.waitForElement();

        System.out.println("Mapping page reached. Setting sales org and customer name...");

        //mapPage.setSalesOrg("LK53");
        //mapPage.setCustomerName(DataItems.custDetails3[0]);

        System.out.println("Details set. Setting mapping...");

        String[][] mapping = {
                {"Article","Article"},
                {"Ticket","N/A"},
                {"Finish","Select"},
                {"Shade Code","Shade Code"},
                {"Required Date","Required Date"},
                {"Qty","Qty"},
                {"Style","N/A"},
                {"Style No./Production No.","Select"},
                {"Contract PO No.","Contract"},
                {"Customer Price","N/A"},
                {"Sub Account","N/A"},
                {"Ship to Party Name","Ship to Party Name"},
                {"Your Material No.","N/A"},
                {"Brand","N/A"},
                {"Length","N/A"},
                {"Buyers","N/A"},
                {"Customer PO No","Customer PO No"},
                {"Requestor Name","Requestor"},
                {"Warehouse Instruction","N/A"},
                {"Buyer Sales Order Number","N/A"},
                {"Other Information","N/A"},
                //{"Customer Price","N/A"},
                {"Line Reference","Line Reference"}
        };

        mapPage.setMappingNew3(mapping,false,false,false,false,false);
        System.out.println("Mapping set. Confirming map...");

        Ecomm_OrderConfirmationPage orderConf = mapPage.pressConfirm();

        try {
            Alert alert = driver.switchTo().alert();
            alert.getText();
            alert.accept();
        } catch (Exception e) {
            System.out.println("No error displayed");
        }
        try {
            Alert alert = driver.switchTo().alert();
            alert.getText();
            alert.accept();
        } catch (Exception e) {
            System.out.println("No alert displayed");
        }
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
    public void CCO_UO_06() throws Exception {
        CCO_GC_01();

        System.out.println("Navigating to Manual Entry...");
        WebDriver driver = getDriver();

        WebElement logout = driver.findElement(By.cssSelector("html body div#container div#header div.top span.right span.logout a"));
        logout.click();

        Ecomm_Base sTest = new Ecomm_Base(driver);
        Ecomm_MainPage eCommPage = sTest.setUp("()", "Unknown", DataItems.validCustUsername, DataItems.validCustPassword);

        //new upload order page
        Ecomm_UploadOrderPage uploadPage = eCommPage.clickUploadOrder();
        uploadPage.waitForElement();

        System.out.println("Upload Order page loaded. Setting filepath...");

        uploadPage.setFilePath(FileFactory.createFile2("SUSST",1,"UO6","",true));

        Ecomm_MappingAlert mapAlert = uploadPage.pressUpload();
        Ecomm_MappingPage mapPage = mapAlert.pressYes();
        mapPage.waitForElement();

        System.out.println("Mapping page reached. Setting sales org and customer name...");

        //mapPage.setSalesOrg("LK53");
        //mapPage.setCustomerName(DataItems.custDetails3[0]);

        System.out.println("Details set. Setting mapping...");

        String[][] mapping = {
                {"Article","Article"},
                {"Ticket","N/A"},
                {"Finish","Select"},
                {"Shade Code","Shade Code"},
                {"Required Date","Required Date"},
                {"Qty","Qty"},
                {"Style","N/A"},
                {"Style No./Production No.","Select"},
                {"Contract PO No.","Contract"},
                {"Customer Price","N/A"},
                {"Sub Account","N/A"},
                {"Ship to Party Name","Ship to Party Name"},
                {"Your Material No.","N/A"},
                {"Brand","N/A"},
                {"Length","N/A"},
                {"Buyers","N/A"},
                {"Customer PO No","Customer PO No"},
                {"Requestor Name","Requestor"},
                {"Warehouse Instruction","N/A"},
                {"Buyer Sales Order Number","N/A"},
                {"Other Information","N/A"},
                //{"Customer Price","N/A"},
                {"Line Reference","Line Reference"}
        };



        mapPage.setMappingNew3(mapping,false,false,false,false,false);
        System.out.println("Mapping set. Confirming map...");

        Ecomm_OrderConfirmationPage orderConf = mapPage.pressConfirm();

        try {
            Alert alert = driver.switchTo().alert();
            alert.getText();
            alert.accept();
        } catch (Exception e) {
            System.out.println("No error displayed");
        }
        try {
            Alert alert = driver.switchTo().alert();
            alert.getText();
            alert.accept();
        } catch (Exception e) {
            System.out.println("No alert displayed");
        }
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
    public void CCO_UO_07() throws Exception {
        CCO_GC_01();

        System.out.println("01");
        System.out.println("Navigating to Manual Entry...");
        WebDriver driver = getDriver();
        System.out.println("02");

        WebElement logout = driver.findElement(By.cssSelector("html body div#container div#header div.top span.right span.logout a"));
        System.out.println("03");
        logout.click();
        System.out.println("04");
        Ecomm_Base sTest = new Ecomm_Base(driver);
        System.out.println("05");
        Ecomm_MainPage eCommPage = sTest.setUp("()", "Unknown", DataItems.validCustUsername, DataItems.validCustPassword);
        System.out.println("06");
        //new upload order page
        Ecomm_UploadOrderPage uploadPage = eCommPage.clickUploadOrder();
        System.out.println("07");
        uploadPage.waitForElement();
        System.out.println("08");

        System.out.println("Upload Order page loaded. Setting filepath...");

        uploadPage.setFilePath(FileFactory.createFile2("SUSST",1,"UO7","",true));
        System.out.println("09");

        Ecomm_MappingAlert mapAlert = uploadPage.pressUpload();
        System.out.println("10");
        Ecomm_MappingPage mapPage = mapAlert.pressYes();
        System.out.println("11");
        mapPage.waitForElement();
        System.out.println("12");

        System.out.println("Mapping page reached. Setting sales org and customer name...");

        //mapPage.setSalesOrg("LK53");
        //mapPage.setCustomerName(DataItems.custDetails3[0]);

        System.out.println("Details set. Setting mapping...");

        String[][] mapping = {
                {"Article","Article"},
                {"Ticket","N/A"},
                {"Finish","Select"},
                {"Shade Code","Shade Code"},
                {"Required Date","Required Date"},
                {"Qty","Qty"},
                {"Style","N/A"},
                {"Style No./Production No.","Select"},
                {"Contract PO No.","Contract"},
                {"Customer Price","N/A"},
                {"Sub Account","N/A"},
                {"Ship to Party Name","Ship to Party Name"},
                {"Your Material No.","N/A"},
                {"Brand","N/A"},
                {"Length","N/A"},
                {"Buyers","N/A"},
                {"Customer PO No","Customer PO No"},
                {"Requestor Name","Requestor"},
                {"Warehouse Instruction","N/A"},
                {"Buyer Sales Order Number","N/A"},
                {"Other Information","N/A"},
                //{"Customer Price","N/A"},
                {"Line Reference","Line Reference"}
        };
        System.out.println("13");

        mapPage.setMappingNew3(mapping,false,false,false,false,false);
        System.out.println("Mapping set. Confirming map...");
        System.out.println("14");

        Ecomm_OrderConfirmationPage orderConf = mapPage.pressConfirm();
        System.out.println("15");

        try {
            Alert alert = driver.switchTo().alert();
            alert.getText();
            alert.accept();
        } catch (Exception e) {
            System.out.println("No error displayed");
        }
        System.out.println("16");
        try {
            Alert alert = driver.switchTo().alert();
            alert.getText();
            alert.accept();
        } catch (Exception e) {
            System.out.println("No alert displayed");
        }
        System.out.println("17");
        orderConf.waitForElement();
        System.out.println("18");

        System.out.println("Order confirmation page reached.");

        boolean success;
        try {
            By lineWithErrorsButton = By.cssSelector("#BulkOrderOrderConfirmForm > div:nth-child(4) > div.grid_12 > a");
            WebElement btn = new WebDriverWait(driver, DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(lineWithErrorsButton));
            btn.click();
            System.out.println("19");
            CommonTask.waitForOverlay(driver);
            System.out.println("20");
            //System.out.println(CommonTask.getErrorDescription(driver));
            CommonTask.closeView(driver);
            System.out.println("21");
            success = false;
            System.out.println("22");
        } catch (Exception e) {
            System.out.println("No lines with errors.");
            success = true;
        }
        System.out.println("23");
        AssertJUnit.assertFalse("Order Confirmation Page: Lines with errors button appears but test expected to fail", success);
        AssertJUnit.assertEquals(CommonTask.getErrorDescription(driver), DataItems.sapCodeMsg);

        System.out.println("CONTRACT SAP MATERIAL CODE DOES NOT MATCH INPUT SAP MATERIAL CODE as expected");

    }


    @Test //Upload Order Page :: SUMST :: Page checks and realtime upload order of <100 lines
            (groups = {"eComm","eComm_Orders","QuickTest","Upload_Order"}, enabled = false)
    public void OE_RTU() throws Exception {
        //new chrome driver
        WebDriver driver = getDriver();

        //new base test to set up
        Ecomm_Base uortTest1 = new Ecomm_Base(driver);
        //Set up returns an eComm page
        Ecomm_MainPage eCommPage = uortTest1.setUp("UPLOAD ORDER TEST 1: File of <100 lines, realtime upload", "OE_RTU_01 OE_RTU_07");

      /*  driver.get(DataItems.cceURL);
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
        editPage2.clickSave();
        //editPage2.waitForElement();

        PreFlows pf = new PreFlows();
        pf.chooseTheOtherProfile(driver);*/

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

        System.out.println("Assertions successful. Sending file path...");

        //Send file path to field
        uploadPage.setFilePath(FileFactory.createFile("SUMST", 3, "Basic2", "", true));

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

        Ecomm_OrderConfirmationPage orderConf = mappedPage.pressConfirm();
        Alert alert2 = Wait.alert(driver);
        alert2.accept();

        try {
            Alert alert3 = driver.switchTo().alert();
            alert3.getText();
            alert3.accept();
        } catch (Exception e) {
            System.out.println("No alert displayed");
        }

        try {
            Alert alert4 = driver.switchTo().alert();
            alert4.getText();
            alert4.accept();
        } catch (Exception e) {
            System.out.println("No alert displayed");
        }

        String flashMessageTxt=driver.findElement(DataItems.flashMessage).getText();
        Assert.assertEquals(flashMessageTxt, "No. of rows Processed :3\n" +
                "No. of rows Failed :0\n" +
                "\n" +
                "Your orders are split due to some of the order lines don’t have the shade code in the upload file or not defined in our WBA system. Please refer to orders/Waiting for shade or shade not available submenu for more detail.\n" +
                "\n" +
                "No. of Order ready to process :1\n" +
                "No. of Line Item ready to process :1\n" +
                "No. of Order transfered to Waiting for Shade page :1\n" +
                "No. of Order transfered to Shade not available page :1");

        By editBtn2= By.cssSelector("#remove_0 > td:nth-child(1) > a > span");
        WebElement element = driver.findElement(editBtn2);
        Assert.assertTrue(element.isDisplayed());
        element.click();

        driver.switchTo().frame("TB_iframeContent");

        By cancelButton = By.id("cancel1");
        WebElement cancelBtn = driver.findElement(cancelButton);
        Assert.assertTrue(cancelBtn.isDisplayed());
        cancelBtn.click();

        try {
            Alert alert5 = driver.switchTo().alert();
            alert5.getText();
            alert5.accept();
        } catch (Exception e) {
            System.out.println("No alert displayed");
        }

        System.out.println("Map confirmed. Submitting order...");

        // Ecomm_OutstandingOrdersPage outOrdersPage = orderConf.pressSubmit();
        //outOrdersPage.waitForElement();

        System.out.println("Order submitted. Navigating to Outstanding Upload Order...");

        // String orderNo = outOrdersPage.getOrderNumber(0);

        //  System.out.println("Order number: "+orderNo);

    }

}
