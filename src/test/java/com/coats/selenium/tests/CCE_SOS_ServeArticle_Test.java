package com.coats.selenium.tests;

import AutomationFramework.DataItems;
import AutomationFramework.PreFlows;
import PageObjects.*;
import com.coats.selenium.DriverFactory;
import com.google.common.base.Verify;
import java.io.File;
import java.io.IOException;

import com.sun.jna.platform.unix.X11;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

public class CCE_SOS_ServeArticle_Test extends DriverFactory {

                                                                                                                        /*

            SL = Single Line
            ML = Multi Line

            Cust Option = Both / HUB / WAREHOUSE

            Similar article: No = There is no similar article
            Similar article: Yes = Article is not present, but a similar article is present

            H:Y = Article/SimilarArticle is in HUB
            H:N = Article/SimilarArticle is not in HUB

            W:Y = Article/SimilarArticle is in Warehouse
            W:N = Article/SimilarArticle is not in Warehouse

            L1,L2,L3 = Line 1,2,3

            Article and Similar article have the same : PLANT, BRAND, TICKET and SHADE



     ######################## Article Example 01
     sos.setStockArticleAndSave(DataItems.addToHub, DataItems.articleData01[0],DataItems.articleData01[1],DataItems.articleData01[2],
                DataItems.articleData01[3],DataItems.articleData01[4], DataItems.articleData01[5], DataItems.articleData01[6],DataItems.articleData01[7]);
     articleData01 = {"ID51", "ID12", "8754180", "astra", "180", "Cone", "WHITE", "100"};

                                                                                                                        */

    public void completeOrderPromptPageAndAddCustDetails() throws Exception {
        WebDriver driver = getDriver();

        CCE_MainPage ccePage = new CCE_MainPage(driver);

        System.out.println("Navigating to Order Samples...");
        CCE_OrderSamplesPage osPage = ccePage.pressOrderSamples();
        osPage.waitForLoad();

        System.out.println("Order Samples Page reached. Entering customer details...");
        osPage.setCustName(DataItems.custDetails[0]);
        osPage.setRequestor(DataItems.custDetails[2]);

        System.out.println("Customer details entered...");
        CCE_AddOrderPage orderPage = osPage.pressSubmit();
        orderPage.waitForLoad();

        orderPage.inputCustDetails(DataItems.custDetails[1], DataItems.othersWithCode);
    }

    public void General(String salesOrgEnrichOption, String addArticleTo, String articleInStock, String articleInManualEntry, String shade, String expectedArticle, String expectedSOS ) throws Exception {
        //New driver object to control browser
        WebDriver driver = getDriver();

        //Setting Master Data
        System.out.println("Setting Master Data for Sales Org and Customer...");
        PreFlows pf = new PreFlows();
        if (salesOrgEnrichOption == "Both")pf.activateEnrichToForSalesOrgAndCustomer(driver, DataItems.salesOrgID, DataItems.lifeEasyCustomer, DataItems.enrichBoth);
        else if (salesOrgEnrichOption == "Hub")pf.activateEnrichToForSalesOrgAndCustomer(driver, DataItems.salesOrgID, DataItems.lifeEasyCustomer, DataItems.enrichHub);
        else if (salesOrgEnrichOption == "Warehouse)")pf.activateEnrichToForSalesOrgAndCustomer(driver, DataItems.salesOrgID, DataItems.lifeEasyCustomer, DataItems.enrichWarehouse);
        else System.out.println("WRONG INPUT FOR SELECTED 'ENRICH TO' PARAM...");

        CCE_SOS_ServeArticle_Page sos = new CCE_SOS_ServeArticle_Page(driver);

        driver.get(DataItems.hubStockURL);
        sos.filterArticlesByPlantAndDeleteAll(DataItems.plantID12);
        driver.get(DataItems.warehouseURL);
        sos.filterArticlesByPlantAndDeleteAll(DataItems.plantID12);


        if (addArticleTo == "Hub") {
            //Add article to HUB
            System.out.println("Navigating to HubStock page...");
            driver.get(DataItems.hubStockURL);
            System.out.println("Adding Article to HubStock...");
            sos.setStockArticleAndSave(DataItems.addToHub, DataItems.articleData01[0], DataItems.articleData01[1], articleInStock,
                    DataItems.articleData01[3], DataItems.articleData01[4], DataItems.articleData01[5], DataItems.articleData01[6], DataItems.articleData01[7]);
        }

        else if (addArticleTo == "Warehouse"){
            //Add article to HUB
            System.out.println("Navigating to HubStock page...");
            driver.get(DataItems.warehouseURL);
            System.out.println("Adding Article to WarehouseStock...");
            sos.setStockArticleAndSave(DataItems.addToWarehouse, DataItems.articleData01[0], DataItems.articleData01[1], articleInStock,
                    DataItems.articleData01[3], DataItems.articleData01[4], DataItems.articleData01[5], DataItems.articleData01[6], DataItems.articleData01[7]);
        }

        else if (addArticleTo == "Both") {
            //Add article to HUB
            System.out.println("Navigating to HubStock page...");
            driver.get(DataItems.hubStockURL);
            System.out.println("Adding Article to HubStock...");
            sos.setStockArticleAndSave(DataItems.addToHub, DataItems.articleData01[0], DataItems.articleData01[1], DataItems.articleData01[2],
                    DataItems.articleData01[3], DataItems.articleData01[4], DataItems.articleData01[5], DataItems.articleData01[6], DataItems.articleData01[7]);

            //Add article to Warehouse
            System.out.println("Navigating to Warehouse page...");
            driver.get(DataItems.warehouseURL);
            System.out.println("Adding Article to WarehouseStock...");
            sos.setStockArticleAndSave(DataItems.addToWarehouse, DataItems.articleData01[0], DataItems.articleData01[1], DataItems.articleData01[2],
                    DataItems.articleData01[3], DataItems.articleData01[4], DataItems.articleData01[5], DataItems.articleData01[6], DataItems.articleData01[7]);
        }

        else System.out.println("WRONG INPUT FOR 'ADD ARTICLE TO' PARAM...");

        //Article is DataItems.articleData01[2]
        //MuM Type is DataItems.articleData01[5]
        //Shade is DataItems.articleData01[6]


        System.out.println("Creating order...");
        completeOrderPromptPageAndAddCustDetails();

        CCE_AddOrderPage aop = new CCE_AddOrderPage(driver);
        System.out.println("Entering product details first line...");
        aop.inputArticleShadeMUMForLine(articleInManualEntry, DataItems.articleData01[5], shade, 0);

        System.out.println("Submitting order...");
        aop.pressSubmit();

        System.out.println(sos.getCurrentSOS().getText());
        AssertJUnit.assertTrue("SOS Type is not what expected", sos.getCurrentSOS().getText().equals(expectedSOS));

        sos.pressViewOrder();
        System.out.println(sos.getArticleFromOrder().getText());
        AssertJUnit.assertTrue("Article is not what  expected", sos.getArticleFromOrder().getText().equals(expectedArticle));

    }


    @Test //SL_B_01 : Single Line - Both - Similar Article:No - H:Y -W:N
            (groups = {"CCE"})
    public void Test() throws Exception {
        //New driver object to control browser
        WebDriver driver = getDriver();

        //New base object to handle log-in and set up
        Cce_Base base = new Cce_Base(driver);

        //Set up returns a CCE Page and outputs test details
        CCE_MainPage ccePage = base.setUp("SL_B_01", "Single Line - Both - Similar Article:No - H:Y - W:N");

        General("Hub", "Hub", DataItems.articleData01[2], DataItems.articleData02[2], DataItems.articleData01[6], DataItems.articleData02[2],"--");
    }



















    @Test //SL_B_01 : Single Line - Both - Similar Article:No - H:Y -W:N
            (groups = {"CCE"})
    public void SL_B_01() throws Exception {
        //New driver object to control browser
        WebDriver driver = getDriver();

        //New base object to handle log-in and set up
        Cce_Base base = new Cce_Base(driver);

        //Set up returns a CCE Page and outputs test details
        CCE_MainPage ccePage = base.setUp("SL_B_01", "Single Line - Both - Similar Article:No - H:Y - W:N");

        //Setting Master Data
        System.out.println("Setting Master Data for Sales Org and Customer...");
        PreFlows pf = new PreFlows();
        pf.activateEnrichToForSalesOrgAndCustomer(driver, DataItems.salesOrgID, DataItems.lifeEasyCustomer, DataItems.enrichBoth);

        CCE_SOS_ServeArticle_Page sos = new CCE_SOS_ServeArticle_Page(driver);
        //Add article to HUB
        System.out.println("Navigating to HubStock page...");
        driver.get(DataItems.hubStockURL);

        System.out.println("Adding Article to HubStock...");
        sos.setStockArticleAndSave(DataItems.addToHub, DataItems.articleData01[0],DataItems.articleData01[1],DataItems.articleData01[2],
                DataItems.articleData01[3],DataItems.articleData01[4], DataItems.articleData01[5], DataItems.articleData01[6],DataItems.articleData01[7]);

        //Article is DataItems.articleData01[2]
        //Shade is DataItems.articleData01[5]
        //MuM Type is DataItems.articleData01[6]

        System.out.println("Creating order...");
        completeOrderPromptPageAndAddCustDetails();

        CCE_AddOrderPage aop = new CCE_AddOrderPage(driver);
        System.out.println("Entering product details first line...");
        aop.inputArticleShadeMUMForLine(DataItems.articleData01[2], DataItems.articleData01[5], DataItems.articleData01[6], 0);

        System.out.println("Submitting order...");
        aop.pressSubmit();

    }

}