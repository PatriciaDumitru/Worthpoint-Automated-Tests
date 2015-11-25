
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

public class Mst_EditFinishPage extends WBA_BasePage {
    
    By finishNameField = By.id("FinishFinishName");
    By saveButton = By.cssSelector("#FinishEditForm > div.actions > ul > li:nth-child(1) > input[type=\"submit\"]");
    By cancelButton = By.cssSelector("#FinishEditForm > div.actions > ul > li:nth-child(2) > a");
    
    public Mst_EditFinishPage(WebDriver driver) {
        super(driver);
    }
    
    public WebElement getBreadcrumb() {
        return new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.visibilityOfElementLocated(DataItems.breadcrumbLocator));
    }
    
    public Mst_AddFinishPage setFinishName(String item) {
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(finishNameField));
        element.clear();
        
        CommonTask.setInputField(driver, finishNameField, item);
        return new Mst_AddFinishPage(driver);
    }
    
    public Mst_FinishesPage pressSave() {
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(saveButton));
        element.click();
        
        return new Mst_FinishesPage(driver);
    }
    
    public Mst_FinishesPage pressCancel() {
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(cancelButton));
        element.click();
        
        return new Mst_FinishesPage(driver);
    }
    
    public void checkFields() {
        WebElement finishName = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(finishNameField));
        WebElement save = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(saveButton));
        WebElement cancel = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(cancelButton));
        
        AssertJUnit.assertTrue("Edit Finish Page: Finish Name field not displayed",finishName.isDisplayed());
        AssertJUnit.assertTrue("Edit Finish Page: Save button not displayed",save.isDisplayed());
        AssertJUnit.assertTrue("Edit Finish Page: Cancel button not displayed",cancel.isDisplayed());
    }
    
    public void waitForElement() {
        WebElement finishName = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(finishNameField));
    }
    
    
}
