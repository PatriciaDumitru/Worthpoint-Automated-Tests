
package PageObjects;

import AutomationFramework.CommonTask;
import static PageObjects.WBA_BasePage.driver;
import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class CCE_ReceivedHubPage extends WBA_BasePage {
    
    //Locator
    By breadcrumb = By.cssSelector("#content > h2");
    By hub = By.id("s2id_filterSampleOrderHubId");
    By orderNoField = By.id("s2id_filterSampleOrderLineOrderId");
    By custNameField = By.id("s2id_filterSampleOrderCustomerId");
    By orderDateFromField = By.id("filterSampleOrderCreatedFrom");
    By orderDateToField = By.id("filterSampleOrderCreatedTo");
    By fceNameField = By.id("s2id_filterSampleOrderFceId");

    By requesterField = By.id("s2id_filterSampleOrderRequesterId");
    By listOrdersButton = By.cssSelector("#FilterReceivedHubForm > div.actions > ul > li:nth-child(1)");
    By resetButton = By.cssSelector("#FilterReceivedHubForm > div.actions > ul > li:nth-child(2)");   
    By viewButton = By.cssSelector("#SampleOrderLineReceivedHubForm > div.flexi-grid > table > tbody > tr:nth-child(3) > td:nth-child(13)");
    By saveButton = By.cssSelector("#SampleOrderLineReceivedHubForm > div.actions > ul > li:nth-child(1)");
    By cancelButton = By.cssSelector("#SampleOrderLineReceivedHubForm > div.actions > ul > li:nth-child(2)");
    By filterForm = By.id("FilterReceivedHubForm");
    By flashMessage = By.id("flashMessage");
    By viewFrame = By.id("TB_iframeContent");
    By sendToCustButton = By.cssSelector("#SampleOrderLineReceivedHubForm > div.flexi-grid > table > tbody > tr:nth-child(3) > td:nth-child(6) > input");
    
    public CCE_ReceivedHubPage(WebDriver passedDriver) {
        super(passedDriver);
    }
    
    public String getTitle() {
        WebElement waitForVis = new WebDriverWait(driver,10).until(ExpectedConditions.visibilityOfElementLocated(breadcrumb));
        
        return driver.findElement(breadcrumb).getText();
    }
    
    public WebElement getHubField() {
        return driver.findElement(hub);
    }
    
    public WebElement getOrderNoField() {
        return driver.findElement(orderNoField);
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
    
    public WebElement getFceNameField() {
        return driver.findElement(fceNameField);
    }
    
    public WebElement getRequesterField() {
        return driver.findElement(requesterField);
    }
    
    public WebElement getListOrdersButton() {
        return driver.findElement(listOrdersButton);
    }
    
    public WebElement getResetButton() {
        return driver.findElement(resetButton);
    }
    
    public WebElement getSaveButton() {
        return driver.findElement(saveButton);
    }
    
    public WebElement getCancelButton() {
        return driver.findElement(cancelButton);
    }
    
    public CCE_ReceivedHubPage setOrderNo(String item) {
        CommonTask.setSearchField(driver, orderNoField, item);
        return this;
    }
    
    public CCE_ReceivedHubPage setCustName(String item) {
        CommonTask.setSearchField(driver, custNameField, item);
        return this;
    }
    
    public CCE_ReceivedHubPage setOrderDateFrom(String item) {
        CommonTask.setSearchField(driver, orderDateFromField, item);
        return this;
    }
    
    public CCE_ReceivedHubPage setOrderDateTo(String item) {
        CommonTask.setSearchField(driver, orderDateToField, item);
        return this;
    }
    
    public CCE_ReceivedHubPage setRequester(String item) {
        CommonTask.setSearchField(driver, requesterField, item);
        return this;
    }
    
    public CCE_ReceivedHubPage setFceName(String item) {
        CommonTask.setChoiceField(driver, fceNameField, item);
        return this;
    }
    
    public CCE_ReceivedHubPage setHub(String item) {
        CommonTask.setSearchField(driver, hub, item);
        return this;
    }
    
    public CCE_ReceivedHubPage pressListOrders() {
        //Wait for button
        WebElement waitForClickable = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(listOrdersButton));
        
        //submit form
        driver.findElement(filterForm).submit();
        
        return this;
    }
    
    public CCE_ReceivedHubPage pressReset() {
        //Wait for reset button
        WebElement waitForClickable = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(resetButton));
        
        driver.findElement(resetButton).click();
        
        return this;
    }
    
    public CCE_ReceivedHubPage pressSave() {
        WebElement waitForClickable = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(saveButton));
        
        driver.findElement(saveButton).click();
        
        Alert alert = new WebDriverWait(driver,10).until(ExpectedConditions.alertIsPresent());
        alert.accept();
        
        String message = driver.findElement(flashMessage).getText();
        
        if (message.contains("Order has been")) {
            System.out.println("Save successful. Message received: "+message);
        } else {
            System.out.println("***Save unsuccessful/unexpected message received: "+message +"***");
        }
        
        return this;
        
    }
    
    public CCE_OrderViewPage pressView() {
        WebElement waitForClickable = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(viewButton));
        
        driver.findElement(viewButton).click();
        
        return new CCE_OrderViewPage(driver);
    }
    
    public CCE_ReceivedHubPage pressSendToCust() {
        WebElement waitForClickable = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(sendToCustButton));
        
        driver.findElement(sendToCustButton).click();
        
        Boolean waitForSelected = new WebDriverWait(driver,10).until(CommonTask.boxIsChecked(driver.findElement(sendToCustButton)));
        
        return this;
    }
    
    public void checkFields() {
        //Wait for all to be clickable
        WebElement waitForOrderNo = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(orderNoField));
        WebElement waitForCustName = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(custNameField));
        WebElement waitForOrderFrom = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(orderDateFromField));
        WebElement waitForOrderTo = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(orderDateToField));
        WebElement waitForRequester = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(requesterField));
        WebElement waitForFce = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(fceNameField));
        WebElement waitForHub = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(hub));
        WebElement waitForListOrders = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(listOrdersButton));
        WebElement waitForReset = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(resetButton));
        
        //Assert all elements are displayed
        Assert.assertTrue("Hub SOS Page: Order No Field not displayed correctly",getOrderNoField().isDisplayed());
        Assert.assertTrue("Hub SOS Page: Hub Field not displayed correctly",getHubField().isDisplayed());
        Assert.assertTrue("Hub SOS Page: Customer Name Field not displayed correctly",getCustNameField().isDisplayed());
        Assert.assertTrue("Hub SOS Page: Order Date From Field not displayed correctly",getOrderDateFromField().isDisplayed());
        Assert.assertTrue("Hub SOS Page: Order Date To Field not displayed correctly",getOrderDateToField().isDisplayed());
        Assert.assertTrue("Hub SOS Page: FCE Field not displayed correctly",getFceNameField().isDisplayed());
        Assert.assertTrue("Hub SOS Page: Requester Field not displayed correctly",getRequesterField().isDisplayed());
               
    }
    
}
