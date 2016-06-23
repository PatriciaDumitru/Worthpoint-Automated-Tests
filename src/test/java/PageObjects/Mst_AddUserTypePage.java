
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
        WebElement wait = Wait.clickable(driver,saveButton);
        driver.findElement(saveButton).click();
        
        return new Mst_AllUserTypesPage(driver);
    }
    
    public Mst_AllUserTypesPage pressCancel() {
        WebElement wait = Wait.clickable(driver,cancelButton);
        driver.findElement(cancelButton).click();
        
        return new Mst_AllUserTypesPage(driver);
    }
    
    public void waitForElement() {
        WebElement wait = Wait.clickable(driver,cancelButton);
    }
    
    public void checkFields() {
        //Wait for elements to be clickable
        WebElement userType = Wait.clickable(driver,userTypeField);
        WebElement requesterType = Wait.clickable(driver,requesterTypeField);
        WebElement level = Wait.clickable(driver,levelField);
        WebElement description = Wait.clickable(driver,descriptionField);
        WebElement statusActive = Wait.clickable(driver,statusActiveBtn);
        WebElement statusInactive = Wait.clickable(driver,statusInactiveBtn);
        WebElement save = Wait.clickable(driver,saveButton);
        WebElement cancel = Wait.clickable(driver,cancelButton);
        
        //Assert all elements are displayed
        AssertJUnit.assertTrue("Add User Type Page: User Type field not displayed as expected",userType.isDisplayed());
        AssertJUnit.assertTrue("Add User Type Page: Requester Type field not displayed as expected",requesterType.isDisplayed());
        AssertJUnit.assertTrue("Add User Type Page: Level field not displayed as expected",level.isDisplayed());
        AssertJUnit.assertTrue("Add User Type Page: Description field not displayed as expected",description.isDisplayed());
        AssertJUnit.assertTrue("Add User Type Page: Status Active button not displayed as expected",statusActive.isDisplayed());
        AssertJUnit.assertTrue("Add User Type Page: Status Inactive button not displayed as expected",statusInactive.isDisplayed());
        AssertJUnit.assertTrue("Add User Type Page: Save button not displayed as expected",save.isDisplayed());
        AssertJUnit.assertTrue("Add User Type Page: Cancel button not displayed as expected",cancel.isDisplayed());
    }
    
}
