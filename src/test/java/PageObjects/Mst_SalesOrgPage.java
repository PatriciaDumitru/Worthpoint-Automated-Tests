
package PageObjects;

import AutomationFramework.CommonTask;
import AutomationFramework.DataItems;
import org.openqa.selenium.Alert;
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
    
    public CCE_ExportDownloadPage pressExport() {
        WebElement btn = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(exportButton));
        
        Actions action = new Actions(driver);
        action.moveToElement(btn).build().perform();
        
        By typeLocator = By.partialLinkText("XLSX");
        
        WebElement typeBtn = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.visibilityOfElementLocated(typeLocator));
        
        action.click(typeBtn).build().perform();
        
        return new CCE_ExportDownloadPage(driver);
    }
    
    public Mst_ImportPage pressImport() {
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(importButton));
        element.click();
        
        return new Mst_ImportPage(driver);
    }
    
    public Mst_AddSalesOrgPage pressNewSalesOrg() {
        WebElement btn = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(newSalesOrgButton));
        
        btn.click();
        return new Mst_AddSalesOrgPage(driver);
    }
    
    public Mst_SalesOrgPage pressDelete(int row) {
        By deleteBtn = By.cssSelector("#content > div.flexi-grid > table > tbody > tr:nth-child("+row+") > td.actions > a:nth-child(3) > span");
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(deleteBtn));
        element.click();
        
        Alert alert = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.alertIsPresent());
        alert.accept();
        
        return new Mst_SalesOrgPage(driver);
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
    
    public int getRow(String salesOrg) {
        if (!this.checkForRecords()) {
            return -1;
        }
        
        By salesOrgHeader = By.cssSelector("#content > div.flexi-grid > table > tbody > tr:nth-child(1) > th:nth-child(2) > a");
        WebElement header = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(salesOrgHeader));
        
        AssertJUnit.assertTrue("Sales Organisation Page: Sales Organisation column has moved, update locators",header.getText().equals("Sales Organisation"));
        
        By recordField = By.cssSelector("#content > div.flexi-grid > dl > dt > span.left");
        int count = this.getRecordCount(recordField);
        
        for (int i = 2; i < (count + 2); i++) {
            By locator = By.cssSelector("#content > div.flexi-grid > table > tbody > tr:nth-child("+i+") > td:nth-child(2)");
            WebElement cell = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(locator));
            
            if (cell.getText().equals(salesOrg)) {
                return i;
            }
            
        }
        
        return -1;
        
    }
    
}
