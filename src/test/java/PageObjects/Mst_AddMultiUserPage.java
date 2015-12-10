
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

public class Mst_AddMultiUserPage extends WBA_BasePage {
    
    //Locators
    By firstNameField = By.id("MultiUserFirstName");
    By lastNameField = By.id("MultiUserLastName");
    By userNameField = By.id("MultiUserUsername");
    By passwordField = By.id("MultiUserPassword");
    By emailField = By.id("MultiUserCommonEmail");
    By userTypeField = By.id("MultiUserUserTypeId");
    By countryField = By.id("MultiUserCountry");
    By salesOrgField = By.id("MultiUserSalesOrg");
    By customerNameField = By.id("s2id_MultiUserCustomer");
    By shipToPartyField = By.id("s2id_MultiUserShipToPartyId");
    By saveButton = By.id("submit");
    By cancelButton = By.cssSelector("#MultiUserAddForm > div.actions > ul > li:nth-child(2) > a");
    
    public Mst_AddMultiUserPage(WebDriver driver) {
        super(driver);
    }
    
    public WebElement getBreadcrumb() {
        return Wait.visible(driver,DataItems.breadcrumbLocator);
    }
    
    public Mst_AddMultiUserPage setFirstName(String item) {
        CommonTask.setInputField(driver, firstNameField, item);
        return new Mst_AddMultiUserPage(driver);
    }
    
    public Mst_AddMultiUserPage setLastName(String item) {
        CommonTask.setInputField(driver, lastNameField, item);
        return new Mst_AddMultiUserPage(driver);
    }
    
    public Mst_AddMultiUserPage setUserName(String item) {
        CommonTask.setInputField(driver, userNameField, item);
        return new Mst_AddMultiUserPage(driver);
    }
    
    public Mst_AddMultiUserPage setPassword(String item) {
        CommonTask.setInputField(driver, passwordField, item);
        return new Mst_AddMultiUserPage(driver);
    }
    
    public Mst_AddMultiUserPage setEmail(String item) {
        CommonTask.setInputField(driver, emailField, item);
        return new Mst_AddMultiUserPage(driver);
    }
    
    public Mst_AddMultiUserPage setUserType(String item) throws InterruptedException {
        CommonTask.setDropDownField(driver, userTypeField, item);
        return new Mst_AddMultiUserPage(driver);
    }
    
    public Mst_AddMultiUserPage setCountry(String item) throws InterruptedException {
        CommonTask.setDropDownField(driver, countryField, item);
        return new Mst_AddMultiUserPage(driver);
    }
    
    public Mst_AddMultiUserPage setSalesOrg(String item) throws InterruptedException {
        CommonTask.setDropDownField(driver, salesOrgField, item);
        return new Mst_AddMultiUserPage(driver);
    }
    
    public Mst_AddMultiUserPage setCustomerName(String item, String append) throws InterruptedException {
        
        WebElement field = Wait.clickable(driver,customerNameField);
        field.click();
        
        By searchField = By.id("s2id_autogen1");
        WebElement search = Wait.clickable(driver,searchField);
        
        search.sendKeys(item);
        
        By resultLocator = By.cssSelector("#select2-drop > ul > li > div > span");
        boolean result = Wait.textPresent(driver,resultLocator,item);
        
        driver.findElement(resultLocator).click();
        
        return new Mst_AddMultiUserPage(driver);
    }
    
    public Mst_AddMultiUserPage setShipToParty(String item,String append) throws InterruptedException {
        WebElement field = Wait.clickable(driver,customerNameField);
        field.click();
        
        By searchField = By.id("s2id_autogen2");
        WebElement search = Wait.clickable(driver,searchField);
        
        search.sendKeys(item);
        
        By resultLocator = By.cssSelector("#select2-drop > ul > li > div > span");
        boolean wait = Wait.textPresent(driver,resultLocator,item);
        
        driver.findElement(resultLocator).click();
        return new Mst_AddMultiUserPage(driver);
    }
    
    public Mst_MultiSoldToPage pressSave() {
        WebElement element = Wait.clickable(driver,saveButton);
        element.click();
        
        return new Mst_MultiSoldToPage(driver);
    }
    
    public Mst_MultiSoldToPage pressCancel() {
        WebElement element = Wait.clickable(driver,cancelButton);
        element.click();
        
        return new Mst_MultiSoldToPage(driver);
    }
    
    public void checkFields() {
        //Wait for all elements to be clickable
        WebElement firstName = Wait.clickable(driver,firstNameField);
        WebElement lastName = Wait.clickable(driver,lastNameField);
        WebElement userName = Wait.clickable(driver,userNameField);
        WebElement password = Wait.clickable(driver,passwordField);
        WebElement email = Wait.clickable(driver,emailField);
        WebElement userType = Wait.clickable(driver,userTypeField);
        WebElement country = Wait.clickable(driver,countryField);
        WebElement salesOrg = Wait.clickable(driver,salesOrgField);
        WebElement customerName = Wait.clickable(driver,customerNameField);
        WebElement shipToParty = Wait.clickable(driver,shipToPartyField);
        WebElement saveBtn = Wait.clickable(driver,saveButton);
        WebElement cancelBtn = Wait.clickable(driver,cancelButton);
    
        //Assert all elements are displayed
        AssertJUnit.assertTrue("Add Multi User Page: First name field not displayed",firstName.isDisplayed());
        AssertJUnit.assertTrue("Add Multi User Page: Last name field not displayed",lastName.isDisplayed());
        AssertJUnit.assertTrue("Add Multi User Page: User name field not displayed",userName.isDisplayed());
        AssertJUnit.assertTrue("Add Multi User Page: Password field not displayed",password.isDisplayed());
        AssertJUnit.assertTrue("Add Multi User Page: Email field not displayed",email.isDisplayed());
        AssertJUnit.assertTrue("Add Multi User Page: User Type field not displayed",userType.isDisplayed());
        AssertJUnit.assertTrue("Add Multi User Page: Country field not displayed",country.isDisplayed());
        AssertJUnit.assertTrue("Add Multi User Page: Sales Org field not displayed",salesOrg.isDisplayed());
        AssertJUnit.assertTrue("Add Multi User Page: Customer Name field not displayed",customerName.isDisplayed());
        AssertJUnit.assertTrue("Add Multi User Page: Ship To Party field not displayed",shipToParty.isDisplayed());
        AssertJUnit.assertTrue("Add Multi User Page: Save button not displayed",saveBtn.isDisplayed());
        AssertJUnit.assertTrue("Add Multi User Page: Cancel button not displayed",cancelBtn.isDisplayed());
        
    }
    
    public void waitForElement() {
        WebElement element = Wait.clickable(driver,userNameField);
    }
    
}
