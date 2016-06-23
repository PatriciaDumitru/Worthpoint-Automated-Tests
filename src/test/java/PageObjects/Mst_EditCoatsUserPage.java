
package PageObjects;

import AutomationFramework.CommonTask;
import AutomationFramework.DataItems;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.AssertJUnit;


public class Mst_EditCoatsUserPage extends WBA_BasePage {
    
    //Locators
    By firstNameField = By.id("CoatsUserFirstName");
    By surnameField = By.id("CoatsUserLastName");
    By usernameField = By.id("CoatsUserUsername");
    By passwordField = By.id("CoatsUserPassword");
    By userTypeField = By.id("CoatsUserUserTypeId");
    By countryField = By.id("s2id_CoatsUserCountry");
    By salesOrgField = By.id("s2id_CoatsUserSalesOrg");
    By hubField = By.id("s2id_CoatsUserHub");
    By timeZoneField = By.id("s2id_CoatsUserTimezoneId");
    By languageField = By.id("CoatsUserLanguageId");
    By dateFormatField = By.id("CoatsUserDateFormatId");
    By timeFormatField = By.id("CoatsUserTimeFormatId");
    By mobileNumberFields = By.id("CoatsUserMobile");
    By syncYes = By.id("CoatsUserIsSyncEnabled1");
    By syncNo = By.id("CoatsUserIsSyncEnabled0");
    By alltcYes = By.id("CoatsUserIsOrderCancelAllowed1");
    By alltcNo = By.id("CoatsUserIsOrderCancelAllowed0");
    By statusActive = By.id("CoatsUserStatusId1");
    By statusInactive = By.id("CoatsUserStatusId2");

    By saveButton = By.id("submit");
    By cancelButton = By.xpath("//*[@id=\"CoatsUserEditForm\"]/div[3]/ul/li[2]/a");
    
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

    public Mst_AddCoatsUserPage setUsername(String item) {
        CommonTask.setInputField(driver,usernameField,item);
        return new Mst_AddCoatsUserPage(driver);
    }

    public void checkFields() {
        //Wait for all elements to be clickable
        WebElement firstName = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(firstNameField));
        WebElement sureName = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(surnameField));
        WebElement userName = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(usernameField));
        WebElement password = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(passwordField));
        WebElement userType = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(userTypeField));
        WebElement country = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(countryField));
        WebElement salesOrg = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(salesOrgField));
        WebElement hub = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(hubField));
        WebElement timeZone = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(timeZoneField));
        WebElement language = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(languageField));
        WebElement dateFormat = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(dateFormatField));
        WebElement timeFormat = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(timeFormatField));
        WebElement mobileNumber = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(mobileNumberFields));
        WebElement syncY = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(syncYes));
        WebElement syncN = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(syncNo));
        WebElement alltcY = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(alltcYes));
        WebElement alltcN = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(alltcNo));
        WebElement statusAc = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(statusActive));
        WebElement statusIn = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(statusInactive));
        WebElement saveBtn = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(saveButton));
        WebElement cancelBtn = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(cancelButton));

        //Assert all elements are displayed correctly
        AssertJUnit.assertTrue("Edit Coats User Page: First Name field not displayed as expected",firstName.isDisplayed());
        AssertJUnit.assertTrue("Edit Coats User Page: Sure Name field not displayed as expected",sureName.isDisplayed());
        AssertJUnit.assertTrue("Edit Coats User Page: User Name field not displayed as expected",userName.isDisplayed());
        AssertJUnit.assertTrue("Edit Coats User Page: Password field not displayed as expected",password.isDisplayed());
        AssertJUnit.assertTrue("Edit Coats User Page: User Type field not displayed as expected",userType.isDisplayed());
        AssertJUnit.assertTrue("Edit Coats User Page: Country field not displayed as expected",country.isDisplayed());
        AssertJUnit.assertTrue("Edit Coats User Page: Sales Organization field not displayed as expected",salesOrg.isDisplayed());
        AssertJUnit.assertTrue("Edit Coats User Page: Hub field not displayed as expected",hub.isDisplayed());
        AssertJUnit.assertTrue("Edit Coats User Page: Time Zone field not displayed as expected",timeZone.isDisplayed());
        AssertJUnit.assertTrue("Edit Coats User Page: Language field not displayed as expected",language.isDisplayed());
        AssertJUnit.assertTrue("Edit Coats User Page: Date Format field not displayed as expected",dateFormat.isDisplayed());
        AssertJUnit.assertTrue("Edit Coats User Page: Time Format field not displayed as expected",timeFormat.isDisplayed());
        AssertJUnit.assertTrue("Edit Coats User Page: Mobile Number field not displayed as expected",mobileNumber.isDisplayed());
        AssertJUnit.assertTrue("Edit Coats User Page: Synchronization Yes button not displayed as expected",syncY.isDisplayed());
        AssertJUnit.assertTrue("Edit Coats User Page: Synchronization No button not displayed as expected",syncN.isDisplayed());
        AssertJUnit.assertTrue("Edit Coats User Page: Allow To Cancel Yes button not displayed as expected",alltcY.isDisplayed());
        AssertJUnit.assertTrue("Edit Coats User Page: Allow To Cancel No button not displayed as expected",alltcN.isDisplayed());
        AssertJUnit.assertTrue("Edit Coats User Page: Status Active button not displayed as expected",statusAc.isDisplayed());
        AssertJUnit.assertTrue("Edit Coats User Page: Status Inactive button not displayed as expected",statusIn.isDisplayed());
        AssertJUnit.assertTrue("Edit Coats User Page: Save button not displayed as expected",saveBtn.isDisplayed());
        AssertJUnit.assertTrue("Edit Coats User Page: Cancel button not displayed as expected",cancelBtn.isDisplayed());
    }
    
}
