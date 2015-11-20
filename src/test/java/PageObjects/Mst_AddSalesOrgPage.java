
package PageObjects;

import AutomationFramework.CommonTask;
import AutomationFramework.DataItems;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.AssertJUnit;

public class Mst_AddSalesOrgPage extends WBA_BasePage {
    
    //Locators
    By nameField = By.id("SalesOrgSalesOrgName");
    By descField = By.id("SalesOrgDescription");
    By sapInstanceField = By.id("SalesOrgSapInstance");
    By contractOrderField = By.id("SalesOrgOffOrder");
    By saveButton = By.id("save");
    By cancelButton = By.cssSelector("#SalesOrgAddForm > div.actions > ul > li:nth-child(2) > a");
    
    public Mst_AddSalesOrgPage(WebDriver driver) {
        super(driver);
    }
    
    public WebElement getBreadcrumb() {
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.visibilityOfElementLocated(DataItems.breadcrumbLocator));
        return element;
    }
    
    public Mst_AddSalesOrgPage setSalesOrg(String item) {
        CommonTask.setInputField(driver, nameField, item);
        return new Mst_AddSalesOrgPage(driver);
    }
    
    public Mst_AddSalesOrgPage setDescription(String item) {
        CommonTask.setInputField(driver, descField, item);
        return new Mst_AddSalesOrgPage(driver);
    }
    
    public Mst_AddSalesOrgPage setSAPInstance(String item) {
        CommonTask.setInputField(driver, sapInstanceField, item);
        return new Mst_AddSalesOrgPage(driver);
    }
    
    public Mst_SalesOrgPage pressSave() {
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(saveButton));
        element.click();
        
        return new Mst_SalesOrgPage(driver);
    } 
    
    public Mst_SalesOrgPage pressCancel() {
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(cancelButton));
        element.click();
        
        return new Mst_SalesOrgPage(driver);
    } 
    
    public void checkFields() {
        //Wait for all elements to be clickable
        WebElement name = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(nameField));
        WebElement desc = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(descField));
        WebElement sapInstance = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(sapInstanceField));
        WebElement co = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(contractOrderField));
        WebElement save = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(saveButton));
        WebElement cancel = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(cancelButton));
        
        //Assert all elements are displayed
        AssertJUnit.assertTrue("Add Sales Org Page: Name field not displayed",name.isDisplayed());
        AssertJUnit.assertTrue("Add Sales Org Page: Description field not displayed",desc.isDisplayed());
        AssertJUnit.assertTrue("Add Sales Org Page: Contract Order field not displayed",co.isDisplayed());
        AssertJUnit.assertTrue("Add Sales Org Page: SAP Instance field not displayed",sapInstance.isDisplayed());
        AssertJUnit.assertTrue("Add Sales Org Page: Save button not displayed",save.isDisplayed());
        AssertJUnit.assertTrue("Add Sales Org Page: Cancel button not displayed",cancel.isDisplayed());
    }
    
    public void waitForElement() {
        WebElement sapInstance = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(sapInstanceField));
    }

}
