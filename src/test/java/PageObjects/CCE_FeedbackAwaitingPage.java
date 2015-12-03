
package PageObjects;

import AutomationFramework.CommonTask;
import AutomationFramework.DataItems;
import AutomationFramework.Wait;
import org.testng.AssertJUnit;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class CCE_FeedbackAwaitingPage extends WBA_BasePage{
    
    //Locators
    By breadcrumb = By.cssSelector("#content > h2");
    By orderNoField = By.id("s2id_filterSampleOrderLineOrderId");
    By custNameField = By.id("s2id_filterSampleOrderCustomerId");
    By requesterField = By.id("s2id_filterSampleOrderRequesterId");
    By shadeCodeField = By.id("s2id_filterSampleOrderLineShadeId");
    By scenarioField = By.id("s2id_filterSampleOrderLineScenarioId");
    By formLocator = By.id("FilterFeedbackAwaitingForm");
    By flashMessage = By.id("flashMessage");
    By listOrdersButton = By.cssSelector("#FilterFeedbackAwaitingForm > div.actions > ul > li:nth-child(1) > input[type=\"submit\"]");
    By resetButton = By.cssSelector("#FilterFeedbackAwaitingForm > div.actions > ul > li:nth-child(2) > a");
    By loadButton = By.cssSelector("#SampleOrderFeedbackAwaitingForm > table > tbody > tr:nth-child(2) > td:nth-child(13) > a");
    By acceptField = By.cssSelector("#SampleOrderFeedbackAwaitingForm > table > tbody > tr:nth-child(2) > td:nth-child(11) > div > input");
    By acceptButton = By.cssSelector("#SampleOrderFeedbackAwaitingForm > table > tbody > tr:nth-child(2) > td:nth-child(12) > div > input");
    By saveButton = By.cssSelector("#content > div.actions > ul > li");
    
    public CCE_FeedbackAwaitingPage(WebDriver passedDriver) {
        super(passedDriver);
    }
    
    public String getBreadcrumb() {
        return driver.findElement(breadcrumb).getText();
    }
    
    public CCE_FeedbackAwaitingPage setOrderNo(String orderNo) {
        CommonTask.setSearchField(driver, orderNoField, orderNo);
        return this;
    }
    
    public CCE_FeedbackAwaitingPage setCustName(String custName) {
        CommonTask.setSearchField(driver, custNameField, custName);
        return this;
    }
    
    public CCE_FeedbackAwaitingPage setRequester(String requester) throws InterruptedException {
        CommonTask.setDropDownField(driver, requesterField, requester);
        return this;
    }
    
    public CCE_FeedbackAwaitingPage pressListOrders() {
        //Wait for button to be clickable
        WebElement listOrders = Wait.clickable(driver,listOrdersButton);
        
        driver.findElement(formLocator).submit();
        
        return this;
    }
    
    public CCE_FeedbackAwaitingPage pressReset() {
        //Wait for button to be clickable
        WebElement reset = Wait.clickable(driver,resetButton);
        reset.click();
        return this;
    }
    
    public CCE_FeedbackPage pressLoad() {
        WebElement load = Wait.clickable(driver,loadButton);
        
        load.click();
        
        return new CCE_FeedbackPage(driver);
    }
    
    public CCE_FeedbackAwaitingPage pressAccept(String acceptCode) {
        //Wait for accept field to be clickable and enter accept code
        WebElement accept = Wait.clickable(driver,acceptField);
        accept.sendKeys(acceptCode);
        
        WebElement acceptBtn  = Wait.clickable(driver, acceptButton);
        acceptBtn.click();
        
        return this;
    }
    
    public CCE_FeedbackAwaitingPage pressSave() {
        //Wait for save button to be clickable
        WebElement save = Wait.clickable(driver,saveButton);
        
        save.click();
        
        Alert alert = Wait.alert(driver);
        alert.accept();
        
        WebElement messageElement = Wait.visible(driver,flashMessage);
        
        String message = messageElement.getText();
        
        if (message.contains("Order has been updated")) {
            System.out.println("Save successful. Message receieved: "+message);
        } else {
            System.out.println("***Error in saving or unexpected message receieved***");
        }
        
        return new CCE_FeedbackAwaitingPage(driver);
    }
    
    public void checkFields() {
        
        //Wait for all to be clickable
        WebElement orderNo = Wait.clickable(driver,orderNoField);
        WebElement custName = Wait.clickable(driver,custNameField);
        WebElement requester = Wait.clickable(driver,requesterField);
        WebElement shadeCode = Wait.clickable(driver,scenarioField);
        WebElement listOrder = Wait.clickable(driver,listOrdersButton);
        WebElement reset = Wait.clickable(driver,resetButton);
        WebElement scenario = Wait.clickable(driver,scenarioField);
    
        //Assert all are displayed
        AssertJUnit.assertTrue("Feedback Awaiting Page: Order No Field not displayed correctly",orderNo.isDisplayed());
        AssertJUnit.assertTrue("Feedback Awaiting Page: Customer name field not displayed correctly",custName.isDisplayed());
        AssertJUnit.assertTrue("Feedback Awaiting Page: Requester field not displayed correctly",requester.isDisplayed());
        AssertJUnit.assertTrue("Feedback Awaiting Page: Shade code field not displayed correctly",shadeCode.isDisplayed());
        AssertJUnit.assertTrue("Feedback Awaiting Page: Scenario field not displayed correctly",scenario.isDisplayed());     
        AssertJUnit.assertTrue("Feedback Awaiting Page: List orders button not displayed correctly",listOrder.isDisplayed());
        AssertJUnit.assertTrue("Feedback Awaiting Page: Reset button not displayed correctly",reset.isDisplayed());
            
    }
    
    public void waitForElement() {
        WebElement wait = Wait.clickable(driver,orderNoField);
    }
    
    public String findOrderAwaitingFeedback(String customerName) {
        
        for (int i = 2; i < 9; i++) {
            By custNameCell = By.cssSelector("#SampleOrderFeedbackAwaitingForm > table > tbody > tr:nth-child("+i+") > td:nth-child(3)");
            By orderNoCell = By.cssSelector("#SampleOrderFeedbackAwaitingForm > table > tbody > tr:nth-child("+i+") > td:nth-child(5)");
        
            if (driver.findElement(custNameCell).getText().equals(customerName)) {
                return driver.findElement(orderNoCell).getText();
            }
        }
        
        return "not found";

    }
    
}


