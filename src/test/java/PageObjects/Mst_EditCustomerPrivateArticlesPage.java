package PageObjects;

import AutomationFramework.CommonTask;
import AutomationFramework.DataItems;
import static PageObjects.WBA_BasePage.driver;

import AutomationFramework.Wait;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.AssertJUnit;

public class Mst_EditCustomerPrivateArticlesPage extends WBA_BasePage{

    //Locators
    By salesOrgField = By.id("CustomerPrivateArticleSalesOrgId");
    By custNameField = By.id("s2id_CustomerPrivateArticleCustomerId");
    By coatsArticleField = By.id("s2id_autogen2");
    By coatsBrandField = By.id("s2id_autogen3");
    By statusEnabledRButton = By.id("CustomerPrivateArticleStatusId1");
    By statusDisabledRbutton = By.id("CustomerPrivateArticleStatusId0");
    By saveButton = By.cssSelector("#CustomerPrivateArticleEditForm > div.actions > ul > li:nth-child(1) > input[type=\"submit\"]");
    By cancelButton = By.xpath(".//*[@id='CustomerPrivateArticleAddForm']/div[3]/ul/li[2]/a");



    public Mst_EditCustomerPrivateArticlesPage(WebDriver driver) {
        super(driver);
    }

    public WebElement getBreadcrumb() {
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.visibilityOfElementLocated(DataItems.breadcrumbLocator));
        return element;
    }

    public Mst_EditCustomerPrivateArticlesPage setSalesOrg(String item) throws InterruptedException {
        CommonTask.setDropDownField(driver, salesOrgField, item);
        return new Mst_EditCustomerPrivateArticlesPage(driver);
    }

    public Mst_EditCustomerPrivateArticlesPage setCustomerName(String item) throws InterruptedException {
        CommonTask.setSearchField(driver, custNameField, item);
        return new Mst_EditCustomerPrivateArticlesPage(driver);
    }

    public Mst_EditCustomerPrivateArticlesPage setArticle(String item) throws InterruptedException {
        CommonTask.setChoiceFieldAlt(driver, coatsArticleField, item);
        return new Mst_EditCustomerPrivateArticlesPage(driver);
    }

    public Mst_EditCustomerPrivateArticlesPage setBrand(String item) throws InterruptedException {
        CommonTask.setChoiceFieldAlt(driver, coatsBrandField, item);
        return new Mst_EditCustomerPrivateArticlesPage(driver);
    }

    public Mst_EditCustomerPrivateArticlesPage pressEnable() {
        //Wait for element to be clickable
        WebElement btn = Wait.clickable(driver, statusEnabledRButton);
        //New action to click button
        Actions clickRealtime = new Actions(driver);
        clickRealtime.click(btn).build().perform();

        return this;
    }

    public Mst_EditCustomerPrivateArticlesPage pressDisable() {
        //Wait for element to be clicable
        WebElement btn = Wait.clickable(driver, statusDisabledRbutton);
        //New action to click button
        Actions clickRealtime = new Actions(driver);
        clickRealtime.click(btn).build().perform();

        return this;
    }

    public Mst_EditCustomerPrivateArticlesPage pressSave() {
        WebElement element = Wait.clickable(driver, saveButton);
        element.click();

        return new Mst_EditCustomerPrivateArticlesPage(driver);
    }

    public Mst_EditCustomerPrivateArticlesPage pressCancel() {
        WebElement element = Wait.clickable(driver, cancelButton);
        element.click();

        return new Mst_EditCustomerPrivateArticlesPage(driver);
    }

    public void checkFieldsEdit() {
        //Wait for all elements to be clickable
        //WebElement salesOrg = Wait.clickable(driver, salesOrgField);
        WebElement custName = Wait.clickable(driver, custNameField);
        WebElement coatsArticle = Wait.clickable(driver, coatsArticleField);
        WebElement coatsBrand = Wait.clickable(driver, coatsBrandField);
        WebElement enableStatus = Wait.clickable(driver, statusEnabledRButton);
        WebElement disableStatus = Wait.clickable(driver, statusDisabledRbutton);
        //WebElement save = Wait.clickable(driver, saveButton);
        //WebElement cancel = Wait.clickable(driver, cancelButton);

        //Assert all elements are displayed
        //AssertJUnit.assertTrue("Add Customer Private Arcticle Page: Sales Organisation Field not displayed", salesOrg.isDisplayed());
        AssertJUnit.assertTrue("Add Customer Private Arcticle Page: Customer Name Field not displayed", custName.isDisplayed());
        AssertJUnit.assertTrue("Add Customer Private Arcticle Page: Coats Article Field not displayed", coatsArticle.isDisplayed());
        AssertJUnit.assertTrue("Add Customer Private Arcticle Page: Coats Brand Field not displayed", coatsBrand.isDisplayed());
        AssertJUnit.assertTrue("Add Customer Private Arcticle Page: RButton Enable not displayed" ,enableStatus.isDisplayed());
        AssertJUnit.assertTrue("Add Customer Private Arcticle Page: RButton Disable not displayed", disableStatus.isDisplayed());
        //AssertJUnit.assertTrue("Add Customer Private Arcticle Page: Save Button not displayed", save.isDisplayed());
        //AssertJUnit.assertTrue("Add Customer Private Arcticle Page: Cancel Button not displayed", cancel.isDisplayed());
    }

    public void waitForElement() {
        WebElement salesOrg = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(coatsArticleField));
    }
}
