
package PageObjects;

import AutomationFramework.CommonTask;
import AutomationFramework.DataItems;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.AssertJUnit;

public class Ecomm_OrderApprovalHistoryPage extends WBA_BasePage {
    
    //Locators
    By custNameField = By.id("s2id_filterBulkOrderCustomerId");
    By orderNoField = By.id("s2id_filterBulkOrderId");
    By requesterField = By.id("s2id_filterBulkOrderRequesterId");
    By approverField = By.id("s2id_filterApprovalOrderHistoryApproverId");
    By statusField = By.id("s2id_filterApprovalOrderHistoryStatusId");
    By custPOField = By.id("filterBulkOrderPoNumber");
    By searchButton = By.cssSelector("#FilterApprovalhistoryForm > div.grid_12 > table > tbody > tr:nth-child(6) > td > div > input");
    By resetButton = By.cssSelector("#FilterApprovalhistoryForm > div.grid_12 > table > tbody > tr:nth-child(6) > td > a");
    By exportButton = By.cssSelector("#content > div.flexi-grid > div > a");
    By orderNoHead = By.cssSelector("#content > div.flexi-grid > table > thead > tr > th:nth-child(4) > label");
    
    public Ecomm_OrderApprovalHistoryPage(WebDriver driver) {
        super(driver);
    }
    
    public WebElement getBreadcrumb() {
        WebElement breadcrumb = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.visibilityOfElementLocated(DataItems.breadcrumbLocator2));
        return breadcrumb;
    }
        
    public WebElement getCustomerNameField() {
        WebElement field = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.visibilityOfElementLocated(custNameField));
        return field;
    }
    
    public WebElement getOrderNoField() {
        WebElement field = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.visibilityOfElementLocated(orderNoField));
        return field;
    }
    
    public WebElement getRequesterField() {
        WebElement field = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.visibilityOfElementLocated(requesterField));
        return field;
    }
    
    public WebElement getApproverField() {
        WebElement field = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.visibilityOfElementLocated(approverField));
        return field;
    }
    
    public WebElement getStatusField() {
        WebElement field = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.visibilityOfElementLocated(statusField));
        return field;
    }
    
    public WebElement getCustomerPOField() {
        WebElement field = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.visibilityOfElementLocated(custPOField));
        return field;
    }
    
    public WebElement getSearchButton() {
        WebElement field = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.visibilityOfElementLocated(searchButton));
        return field;
    }
    
    public WebElement getResetButton() {
        WebElement field = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.visibilityOfElementLocated(resetButton));
        return field;
    }
    
    public WebElement getExportButton() {
        WebElement field = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.visibilityOfElementLocated(exportButton));
        return field;
    }
    
    public WebElement getOrderNoHead() {
        WebElement head = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.visibilityOfElementLocated(orderNoHead));
        return head;
    }
    
    public Ecomm_OrderApprovalHistoryPage setCustomerName(String item) {
        CommonTask.setSearchField(driver, custNameField, item);
        return new Ecomm_OrderApprovalHistoryPage(driver);
    }
    
    public Ecomm_OrderApprovalHistoryPage setCustomerPO(String item) {
        CommonTask.setInputField(driver, custPOField, item);
        return new Ecomm_OrderApprovalHistoryPage(driver);
    }
    
    public Ecomm_OrderApprovalHistoryPage pressSearch() {
        WebElement search = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(searchButton));
        search.click();
        return new Ecomm_OrderApprovalHistoryPage(driver);
    }
    
    public Ecomm_OrderApprovalHistoryPage pressReset() {
        WebElement reset = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(resetButton));
        reset.click();
        return new Ecomm_OrderApprovalHistoryPage(driver);
    }
    
    public Ecomm_ExportDownloadPage pressExport() {
        WebElement exportBtn = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(exportButton));
        exportBtn.click();
        
        return new Ecomm_ExportDownloadPage(driver);
    }
    
    public void checkFields() {
        //Wait for element to be clickable
        WebElement custName = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(custNameField));
        WebElement orderNo = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(orderNoField));
        WebElement requester = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(requesterField));
        WebElement approver = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(approverField));
        WebElement status = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(statusField));
        WebElement custPO = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(custPOField));
        WebElement search = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(searchButton));
        WebElement reset = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(resetButton));
        WebElement export = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(exportButton));
        
        //Assert all elements are displayed correctly
        AssertJUnit.assertTrue("Order Approval History Page: Customer name field not displayed correctly",custName.isDisplayed());
        AssertJUnit.assertTrue("Order Approval History Page: Order No field not displayed correctly",orderNo.isDisplayed());
        AssertJUnit.assertTrue("Order Approval History Page: Requester field not displayed correctly",requester.isDisplayed());
        AssertJUnit.assertTrue("Order Approval History Page: Approver field not displayed correctly",approver.isDisplayed());
        AssertJUnit.assertTrue("Order Approval History Page: Status field not displayed correctly",status.isDisplayed());
        AssertJUnit.assertTrue("Order Approval History Page: Customer PO field not displayed correctly",custPO.isDisplayed());
        AssertJUnit.assertTrue("Order Approval History Page: Search button not displayed correctly",search.isDisplayed());
        AssertJUnit.assertTrue("Order Approval History Page: Reset button not displayed correctly",reset.isDisplayed());
        AssertJUnit.assertTrue("Order Approval History Page: Export button not displayed correctly",export.isDisplayed());
    }
    
    public void waitForElement() {
        WebElement field = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(approverField));
    }
    
    public int findOrder(String orderNo) {
        
        //Check order no column is still in the same place
        AssertJUnit.assertTrue("Approval History Page: Order No. table column has moved, cannot perform search. Update locators",getOrderNoHead().getText().contains("Order No."));
        
        for (int i = 1; i < 9; i++) {
            By selector = By.cssSelector("#content > div.flexi-grid > table > tbody:nth-child(2) > tr:nth-child("+i+") > td:nth-child(4)");
            WebElement cell = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.visibilityOfElementLocated(selector));
            
            if (cell.getText().equals(orderNo)) {
                return i;
            }
            
        }
        
        return -1;
        
    }
    
}
