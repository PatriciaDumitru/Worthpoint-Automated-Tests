
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

public class Mst_WarehouseStocksPage extends WBA_BasePage {
    
    By salesOrgField = By.id("s2id_filterSalesOrgId");
    By articleField = By.id("s2id_filterArticleId");
    By brandField = By.id("s2id_filterBrandId");
    By plantNameField = By.id("s2id_filterPlantId");
    By ticketField = By.id("s2id_filterTicketId");
    By mumTypeField = By.id("s2id_filterMumTypeId");
    By shadeField = By.id("s2id_filterShadeId");
    By searchButton = By.cssSelector("#FilterIndexForm > div.actions > ul > li:nth-child(1) > input[type=\"submit\"]");
    By resetButton = By.cssSelector("#FilterIndexForm > div.actions > ul > li:nth-child(2) > a");
    By importButton = By.cssSelector("#OurStockListForm > div.actions > ul > li:nth-child(1) > a");
    By exportButton = By.cssSelector("#export-menu > a");
    By newOurStockButton = By.cssSelector("#OurStockListForm > div.actions > ul > li:nth-child(3) > a");
    By deleteAllButton = By.cssSelector("#OurStockListForm > div.actions > ul > li:nth-child(4) > input[type=\"submit\"]");
    
    public Mst_WarehouseStocksPage(WebDriver driver) {
        super(driver);
    }
    
    public WebElement getBreadcrumb() {
        return new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.visibilityOfElementLocated(DataItems.breadcrumbLocator));
    }
    
    public Mst_WarehouseStocksPage setSalesOrg(String item) {
        CommonTask.setSearchField(driver,salesOrgField,item);
        return new Mst_WarehouseStocksPage(driver);
    }
    
    public Mst_WarehouseStocksPage setArticle(String item) {
        CommonTask.setSearchField(driver,articleField,item);
        return new Mst_WarehouseStocksPage(driver);
    }
    
    public Mst_WarehouseStocksPage setBrand(String item) {
        CommonTask.setSearchField(driver,brandField,item);
        return new Mst_WarehouseStocksPage(driver);
    }
    
    public Mst_WarehouseStocksPage setPlantName(String item) {
        CommonTask.setSearchField(driver,plantNameField,item);
        return new Mst_WarehouseStocksPage(driver);
    }
    
    public Mst_WarehouseStocksPage setTicket(String item) {
        CommonTask.setSearchField(driver,ticketField,item);
        return new Mst_WarehouseStocksPage(driver);
    }
    
    public Mst_WarehouseStocksPage setMUMType(String item) {
        CommonTask.setSearchField(driver,mumTypeField,item);
        return new Mst_WarehouseStocksPage(driver);
    }
    
    public Mst_WarehouseStocksPage setShade(String item) {
        CommonTask.setSearchField(driver,shadeField,item);
        return new Mst_WarehouseStocksPage(driver);
    }
    
    public Mst_WarehouseStocksPage pressSearch() {
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(searchButton));
        element.click();
        
        return new Mst_WarehouseStocksPage(driver);
    }
    
    public Mst_WarehouseStocksPage pressReset() {
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(resetButton));
        element.click();
        
        return new Mst_WarehouseStocksPage(driver);
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
    
    public Mst_AddOurStockPage pressNewOurStock() {
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(newOurStockButton));
        element.click();
        
        return new Mst_AddOurStockPage(driver);
    }
    
    public Mst_EditOurStockPage pressEdit(int row) {
        By locator = By.cssSelector("#OurStockListForm > div.flexi-grid > table > tbody > tr:nth-child("+row+") > td:nth-child(9) > a:nth-child(1) > span");
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(locator));
        element.click();
        
        return new Mst_EditOurStockPage(driver);
    }
    
    public Mst_WarehouseStocksPage pressDelete(int row) {
        By locator = By.cssSelector("#OurStockListForm > div.flexi-grid > table > tbody > tr:nth-child("+row+") > td:nth-child(9) > a:nth-child(2) > span");
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(locator));
        element.click();
        
        Alert alert = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.alertIsPresent());
        alert.accept();
        
        return new Mst_WarehouseStocksPage(driver);
    }
    
    public int getRow(String salesOrg, String article, String shade) {
        
        if (!checkForRecords()) {
            return -1;
        }
        
        By headerLocator = By.cssSelector("#OurStockListForm > div.flexi-grid > table > tbody > tr:nth-child(1) > th:nth-child(3) > a");
        WebElement header = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(headerLocator));
        AssertJUnit.assertTrue("Our Stocks Page: Article column has moved, update locators",header.getText().equals("Article"));
         
        By recordsField = By.cssSelector("#OurStockListForm > div.flexi-grid > dl > dt > span.left");
        int count = getRecordCount(recordsField);
        int tableCount = (count > 10) ? 10 : count;
        
        for (int i = 2; i < (tableCount+2); i++) {
            
            By locator1 = By.cssSelector("#OurStockListForm > div.flexi-grid > table > tbody > tr:nth-child("+i+") > td:nth-child(2)");
            WebElement cell1 = driver.findElement(locator1);

            if (cell1.getText().trim().equals(salesOrg)) {

                By locator2 = By.cssSelector("#OurStockListForm > div.flexi-grid > table > tbody > tr:nth-child("+i+") > td:nth-child(3)");
                WebElement cell2 = driver.findElement(locator2);
                
                if (cell2.getText().trim().equals(article)) {
                    
                    By locator3 = By.cssSelector("#OurStockListForm > div.flexi-grid > table > tbody > tr:nth-child("+i+") > td:nth-child(8)");
                    WebElement cell3 = driver.findElement(locator3);
                    
                    if (cell3.getText().trim().equals(shade)) {
                        return i;
                    }
                    
                }
                
            }
        }
        return -1;
    }
    
    public void checkFields() {
        WebElement salesOrg = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(salesOrgField));
        WebElement article = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(articleField));
        WebElement brand = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(brandField));
        WebElement plantName = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(plantNameField));
        WebElement ticket = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(ticketField));
        WebElement mumType = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(mumTypeField));
        WebElement shade = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(shadeField));
        WebElement search = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(searchButton));
        WebElement reset = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(resetButton));
        WebElement importB = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(importButton));
        WebElement export = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(exportButton));
    
        AssertJUnit.assertTrue("Warehouse Stocks Page: Sales Org field not displayed as expected",salesOrg.isDisplayed());
        AssertJUnit.assertTrue("Warehouse Stocks Page: Article field not displayed as expected",article.isDisplayed());
        AssertJUnit.assertTrue("Warehouse Stocks Page: Brand field not displayed as expected",brand.isDisplayed());
        AssertJUnit.assertTrue("Warehouse Stocks Page: Plant Name field not displayed as expected",plantName.isDisplayed());
        AssertJUnit.assertTrue("Warehouse Stocks Page: Ticket field not displayed as expected",ticket.isDisplayed());
        AssertJUnit.assertTrue("Warehouse Stocks Page: MUM Type field not displayed as expected",mumType.isDisplayed());
        AssertJUnit.assertTrue("Warehouse Stocks Page: Shade field not displayed as expected",shade.isDisplayed());
        AssertJUnit.assertTrue("Warehouse Stocks Page: Search button not displayed as expected",search.isDisplayed());
        AssertJUnit.assertTrue("Warehouse Stocks Page: Reset button not displayed as expected",reset.isDisplayed());
        AssertJUnit.assertTrue("Warehouse Stocks Page: Import button not displayed as expected",importB.isDisplayed());
        AssertJUnit.assertTrue("Warehouse Stocks Page: Export button not displayed as expected",export.isDisplayed());
    }
    
    public void waitForElement() {
        WebElement plantName = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(plantNameField));
    }
    
}
