
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

public class Mst_ShadeCardsPage extends WBA_BasePage {
    
    By shadeCardField = By.id("filterShadeCardName");
    By shadeCardCodeField = By.id("filterShadeCardCode");
    By searchButton = By.cssSelector("#FilterIndexForm > div.actions > ul > li:nth-child(1) > input[type=\"submit\"]");
    By resetButton = By.cssSelector("#FilterIndexForm > div.actions > ul > li:nth-child(2) > a");
    By importButton = By.cssSelector("#content > div.actions > ul > li:nth-child(1) > a");
    By exportButton = By.cssSelector("#export-menu > a");
    By newShadeCardButton = By.cssSelector("#content > div.actions > ul > li:nth-child(3) > a");
    
    public Mst_ShadeCardsPage(WebDriver driver) {
        super(driver);
    }
    
    public WebElement getBreadcrumb() {
        return new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(DataItems.breadcrumbLocator));
    }
    
    public Mst_ShadeCardsPage setShadeCard(String item) {
        CommonTask.setInputField(driver, shadeCardField, item);
        return new Mst_ShadeCardsPage(driver);
    }
    
    public Mst_ShadeCardsPage setShadeCardCode(String item) {        
        CommonTask.setInputField(driver, shadeCardCodeField, item);
        return new Mst_ShadeCardsPage(driver);
    }
    
    public Mst_ShadeCardsPage pressSearch() {
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(searchButton));
        element.click();
        
        return new Mst_ShadeCardsPage(driver);
    }
    
    public Mst_ShadeCardsPage pressReset() {
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(resetButton));
        element.click();
        
        return new Mst_ShadeCardsPage(driver);
    }
    
    public Mst_ImportPage pressImport() {
        WebElement element= new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(importButton));
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
    
    public Mst_AddShadeCardPage pressNewShadeCard() {
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(newShadeCardButton));
        element.click();
        
        return new Mst_AddShadeCardPage(driver);
    }
    
    public Mst_EditShadeCardPage pressEdit(int row) {
        By locator = By.cssSelector("#content > div.flexi-grid > table > tbody > tr:nth-child("+row+") > td.actions > a:nth-child(1) > span");
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(locator));
        element.click();
        
        return new Mst_EditShadeCardPage(driver);
    }
    
    public Mst_ShadeCardsPage pressDelete(int row) {
        By locator = By.cssSelector("#content > div.flexi-grid > table > tbody > tr:nth-child("+row+") > td.actions > a:nth-child(3) > span");
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(locator));
        element.click();
        
        Alert alert = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.alertIsPresent());
        alert.accept();
        
        return new Mst_ShadeCardsPage(driver);
    }
    
    public int getRow(String item) {
        if (!checkForRecords()) {
            return -1;
        }
        
        By headerLocator = By.cssSelector("#content > div.flexi-grid > table > tbody > tr:nth-child(1) > th:nth-child(2) > a");
        WebElement header = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.visibilityOfElementLocated(headerLocator));
        AssertJUnit.assertTrue("Shade Cards Page: Shade Card column has moved, update locators",header.getText().equals("Shade Card Name"));
        
        By recordsField = By.cssSelector("#content > div.flexi-grid > dl > dt > span.left");
        int records = getRecordCount(recordsField);
        int tableCount = (records >= 10) ? 10 : records;
        
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
        WebElement shadeCard = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(shadeCardField));
        WebElement shadeCardCode = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(shadeCardCodeField));
        WebElement search = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(searchButton));
        WebElement reset = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(resetButton));
        WebElement importB = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(importButton));
        WebElement export = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(exportButton));
        WebElement newShadeCard = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(newShadeCardButton));
    
        AssertJUnit.assertTrue("Shade Cards Page: Shade Card field not displayed",shadeCard.isDisplayed());
        AssertJUnit.assertTrue("Shade Cards Page: Shade Card Code field not displayed",shadeCardCode.isDisplayed());
        AssertJUnit.assertTrue("Shade Cards Page: Search button not displayed",search.isDisplayed());
        AssertJUnit.assertTrue("Shade Cards Page: Reset button not displayed",reset.isDisplayed());
        AssertJUnit.assertTrue("Shade Cards Page: Import button not displayed",importB.isDisplayed());
        AssertJUnit.assertTrue("Shade Cards Page: Export button not displayed",export.isDisplayed());
        AssertJUnit.assertTrue("Shade Cards Page: New Shade Card button not displayed",newShadeCard.isDisplayed());
    }
    
    public void waitForElement() {
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(shadeCardCodeField));
    }
    
}
