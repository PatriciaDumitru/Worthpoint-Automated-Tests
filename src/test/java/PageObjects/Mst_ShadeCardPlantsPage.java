
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

public class Mst_ShadeCardPlantsPage extends WBA_BasePage {
    
    By plantField = By.id("s2id_autogen1");
    By shadeCardField = By.id("s2id_autogen2");
    By searchButton = By.cssSelector("#FilterIndexForm > div.actions > ul > li:nth-child(1) > input[type=\"submit\"]");
    By resetButton = By.cssSelector("#FilterIndexForm > div.actions > ul > li:nth-child(2) > a");
    By importButton = By.cssSelector("#content > div.actions > ul > li:nth-child(1) > a");
    By exportButton = By.cssSelector("#export-menu > a");
    By newShadeCardPlantButton = By.cssSelector("#content > div.actions > ul > li:nth-child(3) > a");
    
    public Mst_ShadeCardPlantsPage(WebDriver driver) {
        super(driver);
    }
    
    public WebElement getBreadcrumb() {
        return new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.visibilityOfElementLocated(DataItems.breadcrumbLocator));
    }
    
    public Mst_ShadeCardPlantsPage setPlant(String item) {
        CommonTask.setChoiceField(driver, plantField, item);
        return new Mst_ShadeCardPlantsPage(driver);
    }
    
    public Mst_ShadeCardPlantsPage setShadeCard(String item) {
        CommonTask.setChoiceField(driver, shadeCardField, item);
        return new Mst_ShadeCardPlantsPage(driver);
    }
    
    public Mst_ShadeCardPlantsPage pressSearch() {
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(searchButton));
        element.click();
        
        return new Mst_ShadeCardPlantsPage(driver);
    }
    
    public Mst_ShadeCardPlantsPage pressReset() {
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(resetButton));
        element.click();
        
        return new Mst_ShadeCardPlantsPage(driver);
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
    
    public Mst_AddShadeCardPlantPage pressNewShadeCardPlant() {
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(newShadeCardPlantButton));
        element.click();
        
        return new Mst_AddShadeCardPlantPage(driver);
    }
    
    public Mst_EditShadeCardPlantPage pressEdit(int row) {
        By locator = By.cssSelector("#content > div.flexi-grid > table > tbody > tr:nth-child("+row+") > td.actions > a:nth-child(1) > span");
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(locator));
        element.click();
        
        return new Mst_EditShadeCardPlantPage(driver);
    }
    
    public Mst_ShadeCardPlantsPage pressDelete(int row) {
        By locator = By.cssSelector("#content > div.flexi-grid > table > tbody > tr:nth-child("+row+") > td.actions > a:nth-child(3) > span");
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(locator));
        element.click();
        
        Alert alert = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.alertIsPresent());
        alert.accept();
        
        return new Mst_ShadeCardPlantsPage(driver);
    }
    
    public int getRow(String plantName,String shadeCard) {
        if (!checkForRecords()) {
            return -1;
        }
        
        By headerLocator = By.cssSelector("#content > div.flexi-grid > table > tbody > tr:nth-child(1) > th:nth-child(2) > a");
        WebElement header = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(headerLocator));
        AssertJUnit.assertTrue("Shade Card Plants Page: Shade Card Code column has moved, update locators",header.getText().equals("Shade Card Code"));
        
        By recordsField = By.cssSelector("#content > div.flexi-grid > dl > dt > span.left");
        int count = getRecordCount(recordsField);
        
        int tableCount = (count >= 10) ? 10 : count;
        
        for (int i = 2; i < (tableCount+2); i++) {
            By plantLocator = By.cssSelector("#content > div.flexi-grid > table > tbody > tr:nth-child("+i+") > td:nth-child(3)");
            WebElement plantCell = driver.findElement(plantLocator);
            
            if (plantCell.getText().equals(plantName)) {
                
                By shadeCardLocator = By.cssSelector("#content > div.flexi-grid > table > tbody > tr:nth-child("+i+") > td:nth-child(2)");
                WebElement cardCell = driver.findElement(shadeCardLocator);
                
                if (cardCell.getText().equals(shadeCard)) {
                    return i;
                }
            }  
        }
        
        return -1;
        
    }
    
    public void checkFields() {
        WebElement plant = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(plantField));
        WebElement shadeCard = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(shadeCardField));
        WebElement search = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(searchButton));
        WebElement reset = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(resetButton));
        WebElement importB = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(importButton));
        WebElement export = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(importButton));
        WebElement newShadeCardPlant = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(newShadeCardPlantButton));
        
        AssertJUnit.assertTrue("Shade Card Plants Page: Plant name field not displayed",plant.isDisplayed());
        AssertJUnit.assertTrue("Shade Card Plants Page: Shade Card field not displayed",shadeCard.isDisplayed());
        AssertJUnit.assertTrue("Shade Card Plants Page: Search button not displayed",search.isDisplayed());
        AssertJUnit.assertTrue("Shade Card Plants Page: Reset button not displayed",reset.isDisplayed());
        AssertJUnit.assertTrue("Shade Card Plants Page: Import button not displayed",importB.isDisplayed());
        AssertJUnit.assertTrue("Shade Card Plants Page: Export button not displayed",export.isDisplayed());
        AssertJUnit.assertTrue("Shade Card Plants Page: New Shade Card Plant button not displayed",newShadeCardPlant.isDisplayed());
    }
    
    public void waitForElement() {
        WebElement plant = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(plantField));
    }
    
}
