
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

public class Mst_EditCountryPage extends WBA_BasePage {
    
    //Locators
    By countryNameField = By.id("s2id_CountryCountryName");
    By countryCodeField = By.id("CountryCountryCode");
    By languageField = By.id("CountryLanguageId");
    By saveButton = By.cssSelector("#CountryEditForm > div.actions > ul > li:nth-child(1) > input[type=\"submit\"]");
    By cancelButton = By.cssSelector("#CountryEditForm > div.actions > ul > li:nth-child(2) > a");
    
    public Mst_EditCountryPage(WebDriver driver) {
        super(driver);
    }
    
    public WebElement getBreadcrumb() {
        return new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.visibilityOfElementLocated(DataItems.breadcrumbLocator));
    }
    
    public Mst_EditCountryPage setCountryName(String item) {
        CommonTask.setSearchField(driver,countryNameField,item);
        return new Mst_EditCountryPage(driver);
    }
    
    public Mst_EditCountryPage setCountryCode(String item) {
        CommonTask.setInputField(driver,countryCodeField,item);
        return new Mst_EditCountryPage(driver);
    }
    
    public Mst_EditCountryPage setLanguage(String item) throws InterruptedException {
        CommonTask.setDropDownField(driver,languageField,item);
        return new Mst_EditCountryPage(driver);
    }
    
    public Mst_CountriesPage pressSave() {
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(saveButton));
        element.click();
        
        return new Mst_CountriesPage(driver);
    }
    
    public Mst_CountriesPage pressCancel() {
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(cancelButton));
        element.click();
        
        return new Mst_CountriesPage(driver);
    }
    
    public void checkFields() {
        WebElement countryName = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(countryNameField));
        WebElement countryCode = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(countryCodeField));
        WebElement language = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(languageField));
        WebElement save = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(saveButton));
        WebElement cancel = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(cancelButton));
        
        AssertJUnit.assertTrue("Add Country Page: Country Name field not displayed",countryName.isDisplayed());
        AssertJUnit.assertTrue("Add Country Page: Country Code field not displayed",countryCode.isDisplayed());
        AssertJUnit.assertTrue("Add Country Page: Language field not displayed",language.isDisplayed());
        AssertJUnit.assertTrue("Add Country Page: Save button not displayed",save.isDisplayed());
        AssertJUnit.assertTrue("Add Country Page: Cancel button not displayed",cancel.isDisplayed());
    }
    
    public void waitForElement() {
        WebElement language = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(languageField));
    }
    
}
