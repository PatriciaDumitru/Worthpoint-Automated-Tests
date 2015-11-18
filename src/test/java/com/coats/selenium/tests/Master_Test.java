
package com.coats.selenium.tests;

import AutomationFramework.CommonTask;
import AutomationFramework.DataItems;
import PageObjects.CCE_ExportDownloadPage;
import PageObjects.CCE_MainPage;
import PageObjects.CCE_OrderViewPage;
import PageObjects.Ecomm_MainPage;
import PageObjects.Mst_AddApproverListPage;
import PageObjects.Mst_AddBusinessPrincipalPage;
import PageObjects.Mst_AddCoatsUserPage;
import PageObjects.Mst_AddCustBrandPage;
import PageObjects.Mst_AddCustBusPrincPage;
import PageObjects.Mst_AddCustFinishPage;
import PageObjects.Mst_AddCustLengthPage;
import PageObjects.Mst_AddCustMaterialPage;
import PageObjects.Mst_AddCustShadePage;
import PageObjects.Mst_AddCustTicketPage;
import PageObjects.Mst_AddMultiUserPage;
import PageObjects.Mst_AddShadePage;
import PageObjects.Mst_AddSubAccountPage;
import PageObjects.Mst_AddUserTypePage;
import PageObjects.Mst_AllUserTypesPage;
import PageObjects.Mst_ApproverListPage;
import PageObjects.Mst_BusinessPrincipalsPage;
import PageObjects.Mst_CoatsUsersPage;
import PageObjects.Mst_CountriesPage;
import PageObjects.Mst_CustBrandsPage;
import PageObjects.Mst_CustBusinessPrincipalPage;
import PageObjects.Mst_CustFinishesPage;
import PageObjects.Mst_CustLengthsPage;
import PageObjects.Mst_CustMaterialsPage;
import PageObjects.Mst_CustTicketsPage;
import PageObjects.Mst_CustomerShadesPage;
import PageObjects.Mst_CustomersPage;
import PageObjects.Mst_EditApproverListPage;
import PageObjects.Mst_EditBusinessPrincipalPage;
import PageObjects.Mst_EditCoatsUserPage;
import PageObjects.Mst_EditCustBrandPage;
import PageObjects.Mst_EditCustBusPrincPage;
import PageObjects.Mst_EditCustFinishPage;
import PageObjects.Mst_EditCustMaterialPage;
import PageObjects.Mst_EditCustShadePage;
import PageObjects.Mst_EditCustTicketPage;
import PageObjects.Mst_EditCustomerPage;
import PageObjects.Mst_EditMultiUserPage;
import PageObjects.Mst_EditSalesOrgPage;
import PageObjects.Mst_EditShadePage;
import PageObjects.Mst_EditSubAccountPage;
import PageObjects.Mst_EditUserTypePage;
import PageObjects.Mst_ImportPage;
import PageObjects.Mst_ImportShadesPage;
import PageObjects.Mst_MultiSoldToPage;
import PageObjects.Mst_SalesOrgPage;
import PageObjects.Mst_ShadesPage;
import PageObjects.Mst_SubAccountPage;
import PageObjects.WBA_BasePage;
import com.coats.selenium.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;


public class Master_Test extends DriverFactory {
    
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
    
    @Test //Countries Page :: Page and filter checks, add/edit/delete
    (groups = {"Masters"})
    public void countries1() throws Exception {
        WebDriver driver = getDriver();
        
        Cce_Base base = new Cce_Base(driver);
        CCE_MainPage ccePage = base.setUp("Countries Page: Page and filter checks, add/edit/delete", "A_CM_C_1 to 8");
        ccePage.waitForLoad();
        
        System.out.println("Navigating to Countries page...");
        
        Mst_CountriesPage countryPage = ccePage.selectCountries();
        countryPage.waitForElement();
        
        System.out.println("Countries Page reached. Checking title...");
        
        AssertJUnit.assertTrue("Coats Users Page: Title not displayed as expected",countryPage.getBreadcrumb().getText().equals("Countries"));
        
        System.out.println("Title checked");
        
        countryPage.assertBaseElements();
        
        System.out.println("Checking fields...");
        
        countryPage.checkFields();
        
        System.out.println("Fields checked. Entering filter criteria...");
        
        countryPage.setCountryName(DataItems.countryName);
        
        System.out.println("Filter criteria entered. Listing orders...");
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
        
        AssertJUnit.assertTrue("Edit Customer Page: Contract Order field not enabled",editPage2.getContractOrderField().getAttribute("checked").equals("true"));
        
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
        
        AssertJUnit.assertTrue("Edit Sales Org Page: Mail Notification label text not as expected",editPage.getMailNotificationLabel().getText().equals("CCE Ship Notice"));
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
    (groups = {"Masters"})
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
    (groups = {"Masters","Solo"})
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
    
}
