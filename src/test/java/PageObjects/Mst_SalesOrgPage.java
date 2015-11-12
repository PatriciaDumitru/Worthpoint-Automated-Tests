
package PageObjects;

import AutomationFramework.CommonTask;
import AutomationFramework.DataItems;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.AssertJUnit;

public class Mst_SalesOrgPage extends WBA_BasePage {
    
    //Locators
    By salesOrgField = By.id("filterSalesOrgName");
    By statusField = By.id("s2id_filterStatusId");
    By searchButton = By.cssSelector("#FilterIndexForm > div.actions > ul > li:nth-child(1) > input[type=\"submit\"]");
    By resetButton = By.cssSelector("#FilterIndexForm > div.actions > ul > li:nth-child(2) > a");
    By importButton = By.cssSelector("#content > div.actions > ul > li:nth-child(1) > a");
    By exportButton = By.cssSelector("#export-menu > a");
    By newSalesOrgButton = By.cssSelector("#content > div.actions > ul > li:nth-child(3) > a");
    
    public Mst_SalesOrgPage(WebDriver driver) {
        super(driver);
    }
    
    public WebElement getBreadcrumb() {
        WebElement item = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.visibilityOfElementLocated(breadcrumbLocator));
        return item;
    }
    
    public WebElement getSalesOrgField() {
        WebElement item = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.visibilityOfElementLocated(salesOrgField));
        return item;
    }
    
    public WebElement getStatusField() {
        WebElement item = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.visibilityOfElementLocated(statusField));
        return item;
    }
    
    public WebElement getImportButton() {
        WebElement btn = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(importButton));
        return btn;
    }
    
    public WebElement getExportButton() {
        WebElement btn = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(exportButton));
        return btn;
    }
    
    public WebElement getSearchButton() {
        WebElement btn = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(searchButton));
        return btn;
    }
    
    public WebElement getResetButton() {
        WebElement btn = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(resetButton));
        return btn;
    }
    
    public WebElement getNewSalesOrgButton() {
        WebElement btn = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(newSalesOrgButton));
        return btn;
    }
    
    public Mst_SalesOrgPage setSalesOrg(String item) {
        CommonTask.setInputField(driver, salesOrgField, item);
        
        return new Mst_SalesOrgPage(driver);
    }
    
    public Mst_EditSalesOrgPage pressEdit(int row) {
        By locator = By.cssSelector("#content > div.flexi-grid > table > tbody > tr:nth-child("+row+") > td.actions > a:nth-child(1) > span");
        
        WebElement btn = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(locator));
        btn.click();
        
        return new Mst_EditSalesOrgPage(driver);
    }
    
    public Mst_SalesOrgPage pressSearch() {
        WebElement btn = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(searchButton));
        btn.click();
        
        return new Mst_SalesOrgPage(driver);
    }
    
    public Mst_SalesOrgPage pressReset() {
        WebElement btn = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(resetButton));
        btn.click();
        
        return new Mst_SalesOrgPage(driver);
    }
    
    public CCE_ExportDownloadPage pressExport(String type) {
        WebElement btn = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(exportButton));
        
        Actions action = new Actions(driver);
        action.moveToElement(btn);
        
        By typeLocator = By.partialLinkText(type.toUpperCase());
        
        WebElement typeBtn = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.visibilityOfElementLocated(typeLocator));
        
        action.click(typeBtn);
        
        return new CCE_ExportDownloadPage(driver);
    }
    
    public Mst_AddSalesOrgPage pressNewSalesOrg() {
        WebElement btn = getNewSalesOrgButton();
        
        btn.click();
        return new Mst_AddSalesOrgPage(driver);
    }
    
    public void checkFields() {
        //Wait for all elements to be clickable
        WebElement salesOrg = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(salesOrgField));
        WebElement status = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(statusField));
        
        //Assert all elements are displayed correctly
        AssertJUnit.assertTrue("Sales Organisations Page: Sales Org field not displayed",salesOrg.isDisplayed());
        AssertJUnit.assertTrue("Sales Organisations Page: Status field not displayed",status.isDisplayed());
        
    }
    
    public void waitForElement() {
        WebElement wait = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(statusField));
    }
    
    
}
