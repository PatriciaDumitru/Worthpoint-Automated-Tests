
package PageObjects;

import AutomationFramework.CommonTask;
import AutomationFramework.DataItems;
import AutomationFramework.Wait;
import static PageObjects.WBA_BasePage.driver;
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
        return Wait.visible(driver,DataItems.breadcrumbLocator2);
    }
    
    public WebElement getCustomerPOField() {
        WebElement field = Wait.visible(driver,custPOField);
        return field;
    }
    
    public WebElement getOrderNoHead() {
        WebElement head = Wait.visible(driver,orderNoHead);
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
        WebElement search = Wait.clickable(driver,searchButton);
        search.click();
        return new Ecomm_OrderApprovalHistoryPage(driver);
    }
    
    public Ecomm_OrderApprovalHistoryPage pressReset() {
        WebElement reset = Wait.clickable(driver,resetButton);
        reset.click();
        return new Ecomm_OrderApprovalHistoryPage(driver);
    }
    
    public Ecomm_ExportDownloadPage pressExport() {
        WebElement exportBtn = Wait.clickable(driver,exportButton);
        exportBtn.click();
        
        return new Ecomm_ExportDownloadPage(driver);
    }
    
    public void checkFields() {
        //Wait for element to be clickable
        WebElement custName = Wait.clickable(driver,custNameField);
        WebElement orderNo = Wait.clickable(driver,orderNoField);
        WebElement requester = Wait.clickable(driver,requesterField);
        WebElement approver = Wait.clickable(driver,approverField);
        WebElement status = Wait.clickable(driver,statusField);
        WebElement custPO = Wait.clickable(driver,custPOField);
        WebElement search = Wait.clickable(driver,searchButton);
        WebElement reset = Wait.clickable(driver,resetButton);
        WebElement export = Wait.clickable(driver,exportButton);
        
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
        WebElement field = Wait.clickable(driver,approverField);
    }
    
    public int findOrder(String orderNo) {
        
        //Check order no column is still in the same place
        AssertJUnit.assertTrue("Approval History Page: Order No. table column has moved, cannot perform search. Update locators",getOrderNoHead().getText().contains("Order No."));
        
        for (int i = 1; i < 9; i++) {
            By selector = By.cssSelector("#content > div.flexi-grid > table > tbody:nth-child(2) > tr:nth-child("+i+") > td:nth-child(4)");
            WebElement cell = Wait.visible(driver,selector);
            
            if (cell.getText().equals(orderNo)) {
                return i;
            }
            
        }
        
        return -1;
        
    }
    
}
