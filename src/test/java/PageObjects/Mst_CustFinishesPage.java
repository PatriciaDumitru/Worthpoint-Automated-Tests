
package PageObjects;

import AutomationFramework.CommonTask;
import AutomationFramework.DataItems;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.AssertJUnit;

public class Mst_CustFinishesPage extends WBA_BasePage {
    
    //Locators
    By salesOrgField = By.id("s2id_filterSalesOrgId");
    By custNameField = By.id("s2id_filterCustomerId");
    By coatsFinishField = By.id("s2id_filterFinishId");
    By custFinishField = By.id("filterCustomerFinishName");
    By searchButton = By.cssSelector("#FilterIndexForm > div.actions > ul > li:nth-child(1) > input[type=\"submit\"]");
    By resetButton = By.cssSelector("#FilterIndexForm > div.actions > ul > li:nth-child(2) > a");
    By importButton = By.cssSelector("#content > div.actions > ul > li:nth-child(1) > a");
    By exportButton = By.cssSelector("#export-menu > a");
    By newCustFinishButton = By.cssSelector("#content > div.actions > ul > li:nth-child(3) > a");
    
    public Mst_CustFinishesPage(WebDriver driver) {
        super(driver);
    }
    
    public WebElement getBreadcrumb() {
        return new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.visibilityOfElementLocated(DataItems.breadcrumbLocator));
    }
    
    public Mst_CustFinishesPage setSalesOrg(String item) {
        CommonTask.setSearchField(driver, salesOrgField, item);
        return new Mst_CustFinishesPage(driver);
    }
    
    public Mst_CustFinishesPage setCustomerName(String item) {
        CommonTask.setSearchField(driver, custNameField, item);
        return new Mst_CustFinishesPage(driver);
    }
    
    public Mst_CustFinishesPage setCoatsFinish(String item) {
        CommonTask.setSearchField(driver, coatsFinishField, item);
        return new Mst_CustFinishesPage(driver);
    }
    
    public Mst_CustFinishesPage setCustFinish(String item) {
        CommonTask.setInputField(driver, custFinishField, item);
        return new Mst_CustFinishesPage(driver);
    }
    
    public Mst_EditCustFinishPage pressEdit(int row) {
        By locator = By.cssSelector("#content > div.flexi-grid > table > tbody > tr:nth-child("+row+") > td.actions > a:nth-child(1) > span");
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(locator));
        element.click();
        
        return new Mst_EditCustFinishPage(driver);
    }
    
    public Mst_CustFinishesPage pressDelete(int row) {
        By locator = By.cssSelector("#content > div.flexi-grid > table > tbody > tr:nth-child("+row+") > td.actions > a:nth-child(3) > span");
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(locator));
        element.click();
        
        return new Mst_CustFinishesPage(driver);
    }
    
    public void checkFields() {
        //Wait for all elements to be clickable
        WebElement salesOrg = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(salesOrgField));
        WebElement custName = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(custNameField));
        WebElement coatsFinish = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(coatsFinishField));
        WebElement custFinish = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(custFinishField));
        WebElement search = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(searchButton));
        WebElement reset = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(resetButton));
        WebElement importBtn = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(importButton));
        WebElement exportBtn = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(exportButton));
        WebElement newCustFinishBtn = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(newCustFinishButton));
     
        //Assert all elements are clickable
        AssertJUnit.assertTrue("Customer Finishes Page: Sales Org Field not displayed",salesOrg.isDisplayed());
        AssertJUnit.assertTrue("Customer Finishes Page: Customer Name Field not displayed",custName.isDisplayed());
        AssertJUnit.assertTrue("Customer Finishes Page: Coats Finish Field not displayed",coatsFinish.isDisplayed());
        AssertJUnit.assertTrue("Customer Finishes Page: Customer Finish Field not displayed",custFinish.isDisplayed());
        AssertJUnit.assertTrue("Customer Finishes Page: Search button not displayed",search.isDisplayed());
        AssertJUnit.assertTrue("Customer Finishes Page: Reset button not displayed",reset.isDisplayed());
        AssertJUnit.assertTrue("Customer Finishes Page: Import button not displayed",importBtn.isDisplayed());
        AssertJUnit.assertTrue("Customer Finishes Page: Export button not displayed",exportBtn.isDisplayed());
        AssertJUnit.assertTrue("Customer Finishes Page: New Customer Finish button not displayed",newCustFinishBtn.isDisplayed());
    }
    
    public void waitForElement() {
        WebElement custFinish = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(custFinishField));
    }
    
}
