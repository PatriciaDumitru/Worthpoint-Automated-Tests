package com.coats.selenium.tests;

import AutomationFramework.DataItems;
import PageObjects.Ecomm_DeliveryNotesPage;
import PageObjects.Ecomm_InvoicesPage;
import PageObjects.Ecomm_MainPage;
import PageObjects.Ecomm_SummaryOfPurchasePage;
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
        AssertJUnit.assertTrue("Incorrect button label!",dn.getClearButtonLabel().equals("Reset")); //"Clear"
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
        AssertJUnit.assertTrue("Incorrect button label!",posr.getClearButtonLabel().equals("Reset")); //"Clear"
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
}
