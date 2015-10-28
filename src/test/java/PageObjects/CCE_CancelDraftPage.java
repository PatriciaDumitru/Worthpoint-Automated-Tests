
package PageObjects;

import AutomationFramework.CommonTask;
import AutomationFramework.DataItems;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
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
        WebElement waitForCancelReason = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(cancelReasonField));
        WebElement waitForSubmit = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(saveButton));
        WebElement waitForCancel = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(cancelButton));
    
        //Assert all elements are displayed
        AssertJUnit.assertTrue("Cancel Draft Page: Cancellation Reason field not displayed",getCancelReasonField().isDisplayed());
        AssertJUnit.assertTrue("Cancel Draft Page: Submit button not displayed",getSubmitButton().isDisplayed());
        AssertJUnit.assertTrue("Cancel Draft Page: Cancel button not displayed",getCancelButton().isDisplayed());
    }
    
    public void waitForElement() {
        WebElement wait = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(cancelReasonField));
    }
    
    public void waitForInvisibility() {
        boolean wait = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.invisibilityOfElementLocated(formLocator));
    }
    
    public void switchTo() {
        WebDriver wait = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameLocator));
    }
    
    public CCE_CancelDraftPage setReason(String reason) throws InterruptedException {
        CommonTask.setDropDownField(driver,cancelReasonField,reason);
        return new CCE_CancelDraftPage(driver);
    }
    
    public CCE_OutstandingDraftPage pressSave() {
        WebElement wait = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(saveButton));
        driver.findElement(saveButton).click();
        boolean waitForUpdate = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.invisibilityOfElementLocated(cancelReasonField));
        
        return new CCE_OutstandingDraftPage(driver);
    }
    
    public void closeView() {
        
        WebDriver d = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id("TB_window")));
        
        Actions action = new Actions(driver);
        action.moveToElement(driver.findElement(closeButton)).build().perform();
        action.click().build().perform();

        Alert alert = new WebDriverWait(driver,DataItems.longWait).until(ExpectedConditions.alertIsPresent());
        alert.accept();
    }
    
}
