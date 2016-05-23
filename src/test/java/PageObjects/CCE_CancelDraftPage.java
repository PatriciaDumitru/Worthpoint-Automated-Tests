
package PageObjects;

import AutomationFramework.CommonTask;
import AutomationFramework.Wait;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.AssertJUnit;

public class CCE_CancelDraftPage extends WBA_BasePage{
    
    //locators
    By formLocator = By.id("SampleOrderLineCancelOrderLineForm");
    By cancelReasonField = By.id("SampleOrderLineCancelReasonId");
    By saveButton = By.cssSelector("#SampleOrderLineCancelOrderLineForm > div.actions > ul > li:nth-child(1) > input[type=\"submit\"]");
    By cancelButton = By.cssSelector("#SampleOrderLineCancelOrderLineForm > div.actions > ul > li:nth-child(2) > a");
    By frameLocator = By.id("TB_iframeContent");
    By closeButton = By.id("TB_closeWindowButton");
    By titleBar = By.id("TB_title");
    By overlayLocator = By.id("TB_overlay");
    
    public CCE_CancelDraftPage(WebDriver passedDriver) {
        super(passedDriver);
    }
    
    public WebElement getCancelReasonField() {
        return driver.findElement(cancelReasonField);
    }
    
    public WebElement getSubmitButton() {
        return driver.findElement(saveButton);
    }
    
    public WebElement getCancelButton() {
        return driver.findElement(cancelButton);
    }
    
    public void checkFields() {
        //Wait for all elements to be clickable
        WebElement cancelReason = Wait.clickable(driver,cancelReasonField);
        WebElement submit = Wait.clickable(driver,saveButton);
        WebElement cancel = Wait.clickable(driver,cancelButton);
    
        //Assert all elements are displayed
        AssertJUnit.assertTrue("Cancel Draft Page: Cancellation Reason field not displayed",cancelReason.isDisplayed());
        AssertJUnit.assertTrue("Cancel Draft Page: Submit button not displayed",submit.isDisplayed());
        AssertJUnit.assertTrue("Cancel Draft Page: Cancel button not displayed",cancel.isDisplayed());
    }
    
    public void waitForElement() {
        WebElement cancelReason = Wait.clickable(driver,cancelReasonField);
    }
    
    public void waitForInvisibility() {
        boolean wait = Wait.invisible(driver,formLocator);
    }
    
    public void switchTo() {
        //Switch focus to the Cancel Draft overlay    
        WebDriver frame = Wait.frame(driver, frameLocator);
    }
    
    public CCE_CancelDraftPage setReason(String reason) throws InterruptedException {
        //Set the cancellation reason in the cancel draft overlay
        CommonTask.setDropDownField(driver,cancelReasonField,reason);
        return new CCE_CancelDraftPage(driver);
    }
    
    public CCE_OutstandingDraftPage pressSave() {
        //Click save
        WebElement element = Wait.clickable(driver,saveButton);
        element.click();
        
        //Wait for overlay to disappear
        boolean waitForUpdate = Wait.invisible(driver,cancelReasonField);
        
        return new CCE_OutstandingDraftPage(driver);
    }
    
    public void closeView() {
        Actions action = new Actions(driver);
        action.moveToElement(driver.findElement(closeButton)).build().perform();
        
        action.click().build().perform();
        
        Alert alert = Wait.alert(driver);
        alert.accept();
       
    }
    
}
