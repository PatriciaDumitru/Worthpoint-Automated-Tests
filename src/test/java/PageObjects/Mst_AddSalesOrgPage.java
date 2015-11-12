
package PageObjects;

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
    
    public WebElement getNameField() {
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(nameField));
        return element;
    }
    
    public WebElement getDescriptionField() {
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(descField));
        return element;
    }
    
    public WebElement getContractOrderField() {
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(contractOrderField));
        return element;
    }
    
    public WebElement getSaveButton() {
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(saveButton));
        return element;
    }
    
    public WebElement getCancelButton() {
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(cancelButton));
        return element;
    }
    
    public void checkFields() {
        //Wait for all elements to be clickable
        WebElement name = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(nameField));
        WebElement desc = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(descField));
        WebElement co = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(contractOrderField));
        WebElement save = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(saveButton));
        WebElement cancel = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(cancelButton));
        
        //Assert all elements are displayed
        AssertJUnit.assertTrue("Add Sales Org Page: Name field not displayed",name.isDisplayed());
        AssertJUnit.assertTrue("Add Sales Org Page: Description field not displayed",desc.isDisplayed());
        AssertJUnit.assertTrue("Add Sales Org Page: Contract Order field not displayed",co.isDisplayed());
        AssertJUnit.assertTrue("Add Sales Org Page: Save button not displayed",save.isDisplayed());
        AssertJUnit.assertTrue("Add Sales Org Page: Cancel button not displayed",cancel.isDisplayed());
    }
    
    public void waitForElement() {
        WebElement wait = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(getDescriptionField()));
    }
        
    
    
}
