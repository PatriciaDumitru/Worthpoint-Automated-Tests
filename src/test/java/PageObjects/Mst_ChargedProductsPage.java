
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

public class Mst_ChargedProductsPage extends WBA_BasePage {
    
    By salesOrgField = By.id("s2id_filterSalesOrgId");
    By custNameField = By.id("s2id_filterCustomerId");
    By brandField = By.id("s2id_filterBrandId");
    By ticketField = By.id("s2id_filterTicketId");
    By mumTypeField = By.id("s2id_filterMumTypeId");
    By searchButton = By.cssSelector("#FilterIndexForm > div.actions > ul > li:nth-child(1) > input[type=\"submit\"]");
    By resetButton = By.cssSelector("#FilterIndexForm > div.actions > ul > li:nth-child(2) > a");
    By importButton = By.cssSelector("#content > div.actions > ul > li:nth-child(1) > a");
    By exportButton = By.cssSelector("#export-menu > a");
    By newChargedProductButton = By.cssSelector("#content > div.actions > ul > li:nth-child(3) > a");
    
    public Mst_ChargedProductsPage(WebDriver driver) {
        super(driver);
    }
    
    public WebElement getBreadcrumb() {
        return new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.visibilityOfElementLocated(DataItems.breadcrumbLocator));
    }
    
    public Mst_ChargedProductsPage setSalesOrg(String item) {
        CommonTask.setSearchField(driver, salesOrgField, item);
        return new Mst_ChargedProductsPage(driver);
    }
    
    public Mst_ChargedProductsPage setCustomerName(String item) {
        CommonTask.setSearchField(driver, custNameField, item);
        return new Mst_ChargedProductsPage(driver);
    }
    
    public Mst_ChargedProductsPage setBrand(String item) {
        CommonTask.setSearchField(driver, brandField, item);
        return new Mst_ChargedProductsPage(driver);
    }
    
    public Mst_ChargedProductsPage setTicket(String item) {
        CommonTask.setSearchField(driver, ticketField, item);
        return new Mst_ChargedProductsPage(driver);
    }
    
    public Mst_ChargedProductsPage setMUMType(String item) {
        CommonTask.setSearchField(driver, mumTypeField, item);
        return new Mst_ChargedProductsPage(driver);
    }
    
    public Mst_ChargedProductsPage pressSearch() {
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(searchButton));
        element.click();
        
        return new Mst_ChargedProductsPage(driver);
    }
    
    public Mst_ChargedProductsPage pressReset() {
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(resetButton));
        element.click();
        
        return new Mst_ChargedProductsPage(driver);
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
    
    public Mst_AddChargedProductPage pressNewChargedProduct() {
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(newChargedProductButton));
        element.click();
        
        return new Mst_AddChargedProductPage(driver);
    }
    
    public Mst_EditChargedProductPage pressEdit(int row) {
        By editButton = By.cssSelector("#content > div.flexi-grid > table > tbody > tr:nth-child("+row+") > td.actions > a:nth-child(1) > span");
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(editButton));
        element.click();
        
        return new Mst_EditChargedProductPage(driver);
    }
    
    public Mst_ChargedProductsPage pressDelete(int row) {
        By deleteButton = By.cssSelector("#content > div.flexi-grid > table > tbody > tr:nth-child("+row+") > td.actions > a:nth-child(3) > span");
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(deleteButton));
        element.click();
        
        Alert alert = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.alertIsPresent());
        alert.accept();
        
        return new Mst_ChargedProductsPage(driver);
    }
    
    public int getRow(String salesOrg, String customer, String brand, String quantity) {
        if (!checkForRecords()) {
            //Return -1 if no records are found
            return -1;
        }
        
        //Check the Sales Organisation field is in the same position
        By headerLocator = By.cssSelector("#content > div.flexi-grid > table > tbody > tr:nth-child(1) > th:nth-child(2) > a");
        WebElement header = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.visibilityOfElementLocated(headerLocator));
        AssertJUnit.assertTrue("Charged Products Page: Sales Organisation column has moved, update locators",header.getText().equals("Sales Organisation"));
        
        //Find the number of records displayed
        By recordsField = By.cssSelector("#content > div.flexi-grid > dl > dt > span.left");
        int count = getRecordCount(recordsField);
        int tableCount = (count >= 10) ? 10 : count;
        
        //For each row, check if the sales org matches. If it does, check if the plant matches, then the brand, and then the ticket. 
        for (int i = 2; i < (tableCount+2); i++) {
            By locator = By.cssSelector("#content > div.flexi-grid > table > tbody > tr:nth-child("+i+") > td:nth-child(2)");
            WebElement cell = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.visibilityOfElementLocated(locator));
            
            if (cell.getText().equals(salesOrg)) {
                
                By custNameLocator = By.cssSelector("#content > div.flexi-grid > table > tbody > tr:nth-child("+i+") > td:nth-child(3)");
                WebElement custNameCell = driver.findElement(custNameLocator);
                
                if (custNameCell.getText().equals(customer)) {
                    
                    By brandLocator = By.cssSelector("#content > div.flexi-grid > table > tbody > tr:nth-child("+i+") > td:nth-child(4)");
                    WebElement brandCell = driver.findElement(brandLocator);
                    
                    if (brandCell.getText().equals(brand)) {
                        
                        By qtyLocator = By.cssSelector("#content > div.flexi-grid > table > tbody > tr:nth-child("+i+") > td:nth-child(7)");
                        WebElement qtyCell = driver.findElement(qtyLocator);
                        System.out.println(qtyCell.getText());
                        if (qtyCell.getText().equals(quantity)) {
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
        WebElement brand = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(brandField));
        WebElement ticket = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(ticketField));
        WebElement search = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(searchButton));
        WebElement reset = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(resetButton));
        WebElement importB = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(importButton));
        WebElement export = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(exportButton));
        WebElement newChargedProduct = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(newChargedProductButton));
        
        AssertJUnit.assertTrue("Purpose Types Page: Sales Org field not displayed",salesOrg.isDisplayed());
        AssertJUnit.assertTrue("Purpose Types Page: Brand field not displayed",brand.isDisplayed());
        AssertJUnit.assertTrue("Purpose Types Page: Ticket button not displayed",ticket.isDisplayed());
        AssertJUnit.assertTrue("Purpose Types Page: Search button not displayed",search.isDisplayed());
        AssertJUnit.assertTrue("Purpose Types Page: Reset button not displayed",reset.isDisplayed());
        AssertJUnit.assertTrue("Purpose Types Page: Import button not displayed",importB.isDisplayed());
        AssertJUnit.assertTrue("Purpose Types Page: Export button not displayed",export.isDisplayed());
        AssertJUnit.assertTrue("Purpose Types Page: New Purpose Type button not displayed",newChargedProduct.isDisplayed());
    }
    
    public void waitForElement() {
        WebElement salesOrg = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(salesOrgField));
    }

    //Deleting old entries
    public void deleteCustChargedProducts(){
        int nrOfEntry = driver.findElements(By.xpath("//*[@id=\"content\"]/div[2]/table/tbody/tr")).size();
        System.out.println(nrOfEntry - 1 +" Test Charged Products found ");

        for(int i = nrOfEntry;i > 1; i--)
        {
            pressDelete(2);
            setSalesOrg(DataItems.salesOrgID51);
            setCustomerName(DataItems.custDetails[0]);
            setBrand("TEST");
            pressSearch();
            waitForElement();
        }
        System.out.println("Charged products cleared");
    }
}
