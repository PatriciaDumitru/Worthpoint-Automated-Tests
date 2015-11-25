
package PageObjects;

import AutomationFramework.CommonTask;
import AutomationFramework.DataItems;
import static PageObjects.WBA_BasePage.driver;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.AssertJUnit;

public class Mst_LightSourcesPage extends WBA_BasePage {
    
    By lightSourceField = By.id("filterLightSource");
    By searchButton = By.cssSelector("#FilterIndexForm > div.actions > ul > li:nth-child(1) > input[type=\"submit\"]");
    By resetButton = By.cssSelector("#FilterIndexForm > div.actions > ul > li:nth-child(2) > a");
    By importButton = By.cssSelector("#content > div.actions > ul > li:nth-child(1) > a");
    By exportButton = By.cssSelector("#export-menu > a");
    By newLightSourceButton = By.cssSelector("#content > div.actions > ul > li:nth-child(3) > a");
    
    public Mst_LightSourcesPage(WebDriver driver) {
        super(driver);
    }
    
    public WebElement getBreadcrumb() {
        return new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.visibilityOfElementLocated(DataItems.breadcrumbLocator));
    }
    
    public Mst_LightSourcesPage setLightSource(String item) {
        CommonTask.setInputField(driver, lightSourceField, item);
        return new Mst_LightSourcesPage(driver);
    }
    
    public Mst_HierarchyPage pressSearch() {
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(searchButton));
        element.click();
        
        return new Mst_HierarchyPage(driver);
    }
    
    public Mst_HierarchyPage pressReset() {
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(resetButton));
        element.click();
        
        return new Mst_HierarchyPage(driver);
    }
    
    public Mst_AddLightSourcePage pressNewLightSource() {
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(newLightSourceButton));
        element.click();
        
        return new Mst_AddLightSourcePage(driver);
    }
    
    public Mst_EditLightSourcePage pressEdit(int row) {
        By locator = By.cssSelector("#content > div.flexi-grid > table > tbody > tr:nth-child("+row+") > td.actions > a:nth-child(1) > span");
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(locator));
        element.click();
        
        return new Mst_EditLightSourcePage(driver);
    }
    
    public Mst_LightSourcesPage pressDelete(int row) {
        By locator = By.cssSelector("#content > div.flexi-grid > table > tbody > tr:nth-child("+row+") > td.actions > a:nth-child(3) > span");
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(locator));
        element.click();
        
        Alert alert = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.alertIsPresent());
        alert.accept();
        
        return new Mst_LightSourcesPage(driver);
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
    
    public int getRow(String item) {
        if (!checkForRecords()) {
            return -1;
        }
        
        By header = By.cssSelector("#content > div.flexi-grid > table > tbody > tr:nth-child(1) > th:nth-child(2) > a");
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.visibilityOfElementLocated(header));
        
        AssertJUnit.assertTrue("Light Sources Page: Light Sourc column has moved, update locators",element.getText().equals("Light Source"));
        
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
        WebElement lightSource = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(lightSourceField));
        WebElement search = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(searchButton));
        WebElement reset = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(resetButton));
        WebElement importB = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(importButton));
        WebElement export = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(exportButton));
        WebElement newHierarchy = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(newLightSourceButton));
        
        AssertJUnit.assertTrue("Hierarchy Page: Hierarchy field not displayed",lightSource.isDisplayed());
        AssertJUnit.assertTrue("Hierarchy Page: Search button not displayed",search.isDisplayed());
        AssertJUnit.assertTrue("Hierarchy Page: Reset button not displayed",reset.isDisplayed());
        AssertJUnit.assertTrue("Hierarchy Page: Import button not displayed",importB.isDisplayed());
        AssertJUnit.assertTrue("Hierarchy Page: Export button not displayed",export.isDisplayed());
        AssertJUnit.assertTrue("Hierarchy Page: New Material Group button not displayed",newHierarchy.isDisplayed());
    }
    
    public void waitForElement() {
        WebElement search = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(searchButton));
    }
    
}
