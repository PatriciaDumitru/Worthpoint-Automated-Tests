package com.coats.selenium.tests;

import AutomationFramework.CommonTask;
import AutomationFramework.DataItems;
import PageObjects.*;
import com.coats.selenium.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import java.io.IOException;

/**
 * Created by dion on 09.03.2016.
 */
public class CCE_Upload_Sample_Methods_Test extends DriverFactory {

    @Test //Order status page :: page and filter checks, reset function, export data
            (groups = {"CCE"})
    public void US01() throws IOException, Exception {

        //New driver object to control browser
        WebDriver driver = getDriver();

        //New base object to handle log-in and set up
        Cce_Base base = new Cce_Base(driver);
        CCE_MainPage ccePage = base.setUp("Upload Order Samples: Page and filter checks, add/edit/delete/export features", "G_CCE_Unknown");
        ccePage.waitForLoad();

        System.out.println("Navigating to Upload Order Samples...");
        //new upload order sample page
        CCE_UploadOrderSamplesPage uosPage = ccePage.pressUploadOrderSamples();
        uosPage.waitForLoad();

        System.out.println("Upload Order Sample page loaded. Navigating to Sample Upload Failed Files Page...");
        CCE_SampleUploadFailedFilesPage suffPage2 = ccePage.pressSampleUploadFailedFiles();
        suffPage2.waitForElement();

        System.out.println("Sample Upload Failed Files page reached. Checking title...");
        AssertJUnit.assertTrue("Sales Organisations Page: Title not as expected", suffPage2.getBreadcrumb2().getText().equals("Sample Upload Failed Files"));
        System.out.println("Title checked");
        suffPage2.assertBaseElements();

        System.out.println("Checking fields...");
        suffPage2.checkFields();
        System.out.println("Fields checked.");

        //Go Masters -> Sales Org and edit Sales Org (MX51)
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

        soPage.setSalesOrg("MX51");
        soPage.pressSearch();
        soPage.waitForElement();

        int row = soPage.getRow("MX51");
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

        //Check that "Enable CCE order upload" flag is present
        System.out.println("Fields checked. Editing Sales Organisation...");
        editPage.enableCCEOrderUploadCheckBox();

        //Save
        System.out.println("'Enable CCE order upload' flag checked. Saving...");
        editPage.pressSave();
        soPage.waitForElement();

        System.out.println("Saved. Navigating to Customer master data...");

        //Go Masters -> Customers and edit Customer(Life Easy Customer)
        Mst_CustomersPage custPage = ccePage.selectCustomers();
        custPage.waitForElement();

        custPage.setCustomerName("AMMAR APPAREL II S DE RL DE CV");
        custPage.pressSearch();
        custPage.waitForElement();

        int row2 = custPage.getRow("AMMAR APPAREL II S DE RL DE CV");
        //AssertJUnit.assertFalse("Customers Page: Customers Page not present in table after creation", row2 == -1);
        System.out.println("Record found. Editing record...");

        Mst_EditCustomerPage editPage2 = custPage.pressEdit(row2);
        editPage2.waitForElement();

        System.out.println("Edit page reached.");

        //Activate "CCE Order Upload" flag for Requester in the Customer (compras.calkini@gkmexico.com)
        By lifeEasyOrderUploadCheckBox = By.id("Requester0SampleUpload");
        CommonTask.setCheckBox(driver, lifeEasyOrderUploadCheckBox);
        System.out.println("'CCE order upload' flag checked for customer Life Easy. Saving...");
        editPage2.pressSave();
        custPage.waitForElement();
        System.out.println("Saved. Log out...");

        //Logout of globalAdmin ang login with Requster(lifeeasy@customer.com)
        WebElement logout = driver.findElement(By.cssSelector("html body div#container div#header div.top span.right span.logout a"));
        logout.click();

        System.out.println("Logging in compras.calkini@gkmexico.com...");

        //new login page
        WBA_LoginPage liPage = new WBA_LoginPage(driver);

        //login with valid coats details
        WBA_ContinuePage contPage = liPage.loginAs(DataItems.susstUsername2, DataItems.susstPassword2);

        System.out.println("Logged in. Asserting continue page elements are displayed...");

        //Wait for page to load
        WebElement waitForLoad = new WebDriverWait(driver, DataItems.shortWait).until(ExpectedConditions.visibilityOf(WBA_ContinuePage.getMainImage()));

        //Assert that all Continue Page elements load correctly
        AssertJUnit.assertTrue(contPage.getWelcomeImage().isDisplayed());
        AssertJUnit.assertTrue(contPage.getMainImage().isDisplayed());
        AssertJUnit.assertTrue(contPage.getContinueImage().isDisplayed());

        System.out.println("Assertions successful. Continuing to selection page...");

        //Press continue to arrive at WBA selection page
        WBA_SelectionPage selectionPage = contPage.pressContinue();
        selectionPage.waitForElement();

        System.out.println("Selection page reached. Asserting elements are displayed...");

        //Scroll down and take another screenshot
        Actions action = new Actions(driver);
        action.sendKeys(Keys.PAGE_DOWN).build().perform();

        //Assert that all WBA selection page elements load correctly
        AssertJUnit.assertTrue(selectionPage.getMainImage().isDisplayed());
        AssertJUnit.assertTrue(selectionPage.getCceCircle().isDisplayed());
        AssertJUnit.assertTrue(selectionPage.getEcommCircle().isDisplayed());

        System.out.println("Assertions successful. Selecting CCE...");

        //Press CCE
        CCE_MainPage ccePage2 = selectionPage.pressCce();
        boolean waitForLoad2 = new WebDriverWait(driver, DataItems.shortWait).until(ExpectedConditions.titleIs(DataItems.ccePageTitle));

        System.out.println("CCE main page reached. Asserting elements are displayed...");

        //Briefly assert that CCE Page elements load correctly, proving the CCE link has worked
        AssertJUnit.assertTrue(ccePage2.getCoatsLogo().isDisplayed());
        AssertJUnit.assertTrue(ccePage2.getNavBar().isDisplayed());
        AssertJUnit.assertTrue(ccePage2.getMainImage().isDisplayed());

        System.out.println("Navigating to Upload Order Samples...");
        CCE_UploadOrderSamplesPage uosPage2 = ccePage.pressUploadOrderSamples();
        uosPage2.waitForLoad();
        System.out.println("Upload Order Sample page loaded.");
    }



    @Test //Order status page :: page and filter checks, reset function, export data
            (groups = {"CCE"})
    public void US02() throws IOException, Exception {

        //New driver object to control browser
        WebDriver driver = getDriver();

        //New base object to handle log-in and set up
        Cce_Base base = new Cce_Base(driver);
        CCE_MainPage ccePage = base.setUp("Upload Order Samples: Page and filter checks, add/edit/delete/export features", "G_CCE_Unknown");
        ccePage.waitForLoad();

        System.out.println("Navigating to Upload Order Samples...");
        //new upload order sample page
        CCE_UploadOrderSamplesPage uosPage = ccePage.pressUploadOrderSamples();
        uosPage.waitForLoad();

        System.out.println("Upload Order Sample page loaded. Navigating to Sample Upload Failed Files Page...");
        CCE_SampleUploadFailedFilesPage suffPage2 = ccePage.pressSampleUploadFailedFiles();
        suffPage2.waitForElement();

        System.out.println("Sample Upload Failed Files page reached. Checking title...");
        AssertJUnit.assertTrue("Sales Organisations Page: Title not as expected", suffPage2.getBreadcrumb2().getText().equals("Sample Upload Failed Files"));
        System.out.println("Title checked");
        suffPage2.assertBaseElements();

        System.out.println("Checking fields...");
        suffPage2.checkFields();
        System.out.println("Fields checked.");

        //Go Masters -> Sales Org and edit Sales Org (MX51)
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

        soPage.setSalesOrg("MX51");
        soPage.pressSearch();
        soPage.waitForElement();

        int row = soPage.getRow("MX51");
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

        //Uncheck that "Enable CCE order upload" flag is present
        System.out.println("Fields checked. Editing Sales Organisation...");
        editPage.disableCCEOrderUploadCheckBox();

        //Save
        System.out.println("'Disable CCE order upload' flag checked. Saving...");
        editPage.pressSave();
        soPage.waitForElement();

        System.out.println("Saved. Navigating to Customer master data...");

        //Go Masters -> Customers and edit Customer(Life Easy Customer)
        Mst_CustomersPage custPage = ccePage.selectCustomers();
        custPage.waitForElement();

        custPage.setCustomerName("AMMAR APPAREL II S DE RL DE CV");
        custPage.pressSearch();
        custPage.waitForElement();

        int row2 = custPage.getRow("AMMAR APPAREL II S DE RL DE CV");
        //AssertJUnit.assertFalse("Customers Page: Customers Page not present in table after creation", row2 == -1);
        System.out.println("Record found. Editing record...");

        Mst_EditCustomerPage editPage2 = custPage.pressEdit(row2);
        editPage2.waitForElement();

        System.out.println("Edit page reached.");

        //Activate "CCE Order Upload" flag for Requester in the Customer (compras.calkini@gkmexico.com)
        By lifeEasyOrderUploadCheckBox = By.id("Requester0SampleUpload");
        CommonTask.setCheckBox(driver, lifeEasyOrderUploadCheckBox);
        System.out.println("'CCE order upload' flag unchecked for customer Life Easy. Saving...");
        editPage2.pressSave();
        custPage.waitForElement();
        System.out.println("Saved. Log out...");

        //Logout of globalAdmin ang login with Requster(compras.calkini@gkmexico.com)
        WebElement logout = driver.findElement(By.cssSelector("html body div#container div#header div.top span.right span.logout a"));
        logout.click();

        System.out.println("Logging in compras.calkini@gkmexico.com...");

        //new login page
        WBA_LoginPage liPage = new WBA_LoginPage(driver);

        //login with valid coats details
        WBA_ContinuePage contPage = liPage.loginAs(DataItems.susstUsername2, DataItems.susstPassword2);

        System.out.println("Logged in. Asserting continue page elements are displayed...");

        //Wait for page to load
        WebElement waitForLoad = new WebDriverWait(driver, DataItems.shortWait).until(ExpectedConditions.visibilityOf(WBA_ContinuePage.getMainImage()));

        //Assert that all Continue Page elements load correctly
        AssertJUnit.assertTrue(contPage.getWelcomeImage().isDisplayed());
        AssertJUnit.assertTrue(contPage.getMainImage().isDisplayed());
        AssertJUnit.assertTrue(contPage.getContinueImage().isDisplayed());

        System.out.println("Assertions successful. Continuing to selection page...");

        //Press continue to arrive at WBA selection page
        WBA_SelectionPage selectionPage = contPage.pressContinue();
        selectionPage.waitForElement();

        System.out.println("Selection page reached. Asserting elements are displayed...");

        //Scroll down and take another screenshot
        Actions action = new Actions(driver);
        action.sendKeys(Keys.PAGE_DOWN).build().perform();

        //Assert that all WBA selection page elements load correctly
        AssertJUnit.assertTrue(selectionPage.getMainImage().isDisplayed());
        AssertJUnit.assertTrue(selectionPage.getCceCircle().isDisplayed());
        AssertJUnit.assertTrue(selectionPage.getEcommCircle().isDisplayed());

        System.out.println("Assertions successful. Selecting CCE...");

        //Press CCE
        CCE_MainPage ccePage2 = selectionPage.pressCce();
        boolean waitForLoad2 = new WebDriverWait(driver, DataItems.shortWait).until(ExpectedConditions.titleIs(DataItems.ccePageTitle));

        System.out.println("CCE main page reached. Asserting elements are displayed...");

        //Briefly assert that CCE Page elements load correctly, proving the CCE link has worked
        AssertJUnit.assertTrue(ccePage2.getCoatsLogo().isDisplayed());
        AssertJUnit.assertTrue(ccePage2.getNavBar().isDisplayed());
        AssertJUnit.assertTrue(ccePage2.getMainImage().isDisplayed());

        System.out.println("Trying to navigate to Upload Order Samples...");

        //This assertion verifies that there are no matching elements in the DOM and returns the value of Zero,
        // so the assertion passes when the element is not present. Also it would fail if it was present.
        Assert.assertEquals(0, driver.findElements(By.cssSelector("a[href*='sampleupload']")).size());
    }
}


