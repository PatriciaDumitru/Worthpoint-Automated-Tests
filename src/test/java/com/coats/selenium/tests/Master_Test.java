
package com.coats.selenium.tests;

import AutomationFramework.CommonTask;
import AutomationFramework.DataItems;
import PageObjects.CCE_ExportDownloadPage;
import PageObjects.CCE_MainPage;
import PageObjects.CCE_OrderViewPage;
import PageObjects.Ecomm_MainPage;
import PageObjects.Mst_AddCoatsUserPage;
import PageObjects.Mst_AddSubAccountPage;
import PageObjects.Mst_AddUserTypePage;
import PageObjects.Mst_AllUserTypesPage;
import PageObjects.Mst_CoatsUsersPage;
import PageObjects.Mst_CountriesPage;
import PageObjects.Mst_CustomersPage;
import PageObjects.Mst_EditCoatsUserPage;
import PageObjects.Mst_EditCustomerPage;
import PageObjects.Mst_EditSalesOrgPage;
import PageObjects.Mst_EditSubAccountPage;
import PageObjects.Mst_EditUserTypePage;
import PageObjects.Mst_ImportPage;
import PageObjects.Mst_SalesOrgPage;
import PageObjects.Mst_SubAccountPage;
import PageObjects.WBA_BasePage;
import com.coats.selenium.DriverFactory;
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
    (groups = {"Masters","Solo"})
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
        
        AssertJUnit.assertTrue("Edit Sales Org Page: Mail Notification label not displayed",editPage.getMailNotificationLabel().getText().equals("Enabled Sub Account Option"));
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
    
}
