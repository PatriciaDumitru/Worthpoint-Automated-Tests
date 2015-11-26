
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

public class Mst_EditPurposeTypePage extends WBA_BasePage {
    
    By purposeTypeField = By.id("PurposeTypePurposeType");
    By saveButton = By.cssSelector("#PurposeTypeEditForm > div.actions > ul > li:nth-child(1) > input[type=\"submit\"]");
    By cancelButton = By.cssSelector("#PurposeTypeEditForm > div.actions > ul > li:nth-child(2) > a");
    
    public Mst_EditPurposeTypePage(WebDriver driver) {
        super(driver);
    }

    public WebElement getBreadcrumb() {
        return new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.visibilityOfElementLocated(DataItems.breadcrumbLocator));
    }
    
    public Mst_EditPurposeTypePage setPurposeType(String item) {
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(purposeTypeField));
        element.clear();
        
        CommonTask.setInputField(driver, purposeTypeField, item);
        return new Mst_EditPurposeTypePage(driver);
    }
    
    public Mst_PurposeTypesPage pressSave() {
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(saveButton));
        element.click();
        
        return new Mst_PurposeTypesPage(driver);
    }
    
    public void checkFields() {
        WebElement purposeType = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(purposeTypeField));
        WebElement save = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(saveButton));
        WebElement cancel = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(cancelButton));
        
        AssertJUnit.assertTrue("Edit Purpose Type: Purpose Type field not displayed",purposeType.isDisplayed());
        AssertJUnit.assertTrue("Edit Purpose Type: Save button not displayed",save.isDisplayed());
        AssertJUnit.assertTrue("Edit Purpose Type: Cancel button not displayed",cancel.isDisplayed());
    }
    
    public void waitForElement() {
        WebElement purposeType = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(purposeTypeField));
    }
    
}
