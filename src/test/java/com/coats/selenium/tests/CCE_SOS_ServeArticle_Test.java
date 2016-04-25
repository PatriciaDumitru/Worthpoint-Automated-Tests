package com.coats.selenium.tests;

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

    public void GeneralSingleLine(String salesOrgEnrichOption, String addArticleTo, String articleInStock, String articleInManualEntry, String shade, String expectedArticle, String expectedSOS ) throws Exception {
        //New driver object to control browser
        WebDriver driver = getDriver();

        //Setting Master Data
        System.out.println("Setting Master Data for Sales Org and Customer...");
        PreFlows pf = new PreFlows();
        if (salesOrgEnrichOption == "Both")pf.activateEnrichToForSalesOrgAndCustomer(driver, DataItems.salesOrgID, DataItems.lifeEasyCustomer, DataItems.enrichBoth);
        else if (salesOrgEnrichOption == "Hub")pf.activateEnrichToForSalesOrgAndCustomer(driver, DataItems.salesOrgID, DataItems.lifeEasyCustomer, DataItems.enrichHub);
        else if (salesOrgEnrichOption == "Warehouse")pf.activateEnrichToForSalesOrgAndCustomer(driver, DataItems.salesOrgID, DataItems.lifeEasyCustomer, DataItems.enrichWarehouse);
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
                    DataItems.articleData180[3], DataItems.articleData180[4], DataItems.articleData180[5], DataItems.articleData180[6], DataItems.articleData180[7]);
        }

        else if (addArticleTo == "Warehouse"){
            //Add article to HUB
            System.out.println("Navigating to HubStock page...");
            driver.get(DataItems.warehouseURL);
            System.out.println("Adding Article to WarehouseStock...");
            sos.setStockArticleAndSave(DataItems.addToWarehouse, DataItems.articleData180[0], DataItems.articleData180[1], articleInStock,
                    DataItems.articleData180[3], DataItems.articleData180[4], DataItems.articleData180[5], DataItems.articleData180[6], DataItems.articleData180[7]);
        }

        else if (addArticleTo == "Both") {
            //Add article to HUB
            System.out.println("Navigating to HubStock page...");
            driver.get(DataItems.hubStockURL);
            System.out.println("Adding Article to HubStock...");
            sos.setStockArticleAndSave(DataItems.addToHub, DataItems.articleData180[0], DataItems.articleData180[1], articleInStock,
                    DataItems.articleData180[3], DataItems.articleData180[4], DataItems.articleData180[5], DataItems.articleData180[6], DataItems.articleData180[7]);

            //Add article to Warehouse
            System.out.println("Navigating to Warehouse page...");
            driver.get(DataItems.warehouseURL);
            System.out.println("Adding Article to WarehouseStock...");
            sos.setStockArticleAndSave(DataItems.addToWarehouse, DataItems.articleData180[0], DataItems.articleData180[1],articleInStock,
                    DataItems.articleData180[3], DataItems.articleData180[4], DataItems.articleData180[5], DataItems.articleData180[6], DataItems.articleData180[7]);
        }

        else System.out.println("WRONG INPUT FOR 'ADD ARTICLE TO' PARAM...");

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


    @Test //SL_B_01 : Single Line - Both - Similar Article:No - H:Y -W:N
            (groups = {"CCE"})
    public void SL_B_01() throws Exception {
        //New driver object to control browser
        WebDriver driver = getDriver();

        //New base object to handle log-in and set up
        Cce_Base base = new Cce_Base(driver);

        //Set up returns a CCE Page and outputs test details
        CCE_MainPage ccePage = base.setUp("SL_W_01", "Single Line - Both - Similar Article:No - H:Y - W:N");

        GeneralSingleLine(DataItems.Both, DataItems.Hub, DataItems.articleData180[2], DataItems.articleData180[2], DataItems.articleData180[6], DataItems.articleData180[2],DataItems.Hub);
    }

    @Test //SL_B_02 : Single Line - Both - Similar Article:No - H:N -W:Y
            (groups = {"CCE"})
    public void SL_B_02() throws Exception {
        //New driver object to control browser
        WebDriver driver = getDriver();

        //New base object to handle log-in and set up
        Cce_Base base = new Cce_Base(driver);

        //Set up returns a CCE Page and outputs test details
        CCE_MainPage ccePage = base.setUp("SL_B_02", "Single Line - Both - Similar Article:No - H:N -W:Y");

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
        CCE_MainPage ccePage = base.setUp("SL_B_03", "Single Line - Both - Similar Article:No - H:N -W:N");

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
        CCE_MainPage ccePage = base.setUp("SL_B_04", "Single Line - Both - Similar Article:No - H:Y -W:Y");

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
        CCE_MainPage ccePage = base.setUp("SL_B_05", "Single Line - Both - Similar Article:Yes - H:Y - W:N");

        GeneralSingleLine(DataItems.Both, DataItems.Hub, DataItems.articleData02[2], DataItems.articleData180[2], DataItems.articleData180[6], DataItems.articleData180[2],DataItems.None);
    }

    @Test //SL_B_06 : Single Line - Both - Similar Article:Yes - H:N -W:Y
            (groups = {"CCE"})
    public void SL_B_06() throws Exception {
        //New driver object to control browser
        WebDriver driver = getDriver();

        //New base object to handle log-in and set up
        Cce_Base base = new Cce_Base(driver);

        //Set up returns a CCE Page and outputs test details
        CCE_MainPage ccePage = base.setUp("SL_B_06", "Single Line - Both - Similar Article:No - H:N -W:Y");

        GeneralSingleLine(DataItems.Both, DataItems.Warehouse, DataItems.articleData02[2], DataItems.articleData180[2], DataItems.articleData180[6], DataItems.articleData02[2], DataItems.Warehouse);
    }

    @Test //SL_B_07 : Single Line - Both - Similar Article:Yes - H:N -W:N
            (groups = {"CCE"})
    public void SL_B_07() throws Exception {
        //New driver object to control browser
        WebDriver driver = getDriver();

        //New base object to handle log-in and set up
        Cce_Base base = new Cce_Base(driver);

        //Set up returns a CCE Page and outputs test details
        CCE_MainPage ccePage = base.setUp("SL_B_07", "Single Line - Both - Similar Article:Yes - H:N -W:N");

        GeneralSingleLine(DataItems.Both, DataItems.Both, DataItems.articleData180[2], DataItems.articleData02[2], DataItems.articleData180[6], DataItems.articleData180[2],DataItems.None);
        GeneralSingleLine(DataItems.Both, DataItems.Both, DataItems.articleData180[2], DataItems.articleData02[2], DataItems.articleData180[6], DataItems.articleData02[2],DataItems.None);
    }

    @Test //SL_B_08 : Single Line - Both - Similar Article:Yes - H:Y -W:Y
            (groups = {"CCE"})
    public void SL_B_08() throws Exception {
        //New driver object to control browser
        WebDriver driver = getDriver();

        //New base object to handle log-in and set up
        Cce_Base base = new Cce_Base(driver);

        //Set up returns a CCE Page and outputs test details
        CCE_MainPage ccePage = base.setUp("SL_B_08", "Single Line - Both - Similar Article:Yes - H:Y -W:Y");

        GeneralSingleLine(DataItems.Both, DataItems.Both, DataItems.articleData02[2], DataItems.articleData180[2], DataItems.articleData180[6], DataItems.articleData02[2],DataItems.Warehouse);
    }

    @Test //SL_W_01 : Single Line - Warehouse - Similar Article:No - H:Y -W:N
            (groups = {"CCE"})
    public void SL_W_01() throws Exception {
        //New driver object to control browser
        WebDriver driver = getDriver();

        //New base object to handle log-in and set up
        Cce_Base base = new Cce_Base(driver);

        //Set up returns a CCE Page and outputs test details
        CCE_MainPage ccePage = base.setUp("SL_W_01", "Single Line - Warehouse - Similar Article:No - H:Y - W:N");

        GeneralSingleLine(DataItems.Warehouse, DataItems.Hub, DataItems.articleData180[2], DataItems.articleData02[2], DataItems.articleData180[6], DataItems.articleData02[2],DataItems.None);
    }

    @Test //SL_W_02 : Single Line - Warehouse - Similar Article:No - H:N -W:Y
            (groups = {"CCE"})
    public void SL_W_02() throws Exception {
        //New driver object to control browser
        WebDriver driver = getDriver();

        //New base object to handle log-in and set up
        Cce_Base base = new Cce_Base(driver);

        //Set up returns a CCE Page and outputs test details
        CCE_MainPage ccePage = base.setUp("SL_W_02", "Single Line - Warehouse - Similar Article:No - H:N -W:Y");

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
        CCE_MainPage ccePage = base.setUp("SL_W_03", "Single Line - Warehouse - Similar Article:No - H:N -W:N");

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
        CCE_MainPage ccePage = base.setUp("SL_W_04", "Single Line - Warehouse - Similar Article:No - H:Y -W:Y");

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
        CCE_MainPage ccePage = base.setUp("SL_W_05", "Single Line - Warehouse - Similar Article:Yes - H:Y - W:N");

        GeneralSingleLine(DataItems.Warehouse, DataItems.Hub, DataItems.articleData02[2], DataItems.articleData180[2], DataItems.articleData180[6], DataItems.articleData180[2],DataItems.None);
    }


    @Test //SL_W_07 : Single Line - Warehouse - Similar Article:Yes - H:N -W:N
            (groups = {"CCE"})
    public void SL_W_06() throws Exception {
        //New driver object to control browser
        WebDriver driver = getDriver();

        //New base object to handle log-in and set up
        Cce_Base base = new Cce_Base(driver);

        //Set up returns a CCE Page and outputs test details
        CCE_MainPage ccePage = base.setUp("SL_W_07", "Single Line - Warehouse - Similar Article:Yes - H:N -W:N");

        GeneralSingleLine(DataItems.Warehouse, DataItems.Both, DataItems.articleData180[2], DataItems.articleData02[2], DataItems.articleData180[6], DataItems.articleData02[2],DataItems.None);
    }

    @Test //SL_W_08 : SSingle Line - Warehouse - Similar Article:Yes - H:Y -W:Y
            (groups = {"CCE"})
    public void SL_W_07() throws Exception {
        //New driver object to control browser
        WebDriver driver = getDriver();

        //New base object to handle log-in and set up
        Cce_Base base = new Cce_Base(driver);

        //Set up returns a CCE Page and outputs test details
        CCE_MainPage ccePage = base.setUp("SL_W_08", "Single Line - Warehouse - Similar Article:Yes - H:Y -W:Y");

        GeneralSingleLine(DataItems.Warehouse, DataItems.Both, DataItems.articleData02[2], DataItems.articleData180[2], DataItems.articleData180[6], DataItems.articleData02[2], DataItems.Warehouse);
    }

    @Test //SL_H_01 : Single Line - Hub - Similar Article:No - H:Y -W:N
            (groups = {"CCE"})
    public void SL_H_01() throws Exception {
        //New driver object to control browser
        WebDriver driver = getDriver();

        //New base object to handle log-in and set up
        Cce_Base base = new Cce_Base(driver);

        //Set up returns a CCE Page and outputs test details
        CCE_MainPage ccePage = base.setUp("SL_H_01", "Single Line - Hub - Similar Article:No - H:Y - W:N");

        GeneralSingleLine(DataItems.Hub, DataItems.Hub, DataItems.articleData180[2], DataItems.articleData180[2], DataItems.articleData180[6], DataItems.articleData180[2],DataItems.Hub);
    }

    @Test //SL_H_02 : Single Line - Hub - Similar Article:No - H:N -W:Y
            (groups = {"CCE"})
    public void SL_H_02() throws Exception {
        //New driver object to control browser
        WebDriver driver = getDriver();

        //New base object to handle log-in and set up
        Cce_Base base = new Cce_Base(driver);

        //Set up returns a CCE Page and outputs test details
        CCE_MainPage ccePage = base.setUp("SL_H_02", "Single Line - Hub - Similar Article:No - H:N -W:Y");

        GeneralSingleLine(DataItems.Hub, DataItems.Warehouse, DataItems.articleData180[2], DataItems.articleData180[2], DataItems.articleData180[6], DataItems.articleData180[2],DataItems.None);
    }

    @Test //SL_H_03 : Single Line - Hub - Similar Article:No - H:N -W:N
            (groups = {"CCE"})
    public void SL_H_03() throws Exception {
        //New driver object to control browser
        WebDriver driver = getDriver();

        //New base object to handle log-in and set up
        Cce_Base base = new Cce_Base(driver);

        //Set up returns a CCE Page and outputs test details
        CCE_MainPage ccePage = base.setUp("SL_H_03", "Single Line - Hub - Similar Article:No - H:N -W:N");

        GeneralSingleLine(DataItems.Hub, DataItems.Both, DataItems.articleData180[2], DataItems.articleData02[2], DataItems.articleData180[6], DataItems.articleData02[2],DataItems.None);
    }

    @Test //SL_H_04 : Single Line - Hub - Similar Article:No - H:Y -W:Y
            (groups = {"CCE"})
    public void SL_H_04() throws Exception {
        //New driver object to control browser
        WebDriver driver = getDriver();

        //New base object to handle log-in and set up
        Cce_Base base = new Cce_Base(driver);

        //Set up returns a CCE Page and outputs test details
        CCE_MainPage ccePage = base.setUp("SL_H_04", "Single Line - Hub - Similar Article:No - H:Y -W:Y");

        GeneralSingleLine(DataItems.Hub, DataItems.Both, DataItems.articleData180[2], DataItems.articleData180[2], DataItems.articleData180[6], DataItems.articleData180[2],DataItems.Hub);
    }


    @Test //SL_H_06 : Single Line - Hub - Similar Article:Yes - H:N -W:Y
            (groups = {"CCE"})
    public void SL_H_06() throws Exception {
        //New driver object to control browser
        WebDriver driver = getDriver();

        //New base object to handle log-in and set up
        Cce_Base base = new Cce_Base(driver);

        //Set up returns a CCE Page and outputs test details
        CCE_MainPage ccePage = base.setUp("SL_H_06", "Single Line - Hub - Similar Article:Yes - H:N -W:Y");

        GeneralSingleLine(DataItems.Hub, DataItems.Warehouse, DataItems.articleData02[2], DataItems.articleData180[2], DataItems.articleData180[6], DataItems.articleData180[2],DataItems.None);
    }

    @Test //SL_H_07 : Single Line - Hub - Similar Article:Yes - H:N -W:N
            (groups = {"CCE"})
    public void SL_H_07() throws Exception {
        //New driver object to control browser
        WebDriver driver = getDriver();

        //New base object to handle log-in and set up
        Cce_Base base = new Cce_Base(driver);

        //Set up returns a CCE Page and outputs test details
        CCE_MainPage ccePage = base.setUp("SL_H_07", "Single Line - Hub - Similar Article:Yes - H:N -W:N");

        GeneralSingleLine(DataItems.Hub, DataItems.Both, DataItems.articleData02[2], DataItems.articleData180[2], DataItems.articleData180[6], DataItems.articleData180[2],DataItems.None);
    }

    @Test //SL_H_08 : SSingle Line - Hub - Similar Article:Yes - H:Y -W:Y
            (groups = {"CCE"})
    public void SL_H_08() throws Exception {
        //New driver object to control browser
        WebDriver driver = getDriver();

        //New base object to handle log-in and set up
        Cce_Base base = new Cce_Base(driver);

        //Set up returns a CCE Page and outputs test details
        CCE_MainPage ccePage = base.setUp("SL_H_08", "Single Line - Hub - Similar Article:Yes - H:Y -W:Y");

        GeneralSingleLine(DataItems.Hub, DataItems.Both, DataItems.articleData02[2], DataItems.articleData180[2], DataItems.articleData180[6], DataItems.articleData180[2],DataItems.None);
    }




    //------------------------------------------------------------------------------------------------------------------------


    public void setEnv(String salesOrgEnrichOption) throws Exception{
        //New driver object to control browser
        WebDriver driver = getDriver();

        //Setting Master Data
        System.out.println("Setting Master Data for Sales Org and Customer...");
        PreFlows pf = new PreFlows();
        if (salesOrgEnrichOption == "Both")pf.activateEnrichToForSalesOrgAndCustomer(driver, DataItems.salesOrgID, DataItems.lifeEasyCustomer, DataItems.enrichBoth);
        else if (salesOrgEnrichOption == "Hub")pf.activateEnrichToForSalesOrgAndCustomer(driver, DataItems.salesOrgID, DataItems.lifeEasyCustomer, DataItems.enrichHub);
        else if (salesOrgEnrichOption == "Warehouse")pf.activateEnrichToForSalesOrgAndCustomer(driver, DataItems.salesOrgID, DataItems.lifeEasyCustomer, DataItems.enrichWarehouse);
        else System.out.println("WRONG INPUT FOR SELECTED 'ENRICH TO' PARAM...");

        CCE_SOS_ServeArticle_Page sos = new CCE_SOS_ServeArticle_Page(driver);

        driver.get(DataItems.hubStockURL);
        sos.filterArticlesByPlantAndDeleteAll(DataItems.plantID12);
        driver.get(DataItems.warehouseURL);
        sos.filterArticlesByPlantAndDeleteAll(DataItems.plantID12);
    }

    public void addArticlesToStock (String addArticleTo, String article1, String shade1, String article2, String shade2, String article3, String shade3 )throws Exception{

        WebDriver driver = getDriver();
        CCE_SOS_ServeArticle_Page sos = new CCE_SOS_ServeArticle_Page(driver);

        if (addArticleTo == "Hub") {
            //Add article to HUB
            System.out.println("Navigating to HubStock page...");
            driver.get(DataItems.hubStockURL);
            System.out.println("Adding Article to HubStock...");
            sos.setStockArticleAndSave(DataItems.addToHub, DataItems.articleData180[0], DataItems.articleData180[1], article1,
                    DataItems.articleData180[3], DataItems.articleData180[4], DataItems.articleData180[5], shade1, DataItems.articleData180[7]);
            driver.get(DataItems.hubStockURL);
            System.out.println("Adding Article to HubStock...");
            sos.setStockArticleAndSave(DataItems.addToHub, DataItems.articleData120[0], DataItems.articleData120[1], article2,
                    DataItems.articleData120[3], DataItems.articleData120[4], DataItems.articleData120[5], shade2, DataItems.articleData120[7]);
            driver.get(DataItems.hubStockURL);
            System.out.println("Adding Article to HubStock...");
            sos.setStockArticleAndSave(DataItems.addToHub, DataItems.articleData110[0], DataItems.articleData110[1], article3,
                    DataItems.articleData110[3], DataItems.articleData110[4], DataItems.articleData110[5], shade3, DataItems.articleData110[7]);
        }

        else if (addArticleTo == "Warehouse"){
            //Add article to HUB
            System.out.println("Navigating to Warehouse page...");
            driver.get(DataItems.warehouseURL);
            System.out.println("Adding Article to WarehouseStock...");
            sos.setStockArticleAndSave(DataItems.addToWarehouse, DataItems.articleData180[0], DataItems.articleData180[1], article1,
                    DataItems.articleData180[3], DataItems.articleData180[4], DataItems.articleData180[5], shade1, DataItems.articleData180[7]);
            driver.get(DataItems.warehouseURL);
            System.out.println("Adding Article to WarehouseStock...");
            sos.setStockArticleAndSave(DataItems.addToWarehouse, DataItems.articleData120[0], DataItems.articleData120[1], article2,
                    DataItems.articleData120[3], DataItems.articleData120[4], DataItems.articleData120[5], shade2, DataItems.articleData120[7]);
            driver.get(DataItems.warehouseURL);
            System.out.println("Adding Article to WarehouseStock...");
            sos.setStockArticleAndSave(DataItems.addToWarehouse, DataItems.articleData110[0], DataItems.articleData110[1], article3,
                    DataItems.articleData110[3], DataItems.articleData110[4], DataItems.articleData110[5], shade3, DataItems.articleData110[7]);
        }

        else if (addArticleTo == "MixHWH"){

            System.out.println("Navigating to HubStock page...");
            driver.get(DataItems.hubStockURL);
            System.out.println("Adding Article to HubStock...");
            sos.setStockArticleAndSave(DataItems.addToHub, DataItems.articleData180[0], DataItems.articleData180[1], article1,
                    DataItems.articleData180[3], DataItems.articleData180[4], DataItems.articleData180[5], shade1, DataItems.articleData180[7]);
            driver.get(DataItems.warehouseURL);
            System.out.println("Adding Article to WarehouseStock...");
            sos.setStockArticleAndSave(DataItems.addToWarehouse, DataItems.articleData120[0], DataItems.articleData120[1], article2,
                    DataItems.articleData120[3], DataItems.articleData120[4], DataItems.articleData120[5], shade2, DataItems.articleData120[7]);
            driver.get(DataItems.hubStockURL);
            System.out.println("Adding Article to HubStock...");
            sos.setStockArticleAndSave(DataItems.addToHub, DataItems.articleData110[0], DataItems.articleData110[1], article3,
                    DataItems.articleData110[3], DataItems.articleData110[4], DataItems.articleData110[5], shade3, DataItems.articleData110[7]);
        }

        else if (addArticleTo == "MixWHW"){

            System.out.println("Adding Article to WarehouseStock...");
            driver.get(DataItems.warehouseURL);
            sos.setStockArticleAndSave(DataItems.addToWarehouse, DataItems.articleData180[0], DataItems.articleData180[1], article1,
                    DataItems.articleData180[3], DataItems.articleData180[4], DataItems.articleData180[5], shade1, DataItems.articleData180[7]);
            System.out.println("Adding Article to HubStock...");
            driver.get(DataItems.hubStockURL);
            sos.setStockArticleAndSave(DataItems.addToHub, DataItems.articleData120[0], DataItems.articleData120[1], article2,
                    DataItems.articleData120[3], DataItems.articleData120[4], DataItems.articleData120[5], shade2, DataItems.articleData120[7]);
            System.out.println("Adding Article to WarehouseStock...");
            driver.get(DataItems.warehouseURL);
            sos.setStockArticleAndSave(DataItems.addToWarehouse, DataItems.articleData110[0], DataItems.articleData110[1], article3,
                    DataItems.articleData110[3], DataItems.articleData110[4], DataItems.articleData110[5], shade3, DataItems.articleData110[7]);
        }
    }

    public void expectedResults (String expectedArticleLine1, String expectedSOSLine1, String expectedArticleLine2, String expectedSOSLine2, String expectedArticleLine3, String expectedSOSLine3) throws Exception {

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

    public void order3Lines (String article1, String shade1, String article2, String shade2, String article3, String shade3) throws Exception{

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



    @Test //SL_H_08 : SSingle Line - Hub - Similar Article:Yes - H:Y -W:Y
            (groups = {"CCE"})
    public void ML_B_01() throws Exception {
        //New driver object to control browser
        WebDriver driver = getDriver();

        //New base object to handle log-in and set up
        Cce_Base base = new Cce_Base(driver);

        //Set up returns a CCE Page and outputs test details
        CCE_MainPage ccePage = base.setUp("SL_H_08", "Single Line - Hub - Similar Article:Yes - H:Y -W:Y");

        setEnv(DataItems.Both);

        addArticlesToStock("Hub", "8754180", "WHITE", "8754120", "WHITE", "8754110", "WHITE");

        System.out.println("Creating order...");
        completeOrderPromptPageAndAddCustDetails();

        order3Lines("8754180", "WHITE", "8754120", "WHITE", "8754110", "WHITE");

        expectedResults("8754180", "Hub", "8754120", "Hub", "8754110", "Hub");

    }


















    @Test //SL_B_01 : Single Line - Both - Similar Article:No - H:Y -W:N
            (groups = {"CCE"}, enabled = false)
    public void SL_B_01A() throws Exception {
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
        sos.setStockArticleAndSave(DataItems.addToHub, DataItems.articleData180[0],DataItems.articleData180[1],DataItems.articleData180[2],
                DataItems.articleData180[3],DataItems.articleData180[4], DataItems.articleData180[5], DataItems.articleData180[6],DataItems.articleData180[7]);

        //Article is DataItems.articleData180[2]
        //Shade is DataItems.articleData180[5]
        //MuM Type is DataItems.articleData180[6]

        System.out.println("Creating order...");
        completeOrderPromptPageAndAddCustDetails();

        CCE_AddOrderPage aop = new CCE_AddOrderPage(driver);
        System.out.println("Entering product details first line...");
        aop.inputArticleShadeMUMForLine(DataItems.articleData180[2], DataItems.articleData180[5], DataItems.articleData180[6], 0);

        System.out.println("Submitting order...");
        aop.pressSubmit();

    }

}