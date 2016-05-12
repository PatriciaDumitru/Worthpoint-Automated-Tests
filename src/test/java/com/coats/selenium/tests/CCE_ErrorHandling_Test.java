package com.coats.selenium.tests;

import com.coats.selenium.DriverFactory;

import AutomationFramework.DataItems;
import PageObjects.*;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

public class CCE_ErrorHandling_Test extends DriverFactory {

    @Test //	ER_MF_XML_01: Error Handling Mandatory Field XML with empty xml file
            (groups = {"CCE", "QuickTest"})
    public void ER_MF_XML_01() throws Exception {
        //New driver object to control browser
        WebDriver driver = getDriver();

        //New base object to handle log-in and set up
        Cce_Base base = new Cce_Base(driver);

        //Set up returns a CCE Page and outputs test details
        CCE_MainPage ccePage = base.setUp("ER_MF_XML_01", "Error Handling Mandatory Field XML with empty xml file");

        System.out.println("Navigating to Order Samples...");

        CCE_OrderSamplesPage osPage = ccePage.pressOrderSamples();
        osPage.waitForLoad();

        System.out.println("Order Samples Page reached. Entering customer details...");

        osPage.setCustName(DataItems.custDetails[0]);
        osPage.setRequestor(DataItems.custDetails[2]);

        System.out.println("Customer details entered. Submitting...");

        CCE_AddOrderPage orderPage = osPage.pressSubmit();
        orderPage.waitForLoad();

        System.out.println("Submitted. Entering product details...");

        orderPage.setShipToParty(DataItems.custDetails[1]);
        orderPage.setArticle(DataItems.article, 0);
        orderPage.setMUMType(DataItems.coneMUM, 0);
        orderPage.setRequestType(DataItems.sewing, 0);
        orderPage.setPurposeType(DataItems.salesSamp, 0);
        orderPage.setQuantity(1,0);

        System.out.println("Set Direct Enrich Yes...");

        try {
            Alert alert = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.alertIsPresent());
            alert.accept();
            System.out.println("Alert appeared: " + alert.getText());
        } catch (Exception e) {
            System.out.println("No alert.");
        }
        //Set Direct Enrich Yes
        orderPage.setDirectEnrichYes(0);

        System.out.println("Set Customer Swatch Yes...");
        //Set Customer Swatch Yes
        orderPage.setCustomerSwatchYes(0);

        orderPage.uploadExtentionFile(DataItems.xmlExtension, 0);

        orderPage.pressSubmit();

        AssertJUnit.assertTrue("Browse button is not enabled", orderPage.checkBrowseButtonEneabled(0));



    }

    @Test //	ER_MF_XML_02: Error Handling Mandatory Field XML multiline : first line empty, second line uploaded xml
            (groups = {"CCE"})
    public void ER_MF_XML_02() throws Exception {
        //New driver object to control browser
        WebDriver driver = getDriver();

        //New base object to handle log-in and set up
        Cce_Base base = new Cce_Base(driver);

        //Set up returns a CCE Page and outputs test details
        CCE_MainPage ccePage = base.setUp("ER_MF_XML_02", "Error Handling Mandatory Field XML multiline : first line empty, second line uploaded xml ");

        System.out.println("Navigating to Order Samples...");

        CCE_OrderSamplesPage osPage = ccePage.pressOrderSamples();
        osPage.waitForLoad();

        System.out.println("Order Samples Page reached. Entering customer details...");

        osPage.setCustName(DataItems.custDetails[0]);
        osPage.setRequestor(DataItems.custDetails[2]);

        System.out.println("Customer details entered. Submitting...");

        CCE_AddOrderPage orderPage = osPage.pressSubmit();
        orderPage.waitForLoad();

        System.out.println("Entering product details first line...");

        orderPage.setShipToParty(DataItems.custDetails[1]);
        orderPage.setArticle(DataItems.article, 0);
        orderPage.setShadeCode(DataItems.shadeCode, 0);
        orderPage.setMUMType(DataItems.coneMUM, 0);
        orderPage.setRequestType(DataItems.sewing, 0);
        orderPage.setPurposeType(DataItems.salesSamp, 0);
        orderPage.setQuantity(1,0);

        //Adding new line
        System.out.println("Entering product details second line...");
        orderPage.pressNewLine(1);

        //Setting second line info
        orderPage.setArticle(DataItems.article, 1);
        orderPage.setMUMType(DataItems.coneMUM, 1);
        orderPage.setRequestType(DataItems.sewing, 1);
        orderPage.setPurposeType(DataItems.salesSamp, 1);
        orderPage.setQuantity(1,1);

        System.out.println("Set Direct Enrich Yes...");
        //Set Direct Enrich Yes
        orderPage.setDirectEnrichYes(1);

        System.out.println("Set Customer Swatch Yes...");
        //Set Customer Swatch Yes
        orderPage.setCustomerSwatchYes(1);

        orderPage.uploadExtentionFile(DataItems.xmlExtension, 0);

        orderPage.pressSubmit();

        AssertJUnit.assertTrue("Browse button is not enabled", orderPage.checkBrowseButtonEneabled(1));
    }

    @Test //	ER_MF_XML_03: Error Handling Mandatory Field XML with uploaded xml file
            (groups = {"CCE"})
    public void ER_MF_XML_03() throws Exception {
        ER_MF_XML_01();





    }

    @Test //	ER_MF_XML_04: Error Handling Mandatory Field XML multiline : first line uploaded xml, second line uploaded xml
            (groups = {"CCE"})
    public void ER_MF_XML_04() throws Exception {
        //New driver object to control browser
        WebDriver driver = getDriver();

        //New base object to handle log-in and set up
        Cce_Base base = new Cce_Base(driver);

        //Set up returns a CCE Page and outputs test details
        CCE_MainPage ccePage = base.setUp("ER_MF_XML_04", "Error Handling Mandatory Field XML multiline : first line uploaded xml, second line uploaded xml  ");

        System.out.println("Navigating to Order Samples...");

        CCE_OrderSamplesPage osPage = ccePage.pressOrderSamples();
        osPage.waitForLoad();

        System.out.println("Order Samples Page reached. Entering customer details...");

        osPage.setCustName(DataItems.custDetails[0]);
        osPage.setRequestor(DataItems.custDetails[2]);

        System.out.println("Customer details entered...");

        CCE_AddOrderPage orderPage = osPage.pressSubmit();
        orderPage.waitForLoad();

        System.out.println("Entering product details first line...");

        orderPage.inputDetails(DataItems.custDetails[1],DataItems.othersWithCode,DataItems.expArticle,
                DataItems.expShadeCode,DataItems.copMUM,DataItems.sewing,DataItems.salesSamp,1);

        orderPage.uploadExtentionFile(DataItems.xmlExtension, 0);

        //Adding new line
        orderPage.pressNewLine(1);
        System.out.println("Entering product details second line...");
        orderPage.inputAdditionalLines(DataItems.article3,
                DataItems.shadeCode3,DataItems.copMUM,DataItems.sewing,DataItems.salesSamp,1,1);
    }







}
