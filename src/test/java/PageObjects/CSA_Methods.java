package PageObjects;

/**
 * Created by Andrei
 */

import AutomationFramework.CommonTask;
import AutomationFramework.DataItems;
import AutomationFramework.Wait;
import org.testng.AssertJUnit;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

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
    By salesOrgFilter = By.xpath("//*[@id=\"FilterIndexForm\"]/table/tbody/tr[1]/td[4]");
    By deleteButton = By.xpath(".//*[@id='content']/div[2]/table/tbody/tr[2]/td[8]/a[2]/span");
    By deleteButton2 = By.xpath("//*[@id=\"content\"]/div[3]/table/tbody/tr[2]/td[8]/a[2]/span");
    By flashMessage = By.cssSelector("div.flash-msg");
    By tableRows = By.xpath("//*[@id=\"content\"]/div[2]/table/tbody/tr");

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

    public void csaClearAllForSalesOrg(String salesOrg){
        /**
         * Created by Stefan
         * Description: This will clear all Customer Specific Articles: Delete all rows from CSA page
         */
        System.out.println("Going to customer page....");
        driver.get(DataItems.mastersCSAUrl);

        //Filtering by Sales Org
        CommonTask.setSearchField(driver,salesOrgFilter,salesOrg);


        int rowCount = driver.findElements(tableRows).size();
        System.out.println(rowCount);
        for (int i=0;i<rowCount-1;i++) {
            //Click delete button of first row
            if (i < 1) {
                driver.findElement(deleteButton).click();
                try {
                    Alert alert = driver.switchTo().alert();
                    System.out.println("Alert message:" + alert.getText());
                    alert.accept();
                    System.out.println("Alert closed!");
                } catch (Exception e) {
                    System.out.println("No error(s) displayed");
                }
            }
            else {
                driver.findElement(deleteButton2).click();
                try {
                    Alert alert = driver.switchTo().alert();
                    System.out.println("Alert message:" + alert.getText());
                    alert.accept();
                    System.out.println("Alert closed!");
                } catch (Exception e) {
                    System.out.println("No error(s) displayed");
                }
            }
        }
        System.out.println("No entries in CSA for Sales Org:"+salesOrg);
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

    public void csaSetup2Brands(String salesOrg, String custName, String brand1, String brand2) throws Exception {

        csaMiniSetup(salesOrg, custName);
        CommonTask.setChoiceField(driver, csaBrand, brand1);
        CommonTask.setChoiceField(driver, csaBrand, brand2);

        System.out.println("Saving CSA....");
        driver.findElement(csaSaveButton).click();
    }

    public void csaSetupOnlyArticle(String salesOrg, String custName, String article) throws Exception {

        csaMiniSetup(salesOrg, custName);
        CommonTask.setChoiceField(driver, csaArticle, article);

        System.out.println("Saving CSA....");
        driver.findElement(csaSaveButton).click();
    }

    public void csaSetup2Articles(String salesOrg, String custName, String article1, String article2) throws Exception {

        csaMiniSetup(salesOrg, custName);
        CommonTask.setChoiceField(driver, csaArticle, article1);
        CommonTask.setChoiceField(driver, csaArticle, article2);

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

        Wait.textPresent(driver, flashMessage, "Customer Specific Article has been deleted");

    }

    public void ecommManualEntrySetup() throws Exception {

        Wait.clickable(driver,chooseTheOtherProfile);

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

