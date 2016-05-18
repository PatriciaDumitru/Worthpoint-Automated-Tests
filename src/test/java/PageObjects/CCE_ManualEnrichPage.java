/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PageObjects;

import AutomationFramework.CommonTask;
import AutomationFramework.DataItems;
import AutomationFramework.Wait;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
        WebElement search = Wait.clickable(driver,searchButton);
        search.click();
        return new CCE_ManualEnrichPage(driver);
    }
    
    public CCE_ManualEnrichPage pressReset() {
        WebElement reset = Wait.clickable(driver,resetButton);
        reset.click();
        return new CCE_ManualEnrichPage(driver);
    }
    
    public CCE_OrderViewPage pressView(int row) {
        By viewButton = By.cssSelector("#content > div.flexi-grid > table > tbody > tr:nth-child("+row+") > td:nth-child(2) > a");
        
        WebElement view = Wait.clickable(driver,viewButton);
        
        view.click();
        
        return new CCE_OrderViewPage(driver);
    }
    
    public CCE_EnrichOrderPage pressEnrich(int row) {
        By enrichButton = By.cssSelector("#content > div.flexi-grid > table > tbody > tr:nth-child("+row+") > td:nth-child(3) > a");
        
        WebElement enrich = Wait.clickable(driver,enrichButton);
        
        enrich.click();
        
        return new CCE_EnrichOrderPage(driver);
    }
    
    public String getOrderNo(int row) {
        By orderNoCell = By.cssSelector("#content > div.flexi-grid > table > tbody > tr:nth-child("+row+") > td:nth-child(6)");
        
        return driver.findElement(orderNoCell).getText();
    }
    
    public int getRow(String orderNo) {
        for (int i = 2; i < 8; i++) {
            By orderNoCell = By.cssSelector("#content > div.flexi-grid > table > tbody > tr:nth-child("+i+") > td:nth-child(6)");
            if (driver.findElement(orderNoCell).getText().equals(orderNo)) {
                return i;
            } 
        }
        return -1;
    }
    
    public boolean findOrder(String orderNo) {
        for (int i = 2; i < 8; i++) {
            By orderNoCell = By.cssSelector("#content > div.flexi-grid > table > tbody > tr:nth-child("+i+") > td:nth-child(6)");
            WebElement cell = Wait.clickable(driver,orderNoCell);
            
            if (cell.getText().equals(orderNo)) {
                return true;
            }
        }
        
        return false;
    }
    
    public void checkFields() {
        //Wait for all elements to be clickable
        WebElement orderNo = Wait.clickable(driver,orderNoField);
        WebElement custName = Wait.clickable(driver,custNameField);
        WebElement scenario = Wait.clickable(driver,scenarioField);
        WebElement requestType = Wait.clickable(driver,requestTypeField);
        WebElement hub = Wait.clickable(driver,hubField);
        WebElement requester = Wait.clickable(driver,requesterField);
        WebElement fceName = Wait.clickable(driver,fceNameField);
        WebElement createdFrom = Wait.clickable(driver,createdDateFromField);
        WebElement createdTo = Wait.clickable(driver,createdDateToField);
        WebElement search = Wait.clickable(driver,searchButton);
        WebElement reset = Wait.clickable(driver,resetButton);
        
        //Assert all elements are displayed
        AssertJUnit.assertTrue("Manual Enrich Page: Order No Field not displayed correctly",orderNo.isDisplayed());
        AssertJUnit.assertTrue("Manual Enrich Page: Customer Name Field not displayed correctly",custName.isDisplayed());
        AssertJUnit.assertTrue("Manual Enrich Page: Scenario Field not displayed correctly",scenario.isDisplayed());
        AssertJUnit.assertTrue("Manual Enrich Page: Request Type Field not displayed correctly",requestType.isDisplayed());
        AssertJUnit.assertTrue("Manual Enrich Page: Hub Field not displayed correctly",hub.isDisplayed());
        AssertJUnit.assertTrue("Manual Enrich Page: Requester Field not displayed correctly",requester.isDisplayed());
        AssertJUnit.assertTrue("Manual Enrich Page: FCE Name Field not displayed correctly",fceName.isDisplayed());
        AssertJUnit.assertTrue("Manual Enrich Page: Created Date From Field not displayed correctly",createdFrom.isDisplayed());
        AssertJUnit.assertTrue("Manual Enrich Page: Created Date To Field not displayed correctly",createdTo.isDisplayed());
        AssertJUnit.assertTrue("Manual Enrich Page: Search Button not displayed correctly",search.isDisplayed());
        AssertJUnit.assertTrue("Manual Enrich Page: Reset Button not displayed correctly",reset.isDisplayed());
    }
    
    public boolean checkForRecords() {
        try {
            WebElement wait = Wait.visible(driver,noRecords);
            return false;
        } catch(TimeoutException e) {
            return true;
        }
    }
    
    public void waitForElement() {
        WebElement wait = Wait.clickable(driver,scenarioField);
    }
    
}