
package PageObjects;

import AutomationFramework.CommonTask;
import AutomationFramework.DataItems;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.AssertJUnit;

public class Mst_AddCustShadePage extends WBA_BasePage {
    
    //Locators
    By salesOrgField = By.id("CustomerShadeSalesOrgId");
    By custNameField = By.id("s2id_CustomerShadeCustomerId");
    By custShadeField = By.id("CustomerShadeCustomerShadeName");
    By coatsShadeField = By.id("s2id_CustomerShadeShadeId");
    By saveButton = By.cssSelector("#CustomerShadeAddForm > div.actions > ul > li:nth-child(1) > input[type=\"submit\"]");
    By cancelButton = By.cssSelector("#CustomerShadeAddForm > div.actions > ul > li:nth-child(2) > a");
    
    public Mst_AddCustShadePage(WebDriver driver) {
        super(driver);
    }
    
    public WebElement getBreadcrumb() {
        return new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(DataItems.breadcrumbLocator));
    }
    
    public Mst_AddCustShadePage setSalesOrg(String item) throws InterruptedException {
        CommonTask.setDropDownField(driver, salesOrgField, item);
        return new Mst_AddCustShadePage(driver);
    }
    
    public Mst_AddCustShadePage setCustomerName(String item) {
        CommonTask.setSearchField(driver, custNameField, item);
        return new Mst_AddCustShadePage(driver);
    }
    
    public Mst_AddCustShadePage setCustomerShade(String item) {
        CommonTask.setInputField(driver, custShadeField, item);
        return new Mst_AddCustShadePage(driver);
    }
    
    public Mst_AddCustShadePage setCoatsShade(String item) {
        CommonTask.setSearchField(driver, coatsShadeField, item);
        return new Mst_AddCustShadePage(driver);
    }
    
    public Mst_AddCustShadePage pressSave() {
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(saveButton));
        element.click();
        
        return new Mst_AddCustShadePage(driver);
    }
    
    public Mst_AddCustShadePage pressCancel() {
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(cancelButton));
        element.click();
        
        return new Mst_AddCustShadePage(driver);
    }
     
    public void checkFields() {
        //Wait for all elements to be clickable
        WebElement salesOrg = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(salesOrgField));
        WebElement custName = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(custNameField));
        WebElement custShade = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(custShadeField));
        WebElement coatsShade = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(coatsShadeField));
        WebElement save = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(saveButton));
        WebElement cancel = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(cancelButton));
        
        //Assert all elements are displayed
        AssertJUnit.assertTrue("Add Customer Shade: Sales Organisation field not displayed",salesOrg.isDisplayed());
        AssertJUnit.assertTrue("Add Customer Shade: Customer Name field not displayed",custName.isDisplayed());
        AssertJUnit.assertTrue("Add Customer Shade: Customer Shade field not displayed",custShade.isDisplayed());
        AssertJUnit.assertTrue("Add Customer Shade: Coats shade field not displayed",coatsShade.isDisplayed());
        AssertJUnit.assertTrue("Add Customer Shade: Save button not displayed",save.isDisplayed());
        AssertJUnit.assertTrue("Add Customer Shade: Cancel button not displayed",cancel.isDisplayed());
    }
    
    public void waitForElement() {
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(coatsShadeField));
    }
    
}
