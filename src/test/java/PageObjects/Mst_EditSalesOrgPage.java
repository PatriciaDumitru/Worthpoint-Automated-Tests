
package PageObjects;

import AutomationFramework.DataItems;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.AssertJUnit;

public class Mst_EditSalesOrgPage extends WBA_BasePage {
    
    //Locators
    By nameField = By.id("SalesOrgSalesOrgName");
    By descriptionField = By.id("SalesOrgDescription");
    By contractOrderField = By.id("SalesOrgOffOrder");
    By subAcctField = By.id("SalesOrgIsPayerEnabled");
    By subAcctLabel = By.cssSelector("#SalesOrgEditForm > div.frm > table > tbody > tr:nth-child(5) > td:nth-child(1) > label");
    By mailNotificationField = By.id("SalesOrgIsCceshipnoticeEnabled");
    By mailNotificationLabel = By.cssSelector("#SalesOrgEditForm > div.frm > table > tbody > tr:nth-child(6) > td:nth-child(1) > label");
    By saveButton = By.id("save");
    By cancelButton = By.cssSelector("#SalesOrgEditForm > div.actions > ul > li:nth-child(2) > a");
    
    public Mst_EditSalesOrgPage(WebDriver driver) {
        super(driver);
    }
    
    public WebElement getBreadcrumb() {
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.visibilityOfElementLocated(DataItems.breadcrumbLocator));
        return element;
    }
    
    public WebElement getNameField() {
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.visibilityOfElementLocated(nameField));
        return element;
    }
    
    public WebElement getDescriptionField() {
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.visibilityOfElementLocated(descriptionField));
        return element;
    }
    
    public WebElement getContractOrderField() {
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.visibilityOfElementLocated(contractOrderField));
        return element;
    }
    
    public WebElement getSubAccountField() {
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(subAcctField));
        return element;
    }
    
    public WebElement getSubAccountLabel() {
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(subAcctLabel));
        return element;
    }
    
    public WebElement getMailNotificationField() {
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(mailNotificationField));
        return element;
    }
    
    public WebElement getMailNotificationLabel() {
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(mailNotificationLabel));
        return element;
    }
    
    public WebElement getSaveButton() {
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.visibilityOfElementLocated(saveButton));
        return element;
    }
    
    public WebElement getCancelButton() {
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.visibilityOfElementLocated(cancelButton));
        return element;
    }
    
    public Mst_SalesOrgPage pressSave() {
        WebElement btn = getSaveButton();
        btn.click();
        
        return new Mst_SalesOrgPage(driver);
    }
    
    public void checkFields() {
        //Wait for all elements to be clickable
        WebElement name = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(nameField));
        WebElement desc = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(descriptionField));
        WebElement contractOrder = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(contractOrderField));
        WebElement saveBtn = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(saveButton));
        WebElement cancelBtn = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(cancelButton));
        
        //Assert all elements are displayed
        AssertJUnit.assertTrue("Edit Sales Org Page: Name field not displayed",name.isDisplayed());
        AssertJUnit.assertTrue("Edit Sales Org Page: Description field not displayed",desc.isDisplayed());
        AssertJUnit.assertTrue("Edit Sales Org Page: Contract Order field not displayed",contractOrder.isDisplayed());
        AssertJUnit.assertTrue("Edit Sales Org Page: Save button not displayed",saveBtn.isDisplayed());
        AssertJUnit.assertTrue("Edit Sales Org Page: Cancel button not displayed",cancelBtn.isDisplayed());
    }
    
    public void waitForElement() {
        WebElement wait = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(descriptionField));
    }
    
}
