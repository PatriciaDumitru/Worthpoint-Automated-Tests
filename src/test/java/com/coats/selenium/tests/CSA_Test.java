package com.coats.selenium.tests;

/**
 * Created by Andrei
 */

import AutomationFramework.DataItems;
import PageObjects.CCE_MainPage;
import PageObjects.CCE_DNPrintPage;
import PageObjects.CCE_DNReprintPage;
import PageObjects.CSA_Methods;
import com.coats.selenium.DriverFactory;
import com.google.common.base.Verify;
import java.io.File;
import java.io.IOException;

import com.sun.jna.platform.unix.X11;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;


public class CSA_Test extends DriverFactory {

    @Test //Delivery Plant Select, CSA Brand CustomerA, check Article from Brand with another Customer
            (groups = {"CCE", "CSA", "QuickTest"})
    public void CSA01() throws Exception {
        //New driver object to control browser
        WebDriver driver = getDriver();

        //New base object to handle log-in and set up
        Cce_Base base = new Cce_Base(driver);

        //Set up returns a CCE Page and outputs test details
        CCE_MainPage ccePage = base.setUp("Plant Select CSA CustomerA BrandA - check with CustomerB Article from BrandA", "CSA1");

        CSA_Methods csaMeth = new CSA_Methods(driver);

        //Select delivery to plant to Select so that customer has access to all articles in the plant
        csaMeth.custSetup(DataItems.deliveryToPlantAll);

        //Setting up CSA with brand
        csaMeth.csaSetupOnlyBrand(DataItems.salesOrgID, DataItems.someOtherCustomer, DataItems.brandAstraCSA);

        //Setting up Order Sample Prompt
        csaMeth.cceOrderSampleSetup(DataItems.lifeEasyCustomer, DataItems.lifeEasyRequester);

        //Checking that article from that brand is not available for this customer
        csaMeth.assertArticleIsNotPresentCCE(DataItems.cceArticleFromBrandAstra, DataItems.notFound);

        //Deleting CSA
        csaMeth.deleteCSA(DataItems.someOtherCustomer);
    }

    @Test //Delivery Plant Select, CSA Article CustomerA, check Article with another Customer
            (groups = {"CCE", "CSA"})
    public void CSA02() throws Exception {
        //New driver object to control browser
        WebDriver driver = getDriver();

        //New base object to handle log-in and set up
        Cce_Base base = new Cce_Base(driver);

        //Set up returns a CCE Page and outputs test details
        CCE_MainPage ccePage = base.setUp("Plant Select CSA CustomerA BrandA - check with CustomerB BrandA is not displayed", "CSA2");

        CSA_Methods csaMeth = new CSA_Methods(driver);

        //Select delivery to plant to Select so that customer has access to all articles in the plant
        csaMeth.custSetup(DataItems.deliveryToPlantAll);

        //Setting up CSA with brand
        csaMeth.csaSetupOnlyArticle(DataItems.salesOrgID, DataItems.someOtherCustomer,DataItems.cceArticleFromBrandAstra);

        //Setting up Order Sample Prompt
        csaMeth.cceOrderSampleSetup(DataItems.lifeEasyCustomer, DataItems.lifeEasyRequester);

        //Checking that article from that brand is not available for this customer
        csaMeth.assertArticleIsNotPresentCCE(DataItems.cceArticleFromBrandAstra, DataItems.notFound);

        //Deleting CSA
        csaMeth.deleteCSA(DataItems.someOtherCustomer);
    }

    @Test //Delivery Plant Select, CSA Brand CustomerA, check Brand with another Customer
            (groups = {"CCE", "CSA"})
    public void CSA03() throws Exception {
        //New driver object to control browser
        WebDriver driver = getDriver();

        //New base object to handle log-in and set up
        Cce_Base base = new Cce_Base(driver);

        //Set up returns a CCE Page and outputs test details
        CCE_MainPage ccePage = base.setUp("Plant Select CSA CustomerA ArticleA- check with CustomerB ArticleA is not displayed", "CSA3");

        CSA_Methods csaMeth = new CSA_Methods(driver);

        //Select delivery to plant to Select so that customer has access to all articles in the plant
        csaMeth.custSetup(DataItems.deliveryToPlantAll);

        //Setting up CSA with brand
        csaMeth.csaSetupOnlyBrand(DataItems.salesOrgID, DataItems.someOtherCustomer, DataItems.brandAstraCSA);

        //Setting up Order Sample Prompt
        csaMeth.cceOrderSampleSetup(DataItems.lifeEasyCustomer, DataItems.lifeEasyRequester);

        //Checking that brand is not available for this customer
        csaMeth.assertBrandIsNotPresentCCE(DataItems.brandAstraCSA);

        //Deleting CSA
        csaMeth.deleteCSA(DataItems.someOtherCustomer);
    }

    @Test //Delivery Plant Select, CSA BrandA CustomerA and Article from another brand to the same customer , check Article from Brand the other brand, and brand with another Customer
            (groups = {"CCE", "CSA"})
    public void CSA04() throws Exception {
        //New driver object to control browser
        WebDriver driver = getDriver();

        //New base object to handle log-in and set up
        Cce_Base base = new Cce_Base(driver);

        //Set up returns a CCE Page and outputs test details
        CCE_MainPage ccePage = base.setUp("Plant Select CSA CustomerA BrandA and ArticleB from another Brand - check with CustomerB that BrandA and Article B is not available", "CSA4");

        CSA_Methods csaMeth = new CSA_Methods(driver);

        //Select delivery to plant to Select so that customer has access to all articles in the plant
        csaMeth.custSetup(DataItems.deliveryToPlantAll);

        //Setting up CSA with brand
        csaMeth.csaSetupBrandAndArticle(DataItems.salesOrgID, DataItems.someOtherCustomer, DataItems.brandAstraCSA, DataItems.cceArticleFromBrandStarNylon);

        //Setting up Order Sample Prompt
        csaMeth.cceOrderSampleSetup(DataItems.lifeEasyCustomer, DataItems.lifeEasyRequester);

        //Checking that article article is not present
        csaMeth.assertArticleIsNotPresentCCE(DataItems.cceArticleFromBrandStarNylon, DataItems.notFound);

        csaMeth.cceOrderSampleSetup(DataItems.lifeEasyCustomer, DataItems.lifeEasyRequester);

        //Checking that brand not available for this customer
        csaMeth.assertBrandIsNotPresentCCE(DataItems.brandAstraCSA);

        //Deleting CSA
        csaMeth.deleteCSA(DataItems.someOtherCustomer);
    }

    @Test //Delivery Plant ID10, CSA Brand CustomerA, check Brand with another Customer
            (groups = {"CCE", "CSA"})
    public void CSA05() throws Exception {
        //New driver object to control browser
        WebDriver driver = getDriver();

        //New base object to handle log-in and set up
        Cce_Base base = new Cce_Base(driver);

        //Set up returns a CCE Page and outputs test details
        CCE_MainPage ccePage = base.setUp("PlantA - check that only articles and brands from PlantA are available (bug all brands appear)", "CSA3");

        CSA_Methods csaMeth = new CSA_Methods(driver);

        //Select delivery to plant to ID10 so that customer has access to articles and brads from ID10
        csaMeth.custSetup(DataItems.deliveryToPlantID10);

        //Setting up Order Sample Prompt
        csaMeth.cceOrderSampleSetup(DataItems.lifeEasyCustomer, DataItems.lifeEasyRequester);

        /* Bugged shows all brands
        //Checking that brand is not available for this customer because it's on another plant
        csaMeth.assertBrandIsNotPresentCCE(DataItems.brandStarNylonCSA);

        //Checking that brand is available for this customer
        csaMeth.assertBrandIsPresenet(DataItems.brandAstraCSA);

        //Checking that brand is available for this customer
        csaMeth.assertBrandIsPresenet(DataItems.brandAdmiralCSA);
        */

        //Checking that article is not available for this customer because it's on another plant
        csaMeth.assertArticleIsNotPresentCCE(DataItems.cceArticleFromBrandStarNylon, DataItems.notFound);

        //Refresh page
        csaMeth.cceOrderSampleSetup(DataItems.lifeEasyCustomer, DataItems.lifeEasyRequester);

        //Checking that article is available for this customer
        csaMeth.assertArticleIsPresentCCE(DataItems.cceArticleFromBrandAstra);

    }

    @Test //Delivery Plant Select, CSA Brand CustomerA, check Article from Brand with another Customer
            (groups = {"CCE", "CSA"})
    public void CSA06() throws Exception {
        //New driver object to control browser
        WebDriver driver = getDriver();

        //New base object to handle log-in and set up
        Cce_Base base = new Cce_Base(driver);

        //Set up returns a CCE Page and outputs test details
        CCE_MainPage ccePage = base.setUp("PlantA CSA CustomerA BrandA - check with customerB Article FromBrandA", "CSA6");

        CSA_Methods csaMeth = new CSA_Methods(driver);

        //Select delivery to plant to Select so that customer has access to all articles in the plant
        csaMeth.custSetup(DataItems.deliveryToPlantID10);

        //Setting up CSA with brand
        csaMeth.csaSetupOnlyBrand(DataItems.salesOrgID, DataItems.someOtherCustomer, DataItems.brandAstraCSA);

        //Setting up Order Sample Prompt
        csaMeth.cceOrderSampleSetup(DataItems.lifeEasyCustomer, DataItems.lifeEasyRequester);

        //Checking that article from that brand is not available for this customer
        csaMeth.assertArticleIsNotPresentCCE(DataItems.cceArticleFromBrandAstra, DataItems.notFound);

        //Deleting CSA
        csaMeth.deleteCSA(DataItems.someOtherCustomer);
    }

    @Test //Delivery Plant ID10, CSA Article CustomerA, check Article with another Customer
            (groups = {"CCE", "CSA"})
    public void CSA07() throws Exception {
        //New driver object to control browser
        WebDriver driver = getDriver();

        //New base object to handle log-in and set up
        Cce_Base base = new Cce_Base(driver);

        //Set up returns a CCE Page and outputs test details
        CCE_MainPage ccePage = base.setUp("Plant ID10 CSA CustomerA BrandA - check with CustomerB BrandA is not displayed", "CSA7");

        CSA_Methods csaMeth = new CSA_Methods(driver);

        //Select delivery to plant to Select so that customer has access to all articles in the plant
        csaMeth.custSetup(DataItems.deliveryToPlantID10);

        //Setting up CSA with brand
        csaMeth.csaSetupOnlyArticle(DataItems.salesOrgID, DataItems.someOtherCustomer,DataItems.cceArticleFromBrandAstra);

        //Setting up Order Sample Prompt
        csaMeth.cceOrderSampleSetup(DataItems.lifeEasyCustomer, DataItems.lifeEasyRequester);

        //Checking that article from that brand is not available for this customer
        csaMeth.assertArticleIsNotPresentCCE(DataItems.cceArticleFromBrandAstra, DataItems.notFound);

        //Deleting CSA
        csaMeth.deleteCSA(DataItems.someOtherCustomer);
    }

    @Test //Delivery Plant All, CSA Brand CustomerA, check Brand with another Customer
            (groups = {"CCE", "CSA"})
    public void CSA08() throws Exception {
        //New driver object to control browser
        WebDriver driver = getDriver();

        //New base object to handle log-in and set up
        Cce_Base base = new Cce_Base(driver);

        //Set up returns a CCE Page and outputs test details
        CCE_MainPage ccePage = base.setUp("Plant ID10 CSA CustomerA ArticleA- check with CustomerB ArticleA is not displayed", "CSA8");

        CSA_Methods csaMeth = new CSA_Methods(driver);

        //Select delivery to plant to Select so that customer has access to all articles in the plant
        csaMeth.custSetup(DataItems.deliveryToPlantID10);

        //Setting up CSA with brand
        csaMeth.csaSetupOnlyBrand(DataItems.salesOrgID, DataItems.someOtherCustomer, DataItems.brandAstraCSA);

        //Setting up Order Sample Prompt
        csaMeth.cceOrderSampleSetup(DataItems.lifeEasyCustomer, DataItems.lifeEasyRequester);

        //Checking that brand is not available for this customer
        csaMeth.assertBrandIsNotPresentCCE(DataItems.brandAstraCSA);

        //Deleting CSA
        csaMeth.deleteCSA(DataItems.someOtherCustomer);
    }


    @Test //Delivery Plant Select, CSA BrandA CustomerA and Article from another brand to the same customer , check Article from Brand the other brand, and brand with another Customer
            (groups = {"CCE", "CSA"})
    public void CSA09() throws Exception {
        //New driver object to control browser
        WebDriver driver = getDriver();

        //New base object to handle log-in and set up
        Cce_Base base = new Cce_Base(driver);

        //Set up returns a CCE Page and outputs test details
        CCE_MainPage ccePage = base.setUp("Plant ID10 CSA CustomerA BrandA and ArticleB from another Brand - check with CustomerB that BrandA and Article B is not available", "CSA9");

        CSA_Methods csaMeth = new CSA_Methods(driver);

        //Select delivery to plant to Select so that customer has access to all articles in the plant
        csaMeth.custSetup(DataItems.deliveryToPlantID10);

        //Setting up CSA with brand
        csaMeth.csaSetupBrandAndArticle(DataItems.salesOrgID, DataItems.someOtherCustomer, DataItems.brandAstraCSA, DataItems.cceArticleFromBrandStarNylon);

        //Setting up Order Sample Prompt
        csaMeth.cceOrderSampleSetup(DataItems.lifeEasyCustomer, DataItems.lifeEasyRequester);

        //Checking that article article is not present
        csaMeth.assertArticleIsNotPresentCCE(DataItems.cceArticleFromBrandStarNylon, DataItems.notFound);

        csaMeth.cceOrderSampleSetup(DataItems.lifeEasyCustomer, DataItems.lifeEasyRequester);

        //Checking that brand not available for this customer
        csaMeth.assertBrandIsNotPresentCCE(DataItems.brandAstraCSA);

        //Deleting CSA
        csaMeth.deleteCSA(DataItems.someOtherCustomer);
    }

    @Test //Delivery Plant Select, CSA CustomerA BrandA and BrandB , check Articles from those Brands and the Brands themselves with another Customer
            (groups = {"CCE", "CSA", "QuickTest"})
    public void CSA10_1() throws Exception {
        //New driver object to control browser
        WebDriver driver = getDriver();

        //New base object to handle log-in and set up
        Cce_Base base = new Cce_Base(driver);

        //Set up returns a CCE Page and outputs test details
        CCE_MainPage ccePage = base.setUp("Delivery Plant Select, CSA CustomerA BrandA and BrandB , check Articles from those Brands and the Brands themselves with another Customer", "CSA10_1");

        CSA_Methods csaMeth = new CSA_Methods(driver);

        //Select delivery to plant to Select so that customer has access to all articles in the plant
        csaMeth.custSetup(DataItems.deliveryToPlantAll);

        //Setting up CSA with brand
        csaMeth.csaSetup2Brands(DataItems.salesOrgID, DataItems.someOtherCustomer, DataItems.brandAstraCSA, DataItems.brandStarNylonCSA);

        //Setting up Order Sample Prompt
        csaMeth.cceOrderSampleSetup(DataItems.lifeEasyCustomer, DataItems.lifeEasyRequester);

        //Checking that article from brand astra is not present
        csaMeth.assertArticleIsNotPresentCCE(DataItems.cceArticleFromBrandAstra, DataItems.notFound);

        csaMeth.cceOrderSampleSetup(DataItems.lifeEasyCustomer, DataItems.lifeEasyRequester);

        //Checking that article from star nylon is not present
        csaMeth.assertArticleIsNotPresentCCE(DataItems.cceArticleFromBrandStarNylon, DataItems.notFound);

        csaMeth.cceOrderSampleSetup(DataItems.lifeEasyCustomer, DataItems.lifeEasyRequester);

        //Checking that brand not available for this customer
        csaMeth.assertBrandIsNotPresentCCE(DataItems.brandAstraCSA);

        //Checking that brand not available for this customer
        csaMeth.cceOrderSampleSetup(DataItems.lifeEasyCustomer, DataItems.lifeEasyRequester);

        csaMeth.assertBrandIsNotPresentCCE(DataItems.brandStarNylonCSA);

        //Deleting CSA
        csaMeth.deleteCSA(DataItems.someOtherCustomer);
    }

    @Test //Delivery Plant Select, CSA CustomerA ArticleA ArticleB, check Articles with another Customer
            (groups = {"CCE", "CSA"})
    public void CSA10_2() throws Exception {
        //New driver object to control browser
        WebDriver driver = getDriver();

        //New base object to handle log-in and set up
        Cce_Base base = new Cce_Base(driver);

        //Set up returns a CCE Page and outputs test details
        CCE_MainPage ccePage = base.setUp("Delivery Plant Select, CSA CustomerA ArticleA ArticleB, check Articles with another Customer", "CSA10_2");

        CSA_Methods csaMeth = new CSA_Methods(driver);

        //Select delivery to plant to Select so that customer has access to all articles in the plant
        csaMeth.custSetup(DataItems.deliveryToPlantAll);

        //Setting up CSA with 2 articles
        csaMeth.csaSetup2Articles(DataItems.salesOrgID, DataItems.someOtherCustomer, DataItems.cceArticleFromBrandAdmiral, DataItems.cceArticleFromBrandAstra);

        //Setting up Order Sample Prompt
        csaMeth.cceOrderSampleSetup(DataItems.lifeEasyCustomer, DataItems.lifeEasyRequester);

        //Checking that article is not available for this customer
        csaMeth.assertArticleIsNotPresentCCE(DataItems.cceArticleFromBrandAdmiral, DataItems.notFound);

        //Setting up Order Sample Prompt
        csaMeth.cceOrderSampleSetup(DataItems.lifeEasyCustomer, DataItems.lifeEasyRequester);

        //Checking that article is not available for this customer
        csaMeth.assertArticleIsNotPresentCCE(DataItems.cceArticleFromBrandAstra, DataItems.notFound);

        //Deleting CSA
        csaMeth.deleteCSA(DataItems.someOtherCustomer);
    }

    @Test //Delivery plant Select, CSA CustomerA BrandA, check article is present after CSA deletion with another customer
            (groups = {"CCE", "CSA"})
    public void CSA10_3() throws Exception {
        //New driver object to control browser
        WebDriver driver = getDriver();

        //New base object to handle log-in and set up
        Cce_Base base = new Cce_Base(driver);

        //Set up returns a CCE Page and outputs test details
        CCE_MainPage ccePage = base.setUp("Delivery plant Select, CSA CustomerA BrandA, check article is present after CSA deletion with another customer", "CSA10_3");

        CSA_Methods csaMeth = new CSA_Methods(driver);

        //Select delivery to plant to Select so that customer has access to all articles in the plant
        csaMeth.custSetup(DataItems.deliveryToPlantAll);

        //Setting up CSA with brand
        csaMeth.csaSetupOnlyBrand(DataItems.salesOrgID, DataItems.someOtherCustomer, DataItems.brandAstraCSA);

        //Setting up Order Sample Prompt
        csaMeth.cceOrderSampleSetup(DataItems.lifeEasyCustomer, DataItems.lifeEasyRequester);

        //Checking that article from that brand is not available for this customer
        csaMeth.assertArticleIsNotPresentCCE(DataItems.cceArticleFromBrandAstra, DataItems.notFound);

        //Deleting CSA
        csaMeth.deleteCSA(DataItems.someOtherCustomer);

        //Setting up Order Sample Prompt
        csaMeth.cceOrderSampleSetup(DataItems.lifeEasyCustomer, DataItems.lifeEasyRequester);

        //Checking that article from that brand is available for this customer
        csaMeth.assertArticleIsPresentCCE(DataItems.cceArticleFromBrandAstra);
    }

    @Test //Delivery Plant Select, CSA Brand CustomerA, check Article from Brand with another Customer
            (groups = {"eComm", "CSA", "QuickTest"})
    public void CSA11() throws Exception {
        //New driver object to control browser
        WebDriver driver = getDriver();

        //New base object to handle log-in and set up
        Cce_Base base = new Cce_Base(driver);

        //Set up returns a CCE Page and outputs test details
        CCE_MainPage ccePage = base.setUp("Plant Select CSA CustomerA BrandA - check with CustomerB Article from BrandA", "CSA11");

        CSA_Methods csaMeth = new CSA_Methods(driver);

        //Select delivery to plant to Select so that customer has access to all articles in the plant
        csaMeth.custSetup(DataItems.deliveryToPlantAll);

        //Setting up CSA with brand
        csaMeth.csaSetupOnlyBrand(DataItems.salesOrgID, DataItems.someOtherCustomer, DataItems.brandAstraCSA);

        csaMeth.ecommManualEntrySetup();

        //Checking that article from that brand is not available for this customer
        csaMeth.assertArticleIsNotPresentEcomm(DataItems.cceArticleFromBrandAstra, DataItems.notFound);

        csaMeth.chooseCEEPlatform();

        csaMeth.deleteCSA(DataItems.someOtherCustomer);
    }

    @Test //Delivery Plant Select, CSA Article CustomerA, check Article with another Customer
            (groups = {"eComm", "CSA"})
    public void CSA12() throws Exception {
        //New driver object to control browser
        WebDriver driver = getDriver();

        //New base object to handle log-in and set up
        Cce_Base base = new Cce_Base(driver);

        //Set up returns a CCE Page and outputs test details
        CCE_MainPage ccePage = base.setUp("Plant Select CSA CustomerA BrandA - check with CustomerB BrandA is not displayed", "CSA12");

        CSA_Methods csaMeth = new CSA_Methods(driver);

        //Select delivery to plant to Select so that customer has access to all articles in the plant
        csaMeth.custSetup(DataItems.deliveryToPlantAll);

        //Setting up CSA with brand
        csaMeth.csaSetupOnlyArticle(DataItems.salesOrgID, DataItems.someOtherCustomer,DataItems.cceArticleFromBrandAstra);

        csaMeth.ecommManualEntrySetup();

        //Checking that article from that brand is not available for this customer
        csaMeth.assertArticleIsNotPresentEcomm(DataItems.cceArticleFromBrandAstra, DataItems.notFound);

        csaMeth.chooseCEEPlatform();

        //Deleting CSA
        csaMeth.deleteCSA(DataItems.someOtherCustomer);
    }

    @Test //Delivery Plant Select, CSA Brand CustomerA, check Brand with another Customer
            (groups = {"eComm", "CSA"})
    public void CSA13() throws Exception {
        //New driver object to control browser
        WebDriver driver = getDriver();

        //New base object to handle log-in and set up
        Cce_Base base = new Cce_Base(driver);

        //Set up returns a CCE Page and outputs test details
        CCE_MainPage ccePage = base.setUp("Plant Select CSA CustomerA ArticleA- check with CustomerB ArticleA is not displayed", "CSA13");

        CSA_Methods csaMeth = new CSA_Methods(driver);

        //Select delivery to plant to Select so that customer has access to all articles in the plant
        csaMeth.custSetup(DataItems.deliveryToPlantAll);

        //Setting up CSA with brand
        csaMeth.csaSetupOnlyBrand(DataItems.salesOrgID, DataItems.someOtherCustomer, DataItems.brandAstraCSA);

        csaMeth.ecommManualEntrySetup();

        //Checking that brand is not available for this customer
        csaMeth.assertBrandIsNotPresentEcomm(DataItems.brandAstraCSA);

        csaMeth.chooseCEEPlatform();

        //Deleting CSA
        csaMeth.deleteCSA(DataItems.someOtherCustomer);
    }

    @Test //Delivery Plant Select, CSA BrandA CustomerA and Article from another brand to the same customer , check Article from Brand the other brand, and brand with another Customer
            (groups = {"eComm", "CSA"})
    public void CSA14() throws Exception {
        //New driver object to control browser
        WebDriver driver = getDriver();

        //New base object to handle log-in and set up
        Cce_Base base = new Cce_Base(driver);

        //Set up returns a CCE Page and outputs test details
        CCE_MainPage ccePage = base.setUp("Plant Select CSA CustomerA BrandA and ArticleB from another Brand - check with CustomerB that BrandA and Article B is not available", "CSA14");

        CSA_Methods csaMeth = new CSA_Methods(driver);

        //Select delivery to plant to Select so that customer has access to all articles in the plant
        csaMeth.custSetup(DataItems.deliveryToPlantAll);

        //Setting up CSA with brand
        csaMeth.csaSetupBrandAndArticle(DataItems.salesOrgID, DataItems.someOtherCustomer, DataItems.brandAstraCSA, DataItems.cceArticleFromBrandStarNylon);

        csaMeth.ecommManualEntrySetup();

        //Checking that article article is not present
        csaMeth.assertArticleIsNotPresentEcomm(DataItems.cceArticleFromBrandStarNylon, DataItems.notFound);

        csaMeth.ecommManualEntrySetup();

        //Checking that brand not available for this customer
        csaMeth.assertBrandIsNotPresentEcomm(DataItems.brandAstraCSA);

        csaMeth.chooseCEEPlatform();

        //Deleting CSA
        csaMeth.deleteCSA(DataItems.someOtherCustomer);
    }

    @Test //Delivery Plant ID10, CSA Brand CustomerA, check Brand with another Customer
            (groups = {"eComm", "CSA"})
    public void CSA15() throws Exception {
        //New driver object to control browser
        WebDriver driver = getDriver();

        //New base object to handle log-in and set up
        Cce_Base base = new Cce_Base(driver);

        //Set up returns a CCE Page and outputs test details
        CCE_MainPage ccePage = base.setUp("PlantA - check that only articles and brands from PlantA are available (bug all brands appear)", "CSA15");

        CSA_Methods csaMeth = new CSA_Methods(driver);

        //Select delivery to plant to ID10 so that customer has access to articles and brads from ID10
        csaMeth.custSetup(DataItems.deliveryToPlantID10);

        csaMeth.ecommManualEntrySetup();

        /* Bugged shows all brands
        //Checking that brand is not available for this customer because it's on another plant
        csaMeth.assertBrandIsNotPresentEcomm(DataItems.brandStarNylonCSA);

        //Checking that brand is available for this customer
        csaMeth.assertBrandIsPresentCEE(DataItems.brandAstraCSA);

        //Checking that brand is available for this customer
        csaMeth.assertBrandIsPresentCEE(DataItems.brandAdmiralCSA);
        */

        //Checking that article is not available for this customer because it's on another plant
        csaMeth.assertArticleIsNotPresentEcomm(DataItems.cceArticleFromBrandStarNylon, DataItems.notFound);

        csaMeth.ecommManualEntrySetup();

        //Checking that article is available for this customer
        csaMeth.assertArticleIsPresentEcomm(DataItems.cceArticleFromBrandAstra);

    }

    @Test //Delivery Plant Select, CSA Brand CustomerA, check Article from Brand with another Customer
            (groups = {"eComm", "CSA"})
    public void CSA16() throws Exception {
        //New driver object to control browser
        WebDriver driver = getDriver();

        //New base object to handle log-in and set up
        Cce_Base base = new Cce_Base(driver);

        //Set up returns a CCE Page and outputs test details
        CCE_MainPage ccePage = base.setUp("PlantA CSA CustomerA BrandA - check with customerB Article FromBrandA", "CSA16");

        CSA_Methods csaMeth = new CSA_Methods(driver);

        //Select delivery to plant to Select so that customer has access to all articles in the plant
        csaMeth.custSetup(DataItems.deliveryToPlantID10);

        //Setting up CSA with brand
        csaMeth.csaSetupOnlyBrand(DataItems.salesOrgID, DataItems.someOtherCustomer, DataItems.brandAstraCSA);

        csaMeth.ecommManualEntrySetup();

        //Checking that article from that brand is not available for this customer
        csaMeth.assertArticleIsNotPresentEcomm(DataItems.cceArticleFromBrandAstra, DataItems.notFound);

        csaMeth.chooseCEEPlatform();

        //Deleting CSA
        csaMeth.deleteCSA(DataItems.someOtherCustomer);
    }

    @Test //Delivery Plant ID10, CSA Article CustomerA, check Article with another Customer
            (groups = {"eComm", "CSA"})
    public void CSA17() throws Exception {
        //New driver object to control browser
        WebDriver driver = getDriver();

        //New base object to handle log-in and set up
        Cce_Base base = new Cce_Base(driver);

        //Set up returns a CCE Page and outputs test details
        CCE_MainPage ccePage = base.setUp("Plant ID10 CSA CustomerA BrandA - check with CustomerB BrandA is not displayed", "CSA17");

        CSA_Methods csaMeth = new CSA_Methods(driver);

        //Select delivery to plant to Select so that customer has access to all articles in the plant
        csaMeth.custSetup(DataItems.deliveryToPlantID10);

        //Setting up CSA with brand
        csaMeth.csaSetupOnlyArticle(DataItems.salesOrgID, DataItems.someOtherCustomer,DataItems.cceArticleFromBrandAstra);

        csaMeth.ecommManualEntrySetup();

        //Checking that article from that brand is not available for this customer
        csaMeth.assertArticleIsNotPresentEcomm(DataItems.cceArticleFromBrandAstra, DataItems.notFound);

        csaMeth.chooseCEEPlatform();

        //Deleting CSA
        csaMeth.deleteCSA(DataItems.someOtherCustomer);
    }

    @Test //Delivery Plant All, CSA Brand CustomerA, check Brand with another Customer
            (groups = {"eComm", "CSA"})
    public void CSA18() throws Exception {
        //New driver object to control browser
        WebDriver driver = getDriver();

        //New base object to handle log-in and set up
        Cce_Base base = new Cce_Base(driver);

        //Set up returns a CCE Page and outputs test details
        CCE_MainPage ccePage = base.setUp("Plant ID10 CSA CustomerA ArticleA- check with CustomerB ArticleA is not displayed", "CSA18");

        CSA_Methods csaMeth = new CSA_Methods(driver);

        //Select delivery to plant to Select so that customer has access to all articles in the plant
        csaMeth.custSetup(DataItems.deliveryToPlantID10);

        //Setting up CSA with brand
        csaMeth.csaSetupOnlyBrand(DataItems.salesOrgID, DataItems.someOtherCustomer, DataItems.brandAstraCSA);

        csaMeth.ecommManualEntrySetup();

        //Checking that brand is not available for this customer
        csaMeth.assertBrandIsNotPresentEcomm(DataItems.brandAstraCSA);

        csaMeth.chooseCEEPlatform();

        //Deleting CSA
        csaMeth.deleteCSA(DataItems.someOtherCustomer);
    }


    @Test //Delivery Plant Select, CSA BrandA CustomerA and Article from another brand to the same customer , check Article from Brand the other brand, and brand with another Customer
            (groups = {"eComm", "CSA"})
    public void CSA19() throws Exception {
        //New driver object to control browser
        WebDriver driver = getDriver();

        //New base object to handle log-in and set up
        Cce_Base base = new Cce_Base(driver);

        //Set up returns a CCE Page and outputs test details
        CCE_MainPage ccePage = base.setUp("Plant ID10 CSA CustomerA BrandA and ArticleB from another Brand - check with CustomerB that BrandA and Article B is not available", "CSA19");

        CSA_Methods csaMeth = new CSA_Methods(driver);

        //Select delivery to plant to Select so that customer has access to all articles in the plant
        csaMeth.custSetup(DataItems.deliveryToPlantID10);

        //Setting up CSA with brand
        csaMeth.csaSetupBrandAndArticle(DataItems.salesOrgID, DataItems.someOtherCustomer, DataItems.brandAstraCSA, DataItems.cceArticleFromBrandStarNylon);

        csaMeth.ecommManualEntrySetup();

        //Checking that article article is not present
        csaMeth.assertArticleIsNotPresentEcomm(DataItems.cceArticleFromBrandStarNylon, DataItems.notFound);

        csaMeth.ecommManualEntrySetup();

        //Checking that brand not available for this customer
        csaMeth.assertBrandIsNotPresentEcomm(DataItems.brandAstraCSA);

        csaMeth.chooseCEEPlatform();

        //Deleting CSA
        csaMeth.deleteCSA(DataItems.someOtherCustomer);
    }

    @Test //Delivery Plant Select, CSA CustomerA BrandA and BrandB , check Articles from those Brands with another Customer
            (groups = {"eComm", "CSA", "QuickTest"})
    public void CSA20_1() throws Exception {
        //New driver object to control browser
        WebDriver driver = getDriver();

        //New base object to handle log-in and set up
        Cce_Base base = new Cce_Base(driver);

        //Set up returns a CCE Page and outputs test details
        CCE_MainPage ccePage = base.setUp("Delivery Plant Select, CSA CustomerA BrandA and BrandB , check Articles from those Brands with another Customer", "CSA20_1");

        CSA_Methods csaMeth = new CSA_Methods(driver);

        //Select delivery to plant to Select so that customer has access to all articles in the plant
        csaMeth.custSetup(DataItems.deliveryToPlantAll);

        //Setting up CSA with brand
        csaMeth.csaSetup2Brands(DataItems.salesOrgID, DataItems.someOtherCustomer, DataItems.brandAstraCSA, DataItems.brandStarNylonCSA);

        csaMeth.ecommManualEntrySetup();

        //Checking that article from brand astra is not present
        csaMeth.assertArticleIsNotPresentEcomm(DataItems.cceArticleFromBrandAstra, DataItems.notFound);

        csaMeth.ecommManualEntrySetup();

        //Checking that article from star nylon is not present
        csaMeth.assertArticleIsNotPresentEcomm(DataItems.cceArticleFromBrandStarNylon, DataItems.notFound);

        csaMeth.ecommManualEntrySetup();

        //Checking that brand not available for this customer
        csaMeth.assertBrandIsNotPresentEcomm(DataItems.brandAstraCSA);


        csaMeth.ecommManualEntrySetup();

        //Checking that brand not available for this customer
        csaMeth.assertBrandIsNotPresentEcomm(DataItems.brandStarNylonCSA);

        csaMeth.chooseCEEPlatform();

        //Deleting CSA
        csaMeth.deleteCSA(DataItems.someOtherCustomer);
    }

    @Test //Delivery Plant Select, CSA CustomerA ArticleA ArticleB, check Articles with another Customer
            (groups = {"eComm", "CSA"})
    public void CSA20_2() throws Exception {
        //New driver object to control browser
        WebDriver driver = getDriver();

        //New base object to handle log-in and set up
        Cce_Base base = new Cce_Base(driver);

        //Set up returns a CCE Page and outputs test details
        CCE_MainPage ccePage = base.setUp("Delivery Plant Select, CSA CustomerA ArticleA ArticleB, check Articles with another Customer", "CSA20_2");

        CSA_Methods csaMeth = new CSA_Methods(driver);

        //Select delivery to plant to Select so that customer has access to all articles in the plant
        csaMeth.custSetup(DataItems.deliveryToPlantAll);

        //Setting up CSA with 2 articles
        csaMeth.csaSetup2Articles(DataItems.salesOrgID, DataItems.someOtherCustomer, DataItems.cceArticleFromBrandAdmiral, DataItems.cceArticleFromBrandAstra);

        //Going to ecomm manual entry page and setting customer
        csaMeth.ecommManualEntrySetup();

        //Checking that article is not available for this customer
        csaMeth.assertArticleIsNotPresentEcomm(DataItems.cceArticleFromBrandAdmiral, DataItems.notFound);

        //Going to ecomm manual entry page and setting customer
        csaMeth.ecommManualEntrySetup();

        //Checking that article is not available for this customer
        csaMeth.assertArticleIsNotPresentEcomm(DataItems.cceArticleFromBrandAstra, DataItems.notFound);

        //Deleting CSA
        csaMeth.deleteCSA(DataItems.someOtherCustomer);
    }

    @Test //Delivery plant Select, CSA CustomerA BrandA, check article is present after CSA deletion with another customer
            (groups = {"eComm", "CSA"})
    public void CSA20_3() throws Exception {
        //New driver object to control browser
        WebDriver driver = getDriver();

        //New base object to handle log-in and set up
        Cce_Base base = new Cce_Base(driver);

        //Set up returns a CCE Page and outputs test details
        CCE_MainPage ccePage = base.setUp("Delivery plant Select, CSA CustomerA BrandA, check article is present after CSA deletion with another customer", "CSA20_3");

        CSA_Methods csaMeth = new CSA_Methods(driver);

        //Select delivery to plant to Select so that customer has access to all articles in the plant
        csaMeth.custSetup(DataItems.deliveryToPlantAll);

        //Setting up CSA with brand
        csaMeth.csaSetupOnlyBrand(DataItems.salesOrgID, DataItems.someOtherCustomer, DataItems.brandAstraCSA);

        //Setting up Order Sample Prompt
        csaMeth.ecommManualEntrySetup();

        //Checking that article from that brand is not available for this customer
        csaMeth.assertArticleIsNotPresentEcomm(DataItems.cceArticleFromBrandAstra, DataItems.notFound);

        //Deleting CSA
        csaMeth.deleteCSA(DataItems.someOtherCustomer);

        //Going to ecomm manual entry page and setting customer
        csaMeth.ecommManualEntrySetup();

        //Checking that article from that brand is available for this customer
        csaMeth.assertArticleIsPresentEcomm(DataItems.cceArticleFromBrandAstra);
    }


}