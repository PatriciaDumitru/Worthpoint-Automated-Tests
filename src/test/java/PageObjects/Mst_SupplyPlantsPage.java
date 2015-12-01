
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

public class Mst_SupplyPlantsPage extends WBA_BasePage {
    
    By deliveryPlantField = By.id("s2id_filterDeliveryPlantId");
    By supplyPlantField = By.id("s2id_filterSupplyPlantId");
    By articleField = By.id("s2id_filterArticleId");
    By brandField = By.id("s2id_filterBrandId");
    By searchButton = By.cssSelector("#FilterIndexForm > div.actions > ul > li:nth-child(1) > input[type=\"submit\"]");
    By resetButton = By.cssSelector("#FilterIndexForm > div.actions > ul > li:nth-child(2) > a");
    By importButton = By.cssSelector("#content > div.actions > ul > li:nth-child(1) > a");
    By exportButton = By.cssSelector("#export-menu > a");
    By newSupplyPlantButton = By.cssSelector("#content > div.actions > ul > li:nth-child(3) > a");
    
    public Mst_SupplyPlantsPage(WebDriver driver) {
        super(driver);
    }
    
    public WebElement getBreadcrumb() {
        return new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(DataItems.breadcrumbLocator));
    }
    
    public Mst_SupplyPlantsPage setDeliveryPlant(String item) {
        CommonTask.setSearchField(driver, deliveryPlantField, item);
        return new Mst_SupplyPlantsPage(driver);
    }
    
    public Mst_SupplyPlantsPage setSupplyPlant(String item) {
        CommonTask.setSearchField(driver, supplyPlantField, item);
        return new Mst_SupplyPlantsPage(driver);
    }
    
    public Mst_SupplyPlantsPage setArticle(String item) {
        CommonTask.setSearchField(driver, articleField, item);
        return new Mst_SupplyPlantsPage(driver);
    }
    
    public Mst_SupplyPlantsPage setBrand(String item) {
        CommonTask.setSearchField(driver, brandField, item);
        return new Mst_SupplyPlantsPage(driver);
    }
    
    public Mst_SupplyPlantsPage pressSearch() {
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(searchButton));
        element.click();
        
        return new Mst_SupplyPlantsPage(driver);
    }
    
    public Mst_SupplyPlantsPage pressReset() {
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(resetButton));
        element.click();
        
        return new Mst_SupplyPlantsPage(driver);
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
    
    public Mst_AddSupplyPlantPage pressNewSupplyPlant() {
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(newSupplyPlantButton));
        element.click();
        
        return new Mst_AddSupplyPlantPage(driver);
    }
    
    public Mst_EditSupplyPlantPage pressEdit(int row) {
        By edit = By.cssSelector("#content > div.flexi-grid > table > tbody > tr:nth-child("+row+") > td.actions > a:nth-child(1) > span");
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(edit));
        element.click();
        
        return new Mst_EditSupplyPlantPage(driver);
    }
    
    public Mst_SupplyPlantsPage pressDelete(int row) {
        By delete = By.cssSelector("#content > div.flexi-grid > table > tbody > tr:nth-child("+row+") > td.actions > a:nth-child(3) > span");
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(delete));
        element.click();
        
        Alert alert = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.alertIsPresent());
        alert.accept();
        
        return new Mst_SupplyPlantsPage(driver);
    }
    
    public int getRow(String delPlant, String brand, String ticket, String supPlant) {
        //Check that records are present
        if (!checkForRecords()) {
            return -1;
        }
        
        //Check if columns have moved
        By headerLocator = By.cssSelector("#content > div.flexi-grid > table > tbody > tr:nth-child(1) > th:nth-child(7) > a");
        WebElement header = driver.findElement(headerLocator);
        AssertJUnit.assertTrue("Supply Plants Page: Supply Plant column has moved, update locators",header.getText().equals("Supply Plant"));
        
        //Check how many records are available in the table
        By recordField = By.cssSelector("#content > div.flexi-grid > dl > dt > span.left");
        int count = getRecordCount(recordField);
        //If 10 or more records found, use value of 10 
        int tableCount = (count >= 10) ? 10 : count;
        
        for (int i = 2; i < (tableCount+2); i++) {
            //Check Delivery Plant, Brand, Ticket, and Supply Plant for each row until found
            By locator1 = By.cssSelector("#content > div.flexi-grid > table > tbody > tr:nth-child("+i+") > td:nth-child(2)");
            WebElement cell1 = driver.findElement(locator1);
            
            if (cell1.getText().equals(delPlant)) {
                
                By locator2 = By.cssSelector("#content > div.flexi-grid > table > tbody > tr:nth-child("+i+") > td:nth-child(4)");
                WebElement cell2 = driver.findElement(locator2);
                
                if (cell2.getText().equals(brand)) {
                    
                    By locator3 = By.cssSelector("#content > div.flexi-grid > table > tbody > tr:nth-child("+i+") > td:nth-child(5)");
                    WebElement cell3 = driver.findElement(locator3);
                    
                    if (cell3.getText().equals(ticket)) {
                        
                        By locator4 = By.cssSelector("#content > div.flexi-grid > table > tbody > tr:nth-child("+i+") > td:nth-child(7)");
                        WebElement cell4 = driver.findElement(locator4);
                        
                        if (cell4.getText().equals(supPlant)) {
                            return i;
                        }
                        
                    }
                    
                }
                
            }
            
        }
        return -1;
    }
    
    public void checkFields() {
        WebElement delPlants = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(deliveryPlantField));
        WebElement supplyPlants = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(supplyPlantField));
        WebElement article = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(articleField));
        WebElement search = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(searchButton));
        WebElement reset = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(resetButton));
        WebElement importBtn = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(importButton));
        WebElement export = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(exportButton));
        WebElement newSupplyPlant = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(newSupplyPlantButton));
        
        AssertJUnit.assertTrue("Supply Plants Page: Delivery Plant field not displayed",delPlants.isDisplayed());
        AssertJUnit.assertTrue("Supply Plants Page: Supply Plant field not displayed",supplyPlants.isDisplayed());
        AssertJUnit.assertTrue("Supply Plants Page: Article field not displayed",article.isDisplayed());
        AssertJUnit.assertTrue("Supply Plants Page: Search button not displayed",search.isDisplayed());
        AssertJUnit.assertTrue("Supply Plants Page: Reset button not displayed",reset.isDisplayed());
        AssertJUnit.assertTrue("Supply Plants Page: Import button not displayed",importBtn.isDisplayed());
        AssertJUnit.assertTrue("Supply Plants Page: Export button not displayed",export.isDisplayed());
        AssertJUnit.assertTrue("Supply Plants Page: New Supply Plant button not displayed",newSupplyPlant.isDisplayed());
    }
    
    public void waitForElement() {
        WebElement newSupplyPlant = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(newSupplyPlantButton));
    }
    
}
