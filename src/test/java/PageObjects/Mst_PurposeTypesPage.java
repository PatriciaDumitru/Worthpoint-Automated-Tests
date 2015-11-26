
package PageObjects;

import AutomationFramework.CommonTask;
import AutomationFramework.DataItems;
import PageObjects.CCE_ExportDownloadPage;
import PageObjects.Mst_AddPurposeTypePage;
import PageObjects.Mst_EditPurposeTypePage;
import PageObjects.Mst_ImportPage;
import PageObjects.WBA_BasePage;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.AssertJUnit;

public class Mst_PurposeTypesPage extends WBA_BasePage {
    
    By purposeTypeField = By.id("filterPurposeType");
    By searchButton = By.cssSelector("#FilterIndexForm > div.actions > ul > li:nth-child(1) > input[type=\"submit\"]");
    By resetButton = By.cssSelector("#FilterIndexForm > div.actions > ul > li:nth-child(2) > a");
    By importButton = By.cssSelector("#content > div.actions > ul > li:nth-child(1) > a");
    By exportButton = By.cssSelector("#export-menu > a");
    By newPurposeTypeButton = By.cssSelector("#content > div.actions > ul > li:nth-child(3) > a");
    
    public Mst_PurposeTypesPage(WebDriver driver) {
        super(driver);
    }
    
    public WebElement getBreadcrumb() {
        return new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.visibilityOfElementLocated(DataItems.breadcrumbLocator));
    }
    
    public Mst_PurposeTypesPage setPurposeType(String item) {
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(purposeTypeField));
        element.clear();
        
        CommonTask.setInputField(driver, purposeTypeField, item);
        return new Mst_PurposeTypesPage(driver);
    }
    
    public Mst_PurposeTypesPage pressSearch() {
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(searchButton));
        element.click();
        
        return new Mst_PurposeTypesPage(driver);
    }
    
    public Mst_PurposeTypesPage pressReset() {
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(resetButton));
        element.click();
        
        return new Mst_PurposeTypesPage(driver);
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
    
    public Mst_AddPurposeTypePage pressNewPurposeType() {
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(newPurposeTypeButton));
        element.click();
        
        return new Mst_AddPurposeTypePage(driver);
    }
    
    public Mst_EditPurposeTypePage pressEdit(int row) {
        By editButton = By.cssSelector("#content > div.flexi-grid > table > tbody > tr:nth-child("+row+") > td.actions > a:nth-child(1) > span");
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(editButton));
        element.click();
        
        return new Mst_EditPurposeTypePage(driver);
    }
    
    public Mst_PurposeTypesPage pressDelete(int row) {
        By deleteButton = By.cssSelector("#content > div.flexi-grid > table > tbody > tr:nth-child("+row+") > td.actions > a:nth-child(3) > span");
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(deleteButton));
        element.click();
        
        Alert alert = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.alertIsPresent());
        alert.accept();
        
        return new Mst_PurposeTypesPage(driver);
    }
    
    public int getRow(String item) {
        if (!checkForRecords()) {
            return -1;
        }
        
        By headerLocator = By.cssSelector("#content > div.flexi-grid > table > tbody > tr:nth-child(1) > th:nth-child(2) > a");
        WebElement header = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.visibilityOfElementLocated(headerLocator));
        AssertJUnit.assertTrue("Purpose Types Page: Purpose Type column has moved, update locators",header.getText().equals("Purpose Type"));
        
        By recordsField = By.cssSelector("#content > div.flexi-grid > dl > dt > span.left");
        int count = getRecordCount(recordsField);
        int tableCount = (count >= 10) ? 10 : count;
        
        for (int i = 2; i < (tableCount+2); i++) {
            By locator = By.cssSelector("#content > div.flexi-grid > table > tbody > tr:nth-child("+i+") > td:nth-child(2)");
            WebElement cell = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.visibilityOfElementLocated(locator));
            if (cell.getText().equals(item)) {
                return i;
            }
        }
        return -1;
    }
    
    public void checkFields() {
        WebElement purposeType = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(purposeTypeField));
        WebElement search = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(searchButton));
        WebElement reset = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(resetButton));
        WebElement importB = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(importButton));
        WebElement export = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(exportButton));
        WebElement newPurpose = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(newPurposeTypeButton));
        
        AssertJUnit.assertTrue("Purpose Types Page: Purpose Type field not displayed",purposeType.isDisplayed());
        AssertJUnit.assertTrue("Purpose Types Page: Search button not displayed",search.isDisplayed());
        AssertJUnit.assertTrue("Purpose Types Page: Reset button not displayed",reset.isDisplayed());
        AssertJUnit.assertTrue("Purpose Types Page: Import button not displayed",importB.isDisplayed());
        AssertJUnit.assertTrue("Purpose Types Page: Export button not displayed",export.isDisplayed());
        AssertJUnit.assertTrue("Purpose Types Page: New Purpose Type button not displayed",newPurpose.isDisplayed());
    }
    
    public void waitForElement() {
        WebElement purposeType = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(purposeTypeField));
    }
    
}
