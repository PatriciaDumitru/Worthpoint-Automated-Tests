
package PageObjects;

import AutomationFramework.DataItems;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.AssertJUnit;


public class Mst_EditCoatsUserPage extends WBA_BasePage {
    
    //Locators
    By usernameField = By.id("CoatsUserFirstName");
    By saveButton = By.id("submit");
    By cancelButton = By.id("#CoatsUserEditForm > div.actions > ul > li:nth-child(2)");
    
    public Mst_EditCoatsUserPage(WebDriver driver) {
        super(driver);
    }
    
    public WebElement getBreadcrumb() {
        return driver.findElement(DataItems.breadcrumbLocator);
    }
    
    public WebElement getUsernameField() {
        WebElement wait = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(usernameField));
        return driver.findElement(usernameField);
    }
    
    public WebElement getSaveButton() {
        WebElement wait = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(saveButton));
        return driver.findElement(saveButton);
    }
    
    public WebElement getCancelButton() {
        //WebElement wait = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(cancelButton));
        return driver.findElement(cancelButton);
    }
    
    public Mst_CoatsUsersPage pressSave() {
        WebElement save = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(saveButton));
        save.click();
        return new Mst_CoatsUsersPage(driver);
    }
    
    public Mst_CoatsUsersPage pressCancel() {
        WebElement cancel = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(cancelButton));
        cancel.click();
        return new Mst_CoatsUsersPage(driver);
    }
    
    public void waitForElement() {
        WebElement wait = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(usernameField));
    }
    
    public void checkFields() {
        //Wait for all elements to be clickable
        WebElement username = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(usernameField));
        WebElement saveButton1 = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(saveButton));
        //WebElement cancelButton1 = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.visibilityOfElementLocated(cancelButton));
        
        //Assert all elements are displayed correctly
        AssertJUnit.assertTrue("Edit Coats User Page: Username field not displayed as expected",getUsernameField().isDisplayed());
        AssertJUnit.assertTrue("Edit Coats User Page: Save button not displayed as expected",getSaveButton().isDisplayed());
        //AssertJUnit.assertTrue("Edit Coats User Page: Cancel button not displayed as expected",getCancelButton().isDisplayed());
    }
    
}
