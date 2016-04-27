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
 * Created by Stefan on 12.04.2016.
 */
public class EComm_Reports_UI_Checks_Test extends DriverFactory {

    @Test //Reporting - Invoice UI screen changes - WBA-562
            (groups = {"eComm"})
    public void Rep_I() throws IOException, InterruptedException, Exception {
        //new driver instance
        WebDriver driver = getDriver();

        //New eComm base test to handle log-in and navigation
        Ecomm_Base baseTest = new Ecomm_Base(driver);
        Ecomm_MainPage eCommPage = baseTest.setUp("Rep_I", "Reporting - Invoice UI screen changes");

        //Navigate to Invoice page
        System.out.println("Navigating to Invoice Page...");
        driver.get(DataItems.invoicePage);

        Ecomm_InvoicesPage invoice = new Ecomm_InvoicesPage(driver);

        /**
        Verifying the label of Clear button - renamed from reset
         */
        System.out.println("Checking \"Clear\" button label...");
        AssertJUnit.assertTrue("Incorrect button label!",invoice.getClearButtonLabel().equals("Reset")); //"Clear"
        System.out.println("Label correct!");

        /**
        Verifying the Table Header
         */
        System.out.println(invoice.getTableHeader());
        String tableHeader = invoice.getTableHeader().toString();
        //Actions|Invoice Creation Date|Invoice No.|Status|No. of lines|Invoice Quantity|Customer PO number|Invoice Amount|
        AssertJUnit.assertTrue("Incorrect table columns!",tableHeader.equals("Action|Invoice No.|Invoice creation date|Payment Date|Status|Value|Currency|No. of Lines|Ship to Party|"));
        System.out.println("Table Header correct!");

        System.out.println("Test PASSED!");
    }

    @Test //Reporting - Delivery Notes UI screen changes - WBA-563
            (groups = {"eComm"})
    public void Rep_DN() throws IOException, InterruptedException, Exception {
        //new driver instance
        WebDriver driver = getDriver();

        //New eComm base test to handle log-in and navigation
        Ecomm_Base baseTest = new Ecomm_Base(driver);
        Ecomm_MainPage eCommPage = baseTest.setUp("Rep_DN", "Reporting - Delivery Notes UI screen changes");

        //Navigate to Delivery Notes page
        System.out.println("Navigating to Invoice Page...");
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
        AssertJUnit.assertTrue("Incorrect table columns!",tableHeader.equals("Actions|Delivery Date|Delivery Note No.|Delivered Quantity|Requester Name|"));
        System.out.println("Table Header correct!");

        System.out.println("Test PASSED!");
    }

    @Test //Reporting - Summary of Purchases UI screen changes - change name to Purchase Order Summary - WBA-564
            (groups = {"eComm"})
    public void Rep_POSR() throws IOException, InterruptedException, Exception {
        //new driver instance
        WebDriver driver = getDriver();

        //New eComm base test to handle log-in and navigation
        Ecomm_Base baseTest = new Ecomm_Base(driver);
        Ecomm_MainPage eCommPage = baseTest.setUp("Rep_POSR", "Reporting - Summary of Purchases UI screen changes - change name to Purchase Order Summary");

        //Navigate to Purchase Order Summary
        System.out.println("Navigating to Invoice Page...");
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
        AssertJUnit.assertTrue("Incorrect table columns!",tableHeader.equals("Action|Purchase Date|Customer PO No|SAP Order No.|Requestor Name|"));
        System.out.println("Table Header correct!");

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

        Ecomm_MyReportsPage advRep = new Ecomm_MyReportsPage(driver);
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
        AssertJUnit.assertTrue("***Incorrect breadcrumb!",cnrp.getBreadcrumb().getText().equals("Reports | Advanced Reports| New Report"));

        cnrp.selectDateRange("Last 90 days");

        cnrp.selectRepCriteria("Custom Fields");

        cnrp.selectAllFromOrders();
        cnrp.selectAllDeliveryNotes();

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

        Ecomm_MyReportsPage advRep2 = new Ecomm_MyReportsPage(driver);


        //Go to Saved Reports

        /**
         * Verify that saved reports is displayed
         */
        advRep2.checkSavedReports();


        /**
         * Verify the Saved Report exist
         */
        advRep2.clickSavedReports();
        AssertJUnit.assertTrue("***Incorrect Saved Report Name!",advRep2.getSavedRepName().contentEquals("Rep1"));

        //Open this saved report and create a new one
        advRep2.selectSavedReport();
        System.out.println("Creating a new report...");
        Ecomm_CreateNewReportPage cnrp2 = advRep2.createNewReport();

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
        System.out.println("Navigating to Invoice Page...");
        driver.get(DataItems.advancedReports);

        Ecomm_MyReportsPage advRep3 = new Ecomm_MyReportsPage(driver);

        advRep3.clickSavedReports();

        //Delete first saved report
        System.out.println("Deleting first saved report...");
        advRep3.deleteSavedReport();

        /**
         * Verify Flash Message
         */
        AssertJUnit.assertEquals("***Incorrect Flash Message!","Saved Report has been deleted",advRep3.getFlashMessageText());
        System.out.println("Saved Report has been deleted!");

    }
}
