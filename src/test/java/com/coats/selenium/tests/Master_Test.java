
package com.coats.selenium.tests;

import AutomationFramework.CommonTask;
import AutomationFramework.DataItems;
import PageObjects.CCE_MainPage;
import PageObjects.CCE_OrderViewPage;
import PageObjects.Ecomm_MainPage;
import PageObjects.Mst_AddCoatsUserPage;
import PageObjects.Mst_AddUserTypePage;
import PageObjects.Mst_AllUserTypesPage;
import PageObjects.Mst_CoatsUsersPage;
import PageObjects.Mst_EditCoatsUserPage;
import PageObjects.Mst_EditUserTypePage;
import PageObjects.Mst_ImportPage;
import com.coats.selenium.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;


public class Master_Test extends DriverFactory {
    
    @Test //All user types page :: Page and filter checks, create new user/edit/delete
    (groups = {"Masters","CCE"})
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
    (groups = {"Masters","CCE"})
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
        addPage.setSalesOrg(DataItems.autoUserSalesOrg);
        addPage.setHub(DataItems.autoUserHub);
        
        System.out.println("User details entered. Saving...");
        
        Mst_CoatsUsersPage coatsPage3 = addPage.pressSave();
        coatsPage3.waitForElement();
        
        System.out.println("Saved. Checking record appears...");
        
        coatsPage3.setUsername(DataItems.autoUsername);
        coatsPage3.waitForElement();
        
        AssertJUnit.assertTrue("New Coats User does not appear after being saved",coatsPage3.checkForRecords());
        
        System.out.println("Record appears. Checking export function...");
        
        CCE_OrderViewPage exportPage = coatsPage3.pressExport("xlsx");
        exportPage.waitForContent();
        
        System.out.println("Export View Page: View page appears. Closing view...");
        
        exportPage.closeView();
        exportPage.waitForInvisibility();
        
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
}
