
package PageObjects;

import AutomationFramework.CommonTask;
import AutomationFramework.DataItems;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.AssertJUnit;

public class Mst_BasicMaterialsPage extends WBA_BasePage {
    
    By brandField = By.id("s2id_filterBrandId");
    By materialGroupField = By.id("s2id_filterMaterialGroupId");
    By productHierarchyField = By.id("s2id_filterProductHierarchyId");
    By searchButton = By.cssSelector("#FilterIndexForm > div.actions > ul > li:nth-child(1) > input[type=\"submit\"]");
    By resetButton = By.cssSelector("#FilterIndexForm > div.actions > ul > li:nth-child(2) > a");
    By importButton = By.cssSelector("#content > div.actions > ul > li:nth-child(1) > a");
    By exportButton = By.cssSelector("#export-menu > a");
    By newBasicMaterialButton = By.cssSelector("#content > div.actions > ul > li:nth-child(3) > a");
    
    public Mst_BasicMaterialsPage(WebDriver driver) {
        super(driver);
    }
    
    public WebElement getBreadcrumb() {
        return new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.visibilityOfElementLocated(DataItems.breadcrumbLocator));
    }
    
    public Mst_BasicMaterialsPage setBrand(String item) {
        CommonTask.setSearchField(driver, brandField, item);
        return new Mst_BasicMaterialsPage(driver);
    }
    
    public Mst_BasicMaterialsPage setProductHierarchy(String item) {
        CommonTask.setSearchField(driver, productHierarchyField, item);
        return new Mst_BasicMaterialsPage(driver);
    }
    
    public Mst_BasicMaterialsPage setMaterialGroup(String item) {
        CommonTask.setSearchField(driver, materialGroupField, item);
        return new Mst_BasicMaterialsPage(driver);
    }
    
    public Mst_BasicMaterialsPage pressSearch() {
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(searchButton));
        element.click();
        
        return new Mst_BasicMaterialsPage(driver);
    }
    
    public Mst_BasicMaterialsPage pressReset() {
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(resetButton));
        element.click();
        
        return new Mst_BasicMaterialsPage(driver);
    }
    
    public Mst_AddBasicMaterialPage pressNewBasicMaterial() {
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(newBasicMaterialButton));
        element.click();
        
        return new Mst_AddBasicMaterialPage(driver);
    }
    
    public Mst_EditBasicMaterialPage pressEdit(int row) {
        By locator = By.cssSelector("#content > div.flexi-grid > table > tbody > tr:nth-child("+row+") > td.actions > a:nth-child(1) > span");
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(locator));
        element.click();
        
        return new Mst_EditBasicMaterialPage(driver);
    }
    
    public Mst_BasicMaterialsPage pressDelete(int row) {
        By locator = By.cssSelector("#content > div.flexi-grid > table > tbody > tr:nth-child("+row+") > td.actions > a:nth-child(3) > span");
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(locator));
        element.click();
        
        Alert alert = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.alertIsPresent());
        alert.accept();
        
        return new Mst_BasicMaterialsPage(driver);
    }
    
    public Mst_ImportPage pressImport() {
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(importButton));
        element.click();
        
        return new Mst_ImportPage(driver);
    }
    
    public CCE_ExportDownloadPage pressExport() {
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(exportButton));
        element.click();
        
        return new CCE_ExportDownloadPage(driver);
    }
    
    public int getRow(String item) {
        if (!checkForRecords()) {
            return -1;
        }
        
        By header = By.cssSelector("#content > div.flexi-grid > table > tbody > tr:nth-child(1) > th:nth-child(2) > a");
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.visibilityOfElementLocated(header));
        
        AssertJUnit.assertTrue("Basic Materials Page: Brand column has moved, update locators",element.getText().equals("Brand"));
        
        By recordsField = By.cssSelector("#content > div.flexi-grid > dl > dt > span.left");
        int count = getRecordCount(recordsField);
        int tableCount = (count >= 10) ? 10 : count;
        
        for (int i = 2; i < (tableCount + 2); i++) {
            By locator = By.cssSelector("#content > div.flexi-grid > table > tbody > tr:nth-child("+i+") > td:nth-child(2)");
            WebElement cell = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.visibilityOfElementLocated(locator));
            
            if (cell.getText().trim().equals(item)) {
                return i;
            }
            
        }
        
        return -1;  
    }
    
    public void checkFields() {
        WebElement brand = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(brandField));
        WebElement materialGroup = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(materialGroupField));
        WebElement productHierarchy = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(productHierarchyField));
        WebElement search = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(searchButton));
        WebElement reset = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(resetButton));
        WebElement importB = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(importButton));
        WebElement export = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(exportButton));
        WebElement newBasicMat = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(newBasicMaterialButton));
        
        AssertJUnit.assertTrue("Basic Materials Page: Brand field not displayed",brand.isDisplayed());
        AssertJUnit.assertTrue("Basic Materials Page: Material Group field not displayed",materialGroup.isDisplayed());
        AssertJUnit.assertTrue("Basic Materials Page: Product Hierarchy field not displayed",productHierarchy.isDisplayed());
        AssertJUnit.assertTrue("Basic Materials Page: Search button not displayed",search.isDisplayed());
        AssertJUnit.assertTrue("Basic Materials Page: Reset button not displayed",reset.isDisplayed());
        AssertJUnit.assertTrue("Basic Materials Page: Import button not displayed",importB.isDisplayed());
        AssertJUnit.assertTrue("Basic Materials Page: Export button not displayed",export.isDisplayed());
        AssertJUnit.assertTrue("Basic Materials Page: New Basic Material button not displayed",export.isDisplayed());
    }
    
    public void waitForElement() {
        WebElement brand = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(brandField));
    }
    
}
