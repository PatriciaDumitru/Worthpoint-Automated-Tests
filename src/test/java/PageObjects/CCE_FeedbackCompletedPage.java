
package PageObjects;

import AutomationFramework.CommonTask;
import AutomationFramework.DataItems;
import AutomationFramework.Wait;
import static PageObjects.WBA_BasePage.driver;
import org.testng.AssertJUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CCE_FeedbackCompletedPage extends WBA_BasePage {
    
    //Locators
    By breadcrumb = By.cssSelector("#content > h2");
    By salesOrgField = By.id("s2id_filterSampleOrderSalesOrgId");
    By hubField = By.id("s2id_filterSampleOrderHubId");
    By orderNoField = By.id("s2id_filterSampleOrderLineOrderId");
    By custNameField = By.id("s2id_filterSampleOrderCustomerId");
    By requesterField = By.id("s2id_filterSampleOrderRequesterId");
    By shadeCodeField = By.id("s2id_filterSampleOrderLineShadeId");
    By orderDateFromField = By.id("filterSampleOrderLineUpdatedTo");
    By orderDateToField = By.id("filterSampleOrderCreatedTo");
    By statusField = By.id("s2id_filterSampleOrderLineIsAccepted");
    By rematchField = By.id("s2id_filterSampleOrderLineIsRematchRequired");
    By fbDateFromField = By.id("filterSampleOrderLineUpdatedFrom");
    By fbDateToField = By.id("filterSampleOrderLineUpdatedTo");
    By listOrdersButton = By.cssSelector("#FilterFeedbackCompletedForm > div.actions > ul > li:nth-child(1)");
    By resetButton = By.cssSelector("#FilterFeedbackCompletedForm > div.actions > ul > li:nth-child(2)");
    By exportMenu = By.id("export-menu");
    By csvButton = By.cssSelector("#export-menu > ul > li:nth-child(1)");
    By txtButton = By.cssSelector("#export-menu > ul > li:nth-child(2)");
    By xlsButton = By.cssSelector("#export-menu > ul > li:nth-child(3)");
    By xlsxButton = By.cssSelector("#export-menu > ul > li:nth-child(4)");
    By filterForm = By.id("FilterFeedbackCompletedForm");
    
    
    public CCE_FeedbackCompletedPage(WebDriver passedDriver) {
        super(passedDriver);
    }
    
    public String getTitle() {
        WebElement breadcrumbField = Wait.visible(driver,breadcrumb);
        
        return breadcrumbField.getText();
    }
    
    public WebElement getSalesOrgField() {
        return driver.findElement(salesOrgField);
    }
    
    public WebElement getHubField() {
        return driver.findElement(hubField);
    }
    
    public WebElement getOrderNoField() {
        return driver.findElement(orderNoField);
    }
    
    public WebElement getCustNameField() {
        return driver.findElement(custNameField);
    }
    
    public WebElement getRequesterField() {
        return driver.findElement(requesterField);
    }
    
    public WebElement getShadeCodeField() {
        return driver.findElement(shadeCodeField);
    }
    
    public WebElement getOrderFromField() {
        return driver.findElement(orderDateFromField);
    }
    
    public WebElement getOrderToField() {
        return driver.findElement(orderDateToField);
    }
    
    public WebElement getStatusField() {
        return driver.findElement(statusField);
    }
    
    public WebElement getRematchField() {
        return driver.findElement(rematchField);
    }
    
    public WebElement getFbFromField() {
        return driver.findElement(fbDateFromField);
    }
    
    public WebElement getFbToField() {
        return driver.findElement(fbDateToField);
    }
    
    public WebElement getListOrdersButton() {
        return driver.findElement(listOrdersButton);
    }
    
    public WebElement getResetButton() {
        return driver.findElement(resetButton);
    }
    
    public WebElement getExportMenu() {
        return driver.findElement(exportMenu);
    }
    
    public CCE_FeedbackCompletedPage setSalesOrg(String item) {
        CommonTask.setSearchField(driver, salesOrgField, item);
        
        return this;
    }
    
    public CCE_FeedbackCompletedPage setHub(String item) {
        CommonTask.setSearchField(driver, hubField, item);
        
        return this;
    }
    
    public CCE_FeedbackCompletedPage setOrderNo(String item) {
        CommonTask.setSearchField(driver, orderNoField, item);
        
        return this;
    }
    
    public CCE_FeedbackCompletedPage setRequester(String item) {
        CommonTask.setSearchField(driver, requesterField, item);
        
        return this;
    }
    
    public CCE_FeedbackCompletedPage setCustName(String item) {
        CommonTask.setSearchField(driver, custNameField, item);
        
        return this;
    }
    
    public CCE_FeedbackCompletedPage setShadeCode(String item) {
        CommonTask.setSearchField(driver, shadeCodeField, item);
        
        return this;
    }
    
    public CCE_FeedbackCompletedPage setOrderDateFrom(String item) {
        CommonTask.setSearchField(driver, orderDateFromField, item);
        
        return this;
    }
    
    public CCE_FeedbackCompletedPage setOrderDateTo(String item) {
        CommonTask.setSearchField(driver, orderDateToField, item);
        
        return this;
    }
    
    public CCE_FeedbackCompletedPage setStatus(String item) {
        CommonTask.setSearchField(driver, statusField, item);
        
        return this;
    }
    
    public CCE_FeedbackCompletedPage setRematch(String item) {
        CommonTask.setSearchField(driver, rematchField, item);
        
        return this;
    }
    
    public CCE_FeedbackCompletedPage setFbDateFrom(String item) {
        CommonTask.setSearchField(driver, fbDateFromField, item);
        
        return this;
    }
    
    public CCE_FeedbackCompletedPage setFbDateToName(String item) {
        CommonTask.setSearchField(driver, fbDateToField, item);
        
        return this;
    }
    
    public CCE_FeedbackCompletedPage pressListOrders() {
        //Wait for element
        WebElement button = Wait.clickable(driver,listOrdersButton);
        
        driver.findElement(filterForm).submit();
        
        return this;
    }
    
    public CCE_FeedbackCompletedPage pressReset() {
        //Wait for element
        WebElement reset = Wait.clickable(driver,resetButton);
        
        reset.click();
        
        return this;  
    }
    
    public CCE_FeedbackCompletedPage exportAs(String type) {
        
        WebElement export = Wait.clickable(driver,exportMenu);
        
        Actions action = new Actions(driver);
        action.moveToElement(export).build().perform();
        
        By xlsx = By.partialLinkText("XLSX");
        WebElement xlsxBtn = Wait.clickable(driver, xlsx);
        xlsxBtn.click();
        
        return this;
    }
    
    public void checkFields() {
        //Wait for all to be clickable
        WebElement salesOrg = Wait.clickable(driver,salesOrgField);
        WebElement hub = Wait.clickable(driver,hubField);
        WebElement orderNo = Wait.clickable(driver,orderNoField);
        WebElement custName = Wait.clickable(driver,custNameField);
        WebElement requester = Wait.clickable(driver,requesterField);
        WebElement shadeCode = Wait.clickable(driver,shadeCodeField);
        WebElement dateFrom = Wait.clickable(driver,orderDateFromField);
        WebElement dateTo = Wait.clickable(driver,orderDateToField);
        WebElement status = Wait.clickable(driver,statusField);
        WebElement rematch = Wait.clickable(driver,rematchField);
        WebElement fbFrom = Wait.clickable(driver,fbDateFromField);
        WebElement fbTo = Wait.clickable(driver,fbDateToField);
        WebElement listOrders = Wait.clickable(driver,listOrdersButton);
        WebElement reset = Wait.clickable(driver,resetButton);
        WebElement export = Wait.clickable(driver,exportMenu);
        
        //Assert all elements are displayed
        AssertJUnit.assertTrue("Feedback Completed Page: Sales Organisation Field not displayed correctly",salesOrg.isDisplayed());
        AssertJUnit.assertTrue("Feedback Completed Page: Hub Field not displayed correctly",hub.isDisplayed());
        AssertJUnit.assertTrue("Feedback Completed Page: Order No Field not displayed correctly",orderNo.isDisplayed());
        AssertJUnit.assertTrue("Feedback Completed Page: Customer Name Field not displayed correctly",custName.isDisplayed());
        AssertJUnit.assertTrue("Feedback Completed Page: Requester Field not displayed correctly",requester.isDisplayed());
        AssertJUnit.assertTrue("Feedback Completed Page: Shade Code Field not displayed correctly",shadeCode.isDisplayed());
        AssertJUnit.assertTrue("Feedback Completed Page: Order Date From Field not displayed correctly",dateFrom.isDisplayed());
        AssertJUnit.assertTrue("Feedback Completed Page: Order Date To Field not displayed correctly",dateTo.isDisplayed());
        AssertJUnit.assertTrue("Feedback Completed Page: Status Field not displayed correctly",status.isDisplayed());
        AssertJUnit.assertTrue("Feedback Completed Page: Rematch Field not displayed correctly",rematch.isDisplayed());
        AssertJUnit.assertTrue("Feedback Completed Page: Feedback Date From Field not displayed correctly",fbFrom.isDisplayed());
        AssertJUnit.assertTrue("Feedback Completed Page: Feedback Date To Field not displayed correctly",fbTo.isDisplayed());
        AssertJUnit.assertTrue("Feedback Completed Page: List Orders Button not displayed correctly",listOrders.isDisplayed());
        AssertJUnit.assertTrue("Feedback Completed Page: Reset Button not displayed correctly",reset.isDisplayed());
        AssertJUnit.assertTrue("Feedback Completed Page: Export Menu not displayed correctly",export.isDisplayed());
               
    }
    
    
    
}
