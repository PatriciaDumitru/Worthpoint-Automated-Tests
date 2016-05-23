
package PageObjects;

import AutomationFramework.CommonTask;
import AutomationFramework.Wait;
import org.testng.AssertJUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CCE_InboxSAPPage extends WBA_BasePage {
    
    //Locators
    By breadcrumb = By.cssSelector("#content > h2");
    By orderNoField = By.id("s2id_filterSampleOrderLineOrderId");
    By custCodeField = By.id("s2id_filterSampleOrderCustomerIdCode");
    By hubField = By.id("s2id_filterSampleOrderHubId");
    By orderFromField = By.id("filterSampleOrderCreatedFrom");
    By orderToField = By.id("filterSampleOrderCreatedTo");
    By requesterField = By.id("s2id_filterSampleOrderRequesterId");
    By salesOrgField = By.id("s2id_filterSampleOrderSalesOrgId");
    By listOrdersButton = By.cssSelector("#FilterInboxSapForm > div.actions > ul > li:nth-child(1)");
    By resetButton = By.cssSelector("#FilterInboxSapForm > div.actions > ul > li:nth-child(2)");
    
    public CCE_InboxSAPPage(WebDriver passedDriver) {
        super(passedDriver);
    }
    
    public WebElement getBreadcrumb() {
        return Wait.visible(driver,breadcrumb);
    }
    
    public WebElement getOrderNoField() {
        return driver.findElement(orderNoField);
    }
    
    public WebElement getCustCodeField() {
        return driver.findElement(custCodeField);
    }
    
    public WebElement getHubField() {
        return driver.findElement(hubField);
    }
    
    public WebElement getOrderDateFromField() {
        return driver.findElement(orderFromField);
    }
    
    public WebElement getOrderDateToField() {
        return driver.findElement(orderToField);
    }
    
    public WebElement getRequesterField() {
        return driver.findElement(requesterField);
    }
    
    public WebElement getSalesOrgField() {
        return driver.findElement(salesOrgField);
    }
    
    public CCE_InboxSAPPage setOrderNo(String item) {
        CommonTask.setSearchField(driver,orderNoField,item);
        return this;
    }
    
    public CCE_InboxSAPPage setHub(String item) {
        CommonTask.setSearchField(driver,hubField,item);
        return this;
    }
    
    public CCE_InboxSAPPage setCustCode(String item) {
        CommonTask.setSearchField(driver,custCodeField,item);
        return this;
    }
    
    public CCE_InboxSAPPage setSalesOrg(String item) {
        CommonTask.setSearchField(driver,salesOrgField,item);
        return this;
    }
    
    public CCE_InboxSAPPage setRequester(String item) {
        CommonTask.setSearchField(driver,requesterField,item);
        return this;
    }
    
    public CCE_InboxSAPPage pressListOrders() {
        WebElement button = Wait.clickable(driver,listOrdersButton);
        button.submit();
        return this;
    }
    
    public CCE_InboxSAPPage pressReset() {
        Wait.clickable(driver,resetButton).click();
        return this;
    }
    
    public void checkFields() {
        //Wait for all to be clickable
        WebElement orderNo = Wait.clickable(driver,orderNoField);
        WebElement custCode = Wait.clickable(driver,custCodeField);
        WebElement hub = Wait.clickable(driver,hubField);
        WebElement orderFrom = Wait.clickable(driver,orderFromField);
        WebElement orderTo = Wait.clickable(driver,orderToField);
        WebElement requester = Wait.clickable(driver,requesterField);
        WebElement salesOrg = Wait.clickable(driver,salesOrgField);
        WebElement listOrders = Wait.clickable(driver,listOrdersButton);
        WebElement reset = Wait.clickable(driver,resetButton);
        
        //Assert all fields are displayed
        AssertJUnit.assertTrue("Inbox SAP page: Order No Field not displayed correctly",orderNo.isDisplayed());
        AssertJUnit.assertTrue("Inbox SAP page: Customer Code Field not displayed correctly",custCode.isDisplayed());
        AssertJUnit.assertTrue("Inbox SAP page: Hub Field not displayed correctly",hub.isDisplayed());
        AssertJUnit.assertTrue("Inbox SAP page: Order Date From field not displayed correctly",orderFrom.isDisplayed());
        AssertJUnit.assertTrue("Inbox SAP page: Order Date To field not displayed correctly",orderTo.isDisplayed());
        AssertJUnit.assertTrue("Inbox SAP page: Requester field not displayed correctly",requester.isDisplayed());
        AssertJUnit.assertTrue("Inbox SAP page: Sales Organisation field not displayed correctly",salesOrg.isDisplayed());
        AssertJUnit.assertTrue("Inbox SAP page: List Orders field not displayed correctly",listOrders.isDisplayed());
        AssertJUnit.assertTrue("Inbox SAP page: Reset field not displayed correctly",reset.isDisplayed());
        
    }
}
