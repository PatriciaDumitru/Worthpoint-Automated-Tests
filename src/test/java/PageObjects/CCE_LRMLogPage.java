
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
    By resendButton = By.xpath(".//*[@id='content']/div[2]/table/tbody/tr[2]/td[13]/a/span");
    
    
    public CCE_LRMLogPage(WebDriver passedDriver) {
        super(passedDriver);
    }
    
    public WebElement getBreadcrumb() {
        return Wait.visible(driver, breadcrumb);
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
        WebElement view = Wait.clickable(driver, viewButton);
        view.click();
        return new CCE_OrderViewPage(driver);
    }
    
    public CCE_LRMLogPage pressResend() {
        WebElement resend = Wait.clickable(driver,resendButton);
        resend.click();
        return this;
    }
    
    public Ecomm_ExportDownloadPage pressExport() {
        WebElement export = Wait.clickable(driver,exportButton);
        export.click();
        return new Ecomm_ExportDownloadPage(driver);
    }
    
    public CCE_LRMLogPage pressReset() {
        WebElement reset = Wait.clickable(driver,resetButton);
        reset.click();
        return new CCE_LRMLogPage(driver);
    }
    
    public CCE_LRMLogPage pressSearch() {
        WebElement search = Wait.clickable(driver,searchButton);
        search.click();
        return new CCE_LRMLogPage(driver);
    }
    
    public void checkFields() {
        //Wait for all elements to be clickable
        WebElement orderNo = Wait.clickable(driver,orderNoField);
        WebElement delPlant = Wait.clickable(driver,deliveryPlantField);
        WebElement shadeName = Wait.clickable(driver,shadeNameField);
        WebElement orderLine = Wait.clickable(driver,orderLineField);
        WebElement supplyPlant = Wait.clickable(driver,supplyPlantField);
        WebElement orderFrom = Wait.clickable(driver,orderFromDateField);
        WebElement orderTo = Wait.clickable(driver,orderToDateField);
        WebElement labSosFrom = Wait.clickable(driver,labsosDateFromField);
        WebElement labSosTo = Wait.clickable(driver,labsosDateToField);
        WebElement shadeCode = Wait.clickable(driver,shadeCodeField);
        WebElement errorMessage = Wait.clickable(driver,errorMessageField);
        WebElement search = Wait.clickable(driver,searchButton);
        WebElement export = Wait.clickable(driver,exportButton);
        WebElement reset = Wait.clickable(driver,resetButton);
        WebElement view = Wait.clickable(driver,viewButton);
        WebElement resend = Wait.clickable(driver,resendButton);
        
        //Assert all elements are displayed
        AssertJUnit.assertTrue("LRM Log Page: Order Number field not displayed",orderNo.isDisplayed());
        AssertJUnit.assertTrue("LRM Log Page: Delivery Plant field not displayed",delPlant.isDisplayed());
        AssertJUnit.assertTrue("LRM Log Page: Shade Name field not displayed",shadeName.isDisplayed());
        AssertJUnit.assertTrue("LRM Log Page: Order Line field not displayed",orderLine.isDisplayed());
        AssertJUnit.assertTrue("LRM Log Page: Supply Plant field not displayed",supplyPlant.isDisplayed());
        AssertJUnit.assertTrue("LRM Log Page: Order Date From field not displayed",orderFrom.isDisplayed());
        AssertJUnit.assertTrue("LRM Log Page: Order Date To field not displayed",orderTo.isDisplayed());
        AssertJUnit.assertTrue("LRM Log Page: Lab SOS Date From field not displayed",labSosFrom.isDisplayed());
        AssertJUnit.assertTrue("LRM Log Page: Lab SOS Date To field not displayed",labSosTo.isDisplayed());
        AssertJUnit.assertTrue("LRM Log Page: Shade Code To field not displayed",shadeCode.isDisplayed());
        AssertJUnit.assertTrue("LRM Log Page: Error Message field not displayed",errorMessage.isDisplayed());
        AssertJUnit.assertTrue("LRM Log Page: Search Button not displayed",search.isDisplayed());
        AssertJUnit.assertTrue("LRM Log Page: Export Button not displayed",export.isDisplayed());
        AssertJUnit.assertTrue("LRM Log Page: Reset Button not displayed",reset.isDisplayed());
        AssertJUnit.assertTrue("LRM Log Page: View Button not displayed",view.isDisplayed());
        AssertJUnit.assertTrue("LRM Log Page: Resend Button not displayed",resend.isDisplayed());
    }
    
    public void waitForElement() {
        WebElement wait = Wait.clickable(driver,searchButton);
    }
    
    public String getErrorMessage(int row) {
        By stageCell = By.cssSelector("#content > div.flexi-grid > table > tbody > tr:nth-child("+row+") > td:nth-child(5)");
        
        return driver.findElement(stageCell).getText();
    }
    
    public String findOrder(String orderNo) {
        for (int i = 2; i < 8; i++) {
            By orderNoCell = By.cssSelector("#content > div.flexi-grid > table > tbody > tr:nth-child("+i+") > td:nth-child(6)");
            WebElement cell = Wait.visible(driver,orderNoCell);
            String cellValue = cell.getText();
            if (cellValue.equals(orderNo)) {
                return getErrorMessage(i);
            }
        }
        return null;
    }
    
}
