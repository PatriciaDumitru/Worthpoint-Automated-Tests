package com.coats.selenium.tests;

import AutomationFramework.DataItems;
import AutomationFramework.Wait;
import PageObjects.*;
import com.coats.selenium.DriverFactory;
import static com.coats.selenium.DriverFactory.getDriver;

import com.coats.selenium.tests.Cce_Base;
import com.google.common.base.Verify;
import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.poi.util.SystemOutLogger;
import org.openqa.selenium.*;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;


public class Ecomm_ReportingPaginationChecks_Test extends DriverFactory{

    @Test //Reporting Check Pagination Invoice
            (groups = {"eComm"})
    public void Rep_CP_I() throws IOException, InterruptedException, Exception {
        //new driver instance
        WebDriver driver = getDriver();

        //New eComm base test to handle log-in and navigation
        Ecomm_Base baseTest = new Ecomm_Base(driver);
        Ecomm_MainPage eCommPage = baseTest.setUp("Rep_CP_I", "Invoices Page Reporting Check Pagination Invoice ");

        //Navigate to Invoice page
        System.out.println("Navigating to Invoice Page...");
        driver.get(DataItems.invoicePage);

        Ecomm_ReportingPaginationChecks_Page rpc = new Ecomm_ReportingPaginationChecks_Page();

        //Check that pagination is not available without date filter
        AssertJUnit.assertFalse("Pagination is displayed and should not",rpc.paginationIsDisplayed(driver));

        //Set date From
        System.out.println("Setting Date From and checking that pagination is available..");
        rpc.setOrderDateFrom(driver);
        rpc.searchFilterInvoice(driver);
        AssertJUnit.assertTrue("Pagination is not displayed and should",rpc.paginationIsDisplayed(driver));

        //Reset Filter
        rpc.resetFilterInvocie(driver);

        //Set date To
        System.out.println("Setting Date To and checking that pagination is available..");
        rpc.setOrderDateTo(driver);
        rpc.searchFilterInvoice(driver);
        AssertJUnit.assertTrue("Pagination is not displayed and should",rpc.paginationIsDisplayed(driver));

        //Reset Filter
        rpc.resetFilterInvocie(driver);

        //Set both Dates From and To
        System.out.println("Setting Date To and From and checking that pagination is available...");
        rpc.setOrderDateFrom(driver);
        rpc.setOrderDateTo(driver);
        rpc.searchFilterInvoice(driver);
        AssertJUnit.assertTrue("Pagination is not displayed and should",rpc.paginationIsDisplayed(driver));

        //Reseting Filter and checking pagination is not available
        System.out.println("Resetting filter and check pagination is not available...");
        rpc.resetFilterInvocie(driver);
        AssertJUnit.assertFalse("Pagination is not displayed and should",rpc.paginationIsDisplayed(driver));

    }

    @Test //Reporting Check Pagination Delivery Notes
            (groups = {"eComm"})
    public void Rep_CP_DN() throws IOException, InterruptedException, Exception {
        //new driver instance
        WebDriver driver = getDriver();

        //New eComm base test to handle log-in and navigation
        Ecomm_Base baseTest = new Ecomm_Base(driver);
        Ecomm_MainPage eCommPage = baseTest.setUp("Rep_CP_DN", "Reporting Check Pagination Delivery Notes");

        //Navigate to Invoice page
        System.out.println("Navigating to Delivery Notes...");
        driver.get(DataItems.deliveyNotesPage);

        Ecomm_ReportingPaginationChecks_Page rpc = new Ecomm_ReportingPaginationChecks_Page();

        //Check that pagination is not available without date filter
        AssertJUnit.assertFalse("Pagination is displayed and should not",rpc.paginationIsDisplayed(driver));

        //Set date From
        System.out.println("Setting Date From and checking that pagination is available..");
        rpc.setDelDateFromField(driver);
        rpc.searchFilterDeliveryNotes(driver);
        AssertJUnit.assertTrue("Pagination is not displayed and should",rpc.paginationIsDisplayed(driver));

        //Reset Filter
        rpc.resetFilterDeliveryNotes(driver);

        //Set date To
        System.out.println("Setting Date To and checking that pagination is available..");
        rpc.setDelDateToField(driver);
        rpc.searchFilterDeliveryNotes(driver);
        AssertJUnit.assertTrue("Pagination is not displayed and should",rpc.paginationIsDisplayed(driver));

        //Reset Filter
        rpc.resetFilterDeliveryNotes(driver);

        //Set both Dates From and To
        System.out.println("Setting Date To and From and checking that pagination is available...");
        rpc.setDelDateFromField(driver);
        rpc.setDelDateToField(driver);
        rpc.searchFilterDeliveryNotes(driver);
        AssertJUnit.assertTrue("Pagination is not displayed and should",rpc.paginationIsDisplayed(driver));

        //Reseting Filter and checking pagination is not available
        System.out.println("Resetting filter and check pagination is not available...");
        rpc.resetFilterDeliveryNotes(driver);
        AssertJUnit.assertFalse("Pagination is not displayed and should",rpc.paginationIsDisplayed(driver));

    }

    @Test //Reporting Check Pagination Summary of Purchase
            (groups = {"eComm"})
    public void Rep_CP_SoP() throws IOException, InterruptedException, Exception {
        //new driver instance
        WebDriver driver = getDriver();

        //New eComm base test to handle log-in and navigation
        Ecomm_Base baseTest = new Ecomm_Base(driver);
        Ecomm_MainPage eCommPage = baseTest.setUp("Rep_CP_SoP", "Reporting Check Pagination Summary of Purchase");

        //Navigate to Invoice page
        System.out.println("Navigating to Summary of Purchase...");
        driver.get(DataItems.summaryOfPurchasePage);

        Ecomm_ReportingPaginationChecks_Page rpc = new Ecomm_ReportingPaginationChecks_Page();

        //Check that pagination is not available without date filter
        AssertJUnit.assertFalse("Pagination is displayed and should not",rpc.paginationIsDisplayed(driver));

        //Set date From
        System.out.println("Setting Date From and checking that pagination is available..");
        rpc.setPurchaseDateFromField(driver);
        rpc.searchFilterSoP(driver);
        AssertJUnit.assertTrue("Pagination is not displayed and should",rpc.paginationIsDisplayed(driver));

        //Reset Filter
        rpc.resetFilterSoP(driver);

        //Set date To
        System.out.println("Setting Date To and checking that pagination is available..");
        rpc.setPurchaseDateToField(driver);
        rpc.searchFilterSoP(driver);
        AssertJUnit.assertTrue("Pagination is not displayed and should",rpc.paginationIsDisplayed(driver));

        //Reset Filter
        rpc.resetFilterSoP(driver);

        //Set both Dates From and To
        System.out.println("Setting Date To and From and checking that pagination is available...");
        rpc.setPurchaseDateFromField(driver);
        rpc.setPurchaseDateToField(driver);
        rpc.searchFilterSoP(driver);
        AssertJUnit.assertTrue("Pagination is not displayed and should",rpc.paginationIsDisplayed(driver));

        //Resetting Filter and checking pagination is not available
        System.out.println("Resetting filter and check pagination is not available...");
        rpc.resetFilterSoP(driver);
        AssertJUnit.assertFalse("Pagination is not displayed and should",rpc.paginationIsDisplayed(driver));
    }

    @Test //Reporting Check Pagination Outstanding Payments
            (groups = {"eComm"})
    public void Rep_CP_OP() throws IOException, InterruptedException, Exception {
        //new driver instance
        WebDriver driver = getDriver();

        //New eComm base test to handle log-in and navigation
        Ecomm_Base baseTest = new Ecomm_Base(driver);
        Ecomm_MainPage eCommPage = baseTest.setUp("Rep_CP_OP", "Reporting Check Pagination Outstanding Payments");

        //Navigate to Invoice page
        System.out.println("Navigating to Outstanding Payments...");
        driver.get(DataItems.outstandingPaymentsPage);

        Ecomm_ReportingPaginationChecks_Page rpc = new Ecomm_ReportingPaginationChecks_Page();

        //Check that pagination is not available without date filter
        AssertJUnit.assertFalse("Pagination is displayed and should not",rpc.paginationIsDisplayed(driver));

        //Set date From
        System.out.println("Setting Date From and checking that pagination is available..");
        rpc.set30Option(driver);
        rpc.searchFilterOP(driver);
        AssertJUnit.assertTrue("Pagination is not displayed and should",rpc.paginationIsDisplayed(driver));

        //Reset Filter
        rpc.resetFilterOP(driver);

        //Set date From
        System.out.println("Setting Date From and checking that pagination is available..");
        rpc.set60Option(driver);
        rpc.searchFilterOP(driver);
        AssertJUnit.assertTrue("Pagination is not displayed and should",rpc.paginationIsDisplayed(driver));

        //Reset Filter
        rpc.resetFilterOP(driver);

        //Set date From
        System.out.println("Setting Date From and checking that pagination is available..");
        rpc.set90Option(driver);
        rpc.searchFilterOP(driver);
        AssertJUnit.assertTrue("Pagination is not displayed and should",rpc.paginationIsDisplayed(driver));

        //Reset Filter
        rpc.resetFilterOP(driver);



        //Resetting Filter and checking pagination is not available
        System.out.println("Resetting filter and check pagination is not available...");
        rpc.resetFilterSoP(driver);
        AssertJUnit.assertFalse("Pagination is not displayed and should",rpc.paginationIsDisplayed(driver));
    }

    @Test //Reporting Check Pagination Order Approval History
            (groups = {"eComm"})
    public void Rep_CP_OAH() throws IOException, InterruptedException, Exception {
        //new driver instance
        WebDriver driver = getDriver();

        //New eComm base test to handle log-in and navigation
        Ecomm_Base baseTest = new Ecomm_Base(driver);
        Ecomm_MainPage eCommPage = baseTest.setUp("Rep_CP_OAH", "Reporting Check Pagination Order Approval History");

        //Navigate to Invoice page
        System.out.println("Navigating to Order Approval History Page...");
        driver.get(DataItems.orderApprovalHistory);

        Ecomm_ReportingPaginationChecks_Page rpc = new Ecomm_ReportingPaginationChecks_Page();

        //Check that pagination is not available without date filter
        AssertJUnit.assertFalse("Pagination is displayed and should not",rpc.paginationIsDisplayed(driver));

        //Set date From
        System.out.println("Setting Date From and checking that pagination is available..");
        rpc.setCreationDateFromField(driver);
        rpc.searchFilterOAH(driver);
        AssertJUnit.assertTrue("Pagination is not displayed and should",rpc.paginationIsDisplayed(driver));

        //Reset Filter
        rpc.resetFilterOAH(driver);

        //Set date To
        System.out.println("Setting Date To and checking that pagination is available..");
        rpc.setCreationDateFromField(driver);
        rpc.searchFilterOAH(driver);
        AssertJUnit.assertTrue("Pagination is not displayed and should",rpc.paginationIsDisplayed(driver));

        //Reset Filter
        rpc.resetFilterOAH(driver);

        //Set both Dates From and To
        System.out.println("Setting Date To and From and checking that pagination is available...");
        rpc.setCreationDateFromField(driver);
        rpc.setCreationDateToField(driver);
        rpc.searchFilterOAH(driver);
        AssertJUnit.assertTrue("Pagination is not displayed and should",rpc.paginationIsDisplayed(driver));

        //Reseting Filter and checking pagination is not available
        System.out.println("Resetting filter and check pagination is not available...");
        rpc.resetFilterOAH(driver);
        AssertJUnit.assertFalse("Pagination is not displayed and should",rpc.paginationIsDisplayed(driver));

    }

}