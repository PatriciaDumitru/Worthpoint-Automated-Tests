
package PageObjects;

import AutomationFramework.CommonTask;
import org.testng.AssertJUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Ecomm_ShadeNotAvailablePage extends WBA_BasePage {
    
    //Locators
    By salesOrgField = By.id("s2id_filterBulkOrderSalesOrgId");
    By custNameField = By.id("s2id_filterBulkOrderCustomerId");
    By createDateFromField = By.id("filterBulkOrderCreatedFrom");
    By createDateToField = By.id("filterBulkOrderCreatedTo");
    By custPOField = By.id("filterBulkOrderPoNumber");
    By orderNoField = By.id("s2id_filterBulkOrderId");
    By searchButton = By.cssSelector("#FilterShadenotavailableListForm > div.grid_12 > table > tbody > tr:nth-child(4) > td > div > input");
    By resetButton = By.cssSelector("#FilterShadenotavailableListForm > div.grid_12 > table > tbody > tr:nth-child(4) > td > a");
    By editButton = By.cssSelector("#content > div.tbl-toggle > div > div.scrollTableContainer.scroll-pane > table > tbody:nth-child(2) > tr:nth-child(1) > td:nth-child(1)");
    By viewButton = By.cssSelector("#content > div.tbl-toggle > div > div.scrollTableContainer.scroll-pane > table > tbody:nth-child(2) > tr:nth-child(1) > td:nth-child(2) > a");
    
    public Ecomm_ShadeNotAvailablePage(WebDriver driver) {
        super(driver);
    }
    
    public WebElement getSalesOrgField() {
        return driver.findElement(salesOrgField);
    }
    
    public WebElement getCustNameField() {
        return driver.findElement(custNameField);
    }
    
    public WebElement getCreateDateFromField() {
        return driver.findElement(createDateFromField);
    }
    
    public WebElement getCreateDateToField() {
        return driver.findElement(createDateToField);
    }
    
    public WebElement getCustPOField() {
        return driver.findElement(custPOField);
    }
    
    public WebElement getOrderNoField() {
        return driver.findElement(orderNoField);
    }
    
    public WebElement getSearchButton() {
        return driver.findElement(searchButton);
    }
    
    public WebElement getResetButton() {
        return driver.findElement(resetButton);
    }
    
    public WebElement getEditButton() {
        return driver.findElement(editButton);
    }
    
    public WebElement getViewButton() {
        return driver.findElement(viewButton);
    }
    
    public Ecomm_ShadeNotAvailablePage setSalesOrg(String item) {
        CommonTask.setSearchField(driver, salesOrgField, item);
        return this;
    }
    
    public Ecomm_ShadeNotAvailablePage setCustName(String item) {
        CommonTask.setSearchField(driver, custNameField, item);
        return this;
    }
    
    public Ecomm_ShadeNotAvailablePage setOrderNo(String item) {
        CommonTask.setSearchField(driver, orderNoField, item);
        return this;
    }
    
    public Ecomm_ShadeNotAvailablePage setCustPO(String item) {
        CommonTask.setSearchField(driver, custPOField, item);
        return this;
    }
    
    public void checkFields() {
        //Wait for all elements to be clickable
        WebElement waitForSalesOrg = new WebDriverWait(driver,5).until(ExpectedConditions.elementToBeClickable(salesOrgField));
        WebElement waitForCustName = new WebDriverWait(driver,5).until(ExpectedConditions.elementToBeClickable(custNameField));
        WebElement waitForCreateFrom = new WebDriverWait(driver,5).until(ExpectedConditions.elementToBeClickable(createDateFromField));
        WebElement waitForCreateTo = new WebDriverWait(driver,5).until(ExpectedConditions.elementToBeClickable(createDateToField));
        WebElement waitForCustPO = new WebDriverWait(driver,5).until(ExpectedConditions.elementToBeClickable(custPOField));
        WebElement waitForOrderNo = new WebDriverWait(driver,5).until(ExpectedConditions.elementToBeClickable(orderNoField));
        WebElement waitForSearchBtn = new WebDriverWait(driver,5).until(ExpectedConditions.elementToBeClickable(searchButton));
        WebElement waitForResetBtn = new WebDriverWait(driver,5).until(ExpectedConditions.elementToBeClickable(resetButton));
        WebElement waitForEditBtn = new WebDriverWait(driver,5).until(ExpectedConditions.elementToBeClickable(editButton));
        WebElement waitForViewBtn = new WebDriverWait(driver,5).until(ExpectedConditions.elementToBeClickable(viewButton));
    
        //Assert all elements are displayed
        AssertJUnit.assertTrue("Shade Not Available Page: Sales Org Field not displayed correctly",getSalesOrgField().isDisplayed());
        AssertJUnit.assertTrue("Shade Not Available Page: Customer Name Field not displayed correctly",getCustNameField().isDisplayed());
        AssertJUnit.assertTrue("Shade Not Available Page: Creation Date From Field not displayed correctly",getCreateDateFromField().isDisplayed());
        AssertJUnit.assertTrue("Shade Not Available Page: Creation Date To Field not displayed correctly",getCreateDateToField().isDisplayed());
        AssertJUnit.assertTrue("Shade Not Available Page: Customer PO Field not displayed correctly",getCustPOField().isDisplayed());
        AssertJUnit.assertTrue("Shade Not Available Page: Order No Field not displayed correctly",getOrderNoField().isDisplayed());
        AssertJUnit.assertTrue("Shade Not Available Page: Search button not displayed correctly",getSearchButton().isDisplayed());
        AssertJUnit.assertTrue("Shade Not Available Page: Reset button not displayed correctly",getResetButton().isDisplayed());
        AssertJUnit.assertTrue("Shade Not Available Page: Edit button not displayed correctly",getEditButton().isDisplayed());
        AssertJUnit.assertTrue("Shade Not Available Page: View button not displayed correctly",getViewButton().isDisplayed());
    }
    
    public Ecomm_ShadeNotAvailablePage pressSearch() {
        WebElement waitForClickable = new WebDriverWait(driver,5).until(ExpectedConditions.elementToBeClickable(searchButton));
        driver.findElement(searchButton).click();
        return this;
    }
    
    public Ecomm_ShadeNotAvailablePage pressReset() {
        WebElement waitForClickable = new WebDriverWait(driver,5).until(ExpectedConditions.elementToBeClickable(resetButton));
        driver.findElement(resetButton).click();
        return this;
    }
    
    public Ecomm_ShadeOrderConfirmationPage pressEdit() {
        WebElement waitForClickable = new WebDriverWait(driver,5).until(ExpectedConditions.elementToBeClickable(editButton));
        driver.findElement(editButton).click();
        return new Ecomm_ShadeOrderConfirmationPage(driver);
    }
    
    public Ecomm_OrderViewPage pressView() {
        WebElement waitForClickable = new WebDriverWait(driver,5).until(ExpectedConditions.elementToBeClickable(viewButton));
        driver.findElement(viewButton).click();
        return new Ecomm_OrderViewPage(driver);
    }
    
    
}
