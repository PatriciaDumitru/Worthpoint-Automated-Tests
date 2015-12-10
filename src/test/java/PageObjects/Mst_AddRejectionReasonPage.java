
package PageObjects;

import AutomationFramework.CommonTask;
import AutomationFramework.DataItems;
import AutomationFramework.Wait;
import static PageObjects.WBA_BasePage.driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.AssertJUnit;

public class Mst_AddRejectionReasonPage extends WBA_BasePage {
    
    By rejectionCodeField = By.id("RejectionCodeRejectionCode");
    By rejectionReasonField = By.id("RejectionReason0RejectionReason");
    By saveButton = By.cssSelector("#submit");
    By cancelButton = By.cssSelector("#RejectionCodeAddForm > div.actions > ul > li:nth-child(2) > a");
    
    public Mst_AddRejectionReasonPage(WebDriver driver) {
        super(driver);
    }
    
    public WebElement getBreadcrumb() {
        return Wait.visible(driver,DataItems.breadcrumbLocator);
    }
    
    public Mst_AddRejectionReasonPage setRejectionCode(String item) {
        CommonTask.setInputField(driver, rejectionCodeField, item);
        return new Mst_AddRejectionReasonPage(driver);
    }
    
    public Mst_AddRejectionReasonPage setRejectionReason(String item) {
        CommonTask.setInputField(driver, rejectionReasonField, item);
        return new Mst_AddRejectionReasonPage(driver);
    }
    
    public Mst_RejectionReasonsPage pressSave() {
        WebElement element = Wait.clickable(driver,saveButton);
        element.click();
        
        return new Mst_RejectionReasonsPage(driver);
    }
    
    public void checkFields() {
        WebElement rejCode = Wait.clickable(driver,rejectionCodeField);
        WebElement rejReason = Wait.clickable(driver,rejectionReasonField);
        WebElement save = Wait.clickable(driver,saveButton);
        WebElement cancel = Wait.clickable(driver,cancelButton);
        
        AssertJUnit.assertTrue("Add Rejection Reason: Rejection Code field not displayed",rejCode.isDisplayed());
        AssertJUnit.assertTrue("Add Rejection Reason: Rejection Reason field not displayed",rejReason.isDisplayed());
        AssertJUnit.assertTrue("Add Rejection Reason: Save button not displayed",save.isDisplayed());
        AssertJUnit.assertTrue("Add Rejection Reason: Cancel button not displayed",cancel.isDisplayed());
    }
    
    public void waitForElement() {
        WebElement rejReason = Wait.clickable(driver,rejectionReasonField);
    }
    
}
