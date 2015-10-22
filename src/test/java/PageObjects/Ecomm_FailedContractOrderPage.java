
package PageObjects;

import AutomationFramework.CommonTask;
import AutomationFramework.DataItems;
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
    
    public WebElement getSalesOrgField() {
        return driver.findElement(salesOrgField);
    }
    
    public WebElement getCustPOField() {
        return driver.findElement(custPOField);
    }
    
    public WebElement getOrderNoField() {
        return driver.findElement(orderNoField);
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
        WebElement waitForButton = new WebDriverWait(driver,5).until(ExpectedConditions.elementToBeClickable(searchButton));
        driver.findElement(searchButton).click();
        return new Ecomm_FailedContractOrderPage(driver);
    }
    
    public Ecomm_FailedContractOrderPage pressReset() {
        WebElement waitForButton = new WebDriverWait(driver,5).until(ExpectedConditions.elementToBeClickable(resetButton));
        driver.findElement(resetButton).click();
        return new Ecomm_FailedContractOrderPage(driver);
    }   
    
    public Ecomm_OrderViewPage pressView(int row) {
        By viewButton = By.cssSelector("#content > div.tbl-toggle > div > div.scrollTableContainer.scroll-pane > table > tbody:nth-child(2) > tr:nth-child("+row+") > td:nth-child(2) > a > span");
        
        WebElement waitForButton = new WebDriverWait(driver,5).until(ExpectedConditions.elementToBeClickable(viewButton));
        driver.findElement(viewButton).click();
        
        return new Ecomm_OrderViewPage(driver);      
    }
    
    public Ecomm_OrderConfirmationPage pressEdit(int row) {
        By editButton = By.cssSelector("#content > div.tbl-toggle > div > div.scrollTableContainer.scroll-pane > table > tbody:nth-child(2) > tr:nth-child("+row+") > td:nth-child(1) > a > span");
        
        WebElement waitForButton = new WebDriverWait(driver,5).until(ExpectedConditions.visibilityOfElementLocated(editButton));
        driver.findElement(editButton).click();
        
        return new Ecomm_OrderConfirmationPage(driver);
    }
    
    public void checkFields() {
        //Wait for all elements to be clickable
        WebElement waitForSalesOrg = new WebDriverWait(driver,5).until(ExpectedConditions.elementToBeClickable(salesOrgField));
        WebElement waitForCustPO = new WebDriverWait(driver,5).until(ExpectedConditions.elementToBeClickable(custPOField));
        WebElement waitForOrderNo = new WebDriverWait(driver,5).until(ExpectedConditions.elementToBeClickable(orderNoField));
        WebElement waitForDateFrom = new WebDriverWait(driver,5).until(ExpectedConditions.elementToBeClickable(createDateFromField));
        WebElement waitForDateTo = new WebDriverWait(driver,5).until(ExpectedConditions.elementToBeClickable(createDateToField));
        WebElement waitForSearch = new WebDriverWait(driver,5).until(ExpectedConditions.elementToBeClickable(searchButton));
        WebElement waitForReset = new WebDriverWait(driver,5).until(ExpectedConditions.elementToBeClickable(resetButton));
        
        //Assert all elements are displayed
        AssertJUnit.assertTrue("Failed Contract Order Page: Sales Organisation Field not displayed correctly",getSalesOrgField().isDisplayed());
        AssertJUnit.assertTrue("Failed Contract Order Page: Customer PO Field not displayed correctly",getCustPOField().isDisplayed());
        AssertJUnit.assertTrue("Failed Contract Order Page: eComm Order No. Field not displayed correctly",getOrderNoField().isDisplayed());
        AssertJUnit.assertTrue("Failed Contract Order Page: Created Date From Field not displayed correctly",getCreateDateFromField().isDisplayed());
        AssertJUnit.assertTrue("Failed Contract Order Page: Created Date To Field not displayed correctly",getCreateDateToField().isDisplayed());
        AssertJUnit.assertTrue("Failed Contract Order Page: Search Button not displayed correctly",getSearchButton().isDisplayed());
        AssertJUnit.assertTrue("Failed Contract Order Page: Reset Button not displayed correctly",getResetButton().isDisplayed());

    }
    
    public boolean checkForRecords() {
        boolean returnMe = false;
        
        try {
            WebElement waitForRecords = new WebDriverWait(driver,3).until(ExpectedConditions.visibilityOfElementLocated(noRecordsLabel));
        } catch (Exception e) {
            returnMe = true;
        }
        
        return returnMe;
        
    }
    
}
