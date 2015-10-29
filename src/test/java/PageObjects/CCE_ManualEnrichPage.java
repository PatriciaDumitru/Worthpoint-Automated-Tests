/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PageObjects;

import AutomationFramework.CommonTask;
import AutomationFramework.DataItems;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.AssertJUnit;

/**
 *
 * @author ukspjsykes
 */
public class CCE_ManualEnrichPage extends WBA_BasePage {
    
    //Locators
    By orderNoField = By.id("s2id_filterSampleOrderLineOrderId");
    By custNameField = By.id("s2id_filterSampleOrderCustomerId");
    By scenarioField = By.id("s2id_filterSampleOrderLineScenarioId");
    By requestTypeField = By.id("s2id_filterSampleOrderLineRequestTypeId");
    By hubField = By.id("s2id_filterSampleOrderHubId");
    By requesterField = By.id("s2id_filterSampleOrderRequesterId");
    By fceNameField = By.id("s2id_filterSampleOrderFceId");
    By createdDateFromField = By.id("filterSampleOrderCreatedFrom");
    By createdDateToField = By.id("filterSampleOrderCreatedTo");
    By searchButton = By.cssSelector("#FilterEnrichForm > div.actions > ul > li:nth-child(1) > input[type=\"submit\"]");
    By resetButton = By.cssSelector("#FilterEnrichForm > div.actions > ul > li:nth-child(2) > a");
    By noRecords = By.cssSelector("#content > div.flexi-grid > div");
    
    public CCE_ManualEnrichPage(WebDriver driver) {
        super(driver);
    }
    
    public WebElement getBreadcrumb() {
        return driver.findElement(DataItems.breadcrumbLocator);
    }
    
    public WebElement getOrderNoField() {
        return driver.findElement(orderNoField);
    }
    
    public WebElement getCustomerNameField() {
        return driver.findElement(custNameField);
    }
    
    public WebElement getScenarioField() {
        return driver.findElement(scenarioField);
    }
    
    public WebElement getRequestTypeField() {
        return driver.findElement(requestTypeField);
    }
    
    public WebElement getHubField() {
        return driver.findElement(hubField);
    }
    
    public WebElement getRequesterField() {
        return driver.findElement(requesterField);
    }
    
    public WebElement getFCENameField() {
        return driver.findElement(fceNameField);
    }
    
    public WebElement getCreatedDateFromField() {
        return driver.findElement(createdDateFromField);
    }
    
    public WebElement getCreatedDateToField() {
        return driver.findElement(createdDateToField);
    }
    
    public WebElement getSearchButton() {
        return driver.findElement(searchButton);
    }
    
    public WebElement getResetButton() {
        return driver.findElement(resetButton);
    }
    
    public CCE_ManualEnrichPage setOrderNo(String item) {
        CommonTask.setSearchField(driver,orderNoField,item);
        return new CCE_ManualEnrichPage(driver);
    }
    
    public CCE_ManualEnrichPage setCustomerName(String item) {
        CommonTask.setSearchField(driver,custNameField,item);
        return new CCE_ManualEnrichPage(driver);
    }
    
    public CCE_ManualEnrichPage pressSearch() {
        WebElement wait = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(searchButton));
        driver.findElement(searchButton);
        return new CCE_ManualEnrichPage(driver);
    }
    
    public CCE_ManualEnrichPage pressReset() {
        WebElement wait = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(resetButton));
        driver.findElement(resetButton);
        return new CCE_ManualEnrichPage(driver);
    }
    
    public CCE_OrderViewPage pressView(int row) {
        By viewButton = By.cssSelector("#content > div.flexi-grid > table > tbody > tr:nth-child("+row+") > td:nth-child(2) > a");
        
        WebElement wait = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(viewButton));
        
        driver.findElement(viewButton).click();
        
        return new CCE_OrderViewPage(driver);
    }
    
    public CCE_EnrichOrderPage pressEnrich(int row) {
        By enrichButton = By.cssSelector("#content > div.flexi-grid > table > tbody > tr:nth-child("+row+") > td:nth-child(3) > a");
        
        WebElement wait = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(enrichButton));
        
        driver.findElement(enrichButton).click();
        
        return new CCE_EnrichOrderPage(driver);
    }
    
    public String getOrderNo(int row) {
        By orderNoCell = By.cssSelector("#content > div.flexi-grid > table > tbody > tr:nth-child("+row+") > td:nth-child(6)");
        
        return driver.findElement(orderNoCell).getText();
    }
    
    public boolean findOrder(String orderNo) {
        for (int i = 2; i < 8; i++) {
            By orderNoCell = By.cssSelector("#content > div.flexi-grid > table > tbody > tr:nth-child("+i+") > td:nth-child(6)");
            WebElement wait = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.visibilityOfElementLocated(orderNoCell));
            
            if (driver.findElement(orderNoCell).getText().equals(orderNo)) {
                return true;
            }
        }
        
        return false;
    }
    
    public void checkFields() {
        //Wait for all elements to be clickable
        WebElement orderNo = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(orderNoField));
        WebElement custName = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(custNameField));
        WebElement scenario = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(scenarioField));
        WebElement requestType = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(requestTypeField));
        WebElement hub = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(hubField));
        WebElement requester = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(requesterField));
        WebElement fceName = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(fceNameField));
        WebElement createdFrom = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(createdDateFromField));
        WebElement createdTo = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(createdDateToField));
        WebElement search = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(searchButton));
        WebElement reset = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(resetButton));
        
        //Assert all elements are displayed
        AssertJUnit.assertTrue("Manual Enrich Page: Order No Field not displayed correctly",getOrderNoField().isDisplayed());
        AssertJUnit.assertTrue("Manual Enrich Page: Customer Name Field not displayed correctly",getCustomerNameField().isDisplayed());
        AssertJUnit.assertTrue("Manual Enrich Page: Scenario Field not displayed correctly",getScenarioField().isDisplayed());
        AssertJUnit.assertTrue("Manual Enrich Page: Request Type Field not displayed correctly",getRequestTypeField().isDisplayed());
        AssertJUnit.assertTrue("Manual Enrich Page: Hub Field not displayed correctly",getHubField().isDisplayed());
        AssertJUnit.assertTrue("Manual Enrich Page: Requester Field not displayed correctly",getRequesterField().isDisplayed());
        AssertJUnit.assertTrue("Manual Enrich Page: FCE Name Field not displayed correctly",getFCENameField().isDisplayed());
        AssertJUnit.assertTrue("Manual Enrich Page: Created Date From Field not displayed correctly",getCreatedDateFromField().isDisplayed());
        AssertJUnit.assertTrue("Manual Enrich Page: Created Date To Field not displayed correctly",getCreatedDateToField().isDisplayed());
        AssertJUnit.assertTrue("Manual Enrich Page: Search Button not displayed correctly",getSearchButton().isDisplayed());
        AssertJUnit.assertTrue("Manual Enrich Page: Reset Button not displayed correctly",getResetButton().isDisplayed());
    }
    
    public boolean checkForRecords() {
        try {
            WebElement wait = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.visibilityOfElementLocated(noRecords));
            return false;
        } catch(TimeoutException e) {
            return true;
        }
    }
    
    public void waitForElement() {
        WebElement wait = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(orderNoField));
    }
    
}