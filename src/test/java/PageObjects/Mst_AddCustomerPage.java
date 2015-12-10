
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
import org.testng.AssertJUnit;


public class Mst_AddCustomerPage extends WBA_BasePage {
    
    By customerNameField = By.id("CustomerCustomerName");
    By salesOrgField = By.id("CustomerSalesOrgId");
    By custCodeField = By.id("CustomerCustomerCode");
    By optionsField = By.id("CustomerNoOfOptions");
    By busPrincField = By.id("s2id_CustomerBusinessPrincipal");
    By cceSolutionField = By.id("CustomerCceSolutionId");
    By priorityField = By.id("CustomerPriorityId");
    By saveButton = By.cssSelector("#CustomerAddForm > div.actions > ul > li:nth-child(1) > input[type=\"submit\"]");
    By cancelButton = By.cssSelector("#CustomerAddForm > div.actions > ul > li:nth-child(2) > a");
    
    public Mst_AddCustomerPage(WebDriver driver) {
        super(driver);
    }
    
    public WebElement getBreadcrumb() {
        return Wait.visible(driver,DataItems.breadcrumbLocator);
    }
    
    public Mst_AddCustomerPage setCustomerName(String item) {
        CommonTask.setInputField(driver, customerNameField, item);
        return new Mst_AddCustomerPage(driver);
    }
    
    public Mst_AddCustomerPage setSalesOrg(String item) throws InterruptedException {
        CommonTask.setDropDownField(driver, salesOrgField, item);
        return new Mst_AddCustomerPage(driver);
    }
    
    public Mst_AddCustomerPage setCustomerCode(String item) {
        CommonTask.setInputField(driver, custCodeField, item);
        return new Mst_AddCustomerPage(driver);
    }
    
    public Mst_AddCustomerPage setNoOfOptions(String item) {
        CommonTask.setInputField(driver, optionsField, item);
        return new Mst_AddCustomerPage(driver);
    }
    
    public Mst_AddCustomerPage setPrincipalName(String item) {
        
        WebElement field = Wait.clickable(driver,busPrincField);
        field.click();
        
        By searchField = By.id("s2id_autogen1");
        WebElement search = Wait.clickable(driver,searchField);
        search.sendKeys(item);
        
        By resultField = By.cssSelector("#select2-drop > ul > li.select2-results-dept-0.select2-result.select2-result-selectable.select2-highlighted > div > span");
        boolean result = Wait.textPresent(driver,resultField,item);
        search.sendKeys(Keys.ENTER);
        
        By choiceField = By.cssSelector("#s2id_CustomerBusinessPrincipal > ul > li.select2-search-choice > div");
        Boolean choice = Wait.textPresent(driver,choiceField, item);
        
        return new Mst_AddCustomerPage(driver);
    }
    
    public Mst_AddCustomerPage setCCESolution(String item) throws InterruptedException {
        CommonTask.setDropDownField(driver, cceSolutionField, item);
        return new Mst_AddCustomerPage(driver);
    }
    
    public Mst_AddCustomerPage setPriority(String item) throws InterruptedException {
        CommonTask.setDropDownField(driver, priorityField, item);
        return new Mst_AddCustomerPage(driver);
    }
    
    public Mst_AddCustomerPage setRequesterFirstName(int row, String item) {
        By firstNameField = By.id("Requester"+row+"FirstName");
        CommonTask.setInputField(driver, firstNameField, item);
        
        return new Mst_AddCustomerPage(driver);
    }
    
    public Mst_AddCustomerPage setRequesterUsername(int row, String item) {
        By usernameField = By.id("Requester"+row+"Username");
        CommonTask.setInputField(driver, usernameField, item);
        
        return new Mst_AddCustomerPage(driver);
    }
    
    public Mst_AddCustomerPage setRequesterPassword(int row, String item) {
        By passwordField = By.id("Requester"+row+"Password");
        CommonTask.setInputField(driver, passwordField, item);
        
        return new Mst_AddCustomerPage(driver);
    }
    
    public Mst_AddCustomerPage setRequesterUsertype(int row, String item) throws InterruptedException {
        By userTypeField = By.id("Requester"+row+"UserTypeId");
        CommonTask.setDropDownField(driver, userTypeField, item);
        
        return new Mst_AddCustomerPage(driver);
    }
    
    public Mst_AddCustomerPage setRequesterLanguage(int row, String item) throws InterruptedException {
        By languageField = By.id("Requester"+row+"LanguageId");
        CommonTask.setDropDownField(driver, languageField, item);
        
        return new Mst_AddCustomerPage(driver);
    }
    
    public Mst_AddCustomerPage setRequesterCountry(int row, String item) throws InterruptedException {
        By countryField = By.id("Requester"+row+"CountryId");
        CommonTask.setDropDownField(driver,countryField,item);
        
        return new Mst_AddCustomerPage(driver);
    }
    
    public Mst_CustomersPage pressSave() {
        WebElement element = Wait.clickable(driver,saveButton);
        element.click();
        
        return new Mst_CustomersPage(driver);
    }
    
    public void checkFields() {
        WebElement custName = Wait.clickable(driver,customerNameField);
        WebElement salesOrg = Wait.clickable(driver,salesOrgField);
        WebElement custCode = Wait.clickable(driver,custCodeField);
        WebElement options = Wait.clickable(driver,optionsField);
        WebElement busPrinc = Wait.clickable(driver,busPrincField);
        WebElement cceSolution = Wait.clickable(driver,cceSolutionField);
        WebElement priority = Wait.clickable(driver,priorityField);
        WebElement save = Wait.clickable(driver,saveButton);
        WebElement cancel = Wait.clickable(driver,cancelButton);
        
        AssertJUnit.assertTrue("Add Customer Page: Customer Name field not displayed",custName.isDisplayed());
        AssertJUnit.assertTrue("Add Customer Page: Sales Org field not displayed",salesOrg.isDisplayed());
        AssertJUnit.assertTrue("Add Customer Page: Customer Code field not displayed",custCode.isDisplayed());
        AssertJUnit.assertTrue("Add Customer Page: No of Options field not displayed",options.isDisplayed());
        AssertJUnit.assertTrue("Add Customer Page: Business Principal field not displayed",busPrinc.isDisplayed());
        AssertJUnit.assertTrue("Add Customer Page: CCE Solution field not displayed",cceSolution.isDisplayed());
        AssertJUnit.assertTrue("Add Customer Page: Priority field not displayed",priority.isDisplayed());
        AssertJUnit.assertTrue("Add Customer Page: Save button not displayed",priority.isDisplayed());
        AssertJUnit.assertTrue("Add Customer Page: Cancel button not displayed",priority.isDisplayed());
    }
    
    public void waitForElement() {
        WebElement busPrinc = Wait.clickable(driver,busPrincField);
    }
    
}
