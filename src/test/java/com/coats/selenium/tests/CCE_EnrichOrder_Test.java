
package com.coats.selenium.tests;

import AutomationFramework.DataItems;
import PageObjects.CCE_AddOrderPage;
import PageObjects.CCE_EnrichOrderPage;
import PageObjects.CCE_HubSosPage;
import PageObjects.CCE_LRMLogPage;
import PageObjects.CCE_MainPage;
import PageObjects.CCE_ManualEnrichPage;
import PageObjects.CCE_OrderSamplesPage;
import PageObjects.CCE_OrderStatusPage;
import PageObjects.CCE_OrderViewPage;
import PageObjects.CCE_SAPLogPage;
import com.coats.selenium.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;


public class CCE_EnrichOrder_Test extends DriverFactory {
    
    @Test //Manual Enrich Page :: SUMST :: Page and filter checks, Hub SOS selection
    (groups={"CCE","CCE_Orders","QuickTest"})
    public void EO1() throws Exception {
        
        WebDriver driver = getDriver();
        
        Cce_Base base = new Cce_Base(driver);
        CCE_MainPage mainPage = base.setUp("Enrich Orders EO1: Page and filter checks, Hub SOS selection", "G_CCE_EO_1");

        CCE_OrderSamplesPage orderPage = mainPage.pressOrderSamples();
        orderPage.waitForElement();

        System.out.println("Order Samples page reached. Entering Customer details...");

        orderPage.setCustName(DataItems.custDetails[0]);
        orderPage.setRequestor(DataItems.custDetails[2]);

        System.out.println("Customer details entered. Submitting prompt...");

        CCE_AddOrderPage aoPage = orderPage.pressSubmit();
        aoPage.waitForElement();

        System.out.println("Prompt submitted. Setting ship to and line details...");

        aoPage.setShipToParty(DataItems.custDetails[1]);
        aoPage.setArticle(DataItems.article, 0);
        aoPage.setShadeCode(DataItems.shadeCode, 0);
        aoPage.setMUMType(DataItems.copMUM, 0);
        aoPage.setCustomerRef(0);
        aoPage.setRequestType(DataItems.sewing, 0);
        aoPage.setPurposeType(DataItems.bulkPurpose, 0);
        aoPage.setQuantity(1, 0);

        System.out.println("Line details entered. Submitting order...");

        CCE_OrderStatusPage statusPage = aoPage.pressSubmit();
        statusPage.waitForElement();


        System.out.println("Order Status Page loaded. Retrieving Order No. for order...");

        mainPage.waitForElement();
        
        System.out.println("Navigating to Manual Enrich Page...");
        
        CCE_ManualEnrichPage mePage = mainPage.pressManualEnrich();
        mePage.waitForElement();
        
        System.out.println("Manual Enrich Page reached. Checking title...");
        
        AssertJUnit.assertTrue("Manual Enrich Page: Title not displayed as expected",mePage.getBreadcrumb().getText().equals("Orders | Enrich Order"));
        
        System.out.println("Title checked");
        
        mePage.assertBaseElements();
        
        System.out.println("Checking fields...");
        
        mePage.checkFields();
        
        System.out.println("Fields checked. Entering filter criteria...");
        
        mePage.setCustomerName(DataItems.custDetails[0]);
        
        System.out.println("Criteria entered. Listing orders...");
        
        mePage.pressSearch();
        mePage.waitForElement();
        
        System.out.println("Orders listed. Resetting filter...");
        
        mePage.pressReset();
        mePage.waitForElement();
        
        System.out.println("Filter reset. Checking for records...");
        
        if (mePage.checkForRecords()) {
            System.out.println("Records found. Retrieving top item Order No. and pressing view...");
            
            String orderNo = mePage.getOrderNo(2);
            
            CCE_OrderViewPage viewPage = mePage.pressView(2);
            viewPage.waitForContentAlt2();
            
            System.out.println("View displayed. Order No.: "+orderNo+". Closing view...");
            
            viewPage.closeView();
            viewPage.waitForInvisibility();
            
            System.out.println("View closed. Enriching order...");
            
            CCE_EnrichOrderPage enrichPage = mePage.pressEnrich(2);
            enrichPage.waitForElement();
            
            System.out.println("Enrich Order page reached. Setting customer reference...");
            
            enrichPage.setCustomerRef();
            
            System.out.println("Customer reference set. Setting SOS to Hub...");
            enrichPage.pressHub();
            
            System.out.println("SOS set. Enriching All Order lines...");
            
            CCE_ManualEnrichPage mePage2 = enrichPage.pressEnrichAll();
            mePage2.waitForElement();
            
            System.out.println("Order enriched. Checking order is removed from Enrich Page...");
            
            AssertJUnit.assertFalse("Manual Enrich Page: Enriched order (Order No.: "+orderNo+") did not disappear from table",mePage2.findOrder(orderNo));
            
            System.out.println("Order removed after enrichment. Checking Hub SOS for order...");
            
            CCE_HubSosPage hubPage = mePage2.pressHubSos();
            hubPage.waitForElement();
            
            System.out.println("Hub SOS page reached. Searching for order...");
            
            String orderStage = hubPage.findOrder(orderNo);
            
            AssertJUnit.assertFalse("Enrich Order Page: Order (Order No.: "+orderNo+") not found in Hub SOS after enrichment",orderStage==null);
            
            System.out.println("Order found in Hub SOS page");
            System.out.println("Order No.: " + orderNo);
            System.out.println("Customer Ref: " + DataItems.lastUsedPO);
            System.out.println("Order Stage: " + orderStage);
            
        } else {
            System.out.println("No records found.");
        }
        
    }
    
    @Test //Manual Enrich Page :: SUMST :: Lab and WHS SOS selection
    (groups={"CCE","CCE_Orders"}) //This test will fail if the order which is selected has multiple lines.
    //for example, if the test is run after SOC12 (multiple copied data test), the order selected will have many lines and the test will fail
    public void EO2() throws Exception {
        
        WebDriver driver = getDriver();
        
        Cce_Base base = new Cce_Base(driver);
        CCE_MainPage mainPage = base.setUp("Enrich Orders EO2: Lab and WHS selection", "G_CCE_EO_2");

        mainPage.waitForElement();


        CCE_OrderSamplesPage orderPage = mainPage.pressOrderSamples();
        orderPage.waitForElement();

        System.out.println("Order Samples page reached. Entering Customer details...");

        orderPage.setCustName(DataItems.custDetails[0]);
        orderPage.setRequestor(DataItems.custDetails[2]);

        System.out.println("Customer details entered. Submitting prompt...");

        CCE_AddOrderPage aoPage = orderPage.pressSubmit();
        aoPage.waitForElement();

        System.out.println("Prompt submitted. Setting ship to and line details...");

        aoPage.setShipToParty(DataItems.custDetails[1]);
        aoPage.setArticle(DataItems.article, 0);
        aoPage.setShadeCode(DataItems.shadeCode, 0);
        aoPage.setMUMType(DataItems.copMUM, 0);
        aoPage.setCustomerRef(0);
        aoPage.setRequestType(DataItems.sewing, 0);
        aoPage.setPurposeType(DataItems.bulkPurpose, 0);
        aoPage.setQuantity(1, 0);

        System.out.println("Line details entered. Submitting order...");

        CCE_OrderStatusPage statusPage = aoPage.pressSubmit();
        statusPage.waitForElement();


        System.out.println("Order Status Page loaded. Retrieving Order No. for order...");
        
        System.out.println("Navigating to Order Samples Page...");
        
        //Create an order so that it is ensured that only a single line is present when enriching (otherwise test will fail)
        CCE_OrderSamplesPage mmainPage = mainPage.pressOrderSamples();
        orderPage.waitForElement();
        
        orderPage.setCustName(DataItems.custDetails[0]);
        orderPage.setRequestor(DataItems.custDetails[2]);
        
        System.out.println("Customer details entered. Entering product details...");
        
        CCE_AddOrderPage addPage = orderPage.pressSubmit();
        addPage.waitForElement();
        
        addPage.setShipToParty(DataItems.custDetails[1]);
        addPage.setBusinessPrincipal(DataItems.othersWithCode);
        addPage.setArticle("8754120",0);
        addPage.setShadeCode("C1202",0);
        addPage.setMUMType("Cone",0);
        addPage.setRequestType(DataItems.sewing, 0);
        addPage.setPurposeType(DataItems.protoPurpose,0);
        addPage.setQuantity(1,0);
        
        System.out.println("Product details entered. Submitting order...");
        
        addPage.pressSubmit();
        statusPage.waitForElement();
        
        System.out.println("Order submitted.");
        
        System.out.println("Navigating to Manual Enrich Page...");
        
        CCE_ManualEnrichPage mePage = mainPage.pressManualEnrich();
        mePage.waitForElement();
        
        System.out.println("Manual Enrich Page reached. Checking for records...");
        
        if (mePage.checkForRecords()) {
            System.out.println("Records found. Retrieving top item Order No. and pressing view...");
            
            String orderNo = mePage.getOrderNo(2);
            
            CCE_OrderViewPage viewPage = mePage.pressView(2);
            viewPage.waitForContentAlt2();
            
            System.out.println("View displayed. Order No.: "+orderNo+". Closing view...");
            
            viewPage.closeView();
            viewPage.waitForInvisibility();
            
            System.out.println("View closed. Enriching order...");
            
            CCE_EnrichOrderPage enrichPage = mePage.pressEnrich(2);
            enrichPage.waitForElement();
            
            System.out.println("Enrich Order page reached. Setting customer reference...");
            
            enrichPage.setCustomerRef();
            
            System.out.println("Customer reference set. Setting SOS to Lab...");
            enrichPage.pressLab();
            
            System.out.println("SOS set. Enriching All Order lines...");
            
            CCE_ManualEnrichPage mePage2 = enrichPage.pressEnrichAll();
            mePage2.waitForElement();
            
            System.out.println("Order enriched. Checking order is removed from Enrich Page...");
            
            AssertJUnit.assertFalse("Manual Enrich Page: Enriched order (Order No.: "+orderNo+") did not disappear from table",mePage2.findOrder(orderNo));
            
            System.out.println("Order removed after enrichment. Checking LRM Log for order...");
            
            CCE_LRMLogPage lrmPage = mePage2.pressLRMLog();
            lrmPage.waitForElement();
            
            System.out.println("LRM Log page reached. Searching for order...");
            
            String orderStage = lrmPage.findOrder(orderNo);
            
            AssertJUnit.assertFalse("Enrich Order Page: Order (Order No.: "+orderNo+") not found in LRM after enrichment",orderStage==null);
            
            System.out.println("Order found in LRM page");
            System.out.println("Order No.: " + orderNo);
            System.out.println("Customer Ref: " + DataItems.lastUsedPO);
            System.out.println("Error message: " + orderStage);
            
            System.out.println("Testing Warehouse SOS...");
            
            CCE_ManualEnrichPage mePage3 = lrmPage.pressManualEnrich();
            mePage3.waitForElement();
            
            System.out.println("Manual Enrich Page reached. Retrieving top item Order No. and pressing view...");
            
            String orderNo2 = mePage3.getOrderNo(2);
            
            CCE_OrderViewPage viewPage2 = mePage3.pressView(2);
            viewPage2.waitForContentAlt2();
            
            System.out.println("View displayed. Order No.: "+orderNo2+". Closing view...");
            
            viewPage2.closeView();
            viewPage2.waitForInvisibility();
            
            System.out.println("View closed. Enriching order...");
            
            CCE_EnrichOrderPage enrichPage2 = mePage3.pressEnrich(2);
            enrichPage2.waitForElement();
            
            System.out.println("Enrich Order page reached. Setting customer reference...");
            
            enrichPage2.setCustomerRef();
            
            System.out.println("Customer reference set. Setting SOS to WHS...");
            enrichPage.pressWHS();
            
            System.out.println("SOS set. Enriching All Order lines...");
            
            CCE_ManualEnrichPage mePage4 = enrichPage.pressEnrichAll();
            mePage4.waitForElement();
            
            System.out.println("Order enriched. Checking order is removed from Enrich Page...");
            
            AssertJUnit.assertFalse("Manual Enrich Page: Enriched order (Order No.: "+orderNo2+") did not disappear from table",mePage4.findOrder(orderNo2));
            
            System.out.println("Order removed after enrichment. Checking SAP Log for order...");
            
            CCE_SAPLogPage sapPage = mePage4.pressSAPLog();
            sapPage.waitForElement();
            
            System.out.println("LRM Log page reached. Searching for order...");
            
            String orderStage2 = sapPage.findOrder(orderNo2);
            
            AssertJUnit.assertFalse("Enrich Order Page: Order (Order No.: "+orderNo2+") not found in LRM after enrichment",orderStage2==null);
            
            System.out.println("Order found in LRM page");
            System.out.println("Order No.: " + orderNo2);
            System.out.println("Customer Ref: " + DataItems.lastUsedPO);
            System.out.println("Order Stage: " + orderStage2);
            
        } else {
            System.out.println("No records found.");
        }
        
    }

    
}
