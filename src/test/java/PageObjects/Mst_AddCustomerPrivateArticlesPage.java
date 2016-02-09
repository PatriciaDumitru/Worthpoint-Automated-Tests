package PageObjects;

import AutomationFramework.CommonTask;
import AutomationFramework.DataItems;
import AutomationFramework.Wait;
import static PageObjects.WBA_BasePage.driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.AssertJUnit;

public class Mst_AddCustomerPrivateArticlesPage extends WBA_BasePage {

    //Locators
    By salesOrgField = By.id("CustomerPrivateArticleSalesOrgId");
    By custNameField = By.id("s2id_CustomerPrivateArticleCustomerId");
    By coatsArticleField = By.id("s2id_autogen2");
    By coatsBrandField = By.id("s2id_autogen3");
    By statusEnabledRButton = By.id("CustomerPrivateArticleStatusId1");
    By statusDisabledRbutton = By.id("CustomerPrivateArticleStatusId0");
    By saveButton = By.cssSelector("#CustomerLengthAddForm > div.actions > ul > li:nth-child(1) > input[type=\"submit\"]");
    By cancelButton = By.cssSelector("#CustomerLengthAddForm > div.actions > ul > li:nth-child(2) > a");


    public Mst_AddCustomerPrivateArticlesPage(WebDriver driver) {
        super(driver);
    }

    public Mst_AddCustomerPrivateArticlesPage setSalesOrg(String item) throws InterruptedException {
        CommonTask.setDropDownField(driver, salesOrgField, item);
        return new Mst_AddCustomerPrivateArticlesPage(driver);
    }

    public Mst_AddCustomerPrivateArticlesPage setCustomerName(String item) throws InterruptedException {
        CommonTask.setSearchField(driver, custNameField, item);
        return new Mst_AddCustomerPrivateArticlesPage(driver);
    }

    public Mst_AddCustomerPrivateArticlesPage setArticle(String item) throws InterruptedException {
        CommonTask.setInputField(driver, coatsArticleField, item);
        return new Mst_AddCustomerPrivateArticlesPage(driver);
    }

    public Mst_AddCustomerPrivateArticlesPage setBrand(String item) throws InterruptedException {
        CommonTask.setInputField(driver, coatsBrandField, item);
        return new Mst_AddCustomerPrivateArticlesPage(driver);
    }

    public Mst_AddCustomerPrivateArticlesPage pressEnable() {
        //Wait for element to be clickable
        WebElement btn = Wait.clickable(driver, statusEnabledRButton);
        //New action to click button
        Actions clickRealtime = new Actions(driver);
        clickRealtime.click(btn).build().perform();

        return this;
    }

    public Mst_AddCustomerPrivateArticlesPage pressDisable() {
        //Wait for element to be clickable
        WebElement btn = Wait.clickable(driver, statusDisabledRbutton);
        //New action to click button
        Actions clickRealtime = new Actions(driver);
        clickRealtime.click(btn).build().perform();

        return this;
    }

    public Mst_CustLengthsPage pressSave() {
        WebElement element = Wait.clickable(driver, saveButton);
        element.click();

        return new Mst_CustLengthsPage(driver);
    }

    public Mst_CustLengthsPage pressCancel() {
        WebElement element = Wait.clickable(driver, cancelButton);
        element.click();

        return new Mst_CustLengthsPage(driver);
    }

    public void checkFields() {
        //Wait for all elements to be clickable
        WebElement salesOrg = Wait.clickable(driver, salesOrgField);
        WebElement custName = Wait.clickable(driver, custNameField);
        WebElement coatsArticle = Wait.clickable(driver, coatsArticleField);
        WebElement coatsBrand = Wait.clickable(driver, coatsBrandField);
        WebElement enableStatus = Wait.clickable(driver, statusEnabledRButton);
        WebElement disableStatus = Wait.clickable(driver, statusDisabledRbutton);
        WebElement save = Wait.clickable(driver, saveButton);
        WebElement cancel = Wait.clickable(driver, cancelButton);

        //Assert all elements are displayed
        AssertJUnit.assertTrue("Add Customer Private Arcticle Page: Sales Organisation Field not displayed", salesOrg.isDisplayed());
        AssertJUnit.assertTrue("Add Customer Private Arcticle Page: Customer Name Field not displayed", custName.isDisplayed());
        AssertJUnit.assertTrue("Add Customer Private Arcticle Page: Coats Article Field not displayed", coatsArticle.isDisplayed());
        AssertJUnit.assertTrue("Add Customer Private Arcticle Page: Coats Brand Field not displayed", coatsBrand.isDisplayed());
        AssertJUnit.assertTrue("Add Customer Private Arcticle Page: RButton Enable not displayed" ,enableStatus.isDisplayed());
        AssertJUnit.assertTrue("Add Customer Private Arcticle Page: RButton Disable not displayed", disableStatus.isDisplayed());
        AssertJUnit.assertTrue("Add Customer Private Arcticle Page: Save Button not displayed", save.isDisplayed());
        AssertJUnit.assertTrue("Add Customer Private Arcticle Page: Cancel Button not displayed", cancel.isDisplayed());
    }

}