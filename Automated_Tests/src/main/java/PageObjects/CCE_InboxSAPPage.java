
package PageObjects;

import AutomationFramework.CommonTask;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

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
        WebElement waitForBreadcrumb = new WebDriverWait(driver,10).until(ExpectedConditions.visibilityOfElementLocated(breadcrumb));
        return driver.findElement(breadcrumb);
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
        WebElement waitForButton = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(listOrdersButton));
        driver.findElement(listOrdersButton).submit();
        return this;
    }
    
    public CCE_InboxSAPPage pressReset() {
        WebElement waitForButton = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(resetButton));
        driver.findElement(resetButton).click();
        return this;
    }
    
    public void checkFields() {
        //Wait for all to be clickable
        WebElement waitForOrderNo = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(orderNoField));
        WebElement waitForCustCode = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(custCodeField));
        WebElement waitForHub = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(hubField));
        WebElement waitForOrderFrom = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(orderFromField));
        WebElement waitForOrderTo = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(orderToField));
        WebElement waitForRequester = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(requesterField));
        WebElement waitForSalesOrg = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(salesOrgField));
        WebElement waitForListOrders = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(listOrdersButton));
        WebElement waitForReset = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(resetButton));
        
        //Assert all fields are displayed
        Assert.assertTrue("Inbox SAP page: Order No Field not displayed correctly",getOrderNoField().isDisplayed());
        Assert.assertTrue("Inbox SAP page: Customer Code Field not displayed correctly",getCustCodeField().isDisplayed());
        Assert.assertTrue("Inbox SAP page: Hub Field not displayed correctly",getHubField().isDisplayed());
        Assert.assertTrue("Inbox SAP page: Order Date From field not displayed correctly",getOrderDateFromField().isDisplayed());
        Assert.assertTrue("Inbox SAP page: Order Date To field not displayed correctly",getOrderDateToField().isDisplayed());
        Assert.assertTrue("Inbox SAP page: Requester field not displayed correctly",getRequesterField().isDisplayed());
        Assert.assertTrue("Inbox SAP page: Sales Organisation field not displayed correctly",getSalesOrgField().isDisplayed());
        
    }
}
