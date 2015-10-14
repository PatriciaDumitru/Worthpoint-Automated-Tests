
package PageObjects;

import AutomationFramework.CommonTask;
import AutomationFramework.TestSuiteOLD;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class CCE_FCETaskStatusPage extends WBA_BasePage {
    
    //Locators
    By breadcrumb = By.cssSelector("#content > h2");
    By salesOrgField = By.cssSelector("#s2id_filterCustomerSalesOrgId > ul > li > input");
    By custNameField = By.id("filterShipToPartyCustomerId");
    By orderDateFromField = By.id("filterSampleOrderCreatedFrom");
    By orderDateToField = By.id("filterSampleOrderCreatedTo");
    By hubField = By.cssSelector("#s2id_filterShipToPartyHubId > ul > li > input");
    By taskTypeField = By.cssSelector("#s2id_filterTaskModeId > ul > li > input");
    By fceNameField = By.id("filterShipToPartyFceId");
    By taskStatusField = By.id("s2id_filterIsCompleted");
    By printButton = By.cssSelector("#FilterTaskStatusReportForm > div.actions > ul > li:nth-child(1)");
    By exportToExcelButton = By.cssSelector("#FilterTaskStatusReportForm > div.actions > ul > li:nth-child(2)");
    By resetButton = By.cssSelector("#FilterTaskStatusReportForm > div.actions > ul > li:nth-child(3)");
    By printFrame = By.id("TB_iframeContent");
    
    public CCE_FCETaskStatusPage(WebDriver passedDriver) {
        super(passedDriver);
    }
    
    public WebElement getBreadcrumb() {
        WebElement waitForBreadcrumb = new WebDriverWait(driver,10).until(ExpectedConditions.visibilityOfElementLocated(breadcrumb));
        return driver.findElement(breadcrumb);
    }
    
    public WebElement getSalesOrgField() {
        return driver.findElement(salesOrgField);
    }
    
    public WebElement getCustNameField() {
        return driver.findElement(custNameField);
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
    
    public WebElement getTaskTypeField() {
        return driver.findElement(taskTypeField);
    }
    
    public WebElement getFceNameField() {
        return driver.findElement(fceNameField);
    }
    
    public WebElement getTaskStatusField() {
        return driver.findElement(taskStatusField);
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
    
    
    public CCE_FCETaskStatusPage setSalesOrg(String item) {
        CommonTask.setChoiceField(driver,salesOrgField,item);
        return this;
    }
    
    public CCE_FCETaskStatusPage setCustName(String item) {
        WebElement waitForClickable = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(custNameField));
        driver.findElement(custNameField).click();
        driver.findElement(custNameField).sendKeys(item);
        return this;
    }
    
    public CCE_FCETaskStatusPage setHub(String item) {
        CommonTask.setChoiceField(driver,hubField,item);
        return this;
    }
    
    public CCE_FCETaskStatusPage setTaskType(String item) {
        CommonTask.setChoiceField(driver,taskTypeField,item);
        return this;
    }
    
    public CCE_FCETaskStatusPage setFceName(String item) {
        WebElement waitForClickable = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(fceNameField));
        driver.findElement(fceNameField).click();
        driver.findElement(fceNameField).sendKeys(item);
        return this;
    }
    
    public CCE_FCETaskStatusPage setTaskStatus(String item) {
        CommonTask.setSearchField(driver, taskStatusField, item);
        return this;
    }
    
    public CCE_OrderViewPage pressPrint() {
        WebElement waitForClickable = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(printButton));
        driver.findElement(printButton).click();
        return new CCE_OrderViewPage(driver);
    }
    
    public Ecomm_ExportDownloadPage pressExport() {
        WebElement waitForClickable = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(exportToExcelButton));
        driver.findElement(exportToExcelButton).click();
        return new Ecomm_ExportDownloadPage(driver);
    }
    
    public CCE_FCETaskStatusPage pressReset() {
        WebElement waitForClickable = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(resetButton));
        driver.findElement(resetButton).click();
        
        return new CCE_FCETaskStatusPage(driver);
    }
    
    public void checkFields() {
        //Wait for all fields to be clickable
        WebElement waitForSalesOrg = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(salesOrgField));
        WebElement waitForCustName = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(custNameField));
        WebElement waitForOrderFrom = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(orderDateFromField));
        WebElement waitForOrderTo = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(orderDateToField));
        WebElement waitForHub = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(hubField));
        WebElement waitForTaskType = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(taskTypeField));
        WebElement waitForFCE = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(fceNameField));
        WebElement waitForTaskStatus = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(taskStatusField));
        WebElement waitForPrint = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(printButton));
        WebElement waitForExport = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(exportToExcelButton));
        WebElement waitForReset = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(resetButton));
        
        //Assert all elements are displayed
        Assert.assertTrue("FCE Task Status Page: Sales Org Field not displayed correctly",getSalesOrgField().isDisplayed());
        Assert.assertTrue("FCE Task Status Page: Customer name Field not displayed correctly",getCustNameField().isDisplayed());
        Assert.assertTrue("FCE Task Status Page: Order Date From Field not displayed correctly",getOrderDateFromField().isDisplayed());
        Assert.assertTrue("FCE Task Status Page: Order Date To Field not displayed correctly",getOrderDateToField().isDisplayed());
        Assert.assertTrue("FCE Task Status Page: Hub Field not displayed correctly",getHubField().isDisplayed());
        Assert.assertTrue("FCE Task Status Page: Task Type Field not displayed correctly",getTaskTypeField().isDisplayed());
        Assert.assertTrue("FCE Task Status Page: FCE Name Field not displayed correctly",getFceNameField().isDisplayed());
        Assert.assertTrue("FCE Task Status Page: Task Status Field not displayed correctly",getTaskStatusField().isDisplayed());
        Assert.assertTrue("FCE Task Status Page: Print button not displayed correctly",getPrintButton().isDisplayed());
        Assert.assertTrue("FCE Task Status Page: Export button not displayed correctly",getExportButton().isDisplayed());
        Assert.assertTrue("FCE Task Status Page: Reset button not displayed correctly",getResetButton().isDisplayed());
    
    }
    
}
