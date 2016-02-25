package PageObjects;

import AutomationFramework.CommonTask;
import AutomationFramework.DataItems;
import AutomationFramework.Wait;
import static PageObjects.WBA_BasePage.driver;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;
import org.testng.AssertJUnit;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CSA_Methods extends WBA_BasePage {

    public CSA_Methods(WebDriver passedDriver) {
        super(passedDriver);
    }


    //Locators
    By chooseTheOtherProfile = By.id("access_type"); //CCE or Ecomm depending on which page you are

    //Customer Masters Page
    By customerNameField = By.id("filterCustomerCustomerName");
    By filterCustomerButton = By.xpath(".//*[@id='FilterIndexForm']/div[3]/ul/li[1]/input");
    By editCustomerButton = By.cssSelector("span.btn-edit");

    //Customer Edit Page
    By customerNameEditPageField = By.id("CustomerCustomerName");
    By customerDeliveryPlantIdField = By.id("CustomerDeliveryPlantId");
    By saveButtonCustomerPage = By.cssSelector("input[type=\"submit\"]");

    //CSA Page
    By addCSA = By.linkText("Assign Specific Article");
    By filterCustomerNameFieldCSA = By.id("filterCustomerCustomerName");
    By searchButton = By.xpath(".//*[@id='FilterIndexForm']/div[3]/ul/li[1]/input");
    By deleteButton = By.xpath(".//*[@id='content']/div[2]/table/tbody/tr[2]/td[8]/a[2]/span");

    //CSA Edit Page
    By csaSalesOrgField = By.id("CustomerPrivateArticleSalesOrgId");
    By enableCSARBtutton = By.cssSelector("option[value=\"11\"]");
    By customerNameFieldCSA = By.id("s2id_CustomerPrivateArticleCustomerId");
    By csaBrand = By.id("s2id_autogen3");
    By csaArticle = By.id("s2id_autogen2");
    By csaSaveButton = By.cssSelector("input[type=\"submit\"]");

    //CCE Order Sample Prompt
    By cceOScustName = By.id("s2id_customer_id");
    By cceOSreqName = By.id("SampleOrderRequesterId");
    By saveCCEOSPrompt = By.xpath(".//*[@id='SampleOrderPromptForm']/div[3]/ul/li[1]/input");

    //CCE Order Sample Page
    By checkArticleFieldCCE = By.id("s2id_SampleOrderLine0ArticleId");
    By checkBrandFrieldCCE = By.id("SampleOrderLine0BrandId");

    //Ecomm Manual Entry Page
    By customerNameMEField = By.id("s2id_customer_id");
    By checkArticleFieldEcommME = By.id("s2id_BulkOrderLine0ArticleId");
    By checkBrandFieldEcommME = By.id("Brand0");
    By waitForFieldToBePresent = By.id("BulkOrderStyle");


    public CSA_Methods custSetup(String plant){

        System.out.println("Going to customer page....");
        driver.get(DataItems.mastersCustomerURL);

        System.out.println("Selecting customer and editing ....");
        driver.findElement(customerNameField).clear();
        driver.findElement(customerNameField).sendKeys("life easy");
        driver.findElement(filterCustomerButton).click();
        driver.findElement(editCustomerButton).click();

        System.out.println("Selecting Delivery to plant option....");
        new Select(driver.findElement(customerDeliveryPlantIdField)).selectByVisibleText(plant);

        System.out.println("Saving changes....");
        driver.findElement(saveButtonCustomerPage).click();


        return this;
    }

    public void csaMiniSetup(String salesOrg, String custName) throws  Exception {
        System.out.println("Going to CSA page....");
        driver.get(DataItems.mastersCSAUrl);

        System.out.println("Adding new CSA....");
        driver.findElement(addCSA).click();

        System.out.println("Setting CSA details....");
        new Select(driver.findElement(csaSalesOrgField)).selectByVisibleText(salesOrg);
        CommonTask.setSearchField(driver, customerNameFieldCSA, custName);
    }

    public void csaSetupBrandAndArticle(String salesOrg, String custName, String brand, String article) throws Exception {

        csaMiniSetup(salesOrg, custName);
        CommonTask.setChoiceField(driver, csaBrand, brand);
        CommonTask.setChoiceField(driver, csaArticle, article);

        System.out.println("Saving CSA....");
        driver.findElement(csaSaveButton).click();
    }

    public void csaSetupOnlyBrand(String salesOrg, String custName, String brand) throws Exception {

        csaMiniSetup(salesOrg, custName);
        CommonTask.setChoiceField(driver, csaBrand, brand);

        System.out.println("Saving CSA....");
        driver.findElement(csaSaveButton).click();
    }

    public void csaSetupOnlyArticle(String salesOrg, String custName, String article) throws Exception {

        csaMiniSetup(salesOrg, custName);
        CommonTask.setChoiceField(driver, csaArticle, article);

        System.out.println("Saving CSA....");
        driver.findElement(csaSaveButton).click();
    }

    public void cceOrderSampleSetup(String customer, String requester) throws Exception {

        System.out.println("Going to Order Sample Page....");
        driver.get(DataItems.cceOrderSamplePrompt);

        System.out.println("Setting Order Sample imputs....");
        CommonTask.setSearchField(driver, cceOScustName, customer);
        CommonTask.setDropDownField(driver, cceOSreqName, requester);

        System.out.println("Saving Order Sample Prompt....");
        driver.findElement(saveCCEOSPrompt).click();

    }

    public void assertArticleIsNotPresentCCE(String article, String notFoundMSG) throws Exception {
        System.out.println("Checking that article is not available....");
        CommonTask.setSearchFieldAndCheckElementIsNotPresent(driver, checkArticleFieldCCE, article, notFoundMSG );
    }

    public void assertArticleIsPresentCCE(String article) throws Exception {
        System.out.println("Checking that article is available....");
        CommonTask.setSearchFieldAndCheckElementIsNotPresent(driver, checkArticleFieldCCE, article, article );
    }

    public void assertBrandIsNotPresentCCE(String brand) throws Exception {

        System.out.println("Checking that brand is not available....");
        AssertJUnit.assertTrue(CommonTask.setDropDownFieldAndCheckThatElementIsNotPresent(driver, checkBrandFrieldCCE, brand));
    }

    public void assertBrandIsPresentCCE(String brand) throws Exception {

        System.out.println("Checking that brand is available....");
        CommonTask.setDropDownField(driver, checkBrandFrieldCCE, brand);
    }

    public void assertArticleIsNotPresentEcomm(String article, String notFoundMSG) throws Exception {
        System.out.println("Checking that article is not available....");
        CommonTask.setSearchFieldAndCheckElementIsNotPresent(driver, checkArticleFieldEcommME, article, notFoundMSG );
    }

    public void assertArticleIsPresentEcomm(String article) throws Exception {
        System.out.println("Checking that article is available....");
        CommonTask.setSearchFieldAndCheckElementIsNotPresent(driver, checkArticleFieldEcommME, article, article );
    }

    public void assertBrandIsNotPresentEcomm(String brand) throws Exception {

        System.out.println("Checking that brand is not available....");
        AssertJUnit.assertTrue(CommonTask.setDropDownFieldAndCheckThatElementIsNotPresent(driver, checkBrandFieldEcommME, brand));
    }

    public void assertBrandIsPresentEcomm(String brand) throws Exception {

        System.out.println("Checking that brand is available....");
        CommonTask.setDropDownField(driver, checkBrandFieldEcommME, brand);
    }

    public void deleteCSA(String custName) throws Exception {

        System.out.println("Going to CSA page....");
        driver.get(DataItems.mastersCSAUrl);

        System.out.println("Deleting CSA....");
        driver.findElement(searchButton).sendKeys(custName);
        driver.findElement(searchButton).click();
        driver.findElement(deleteButton).click();

        Alert alert = Wait.alert(driver);
        alert.accept();

    }

    public void ecommManualEntrySetup() throws Exception {

        Actions action = new Actions(driver);
        action.click(driver.findElement(chooseTheOtherProfile)).build().perform();

        System.out.println("Navigating to Manual Order Entry Page...");

        driver.get(DataItems.manualEntryEcommURL);

        System.out.println("Checking Manual Order Entry Page is reached...");

        waitForManualEntryPageToOpen();

        CommonTask.setSearchField(driver, customerNameMEField, DataItems.lifeEasyCustomer);

        Wait.presence(driver,waitForFieldToBePresent);


    }

    public void waitForManualEntryPageToOpen() {
        WebElement wait = Wait.clickable(driver,customerNameMEField);
    }

    public void chooseCEEPlatform(){
        Actions action = new Actions(driver);
        action.click(driver.findElement(chooseTheOtherProfile)).build().perform();
    }


}

