
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

public class Mst_CustTicketsPage extends WBA_BasePage {
    
    By salesOrgField = By.id("s2id_filterSalesOrgId");
    By custNameField = By.id("s2id_filterCustomerId");
    By coatsTicketField = By.id("s2id_filterTicketId");
    By custTicketField = By.id("filterCustomerTicket");
    By searchButton = By.cssSelector("#FilterIndexForm > div.actions > ul > li:nth-child(1) > input[type=\"submit\"]");
    By resetButton = By.cssSelector("#FilterIndexForm > div.actions > ul > li:nth-child(2) > a");
    By importButton = By.cssSelector("#content > div.actions > ul > li:nth-child(1) > a");
    By exportButton = By.cssSelector("#export-menu > a");
    By newCustTicketButton = By.cssSelector("#content > div.actions > ul > li:nth-child(3) > a");
    
    public Mst_CustTicketsPage(WebDriver driver) {
        super(driver);
    }
    
    public WebElement getBreadcrumb() {
        return new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.visibilityOfElementLocated(DataItems.breadcrumbLocator));
    }
    
    public Mst_CustTicketsPage setSalesOrg(String item) {
        CommonTask.setSearchField(driver, salesOrgField, item);
        return new Mst_CustTicketsPage(driver);
    }
    
    public Mst_CustTicketsPage setCustomerName(String item) {
        CommonTask.setSearchField(driver, custNameField, item);
        return new Mst_CustTicketsPage(driver);
    }
    
    public Mst_CustTicketsPage pressSearch() {
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(searchButton));
        element.click();
        
        return new Mst_CustTicketsPage(driver);
    }
    
    public Mst_CustTicketsPage pressReset() {
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(resetButton));
        element.click();
        
        return new Mst_CustTicketsPage(driver);
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
    
    public Mst_AddCustTicketPage pressNewCustTicket() {
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(newCustTicketButton));
        element.click();
        
        return new Mst_AddCustTicketPage(driver);
    }
    
    public int getRow(String value) {
        By tktHeader = By.cssSelector("#content > div.flexi-grid > table > tbody > tr:nth-child(1) > th:nth-child(4) > a");
        WebElement header = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.visibilityOfElementLocated(tktHeader));
        
        AssertJUnit.assertTrue("Customer Tickets Page: Customer Ticket column has moved, update locators",header.getText().equals("Customer Ticket"));
        
        By recordField = By.cssSelector("#content > div.flexi-grid > dl > dt > span.left");
        int count = this.getRecordCount(recordField);
        
        for (int i = 2; i < (count+2); i++) {
            By locator = By.cssSelector("#content > div.flexi-grid > table > tbody > tr:nth-child("+i+") > td:nth-child(4)");
            WebElement cell = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.visibilityOfElementLocated(locator));
            
            if (cell.getText().trim().equals(value)) {
                return i;
            }
            
        }     
        return -1;
    }

    public int getNrOfEntry() {
        By brandHeader = By.cssSelector("#content > div.flexi-grid > table > tbody > tr:nth-child(1) > th:nth-child(4) > a");
        WebElement header = new WebDriverWait(driver, DataItems.shortWait).until(ExpectedConditions.visibilityOfElementLocated(brandHeader));

        AssertJUnit.assertTrue("Customer Brands Page: Customer Brand column has moved, update locators", header.getText().equals("Customer Ticket"));

        int nrOfEntry = driver.findElements(By.xpath("//*[@id=\"content\"]/div[2]/table/tbody/tr")).size();

        if (nrOfEntry > 1){
            return 1;
        }
        return -1;
    }
    
    public Mst_EditCustTicketPage pressEdit(int row) {
        By editButton = By.cssSelector("#content > div.flexi-grid > table > tbody > tr:nth-child("+row+") > td.actions > a:nth-child(1) > span");
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(editButton));
        element.click();
        
        return new Mst_EditCustTicketPage(driver);
    }
    
    public Mst_CustTicketsPage pressDelete(int row) {
        By editButton = By.cssSelector("#content > div.flexi-grid > table > tbody > tr:nth-child("+row+") > td.actions > a:nth-child(3) > span");
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(editButton));
        element.click();
        
        Alert alert = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.alertIsPresent());
        alert.accept();
        
        return new Mst_CustTicketsPage(driver);
    }
    
    public void checkFields() {
        //Wait for all elements to be clickable
        WebElement salesOrg = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(salesOrgField));
        WebElement custName = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(custNameField));
        WebElement coatsTicket = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(coatsTicketField));
        WebElement custTicket = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(custTicketField));
        WebElement searchBtn = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(searchButton));
        WebElement resetBtn = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(resetButton));
        WebElement importBtn = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(importButton));
        WebElement exportBtn = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(exportButton));
        WebElement newCustTicketBtn = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(newCustTicketButton));
        
        //Assert all elements are displayed
        AssertJUnit.assertTrue("Customer Tickets Page: Sales Org field not displayed",salesOrg.isDisplayed());
        AssertJUnit.assertTrue("Customer Tickets Page: Customer name field not displayed",custName.isDisplayed());
        AssertJUnit.assertTrue("Customer Tickets Page: Coats Ticket field not displayed",coatsTicket.isDisplayed());
        AssertJUnit.assertTrue("Customer Tickets Page: Customer Ticket field not displayed",custTicket.isDisplayed());
        AssertJUnit.assertTrue("Customer Tickets Page: Search button not displayed",searchBtn.isDisplayed());
        AssertJUnit.assertTrue("Customer Tickets Page: Reset button not displayed",resetBtn.isDisplayed());
        AssertJUnit.assertTrue("Customer Tickets Page: Import button not displayed",importBtn.isDisplayed());
        AssertJUnit.assertTrue("Customer Tickets Page: Export button not displayed",exportBtn.isDisplayed());
        AssertJUnit.assertTrue("Customer Tickets Page: New Customer Ticket button not displayed",newCustTicketBtn.isDisplayed());
    }
    
    public void waitForElement() {
        WebElement custTicket = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(custTicketField));
    }

    public void deleteCustTicket(){
        int nrOfEntry = driver.findElements(By.xpath("//*[@id=\"content\"]/div[2]/table/tbody/tr")).size();
        System.out.println(nrOfEntry - 1 +" Customer Ticket matching the test criteria found ");

        for(int i = nrOfEntry;i > 1; i--)
        {
            pressDelete(2);
            setSalesOrg(DataItems.autoUserSalesOrg);
            setCustomerName(DataItems.custDetails[0]);
            pressSearch();
            waitForElement();
        }
        System.out.println("Customer Ticket deleted");
    }
    
}
