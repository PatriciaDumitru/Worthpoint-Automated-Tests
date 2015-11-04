
package PageObjects;

import AutomationFramework.CommonTask;
import AutomationFramework.DataItems;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.AssertJUnit;

/**
 *
 * @author ukspjsykes
 */
public class Mst_AddUserTypePage extends WBA_BasePage {
    
    //Locators
    By userTypeField = By.id("UserTypeUserType");
    By requesterTypeField = By.id("UserTypeUserCategoryId");
    By levelField = By.id("UserTypeLevelId");
    By descriptionField = By.id("UserTypeDescription");
    By statusActiveBtn = By.id("UserTypeStatusId1");
    By statusInactiveBtn = By.id("UserTypeStatusId2");
    By saveButton = By.cssSelector("#UserTypeAddForm > div.actions > ul > li:nth-child(1) > input[type=\"submit\"]");
    By cancelButton = By.cssSelector("#UserTypeAddForm > div.actions > ul > li:nth-child(2) > a");
    
    public Mst_AddUserTypePage(WebDriver driver) {
        super(driver);
    }
    
    public WebElement getBreadcrumb() {
        return driver.findElement(DataItems.breadcrumbLocator);
    }
    
    public WebElement getUserTypeField() {
        return driver.findElement(userTypeField);
    }
    
    public WebElement getRequesterTypeField() {
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
    
    public Mst_AddUserTypePage setUserType(String item) {
        CommonTask.setInputField(driver,userTypeField,item);
        
        return new Mst_AddUserTypePage(driver);
    }
    
    public Mst_AddUserTypePage setRequesterType(String item) throws InterruptedException {
        CommonTask.setDropDownField(driver, requesterTypeField, item);
        
        return new Mst_AddUserTypePage(driver);
    }
    
    public Mst_AddUserTypePage setLevel(String item) throws InterruptedException {
        CommonTask.setDropDownField(driver, levelField, item);
        
        return new Mst_AddUserTypePage(driver);
    }
    
    public Mst_AddUserTypePage setDescription(String item) {
        CommonTask.setInputField(driver, descriptionField, item);
        
        return new Mst_AddUserTypePage(driver);
    }
    
    public Mst_AllUserTypesPage pressSave() {
        WebElement wait = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(saveButton));
        driver.findElement(saveButton).click();
        
        return new Mst_AllUserTypesPage(driver);
    }
    
    public Mst_AllUserTypesPage pressCancel() {
        WebElement wait = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(cancelButton));
        driver.findElement(cancelButton).click();
        
        return new Mst_AllUserTypesPage(driver);
    }
    
    public void waitForElement() {
        WebElement wait = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(cancelButton));
    }
    
    public void checkFields() {
        //Wait for elements to be clickable
        WebElement userType = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(userTypeField));
        WebElement requesterType = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(requesterTypeField));
        WebElement level = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(levelField));
        WebElement description = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(descriptionField));
        WebElement statusActive = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(statusActiveBtn));
        WebElement statusInactive = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(statusInactiveBtn));
        WebElement save = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(saveButton));
        WebElement cancel = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(cancelButton));
        
        //Assert all elements are displayed
        AssertJUnit.assertTrue("Add User Type Page: User Type field not displayed as expected",getUserTypeField().isDisplayed());
        AssertJUnit.assertTrue("Add User Type Page: Requester Type field not displayed as expected",getRequesterTypeField().isDisplayed());
        AssertJUnit.assertTrue("Add User Type Page: Level field not displayed as expected",getLevelField().isDisplayed());
        AssertJUnit.assertTrue("Add User Type Page: Description field not displayed as expected",getDescriptionField().isDisplayed());
        AssertJUnit.assertTrue("Add User Type Page: Status:Active button not displayed as expected",getStatusActiveBtn().isDisplayed());
        AssertJUnit.assertTrue("Add User Type Page: Status:Inactive button not displayed as expected",getStatusInactiveBtn().isDisplayed());
    }
    
}
