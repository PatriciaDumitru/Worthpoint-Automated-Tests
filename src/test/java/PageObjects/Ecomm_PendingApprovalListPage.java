
package PageObjects;

import AutomationFramework.CommonTask;
import AutomationFramework.DataItems;
import org.testng.AssertJUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class Ecomm_PendingApprovalListPage extends WBA_BasePage {
    
    //Locators
    By custNameField = By.id("s2id_filterBulkOrderCustomerId");
    By orderNoField = By.id("s2id_filterBulkOrderId");
    By custPriceAvailField = By.id("s2id_filterBulkOrderCustomerPriceAvailabilty");
    By custPOField = By.id("filterBulkOrderPoNumber");
    By createDateFromField = By.id("filterBulkOrderCreatedFrom");
    By createDateToField = By.id("filterBulkOrderCreatedTo");
    By searchButton = By.cssSelector("#FilterIndexForm > div.grid_12 > table > tbody > tr:nth-child(4) > td > div > input");
    By resetButton = By.cssSelector("#FilterIndexForm > div.grid_12 > table > tbody > tr:nth-child(4) > td > a");
    By viewButton = By.cssSelector("#ApprovalOrdersTickForm > div.tbl-toggle > div > div.scrollTableContainer.scroll-pane > table > tbody:nth-child(2) > tr:nth-child(1) > td:nth-child(1) > a");
    By editButton = By.cssSelector("#ApprovalOrdersTickForm > div.tbl-toggle > div > div.scrollTableContainer.scroll-pane > table > tbody:nth-child(2) > tr:nth-child(1) > td:nth-child(2)");
    By printButton = By.cssSelector("#ApprovalOrdersTickForm > div.tbl-toggle > div > div.scrollTableContainer.scroll-pane > table > tbody:nth-child(2) > tr:nth-child(1) > td:nth-child(3) > a");
    By resendButton = By.cssSelector("#ApprovalOrdersTickForm > div.tbl-toggle > div > div.scrollTableContainer.scroll-pane > table > tbody:nth-child(2) > tr:nth-child(1) > td:nth-child(11)");
    By custPOHead = By.cssSelector("#ApprovalOrdersTickForm > div.tbl-toggle > div > div.scrollTableContainer.scroll-pane > table > thead > tr:nth-child(1) > th:nth-child(3)");
    
    public Ecomm_PendingApprovalListPage(WebDriver driver) {
        super(driver);
    }
    
    public WebElement getBreadcrumb() {
        WebElement breadcrumb = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.visibilityOfElementLocated(DataItems.breadcrumbLocator2));
        return breadcrumb;
    }
    
    public WebElement getCustNameField() {
        return driver.findElement(custNameField);
    }
    
    public WebElement getOrderNoField() {
        return driver.findElement(orderNoField);
    }
    
    public WebElement getCustPriceAvailField() {
        return driver.findElement(custPriceAvailField);
    }
    
    public WebElement getCustPOField() {
        return driver.findElement(custPOField);
    }
    
    public WebElement getCreateDateFromField() {
        return driver.findElement(createDateFromField);
    }
    
    public WebElement getCreateDateToField() {
        return driver.findElement(createDateToField);
    }
    
    public WebElement getSearchButton() {
        return driver.findElement(searchButton);
    }
    
    public WebElement getResetButton() {
        return driver.findElement(resetButton);
    }
    
    public WebElement getViewButton() {
        return driver.findElement(viewButton);
    }
    
    public WebElement getEditButton() {
        return driver.findElement(editButton);
    }
    
    public WebElement getPrintButton() {
        return driver.findElement(printButton);
    }
    
    public WebElement getResendButton() {
        return driver.findElement(resendButton);
    }
    
    public Ecomm_PendingApprovalListPage setCustName(String item) {
        CommonTask.setSearchField(driver, custNameField, item);
        return this;
    }
    
    public Ecomm_PendingApprovalListPage setOrderNo(String item) {
        CommonTask.setSearchField(driver, orderNoField, item);
        return this;
    }
    
    public Ecomm_PendingApprovalListPage setCustPriceAvail(String item) {
        CommonTask.setSearchField(driver, custPriceAvailField, item);
        return this;
    }
    
    public Ecomm_PendingApprovalListPage setCustPO(String item) {
        CommonTask.setSearchField(driver, custPOField, item);
        return this;
    }
    
    public Ecomm_PendingApprovalListPage pressSearch() {
        WebElement btn = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(searchButton));
        btn.click();
        
        return new Ecomm_PendingApprovalListPage(driver);
    }
    
    public void checkFields() {
        //Wait for all elements to be clickable
        WebElement waitForCustName = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(custNameField));
        WebElement waitForOrderNo = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(orderNoField));
        WebElement waitForCustPrice = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(custPriceAvailField));
        WebElement waitForCustPO = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(custPOField));
        WebElement waitForCustDateFrom = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(createDateFromField));
        WebElement waitForCustDateTo = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(createDateToField));
        WebElement waitForSearch = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(searchButton));
        WebElement waitForReset = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(resetButton));
        WebElement waitForView = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(viewButton));
        WebElement waitForEdit = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(editButton));
        WebElement waitForPrint = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(printButton));
        
        //Assert all elements are displayed
        AssertJUnit.assertTrue("Pending Approval List Page: Customer name field not displayed correctly",getCustNameField().isDisplayed());
        AssertJUnit.assertTrue("Pending Approval List Page: Order No field not displayed correctly",getOrderNoField().isDisplayed());
        AssertJUnit.assertTrue("Pending Approval List Page: Customer Price Availability field not displayed correctly",getCustPriceAvailField().isDisplayed());
        AssertJUnit.assertTrue("Pending Approval List Page: Customer PO field not displayed correctly",getCustPOField().isDisplayed());
        AssertJUnit.assertTrue("Pending Approval List Page: Create Date From Field not displayed correctly",getCreateDateFromField().isDisplayed());
        AssertJUnit.assertTrue("Pending Approval List Page: Create Date To Field not displayed correctly",getCreateDateToField().isDisplayed());
        AssertJUnit.assertTrue("Pending Approval List Page: Search Button not displayed correctly",getSearchButton().isDisplayed());
        AssertJUnit.assertTrue("Pending Approval List Page: Reset Button not displayed correctly",getResetButton().isDisplayed());
        AssertJUnit.assertTrue("Pending Approval List Page: View Button not displayed correctly",getViewButton().isDisplayed());
        AssertJUnit.assertTrue("Pending Approval List Page: Edit Button not displayed correctly",getEditButton().isDisplayed());
        AssertJUnit.assertTrue("Pending Approval List Page: Print Button not displayed correctly",getPrintButton().isDisplayed());
    }
    
    public void waitForElement() {
        WebElement wait = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(custPriceAvailField));
    }
    
    public int getRow(String PONumber) {
        AssertJUnit.assertTrue("Pending Approval List Page: Customer PO No column has moved",driver.findElement(custPOHead).getText().contains("Customer PO No."));
        int row = -1;
        for (int i = 1; i <= 10; i++) {
            By locator = By.cssSelector("#ApprovalOrdersTickForm > div.tbl-toggle > div > div.scrollTableContainer.scroll-pane > table > tbody:nth-child(2) > tr:nth-child("+i+") > td:nth-child(5)");
            if (driver.findElement(locator).getText().equals(PONumber)) {
                row = i;
                break;
            }
        }       
        return row;
    }
    
    public String getOrderNo(int row) {
        By orderNoHeader = By.cssSelector("#ApprovalOrdersTickForm > div.tbl-toggle > div > div.scrollTableContainer.scroll-pane > table > thead > tr:nth-child(1) > th:nth-child(4)");
        AssertJUnit.assertTrue("Pending approval list page: Order No. Column has moved",driver.findElement(orderNoHeader).getText().contains("Order No."));
        By orderNoCell = By.cssSelector("#ApprovalOrdersTickForm > div.tbl-toggle > div > div.scrollTableContainer.scroll-pane > table > tbody:nth-child(2) > tr:nth-child("+row+") > td:nth-child(6)");
        return driver.findElement(orderNoCell).getText();
    }
    
    public Ecomm_OrderViewPage pressView(int row) {
        By locator = By.cssSelector("#ApprovalOrdersTickForm > div.tbl-toggle > div > div.scrollTableContainer.scroll-pane > table > tbody:nth-child(2) > tr:nth-child("+row+") > td:nth-child(1) > a");
        WebElement waitForClickable = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(locator));
        driver.findElement(locator).click();
        return new Ecomm_OrderViewPage(driver);
    }
    
    public Ecomm_OrderViewPage pressPrint(int row) {
        By printLocator = By.cssSelector("#ApprovalOrdersTickForm > div.tbl-toggle > div > div.scrollTableContainer.scroll-pane > table > tbody:nth-child("+row+") > tr > td:nth-child(2) > a > img");
        WebElement print = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(printLocator));
        
        print.click();
        
        return new Ecomm_OrderViewPage(driver);
    }
    
    public Ecomm_PendingApprovalListPage pressApprove(int row) {
        By approveButton = By.cssSelector("#ApprovalOrdersTickForm > div.tbl-toggle > div > div.scrollTableContainer.scroll-pane > table > tbody:nth-child(2) > tr:nth-child("+row+") > td:nth-child(10) > a");
        WebElement wait = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(approveButton));
        driver.findElement(approveButton).click();
        return new Ecomm_PendingApprovalListPage(driver);
    }
    
    public boolean approveOrder(String orderNo) {
        for (int i = 1; i < 10; i++) {
            By orderNoCell = By.cssSelector("#ApprovalOrdersTickForm > div.tbl-toggle > div > div.scrollTableContainer.scroll-pane > table > tbody:nth-child(2) > tr:nth-child("+i+") > td:nth-child(5)");
            
            if (driver.findElement(orderNoCell).getText().equals(orderNo)) {
                Ecomm_PendingApprovalListPage pendPage = pressApprove(i);
                pendPage.waitForElement();
                
                return true;
            } 
            
        }
        return false;
        
    }
    
}
