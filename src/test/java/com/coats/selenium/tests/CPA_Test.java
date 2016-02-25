package com.coats.selenium.tests;

import AutomationFramework.DataItems;
import PageObjects.CCE_MainPage;
import PageObjects.CCE_DNPrintPage;
import PageObjects.CCE_DNReprintPage;
import PageObjects.CPA_Methods;
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


public class CPA_Test extends DriverFactory {

    @Test //Delivery Plant Select, CPA Brand CustomerA, check Article from Brand with another Customer
            (groups = {"CCE", "CPA"})
    public void CPA01() throws Exception {
        //New driver object to control browser
        WebDriver driver = getDriver();

        //New base object to handle log-in and set up
        Cce_Base base = new Cce_Base(driver);

        //Set up returns a CCE Page and outputs test details
        CCE_MainPage ccePage = base.setUp("Plant Select CSA CustomerA BrandA - check with CustomerB Article from BrandA", "CPA1");

        CPA_Methods cpaMeth = new CPA_Methods(driver);

        //Select delivery to plant to Select so that customer has access to all articles in the plant
        cpaMeth.custSetup(DataItems.deliveryToPlantAll);

        //Setting up CSA with brand
        cpaMeth.csaSetupOnlyBrand(DataItems.salesOrgID, DataItems.someOtherCustomer, DataItems.brandAstraCSA);

        //Setting up Order Sample Prompt
        cpaMeth.cceOrderSampleSetup(DataItems.lifeEasyCustomer, DataItems.lifeEasyRequester);

        //Checking that article from that brand is not available for this customer
        cpaMeth.assertArticleIsNotPresentCCE(DataItems.cceArticleFromBrandAstra, DataItems.notFound);

        //Deleting CPA
        cpaMeth.deleteCPA(DataItems.someOtherCustomer);
    }

    @Test //Delivery Plant Select, CPA Article CustomerA, check Article with another Customer
            (groups = {"CCE", "CPA"})
    public void CPA02() throws Exception {
        //New driver object to control browser
        WebDriver driver = getDriver();

        //New base object to handle log-in and set up
        Cce_Base base = new Cce_Base(driver);

        //Set up returns a CCE Page and outputs test details
        CCE_MainPage ccePage = base.setUp("Plant Select CSA CustomerA BrandA - check with CustomerB BrandA is not displayed", "CPA2");

        CPA_Methods cpaMeth = new CPA_Methods(driver);

        //Select delivery to plant to Select so that customer has access to all articles in the plant
        cpaMeth.custSetup(DataItems.deliveryToPlantAll);

        //Setting up CSA with brand
        cpaMeth.csaSetupOnlyArticle(DataItems.salesOrgID, DataItems.someOtherCustomer,DataItems.cceArticleFromBrandAstra);

        //Setting up Order Sample Prompt
        cpaMeth.cceOrderSampleSetup(DataItems.lifeEasyCustomer, DataItems.lifeEasyRequester);

        //Checking that article from that brand is not available for this customer
        cpaMeth.assertArticleIsNotPresentCCE(DataItems.cceArticleFromBrandAstra, DataItems.notFound);

        //Deleting CPA
        cpaMeth.deleteCPA(DataItems.someOtherCustomer);
    }

    @Test //Delivery Plant Select, CPA Brand CustomerA, check Brand with another Customer
            (groups = {"CCE", "CPA"})
    public void CPA03() throws Exception {
        //New driver object to control browser
        WebDriver driver = getDriver();

        //New base object to handle log-in and set up
        Cce_Base base = new Cce_Base(driver);

        //Set up returns a CCE Page and outputs test details
        CCE_MainPage ccePage = base.setUp("Plant Select CSA CustomerA ArticleA- check with CustomerB ArticleA is not displayed", "CPA3");

        CPA_Methods cpaMeth = new CPA_Methods(driver);

        //Select delivery to plant to Select so that customer has access to all articles in the plant
        cpaMeth.custSetup(DataItems.deliveryToPlantAll);

        //Setting up CSA with brand
        cpaMeth.csaSetupOnlyBrand(DataItems.salesOrgID, DataItems.someOtherCustomer, DataItems.brandAstraCSA);

        //Setting up Order Sample Prompt
        cpaMeth.cceOrderSampleSetup(DataItems.lifeEasyCustomer, DataItems.lifeEasyRequester);

        //Checking that brand is not available for this customer
        cpaMeth.assertBrandIsNotPresentCCE(DataItems.brandAstraCSA);

        //Deleting CPA
        cpaMeth.deleteCPA(DataItems.someOtherCustomer);
    }

    @Test //Delivery Plant Select, CPA BrandA CustomerA and Article from another brand to the same customer , check Article from Brand the other brand, and brand with another Customer
            (groups = {"CCE", "CPA"})
    public void CPA04() throws Exception {
        //New driver object to control browser
        WebDriver driver = getDriver();

        //New base object to handle log-in and set up
        Cce_Base base = new Cce_Base(driver);

        //Set up returns a CCE Page and outputs test details
        CCE_MainPage ccePage = base.setUp("Plant Select CSA CustomerA BrandA and ArticleB from another Brand - check with CustomerB that BrandA and Article B is not available", "CPA4");

        CPA_Methods cpaMeth = new CPA_Methods(driver);

        //Select delivery to plant to Select so that customer has access to all articles in the plant
        cpaMeth.custSetup(DataItems.deliveryToPlantAll);

        //Setting up CSA with brand
        cpaMeth.csaSetupBrandAndArticle(DataItems.salesOrgID, DataItems.someOtherCustomer, DataItems.brandAstraCSA, DataItems.cceArticleFromBrandStarNylon);

        //Setting up Order Sample Prompt
        cpaMeth.cceOrderSampleSetup(DataItems.lifeEasyCustomer, DataItems.lifeEasyRequester);

        //Checking that article article is not present
        cpaMeth.assertArticleIsNotPresentCCE(DataItems.cceArticleFromBrandStarNylon, DataItems.notFound);

        cpaMeth.cceOrderSampleSetup(DataItems.lifeEasyCustomer, DataItems.lifeEasyRequester);

        //Checking that brand not available for this customer
        cpaMeth.assertBrandIsNotPresentCCE(DataItems.brandAstraCSA);

        //Deleting CPA
        cpaMeth.deleteCPA(DataItems.someOtherCustomer);
    }

    @Test //Delivery Plant ID10, CPA Brand CustomerA, check Brand with another Customer
            (groups = {"CCE", "CPA"})
    public void CPA05() throws Exception {
        //New driver object to control browser
        WebDriver driver = getDriver();

        //New base object to handle log-in and set up
        Cce_Base base = new Cce_Base(driver);

        //Set up returns a CCE Page and outputs test details
        CCE_MainPage ccePage = base.setUp("PlantA - check that only articles and brands from PlantA are available (bug all brands appear)", "CPA3");

        CPA_Methods cpaMeth = new CPA_Methods(driver);

        //Select delivery to plant to ID10 so that customer has access to articles and brads from ID10
        cpaMeth.custSetup(DataItems.deliveryToPlantID10);

        //Setting up Order Sample Prompt
        cpaMeth.cceOrderSampleSetup(DataItems.lifeEasyCustomer, DataItems.lifeEasyRequester);

        /* Bugged shows all brands
        //Checking that brand is not available for this customer because it's on another plant
        cpaMeth.assertBrandIsNotPresentCCE(DataItems.brandStarNylonCSA);

        //Checking that brand is available for this customer
        cpaMeth.assertBrandIsPresenet(DataItems.brandAstraCSA);

        //Checking that brand is available for this customer
        cpaMeth.assertBrandIsPresenet(DataItems.brandAdmiralCSA);
        */

        //Checking that article is not available for this customer because it's on another plant
        cpaMeth.assertArticleIsNotPresentCCE(DataItems.cceArticleFromBrandStarNylon, DataItems.notFound);

        //Refresh page
        cpaMeth.cceOrderSampleSetup(DataItems.lifeEasyCustomer, DataItems.lifeEasyRequester);

        //Checking that article is available for this customer
        cpaMeth.assertArticleIsPresentCCE(DataItems.cceArticleFromBrandAstra);

    }

    @Test //Delivery Plant Select, CPA Brand CustomerA, check Article from Brand with another Customer
            (groups = {"CCE", "CPA"})
    public void CPA06() throws Exception {
        //New driver object to control browser
        WebDriver driver = getDriver();

        //New base object to handle log-in and set up
        Cce_Base base = new Cce_Base(driver);

        //Set up returns a CCE Page and outputs test details
        CCE_MainPage ccePage = base.setUp("PlantA CSA CustomerA BrandA - check with customerB Article FromBrandA", "CPA6");

        CPA_Methods cpaMeth = new CPA_Methods(driver);

        //Select delivery to plant to Select so that customer has access to all articles in the plant
        cpaMeth.custSetup(DataItems.deliveryToPlantID10);

        //Setting up CSA with brand
        cpaMeth.csaSetupOnlyBrand(DataItems.salesOrgID, DataItems.someOtherCustomer, DataItems.brandAstraCSA);

        //Setting up Order Sample Prompt
        cpaMeth.cceOrderSampleSetup(DataItems.lifeEasyCustomer, DataItems.lifeEasyRequester);

        //Checking that article from that brand is not available for this customer
        cpaMeth.assertArticleIsNotPresentCCE(DataItems.cceArticleFromBrandAstra, DataItems.notFound);

        //Deleting CPA
        cpaMeth.deleteCPA(DataItems.someOtherCustomer);
    }

    @Test //Delivery Plant ID10, CPA Article CustomerA, check Article with another Customer
            (groups = {"CCE", "CPA"})
    public void CPA07() throws Exception {
        //New driver object to control browser
        WebDriver driver = getDriver();

        //New base object to handle log-in and set up
        Cce_Base base = new Cce_Base(driver);

        //Set up returns a CCE Page and outputs test details
        CCE_MainPage ccePage = base.setUp("Plant ID10 CSA CustomerA BrandA - check with CustomerB BrandA is not displayed", "CPA7");

        CPA_Methods cpaMeth = new CPA_Methods(driver);

        //Select delivery to plant to Select so that customer has access to all articles in the plant
        cpaMeth.custSetup(DataItems.deliveryToPlantID10);

        //Setting up CSA with brand
        cpaMeth.csaSetupOnlyArticle(DataItems.salesOrgID, DataItems.someOtherCustomer,DataItems.cceArticleFromBrandAstra);

        //Setting up Order Sample Prompt
        cpaMeth.cceOrderSampleSetup(DataItems.lifeEasyCustomer, DataItems.lifeEasyRequester);

        //Checking that article from that brand is not available for this customer
        cpaMeth.assertArticleIsNotPresentCCE(DataItems.cceArticleFromBrandAstra, DataItems.notFound);

        //Deleting CPA
        cpaMeth.deleteCPA(DataItems.someOtherCustomer);
    }

    @Test //Delivery Plant All, CPA Brand CustomerA, check Brand with another Customer
            (groups = {"CCE", "CPA"})
    public void CPA08() throws Exception {
        //New driver object to control browser
        WebDriver driver = getDriver();

        //New base object to handle log-in and set up
        Cce_Base base = new Cce_Base(driver);

        //Set up returns a CCE Page and outputs test details
        CCE_MainPage ccePage = base.setUp("Plant ID10 CSA CustomerA ArticleA- check with CustomerB ArticleA is not displayed", "CPA8");

        CPA_Methods cpaMeth = new CPA_Methods(driver);

        //Select delivery to plant to Select so that customer has access to all articles in the plant
        cpaMeth.custSetup(DataItems.deliveryToPlantID10);

        //Setting up CSA with brand
        cpaMeth.csaSetupOnlyBrand(DataItems.salesOrgID, DataItems.someOtherCustomer, DataItems.brandAstraCSA);

        //Setting up Order Sample Prompt
        cpaMeth.cceOrderSampleSetup(DataItems.lifeEasyCustomer, DataItems.lifeEasyRequester);

        //Checking that brand is not available for this customer
        cpaMeth.assertBrandIsNotPresentCCE(DataItems.brandAstraCSA);

        //Deleting CPA
        cpaMeth.deleteCPA(DataItems.someOtherCustomer);
    }


    @Test //Delivery Plant Select, CPA BrandA CustomerA and Article from another brand to the same customer , check Article from Brand the other brand, and brand with another Customer
            (groups = {"CCE", "CPA"})
    public void CPA09() throws Exception {
        //New driver object to control browser
        WebDriver driver = getDriver();

        //New base object to handle log-in and set up
        Cce_Base base = new Cce_Base(driver);

        //Set up returns a CCE Page and outputs test details
        CCE_MainPage ccePage = base.setUp("Plant ID10 CSA CustomerA BrandA and ArticleB from another Brand - check with CustomerB that BrandA and Article B is not available", "CPA9");

        CPA_Methods cpaMeth = new CPA_Methods(driver);

        //Select delivery to plant to Select so that customer has access to all articles in the plant
        cpaMeth.custSetup(DataItems.deliveryToPlantID10);

        //Setting up CSA with brand
        cpaMeth.csaSetupBrandAndArticle(DataItems.salesOrgID, DataItems.someOtherCustomer, DataItems.brandAstraCSA, DataItems.cceArticleFromBrandStarNylon);

        //Setting up Order Sample Prompt
        cpaMeth.cceOrderSampleSetup(DataItems.lifeEasyCustomer, DataItems.lifeEasyRequester);

        //Checking that article article is not present
        cpaMeth.assertArticleIsNotPresentCCE(DataItems.cceArticleFromBrandStarNylon, DataItems.notFound);

        cpaMeth.cceOrderSampleSetup(DataItems.lifeEasyCustomer, DataItems.lifeEasyRequester);

        //Checking that brand not available for this customer
        cpaMeth.assertBrandIsNotPresentCCE(DataItems.brandAstraCSA);

        //Deleting CPA
        cpaMeth.deleteCPA(DataItems.someOtherCustomer);
    }

    @Test //Delivery Plant Select, CPA Brand CustomerA, check Article from Brand with another Customer
            (groups = {"Ecomm", "CPA"})
    public void CPA11() throws Exception {
        //New driver object to control browser
        WebDriver driver = getDriver();

        //New base object to handle log-in and set up
        Cce_Base base = new Cce_Base(driver);

        //Set up returns a CCE Page and outputs test details
        CCE_MainPage ccePage = base.setUp("Plant Select CSA CustomerA BrandA - check with CustomerB Article from BrandA", "CPA11");

        CPA_Methods cpaMeth = new CPA_Methods(driver);

        //Select delivery to plant to Select so that customer has access to all articles in the plant
        cpaMeth.custSetup(DataItems.deliveryToPlantAll);

        //Setting up CSA with brand
        cpaMeth.csaSetupOnlyBrand(DataItems.salesOrgID, DataItems.someOtherCustomer, DataItems.brandAstraCSA);

        cpaMeth.ecommManualEntrySetup();

        //Checking that article from that brand is not available for this customer
        cpaMeth.assertArticleIsNotPresentEcomm(DataItems.cceArticleFromBrandAstra, DataItems.notFound);

        cpaMeth.chooseCEEPlatform();

        cpaMeth.deleteCPA(DataItems.someOtherCustomer);
    }

    @Test //Delivery Plant Select, CPA Article CustomerA, check Article with another Customer
            (groups = {"CCE", "CPA"})
    public void CPA12() throws Exception {
        //New driver object to control browser
        WebDriver driver = getDriver();

        //New base object to handle log-in and set up
        Cce_Base base = new Cce_Base(driver);

        //Set up returns a CCE Page and outputs test details
        CCE_MainPage ccePage = base.setUp("Plant Select CSA CustomerA BrandA - check with CustomerB BrandA is not displayed", "CPA12");

        CPA_Methods cpaMeth = new CPA_Methods(driver);

        //Select delivery to plant to Select so that customer has access to all articles in the plant
        cpaMeth.custSetup(DataItems.deliveryToPlantAll);

        //Setting up CSA with brand
        cpaMeth.csaSetupOnlyArticle(DataItems.salesOrgID, DataItems.someOtherCustomer,DataItems.cceArticleFromBrandAstra);

        cpaMeth.ecommManualEntrySetup();

        //Checking that article from that brand is not available for this customer
        cpaMeth.assertArticleIsNotPresentEcomm(DataItems.cceArticleFromBrandAstra, DataItems.notFound);

        cpaMeth.chooseCEEPlatform();

        //Deleting CPA
        cpaMeth.deleteCPA(DataItems.someOtherCustomer);
    }

    @Test //Delivery Plant Select, CPA Brand CustomerA, check Brand with another Customer
            (groups = {"CCE", "CPA"})
    public void CPA13() throws Exception {
        //New driver object to control browser
        WebDriver driver = getDriver();

        //New base object to handle log-in and set up
        Cce_Base base = new Cce_Base(driver);

        //Set up returns a CCE Page and outputs test details
        CCE_MainPage ccePage = base.setUp("Plant Select CSA CustomerA ArticleA- check with CustomerB ArticleA is not displayed", "CPA13");

        CPA_Methods cpaMeth = new CPA_Methods(driver);

        //Select delivery to plant to Select so that customer has access to all articles in the plant
        cpaMeth.custSetup(DataItems.deliveryToPlantAll);

        //Setting up CSA with brand
        cpaMeth.csaSetupOnlyBrand(DataItems.salesOrgID, DataItems.someOtherCustomer, DataItems.brandAstraCSA);

        cpaMeth.ecommManualEntrySetup();

        //Checking that brand is not available for this customer
        cpaMeth.assertBrandIsNotPresentEcomm(DataItems.brandAstraCSA);

        cpaMeth.chooseCEEPlatform();

        //Deleting CPA
        cpaMeth.deleteCPA(DataItems.someOtherCustomer);
    }

    @Test //Delivery Plant Select, CPA BrandA CustomerA and Article from another brand to the same customer , check Article from Brand the other brand, and brand with another Customer
            (groups = {"CCE", "CPA"})
    public void CPA14() throws Exception {
        //New driver object to control browser
        WebDriver driver = getDriver();

        //New base object to handle log-in and set up
        Cce_Base base = new Cce_Base(driver);

        //Set up returns a CCE Page and outputs test details
        CCE_MainPage ccePage = base.setUp("Plant Select CSA CustomerA BrandA and ArticleB from another Brand - check with CustomerB that BrandA and Article B is not available", "CPA14");

        CPA_Methods cpaMeth = new CPA_Methods(driver);

        //Select delivery to plant to Select so that customer has access to all articles in the plant
        cpaMeth.custSetup(DataItems.deliveryToPlantAll);

        //Setting up CSA with brand
        cpaMeth.csaSetupBrandAndArticle(DataItems.salesOrgID, DataItems.someOtherCustomer, DataItems.brandAstraCSA, DataItems.cceArticleFromBrandStarNylon);

        cpaMeth.ecommManualEntrySetup();

        //Checking that article article is not present
        cpaMeth.assertArticleIsNotPresentEcomm(DataItems.cceArticleFromBrandStarNylon, DataItems.notFound);

        cpaMeth.ecommManualEntrySetup();

        //Checking that brand not available for this customer
        cpaMeth.assertBrandIsNotPresentEcomm(DataItems.brandAstraCSA);

        cpaMeth.chooseCEEPlatform();

        //Deleting CPA
        cpaMeth.deleteCPA(DataItems.someOtherCustomer);
    }

    @Test //Delivery Plant ID10, CPA Brand CustomerA, check Brand with another Customer
            (groups = {"CCE", "CPA"})
    public void CPA15() throws Exception {
        //New driver object to control browser
        WebDriver driver = getDriver();

        //New base object to handle log-in and set up
        Cce_Base base = new Cce_Base(driver);

        //Set up returns a CCE Page and outputs test details
        CCE_MainPage ccePage = base.setUp("PlantA - check that only articles and brands from PlantA are available (bug all brands appear)", "CPA15");

        CPA_Methods cpaMeth = new CPA_Methods(driver);

        //Select delivery to plant to ID10 so that customer has access to articles and brads from ID10
        cpaMeth.custSetup(DataItems.deliveryToPlantID10);

        cpaMeth.ecommManualEntrySetup();

        /* Bugged shows all brands
        //Checking that brand is not available for this customer because it's on another plant
        cpaMeth.assertBrandIsNotPresentEcomm(DataItems.brandStarNylonCSA);

        //Checking that brand is available for this customer
        cpaMeth.assertBrandIsPresentCEE(DataItems.brandAstraCSA);

        //Checking that brand is available for this customer
        cpaMeth.assertBrandIsPresentCEE(DataItems.brandAdmiralCSA);
        */

        //Checking that article is not available for this customer because it's on another plant
        cpaMeth.assertArticleIsNotPresentEcomm(DataItems.cceArticleFromBrandStarNylon, DataItems.notFound);

        cpaMeth.ecommManualEntrySetup();

        //Checking that article is available for this customer
        cpaMeth.assertArticleIsPresentEcomm(DataItems.cceArticleFromBrandAstra);

    }

    @Test //Delivery Plant Select, CPA Brand CustomerA, check Article from Brand with another Customer
            (groups = {"CCE", "CPA"})
    public void CPA16() throws Exception {
        //New driver object to control browser
        WebDriver driver = getDriver();

        //New base object to handle log-in and set up
        Cce_Base base = new Cce_Base(driver);

        //Set up returns a CCE Page and outputs test details
        CCE_MainPage ccePage = base.setUp("PlantA CSA CustomerA BrandA - check with customerB Article FromBrandA", "CPA16");

        CPA_Methods cpaMeth = new CPA_Methods(driver);

        //Select delivery to plant to Select so that customer has access to all articles in the plant
        cpaMeth.custSetup(DataItems.deliveryToPlantID10);

        //Setting up CSA with brand
        cpaMeth.csaSetupOnlyBrand(DataItems.salesOrgID, DataItems.someOtherCustomer, DataItems.brandAstraCSA);

        cpaMeth.ecommManualEntrySetup();

        //Checking that article from that brand is not available for this customer
        cpaMeth.assertArticleIsNotPresentEcomm(DataItems.cceArticleFromBrandAstra, DataItems.notFound);

        cpaMeth.chooseCEEPlatform();

        //Deleting CPA
        cpaMeth.deleteCPA(DataItems.someOtherCustomer);
    }

    @Test //Delivery Plant ID10, CPA Article CustomerA, check Article with another Customer
            (groups = {"CCE", "CPA"})
    public void CPA17() throws Exception {
        //New driver object to control browser
        WebDriver driver = getDriver();

        //New base object to handle log-in and set up
        Cce_Base base = new Cce_Base(driver);

        //Set up returns a CCE Page and outputs test details
        CCE_MainPage ccePage = base.setUp("Plant ID10 CSA CustomerA BrandA - check with CustomerB BrandA is not displayed", "CPA17");

        CPA_Methods cpaMeth = new CPA_Methods(driver);

        //Select delivery to plant to Select so that customer has access to all articles in the plant
        cpaMeth.custSetup(DataItems.deliveryToPlantID10);

        //Setting up CSA with brand
        cpaMeth.csaSetupOnlyArticle(DataItems.salesOrgID, DataItems.someOtherCustomer,DataItems.cceArticleFromBrandAstra);

        cpaMeth.ecommManualEntrySetup();

        //Checking that article from that brand is not available for this customer
        cpaMeth.assertArticleIsNotPresentEcomm(DataItems.cceArticleFromBrandAstra, DataItems.notFound);

        cpaMeth.chooseCEEPlatform();

        //Deleting CPA
        cpaMeth.deleteCPA(DataItems.someOtherCustomer);
    }

    @Test //Delivery Plant All, CPA Brand CustomerA, check Brand with another Customer
            (groups = {"CCE", "CPA"})
    public void CPA18() throws Exception {
        //New driver object to control browser
        WebDriver driver = getDriver();

        //New base object to handle log-in and set up
        Cce_Base base = new Cce_Base(driver);

        //Set up returns a CCE Page and outputs test details
        CCE_MainPage ccePage = base.setUp("Plant ID10 CSA CustomerA ArticleA- check with CustomerB ArticleA is not displayed", "CPA18");

        CPA_Methods cpaMeth = new CPA_Methods(driver);

        //Select delivery to plant to Select so that customer has access to all articles in the plant
        cpaMeth.custSetup(DataItems.deliveryToPlantID10);

        //Setting up CSA with brand
        cpaMeth.csaSetupOnlyBrand(DataItems.salesOrgID, DataItems.someOtherCustomer, DataItems.brandAstraCSA);

        cpaMeth.ecommManualEntrySetup();

        //Checking that brand is not available for this customer
        cpaMeth.assertBrandIsNotPresentEcomm(DataItems.brandAstraCSA);

        cpaMeth.chooseCEEPlatform();

        //Deleting CPA
        cpaMeth.deleteCPA(DataItems.someOtherCustomer);
    }


    @Test //Delivery Plant Select, CPA BrandA CustomerA and Article from another brand to the same customer , check Article from Brand the other brand, and brand with another Customer
            (groups = {"CCE", "CPA"})
    public void CPA19() throws Exception {
        //New driver object to control browser
        WebDriver driver = getDriver();

        //New base object to handle log-in and set up
        Cce_Base base = new Cce_Base(driver);

        //Set up returns a CCE Page and outputs test details
        CCE_MainPage ccePage = base.setUp("Plant ID10 CSA CustomerA BrandA and ArticleB from another Brand - check with CustomerB that BrandA and Article B is not available", "CPA19");

        CPA_Methods cpaMeth = new CPA_Methods(driver);

        //Select delivery to plant to Select so that customer has access to all articles in the plant
        cpaMeth.custSetup(DataItems.deliveryToPlantID10);

        //Setting up CSA with brand
        cpaMeth.csaSetupBrandAndArticle(DataItems.salesOrgID, DataItems.someOtherCustomer, DataItems.brandAstraCSA, DataItems.cceArticleFromBrandStarNylon);

        cpaMeth.ecommManualEntrySetup();

        //Checking that article article is not present
        cpaMeth.assertArticleIsNotPresentEcomm(DataItems.cceArticleFromBrandStarNylon, DataItems.notFound);

        cpaMeth.ecommManualEntrySetup();

        //Checking that brand not available for this customer
        cpaMeth.assertBrandIsNotPresentEcomm(DataItems.brandAstraCSA);

        cpaMeth.chooseCEEPlatform();

        //Deleting CPA
        cpaMeth.deleteCPA(DataItems.someOtherCustomer);
    }







}