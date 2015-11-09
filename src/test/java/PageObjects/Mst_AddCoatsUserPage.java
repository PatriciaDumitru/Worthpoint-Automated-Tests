
package PageObjects;

import AutomationFramework.CommonTask;
import AutomationFramework.DataItems;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Mst_AddCoatsUserPage extends WBA_BasePage {
    
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
    By saveButton = By.id("submit");
    By cancelButton = By.cssSelector("#CoatsUserAddForm > div.actions > ul > li:nth-child(2) > a");
    
    public Mst_AddCoatsUserPage(WebDriver driver) {
        super(driver);
    }
    
    public WebElement getBreadcrumb() {
        WebElement wait = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.visibilityOfElementLocated(breadcrumbLocator));
        return driver.findElement(DataItems.breadcrumbLocator);
    }
    
    public WebElement getFirstNameField() {
        return driver.findElement(firstNameField);
    }
    
    public WebElement getLastNameField() {
        return driver.findElement(surnameField);
    }
    
    public WebElement getUsernameField() {
        return driver.findElement(usernameField);
    }
    
    public WebElement getPasswordField() {
        return driver.findElement(passwordField);
    }
    
    public WebElement getUserTypeField() {
        return driver.findElement(userTypeField);
    }
    
    public WebElement getCountryField() {
        return driver.findElement(countryField);
    }
    
    public WebElement getSalesOrgField() {
        return driver.findElement(salesOrgField);
    }
    
    public WebElement getHubField() {
        return driver.findElement(hubField);
    }
    
    public WebElement getTimeZoneField() {
        return driver.findElement(timeZoneField);
    }
    
    public WebElement getLanguageField() {
        return driver.findElement(languageField);
    }
    
    public WebElement getDateFormatField() {
        return driver.findElement(dateFormatField);
    }
    
    public WebElement getTimeFormatField() {
        return driver.findElement(timeFormatField);
    }
    
    public WebElement getSaveButton() {
        return driver.findElement(saveButton);
    }
    
    public WebElement getCancelButton() {
        return driver.findElement(cancelButton);
    }
    
    public Mst_AddCoatsUserPage setFirstName(String item) {
        CommonTask.setInputField(driver,firstNameField,item);
        return new Mst_AddCoatsUserPage(driver);
    }
    
    public Mst_AddCoatsUserPage setLastName(String item) {
        CommonTask.setInputField(driver,surnameField,item);
        return new Mst_AddCoatsUserPage(driver);
    }
    
    public Mst_AddCoatsUserPage setUsername(String item) {
        CommonTask.setInputField(driver,usernameField,item);
        return new Mst_AddCoatsUserPage(driver);
    }
    
    public Mst_AddCoatsUserPage setPassword(String item) {
        CommonTask.setInputField(driver,passwordField,item);
        return new Mst_AddCoatsUserPage(driver);
    }
    
    public Mst_AddCoatsUserPage setUserType(String item) throws InterruptedException {
        CommonTask.setDropDownField(driver,userTypeField,item);
        return new Mst_AddCoatsUserPage(driver);
    }
    
    public Mst_AddCoatsUserPage setCountry(String item) throws InterruptedException {
        CommonTask.setDropDownFieldAlt(driver,countryField,item);
        return new Mst_AddCoatsUserPage(driver);
    }
    
    public Mst_AddCoatsUserPage setSalesOrg(String item) throws InterruptedException {
        CommonTask.setDropDownFieldAlt(driver,salesOrgField,item);
        return new Mst_AddCoatsUserPage(driver);
    }
    
    public Mst_AddCoatsUserPage setHub(String item) throws InterruptedException {
        CommonTask.setDropDownFieldAlt(driver,hubField,item);
        return new Mst_AddCoatsUserPage(driver);
    }
    
    public Mst_CoatsUsersPage pressSave() {
        WebElement save = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(saveButton));
        driver.findElement(saveButton).click();
        return new Mst_CoatsUsersPage(driver);
    }
    
    public void waitForElement() {
        WebElement wait = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(timeZoneField));
    }
    
}
