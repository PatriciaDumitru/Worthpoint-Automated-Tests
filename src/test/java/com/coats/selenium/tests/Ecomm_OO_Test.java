package com.coats.selenium.tests;

import AutomationFramework.DataItems;
import AutomationFramework.PreFlows;
import PageObjects.CCE_MainPage;
import PageObjects.Ecomm_DeniedOrderPage;
import PageObjects.Ecomm_ExportDownloadPage;
import PageObjects.Ecomm_MainPage;
import PageObjects.Ecomm_ManualEntryPage;
import PageObjects.Ecomm_OrderApprovalHistoryPage;
import PageObjects.Ecomm_OrderConfirmationPage;
import PageObjects.Ecomm_OrderViewPage;
import PageObjects.Ecomm_OutstandingOrderDraftPage;
import PageObjects.Ecomm_OutstandingOrdersPage;
import PageObjects.Ecomm_OutstandingUploadDraftPage;
import PageObjects.Ecomm_PendingApprovalListPage;
import PageObjects.Mst_CustomersPage;
import PageObjects.Mst_EditCustomerPage;
import PageObjects.WBA_LoginPage;
import com.coats.selenium.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

//This class covers the Outstanding Orders section
public class Ecomm_OO_Test extends DriverFactory {
    
    @Test //Outstanding Orders Page :: Page and filter checks
    (groups ={"eComm", "QuickTest"})
    public void OP1() throws Exception{
        WebDriver driver = getDriver();
        
        //new base test to handle set up
        Ecomm_Base susstTest4 = new Ecomm_Base(driver);
        //Set up returns a manual entry page to begin data entry
        Ecomm_MainPage eCommPage = susstTest4.setUp("OUTSTANDING ORDERS OP1: Complete order from draft","G_OP_F_1");

        eCommPage.waitForElement();

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
    
    @Test //Outstanding Order Drafts Page :: Page and filter checks, view, edit, and cancel
    (groups = {"eComm", "QuickTest"})
    public void ODP1() throws Exception {
        WebDriver driver = getDriver();
        
        //new base test to handle set up
        Ecomm_Base susstTest4 = new Ecomm_Base(driver);
        //Set up returns a manual entry page to begin data entry
        Ecomm_MainPage eCommPage = susstTest4.setUp("OUTSTANDING ORDER DRAFTS ODP1: Page check, search, view, edit, cancel draft","G_OP_ODP_1 to 5");

        eCommPage.waitForElement();

        System.out.println("Navigating to Outstanding Order Draft Page...");
        
        Ecomm_OutstandingOrderDraftPage draftPage = eCommPage.clickOutstandingDraft();
        draftPage.waitForElement();
        
        System.out.println("Outstanding Draft Page reached.");
                
        draftPage.assertBaseElements();
        
        System.out.println("Entering PO number for search criteria...");
        
        draftPage.setPONumber("AutoTestPO");
        
        draftPage.pressSearch();
        draftPage.waitForLoad();
        
        System.out.println("Search complete. Viewing draft...");
        
        draftPage.pressView();
        
        //Close view
        Ecomm_OutstandingOrderDraftPage draftPage2 = draftPage.closeView();
        
        System.out.println("Draft view closed. Pressing edit draft...");

        driver.navigate().refresh();

        Ecomm_ManualEntryPage manualEntry = draftPage2.pressEdit();
        
        System.out.println("Edit draft pressed. Navigating back to draft search page...");
        
        driver.navigate().back();
        
        System.out.println("Search page reached. Pressing cancel draft...");
        
        //Cancel a draft
        draftPage2.pressCancel();
        
        System.out.println("Draft cancelled.");

    }
       
    @Test //Upload Order Drafts Page :: Page checks, edit, and cancel upload order draft
    (groups = {"eComm", "QuickTest"})
    public void UODP1() throws Exception {
        WebDriver driver = getDriver();
        
        //new base test to handle set up
        Ecomm_Base susstTest4 = new Ecomm_Base(driver);
        //Set up returns a manual entry page to begin data entry
        Ecomm_MainPage eCommPage = susstTest4.setUp("OUTSTANDING UPLOAD DRAFTS UODP1: Page check, search, view, edit, cancel draft","G_OP_UODP_1 to 5");

        System.out.println("Navigating to Outstanding Upload Drafts Page...");
        
        Ecomm_OutstandingUploadDraftPage upDraftPage = eCommPage.clickOutstandingUploadDraft();
        upDraftPage.waitForLoad();
        
        System.out.println("Outstanding Upload Drafts reached.");
        
        upDraftPage.assertBaseElements();
        
        System.out.println("Entering Customer Name for search criteria...");
        
        upDraftPage.setCustomerName(DataItems.custDetails[0]);
        
        upDraftPage.pressSearch();
        upDraftPage.waitForElement();
        
        System.out.println("Search complete. Pressing edit...");
        
        Ecomm_OrderConfirmationPage upConf = upDraftPage.pressEdit();
        upConf.waitForLoad();
        
        System.out.println("Order confirmation page reached. Asserting base elements...");
        
        upConf.assertBaseElements();
        
        System.out.println("Assertions successful. Navigating back to Outstanding Upload Drafts...");
        
        driver.navigate().back();
        
        System.out.println("Outstanding Upload Drafts page reached. Pressing delete draft...");
        
        upDraftPage.pressDelete();
        
        System.out.println("Draft deleted.");
        
    }
    
    @Test //Pending Approval List Page :: Requester user :: Page and filter checks, print function
    (groups = {"eComm", "QuickTest"}) //CHANGES MASTER DATA
    public void PA1() throws Exception {
        WebDriver driver = getDriver();
        
        Cce_Base base = new Cce_Base(driver);
        CCE_MainPage mainPage = base.setUp("Pending Approval Page: Page and filter checks/print function for Requester","OA_WP_OO_PAL_RU_1 to 4"); 
        mainPage.waitForLoad();

        PreFlows pf = new PreFlows();
        pf.deactivateCallOffOrderForSalesOrg(driver, DataItems.salesOrgID51);
//        pf.deActivateCallOffOrderForCustomer(driver, DataItems.lifeEasyCustomer);

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
        editPage.clickSave();
        
        System.out.println("Approval Workflow enabled. Logging into requester account...");
        
        WBA_LoginPage liPage = editPage.pressLogout();
        liPage.waitForElement();
        
        Ecomm_Base base2 = new Ecomm_Base(driver);
        Ecomm_MainPage mainPage2 = base2.setUp("", "", DataItems.requesterUsername, DataItems.requesterPassword);
        mainPage2.waitForLoad();

        System.out.println("Logged in. Navigating to Manual Entry Page...");


        Ecomm_ManualEntryPage mePage = mainPage2.clickManualEntry();
        mePage.waitForElement();
        
        System.out.println("Manual Entry Page reached. Creating order...");
        
        mePage.setShipToParty(DataItems.custDetails[1]);
        mePage.setPONumber("Requester_");
        
        mePage.setYourMaterialNumber(DataItems.yourMatNum, 0);
        mePage.setQty(DataItems.quantity, 0);
        mePage.setDate(0);
        
        Ecomm_OrderConfirmationPage orderConf = mePage.pressNext();
        orderConf.waitForLoad();

        Ecomm_PendingApprovalListPage pendPage = orderConf.pressSendForApproval();
        pendPage.waitForElement();
        
        System.out.println("Pending Approval List Page reached. Checking title...");
        
        AssertJUnit.assertTrue("Pending Approval Page: Title not as expected",pendPage.getBreadcrumb().getText().equals("Orders | Pending Approval List"));
        
        System.out.println("Title checked");
        
        pendPage.assertBaseElements();
        
        System.out.println("Checking fields...");
        
        //pendPage.checkFieldsRequester();
        
        System.out.println("Fields checked. Entering filter criteria...");
        System.out.println("Last used Po Number:"+DataItems.lastUsedPO);
        System.out.println("Last used Po Number:"+DataItems.lastUsedPO);
        pendPage.setCustPOInput(DataItems.lastUsedPO);
        
        System.out.println("Filter criteria entered. Listing records...");
        
        pendPage.pressSearch();
        pendPage.waitForLoad();
        
        System.out.println("Records listed. Finding order...");
        
        int row2 = pendPage.getRow(DataItems.lastUsedPO);
        
        AssertJUnit.assertTrue("Pending Approval List Page: Order (Customer PO: "+DataItems.lastUsedPO+") not displayed in list",row2==-1);
        
        String orderNo = pendPage.getOrderNo(row2);
        
        System.out.println("Order found.");
        System.out.println("Order No.: " + orderNo);
        
        System.out.println("Viewing order...");
        
        Ecomm_OrderViewPage viewPage = pendPage.pressView(row2);
        viewPage.waitForContent();
        
        System.out.println("Order view displayed. Closing view...");
        
        viewPage.closeView();
        viewPage.waitForInvisibility();
        
        System.out.println("View closed. Printing record...");
        
        Ecomm_OrderViewPage printPage = pendPage.pressPrint(row2);
        printPage.waitForContent();
        
        System.out.println("Print view displayed. Closing view...");
        
        printPage.closeView();
        printPage.waitForInvisibility();
        
        System.out.println("Print view closed. Turning approval workflow off...");
        
        WBA_LoginPage liPage2 = pendPage.pressLogout();
        liPage2.waitForElement();
        
        Cce_Base base3 = new Cce_Base(driver);
        CCE_MainPage cceMainPage = base3.setUp("", "", DataItems.validCoatsUsername, DataItems.validCoatsPassword);
        cceMainPage.waitForLoad();
        
        Mst_CustomersPage custPage2 = cceMainPage.selectCustomers();
        custPage2.waitForElement();
        
        System.out.println("Customers page reached. Finding customer...");
        
        custPage.setCustomerName(DataItems.custDetails[0]);
        custPage.pressSearch();
        int row3 = custPage.findCustomer(DataItems.custDetails[0]);
        
        Mst_EditCustomerPage editPage2 = custPage.pressEdit(row3);
        editPage2.waitForElement();
        
        AssertJUnit.assertTrue("Edit Customer Page: Customer name differs in edit page from Customers Table",editPage2.getCustomerName().equals(DataItems.custDetails[0]));
        
        editPage2.unsetApprovalWorkflow();
        Mst_CustomersPage custPage3 = editPage2.clickSave();
        custPage3.waitForElement();
        
        System.out.println("Approval workflow disabled.");
        
    }

     // APPROVERS DON'T HAVE MANUAL ENTRY ACCESS, THIS TEST MAY BE readded later
    @Test //Pending Approval List Page :: Approver User :: Page and filter checks, approver/deny function
    (groups = {"eComm"},enabled = true) //CHANGES MASTER DATA
    public void PA2() throws Exception {
        WebDriver driver = getDriver();
        
        Cce_Base base = new Cce_Base(driver);
        CCE_MainPage mainPage = base.setUp("Pending Approval Page PA2: (Approver user) Page and filter checks/print function, approver/reject","OA_WP_OO_PAL_AU_1 to 6"); 
        mainPage.waitForLoad();
        
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
        editPage.clickSave();
        
        System.out.println("Approval Workflow turned on. Logging into approver account...");
        
        WBA_LoginPage liPage = editPage.pressLogout();
        liPage.waitForElement();
        
        Ecomm_Base base2 = new Ecomm_Base(driver);
        Ecomm_MainPage mainPage2 = base2.setUp("", "", DataItems.approverUsername, DataItems.approverPassword);
        mainPage2.waitForLoad();


        System.out.println("Logged in. Navigating to Manual Entry...");
        
        Ecomm_ManualEntryPage mePage = mainPage2.clickManualEntry();
        mePage.waitForElement();
        
        System.out.println("Manual Entry Page reached. Creating order...");
        
        mePage.setShipToParty(DataItems.subCustDetails[1]);
        mePage.setPONumber("ApproverTest_");
        mePage.setBuyers(DataItems.subCustDetails[3]);
        mePage.setArticle(DataItems.article,0);
        mePage.setShadeCode(DataItems.expShadeCode, 0);
        mePage.setQty(DataItems.quantity, 0);
        mePage.setDate(0);
        
        Ecomm_OrderConfirmationPage orderConf = mePage.pressNext();
        orderConf.waitForLoad();

        Ecomm_PendingApprovalListPage pendPage = orderConf.pressSendForApproval();
        pendPage.waitForElement();

        System.out.println("Pending Approval List Page reached. Checking title...");
        
        AssertJUnit.assertTrue("Pending Approval Page: Title not as expected",pendPage.getBreadcrumb().getText().equals("Orders | Pending Approval List"));
        
        System.out.println("Title checked");
        
        pendPage.assertBaseElements();
        
        System.out.println("Checking fields...");
        
        pendPage.checkFieldsApprover();

        driver.navigate().refresh();

        System.out.println("Fields checked. Creating another order...");
        
        Ecomm_ManualEntryPage mePage2 = pendPage.clickManualEntry();
        mePage2.waitForElement();
        
        mePage.setShipToParty(DataItems.subCustDetails[1]);
        mePage.setPONumber("ApproverTest_");
        mePage.setBuyers(DataItems.subCustDetails[3]);
        mePage.setArticle(DataItems.article,0);
        mePage.setShadeCode(DataItems.shadeCode,0);
        mePage.setQty(DataItems.quantity, 0);
        mePage.setDate(0);
        
        Ecomm_OrderConfirmationPage orderConf2 = mePage.pressNext();
        orderConf2.waitForLoad();

        Ecomm_PendingApprovalListPage pendPage2 = orderConf2.pressSendForApproval();
        pendPage2.waitForElement();
        
        System.out.println("Order created, Pending page reached. Entering filter criteria...");
        
        pendPage2.setCustPOInput(DataItems.lastUsedPO);
        
        System.out.println("Filter criteria entered. Listing records...");
        
        pendPage2.pressSearch();
        pendPage2.waitForLoad();
        
        System.out.println("Records listed. Finding order...");
        
        int row2 = pendPage2.getRowAlt(DataItems.lastUsedPO);
        
        AssertJUnit.assertFalse("Pending Approval List Page: Order (Customer PO: "+DataItems.lastUsedPO+") not displayed in list",row2==-1);
        
        String orderNo = pendPage2.getOrderNo(row2);
        
        System.out.println("Order found.");
        System.out.println("Order No.: " + orderNo);
        
        System.out.println("Viewing order...");
        
        Ecomm_OrderViewPage viewPage = pendPage2.pressView(row2);
        viewPage.waitForContent();
        
        System.out.println("Order view displayed. Closing view...");
        
        viewPage.closeView();
        viewPage.waitForInvisibility();
        
        System.out.println("View closed. Printing record...");
        
        Ecomm_OrderViewPage printPage = pendPage2.pressPrint(row2);
        printPage.waitForContent();
        
        System.out.println("Print view displayed. Closing view...");
        
        printPage.closeView();
        printPage.waitForInvisibility();
        
        pendPage2.pressReset();
        pendPage2.waitForElement();
        
        System.out.println("Print view closed. Approving order...");
        
        System.out.println("Order Number searched: " + orderNo);
        AssertJUnit.assertTrue("Pending Approval List Page: Approver user: Order not approved as it was not found in table",pendPage2.approveOrder(orderNo));
    
        System.out.println("Order approved. Approving all orders...");
        
        //Get PO of top row to check presence in outstanding orders
        String custPO = pendPage2.getCustPOCell(1).getText();
        
        Ecomm_PendingApprovalListPage pendPage3 = pendPage2.pressApproveAll();
        pendPage3.waitForElement();
        
        System.out.println("All orders approved. Checking orders appear in outstanding orders page...");
        
        Ecomm_OutstandingOrdersPage outOrders = pendPage3.clickOutstandingOrders();
        outOrders.waitForElement();
        
        AssertJUnit.assertFalse("Outstanding Orders Page: Order (PO: "+custPO+") not found after approval submitted",outOrders.getRowAlt(custPO)==-1);
        
        System.out.println("Order appears correctly. Disabling approver workflow...");
        
        WBA_LoginPage liPage2 = pendPage2.pressLogout();
        liPage2.waitForElement();
        
        Cce_Base base3 = new Cce_Base(driver);
        CCE_MainPage cceMainPage = base3.setUp("", "");
        cceMainPage.waitForLoad();
        
        Mst_CustomersPage custPage2 = cceMainPage.selectCustomers();
        custPage2.waitForElement();
        
        System.out.println("Customers page reached. Finding customer...");
        
        custPage.setCustomerName(DataItems.custDetails[0]);
        custPage.pressSearch();
        int row3 = custPage.findCustomer(DataItems.custDetails[0]);
        
        Mst_EditCustomerPage editPage2 = custPage.pressEdit(row3);
        editPage2.waitForElement();
        
        AssertJUnit.assertTrue("Edit Customer Page: Customer name differs in edit page from Customers Table",editPage2.getCustomerName().equals(DataItems.custDetails[0]));
        
        editPage2.unsetApprovalWorkflow();
        Mst_CustomersPage custPage3 = editPage2.clickSave();
        custPage3.waitForElement();
        
        System.out.println("Approval workflow disabled.");
        
    }

    @Test //Denied Order Page :: Requester User :: Page and filter checks, edit and delete
    (groups = {"eComm", "QuickTest"}) //CHANGES MASTER DATA
    public void DO1() throws Exception {
        WebDriver driver = getDriver();
        
        Cce_Base base = new Cce_Base(driver);
        CCE_MainPage mainPage = base.setUp("Denied Order Page DO1: (Requester user) Page and filter checks, view/edit/delete functions","OA_WP_OO_DO_RU_1 to 5"); 
        mainPage.waitForLoad();

        PreFlows pf = new PreFlows();
        pf.activateCallOffOrderForSalesOrg(driver, DataItems.salesOrgID51);
        pf.deActivateCallOffOrderForCustomer(driver, DataItems.anglerTestIndonesiaCustomer);

        System.out.println("Navigating to Masters...");
        
        Mst_CustomersPage custPage = mainPage.selectCustomers();
        custPage.waitForElement();
        
        System.out.println("Customers Master reached. Finding 'Angler' and turning approval workflow on...");
        
        custPage.setCustomerName(DataItems.custDetails[0]);
        custPage.pressSearch();
        int row = custPage.findCustomer(DataItems.custDetails[0]);
        
        Mst_EditCustomerPage editPage = custPage.pressEdit(row);
        editPage.waitForElement();
        
        AssertJUnit.assertTrue("Edit Customer Page: Customer name differs in edit page from Customers Table",editPage.getCustomerName().equals(DataItems.custDetails[0]));
        
        editPage.setApprovalWorkflow();
        editPage.clickSave();
        custPage.waitForElement();
        
        System.out.println("Approval Workflow enabled. Logging in to requester account...");

        Ecomm_MainPage mainPage2 = custPage.clickEcomm();
        mainPage2.waitForLoad();
        
        Ecomm_ManualEntryPage mePage = mainPage2.clickManualEntry();
        mePage.waitForElement();
        
        System.out.println("Manual Entry Page reached. Creating order...");

        mePage.setCustomerName(DataItems.subCustDetails[0]);
        mePage.setShipToParty(DataItems.subCustDetails[1]);
        mePage.setPONumber("DeniedOrderTest_");
        mePage.setRequestor(DataItems.subCustDetails[2]);
        mePage.setBuyers(DataItems.subCustDetails[3]);
        mePage.setArticle(DataItems.article,0);
        mePage.setShadeCode(DataItems.shadeCode,0);
        mePage.setQty(DataItems.quantity, 0);
        mePage.setDate(0);
        
        Ecomm_OrderConfirmationPage orderConf = mePage.pressNext();
        orderConf.waitForElement();
        
        Ecomm_PendingApprovalListPage pendPage = orderConf.pressSendForApproval();
        pendPage.waitForElement();
        
        System.out.println("Order created. Creating another...");
        
        Ecomm_ManualEntryPage mePage2 = pendPage.clickManualEntry();
        mePage2.waitForElement();

        mePage2.setCustomerName(DataItems.subCustDetails[0]);
        mePage2.setShipToParty(DataItems.subCustDetails[1]);
        mePage2.setPONumber("DeniedOrderTest_");
        mePage2.setBuyers(DataItems.subCustDetails[3]);
        mePage2.setRequestor(DataItems.subCustDetails[2]);
        mePage2.setArticle(DataItems.article,0);
        mePage2.setShadeCode(DataItems.expShadeCode,0);
        mePage2.setQty(DataItems.quantity, 0);
        mePage2.setDate(0);
        
        Ecomm_OrderConfirmationPage orderConf2 = mePage2.pressNext();
        orderConf2.waitForElement();
        
        Ecomm_PendingApprovalListPage pendPage2 = orderConf2.pressSendForApproval();
        pendPage2.waitForLoad();
        
        System.out.println("Orders created. Logging in to approver account to deny orders...");
        
        WBA_LoginPage liPage3 = pendPage2.pressLogout();
        liPage3.waitForElement();
        
        Ecomm_Base base3 = new Ecomm_Base(driver);
        Ecomm_MainPage mainPage3 = base3.setUp("", "", DataItems.approverUsername, DataItems.approverPassword);
        mainPage3.waitForLoad();
        
        System.out.println("Logged in. Navigating to Pending Approval Page...");
        
        Ecomm_PendingApprovalListPage pendPage3 = mainPage3.clickPendingApprovalListPageApprover2();
        pendPage3.waitForLoad();
        
        System.out.println("Pending page reached. Checking for records...");
        
        AssertJUnit.assertTrue("Pending Approval List Page: No records despite two orders sent for approval",pendPage3.checkForRecords());
        
        System.out.println("Records present. Denying top record...");
        
        String orderNo = pendPage3.getOrderNo(1);
        
        pendPage3.denyOrder(orderNo);
        
        System.out.println("Navigating to Denied Order Page...");
        
        Ecomm_DeniedOrderPage doPage = mainPage2.clickDeniedOrderApprover2();
        doPage.waitForElement();
        
        System.out.println("Denied Order Page reached. Checking title...");
        
        AssertJUnit.assertTrue("Denied Order Page: Title not as expected",doPage.getBreadcrumb().getText().equals("Orders | Denied Order List"));
    
        System.out.println("Title checked");
        
        doPage.assertBaseElements();
        
        System.out.println("Checking fields...");
        
        doPage.checkFields();
        
        System.out.println("Fields checked. Checking Order appears as expected...");
        
        AssertJUnit.assertFalse("Denied Order Page: Order (Order No.: "+orderNo+") not found",doPage.findOrder(orderNo)==-1);
        
        System.out.println("Order appears. Navigating to Pending Approval Page...");
        
        Ecomm_PendingApprovalListPage pendPage4 = doPage.clickPendingApprovalListPageApprover();
        pendPage4.waitForElement();
        
        System.out.println("Page reached. Denying all orders...");
        
        String orderNo2 = pendPage4.getOrderNo(1);
        
        pendPage4.pressDenyAll();
        pendPage4.waitForElement();
        
        System.out.println("All orders denied. Checking orders appear in denied orders page...");
        
        Ecomm_DeniedOrderPage doPage2 = pendPage4.clickDeniedOrderApprover();
        doPage2.waitForElement();
        
        AssertJUnit.assertFalse("Denied Orders Page: Orders do not appear in denied orders page after multi-deny",doPage2.findOrder(orderNo2)==1);
        
        System.out.println("Orders appear. Logging into requester account to edit and delete denied orders...");
        
        WBA_LoginPage liPage4 = doPage2.pressLogout();
        liPage4.waitForElement();
        
        Ecomm_Base base4 = new Ecomm_Base(driver);
        Ecomm_MainPage mainPage4 = base4.setUp("", "", DataItems.anglerRequesterUsername, DataItems.anglerRequesterPassword);
        mainPage4.waitForLoad();
        
        Ecomm_DeniedOrderPage doPage3 = mainPage4.clickDeniedOrderRequester();
        doPage3.waitForElement();
        
        System.out.println("Denied orders page reached. Viewing top item...");
        
        String orderNo3 = doPage3.getOrderNo(1);
        
        Ecomm_OrderViewPage viewPage = doPage3.pressView(1);
        viewPage.waitForContent();
        
        System.out.println("View opened. Closing view...");
        
        viewPage.closeView();
        viewPage.waitForInvisibility();
        
        System.out.println("View closed. Deleting order...");
        
        Ecomm_OutstandingOrderDraftPage draftPage = doPage3.pressCancel(1);
        draftPage.waitForElement();
        
        System.out.println("Order deleted (Order No.: "+orderNo3+"). Checking order is removed...");
        
        doPage3 = draftPage.clickDeniedOrderRequester();
        doPage3.waitForElement();
        
        AssertJUnit.assertTrue("Denied Order Page: Order persists in table after being deleted",doPage3.findOrder(orderNo3)==-1);
        
        System.out.println("Order removed. Editing top item...");
        
        String orderNo4 = doPage3.getOrderNo(1);
        
        Ecomm_ManualEntryPage mePage4 = doPage3.pressEdit(1);
        mePage4.waitForElement();
        
        System.out.println("Manual Entry Page reached. Logging out to check deleted item appears in Approval History...");
        
        Ecomm_OutstandingOrdersPage outOrds = mePage4.clickOutstandingOrders();
        outOrds.waitForElement();
        
        WBA_LoginPage liPage6 = outOrds.pressLogout();
        liPage6.waitForElement();

        Ecomm_MainPage mainPage5 = base3.setUp("","");
        mainPage5.waitForLoad();
        
        Ecomm_OrderApprovalHistoryPage appHist = mainPage5.clickOrderApprovalHistory();
        appHist.waitForElement();
        
        System.out.println("Order Approval History page reached. Checking item does not appear...");
        
        int row3 = appHist.findOrder(orderNo3);
        
        AssertJUnit.assertTrue("Order Approval History Page: Deleted declined order appears in table",row3==-1);
        
        System.out.println("Item removed. Disabling approval workflow...");
        
        WBA_LoginPage liPage5 = pendPage2.pressLogout();
        liPage5.waitForElement();
        
        Cce_Base base6 = new Cce_Base(driver);
        CCE_MainPage cceMainPage = base6.setUp("", "");
        cceMainPage.waitForLoad();
        
        Mst_CustomersPage custPage2 = cceMainPage.selectCustomers();
        custPage2.waitForElement();
        
        System.out.println("Customers page reached. Finding customer...");
        
        custPage.setCustomerName(DataItems.custDetails[0]);
        custPage.pressSearch();
        int row4 = custPage.findCustomer(DataItems.custDetails[0]);
        
        Mst_EditCustomerPage editPage2 = custPage.pressEdit(row4);
        editPage2.waitForElement();
        
        AssertJUnit.assertTrue("Edit Customer Page: Customer name differs in edit page from Customers Table",editPage2.getCustomerName().equals(DataItems.custDetails[0]));
        
        editPage2.unsetApprovalWorkflow();
        Mst_CustomersPage custPage3 = editPage2.clickSave();
        custPage3.waitForElement();
        
        System.out.println("Approval workflow disabled.");
        
    }
    
    @Test //Order Approver History Page :: Global Admin :: Filter checks, export function
    (groups = {"eComm", "QuickTest"})
    public void OAH1() throws Exception {
        WebDriver driver = getDriver();
        
        Ecomm_Base base = new Ecomm_Base(driver);
        Ecomm_MainPage mainPage = base.setUp("Order Approval History Page OAH1: Filter checks, export function","OA_WP_R_OAH_2 and 3");
        mainPage.waitForLoad();
        
        System.out.println("Navigating to Order Approval History Page...");
        
        Ecomm_OrderApprovalHistoryPage appHist = mainPage.clickOrderApprovalHistory();
        appHist.waitForElement();
        
        System.out.println("Page reached. Entering filter criteria...");
        
        appHist.setCustomerName(DataItems.subCustDetails[0]);
        appHist.setCustomerPO("DeniedOrderTest_");
        
        System.out.println("Criteria entered. Listing orders...");
        
        appHist.pressSearch();
        appHist.waitForElement();
        
        System.out.println("Orders listed. Checking filtration...");
        
        String locator1 = "#content > div.flexi-grid > table > tbody:nth-child(2) > tr:nth-child(";
        String locator2 = ") > td:nth-child(2)";
        
        //AssertJUnit.assertTrue("Order Approval History Page: Filtration not functioning as expected",appHist.checkFiltration(locator1, locator2, "DeniedOrderTest_", 1,3));
        //Check filtration method above does not work as each record can take up between 1 and 3 rows  
        
        System.out.println("Filtration functioning. Re-setting filter...");
        
        appHist.pressReset();
        appHist.waitForElement();
        
        AssertJUnit.assertTrue("Order Approval History Page: Filter not reset upon click",appHist.getCustomerPOField().getAttribute("value").equals(""));
        
        System.out.println("Filter reset. Re-entering criteria and listing orders...");
        
        appHist.setCustomerName(DataItems.subCustDetails[0]);
        appHist.setCustomerPO("DeniedOrderTest_12");
        appHist.pressSearch();
        
        System.out.println("Orders listed. Exporting...");
        
        Ecomm_ExportDownloadPage dlPage = appHist.pressExport();
        dlPage.waitForDownloadCompletion();
        
        System.out.println("Download page open. ");
        
    }
    
    
}
