
package PageObjects;

import AutomationFramework.CommonTask;
import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class FeedbackAwaitingPage_CCE extends BasePage{
    
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
    
    public FeedbackAwaitingPage_CCE(WebDriver passedDriver) {
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
    
    public FeedbackAwaitingPage_CCE setOrderNo(String orderNo) {
        //Wait for element to be clickable
        WebElement waitForClickable = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(orderNoField));
        CommonTask.setSearchField(driver, orderNoField, orderNo);
        return this;
    }
    
    public FeedbackAwaitingPage_CCE setCustName(String custName) {
        //Wait for element to be clickable
        WebElement waitForClickable = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(custNameField));
        CommonTask.setSearchField(driver, custNameField, custName);
        return this;
    }
    
    public FeedbackAwaitingPage_CCE setRequester(String requester) throws InterruptedException {
        //Wait for element to be clickable
        WebElement waitForClickable = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(custNameField));
        CommonTask.setDropDownField(driver, requesterField, requester);
        return this;
    }
    
    public FeedbackAwaitingPage_CCE pressListOrders() {
        //Wait for button to be clickable
        WebElement waitForClickable = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(listOrdersButton));
        
        driver.findElement(formLocator).submit();
        
        return this;
    }
    
    public FeedbackAwaitingPage_CCE pressReset() {
        //Wait for button to be clickable
        WebElement waitForClickable = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(resetButton));
        driver.findElement(resetButton).click();
        return this;
    }
    
    public FeedbackPage_CCE pressLoad() {
        WebElement waitForClickable = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(loadButton));
        
        driver.findElement(loadButton).click();
        
        return new FeedbackPage_CCE(driver);
    }
    
    public FeedbackAwaitingPage_CCE pressAccept(String acceptCode) {
        //Wait for accept field to be clickable and enter accept code
        WebElement waitForField = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(acceptField));
        driver.findElement(acceptField).sendKeys(acceptCode);
        
        WebElement waitForClickable  = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(acceptButton));
        driver.findElement(acceptButton).click();
        
        return this;
    }
    
    public FeedbackAwaitingPage_CCE pressSave() {
        //Wait for save button to be clickable
        WebElement waitForClickable = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(saveButton));
        
        driver.findElement(saveButton).click();
        
        Alert alert = new WebDriverWait(driver,10).until(ExpectedConditions.alertIsPresent());
        alert.accept();
        
        String message = driver.findElement(flashMessage).getText();
        
        if (message.contains("Order has been updated")) {
            System.out.println("Save successful. Message receieved: "+message);
        } else {
            System.out.println("***Error in saving or unexpected message receieved***");
        }
        
        return new FeedbackAwaitingPage_CCE(driver);
    }
    
    public void checkFields() {
        
        //Wait for all to be clickable
        WebElement waitForOrderNo = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(orderNoField));
        WebElement waitForCustName = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(custNameField));
        WebElement waitForRequester = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(requesterField));
        WebElement waitForShadeCode = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(shadeCodeField));
        WebElement waitForScenario = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(scenarioField));
        WebElement waitForListOrder = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(listOrdersButton));
        WebElement waitForReset = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(resetButton));
    
        //Assert all are displayed
        Assert.assertTrue("Feedback Awaiting Page: Order No Field not displayed correctly",getOrderNoField().isDisplayed());
        Assert.assertTrue("Feedback Awaiting Page: Customer name field not displayed correctly",getCustNameField().isDisplayed());
        Assert.assertTrue("Feedback Awaiting Page: Requester field not displayed correctly",getRequesterField().isDisplayed());
        Assert.assertTrue("Feedback Awaiting Page: Shade code field not displayed correctly",getShadeCodeField().isDisplayed());
        Assert.assertTrue("Feedback Awaiting Page: Scenario field not displayed correctly",getScenarioField().isDisplayed());     
        Assert.assertTrue("Feedback Awaiting Page: List orders button not displayed correctly",getListOrdersButton().isDisplayed());
        Assert.assertTrue("Feedback Awaiting Page: Reset button not displayed correctly",getResetButton().isDisplayed());
            
    }
    
}


