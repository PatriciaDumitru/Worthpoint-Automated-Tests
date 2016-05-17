package com.coats.selenium.tests;

import AutomationFramework.CommonTask;
import AutomationFramework.DataItems;
import PageObjects.*;
import com.coats.selenium.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import java.io.IOException;

/**
 * Created by: Stefan on 12.04.2016.
 * Description: This class contains tests for eComm > Reports User Interface verifications. WBA-562,WBA-563,WBA-564 and WBA-566
 * changed the User Interface for reports and the following test will cover the testing of reports changes.
 * Contributors:
 */
public class EComm_Reports_UI_Checks_Test extends DriverFactory {

    @Test //Reporting - Invoice UI screen changes - WBA-562
            (groups = {"eComm"})
    public void Rep_I() throws IOException, InterruptedException, Exception {
        //new driver instance
        WebDriver driver = getDriver();

        //New eComm base test to handle log-in and navigation
        Ecomm_Base baseTest = new Ecomm_Base(driver);
        Ecomm_MainPage eCommPage = baseTest.setUp("Rep_I", "Reporting - Invoice UI screen changes","admin@coats.com", "superadmin@coats");

        //Navigate to Invoice page
        System.out.println("Navigating to Invoice Page...");
        driver.get(DataItems.invoicePage);

        Ecomm_InvoicesPage invoice = new Ecomm_InvoicesPage(driver);

        /**
        Verifying the label of Clear button - renamed from reset
         */
        System.out.println("Checking \"Clear\" button label...");
        AssertJUnit.assertTrue("Incorrect button label!",invoice.getClearButtonLabel().equals("Clear"));
        System.out.println("Label correct!");

        /**
        Verifying the Table Header
         */
        System.out.println("Table header:"+invoice.getTableHeader());
        String tableHeader = invoice.getTableHeader().toString();
        //Actions|Invoice Creation Date|Invoice No.|Status|No. of lines|Invoice Quantity|Customer PO number|Invoice Amount|
        AssertJUnit.assertTrue("Incorrect table columns!",tableHeader.equals("Action|Invoice number|Payer name|Invoice amount|Currency|Status|Payment date|Payment due date|Business principle name|"));
        System.out.println("Table Header correct!");

        /**
         * Verify Pagination
         */
        CommonTask.checkReportsPagination(driver);

        System.out.println("Test PASSED!");
    }

    @Test //Reporting - Delivery Notes UI screen changes - WBA-563
            (groups = {"eComm"})
    public void Rep_DN() throws IOException, InterruptedException, Exception {
        //new driver instance
        WebDriver driver = getDriver();

        //New eComm base test to handle log-in and navigation
        Ecomm_Base baseTest = new Ecomm_Base(driver);
        Ecomm_MainPage eCommPage = baseTest.setUp("Rep_DN", "Reporting - Delivery Notes UI screen changes","admin@coats.com", "superadmin@coats");

        //Navigate to Delivery Notes page
        System.out.println("Navigating to Delivery Notes page...");
        driver.get(DataItems.deliveyNotesPage);

        Ecomm_DeliveryNotesPage dn = new Ecomm_DeliveryNotesPage(driver);

        /**
         Verifying the label of Clear button - renamed from reset
         */
        System.out.println("Checking \"Clear\" button label...");
        AssertJUnit.assertTrue("Incorrect button label!",dn.getClearButtonLabel().equals("Clear")); //"Clear"
        System.out.println("Label correct!");

        /**
         Verifying the Table Header
         */
        System.out.println(dn.getTableHeader());
        String tableHeader = dn.getTableHeader().toString();
        //Actions|Delivery Date|Delivery Note No.|Delivered Quantity|Customer PO number|Requester Name|
        AssertJUnit.assertTrue("Incorrect table columns!",tableHeader.equals("Action|Delivery Note No.|Ship To Party Name|Delivery Date|Delivery QTY|Requester Name|Business Principle Name|"));
        System.out.println("Table Header correct!");

        /**
         * Verify Pagination
         */
        CommonTask.checkReportsPagination(driver);

        System.out.println("Test PASSED!");
    }

    @Test //Reporting - Summary of Purchases UI screen changes - change name to Purchase Order Summary - WBA-564
            (groups = {"eComm"})
    public void Rep_POSR() throws IOException, InterruptedException, Exception {
        //new driver instance
        WebDriver driver = getDriver();

        //New eComm base test to handle log-in and navigation
        Ecomm_Base baseTest = new Ecomm_Base(driver);
        Ecomm_MainPage eCommPage = baseTest.setUp("Rep_POSR", "Reporting - Summary of Purchases UI screen changes - change name to Purchase Order Summary","admin@coats.com", "superadmin@coats");

        //Navigate to Purchase Order Summary
        System.out.println("Navigating to Purchase Order Summary page...");
        driver.get(DataItems.summaryOfPurchasePage);

        Ecomm_SummaryOfPurchasePage posr = new Ecomm_SummaryOfPurchasePage(driver);

        /**
         Verifying the label of Clear button - renamed from reset
         */
        System.out.println("Checking \"Clear\" button label...");
        AssertJUnit.assertTrue("Incorrect button label!",posr.getClearButtonLabel().equals("Clear")); //"Clear"
        System.out.println("Label correct!");

        /**
         Verifying the Table Header
         */
        System.out.println(posr.getTableHeader());
        String tableHeader = posr.getTableHeader().toString();
        //Action|Purchase Date|Customer PO number.|Requester Name|
        AssertJUnit.assertTrue("Incorrect table columns!",tableHeader.equals("Action|Customer PO No|eComm Order No|SAP Order No.|Order Type|Payer Name|Net Value|Currency|Business Principle Name|"));
        System.out.println("Table Header correct!");

        /**
         * Verify Pagination
         */
        CommonTask.checkReportsPagination(driver);

        System.out.println("Test PASSED!");
    }

    @Test //My Reports UI screen changes - WBA-566
            (groups = {"eComm"})
    public void Rep_UI() throws IOException, InterruptedException, Exception {
        /**
         * Ensure there is no saved report for this user when starting test
         */
        //new driver instance
        WebDriver driver = getDriver();

        //New eComm base test to handle log-in and navigation
        Ecomm_Base baseTest = new Ecomm_Base(driver);
        Ecomm_MainPage eCommPage = baseTest.setUp("Rep_UI", "My Reports UI screen changes","admin@coats.com", "superadmin@coats");

        //Navigate to Advanced Reports
        System.out.println("Navigating to Invoice Page...");
        driver.get(DataItems.advancedReports);

        Ecomm_AdvancedReportsPage advRep = new Ecomm_AdvancedReportsPage(driver);
        /**
         Verifying the breadcrumb - renamed from Reports | My Report to Reports | Advanced Reports
         */
        System.out.println("Checking breadcrumb...");
        AssertJUnit.assertTrue("Incorrect breadcrumb!",advRep.getBreadcrumb().getText().equals("Reports | Advanced Reports"));

        //Create New Report
        System.out.println("Creating a new report...");
        Ecomm_CreateNewReportPage cnrp = advRep.createNewReport();

        /**
         Verifying the breadcrumb - Reports | Advanced Reports| New Report
         */
        AssertJUnit.assertTrue("***Incorrect breadcrumb!",cnrp.getBreadcrumb().getText().equals("Reports | Advanced Reports | New Report"));

        cnrp.setCreationDateFrom("2016-02-24 00:00");

        cnrp.selectDateRange("Last 90 days");

        cnrp.selectRepCriteria("Custom Fields");

        cnrp.selectAllFromOrders();
        cnrp.selectAllDeliveryNotes();

        //Export report and verify
        Ecomm_ExportDownloadPage exportPage = cnrp.pressExport();
        exportPage.waitForDownloadCompletion();
        System.out.println("Export pressed, download completed.");

        cnrp.pressSaveReport();

        CommonTask.waitForOverlay(driver);

        //Input report name
        cnrp.inputReportName("Rep1");
        cnrp.saveRep();

        /**
         * Verify flash message
         */
        AssertJUnit.assertEquals("***Incorrect Flash Message!","Message:\n" + "Your Report has been saved",cnrp.getFlashMessageText());
        System.out.println("Report Rep1 created!");

        //Navigate again to Advanced Reports in order to verify the saved report
        System.out.println("Navigating to Invoice Page...");
        driver.get(DataItems.advancedReports);

        Ecomm_AdvancedReportsPage advRep2 = new Ecomm_AdvancedReportsPage(driver);


        //Go to Saved Reports

        /**
         * Verify that saved reports is displayed
         */
        advRep2.checkSavedReports();


        /**
         * Verify the Saved Report exist
         */
        advRep2.clickSavedReports();
        AssertJUnit.assertTrue(advRep2.searchSavedRepName("Rep1"));
        //AssertJUnit.assertTrue("***Incorrect Saved Report Name!",advRep2.getSavedRepName().contentEquals("Rep1"));

        /**
         * Crate new Report based on Rep1 template
         */
        //Open this saved report and create a new one

        Ecomm_CreateNewReportPage cnrp2 = advRep2.selectSavedReport("Rep1");
        System.out.println("Creating a new report...");

        cnrp2.selectAllFromInvoices();
        cnrp2.pressSaveReport();

        CommonTask.waitForOverlay(driver);
        //Input report name
        cnrp2.inputReportName("Rep2");
        cnrp2.saveRep();

        /**
         * Verify flash message
         */
        AssertJUnit.assertEquals("***Incorrect Flash Message!","Message:\n" + "Your Report has been saved",cnrp.getFlashMessageText());
        System.out.println("Report Rep2 created!");

        //Navigate again to Advanced Reports in order to delete one saved report
        System.out.println("Navigating to Advanced Reports...");
        driver.get(DataItems.advancedReports);

        Ecomm_AdvancedReportsPage advRep3 = new Ecomm_AdvancedReportsPage(driver);

        advRep3.clickSavedReports();

        //Delete first saved report
        System.out.println("Deleting previously created reports...");
        advRep3.deleteSavedReport("Rep1");

        /**
         * Verify Flash Message
         */
        AssertJUnit.assertEquals("***Incorrect Flash Message!","Saved Report has been deleted",advRep3.getFlashMessageText());
        System.out.println("Saved Report has been deleted!");

        //Navigate again to Advanced Reports in order to delete one saved report
        System.out.println("Navigating to Advanced Reports...");
        driver.get(DataItems.advancedReports);

        Ecomm_AdvancedReportsPage advRep4 = new Ecomm_AdvancedReportsPage(driver);

        advRep4.clickSavedReports();
        advRep4.deleteSavedReport("Rep2");

        /**
         * Verify Flash Message
         */
        AssertJUnit.assertEquals("***Incorrect Flash Message!","Saved Report has been deleted",advRep3.getFlashMessageText());
        System.out.println("Saved Report has been deleted!");


        System.out.println("Test PASSED!");

    }
}
