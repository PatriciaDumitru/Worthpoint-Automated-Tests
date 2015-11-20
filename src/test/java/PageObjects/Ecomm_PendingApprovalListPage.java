
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
    By custPOHead = By.cssSelector("#ApprovalOrdersTickForm > div.tbl-toggle > div > div.scrollTableContainer.scroll-pane > table > thead > tr:nth-child(1) > th:nth-child(3) > label");
    By approveButton = By.cssSelector("#ApprovalOrdersTickForm > div.tbl-toggle > div > div.scrollTableContainer.scroll-pane > div > div > input[type=\"submit\"]:nth-child(1)");
    By denyButton = By.cssSelector("#ApprovalOrdersTickForm > div.tbl-toggle > div > div.scrollTableContainer.scroll-pane > div > div > input[type=\"submit\"]:nth-child(2)");
    By recordCountField = By.cssSelector("#ApprovalOrdersTickForm > div.tbl-toggle > div > div.scrollTableContainer.scroll-pane > div > dl > dt > span.left");
    
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
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(custPriceAvailField));
        return element;
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
    
    public Ecomm_PendingApprovalListPage setCustPOInput(String item) {
        CommonTask.setInputField(driver, custPOField, item);
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
    
    public void checkFieldsRequester() {
        //Wait for all elements to be clickable        
        WebElement waitForOrderNo = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(orderNoField));
        WebElement waitForCustPrice = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(custPriceAvailField));
        WebElement waitForCustPO = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(custPOField));
        WebElement waitForCustDateFrom = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(createDateFromField));
        WebElement waitForCustDateTo = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(createDateToField));
        WebElement waitForSearch = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(searchButton));
        WebElement waitForReset = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(resetButton));
        WebElement waitForView = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(viewButton));
        WebElement waitForEdit = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(editButton));
        
        //Assert all elements are displayed      
        AssertJUnit.assertTrue("Pending Approval List Page: Order No field not displayed correctly",getOrderNoField().isDisplayed());
        AssertJUnit.assertTrue("Pending Approval List Page: Customer Price Availability field not displayed correctly",getCustPriceAvailField().isDisplayed());
        AssertJUnit.assertTrue("Pending Approval List Page: Customer PO field not displayed correctly",getCustPOField().isDisplayed());
        AssertJUnit.assertTrue("Pending Approval List Page: Create Date From Field not displayed correctly",getCreateDateFromField().isDisplayed());
        AssertJUnit.assertTrue("Pending Approval List Page: Create Date To Field not displayed correctly",getCreateDateToField().isDisplayed());
        AssertJUnit.assertTrue("Pending Approval List Page: Search Button not displayed correctly",getSearchButton().isDisplayed());
        AssertJUnit.assertTrue("Pending Approval List Page: Reset Button not displayed correctly",getResetButton().isDisplayed());
        AssertJUnit.assertTrue("Pending Approval List Page: View Button not displayed correctly",getViewButton().isDisplayed());
        AssertJUnit.assertTrue("Pending Approval List Page: Edit Button not displayed correctly",getEditButton().isDisplayed());
    }
    
    public void checkFieldsApprover() {
        //Wait for all elements to be clickable
        WebElement waitForOrderNo = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(orderNoField));
        WebElement waitForCustPrice = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(custPriceAvailField));
        WebElement waitForCustPO = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(custPOField));
        WebElement waitForCustDateFrom = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(createDateFromField));
        WebElement waitForCustDateTo = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(createDateToField));
        WebElement waitForSearch = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(searchButton));
        WebElement waitForReset = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(resetButton));
        WebElement waitForView = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(viewButton));
        WebElement waitForEdit = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(editButton));
        WebElement approveBtn = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(approveButton));
        WebElement denyBtn = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(denyButton));
        
        //Assert all elements are displayed
        AssertJUnit.assertTrue("Pending Approval List Page: Order No field not displayed correctly",getOrderNoField().isDisplayed());
        AssertJUnit.assertTrue("Pending Approval List Page: Customer Price Availability field not displayed correctly",getCustPriceAvailField().isDisplayed());
        AssertJUnit.assertTrue("Pending Approval List Page: Customer PO field not displayed correctly",getCustPOField().isDisplayed());
        AssertJUnit.assertTrue("Pending Approval List Page: Create Date From Field not displayed correctly",getCreateDateFromField().isDisplayed());
        AssertJUnit.assertTrue("Pending Approval List Page: Create Date To Field not displayed correctly",getCreateDateToField().isDisplayed());
        AssertJUnit.assertTrue("Pending Approval List Page: Search Button not displayed correctly",getSearchButton().isDisplayed());
        AssertJUnit.assertTrue("Pending Approval List Page: Reset Button not displayed correctly",getResetButton().isDisplayed());
        AssertJUnit.assertTrue("Pending Approval List Page: View Button not displayed correctly",getViewButton().isDisplayed());
        AssertJUnit.assertTrue("Pending Approval List Page: Edit Button not displayed correctly",getEditButton().isDisplayed());       
        AssertJUnit.assertTrue("Pending Approval List Page: Approve Button not displayed correctly",approveBtn.isDisplayed());        
        AssertJUnit.assertTrue("Pending Approval List Page: Deny Button not displayed correctly",denyBtn.isDisplayed());        
    }
    
    public void waitForElement() {
        WebElement wait = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(custPriceAvailField));
    }
    
    public int getRow(String PONumber) {
        WebElement head = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.visibilityOfElementLocated(custPOHead));
        AssertJUnit.assertTrue("Pending Approval List Page: Customer PO No column has moved",head.getText().contains("Customer PO No."));
        
        for (int i = 1; i <= 10; i++) {
            By locator = By.cssSelector("#ApprovalOrdersTickForm > div.tbl-toggle > div > div.scrollTableContainer.scroll-pane > table > tbody:nth-child(2) > tr:nth-child("+i+") > td:nth-child(4)");
            WebElement cell = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.visibilityOfElementLocated(locator));
            if (cell.getText().equals(PONumber)) {
                return i;
            }
        }       
        return -1;
    }
    
    public int getRowNonApprover(String PONumber) {
        
        AssertJUnit.assertTrue("Pending Approval List Page: Customer PO No column has moved",driver.findElement(custPOHead).getText().contains("Customer PO No."));
        int row = -1;
        for (int i = 1; i <= 10; i++) {
            By locator = By.cssSelector("#ApprovalOrdersTickForm > div.tbl-toggle > div > div.scrollTableContainer.scroll-pane > table > tbody:nth-child(2) > tr:nth-child("+i+") > td:nth-child(5)");
            WebElement cell = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.visibilityOfElementLocated(locator));
            if (cell.getText().equals(PONumber)) {
                row = i;
                break;
            }
        }       
        return row;
    }
    
    public String getOrderNo(int row) {
        By orderNoHeader = By.cssSelector("#ApprovalOrdersTickForm > div.tbl-toggle > div > div.scrollTableContainer.scroll-pane > table > thead > tr:nth-child(1) > th:nth-child(4) > label");
        WebElement header = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.visibilityOfElementLocated(orderNoHeader));
        AssertJUnit.assertTrue("Pending approval list page: Order No. Column has moved, update locators",header.getText().contains("Order No."));
        By orderNoCell = By.cssSelector("#ApprovalOrdersTickForm > div.tbl-toggle > div > div.scrollTableContainer.scroll-pane > table > tbody:nth-child(2) > tr:nth-child("+row+") > td:nth-child(5)");
        return driver.findElement(orderNoCell).getText();
    }
    
    public String getOrderNoSUMST(int row) {
        By orderNoHeader = By.cssSelector("#ApprovalOrdersTickForm > div.tbl-toggle > div > div.scrollTableContainer.scroll-pane > table > thead > tr:nth-child(1) > th:nth-child(4) > label");
        AssertJUnit.assertTrue("Pending approval list page: Order No. Column has moved",driver.findElement(orderNoHeader).getText().contains("Order No."));
        By orderNoCell = By.cssSelector("#ApprovalOrdersTickForm > div.tbl-toggle > div > div.scrollTableContainer.scroll-pane > table > tbody:nth-child(2) > tr:nth-child("+row+") > td:nth-child(5)");
        return driver.findElement(orderNoCell).getText();
    }
    
    public WebElement getCustPOCell(int row) {
        By selector = By.cssSelector("#ApprovalOrdersTickForm > div.tbl-toggle > div > div.scrollTableContainer.scroll-pane > table > tbody:nth-child(2) > tr:nth-child("+row+") > td:nth-child(4)");
        WebElement cell = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.visibilityOfElementLocated(selector));
        return cell;
    }
    
    public Ecomm_OrderViewPage pressView(int row) {
        By locator = By.cssSelector("#ApprovalOrdersTickForm > div.tbl-toggle > div > div.scrollTableContainer.scroll-pane > table > tbody:nth-child(2) > tr:nth-child("+row+") > td:nth-child(1) > a");
        WebElement waitForClickable = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(locator));
        driver.findElement(locator).click();
        return new Ecomm_OrderViewPage(driver);
    }
    
    public Ecomm_OrderViewPage pressPrint(int row) {
        By printLocator = By.cssSelector("#ApprovalOrdersTickForm > div.tbl-toggle > div > div.scrollTableContainer.scroll-pane > table > tbody:nth-child(2) > tr:nth-child("+row+") > td:nth-child(2) > a");
        WebElement print = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(printLocator));
        
        print.click();
        
        return new Ecomm_OrderViewPage(driver);
    }
    
    public Ecomm_PendingApprovalListPage pressApprove(int row) {
        
        By selectButton = By.cssSelector("#ApprovalOrdersTickForm > div.tbl-toggle > div > div.scrollTableContainer.scroll-pane > table > tbody:nth-child(2) > tr:nth-child("+row+") > td:nth-child(13)");
        CommonTask.clickInputCheckBox(driver, selectButton);
        
        WebElement approveBtn = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(approveButton));
        approveBtn.click();
        
        WebElement flash = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.visibilityOfElementLocated(DataItems.flashMessage));
        AssertJUnit.assertTrue("Pending Approval Page: Confirmation message does not appear following order approval",flash.getText().contains("has been Approved"));
        
        System.out.println("Message received: " + flash.getText());
        
        return new Ecomm_PendingApprovalListPage(driver);
    }
    
    public Ecomm_PendingApprovalListPage pressApproveAll() {
        By selectAll = By.id("select_all");
        
        CommonTask.clickInputCheckBox(driver, selectAll);
        
        WebElement approveBtn = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(approveButton));
        approveBtn.click();
        
        return new Ecomm_PendingApprovalListPage(driver);  
    }
    
    public Ecomm_PendingApprovalListPage pressDeny(int row) {
        By selectButton = By.cssSelector("#ApprovalOrdersTickForm > div.tbl-toggle > div > div.scrollTableContainer.scroll-pane > table > tbody:nth-child(2) > tr:nth-child("+row+") > td:nth-child(13)");
        CommonTask.clickInputCheckBox(driver, selectButton);
        
        By reasonField = By.cssSelector("#ApprovalOrdersTickForm > div.tbl-toggle > div > div.scrollTableContainer.scroll-pane > table > tbody:nth-child(2) > tr:nth-child("+row+") > td:nth-child(12) > div > input");
        WebElement reason = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(reasonField));
        CommonTask.setInputField(driver, reasonField, "AutoTest Denied");
        
        WebElement denyBtn = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(denyButton));
        denyBtn.click();
        
        WebElement flash = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.visibilityOfElementLocated(DataItems.flashMessage));
        AssertJUnit.assertTrue("Pending Approval Page: Confirmation message does not appear following order approval",flash.getText().contains("has been Denied"));
        
        System.out.println("Message received: " + flash.getText());
        
        return new Ecomm_PendingApprovalListPage(driver);
    }
    
    public Ecomm_PendingApprovalListPage pressDenyAll() {
        By selectAll = By.id("select_all");
        
        CommonTask.clickInputCheckBox(driver, selectAll);

        int recordCount = getRecordCount(recordCountField);
        
        int tableCount = (recordCount>=10) ? 10 : recordCount;
        
        for (int i = 1; i <= tableCount; i++) {
            
            By reasonField = By.cssSelector("#ApprovalOrdersTickForm > div.tbl-toggle > div > div.scrollTableContainer.scroll-pane > table > tbody:nth-child(2) > tr:nth-child("+i+") > td:nth-child(12) > div > input");
            CommonTask.setInputField(driver,reasonField,"AutoTest Denied");
        }
        
        WebElement denyBtn = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(denyButton));
        denyBtn.click();
        
        return new Ecomm_PendingApprovalListPage(driver);  
    }
    
    public boolean approveOrder(String orderNo) {
        for (int i = 1; i < 10; i++) {
            By orderNoCell = By.cssSelector("#ApprovalOrdersTickForm > div.tbl-toggle > div > div.scrollTableContainer.scroll-pane > table > tbody:nth-child(2) > tr:nth-child("+i+") > td:nth-child(5)");
            
            WebElement cell = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.visibilityOfElementLocated(orderNoCell));
            
            if (cell.getText().equals(orderNo)) {
                System.out.println("order found");
                Ecomm_PendingApprovalListPage pendPage = pressApprove(i);
                pendPage.waitForElement();
                
                return true;
            } 
            
        }
        return false;
        
    }
    
    public boolean denyOrder(String orderNo) {
        for (int i = 1; i < 10; i++) {
            By orderNoCell = By.cssSelector("#ApprovalOrdersTickForm > div.tbl-toggle > div > div.scrollTableContainer.scroll-pane > table > tbody:nth-child(2) > tr:nth-child("+i+") > td:nth-child(5)");
            
            WebElement cell = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.visibilityOfElementLocated(orderNoCell));
            
            if (cell.getText().equals(orderNo)) {
                Ecomm_PendingApprovalListPage pendPage = pressDeny(i);
                pendPage.waitForElement();
                
                return true;
            } 
            
        }
        return false;
    }
    
    
    
}
