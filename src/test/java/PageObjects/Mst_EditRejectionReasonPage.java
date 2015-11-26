
package PageObjects;

import AutomationFramework.CommonTask;
import AutomationFramework.DataItems;
import static PageObjects.WBA_BasePage.driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.AssertJUnit;

public class Mst_EditRejectionReasonPage extends WBA_BasePage {
    
    By rejectionCodeField = By.id("RejectionCodeRejectionCode");
    By rejectionReasonField = By.id("RejectionReason0RejectionReason");
    By saveButton = By.cssSelector("#submit");
    By cancelButton = By.cssSelector("#RejectionCodeEditForm > div.actions > ul > li:nth-child(2) > a");
    
    public Mst_EditRejectionReasonPage(WebDriver driver) {
        super(driver);
    }
    
    public WebElement getBreadcrumb() {
        return new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.visibilityOfElementLocated(DataItems.breadcrumbLocator));
    }
    
    public Mst_EditRejectionReasonPage setRejectionCode(String item) {
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(rejectionCodeField));
        element.clear();
        
        CommonTask.setInputField(driver, rejectionCodeField, item);
        return new Mst_EditRejectionReasonPage(driver);
    }
    
    public Mst_EditRejectionReasonPage setRejectionReason(String item) {
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(rejectionReasonField));
        element.clear();
        
        CommonTask.setInputField(driver, rejectionReasonField, item);
        return new Mst_EditRejectionReasonPage(driver);
    }
    
    public Mst_RejectionReasonsPage pressSave() {
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(saveButton));
        element.click();
        
        return new Mst_RejectionReasonsPage(driver);
    }
    
    public void checkFields() {
        WebElement rejCode = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(rejectionCodeField));
        WebElement rejReason = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(rejectionReasonField));
        WebElement save = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(saveButton));
        WebElement cancel = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(cancelButton));
        
        AssertJUnit.assertTrue("Edit Rejection Reason: Rejection Code field not displayed",rejCode.isDisplayed());
        AssertJUnit.assertTrue("Edit Rejection Reason: Rejection Reason field not displayed",rejReason.isDisplayed());
        AssertJUnit.assertTrue("Edit Rejection Reason: Save button not displayed",save.isDisplayed());
        AssertJUnit.assertTrue("Edit Rejection Reason: Cancel button not displayed",cancel.isDisplayed());
    }
    
    public void waitForElement() {
        WebElement rejReason = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(rejectionReasonField));
    }
    
}
