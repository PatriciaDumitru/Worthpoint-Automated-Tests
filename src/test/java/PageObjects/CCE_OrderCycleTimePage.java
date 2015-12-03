
package PageObjects;

import AutomationFramework.CommonTask;
import AutomationFramework.DataItems;
import AutomationFramework.Wait;
import org.testng.AssertJUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class CCE_OrderCycleTimePage extends WBA_BasePage {
    
    //Locators
    By breadcrumb = By.cssSelector("#content > h2");
    By orderNoField = By.id("s2id_filterSampleOrderLineOrderId");
    By custNameField = By.cssSelector("#s2id_filterSampleOrderCustomerId > ul > li > input");
    By orderLineField = By.cssSelector("#FilterCycleTimeReportForm > table > tbody > tr:nth-child(1) > td:nth-child(4) > input");
    By orderDateFromField = By.id("filterSampleOrderCreatedFrom");
    By orderDateToField = By.id("filterSampleOrderCreatedTo");
    By hubField = By.cssSelector("#s2id_filterSampleOrderHubId > ul > li > input");
    By fceNameField = By.cssSelector("#s2id_filterSampleOrderFceId > ul > li > input");
    By shipToNameField = By.cssSelector("#s2id_filterSampleOrderShipToPartyId > ul > li > input");
    By printButton = By.cssSelector("#FilterCycleTimeReportForm > div.actions > ul > li:nth-child(1)");
    By exportToExcelButton = By.cssSelector("#FilterCycleTimeReportForm > div.actions > ul > li:nth-child(2)");
    By resetButton = By.cssSelector("#FilterCycleTimeReportForm > div.actions > ul > li:nth-child(3)");
    By printFrame = By.id("TB_iframeContent");
    By filterForm = By.id("FilterCycleTimeReportForm");
    
    public CCE_OrderCycleTimePage(WebDriver passedDriver) {
        super(passedDriver);
    }
    
    public WebElement getBreadcrumb() {
        return Wait.visible(driver,breadcrumb);
    }
    
    public WebElement getOrderNoField() {
        return driver.findElement(orderNoField);
    }
    
    public WebElement getCustNameField() {
        return driver.findElement(custNameField);
    }
     
    public WebElement getOrderLineField() {
        return driver.findElement(orderLineField);
    }
    
    public WebElement getOrderDateFromField() {
        return driver.findElement(orderDateFromField);
    }
    
    public WebElement getOrderDateToField() {
        return driver.findElement(orderDateToField);
    }
    
    public WebElement getHubField() {
        return driver.findElement(hubField);
    }
    
    public WebElement getFceNameField() {
        return driver.findElement(fceNameField);
    }
    
    public WebElement getPrintButton() {
        return driver.findElement(printButton);
    }
    
    public WebElement getResetButton() {
        return driver.findElement(resetButton);
    }
    
    public WebElement getExportButton() {
        return driver.findElement(exportToExcelButton);
    }
    
    public CCE_OrderCycleTimePage setCustName(String item) {
        CommonTask.setChoiceField(driver,custNameField,item);
        
        return this;
    }
    
    public CCE_OrderCycleTimePage setOrderNo(String item) {
        CommonTask.setSearchField(driver, orderNoField, item);
        
        return this;
    }
    
    public CCE_OrderCycleTimePage setOrderLine(String item) {
        WebElement field = Wait.clickable(driver,orderLineField);
        field.click();
        field.sendKeys(item);
        
        return this;
    }
    
    public CCE_OrderCycleTimePage setHub(String item) {
        CommonTask.setChoiceField(driver,hubField,item);
        
        return this;
    }
    
    public CCE_OrderCycleTimePage setFCEName(String item) {
        CommonTask.setChoiceField(driver,fceNameField,item);
        
        return this;
    }
    
    public CCE_OrderCycleTimePage setShipToPartyName(String item) {
        CommonTask.setChoiceField(driver,shipToNameField,item);
        
        return this;
    }
    
    public CCE_OrderViewPage pressPrint() {
        WebElement print = Wait.clickable(driver,printButton);
        print.click();
        return new CCE_OrderViewPage(driver);
    }
    
    public Ecomm_ExportDownloadPage pressExport() {
        WebElement export = Wait.clickable(driver,exportToExcelButton);
        export.click();
        
        return new Ecomm_ExportDownloadPage(driver);
    }
    
    public CCE_OrderCycleTimePage pressReset() {
        WebElement reset = Wait.clickable(driver,resetButton);
        reset.click();
        return this;
    }
    
    public void checkFields() {
        //Wait for all fields to be clickable
        WebElement orderNo = Wait.clickable(driver,orderNoField);
        WebElement custName = Wait.clickable(driver,custNameField);
        WebElement orderLine = Wait.clickable(driver,orderLineField);
        WebElement orderFrom = Wait.clickable(driver,orderDateFromField);
        WebElement orderTo = Wait.clickable(driver,orderDateToField);
        WebElement hub = Wait.clickable(driver,hubField);
        WebElement fce = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(fceNameField));
        WebElement shipTo = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(shipToNameField));
        WebElement print = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(printButton));
        WebElement export = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(exportToExcelButton));
        WebElement reset = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(resetButton));
        
        //Assert all elements displayed
        AssertJUnit.assertTrue("Order Cycle Time Page: Order No Field not displayed correctly",getOrderNoField().isDisplayed());
        AssertJUnit.assertTrue("Order Cycle Time Page: Customer name Field not displayed correctly",getCustNameField().isDisplayed());
        AssertJUnit.assertTrue("Order Cycle Time Page: Order Date From Field not displayed correctly",getOrderDateFromField().isDisplayed());
        AssertJUnit.assertTrue("Order Cycle Time Page: Order Date To Field not displayed correctly",getOrderDateToField().isDisplayed());
        AssertJUnit.assertTrue("Order Cycle Time Page: Hub Field not displayed correctly",getHubField().isDisplayed());
        AssertJUnit.assertTrue("Order Cycle Time Page: FCE Name Field not displayed correctly",getFceNameField().isDisplayed());
        AssertJUnit.assertTrue("Order Cycle Time Page: Print button not displayed correctly",getPrintButton().isDisplayed());
        AssertJUnit.assertTrue("Order Cycle Time Page: Export button not displayed correctly",getExportButton().isDisplayed());
        AssertJUnit.assertTrue("Order Cycle Time Page: Reset button not displayed correctly",getResetButton().isDisplayed());        
        
    }
    
}
