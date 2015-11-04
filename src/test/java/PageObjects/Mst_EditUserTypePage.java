
package PageObjects;

import AutomationFramework.CommonTask;
import AutomationFramework.DataItems;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.AssertJUnit;

public class Mst_EditUserTypePage extends WBA_BasePage {
    
    //locators
    By userTypeField = By.id("UserTypeUserType");
    By requesterTypeField = By.id("UserTypeUserCategoryId");
    By levelField = By.id("UserTypeLevelId");
    By descriptionField = By.id("UserTypeDescription");
    By statusActiveBtn = By.id("UserTypeStatusId1");
    By statusInactiveBtn = By.id("UserTypeStatusId2");
    By saveButton = By.cssSelector("#UserTypeEditForm > div.actions > ul > li:nth-child(1) > input[type=\"submit\"]");
    By cancelButton = By.cssSelector("#UserTypeEditForm > div.actions > ul > li:nth-child(2) > a");
    
    public Mst_EditUserTypePage(WebDriver driver) {
        super(driver);
    }
    
    public WebElement getBreadcrumb() {
        return driver.findElement(DataItems.breadcrumbLocator);
    }
    
    public WebElement getUserTypeField() {
        return driver.findElement(requesterTypeField);
    }
    
    public WebElement getLevelField() {
        return driver.findElement(levelField);
    }
    
    public WebElement getDescriptionField() {
        return driver.findElement(descriptionField);
    }
    
    public WebElement getStatusActiveBtn() {
        return driver.findElement(statusActiveBtn);
    }
    
    public WebElement getStatusInactiveBtn() {
        return driver.findElement(statusInactiveBtn);
    }
    
    public WebElement getSaveButton() {
        return driver.findElement(saveButton);
    }
    
    public WebElement getCancelButton() {
        return driver.findElement(cancelButton);
    }
    
    public Mst_EditUserTypePage setStatusActive() throws InterruptedException {
        CommonTask.setCheckBox(driver, statusActiveBtn);
        return new Mst_EditUserTypePage(driver);
    }
    
    public Mst_EditUserTypePage setStatusInactive() throws InterruptedException {
        CommonTask.setCheckBox(driver, statusInactiveBtn);
        return new Mst_EditUserTypePage(driver);
    }
    
    public Mst_EditUserTypePage setLevel(String item) throws InterruptedException {
        CommonTask.setDropDownField(driver,levelField,item);
        return new Mst_EditUserTypePage(driver);
    }
    
    public Mst_EditUserTypePage setStatus(String item) throws InterruptedException {
        switch(item) {
            case "active": setStatusActive(); break;
            case "inactive": setStatusInactive(); break;
        }
        return new Mst_EditUserTypePage(driver);
    }
    
    public Mst_AllUserTypesPage pressSave() {
        WebElement wait = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(saveButton));
        driver.findElement(saveButton).click();
        
        return new Mst_AllUserTypesPage(driver);
    }
    
    public void waitForElement() {
        WebElement wait = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(levelField));
    }
    
    public void checkFields() {
        //Wait for all elements to be clickable
        WebElement userType = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(userTypeField));
        WebElement level = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(levelField));
        WebElement description = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(descriptionField));
        WebElement statusActive = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(statusActiveBtn));
        WebElement statusInactive = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(statusInactiveBtn));
        WebElement save = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(saveButton));
        WebElement cancel = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(cancelButton));
        
        //Assert all elements are displayed
        AssertJUnit.assertTrue("Edit User Type Page: User Type Field not displayed as expected",getUserTypeField().isDisplayed());
        AssertJUnit.assertTrue("Edit User Type Page: Level Field not displayed as expected",getLevelField().isDisplayed());
        AssertJUnit.assertTrue("Edit User Type Page: Description Field not displayed as expected",getDescriptionField().isDisplayed());
        AssertJUnit.assertTrue("Edit User Type Page: Status Active Button not displayed as expected",getStatusActiveBtn().isDisplayed());
        AssertJUnit.assertTrue("Edit User Type Page: Status Inactive Button not displayed as expected",getStatusInactiveBtn().isDisplayed());
        AssertJUnit.assertTrue("Edit User Type Page: Save Button not displayed as expected",getSaveButton().isDisplayed());
        AssertJUnit.assertTrue("Edit User Type Page: Cancel Button not displayed as expected",getCancelButton().isDisplayed());
    }
    
}
