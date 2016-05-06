package com.coats.selenium.tests;

import AutomationFramework.DataItems;
import PageObjects.CCE_AddOrderPage;
import PageObjects.CCE_MainPage;
import PageObjects.CCE_OrderSamplesPage;
import PageObjects.CCE_OrderStatusPage;
import com.coats.selenium.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

/**
 * Created by Stefan on 27.04.2016.
 */
public class CCE_Fce_Comments_Mandatory_Test extends DriverFactory{
    @Test //WBA-596 FCE Comments should be conditionally mandatory field
            (groups = {"CCE"})
    public void FCE_Comments_MF_01() throws Exception {
        WebDriver driver = getDriver();

        Cce_Base base = new Cce_Base(driver);
        CCE_MainPage ccePage = base.setUp("Outstanding Draft Page: Page and filter checks", "G_CCE_Unknown");
        ccePage.waitForLoad();

        System.out.println("Navigating to Order Samples...");

        CCE_OrderSamplesPage osPage = ccePage.pressOrderSamples();
        osPage.waitForLoad();

        System.out.println("Order Samples Page reached. Entering customer details...");

        osPage.setCustName(DataItems.custDetails[0]);
        osPage.setRequestor(DataItems.custDetails[2]);

        System.out.println("Customer details entered. Submitting...");

        CCE_AddOrderPage orderPage = osPage.pressSubmit();
        orderPage.waitForElement();

        System.out.println("Submitted. Entering product details...");

        orderPage.setShipToParty(DataItems.custDetails[1]);
        orderPage.setArticle(DataItems.article, 0);
        orderPage.setMUMType(DataItems.coneMUM, 0);
        orderPage.setRequestType(DataItems.sewing, 0);
        orderPage.setPurposeType(DataItems.salesSamp, 0); //mandatory
        orderPage.setQuantity(1,0); //mandatory
        //orderPage.setShadeCode(DataItems.expShadeCode,0);

        //Set Direct Enrich -> YES
        orderPage.setDirectEnrichYes(0);

        System.out.println("Product details entered. Submitting order...");

        orderPage.pressSubmit();

        System.out.println("Order submitted.");

        orderPage.waitForElement2();

        AssertJUnit.assertTrue("***FCE Comments are not mandatory!",orderPage.getFlashMessageText().contains("Fce Comments are required"));


    }
}
