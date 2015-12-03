
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

public class CCE_FeedbackPage extends WBA_BasePage{
    
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
    
    public CCE_FeedbackPage(WebDriver passedDriver) {
        super(passedDriver);
    }
    
    public WebElement getBreadcrumb() {
        return Wait.visible(driver, breadcrumb);
    }
    
    public CCE_FeedbackPage setOrderNo(String orderNo) {
        CommonTask.setSearchField(driver, orderNoField, orderNo);
        
        //Wait for customer name to appear
        boolean waitForName = Wait.textPresent(driver,custNameField,DataItems.sampCustName);
        
        return this;
    }
    
    public CCE_FeedbackPage setRequester(String requester) throws InterruptedException {
        CommonTask.setDropDownField(driver, requesterField, requester);
        return this;
    }
    
    public String getCustName() {
        return driver.findElement(custNameField).getText();
    }
    
    public CCE_FeedbackPage pressYesSatisfied() {
        WebElement acceptedYes = Wait.clickable(driver,acceptedYesButton);
        acceptedYes.click();
        boolean checked = Wait.checked(driver,acceptedYesButton);
        return this;
    }
    
    public CCE_FeedbackPage pressNoSatisfied() {
        WebElement button = Wait.clickable(driver,acceptedNoButton);
        button.click();
        WebElement button2 = Wait.clickable(driver,acceptedYesButton);
        boolean checked = Wait.checked(driver,acceptedNoButton);
        return this;
    }
    
    public CCE_FeedbackPage pressYesRematch() {
        CommonTask.setCheckBox(driver, rematchYesButton);
        return this;
    }
    
    public CCE_FeedbackPage pressNoRematch() {
        CommonTask.setCheckBox(driver, rematchNoButton);
        return this;
    }
    
    public CCE_FeedbackPage setReason(String reason) {
        
        switch(reason) {
            case "Metamerism": driver.findElement(metamerism).click();break;
            case "Different Hue": driver.findElement(differentHue).click(); break;
            case "Too Dull": driver.findElement(tooDull).click(); break;
            case "Too Bright": driver.findElement(tooBright).click(); break;
        }
        
        return this;
    }
    
    public CCE_FeedbackPage pressSave() {
        //Wait for element to be clickable
        WebElement save = Wait.clickable(driver,saveButton);
        
        //Click save
        save.click();
        
        //Handle alert
        Alert alert = Wait.alert(driver);
        alert.accept();    
        
        //Wait for message to be present and output
        WebElement confMessage = Wait.visible(driver,confirmMessage);
        String message = confMessage.getText();
        if (message.contains("cannot be updated")) {
            System.out.println("***Feedback save failed. Error message: " + message + "***");
        } else {
            System.out.println("Confirmation message: "+message);
        }
        
        return new CCE_FeedbackPage(driver);
    }
    
    public CCE_OrderSamplesPage pressCancel() {
        //Wait for element to be clickable
        WebElement cancel = Wait.clickable(driver,cancelButton);
        
        //Click cancel
        cancel.click();
         
        return new CCE_OrderSamplesPage(driver);
    }
    
    public void checkFields() {
        //Wait for elements to be clickable
        WebElement orderNo = Wait.clickable(driver,orderNoField);
        WebElement orderLine = Wait.clickable(driver,orderLineField);
        WebElement acceptYes = Wait.clickable(driver,acceptedYesButton);
        WebElement acceptNo = Wait.clickable(driver,acceptedNoButton);
        WebElement requestor = Wait.clickable(driver,requesterField);
        WebElement save = Wait.clickable(driver,saveButton);
        WebElement cancel = Wait.clickable(driver,cancelButton);
    
        //Assert all elements are displayed
        AssertJUnit.assertTrue("Feedback page: Order No field not displayed",orderNo.isDisplayed());
        AssertJUnit.assertTrue("Feedback page: Order line field not displayed",orderLine.isDisplayed());
        AssertJUnit.assertTrue("Feedback page: 'Yes' button not displayed",acceptYes.isDisplayed());
        AssertJUnit.assertTrue("Feedback page: 'No' button not displayed",acceptNo.isDisplayed());
        AssertJUnit.assertTrue("Feedback page: Requester field not displayed",requestor.isDisplayed());
        AssertJUnit.assertTrue("Feedback page: Save button not displayed",save.isDisplayed());
        AssertJUnit.assertTrue("Feedback page: Cancel button not displayed",cancel.isDisplayed());     
    
    }
    
}
