package PageObjects;

import AutomationFramework.CommonTask;
import AutomationFramework.DataItems;
import AutomationFramework.Wait;
import static PageObjects.WBA_BasePage.driver;
import org.testng.AssertJUnit;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
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
    By saveButton = By.cssSelector("#SampleOrderLineReceivedHubForm > div.actions > ul > li:nth-child(1) > input[type=\"submit\"]");
    By cancelButton = By.cssSelector("#SampleOrderLineReceivedHubForm > div.actions > ul > li:nth-child(2)");
    By filterForm = By.id("FilterReceivedHubForm");
    By flashMessage = By.id("flashMessage");
    By viewFrame = By.id("TB_iframeContent");
    By sendToCustButton = By.cssSelector("#SampleOrderLineReceivedHubForm > div.flexi-grid > table > tbody > tr:nth-child(3) > td:nth-child(6) > input");
    
    By noRecordsField = By.cssSelector("#SampleOrderLineReceivedHubForm > div.flexi-grid > div");
    
    public CCE_ReceivedHubPage(WebDriver passedDriver) {
        super(passedDriver);
    }
    
    public String getTitle() {
        WebElement title = Wait.visible(driver,breadcrumb);
        
        return title.getText();
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
        WebElement listOrders = Wait.clickable(driver,listOrdersButton);
        
        //submit form
        driver.findElement(filterForm).submit();
        
        return this;
    }
    
    public CCE_ReceivedHubPage pressReset() {
        //Wait for reset button
        WebElement reset = Wait.clickable(driver,resetButton);
        
        reset.click();
        
        return this;
    }
    
    public CCE_ReceivedHubPage pressSave() {
        WebElement save = Wait.clickable(driver,saveButton);
        
        save.click();
        
        Alert alert = Wait.alert(driver);
        alert.accept();
        
        WebElement msg = Wait.visible(driver,flashMessage);
        
        String message = msg.getText();
        
        if (message.contains("Order has been")) {
            System.out.println("Save successful. Message received: "+message);
        } else {
            System.out.println("***Save unsuccessful/unexpected message received: "+message +"***");
        }
        
        return this;
        
    }
    
    public CCE_OrderViewPage pressView() {
        WebElement view = Wait.clickable(driver,viewButton);
        
        view.click();
        
        return new CCE_OrderViewPage(driver);
    }
    
    public CCE_ReceivedHubPage pressSendToCust() {
        WebElement sendToCust = Wait.clickable(driver,sendToCustButton);
        
        sendToCust.click();
        
        Boolean wait = Wait.checked(driver,sendToCustButton);
        
        return this;
    }
    
    public void checkFields() {
        //Wait for all to be clickable
        WebElement orderNo = Wait.clickable(driver,orderNoField);
        WebElement custName = Wait.clickable(driver,custNameField);
        WebElement orderFrom = Wait.clickable(driver,orderDateFromField);
        WebElement orderTo = Wait.clickable(driver,orderDateToField);
        WebElement requester = Wait.clickable(driver,requesterField);
        WebElement fce = Wait.clickable(driver,fceNameField);
        WebElement hubField = Wait.clickable(driver,hub);
        WebElement listOrders = Wait.clickable(driver,listOrdersButton);
        WebElement reset = Wait.clickable(driver,resetButton);
        
        //Assert all elements are displayed
        AssertJUnit.assertTrue("Hub SOS Page: Order No Field not displayed correctly",orderNo.isDisplayed());
        AssertJUnit.assertTrue("Hub SOS Page: Hub Field not displayed correctly",hubField.isDisplayed());
        AssertJUnit.assertTrue("Hub SOS Page: Customer Name Field not displayed correctly",custName.isDisplayed());
        AssertJUnit.assertTrue("Hub SOS Page: Order Date From Field not displayed correctly",orderFrom.isDisplayed());
        AssertJUnit.assertTrue("Hub SOS Page: Order Date To Field not displayed correctly",orderTo.isDisplayed());
        AssertJUnit.assertTrue("Hub SOS Page: FCE Field not displayed correctly",fce.isDisplayed());
        AssertJUnit.assertTrue("Hub SOS Page: Requester Field not displayed correctly",requester.isDisplayed());
               
    }
    
    public boolean checkForRecords() {
        try {
            WebElement wait = Wait.visible(driver,noRecordsField);
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }
    
}
