
package PageObjects;

import AutomationFramework.CommonTask;
import AutomationFramework.DataItems;
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
    
    public WebElement getScenarioField() {
        return driver.findElement(scenarioField);
    }
    
    public WebElement getListOrdersButton() {
        return driver.findElement(listOrdersButton);
    }
    
    public WebElement getResetButton() {
        return driver.findElement(resetButton);
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
        WebElement waitForClickable = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(listOrdersButton));
        
        driver.findElement(formLocator).submit();
        
        return this;
    }
    
    public CCE_FeedbackAwaitingPage pressReset() {
        //Wait for button to be clickable
        WebElement waitForClickable = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(resetButton));
        driver.findElement(resetButton).click();
        return this;
    }
    
    public CCE_FeedbackPage pressLoad() {
        WebElement waitForClickable = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(loadButton));
        
        driver.findElement(loadButton).click();
        
        return new CCE_FeedbackPage(driver);
    }
    
    public CCE_FeedbackAwaitingPage pressAccept(String acceptCode) {
        //Wait for accept field to be clickable and enter accept code
        WebElement waitForField = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(acceptField));
        driver.findElement(acceptField).sendKeys(acceptCode);
        
        WebElement waitForClickable  = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(acceptButton));
        driver.findElement(acceptButton).click();
        
        return this;
    }
    
    public CCE_FeedbackAwaitingPage pressSave() {
        //Wait for save button to be clickable
        WebElement waitForClickable = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(saveButton));
        
        driver.findElement(saveButton).click();
        
        Alert alert = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.alertIsPresent());
        alert.accept();
        
        WebElement waitForMessage = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.visibilityOfElementLocated(flashMessage));
        
        String message = driver.findElement(flashMessage).getText();
        
        if (message.contains("Order has been updated")) {
            System.out.println("Save successful. Message receieved: "+message);
        } else {
            System.out.println("***Error in saving or unexpected message receieved***");
        }
        
        return new CCE_FeedbackAwaitingPage(driver);
    }
    
    public void checkFields() {
        
        //Wait for all to be clickable
        WebElement waitForOrderNo = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(orderNoField));
        WebElement waitForCustName = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(custNameField));
        WebElement waitForRequester = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(requesterField));
        WebElement waitForShadeCode = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(scenarioField));
        WebElement waitForListOrder = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(listOrdersButton));
        WebElement waitForReset = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(resetButton));
    
        //Assert all are displayed
        AssertJUnit.assertTrue("Feedback Awaiting Page: Order No Field not displayed correctly",getOrderNoField().isDisplayed());
        AssertJUnit.assertTrue("Feedback Awaiting Page: Customer name field not displayed correctly",getCustNameField().isDisplayed());
        AssertJUnit.assertTrue("Feedback Awaiting Page: Requester field not displayed correctly",getRequesterField().isDisplayed());
        AssertJUnit.assertTrue("Feedback Awaiting Page: Shade code field not displayed correctly",getShadeCodeField().isDisplayed());
        AssertJUnit.assertTrue("Feedback Awaiting Page: Scenario field not displayed correctly",getScenarioField().isDisplayed());     
        AssertJUnit.assertTrue("Feedback Awaiting Page: List orders button not displayed correctly",getListOrdersButton().isDisplayed());
        AssertJUnit.assertTrue("Feedback Awaiting Page: Reset button not displayed correctly",getResetButton().isDisplayed());
            
    }
    
    public void waitForElement() {
        WebElement wait = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(orderNoField));
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


