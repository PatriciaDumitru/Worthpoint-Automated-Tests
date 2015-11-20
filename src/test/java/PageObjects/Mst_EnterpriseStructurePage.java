
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

public class Mst_EnterpriseStructurePage extends WBA_BasePage {
    
    By countryCodeField = By.id("s2id_filterCountryId");
    By salesOrgField = By.id("s2id_filterSalesOrgId");
    By hubField = By.id("s2id_filterHubId");
    By regionField = By.id("s2id_filterRegionId");
    By searchButton = By.cssSelector("#FilterIndexForm > div.actions > ul > li:nth-child(1) > input[type=\"submit\"]");
    By resetButton = By.cssSelector("#FilterIndexForm > div.actions > ul > li:nth-child(2) > a");
    By importButton = By.cssSelector("#content > div.actions > ul > li:nth-child(1) > a");
    By exportButton = By.cssSelector("#export-menu > a");
    By newEnterpriseStructureButton = By.cssSelector("#content > div.actions > ul > li:nth-child(3) > a");
    
    
    public Mst_EnterpriseStructurePage(WebDriver driver) {
        super(driver);
    }
    
    public WebElement getBreadcrumb() {
        return new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.visibilityOfElementLocated(DataItems.breadcrumbLocator));
    }
    
    public Mst_EnterpriseStructurePage setCountryCode(String item) {
        CommonTask.setSearchField(driver, countryCodeField, item);
        return new Mst_EnterpriseStructurePage(driver);
    }
    
    public Mst_EnterpriseStructurePage setSalesOrg(String item) {
        CommonTask.setSearchField(driver, salesOrgField, item);
        return new Mst_EnterpriseStructurePage(driver);
    }
    
    public Mst_EnterpriseStructurePage setHub(String item) {
        CommonTask.setSearchField(driver, hubField, item);
        return new Mst_EnterpriseStructurePage(driver);
    }
    
    public Mst_EnterpriseStructurePage setRegion(String item) {
        CommonTask.setSearchField(driver, regionField, item);
        return new Mst_EnterpriseStructurePage(driver);
    }
    
    public Mst_EditEnterpriseStructurePage pressEdit(int row) {
        By locator = By.cssSelector("#content > div.flexi-grid > table > tbody > tr:nth-child("+row+") > td.actions > a:nth-child(1) > span");
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(locator));
        element.click();
        
        return new Mst_EditEnterpriseStructurePage(driver);
    }
    
    public Mst_EditEnterpriseStructurePage pressDelete(int row) {
        By locator = By.cssSelector("#content > div.flexi-grid > table > tbody > tr:nth-child("+row+") > td.actions > a:nth-child(3) > span");
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(locator));
        element.click();
        
        return new Mst_EditEnterpriseStructurePage(driver);
    }
    
    public int getRow(String hub) {
        if (!checkForRecords()) {
            return -1;
        }
        
        By hubHeader = By.cssSelector("#content > div.flexi-grid > table > tbody > tr:nth-child(1) > th:nth-child(5) > a");
        WebElement header = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.visibilityOfElementLocated(hubHeader));
        
        AssertJUnit.assertTrue("Enterprise Structure Page: Hub Name column has moved, update locators",header.getText().equals("Hub Name"));
        
        By recordsField = By.cssSelector("#content > div.flexi-grid > dl > dt > span.left");
        int count = getRecordCount(recordsField);
        
        int tableCount = (count >= 10) ? 10 : count;
        
        for (int i = 2; i < (tableCount+2); i++) {
            By locator = By.cssSelector("#content > div.flexi-grid > table > tbody > tr:nth-child("+i+") > td:nth-child(5)");
            WebElement cell = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.visibilityOfElementLocated(locator));
            
            if (cell.getText().trim().equals(hub)) {
                return i;
            }
            
        }
        
        return -1;
        
    }
    
    public Mst_EnterpriseStructurePage pressSearch() {
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(searchButton));
        element.click();
        return new Mst_EnterpriseStructurePage(driver);
    }
    
    public Mst_EnterpriseStructurePage pressReset() {
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(resetButton));
        element.click();
        return new Mst_EnterpriseStructurePage(driver);
    }
    
    public Mst_ImportPage pressImport() {
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(importButton));
        element.click();
        return new Mst_ImportPage(driver);
    }
    
    public CCE_ExportDownloadPage pressExport() {
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(exportButton));
        Actions action = new Actions(driver);
        action.moveToElement(element).build().perform();
        
        By xlsx = By.partialLinkText("XLSX");
        WebElement xlsxBtn = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(xlsx));
        xlsxBtn.click();
        
        return new CCE_ExportDownloadPage(driver);
    }
    
    public Mst_AddEnterpriseStructurePage pressNewEnterpriseStructure() {
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(newEnterpriseStructureButton));
        element.click();
        return new Mst_AddEnterpriseStructurePage(driver);
    }
    
    public void checkFields() {
        WebElement countryCode = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(countryCodeField));
        WebElement salesOrg = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(salesOrgField));
        WebElement hub = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(hubField));
        WebElement region = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(regionField));
        WebElement search = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(searchButton));
        WebElement reset = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(resetButton));
        WebElement importB = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(importButton));
        WebElement export = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(exportButton));
        WebElement enterpriseStructure = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(newEnterpriseStructureButton));
    
        AssertJUnit.assertTrue("Enterprise Structure Page: Country Code field not displayed",countryCode.isDisplayed());
        AssertJUnit.assertTrue("Enterprise Structure Page: Sales Org field not displayed",salesOrg.isDisplayed());
        AssertJUnit.assertTrue("Enterprise Structure Page: Hub field not displayed",hub.isDisplayed());
        AssertJUnit.assertTrue("Enterprise Structure Page: Region field not displayed",region.isDisplayed());
        AssertJUnit.assertTrue("Enterprise Structure Page: Search button not displayed",search.isDisplayed());
        AssertJUnit.assertTrue("Enterprise Structure Page: Reset button not displayed",reset.isDisplayed());
        AssertJUnit.assertTrue("Enterprise Structure Page: Import button not displayed",importB.isDisplayed());
        AssertJUnit.assertTrue("Enterprise Structure Page: Export button not displayed",export.isDisplayed());
        AssertJUnit.assertTrue("Enterprise Structure Page: New Enterprise Structure button not displayed",enterpriseStructure.isDisplayed());
        
    }
    
    public void waitForElement() {
        WebElement region = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(regionField));
    }
    
}
