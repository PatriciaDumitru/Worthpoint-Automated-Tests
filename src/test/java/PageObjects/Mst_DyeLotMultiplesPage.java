
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

public class Mst_DyeLotMultiplesPage extends WBA_BasePage {
    
    By salesOrgField = By.id("s2id_filterSalesOrgId");
    By articleField = By.id("s2id_filterArticleId");
    By brandField = By.id("s2id_filterBrandId");
    By ticketField = By.id("s2id_filterTicketId");
    By lengthField = By.id("s2id_filterLengthId");
    By finishField = By.id("s2id_filterFinishId");
    By searchButton = By.cssSelector("#FilterIndexForm > div.actions > ul > li:nth-child(1) > input[type=\"submit\"]");
    By resetButton = By.cssSelector("#FilterIndexForm > div.actions > ul > li:nth-child(2) > a");
    By importButton = By.cssSelector("#content > div.actions > ul > li:nth-child(1) > a");
    By exportButton = By.cssSelector("#export-menu > a");
    By newDLMButton = By.cssSelector("#content > div.actions > ul > li:nth-child(3) > a");
    
    public Mst_DyeLotMultiplesPage(WebDriver driver) {
        super(driver);
    }
    
    public WebElement getBreadcrumb() {
        return new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.visibilityOfElementLocated(DataItems.breadcrumbLocator));
    }
    
    public Mst_DyeLotMultiplesPage setSalesOrg(String item) {
        CommonTask.setSearchField(driver, salesOrgField, item);
        return new Mst_DyeLotMultiplesPage(driver);
    }
    
    public Mst_DyeLotMultiplesPage setArticle(String item) {
        CommonTask.setSearchField(driver, articleField, item);
        return new Mst_DyeLotMultiplesPage(driver);
    }
    
    public Mst_DyeLotMultiplesPage setBrand(String item) {
        CommonTask.setSearchField(driver, brandField, item);
        return new Mst_DyeLotMultiplesPage(driver);
    }
    
    public Mst_DyeLotMultiplesPage setTicket(String item) {
        CommonTask.setSearchField(driver,ticketField, item);
        return new Mst_DyeLotMultiplesPage(driver);
    }
    
    public Mst_DyeLotMultiplesPage setLength(String item) {
        CommonTask.setSearchField(driver, lengthField, item);
        return new Mst_DyeLotMultiplesPage(driver);
    }
    
    public Mst_DyeLotMultiplesPage setFinish(String item) {
        CommonTask.setSearchField(driver, finishField, item);
        return new Mst_DyeLotMultiplesPage(driver);
    }
    
    public Mst_DyeLotMultiplesPage pressSearch() {
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(searchButton));
        element.click();
        
        return new Mst_DyeLotMultiplesPage(driver);
    }
    
    public Mst_DyeLotMultiplesPage pressReset() {
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(resetButton));
        element.click();
        
        return new Mst_DyeLotMultiplesPage(driver);
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
    
    public Mst_AddDLMPage pressNewDLM() {
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(newDLMButton));
        element.click();
        
        return new Mst_AddDLMPage(driver);
    }
    
    public Mst_EditDLMPage pressEdit(int row) {
        By locator = By.cssSelector("#content > div.flexi-grid > table > tbody > tr:nth-child("+row+") > td.actions > a:nth-child(1) > span");
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(locator));
        element.click();
        
        return new Mst_EditDLMPage(driver);
    }
    
    public Mst_DyeLotMultiplesPage pressDelete(int row) {
        By locator = By.cssSelector("#content > div.flexi-grid > table > tbody > tr:nth-child("+row+") > td.actions > a:nth-child(3) > span");
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(locator));
        element.click();
        
        Alert alert = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.alertIsPresent());
        alert.accept();
        
        return new Mst_DyeLotMultiplesPage(driver);
    }
    
    public int getRow(String salesOrg, String brand, String ticket, String level) {
        if (!checkForRecords()) {
            return -1;
        }
        
        By headerLocator = By.cssSelector("#content > div.flexi-grid > table > tbody > tr:nth-child(1) > th:nth-child(4) > a");
        WebElement header = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.visibilityOfElementLocated(headerLocator));
        AssertJUnit.assertTrue("Dye Lot Multiples Page: Brand column has moved, update locators",header.getText().equals("Brand"));
        
        By recordsField = By.cssSelector("#content > div.flexi-grid > dl > dt > span.left");
        int count = getRecordCount(recordsField);
        int tableCount = (count >= 10) ? 10 : count;
        
        for (int i = 2; i < (tableCount+2); i++) {
            
            By locator1 = By.cssSelector("#content > div.flexi-grid > table > tbody > tr:nth-child("+i+") > td:nth-child(2)");
            WebElement cell1 = driver.findElement(locator1);
            
            if (cell1.getText().trim().equals(salesOrg)) {
                
                By locator2 = By.cssSelector("#content > div.flexi-grid > table > tbody > tr:nth-child("+i+") > td:nth-child(4)");
                WebElement cell2 = driver.findElement(locator2);
                
                if (cell2.getText().trim().equals(brand)) {
                    
                    By locator3 = By.cssSelector("#content > div.flexi-grid > table > tbody > tr:nth-child("+i+") > td:nth-child(5)");
                    WebElement cell3 = driver.findElement(locator3);
                    
                    if (cell3.getText().trim().equals(ticket)) {
                        
                        By locator4 = By.cssSelector("#content > div.flexi-grid > table > tbody > tr:nth-child("+i+") > td:nth-child(8)");
                        WebElement cell4 = driver.findElement(locator4);
                        
                        if (cell4.getText().trim().equals(level)) {
                            return i;
                        }
                        
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
        WebElement ticket = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(ticketField));
        WebElement length = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(lengthField));
        WebElement finish = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(finishField));
        WebElement search = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(searchButton));
        WebElement reset = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(resetButton));
    
        AssertJUnit.assertTrue("Dye Lot Multiples Page: Sales Org field not displayed",salesOrg.isDisplayed());
        AssertJUnit.assertTrue("Dye Lot Multiples Page: Article field not displayed",article.isDisplayed());
        AssertJUnit.assertTrue("Dye Lot Multiples Page: Brand field not displayed",brand.isDisplayed());
        AssertJUnit.assertTrue("Dye Lot Multiples Page: Ticket field not displayed",ticket.isDisplayed());
        AssertJUnit.assertTrue("Dye Lot Multiples Page: Length field not displayed",length.isDisplayed());
        AssertJUnit.assertTrue("Dye Lot Multiples Page: Finish field not displayed",finish.isDisplayed());
        AssertJUnit.assertTrue("Dye Lot Multiples Page: Search field not displayed",search.isDisplayed());
        AssertJUnit.assertTrue("Dye Lot Multiples Page: Reset field not displayed",reset.isDisplayed());
    }
    
    public void waitForElement() {
        WebElement salesOrg = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(salesOrgField));
    }
    
}
