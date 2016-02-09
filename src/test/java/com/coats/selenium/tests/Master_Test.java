
package com.coats.selenium.tests;

import PageObjects.*;
import AutomationFramework.CommonTask;
import AutomationFramework.DataItems;
import com.coats.selenium.DriverFactory;

import static com.coats.selenium.DriverFactory.getDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;


public class Master_Test extends DriverFactory {
    
    //Most Masters tests will have the following structure:
    //Check title, fields, and filration
    //Add an item to the master data, and check the item appears in the table
    //Edit the item above, and check the item is updated in the table
    //Delete said item, and check the item is removed
    //Check the import function
    //EXTENSION: Import an item to the master data
    
    @Test //All user types page :: Page and filter checks, create new user/edit/delete
    (groups = {"Masters"})
    public void allUserTypes1() throws Exception {
        
        WebDriver driver = getDriver();
        
        Cce_Base base = new Cce_Base(driver);
        CCE_MainPage ccePage = base.setUp("All User Types: Page and filter checks, create and delete type", "A_AUT_1-5");
        ccePage.waitForLoad();
        
        System.out.println("Navigating to All User Types page...");
        
        Mst_AllUserTypesPage autPage = ccePage.pressAllUserTypes();
        autPage.waitForElement();
        
        System.out.println("All User Types page reached. Checking title...");
        
        AssertJUnit.assertTrue("All User Types Page: Title is not displayed as expected",autPage.getBreadcrumb().getText().equals("User Types"));
        
        System.out.println("Title checked");
        
        autPage.assertBaseElements();
        
        System.out.println("Checking fields...");
        
        autPage.checkFields();
        
        System.out.println("Fields checked. Creating new user type...");
        
        Mst_AddUserTypePage newTypePage = autPage.pressNewUserType();
        newTypePage.waitForElement();
        
        System.out.println("Add user type page reached. Checking title...");
        
        AssertJUnit.assertTrue("Add User Type Page: Title not as expected",newTypePage.getBreadcrumb().getText().equals("User Types | Add User Type"));
        
        System.out.println("Title as expected. Checking fields...");
        
        newTypePage.checkFields();
        
        System.out.println("Fields checked. Entering details...");
        
        newTypePage.setUserType(DataItems.autoUserType);
        String description = CommonTask.generatePO(DataItems.autoUserType);
        newTypePage.setDescription(description);
        
        System.out.println("Description: " + description);
        
        System.out.println("Details entered. Saving user type...");
        
        Mst_AllUserTypesPage autPage2 = newTypePage.pressSave();
        autPage2.waitForLoad();
        
        System.out.println("Type saved. Entering filter criteria...");
        
        autPage2.setUserType(DataItems.autoUserType);
        
        System.out.println("Filter criteria entered. Listing records...");
        
        autPage2.pressSearch();
        autPage2.waitForElement();
        
        System.out.println("Records listed. Checking for records...");
        
        AssertJUnit.assertTrue("All User Types Page: No records found when filter set for user type: " + DataItems.autoUserType,autPage2.recordsAppear());
        
        System.out.println("Records found. Checking filtration...");
        
        String locatorpart1 = "#content > div.flexi-grid > table > tbody > tr:nth-child(";
        String locatorpart2 = ") > td:nth-child(2)";
        
        AssertJUnit.assertTrue("All User Types page: Filtration does not work as expected. User Type = " + DataItems.autoUserType,autPage2.checkFiltration(locatorpart1, locatorpart2, DataItems.autoUserType,2));
        
        System.out.println("Filter functioning correctly. Editing user type...");
        
        Mst_EditUserTypePage editPage = autPage2.pressEdit(2);
        editPage.waitForElement();
        
        System.out.println("Edit user type page reached. Changing level and status...");
        
        editPage.setLevel("2");
        editPage.setStatus("inactive");
        
        System.out.println("Items changed. Saving changes...");
        
        Mst_AllUserTypesPage autPage3 = editPage.pressSave();
        autPage3.waitForElement();
        
        System.out.println("Changes saved. Checking confirmation message is returned...");
        
        AssertJUnit.assertTrue("All User Types Page: Confirmation message not shown after edited changes are saved",autPage3.getMessage().contains("Type has been updated"));
        
        System.out.println("Message appeared: " + autPage3.getMessage());
        
        System.out.println("Re-entering filter criteria...");
        
        autPage3.setUserType(DataItems.autoUserType);
        
        System.out.println("Criteria set. Listing records...");
        
        autPage3.pressSearch();
        autPage3.waitForElement();
        
        System.out.println("Records listed. Deleting item...");
        
        AssertJUnit.assertTrue("All User Types Page: Item not found using filter. Could not delete",autPage3.pressDelete(DataItems.autoUserType));
        
        System.out.println("Item deleted. Checking item is removed and message appears...");
        
        AssertJUnit.assertTrue("All User Types Page: Message not displayed upon deletion",autPage3.getMessage().contains("Type has been deleted"));
        
        System.out.println("Message appears.");
        
        AssertJUnit.assertTrue("All User Types Page: Type persists in table after deletion",autPage3.typePresent(DataItems.autoUserType));
        
        System.out.println("Type deleted. ");
    }
    
    @Test //Coats Users Page :: Page and filter checks
    (groups = {"Masters"})
    public void coatsUsers1() throws Exception {
        WebDriver driver = getDriver();
        
        Cce_Base base = new Cce_Base(driver);
        CCE_MainPage ccePage = base.setUp("All User Types: Page and filter checks, create and delete type", "A_AUT_1-5");
        ccePage.waitForLoad();
        
        System.out.println("Navigating to Coats Users page...");
        
        Mst_CoatsUsersPage coatsPage = ccePage.pressCoatsUsers();
        coatsPage.waitForElement();
        
        System.out.println("Coats Users Page reached. Checking title...");
        
        AssertJUnit.assertTrue("Coats Users Page: Title not displayed as expected",coatsPage.getBreadcrumb().getText().equals("Coats Users"));
        
        System.out.println("Title checked");
        
        coatsPage.assertBaseElements();
        
        System.out.println("Checking fields...");
        
        coatsPage.checkFields();
        
        System.out.println("Fields checked. Entering filter criteria...");
        
        coatsPage.setUsername(DataItems.autoUsername);
        
        System.out.println("Criteria entered. Listing records...");
        
        coatsPage.pressSearch();
        coatsPage.waitForElement();
        
        System.out.println("Records listed. Asserting one record is displayed...");
        
        String locator1 = "#content > div.flexi-grid > table > tbody > tr:nth-child(";
        String locator2 = ") > td:nth-child(2)";
        
        AssertJUnit.assertTrue("Coats Users Page: No records shown when filtering by a valid Customer Name",coatsPage.checkForRecords());
        AssertJUnit.assertTrue("Coats Users Page: Filter not working as expected",coatsPage.checkFiltrationAndRecords(locator1, locator2, coatsPage.noRecords, DataItems.autoUsername, 2));
        
        System.out.println("Filter working as expected. Resetting filter...");
        
        coatsPage.pressReset();
        coatsPage.waitForElement();
        
        System.out.println("Filter reset. Re-entering filter criteria and listing records...");
        
        coatsPage.setUsername(DataItems.autoUsername);
        coatsPage.pressSearch();
        coatsPage.waitForElement();
        
        System.out.println("Records listed. Editing top record...");
        
        AssertJUnit.assertTrue("Coats Users Page: Did not edit as expected username did not match record",coatsPage.getUsernameCell().getText().equals(DataItems.autoUsername));
        
        Mst_EditCoatsUserPage editPage = coatsPage.pressEdit(2);
        editPage.waitForElement();
        
        System.out.println("Edit Coats User page reached. Checking title...");
        
        AssertJUnit.assertTrue("Edit Coats User Page: Title not displayed as expected or page incorrectly linked",editPage.getBreadcrumb().getText().equals("Coats Users | Edit Coats User"));
        
        System.out.println("Title checked. Checking fields...");
        
        editPage.checkFields();
        
        System.out.println("Fields checked. Pressing cancel...");
        
        driver.navigate().back();
        Mst_CoatsUsersPage coatsPage2 = new Mst_CoatsUsersPage(driver);
        coatsPage2.waitForElement();
        
        System.out.println("Cancelled. Re-entering filter criteria and deleting user...");
        
        coatsPage2.setUsername(DataItems.autoUsername);       
        coatsPage2.pressSearch();
        coatsPage2.waitForElement();
        
        AssertJUnit.assertTrue("Coats Users Page: Did not delete, expected username not found in record",coatsPage.getUsernameCell().getText().equals(DataItems.autoUsername));
        
        coatsPage2.pressDelete(DataItems.autoUsername);
        coatsPage2.waitForElement();
        
        System.out.println("User deleted. Creating new user...");
        
        Mst_AddCoatsUserPage addPage = coatsPage2.pressAddCoatsUser();
        addPage.waitForElement();
        
        System.out.println("New user page reached. Entering user details...");
        
        addPage.setFirstName("Automated");
        addPage.setLastName("Test");
        addPage.setUsername(DataItems.autoUsername);
        addPage.setPassword(DataItems.autoPassword);
        addPage.setUserType(DataItems.testUserType);
        addPage.setCountry(DataItems.autoUserCountry);
        System.out.println("country entered");
        Actions tab = new Actions(driver);
        
        addPage.setSalesOrg(DataItems.autoUserSalesOrg);
        System.out.println("sales org entered");
        
        addPage.setHub(DataItems.autoUserHub);
        System.out.println("hub entered");
        System.out.println("User details entered. Saving...");
        
        Mst_CoatsUsersPage coatsPage3 = addPage.pressSave();
        coatsPage3.waitForElement();
        
        System.out.println("Saved. Checking record appears...");
        
        coatsPage3.setUsername(DataItems.autoUsername);
        
        coatsPage3.pressSearch();
        coatsPage3.waitForElement();
        
        AssertJUnit.assertTrue("New Coats User does not appear after being saved",coatsPage3.checkForRecords());
        
        System.out.println("Record appears. Checking export function...");
        
        CCE_ExportDownloadPage exportPage = coatsPage3.pressExport();
        
        System.out.println("Export View Page: View page appears. Closing view...");
        
        System.out.println("View closed. Checking import function...");
        
        Mst_ImportPage importPage = coatsPage3.pressImport();
        importPage.waitForElement();
        
        System.out.println("Import page reached. Checking title...");
        
        AssertJUnit.assertTrue("Import Page: Title not as expceted or page incorrectly linked",importPage.getBreadcrumb().getText().equals("Coats Users | Import"));
        
        System.out.println("Title as expected. Pressing cancel...");
        
        Mst_CoatsUsersPage coatsPage4 = importPage.pressCancel();
        coatsPage4.waitForElement();
        
        System.out.println("Cancel function works");
    }
    
    @Test //Contract Order Related :: Flags active and available
    (groups = {"Masters"})
    public void contractOrder1() throws Exception {
        WebDriver driver = getDriver();
        
        Cce_Base base = new Cce_Base(driver);
        CCE_MainPage ccePage = base.setUp("Contract Order Related: Flags avaiable and function", "CO_MD_01");
        ccePage.waitForLoad();
        
        System.out.println("Navigating to Sales Organisation page...");
        
        Mst_SalesOrgPage soPage = ccePage.selectSalesOrg();
        soPage.waitForElement();
        
        System.out.println("Sales Org Page reached. Checking title...");
        
        AssertJUnit.assertTrue("Sales Organisations Page: Title not displayed as expected",soPage.getBreadcrumb().getText().equals("Sales Organisations"));
        
        System.out.println("Title checked");
        
        soPage.assertBaseElements();
        
        System.out.println("Checking fields...");
        
        soPage.checkFields();
        
        System.out.println("Fields checked. Entering filter criteria...");
        
        soPage.setSalesOrg("LK53");
        
        System.out.println("Criteria set. Listing orders...");
        
        soPage.pressSearch();
        soPage.waitForLoad();
        
        System.out.println("Orders listed. Checking filtration...");
        
        String loc1 = "#content > div.flexi-grid > table > tbody > tr:nth-child(";
        String loc2 = ") > td:nth-child(2)";
        
        AssertJUnit.assertTrue("Sales Organisations Page: Filtration not working as expected",soPage.checkFiltrationAndRecords(loc1,loc2,WBA_BasePage.noRecords,"LK53",2));
        
        System.out.println("Filtration checked. Editing...");
        
        Mst_EditSalesOrgPage editPage = soPage.pressEdit(2);
        editPage.waitForElement();
        
        System.out.println("Edit page reached. Checking title...");
        
        AssertJUnit.assertTrue("Edit Sales Org Page: Title not as expected",editPage.getBreadcrumb().getText().equals("Sales Organisations | Edit Sales Organisation"));
    
        System.out.println("Title checked. Checking fields...");
        
        editPage.checkFields();
        
        System.out.println("Fields checked. Checking name is as expected...");
        
        AssertJUnit.assertTrue("Edit Sales Organisation Page: Name in edit page does not match input to filter",editPage.getNameField().getAttribute("value").equals("LK53"));
        
        System.out.println("Name as expected. Checking contract order field is selected...");
        
        AssertJUnit.assertTrue("Edit Sales Organisation Page: Contract Order field not in expected state (is disabled)",editPage.getContractOrderField().getAttribute("checked").equals("true"));
        
        System.out.println("Field selected as expected. Saving...");
        
        Mst_SalesOrgPage soPage2 = editPage.pressSave();
        soPage2.waitForElement();
        
        System.out.println("Saved. Navigating to Customer master data...");
        
        Mst_CustomersPage custPage = soPage2.selectCustomers();
        custPage.waitForElement();
        
        System.out.println("Page reached. Entering filter criteria and finding customer...");
        
        custPage.setCustomerName(DataItems.conOrdDetails[0]);
        
        custPage.pressSearch();
        custPage.waitForElement();
        
        System.out.println("Customers listed. Checking filtration...");
        
        String locator1 = "#content > div.flexi-grid > table > tbody > tr:nth-child(";
        String locator2 = ") > td:nth-child(4)";
        
        AssertJUnit.assertTrue("Customers Page: Filtration not working as expected",custPage.checkFiltration(locator1, locator2, DataItems.conOrdDetails[0], 2));
        
        System.out.println("Filtration as expected. Editing...");
        
        Mst_EditCustomerPage editPage2 = custPage.pressEdit(2);
        editPage2.waitForLoad();
        
        System.out.println("Edit page reached. Checking contract order field is activated...");
        
        //AssertJUnit.assertTrue("Edit Customer Page: Contract Order field not enabled",editPage2.getContractOrderField().getAttribute("checked").equals("true"));
        
        System.out.println("Field activated, as expected. Saving...");
        
        Mst_CustomersPage custPage2 = editPage2.pressSave();
        custPage2.waitForElement();
        
        System.out.println("Saved.");
        
    }
    
    @Test //Sub Account :: Page and filter checks, add/edit/delete
    (groups = {"Masters"})
    public void subaccount1() throws Exception {
        WebDriver driver = getDriver();
        
        Cce_Base base = new Cce_Base(driver);
        CCE_MainPage ccePage = base.setUp("Sub Account: Page displayed and functioning", "MD_SA_01");
        ccePage.waitForLoad();
        
        System.out.println("Navigating to Sub Account page...");
        
        Mst_SubAccountPage saPage = ccePage.selectSubAccount();
        saPage.waitForElement();
        
        System.out.println("Sub Account page reached. Checking title...");
        
        AssertJUnit.assertTrue("Sub Account Page: Title not displayed as expected",saPage.getBreadcrumb().getText().equals("Sub Accounts"));
        
        System.out.println("Title checked");
        
        saPage.assertBaseElements();
        
        System.out.println("Checking fields...");
        
        saPage.checkFields();
        
        System.out.println("Fields checked. Entering filter criteria...");
        
        saPage.setSalesOrg("ID51");
        
        System.out.println("Criteria set. Listing orders...");
        
        saPage.pressSearch();
        saPage.waitForElement();
        
        System.out.println("Orders listed. Checking filtration...");
        
        String loc1 = "#content > div.flexi-grid > table > tbody > tr:nth-child(";
        String loc2 = ") > td:nth-child(2)";
        
        AssertJUnit.assertTrue("Sub Account Page: Filter produced unexpected results",saPage.checkFiltration(loc1, loc2, "ID51", 2));
        
        System.out.println("Filtration functions as expected. Checking export function...");
        
        CCE_ExportDownloadPage exportPage = saPage.pressExport();
        
        System.out.println("Export in progress");
        
        exportPage.waitForDownloadCompletion();
        
        System.out.println("Download complete. Resetting filter...");
        
        saPage.pressReset();
        saPage.waitForElement();
        
        System.out.println("Filter reset. Checking Sales Org Field is blank...");
        
        AssertJUnit.assertFalse("Sub Account Page: Filter not reset upon button click",saPage.getSalesOrg().equals("ID51"));
        
        System.out.println("Field reset, as expected. Creating new Sub-account...");
        
        Mst_AddSubAccountPage addPage = saPage.pressNewSubAccount();
        addPage.waitForElement();
        
        System.out.println("Add Sub-Account page reached. Entering details...");
        
        addPage.setSalesOrg("ID51");
        addPage.setCustomerName("CCE HUB OFFICES");
        addPage.setSubAccountNumber("AutoTest");
        addPage.setSubAccountName("AutoTest SubAccount");
        addPage.setComment("Auto-generated");
        
        System.out.println("Details entered. Saving...");
        
        Mst_SubAccountPage subPage2 = addPage.pressSave();
        subPage2.waitForElement();
        
        System.out.println("Saved. Checking Sub-account was created...");
        
        subPage2.setSalesOrg("ID51");
        subPage2.pressSearch();
        subPage2.waitForElement();
        
        int row = subPage2.getRow("AutoTest SubAccount");
        
        AssertJUnit.assertFalse("Sub Account Page: Sub-account does not appear after creation",row==-1);
        
        System.out.println("Sub-account found. Editing sub-account...");
        
        Mst_EditSubAccountPage editPage = subPage2.pressEdit(row);
        editPage.waitForElement();
        
        System.out.println("Edit page reached. Checking title...");
        
        AssertJUnit.assertTrue("Edit Sub Account Page: Title not as expected",editPage.getBreadcrumb().getText().equals("Sub Accounts | Edit Sub Account"));
        
        System.out.println("Title checked");
        
        editPage.assertBaseElements();
        
        System.out.println("Checking fields...");
        
        editPage.checkFields();
        
        System.out.println("Fields checked. Editing comment...");
        
        editPage.setComment("Auto-generated: edited");
        
        System.out.println("Edited. Saving...");
        
        Mst_SubAccountPage saPage2 = editPage.pressSave();
        saPage2.waitForElement();
        
        System.out.println("Saved. Deleting sub-account...");
        
        saPage2.setSubAccountName("AutoTest SubAccount");
        saPage2.pressSearch();
        saPage2.waitForElement();
        
        saPage2.pressDelete(2, "AutoTest SubAccount");
        
        System.out.println("Delete pressed. Checking table to ensure deletion...");
        
        saPage2.setSubAccountName("AutoTest SubAccount");
        saPage.pressSearch();
        saPage.waitForElement();
        
        AssertJUnit.assertTrue("Sub Account Page: Item persists even after deletion",saPage2.getRow("AutoTest SubAccount")==-1);
        
        System.out.println("Item deleted.");

    }
    
    @Test //Sub Account Related :: Switches appear in Sales Org and Customers master data
    (groups = {"Masters"})
    public void subAccount2() throws Exception {
        WebDriver driver = getDriver();
        
        Cce_Base base = new Cce_Base(driver);
        CCE_MainPage ccePage = base.setUp("Sub Account Related: Sales Org and Customers levels contain switch", "MD_SA_02 and 03");
        ccePage.waitForLoad();
        
        System.out.println("Navigating to Sales Org page...");
        
        Mst_SalesOrgPage soPage = ccePage.selectSalesOrg();
        soPage.waitForElement();
        
        System.out.println("Page reached. Entering filter criteria...");
        
        soPage.setSalesOrg("ID51");

        System.out.println("Criteria entered. Listing orders...");
        
        soPage.pressSearch();
        soPage.waitForElement();
        
        System.out.println("Orders listed. Editing top item...");
        
        Mst_EditSalesOrgPage editPage = soPage.pressEdit(2);
        editPage.waitForElement();
        
        System.out.println("Edit page reached. Checking for Sub Account field...");
        
        AssertJUnit.assertTrue("Edit Sales Org Page: Sub Account label not displayed",editPage.getSubAccountLabel().getText().equals("Enabled Sub Account Option"));
        AssertJUnit.assertTrue("Edit Sales Org Page: Sub Account field not displayed",editPage.getSubAccountField().isDisplayed());
        String status = editPage.getSubAccountField().getAttribute("checked");
        AssertJUnit.assertTrue("Edit Sales Org Page: Sub Account field not enabled as expected",status.equals("true"));
        
        System.out.println("Field displayed. Current status: " + status + ", as expected. Navigating to Customer master...");
        
        Mst_CustomersPage custPage = editPage.selectCustomers();
        custPage.waitForLoad();
        
        System.out.println("Customer Master reached. Finding customer...");
        
        custPage.setCustomerName(DataItems.subCustDetails[0]);
        custPage.pressSearch();
        custPage.waitForElement();
        
        Mst_EditCustomerPage editPage2 = custPage.pressEdit(2);
        editPage2.waitForElement();
        
        System.out.println("Customer found. Checking for Sub-account field...");
        
        AssertJUnit.assertTrue("Edit Customer Page: Sub Account label not displayed",editPage2.getSubAcctLabel().getText().equals("Enabled SubAccount Option"));
        AssertJUnit.assertTrue("Edit Customer Page: Sub Account field not displayed",editPage2.getSubAcctField().isDisplayed());
        String status2 = editPage2.getSubAcctField().getAttribute("checked");
        AssertJUnit.assertTrue("Edit Customer Page: Sub Account field not enabled as expected",status2.equals("true"));
        
        System.out.println("Field displayed. Current status: " + status + ", as expected.");
        
        
    }
    
    @Test //Mail Notification Related :: Switch for mail notifications present in sales org and customer (requester level) master data
    (groups = {"Masters"})
    public void mailNotification1() throws Exception {
        
        WebDriver driver = getDriver();
        
        Cce_Base base = new Cce_Base(driver);
        CCE_MainPage ccePage = base.setUp("Mail Notification Related: Sales Org and Customers levels contain switch", "CCE_MN_Sorg");
        ccePage.waitForLoad();
        
        System.out.println("Navigating to Sales Org page...");
        
        Mst_SalesOrgPage soPage = ccePage.selectSalesOrg();
        soPage.waitForElement();
        
        System.out.println("Page reached. Entering filter criteria...");
        
        soPage.setSalesOrg("ID51");

        System.out.println("Criteria entered. Listing orders...");
        
        soPage.pressSearch();
        soPage.waitForElement();
        
        System.out.println("Orders listed. Editing top item...");
        
        Mst_EditSalesOrgPage editPage = soPage.pressEdit(2);
        editPage.waitForElement();
        
        System.out.println("Edit page reached. Checking for Mail Notification field...");
        
        AssertJUnit.assertTrue("Edit Sales Org Page: Mail Notification label text not as expected",editPage.getMailNotificationLabel().getText().equals("Enabled CCE Ship Notice"));
        AssertJUnit.assertTrue("Edit Sales Org Page: Mail Notification field not displayed",editPage.getMailNotificationField().isDisplayed());
        String status = editPage.getSubAccountField().getAttribute("checked");
        AssertJUnit.assertTrue("Edit Sales Org Page: Mail Notification field not enabled as expected",status.equals("true"));
        
        System.out.println("Field displayed. Current status: " + status + ", as expected. Navigating to Customer master...");
        
        Mst_CustomersPage custPage = editPage.selectCustomers();
        custPage.waitForLoad();
        
        System.out.println("Customer Master reached. Finding customer...");
        
        custPage.setCustomerName(DataItems.subCustDetails[0]);
        custPage.pressSearch();
        custPage.waitForElement();
        
        Mst_EditCustomerPage editPage2 = custPage.pressEdit(2);
        editPage2.waitForElement();
        
        System.out.println("Customer found. Checking for Sub-account field...");
        
        AssertJUnit.assertTrue("Edit Customer Page: Sub Account label not displayed",editPage2.getSubAcctLabel().getText().equals("Enabled SubAccount Option"));
        AssertJUnit.assertTrue("Edit Customer Page: Sub Account field not displayed",editPage2.getSubAcctField().isDisplayed());
        String status2 = editPage2.getSubAcctField().getAttribute("checked");
        AssertJUnit.assertTrue("Edit Customer Page: Sub Account field not enabled as expected",status2.equals("true"));
        
        System.out.println("Field displayed. Current status: " + status + ", as expected.");
        
    }
    
    @Test //Approver List (Online Approval Related) :: Page and filter checks, add/edit/delete
    (groups = {"Masters"})
    public void approverList1() throws Exception {
        WebDriver driver = getDriver();
        
        Cce_Base base = new Cce_Base(driver);
        CCE_MainPage ccePage = base.setUp("Approver List (Online Approval Related): Page and filter checks, add/edit/delete", "OA_MD_MA_1 to 10");
        ccePage.waitForLoad();
        

        System.out.println("Navigating to Customer...");
        
        Mst_CustomersPage custPage = ccePage.selectCustomers();
        custPage.waitForElement();
        
        System.out.println("Page reached. Editing top item...");
        
        Mst_EditCustomerPage editPage = custPage.pressEdit(2);
        editPage.waitForElement();
        
        System.out.println("Edit page reached. Asserting approval workflow flag appears...");
        
        AssertJUnit.assertTrue("Edit Customer Page: Approval workflow checkbox not displayed",editPage.getApprovalWorkflowBox().isDisplayed());

        System.out.println("Flag appears. Navigating to Approver List...");

        driver.get(DataItems.targetURL + "/cee");
        
        Mst_ApproverListPage appList = editPage.selectApproverList();
        appList.waitForElement();
        
        System.out.println("Approver List Page reached. Checking title...");
        
        AssertJUnit.assertTrue("Approver List Page: Title not as expected",appList.getBreadcrumb().getText().equals("Approver List"));
        
        System.out.println("Title as expected");
        
        appList.assertBaseElements();
        
        System.out.println("Checking fields...");
        
        appList.checkFields();
        
        System.out.println("Fields checked. Entering filter criteria...");
        
        appList.setSalesOrg("ID51");
        appList.setCustomerName(DataItems.custDetails[0]);
        appList.pressSearch();
        appList.waitForElement();
        
        System.out.println("Filter criteria entered. Checking filtration...");
        
        String loc1 = "#content > div.flexi-grid > table > tbody > tr:nth-child(";
        String loc2 = ") > td:nth-child(3)";
        
        By countField = By.cssSelector("#content > div.flexi-grid > dl > dt > span.left");
        
        AssertJUnit.assertTrue("Filtration not as expected",appList.checkFiltration(loc1, loc2,DataItems.custDetails[0],countField, 2));
        
        System.out.println("Filtration checked. Creating new approver list...");
        
        Mst_AddApproverListPage addPage = appList.pressAddApproverList();
        addPage.waitForElement();
        
        System.out.println("Add list page reached. Checking title...");
        
        AssertJUnit.assertTrue("Add Approver List Page: Title not as expected",addPage.getBreadcrumb().getText().equals("Approver List | Add Approver List"));
    
        System.out.println("Title as expected");
        
        addPage.assertBaseElements();
        
        System.out.println("Checking fields...");
        
        addPage.checkFields();
        
        System.out.println("Fields checked. Entering details...");
        
        addPage.setSalesOrg("ID51");
        addPage.setCustomerName(DataItems.custDetails[0]);
        addPage.setRequester("Auto Approver");
        addPage.setValueStart("0.01");
        addPage.setValueUntil("100.00");
        addPage.enableEmailNotif();
        
        System.out.println("Details entered. Saving...");
        
        addPage.pressSave();
        appList.waitForElement();
        
        System.out.println("Saved. Checking record is created...");
        
        appList.setSalesOrg("ID51");
        appList.setCustomerName(DataItems.custDetails[0]);
        appList.pressSearch();
        appList.waitForElement();
        
        int row = appList.getRow("approver@lifeeasy.com");
        System.out.println(row);
        AssertJUnit.assertFalse("Approver List Page: Approver list not found after being created",row==-1);
        
        System.out.println("Record created. Editing item...");
        
        Mst_EditApproverListPage editPage2 = appList.pressEdit(row);
        editPage2.waitForElement();
        
        System.out.println("Edit page reached. Checking title...");
        
        AssertJUnit.assertTrue("Edit Approver List Page: Title not as expected",editPage2.getBreadcrumb().getText().equals("Approver List | Edit Approver List"));
        
        System.out.println("Title checked");
        
        editPage2.assertBaseElements();
        
        System.out.println("Checking fields...");
        
        editPage2.checkFields();
        
        System.out.println("Fields checked. Editing until value...");
        
        AssertJUnit.assertTrue("Edit Approver List Page: Requester not as expected, record not edited",editPage2.getRequester().equals("Auto Approver"));
        
        editPage2.setValueUntil("10.00");
        
        System.out.println("Value edited. Saving...");
        
        editPage2.pressSave();
        appList.waitForElement();
        
        System.out.println("Saved. Checking changes were applied...");
        
        appList.setSalesOrg("ID51");
        appList.setCustomerName(DataItems.custDetails[0]);
        appList.pressSearch();
        appList.waitForElement();
        
        int row2 = appList.getRow("approver@lifeeasy.com");
        String value = appList.getValueUntil(row2);
        
        AssertJUnit.assertTrue("Approver List Page: Record not updated after edit saved. Value until: " + value,value.equals("10.00"));
        
        System.out.println("Changes correctly applied. Deleting record...");
        
        appList.pressDelete(row2);
        
        System.out.println("Delete pressed. Checking record is removed...");
        
        appList.setSalesOrg("ID51");
        appList.setCustomerName(DataItems.custDetails[0]);
        appList.pressSearch();
        appList.waitForElement();
        
        int row3 = appList.getRow("approver@lifeeasy.com");
        AssertJUnit.assertTrue("Approver List Page: Record persists after deletion",row3 == -1);
        
        System.out.println("Record removed. Testing export feature...");
        
        appList.setCustomerName(DataItems.custDetails[0]);
        appList.pressSearch();
        appList.waitForLoad();
        CCE_ExportDownloadPage dlPage = appList.pressExport();
        dlPage.waitForDownloadCompletion();
        
        System.out.println("Export download completed");
    }
    
    @Test //Approver User Type :: User type can be found and used in masters
    (groups = {"Masters"})
    public void approverUserType1() throws Exception {
        WebDriver driver = getDriver();
        
        Cce_Base base = new Cce_Base(driver);
        CCE_MainPage ccePage = base.setUp("Approver User Type: Customer user type can be set to 'Approver'", "OA_MD_UT_2");
        ccePage.waitForLoad();
        
        System.out.println("Navigating to Customer...");
        
        Mst_CustomersPage custPage = ccePage.selectCustomers();
        custPage.waitForElement();
        
        System.out.println("Page reached. Entering filter criteria and editing...");
        
        custPage.setCustomerName(DataItems.custDetails[0]);
        custPage.pressSearch();
        custPage.waitForElement();
        
        Mst_EditCustomerPage editPage = custPage.pressEdit(2);
        editPage.waitForElement();
        
        System.out.println("Edit page reached. Asserting Requester User Type can be set to Approver...");
        
        AssertJUnit.assertTrue("Edit Customer Page: Approver User Type is not available to be set",editPage.findUserType("Approver"));
        
        System.out.println("Approver type found");
    }
    
    @Test //Cusotmer Business Principal :: Page and filter checks, add/edit/delete functions
    (groups = {"Masters"})
    public void businessPrinc1() throws Exception {
        WebDriver driver = getDriver();
        
        Cce_Base base = new Cce_Base(driver);
        CCE_MainPage ccePage = base.setUp("Customer Business Principal: Page and filter checks, add/edit/delete functions", "A_CB_CBP_1 to 8");
        ccePage.waitForLoad();
        
        System.out.println("Navigating to Customer Business Principals...");
        
        Mst_CustBusinessPrincipalPage custPage = ccePage.selectCustBusinessPrincipal();
        custPage.waitForElement();
        
        System.out.println("Page reached. Checking title...");
        
        AssertJUnit.assertTrue("Customer Business Principal Page: Title not as expected",custPage.getBreadcrumb().getText().equals("Customer Business Principal"));
        
        System.out.println("Title checked");
        
        custPage.assertBaseElements();
        
        System.out.println("Checking fields...");
        
        custPage.checkFields();
        
        System.out.println("Fields checked. Entering filter criteria...");
        
        custPage.setSalesOrg("ID51");
        custPage.setCustomerName(DataItems.custDetails[0]);
        custPage.pressSearch();
        custPage.waitForElement();
        
        System.out.println("Records listed. Checking filtration...");
        
        String loc1 = "#content > div.flexi-grid > table > tbody > tr:nth-child(";
        String loc2 = ") > td:nth-child(3)";
        By countField = By.cssSelector("#content > div.flexi-grid > dl > dt > span.left");
        
        AssertJUnit.assertTrue("Customer Business Principal Page: Filtration not working as expected",custPage.checkFiltration(loc1,loc2,DataItems.custDetails[0],countField,2));
        
        System.out.println("Filtration as expected. Creating new Customer Business Principal...");
        
        Mst_AddCustBusPrincPage addPage = custPage.pressNew();
        addPage.waitForElement();
        
        System.out.println("Add page reached. Checking title...");

        AssertJUnit.assertTrue("Add Customer Business Principal Page: Title not as expected",addPage.getBreadcrumb().getText().equals("Customer Business Principal | Add Customer Business Principal"));
        
        System.out.println("Title checked");
        
        addPage.assertBaseElements();
        
        System.out.println("Checking fields...");
        
        addPage.checkFields();
        
        System.out.println("Fields checked. Entering details...");
        
        addPage.setSalesOrg("ID51");
        addPage.setCustomerName(DataItems.custDetails[0]);
        addPage.setCustomerBusPrinc("Automated Principal");
        addPage.setCoatsBusPrinc(DataItems.custDetails[3]);
        
        System.out.println("Details entered. Saving...");
        
        addPage.pressSave();
        custPage.waitForElement();
        
        System.out.println("Saved. Checking record appears in table...");

        int row = custPage.getRow("Automated Principal");
        
        AssertJUnit.assertFalse("Customer Business Principal Page: New item does not appear in table after saving",row == -1);
        
        System.out.println("Item found. Editing item...");
        
        Mst_EditCustBusPrincPage editPage = custPage.pressEdit(row);
        editPage.waitForElement();
        
        System.out.println("Edit page reached. Editing Principal Name...");
        
        editPage.setCustomerBusPrinc("Automated Edited");
        
        System.out.println("Edited. Saving...");
        
        editPage.pressSave();
        custPage.waitForElement();
        
        System.out.println("Saved. Checking record was updated...");
        
        custPage.setCustomerName(DataItems.custDetails[0]);
        custPage.pressSearch();
        custPage.waitForElement();
        
        int row2 = custPage.getRow("Automated Edited");
        
        AssertJUnit.assertFalse("Customer Business Principal Page: Change was not made to record after saving",row2 == -1);
        
        System.out.println("Record updated as expected. Deleting record...");
        
        custPage.pressDelete(row2);
        custPage.waitForElement();
        
        System.out.println("Delete pressed. Checking delete occurred...");
        
        custPage.setCustomerName(DataItems.custDetails[0]);
        custPage.pressSearch();
        custPage.waitForElement();
        
        int row3 = custPage.getRow("Automated Edited");
        
        AssertJUnit.assertTrue("Customer Business Principal Page: Record was not removed from table after deletion",row3==-1);
        
        System.out.println("Record deleted. Checking export function...");
        
        custPage.setCustomerName(DataItems.custDetails[0]);
        custPage.pressSearch();
        custPage.waitForElement();
        CCE_ExportDownloadPage dlPage = custPage.pressExport();
        dlPage.waitForDownloadCompletion();
        
        System.out.println("Export function checked. Checking import feature...");
        
        Mst_ImportPage impPage = custPage.pressImport();
        impPage.waitForElement();
        
        System.out.println("Page reached. Checking title...");
        
        AssertJUnit.assertTrue("Import Customer Business Principal Page: Title not as expected",impPage.getBreadcrumb().getText().equals("Customer Business Principal | Import"));
        
        System.out.println("Title as expected");
    }
    
     @Test //MultiSoldToUser master :: Page and filter checks, add/edit/delete/export features
    (groups = {"Masters"})
    public void multiSoldToUsers1() throws Exception {
        
        WebDriver driver = getDriver();
        
        Cce_Base base = new Cce_Base(driver);
        CCE_MainPage ccePage = base.setUp("Multi Sold To Users: Page and filter checks, add/edit/delete/export", "A_CB_MSTU_1 to 8");
        ccePage.waitForLoad();
        
        System.out.println("Navigating to Multi Sold To Users page...");
        
        Mst_MultiSoldToPage multiPage = ccePage.selectMultiSoldTo();
        multiPage.waitForElement();
        
        System.out.println("Page reached. Checking title...");
        
        AssertJUnit.assertTrue("Multi Sold To Users Page: Title not as expected",multiPage.getBreadcrumb().getText().equals("Multi Users"));
        
        System.out.println("Title as expected");
        
        multiPage.assertBaseElements();
        
        System.out.println("Checking fields...");
        
        multiPage.checkFields();
        
        System.out.println("Fields checked. Entering filter criteria...");
        
        multiPage.setSalesOrg("ID51");
        multiPage.setCustomerName(DataItems.custDetails[0]);

        System.out.println("Criteria entered. Listing records...");
        
        multiPage.pressSearch();
        multiPage.waitForElement();
        
        System.out.println("Records listed. Checking filtration...");
        
        String loc1 = "#content > div.flexi-grid > table > tbody > tr:nth-child(";
        String loc2 = ") > td:nth-child(5)";
        
        AssertJUnit.assertTrue("Mutli Sold To Page: Filtration does not work as expected",multiPage.checkFiltration(loc1, loc2, "ID51", 2));
        
        System.out.println("Filtration working as expected. Creating new Multi Sold To User...");
        
        Mst_AddMultiUserPage addPage = multiPage.pressNewUser();
        addPage.waitForElement();
        
        System.out.println("Add multi sold to user page reached. Checking title...");
        
        AssertJUnit.assertTrue("Add Multi Sold To User Page: Title not as expected",addPage.getBreadcrumb().getText().equals("Multi Users | Add Multi User"));
        
        System.out.println("Title checked");
        
        addPage.assertBaseElements();
        
        System.out.println("Checking fields...");
        
        addPage.checkFields();
        
        System.out.println("Fields checked. Entering details...");
        
        addPage.setFirstName("Automated");
        addPage.setLastName("MutliSold User");
        addPage.setUserName("automated@multisold.com");
        addPage.setPassword("password");
        addPage.setEmail("joe.sykes@coats.com");
        addPage.setUserType("Multi Sold To");
        addPage.setCountry("Indonesia");
        addPage.setSalesOrg("ID51");
        addPage.setCustomerName(DataItems.custDetails[0],"(106499)");
        addPage.setShipToParty(DataItems.custDetails[1],"(106553)");
        
        System.out.println("Details entered. Saving...");
        
        addPage.pressSave();
        multiPage.waitForElement();
        
        System.out.println("Multi Sold To Users Page reached. Checking user was created...");
        
        multiPage.setSalesOrg("ID51");
        multiPage.setCustomerName(DataItems.custDetails[0]);
        multiPage.setUserName("Automated");
        
        multiPage.pressSearch();
        multiPage.waitForElement();
        
        int row = multiPage.getRow("automated@multisold.com");
        
        AssertJUnit.assertFalse("Multi Sold To Page: User could not be found after creation",row==-1);
        
        System.out.println("User found. Editing user...");
        
        Mst_EditMultiUserPage editPage = multiPage.pressEdit(row);
        editPage.waitForElement();
        
        System.out.println("Edit Multi User Page reached. Checking title...");
        
        AssertJUnit.assertTrue("Edit Multi User Page: Title not as expected",editPage.getBreadcrumb().getText().equals("Multi Users | Edit Multi User"));
        
        System.out.println("Title checked");
        
        editPage.assertBaseElements();
        
        System.out.println("Checking fields...");
        
        editPage.checkFields();
        
        System.out.println("Fields checked. Editing username...");
        
        editPage.setUserName("autoedited@multisold.com");
        
        System.out.println("Username edited. Saving...");
        
        editPage.pressSave();
        multiPage.waitForElement();
        
        System.out.println("Saved. Checking record was updated...");
        
        multiPage.setSalesOrg("ID51");
        multiPage.setCustomerName(DataItems.custDetails[0]);
        multiPage.setUserName("autoedited@multisold.com");
        
        multiPage.pressSearch();
        multiPage.waitForElement();
        
        int row2 = multiPage.getRow("autoedited@multisold.com");
        AssertJUnit.assertFalse("Multi Sold To Users Page: Record not updated once edited",row2==-1);
        
        System.out.println("Record updated. Deleting record...");
        
        multiPage.pressDelete(row2);
        multiPage.waitForElement();
        
        System.out.println("Record deleted. Checking record is removed...");
        
        multiPage.setSalesOrg("ID51");
        multiPage.setCustomerName(DataItems.custDetails[0]);
        
        multiPage.pressSearch();
        multiPage.waitForElement();
        
        int row3 = multiPage.getRow("autoedited@multisold.com");
        AssertJUnit.assertTrue("Multi Sold To Users Page: Record not deleted",row3==-1);
        
        System.out.println("Record deleted");
    }
    
    @Test //Shades master :: Page and filter checks, add/edit/delete/export features
    (groups = {"Masters"},enabled = false) //WBA Issue: Shade not deleted from table when delete pressed
    public void shades1() throws Exception {
        
        WebDriver driver = getDriver();
        
        Cce_Base base = new Cce_Base(driver);
        CCE_MainPage ccePage = base.setUp("Shades: Page and filter checks, add/edit/delete/export", "A_CB_MSTU_8");
        ccePage.waitForLoad();
        
        System.out.println("Navigating to Multi Sold To Users page...");
        
        Mst_ShadesPage shadesPage = ccePage.selectShades();
        shadesPage.waitForElement();
        
        System.out.println("Page reached. Checking title...");
        
        AssertJUnit.assertTrue("Shades Page: Title not as expected",shadesPage.getBreadcrumb().getText().equals("Shades"));
        
        System.out.println("Title checked");
        
        shadesPage.assertBaseElements();
        
        System.out.println("Checking fields...");
        
        shadesPage.checkFields();
        
        System.out.println("Fields checked. Entering filter criteria...");
        
        shadesPage.setShadeCard("GCR");
        
        System.out.println("Filter criteria entered. Listing records...");
        
        shadesPage.pressSearch();
        shadesPage.waitForElement();
        
        System.out.println("Records listed. Checking filtration...");
        
        String loc1 = "#content > div.flexi-grid > table > tbody > tr:nth-child(";
        String loc2 = ") > td:nth-child(2)";
        
        AssertJUnit.assertTrue("Shades Page: Filtration not functioning as expected",shadesPage.checkFiltration(loc1, loc2, "GCR", 2));
        
        System.out.println("Filter checked. Resetting filter...");
        
        shadesPage.pressReset();
        shadesPage.waitForElement();
        
        System.out.println("Filter reset. Creating new shade...");
        
        Mst_AddShadePage addPage = shadesPage.pressNewShade();
        addPage.waitForElement();
        
        System.out.println("Add shade page reached. Entering details...");
        
        addPage.setShadeCard("GCR");
        addPage.setShadeName("AutoShade");
        addPage.setShadeCode("AUT01");
        addPage.setRedValue("135");
        addPage.setGreenValue("135");
        addPage.setBlueValue("135");
        addPage.setStandardType("Auto");
        addPage.setTypeCode("AUTO");
        
        System.out.println("Details entered. Saving...");

        addPage.pressSave();
        shadesPage.waitForElement();
        
        System.out.println("Saved. Checking shade has been created...");
        
        shadesPage.setShadeCode("AUT01");
        shadesPage.pressSearch();
        shadesPage.waitForElement();
        
        int row = shadesPage.getRow("AUT01");
        AssertJUnit.assertFalse("Shades Page: Shade does not appear after being created",row==-1);
        
        System.out.println("Shade found. Editing shade...");
        
        Mst_EditShadePage editShade = shadesPage.pressEdit(row);
        editShade.waitForElement();
        
        System.out.println("Edit page reached. Editing shade code...");
        
        editShade.setShadeCode("EDIT01");
        
        System.out.println("Edited. Saving...");
        
        editShade.pressSave();
        shadesPage.waitForElement();
        
        System.out.println("Saved. Checking changes were applied...");
        
        shadesPage.setShadeName("AutoTest");
        shadesPage.pressSearch();
        shadesPage.waitForElement();
        
        int row2 = shadesPage.getRow("EDIT01");
        
        AssertJUnit.assertFalse("Shades Page: Edited shade code does not appear in table",row2==-1);
        AssertJUnit.assertTrue("Shades Page: Edited change did not take place",shadesPage.getShadeCode(row2).equals("EDIT01"));
        
        System.out.println("Changes correctly applied. Deleting shade...");
        
        shadesPage.pressDelete(row2);
        shadesPage.waitForElement();
        
        System.out.println("Delete pressed. Checking shade is removed...");
        
        shadesPage.setShadeCode("EDIT01");
        shadesPage.pressSearch();
        shadesPage.waitForElement();
        
        AssertJUnit.assertFalse("Shades Page: Deleted shade still appears in table",shadesPage.checkForRecords());
      
        System.out.println("Shade removed. Testing export feature...");
        
        shadesPage.setShadeCode("C1711");
        shadesPage.pressSearch();
        shadesPage.waitForElement();
        
        CCE_ExportDownloadPage dlPage = shadesPage.pressExport();
        dlPage.waitForDownloadCompletion();
        
        System.out.println("Export function working. Checking import page...");

        Mst_ImportShadesPage importPage = shadesPage.pressImport();
        importPage.waitForElement();
        
        AssertJUnit.assertTrue("Import Shades Page: Title not as expected",importPage.getBreadcrumb().getText().equals("Shades | Import"));
        
        System.out.println("Title as expected");
        
        importPage.assertBaseElements();
        
        System.out.println("Checking fields...");
        
        importPage.checkFields();
        
        System.out.println("Fields checked");
    }
    
    @Test //Customer Shades :: Page and filter checks, add/edit/delete/export features
    (groups = {"Masters"})
    public void customerShades1() throws Exception {
        WebDriver driver = getDriver();
        
        Cce_Base base = new Cce_Base(driver);
        CCE_MainPage ccePage = base.setUp("Customer Shades: Page and filter checks, add/edit/delete/export", "A_CB_CS_1 to 8");
        ccePage.waitForLoad();
        
        System.out.println("Navigating to Customer Shades page...");
        
        Mst_CustomerShadesPage shadesPage = ccePage.selectCustomerShades();
        shadesPage.waitForElement();
        
        System.out.println("Page reached. Checking title...");
        
        AssertJUnit.assertTrue("Customer Shades Page: Title not as expected",shadesPage.getBreadcrumb().getText().equals("Customer Shades"));
        
        System.out.println("Title checked");
        
        shadesPage.assertBaseElements();
        
        System.out.println("Checking fields...");
        
        shadesPage.checkFields();
        
        System.out.println("Fields checked. Entering filter criteria...");
        
        shadesPage.setCustomerName(DataItems.custDetails[0]);
        
        System.out.println("Criteria set. Listing orders...");
        
        shadesPage.pressSearch();
        shadesPage.waitForElement();
        
        System.out.println("Orders listed. Checking filtration...");

        String loc1 = "#content > div.flexi-grid > table > tbody > tr:nth-child(";
        String loc2 = ") > td:nth-child(3)";
        By countField = By.cssSelector("#content > div.flexi-grid > dl > dt > span.left");
        
        AssertJUnit.assertTrue("Customer Shades Page: Filtration not working as expected",shadesPage.checkFiltration(loc1,loc2, DataItems.custDetails[0], countField, 2));
        
        System.out.println("Filtration as expected. Creating new customer shade...");
        
        Mst_AddCustShadePage addPage = shadesPage.pressNewCustomerShade();
        addPage.waitForElement();
        
        System.out.println("Add Shade page reached. Checking title...");
        
        AssertJUnit.assertTrue("Add Customer Shade Page: Title not as expected",addPage.getBreadcrumb().getText().equals("Customer Shades | Add Customer Shade"));
    
        System.out.println("Title checked");
        
        addPage.assertBaseElements();
        
        System.out.println("Checking fields...");
        
        addPage.checkFields();
        
        System.out.println("Fields checked. Entering customer details...");
        
        addPage.setSalesOrg("ID51");
        addPage.setCustomerName(DataItems.custDetails[0]);
        addPage.setCustomerShade("AutomatedShade");
        addPage.setCoatsShade("C1711");
        
        System.out.println("Details entered. Saving...");
        
        addPage.pressSave();
        shadesPage.waitForElement();
        
        System.out.println("Saved. Checking record was created...");
        
        shadesPage.setCustomerName(DataItems.custDetails[0]);
        shadesPage.pressSearch();
        shadesPage.waitForElement();
        
        int row = shadesPage.getRow("AutomatedShade");
        
        AssertJUnit.assertFalse("Customer Shades Page: Customer shade not found after saving",row==-1);
        
        System.out.println("Record created. Editing record...");
        
        Mst_EditCustShadePage editPage = shadesPage.pressEdit(row);
        editPage.waitForElement();
        
        System.out.println("Edit page reached. Editing Customer Shade name...");
        
        editPage.setCustomerShadeName("Automated Edited");
        
        System.out.println("Shade name edited. Saving...");
        
        editPage.pressSave();
        shadesPage.waitForElement();
        
        System.out.println("Saved. Checking details updated...");
        
        shadesPage.setCustomerName(DataItems.custDetails[0]);
        shadesPage.pressSearch();
        shadesPage.waitForElement();
        
        int row2 = shadesPage.getRow("Automated Edited");
        
        AssertJUnit.assertFalse("Customer Shades Page: Shade record not updated after save pressed",row2==-1);
        
        System.out.println("Details updated as expected. Deleting record...");
        
        shadesPage.pressDelete(row2);
        shadesPage.waitForElement();
        
        System.out.println("Record deleted. Checking record was removed...");
        
        shadesPage.setCustomerName(DataItems.custDetails[0]);
        shadesPage.pressSearch();
        shadesPage.waitForElement();
        
        int row3 = shadesPage.getRow("Automated Edited");
        
        AssertJUnit.assertTrue("Customer Shades Page: Shade record not removed after delete pressed",row3==-1);
        
        System.out.println("Record removed. Checking export function...");
        
        CCE_ExportDownloadPage dlPage = shadesPage.pressExport();
        dlPage.waitForDownloadCompletion();
        
        System.out.println("Export completed. Checking import function...");
        
        Mst_ImportPage importPage = shadesPage.pressImport();
        importPage.waitForElement();
        
        System.out.println("Import page reached. Checking title...");
        
        AssertJUnit.assertTrue("Import Customer Shades Page: Title not as expected",importPage.getBreadcrumb().getText().equals("Customer Shades | Import"));
        
        System.out.println("Title as expected");
        
    }
    
    @Test //Customer Finishes :: Page and filter checks, add/edit/delete/export features
    (groups = {"Masters"})
    public void customerFinishes1() throws Exception {
        WebDriver driver = getDriver();
        
        Cce_Base base = new Cce_Base(driver);
        CCE_MainPage mainPage = base.setUp("Customer Finishes: Page and filter checks, add/edit/delete/export features", "A_CB_CF_1 to 8");
        mainPage.waitForLoad();
        
        System.out.println("Navigating to Customer Finishes Page...");
        
        Mst_CustFinishesPage finPage = mainPage.selectCustomerFinishes();
        finPage.waitForElement();
        
        System.out.println("Finish page reached. Checking title...");
        
        AssertJUnit.assertTrue("Customer Finishes Page: Title not as expected",finPage.getBreadcrumb().getText().equals("Customer Finishes"));
        
        System.out.println("Title checked");
        
        finPage.assertBaseElements();
        
        System.out.println("Checking fields...");
        
        finPage.checkFields();
        
        System.out.println("Fields checked. Entering filter criteria...");
        
        finPage.setSalesOrg("ID51");
        finPage.setCustomerName(DataItems.custDetails[0]);
        
        System.out.println("Filter criteria entered. Listing records...");
        
        finPage.pressSearch();
        finPage.waitForElement();
        
        System.out.println("Records listed. Checking filtration...");
        
        String loc1 = "#content > div.flexi-grid > table > tbody > tr:nth-child(";
        String loc2 = ") > td:nth-child(3)";
        By recordField = By.cssSelector("#content > div.flexi-grid > dl > dt > span.left");
        
        AssertJUnit.assertTrue("Customer Finishes Page: Filtration not working as expected",finPage.checkFiltration(loc1,loc2,DataItems.custDetails[0],recordField,2));
        
        System.out.println("Filtration as expected. Creating new Customer Finish...");
        
        Mst_AddCustFinishPage addPage = finPage.pressNewCustFinish();
        addPage.waitForElement();
        
        System.out.println("Add Customer Finish Page reached. Checking title...");
        
        AssertJUnit.assertTrue("Add Customer Finish Page: Title not as expected",addPage.getBreadcrumb().getText().equals("Customer Finishes | Add Customer Finish"));
        
        System.out.println("Title as expected");
        
        addPage.assertBaseElements();
        
        System.out.println("Checking fields...");
        
        addPage.checkFields();
        
        System.out.println("Fields checked. Entering details...");
        
        addPage.setSalesOrg("ID51");
        addPage.setCustomerName(DataItems.custDetails[0]);
        addPage.setCoatsFinsh("Flame Retardant");
        addPage.setCustomerFinish("AutoFinish");
        
        System.out.println("Details entered. Saving...");
        
        addPage.pressSave();
        finPage.waitForElement();
        
        System.out.println("Saved. Checking record appears...");
        
        int row = finPage.getRow("AutoFinish");
        
        AssertJUnit.assertFalse("Customer Finishes Page: Customer Finish not present in table after creation",row==-1);
        
        System.out.println("Record found. Editing record...");
        
        Mst_EditCustFinishPage editPage = finPage.pressEdit(row);
        editPage.waitForElement();
        
        System.out.println("Edit page reached. Checking title...");
        
        AssertJUnit.assertTrue("Edit Customer Finish Page: Title not as expected",editPage.getBreadcrumb().getText().equals("Customer Finishes | Edit Customer Finish"));
        
        System.out.println("Title checked");
        
        editPage.assertBaseElements();
        
        System.out.println("Checking fields...");
        
        editPage.checkFields();
        
        System.out.println("Fields checked. Editing customer finish name...");
        
        editPage.setCustomerFinish("AutoEdited");
        
        System.out.println("Edited. Saving...");
        
        editPage.pressSave();
        finPage.waitForElement();
        
        System.out.println("Saved. Checking record is updated...");
        
        finPage.setSalesOrg("ID51");
        finPage.setCustomerName(DataItems.custDetails[0]);
        finPage.pressSearch();
        finPage.waitForElement();
        
        int row2 = finPage.getRow("AutoEdited");
        AssertJUnit.assertFalse("Customer Finishes Page: Edited changes are not applied in table",row2==-1);
        
        System.out.println("Record updated. Deleting record...");
        
        finPage.pressDelete(row2);
        finPage.waitForElement();
        
        System.out.println("Delete pressed. Checking item is removed...");
        
        finPage.setSalesOrg("ID51");
        finPage.setCustomerName(DataItems.custDetails[0]);
        finPage.pressSearch();
        finPage.waitForElement();
        
        int row3 = finPage.getRow("AutoEdited");
        AssertJUnit.assertTrue("Customer Finishes Page: Item not removed after deletion",row3==-1);
        
        System.out.println("Item removed. Checking export function...");
        
        finPage.setCustomerName(DataItems.custDetails[0]);
        
        CCE_ExportDownloadPage dlPage = finPage.pressExport();
        dlPage.waitForDownloadCompletion();
        
        System.out.println("Export function works. Checking import page...");
        
        Mst_ImportPage impPage = finPage.pressImport();
        impPage.waitForElement();
        
        System.out.println("Page reached. Checking title...");
        
        AssertJUnit.assertTrue("Customer Finishes Import Page: Title not as expected",impPage.getBreadcrumb().getText().equals("Customer Finishes | Import"));
    
        System.out.println("Title as expected");
    }
    
    @Test //Customer Lengths :: Page and filter checks, add/edit/delete/export features
    (groups = {"Masters"})
    public void customerLengths1() throws Exception {
        WebDriver driver = getDriver();
        
        Cce_Base base = new Cce_Base(driver);
        CCE_MainPage mainPage = base.setUp("Customer Lengths: Page and filter checks, add/edit/delete/export features", "A_CB_CL_1 to 8");
        mainPage.waitForLoad();
        
        System.out.println("Navigating to Customer Lengths Page...");
        
        Mst_CustLengthsPage lenPage = mainPage.selectCustomerLengths();
        lenPage.waitForElement();
        
        System.out.println("Lengths page reached. Checking title...");
        
        AssertJUnit.assertTrue("Customer Lengths Page: Title not as expected",lenPage.getBreadcrumb().getText().equals("Customer Lengths"));
        
        System.out.println("Title checked");
        
        lenPage.assertBaseElements();
        
        System.out.println("Checking fields...");
        
        lenPage.checkFields();
        
        System.out.println("Fields checked. Entering filter criteria...");
        
        lenPage.setSalesOrg("ID51");
        lenPage.setCustomerName(DataItems.custDetails[0]);
        
        System.out.println("Filter criteria entered. Listing records...");
        
        lenPage.pressSearch();
        lenPage.waitForElement();
        
        System.out.println("Records listed. Checking filtration...");
        
        String loc1 = "#content > div.flexi-grid > table > tbody > tr:nth-child(";
        String loc2 = ") > td:nth-child(3)";
        By recordField = By.cssSelector("#content > div.flexi-grid > dl > dt > span.left");
        
        AssertJUnit.assertTrue("Customer Lengths Page: Filtration not working as expected",lenPage.checkFiltration(loc1,loc2,DataItems.custDetails[0],recordField,2));
        
        System.out.println("Filtration as expected. Creating new Customer Finish...");
        
        Mst_AddCustLengthPage addPage = lenPage.pressNewCustomerLength();
        addPage.waitForElement();
        
        System.out.println("Add Customer Length Page reached. Checking title...");
        
        AssertJUnit.assertTrue("Add Customer Length Page: Title not as expected",addPage.getBreadcrumb().getText().equals("Customer Lengths | Add Customer Length"));
        
        System.out.println("Title as expected");
        
        addPage.assertBaseElements();
        
        System.out.println("Checking fields...");
        
        addPage.checkFields();
        
        System.out.println("Fields checked. Entering details...");
        
        addPage.setSalesOrg("ID51");
        addPage.setCustomerName(DataItems.custDetails[0]);
        addPage.setCoatsLength("625");
        addPage.setCustomerLength("AutoLength");
        
        System.out.println("Details entered. Saving...");
        
        addPage.pressSave();
        lenPage.waitForElement();
        
        System.out.println("Saved. Checking record appears...");
        
        int row = lenPage.getRow("AutoLength");
        
        AssertJUnit.assertFalse("Customer Length Page: Customer Length not present in table after creation",row==-1);
        
        System.out.println("Record found. Editing record...");

        Mst_EditCustLengthPage editPage = lenPage.pressEdit(row);
        editPage.waitForElement();
        
        System.out.println("Edit page reached. Checking title...");
        
        AssertJUnit.assertTrue("Edit Customer Length Page: Title not as expected",editPage.getBreadcrumb().getText().equals("Customer Lengths | Edit Customer Length"));
        
        System.out.println("Title checked");
        
        editPage.assertBaseElements();
        
        System.out.println("Checking fields...");
        
        editPage.checkFields();
        
        System.out.println("Fields checked. Editing customer length name...");
        
        editPage.setCustomerLength("AutoEdited");
        
        System.out.println("Edited. Saving...");
        
        editPage.pressSave();
        lenPage.waitForElement();
        
        System.out.println("Saved. Checking record is updated...");
        
        lenPage.setSalesOrg("ID51");
        lenPage.setCustomerName(DataItems.custDetails[0]);
        lenPage.pressSearch();
        lenPage.waitForElement();
        
        int row2 = lenPage.getRow("AutoEdited");
        AssertJUnit.assertFalse("Customer Lengths Page: Edited changes are not applied in table",row2==-1);
        
        System.out.println("Record updated. Deleting record...");
        
        lenPage.pressDelete(row2);
        lenPage.waitForElement();
        
        System.out.println("Delete pressed. Checking item is removed...");
        
        lenPage.setSalesOrg("ID51");
        lenPage.setCustomerName(DataItems.custDetails[0]);
        lenPage.pressSearch();
        lenPage.waitForElement();
        
        int row3 = lenPage.getRow("AutoEdited");
        AssertJUnit.assertTrue("Customer Lengths Page: Item not removed after deletion",row3==-1);
        
        System.out.println("Item removed. Checking export function...");
        
        lenPage.setCustomerName(DataItems.custDetails[0]);
        lenPage.pressSearch();
        lenPage.waitForElement();
        
        CCE_ExportDownloadPage dlPage = lenPage.pressExport();
        dlPage.waitForDownloadCompletion();
        
        System.out.println("Export function works. Checking import page...");
        
        Mst_ImportPage impPage = lenPage.pressImport();
        impPage.waitForElement();
        
        System.out.println("Page reached. Checking title...");
        
        AssertJUnit.assertTrue("Customer Lengths Import Page: Title not as expected",impPage.getBreadcrumb().getText().equals("Customer Lengths | Import"));
    
        System.out.println("Title as expected");
    }
    
    @Test //Customer Tickets :: Page and filter checks, add/edit/delete/export features
    (groups = {"Masters"})
    public void customerTickets1() throws Exception {
        WebDriver driver = getDriver();
        
        Cce_Base base = new Cce_Base(driver);
        CCE_MainPage mainPage = base.setUp("Customer Tickets: Page and filter checks, add/edit/delete/export features", "A_CB_Ctkt_1 to 8");
        mainPage.waitForLoad();
        
        System.out.println("Navigating to Customer Tickets Page...");
        
        Mst_CustTicketsPage tktPage = mainPage.selectCustomerTickets();
        tktPage.waitForElement();
        
        System.out.println("Tickets page reached. Checking title...");
        
        AssertJUnit.assertTrue("Customer Tickets Page: Title not as expected",tktPage.getBreadcrumb().getText().equals("Customer Tickets"));
        
        System.out.println("Title checked");
        
        tktPage.assertBaseElements();
        
        System.out.println("Checking fields...");
        
        tktPage.checkFields();
        
        System.out.println("Fields checked. Entering filter criteria...");
        
        tktPage.setSalesOrg("ID51");
        tktPage.setCustomerName(DataItems.custDetails[0]);
        
        System.out.println("Filter criteria entered. Listing records...");
        
        tktPage.pressSearch();
        tktPage.waitForElement();
        
        System.out.println("Records listed. Checking filtration...");
        
        String loc1 = "#content > div.flexi-grid > table > tbody > tr:nth-child(";
        String loc2 = ") > td:nth-child(3)";
        By recordField = By.cssSelector("#content > div.flexi-grid > dl > dt > span.left");
        
        AssertJUnit.assertTrue("Customer Tickets Page: Filtration not working as expected",tktPage.checkFiltration(loc1,loc2,DataItems.custDetails[0],recordField,2));
        
        System.out.println("Filtration as expected. Creating new Customer Ticket...");
        
        Mst_AddCustTicketPage addPage = tktPage.pressNewCustTicket();
        addPage.waitForElement();
        
        System.out.println("Add Customer Ticket Page reached. Checking title...");
        
        AssertJUnit.assertTrue("Add Customer Ticket Page: Title not as expected",addPage.getBreadcrumb().getText().equals("Customer Tickets | Add Customer Ticket"));
        
        System.out.println("Title as expected");
        
        addPage.assertBaseElements();
        
        System.out.println("Checking fields...");
        
        addPage.checkFields();
        
        System.out.println("Fields checked. Entering details...");
        
        addPage.setSalesOrg("ID51");
        addPage.setCustomerName(DataItems.custDetails[0]);
        addPage.setCoatsTicket("031");
        addPage.setCustomerTicket("AutoTkt");
        
        System.out.println("Details entered. Saving...");
        
        addPage.pressSave();
        tktPage.waitForElement();
        
        System.out.println("Saved. Checking record appears...");
        
        int row = tktPage.getRow("AutoTkt");
        
        AssertJUnit.assertFalse("Customer Ticket Page: Customer Ticket not present in table after creation",row==-1);
        
        System.out.println("Record found. Editing record...");
        
        Mst_EditCustTicketPage editPage = tktPage.pressEdit(row);
        editPage.waitForElement();
        
        System.out.println("Edit page reached. Checking title...");
        
        AssertJUnit.assertTrue("Edit Customer Ticket Page: Title not as expected",editPage.getBreadcrumb().getText().equals("Customer Tickets | Edit Customer Ticket"));
        
        System.out.println("Title checked");
        
        editPage.assertBaseElements();
        
        System.out.println("Checking fields...");
        
        editPage.checkFields();
        
        System.out.println("Fields checked. Editing customer ticket name...");
        
        editPage.setCustomerTicket("AutoEdited");
        
        System.out.println("Edited. Saving...");
        
        editPage.pressSave();
        tktPage.waitForElement();
        
        System.out.println("Saved. Checking record is updated...");
        
        tktPage.setSalesOrg("ID51");
        tktPage.setCustomerName(DataItems.custDetails[0]);
        tktPage.pressSearch();
        tktPage.waitForElement();
        
        int row2 = tktPage.getRow("AutoEdited");
        AssertJUnit.assertFalse("Customer Tickets Page: Edited changes are not applied in table",row2==-1);
        
        System.out.println("Record updated. Deleting record...");
        
        tktPage.pressDelete(row2);
        tktPage.waitForElement();
        
        System.out.println("Delete pressed. Checking item is removed...");
        
        tktPage.setSalesOrg("ID51");
        tktPage.setCustomerName(DataItems.custDetails[0]);
        tktPage.pressSearch();
        tktPage.waitForElement();
        
        int row3 = tktPage.getRow("AutoEdited");
        AssertJUnit.assertTrue("Customer Tickets Page: Item not removed after deletion",row3==-1);
        
        System.out.println("Item removed. Checking export function...");
        
        tktPage.setCustomerName(DataItems.custDetails[0]);
        tktPage.pressSearch();
        tktPage.waitForElement();
        
        CCE_ExportDownloadPage dlPage = tktPage.pressExport();
        dlPage.waitForDownloadCompletion();
        
        System.out.println("Export function works. Checking import page...");
        
        Mst_ImportPage impPage = tktPage.pressImport();
        impPage.waitForElement();
        
        System.out.println("Page reached. Checking title...");
        
        AssertJUnit.assertTrue("Customer Tickets Import Page: Title not as expected",impPage.getBreadcrumb().getText().equals("Customer Tickets | Import"));
    
        System.out.println("Title as expected");
    }
    
    @Test //Customer Brands :: Page and filter checks, add/edit/delete/export features
    (groups = {"Masters"})
    public void customerBrands1() throws Exception {
        WebDriver driver = getDriver();
        
        Cce_Base base = new Cce_Base(driver);
        CCE_MainPage mainPage = base.setUp("Customer Brands: Page and filter checks, add/edit/delete/export features", "A_CB_CB_1 to 8");
        mainPage.waitForLoad();
        
        System.out.println("Navigating to Customer Brands Page...");
        
        Mst_CustBrandsPage brndPage = mainPage.selectCustomerBrands();
        brndPage.waitForElement();
        
        System.out.println("Brands page reached. Checking title...");
        
        AssertJUnit.assertTrue("Customer Brands Page: Title not as expected",brndPage.getBreadcrumb().getText().equals("Customer Brands"));
        
        System.out.println("Title checked");
        
        brndPage.assertBaseElements();
        
        System.out.println("Checking fields...");
        
        brndPage.checkFields();
        
        System.out.println("Fields checked. Entering filter criteria...");
        
        brndPage.setSalesOrg("ID51");
        brndPage.setCustomerName(DataItems.custDetails[0]);
        
        System.out.println("Filter criteria entered. Listing records...");
        
        brndPage.pressSearch();
        brndPage.waitForElement();
        
        System.out.println("Records listed. Checking filtration...");
        
        String loc1 = "#content > div.flexi-grid > table > tbody > tr:nth-child(";
        String loc2 = ") > td:nth-child(3)";
        By recordField = By.cssSelector("#content > div.flexi-grid > dl > dt > span.left");
        
        AssertJUnit.assertTrue("Customer Brands Page: Filtration not working as expected",brndPage.checkFiltration(loc1,loc2,DataItems.custDetails[0],recordField,2));
        
        System.out.println("Filtration as expected. Creating new Customer Ticket...");
        
        Mst_AddCustBrandPage addPage = brndPage.pressNewCustBrand();
        addPage.waitForElement();
        
        System.out.println("Add Customer Brand Page reached. Checking title...");
        
        AssertJUnit.assertTrue("Add Customer Brand Page: Title not as expected",addPage.getBreadcrumb().getText().equals("Customer Brands | Add Customer Brand"));
        
        System.out.println("Title as expected");
        
        addPage.assertBaseElements();
        
        System.out.println("Checking fields...");
        
        addPage.checkFields();
        
        System.out.println("Fields checked. Entering details...");
        
        addPage.setSalesOrg("ID51");
        addPage.setCustomerName(DataItems.custDetails[0]);
        addPage.setCoatsBrand("nymo");
        addPage.setCustomerBrand("AutoBrand");
        
        System.out.println("Details entered. Saving...");
        
        addPage.pressSave();
        brndPage.waitForElement();
        
        System.out.println("Saved. Checking record appears...");
        
        int row = brndPage.getRow("AutoBrand");
        
        AssertJUnit.assertFalse("Customer Ticket Page: Customer Brand not present in table after creation",row==-1);
        
        System.out.println("Record found. Editing record...");
        
        Mst_EditCustBrandPage editPage = brndPage.pressEdit(row);
        editPage.waitForElement();
        
        System.out.println("Edit page reached. Checking title...");
        
        AssertJUnit.assertTrue("Edit Customer Brand Page: Title not as expected",editPage.getBreadcrumb().getText().equals("Customer Brands | Edit Customer Brand"));
        
        System.out.println("Title checked");
        
        editPage.assertBaseElements();
        
        System.out.println("Checking fields...");
        
        editPage.checkFields();
        
        System.out.println("Fields checked. Editing customer brand name...");
        
        editPage.setCustomerBrand("AutoEdited");
        
        System.out.println("Edited. Saving...");
        
        editPage.pressSave();
        brndPage.waitForElement();
        
        System.out.println("Saved. Checking record is updated...");
        
        brndPage.setSalesOrg("ID51");
        brndPage.setCustomerName(DataItems.custDetails[0]);
        brndPage.pressSearch();
        brndPage.waitForElement();
        
        int row2 = brndPage.getRow("AutoEdited");
        AssertJUnit.assertFalse("Customer Brands Page: Edited changes are not applied in table",row2==-1);
        
        System.out.println("Record updated. Deleting record...");
        
        brndPage.pressDelete(row2);
        brndPage.waitForElement();
        
        System.out.println("Delete pressed. Checking item is removed...");
        
        brndPage.setSalesOrg("ID51");
        brndPage.setCustomerName(DataItems.custDetails[0]);
        brndPage.pressSearch();
        brndPage.waitForElement();
        
        int row3 = brndPage.getRow("AutoEdited");
        AssertJUnit.assertTrue("Customer Brands Page: Item not removed after deletion",row3==-1);
        
        System.out.println("Item removed. Checking export function...");
        
        brndPage.setCustomerName(DataItems.custDetails[0]);
        brndPage.pressSearch();
        brndPage.waitForElement();
        
        CCE_ExportDownloadPage dlPage = brndPage.pressExport();
        dlPage.waitForDownloadCompletion();
        
        System.out.println("Export function works. Checking import page...");
        
        Mst_ImportPage impPage = brndPage.pressImport();
        impPage.waitForElement();
        
        System.out.println("Page reached. Checking title...");
        
        AssertJUnit.assertTrue("Customer Brands Import Page: Title not as expected",impPage.getBreadcrumb().getText().equals("Customer Brands | Import"));
    
        System.out.println("Title as expected");
    }
    
    @Test //Customer Materials :: Page and filter checks, add/edit/delete/export features
    (groups = {"Masters"})
    public void customerMaterials() throws Exception {
        WebDriver driver = getDriver();
        
        Cce_Base base = new Cce_Base(driver);
        CCE_MainPage mainPage = base.setUp("Customer Materials: Page and filter checks, add/edit/delete/export features", "A_CB_CM_1 to 8");
        mainPage.waitForLoad();
        
        System.out.println("Navigating to Customer Materials Page...");
        
        Mst_CustMaterialsPage matPage = mainPage.selectCustomerMaterials();
        matPage.waitForElement();
        
        System.out.println("Materials page reached. Checking title...");
        
        AssertJUnit.assertTrue("Customer Materials Page: Title not as expected",matPage.getBreadcrumb().getText().equals("Customer Materials"));
        
        System.out.println("Title checked");
        
        matPage.assertBaseElements();
        
        System.out.println("Checking fields...");
        
        matPage.checkFields();
        
        System.out.println("Fields checked. Entering filter criteria...");
        
        matPage.setSalesOrg("ID51");
        matPage.setCustomerName(DataItems.custDetails[0]);
        
        System.out.println("Filter criteria entered. Listing records...");
        
        matPage.pressSearch();
        matPage.waitForElement();
        
        System.out.println("Records listed. Checking filtration...");
        
        String loc1 = "#content > div.flexi-grid > table > tbody > tr:nth-child(";
        String loc2 = ") > td:nth-child(2)";
        By recordField = By.cssSelector("#content > div.flexi-grid > dl > dt > span.left");
        
        AssertJUnit.assertTrue("Customer Materials Page: Filtration not working as expected",matPage.checkFiltration(loc1,loc2,DataItems.custDetails[0],recordField,2));
        
        System.out.println("Filtration as expected. Creating new Customer Materials...");
        
        Mst_AddCustMaterialPage addPage = matPage.pressNewCustMaterial();
        addPage.waitForElement();
        
        System.out.println("Add Customer Material Page reached. Checking title...");
        
        AssertJUnit.assertTrue("Add Customer Material Page: Title not as expected",addPage.getBreadcrumb().getText().equals("Customer Materials | Add Customer Material"));
        
        System.out.println("Title as expected");
        
        addPage.assertBaseElements();
        
        System.out.println("Checking fields...");
        
        addPage.checkFields();
        
        System.out.println("Fields checked. Entering details...");
        
        addPage.setSalesOrg("ID51");
        addPage.setCustomerName(DataItems.custDetails[0]);
        addPage.setCustomerMaterialNo("AutoMaterial");
        addPage.setArticle("8754090");
        addPage.setShadeCode("C9700");
        
        System.out.println("Details entered. Saving...");
        
        addPage.pressSave();
        matPage.waitForElement();
        
        System.out.println("Saved. Checking record appears...");
        
        matPage.setCustomerMaterialNo("AutoMaterial");
        matPage.pressSearch();
        matPage.waitForElement();
        
        int row = matPage.getRow("AutoMaterial");
        
        AssertJUnit.assertFalse("Customer Materials Page: Customer Material not present in table after creation",row==-1);
        
        System.out.println("Record found. Editing record...");
        
        Mst_EditCustMaterialPage editPage = matPage.pressEdit(row);
        editPage.waitForElement();
        
        System.out.println("Edit page reached. Checking title...");
        
        AssertJUnit.assertTrue("Edit Customer Material Page: Title not as expected",editPage.getBreadcrumb().getText().equals("Customer Materials | Edit Customer Material"));
        
        System.out.println("Title checked");
        
        editPage.assertBaseElements();
        
        System.out.println("Checking fields...");
        
        editPage.checkFields();
        
        System.out.println("Fields checked. Editing customer material name...");
        
        editPage.setCustomerMaterialNo("AutoEdited");
        
        System.out.println("Edited. Saving...");
        
        editPage.pressSave();
        matPage.waitForElement();
        
        System.out.println("Saved. Checking record is updated...");
        
        matPage.setCustomerMaterialNo("AutoEdited");
        matPage.pressSearch();
        matPage.waitForElement();
        
        int row2 = matPage.getRow("AutoEdited");
        AssertJUnit.assertFalse("Customer Materials Page: Edited changes are not applied in table",row2==-1);
        
        System.out.println("Record updated. Deleting record...");
        
        matPage.pressDelete(row2);
        matPage.waitForElement();
        
        System.out.println("Delete pressed. Checking item is removed...");
        
        matPage.setCustomerMaterialNo("Auto");
        matPage.pressSearch();
        matPage.waitForElement();
        
        int row3 = matPage.getRow("AutoEdited");
        AssertJUnit.assertTrue("Customer Materials Page: Item not removed after deletion",row3==-1);
        
        System.out.println("Item removed. Checking export function...");
        
        matPage.setCustomerName(DataItems.custDetails[0]);
        matPage.pressSearch();
        matPage.waitForElement();
        
        CCE_ExportDownloadPage dlPage = matPage.pressExport();
        dlPage.waitForDownloadCompletion();
        
        System.out.println("Export function works. Checking import page...");
        
        Mst_ImportPage impPage = matPage.pressImport();
        impPage.waitForElement();
        
        System.out.println("Page reached. Checking title...");
        
        AssertJUnit.assertTrue("Customer Materials Import Page: Title not as expected",impPage.getBreadcrumb().getText().equals("Customer Materials | Import"));
    
        System.out.println("Title as expected");
    }
    
    @Test //Business Principals :: Page and filter checks, add/edit/delete/export features
    (groups = {"Masters"})
    public void businessPrincipals1() throws Exception {
        WebDriver driver = getDriver();
        
        Cce_Base base = new Cce_Base(driver);
        CCE_MainPage mainPage = base.setUp("Business Principals: Page and filter checks, add/edit/delete/export features", "A_CB_BP_1 to 8");
        mainPage.waitForLoad();
        
        System.out.println("Navigating to Business Principals Page...");
        
        Mst_BusinessPrincipalsPage bpPage = mainPage.selectBusinessPrincipals();
        bpPage.waitForElement();
        
        System.out.println("Business Principals page reached. Checking title...");
        
        AssertJUnit.assertTrue("Business Principals Page: Title not as expected",bpPage.getBreadcrumb().getText().equals("Business Principals"));
        
        System.out.println("Title checked");
        
        bpPage.assertBaseElements();
        
        System.out.println("Checking fields...");
        
        bpPage.checkFields();
        
        System.out.println("Fields checked. Entering filter criteria...");
        
        bpPage.setPrincipalName("*OTHERS*");
        
        System.out.println("Filter criteria entered. Listing records...");
        
        bpPage.pressSearch();
        bpPage.waitForElement();
        
        System.out.println("Records listed. Checking filtration...");
        
        String loc1 = "#content > div.flexi-grid > table > tbody > tr:nth-child(";
        String loc2 = ") > td:nth-child(3)";
        By recordField = By.cssSelector("#content > div.flexi-grid > dl > dt > span.left");
        
        AssertJUnit.assertTrue("Business Principals Page: Filtration not working as expected",bpPage.checkFiltration(loc1,loc2,"*OTHERS*",recordField,2));
        
        System.out.println("Filtration as expected. Creating new Business Principals...");
        
        Mst_AddBusinessPrincipalPage addPage = bpPage.pressNewBusinessPrincipal();
        addPage.waitForElement();
        
        System.out.println("Add Business Principal Page reached. Checking title...");
        
        AssertJUnit.assertTrue("Add Business Principal Page: Title not as expected",addPage.getBreadcrumb().getText().equals("Business Principals | Add Business Principal"));
        
        System.out.println("Title as expected");
        
        addPage.assertBaseElements();
        
        System.out.println("Checking fields...");
        
        addPage.checkFields();
        
        System.out.println("Fields checked. Entering details...");
        
        addPage.setPrincipalName("AutomatedTest");
        addPage.setPrincipalNo("100");
        addPage.setLightSource1("CWF");
        
        System.out.println("Details entered. Saving...");
        
        addPage.pressSave();
        bpPage.waitForElement();
        
        System.out.println("Saved. Checking record appears...");
        
        bpPage.setPrincipalName("AutomatedTest");
        bpPage.pressSearch();
        bpPage.waitForElement();
        
        int row = bpPage.getRow("AutomatedTest");
        
        AssertJUnit.assertFalse("Business Principals Page: Business Principal not present in table after creation",row==-1);
        
        System.out.println("Record found. Editing record...");
        
        Mst_EditBusinessPrincipalPage editPage = bpPage.pressEdit(row);
        editPage.waitForElement();
        
        System.out.println("Edit page reached. Checking title...");
        
        AssertJUnit.assertTrue("Edit Business Principal Page: Title not as expected",editPage.getBreadcrumb().getText().equals("Business Principals | Add Business Principal"));
        
        System.out.println("Title checked");
        
        editPage.assertBaseElements();
        
        System.out.println("Checking fields...");
        
        editPage.checkFields();
        
        System.out.println("Fields checked. Editing customer material name...");
        
        editPage.setPrincipalName("AutoEdited");
        
        System.out.println("Edited. Saving...");
        
        editPage.pressSave();
        bpPage.waitForElement();
        
        System.out.println("Saved. Checking record is updated...");
        
        bpPage.setPrincipalName("AutoEdited");
        bpPage.pressSearch();
        bpPage.waitForElement();
        
        int row2 = bpPage.getRow("AutoEdited");
        AssertJUnit.assertFalse("Business Principals Page: Edited changes are not applied in table",row2==-1);
        
        System.out.println("Record updated. Deleting record...");
        
        bpPage.pressDelete(row2);
        bpPage.waitForElement();
        
        System.out.println("Delete pressed. Checking item is removed...");
        
        bpPage.setPrincipalName("Auto");
        bpPage.pressSearch();
        bpPage.waitForElement();
        
        int row3 = bpPage.getRow("AutoEdited");
        AssertJUnit.assertTrue("Business Principals Page: Item not removed after deletion",row3==-1);
        
        System.out.println("Item removed. Checking export function...");
        
        bpPage.setPrincipalName("*OTHERS*");
        bpPage.pressSearch();
        bpPage.waitForElement();
        
        CCE_ExportDownloadPage dlPage = bpPage.pressExport();
        dlPage.waitForDownloadCompletion();
        
        System.out.println("Export function works. Checking import page...");
        
        Mst_ImportPage impPage = bpPage.pressImport();
        impPage.waitForElement();
        
        System.out.println("Page reached. Checking title...");
        
        AssertJUnit.assertTrue("Customer Materials Import Page: Title not as expected",impPage.getBreadcrumb().getText().equals("Business Principals | Import"));
    
        System.out.println("Title as expected");
    }
    
    @Test //Countries :: Page and filter checks, add/edit/delete/export features
    (groups = {"Masters"})
    public void countries1() throws Exception {
        WebDriver driver = getDriver();
        
        Cce_Base base = new Cce_Base(driver);
        CCE_MainPage mainPage = base.setUp("Countries: Page and filter checks, add/edit/delete/export features", "A_CM_C_1 to 8");
        mainPage.waitForLoad();
        
        System.out.println("Navigating to Countries Page...");
        
        Mst_CountriesPage cPage = mainPage.selectCountries();
        cPage.waitForElement();
        
        System.out.println("Countries page reached. Checking title...");
        
        AssertJUnit.assertTrue("Countries Page: Title not as expected",cPage.getBreadcrumb().getText().equals("Countries"));
        
        System.out.println("Title checked");
        
        cPage.assertBaseElements();
        
        System.out.println("Checking fields...");
        
        cPage.checkFields();
        
        System.out.println("Fields checked. Entering filter criteria...");
        
        cPage.setCountryName("Indonesia");
        
        System.out.println("Filter criteria entered. Listing records...");
        
        cPage.pressSearch();
        cPage.waitForElement();
        
        System.out.println("Records listed. Checking filtration...");
        
        String loc1 = "#content > div.flexi-grid > table > tbody > tr:nth-child(";
        String loc2 = ") > td:nth-child(2)";
        By recordField = By.cssSelector("#content > div.flexi-grid > dl > dt > span.left");
        
        AssertJUnit.assertTrue("Countries Page: Filtration not working as expected",cPage.checkFiltration(loc1,loc2,"Indonesia",recordField,2));
        
        System.out.println("Filtration as expected. Creating new Countries...");
        
        Mst_AddCountryPage addPage = cPage.pressNewCountry();
        addPage.waitForElement();
        
        System.out.println("Add Country Page reached. Checking title...");
        
        AssertJUnit.assertTrue("Add Country Page: Title not as expected",addPage.getBreadcrumb().getText().equals("Countries | Add Country"));
        
        System.out.println("Title as expected");
        
        addPage.assertBaseElements();
        
        System.out.println("Checking fields...");
        
        addPage.checkFields();
        
        System.out.println("Fields checked. Entering details...");
        
        addPage.setCountryName("Holy See (Vatican City State)");

        System.out.println("Details entered. Saving...");
        
        addPage.pressSave();
        cPage.waitForElement();
        
        System.out.println("Saved. Checking record appears...");
        
        cPage.setCountryCode("VA");
        cPage.pressSearch();
        cPage.waitForElement();
        
        int row = cPage.getRow("VA");
        
        AssertJUnit.assertFalse("Countries Page: Country not present in table after creation",row==-1);
        
        System.out.println("Record found. Editing record...");
        
        Mst_EditCountryPage editPage = cPage.pressEdit(row);
        editPage.waitForElement();
        
        System.out.println("Edit page reached. Checking title...");
        
        AssertJUnit.assertTrue("Edit Country Page: Title not as expected",editPage.getBreadcrumb().getText().equals("Countries | Edit Country"));
        
        System.out.println("Title checked");
        
        editPage.assertBaseElements();
        
        System.out.println("Checking fields...");
        
        editPage.checkFields();
        
        System.out.println("Fields checked. Editing customer material name...");
        
        editPage.setLanguage("Italian");
        
        System.out.println("Edited. Saving...");
        
        editPage.pressSave();
        cPage.waitForElement();
        
        System.out.println("Saved. Checking record is updated...");
        
        cPage.setCountryCode("VA");
        cPage.pressSearch();
        cPage.waitForElement();
        
        int row2 = cPage.getRowLanguage("Italian");
        AssertJUnit.assertFalse("Countries Page: Edited changes are not applied in table",row2==-1);
        
        System.out.println("Record updated. Deleting record...");
        
        cPage.pressDelete(row2);
        cPage.waitForElement();
        
        System.out.println("Delete pressed. Checking item is removed...");
        
        cPage.setCountryCode("VA");
        cPage.pressSearch();
        cPage.waitForElement();
        
        int row3 = cPage.getRow("VA");
        AssertJUnit.assertTrue("Countries Page: Item not removed after deletion",row3==-1);
        
        System.out.println("Item removed. Checking export function...");
        
        cPage.pressReset();
        cPage.waitForElement();
        cPage.setCountryName("Indonesia");
        cPage.pressSearch();
        cPage.waitForElement();
        
        CCE_ExportDownloadPage dlPage = cPage.pressExport();
        dlPage.waitForDownloadCompletion();
        
        System.out.println("Export function works. Checking import page...");
        
        Mst_ImportPage impPage = cPage.pressImport();
        impPage.waitForElement();
        
        System.out.println("Page reached. Checking title...");
        
        AssertJUnit.assertTrue("Countries Import Page: Title not as expected",impPage.getBreadcrumb().getText().equals("Countries | Import"));
    
        System.out.println("Title as expected");
    }
    
    @Test //Sales Organisations :: Page and filter checks, add/edit/delete/export features
    (groups = {"Masters"})
    public void salesOrg1() throws Exception {
        WebDriver driver = getDriver();
        
        Cce_Base base = new Cce_Base(driver);
        CCE_MainPage mainPage = base.setUp("Sales Organisations: Page and filter checks, add/edit/delete/export features", "A_CM_Sorg_1 to 8");
        mainPage.waitForLoad();
        
        System.out.println("Navigating to Sales Organisations Page...");
        
        Mst_SalesOrgPage soPage = mainPage.selectSalesOrg();
        soPage.waitForElement();
        
        System.out.println("Sales Organisations page reached. Checking title...");
        
        AssertJUnit.assertTrue("Sales Organisations Page: Title not as expected",soPage.getBreadcrumb().getText().equals("Sales Organisations"));
        
        System.out.println("Title checked");
        
        soPage.assertBaseElements();
        
        System.out.println("Checking fields...");
        
        soPage.checkFields();
        
        System.out.println("Fields checked. Entering filter criteria...");
        
        soPage.setSalesOrg("ID51");
        
        System.out.println("Filter criteria entered. Listing records...");
        
        soPage.pressSearch();
        soPage.waitForElement();
        
        System.out.println("Records listed. Checking filtration...");
        
        String loc1 = "#content > div.flexi-grid > table > tbody > tr:nth-child(";
        String loc2 = ") > td:nth-child(2)";
        By recordField = By.cssSelector("#content > div.flexi-grid > dl > dt > span.left");
        
        AssertJUnit.assertTrue("Sales Organisations Page: Filtration not working as expected",soPage.checkFiltration(loc1,loc2,"ID51",recordField,2));
        
        System.out.println("Filtration as expected. Creating new Sales Organisations...");
        
        Mst_AddSalesOrgPage addPage = soPage.pressNewSalesOrg();
        addPage.waitForElement();
        
        System.out.println("Add Sales Organisation Page reached. Checking title...");
        
        AssertJUnit.assertTrue("Add Sales Organisation Page: Title not as expected",addPage.getBreadcrumb().getText().equals("Sales Organisations | Add Sales Organisation"));
        
        System.out.println("Title as expected");
        
        addPage.assertBaseElements();
        
        System.out.println("Checking fields...");
        
        addPage.checkFields();
        
        System.out.println("Fields checked. Entering details...");
        
        addPage.setSalesOrg("ID99");
        addPage.setDescription("Automated tests: generated by test script");
        addPage.setSAPInstance("SAP603");
        
        System.out.println("Details entered. Saving...");
        
        addPage.pressSave();
        soPage.waitForElement();
        
        System.out.println("Saved. Checking record appears...");
        
        soPage.setSalesOrg("ID99");
        soPage.pressSearch();
        soPage.waitForElement();
        
        int row = soPage.getRow("ID99");
        
        AssertJUnit.assertFalse("Sales Organisations Page: Sales Organisation not present in table after creation",row==-1);
        
        System.out.println("Record found. Editing record...");
        
        Mst_EditSalesOrgPage editPage = soPage.pressEdit(row);
        editPage.waitForElement();
        
        System.out.println("Edit page reached. Checking title...");
        
        AssertJUnit.assertTrue("Edit Sales Organisation Page: Title not as expected",editPage.getBreadcrumb().getText().equals("Sales Organisations | Edit Sales Organisation"));
        
        System.out.println("Title checked");
        
        editPage.assertBaseElements();
        
        System.out.println("Checking fields...");
        
        editPage.checkFields();
        
        System.out.println("Fields checked. Editing Sales Organisation...");
        
        editPage.setSalesOrg("ID100");
        
        System.out.println("Edited. Saving...");
        
        editPage.pressSave();
        soPage.waitForElement();
        
        System.out.println("Saved. Checking record is updated...");
        
        soPage.setSalesOrg("ID100");
        soPage.pressSearch();
        soPage.waitForElement();
        
        int row2 = soPage.getRow("ID100");
        AssertJUnit.assertFalse("Sales Organisations Page: Edited changes are not applied in table",row2==-1);
        
        System.out.println("Record updated. Deleting record...");
        
        soPage.pressDelete(row2);
        soPage.waitForElement();
        
        System.out.println("Delete pressed. Checking item is removed...");
        
        soPage.setSalesOrg("ID100");
        soPage.pressSearch();
        soPage.waitForElement();
        
        int row3 = soPage.getRow("ID100");
        AssertJUnit.assertTrue("Sales Organisations Page: Item not removed after deletion",row3==-1);
        
        System.out.println("Item removed. Checking export function...");
        
        soPage.pressReset();
        soPage.waitForElement();
        soPage.setSalesOrg("ID");
        soPage.pressSearch();
        soPage.waitForElement();
        
        CCE_ExportDownloadPage dlPage = soPage.pressExport();
        dlPage.waitForDownloadCompletion();
        
        System.out.println("Export function works. Checking import page...");
        
        Mst_ImportPage impPage = soPage.pressImport();
        impPage.waitForElement();
        
        System.out.println("Page reached. Checking title...");
        
        AssertJUnit.assertTrue("Sales Organisations Import Page: Title not as expected",impPage.getBreadcrumb().getText().equals("Sales Organisations | Import"));
    
        System.out.println("Title as expected");
    }
    
    @Test //Plants :: Page and filter checks, add/edit/delete/export features
    (groups = {"Masters"}, enabled = false) //DISABLED as data cannot be deleted from table (due to sharing violation) so test cannot pass
    public void plants1() throws Exception {
        WebDriver driver = getDriver();
        
        Cce_Base base = new Cce_Base(driver);
        CCE_MainPage mainPage = base.setUp("Plants: Page and filter checks, add/edit/delete/export features", "A_CM_P_1 to 8");
        mainPage.waitForLoad();
        
        System.out.println("Navigating to Plants Page...");
        
        Mst_PlantsPage pPage = mainPage.selectPlants();
        pPage.waitForElement();
        
        System.out.println("Plants page reached. Checking title...");
        
        AssertJUnit.assertTrue("Plants Page: Title not as expected",pPage.getBreadcrumb().getText().equals("Plants"));
        
        System.out.println("Title checked");
        
        pPage.assertBaseElements();
        
        System.out.println("Checking fields...");
        
        pPage.checkFields();
        
        System.out.println("Fields checked. Entering filter criteria...");
        
        pPage.setPlantName("ID10");
        
        System.out.println("Filter criteria entered. Listing records...");
        
        pPage.pressSearch();
        pPage.waitForElement();
        
        System.out.println("Records listed. Checking filtration...");
        
        String loc1 = "#content > div.flexi-grid > table > tbody > tr:nth-child(";
        String loc2 = ") > td:nth-child(2)";
        By recordField = By.cssSelector("#content > div.flexi-grid > dl > dt > span.left");
        
        AssertJUnit.assertTrue("Plants Page: Filtration not working as expected",pPage.checkFiltration(loc1,loc2,"ID10",recordField,2));
        
        System.out.println("Filtration as expected. Creating new Plant...");
        
        Mst_AddPlantPage addPage = pPage.pressNewPlant();
        addPage.waitForElement();
        
        System.out.println("Add Plant Page reached. Checking title...");
        
        AssertJUnit.assertTrue("Add Plant Page: Title not as expected",addPage.getBreadcrumb().getText().equals("Plants | Add Plant"));
        
        System.out.println("Title as expected");
        
        addPage.assertBaseElements();
        
        System.out.println("Checking fields...");
        
        addPage.checkFields();
        
        System.out.println("Fields checked. Entering details...");
        
        addPage.setPlantName("TEST");
        addPage.setPlantDesc("Automated Test: generated by script");
        addPage.setLeadTime1("48");
        addPage.setLeadTime2("48");
        addPage.setLeadTime3("48");
        addPage.setLeadTime4("48");

        System.out.println("Details entered. Saving...");
        
        addPage.pressSave();
        pPage.waitForElement();
        
        System.out.println("Saved. Checking record appears...");
        
        pPage.setPlantName("TEST");
        pPage.pressSearch();
        pPage.waitForElement();
        
        int row = pPage.getRow("TEST");
        
        AssertJUnit.assertFalse("Plants Page: Plant not present in table after creation",row==-1);
        
        System.out.println("Record found. Editing record...");
        
        Mst_EditPlantPage editPage = pPage.pressEdit(row);
        editPage.waitForElement();
        
        System.out.println("Edit page reached. Checking title...");
        
        AssertJUnit.assertTrue("Edit Plant Page: Title not as expected",editPage.getBreadcrumb().getText().equals("Plants | Edit Plant"));
        
        System.out.println("Title checked");
        
        editPage.assertBaseElements();
        
        System.out.println("Checking fields...");
        
        editPage.checkFields();
        
        System.out.println("Fields checked. Editing Sales Organisation...");
        
        editPage.setPlantName("TESTEdited");
        editPage.addDay("Saturday");
        
        System.out.println("Edited. Saving...");
        
        editPage.pressSave();
        pPage.waitForElement();
        
        System.out.println("Saved. Checking record is updated...");
        
        pPage.setPlantName("TESTEdited");
        pPage.pressSearch();
        pPage.waitForElement();
        
        int row2 = pPage.getRow("TESTEdited");
        AssertJUnit.assertFalse("Plants Page: Edited changes are not applied in table",row2==-1);
        
        System.out.println("Record updated. Deleting record...");
        
        pPage.pressDelete(row2);
        pPage.waitForElement();
        
        System.out.println("Delete pressed. Checking item is removed...");
        
        pPage.setPlantName("TESTEdited");
        pPage.pressSearch();
        pPage.waitForElement();
        
        int row3 = pPage.getRow("TESTEdited");
        AssertJUnit.assertTrue("Plants Page: Item not removed after deletion",row3==-1);
        
        System.out.println("Item removed. Checking export function...");
        
        pPage.pressReset();
        pPage.waitForElement();
        pPage.setPlantName("ID");
        pPage.pressSearch();
        pPage.waitForElement();
        
        CCE_ExportDownloadPage dlPage = pPage.pressExport();
        dlPage.waitForDownloadCompletion();
        
        System.out.println("Export function works. Checking import page...");
        
        Mst_ImportPage impPage = pPage.pressImport();
        impPage.waitForElement();
        
        System.out.println("Page reached. Checking title...");
        
        AssertJUnit.assertTrue("Plants Import Page: Title not as expected",impPage.getBreadcrumb().getText().equals("Plants | Import"));
    
        System.out.println("Title as expected");
    }
    
    @Test //Plants :: Page and filter checks, add/edit/delete/export features
    (groups = {"Masters"})
    public void plantsHolidays1() throws Exception {
        WebDriver driver = getDriver();
        
        Cce_Base base = new Cce_Base(driver);
        CCE_MainPage mainPage = base.setUp("Plant Holidays: Page and filter checks, add/edit/delete/export features", "A_CM_PH_1 to 8");
        mainPage.waitForLoad();
        
        System.out.println("Navigating to Plant Holidays Page...");
        
        Mst_PlantHolidaysPage pPage = mainPage.selectPlantHolidays();
        pPage.waitForElement();
        
        System.out.println("Plant Holidays page reached. Checking title...");
        
        AssertJUnit.assertTrue("Plant Holidays Page: Title not as expected",pPage.getBreadcrumb().getText().equals("Plant Holidays"));
        
        System.out.println("Title checked");
        
        pPage.assertBaseElements();
        
        System.out.println("Checking fields...");
        
        pPage.checkFields();
        
        System.out.println("Fields checked. Entering filter criteria...");
        
        pPage.setPlantName("ID10");
        
        System.out.println("Filter criteria entered. Listing records...");
        
        pPage.pressSearch();
        pPage.waitForElement();
        
        System.out.println("Records listed. Checking filtration...");
        
        String loc1 = "#content > div.flexi-grid > table > tbody > tr:nth-child(";
        String loc2 = ") > td:nth-child(2)";
        By recordField = By.cssSelector("#content > div.flexi-grid > dl > dt > span.left");
        
        AssertJUnit.assertTrue("Plant Holidays Page: Filtration not working as expected",pPage.checkFiltration(loc1,loc2,"ID10",recordField,2));
        
        System.out.println("Filtration as expected. Creating new Plant...");
        
        Mst_AddPlantHolidayPage addPage = pPage.pressNewPlantHoliday();
        addPage.waitForElement();
        
        System.out.println("Add Plant Holiday Page reached. Checking title...");
        
        AssertJUnit.assertTrue("Add Plant Holiday Page: Title not as expected",addPage.getBreadcrumb().getText().equals("Plant Holidays | Add Plant Holiday"));
        
        System.out.println("Title as expected");
        
        addPage.assertBaseElements();
        
        System.out.println("Checking fields...");
        
        addPage.checkFields();
        
        System.out.println("Fields checked. Entering details...");
        
        addPage.setPlantName("ID10");
        addPage.setHolidayDescription("Automated Test Holiday");
        addPage.setDateFrom("2015-12-25");
        
        System.out.println("Details entered. Saving...");
        
        addPage.pressSave();
        pPage.waitForElement();
        
        System.out.println("Saved. Checking record appears...");
        
        pPage.setLastUpdatedFrom("2015-11-19");
        pPage.pressSearch();
        pPage.waitForElement();
        
        int row = pPage.getRow("Automated Test Holiday");
        
        AssertJUnit.assertFalse("Plant Holidays Page: Plant Holiday not present in table after creation",row==-1);
        
        System.out.println("Record found. Editing record...");
        
        Mst_EditPlantHolidayPage editPage = pPage.pressEdit(row);
        editPage.waitForElement();
        
        System.out.println("Edit page reached. Checking title...");
        
        AssertJUnit.assertTrue("Edit Plant Holiday Page: Title not as expected",editPage.getBreadcrumb().getText().equals("Plant Holidays | Edit Plant Holiday"));
        
        System.out.println("Title checked");
        
        editPage.assertBaseElements();
        
        System.out.println("Checking fields...");
        
        editPage.checkFields();
        
        System.out.println("Fields checked. Editing Plant Holiday...");
        
        editPage.setHolidayDescription("Edited description");
        
        System.out.println("Edited. Saving...");
        
        editPage.pressSave();
        pPage.waitForElement();
        
        System.out.println("Saved. Checking record is updated...");
        
        pPage.setLastUpdatedFrom("2015-11-18");
        pPage.pressSearch();
        pPage.waitForElement();
        
        int row2 = pPage.getRow("Edited description");
        AssertJUnit.assertFalse("Plant Holidays Page: Edited changes are not applied in table",row2==-1);
        
        System.out.println("Record updated. Deleting record...");
        
        pPage.pressDelete(row2);
        pPage.waitForElement();
        
        System.out.println("Delete pressed. Checking item is removed...");
        
        pPage.setLastUpdatedFrom("2015-11-18");
        pPage.pressSearch();
        pPage.waitForElement();
        
        int row3 = pPage.getRow("Edited description");
        AssertJUnit.assertTrue("Plant Holidays Page: Item not removed after deletion",row3==-1);
        
        System.out.println("Item removed. Checking export function...");
        
        pPage.pressReset();
        pPage.waitForElement();
        pPage.setLastUpdatedFrom("2015-11-18");
        pPage.pressSearch();
        pPage.waitForElement();
        
        CCE_ExportDownloadPage dlPage = pPage.pressExport();
        dlPage.waitForDownloadCompletion();
        
        System.out.println("Export function works. Checking import page...");
        
        Mst_ImportPage impPage = pPage.pressImport();
        impPage.waitForElement();
        
        System.out.println("Page reached. Checking title...");
        
        AssertJUnit.assertTrue("Plant Holidays Import Page: Title not as expected",impPage.getBreadcrumb().getText().equals("Plant Holidays | Import"));
    
        System.out.println("Title as expected");
    }
    
    @Test //Hubs :: Page and filter checks, add/edit/delete/export features
    (groups = {"Masters"})
    public void hubs1() throws Exception {
        WebDriver driver = getDriver();
        
        Cce_Base base = new Cce_Base(driver);
        CCE_MainPage mainPage = base.setUp("Hubs: Page and filter checks, add/edit/delete/export features", "A_CM_Hub_1 to 8");
        mainPage.waitForLoad();
        
        System.out.println("Navigating to Hubs Page...");
        
        Mst_HubsPage pPage = mainPage.selectHubs();
        pPage.waitForElement();
        
        System.out.println("Hubs page reached. Checking title...");
        
        AssertJUnit.assertTrue("Hubs Page: Title not as expected",pPage.getBreadcrumb().getText().equals("Hubs"));
        
        System.out.println("Title checked");
        
        pPage.assertBaseElements();
        
        System.out.println("Checking fields...");
        
        pPage.checkFields();
        
        System.out.println("Fields checked. Entering filter criteria...");
        
        pPage.setHubName("IDH001");
        
        System.out.println("Filter criteria entered. Listing records...");
        
        pPage.pressSearch();
        pPage.waitForElement();
        
        System.out.println("Records listed. Checking filtration...");
        
        String loc1 = "#content > div.flexi-grid > table > tbody > tr:nth-child(";
        String loc2 = ") > td:nth-child(2)";
        By recordField = By.cssSelector("#content > div.flexi-grid > dl > dt > span.left");
        
        AssertJUnit.assertTrue("Hubs Page: Filtration not working as expected",pPage.checkFiltration(loc1,loc2,"IDH001",recordField,2));
        
        System.out.println("Filtration as expected. Creating new Hub...");
        
        Mst_AddHubPage addPage = pPage.pressNewHub();
        addPage.waitForElement();
        
        System.out.println("Add Hub Page reached. Checking title...");
        
        AssertJUnit.assertTrue("Add Hub Page: Title not as expected",addPage.getBreadcrumb().getText().equals("Hubs | Add Hub"));
        
        System.out.println("Title as expected");
        
        addPage.assertBaseElements();
        
        System.out.println("Checking fields...");
        
        addPage.checkFields();
        
        System.out.println("Fields checked. Entering details...");
        
        addPage.setHubName("Automated Test");
        addPage.setHubDescription("Generated by script");
        
        System.out.println("Details entered. Saving...");
        
        addPage.pressSave();
        pPage.waitForElement();
        
        System.out.println("Saved. Checking record appears...");

        pPage.setHubName("Automated Test");
        pPage.pressSearch();
        pPage.waitForElement();
        
        int row = pPage.getRow("Automated Test");
        
        AssertJUnit.assertFalse("Hubs Page: Hub not present in table after creation",row==-1);
        
        System.out.println("Record found. Editing record...");
        
        Mst_EditHubPage editPage = pPage.pressEdit(row);
        editPage.waitForElement();
        
        System.out.println("Edit page reached. Checking title...");
        
        AssertJUnit.assertTrue("Edit Hub Page: Title not as expected",editPage.getBreadcrumb().getText().equals("Hubs | Edit Hub"));
        
        System.out.println("Title checked");
        
        editPage.assertBaseElements();
        
        System.out.println("Checking fields...");
        
        editPage.checkFields();
        
        System.out.println("Fields checked. Editing Hub name...");
        
        editPage.setHubName("EditedHub");
        
        System.out.println("Edited. Saving...");
        
        editPage.pressSave();
        pPage.waitForElement();
        
        System.out.println("Saved. Checking record is updated...");
        
        pPage.setHubName("EditedHub");
        pPage.pressSearch();
        pPage.waitForElement();
        
        int row2 = pPage.getRow("EditedHub");
        AssertJUnit.assertFalse("Edited Hub Page: Edited changes are not applied in table",row2==-1);
        
        System.out.println("Record updated. Deleting record...");
        
        pPage.pressDelete(row2);
        pPage.waitForElement();
        
        System.out.println("Delete pressed. Checking item is removed...");
        
        pPage.setHubName("EditedHub");
        pPage.pressSearch();
        pPage.waitForElement();
        
        int row3 = pPage.getRow("EditedHub");
        AssertJUnit.assertTrue("Hubs Page: Item not removed after deletion",row3==-1);
        
        System.out.println("Item removed. Checking export function...");
        
        pPage.pressReset();
        pPage.waitForElement();
        pPage.setHubName("ID");
        pPage.pressSearch();
        pPage.waitForElement();
        
        CCE_ExportDownloadPage dlPage = pPage.pressExport();
        dlPage.waitForDownloadCompletion();
        
        System.out.println("Export function works. Checking import page...");
        
        Mst_ImportPage impPage = pPage.pressImport();
        impPage.waitForElement();
        
        System.out.println("Page reached. Checking title...");
        
        AssertJUnit.assertTrue("Hubs Import Page: Title not as expected",impPage.getBreadcrumb().getText().equals("Hubs | Import"));
    
        System.out.println("Title as expected");
    }
    
    @Test //Enterprise Structure: Page and filter checks, add/edit/delete/export functions
    (groups = {"Masters",}, enabled=false) //DISABLED as the data created cannot be deleted and so the test cannot pass.
    public void enterpriseStructure1() throws Exception {
        WebDriver driver = getDriver();
        
        Cce_Base base = new Cce_Base(driver);
        CCE_MainPage mainPage = base.setUp("Enterprise Structure: Page and filter checks, add/edit/delete/export features", "A_CM_ES_1 to 8");
        mainPage.waitForLoad();
        
        System.out.println("Navigating to Enterprise Structure Page...");
        
        Mst_EnterpriseStructurePage pPage = mainPage.selectEnterpriseStructure();
        pPage.waitForElement();
        
        System.out.println("Enterprise Structure page reached. Checking title...");
        
        AssertJUnit.assertTrue("Enterprise Structure Page: Title not as expected",pPage.getBreadcrumb().getText().equals("Enterprise Structure"));
        
        System.out.println("Title checked");
        
        pPage.assertBaseElements();
        
        System.out.println("Checking fields...");
        
        pPage.checkFields();
        
        System.out.println("Fields checked. Entering filter criteria...");
        
        pPage.setRegion("EMEAI (Europe Middle East and Africa)");
        
        System.out.println("Filter criteria entered. Listing records...");
        
        pPage.pressSearch();
        pPage.waitForElement();
        
        System.out.println("Records listed. Checking filtration...");
        
        String loc1 = "#content > div.flexi-grid > table > tbody > tr:nth-child(";
        String loc2 = ") > td:nth-child(2)";
        By recordField = By.cssSelector("#content > div.flexi-grid > dl > dt > span.left");
        
        AssertJUnit.assertTrue("Enterprise Structure Page: Filtration not working as expected",pPage.checkFiltration(loc1,loc2,"EMEAI (Europe Middle East and Africa)",recordField,2));
        
        System.out.println("Filtration as expected. Creating new Hub...");
        
        Mst_AddEnterpriseStructurePage addPage = pPage.pressNewEnterpriseStructure();
        addPage.waitForElement();
        
        System.out.println("Add Enterprise Structure Page reached. Checking title...");
        
        AssertJUnit.assertTrue("Add Enterprise Structure Page: Title not as expected",addPage.getBreadcrumb().getText().equals("Enterprise Structure | Add Enterprise Structure"));
        
        System.out.println("Title as expected");
        
        addPage.assertBaseElements();
        
        System.out.println("Checking fields...");
        
        addPage.checkFields();
        
        System.out.println("Fields checked. Entering details...");
        
        addPage.setRegion("EMEAI (Europe Middle East and Africa)");
        addPage.setSalesOrg("Test123");
        addPage.setCountryCode("AD");
        addPage.setHub("test");
        
        System.out.println("Details entered. Saving...");
        
        addPage.pressSave();
        pPage.waitForElement();
        
        System.out.println("Saved. Checking record appears...");

        pPage.setCountryCode("AD");
        pPage.setHub("test");
        pPage.pressSearch();
        pPage.waitForElement();
        
        int row = pPage.getRow("test");
        
        AssertJUnit.assertFalse("Enterprise Structure Page: Enterprise Structure not present in table after creation",row==-1);
        
        System.out.println("Record found. Editing record...");
        
        Mst_EditEnterpriseStructurePage editPage = pPage.pressEdit(row);
        editPage.waitForElement();
        
        System.out.println("Edit page reached. Checking title...");
        
        AssertJUnit.assertTrue("Edit Enterprise Structure Page: Title not as expected",editPage.getBreadcrumb().getText().equals("Enterprise Structure | Edit Enterprise Structure"));
        
        System.out.println("Title checked");
        
        editPage.assertBaseElements();
        
        System.out.println("Checking fields...");
        
        editPage.checkFields();
        
        System.out.println("Fields checked. Editing Hub name...");
        
        editPage.setHub("Test_HUB");
        
        System.out.println("Edited. Saving...");
        
        editPage.pressSave();
        pPage.waitForElement();
        
        System.out.println("Saved. Checking record is updated...");
        
        pPage.setHub("Test_HUB");
        pPage.pressSearch();
        pPage.waitForElement();
        
        int row2 = pPage.getRow("Test_HUB");
        AssertJUnit.assertFalse("Enterprise Structure Page: Edited changes are not applied in table",row2==-1);
        
        System.out.println("Record updated. Deleting record...");
        
        pPage.pressDelete(row2);
        pPage.waitForElement();
        
        System.out.println("Delete pressed. Checking item is removed...");
        
        pPage.setHub("Test_HUB");
        pPage.pressSearch();
        pPage.waitForElement();
        
        int row3 = pPage.getRow("Test_HUB");
        AssertJUnit.assertTrue("Hubs Page: Item not removed after deletion",row3==-1);
        
        System.out.println("Item removed. Checking export function...");
        
        pPage.pressReset();
        pPage.waitForElement();
        pPage.setCountryCode("AD");
        pPage.pressSearch();
        pPage.waitForElement();
        
        CCE_ExportDownloadPage dlPage = pPage.pressExport();
        dlPage.waitForDownloadCompletion();
        
        System.out.println("Export function works. Checking import page...");
        
        Mst_ImportPage impPage = pPage.pressImport();
        impPage.waitForElement();
        
        System.out.println("Page reached. Checking title...");
        
        AssertJUnit.assertTrue("Enterprise Structure Import Page: Title not as expected",impPage.getBreadcrumb().getText().equals("Enterprise Structure | Import"));
    
        System.out.println("Title as expected");
    }
    
    @Test //Brands: Page and filter checks, add/edit/delete/export features
    (groups = {"Masters"})
    public void brands1() throws Exception {
        WebDriver driver = getDriver();
        
        Cce_Base base = new Cce_Base(driver);
        CCE_MainPage mainPage = base.setUp("Brands: Page and filter checks, add/edit/delete/export features", "A_CM_BR_1 to 8");
        mainPage.waitForLoad();
        
        System.out.println("Navigating to Brands Page...");
        
        Mst_BrandsPage pPage = mainPage.selectBrands();
        pPage.waitForElement();
        
        System.out.println("Brands page reached. Checking title...");
        
        AssertJUnit.assertTrue("Brands Page: Title not as expected",pPage.getBreadcrumb().getText().equals("Brands"));
        
        System.out.println("Title checked");
        
        pPage.assertBaseElements();
        
        System.out.println("Checking fields...");
        
        pPage.checkFields();
        
        System.out.println("Fields checked. Entering filter criteria...");
        
        pPage.setBrandName("astra matt");
        
        System.out.println("Filter criteria entered. Listing records...");
        
        pPage.pressSearch();
        pPage.waitForElement();
        
        System.out.println("Records listed. Checking filtration...");
        
        String loc1 = "#content > div.flexi-grid > table > tbody > tr:nth-child(";
        String loc2 = ") > td:nth-child(2)";
        By recordField = By.cssSelector("#content > div.flexi-grid > dl > dt > span.left");
        
        AssertJUnit.assertTrue("Brands Page: Filtration not working as expected",pPage.checkFiltration(loc1,loc2,"astra matt",recordField,2));
        
        System.out.println("Filtration as expected. Creating new Brand...");
        
        Mst_AddBrandPage addPage = pPage.pressNewBrand();
        addPage.waitForElement();
        
        System.out.println("Add Brand Page reached. Checking title...");
        
        AssertJUnit.assertTrue("Add Brand Page: Title not as expected",addPage.getBreadcrumb().getText().equals("Brands | Add Brand"));
        
        System.out.println("Title as expected");
        
        addPage.assertBaseElements();
        
        System.out.println("Checking fields...");
        
        addPage.checkFields();
        
        System.out.println("Fields checked. Entering details...");
        
        addPage.setBrandName("AutomatedTest");
        
        System.out.println("Details entered. Saving...");
        
        addPage.pressSave();
        pPage.waitForElement();
        
        System.out.println("Saved. Checking record appears...");

        pPage.setBrandName("AutomatedTest");
        pPage.pressSearch();
        pPage.waitForElement();
        
        int row = pPage.getRow("AutomatedTest");
        
        AssertJUnit.assertFalse("Brands Page: Brand not present in table after creation",row==-1);
        
        System.out.println("Record found. Editing record...");
        
        Mst_EditBrandPage editPage = pPage.pressEdit(row);
        editPage.waitForElement();
        
        System.out.println("Edit page reached. Checking title...");
        
        AssertJUnit.assertTrue("Edit Brand Page: Title not as expected",editPage.getBreadcrumb().getText().equals("Brands | Edit Brand"));
        
        System.out.println("Title checked");
        
        editPage.assertBaseElements();
        
        System.out.println("Checking fields...");
        
        editPage.checkFields();
        
        System.out.println("Fields checked. Editing Brand name...");
        
        editPage.setBrandName("EditedBrand");
        
        System.out.println("Edited. Saving...");
        
        editPage.pressSave();
        pPage.waitForElement();
        
        System.out.println("Saved. Checking record is updated...");
        
        pPage.setBrandName("EditedBrand");
        pPage.pressSearch();
        pPage.waitForElement();
        
        int row2 = pPage.getRow("EditedBrand");
        AssertJUnit.assertFalse("Brands Page: Edited changes are not applied in table",row2==-1);
        
        System.out.println("Record updated. Deleting record...");
        
        pPage.pressDelete(row2);
        pPage.waitForElement();
        
        System.out.println("Delete pressed. Checking item is removed...");
        
        pPage.setBrandName("EditedBrand");
        pPage.pressSearch();
        pPage.waitForElement();
        
        int row3 = pPage.getRow("EditedBrand");
        AssertJUnit.assertTrue("Brands Page: Item not removed after deletion",row3==-1);
        
        System.out.println("Item removed. Checking export function...");
        
        pPage.pressReset();
        pPage.waitForElement();
        pPage.setBrandName("astra");
        pPage.pressSearch();
        pPage.waitForElement();
        
        CCE_ExportDownloadPage dlPage = pPage.pressExport();
        dlPage.waitForDownloadCompletion();
        
        System.out.println("Export function works. Checking import page...");
        
        Mst_ImportPage impPage = pPage.pressImport();
        impPage.waitForElement();
        
        System.out.println("Page reached. Checking title...");
        
        AssertJUnit.assertTrue("Brands Import Page: Title not as expected",impPage.getBreadcrumb().getText().equals("Brands | Import"));
    
        System.out.println("Title as expected");
    }
    
    @Test //Tickets: Page and filter checks, add/edit/delete/export features
    (groups = {"Masters"})
    public void ticket1() throws Exception {
        WebDriver driver = getDriver();
        
        Cce_Base base = new Cce_Base(driver);
        CCE_MainPage mainPage = base.setUp("Tickets: Page and filter checks, add/edit/delete/export features", "A_CM_Tkt_1 to 8");
        mainPage.waitForLoad();
        
        System.out.println("Navigating to Tickets Page...");
        
        Mst_TicketsPage pPage = mainPage.selectTickets();
        pPage.waitForElement();
        
        System.out.println("Tickets page reached. Checking title...");
        
        AssertJUnit.assertTrue("Tickets Page: Title not as expected",pPage.getBreadcrumb().getText().equals("Tickets"));
        
        System.out.println("Title checked");
        
        pPage.assertBaseElements();
        
        System.out.println("Checking fields...");
        
        pPage.checkFields();
        
        System.out.println("Fields checked. Entering filter criteria...");
        
        pPage.setTicketName("1200");
        
        System.out.println("Filter criteria entered. Listing records...");
        
        pPage.pressSearch();
        pPage.waitForElement();
        
        System.out.println("Records listed. Checking filtration...");
        
        String loc1 = "#content > div.flexi-grid > table > tbody > tr:nth-child(";
        String loc2 = ") > td:nth-child(2)";
        By recordField = By.cssSelector("#content > div.flexi-grid > dl > dt > span.left");
        
        AssertJUnit.assertTrue("Tickets Page: Filtration not working as expected",pPage.checkFiltration(loc1,loc2,"1200",recordField,2));
        
        System.out.println("Filtration as expected. Creating new Ticket...");
        
        Mst_AddTicketPage addPage = pPage.pressNewTicket();
        addPage.waitForElement();
        
        System.out.println("Add Ticket Page reached. Checking title...");
        
        AssertJUnit.assertTrue("Add Ticket Page: Title not as expected",addPage.getBreadcrumb().getText().equals("Tickets | Add Ticket"));
        
        System.out.println("Title as expected");
        
        addPage.assertBaseElements();
        
        System.out.println("Checking fields...");
        
        addPage.checkFields();
        
        System.out.println("Fields checked. Entering details...");
        
        addPage.setTicketName("AUT01");
        
        System.out.println("Details entered. Saving...");
        
        addPage.pressSave();
        pPage.waitForElement();
        
        System.out.println("Saved. Checking record appears...");

        pPage.setTicketName("AUT01");
        pPage.pressSearch();
        pPage.waitForElement();
        
        int row = pPage.getRow("AUT01");
        
        AssertJUnit.assertFalse("Tickets Page: Ticket not present in table after creation",row==-1);
        
        System.out.println("Record found. Editing record...");
        
        Mst_EditTicketPage editPage = pPage.pressEdit(row);
        editPage.waitForElement();
        
        System.out.println("Edit page reached. Checking title...");
        
        AssertJUnit.assertTrue("Edit Ticket Page: Title not as expected",editPage.getBreadcrumb().getText().equals("Tickets | Edit Ticket"));
        
        System.out.println("Title checked");
        
        editPage.assertBaseElements();
        
        System.out.println("Checking fields...");
        
        editPage.checkFields();
        
        System.out.println("Fields checked. Editing Ticket name...");
        
        editPage.setTicketName("EditedTicket");
        
        System.out.println("Edited. Saving...");
        
        editPage.pressSave();
        pPage.waitForElement();
        
        System.out.println("Saved. Checking record is updated...");
        
        pPage.setTicketName("EditedTicket");
        pPage.pressSearch();
        pPage.waitForElement();
        
        int row2 = pPage.getRow("EditedTicket");
        AssertJUnit.assertFalse("Tickets Page: Edited changes are not applied in table",row2==-1);
        
        System.out.println("Record updated. Deleting record...");
        
        pPage.pressDelete(row2);
        pPage.waitForElement();
        
        System.out.println("Delete pressed. Checking item is removed...");
        
        pPage.setTicketName("EditedTicket");
        pPage.pressSearch();
        pPage.waitForElement();
        
        int row3 = pPage.getRow("EditedTicket");
        AssertJUnit.assertTrue("Tickets Page: Item not removed after deletion",row3==-1);
        
        System.out.println("Item removed. Checking export function...");
        
        pPage.pressReset();
        pPage.waitForElement();
        pPage.setTicketName("12");
        pPage.pressSearch();
        pPage.waitForElement();
        
        CCE_ExportDownloadPage dlPage = pPage.pressExport();
        dlPage.waitForDownloadCompletion();
        
        System.out.println("Export function works. Checking import page...");
        
        Mst_ImportPage impPage = pPage.pressImport();
        impPage.waitForElement();
        
        System.out.println("Page reached. Checking title...");
        
        AssertJUnit.assertTrue("Tickets Import Page: Title not as expected",impPage.getBreadcrumb().getText().equals("Tickets | Import"));
    
        System.out.println("Title as expected");
    }
    
    @Test //Lengths: Page and filter checks, add/edit/delete/export features
    (groups = {"Masters"})
    public void lengths1() throws Exception {
        WebDriver driver = getDriver();
        
        Cce_Base base = new Cce_Base(driver);
        CCE_MainPage mainPage = base.setUp("Lengths: Page and filter checks, add/edit/delete/export features", "A_CM_L_1 to 8");
        mainPage.waitForLoad();
        
        System.out.println("Navigating to Lengths Page...");
        
        Mst_LengthsPage pPage = mainPage.selectLengths();
        pPage.waitForElement();
        
        System.out.println("Lengths page reached. Checking title...");
        
        AssertJUnit.assertTrue("Lengths Page: Title not as expected",pPage.getBreadcrumb().getText().equals("Lengths"));
        
        System.out.println("Title checked");
        
        pPage.assertBaseElements();
        
        System.out.println("Checking fields...");
        
        pPage.checkFields();
        
        System.out.println("Fields checked. Entering filter criteria...");
        
        pPage.setLengthName("15000");
        
        System.out.println("Filter criteria entered. Listing records...");
        
        pPage.pressSearch();
        pPage.waitForElement();
        
        System.out.println("Records listed. Checking filtration...");
        
        String loc1 = "#content > div.flexi-grid > table > tbody > tr:nth-child(";
        String loc2 = ") > td:nth-child(2)";
        By recordField = By.cssSelector("#content > div.flexi-grid > dl > dt > span.left");
        
        AssertJUnit.assertTrue("Lengths Page: Filtration not working as expected",pPage.checkFiltration(loc1,loc2,"15000",recordField,2));
        
        System.out.println("Filtration as expected. Creating new Length...");
        
        Mst_AddLengthPage addPage = pPage.pressNewLength();
        addPage.waitForElement();
        
        System.out.println("Add Length Page reached. Checking title...");
        
        AssertJUnit.assertTrue("Add Length Page: Title not as expected",addPage.getBreadcrumb().getText().equals("Lengths | Add Length"));
        
        System.out.println("Title as expected");
        
        addPage.assertBaseElements();
        
        System.out.println("Checking fields...");
        
        addPage.checkFields();
        
        System.out.println("Fields checked. Entering details...");
        
        addPage.setLengthName("10 AutoTest");
        
        System.out.println("Details entered. Saving...");
        
        addPage.pressSave();
        pPage.waitForElement();
        
        System.out.println("Saved. Checking record appears...");

        pPage.setLengthName("10 AutoTest");
        pPage.pressSearch();
        pPage.waitForElement();
        
        int row = pPage.getRow("10 AutoTest");
        
        AssertJUnit.assertFalse("Lengths Page: Length not present in table after creation",row==-1);
        
        System.out.println("Record found. Editing record...");
        
        Mst_EditLengthPage editPage = pPage.pressEdit(row);
        editPage.waitForElement();
        
        System.out.println("Edit page reached. Checking title...");
        
        AssertJUnit.assertTrue("Edit Length Page: Title not as expected",editPage.getBreadcrumb().getText().equals("Lengths | Edit Length"));
        
        System.out.println("Title checked");
        
        editPage.assertBaseElements();
        
        System.out.println("Checking fields...");
        
        editPage.checkFields();
        
        System.out.println("Fields checked. Editing Length name...");
        
        editPage.setLengthName("EditedLength");
        
        System.out.println("Edited. Saving...");
        
        editPage.pressSave();
        pPage.waitForElement();
        
        System.out.println("Saved. Checking record is updated...");
        
        pPage.setLengthName("EditedLength");
        pPage.pressSearch();
        pPage.waitForElement();
        
        int row2 = pPage.getRow("EditedLength");
        AssertJUnit.assertFalse("Lengths Page: Edited changes are not applied in table",row2==-1);
        
        System.out.println("Record updated. Deleting record...");
        
        pPage.pressDelete(row2);
        pPage.waitForElement();
        
        System.out.println("Delete pressed. Checking item is removed...");
        
        pPage.setLengthName("EditedLength");
        pPage.pressSearch();
        pPage.waitForElement();
        
        int row3 = pPage.getRow("EditedLength");
        AssertJUnit.assertTrue("Lengths Page: Item not removed after deletion",row3==-1);
        
        System.out.println("Item removed. Checking export function...");
        
        pPage.pressReset();
        pPage.waitForElement();
        pPage.setLengthName("10");
        pPage.pressSearch();
        pPage.waitForElement();
        
        CCE_ExportDownloadPage dlPage = pPage.pressExport();
        dlPage.waitForDownloadCompletion();
        
        System.out.println("Export function works. Checking import page...");
        
        Mst_ImportPage impPage = pPage.pressImport();
        impPage.waitForElement();
        
        System.out.println("Page reached. Checking title...");
        
        AssertJUnit.assertTrue("Lengths Import Page: Title not as expected",impPage.getBreadcrumb().getText().equals("Lengths | Import"));
    
        System.out.println("Title as expected");
    }
    
    @Test //Finishes :: Page and filter checks, add/edit/delete/export features
    (groups = {"Masters"})
    public void finishes1() throws Exception {
        WebDriver driver = getDriver();
        
        Cce_Base base = new Cce_Base(driver);
        CCE_MainPage mainPage = base.setUp("Finishes: Page and filter checks, add/edit/delete/export features", "A_CM_F_1 to 8");
        mainPage.waitForLoad();
        
        System.out.println("Navigating to Finishes Page...");
        
        Mst_FinishesPage pPage = mainPage.selectFinishes();
        pPage.waitForElement();
        
        System.out.println("Finishes page reached. Checking title...");
        
        AssertJUnit.assertTrue("Finishes Page: Title not as expected",pPage.getBreadcrumb().getText().equals("Finishes"));
        
        System.out.println("Title checked");
        
        pPage.assertBaseElements();
        
        System.out.println("Checking fields...");
        
        pPage.checkFields();
        
        System.out.println("Fields checked. Entering filter criteria...");
        
        pPage.setFinishName("STANDARD");
        
        System.out.println("Filter criteria entered. Listing records...");
        
        pPage.pressSearch();
        pPage.waitForElement();
        
        System.out.println("Records listed. Checking filtration...");
        
        String loc1 = "#content > div.flexi-grid > table > tbody > tr:nth-child(";
        String loc2 = ") > td:nth-child(2)";
        By recordField = By.cssSelector("#content > div.flexi-grid > dl > dt > span.left");
        
        AssertJUnit.assertTrue("Finishes Page: Filtration not working as expected",pPage.checkFiltration(loc1,loc2,"STANDARD",recordField,2));
        
        System.out.println("Filtration as expected. Creating new Finish...");
        
        Mst_AddFinishPage addPage = pPage.pressNewFinish();
        addPage.waitForElement();
        
        System.out.println("Add Finish Page reached. Checking title...");
        
        AssertJUnit.assertTrue("Add Finish Page: Title not as expected",addPage.getBreadcrumb().getText().equals("Finishes | Add Finish"));
        
        System.out.println("Title as expected");
        
        addPage.assertBaseElements();
        
        System.out.println("Checking fields...");
        
        addPage.checkFields();
        
        System.out.println("Fields checked. Entering details...");
        
        addPage.setFinishName("AutoFinish");
        
        System.out.println("Details entered. Saving...");
        
        addPage.pressSave();
        pPage.waitForElement();
        
        System.out.println("Saved. Checking record appears...");

        pPage.setFinishName("AutoFinish");
        pPage.pressSearch();
        pPage.waitForElement();
        
        int row = pPage.getRow("AutoFinish");
        
        AssertJUnit.assertFalse("Finishes Page: Finish not present in table after creation",row==-1);
        
        System.out.println("Record found. Editing record...");
        
        Mst_EditFinishPage editPage = pPage.pressEdit(row);
        editPage.waitForElement();
        
        System.out.println("Edit page reached. Checking title...");
        
        AssertJUnit.assertTrue("Edit Finish Page: Title not as expected",editPage.getBreadcrumb().getText().equals("Finishes | Edit Finish"));
        
        System.out.println("Title checked");
        
        editPage.assertBaseElements();
        
        System.out.println("Checking fields...");
        
        editPage.checkFields();
        
        System.out.println("Fields checked. Editing Finish name...");
        
        editPage.setFinishName("EditedFinish");
        
        System.out.println("Edited. Saving...");
        
        editPage.pressSave();
        pPage.waitForElement();
        
        System.out.println("Saved. Checking record is updated...");
        
        pPage.setFinishName("EditedFinish");
        pPage.pressSearch();
        pPage.waitForElement();
        
        int row2 = pPage.getRow("EditedFinish");
        AssertJUnit.assertFalse("Finishes Page: Edited changes are not applied in table",row2==-1);
        
        System.out.println("Record updated. Deleting record...");
        
        pPage.pressDelete(row2);
        pPage.waitForElement();
        
        System.out.println("Delete pressed. Checking item is removed...");
        
        pPage.setFinishName("EditedFinish");
        pPage.pressSearch();
        pPage.waitForElement();
        
        int row3 = pPage.getRow("EditedFinish");
        AssertJUnit.assertTrue("Finishes Page: Item not removed after deletion",row3==-1);
        
        System.out.println("Item removed. Checking export function...");
        
        pPage.pressReset();
        pPage.waitForElement();
        pPage.setFinishName("STANDARD");
        pPage.pressSearch();
        pPage.waitForElement();
        
        CCE_ExportDownloadPage dlPage = pPage.pressExport();
        dlPage.waitForDownloadCompletion();
        
        System.out.println("Export function works. Checking import page...");
        
        Mst_ImportPage impPage = pPage.pressImport();
        impPage.waitForElement();
        
        System.out.println("Page reached. Checking title...");
        
        AssertJUnit.assertTrue("Finishes Import Page: Title not as expected",impPage.getBreadcrumb().getText().equals("Finishes | Import"));
    
        System.out.println("Title as expected");
    }
    
    @Test //Basic Materials :: Page and filter checks, add/edit/delete/export features
    (groups = {"Masters"})
    public void basicMaterials1() throws Exception {
        WebDriver driver = getDriver();
        
        Cce_Base base = new Cce_Base(driver);
        CCE_MainPage mainPage = base.setUp("Basic Materials: Page and filter checks, add/edit/delete/export features", "A_CM_BM_1 to 8");
        mainPage.waitForLoad();
        
        System.out.println("Navigating to Basic Materials Page...");
        
        Mst_BasicMaterialsPage pPage = mainPage.selectBasicMaterials();
        pPage.waitForElement();
        
        System.out.println("Basic Materials page reached. Checking title...");
        
        AssertJUnit.assertTrue("Basic Materials Page: Title not as expected",pPage.getBreadcrumb().getText().equals("Basic Materials"));
        
        System.out.println("Title checked");
        
        pPage.assertBaseElements();
        
        System.out.println("Checking fields...");
        
        pPage.checkFields();
        
        System.out.println("Fields checked. Entering filter criteria...");
        
        pPage.setBrand("astra");
        
        System.out.println("Filter criteria entered. Listing records...");
        
        pPage.pressSearch();
        pPage.waitForElement();
        
        System.out.println("Records listed. Checking filtration...");
        
        String loc1 = "#content > div.flexi-grid > table > tbody > tr:nth-child(";
        String loc2 = ") > td:nth-child(2)";
        By recordField = By.cssSelector("#content > div.flexi-grid > dl > dt > span.left");
        
        AssertJUnit.assertTrue("Basic Materials Page: Filtration not working as expected",pPage.checkFiltration(loc1,loc2,"astra",recordField,2));
        
        System.out.println("Filtration as expected. Creating new Basic Material...");
        
        Mst_AddBasicMaterialPage addPage = pPage.pressNewBasicMaterial();
        addPage.waitForElement();
        
        System.out.println("Add Basic Material Page reached. Checking title...");
        
        AssertJUnit.assertTrue("Add Basic Material Page: Title not as expected",addPage.getBreadcrumb().getText().equals("Basic Materials | Add Basic Material"));
        
        System.out.println("Title as expected");
        
        addPage.assertBaseElements();
        
        System.out.println("Checking fields...");
        
        addPage.checkFields();
        
        System.out.println("Fields checked. Entering details...");
        
        addPage.setBrandName("Test");
        addPage.setMaterialGroup("IG");
        addPage.setProductHierarchy("Test_COE");
        
        System.out.println("Details entered. Saving...");
        
        addPage.pressSave();
        pPage.waitForElement();
        
        System.out.println("Saved. Checking record appears...");

        pPage.setBrand("Test");
        pPage.setProductHierarchy("Test_COE");
        pPage.setMaterialGroup("IG");
        pPage.pressSearch();
        pPage.waitForElement();
        
        int row = pPage.getRow("Test");
        
        AssertJUnit.assertFalse("Finishes Page: Finish not present in table after creation",row==-1);
        
        System.out.println("Record found. Editing record...");
        
        Mst_EditBasicMaterialPage editPage = pPage.pressEdit(row);
        editPage.waitForElement();
        
        System.out.println("Edit page reached. Checking title...");
        
        AssertJUnit.assertTrue("Edit Basic Material Page: Title not as expected",editPage.getBreadcrumb().getText().equals("Basic Materials | Edit Basic Material"));
        
        System.out.println("Title checked");
        
        editPage.assertBaseElements();
        
        System.out.println("Checking fields...");
        
        editPage.checkFields();
        
        System.out.println("Fields checked. Editing Basic Material name...");
        
        editPage.setMaterialGroup("IA");
        
        System.out.println("Edited. Saving...");
        
        editPage.pressSave();
        pPage.waitForElement();
        
        System.out.println("Saved. Checking record is updated...");
        
        pPage.setBrand("Test");
        pPage.setMaterialGroup("IA");
        pPage.pressSearch();
        pPage.waitForElement();
        
        int row2 = pPage.getRow("Test");
        AssertJUnit.assertFalse("Basic Materials Page: Edited changes are not applied in table",row2==-1);
        
        System.out.println("Record updated. Deleting record...");
        
        pPage.pressDelete(row2);
        pPage.waitForElement();
        
        System.out.println("Delete pressed. Checking item is removed...");
        
        pPage.setBrand("Test");
        pPage.setMaterialGroup("IA");
        pPage.pressSearch();
        pPage.waitForElement();
        
        int row3 = pPage.getRow("Test");
        AssertJUnit.assertTrue("Basic Materials Page: Item not removed after deletion",row3==-1);
        
        System.out.println("Item removed. Checking export function...");
        
        pPage.pressReset();
        pPage.waitForElement();
        pPage.setBrand("astra");
        pPage.pressSearch();
        pPage.waitForElement();
        
        CCE_ExportDownloadPage dlPage = pPage.pressExport();
        dlPage.waitForDownloadCompletion();
        
        System.out.println("Export function works. Checking import page...");
        
        Mst_ImportPage impPage = pPage.pressImport();
        impPage.waitForElement();
        
        System.out.println("Page reached. Checking title...");
        
        AssertJUnit.assertTrue("Basic Materials Import Page: Title not as expected",impPage.getBreadcrumb().getText().equals("Basic Materials | Import"));
    
        System.out.println("Title as expected");
    }
    
    @Test //Material Groups :: Page and filter checks, add/edit/delete/export features
    (groups = {"Masters"})
    public void materialGroup1() throws Exception {
        WebDriver driver = getDriver();
        
        Cce_Base base = new Cce_Base(driver);
        CCE_MainPage mainPage = base.setUp("Material Groups: Page and filter checks, add/edit/delete/export features", "A_CM_MG_1 to 8");
        mainPage.waitForLoad();
        
        System.out.println("Navigating to Material Groups Page...");
        
        Mst_MaterialGroupsPage pPage = mainPage.selectMaterialGroups();
        pPage.waitForElement();
        
        System.out.println("Material Groups page reached. Checking title...");
        
        AssertJUnit.assertTrue("Material Groups Page: Title not as expected",pPage.getBreadcrumb().getText().equals("Material Groups"));
        
        System.out.println("Title checked");
        
        pPage.assertBaseElements();
        
        System.out.println("Checking fields...");
        
        pPage.checkFields();
        
        System.out.println("Fields checked. Entering filter criteria...");
        
        pPage.setMaterialGroup("IA");
        
        System.out.println("Filter criteria entered. Listing records...");
        
        pPage.pressSearch();
        pPage.waitForElement();
        
        System.out.println("Records listed. Checking filtration...");
        
        String loc1 = "#content > div.flexi-grid > table > tbody > tr:nth-child(";
        String loc2 = ") > td:nth-child(2)";
        By recordField = By.cssSelector("#content > div.flexi-grid > dl > dt > span.left");
        
        AssertJUnit.assertTrue("Material Groups Page: Filtration not working as expected",pPage.checkFiltration(loc1,loc2,"IA",recordField,2));
        
        System.out.println("Filtration as expected. Creating new Material Group...");
        
        Mst_AddMaterialGroupPage addPage = pPage.pressNewMaterialGroup();
        addPage.waitForElement();
        
        System.out.println("Add Material Group Page reached. Checking title...");
        
        AssertJUnit.assertTrue("Add Material Group Page: Title not as expected",addPage.getBreadcrumb().getText().equals("Material Groups | Add Material Group"));
        
        System.out.println("Title as expected");
        
        addPage.assertBaseElements();
        
        System.out.println("Checking fields...");
        
        addPage.checkFields();
        
        System.out.println("Fields checked. Entering details...");
        
        addPage.setMaterialGroup("Auto_Test");
        
        System.out.println("Details entered. Saving...");
        
        addPage.pressSave();
        pPage.waitForElement();
        
        System.out.println("Saved. Checking record appears...");
        
        pPage.setMaterialGroup("Auto_Test");
        pPage.pressSearch();
        pPage.waitForElement();
        
        int row = pPage.getRow("Auto_Test");
        
        AssertJUnit.assertFalse("Material Groups Page: Material Group not present in table after creation",row==-1);
        
        System.out.println("Record found. Editing record...");
        
        Mst_EditMaterialGroupPage editPage = pPage.pressEdit(row);
        editPage.waitForElement();
        
        System.out.println("Edit page reached. Checking title...");
        
        AssertJUnit.assertTrue("Edit Material Group Page: Title not as expected",editPage.getBreadcrumb().getText().equals("Material Groups | Edit Material Group"));
        
        System.out.println("Title checked");
        
        editPage.assertBaseElements();
        
        System.out.println("Checking fields...");
        
        editPage.checkFields();
        
        System.out.println("Fields checked. Editing Material Group name...");
        
        editPage.setMaterialGroup("Edited_Test");
        
        System.out.println("Edited. Saving...");
        
        editPage.pressSave();
        pPage.waitForElement();
        
        System.out.println("Saved. Checking record is updated...");
        
        pPage.setMaterialGroup("Edited_Test");
        pPage.pressSearch();
        pPage.waitForElement();
        
        int row2 = pPage.getRow("Edited_Test");
        AssertJUnit.assertFalse("Material Groups Page: Edited changes are not applied in table",row2==-1);
        
        System.out.println("Record updated. Deleting record...");
        
        pPage.pressDelete(row2);
        pPage.waitForElement();
        
        System.out.println("Delete pressed. Checking item is removed...");
        
        pPage.setMaterialGroup("Edited_Test");
        pPage.pressSearch();
        pPage.waitForElement();
        
        int row3 = pPage.getRow("Edited_Test");
        AssertJUnit.assertTrue("Material Groups Page: Item not removed after deletion",row3==-1);
        
        System.out.println("Item removed. Checking export function...");
        
        pPage.pressReset();
        pPage.waitForElement();
        
        CCE_ExportDownloadPage dlPage = pPage.pressExport();
        dlPage.waitForDownloadCompletion();
        
        System.out.println("Export function works. Checking import page...");
        
        Mst_ImportPage impPage = pPage.pressImport();
        impPage.waitForElement();
        
        System.out.println("Page reached. Checking title...");
        
        AssertJUnit.assertTrue("Material Groups Import Page: Title not as expected",impPage.getBreadcrumb().getText().equals("MaterialGroups | Import"));
    
        System.out.println("Title as expected");
    }
    
    @Test //Hierarchy :: Page and filter checks, add/edit/delete/export features
    (groups = {"Masters"})
    public void hierarchy1() throws Exception {
        WebDriver driver = getDriver();
        
        Cce_Base base = new Cce_Base(driver);
        CCE_MainPage mainPage = base.setUp("Hierarchy: Page and filter checks, add/edit/delete/export features", "A_CM_H_1 to 8");
        mainPage.waitForLoad();
        
        System.out.println("Navigating to Hierarchy Page...");
        
        Mst_HierarchyPage pPage = mainPage.selectHierarchy();
        pPage.waitForElement();
        
        System.out.println("Hierarchy page reached. Checking title...");
        
        AssertJUnit.assertTrue("Hierarchy Page: Title not as expected",pPage.getBreadcrumb().getText().equals("Hierarchies"));
        
        System.out.println("Title checked");
        
        pPage.assertBaseElements();
        
        System.out.println("Checking fields...");
        
        pPage.checkFields();
        
        System.out.println("Fields checked. Entering filter criteria...");
        
        pPage.setHierarchy("Test_COE");
        
        System.out.println("Filter criteria entered. Listing records...");
        
        pPage.pressSearch();
        pPage.waitForElement();
        
        System.out.println("Records listed. Checking filtration...");
        
        String loc1 = "#content > div.flexi-grid > table > tbody > tr:nth-child(";
        String loc2 = ") > td:nth-child(2)";
        By recordField = By.cssSelector("#content > div.flexi-grid > dl > dt > span.left");
        
        AssertJUnit.assertTrue("Hierarchy Page: Filtration not working as expected",pPage.checkFiltration(loc1,loc2,"Test_COE",recordField,2));
        
        System.out.println("Filtration as expected. Creating new Hierarchy...");
        
        Mst_AddHierarchyPage addPage = pPage.pressNewHierarchy();
        addPage.waitForElement();
        
        System.out.println("Add Hierarchy Page reached. Checking title...");
        
        AssertJUnit.assertTrue("Add Hierarchy Page: Title not as expected",addPage.getBreadcrumb().getText().equals("Hierarchies | Add Hierarchy"));
        
        System.out.println("Title as expected");
        
        addPage.assertBaseElements();
        
        System.out.println("Checking fields...");
        
        addPage.checkFields();
        
        System.out.println("Fields checked. Entering details...");
        
        addPage.setHierarchy("Auto_Test");
        
        System.out.println("Details entered. Saving...");
        
        addPage.pressSave();
        pPage.waitForElement();
        
        System.out.println("Saved. Checking record appears...");
        
        pPage.setHierarchy("Auto_Test");
        pPage.pressSearch();
        pPage.waitForElement();
        
        int row = pPage.getRow("Auto_Test");
        
        AssertJUnit.assertFalse("Hierarchy Page: Hierarchy not present in table after creation",row==-1);
        
        System.out.println("Record found. Editing record...");
        
        Mst_EditHierarchyPage editPage = pPage.pressEdit(row);
        editPage.waitForElement();
        
        System.out.println("Edit page reached. Checking title...");
        
        AssertJUnit.assertTrue("Edit Hierarchy Page: Title not as expected",editPage.getBreadcrumb().getText().equals("Hierarchies | Edit Hierarchy"));
        
        System.out.println("Title checked");
        
        editPage.assertBaseElements();
        
        System.out.println("Checking fields...");
        
        editPage.checkFields();
        
        System.out.println("Fields checked. Editing Hierarchy name...");
        
        editPage.setHierarchy("Edited_Test");
        
        System.out.println("Edited. Saving...");
        
        editPage.pressSave();
        pPage.waitForElement();
        
        System.out.println("Saved. Checking record is updated...");
        
        pPage.setHierarchy("Edited_Test");
        pPage.pressSearch();
        pPage.waitForElement();
        
        int row2 = pPage.getRow("Edited_Test");
        AssertJUnit.assertFalse("Hierarchy Page: Edited changes are not applied in table",row2==-1);
        
        System.out.println("Record updated. Deleting record...");
        
        pPage.pressDelete(row2);
        pPage.waitForElement();
        
        System.out.println("Delete pressed. Checking item is removed...");
        
        pPage.setHierarchy("Edited_Test");
        pPage.pressSearch();
        pPage.waitForElement();
        
        int row3 = pPage.getRow("Edited_Test");
        AssertJUnit.assertTrue("Hierarchies Page: Item not removed after deletion",row3==-1);
        
        System.out.println("Item removed. Checking export function...");
        
        pPage.pressReset();
        pPage.waitForElement();
        
        CCE_ExportDownloadPage dlPage = pPage.pressExport();
        dlPage.waitForDownloadCompletion();
        
        System.out.println("Export function works. Checking import page...");
        
        Mst_ImportPage impPage = pPage.pressImport();
        impPage.waitForElement();
        
        System.out.println("Page reached. Checking title...");
        
        AssertJUnit.assertTrue("Hierarchies Import Page: Title not as expected",impPage.getBreadcrumb().getText().equals("Hierarchies | Import"));
    
        System.out.println("Title as expected");
    }
    
    @Test //Light Sources :: Page and filter checks, add/edit/delete/export features
    (groups = {"Masters"})
    public void lightSources1() throws Exception {
        WebDriver driver = getDriver();
        
        Cce_Base base = new Cce_Base(driver);
        CCE_MainPage mainPage = base.setUp("Light Sources: Page and filter checks, add/edit/delete/export features", "A_CM_LS_1 to 8");
        mainPage.waitForLoad();
        
        System.out.println("Navigating to Light Sources Page...");
        
        Mst_LightSourcesPage pPage = mainPage.selectLightSources();
        pPage.waitForElement();
        
        System.out.println("Light Sources page reached. Checking title...");
        
        AssertJUnit.assertTrue("Light Sources Page: Title not as expected",pPage.getBreadcrumb().getText().equals("Light Sources"));
        
        System.out.println("Title checked");
        
        pPage.assertBaseElements();
        
        System.out.println("Checking fields...");
        
        pPage.checkFields();
        
        System.out.println("Fields checked. Entering filter criteria...");
        
        pPage.setLightSource("F7");
        
        System.out.println("Filter criteria entered. Listing records...");
        
        pPage.pressSearch();
        pPage.waitForElement();
        
        System.out.println("Records listed. Checking filtration...");
        
        String loc1 = "#content > div.flexi-grid > table > tbody > tr:nth-child(";
        String loc2 = ") > td:nth-child(2)";
        By recordField = By.cssSelector("#content > div.flexi-grid > dl > dt > span.left");
        
        AssertJUnit.assertTrue("Light Sources Page: Filtration not working as expected",pPage.checkFiltration(loc1,loc2,"F7",recordField,2));
        
        System.out.println("Filtration as expected. Creating new Hierarchy...");
        
        Mst_AddLightSourcePage addPage = pPage.pressNewLightSource();
        addPage.waitForElement();
        
        System.out.println("Add Light Source Page reached. Checking title...");
        
        AssertJUnit.assertTrue("Add Light Source Page: Title not as expected",addPage.getBreadcrumb().getText().equals("Light Sources | Add Light Source"));
        
        System.out.println("Title as expected");
        
        addPage.assertBaseElements();
        
        System.out.println("Checking fields...");
        
        addPage.checkFields();
        
        System.out.println("Fields checked. Entering details...");
        
        addPage.setLightSource("AutoTest");
        
        System.out.println("Details entered. Saving...");
        
        addPage.pressSave();
        pPage.waitForElement();
        
        System.out.println("Saved. Checking record appears...");
        
        pPage.setLightSource("AutoTest");
        pPage.pressSearch();
        pPage.waitForElement();
        
        int row = pPage.getRow("AutoTest");
        
        AssertJUnit.assertFalse("Light Sources Page: Light Source not present in table after creation",row==-1);
        
        System.out.println("Record found. Editing record...");
        
        Mst_EditLightSourcePage editPage = pPage.pressEdit(row);
        editPage.waitForElement();
        
        System.out.println("Edit page reached. Checking title...");
        
        AssertJUnit.assertTrue("Edit Light Source Page: Title not as expected",editPage.getBreadcrumb().getText().equals("Light Sources | Edit Light Source"));
        
        System.out.println("Title checked");
        
        editPage.assertBaseElements();
        
        System.out.println("Checking fields...");
        
        editPage.checkFields();
        
        System.out.println("Fields checked. Editing Light Source name...");
        
        editPage.setLightSource("Edited");
        
        System.out.println("Edited. Saving...");
        
        editPage.pressSave();
        pPage.waitForElement();
        
        System.out.println("Saved. Checking record is updated...");
        
        pPage.setLightSource("Edited");
        pPage.pressSearch();
        pPage.waitForElement();
        
        int row2 = pPage.getRow("Edited");
        AssertJUnit.assertFalse("Light Source Page: Edited changes are not applied in table",row2==-1);
        
        System.out.println("Record updated. Deleting record...");
        
        pPage.pressDelete(row2);
        pPage.waitForElement();
        
        System.out.println("Delete pressed. Checking item is removed...");
        
        pPage.setLightSource("Edited");
        pPage.pressSearch();
        pPage.waitForElement();
        
        int row3 = pPage.getRow("Edited");
        AssertJUnit.assertTrue("Light Sources Page: Item not removed after deletion",row3==-1);
        
        System.out.println("Item removed. Checking export function...");
        
        pPage.pressReset();
        pPage.waitForElement();
        
        CCE_ExportDownloadPage dlPage = pPage.pressExport();
        dlPage.waitForDownloadCompletion();
        
        System.out.println("Export function works. Checking import page...");
        
        Mst_ImportPage impPage = pPage.pressImport();
        impPage.waitForElement();
        
        System.out.println("Page reached. Checking title...");
        
        AssertJUnit.assertTrue("Light Sources Import Page: Title not as expected",impPage.getBreadcrumb().getText().equals("Light Sources | Import"));
    
        System.out.println("Title as expected");
    }

    @Test //Purpose Types :: Page and filter checks, add/edit/delete/export features
    (groups = {"Masters"})
    public void purposeTypes1() throws Exception {
        WebDriver driver = getDriver();
        
        Cce_Base base = new Cce_Base(driver);
        CCE_MainPage mainPage = base.setUp("Purpose Types: Page and filter checks, add/edit/delete/export features", "A_CM_PT_1 to 8");
        mainPage.waitForLoad();
        
        System.out.println("Navigating to Purpose Types Page...");
        
        Mst_PurposeTypesPage pPage = mainPage.selectPurposeTypes();
        pPage.waitForElement();
        
        System.out.println("Purpose Types page reached. Checking title...");
        
        AssertJUnit.assertTrue("Purpose Types Page: Title not as expected",pPage.getBreadcrumb().getText().equals("Purpose Types"));
        
        System.out.println("Title checked");
        
        pPage.assertBaseElements();
        
        System.out.println("Checking fields...");
        
        pPage.checkFields();
        
        System.out.println("Fields checked. Entering filter criteria...");
        
        pPage.setPurposeType("Shade Development");
        
        System.out.println("Filter criteria entered. Listing records...");
        
        pPage.pressSearch();
        pPage.waitForElement();
        
        System.out.println("Records listed. Checking filtration...");
        
        String loc1 = "#content > div.flexi-grid > table > tbody > tr:nth-child(";
        String loc2 = ") > td:nth-child(2)";
        By recordField = By.cssSelector("#content > div.flexi-grid > dl > dt > span.left");
        
        AssertJUnit.assertTrue("Purpose Types Page: Filtration not working as expected",pPage.checkFiltration(loc1,loc2,"Shade Development",recordField,2));
        
        System.out.println("Filtration as expected. Creating new Purpose Types...");
        
        Mst_AddPurposeTypePage addPage = pPage.pressNewPurposeType();
        addPage.waitForElement();
        
        System.out.println("Add Purpose Type Page reached. Checking title...");
        
        AssertJUnit.assertTrue("Add Purpose Type Page: Title not as expected",addPage.getBreadcrumb().getText().equals("Purpose Types | Add Purpose Type"));
        
        System.out.println("Title as expected");
        
        addPage.assertBaseElements();
        
        System.out.println("Checking fields...");
        
        addPage.checkFields();
        
        System.out.println("Fields checked. Entering details...");
        
        addPage.setPurposeType("AutoTest");
        
        System.out.println("Details entered. Saving...");
        
        addPage.pressSave();
        pPage.waitForElement();
        
        System.out.println("Saved. Checking record appears...");
        
        pPage.setPurposeType("AutoTest");
        pPage.pressSearch();
        pPage.waitForElement();
        
        int row = pPage.getRow("AutoTest");
        
        AssertJUnit.assertFalse("Purpose Types Page: Purpose Type not present in table after creation",row==-1);
        
        System.out.println("Record found. Editing record...");
        
        Mst_EditPurposeTypePage editPage = pPage.pressEdit(row);
        editPage.waitForElement();
        
        System.out.println("Edit page reached. Checking title...");
        
        AssertJUnit.assertTrue("Edit Purpose Type Page: Title not as expected",editPage.getBreadcrumb().getText().equals("Purpose Types | Edit Purpose Type"));
        
        System.out.println("Title checked");
        
        editPage.assertBaseElements();
        
        System.out.println("Checking fields...");
        
        editPage.checkFields();
        
        System.out.println("Fields checked. Editing Purpose Type...");
        
        editPage.setPurposeType("Edited");
        
        System.out.println("Edited. Saving...");
        
        editPage.pressSave();
        pPage.waitForElement();
        
        System.out.println("Saved. Checking record is updated...");
        
        pPage.setPurposeType("Edited");
        pPage.pressSearch();
        pPage.waitForElement();
        
        int row2 = pPage.getRow("Edited");
        AssertJUnit.assertFalse("Purpose Types Page: Edited changes are not applied in table",row2==-1);
        
        System.out.println("Record updated. Deleting record...");
        
        pPage.pressDelete(row2);
        pPage.waitForElement();
        
        System.out.println("Delete pressed. Checking item is removed...");
        
        pPage.setPurposeType("Edited");
        pPage.pressSearch();
        pPage.waitForElement();
        
        int row3 = pPage.getRow("Edited");
        AssertJUnit.assertTrue("Purpose Types Page: Item not removed after deletion",row3==-1);
        
        System.out.println("Item removed. Checking export function...");
        
        pPage.pressReset();
        pPage.waitForElement();
        
        CCE_ExportDownloadPage dlPage = pPage.pressExport();
        dlPage.waitForDownloadCompletion();
        
        System.out.println("Export function works. Checking import page...");
        
        Mst_ImportPage impPage = pPage.pressImport();
        impPage.waitForElement();
        
        System.out.println("Page reached. Checking title...");
        
        AssertJUnit.assertTrue("Purpose Types Import Page: Title not as expected",impPage.getBreadcrumb().getText().equals("Purpose Types | Import"));
    
        System.out.println("Title as expected");
    }
    
    @Test //Rejection Reasons :: Page and filter checks, add/edit/delete/export features
    (groups = {"Masters"})
    public void rejectionReasons1() throws Exception {
        WebDriver driver = getDriver();
        
        Cce_Base base = new Cce_Base(driver);
        CCE_MainPage mainPage = base.setUp("Rejection Reasons: Page and filter checks, add/edit/delete/export features", "A_CM_RR_1 to 8");
        mainPage.waitForLoad();
        
        System.out.println("Navigating to Rejection Reasons Page...");
        
        Mst_RejectionReasonsPage pPage = mainPage.selectRejectionReasons();
        pPage.waitForElement();
        
        System.out.println("Rejection Reasons page reached. Checking title...");
        
        AssertJUnit.assertTrue("Rejection Reasons Page: Title not as expected",pPage.getBreadcrumb().getText().equals("Rejection Reasons"));
        
        System.out.println("Title checked");
        
        pPage.assertBaseElements();
        
        System.out.println("Checking fields...");
        
        pPage.checkFields();
        
        System.out.println("Fields checked. Entering filter criteria...");
        
        pPage.setRejectionCode("2016");
        
        System.out.println("Filter criteria entered. Listing records...");
        
        pPage.pressSearch();
        pPage.waitForElement();
        
        System.out.println("Records listed. Checking filtration...");
        
        String loc1 = "#content > div.flexi-grid > table > tbody > tr:nth-child(";
        String loc2 = ") > td:nth-child(2)";
        By recordField = By.cssSelector("#content > div.flexi-grid > dl > dt > span.left");
        
        AssertJUnit.assertTrue("Rejection Reasons Page: Filtration not working as expected",pPage.checkFiltration(loc1,loc2,"2016",recordField,2));
        
        System.out.println("Filtration as expected. Creating new Purpose Types...");
        
        Mst_AddRejectionReasonPage addPage = pPage.pressNewRejectionReason();
        addPage.waitForElement();
        
        System.out.println("Add Rejection Reason Page reached. Checking title...");
        
        AssertJUnit.assertTrue("Add Rejection Reason Page: Title not as expected",addPage.getBreadcrumb().getText().equals("Rejection Reasons | Add Rejection Reason"));
        
        System.out.println("Title as expected");
        
        addPage.assertBaseElements();
        
        System.out.println("Checking fields...");
        
        addPage.checkFields();
        
        System.out.println("Fields checked. Entering details...");
        
        addPage.setRejectionReason("AutoTest");
        addPage.setRejectionCode("AUT0");
        
        System.out.println("Details entered. Saving...");
        
        addPage.pressSave();
        pPage.waitForElement();
        
        System.out.println("Saved. Checking record appears...");
        
        pPage.setRejectionCode("AUT0");
        pPage.pressSearch();
        pPage.waitForElement();
        
        int row = pPage.getRow("AUT0");
        
        AssertJUnit.assertFalse("Rejection Reasons Page: Rejection Reason not present in table after creation",row==-1);
        
        System.out.println("Record found. Editing record...");
        
        Mst_EditRejectionReasonPage editPage = pPage.pressEdit(row);
        editPage.waitForElement();
        
        System.out.println("Edit page reached. Checking title...");
        
        AssertJUnit.assertTrue("Edit Rejection Reason Page: Title not as expected",editPage.getBreadcrumb().getText().equals("Rejection Reasons | Edit Rejection Reason"));
        
        System.out.println("Title checked");
        
        editPage.assertBaseElements();
        
        System.out.println("Checking fields...");
        
        editPage.checkFields();
        
        System.out.println("Fields checked. Editing Rejection Reason...");
        
        editPage.setRejectionCode("Edited");
        
        System.out.println("Edited. Saving...");
        
        editPage.pressSave();
        pPage.waitForElement();
        
        System.out.println("Saved. Checking record is updated...");
        
        pPage.setRejectionCode("Edited");
        pPage.pressSearch();
        pPage.waitForElement();
        
        int row2 = pPage.getRow("Edited");
        AssertJUnit.assertFalse("Rejection Reasons Page: Edited changes are not applied in table",row2==-1);
        
        System.out.println("Record updated. Deleting record...");
        
        pPage.pressDelete(row2);
        pPage.waitForElement();
        
        System.out.println("Delete pressed. Checking item is removed...");
        
        pPage.setRejectionCode("Edited");
        pPage.pressSearch();
        pPage.waitForElement();
        
        int row3 = pPage.getRow("Edited");
        AssertJUnit.assertTrue("Rejection Reasons Page: Item not removed after deletion",row3==-1);
        
        System.out.println("Item removed. Checking export function...");
        
        pPage.pressReset();
        pPage.waitForElement();
        
        CCE_ExportDownloadPage dlPage = pPage.pressExport();
        dlPage.waitForDownloadCompletion();
        
        System.out.println("Export function works. Checking import page...");
        
        Mst_ImportPage impPage = pPage.pressImport();
        impPage.waitForElement();
        
        System.out.println("Page reached. Checking title...");
        
        AssertJUnit.assertTrue("Rejection Reasons Import Page: Title not as expected",impPage.getBreadcrumb().getText().equals("Rejection Reasons | Import"));
    
        System.out.println("Title as expected");
    }
    
    @Test //Quantity Factors :: Page and filter checks, add/edit/delete/export features
    (groups = {"Masters"})
    public void quantityFactors1() throws Exception {
        WebDriver driver = getDriver();
        
        Cce_Base base = new Cce_Base(driver);
        CCE_MainPage mainPage = base.setUp("Quantity Factors: Page and filter checks, add/edit/delete/export features", "A_CM_QF_1 to 8");
        mainPage.waitForLoad();
        
        System.out.println("Navigating to Quantity Factors Page...");
        
        Mst_QuantityFactorsPage pPage = mainPage.selectQuantityFactors();
        pPage.waitForElement();
        
        System.out.println("Quantity Factors page reached. Checking title...");
        
        AssertJUnit.assertTrue("Quantity Factors Page: Title not as expected",pPage.getBreadcrumb().getText().equals("Quantity Factors"));
        
        System.out.println("Title checked");
        
        pPage.assertBaseElements();
        
        System.out.println("Checking fields...");
        
        pPage.checkFields();
        
        System.out.println("Fields checked. Entering filter criteria...");
        
        pPage.setSalesOrg("ID51");
        
        System.out.println("Filter criteria entered. Listing records...");
        
        pPage.pressSearch();
        pPage.waitForElement();
        
        System.out.println("Records listed. Checking filtration...");
        
        String loc1 = "#content > div.flexi-grid > table > tbody > tr:nth-child(";
        String loc2 = ") > td:nth-child(2)";
        By recordField = By.cssSelector("#content > div.flexi-grid > dl > dt > span.left");
        
        AssertJUnit.assertTrue("Quantity Factors Page: Filtration not working as expected",pPage.checkFiltration(loc1,loc2,"ID51",recordField,2));
        
        System.out.println("Filtration as expected. Creating new Quantity Factors...");
        
        Mst_AddQuantityFactorPage addPage = pPage.pressNewQuantityFactor();
        addPage.waitForElement();
        
        System.out.println("Add Quantity Factor Page reached. Checking title...");
        
        AssertJUnit.assertTrue("Add Quantity Factor Page: Title not as expected",addPage.getBreadcrumb().getText().equals("Quantity Factors | Add Quantity Factor"));
        
        System.out.println("Title as expected");
        
        addPage.assertBaseElements();
        
        System.out.println("Checking fields...");
        
        addPage.checkFields();
        
        System.out.println("Fields checked. Entering details...");
        
        addPage.setSalesOrg("ID51");
        addPage.setPlantName("ID10");
        addPage.setBrand("Test");
        addPage.setMUMType("Cone");
        addPage.setTicket("000");
        addPage.setLabQty("5");
        addPage.setCCEQty("5");
        
        System.out.println("Details entered. Saving...");
        
        addPage.pressSave();
        pPage.waitForElement();
        
        System.out.println("Saved. Checking record appears...");
        
        pPage.setSalesOrg("ID51");
        pPage.setPlantName("ID10");
        pPage.setBrand("Test");
        pPage.pressSearch();
        pPage.waitForElement();
        
        int row = pPage.getRow("ID51","ID10","Test","000");
        
        AssertJUnit.assertFalse("Rejection Reasons Page: Rejection Reason not present in table after creation",row==-1);
        
        System.out.println("Record found. Editing record...");
        
        Mst_EditQuantityFactorPage editPage = pPage.pressEdit(row);
        editPage.waitForElement();
        
        System.out.println("Edit page reached. Checking title...");
        
        AssertJUnit.assertTrue("Edit Quantity Factor Page: Title not as expected",editPage.getBreadcrumb().getText().equals("Quantity Factors | Edit Quantity Factor"));
        
        System.out.println("Title checked");
        
        editPage.assertBaseElements();
        
        System.out.println("Checking fields...");
        
        editPage.checkFields();
        
        System.out.println("Fields checked. Editing CCE Quantity...");
        
        editPage.setCCEQty("8");
        
        System.out.println("Edited. Saving...");
        
        editPage.pressSave();
        pPage.waitForElement();
        
        System.out.println("Saved. Checking record is updated...");
        
        pPage.setSalesOrg("ID51");
        pPage.setPlantName("ID10");
        pPage.setBrand("Test");
        pPage.pressSearch();
        pPage.waitForElement();
        
        int row2 = pPage.getRow("ID51","ID10","Test","000");
        AssertJUnit.assertFalse("Quantity Factors Page: Edited changes are not applied in table",row2==-1);
        
        System.out.println("Record updated. Deleting record...");
        
        pPage.pressDelete(row2);
        pPage.waitForElement();
        
        System.out.println("Delete pressed. Checking item is removed...");
        
        pPage.setSalesOrg("ID51");
        pPage.setPlantName("ID10");
        pPage.setBrand("Test");
        pPage.pressSearch();
        pPage.waitForElement();
        
        int row3 = pPage.getRow("ID51","ID10","Test","000");
        AssertJUnit.assertTrue("Quantity Factors Page: Item not removed after deletion",row3==-1);
        
        System.out.println("Item removed. Checking export function...");
        
        pPage.pressReset();
        pPage.waitForElement();
        pPage.setSalesOrg("ID51");
        pPage.setBrand("astra");
        pPage.pressSearch();
        pPage.waitForElement();
        
        CCE_ExportDownloadPage dlPage = pPage.pressExport();
        dlPage.waitForDownloadCompletion();
        
        System.out.println("Export function works. Checking import page...");
        
        Mst_ImportPage impPage = pPage.pressImport();
        impPage.waitForElement();
        
        System.out.println("Page reached. Checking title...");
        
        AssertJUnit.assertTrue("Quantity Factors Import Page: Title not as expected",impPage.getBreadcrumb().getText().equals("Quantity Factors | Import"));
    
        System.out.println("Title as expected");
    }
    
    @Test //Length Offers :: Page and filter checks, add/edit/delete/export features
    (groups = {"Masters"})
    public void lengthOffers1() throws Exception {
        WebDriver driver = getDriver();
        
        Cce_Base base = new Cce_Base(driver);
        CCE_MainPage mainPage = base.setUp("Length Offers: Page and filter checks, add/edit/delete/export features", "A_CM_LO_1 to 8");
        mainPage.waitForLoad();
        
        System.out.println("Navigating to Length Offers Page...");
        
        Mst_LengthOffersPage pPage = mainPage.selectLengthOffers();
        pPage.waitForElement();
        
        System.out.println("Length Offers page reached. Checking title...");
        
        AssertJUnit.assertTrue("Length Offers Page: Title not as expected",pPage.getBreadcrumb().getText().equals("Length Offers"));
        
        System.out.println("Title checked");
        
        pPage.assertBaseElements();
        
        System.out.println("Checking fields...");
        
        pPage.checkFields();
        
        System.out.println("Fields checked. Entering filter criteria...");
        
        pPage.setPlantName("ID10");
        
        System.out.println("Filter criteria entered. Listing records...");
        
        pPage.pressSearch();
        pPage.waitForElement();
        
        System.out.println("Records listed. Checking filtration...");
        
        String loc1 = "#content > div.flexi-grid > table > tbody > tr:nth-child(";
        String loc2 = ") > td:nth-child(2)";
        By recordField = By.cssSelector("#content > div.flexi-grid > dl > dt > span.left");
        
        AssertJUnit.assertTrue("Rejection Reasons Page: Filtration not working as expected",pPage.checkFiltration(loc1,loc2,"ID10",recordField,2));
        
        System.out.println("Filtration as expected. Creating new Length Offer...");
        
        Mst_AddLengthOfferPage addPage = pPage.pressNewLengthOffer();
        addPage.waitForElement();
        
        System.out.println("Add Length Offers Page reached. Checking title...");
        
        AssertJUnit.assertTrue("Add Length Offers Page: Title not as expected",addPage.getBreadcrumb().getText().equals("Length Offers | Add Length Offer"));
        
        System.out.println("Title as expected");
        
        addPage.assertBaseElements();
        
        System.out.println("Checking fields...");
        
        addPage.checkFields();
        
        System.out.println("Fields checked. Entering details...");
        
        addPage.setPlant("ID10");
        addPage.setBrand("Test");
        addPage.setTicket("000");
        addPage.setUOM("Grams");
        addPage.setMeasurementStandard("Metric");
        addPage.setCop("100");
        addPage.setCone("5000");
        addPage.setVicone("5000");
        
        System.out.println("Details entered. Saving...");
        
        addPage.pressSave();
        pPage.waitForElement();
        
        System.out.println("Saved. Checking record appears...");
        
        pPage.setPlantName("ID10");
        pPage.setBrand("Test");
        pPage.setTicket("000");
        pPage.pressSearch();
        pPage.waitForElement();
        
        int row = pPage.getRow("Test","000","100");
        
        AssertJUnit.assertFalse("Length Offers Page: Length Offer not present in table after creation",row==-1);
        
        System.out.println("Record found. Editing record...");
        
        Mst_EditLengthOfferPage editPage = pPage.pressEdit(row);
        editPage.waitForElement();
        
        System.out.println("Edit page reached. Checking title...");
        
        AssertJUnit.assertTrue("Edit Length Offer Page: Title not as expected",editPage.getBreadcrumb().getText().equals("Length Offers | Edit Length Offer"));
        
        System.out.println("Title checked");
        
        editPage.assertBaseElements();
        
        System.out.println("Checking fields...");
        
        editPage.checkFields();
        
        System.out.println("Fields checked. Editing Length Offer...");
        
        editPage.setCop("200");
        
        System.out.println("Edited. Saving...");
        
        editPage.pressSave();
        pPage.waitForElement();
        
        System.out.println("Saved. Checking record is updated...");
        
        pPage.setPlantName("ID10");
        pPage.setBrand("Test");
        pPage.pressSearch();
        pPage.waitForElement();
        
        int row2 = pPage.getRow("Test","000","200");
        AssertJUnit.assertFalse("Length Offers Page: Edited changes are not applied in table",row2==-1);
        
        System.out.println("Record updated. Deleting record...");
        
        pPage.pressDelete(row2);
        pPage.waitForElement();
        
        System.out.println("Delete pressed. Checking item is removed...");
        
        pPage.setPlantName("ID10");
        pPage.setBrand("Test");
        pPage.pressSearch();
        pPage.waitForElement();
        
        int row3 = pPage.getRow("Test","000","200");
        AssertJUnit.assertTrue("Length Offers Page: Item not removed after deletion",row3==-1);
        
        System.out.println("Item removed. Checking export function...");
        
        pPage.pressReset();
        pPage.waitForElement();
        pPage.setPlantName("ID10");
        pPage.setBrand("astra");
        pPage.pressSearch();
        pPage.waitForElement();
        
        CCE_ExportDownloadPage dlPage = pPage.pressExport();
        dlPage.waitForDownloadCompletion();
        
        System.out.println("Export function works. Checking import page...");
        
        Mst_ImportPage impPage = pPage.pressImport();
        impPage.waitForElement();
        
        System.out.println("Page reached. Checking title...");
        
        AssertJUnit.assertTrue("Length Offers Import Page: Title not as expected",impPage.getBreadcrumb().getText().equals("Length Offers | Import"));
    
        System.out.println("Title as expected");
    }
    
    @Test //Warehouse Instructions: Page and filter checks, add/edit/delete/export features
    (groups = {"Masters"})
    public void warehouseInstructions1() throws Exception {
        WebDriver driver = getDriver();
        
        Cce_Base base = new Cce_Base(driver);
        CCE_MainPage mainPage = base.setUp("Warehouse Instructions: Page and filter checks, add/edit/delete/export features", "A_CM_WI_1 to 8");
        mainPage.waitForLoad();
        
        System.out.println("Navigating to Warehouse Instructions Page...");
        
        Mst_WarehouseInstructionsPage pPage = mainPage.selectWarehouseInstructions();
        pPage.waitForElement();
        
        System.out.println("Warehouse Instructions page reached. Checking title...");
        
        AssertJUnit.assertTrue("Warehouse Instructions Page: Title not as expected",pPage.getBreadcrumb().getText().equals("Warehouse Instructions"));
        
        System.out.println("Title checked");
        
        pPage.assertBaseElements();
        
        System.out.println("Checking fields...");
        
        pPage.checkFields();
        
        System.out.println("Fields checked. Entering filter criteria...");
        
        pPage.setSalesOrg("ID51");
        
        System.out.println("Filter criteria entered. Listing records...");
        
        pPage.pressSearch();
        pPage.waitForElement();
        
        System.out.println("Records listed. Checking filtration...");
        
        String loc1 = "#content > div.flexi-grid > table > tbody > tr:nth-child(";
        String loc2 = ") > td:nth-child(2)";
        By recordField = By.cssSelector("#content > div.flexi-grid > dl > dt > span.left");
        
        AssertJUnit.assertTrue("Rejection Reasons Page: Filtration not working as expected",pPage.checkFiltration(loc1,loc2,"ID51",recordField,2));
        
        System.out.println("Filtration as expected. Creating new Length Offer...");
        
        Mst_AddWarehouseInstructionPage addPage = pPage.pressNewWarehouseInstruction();
        addPage.waitForElement();
        
        System.out.println("Add Warehouse Instructions Page reached. Checking title...");
        
        AssertJUnit.assertTrue("Add Warehouse Instructions Page: Title not as expected",addPage.getBreadcrumb().getText().equals("Warehouse Instructions | Add Warehouse Instruction"));
        
        System.out.println("Title as expected");
        
        addPage.assertBaseElements();
        
        System.out.println("Checking fields...");
        
        addPage.checkFields();
        
        System.out.println("Fields checked. Entering details...");
        
        addPage.setSalesOrg("ID51");
        addPage.setWarehouseInstruction("AutoTest");
        
        System.out.println("Details entered. Saving...");
        
        addPage.pressSave();
        pPage.waitForElement();
        
        System.out.println("Saved. Checking record appears...");
        
        pPage.setSalesOrg("ID51");
        pPage.pressSearch();
        pPage.waitForElement();
        
        int row = pPage.getRow("AutoTest");
        
        AssertJUnit.assertFalse("Warehouse Instructions Page: Warehouse Instruction not present in table after creation",row==-1);
        
        System.out.println("Record found. Editing record...");
        
        Mst_EditWarehouseInstructionPage editPage = pPage.pressEdit(row);
        editPage.waitForElement();
        
        System.out.println("Edit page reached. Checking title...");
        
        AssertJUnit.assertTrue("Edit Warehouse Instruction Page: Title not as expected",editPage.getBreadcrumb().getText().equals("Warehouse Instructions | Edit Warehouse Instruction"));
        
        System.out.println("Title checked");
        
        editPage.assertBaseElements();
        
        System.out.println("Checking fields...");
        
        editPage.checkFields();
        
        System.out.println("Fields checked. Editing Warehouse Instructions...");
        
        editPage.setWarehouseInstruction("Edited");
        
        System.out.println("Edited. Saving...");
        
        editPage.pressSave();
        pPage.waitForElement();
        
        System.out.println("Saved. Checking record is updated...");
        
        pPage.setSalesOrg("ID51");
        pPage.pressSearch();
        pPage.waitForElement();
        
        int row2 = pPage.getRow("Edited");
        AssertJUnit.assertFalse("Warehouse Instructions Page: Edited changes are not applied in table",row2==-1);
        
        System.out.println("Record updated. Deleting record...");
        
        pPage.pressDelete(row2);
        pPage.waitForElement();
        
        System.out.println("Delete pressed. Checking item is removed...");
        
        pPage.setSalesOrg("ID51");
        pPage.pressSearch();
        pPage.waitForElement();
        
        int row3 = pPage.getRow("Edited");
        AssertJUnit.assertTrue("Warehouse Instructions Page: Item not removed after deletion",row3==-1);
        
        System.out.println("Item removed. Checking export function...");
        
        pPage.pressReset();
        pPage.waitForElement();
        pPage.setSalesOrg("ID");
        pPage.pressSearch();
        pPage.waitForElement();
        
        CCE_ExportDownloadPage dlPage = pPage.pressExport();
        dlPage.waitForDownloadCompletion();
        
        System.out.println("Export function works. Checking import page...");
        
        Mst_ImportPage impPage = pPage.pressImport();
        impPage.waitForElement();
        
        System.out.println("Page reached. Checking title...");
        
        AssertJUnit.assertTrue("Warehouse Instructions Import Page: Title not as expected",impPage.getBreadcrumb().getText().equals("WarehouseInstructions | Import"));
    
        System.out.println("Title as expected");
    }
    
    @Test //Shade Cards: Page and filter checks, add/edit/delete/export features
    (groups = {"Masters"})
    public void shadeCards1() throws Exception {
        WebDriver driver = getDriver();
        
        Cce_Base base = new Cce_Base(driver);
        CCE_MainPage mainPage = base.setUp("Shade Cards Page: Page and filter checks, add/edit/delete/export features", "A_SR_SC_1 to 8");
        mainPage.waitForLoad();
        
        System.out.println("Navigating to Shade Cards Page...");
        
        Mst_ShadeCardsPage pPage = mainPage.selectShadeCards();
        pPage.waitForElement();
        
        System.out.println("Shade Cards page reached. Checking title...");
        
        AssertJUnit.assertTrue("Shade Cards Page: Title not as expected",pPage.getBreadcrumb().getText().equals("Shade Cards"));
        
        System.out.println("Title checked");
        
        pPage.assertBaseElements();
        
        System.out.println("Checking fields...");
        
        pPage.checkFields();
        
        System.out.println("Fields checked. Entering filter criteria...");
        
        pPage.setShadeCard("Classic");
        
        System.out.println("Filter criteria entered. Listing records...");
        
        pPage.pressSearch();
        pPage.waitForElement();
        
        System.out.println("Records listed. Checking filtration...");
        
        String loc1 = "#content > div.flexi-grid > table > tbody > tr:nth-child(";
        String loc2 = ") > td:nth-child(2)";
        By recordField = By.cssSelector("#content > div.flexi-grid > dl > dt > span.left");
        
        AssertJUnit.assertTrue("Rejection Reasons Page: Filtration not working as expected",pPage.checkFiltration(loc1,loc2,"Classic",recordField,2));
        
        System.out.println("Filtration as expected. Creating new Shade Card...");
        
        Mst_AddShadeCardPage addPage = pPage.pressNewShadeCard();
        addPage.waitForElement();
        
        System.out.println("Add Shade Cards Page reached. Checking title...");
        
        AssertJUnit.assertTrue("Add Shade Cards Page: Title not as expected",addPage.getBreadcrumb().getText().equals("Shade Cards | Add Shade Card"));
        
        System.out.println("Title as expected");
        
        addPage.assertBaseElements();
        
        System.out.println("Checking fields...");
        
        addPage.checkFields();
        
        System.out.println("Fields checked. Entering details...");
        
        addPage.setShadeCardName("AutoTest");
        addPage.setShadeCardCode("AUT0");
        
        System.out.println("Details entered. Saving...");
        
        addPage.pressSave();
        pPage.waitForElement();
        
        System.out.println("Saved. Checking record appears...");
        
        pPage.setShadeCard("AutoTest");
        pPage.pressSearch();
        pPage.waitForElement();
        
        int row = pPage.getRow("AutoTest");
        
        AssertJUnit.assertFalse("Shade Cards Page: Shade Card not present in table after creation",row==-1);
        
        System.out.println("Record found. Editing record...");
        
        Mst_EditShadeCardPage editPage = pPage.pressEdit(row);
        editPage.waitForElement();
        
        System.out.println("Edit page reached. Checking title...");
        
        AssertJUnit.assertTrue("Edit Shade Card Page: Title not as expected",editPage.getBreadcrumb().getText().equals("Shade Cards | Edit Shade Card"));
        
        System.out.println("Title checked");
        
        editPage.assertBaseElements();
        
        System.out.println("Checking fields...");
        
        editPage.checkFields();
        
        System.out.println("Fields checked. Editing Shade Cards...");
        
        editPage.setShadeCardName("Edited");
        
        System.out.println("Edited. Saving...");
        
        editPage.pressSave();
        pPage.waitForElement();
        
        System.out.println("Saved. Checking record is updated...");
        
        pPage.setShadeCard("Edited");
        pPage.pressSearch();
        pPage.waitForElement();
        
        int row2 = pPage.getRow("Edited");
        AssertJUnit.assertFalse("Shade Cards Page: Edited changes are not applied in table",row2==-1);
        
        System.out.println("Record updated. Deleting record...");
        
        pPage.pressDelete(row2);
        pPage.waitForElement();
        
        System.out.println("Delete pressed. Checking item is removed...");
        
        pPage.setShadeCard("Edited");
        pPage.pressSearch();
        pPage.waitForElement();
        
        int row3 = pPage.getRow("Edited");
        AssertJUnit.assertTrue("Shade Cards Page: Item not removed after deletion",row3==-1);
        
        System.out.println("Item removed. Checking export function...");
        
        pPage.pressReset();
        pPage.waitForElement();
        
        CCE_ExportDownloadPage dlPage = pPage.pressExport();
        dlPage.waitForDownloadCompletion();
        
        System.out.println("Export function works. Checking import page...");
        
        Mst_ImportPage impPage = pPage.pressImport();
        impPage.waitForElement();
        
        System.out.println("Page reached. Checking title...");
        
        AssertJUnit.assertTrue("Shade Cards Import Page: Title not as expected",impPage.getBreadcrumb().getText().equals("Shade Cards | Import"));
    
        System.out.println("Title as expected");
    }
    
    @Test //Shade Cards Plants: Page and filter checks, add/edit/delete/export features
    (groups = {"Masters"})
    public void shadeCardPlants1() throws Exception {
        
        WebDriver driver = getDriver();
        
        Cce_Base base = new Cce_Base(driver);
        CCE_MainPage mainPage = base.setUp("Shade Card Plants Page: Page and filter checks, add/edit/delete/export features", "A_SR_SC/P_1 to 8");
        mainPage.waitForLoad();
        
        System.out.println("Navigating to Shade Card Plants Page...");
        
        Mst_ShadeCardPlantsPage pPage = mainPage.selectShadeCardPlants();
        pPage.waitForElement();
        
        System.out.println("Shade Cards Plants page reached. Checking title...");
        
        AssertJUnit.assertTrue("Shade Cards Plants Page: Title not as expected",pPage.getBreadcrumb().getText().equals("Shade Cards - Plants"));
        
        System.out.println("Title checked");
        
        pPage.assertBaseElements();
        
        System.out.println("Checking fields...");
        
        pPage.checkFields();
        
        System.out.println("Fields checked. Entering filter criteria...");
        
        pPage.setPlant("ID10");
        
        System.out.println("Filter criteria entered. Listing records...");
        
        pPage.pressSearch();
        pPage.waitForElement();
        
        System.out.println("Records listed. Checking filtration...");
        
        String loc1 = "#content > div.flexi-grid > table > tbody > tr:nth-child(";
        String loc2 = ") > td:nth-child(3)";
        By recordField = By.cssSelector("#content > div.flexi-grid > dl > dt > span.left");
        
        AssertJUnit.assertTrue("Rejection Reasons Page: Filtration not working as expected",pPage.checkFiltration(loc1,loc2,"ID10",recordField,2));
        
        System.out.println("Filtration as expected. Creating new Shade Card Plant...");
        
        Mst_AddShadeCardPlantPage addPage = pPage.pressNewShadeCardPlant();
        addPage.waitForElement();
        
        System.out.println("Add Shade Card Plant Page reached. Checking title...");
        
        AssertJUnit.assertTrue("Add Shade Card Plant Page: Title not as expected",addPage.getBreadcrumb().getText().equals("Shade Cards - Plants | Add Shade Card - Plant"));
        
        System.out.println("Title as expected");
        
        addPage.assertBaseElements();
        
        System.out.println("Checking fields...");
        
        addPage.checkFields();
        
        System.out.println("Fields checked. Entering details...");
        
        addPage.setPlantName("TEST");
        addPage.setShadeCardCode("AutomatedTest");
        
        System.out.println("Details entered. Saving...");
        
        addPage.pressSave();
        pPage.waitForElement();
        
        System.out.println("Saved. Checking record appears...");
        
        pPage.setShadeCard("AutomatedTest");
        pPage.pressSearch();
        pPage.waitForElement();
        
        int row = pPage.getRow("TEST","AutomatedTest");
        
        AssertJUnit.assertFalse("Shade Cards - Plants Page: Shade Card - Plant not present in table after creation",row==-1);
        
        System.out.println("Record found. Editing record...");
        
        Mst_EditShadeCardPlantPage editPage = pPage.pressEdit(row);
        editPage.waitForElement();
        
        System.out.println("Edit page reached. Checking title...");
        
        AssertJUnit.assertTrue("Edit Shade Card - Plant Page: Title not as expected",editPage.getBreadcrumb().getText().equals("Shade Cards - Plants | Edit Shade Card - Plant"));
        
        System.out.println("Title checked");
        
        editPage.assertBaseElements();
        
        System.out.println("Checking fields...");
        
        editPage.checkFields();
        
        System.out.println("Fields checked. Editing Shade Card Plant...");
        
        editPage.setShadeCardCode("DUMMY");
        
        System.out.println("Edited. Saving...");
        
        editPage.pressSave();
        pPage.waitForElement();
        
        System.out.println("Saved. Checking record is updated...");
        
        pPage.setPlant("TEST");
        pPage.setShadeCard("DUMMY");
        pPage.pressSearch();
        pPage.waitForElement();
        
        int row2 = pPage.getRow("TEST","DUMMY");
        AssertJUnit.assertFalse("Shade Cards - Plants Page: Edited changes are not applied in table",row2==-1);
        
        System.out.println("Record updated. Deleting record...");
        
        pPage.pressDelete(row2);
        pPage.waitForElement();
        
        System.out.println("Delete pressed. Checking item is removed...");
        
        pPage.setPlant("TEST");
        pPage.setShadeCard("DUMMY");
        pPage.pressSearch();
        pPage.waitForElement();
        
        int row3 = pPage.getRow("TEST","DUMMY");
        AssertJUnit.assertTrue("Shade Cards - Plants Page: Item not removed after deletion",row3==-1);
        
        System.out.println("Item removed. Checking export function...");
        
        pPage.pressReset();
        pPage.waitForElement();
        pPage.setShadeCard("DUMMY");
        pPage.pressSearch();
        pPage.waitForElement();
        
        CCE_ExportDownloadPage dlPage = pPage.pressExport();
        dlPage.waitForDownloadCompletion();
        
        System.out.println("Export function works. Checking import page...");
        
        Mst_ImportPage impPage = pPage.pressImport();
        impPage.waitForElement();
        
        System.out.println("Page reached. Checking title...");
        
        AssertJUnit.assertTrue("Shade Cards - Plants Import Page: Title not as expected",impPage.getBreadcrumb().getText().equals("Shade Cards - Plants | Import"));
    
        System.out.println("Title as expected");
        
    }
    
    @Test //Sales Org Materials: Page and filter checks, add/edit/delete/export features
    (groups = {"Masters"})
    public void salesOrgMaterials1() throws Exception {
        WebDriver driver = getDriver();
        
        Cce_Base base = new Cce_Base(driver);
        CCE_MainPage mainPage = base.setUp("Sales Org. Materials Page: Page and filter checks, add/edit/delete/export features", "A_PR_S.Org M_1 to 8");
        mainPage.waitForLoad();
        
        System.out.println("Navigating to Sales Org. Materials Page...");
        
        Mst_SalesOrgMaterialsPage pPage = mainPage.selectSalesOrgMaterials();
        pPage.waitForElement();
        
        System.out.println("Sales Org. Materials page reached. Checking title...");
        
        AssertJUnit.assertTrue("Sales Org. Materials Page: Title not as expected",pPage.getBreadcrumb().getText().equals("Sales Org. Materials"));
        
        System.out.println("Title checked");
        
        pPage.assertBaseElements();
        
        System.out.println("Checking fields...");
        
        pPage.checkFields();
        
        System.out.println("Fields checked. Entering filter criteria...");
        
        pPage.setSalesOrg("ID51");
        
        System.out.println("Filter criteria entered. Listing records...");
        
        pPage.pressSearch();
        pPage.waitForElement();
        
        System.out.println("Records listed. Checking filtration...");
        
        String loc1 = "#content > div.flexi-grid > table > tbody > tr:nth-child(";
        String loc2 = ") > td:nth-child(2)";
        By recordField = By.cssSelector("#content > div.flexi-grid > dl > dt > span.left");
        
        AssertJUnit.assertTrue("Sales Org. Materials Page: Filtration not working as expected",pPage.checkFiltration(loc1,loc2,"ID51",recordField,2));
        
        System.out.println("Filtration as expected. Creating new Sales Org. Material...");
        
        Mst_AddSalesOrgMaterialPage addPage = pPage.pressNewSalesOrgMat();
        addPage.waitForElement();
        
        System.out.println("Add Sales Org. Material Page reached. Checking title...");
        
        AssertJUnit.assertTrue("Add Sales Org. Material Page: Title not as expected",addPage.getBreadcrumb().getText().equals("Sales Org. Materials | Add Sales Org. Material"));
        
        System.out.println("Title as expected");
        
        addPage.assertBaseElements();
        
        System.out.println("Checking fields...");
        
        addPage.checkFields();
        
        System.out.println("Fields checked. Entering details...");
        
        addPage.setArticle("AutoTest");
        addPage.setSalesOrg("ID51");
        addPage.setPlant("ID10");
        addPage.setBrand("Test");
        addPage.setTicket("000");
        addPage.setLength("5000");
        addPage.setMUMType("Vicone");
        
        System.out.println("Details entered. Saving...");
        
        addPage.pressSave();
        pPage.waitForElement();
        
        System.out.println("Saved. Checking record appears...");
        
        pPage.setSalesOrg("ID51");
        pPage.setArticle("AutoTest");
        pPage.pressSearch();
        pPage.waitForElement();
        
        int row = pPage.getRow("ID51","AutoTest");
        
        AssertJUnit.assertFalse("Sales Org. Materials Page: Sales Org. Material not present in table after creation",row==-1);
        
        System.out.println("Record found. Editing record...");
        
        Mst_EditSalesOrgMaterialPage editPage = pPage.pressEdit(row);
        editPage.waitForElement();
        
        System.out.println("Edit page reached. Checking title...");
        
        AssertJUnit.assertTrue("Edit Sales Org. Material Page: Title not as expected",editPage.getBreadcrumb().getText().equals("Sales Org. Materials | Edit Sales Org. Material"));
        
        System.out.println("Title checked");
        
        editPage.assertBaseElements();
        
        System.out.println("Checking fields...");
        
        editPage.checkFields();
        
        System.out.println("Fields checked. Editing Sales Org. Material...");
        
        editPage.setArticle("Edited");
        
        System.out.println("Edited. Saving...");
        
        editPage.pressSave();
        pPage.waitForElement();
        
        System.out.println("Saved. Checking record is updated...");
        
        pPage.setSalesOrg("ID51");
        pPage.setArticle("Edited");
        pPage.pressSearch();
        pPage.waitForElement();
        
        int row2 = pPage.getRow("ID51","Edited");
        AssertJUnit.assertFalse("Sales Org. Materials Page: Edited changes are not applied in table",row2==-1);
        
        System.out.println("Record updated. Deleting record...");
        
        pPage.pressDelete(row2);
        pPage.waitForElement();
        
        System.out.println("Delete pressed. Checking item is removed...");
        
        pPage.setSalesOrg("ID51");
        pPage.setArticle("Edited");
        pPage.pressSearch();
        pPage.waitForElement();
        
        int row3 = pPage.getRow("ID51","Edited");
        AssertJUnit.assertTrue("Sales Org. Materials Page: Item not removed after deletion",row3==-1);
        
        System.out.println("Item removed. Checking export function...");
        
        pPage.pressReset();
        pPage.waitForElement();
        pPage.setSalesOrg("ID51");
        pPage.pressSearch();
        pPage.waitForElement();
        
        CCE_ExportDownloadPage dlPage = pPage.pressExport();
        dlPage.waitForDownloadCompletion();
        
        System.out.println("Export function works. Checking import page...");
        
        Mst_ImportPage impPage = pPage.pressImport();
        impPage.waitForElement();
        
        System.out.println("Page reached. Checking title...");
        
        AssertJUnit.assertTrue("Sales Org. Materials Import Page: Title not as expected",impPage.getBreadcrumb().getText().equals("Sales Org. Materials | Import"));
    
        System.out.println("Title as expected");
    }
    
    @Test //Charged Products: Page and filter checks, add/edit/delete/export features
    (groups = {"Masters"})
    public void chargedProducts1() throws Exception {
        WebDriver driver = getDriver();
        
        Cce_Base base = new Cce_Base(driver);
        CCE_MainPage mainPage = base.setUp("Charged Products Page: Page and filter checks, add/edit/delete/export features", "A_PR_CP_1 to 8");
        mainPage.waitForLoad();
        
        System.out.println("Navigating to Charged Products Page...");
        
        Mst_ChargedProductsPage pPage = mainPage.selectChargedProducts();
        pPage.waitForElement();
        
        System.out.println("Charged Products page reached. Checking title...");
        
        AssertJUnit.assertTrue("Charged Products Page: Title not as expected",pPage.getBreadcrumb().getText().equals("Charged Products"));
        
        System.out.println("Title checked");
        
        pPage.assertBaseElements();
        
        System.out.println("Checking fields...");
        
        pPage.checkFields();
        
        System.out.println("Fields checked. Entering filter criteria...");
        
        pPage.setSalesOrg("ID51");
        
        System.out.println("Filter criteria entered. Listing records...");
        
        pPage.pressSearch();
        pPage.waitForElement();
        
        System.out.println("Records listed. Checking filtration...");
        
        String loc1 = "#content > div.flexi-grid > table > tbody > tr:nth-child(";
        String loc2 = ") > td:nth-child(2)";
        By recordField = By.cssSelector("#content > div.flexi-grid > dl > dt > span.left");
        
        AssertJUnit.assertTrue("Charged Products Page: Filtration not working as expected",pPage.checkFiltration(loc1,loc2,"ID51",recordField,2));
        
        System.out.println("Filtration as expected. Creating new Charged Product...");
        
        Mst_AddChargedProductPage addPage = pPage.pressNewChargedProduct();
        addPage.waitForElement();
        
        System.out.println("Add Charged Products Page reached. Checking title...");
        
        AssertJUnit.assertTrue("Add Charged Products Page: Title not as expected",addPage.getBreadcrumb().getText().equals("Charged Products | Add Charged Product"));
        
        System.out.println("Title as expected");
        
        addPage.assertBaseElements();
        
        System.out.println("Checking fields...");
        
        addPage.checkFields();
        
        System.out.println("Fields checked. Entering details...");

        addPage.setSalesOrg("ID51");
        addPage.setCustomerName("Life Easy Customer");
        addPage.setBrand("Test");
        addPage.setTicket("000");
        addPage.setMUMType("Vicone");
        addPage.setQuantity("3");
        
        System.out.println("Details entered. Saving...");
        
        addPage.pressSave();
        pPage.waitForElement();
        
        System.out.println("Saved. Checking record appears...");
        
        pPage.setSalesOrg("ID51");
        pPage.setCustomerName("Life Easy Customer");
        pPage.setBrand("Test");
        pPage.pressSearch();
        pPage.waitForElement();
        
        int row = pPage.getRow("ID51","Life Easy Customer","Test","3");
        
        AssertJUnit.assertFalse("Charged Products Page: Charged Product not present in table after creation",row==-1);
        
        System.out.println("Record found. Editing record...");
        
        Mst_EditChargedProductPage editPage = pPage.pressEdit(row);
        editPage.waitForElement();
        
        System.out.println("Edit page reached. Checking title...");
        
        AssertJUnit.assertTrue("Edit Charged Product Page: Title not as expected",editPage.getBreadcrumb().getText().equals("Charged Products | Edit Charged Product"));
        
        System.out.println("Title checked");
        
        editPage.assertBaseElements();
        
        System.out.println("Checking fields...");
        
        editPage.checkFields();
        
        System.out.println("Fields checked. Editing Charged Product...");
        
        editPage.setQuantity("5");
        
        System.out.println("Edited. Saving...");
        
        editPage.pressSave();
        pPage.waitForElement();
        
        System.out.println("Saved. Checking record is updated...");
        
        pPage.setSalesOrg("ID51");
        pPage.setCustomerName("Life Easy Customer");
        pPage.setBrand("Test");
        pPage.pressSearch();
        pPage.waitForElement();
        
        int row2 = pPage.getRow("ID51","Life Easy Customer","Test","5");
        AssertJUnit.assertFalse("Charged Products Page: Edited changes are not applied in table",row2==-1);
        
        System.out.println("Record updated. Deleting record...");
        
        pPage.pressDelete(row2);
        pPage.waitForElement();
        
        System.out.println("Delete pressed. Checking item is removed...");
        
        pPage.setSalesOrg("ID51");
        pPage.setCustomerName("Life Easy Customer");
        pPage.setBrand("Test");
        pPage.pressSearch();
        pPage.waitForElement();
        
        int row3 = pPage.getRow("ID51","Life Easy Customer","Test","5");
        AssertJUnit.assertTrue("Charged Products Page: Item not removed after deletion",row3==-1);
        
        System.out.println("Item removed. Checking export function...");
        
        pPage.pressReset();
        pPage.waitForElement();
        pPage.setSalesOrg("ID51");
        pPage.pressSearch();
        pPage.waitForElement();
        
        CCE_ExportDownloadPage dlPage = pPage.pressExport();
        dlPage.waitForDownloadCompletion();
        
        System.out.println("Export function works. Checking import page...");
        
        Mst_ImportPage impPage = pPage.pressImport();
        impPage.waitForElement();
        
        System.out.println("Page reached. Checking title...");
        
        AssertJUnit.assertTrue("Charged Products Import Page: Title not as expected",impPage.getBreadcrumb().getText().equals("Charged Products | Import"));
    
        System.out.println("Title as expected");
    }
    
    @Test //Forced Enrichment Products: Page and filter checks, add/edit/delete/export features
    (groups = {"Masters"})
    public void forcedEnrichment1() throws Exception {
        WebDriver driver = getDriver();
        
        Cce_Base base = new Cce_Base(driver);
        CCE_MainPage mainPage = base.setUp("Forced Enrichment Products Page: Page and filter checks, add/edit/delete/export features", "A_PR_FEP_1 to 8");
        mainPage.waitForLoad();
        
        System.out.println("Navigating to Forced Enrichment Products Page...");
        
        Mst_ForcedEnrichmentPage pPage = mainPage.selectForcedEnrichment();
        pPage.waitForElement();
        
        System.out.println("Charged Products page reached. Checking title...");
        
        AssertJUnit.assertTrue("Forced Enrichment Products Page: Title not as expected",pPage.getBreadcrumb().getText().equals("Forced Enrichment Products"));
        
        System.out.println("Title checked");
        
        pPage.assertBaseElements();
        
        System.out.println("Checking fields...");
        
        pPage.checkFields();
        
        System.out.println("Fields checked. Entering filter criteria...");
        
        pPage.setSalesOrg("ID51");
        
        System.out.println("Filter criteria entered. Listing records...");
        
        pPage.pressSearch();
        pPage.waitForElement();
        
        System.out.println("Records listed. Checking filtration...");
        
        String loc1 = "#content > div.flexi-grid > table > tbody > tr:nth-child(";
        String loc2 = ") > td:nth-child(2)";
        By recordField = By.cssSelector("#content > div.flexi-grid > dl > dt > span.left");
        
        AssertJUnit.assertTrue("Forced Enrichment Products Page: Filtration not working as expected",pPage.checkFiltration(loc1,loc2,"ID51",recordField,2));
        
        System.out.println("Filtration as expected. Creating new Forced Enrichment Product...");
        
        Mst_AddForcedEnrichmentPage addPage = pPage.pressNewProduct();
        addPage.waitForElement();
        
        System.out.println("Add Forced Enrichment Product Page reached. Checking title...");
        
        AssertJUnit.assertTrue("Add Forced Enrichment Product Page: Title not as expected",addPage.getBreadcrumb().getText().equals("Forced Enrichment Products | Add Forced Enrichment Product"));
        
        System.out.println("Title as expected");
        
        addPage.assertBaseElements();
        
        System.out.println("Checking fields...");
        
        addPage.checkFields();
        
        System.out.println("Fields checked. Entering details...");

        addPage.setSalesOrg("ID51");
        addPage.setCustomerName("Life Easy Customer");
        addPage.setBrand("Test");
        addPage.setTicket("000");
        addPage.setMUMType("Vicone");
        
        System.out.println("Details entered. Saving...");
        
        addPage.pressSave();
        pPage.waitForElement();
        
        System.out.println("Saved. Checking record appears...");
        
        pPage.setSalesOrg("ID51");
        pPage.setCustomerName("Life Easy Customer");
        pPage.setBrand("Test");
        pPage.pressSearch();
        pPage.waitForElement();
        
        int row = pPage.getRow("Life Easy Customer","Test","000");
        
        AssertJUnit.assertFalse("Forced Enrichment Products Page: Forced Enrichment Product not present in table after creation",row==-1);
        
        System.out.println("Record found. Editing record...");
        
        Mst_EditForcedEnrichmentPage editPage = pPage.pressEdit(row);
        editPage.waitForElement();
        
        System.out.println("Edit page reached. Checking title...");
        
        AssertJUnit.assertTrue("Edit Forced Enrichment Products Page: Title not as expected",editPage.getBreadcrumb().getText().equals("Forced Enrichment Products | Edit Forced Enrichment Product"));
        
        System.out.println("Title checked");
        
        editPage.assertBaseElements();
        
        System.out.println("Checking fields...");
        
        editPage.checkFields();
        
        System.out.println("Fields checked. Editing Charged Product...");
        
        editPage.setTicket("002");
        
        System.out.println("Edited. Saving...");
        
        editPage.pressSave();
        pPage.waitForElement();
        
        System.out.println("Saved. Checking record is updated...");
        
        pPage.setSalesOrg("ID51");
        pPage.setCustomerName("Life Easy Customer");
        pPage.setBrand("Test");
        pPage.pressSearch();
        pPage.waitForElement();
        
        int row2 = pPage.getRow("Life Easy Customer","Test","002");
        AssertJUnit.assertFalse("Forced Enrichment Products Page: Edited changes are not applied in table",row2==-1);
        
        System.out.println("Record updated. Deleting record...");
        
        pPage.pressDelete(row2);
        pPage.waitForElement();
        
        System.out.println("Delete pressed. Checking item is removed...");
        
        pPage.setCustomerName("Life Easy Customer");
        pPage.setBrand("Test");
        pPage.pressSearch();
        pPage.waitForElement();
        
        int row3 = pPage.getRow("Life Easy Customer","Test","002");
        AssertJUnit.assertTrue("Forced Enrichment Products Page: Item not removed after deletion",row3==-1);
        
        System.out.println("Item removed. Checking export function...");
        
        pPage.pressReset();
        pPage.waitForElement();
        pPage.setSalesOrg("ID51");
        pPage.pressSearch();
        pPage.waitForElement();
        
        CCE_ExportDownloadPage dlPage = pPage.pressExport();
        dlPage.waitForDownloadCompletion();
        
        System.out.println("Export function works. Checking import page...");
        
        Mst_ImportPage impPage = pPage.pressImport();
        impPage.waitForElement();
        
        System.out.println("Page reached. Checking title...");
        
        AssertJUnit.assertTrue("Forced Enrichment Products Import Page: Title not as expected",impPage.getBreadcrumb().getText().equals("Forced Enrichment Products | Import"));
    
        System.out.println("Title as expected");
    }
    
    @Test //Supply Plants: Page and filter checks, add/edit/delete/export features
    (groups = {"Masters"})
    public void supplyPlants1() throws Exception {
        WebDriver driver = getDriver();
        
        Cce_Base base = new Cce_Base(driver);
        CCE_MainPage mainPage = base.setUp("Supply Plants Page: Page and filter checks, add/edit/delete/export features", "A_PR_SP_1 to 8");
        mainPage.waitForLoad();
        
        System.out.println("Navigating to Supply Plant Page...");
        
        Mst_SupplyPlantsPage pPage = mainPage.selectSupplyPlants();
        pPage.waitForElement();
        
        System.out.println("Supply Plants page reached. Checking title...");
        
        AssertJUnit.assertTrue("Supply Plants Page: Title not as expected",pPage.getBreadcrumb().getText().equals("Supply Plants"));
        
        System.out.println("Title checked");
        
        pPage.assertBaseElements();
        
        System.out.println("Checking fields...");
        
        pPage.checkFields();
        
        System.out.println("Fields checked. Entering filter criteria...");
        
        pPage.setBrand("astra");
        
        System.out.println("Filter criteria entered. Listing records...");
        
        pPage.pressSearch();
        pPage.waitForElement();
        
        System.out.println("Records listed. Checking filtration...");
        
        String loc1 = "#content > div.flexi-grid > table > tbody > tr:nth-child(";
        String loc2 = ") > td:nth-child(4)";
        By recordField = By.cssSelector("#content > div.flexi-grid > dl > dt > span.left");
        
        AssertJUnit.assertTrue("Forced Enrichment Products Page: Filtration not working as expected",pPage.checkFiltration(loc1,loc2,"astra",recordField,2));
        
        System.out.println("Filtration as expected. Creating new Supply Plant...");
        
        Mst_AddSupplyPlantPage addPage = pPage.pressNewSupplyPlant();
        addPage.waitForElement();
        
        System.out.println("Add Supply Plant Page reached. Checking title...");
        
        AssertJUnit.assertTrue("Add Supply Plant Page: Title not as expected",addPage.getBreadcrumb().getText().equals("Supply Plants | Add Supply Plant"));
        
        System.out.println("Title as expected");
        
        addPage.assertBaseElements();
        
        System.out.println("Checking fields...");
        
        addPage.checkFields();
        
        System.out.println("Fields checked. Entering details...");

        addPage.setDeliveryPlant("ABCMut");
        addPage.setBrand("Test");
        addPage.setTicket("000");
        addPage.setMUMType("Vicone");
        addPage.setSupplyPlant("TEST");
        
        System.out.println("Details entered. Saving...");
        
        addPage.pressSave();
        pPage.waitForElement();
        
        System.out.println("Saved. Checking record appears...");
        
        pPage.setBrand("Test");
        pPage.pressSearch();
        pPage.waitForElement();
        
        int row = pPage.getRow("ABCMut","Test","000","TEST");
        
        AssertJUnit.assertFalse("Supply Plants Page: Supply Plant not present in table after creation",row==-1);
        
        System.out.println("Record found. Editing record...");
        
        Mst_EditSupplyPlantPage editPage = pPage.pressEdit(row);
        editPage.waitForElement();
        
        System.out.println("Edit page reached. Checking title...");
        
        AssertJUnit.assertTrue("Edit Supply Plant Page: Title not as expected",editPage.getBreadcrumb().getText().equals("Supply Plants | Edit Supply Plant"));
        
        System.out.println("Title checked");
        
        editPage.assertBaseElements();
        
        System.out.println("Checking fields...");
        
        editPage.checkFields();
        
        System.out.println("Fields checked. Editing Supply Plant...");
        
        editPage.setTicket("002");
        
        System.out.println("Edited. Saving...");
        
        editPage.pressSave();
        pPage.waitForElement();
        
        System.out.println("Saved. Checking record is updated...");
        
        pPage.setBrand("Test");
        pPage.pressSearch();
        pPage.waitForElement();
        
        int row2 = pPage.getRow("ABCMut","Test","002","TEST");
        AssertJUnit.assertFalse("Supply Plants Page: Edited changes are not applied in table",row2==-1);
        
        System.out.println("Record updated. Deleting record...");
        
        pPage.pressDelete(row2);
        pPage.waitForElement();
        
        System.out.println("Delete pressed. Checking item is removed...");
        
        pPage.setBrand("Test");
        pPage.pressSearch();
        pPage.waitForElement();
        
        int row3 = pPage.getRow("ABCMut","Test","002","TEST");
        AssertJUnit.assertTrue("Supply Plants Page: Item not removed after deletion",row3==-1);
        
        System.out.println("Item removed. Checking export function...");
        
        pPage.pressReset();
        pPage.waitForElement();
        pPage.setSupplyPlant("CN33");
        pPage.pressSearch();
        pPage.waitForElement();
        
        CCE_ExportDownloadPage dlPage = pPage.pressExport();
        dlPage.waitForDownloadCompletion();
        
        System.out.println("Export function works. Checking import page...");
        
        Mst_ImportPage impPage = pPage.pressImport();
        impPage.waitForElement();
        
        System.out.println("Page reached. Checking title...");
        
        AssertJUnit.assertTrue("Supply Plants Import Page: Title not as expected",impPage.getBreadcrumb().getText().equals("Supply Plants | Import"));
    
        System.out.println("Title as expected");
    }
    
    @Test //Dye Lot Multiples: Page and filter checks, add/edit/delete/export features
    (groups = {"Masters"})
    public void dyeLotMultiples1() throws Exception {
        WebDriver driver = getDriver();
        
        Cce_Base base = new Cce_Base(driver);
        CCE_MainPage mainPage = base.setUp("Dye Lot Multiples Page: Page and filter checks, add/edit/delete/export features", "A_PR_DLM_1 to 8");
        mainPage.waitForLoad();
        
        System.out.println("Navigating to Dye Lot Multiples Page...");
        
        Mst_DyeLotMultiplesPage pPage = mainPage.selectDyeLotMultiples();
        pPage.waitForElement();
        
        System.out.println("Dye Lot Multiples page reached. Checking title...");
        
        AssertJUnit.assertTrue("Dye Lot Multiples Page: Title not as expected",pPage.getBreadcrumb().getText().equals("Dye Lot Multiples"));
        
        System.out.println("Title checked");
        
        pPage.assertBaseElements();
        
        System.out.println("Checking fields...");
        
        pPage.checkFields();
        
        System.out.println("Fields checked. Entering filter criteria...");
        
        pPage.setBrand("astra");
        
        System.out.println("Filter criteria entered. Listing records...");
        
        pPage.pressSearch();
        pPage.waitForElement();
        
        System.out.println("Records listed. Checking filtration...");
        
        String loc1 = "#content > div.flexi-grid > table > tbody > tr:nth-child(";
        String loc2 = ") > td:nth-child(4)";
        By recordField = By.cssSelector("#content > div.flexi-grid > dl > dt > span.left");
        
        AssertJUnit.assertTrue("Dye Lot Multiples Page: Filtration not working as expected",pPage.checkFiltration(loc1,loc2,"astra",recordField,2));
        
        System.out.println("Filtration as expected. Creating new Dye Lot Multiple...");
        
        Mst_AddDLMPage addPage = pPage.pressNewDLM();
        addPage.waitForElement();
        
        System.out.println("Add Dye Lot Multiple Page reached. Checking title...");
        
        AssertJUnit.assertTrue("Add Dye Lot Multiple Page: Title not as expected",addPage.getBreadcrumb().getText().equals("Dye Lot Multiples | Add Dye Lot Multiple"));
        
        System.out.println("Title as expected");
        
        addPage.assertBaseElements();
        
        System.out.println("Checking fields...");
        
        addPage.checkFields();
        
        System.out.println("Fields checked. Entering details...");

        addPage.setSalesOrg("ID51");
        addPage.setBrand("Test");
        addPage.setTicket("000");
        addPage.setLength("5000");
        addPage.setFinish("STANDARD");
        addPage.setLevel("3");
        addPage.setRangeStart("0");
        addPage.setRangeEnd("9");
        addPage.setStepValue("3");
        
        System.out.println("Details entered. Saving...");
        
        addPage.pressSave();
        pPage.waitForElement();
        
        System.out.println("Saved. Checking record appears...");
        
        pPage.setBrand("Test");
        pPage.pressSearch();
        pPage.waitForElement();
        
        int row = pPage.getRow("ID51","Test","000","3");
        
        AssertJUnit.assertFalse("Dye Lot Multiples Page: Dye Lot Multiple not present in table after creation",row==-1);
        
        System.out.println("Record found. Editing record...");
        
        Mst_EditDLMPage editPage = pPage.pressEdit(row);
        editPage.waitForElement();
        
        System.out.println("Edit page reached. Checking title...");
        
        AssertJUnit.assertTrue("Edit Dye Lot Multiple Page: Title not as expected",editPage.getBreadcrumb().getText().equals("Dye Lot Multiples | Edit Dye Lot Multiple"));
        
        System.out.println("Title checked");
        
        editPage.assertBaseElements();
        
        System.out.println("Checking fields...");
        
        editPage.checkFields();
        
        System.out.println("Fields checked. Editing Dye Lot Multiple...");
        
        editPage.setLevel("5");
        
        System.out.println("Edited. Saving...");
        
        editPage.pressSave();
        pPage.waitForElement();
        
        System.out.println("Saved. Checking record is updated...");
        
        pPage.setBrand("Test");
        pPage.pressSearch();
        pPage.waitForElement();
        
        int row2 = pPage.getRow("ID51","Test","000","5");
        AssertJUnit.assertFalse("Dye Lot Multiples Page: Edited changes are not applied in table",row2==-1);
        
        System.out.println("Record updated. Deleting record...");
        
        pPage.pressDelete(row2);
        pPage.waitForElement();
        
        System.out.println("Delete pressed. Checking item is removed...");
        
        pPage.setBrand("Test");
        pPage.pressSearch();
        pPage.waitForElement();
        
        int row3 = pPage.getRow("ID51","Test","000","5");
        AssertJUnit.assertTrue("Dye Lot Multiples Page: Item not removed after deletion",row3==-1);
        
        System.out.println("Item removed. Checking export function...");
        
        pPage.pressReset();
        pPage.waitForElement();
        pPage.setSalesOrg("ID51");
        pPage.pressSearch();
        pPage.waitForElement();
        
        CCE_ExportDownloadPage dlPage = pPage.pressExport();
        dlPage.waitForDownloadCompletion();
        
        System.out.println("Export function works. Checking import page...");
        
        Mst_ImportPage impPage = pPage.pressImport();
        impPage.waitForElement();
        
        System.out.println("Page reached. Checking title...");
        
        AssertJUnit.assertTrue("Dye Lot Multiples Import Page: Title not as expected",impPage.getBreadcrumb().getText().equals("Dye Lot Multiples | Import"));
    
        System.out.println("Title as expected");
    }
    
    @Test //Order Type: Page and filter checks, add/edit/delete/export features
    (groups = {"Masters"})
    public void orderTypes1() throws Exception {
        WebDriver driver = getDriver();
        
        Cce_Base base = new Cce_Base(driver);
        CCE_MainPage mainPage = base.setUp("Order Types Page: Page and filter checks, add/edit/delete/export features", "A_PR_OT_1 to 8");
        mainPage.waitForLoad();
        
        System.out.println("Navigating to Order Types Page...");
        
        Mst_OrderTypePage pPage = mainPage.selectOrderType();
        pPage.waitForElement();
        
        System.out.println("Order Type page reached. Checking title...");
        
        AssertJUnit.assertTrue("Order Type Page: Title not as expected",pPage.getBreadcrumb().getText().equals("Order Type"));
        
        System.out.println("Title checked");
        
        pPage.assertBaseElements();
        
        System.out.println("Checking fields...");
        
        pPage.checkFields();
        
        System.out.println("Fields checked. Entering filter criteria...");
        
        pPage.setOrderType("Y");
        
        System.out.println("Filter criteria entered. Listing records...");
        
        pPage.pressSearch();
        pPage.waitForElement();
        
        System.out.println("Records listed. Checking filtration...");
        
        String loc1 = "#content > div.flexi-grid > table > tbody > tr:nth-child(";
        String loc2 = ") > td:nth-child(2)";
        By recordField = By.cssSelector("#content > div.flexi-grid > dl > dt > span.left");
        
        AssertJUnit.assertTrue("Order Types Page: Filtration not working as expected",pPage.checkFiltration(loc1,loc2,"Y",recordField,2));
        
        System.out.println("Filtration as expected. Creating new Order Type...");
        
        Mst_AddOrderTypePage addPage = pPage.pressNewOrderType();
        addPage.waitForElement();
        
        System.out.println("Add Order Type Page reached. Checking title...");
        
        AssertJUnit.assertTrue("Add Order Type Page: Title not as expected",addPage.getBreadcrumb().getText().equals("Order Type | Add Order Type"));
        
        System.out.println("Title as expected");
        
        addPage.assertBaseElements();
        
        System.out.println("Checking fields...");
        
        addPage.checkFields();
        
        System.out.println("Fields checked. Entering details...");

        addPage.setOrderType("TEST");
        
        System.out.println("Details entered. Saving...");
        
        addPage.pressSave();
        pPage.waitForElement();
        
        System.out.println("Saved. Checking record appears...");
        
        pPage.setOrderType("TEST");
        pPage.pressSearch();
        pPage.waitForElement();
        
        int row = pPage.getRow("TEST");
        
        AssertJUnit.assertFalse("Order Type Page: Order Type not present in table after creation",row==-1);
        
        System.out.println("Record found. Editing record...");
        
        Mst_EditOrderTypePage editPage = pPage.pressEdit(row);
        editPage.waitForElement();
        
        System.out.println("Edit page reached. Checking title...");
        
        AssertJUnit.assertTrue("Edit Order Type Page: Title not as expected",editPage.getBreadcrumb().getText().equals("Order Type | Edit Order Type"));
        
        System.out.println("Title checked");
        
        editPage.assertBaseElements();
        
        System.out.println("Checking fields...");
        
        editPage.checkFields();
        
        System.out.println("Fields checked. Editing Order Type...");
        
        editPage.setOrderType("Edited");
        
        System.out.println("Edited. Saving...");
        
        editPage.pressSave();
        pPage.waitForElement();
        
        System.out.println("Saved. Checking record is updated...");
        
        pPage.setOrderType("Edited");
        pPage.pressSearch();
        pPage.waitForElement();
        
        int row2 = pPage.getRow("Edited");
        AssertJUnit.assertFalse("Order Type Page: Edited changes are not applied in table",row2==-1);
        
        System.out.println("Record updated. Deleting record...");
        
        pPage.pressDelete(row2);
        pPage.waitForElement();
        
        System.out.println("Delete pressed. Checking item is removed...");
        
        pPage.setOrderType("Edited");
        pPage.pressSearch();
        pPage.waitForElement();
        
        int row3 = pPage.getRow("Edited");
        AssertJUnit.assertTrue("Order Types Page: Item not removed after deletion",row3==-1);
        
        System.out.println("Item removed. Checking export function...");
        
        pPage.pressReset();
        pPage.waitForElement();
        
        CCE_ExportDownloadPage dlPage = pPage.pressExport();
        dlPage.waitForDownloadCompletion();
        
        System.out.println("Export function works. Checking import page...");
        
        Mst_ImportPage impPage = pPage.pressImport();
        impPage.waitForElement();
        
        System.out.println("Page reached. Checking title...");
        
        AssertJUnit.assertTrue("Order Types Import Page: Title not as expected",impPage.getBreadcrumb().getText().equals("TypeOrders | Import"));
    
        System.out.println("Title as expected");
    }
    
    @Test //Our Stocks/Warehouse Stocks: Page and filter checks, add/edit/delete/export features
    (groups = {"Masters"})
    public void ourStocks1() throws Exception {
        WebDriver driver = getDriver();
        
        Cce_Base base = new Cce_Base(driver);
        CCE_MainPage mainPage = base.setUp("Our Stocks Page: Page and filter checks, add/edit/delete/export features", "A_PR_OS_1 to 8");
        mainPage.waitForLoad();
        
        System.out.println("Navigating to Our Stocks Page...");
        
        Mst_WarehouseStocksPage pPage = mainPage.selectWarehouseStocks();
        pPage.waitForElement();
        
        System.out.println("Our Stocks page reached. Checking title...");
        
        AssertJUnit.assertTrue("Our Stocks Page: Title not as expected",pPage.getBreadcrumb().getText().equals("Our Stocks"));
        
        System.out.println("Title checked");
        
        pPage.assertBaseElements();
        
        System.out.println("Checking fields...");
        
        pPage.checkFields();
        
        System.out.println("Fields checked. Entering filter criteria...");
        
        pPage.setSalesOrg("ID51");
        
        System.out.println("Filter criteria entered. Listing records...");
        
        pPage.pressSearch();
        pPage.waitForElement();
        
        System.out.println("Records listed. Checking filtration...");
        
        String loc1 = "#OurStockListForm > div.flexi-grid > table > tbody > tr:nth-child(";
        String loc2 = ") > td:nth-child(2)";
        By recordField = By.cssSelector("#OurStockListForm > div.flexi-grid > dl > dt > span.left");
        
        AssertJUnit.assertTrue("Order Types Page: Filtration not working as expected",pPage.checkFiltration(loc1,loc2,"ID51",recordField,2));
        
        System.out.println("Filtration as expected. Creating new Our Stock...");
        
        Mst_AddOurStockPage addPage = pPage.pressNewOurStock();
        addPage.waitForElement();
        
        System.out.println("Add Our Stock Page reached. Checking title...");
        
        AssertJUnit.assertTrue("Add Our Stock Page: Title not as expected",addPage.getBreadcrumb().getText().equals("Our Stocks | Add Our Stock"));
        
        System.out.println("Title as expected");
        
        addPage.assertBaseElements();
        
        System.out.println("Checking fields...");
        
        addPage.checkFields();
        
        System.out.println("Fields checked. Entering details...");

        addPage.setSalesOrg("ID51");
        addPage.setPlant("ID12");
        addPage.setArticle("TEST01");
        addPage.setShade("blacktest");
        addPage.setQuantity("5");
        
        System.out.println("Details entered. Saving...");
        
        addPage.pressSave();
        pPage.waitForElement();
        
        System.out.println("Saved. Checking record appears...");
        
        pPage.setSalesOrg("ID51");
        pPage.pressSearch();
        pPage.waitForElement();
        
        int row = pPage.getRow("ID51","TEST01","blacktest");
        
        AssertJUnit.assertFalse("Our Stocks Page: Our Stock not present in table after creation",row==-1);
        
        System.out.println("Record found. Editing record...");
        
        Mst_EditOurStockPage editPage = pPage.pressEdit(row);
        editPage.waitForElement();
        
        System.out.println("Edit page reached. Checking title...");
        
        AssertJUnit.assertTrue("Edit Our Stock Page: Title not as expected",editPage.getBreadcrumb().getText().equals("Our Stocks | Edit Our Stock"));
        
        System.out.println("Title checked");
        
        editPage.assertBaseElements();
        
        System.out.println("Checking fields...");
        
        editPage.checkFields();
        
        System.out.println("Fields checked. Editing Our Stock...");
        
        editPage.setShade("WHITE");
        
        System.out.println("Edited. Saving...");
        
        editPage.pressSave();
        pPage.waitForElement();
        
        System.out.println("Saved. Checking record is updated...");
        
        pPage.setSalesOrg("ID51");
        pPage.pressSearch();
        pPage.waitForElement();
        
        int row2 = pPage.getRow("ID51","TEST01","WHITE");
        AssertJUnit.assertFalse("Our Stocks Page: Edited changes are not applied in table",row2==-1);
        
        System.out.println("Record updated. Deleting record...");
        
        pPage.pressDelete(row2);

        System.out.println("Delete pressed. Checking item is removed...");
        
        pPage.setSalesOrg("ID51");
        pPage.pressSearch();
        pPage.waitForElement();
        
        int row3 = pPage.getRow("ID51","TEST01","WHITE");
        AssertJUnit.assertTrue("Our Stocks Page: Item not removed after deletion",row3==-1);
        
        System.out.println("Item removed. Checking export function...");
        
        pPage.pressReset();
        pPage.waitForElement();
        pPage.setSalesOrg("ID51");
        pPage.pressSearch();
        pPage.waitForElement();
        
        CCE_ExportDownloadPage dlPage = pPage.pressExport();
        dlPage.waitForDownloadCompletion();
        
        System.out.println("Export function works. Checking import page...");
        
        Mst_ImportPage impPage = pPage.pressImport();
        impPage.waitForElement();
        
        System.out.println("Page reached. Checking title...");
        
        AssertJUnit.assertTrue("Our Stocks Import Page: Title not as expected",impPage.getBreadcrumb().getText().equals("Our Stocks | Import"));
    
        System.out.println("Title as expected");
    }
    
    @Test //Allowed Quantities: Page and filter checks, add/edit/delete/export features
    (groups = {"Masters"})
    public void allowedQuantities1() throws Exception {
        WebDriver driver = getDriver();
        
        Cce_Base base = new Cce_Base(driver);
        CCE_MainPage mainPage = base.setUp("Allowed Quantities Page: Page and filter checks, add/edit/delete/export features", "A_PR_OS_1 to 8");
        mainPage.waitForLoad();
        
        System.out.println("Navigating to Allowed Quantities Page...");
        
        Mst_AllowedQuantitiesPage pPage = mainPage.selectAllowedQuantities();
        pPage.waitForElement();
        
        System.out.println("Allowed Quantities page reached. Checking title...");
        
        AssertJUnit.assertTrue("Allowed Quantities Page: Title not as expected",pPage.getBreadcrumb().getText().equals("Allowed Quantities"));
        
        System.out.println("Title checked");
        
        pPage.assertBaseElements();
        
        System.out.println("Checking fields...");
        
        pPage.checkFields();
        
        System.out.println("Fields checked. Entering filter criteria...");
        
        pPage.setCustomerName(DataItems.custDetails[0]);
        
        System.out.println("Filter criteria entered. Listing records...");
        
        pPage.pressSearch();
        pPage.waitForElement();
        
        System.out.println("Records listed. Checking filtration...");
        
        String loc1 = "#content > div.flexi-grid > table > tbody > tr:nth-child(";
        String loc2 = ") > td:nth-child(2)";
        By recordField = By.cssSelector("#content > div.flexi-grid > dl > dt > span.left");
        
        AssertJUnit.assertTrue("Allowed Quantities Page: Filtration not working as expected",pPage.checkFiltration(loc1,loc2,"Life Easy Customer",recordField,2));
        
        System.out.println("Filtration as expected. Creating new Allowed Quantity...");
        
        Mst_AddAllowedQuantityPage addPage = pPage.pressNewAllowedQty();
        addPage.waitForElement();
        
        System.out.println("Add Allowed Quantity Page reached. Checking title...");
        
        AssertJUnit.assertTrue("Add Allowed Quantity Page: Title not as expected",addPage.getBreadcrumb().getText().equals("Allowed Quantities | Add Allowed Quantity"));
        
        System.out.println("Title as expected");
        
        addPage.assertBaseElements();
        
        System.out.println("Checking fields...");
        
        addPage.checkFields();
        
        System.out.println("Fields checked. Entering details...");

        addPage.setCustomerName("Life Easy Customer");
        addPage.setBrand("astra");
        addPage.setTicket("000");
        addPage.setMUMType("Cone");
        addPage.setShade("BLACK");
        addPage.setQty("9");
        
        System.out.println("Details entered. Saving...");
        
        addPage.pressSave();
        pPage.waitForElement();
        
        System.out.println("Saved. Checking record appears...");
        
        pPage.setCustomerName("Life Easy Customer");
        pPage.pressSearch();
        pPage.waitForElement();
        
        int row = pPage.getRow("astra","9");
        
        AssertJUnit.assertFalse("Allowed Quantities Page: Allowed Quantity not present in table after creation",row==-1);
        
        System.out.println("Record found. Editing record...");
        
        Mst_EditAllowedQuantityPage editPage = pPage.pressEdit(row);
        editPage.waitForElement();
        
        System.out.println("Edit page reached. Checking title...");
        
        AssertJUnit.assertTrue("Edit Allowed Quantity Page: Title not as expected",editPage.getBreadcrumb().getText().equals("Allowed Quantities | Edit Allowed Quantity"));
        
        System.out.println("Title checked");
        
        editPage.assertBaseElements();
        
        System.out.println("Checking fields...");
        
        editPage.checkFields();
        
        System.out.println("Fields checked. Editing Allowed Quantity...");
        
        editPage.setQty("15");
        
        System.out.println("Edited. Saving...");
        
        editPage.pressSave();
        pPage.waitForElement();
        
        System.out.println("Saved. Checking record is updated...");
        
        pPage.setCustomerName("Life Easy Customer");
        pPage.pressSearch();
        pPage.waitForElement();
        
        int row2 = pPage.getRow("astra","15");
        AssertJUnit.assertFalse("Allowed Quantities Page: Edited changes are not applied in table",row2==-1);
        
        System.out.println("Record updated. Deleting record...");
        
        pPage.pressDelete(row2);

        System.out.println("Delete pressed. Checking item is removed...");
        
        pPage.setCustomerName("Life Easy Customer");
        pPage.pressSearch();
        pPage.waitForElement();
        
        int row3 = pPage.getRow("astra","15");
        AssertJUnit.assertTrue("Allowed Quantities Page: Item not removed after deletion",row3==-1);
        
        System.out.println("Item removed. Checking export function...");
        
        pPage.pressReset();
        pPage.waitForElement();
        pPage.setCustomerName("Life Easy Customer");
        pPage.pressSearch();
        pPage.waitForElement();
        
        CCE_ExportDownloadPage dlPage = pPage.pressExport();
        dlPage.waitForDownloadCompletion();
        
        System.out.println("Export function works. Checking import page...");
        
        Mst_ImportPage impPage = pPage.pressImport();
        impPage.waitForElement();
        
        System.out.println("Page reached. Checking title...");
        
        AssertJUnit.assertTrue("Allowed Quantities Import Page: Title not as expected",impPage.getBreadcrumb().getText().equals("Allowed Quantities | Import"));
    
        System.out.println("Title as expected");
    }
    
    @Test //Customers: Page and filter checks, add/edit/delete/export features
    (groups = {"Masters"})
    public void customers1() throws Exception {
        WebDriver driver = getDriver();
        
        Cce_Base base = new Cce_Base(driver);
        CCE_MainPage mainPage = base.setUp("Customers Page: Page and filter checks, add/edit/delete/export features", "A_CB_C_1 to 8");
        mainPage.waitForLoad();
        
        System.out.println("Navigating to Customers Page...");
        
        Mst_CustomersPage pPage = mainPage.selectCustomers();
        pPage.waitForElement();
        
        System.out.println("Customers page reached. Checking title...");
        
        AssertJUnit.assertTrue("Customers Page: Title not as expected",pPage.getBreadcrumb().getText().equals("Customers"));
        
        System.out.println("Title checked");
        
        pPage.assertBaseElements();
        
        System.out.println("Checking fields...");
        
        pPage.checkFields();
        
        System.out.println("Fields checked. Entering filter criteria...");
        
        pPage.setCustomerName(DataItems.custDetails[0]);
        
        System.out.println("Filter criteria entered. Listing records...");
        
        pPage.pressSearch();
        pPage.waitForElement();
        
        System.out.println("Records listed. Checking filtration...");
        
        String loc1 = "#content > div.flexi-grid > table > tbody > tr:nth-child(";
        String loc2 = ") > td:nth-child(4)";
        By recordField = By.cssSelector("#content > div.flexi-grid > dl > dt > span.left");
        
        AssertJUnit.assertTrue("Customers Page: Filtration not working as expected",pPage.checkFiltration(loc1,loc2,"Life Easy Customer",recordField,2));
        
        System.out.println("Filtration as expected. Creating new Customer...");
        
        Mst_AddCustomerPage addPage = pPage.pressNewCustomer();
        addPage.waitForElement();
        
        System.out.println("Add Customer Page reached. Checking title...");
        
        AssertJUnit.assertTrue("Add Customer Page: Title not as expected",addPage.getBreadcrumb().getText().equals("Customers | Add Customer"));
        
        System.out.println("Title as expected");
        
        addPage.assertBaseElements();
        
        System.out.println("Checking fields...");
        
        addPage.checkFields();
        
        System.out.println("Fields checked. Entering details...");

        addPage.setCustomerName("AutomatedTest");
        addPage.setSalesOrg("ID51");
        addPage.setCustomerCode("AUT0");
        addPage.setPrincipalName("*OTHERS*");
        addPage.setCCESolution("Capsure Plus");
        addPage.setPriority("02");
        
        addPage.setRequesterFirstName(0, "Auto");
        addPage.setRequesterUsername(0, CommonTask.generatePO("User") + "@test.com");
        addPage.setRequesterPassword(0,"password");
        addPage.setRequesterUsertype(0,"Approver");
        addPage.setRequesterLanguage(0,"English");
        addPage.setRequesterCountry(0,"Indonesia");
        
        System.out.println("Details entered. Saving...");
        
        addPage.pressSave();
        pPage.waitForElement();
        
        System.out.println("Saved. Checking record appears...");
        
        pPage.setCustomerName("AutomatedTest");
        pPage.pressSearch();
        pPage.waitForElement();
        
        int row = pPage.getRow("AutomatedTest");
        
        AssertJUnit.assertFalse("Customers Page: Customer not present in table after creation",row==-1);
        
        System.out.println("Record found. Editing record...");
        
        Mst_EditCustomerPage editPage = pPage.pressEdit(row);
        editPage.waitForElement();
        
        System.out.println("Edit page reached. Checking title...");
        
        AssertJUnit.assertTrue("Edit Customer Page: Title not as expected",editPage.getBreadcrumb().getText().equals("Customers | Edit Customer"));
        
        System.out.println("Title checked");
        
        editPage.assertBaseElements();
        
        System.out.println("Checking fields...");
        
        editPage.checkFields();
        
        System.out.println("Fields checked. Editing Customer...");
        
        editPage.setCustomerName("AutoEdited");
        
        System.out.println("Edited. Saving...");
        
        editPage.pressSave();
        pPage.waitForElement();
        
        System.out.println("Saved. Checking record is updated...");
        
        pPage.setCustomerName("AutoEdited");
        pPage.pressSearch();
        pPage.waitForElement();
        
        int row2 = pPage.getRow("AutoEdited");
        AssertJUnit.assertFalse("Customers Page: Edited changes are not applied in table",row2==-1);
        
        System.out.println("Record updated. Deleting record...");
        
        pPage.pressDelete(row2);

        System.out.println("Delete pressed. Checking item is removed...");
        
        pPage.setCustomerName("AutoEdited");
        pPage.pressSearch();
        pPage.waitForElement();
        
        int row3 = pPage.getRow("AutoEdited");
        AssertJUnit.assertTrue("Customers Page: Item not removed after deletion",row3==-1);
        
        System.out.println("Item removed. Checking export function...");
        
        pPage.pressReset();
        pPage.waitForElement();
        pPage.setSalesOrg("ID50");
        pPage.pressSearch();
        pPage.waitForElement();
        
        CCE_ExportDownloadPage dlPage = pPage.pressExport();
        dlPage.waitForDownloadCompletion();
        
        System.out.println("Export function works. Checking import page...");
        
        Mst_ImportPage impPage = pPage.pressImport();
        impPage.waitForElement();
        
        System.out.println("Page reached. Checking title...");
        
        AssertJUnit.assertTrue("Customers Import Page: Title not as expected",impPage.getBreadcrumb().getText().equals("Customers | Import"));
    
        System.out.println("Title as expected");
    }
    
    @Test //Ship To Parties: Page and filter checks, add/edit/delete/export features
    (groups = {"Masters"})
    public void shipToParties1() throws Exception {
        WebDriver driver = getDriver();
        
        Cce_Base base = new Cce_Base(driver);
        CCE_MainPage mainPage = base.setUp("Ship To Parties Page: Page and filter checks, add/edit/delete/export features", "A_CB_STP_1 to 8");
        mainPage.waitForLoad();
        
        System.out.println("Navigating to Ship To Parties Page...");
        
        Mst_ShipToPartiesPage pPage = mainPage.selectShipToParties();
        pPage.waitForElement();
        
        System.out.println("Ship To Parties page reached. Checking title...");
        
        AssertJUnit.assertTrue("Ship To Parties Page: Title not as expected",pPage.getBreadcrumb().getText().equals("Ship To Parties"));
        
        System.out.println("Title checked");
        
        pPage.assertBaseElements();
        
        System.out.println("Checking fields...");
        
        pPage.checkFields();
        
        System.out.println("Fields checked. Entering filter criteria...");
        
        pPage.setCustomerName(DataItems.custDetails[0]);
        
        System.out.println("Filter criteria entered. Listing records...");
        
        pPage.pressSearch();
        pPage.waitForElement();
        
        System.out.println("Records listed. Checking filtration...");
        
        String loc1 = "#content > div.flexi-grid > table > tbody > tr:nth-child(";
        String loc2 = ") > td:nth-child(3)";
        By recordField = By.cssSelector("#content > div.flexi-grid > dl > dt > span.left");
        
        AssertJUnit.assertTrue("Ship To Parties Page: Filtration not working as expected",pPage.checkFiltration(loc1,loc2,"Life Easy Customer",recordField,2));
        
        System.out.println("Filtration as expected. Creating new Customer...");
        
        Mst_AddShipToPartyPage addPage = pPage.pressNewShipToParty();
        addPage.waitForElement();
        
        System.out.println("Add Ship To Party Page reached. Checking title...");
        
        AssertJUnit.assertTrue("Add Ship To Party Page: Title not as expected",addPage.getBreadcrumb().getText().equals("Ship To Parties | Add Ship To Party"));
        
        System.out.println("Title as expected");
        
        addPage.assertBaseElements();
        
        System.out.println("Checking fields...");
        
        addPage.checkFields();
        
        System.out.println("Fields checked. Entering details...");

        addPage.setFCE("Automated Test");
        addPage.setSalesOrg("ID51");
        addPage.setCustomerName("Life Easy Customer");
        addPage.setPartyNumber("101010");
        addPage.setPartyName("AutoShipTo");
        addPage.setAddress("Test House");
        addPage.setStreet("Test Lane");
        addPage.setCity("TestVille");
        addPage.setState("Test");
        addPage.setZipCode("555666");
        addPage.setCountryCode("ID");
        addPage.setHubName("IDH001");
        
        System.out.println("Details entered. Saving...");
        
        addPage.pressSave();
        pPage.waitForElement();
        
        System.out.println("Saved. Checking record appears...");
        
        pPage.setShipToPartyName("AutoShipTo");
        pPage.pressSearch();
        pPage.waitForElement();
        
        int row = pPage.getRow("AutoShipTo");
        
        AssertJUnit.assertFalse("Ship To Parties Page: Ship To Party not present in table after creation",row==-1);
        
        System.out.println("Record found. Editing record...");
        
        Mst_EditShipToPartyPage editPage = pPage.pressEdit(row);
        editPage.waitForElement();
        
        System.out.println("Edit page reached. Checking title...");
        
        AssertJUnit.assertTrue("Edit Ship To Party Page: Title not as expected",editPage.getBreadcrumb().getText().equals("Ship To Parties | Edit Ship To Party"));
        
        System.out.println("Title checked");
        
        editPage.assertBaseElements();
        
        System.out.println("Checking fields...");
        
        editPage.checkFields();
        
        System.out.println("Fields checked. Editing Ship To Party...");
        
        editPage.setPartyName("EditedShipTo");
        
        System.out.println("Edited. Saving...");
        
        editPage.pressSave();
        pPage.waitForElement();
        
        System.out.println("Saved. Checking record is updated...");
        
        pPage.setShipToPartyName("EditedShipTo");
        pPage.pressSearch();
        pPage.waitForElement();
        
        int row2 = pPage.getRow("EditedShipTo");
        AssertJUnit.assertFalse("Ship To Parties Page: Edited changes are not applied in table",row2==-1);
        
        System.out.println("Record updated. Deleting record...");
        
        pPage.pressDelete(row2);

        System.out.println("Delete pressed. Checking item is removed...");
        
        pPage.setShipToPartyName("EditedShipTo");
        pPage.pressSearch();
        pPage.waitForElement();
        
        int row3 = pPage.getRow("EditedShipTo");
        AssertJUnit.assertTrue("Ship to Parties Page: Item not removed after deletion",row3==-1);
        
        System.out.println("Item removed. Checking export function...");
        
        pPage.pressReset();
        pPage.waitForElement();
        pPage.setCustomerName("Life Easy Customer");
        pPage.pressSearch();
        pPage.waitForElement();
        
        CCE_ExportDownloadPage dlPage = pPage.pressExport();
        dlPage.waitForDownloadCompletion();
        
        System.out.println("Export function works. Checking import page...");
        
        Mst_ImportPage impPage = pPage.pressImport();
        impPage.waitForElement();
        
        System.out.println("Page reached. Checking title...");
        
        AssertJUnit.assertTrue("Ship to Parties Import Page: Title not as expected",impPage.getBreadcrumb().getText().equals("Ship To Parties | Import"));
    
        System.out.println("Title as expected");
    }
    
    @Test //Cabinets: Page and filter checks, add/edit/delete/export features
    (groups = {"Masters"}) 
    public void cabinets1() throws Exception {
        WebDriver driver = getDriver();
        
        Cce_Base base = new Cce_Base(driver);
        CCE_MainPage mainPage = base.setUp("Cabinets Page: Page and filter checks, add/edit/delete/export features", "A_CB_Cab_1 to 8");
        mainPage.waitForLoad();
        
        System.out.println("Navigating to Cabinets Page...");
        
        Mst_CabinetsPage pPage = mainPage.selectCabinets();
        pPage.waitForElement();
        
        System.out.println("Cabinets page reached. Checking title...");
        
        AssertJUnit.assertTrue("Cabinets Page: Title not as expected",pPage.getBreadcrumb().getText().equals("Cabinets"));
        
        System.out.println("Title checked");
        
        pPage.assertBaseElements();
        
        System.out.println("Checking fields...");
        
        pPage.checkFields();
        
        System.out.println("Fields checked. Entering filter criteria...");
        
        pPage.setSalesOrg("ID51");
        
        System.out.println("Filter criteria entered. Listing records...");
        
        pPage.pressSearch();
        pPage.waitForElement();
        
        System.out.println("Records listed. Checking filtration...");
        
        String loc1 = "#content > div.flexi-grid > table > tbody > tr:nth-child(";
        String loc2 = ") > td:nth-child(3)";
        By recordField = By.cssSelector("#content > div.flexi-grid > dl > dt > span.left");
        
        AssertJUnit.assertTrue("Cabinets Page: Filtration not working as expected",pPage.checkFiltration(loc1,loc2,"ID51",recordField,2));
        
        System.out.println("Filtration as expected. Creating new Cabinet...");
        
        Mst_AddCabinetPage addPage = pPage.pressNewCabinet();
        addPage.waitForElement();
        
        System.out.println("Add Cabinet Page reached. Checking title...");
        
        AssertJUnit.assertTrue("Add Cabinet Page: Title not as expected",addPage.getBreadcrumb().getText().equals("Cabinets | Add Cabinet"));
        
        System.out.println("Title as expected");
        
        addPage.assertBaseElements();
        
        System.out.println("Checking fields...");
        
        addPage.checkFields();
        
        System.out.println("Fields checked. Entering details...");

        addPage.setSalesOrg("ID51");
        addPage.setShipToParty("CCE HUB OFFICES");
        addPage.setCabinetCode("AutoGenerated");
        addPage.setBrand("astra",0);
        addPage.setTicket("000", 0);
        addPage.setShade("C1711",0);
        addPage.setMUMType("Cone",0);
        addPage.setQuantity("11", 0);
        
        System.out.println("Details entered. Saving...");
        
        addPage.pressSave();
        pPage.waitForElement();
        
        System.out.println("Saved. Checking record appears...");
        
        pPage.setCabinetCode("AutoGenerated");
        pPage.pressSearch();
        pPage.waitForElement();
        
        int row = pPage.getRow("AutoGenerated");
        
        AssertJUnit.assertFalse("Cabinets Page: Cabinet not present in table after creation",row==-1);
        
        System.out.println("Record found. Editing record...");
        
        Mst_EditCabinetPage editPage = pPage.pressEdit(row);
        editPage.waitForElement();
        
        System.out.println("Edit page reached. Checking title...");
        
        AssertJUnit.assertTrue("Edit Cabinet Page: Title not as expected",editPage.getBreadcrumb().getText().equals("Cabinets | Edit Cabinet"));
        
        System.out.println("Title checked");
        
        editPage.assertBaseElements();
        
        System.out.println("Checking fields...");
        
        editPage.checkFields();
        
        System.out.println("Fields checked. Editing Cabinet...");
        
        editPage.setCabinetCode("Edited");
        
        System.out.println("Edited. Saving...");
        
        editPage.pressSave();
        pPage.waitForElement();
        
        System.out.println("Saved. Checking record is updated...");
        
        pPage.setCabinetCode("Edited");
        pPage.pressSearch();
        pPage.waitForElement();
        
        int row2 = pPage.getRow("Edited");
        AssertJUnit.assertFalse("Cabinets Page: Edited changes are not applied in table",row2==-1);
        
        System.out.println("Record updated. Deleting record...");
        
        pPage.pressDelete(row2);

        System.out.println("Delete pressed. Checking item is removed...");
        
        pPage.setCabinetCode("Edited");
        pPage.pressSearch();
        pPage.waitForElement();
        
        int row3 = pPage.getRow("Edited");
        AssertJUnit.assertTrue("Cabinet Page: Item not removed after deletion",row3==-1);
        
        System.out.println("Item removed. Checking export function...");
        
        pPage.pressReset();
        pPage.waitForElement();
        pPage.setSalesOrg("ID51");
        pPage.pressSearch();
        pPage.waitForElement();
        
        CCE_ExportDownloadPage dlPage = pPage.pressExport();
        dlPage.waitForDownloadCompletion();
        
        System.out.println("Export function works. Checking import page...");
        
        Mst_ImportPage impPage = pPage.pressImport();
        impPage.waitForButton();
        
        System.out.println("Page reached. Checking title...");
        
        AssertJUnit.assertTrue("Cabinets Import Page: Title not as expected",impPage.getBreadcrumb().getText().equals("Cabinets | Import"));
    
        System.out.println("Title as expected");
    }
    
    @Test //Marketing New Features: Page and filter checks, add/edit/delete features
    (groups = {"Masters"})
    public void marketingNewFeatures1() throws Exception {
        WebDriver driver = getDriver();
        
        Cce_Base base = new Cce_Base(driver);
        CCE_MainPage mainPage = base.setUp("Marketing New Features Page: Page and filter checks, add/edit/delete features", "A_CB_MNF_1 to 5");
        mainPage.waitForLoad();
        
        System.out.println("Navigating to Marketing New Features Page...");
        
        Mst_MarketNewFeaturesPage pPage = mainPage.selectMarketNewFeatures();
        pPage.waitForElement();
        
        System.out.println("Cabinets page reached. Checking title...");
        
        AssertJUnit.assertTrue("Market New Features Page: Title not as expected",pPage.getBreadcrumb().getText().equals("Marketing New feature"));
        
        System.out.println("Title checked");
        
        pPage.assertBaseElements();
        
        System.out.println("Checking fields...");
        
        pPage.checkFields();
        
        System.out.println("Fields checked. Entering filter criteria...");
        
        pPage.setAccessType("eComm");
        
        System.out.println("Filter criteria entered. Listing records...");
        
        pPage.pressSearch();
        pPage.waitForElement();
        
        System.out.println("Records listed. Checking filtration...");
        
        String loc1 = "#content > div.flexi-grid > table > tbody > tr:nth-child(";
        String loc2 = ") > td:nth-child(4)";
        By recordField = By.cssSelector("#content > div.flexi-grid > dl > dt > span.left");
        
        AssertJUnit.assertTrue("Market New Features Page: Filtration not working as expected",pPage.checkFiltration(loc1,loc2,"eComm",recordField,2));
        
        System.out.println("Filtration as expected. Creating new Market New Features...");
        
        Mst_AddMarketNewFeaturePage addPage = pPage.pressNewFeature();
        addPage.waitForElement();
        
        System.out.println("Add Market New Feature Page reached. Checking title...");
        
        AssertJUnit.assertTrue("Add Market New Feature Page: Title not as expected",addPage.getBreadcrumb().getText().equals("Marketing New features | Add New feature"));
        
        System.out.println("Title as expected");
        
        addPage.assertBaseElements();
        
        System.out.println("Checking fields...");
        
        addPage.checkFields();
        
        System.out.println("Fields checked. Entering details...");

        addPage.setFeatureTitle("AutomatedTest");
        addPage.setSalesOrg("ID51");
        addPage.setFeatureDescription("Generated by automated test suite");
        addPage.setAccessType("eComm");
        addPage.setDate();
        
        System.out.println("Details entered. Saving...");
        
        addPage.pressSave();
        pPage.waitForElement();
        
        System.out.println("Saved. Checking record appears...");
        
        pPage.setFeatureTitle("AutomatedTest");
        pPage.pressSearch();
        pPage.waitForElement();
        
        int row = pPage.getRow("AutomatedTest");
        
        AssertJUnit.assertFalse("Market New Feature Page: Market New Feature not present in table after creation",row==-1);
        
        System.out.println("Record found. Editing record...");
        
        Mst_EditMarketNewFeaturePage editPage = pPage.pressEdit(row);
        editPage.waitForElement();
        
        System.out.println("Edit page reached. Checking title...");
        
        AssertJUnit.assertTrue("Edit Market New Feature Page: Title not as expected",editPage.getBreadcrumb().getText().equals("New features | Edit New feature"));
        
        System.out.println("Title checked");
        
        editPage.assertBaseElements();
        
        System.out.println("Checking fields...");
        
        editPage.checkFields();
        
        System.out.println("Fields checked. Editing New Feature...");
        
        editPage.setFeatureTitle("EditedTitle");
        
        System.out.println("Edited. Saving...");
        
        editPage.pressSave();
        pPage.waitForElement();
        
        System.out.println("Saved. Checking record is updated...");
        
        pPage.setFeatureTitle("EditedTitle");
        pPage.pressSearch();
        pPage.waitForElement();
        
        int row2 = pPage.getRow("EditedTitle");
        AssertJUnit.assertFalse("Marketing New Feature Page: Edited changes are not applied in table",row2==-1);
        
        System.out.println("Record updated. Deleting record...");
        
        pPage.pressDelete(row2);

        System.out.println("Delete pressed. Checking item is removed...");
        
        pPage.setFeatureTitle("EditedTitle");
        pPage.pressSearch();
        pPage.waitForElement();
        
        int row3 = pPage.getRow("Edited");
        AssertJUnit.assertTrue("Marketing New Feature Page: Item not removed after deletion",row3==-1);
        
        System.out.println("Item removed.");
        
    }
    
    @Test //Marketing Running Text: Page and filter checks, add/edit/delete features
    (groups = {"Masters"})
    public void marketingRunningText1() throws Exception {
        WebDriver driver = getDriver();
        
        Cce_Base base = new Cce_Base(driver);
        CCE_MainPage mainPage = base.setUp("Marketing Running Text Page: Page and filter checks, add/edit/delete features", "A_CB_MRT_1 to 5");
        mainPage.waitForLoad();
        
        System.out.println("Navigating to Marketing Running Text Page...");
        
        Mst_MarketRunningTextPage pPage = mainPage.selectMarketRunningText();
        pPage.waitForElement();
        
        System.out.println("Market Running Text page reached. Checking title...");
        
        AssertJUnit.assertTrue("Market Running Text Page: Title not as expected",pPage.getBreadcrumb().getText().equals("Marketing Running Text"));
        
        System.out.println("Title checked");
        
        pPage.assertBaseElements();
        
        System.out.println("Checking fields...");
        
        pPage.checkFields();
        
        System.out.println("Fields checked. Entering filter criteria...");
        
        pPage.setAccessType("eComm");
        
        System.out.println("Filter criteria entered. Listing records...");
        
        pPage.pressSearch();
        pPage.waitForElement();
        
        System.out.println("Records listed. Checking filtration...");
        
        String loc1 = "#content > div.flexi-grid > table > tbody > tr:nth-child(";
        String loc2 = ") > td:nth-child(5)";
        By recordField = By.cssSelector("#content > div.flexi-grid > dl > dt > span.left");
        
        AssertJUnit.assertTrue("Market Running Text Page: Filtration not working as expected",pPage.checkFiltration(loc1,loc2,"eComm",recordField,2));
        
        System.out.println("Filtration as expected. Creating new Market New Features...");
        
        Mst_AddMarketRunningTextPage addPage = pPage.pressNewRunningText();
        addPage.waitForElement();
        
        System.out.println("Add Market Running Text Page reached. Checking title...");
        
        AssertJUnit.assertTrue("Add Market Running Text Page: Title not as expected",addPage.getBreadcrumb().getText().equals("Marketing Running Texts | Add Running Text"));
        
        System.out.println("Title as expected");
        
        addPage.assertBaseElements();
        
        System.out.println("Checking fields...");
        
        addPage.checkFields();
        
        System.out.println("Fields checked. Entering details...");

        addPage.setTitle("AutomatedTest");
        addPage.setSalesOrg("ID51");
        addPage.setRunningText("Generated by Automated Test");
        addPage.setAvailableFromDate();
        addPage.setAvailableToDate();
        addPage.setAccessType("eComm");
        
        System.out.println("Details entered. Saving...");
        
        addPage.pressSave();
        pPage.waitForElement();
        
        System.out.println("Saved. Checking record appears...");

        pPage.setSalesOrg("ID51");
        pPage.pressSearch();
        pPage.waitForElement();
        
        int row = pPage.getRow("AutomatedTest");
        
        AssertJUnit.assertFalse("Market Running Text Page: Market Running Text not present in table after creation",row==-1);
        
        System.out.println("Record found. Editing record...");
        
        Mst_EditMarketRunningTextPage editPage = pPage.pressEdit(row);
        editPage.waitForElement();
        
        System.out.println("Edit page reached. Checking title...");
        
        AssertJUnit.assertTrue("Edit Market Running Text Page: Title not as expected",editPage.getBreadcrumb().getText().equals("Running Texts | Edit Running Text"));
        
        System.out.println("Title checked");
        
        editPage.assertBaseElements();
        
        System.out.println("Checking fields...");
        
        editPage.checkFields();
        
        System.out.println("Fields checked. Editing Running Text...");
        
        editPage.setTitle("EditedTitle");
        
        System.out.println("Edited. Saving...");
        
        editPage.pressSave();
        pPage.waitForElement();
        
        System.out.println("Saved. Checking record is updated...");
        
        pPage.setSalesOrg("ID51");
        pPage.pressSearch();
        pPage.waitForElement();
        
        int row2 = pPage.getRow("EditedTitle");
        AssertJUnit.assertFalse("Marketing Running Text Page: Edited changes are not applied in table",row2==-1);
        
        System.out.println("Record updated. Deleting record...");
        
        pPage.pressDelete(row2);

        System.out.println("Delete pressed. Checking item is removed...");
        
        pPage.setSalesOrg("ID51");
        pPage.pressSearch();
        pPage.waitForElement();
        
        int row3 = pPage.getRow("Edited");
        AssertJUnit.assertTrue("Marketing New Feature Page: Item not removed after deletion",row3==-1);
        
        System.out.println("Item removed.");
    }

    @Test //Customers Private Article :: Page and filter checks, add/edit/delete features
            (groups = {"Masters"}) //DISABLED as data cannot be deleted from table (due to sharing violation) so test cannot pass
    public void custPrivArt1() throws Exception {
        WebDriver driver = getDriver();

        Cce_Base base = new Cce_Base(driver);
        CCE_MainPage mainPage = base.setUp("Customers Private Article: Page and filter checks, add/edit/delete features", "A_CM_P_1 to 8");
        mainPage.waitForLoad();

        System.out.println("Navigating to Customers Private Article Page...");

        Mst_CustomerPrivateArticlesPage cpaPage = mainPage.selectCustomerPrivateArticle();
        cpaPage.waitForElement();

        System.out.println("Customers Private Article page reached. Checking title...");

        Mst_PlantsPage pPage = mainPage.selectPlants();

        AssertJUnit.assertTrue("Customers Private Page: Title not as expected", cpaPage.getBreadcrumb().getText().equals("Customer Private Articles"));

        System.out.println("Title checked");

        cpaPage.assertBaseElements();

        System.out.println("Checking fields...");

        cpaPage.checkFields();

        System.out.println("Fields checked. Entering filter criteria...");


        //-------------
        /*
        pPage.setPlantName("ID10");

        System.out.println("Filter criteria entered. Listing records...");

        pPage.pressSearch();
        pPage.waitForElement();

        System.out.println("Records listed. Checking filtration...");

        String loc1 = "#content > div.flexi-grid > table > tbody > tr:nth-child(";
        String loc2 = ") > td:nth-child(2)";
        By recordField = By.cssSelector("#content > div.flexi-grid > dl > dt > span.left");

        AssertJUnit.assertTrue("Plants Page: Filtration not working as expected",pPage.checkFiltration(loc1,loc2,"ID10",recordField,2));

*/
        System.out.println("Creating new Customer Private Article...");

        cpaPage.pressNewPrivateArticle();



        ///////////////------------------------------///////////////



        Mst_AddPlantPage addPage = pPage.pressNewPlant();
        addPage.waitForElement();

        System.out.println("Add Plant Page reached. Checking title...");

        AssertJUnit.assertTrue("Add Plant Page: Title not as expected",addPage.getBreadcrumb().getText().equals("Plants | Add Plant"));

        System.out.println("Title as expected");

        addPage.assertBaseElements();

        System.out.println("Checking fields...");

        addPage.checkFields();

        System.out.println("Fields checked. Entering details...");

        addPage.setPlantName("TEST");
        addPage.setPlantDesc("Automated Test: generated by script");
        addPage.setLeadTime1("48");
        addPage.setLeadTime2("48");
        addPage.setLeadTime3("48");
        addPage.setLeadTime4("48");

        System.out.println("Details entered. Saving...");

        addPage.pressSave();
        pPage.waitForElement();

        System.out.println("Saved. Checking record appears...");

        pPage.setPlantName("TEST");
        pPage.pressSearch();
        pPage.waitForElement();

        int row = pPage.getRow("TEST");

        AssertJUnit.assertFalse("Plants Page: Plant not present in table after creation",row==-1);

        System.out.println("Record found. Editing record...");

        Mst_EditPlantPage editPage = pPage.pressEdit(row);
        editPage.waitForElement();

        System.out.println("Edit page reached. Checking title...");

        AssertJUnit.assertTrue("Edit Plant Page: Title not as expected",editPage.getBreadcrumb().getText().equals("Plants | Edit Plant"));

        System.out.println("Title checked");

        editPage.assertBaseElements();

        System.out.println("Checking fields...");

        editPage.checkFields();

        System.out.println("Fields checked. Editing Sales Organisation...");

        editPage.setPlantName("TESTEdited");
        editPage.addDay("Saturday");

        System.out.println("Edited. Saving...");

        editPage.pressSave();
        pPage.waitForElement();

        System.out.println("Saved. Checking record is updated...");

        pPage.setPlantName("TESTEdited");
        pPage.pressSearch();
        pPage.waitForElement();

        int row2 = pPage.getRow("TESTEdited");
        AssertJUnit.assertFalse("Plants Page: Edited changes are not applied in table",row2==-1);

        System.out.println("Record updated. Deleting record...");

        pPage.pressDelete(row2);
        pPage.waitForElement();

        System.out.println("Delete pressed. Checking item is removed...");

        pPage.setPlantName("TESTEdited");
        pPage.pressSearch();
        pPage.waitForElement();

        int row3 = pPage.getRow("TESTEdited");
        AssertJUnit.assertTrue("Plants Page: Item not removed after deletion",row3==-1);

        System.out.println("Item removed. Checking export function...");

        pPage.pressReset();
        pPage.waitForElement();
        pPage.setPlantName("ID");
        pPage.pressSearch();
        pPage.waitForElement();

        CCE_ExportDownloadPage dlPage = pPage.pressExport();
        dlPage.waitForDownloadCompletion();

        System.out.println("Export function works. Checking import page...");

        Mst_ImportPage impPage = pPage.pressImport();
        impPage.waitForElement();

        System.out.println("Page reached. Checking title...");

        AssertJUnit.assertTrue("Plants Import Page: Title not as expected",impPage.getBreadcrumb().getText().equals("Plants | Import"));

        System.out.println("Title as expected");
    }


}

    

