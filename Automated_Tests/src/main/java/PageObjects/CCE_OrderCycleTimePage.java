
package PageObjects;

import AutomationFramework.CommonTask;
import org.junit.Assert;
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
        WebElement waitForBreadcrumb = new WebDriverWait(driver,10).until(ExpectedConditions.visibilityOfElementLocated(breadcrumb));
        return driver.findElement(breadcrumb);
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
        WebElement waitForOrderLine = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(orderLineField));
        driver.findElement(orderLineField).click();
        driver.findElement(orderLineField).sendKeys(item);
        
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
        WebElement waitForClickable = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(printButton));
        driver.findElement(printButton).click();
        driver.findElement(printButton).click();
        return new CCE_OrderViewPage(driver);
    }
    
    public Ecomm_ExportDownloadPage pressExport() {
        WebElement waitForClickable = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(exportToExcelButton));
        driver.findElement(exportToExcelButton).click();
        
        return new Ecomm_ExportDownloadPage(driver);
    }
    
    public CCE_OrderCycleTimePage pressReset() {
        WebElement waitForClickable = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(resetButton));
        driver.findElement(resetButton).click();
        return this;
    }
    
    public void checkFields() {
        //Wait for all fields to be clickable
        WebElement waitForOrderNo = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(orderNoField));
        WebElement waitForCustName = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(custNameField));
        WebElement waitForOrderLine = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(orderLineField));
        WebElement waitForOrderFrom = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(orderDateFromField));
        WebElement waitForOrderTo = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(orderDateToField));
        WebElement waitForHub = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(hubField));
        WebElement waitForFce = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(fceNameField));
        WebElement waitForShipTo = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(shipToNameField));
        WebElement waitForPrintButton = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(printButton));
        WebElement waitForExportButton = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(exportToExcelButton));
        WebElement waitForResetButton = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(resetButton));
        
        //Assert all elements displayed
        Assert.assertTrue("Order Cycle Time Page: Order No Field not displayed correctly",getOrderNoField().isDisplayed());
        Assert.assertTrue("Order Cycle Time Page: Customer name Field not displayed correctly",getCustNameField().isDisplayed());
        Assert.assertTrue("Order Cycle Time Page: Order Date From Field not displayed correctly",getOrderDateFromField().isDisplayed());
        Assert.assertTrue("Order Cycle Time Page: Order Date To Field not displayed correctly",getOrderDateToField().isDisplayed());
        Assert.assertTrue("Order Cycle Time Page: Hub Field not displayed correctly",getHubField().isDisplayed());
        Assert.assertTrue("Order Cycle Time Page: FCE Name Field not displayed correctly",getFceNameField().isDisplayed());
        Assert.assertTrue("Order Cycle Time Page: Print button not displayed correctly",getPrintButton().isDisplayed());
        Assert.assertTrue("Order Cycle Time Page: Export button not displayed correctly",getExportButton().isDisplayed());
        Assert.assertTrue("Order Cycle Time Page: Reset button not displayed correctly",getResetButton().isDisplayed());        
        
    }
    
}
