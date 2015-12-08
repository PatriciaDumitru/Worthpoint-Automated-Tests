
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

public class Ecomm_FailedContractOrderPage extends WBA_BasePage {
    
    //Locators
    By salesOrgField = By.cssSelector("#s2id_filterBulkOrderSalesOrgId > a");
    By custPOField = By.id("filterBulkOrderPoNumber");
    By orderNoField = By.cssSelector("#s2id_filterBulkOrderId > a");
    By createDateFromField = By.id("filterBulkOrderCreatedFrom");
    By createDateToField = By.id("filterBulkOrderCreatedTo");
    By searchButton = By.cssSelector("#FilterFailedContractListForm > div.grid_12 > table > tbody > tr:nth-child(4) > td > div > input");
    By resetButton = By.cssSelector("#FilterFailedContractListForm > div.grid_12 > table > tbody > tr:nth-child(4) > td > a");
    By noRecordsLabel = By.cssSelector("#FilterShowinerrorlistForm > div.tbl-toggle > div > div.scrollTableContainer.scroll-pane > div.flexi-grid > div");
    
    public Ecomm_FailedContractOrderPage(WebDriver driver) {
        super(driver);
    }
    
    public WebElement getBreadcrumb() {
        return driver.findElement(DataItems.breadcrumbLocator2);
    }
    
    public Ecomm_FailedContractOrderPage setSalesOrg(String item) {
        CommonTask.setSearchField(driver,salesOrgField,item);
        return this;
    }
    
    public Ecomm_FailedContractOrderPage setCustomerPO(String item) {
        CommonTask.setInputField(driver,custPOField,item);
        return this;
    }
    
    public Ecomm_FailedContractOrderPage setOrderNo(String item) {
        CommonTask.setSearchField(driver,orderNoField,item);
        return this;
    }
    
    public Ecomm_FailedContractOrderPage pressSearch() {
        WebElement button = Wait.clickable(driver,searchButton);
        button.click();
        return new Ecomm_FailedContractOrderPage(driver);
    }
    
    public Ecomm_FailedContractOrderPage pressReset() {
        WebElement button = Wait.clickable(driver,resetButton);
        button.click();
        return new Ecomm_FailedContractOrderPage(driver);
    }   
    
    public Ecomm_OrderViewPage pressView(int row) {
        By viewButton = By.cssSelector("#content > div.tbl-toggle > div > div.scrollTableContainer.scroll-pane > table > tbody:nth-child(2) > tr:nth-child("+row+") > td:nth-child(2) > a > span");
        
        WebElement view = Wait.clickable(driver,viewButton);
        view.click();
        
        return new Ecomm_OrderViewPage(driver);      
    }
    
    public Ecomm_OrderConfirmationPage pressEdit(int row) {
        By editButton = By.cssSelector("#content > div.tbl-toggle > div > div.scrollTableContainer.scroll-pane > table > tbody:nth-child(2) > tr:nth-child("+row+") > td:nth-child(1) > a > span");
        
        WebElement button = Wait.visible(driver,editButton);
        button.click();
        
        return new Ecomm_OrderConfirmationPage(driver);
    }
    
    public void checkFields() {
        //Wait for all elements to be clickable
        WebElement salesOrg = Wait.clickable(driver,salesOrgField);
        WebElement custPO = Wait.clickable(driver,custPOField);
        WebElement orderNo = Wait.clickable(driver,orderNoField);
        WebElement dateFrom = Wait.clickable(driver,createDateFromField);
        WebElement dateTo = Wait.clickable(driver,createDateToField);
        WebElement search = Wait.clickable(driver,searchButton);
        WebElement reset = Wait.clickable(driver,resetButton);
        
        //Assert all elements are displayed
        AssertJUnit.assertTrue("Failed Contract Order Page: Sales Organisation Field not displayed correctly",salesOrg.isDisplayed());
        AssertJUnit.assertTrue("Failed Contract Order Page: Customer PO Field not displayed correctly",custPO.isDisplayed());
        AssertJUnit.assertTrue("Failed Contract Order Page: eComm Order No. Field not displayed correctly",orderNo.isDisplayed());
        AssertJUnit.assertTrue("Failed Contract Order Page: Created Date From Field not displayed correctly",dateFrom.isDisplayed());
        AssertJUnit.assertTrue("Failed Contract Order Page: Created Date To Field not displayed correctly",dateTo.isDisplayed());
        AssertJUnit.assertTrue("Failed Contract Order Page: Search Button not displayed correctly",search.isDisplayed());
        AssertJUnit.assertTrue("Failed Contract Order Page: Reset Button not displayed correctly",reset.isDisplayed());

    }
    
    public boolean checkForRecords() {
        boolean returnMe = false;
        
        try {
            WebElement waitForRecords = Wait.visible(driver,noRecordsLabel);
        } catch (Exception e) {
            returnMe = true;
        }
        
        return returnMe;
        
    }
    
}
