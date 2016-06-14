
package PageObjects;

import AutomationFramework.CommonTask;
import AutomationFramework.DataItems;
import AutomationFramework.Wait;
import static PageObjects.WBA_BasePage.driver;
import java.sql.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.AssertJUnit;

public class Mst_AddCustFinishPage extends WBA_BasePage {
    
    public Mst_AddCustFinishPage(WebDriver driver) {
        super(driver);
    }
    
    //Locators
    By salesOrgField = By.id("CustomerFinishSalesOrgId");
    By custNameField = By.id("s2id_CustomerFinishCustomerId");
    By custFinishField = By.id("CustomerFinishCustomerFinishName");
    By coatsFinishField = By.id("CustomerFinishFinishId");
    By saveButton = By.cssSelector("#CustomerFinishAddForm > div.actions > ul > li:nth-child(1) > input[type=\"submit\"]");
    By cancelButton = By.cssSelector("#CustomerFinishAddForm > div.actions > ul > li:nth-child(2) > a");
    
    public WebElement getBreadcrumb() {
        return Wait.visible(driver,DataItems.breadcrumbLocator);
    }
    
    public Mst_EditCustFinishPage setSalesOrg(String item) throws InterruptedException {
        CommonTask.setDropDownField(driver, salesOrgField, item);
        return new Mst_EditCustFinishPage(driver);
    }

    public Mst_EditCustFinishPage setCoatsFinish(String item) throws InterruptedException {
        CommonTask.setDropDownField(driver, coatsFinishField, item);
        return new Mst_EditCustFinishPage(driver);
    }

    public Mst_EditCustFinishPage setCustomerName(String item) throws InterruptedException {
        CommonTask.setSearchField(driver, custNameField, item);
        return new Mst_EditCustFinishPage(driver);
    }
    
    public Mst_EditCustFinishPage setCustomerFinish(String item) throws InterruptedException {
        CommonTask.setInputField(driver, custFinishField, item);
        return new Mst_EditCustFinishPage(driver);
    }
    
    public Mst_EditCustFinishPage setCoatsFinsh(String item) throws InterruptedException {
        CommonTask.setDropDownField(driver, coatsFinishField, item);
        return new Mst_EditCustFinishPage(driver);
    }
    
    public Mst_CustFinishesPage pressSave() {
        WebElement element = Wait.clickable(driver,saveButton);
        element.click();
        
        return new Mst_CustFinishesPage(driver);
    }
    
    public Mst_CustFinishesPage pressCancel() {
        WebElement element = Wait.clickable(driver,cancelButton);
        element.click();
        
        return new Mst_CustFinishesPage(driver);
    }
    
    public void checkFields() {
        //Wait for all elements to be clickable
        WebElement salesOrg = Wait.clickable(driver,salesOrgField);
        WebElement custName = Wait.clickable(driver,custNameField);
        WebElement custFinish = Wait.clickable(driver,custFinishField);
        WebElement coatsFinish = Wait.clickable(driver,coatsFinishField);
        WebElement save = Wait.clickable(driver,saveButton);
        WebElement cancel = Wait.clickable(driver,cancelButton);
        
        //Assert all elements are displayed
        AssertJUnit.assertTrue("Edit Customer Finish Page: Sales Organisation field not displayed as expected",salesOrg.isDisplayed());
        AssertJUnit.assertTrue("Edit Customer Finish Page: Customer Name field not displayed as expected",custName.isDisplayed());
        AssertJUnit.assertTrue("Edit Customer Finish Page: Customer Finish field not displayed as expected",custFinish.isDisplayed());
        AssertJUnit.assertTrue("Edit Customer Finish Page: Coats Finish field not displayed as expected",coatsFinish.isDisplayed());
        AssertJUnit.assertTrue("Edit Customer Finish Page: Save button not displayed as expected",save.isDisplayed());
        AssertJUnit.assertTrue("Edit Customer Finish Page: Cancel button not displayed as expected",cancel.isDisplayed());
    }
    
    public void waitForElement() {
        WebElement coatsFinish = Wait.clickable(driver,coatsFinishField);
    }
    
}
