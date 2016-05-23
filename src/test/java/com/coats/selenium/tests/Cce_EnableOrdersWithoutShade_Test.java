package com.coats.selenium.tests;

import AutomationFramework.DataItems;
import PageObjects.*;
import com.coats.selenium.DriverFactory;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.AssertJUnit;

import org.testng.annotations.Test;

/**
 * Created by Stefan on 22.03.2016.
 * This class contains 2 tests:
 * test1: Enable flag for Enable Order without Shade in Sales Organizations Page and Customers Page
 * test2: Disable flag for Enable Order without Shade in Sales Organizations Page and Customers Page
 */
public class Cce_EnableOrdersWithoutShade_Test extends DriverFactory {

    @Test //Enable Order without Shade :: Enable Checkbox in Sales Organizations and Customers
            (groups={"CCE"})
    public void EOwS_GC_010() throws Exception {
        //New driver object to control browser
        WebDriver driver = getDriver();

        //New base object to handle log-in and set up
        Cce_Base base = new Cce_Base(driver);

        CCE_MainPage mainPage = base.setUp("Enable Order without Shade on Sales Organizations page", "EOwS_GC_010");

        mainPage.waitForElement();

        System.out.println("Navigating to Sales Organisations Page...");

        //Navigating to Admin -> Master -> Sales Organizations Page
        Mst_SalesOrgPage soPage = mainPage.selectSalesOrg();
        soPage.waitForElement();

        System.out.println("Sales Organisations page reached. Checking title...");

        //Verify that Sales Organisations Page is shown
        AssertJUnit.assertTrue("Sales Organisations Page: Title not as expected", soPage.getBreadcrumb().getText().equals("Sales Organisations"));

        //Input a valid Sales Organization ID51
        soPage.setSalesOrg(DataItems.salesOrgFilterString);

        //Search for the above entered Sales Organization
        System.out.print("Searching for Sales Organization: ID51...");
        soPage.pressSearch();

        soPage.waitForElement();

        //get the row number of Sales Organization searched for
        int row = soPage.getRow(DataItems.salesOrgFilterString);

          //Press Edit
        System.out.println("Record found on row:"+row+". Editing record...");
        Mst_EditSalesOrgPage editPage = soPage.pressEdit(row);
        editPage.waitForElement();
        System.out.println("Edit page reached. Checking title...");

        //Verify that Edit page is displayed
        AssertJUnit.assertTrue("Edit Sales Organisation Page: Title not as expected", editPage.getBreadcrumb().getText().equals("Sales Organisations | Edit Sales Organisation"));
        System.out.println("Title checked");

        //Enabling Orders without Shade for this Sales Organization
        System.out.println("Editing Sales Organisation...");
        editPage.enableCCEOrdersWithoutShade();

        //Save
        System.out.println("'Enable Orders without Shade' flag checked. Saving...");
        editPage.pressSave();
        soPage.waitForElement();

        System.out.println("Saved.");

        //Verifying that checkbox was correctly saved
        soPage.setSalesOrg(DataItems.salesOrgFilterString);
        soPage.pressSearch();
        soPage.waitForElement();
        soPage.pressEdit(row);
        editPage.waitForElement();

        System.out.println("Verifying that checkbox was correctly saved...");

        //Assert.assertEquals(true,editPage.isEOwS_Checked(),"'Enable Orders without Shade' flag is not checked");
        AssertJUnit.assertTrue("'Enable Orders without Shade' flag is not checked", editPage.getEnableOrdersWithoutShade().isSelected());
        System.out.println("Checkbox is enabled! Test pass!");

        //Enabling Orders without Shade to Customers as well


        System.out.print("Navigating to Customer master data...");

        Mst_CustomersPage custPage = mainPage.selectCustomers();
        custPage.waitForElement();

        //Searching for Customer Name: Life Easy Customer
        System.out.print("Searching for Customer Name: Life Easy Customer");
        custPage.setCustomerName(DataItems.customerName);
        custPage.pressSearch();
        custPage.waitForElement();

        //get the row number of above searched customer name
        int row2 = custPage.getRow(DataItems.customerName);

        //Press Edit
        System.out.println("Record found. Editing record...");
        Mst_EditCustomerPage editPage2 = custPage.pressEdit(row2);
        editPage2.waitForElement();
        System.out.println("Edit page reached. Checking title...");

        //Verify that Edit page is displayed
        AssertJUnit.assertTrue("Edit Sales Organisation Page: Title not as expected", editPage2.getBreadcrumb().getText().equals("Customers | Edit Customer"));
        System.out.println("Title checked");

        //Enabling Orders without Shade for this Sales Organization
        System.out.println("Editing Customer...");
        editPage2.enableCCEOrdersWithoutShade();

        //Save
        System.out.println("'Enable Orders without Shade' flag checked. Saving...");
        editPage2.clickSave();
        custPage.waitForElement();

        System.out.println("Saved.");
    }

    @Test //Enable Order without Shade :: Disable Checkbox in Sales Organizations and Customers
            (groups={"CCE"})
    public void EOwS_GC_020() throws Exception {
        //New driver object to control browser
        WebDriver driver = getDriver();

        //New base object to handle log-in and set up
        Cce_Base base = new Cce_Base(driver);

        CCE_MainPage mainPage = base.setUp("Enable Order without Shade on Sales Organizations page", "EOwS_GC_010");

        mainPage.waitForElement();

        System.out.println("Navigating to Sales Organisations Page...");

        //Navigating to Admin -> Master -> Sales Organizations Page
        Mst_SalesOrgPage soPage = mainPage.selectSalesOrg();
        soPage.waitForElement();

        System.out.println("Sales Organisations page reached. Checking title...");

        //Verify that Sales Organisations Page is shown
        AssertJUnit.assertTrue("Sales Organisations Page: Title not as expected", soPage.getBreadcrumb().getText().equals("Sales Organisations"));

        //Input a valid Sales Organization ID51
        soPage.setSalesOrg(DataItems.salesOrgFilterString);

        //Search for the above entered Sales Organization
        System.out.print("Searching for Sales Organization: ID51...");
        soPage.pressSearch();

        soPage.waitForElement();

        //get the row number of Sales Organization searched for
        int row = soPage.getRow(DataItems.salesOrgFilterString);

        //Press Edit
        System.out.println("Record found. Editing record...");
        Mst_EditSalesOrgPage editPage = soPage.pressEdit(row);
        editPage.waitForElement();
        System.out.println("Edit page reached. Checking title...");

        //Verify that Edit page is displayed
        AssertJUnit.assertTrue("Edit Sales Organisation Page: Title not as expected", editPage.getBreadcrumb().getText().equals("Sales Organisations | Edit Sales Organisation"));
        System.out.println("Title checked");

        //Enabling Orders without Shade for this Sales Organization
        System.out.println("Editing Sales Organisation...");
        editPage.disableCCEOrdersWithoutShade();

        //Save
        System.out.println("'Enable Orders without Shade' flag checked. Saving...");
        editPage.pressSave();
        soPage.waitForElement();

        System.out.println("Saved.");

        //Enabling Orders without Shade to Customers as well

        System.out.print(" Navigating to Customer master data...");

        Mst_CustomersPage custPage = mainPage.selectCustomers();
        custPage.waitForElement();

        //Searching for Customer Name: Life Easy Customer
        System.out.print("Searching for Customer Name: Life Easy Customer");
        custPage.setCustomerName(DataItems.customerName);
        custPage.pressSearch();
        custPage.waitForElement();

        //get the row number of above searched customer name
        int row2 = custPage.getRow(DataItems.customerName);

        //Press Edit
        System.out.println("Record found. Editing record...");
        Mst_EditCustomerPage editPage2 = custPage.pressEdit(row2);
        editPage2.waitForElement();
        System.out.println("Edit page reached. Checking title...");

        //Verify that Edit page is displayed
        AssertJUnit.assertTrue("Edit Sales Organisation Page: Title not as expected", editPage2.getBreadcrumb().getText().equals("Customers | Edit Customer"));
        System.out.println("Title checked");

        //Enabling Orders without Shade for this Sales Organization
        System.out.println("Editing Customer...");
        editPage2.disableCCEOrdersWithoutShade();

        //Save
        System.out.println("'Enable Orders without Shade' flag checked. Saving...");
        editPage2.clickSave();
        custPage.waitForElement();

        System.out.println("Saved.");
    }

    @Test //Enable Order without Shade :: Enable Checkbox in Sales Organizations and if activated, the customer master data will have the same option
            (groups={"CCE"})
    public void M_CMD_01() throws Exception {
        //New driver object to control browser
        WebDriver driver = getDriver();

        //New base object to handle log-in and set up
        Cce_Base base = new Cce_Base(driver);

        CCE_MainPage mainPage = base.setUp("Master: Enable Order without Shade on Sales Organizations page", "M_CMD_01");

        mainPage.waitForElement();

        System.out.println("Navigating to Sales Organisations Page...");

        //Navigating to Admin -> Master -> Sales Organizations Page
        Mst_SalesOrgPage soPage = mainPage.selectSalesOrg();
        soPage.waitForElement();

        System.out.println("Sales Organisations page reached.");

        //Input a valid Sales Organization - ID51
        soPage.setSalesOrg(DataItems.salesOrgFilterString);

        //Search for the above entered Sales Organization
        System.out.print("Searching for Sales Organization: ID51...");
        soPage.pressSearch();

        soPage.waitForElement();

        //get the row number of Sales Organization searched for
        int row = soPage.getRow(DataItems.salesOrgFilterString);

        //Press Edit
        System.out.println("Record found on row:"+row+". Editing record...");
        Mst_EditSalesOrgPage editPage = soPage.pressEdit(row);
        editPage.waitForElement();
        System.out.println("Edit page reached. Checking title...");

        //Verify that Edit page is displayed
        AssertJUnit.assertTrue("Edit Sales Organisation Page: Title not as expected", editPage.getBreadcrumb().getText().equals("Sales Organisations | Edit Sales Organisation"));
        System.out.println("Title checked");

        //Enabling Orders without Shade for this Sales Organization
        System.out.println("Editing Sales Organisation...");
        editPage.enableCCEOrdersWithoutShade();

        //Save
        System.out.println("'Enable Orders without Shade' flag checked. Saving...");
        editPage.pressSave();
        soPage.waitForElement();

        System.out.println("Saved.");

        //Verifying that checkbox was correctly saved
        soPage.setSalesOrg(DataItems.salesOrgFilterString);
        soPage.pressSearch();
        soPage.waitForElement();
        soPage.pressEdit(row);
        editPage.waitForElement();

        System.out.println("Verifying that checkbox was correctly saved...");

        //Assert.assertEquals(true,editPage.isEOwS_Checked(),"'Enable Orders without Shade' flag is not checked");
        AssertJUnit.assertTrue("'Enable Orders without Shade' flag is not checked", editPage.getEnableOrdersWithoutShade().isSelected());
        System.out.println("Checkbox is enabled! Test pass!");

        //Enabling Orders without Shade to Customers
        System.out.print("Navigating to Customer master data...");

        Mst_CustomersPage custPage = mainPage.selectCustomers();
        custPage.waitForElement();

        //Searching for Customer Name: Life Easy Customer
        System.out.print("Searching for Customer Name: Life Easy Customer");
        custPage.setCustomerName(DataItems.customerName);
        custPage.pressSearch();
        custPage.waitForElement();

        //get the row number of above searched customer name
        int row2 = custPage.getRow(DataItems.customerName);

        //Press Edit
        System.out.println("Record found. Editing record...");
        Mst_EditCustomerPage editPage2 = custPage.pressEdit(row2);
        editPage2.waitForElement();
        System.out.println("Edit page reached. Checking title...");

        //Verify that Edit page is displayed
        AssertJUnit.assertTrue("Edit Sales Organisation Page: Title not as expected", editPage2.getBreadcrumb().getText().equals("Customers | Edit Customer"));
        System.out.println("Title checked");

        //Enabling Orders without Shade for this Sales Organization
        System.out.println("Editing Customer...");
        //editPage2.enableCCEOrdersWithoutShade();

        By shadeLabel = By.cssSelector("#CustomerEditForm > div:nth-child(2) > table > tbody > tr:nth-child(39) > td:nth-child(1) > label");
        WebElement element = driver.findElement(shadeLabel);
        Assert.assertTrue(element.isDisplayed());

        //Save
        System.out.println("'Enable Orders without Shade' flag checked. Saving...");
        Mst_CustomersPage custPage2 = editPage2.clickSave();
        custPage2.waitForElement();

        System.out.println("Saved.");
    }

    @Test //Enable Order without Shade :: Enable Checkbox in Sales Organizations and if activated, the Orders without shade Flow will be activated for this customer
            (groups={"CCE"})
    public void M_CMD_02() throws Exception {
        //New driver object to control browser
        WebDriver driver = getDriver();

        //New base object to handle log-in and set up
        Cce_Base base = new Cce_Base(driver);

        CCE_MainPage mainPage = base.setUp("Enable Order without Shade on Sales Organizations page", "M_CMD_02");

        mainPage.waitForElement();

        System.out.println("Navigating to Sales Organisations Page...");

        //Navigating to Admin -> Master -> Sales Organizations Page
        Mst_SalesOrgPage soPage = mainPage.selectSalesOrg();
        soPage.waitForElement();

        System.out.println("Sales Organisations page reached. Checking title...");

        //Verify that Sales Organisations Page is shown
        AssertJUnit.assertTrue("Sales Organisations Page: Title not as expected", soPage.getBreadcrumb().getText().equals("Sales Organisations"));

        //Input a valid Sales Organization ID51
        soPage.setSalesOrg(DataItems.salesOrgFilterString);

        //Search for the above entered Sales Organization
        System.out.print("Searching for Sales Organization...");
        soPage.pressSearch();
        soPage.waitForElement();

        //get the row number of Sales Organization searched for
        int row = soPage.getRow(DataItems.salesOrgFilterString);

        //Press Edit
        System.out.println("Record found on row:"+row+". Editing record...");
        Mst_EditSalesOrgPage editPage = soPage.pressEdit(row);
        editPage.waitForElement();
        System.out.println("Edit page reached. Checking title...");

        //Verify that Edit page is displayed
        AssertJUnit.assertTrue("Edit Sales Organisation Page: Title not as expected", editPage.getBreadcrumb().getText().equals("Sales Organisations | Edit Sales Organisation"));
        System.out.println("Title checked");

        //Enabling Orders without Shade for this Sales Organization
        System.out.println("Editing Sales Organisation...");
        editPage.enableCCEOrdersWithoutShade();

        //Save
        System.out.println("'Enable Orders without Shade' flag checked. Saving...");
        editPage.pressSave();
        soPage.waitForElement();

        System.out.println("Saved.");

        //Verifying that checkbox was correctly saved
        soPage.setSalesOrg(DataItems.salesOrgFilterString);
        soPage.pressSearch();
        soPage.waitForElement();
        soPage.pressEdit(row);
        editPage.waitForElement();

        System.out.println("Verifying that checkbox was correctly saved...");

        //Assert.assertEquals(true,editPage.isEOwS_Checked(),"'Enable Orders without Shade' flag is not checked");
        AssertJUnit.assertTrue("'Enable Orders without Shade' flag is not checked", editPage.getEnableOrdersWithoutShade().isSelected());
        System.out.println("Checkbox is enabled! Test pass!");

        //Enabling Orders without Shade to Customers
        System.out.print("Navigating to Customer master data...");

        Mst_CustomersPage custPage = mainPage.selectCustomers();
        custPage.waitForElement();

        //Searching for Customer Name: Life Easy Customer
        System.out.print("Searching for Customer Name: Life Easy Customer");
        custPage.setCustomerName(DataItems.customerName);
        custPage.pressSearch();
        custPage.waitForElement();

        //get the row number of above searched customer name
        int row2 = custPage.getRow(DataItems.customerName);

        //Press Edit
        System.out.println("Record found. Editing record...");
        Mst_EditCustomerPage editPage2 = custPage.pressEdit(row2);
        editPage2.waitForElement();
        System.out.println("Edit page reached. Checking title...");

        //Verify that Edit page is displayed
        AssertJUnit.assertTrue("Edit Sales Organisation Page: Title not as expected", editPage2.getBreadcrumb().getText().equals("Customers | Edit Customer"));
        System.out.println("Title checked");

        //Enabling Orders without Shade for this Sales Organization
        System.out.println("Editing Customer...");
        editPage2.enableCCEOrdersWithoutShade();

        //Save
        System.out.println("'Enable Orders without Shade' flag checked. Saving...");
        Mst_CustomersPage custPage2 = editPage2.clickSave();
        custPage2.waitForElement();

        System.out.println("Saved.");
    }

}
