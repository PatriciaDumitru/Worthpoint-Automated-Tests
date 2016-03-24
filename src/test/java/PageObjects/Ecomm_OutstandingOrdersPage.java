
package PageObjects;

import AutomationFramework.CommonTask;
import AutomationFramework.DataItems;
import AutomationFramework.Wait;
import static PageObjects.WBA_BasePage.driver;
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
    By orderStatusField = By.id("s2id_autogen7");
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
        
    public WebElement getCustomerPOField() {
        return driver.findElement(custPOField);
    }
        
    public WebElement getOrderStatusField() {
        return driver.findElement(orderStatusField);
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
        WebElement search = Wait.clickable(driver,searchButton);
        search.click();
        return this;
    }
    
    public Ecomm_OutstandingOrdersPage pressReset() {
        WebElement reset = Wait.clickable(driver,resetButton);
        reset.click();
        return this;
    }
    
    public Ecomm_ExportDownloadPage pressExport() {
        WebElement export = Wait.clickable(driver,exportButton);
        export.click();
        return new Ecomm_ExportDownloadPage(driver);
    }
    
    public boolean checkForRecords() {
        try {
            WebElement wait = Wait.visible(driver,noRecords);
            System.out.println("No records displayed");
            return false;
        } catch (Exception e) {
            System.out.println("Records displayed");
            return true;
        }
    }
    
    public int getRow(String poNumber) {
        //wait for table to load
        boolean waitForLoad = Wait.textPresent(driver,By.cssSelector("#FilterOutstandingOrderForm > div.container > div.tbl-toggle > div > div.scrollTableContainer.scroll-pane > table > thead > tr:nth-child(1) > th:nth-child(5) > label"), "Customer PO No.");
        
        for (int i = 0; i < 8; i++) {
            By locator = By.cssSelector("#FilterOutstandingOrderForm > div.container > div.tbl-toggle > div > div.scrollTableContainer.scroll-pane > table > tbody:nth-child(2) > tr.row-remove_"+i+" > td:nth-child(6)");

            WebElement cell = Wait.visible(driver,locator);
            
            if (cell.getText().equals(poNumber)) {
                return i;
            }
            
        }
        
        return -1;  
    }
    
    public int getRowAlt(String poNumber) {
        //The outstanding order tables vary so require alternative methods to getRow
        boolean waitForLoad = Wait.textPresent(driver,By.cssSelector("#FilterOutstandingOrderForm > div.container > div.tbl-toggle > div > div.scrollTableContainer.scroll-pane > table > thead > tr:nth-child(1) > th:nth-child(5) > label"), "Customer PO No.");
        
        for (int i = 0; i < 8; i++) {
            By locator = By.cssSelector("#FilterOutstandingOrderForm > div.container > div.tbl-toggle > div > div.scrollTableContainer.scroll-pane > table > tbody:nth-child(2) > tr.row-remove_"+i+" > td:nth-child(7)");
            
            WebElement cell = Wait.visible(driver,locator);
            
            if (cell.getText().equals(poNumber)) {
                return i;
            }
            
        }
        
        return -1;  
    }
    
    public int getRowOffset(String poNumber,int offset) {
        //For some users, the outstanding orders page table have slightly offset locators. e.g. for testArun01 account

        //wait for table to load
        boolean waitForLoad = Wait.textPresent(driver,By.cssSelector("#FilterOutstandingOrderForm > div.container > div.tbl-toggle > div > div.scrollTableContainer.scroll-pane > table > thead > tr:nth-child(1) > th:nth-child(5) > label"), "Customer PO No.");
        boolean found = false;
        int i = 0;
        while(!found && i < 8) {
            By locator = By.cssSelector("#FilterOutstandingOrderForm > div.container > div.tbl-toggle > div > div.scrollTableContainer.scroll-pane > table > tbody:nth-child(2) > tr.row-remove_"+i+" > td:nth-child("+(6+offset)+")");
            WebElement wait = Wait.visible(driver,locator);
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
        By viewButtonLocator = By.cssSelector("#FilterOutstandingOrderForm > div.container > div.tbl-toggle > div > div.scrollTableContainer.scroll-pane > table > tbody:nth-child(2) > tr.row-remove_"+orderRow+" > td:nth-child(2) > a");
        //wait for page to load and button to become clickable
        WebElement view = Wait.clickable(driver,viewButtonLocator);
        //click view button
        Actions clickView = new Actions(driver);
        clickView.click(view).build().perform();
        return new Ecomm_OrderViewPage(driver);
    }
    
    public Ecomm_OrderViewPage pressPrint(int orderRow) {
        //create locator for view button in correct row
        By printButtonLocator = By.cssSelector("#FilterOutstandingOrderForm > div.container > div.tbl-toggle > div > div.scrollTableContainer.scroll-pane > table > tbody:nth-child(2) > tr.row-remove_"+orderRow+" > td:nth-child(3) > a");
        //wait for page to load and button to become clickable
        WebElement print = Wait.clickable(driver,printButtonLocator);
        //click view button
        Actions clickPrint = new Actions(driver);
        clickPrint.click(print).build().perform();
        return new Ecomm_OrderViewPage(driver);
    }
    
    public String getOrderNumber(int orderRow) {
        //Locator for order number cell in table
        By locator = By.cssSelector("#FilterOutstandingOrderForm > div.container > div.tbl-toggle > div > div.scrollTableContainer.scroll-pane > table > tbody:nth-child(2) > tr.row-remove_"+orderRow+" > td:nth-child(7)");
        //Wait for cell
        WebElement cell = Wait.clickable(driver,locator);
        return cell.getText();
    }
    
    public String getOrderNumberSUMST(int orderRow) {
        //The outstanding order tables are different for SUMST and SUSST customers, and so different locators are used
        //Locator for order number cell in table
        By locator = By.cssSelector("#FilterOutstandingOrderForm > div.container > div.tbl-toggle > div > div.scrollTableContainer.scroll-pane > table > tbody:nth-child(2) > tr.row-remove_"+orderRow+" > td:nth-child(8)");
        
        //Wait for cell
        WebElement cell = Wait.clickable(driver,locator);
        return cell.getText();
    }
    
    public String getOrderNumberOffset(int orderRow,int offset) {
        //For some user accounts, the outstanding orders table has different locators. Use the offset to get around this

        //Locator for order number cell in table
        By locator = By.cssSelector("#FilterOutstandingOrderForm > div.container > div.tbl-toggle > div > div.scrollTableContainer.scroll-pane > table > tbody:nth-child(2) > tr.row-remove_"+orderRow+" > td:nth-child("+(7+offset)+")");
        //Wait for cell
        WebElement cell = Wait.visible(driver,locator);
        return cell.getText();
    }
    
    public String getOrderStatus(int row) {
        //For approver account (mail.kamleshpatidar@gmail.com)
        By locator = By.cssSelector("#FilterOutstandingOrderForm > div.container > div.tbl-toggle > div > div.scrollTableContainer.scroll-pane > table > tbody:nth-child(2) > tr.row-remove_"+row+" > td:nth-child(13)");
        WebElement cell = Wait.visible(driver,locator);
        return cell.getText();
    }
    
    public void checkFields() {
        //Wait for all elements to be clickable
        WebElement custName = Wait.clickable(driver,custNameField);
        WebElement custCode = Wait.clickable(driver,custCodeField);
        WebElement createdFrom = Wait.clickable(driver,createdDateFromField);
        WebElement createdTo = Wait.clickable(driver,createdDateToField);
        WebElement custPO = Wait.clickable(driver,custPOField);
        WebElement orderStatus = Wait.clickable(driver,orderStatusField);
        WebElement search = Wait.clickable(driver,searchButton);
        WebElement reset = Wait.clickable(driver,resetButton);
        
        //Assert all elements are displayed
        AssertJUnit.assertTrue("Outstanding Orders Page: Customer Name Field not displayed correctly",custName.isDisplayed());
        AssertJUnit.assertTrue("Outstanding Orders Page: Customer Code Field not displayed correctly",custCode.isDisplayed());
        AssertJUnit.assertTrue("Outstanding Orders Page: Created Date From Field not displayed correctly",createdFrom.isDisplayed());
        AssertJUnit.assertTrue("Outstanding Orders Page: Created Date To Field not displayed correctly",createdTo.isDisplayed());
        AssertJUnit.assertTrue("Outstanding Orders Page: Customer PO No. Field not displayed correctly",custPO.isDisplayed());
        AssertJUnit.assertTrue("Outstanding Orders Page: Order Status Field not displayed correctly",orderStatus.isDisplayed());
        AssertJUnit.assertTrue("Outstanding Orders Page: Search Button not displayed correctly",search.isDisplayed());
        AssertJUnit.assertTrue("Outstanding Orders Page: Reset Button not displayed correctly",reset.isDisplayed());
    }
    
    public void waitForElement() {
        WebElement waitForForm = Wait.visible(driver,formLocator);
    }


}
