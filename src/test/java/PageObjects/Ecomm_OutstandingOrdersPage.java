
package PageObjects;

import AutomationFramework.CommonTask;
import AutomationFramework.DataItems;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.AssertJUnit;

public class Ecomm_OutstandingOrdersPage extends WBA_BasePage {
    
    By formLocator = By.id("FilterOutstandingOrderForm");
    By custNameField = By.id("s2id_filterBulkOrderCustomerId");
    By custCodeField = By.id("filterCustomerCustomerCode");
    By createdDateFromField = By.id("filterBulkOrderCreatedFrom");
    By createdDateToField = By.id("filterBulkOrderCreatedTo");
    By custPOField = By.id("filterBulkOrderPoNumber");
    By orderStatusField = By.cssSelector("#s2id_filterBulkOrderStatusId > ul > li > input");
    By searchButton = By.cssSelector("#freetext > table > tbody > tr > td.searchreset_buttons > div > input");
    By resetButton = By.cssSelector("#freetext > table > tbody > tr > td.searchreset_buttons > div > input");
    By exportButton = By.cssSelector("#FilterOutstandingOrderForm > div.container > div.btn_sap_error > a");
    By noRecords = By.cssSelector("#FilterOutstandingOrderForm > div.container > div.tbl-toggle > div > div.scrollTableContainer.scroll-pane > div > div");
    
    public Ecomm_OutstandingOrdersPage(WebDriver driver) {
        super(driver);
    }
    
    public WebElement getBreadcrumb() {
        return driver.findElement(DataItems.breadcrumbLocator2);
    }
    
    public WebElement getCustNameField() {
        return driver.findElement(custNameField);
    }
        
    public WebElement getCustCodeField() {
        return driver.findElement(custCodeField);
    }
        
    public WebElement getCreatedDateFromField() {
        return driver.findElement(createdDateFromField);
    }
        
    public WebElement getCreatedDateToField() {
        return driver.findElement(createdDateToField);
    }
        
    public WebElement getCustomerPOField() {
        return driver.findElement(custPOField);
    }
        
    public WebElement getOrderStatusField() {
        return driver.findElement(orderStatusField);
    }
        
    public WebElement getSearchButton() {
        return driver.findElement(searchButton);
    }
        
    public WebElement getResetButton() {
        return driver.findElement(resetButton);
    }
    
    public WebElement getExportButton() {
        return driver.findElement(exportButton);
    }
    
    public Ecomm_OutstandingOrdersPage setCustomerName(String item) {
        CommonTask.setSearchField(driver,custNameField,item);
        return this;
    }
    
    public Ecomm_OutstandingOrdersPage setCustomerPO(String item) {
        CommonTask.setInputField(driver,custPOField,item);
        return this;
    }
    
    public Ecomm_OutstandingOrdersPage setOrderStatus(String item) {
        CommonTask.setChoiceField(driver,orderStatusField,item);
        return this;
    }
    
    public Ecomm_OutstandingOrdersPage pressSearch() {
        WebElement wait = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(searchButton));
        driver.findElement(searchButton).click();
        return this;
    }
    
    public Ecomm_OutstandingOrdersPage pressReset() {
        WebElement wait = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(resetButton));
        driver.findElement(resetButton).click();
        return this;
    }
    
    public Ecomm_ExportDownloadPage pressExport() {
        WebElement wait = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(exportButton));
        driver.findElement(exportButton).click();
        return new Ecomm_ExportDownloadPage(driver);
    }
    
    public boolean checkForRecords() {
        try {
            WebElement wait = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.visibilityOfElementLocated(noRecords));
            System.out.println("No records displayed");
            return false;
        } catch (Exception e) {
            System.out.println("Records displayed");
            return true;
        }
    }
    
    public int getRow(String poNumber) {
        //wait for table to load
        boolean waitForLoad = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.textToBePresentInElementLocated(By.cssSelector("#FilterOutstandingOrderForm > div.container > div.tbl-toggle > div > div.scrollTableContainer.scroll-pane > table > thead > tr:nth-child(1) > th:nth-child(5) > label"), "Customer PO No."));
        boolean found = false;
        int i = 0;
        while(!found && i < 8) {
            By locator = By.cssSelector("#FilterOutstandingOrderForm > div.container > div.tbl-toggle > div > div.scrollTableContainer.scroll-pane > table > tbody:nth-child(2) > tr.row-remove_"+i+" > td:nth-child(6)");
            WebElement wait = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.visibilityOfElementLocated(locator));
            if (driver.findElement(locator).getText().equals(poNumber)) {
                found = true;
            }
            i++;
        }
        
        if (found) {
            return i-1;
        } else {
            return -1;
        }       
    }
    
    
    public int getRowOffset(String poNumber,int offset) {
        //For some users, the outstanding orders page table have slightly offset locators. e.g. for testArun01 account

        //wait for table to load
        boolean waitForLoad = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.textToBePresentInElementLocated(By.cssSelector("#FilterOutstandingOrderForm > div.container > div.tbl-toggle > div > div.scrollTableContainer.scroll-pane > table > thead > tr:nth-child(1) > th:nth-child(5) > label"), "Customer PO No."));
        boolean found = false;
        int i = 0;
        while(!found && i < 8) {
            By locator = By.cssSelector("#FilterOutstandingOrderForm > div.container > div.tbl-toggle > div > div.scrollTableContainer.scroll-pane > table > tbody:nth-child(2) > tr.row-remove_"+i+" > td:nth-child("+(6+offset)+")");
            WebElement wait = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.visibilityOfElementLocated(locator));
            if (driver.findElement(locator).getText().equals(poNumber)) {
                found = true;
            }
            i++;
        }
        
        if (found) {
            return i-1;
        } else {
            return -1;
        }       
    }
    
    public Ecomm_OrderViewPage pressView(int orderRow) {
        //create locator for view button in correct row
        By viewButtonLocator = By.cssSelector("#FilterOutstandingOrderForm > div.container > div.tbl-toggle > div > div.scrollTableContainer.scroll-pane > table > tbody:nth-child(2) > tr.row-remove_"+orderRow+" > td:nth-child(2) > a > span");
        //wait for page to load and button to become clickable
        WebElement waitForClickable = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(viewButtonLocator));
        //click view button
        Actions clickView = new Actions(driver);
        clickView.click(driver.findElement(viewButtonLocator)).build().perform();
        return new Ecomm_OrderViewPage(driver);
    }
    
    public Ecomm_OrderViewPage pressPrint(int orderRow) {
        //create locator for view button in correct row
        By printButtonLocator = By.cssSelector("#FilterOutstandingOrderForm > div.container > div.tbl-toggle > div > div.scrollTableContainer.scroll-pane > table > tbody:nth-child(2) > tr.row-remove_"+orderRow+" > td:nth-child(3) > a");
        //wait for page to load and button to become clickable
        WebElement waitForClickable = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(printButtonLocator));
        //click view button
        Actions clickView = new Actions(driver);
        clickView.click(driver.findElement(printButtonLocator)).build().perform();
        return new Ecomm_OrderViewPage(driver);
    }
    
    public String getOrderNumber(int orderRow) {
        //Locator for order number cell in table
        By locator = By.cssSelector("#FilterOutstandingOrderForm > div.container > div.tbl-toggle > div > div.scrollTableContainer.scroll-pane > table > tbody:nth-child(2) > tr.row-remove_"+orderRow+" > td:nth-child(7)");
        //Wait for cell
        WebElement waitForCell = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.visibilityOfElementLocated(locator));
        return driver.findElement(locator).getText();
    }
    
    public String getOrderNumberOffset(int orderRow,int offset) {
        //For some user accounts, the outstanding orders table has different locators. Use the offset to get around this

        //Locator for order number cell in table
        By locator = By.cssSelector("#FilterOutstandingOrderForm > div.container > div.tbl-toggle > div > div.scrollTableContainer.scroll-pane > table > tbody:nth-child(2) > tr.row-remove_"+orderRow+" > td:nth-child("+(7+offset)+")");
        //Wait for cell
        WebElement waitForCell = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.visibilityOfElementLocated(locator));
        return driver.findElement(locator).getText();
    }
    
    public void checkFields() {
        //Wait for all elements to be clickable
        WebElement waitForCustName = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(custNameField));
        WebElement waitForCustCode = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(custCodeField));
        WebElement waitForCreatedFrom = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(createdDateFromField));
        WebElement waitForCreatedTo = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(createdDateToField));
        WebElement waitForCustPO = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(custPOField));
        WebElement waitForOrderStatus = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(orderStatusField));
        WebElement waitForSearch = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(searchButton));
        WebElement waitForReset = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(resetButton));
        
        //Assert all elements are displayed
        AssertJUnit.assertTrue("Outstanding Orders Page: Customer Name Field not displayed correctly",getCustNameField().isDisplayed());
        AssertJUnit.assertTrue("Outstanding Orders Page: Customer Code Field not displayed correctly",getCustCodeField().isDisplayed());
        AssertJUnit.assertTrue("Outstanding Orders Page: Created Date From Field not displayed correctly",getCreatedDateFromField().isDisplayed());
        AssertJUnit.assertTrue("Outstanding Orders Page: Created Date To Field not displayed correctly",getCreatedDateToField().isDisplayed());
        AssertJUnit.assertTrue("Outstanding Orders Page: Customer PO No. Field not displayed correctly",getCustomerPOField().isDisplayed());
        AssertJUnit.assertTrue("Outstanding Orders Page: Order Status Field not displayed correctly",getOrderStatusField().isDisplayed());
        AssertJUnit.assertTrue("Outstanding Orders Page: Search Button not displayed correctly",getSearchButton().isDisplayed());
        AssertJUnit.assertTrue("Outstanding Orders Page: Reset Button not displayed correctly",getResetButton().isDisplayed());
    }
    
    public void waitForElement() {
        WebElement waitForForm = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.visibilityOfElementLocated(formLocator));
    }
    
}
