
package PageObjects;

import AutomationFramework.CommonTask;
import AutomationFramework.TestSuite;
import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FeedbackPage_CCE extends BasePage{
    
    //Locators
    By breadcrumb = By.cssSelector("#content > h2");
    By orderNoField = By.id("s2id_SampleOrderLineOrderId");
    By orderLineField = By.id("SampleOrderLineId");
    By custNameField = By.id("SampleOrderLineCustomerName");
    By requesterField = By.id("SampleOrderLineFeedbackRequesterId");
    By acceptedYesButton = By.id("SampleOrderLineIsAccepted1");
    By acceptedNoButton = By.id("SampleOrderLineIsAccepted0");
    By rematchYesButton = By.id("SampleOrderLineIsRematchRequired1");
    By rematchNoButton = By.id("SampleOrderLineIsRematchRequired0");
    By saveButton = By.id("save");
    By cancelButton = By.cssSelector("#SampleOrderLineFeedbackForm > div.actions > ul > li:nth-child(2) > a");
    By confirmMessage = By.id("flashMessage");
    
    //Rejection reason locators
    By differentHue = By.id("SampleOrderLineRejectionCodeId2");
    By tooDull = By.id("SampleOrderLineRejectionCodeId7");
    By tooBright = By.id("SampleOrderLineRejectionCodeId6");
    By metamerism = By.id("SampleOrderLineRejectionCodeId3");
    
    
    
    public FeedbackPage_CCE(WebDriver passedDriver) {
        super(passedDriver);
    }
    
    public WebElement getBreadcrumb() {
        //Wait for element to be present
        WebElement waitForBreadcrumb = new WebDriverWait(driver,10).until(ExpectedConditions.presenceOfElementLocated(breadcrumbLocator));
        return driver.findElement(breadcrumb);
    }
    
    public WebElement getOrderNoField() {
        return driver.findElement(orderNoField);
    }
    
    public WebElement getOrderLineField() {
        return driver.findElement(orderLineField);
    }
    
    public WebElement getCustNameField() {
        return driver.findElement(custNameField);
    }
    
    public WebElement getYesButton() {
        return driver.findElement(acceptedYesButton);
    }
    
    public WebElement getNoButton() {
        return driver.findElement(acceptedNoButton);
    }
    
    public WebElement getRequesterField() {
        return driver.findElement(requesterField);
    }
    
    public WebElement getSaveButton() {
        return driver.findElement(saveButton);
    }
    
    public WebElement getCancelButton() {
        return driver.findElement(cancelButton);
    }
    
    public FeedbackPage_CCE setOrderNo(String orderNo) {
        CommonTask.setSearchField(driver, orderNoField, orderNo);
        
        //Wait for customer name to appear
        boolean waitForName = new WebDriverWait(driver,10).until(ExpectedConditions.textToBePresentInElement(custNameField, TestSuite.sampCustName));
        
        return this;
    }
    
    public FeedbackPage_CCE setRequester(String requester) throws InterruptedException {
        CommonTask.setDropDownField(driver, requesterField, requester);
        return this;
    }
    
    public String getCustName() {
        return driver.findElement(custNameField).getText();
    }
    
    public FeedbackPage_CCE pressYesSatisfied() {
        driver.findElement(acceptedYesButton).click();
        boolean waitForChecked = new WebDriverWait(driver,10).until(CommonTask.boxIsChecked(driver.findElement(acceptedYesButton)));
        return this;
    }
    
    public FeedbackPage_CCE pressNoSatisfied() {
        driver.findElement(acceptedNoButton).click();
        boolean waitForChecked = new WebDriverWait(driver,10).until(CommonTask.boxIsChecked(driver.findElement(acceptedYesButton)));
        return this;
    }
    
    public FeedbackPage_CCE pressYesRematch() {
        //Wait for button
        WebElement waitForClickable = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(rematchYesButton));
        driver.findElement(rematchYesButton).click();
        boolean waitForChecked = new WebDriverWait(driver,10).until(CommonTask.boxIsChecked(driver.findElement(rematchYesButton)));
        return this;
    }
    
    public FeedbackPage_CCE pressNoRematch() {
        //Wait for button
        WebElement waitForClickable = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(rematchNoButton));
        driver.findElement(rematchNoButton).click();
        boolean waitForChecked = new WebDriverWait(driver,10).until(CommonTask.boxIsChecked(driver.findElement(rematchNoButton)));
        return this;
    }
    
    public FeedbackPage_CCE setReason(String reason) {
        
        switch(reason) {
            case "Metamerism": driver.findElement(metamerism).click();break;
            case "Different Hue": driver.findElement(differentHue).click(); break;
            case "Too Dull": driver.findElement(tooDull).click(); break;
            case "Too Bright": driver.findElement(tooBright).click(); break;
        }
        
        return this;
    }
    
    public FeedbackPage_CCE pressSave() {
        //Wait for element to be clickable
        WebElement waitForClickable = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(saveButton));
        //Click save
        driver.findElement(saveButton).click();
        //Handle alert
        Alert alert = new WebDriverWait(driver,10).until(ExpectedConditions.alertIsPresent());
        alert.accept();    
        //Wait for message to be present and output
        WebElement waitForVisible = new WebDriverWait(driver,10).until(ExpectedConditions.visibilityOf(driver.findElement(confirmMessage)));
        String message = driver.findElement(confirmMessage).getText();
        if (message.contains("cannot be updated")) {
            System.out.println("***Feedback save failed. Error message: " + message + "***");
        } else {
            System.out.println("Confirmation message: "+message);
        }
        
        return new FeedbackPage_CCE(driver);
    }
    
    public OrderSamplesPage_CCE pressCancel() {
        //Wait for element to be clickable
        WebElement waitForClickable = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(cancelButton));
        //Click cancel
        driver.findElement(cancelButton).click();
         
        return new OrderSamplesPage_CCE(driver);
    }
    
    public void checkFields() {
        //Wait for elements to be clickable
        WebElement waitForOrderNo = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(orderNoField));
        WebElement waitForOrderLine = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(orderLineField));
        WebElement waitForYes = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(acceptedYesButton));
        WebElement waitForNo = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(acceptedNoButton));
        WebElement waitForRequestor = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(requesterField));
        WebElement waitForSave = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(saveButton));
        WebElement waitForCancel = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(saveButton));
    
        //Assert all elements are displayed
        Assert.assertTrue("Feedback page: Order No field not displayed",getOrderNoField().isDisplayed());
        Assert.assertTrue("Feedback page: Order line field not displayed",getOrderLineField().isDisplayed());
        Assert.assertTrue("Feedback page: 'Yes' button not displayed",getYesButton().isDisplayed());
        Assert.assertTrue("Feedback page: 'No' button not displayed",getNoButton().isDisplayed());
        Assert.assertTrue("Feedback page: Requester field not displayed",getRequesterField().isDisplayed());
        Assert.assertTrue("Feedback page: Save button not displayed",getSaveButton().isDisplayed());
        Assert.assertTrue("Feedback page: Cancel button not displayed",getCancelButton().isDisplayed());     
    
    }
    
}
