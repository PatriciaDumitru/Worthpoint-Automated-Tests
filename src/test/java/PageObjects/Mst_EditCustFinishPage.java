
package PageObjects;

import AutomationFramework.CommonTask;
import AutomationFramework.DataItems;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.AssertJUnit;

public class Mst_EditCustFinishPage extends WBA_BasePage {
    
    //Locators
    By salesOrgField = By.id("CustomerFinishSalesOrgId");
    By custNameField = By.id("s2id_CustomerFinishCustomerId");
    By custFinishField = By.id("CustomerFinishCustomerFinishName");
    By coatsFinishField = By.id("CustomerFinishFinishId");
    By saveButton = By.cssSelector("#CustomerFinishEditForm > div.actions > ul > li:nth-child(1) > input[type=\"submit\"]");
    By cancelButton = By.cssSelector("#CustomerFinishEditForm > div.actions > ul > li:nth-child(2) > a");
    
    public Mst_EditCustFinishPage(WebDriver driver) {
        super(driver);
    }
    
    public WebElement getBreadcrumb() {
        return new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.visibilityOfElementLocated(DataItems.breadcrumbLocator));
    }
    
    public Mst_EditCustFinishPage setSalesOrg(String item) throws InterruptedException {
        CommonTask.setDropDownField(driver, salesOrgField, item);
        return new Mst_EditCustFinishPage(driver);
    }
    
    public Mst_EditCustFinishPage setCustomerName(String item) throws InterruptedException {
        CommonTask.setSearchField(driver, custNameField, item);
        return new Mst_EditCustFinishPage(driver);
    }
    
    public Mst_EditCustFinishPage setCustomerFinish(String item) throws InterruptedException {
        CommonTask.setDropDownField(driver, custFinishField, item);
        return new Mst_EditCustFinishPage(driver);
    }
    
    public Mst_EditCustFinishPage setCoatsFinsh(String item) throws InterruptedException {
        CommonTask.setDropDownField(driver, coatsFinishField, item);
        return new Mst_EditCustFinishPage(driver);
    }
    
    public Mst_CustFinishesPage pressSave() {
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(saveButton));
        element.click();
        
        return new Mst_CustFinishesPage(driver);
    }
    
    public Mst_CustFinishesPage pressCancel() {
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(cancelButton));
        element.click();
        
        return new Mst_CustFinishesPage(driver);
    }
    
    public void checkFields() {
        //Wait for all elements to be clickable
        WebElement salesOrg = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(salesOrgField));
        WebElement custName = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(custNameField));
        WebElement custFinish = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(custFinishField));
        WebElement coatsFinish = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(coatsFinishField));
        WebElement save = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(saveButton));
        WebElement cancel = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(cancelButton));
        
        //Assert all elements are displayed
        AssertJUnit.assertTrue("Edit Customer Finish Page: Sales Organisation field not displayed as expected",salesOrg.isDisplayed());
        AssertJUnit.assertTrue("Edit Customer Finish Page: Customer Name field not displayed as expected",custName.isDisplayed());
        AssertJUnit.assertTrue("Edit Customer Finish Page: Customer Finish field not displayed as expected",custFinish.isDisplayed());
        AssertJUnit.assertTrue("Edit Customer Finish Page: Coats Finish field not displayed as expected",coatsFinish.isDisplayed());
        AssertJUnit.assertTrue("Edit Customer Finish Page: Save button not displayed as expected",save.isDisplayed());
        AssertJUnit.assertTrue("Edit Customer Finish Page: Cancel button not displayed as expected",cancel.isDisplayed());
    }
    
    public void waitForElement() {
        WebElement coatsFinish = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(coatsFinishField));
    }
    
}
