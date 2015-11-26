
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

public class Mst_LengthOffersPage extends WBA_BasePage {
    
    By plantNameField = By.id("s2id_filterPlantId");
    By brandField = By.id("s2id_filterBrandId");
    By ticketField = By.id("s2id_filterTicketId");
    By searchButton = By.cssSelector("#FilterIndexForm > div.actions > ul > li:nth-child(1) > input[type=\"submit\"]");
    By resetButton = By.cssSelector("#FilterIndexForm > div.actions > ul > li:nth-child(2) > a");
    By importButton = By.cssSelector("#content > div.actions > ul > li:nth-child(1) > a");
    By exportButton = By.cssSelector("#export-menu > a");
    By newLengthOfferButton = By.cssSelector("#content > div.actions > ul > li:nth-child(3) > a");
    
    public Mst_LengthOffersPage(WebDriver driver) {
        super(driver);
    }
    
    public WebElement getBreadcrumb() {
        return new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.visibilityOfElementLocated(DataItems.breadcrumbLocator));
    }
    
    public Mst_LengthOffersPage setPlantName(String item) {
        CommonTask.setSearchField(driver, plantNameField, item);
        return new Mst_LengthOffersPage(driver);
    }
    
    public Mst_LengthOffersPage setBrand(String item) {
        CommonTask.setSearchField(driver, brandField, item);
        return new Mst_LengthOffersPage(driver);
    }
    
    public Mst_LengthOffersPage setTicket(String item) {
        CommonTask.setSearchField(driver, ticketField, item);
        return new Mst_LengthOffersPage(driver);
    }
    
    public Mst_LengthOffersPage pressSearch() {
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(searchButton));
        element.click();
        
        return new Mst_LengthOffersPage(driver);
    }
    
    public Mst_LengthOffersPage pressReset() {
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(resetButton));
        element.click();
        
        return new Mst_LengthOffersPage(driver);
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
    
    public Mst_AddLengthOfferPage pressNewLengthOffer() {
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(newLengthOfferButton));
        element.click();
        
        return new Mst_AddLengthOfferPage(driver);
    }
    
    public Mst_EditLengthOfferPage pressEdit(int row) {
        By editButton = By.cssSelector("#content > div.flexi-grid > table > tbody > tr:nth-child("+row+") > td.actions > a:nth-child(1) > span");
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(editButton));
        element.click();
        
        return new Mst_EditLengthOfferPage(driver);
    }
    
    public Mst_LengthOffersPage pressDelete(int row) {
        By deleteButton = By.cssSelector("#content > div.flexi-grid > table > tbody > tr:nth-child("+row+") > td.actions > a:nth-child(3) > span");
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(deleteButton));
        element.click();
        
        Alert alert = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.alertIsPresent());
        alert.accept();
        
        return new Mst_LengthOffersPage(driver);
    }
    
    public int getRow(String brand, String ticket, String cop) {
        if (!checkForRecords()) {
            //Return -1 if no records are found
            return -1;
        }
        
        //Check the Sales Organisation field is in the same position
        By headerLocator = By.cssSelector("#content > div.flexi-grid > table > tbody > tr:nth-child(1) > th:nth-child(3) > a");
        WebElement header = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.visibilityOfElementLocated(headerLocator));
        AssertJUnit.assertTrue("Length Offers Page: Brand column has moved, update locators",header.getText().equals("Brand"));
        
        //Find the number of records displayed
        By recordsField = By.cssSelector("#content > div.flexi-grid > dl > dt > span.left");
        int count = getRecordCount(recordsField);
        int tableCount = (count >= 10) ? 10 : count;
        
        //For each row, check if the Brand matches. If it does, check if the ticket matches, then the cop value 
        for (int i = 2; i < (tableCount+2); i++) {
                
                By brandLocator = By.cssSelector("#content > div.flexi-grid > table > tbody > tr:nth-child("+i+") > td:nth-child(3)");
                WebElement brandCell = driver.findElement(brandLocator);
                
                if (brandCell.getText().equals(brand)) {
                    
                    By ticketLocator = By.cssSelector("#content > div.flexi-grid > table > tbody > tr:nth-child("+i+") > td:nth-child(4)");
                    WebElement ticketCell = driver.findElement(ticketLocator);
                    
                    if (ticketCell.getText().equals(ticket)) {
                        
                        By copLocator = By.cssSelector("#content > div.flexi-grid > table > tbody > tr:nth-child("+i+") > td:nth-child(5)");
                        WebElement copCell = driver.findElement(copLocator);
                        
                        if (copCell.getText().equals(cop)) {
                            return i;
                        }
                        
                    }
                    
                }
                
            }       
        return -1;
    }
    
    public void checkFields() {
        WebElement plant = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(plantNameField));
        WebElement brand = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(brandField));
        WebElement ticket = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(ticketField));
        WebElement search = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(searchButton));
        WebElement reset = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(resetButton));
        WebElement importB = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(importButton));
        WebElement export = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(exportButton));
        WebElement newLengthOffer = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(newLengthOfferButton));
        
        AssertJUnit.assertTrue("Purpose Types Page: Plant name field not displayed",plant.isDisplayed());
        AssertJUnit.assertTrue("Purpose Types Page: Brand field not displayed",brand.isDisplayed());
        AssertJUnit.assertTrue("Purpose Types Page: Ticket button not displayed",ticket.isDisplayed());
        AssertJUnit.assertTrue("Purpose Types Page: Search button not displayed",search.isDisplayed());
        AssertJUnit.assertTrue("Purpose Types Page: Reset button not displayed",reset.isDisplayed());
        AssertJUnit.assertTrue("Purpose Types Page: Import button not displayed",importB.isDisplayed());
        AssertJUnit.assertTrue("Purpose Types Page: Export button not displayed",export.isDisplayed());
        AssertJUnit.assertTrue("Purpose Types Page: New Purpose Type button not displayed",newLengthOffer.isDisplayed());
    }
    
    public void waitForElement() {
        WebElement plantName = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(plantNameField));
    }
    
}
