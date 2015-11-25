
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

public class Mst_MaterialGroupsPage extends WBA_BasePage {

    By materialGroupField = By.id("filterMaterialGroup");
    By searchButton = By.cssSelector("#FilterIndexForm > div.actions > ul > li:nth-child(1) > input[type=\"submit\"]");
    By resetButton = By.cssSelector("#FilterIndexForm > div.actions > ul > li:nth-child(2) > a");
    By importButton = By.cssSelector("#content > div.actions > ul > li:nth-child(1) > a");
    By exportButton = By.cssSelector("#export-menu > a");
    By newMaterialGroupButton = By.cssSelector("#content > div.actions > ul > li:nth-child(3) > a");
    
    public Mst_MaterialGroupsPage(WebDriver driver) {
        super(driver);
    }
    
    public WebElement getBreadcrumb() {
        return new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.visibilityOfElementLocated(DataItems.breadcrumbLocator));
    }
    
    public Mst_MaterialGroupsPage setMaterialGroup(String item) {
        CommonTask.setInputField(driver, materialGroupField, item);
        return new Mst_MaterialGroupsPage(driver);
    }
    
    public Mst_MaterialGroupsPage pressSearch() {
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(searchButton));
        element.click();
        
        return new Mst_MaterialGroupsPage(driver);
    }
    
    public Mst_MaterialGroupsPage pressReset() {
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(resetButton));
        element.click();
        
        return new Mst_MaterialGroupsPage(driver);
    }
    
    public Mst_AddMaterialGroupPage pressNewMaterialGroup() {
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(newMaterialGroupButton));
        element.click();
        
        return new Mst_AddMaterialGroupPage(driver);
    }
    
    public Mst_EditMaterialGroupPage pressEdit(int row) {
        By locator = By.cssSelector("#content > div.flexi-grid > table > tbody > tr:nth-child("+row+") > td.actions > a:nth-child(1) > span");
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(locator));
        element.click();
        
        return new Mst_EditMaterialGroupPage(driver);
    }
    
    public Mst_MaterialGroupsPage pressDelete(int row) {
        By locator = By.cssSelector("#content > div.flexi-grid > table > tbody > tr:nth-child("+row+") > td.actions > a:nth-child(3) > span");
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(locator));
        element.click();
        
        Alert alert = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.alertIsPresent());
        alert.accept();
        
        return new Mst_MaterialGroupsPage(driver);
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
        
        AssertJUnit.assertTrue("Material Groups Page: Material Group column has moved, update locators",element.getText().equals("Material Group"));
        
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
        WebElement materialGroup = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(materialGroupField));
        WebElement search = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(searchButton));
        WebElement reset = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(resetButton));
        WebElement importB = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(importButton));
        WebElement export = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(exportButton));
        WebElement newMatGroup = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(newMaterialGroupButton));
        
        AssertJUnit.assertTrue("Material Groups Page: Material Group field not displayed",materialGroup.isDisplayed());
        AssertJUnit.assertTrue("Material Groups Page: Search button not displayed",search.isDisplayed());
        AssertJUnit.assertTrue("Material Groups Page: Reset button not displayed",reset.isDisplayed());
        AssertJUnit.assertTrue("Material Groups Page: Import button not displayed",importB.isDisplayed());
        AssertJUnit.assertTrue("Material Groups Page: Export button not displayed",export.isDisplayed());
        AssertJUnit.assertTrue("Material Groups Page: New Material Group button not displayed",newMatGroup.isDisplayed());
    }
    
    public void waitForElement() {
        WebElement materialGroup = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(materialGroupField));
    }
    
}
