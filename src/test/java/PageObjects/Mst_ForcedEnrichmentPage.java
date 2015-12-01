
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

public class Mst_ForcedEnrichmentPage extends WBA_BasePage {
    
    By salesOrgField = By.id("s2id_filterSalesOrgId");
    By custNameField = By.id("s2id_filterCustomerId");
    By brandField = By.id("s2id_filterBrandId");
    By ticketField = By.id("s2id_filterTicketId");
    By mumTypeField = By.id("s2id_filterMumTypeId");
    By searchButton = By.cssSelector("#FilterIndexForm > div.actions > ul > li:nth-child(1) > input[type=\"submit\"]");
    By resetButton = By.cssSelector("#FilterIndexForm > div.actions > ul > li:nth-child(2) > a");
    By importButton = By.cssSelector("#content > div.actions > ul > li:nth-child(1) > a");
    By exportButton = By.cssSelector("#export-menu > a");
    By newProductButton = By.cssSelector("#content > div.actions > ul > li:nth-child(3) > a");
    
    public Mst_ForcedEnrichmentPage(WebDriver driver) {
        super(driver);
    }
    
    public WebElement getBreadcrumb() {
        return new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(DataItems.breadcrumbLocator));
    }
    
    public Mst_ForcedEnrichmentPage setSalesOrg(String item) {
        CommonTask.setSearchField(driver, salesOrgField, item);
        return new Mst_ForcedEnrichmentPage(driver);
    }
    
    public Mst_ForcedEnrichmentPage setCustomerName(String item) {
        CommonTask.setSearchField(driver, custNameField, item);
        return new Mst_ForcedEnrichmentPage(driver);
    }
    
    public Mst_ForcedEnrichmentPage setBrand(String item) {
        CommonTask.setSearchField(driver, brandField, item);
        return new Mst_ForcedEnrichmentPage(driver);
    }
    
    public Mst_ForcedEnrichmentPage setTicket(String item) {
        CommonTask.setSearchField(driver, ticketField, item);
        return new Mst_ForcedEnrichmentPage(driver);
    }
    
    public Mst_ForcedEnrichmentPage setMUMType(String item) {
        CommonTask.setSearchField(driver, mumTypeField, item);
        return new Mst_ForcedEnrichmentPage(driver);
    }
    
    public Mst_ForcedEnrichmentPage pressSearch() {
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(searchButton));
        element.click();
        
        return new Mst_ForcedEnrichmentPage(driver);
    }
    
    public Mst_ForcedEnrichmentPage pressReset() {
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(resetButton));
        element.click();
        
        return new Mst_ForcedEnrichmentPage(driver);
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
    
    public Mst_AddForcedEnrichmentPage pressNewProduct() {
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(newProductButton));
        element.click();
        
        return new Mst_AddForcedEnrichmentPage(driver);
    }
    
    public Mst_EditForcedEnrichmentPage pressEdit(int row) {
        By editButton = By.cssSelector("#content > div.flexi-grid > table > tbody > tr:nth-child("+row+") > td.actions > a:nth-child(1) > span");
        WebElement btn = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(editButton));
        btn.click();
        
        return new Mst_EditForcedEnrichmentPage(driver);
    }
    
    public Mst_ForcedEnrichmentPage pressDelete(int row) {
        By deleteButton = By.cssSelector("#content > div.flexi-grid > table > tbody > tr:nth-child("+row+") > td.actions > a:nth-child(3) > span");
        WebElement btn = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(deleteButton));
        btn.click();
        
        Alert alert = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.alertIsPresent());
        alert.accept();
        
        return new Mst_ForcedEnrichmentPage(driver);
    }
    
    public int getRow(String custName, String brand, String ticket) {
        
        if (!checkForRecords()) {
            return -1;
        }
        
        By custNameHeader = By.cssSelector("#content > div.flexi-grid > table > tbody > tr:nth-child(1) > th:nth-child(3)");
        WebElement header = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(custNameHeader));
        AssertJUnit.assertTrue("Forced Enrichment Products Page: Customer Name field has moved, update locators",header.getText().equals("Customer Name"));
        
        By recordField = By.cssSelector("#content > div.flexi-grid > dl > dt > span.left");
        int count = getRecordCount(recordField);
        int tableCount = (count >= 10) ? 10 : count;
        
        for (int i = 2; i < (tableCount+2); i++) {
            
            By custNameLocator = By.cssSelector("#content > div.flexi-grid > table > tbody > tr:nth-child("+i+") > td:nth-child(3)");
            WebElement custNameCell = driver.findElement(custNameLocator);
            if (custNameCell.getText().equals(custName)) {
                
                By brandLocator = By.cssSelector("#content > div.flexi-grid > table > tbody > tr:nth-child("+i+") > td:nth-child(4)");
                WebElement brandCell = driver.findElement(brandLocator);
                if (brandCell.getText().equals(brand)) {
                    
                    By ticketLocator = By.cssSelector("#content > div.flexi-grid > table > tbody > tr:nth-child("+i+") > td:nth-child(5)");
                    WebElement ticketCell = driver.findElement(ticketLocator);
                    if (ticketCell.getText().equals(ticket)) {
                        return i;
                    }
                    
                }
                
            }
        }
        return -1;
    }
    
    public void checkFields() {
        WebElement salesOrg = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(salesOrgField));
        WebElement custName = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(custNameField));
        WebElement brand = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(brandField));
        WebElement ticket = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(ticketField));
        WebElement mumType = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(mumTypeField));
        WebElement search = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(searchButton));
        WebElement reset = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(resetButton));
        WebElement importB = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(importButton));
        WebElement export = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(exportButton));
        WebElement newProduct = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(newProductButton));
    
        AssertJUnit.assertTrue("Forced Enrichment Products Page: Sales Org field not displayed",salesOrg.isDisplayed());
        AssertJUnit.assertTrue("Forced Enrichment Products Page: Customer Name field not displayed",custName.isDisplayed());
        AssertJUnit.assertTrue("Forced Enrichment Products Page: Brand field not displayed",brand.isDisplayed());
        AssertJUnit.assertTrue("Forced Enrichment Products Page: Ticket field not displayed",ticket.isDisplayed());
        AssertJUnit.assertTrue("Forced Enrichment Products Page: MUM Type field not displayed",mumType.isDisplayed());
        AssertJUnit.assertTrue("Forced Enrichment Products Page: Search button not displayed",search.isDisplayed());
        AssertJUnit.assertTrue("Forced Enrichment Products Page: Reset button not displayed",reset.isDisplayed());
        AssertJUnit.assertTrue("Forced Enrichment Products Page: Import button not displayed",importB.isDisplayed());
        AssertJUnit.assertTrue("Forced Enrichment Products Page: Export button not displayed",export.isDisplayed());
        AssertJUnit.assertTrue("Forced Enrichment Products Page: New FE Product button not displayed",newProduct.isDisplayed());
    }
    
    public void waitForElement() {
        WebElement salesOrg = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(salesOrgField));
    }
    
}
