
package com.coats.selenium.tests;

import AutomationFramework.CommonTask;
import AutomationFramework.DataItems;
import PageObjects.CCE_MainPage;
import PageObjects.Ecomm_MainPage;
import PageObjects.Mst_AddUserTypePage;
import PageObjects.Mst_AllUserTypesPage;
import PageObjects.Mst_CoatsUsersPage;
import PageObjects.Mst_EditUserTypePage;
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
        CCE_MainPage ccePage = base.SUMST_SetUp("All User Types: Page and filter checks, create and delete type", "A_AUT_1-5");
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
        CCE_MainPage ccePage = base.SUMST_SetUp("All User Types: Page and filter checks, create and delete type", "A_AUT_1-5");
        ccePage.waitForLoad();
        
        System.out.println("Navigating to Coats Users page...");
        
        Mst_CoatsUsersPage coatsPage = ccePage.pressCoatsUsers();
        coatsPage.waitForElement();
        
        System.out.println("Coats Users Page reached. ");
    }
}
