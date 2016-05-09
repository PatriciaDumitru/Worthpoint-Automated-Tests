package com.coats.selenium.tests;

/**
 * Created by Andrei
 */

import AutomationFramework.DataItems;
import AutomationFramework.PreFlows;
import PageObjects.*;
import com.coats.selenium.DriverFactory;
import com.google.common.base.Verify;
import java.io.File;
import java.io.IOException;
import java.util.Set;

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
     sos.setStockArticleAndSave(DataItems.addToHub, DataItems.articleData180[0],DataItems.articleData180[1],DataItems.articleData180[2],
                DataItems.articleData180[3],DataItems.articleData180[4], DataItems.articleData180[5], DataItems.articleData180[6],DataItems.articleData180[7]);
     articleData180 = {"ID51", "ID12", "8754180", "astra", "180", "Cone", "WHITE", "100"};

      // GeneralSingleLine (SalesOrg Enrich Option, Where to add article, Name of article that is added, Article to make order with, Shade that is the same for both articles, What article should appear after order is processed, what sos is expected)


                                                                                                                        */

    public void completeOrderPromptPageAndAddCustDetails() throws Exception {
        WebDriver driver = getDriver();

        CCE_MainPage ccePage =  new CCE_MainPage(driver);

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

    public void GeneralSingleLine(String salesOrgEnrichOption, String addArticleTo, String articleInStock, String articleInManualEntry, String shade, String expectedArticle, String expectedSOS) throws Exception {
        //New driver object to control browser
        WebDriver driver = getDriver();

        //Setting Master Data
        System.out.println("Setting Master Data for Sales Org and Customer...");
        PreFlows pf = new PreFlows();
        if (salesOrgEnrichOption == "Both")
            pf.activateEnrichToForSalesOrgAndCustomer(driver, DataItems.salesOrgID, DataItems.lifeEasyCustomer, DataItems.enrichBoth);
        else if (salesOrgEnrichOption == "Hub")
            pf.activateEnrichToForSalesOrgAndCustomer(driver, DataItems.salesOrgID, DataItems.lifeEasyCustomer, DataItems.enrichHub);
        else if (salesOrgEnrichOption == "Warehouse")
            pf.activateEnrichToForSalesOrgAndCustomer(driver, DataItems.salesOrgID, DataItems.lifeEasyCustomer, DataItems.enrichWarehouse);
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
            sos.setStockArticleAndSave(DataItems.addToHub, DataItems.articleData180[0], DataItems.articleData180[1], articleInStock,
                     DataItems.articleData180[6], DataItems.articleData180[7]);
        } else if (addArticleTo == "Warehouse") {
            //Add article to HUB
            System.out.println("Navigating to HubStock page...");
            driver.get(DataItems.warehouseURL);
            System.out.println("Adding Article to WarehouseStock...");
            sos.setStockArticleAndSave(DataItems.addToWarehouse, DataItems.articleData180[0], DataItems.articleData180[1], articleInStock,
                     DataItems.articleData180[6], DataItems.articleData180[7]);
        } else if (addArticleTo == "Both") {
            //Add article to HUB
            System.out.println("Navigating to HubStock page...");
            driver.get(DataItems.hubStockURL);
            System.out.println("Adding Article to HubStock...");
            sos.setStockArticleAndSave(DataItems.addToHub, DataItems.articleData180[0], DataItems.articleData180[1], articleInStock,
                     DataItems.articleData180[6], DataItems.articleData180[7]);

            //Add article to Warehouse
            System.out.println("Navigating to Warehouse page...");
            driver.get(DataItems.warehouseURL);
            System.out.println("Adding Article to WarehouseStock...");
            sos.setStockArticleAndSave(DataItems.addToWarehouse, DataItems.articleData180[0], DataItems.articleData180[1], articleInStock,
                     DataItems.articleData180[6], DataItems.articleData180[7]);
        } else System.out.println("WRONG INPUT FOR 'ADD ARTICLE TO' PARAM...");

        //Article is DataItems.articleData180[2]
        //MuM Type is DataItems.articleData180[5]
        //Shade is DataItems.articleData180[6]


        System.out.println("Creating order...");
        completeOrderPromptPageAndAddCustDetails();

        CCE_AddOrderPage aop = new CCE_AddOrderPage(driver);
        System.out.println("Entering product details first line...");
        aop.inputArticleShadeMUMForLine(articleInManualEntry, DataItems.articleData180[5], shade, 0);


        System.out.println("Submitting order...");
        aop.pressSubmit();

        System.out.println(sos.getCurrentSOS().getText());
        AssertJUnit.assertTrue("SOS Type is not what expected", sos.getCurrentSOS().getText().equals(expectedSOS));

        sos.pressViewOrder();
        System.out.println(sos.getArticleFromOrder().getText());
        AssertJUnit.assertTrue("Article is not what  expected", sos.getArticleFromOrder().getText().equals(expectedArticle));

    }

    public void addSimilarArticle01() throws Exception{
        WebDriver driver = getDriver();
        CCE_SOS_ServeArticle_Page sos = new CCE_SOS_ServeArticle_Page(driver);
        sos.createNewArticle(DataItems.similarArticle01[0],DataItems.similarArticle01[1], DataItems.similarArticle01[2], DataItems.similarArticle01[3], DataItems.similarArticle01[4], DataItems.similarArticle01[5], DataItems.similarArticle01[6]);
    }

    public void addSimilarArticle02() throws Exception{
        WebDriver driver = getDriver();
        CCE_SOS_ServeArticle_Page sos = new CCE_SOS_ServeArticle_Page(driver);
        sos.createNewArticle(DataItems.similarArticle02[0],DataItems.similarArticle02[1], DataItems.similarArticle02[2], DataItems.similarArticle02[3], DataItems.similarArticle02[4], DataItems.similarArticle02[5], DataItems.similarArticle02[6]);
    }

    public void addSimilarArticle03() throws Exception{
        WebDriver driver = getDriver();
        CCE_SOS_ServeArticle_Page sos = new CCE_SOS_ServeArticle_Page(driver);
        sos.createNewArticle(DataItems.similarArticle03[0],DataItems.similarArticle03[1], DataItems.similarArticle03[2], DataItems.similarArticle03[3], DataItems.similarArticle03[4], DataItems.similarArticle03[5], DataItems.similarArticle03[6]);
    }


    @Test //SL_B_01 : Single Line - Both - Similar Article:No - H:Y -W:N
            (groups = {"CCE"})
    public void SL_B_01() throws Exception {
        //New driver object to control browser
        WebDriver driver = getDriver();

        //New base object to handle log-in and set up
        Cce_Base base = new Cce_Base(driver);

        //Set up returns a CCE Page and outputs test details
        base.setUp("SL_W_01", "Single Line - Both - Similar Article:No - H:Y - W:N");

        addSimilarArticle01();
        addSimilarArticle02();
        addSimilarArticle03();

        GeneralSingleLine(DataItems.Both, DataItems.Hub, DataItems.articleData180[2], DataItems.articleData180[2], DataItems.articleData180[6], DataItems.articleData180[2], DataItems.Hub);
    }

    @Test //SL_B_02 : Single Line - Both - Similar Article:No - H:N -W:Y
            (groups = {"CCE"})
    public void SL_B_02() throws Exception {
        //New driver object to control browser
        WebDriver driver = getDriver();

        //New base object to handle log-in and set up
        Cce_Base base = new Cce_Base(driver);

        //Set up returns a CCE Page and outputs test details
        base.setUp("SL_B_02", "Single Line - Both - Similar Article:No - H:N -W:Y");

        GeneralSingleLine(DataItems.Both, DataItems.Warehouse, DataItems.articleData180[2], DataItems.articleData180[2], DataItems.articleData180[6], DataItems.articleData180[2], DataItems.Warehouse);
    }

    @Test //SL_B_03 : Single Line - Both - Similar Article:No - H:N -W:N
            (groups = {"CCE"})
    public void SL_B_03() throws Exception {
        //New driver object to control browser
        WebDriver driver = getDriver();

        //New base object to handle log-in and set up
        Cce_Base base = new Cce_Base(driver);

        //Set up returns a CCE Page and outputs test details
        base.setUp("SL_B_03", "Single Line - Both - Similar Article:No - H:N -W:N");

        GeneralSingleLine(DataItems.Both, DataItems.Both, DataItems.articleData180[2], DataItems.articleData02[2], DataItems.articleData180[6], DataItems.articleData02[2], DataItems.None);
    }

    @Test //SL_B_04 : SSingle Line - Both - Similar Article:No - H:Y -W:Y
            (groups = {"CCE"})
    public void SL_B_04() throws Exception {
        //New driver object to control browser
        WebDriver driver = getDriver();

        //New base object to handle log-in and set up
        Cce_Base base = new Cce_Base(driver);

        //Set up returns a CCE Page and outputs test details
        base.setUp("SL_B_04", "Single Line - Both - Similar Article:No - H:Y -W:Y");

        GeneralSingleLine(DataItems.Both, DataItems.Both, DataItems.articleData180[2], DataItems.articleData180[2], DataItems.articleData180[6], DataItems.articleData180[2], DataItems.Hub);
    }

    @Test //SL_B_05 : Single Line - Both - Similar Article:Yes - H:Y -W:N
            (groups = {"CCE"})
    public void SL_B_05() throws Exception {
        //New driver object to control browser
        WebDriver driver = getDriver();

        //New base object to handle log-in and set up
        Cce_Base base = new Cce_Base(driver);

        //Set up returns a CCE Page and outputs test details
        base.setUp("SL_B_05", "Single Line - Both - Similar Article:Yes - H:Y - W:N");

        GeneralSingleLine(DataItems.Both, DataItems.Hub, DataItems.similarArticle01[0], DataItems.articleData180[2], DataItems.articleData180[6], DataItems.articleData180[2], DataItems.None);
    }

    @Test //SL_B_06 : Single Line - Both - Similar Article:Yes - H:N -W:Y
            (groups = {"CCE"})
    public void SL_B_06() throws Exception {
        //New driver object to control browser
        WebDriver driver = getDriver();

        //New base object to handle log-in and set up
        Cce_Base base = new Cce_Base(driver);

        //Set up returns a CCE Page and outputs test details
        base.setUp("SL_B_06", "Single Line - Both - Similar Article:No - H:N -W:Y");

        GeneralSingleLine(DataItems.Both, DataItems.Warehouse, DataItems.similarArticle01[0], DataItems.articleData180[2], DataItems.articleData180[6],  DataItems.similarArticle01[0], DataItems.Warehouse);
    }

    @Test //SL_B_07 : Single Line - Both - Similar Article:Yes - H:N -W:N
            (groups = {"CCE"})
    public void SL_B_07() throws Exception {
        //New driver object to control browser
        WebDriver driver = getDriver();

        //New base object to handle log-in and set up
        Cce_Base base = new Cce_Base(driver);

        //Set up returns a CCE Page and outputs test details
        base.setUp("SL_B_07", "Single Line - Both - Similar Article:Yes - H:N -W:N");

        GeneralSingleLine(DataItems.Both, DataItems.Both, DataItems.articleData180[2], DataItems.articleData02[2], DataItems.articleData180[6], DataItems.articleData120[2], DataItems.None);
    }

    @Test //SL_B_08 : Single Line - Both - Similar Article:Yes - H:Y -W:Y
            (groups = {"CCE"})
    public void SL_B_08() throws Exception {
        //New driver object to control browser
        WebDriver driver = getDriver();

        //New base object to handle log-in and set up
        Cce_Base base = new Cce_Base(driver);

        //Set up returns a CCE Page and outputs test details
        base.setUp("SL_B_08", "Single Line - Both - Similar Article:Yes - H:Y -W:Y");

        GeneralSingleLine(DataItems.Both, DataItems.Both, DataItems.similarArticle01[0], DataItems.articleData180[2], DataItems.articleData180[6], DataItems.similarArticle01[0], DataItems.Warehouse);
    }

    @Test //SL_W_01 : Single Line - Warehouse - Similar Article:No - H:Y -W:N
            (groups = {"CCE"})
    public void SL_W_01() throws Exception {
        //New driver object to control browser
        WebDriver driver = getDriver();

        //New base object to handle log-in and set up
        Cce_Base base = new Cce_Base(driver);

        //Set up returns a CCE Page and outputs test details
        base.setUp("SL_W_01", "Single Line - Warehouse - Similar Article:No - H:Y - W:N");

        GeneralSingleLine(DataItems.Warehouse, DataItems.Hub, DataItems.articleData180[2], DataItems.articleData02[2], DataItems.articleData180[6], DataItems.articleData02[2], DataItems.None);
    }

    @Test //SL_W_02 : Single Line - Warehouse - Similar Article:No - H:N -W:Y
            (groups = {"CCE"})
    public void SL_W_02() throws Exception {
        //New driver object to control browser
        WebDriver driver = getDriver();

        //New base object to handle log-in and set up
        Cce_Base base = new Cce_Base(driver);

        //Set up returns a CCE Page and outputs test details
        base.setUp("SL_W_02", "Single Line - Warehouse - Similar Article:No - H:N -W:Y");

        GeneralSingleLine(DataItems.Warehouse, DataItems.Warehouse, DataItems.articleData180[2], DataItems.articleData180[2], DataItems.articleData180[6], DataItems.articleData180[2], DataItems.Warehouse);
    }

    @Test //SL_W_03 : Single Line - Warehouse - Similar Article:No - H:N -W:N
            (groups = {"CCE"})
    public void SL_W_03() throws Exception {
        //New driver object to control browser
        WebDriver driver = getDriver();

        //New base object to handle log-in and set up
        Cce_Base base = new Cce_Base(driver);

        //Set up returns a CCE Page and outputs test details
        base.setUp("SL_W_03", "Single Line - Warehouse - Similar Article:No - H:N -W:N");

        GeneralSingleLine(DataItems.Warehouse, DataItems.Both, DataItems.articleData180[2], DataItems.articleData02[2], DataItems.articleData180[6], DataItems.articleData02[2], DataItems.None);
    }

    @Test //SL_W_04 : SSingle Line - Warehouse - Similar Article:No - H:Y -W:Y
            (groups = {"CCE"})
    public void SL_W_04() throws Exception {
        //New driver object to control browser
        WebDriver driver = getDriver();

        //New base object to handle log-in and set up
        Cce_Base base = new Cce_Base(driver);

        //Set up returns a CCE Page and outputs test details
        base.setUp("SL_W_04", "Single Line - Warehouse - Similar Article:No - H:Y -W:Y");

        GeneralSingleLine(DataItems.Warehouse, DataItems.Both, DataItems.articleData180[2], DataItems.articleData180[2], DataItems.articleData180[6], DataItems.articleData180[2], DataItems.Warehouse);
    }

    @Test //SL_W_05 : Single Line - Warehouse - Similar Article:Yes - H:Y -W:N
            (groups = {"CCE"})
    public void SL_W_05() throws Exception {
        //New driver object to control browser
        WebDriver driver = getDriver();

        //New base object to handle log-in and set up
        Cce_Base base = new Cce_Base(driver);

        //Set up returns a CCE Page and outputs test details
        base.setUp("SL_W_05", "Single Line - Warehouse - Similar Article:Yes - H:Y - W:N");

        GeneralSingleLine(DataItems.Warehouse, DataItems.Hub, DataItems.similarArticle01[0], DataItems.articleData180[2], DataItems.articleData180[6], DataItems.articleData180[2], DataItems.None);
    }

    @Test //SL_W_08 : SSingle Line - Warehouse - Similar Article:Yes - H:Y -W:Y
            (groups = {"CCE"})
    public void SL_W_08() throws Exception {
        //New driver object to control browser
        WebDriver driver = getDriver();

        //New base object to handle log-in and set up
        Cce_Base base = new Cce_Base(driver);

        //Set up returns a CCE Page and outputs test details
        base.setUp("SL_W_08", "Single Line - Warehouse - Similar Article:Yes - H:Y -W:Y");

        GeneralSingleLine(DataItems.Warehouse, DataItems.Both, DataItems.similarArticle01[0], DataItems.articleData180[2], DataItems.articleData180[6], DataItems.similarArticle01[0], DataItems.Warehouse);
    }

    @Test //SL_H_01 : Single Line - Hub - Similar Article:No - H:Y -W:N
            (groups = {"CCE"})
    public void SL_H_01() throws Exception {
        //New driver object to control browser
        WebDriver driver = getDriver();

        //New base object to handle log-in and set up
        Cce_Base base = new Cce_Base(driver);

        //Set up returns a CCE Page and outputs test details
        base.setUp("SL_H_01", "Single Line - Hub - Similar Article:No - H:Y - W:N");

        GeneralSingleLine(DataItems.Hub, DataItems.Hub, DataItems.articleData180[2], DataItems.articleData180[2], DataItems.articleData180[6], DataItems.articleData180[2], DataItems.Hub);
    }

    @Test //SL_H_02 : Single Line - Hub - Similar Article:No - H:N -W:Y
            (groups = {"CCE"})
    public void SL_H_02() throws Exception {
        //New driver object to control browser
        WebDriver driver = getDriver();

        //New base object to handle log-in and set up
        Cce_Base base = new Cce_Base(driver);

        //Set up returns a CCE Page and outputs test details
        base.setUp("SL_H_02", "Single Line - Hub - Similar Article:No - H:N -W:Y");

        GeneralSingleLine(DataItems.Hub, DataItems.Warehouse, DataItems.articleData180[2], DataItems.articleData180[2], DataItems.articleData180[6], DataItems.articleData180[2], DataItems.None);
    }

    @Test //SL_H_03 : Single Line - Hub - Similar Article:No - H:N -W:N
            (groups = {"CCE"})
    public void SL_H_03() throws Exception {
        //New driver object to control browser
        WebDriver driver = getDriver();

        //New base object to handle log-in and set up
        Cce_Base base = new Cce_Base(driver);

        //Set up returns a CCE Page and outputs test details
        base.setUp("SL_H_03", "Single Line - Hub - Similar Article:No - H:N -W:N");

        GeneralSingleLine(DataItems.Hub, DataItems.Both, DataItems.articleData180[2], DataItems.articleData02[2], DataItems.articleData180[6], DataItems.articleData02[2], DataItems.None);
    }

    @Test //SL_H_04 : Single Line - Hub - Similar Article:No - H:Y -W:Y
            (groups = {"CCE"})
    public void SL_H_04() throws Exception {
        //New driver object to control browser
        WebDriver driver = getDriver();

        //New base object to handle log-in and set up
        Cce_Base base = new Cce_Base(driver);

        //Set up returns a CCE Page and outputs test details
        base.setUp("SL_H_04", "Single Line - Hub - Similar Article:No - H:Y -W:Y");

        GeneralSingleLine(DataItems.Hub, DataItems.Both, DataItems.articleData180[2], DataItems.articleData180[2], DataItems.articleData180[6], DataItems.articleData180[2], DataItems.Hub);
    }


    @Test //SL_H_06 : Single Line - Hub - Similar Article:Yes - H:N -W:Y
            (groups = {"CCE"})
    public void SL_H_06() throws Exception {
        //New driver object to control browser
        WebDriver driver = getDriver();

        //New base object to handle log-in and set up
        Cce_Base base = new Cce_Base(driver);

        //Set up returns a CCE Page and outputs test details
        base.setUp("SL_H_06", "Single Line - Hub - Similar Article:Yes - H:N -W:Y");

        GeneralSingleLine(DataItems.Hub, DataItems.Warehouse, DataItems.similarArticle01[0], DataItems.articleData180[2], DataItems.articleData180[6], DataItems.articleData180[2], DataItems.None);
    }


    @Test //SL_H_08 : SSingle Line - Hub - Similar Article:Yes - H:Y -W:Y
            (groups = {"CCE"})
    public void SL_H_08() throws Exception {
        //New driver object to control browser
        WebDriver driver = getDriver();

        //New base object to handle log-in and set up
        Cce_Base base = new Cce_Base(driver);

        //Set up returns a CCE Page and outputs test details
        base.setUp("SL_H_08", "Single Line - Hub - Similar Article:Yes - H:Y -W:Y");

        GeneralSingleLine(DataItems.Hub, DataItems.Both, DataItems.similarArticle01[0], DataItems.articleData180[2], DataItems.articleData180[6], DataItems.articleData180[2], DataItems.None);
    }


    //------------------------------------------------------------------------------------------------------------------------


    public void setEnv(String salesOrgEnrichOption) throws Exception {
        //New driver object to control browser
        WebDriver driver = getDriver();

        //Setting Master Data
        System.out.println("Setting Master Data for Sales Org and Customer...");
        PreFlows pf = new PreFlows();
        if (salesOrgEnrichOption == "Both")
            pf.activateEnrichToForSalesOrgAndCustomer(driver, DataItems.salesOrgID, DataItems.lifeEasyCustomer, DataItems.enrichBoth);
        else if (salesOrgEnrichOption == "Hub")
            pf.activateEnrichToForSalesOrgAndCustomer(driver, DataItems.salesOrgID, DataItems.lifeEasyCustomer, DataItems.enrichHub);
        else if (salesOrgEnrichOption == "Warehouse")
            pf.activateEnrichToForSalesOrgAndCustomer(driver, DataItems.salesOrgID, DataItems.lifeEasyCustomer, DataItems.enrichWarehouse);
        else System.out.println("WRONG INPUT FOR SELECTED 'ENRICH TO' PARAM...");

        CCE_SOS_ServeArticle_Page sos = new CCE_SOS_ServeArticle_Page(driver);

        driver.get(DataItems.hubStockURL);
        sos.filterArticlesByPlantAndDeleteAll(DataItems.plantID12);
        driver.get(DataItems.warehouseURL);
        sos.filterArticlesByPlantAndDeleteAll(DataItems.plantID12);
    }

    public void addArticlesToStock3Lines(String addArticleTo, String article1, String shade1, String article2, String shade2, String article3, String shade3) throws Exception {

        WebDriver driver = getDriver();
        CCE_SOS_ServeArticle_Page sos = new CCE_SOS_ServeArticle_Page(driver);

        if (addArticleTo == "Hub") {
            //Add article to HUB
            System.out.println("Navigating to HubStock page...");
            driver.get(DataItems.hubStockURL);
            System.out.println("Adding Article to HubStock...");
            sos.setStockArticleAndSave(DataItems.addToHub, DataItems.articleData180[0], DataItems.articleData180[1], article1,
                     shade1, DataItems.articleData180[7]);
            driver.get(DataItems.hubStockURL);
            System.out.println("Adding Article to HubStock...");
            sos.setStockArticleAndSave(DataItems.addToHub, DataItems.articleData120[0], DataItems.articleData120[1], article2,
                     shade2, DataItems.articleData120[7]);
            driver.get(DataItems.hubStockURL);
            System.out.println("Adding Article to HubStock...");
            sos.setStockArticleAndSave(DataItems.addToHub, DataItems.articleData110[0], DataItems.articleData110[1], article3,
                     shade3, DataItems.articleData110[7]);
        } else if (addArticleTo == "Warehouse") {
            //Add article to HUB
            System.out.println("Navigating to Warehouse page...");
            driver.get(DataItems.warehouseURL);
            System.out.println("Adding Article to WarehouseStock...");
            sos.setStockArticleAndSave(DataItems.addToWarehouse, DataItems.articleData180[0], DataItems.articleData180[1], article1,
                     shade1, DataItems.articleData180[7]);
            driver.get(DataItems.warehouseURL);
            System.out.println("Adding Article to WarehouseStock...");
            sos.setStockArticleAndSave(DataItems.addToWarehouse, DataItems.articleData120[0], DataItems.articleData120[1], article2,
                     shade2, DataItems.articleData120[7]);
            driver.get(DataItems.warehouseURL);
            System.out.println("Adding Article to WarehouseStock...");
            sos.setStockArticleAndSave(DataItems.addToWarehouse, DataItems.articleData110[0], DataItems.articleData110[1], article3,
                     shade3, DataItems.articleData110[7]);
        } else if (addArticleTo == "MixHWH") {

            System.out.println("Navigating to HubStock page...");
            driver.get(DataItems.hubStockURL);
            System.out.println("Adding Article to HubStock...");
            sos.setStockArticleAndSave(DataItems.addToHub, DataItems.articleData180[0], DataItems.articleData180[1], article1,
                     shade1, DataItems.articleData180[7]);
            driver.get(DataItems.warehouseURL);
            System.out.println("Adding Article to WarehouseStock...");
            sos.setStockArticleAndSave(DataItems.addToWarehouse, DataItems.articleData120[0], DataItems.articleData120[1], article2,
                     shade2, DataItems.articleData120[7]);
            driver.get(DataItems.hubStockURL);
            System.out.println("Adding Article to HubStock...");
            sos.setStockArticleAndSave(DataItems.addToHub, DataItems.articleData110[0], DataItems.articleData110[1], article3,
                     shade3, DataItems.articleData110[7]);
        } else if (addArticleTo == "MixWHW") {

            System.out.println("Adding Article to WarehouseStock...");
            driver.get(DataItems.warehouseURL);
            sos.setStockArticleAndSave(DataItems.addToWarehouse, DataItems.articleData180[0], DataItems.articleData180[1], article1,
                     shade1, DataItems.articleData180[7]);
            System.out.println("Adding Article to HubStock...");
            driver.get(DataItems.hubStockURL);
            sos.setStockArticleAndSave(DataItems.addToHub, DataItems.articleData120[0], DataItems.articleData120[1], article2,
                     shade2, DataItems.articleData120[7]);
            System.out.println("Adding Article to WarehouseStock...");
            driver.get(DataItems.warehouseURL);
            sos.setStockArticleAndSave(DataItems.addToWarehouse, DataItems.articleData110[0], DataItems.articleData110[1], article3,
                     shade3, DataItems.articleData110[7]);
        }
    }

    public void expectedResults3Lines(String expectedArticleLine1, String expectedSOSLine1, String expectedArticleLine2, String expectedSOSLine2, String expectedArticleLine3, String expectedSOSLine3) throws Exception {

        WebDriver driver = getDriver();

        CCE_SOS_ServeArticle_Page sos = new CCE_SOS_ServeArticle_Page(driver);
        System.out.println(sos.getCurrentSOSLine(2).getText());
        System.out.println(sos.getCurrentSOSLine(3).getText());
        System.out.println(sos.getCurrentSOSLine(4).getText());
        AssertJUnit.assertTrue("SOS Type is not what expected on Line 1", sos.getCurrentSOSLine(2).getText().equals(expectedSOSLine1));
        AssertJUnit.assertTrue("SOS Type is not what expected on Line 2", sos.getCurrentSOSLine(3).getText().equals(expectedSOSLine2));
        AssertJUnit.assertTrue("SOS Type is not what expected on Line 2", sos.getCurrentSOSLine(4).getText().equals(expectedSOSLine3));

        sos.pressViewOrder();
        System.out.println(sos.getArticleFromOrderLine(2).getText());
        System.out.println(sos.getArticleFromOrderLine(3).getText());
        System.out.println(sos.getArticleFromOrderLine(4).getText());
        AssertJUnit.assertTrue("Article is not what expected on Line 1", sos.getArticleFromOrderLine(2).getText().equals(expectedArticleLine1));
        AssertJUnit.assertTrue("Article is not what expected on Line 2", sos.getArticleFromOrderLine(3).getText().equals(expectedArticleLine2));
        AssertJUnit.assertTrue("Article is not what expected on Line 3", sos.getArticleFromOrderLine(4).getText().equals(expectedArticleLine3));
    }

    public void order3Lines(String article1, String shade1, String article2, String shade2, String article3, String shade3) throws Exception {

        WebDriver driver = getDriver();

        CCE_AddOrderPage aop = new CCE_AddOrderPage(driver);
        System.out.println("Entering product details first line...");
        aop.inputArticleShadeMUMForLine(article1, DataItems.articleData180[5], shade1, 0);

        System.out.println("Entering product details second line...");
        aop.pressNewLineAlt(1);
        aop.inputArticleShadeMUMForLine(article2, DataItems.articleData180[5], shade2, 1);

        System.out.println("Entering product details third line...");
        aop.pressNewLineAlt(2);
        aop.inputArticleShadeMUMForLine(article3, DataItems.articleData180[5], shade3, 2);

        aop.pressSubmit();
    }


    @Test //ML_B_01 : Multi Line – Both – Similar Article:No – L1[H:Y, W:N] – L2[H:Y, W:N] – L3[H:Y, W:N]
            (groups = {"CCE"})
    public void ML_B_01() throws Exception {
        //New driver object to control browser
        WebDriver driver = getDriver();

        //New base object to handle log-in and set up
        Cce_Base base = new Cce_Base(driver);

        //Set up returns a CCE Page and outputs test details
        base.setUp("ML_B_01", "Multi Line – Both – Similar Article:No – L1[H:Y, W:N] – L2[H:Y, W:N] – L3[H:Y, W:N] ");

        setEnv(DataItems.Both);

        addSimilarArticle01();
        addSimilarArticle02();
        addSimilarArticle03();

        addArticlesToStock3Lines(DataItems.Hub, DataItems.articleData180[2], DataItems.articleData180[6], DataItems.articleData120[2], DataItems.articleData180[6], DataItems.articleData110[2], DataItems.articleData180[6]);

        System.out.println("Creating order...");
        completeOrderPromptPageAndAddCustDetails();

        order3Lines(DataItems.articleData180[2], DataItems.articleData180[6], DataItems.articleData120[2], DataItems.articleData180[6], DataItems.articleData110[2], DataItems.articleData180[6]);

        expectedResults3Lines(DataItems.articleData180[2], DataItems.Hub, DataItems.articleData120[2], DataItems.Hub, DataItems.articleData110[2], DataItems.Hub);
    }

    @Test //ML_B_02 : Multi Line – Both – Similar Article:No – L1[H:N, W:Y] – L2[H:N, W:Y] – L3[H:N, W:Y]
            (groups = {"CCE"})
    public void ML_B_02() throws Exception {
        //New driver object to control browser
        WebDriver driver = getDriver();

        //New base object to handle log-in and set up
        Cce_Base base = new Cce_Base(driver);

        //Set up returns a CCE Page and outputs test details
        base.setUp("ML_B_2", "Multi Line – Both – Similar Article:No – L1[H:Y, W:N] – L2[H:Y, W:N] – L3[H:Y, W:N] ");

        setEnv(DataItems.Both);

        addArticlesToStock3Lines(DataItems.Warehouse, DataItems.articleData180[2], DataItems.articleData180[6], DataItems.articleData120[2], DataItems.articleData180[6], DataItems.articleData110[2], DataItems.articleData180[6]);

        System.out.println("Creating order...");
        completeOrderPromptPageAndAddCustDetails();

        order3Lines(DataItems.articleData180[2], DataItems.articleData180[6], DataItems.articleData120[2], DataItems.articleData180[6], DataItems.articleData110[2], DataItems.articleData180[6]);

        expectedResults3Lines(DataItems.articleData180[2], DataItems.Warehouse, DataItems.articleData120[2], DataItems.Warehouse, DataItems.articleData110[2], DataItems.Warehouse);
    }

    @Test //ML_B_03 : Multi Line – Both – Similar Article:No – L1[H:N, W:N] – L2[H:N, W:N] – L3[H:N, W:N]
            (groups = {"CCE"})
    public void ML_B_03() throws Exception {
        //New driver object to control browser
        WebDriver driver = getDriver();

        //New base object to handle log-in and set up
        Cce_Base base = new Cce_Base(driver);

        //Set up returns a CCE Page and outputs test details
        base.setUp("ML_B_03", "Multi Line – Both – Similar Article:No – L1[H:N, W:N] – L2[H:N, W:N] – L3[H:N, W:N]");

        setEnv(DataItems.Both);

        System.out.println("Creating order...");
        completeOrderPromptPageAndAddCustDetails();

        order3Lines(DataItems.articleData180[2], DataItems.articleData180[6], DataItems.articleData120[2], DataItems.articleData180[6], DataItems.articleData110[2], DataItems.articleData180[6]);

        expectedResults3Lines(DataItems.articleData180[2], DataItems.None, DataItems.articleData120[2], DataItems.None, DataItems.articleData110[2], DataItems.None);
    }


    @Test //ML_B_04 : Multi Line – Both – Similar Article:No – L1[H:Y, W:N] – L2[H:N, W:Y] – L3[H:N, W:N]
            (groups = {"CCE"})
    public void ML_B_04() throws Exception {
        //New driver object to control browser
        WebDriver driver = getDriver();

        //New base object to handle log-in and set up
        Cce_Base base = new Cce_Base(driver);

        //Set up returns a CCE Page and outputs test details
        base.setUp("ML_B_04", "Multi Line – Both – Similar Article:No – L1[H:Y, W:N] – L2[H:N, W:Y] – L3[H:N, W:N] ");

        setEnv(DataItems.Both);

        addArticlesToStock3Lines(DataItems.MixHWH, DataItems.articleData180[2], DataItems.articleData180[6], DataItems.articleData120[2], DataItems.articleData180[6], DataItems.noneArticle01, DataItems.articleData180[6]);

        System.out.println("Creating order...");
        completeOrderPromptPageAndAddCustDetails();

        order3Lines(DataItems.articleData180[2], DataItems.articleData180[6], DataItems.articleData120[2], DataItems.articleData180[6], DataItems.articleData110[2], DataItems.articleData180[6]);

        expectedResults3Lines(DataItems.articleData180[2], DataItems.Hub, DataItems.articleData120[2], DataItems.Warehouse, DataItems.articleData110[2], DataItems.None);
    }

    @Test //ML_B_05 : Multi Line – Both – Similar Article:Yes – L1[H:Y, W:N] – L2[H:Y, W:N] – L3[H:Y, W:N]
            (groups = {"CCE"})
    public void ML_B_05() throws Exception {
        //New driver object to control browser
        WebDriver driver = getDriver();

        //New base object to handle log-in and set up
        Cce_Base base = new Cce_Base(driver);

        //Set up returns a CCE Page and outputs test details
        base.setUp("ML_B_05", "Multi Line – Both – Similar Article:Yes – L1[H:Y, W:N] – L2[H:Y, W:N] – L3[H:Y, W:N]");

        setEnv(DataItems.Both);

        addArticlesToStock3Lines(DataItems.Hub, DataItems.similarArticle01[0], DataItems.articleData180[6], DataItems.similarArticle02[0], DataItems.articleData180[6], DataItems.similarArticle03[0], DataItems.articleData180[6]);

        System.out.println("Creating order...");
        completeOrderPromptPageAndAddCustDetails();

        order3Lines(DataItems.articleData180[2], DataItems.articleData180[6], DataItems.articleData120[2], DataItems.articleData180[6], DataItems.articleData110[2], DataItems.articleData180[6]);

        expectedResults3Lines(DataItems.articleData180[2], DataItems.None, DataItems.articleData120[2], DataItems.None, DataItems.articleData110[2], DataItems.None);
    }

    @Test //ML_B_06 : Multi Line – Both – Similar Article:Yes – L1[H:N, W:Y] – L2[H:N, W:Y] – L3[H:N, W:Y]
            (groups = {"CCE"})
    public void ML_B_06() throws Exception {
        //New driver object to control browser
        WebDriver driver = getDriver();

        //New base object to handle log-in and set up
        Cce_Base base = new Cce_Base(driver);

        //Set up returns a CCE Page and outputs test details
        base.setUp("ML_B_06", "Multi Line – Both – Similar Article:Yes – L1[H:N, W:Y] – L2[H:N, W:Y] – L3[H:N, W:Y]  ");

        setEnv(DataItems.Both);

        addArticlesToStock3Lines(DataItems.Warehouse, DataItems.similarArticle01[0], DataItems.articleData180[6], DataItems.similarArticle02[0], DataItems.articleData180[6], DataItems.similarArticle03[0], DataItems.articleData180[6]);

        System.out.println("Creating order...");
        completeOrderPromptPageAndAddCustDetails();

        order3Lines(DataItems.articleData180[2], DataItems.articleData180[6], DataItems.articleData120[2], DataItems.articleData180[6], DataItems.articleData110[2], DataItems.articleData180[6]);

        expectedResults3Lines(DataItems.similarArticle01[0], DataItems.Warehouse, DataItems.similarArticle02[0], DataItems.Warehouse, DataItems.similarArticle03[0], DataItems.Warehouse);
    }


    @Test //ML_B_08 : Multi Line – Both – Similar Article:Yes – L1[H:Y, W:N] – L2[H:N, W:Y] – L3[H:N, W:N]
            (groups = {"CCE"})
    public void ML_B_08() throws Exception {
        //New driver object to control browser
        WebDriver driver = getDriver();

        //New base object to handle log-in and set up
        Cce_Base base = new Cce_Base(driver);

        //Set up returns a CCE Page and outputs test details
        base.setUp("ML_B_08", "Multi Line – Both – Similar Article:Yes – L1[H:Y, W:N] – L2[H:N, W:Y] – L3[H:N, W:N]");

        setEnv(DataItems.Both);

        addArticlesToStock3Lines(DataItems.MixHWH, DataItems.similarArticle01[0], DataItems.articleData180[6], DataItems.similarArticle02[0], DataItems.articleData180[6], DataItems.similarArticle03[0], DataItems.articleData180[6]);

        System.out.println("Creating order...");
        completeOrderPromptPageAndAddCustDetails();

        order3Lines(DataItems.articleData180[2], DataItems.articleData180[6], DataItems.articleData120[2], DataItems.articleData180[6], DataItems.articleData110[2], DataItems.articleData180[6]);

        expectedResults3Lines(DataItems.articleData180[2], DataItems.None, DataItems.similarArticle02[0], DataItems.Warehouse, DataItems.articleData110[2], DataItems.None);
    }

    @Test //ML_B_09 : Multi Line – Both – Similar Article:Mixed – L1[H:Y, W:N] – L2[H:N, W:Y] – L3[H:N, W:N]
            (groups = {"CCE"})
    public void ML_B_09() throws Exception {
        //New driver object to control browser
        WebDriver driver = getDriver();

        //New base object to handle log-in and set up
        Cce_Base base = new Cce_Base(driver);

        //Set up returns a CCE Page and outputs test details
        base.setUp("ML_B_09", "Multi Line – Both – Similar Article:Mixed – L1[H:Y, W:N] – L2[H:N, W:Y] – L3[H:N, W:N]  ");

        setEnv(DataItems.Both);

        addArticlesToStock3Lines(DataItems.MixHWH, DataItems.similarArticle01[0], DataItems.articleData180[6], DataItems.similarArticle02[0], DataItems.articleData180[6], DataItems.articleData110[2], DataItems.articleData180[6]);

        System.out.println("Creating order...");
        completeOrderPromptPageAndAddCustDetails();

        order3Lines(DataItems.articleData180[2], DataItems.articleData180[6], DataItems.articleData120[2], DataItems.articleData180[6], DataItems.articleData110[2], DataItems.articleData180[6]);

        expectedResults3Lines(DataItems.articleData180[2], DataItems.None, DataItems.similarArticle02[0], DataItems.Warehouse, DataItems.articleData110[2], DataItems.Hub);
    }

    @Test //ML_B_10 : Multi Line – Both – Similar Article:Mixed – L1[H:Y, W:N] – L2[H:N, W:Y] – L3[H:N, W:N]
            (groups = {"CCE"})
    public void ML_B_10() throws Exception {
        //New driver object to control browser
        WebDriver driver = getDriver();

        //New base object to handle log-in and set up
        Cce_Base base = new Cce_Base(driver);

        //Set up returns a CCE Page and outputs test details
        base.setUp("ML_B_10", "Multi Line – Both – Similar Article:Mixed – L1[H:Y, W:N] – L2[H:N, W:Y] – L3[H:N, W:N] ");

        setEnv(DataItems.Both);

        addArticlesToStock3Lines(DataItems.MixHWH, DataItems.articleData180[2], DataItems.articleData180[6], DataItems.articleData120[2], DataItems.articleData180[6], DataItems.similarArticle03[0], DataItems.articleData180[6]);

        System.out.println("Creating order...");
        completeOrderPromptPageAndAddCustDetails();

        order3Lines(DataItems.articleData180[2], DataItems.articleData180[6], DataItems.articleData120[2], DataItems.articleData180[6], DataItems.articleData110[2], DataItems.articleData180[6]);

        expectedResults3Lines(DataItems.articleData180[2], DataItems.Hub, DataItems.articleData120[2], DataItems.Warehouse, DataItems.articleData110[2], DataItems.None);
    }

//--------------------------------------------------------------------------------------------------------------------------------------------------

    public void addArticlesToStock2Lines(String addArticleTo, String article1, String shade1, String article2, String shade2) throws Exception {

        WebDriver driver = getDriver();
        CCE_SOS_ServeArticle_Page sos = new CCE_SOS_ServeArticle_Page(driver);

        if (addArticleTo == "Hub") {
            System.out.println("Navigating to HubStock page...");
            driver.get(DataItems.hubStockURL);
            System.out.println("Adding Article to HubStock...");
            sos.setStockArticleAndSave(DataItems.addToHub, DataItems.articleData180[0], DataItems.articleData180[1], article1,
                     shade1, DataItems.articleData180[7]);
            driver.get(DataItems.hubStockURL);
            System.out.println("Adding Article to HubStock...");
            sos.setStockArticleAndSave(DataItems.addToHub, DataItems.articleData120[0], DataItems.articleData120[1], article2,
                     shade2, DataItems.articleData120[7]);
        } else if (addArticleTo == "Warehouse") {
            System.out.println("Navigating to Warehouse page...");
            driver.get(DataItems.warehouseURL);
            System.out.println("Adding Article to WarehouseStock...");
            sos.setStockArticleAndSave(DataItems.addToWarehouse, DataItems.articleData180[0], DataItems.articleData180[1], article1,
                     shade1, DataItems.articleData180[7]);
            driver.get(DataItems.warehouseURL);
            System.out.println("Adding Article to WarehouseStock...");
            sos.setStockArticleAndSave(DataItems.addToWarehouse, DataItems.articleData120[0], DataItems.articleData120[1], article2,
                     shade2, DataItems.articleData120[7]);
        } else if (addArticleTo == "MixHW") {
            System.out.println("Navigating to HubStock page...");
            driver.get(DataItems.hubStockURL);
            System.out.println("Adding Article to HubStock...");
            sos.setStockArticleAndSave(DataItems.addToHub, DataItems.articleData180[0], DataItems.articleData180[1], article1,
                     shade1, DataItems.articleData180[7]);
            driver.get(DataItems.warehouseURL);
            System.out.println("Adding Article to WarehouseStock...");
            sos.setStockArticleAndSave(DataItems.addToWarehouse, DataItems.articleData120[0], DataItems.articleData120[1], article2,
                     shade2, DataItems.articleData120[7]);
        } else if (addArticleTo == "MixWH") {
            System.out.println("Adding Article to WarehouseStock...");
            driver.get(DataItems.warehouseURL);
            sos.setStockArticleAndSave(DataItems.addToWarehouse, DataItems.articleData180[0], DataItems.articleData180[1], article1,
                     shade1, DataItems.articleData180[7]);
            System.out.println("Adding Article to HubStock...");
            driver.get(DataItems.hubStockURL);
            sos.setStockArticleAndSave(DataItems.addToHub, DataItems.articleData120[0], DataItems.articleData120[1], article2,
                     shade2, DataItems.articleData120[7]);
            System.out.println("Adding Article to WarehouseStock...");
        }
        else System.out.println("Wrong input...");
    }

    public void expectedResults2Lines(String expectedArticleLine1, String expectedSOSLine1, String expectedArticleLine2, String expectedSOSLine2) throws Exception {

        WebDriver driver = getDriver();

        CCE_SOS_ServeArticle_Page sos = new CCE_SOS_ServeArticle_Page(driver);
        System.out.println(sos.getCurrentSOSLine(2).getText());
        System.out.println(sos.getCurrentSOSLine(3).getText());
        AssertJUnit.assertTrue("SOS Type is not what expected on Line 1", sos.getCurrentSOSLine(2).getText().equals(expectedSOSLine1));
        AssertJUnit.assertTrue("SOS Type is not what expected on Line 2", sos.getCurrentSOSLine(3).getText().equals(expectedSOSLine2));


        sos.pressViewOrder();
        System.out.println(sos.getArticleFromOrderLine(2).getText());
        System.out.println(sos.getArticleFromOrderLine(3).getText());
        AssertJUnit.assertTrue("Article is not what expected on Line 1", sos.getArticleFromOrderLine(2).getText().equals(expectedArticleLine1));
        AssertJUnit.assertTrue("Article is not what expected on Line 2", sos.getArticleFromOrderLine(3).getText().equals(expectedArticleLine2));

    }

    public void order2Lines(String article1, String shade1, String article2, String shade2) throws Exception {

        WebDriver driver = getDriver();

        CCE_AddOrderPage aop = new CCE_AddOrderPage(driver);
        System.out.println("Entering product details first line...");
        aop.inputArticleShadeMUMForLine(article1, DataItems.articleData180[5], shade1, 0);

        System.out.println("Entering product details second line...");
        aop.pressNewLineAlt(1);
        aop.inputArticleShadeMUMForLine(article2, DataItems.articleData180[5], shade2, 1);

        aop.pressSubmit();
    }

    @Test //ML_H_01 : Multi Line – Hub – Similar Article:No – L1[H:Y] – L2[H:N]
            (groups = {"CCE"})
    public void ML_H_01() throws Exception {
        //New driver object to control browser
        WebDriver driver = getDriver();

        //New base object to handle log-in and set up
        Cce_Base base = new Cce_Base(driver);

        //Set up returns a CCE Page and outputs test details
        base.setUp("ML_H_01", "Multi Hub – Both – Multi Line – Hub – Similar Article:No – L1[H:Y] – L2[H:N]  ");

        setEnv(DataItems.Hub);

        addArticlesToStock2Lines(DataItems.Hub, DataItems.articleData180[2], DataItems.articleData180[6], DataItems.articleData120[2], DataItems.articleData180[6]);

        System.out.println("Creating order...");
        completeOrderPromptPageAndAddCustDetails();

        order2Lines(DataItems.articleData180[2], DataItems.articleData180[6], DataItems.articleData110[2], DataItems.articleData180[6]);

        expectedResults2Lines(DataItems.articleData180[2], DataItems.Hub, DataItems.articleData110[2], DataItems.None);
    }

    @Test //ML_H_02 : Multi Line – Hub – Similar Article:No – L1[H:N, W:Y] – L2[H:Y]
            (groups = {"CCE"})
    public void ML_H_02() throws Exception {
        //New driver object to control browser
        WebDriver driver = getDriver();

        //New base object to handle log-in and set up
        Cce_Base base = new Cce_Base(driver);

        //Set up returns a CCE Page and outputs test details
        base.setUp("ML_H_02", "Multi Line – Hub – Similar Article:No – L1[H:N, W:Y] – L2[H:Y] ");

        setEnv(DataItems.Hub);

        addArticlesToStock2Lines(DataItems.MixWH, DataItems.articleData180[2], DataItems.articleData180[6], DataItems.articleData120[2], DataItems.articleData180[6]);

        System.out.println("Creating order...");
        completeOrderPromptPageAndAddCustDetails();

        order2Lines(DataItems.articleData180[2], DataItems.articleData180[6], DataItems.articleData120[2], DataItems.articleData180[6]);

        expectedResults2Lines(DataItems.articleData180[2], DataItems.None, DataItems.articleData120[2], DataItems.Hub);
    }

    @Test //ML_H_03 : Multi Line – Hub – Similar Article:Yes – L1[H:Y] – L2[H:N]
            (groups = {"CCE"})
    public void ML_H_03() throws Exception {
        //New driver object to control browser
        WebDriver driver = getDriver();

        //New base object to handle log-in and set up
        Cce_Base base = new Cce_Base(driver);

        //Set up returns a CCE Page and outputs test details
        base.setUp("ML_H_03", "Multi Line – Hub – Similar Article:Yes – L1[H:Y] – L2[H:N]  ");

        setEnv(DataItems.Hub);

        addArticlesToStock2Lines(DataItems.Hub, DataItems.similarArticle01[0], DataItems.articleData180[6], DataItems.similarArticle02[0], DataItems.articleData180[6]);

        System.out.println("Creating order...");
        completeOrderPromptPageAndAddCustDetails();

        order2Lines(DataItems.articleData180[2], DataItems.articleData180[6], DataItems.articleData120[2], DataItems.articleData180[6]);

        expectedResults2Lines(DataItems.articleData180[2], DataItems.None, DataItems.similarArticle02[0], DataItems.Hub);
    }

    @Test //ML_H_04 : Multi Line – Hub – Similar Article:Yes – L1[H:N, W:Y] – L2[H:Y]
            (groups = {"CCE"})
    public void ML_H_04() throws Exception {
        //New driver object to control browser
        WebDriver driver = getDriver();

        //New base object to handle log-in and set up
        Cce_Base base = new Cce_Base(driver);

        //Set up returns a CCE Page and outputs test details
        base.setUp("ML_H_04", "Multi Line – Hub – Similar Article:Yes – L1[H:N, W:Y] – L2[H:Y]  ");

        setEnv(DataItems.Hub);

        addArticlesToStock2Lines(DataItems.MixWH, DataItems.similarArticle01[0], DataItems.articleData180[6], DataItems.similarArticle02[0], DataItems.articleData180[6]);

        System.out.println("Creating order...");
        completeOrderPromptPageAndAddCustDetails();

        order2Lines(DataItems.articleData180[2], DataItems.articleData180[6], DataItems.articleData120[2], DataItems.articleData180[6]);

        expectedResults2Lines(DataItems.articleData180[2], DataItems.None, DataItems.articleData120[2], DataItems.None);
    }

    @Test //ML_H_05 : Multi Line – Hub – Similar Article:Mix – L1[H:Y] – L2[H:N]
            (groups = {"CCE"})
    public void ML_H_05() throws Exception {
        //New driver object to control browser
        WebDriver driver = getDriver();

        //New base object to handle log-in and set up
        Cce_Base base = new Cce_Base(driver);

        //Set up returns a CCE Page and outputs test details
        base.setUp("ML_H_05", "Multi Line – Hub – Similar Article:Mix – L1[H:Y] – L2[H:N] ");

        setEnv(DataItems.Hub);

        addArticlesToStock2Lines(DataItems.Hub, DataItems.similarArticle01[0], DataItems.articleData180[6], DataItems.articleData120[2], DataItems.articleData180[6]);

        System.out.println("Creating order...");
        completeOrderPromptPageAndAddCustDetails();

        order2Lines(DataItems.articleData180[2], DataItems.articleData180[6], DataItems.articleData120[2], DataItems.articleData180[6]);

        expectedResults2Lines(DataItems.articleData180[2], DataItems.None, DataItems.articleData120[2], DataItems.Hub);
    }

    @Test //ML_H_06 : Multi Line – Hub – Similar Article:Mix – L1[H:N, W:Y] – L2[H:Y]
            (groups = {"CCE"})
    public void ML_H_06() throws Exception {
        //New driver object to control browser
        WebDriver driver = getDriver();

        //New base object to handle log-in and set up
        Cce_Base base = new Cce_Base(driver);

        //Set up returns a CCE Page and outputs test details
        base.setUp("ML_H_06", "Multi Line – Hub – Similar Article:Mix – L1[H:N, W:Y] – L2[H:Y] ");

        setEnv(DataItems.Hub);

        addArticlesToStock2Lines(DataItems.MixWH, DataItems.articleData180[2], DataItems.articleData180[6], DataItems.similarArticle02[0], DataItems.articleData180[6]);

        System.out.println("Creating order...");
        completeOrderPromptPageAndAddCustDetails();

        order2Lines(DataItems.articleData180[2], DataItems.articleData180[6], DataItems.articleData120[2], DataItems.articleData180[6]);

        expectedResults2Lines(DataItems.None, DataItems.Hub, DataItems.articleData120[2], DataItems.None);
    }

    @Test //ML_W_01 : Multi Line – Warehouse – Similar Article:No – L1[W:Y] – L2[W:N]
            (groups = {"CCE"})
    public void ML_W_01() throws Exception {
        //New driver object to control browser
        WebDriver driver = getDriver();

        //New base object to handle log-in and set up
        Cce_Base base = new Cce_Base(driver);

        //Set up returns a CCE Page and outputs test details
        base.setUp("ML_W_01", "Multi Line – Warehouse – Similar Article:No – L1[W:Y] – L2[W:N] ");

        setEnv(DataItems.Warehouse);

        addArticlesToStock2Lines(DataItems.Warehouse, DataItems.articleData180[2], DataItems.articleData180[6], DataItems.noneArticle01, DataItems.articleData180[6]);

        System.out.println("Creating order...");
        completeOrderPromptPageAndAddCustDetails();

        order2Lines(DataItems.articleData180[2], DataItems.articleData180[6], DataItems.articleData120[2], DataItems.articleData180[6]);

        expectedResults2Lines(DataItems.articleData180[2], DataItems.Warehouse, DataItems.articleData120[2], DataItems.None);
    }

    @Test //ML_W_02 : Multi Line – Warehouse – Similar Article:No – L1[W:N, H:Y] – L2[W:Y]
            (groups = {"CCE"})
    public void ML_W_02() throws Exception {
        //New driver object to control browser
        WebDriver driver = getDriver();

        //New base object to handle log-in and set up
        Cce_Base base = new Cce_Base(driver);

        //Set up returns a CCE Page and outputs test details
        base.setUp("ML_W_02", "Multi Line – Warehouse – Similar Article:No – L1[W:N, H:Y] – L2[W:Y] ");

        setEnv(DataItems.Warehouse);

        addArticlesToStock2Lines(DataItems.MixHW, DataItems.articleData180[2], DataItems.articleData180[6], DataItems.articleData120[2], DataItems.articleData180[6]);

        System.out.println("Creating order...");
        completeOrderPromptPageAndAddCustDetails();

        order2Lines(DataItems.articleData180[2], DataItems.articleData180[6], DataItems.articleData120[2], DataItems.articleData180[6]);

        expectedResults2Lines(DataItems.articleData180[2], DataItems.None, DataItems.articleData120[2], DataItems.None);
    }

    @Test //ML_W_03 : Multi Line – Warehouse – Similar Article:Yes – L1[W:Y] – L2[W:N]
            (groups = {"CCE"})
    public void ML_W_03() throws Exception {
        //New driver object to control browser
        WebDriver driver = getDriver();

        //New base object to handle log-in and set up
        Cce_Base base = new Cce_Base(driver);

        //Set up returns a CCE Page and outputs test details
        base.setUp("ML_W_03", "Multi Line – Warehouse – Similar Article:Yes – L1[W:Y] – L2[W:N]");

        setEnv(DataItems.Warehouse);

        addArticlesToStock2Lines(DataItems.Warehouse, DataItems.similarArticle01[0], DataItems.articleData180[6], DataItems.articleData120[2], DataItems.articleData180[6]);

        System.out.println("Creating order...");
        completeOrderPromptPageAndAddCustDetails();

        order2Lines(DataItems.articleData180[2], DataItems.articleData180[6], DataItems.articleData120[2], DataItems.articleData180[6]);

        expectedResults2Lines(DataItems.similarArticle01[0], DataItems.Warehouse, DataItems.articleData120[2], DataItems.None);
    }

    @Test //ML_W_04 : Multi Line – Warehouse – Similar Article:Yes – L1[W:N, H:Y] – L2[W:Y]
            (groups = {"CCE"})
    public void ML_W_04() throws Exception {
        //New driver object to control browser
        WebDriver driver = getDriver();

        //New base object to handle log-in and set up
        Cce_Base base = new Cce_Base(driver);

        //Set up returns a CCE Page and outputs test details
        base.setUp("ML_W_04", "Multi Line – Warehouse – Similar Article:Yes – L1[W:N, H:Y] – L2[W:Y]  ");

        setEnv(DataItems.Warehouse);

        addArticlesToStock2Lines(DataItems.MixHW, DataItems.similarArticle01[0], DataItems.articleData180[6], DataItems.similarArticle02[0], DataItems.articleData180[6]);

        System.out.println("Creating order...");
        completeOrderPromptPageAndAddCustDetails();

        order2Lines(DataItems.articleData180[2], DataItems.articleData180[6],  DataItems.articleData120[2], DataItems.articleData180[6]);

        expectedResults2Lines(DataItems.articleData180[2], DataItems.None, DataItems.similarArticle02[0], DataItems.Warehouse);
    }

    @Test //ML_W_05 : Multi Line – Warehouse – Similar Article:Mix – L1[W:Y] – L2[W:Y]
            (groups = {"CCE"})
    public void ML_W_05() throws Exception {
        //New driver object to control browser
        WebDriver driver = getDriver();

        //New base object to handle log-in and set up
        Cce_Base base = new Cce_Base(driver);

        //Set up returns a CCE Page and outputs test details
        base.setUp("ML_W_05", "Multi Line – Warehouse – Similar Article:Mix – L1[W:Y] – L2[W:Y]");

        setEnv(DataItems.Warehouse);

        addArticlesToStock2Lines(DataItems.Warehouse, DataItems.similarArticle01[0], DataItems.articleData180[6], DataItems.articleData120[2], DataItems.articleData180[6]);

        System.out.println("Creating order...");
        completeOrderPromptPageAndAddCustDetails();

        order2Lines(DataItems.articleData180[2], DataItems.articleData180[6], DataItems.articleData120[2], DataItems.articleData180[6]);

        expectedResults2Lines(DataItems.similarArticle01[0], DataItems.Warehouse,  DataItems.articleData120[2], DataItems.Warehouse);
    }

    @Test //ML_W_06 : Multi Line – Warehouse – Similar Article:Mix – L1[W:N, H:Y] – L2[W:Y]
            (groups = {"CCE"})
    public void ML_W_06() throws Exception {
        //New driver object to control browser
        WebDriver driver = getDriver();

        //New base object to handle log-in and set up
        Cce_Base base = new Cce_Base(driver);

        //Set up returns a CCE Page and outputs test details
        base.setUp("ML_W_01", "Multi Line – Warehouse – Similar Article:Mix – L1[W:N, H:Y] – L2[W:Y]  ");

        setEnv(DataItems.Warehouse);

        addArticlesToStock2Lines(DataItems.MixHW, DataItems.articleData180[2], DataItems.articleData180[6], DataItems.similarArticle02[2], DataItems.articleData180[6]);

        System.out.println("Creating order...");
        completeOrderPromptPageAndAddCustDetails();

        order2Lines(DataItems.articleData180[2], DataItems.articleData180[6], DataItems.articleData120[2], DataItems.articleData180[6]);

        expectedResults2Lines(DataItems.articleData180[2], DataItems.None, DataItems.similarArticle02[2], DataItems.Warehouse);
    }

}