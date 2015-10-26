
package PageObjects;

import AutomationFramework.CommonTask;
import org.testng.AssertJUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class CCE_LRMLogPage extends WBA_BasePage {
    
    //Locators
    By breadcrumb = By.cssSelector("#content > h2");
    By orderNoField = By.id("s2id_filterLrmLogOrderNo");
    By deliveryPlantField = By.cssSelector("#s2id_filterLrmLogDeliveryPlantId > ul > li > input");
    By shadeNameField = By.id("filterLrmLogShadeName");
    By orderLineField = By.id("filterLrmLogLineNo");
    By supplyPlantField = By.cssSelector("#s2id_filterLrmLogSupplyPlantId > ul > li > input");
    By orderFromDateField = By.id("filterLrmLogUpdatedFrom");
    By orderToDateField = By.id("filterLrmLogUpdatedTo");
    By labsosDateFromField = By.id("filterSampleOrderLineHistoryHistoryFrom");
    By labsosDateToField = By.id("filterSampleOrderLineHistoryHistoryTo");
    By shadeCodeField = By.id("s2id_filterSampleOrderLineShadeId");
    By errorMessageField = By.id("s2id_filterIsSuccess");
    By searchButton = By.cssSelector("#FilterLrmLogForm > div.actions > ul > li:nth-child(1)");
    By resetButton = By.cssSelector("#FilterLrmLogForm > div.actions > ul > li:nth-child(2)");
    By exportButton = By.cssSelector("#content > div.actions > ul > li");
    By viewButton = By.cssSelector("#content > div.flexi-grid > table > tbody > tr:nth-child(2) > td:nth-child(12)");
    By resendButton = By.cssSelector("#content > div.flexi-grid > table > tbody > tr:nth-child(2) > td:nth-child(13)");
    
    
    public CCE_LRMLogPage(WebDriver passedDriver) {
        super(passedDriver);
    }
    
    public WebElement getBreadcrumb() {
        WebElement waitForBreadcrumb = new WebDriverWait(driver,8).until(ExpectedConditions.elementToBeClickable(breadcrumb));
        return driver.findElement(breadcrumb);
    }
    
    public WebElement getOrderNoField() {
        return driver.findElement(orderNoField);
    }
    
    public WebElement getDeliveryPlantField() {
        return driver.findElement(deliveryPlantField);
    }
    
    public WebElement getShadeNameField() {
        return driver.findElement(shadeNameField);
    }
    
    public WebElement getOrderLineField() {
        return driver.findElement(orderLineField);
    }
    
    public WebElement getSupplyPlantField() {
        return driver.findElement(supplyPlantField);
    }
    
    public WebElement getOrderDateFromField() {
        return driver.findElement(orderFromDateField);
    }
    
    public WebElement getOrderDateToField() {
        return driver.findElement(orderToDateField);
    }
    
    public WebElement getLabSosDateFromField() {
        return driver.findElement(labsosDateFromField);
    }
    
    public WebElement getLabSosDateToField() {
        return driver.findElement(labsosDateToField);
    }
    
    public WebElement getShadeCodeField() {
        return driver.findElement(shadeCodeField);
    }
    
    public WebElement getErrorMessageField() {
        return driver.findElement(errorMessageField);
    }
    
    public WebElement getSearchButton() {
        return driver.findElement(searchButton);
    }
    
    public WebElement getResetButton() {
        return driver.findElement(resetButton);
    }
    
    public WebElement getExportButton() {
        return driver.findElement(exportButton);
    }
    
    public WebElement getViewButton() {
        return driver.findElement(viewButton);
    }
    
    public WebElement getResendButton() {
        return driver.findElement(resendButton);
    }
    
    public CCE_LRMLogPage setOrderNo(String item) {
        CommonTask.setSearchField(driver, orderNoField, item);
        return this;
    }
    
    public CCE_LRMLogPage setShadeCode(String item) {
        CommonTask.setSearchField(driver, shadeCodeField, item);
        return this;
    }
    
    public CCE_LRMLogPage setErrorMessage(String item) {
        CommonTask.setSearchField(driver, errorMessageField, item);
        return this;
    }
    
    public CCE_OrderViewPage pressView() {
        WebElement waitForClickable = new WebDriverWait(driver,5).until(ExpectedConditions.elementToBeClickable(viewButton));
        driver.findElement(viewButton).click();
        return new CCE_OrderViewPage(driver);
    }
    
    public CCE_LRMLogPage pressResend() {
        WebElement waitForClickable = new WebDriverWait(driver,5).until(ExpectedConditions.elementToBeClickable(resendButton));
        driver.findElement(resendButton).click();
        return this;
    }
    
    public Ecomm_ExportDownloadPage pressExport() {
        WebElement waitForClickable = new WebDriverWait(driver,5).until(ExpectedConditions.elementToBeClickable(exportButton));
        driver.findElement(exportButton).click();
        return new Ecomm_ExportDownloadPage(driver);
    }
    
    public CCE_LRMLogPage pressReset() {
        WebElement waitForClickable = new WebDriverWait(driver,5).until(ExpectedConditions.elementToBeClickable(resetButton));
        driver.findElement(resetButton).click();
        return new CCE_LRMLogPage(driver);
    }
    
    public CCE_LRMLogPage pressSearch() {
        WebElement waitForClickable = new WebDriverWait(driver,5).until(ExpectedConditions.elementToBeClickable(searchButton));
        driver.findElement(searchButton).click();
        return new CCE_LRMLogPage(driver);
    }
    
    public void checkFields() {
        //Wait for all elements to be clickable
        WebElement waitForOrderNo = new WebDriverWait(driver,5).until(ExpectedConditions.elementToBeClickable(orderNoField));
        WebElement waitForDelPlant = new WebDriverWait(driver,5).until(ExpectedConditions.elementToBeClickable(deliveryPlantField));
        WebElement waitForShadeName = new WebDriverWait(driver,5).until(ExpectedConditions.elementToBeClickable(shadeNameField));
        WebElement waitForOrderLine = new WebDriverWait(driver,5).until(ExpectedConditions.elementToBeClickable(orderLineField));
        WebElement waitForSupplyPlant = new WebDriverWait(driver,5).until(ExpectedConditions.elementToBeClickable(supplyPlantField));
        WebElement waitForOrderFrom = new WebDriverWait(driver,5).until(ExpectedConditions.elementToBeClickable(orderFromDateField));
        WebElement waitForOrderTo = new WebDriverWait(driver,5).until(ExpectedConditions.elementToBeClickable(orderToDateField));
        WebElement waitForLabSosFrom = new WebDriverWait(driver,5).until(ExpectedConditions.elementToBeClickable(labsosDateFromField));
        WebElement waitForLabSosTo = new WebDriverWait(driver,5).until(ExpectedConditions.elementToBeClickable(labsosDateToField));
        WebElement waitForShadeCode = new WebDriverWait(driver,5).until(ExpectedConditions.elementToBeClickable(shadeCodeField));
        WebElement waitForErrorMessage = new WebDriverWait(driver,5).until(ExpectedConditions.elementToBeClickable(errorMessageField));
        WebElement waitForSearch = new WebDriverWait(driver,5).until(ExpectedConditions.elementToBeClickable(searchButton));
        WebElement waitForExport = new WebDriverWait(driver,5).until(ExpectedConditions.elementToBeClickable(exportButton));
        WebElement waitForReset = new WebDriverWait(driver,5).until(ExpectedConditions.elementToBeClickable(resetButton));
        WebElement waitForView = new WebDriverWait(driver,5).until(ExpectedConditions.elementToBeClickable(viewButton));
        WebElement waitForResend = new WebDriverWait(driver,5).until(ExpectedConditions.elementToBeClickable(resendButton));
        
        //Assert all elements are displayed
        AssertJUnit.assertTrue("LRM Log Page: Order Number field not displayed",getOrderNoField().isDisplayed());
        AssertJUnit.assertTrue("LRM Log Page: Delivery Plant field not displayed",getDeliveryPlantField().isDisplayed());
        AssertJUnit.assertTrue("LRM Log Page: Shade Name field not displayed",getShadeNameField().isDisplayed());
        AssertJUnit.assertTrue("LRM Log Page: Order Line field not displayed",getOrderLineField().isDisplayed());
        AssertJUnit.assertTrue("LRM Log Page: Supply Plant field not displayed",getSupplyPlantField().isDisplayed());
        AssertJUnit.assertTrue("LRM Log Page: Order Date From field not displayed",getOrderDateFromField().isDisplayed());
        AssertJUnit.assertTrue("LRM Log Page: Order Date To field not displayed",getOrderDateToField().isDisplayed());
        AssertJUnit.assertTrue("LRM Log Page: Lab SOS Date From field not displayed",getLabSosDateFromField().isDisplayed());
        AssertJUnit.assertTrue("LRM Log Page: Lab SOS Date To field not displayed",getLabSosDateToField().isDisplayed());
        AssertJUnit.assertTrue("LRM Log Page: Shade Code To field not displayed",getShadeCodeField().isDisplayed());
        AssertJUnit.assertTrue("LRM Log Page: Error Message field not displayed",getErrorMessageField().isDisplayed());
        AssertJUnit.assertTrue("LRM Log Page: Search Button not displayed",getSearchButton().isDisplayed());
        AssertJUnit.assertTrue("LRM Log Page: Export Button not displayed",getExportButton().isDisplayed());
        AssertJUnit.assertTrue("LRM Log Page: Reset Button not displayed",getResetButton().isDisplayed());
        AssertJUnit.assertTrue("LRM Log Page: View Button not displayed",getViewButton().isDisplayed());
        AssertJUnit.assertTrue("LRM Log Page: Resend Button not displayed",getResendButton().isDisplayed());
    
    }
    
}
