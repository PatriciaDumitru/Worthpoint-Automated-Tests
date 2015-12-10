
package PageObjects;

import AutomationFramework.CommonTask;
import AutomationFramework.DataItems;
import AutomationFramework.Wait;
import static PageObjects.WBA_BasePage.driver;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
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
        return Wait.visible(driver,breadcrumbLocator);
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
        WebElement field = Wait.clickable(driver,countryField);

        field.click();
        
        By searchFieldLocator = By.id("s2id_autogen1");
        WebElement searchField = Wait.clickable(driver,searchFieldLocator);
        searchField.sendKeys(item+Keys.ENTER);

        By choiceLocator = By.cssSelector("#s2id_CoatsUserCountry > ul > li.select2-search-choice > div");
        WebElement choice = Wait.visible(driver,choiceLocator);
        
        return new Mst_AddCoatsUserPage(driver);
    }
    
    public Mst_AddCoatsUserPage setSalesOrg(String item) throws InterruptedException {
        WebElement field = Wait.clickable(driver,salesOrgField);

        field.click();
        
        By searchFieldLocator = By.id("s2id_autogen2");
        WebElement searchField = Wait.clickable(driver,searchFieldLocator);
        searchField.sendKeys(item+Keys.ENTER);

        By choiceLocator = By.cssSelector("#s2id_CoatsUserSalesOrg > ul > li.select2-search-choice > div");
        WebElement choice = Wait.visible(driver,choiceLocator);
        return new Mst_AddCoatsUserPage(driver);
    }
    
    public Mst_AddCoatsUserPage setHub(String item) throws InterruptedException {
        WebElement field = Wait.clickable(driver,hubField);

        field.click();
        
        By searchFieldLocator = By.id("s2id_autogen3");
        WebElement searchField = Wait.clickable(driver,searchFieldLocator);
        searchField.sendKeys(item+Keys.ENTER);

        By choiceLocator = By.cssSelector("#s2id_CoatsUserHub > ul > li.select2-search-choice > div");
        WebElement choice = Wait.visible(driver,choiceLocator);
        
        return new Mst_AddCoatsUserPage(driver);
    }
    
    public Mst_CoatsUsersPage pressSave() {
        WebElement save = Wait.clickable(driver,saveButton);
        save.click();
        return new Mst_CoatsUsersPage(driver);
    }
    
    public void waitForElement() {
        WebElement wait = Wait.clickable(driver,timeZoneField);
    }
    
}
