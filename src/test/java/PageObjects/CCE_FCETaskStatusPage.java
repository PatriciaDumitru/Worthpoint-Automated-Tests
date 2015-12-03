
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
        return Wait.visible(driver, breadcrumb);
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
        CommonTask.setTextField(driver, custNameField, item);
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
        CommonTask.setTextField(driver, fceNameField, item);
        return this;
    }
    
    public CCE_FCETaskStatusPage setTaskStatus(String item) {
        CommonTask.setSearchField(driver, taskStatusField, item);
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
    
    public CCE_FCETaskStatusPage pressReset() {
        WebElement reset = Wait.clickable(driver,resetButton);
        reset.click();
        
        return new CCE_FCETaskStatusPage(driver);
    }
    
    public void checkFields() {
        //Wait for all fields to be clickable
        WebElement salesOrg = Wait.clickable(driver,salesOrgField);
        WebElement custName = Wait.clickable(driver,custNameField);
        WebElement orderFrom = Wait.clickable(driver,orderDateFromField);
        WebElement orderTo = Wait.clickable(driver,orderDateToField);
        WebElement hub = Wait.clickable(driver,hubField);
        WebElement taskType = Wait.clickable(driver,taskTypeField);
        WebElement fce = Wait.clickable(driver,fceNameField);
        WebElement taskStatus = Wait.clickable(driver,taskStatusField);
        WebElement print = Wait.clickable(driver,printButton);
        WebElement export = Wait.clickable(driver,exportToExcelButton);
        WebElement reset = Wait.clickable(driver,resetButton);
        
        //Assert all elements are displayed
        AssertJUnit.assertTrue("FCE Task Status Page: Sales Org Field not displayed correctly",salesOrg.isDisplayed());
        AssertJUnit.assertTrue("FCE Task Status Page: Customer name Field not displayed correctly",custName.isDisplayed());
        AssertJUnit.assertTrue("FCE Task Status Page: Order Date From Field not displayed correctly",orderFrom.isDisplayed());
        AssertJUnit.assertTrue("FCE Task Status Page: Order Date To Field not displayed correctly",orderTo.isDisplayed());
        AssertJUnit.assertTrue("FCE Task Status Page: Hub Field not displayed correctly",hub.isDisplayed());
        AssertJUnit.assertTrue("FCE Task Status Page: Task Type Field not displayed correctly",taskType.isDisplayed());
        AssertJUnit.assertTrue("FCE Task Status Page: FCE Name Field not displayed correctly",fce.isDisplayed());
        AssertJUnit.assertTrue("FCE Task Status Page: Task Status Field not displayed correctly",taskStatus.isDisplayed());
        AssertJUnit.assertTrue("FCE Task Status Page: Print button not displayed correctly",print.isDisplayed());
        AssertJUnit.assertTrue("FCE Task Status Page: Export button not displayed correctly",export.isDisplayed());
        AssertJUnit.assertTrue("FCE Task Status Page: Reset button not displayed correctly",reset.isDisplayed());
    }
    
}
