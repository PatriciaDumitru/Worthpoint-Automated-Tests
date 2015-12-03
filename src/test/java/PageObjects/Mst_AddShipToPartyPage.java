
package PageObjects;

import AutomationFramework.CommonTask;
import AutomationFramework.DataItems;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.AssertJUnit;

public class Mst_AddShipToPartyPage extends WBA_BasePage {
    
    By fceField = By.id("s2id_ShipToPartyFceId");
    By salesOrgField = By.id("ShipToPartySalesOrgId");
    By custNameField = By.id("s2id_ShipToPartyCustomerId");
    By partyNumberField = By.id("ShipToPartyShipToPartyNo");
    By partyNameField = By.id("ShipToPartyShipToPartyName");
    By addressField = By.id("ShipToPartyAddressLine1");
    By streetField = By.id("ShipToPartyStreet");
    By cityField = By.id("ShipToPartyCity");
    By stateField = By.id("ShipToPartyState");
    By zipCodeField = By.id("ShipToPartyZipCode");
    By countryCodeField = By.id("ShipToPartyCountryId");
    By hubNameField = By.id("ShipToPartyHubId");
    By saveButton = By.cssSelector("#ShipToPartyAddForm > div.actions > ul > li:nth-child(1) > input[type=\"submit\"]");
    By cancelButton = By.cssSelector("#ShipToPartyAddForm > div.actions > ul > li:nth-child(2) > a");
    
    public Mst_AddShipToPartyPage(WebDriver driver) {
        super(driver);
    }
    
    public WebElement getBreadcrumb() {
        return new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.visibilityOfElementLocated(DataItems.breadcrumbLocator));
    }
    
    public Mst_AddShipToPartyPage setFCE(String item) {
        CommonTask.setSearchField(driver,fceField,item);
        return new Mst_AddShipToPartyPage(driver);
    }
    
    public Mst_AddShipToPartyPage setSalesOrg(String item) throws InterruptedException {
        CommonTask.setDropDownField(driver,salesOrgField,item);
        return new Mst_AddShipToPartyPage(driver);
    }
    
    public Mst_AddShipToPartyPage setCustomerName(String item) {
        CommonTask.setSearchField(driver,custNameField,item);
        return new Mst_AddShipToPartyPage(driver);
    }
    
    public Mst_AddShipToPartyPage setPartyNumber(String item) {
        CommonTask.setInputField(driver,partyNumberField,item);
        return new Mst_AddShipToPartyPage(driver);
    }
    
    public Mst_AddShipToPartyPage setPartyName(String item) {
        CommonTask.setInputField(driver,partyNameField,item);
        return new Mst_AddShipToPartyPage(driver);
    }
    
    public Mst_AddShipToPartyPage setAddress(String item) {
        CommonTask.setInputField(driver,addressField,item);
        return new Mst_AddShipToPartyPage(driver);
    }
    
    public Mst_AddShipToPartyPage setStreet(String item) {
        CommonTask.setInputField(driver,streetField,item);
        return new Mst_AddShipToPartyPage(driver);
    }
    
    public Mst_AddShipToPartyPage setCity(String item) {
        CommonTask.setInputField(driver,cityField,item);
        return new Mst_AddShipToPartyPage(driver);
    }
    
    public Mst_AddShipToPartyPage setState(String item) {
        CommonTask.setInputField(driver,stateField,item);
        return new Mst_AddShipToPartyPage(driver);
    }
    
    public Mst_AddShipToPartyPage setZipCode(String item) {
        CommonTask.setInputField(driver,zipCodeField,item);
        return new Mst_AddShipToPartyPage(driver);
    }
    
    public Mst_AddShipToPartyPage setCountryCode(String item) throws InterruptedException {
        CommonTask.setDropDownField(driver,countryCodeField,item);
        return new Mst_AddShipToPartyPage(driver);
    }
    
    public Mst_AddShipToPartyPage setHubName(String item) throws InterruptedException {
        CommonTask.setDropDownField(driver,hubNameField,item);
        return new Mst_AddShipToPartyPage(driver);
    }
    
    public Mst_ShipToPartiesPage pressSave() {
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(saveButton));
        element.click();
        
        return new Mst_ShipToPartiesPage(driver);
    }
    
    public void checkFields() {
        WebElement fce = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(fceField));
        WebElement salesOrg = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(salesOrgField));
        WebElement custName = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(custNameField));
        WebElement partyNumber = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(partyNumberField));
        WebElement partyName = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(partyNameField));
        WebElement address = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(addressField));
        WebElement street = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(streetField));
        WebElement city = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(cityField));
        WebElement state = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(stateField));
        WebElement zipCode = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(zipCodeField));
        WebElement countryCode = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(countryCodeField));
        WebElement hubName = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(hubNameField));
        WebElement save = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(saveButton));
        WebElement cancel = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(cancelButton));
        
        AssertJUnit.assertTrue("Add Ship To Party Page: FCE field not displayed",fce.isDisplayed());
        AssertJUnit.assertTrue("Add Ship To Party Page: Sales Org field not displayed",salesOrg.isDisplayed());
        AssertJUnit.assertTrue("Add Ship To Party Page: Customer Name field not displayed",custName.isDisplayed());
        AssertJUnit.assertTrue("Add Ship To Party Page: Party number field not displayed",partyNumber.isDisplayed());
        AssertJUnit.assertTrue("Add Ship To Party Page: Party name field not displayed",partyName.isDisplayed());
        AssertJUnit.assertTrue("Add Ship To Party Page: Address field not displayed",address.isDisplayed());
        AssertJUnit.assertTrue("Add Ship To Party Page: Street field not displayed",street.isDisplayed());
        AssertJUnit.assertTrue("Add Ship To Party Page: City field not displayed",city.isDisplayed());
        AssertJUnit.assertTrue("Add Ship To Party Page: State field not displayed",state.isDisplayed());
        AssertJUnit.assertTrue("Add Ship To Party Page: Zip Code field not displayed",zipCode.isDisplayed());
        AssertJUnit.assertTrue("Add Ship To Party Page: Country Code field not displayed",countryCode.isDisplayed());
        AssertJUnit.assertTrue("Add Ship To Party Page: Hub Name field not displayed",hubName.isDisplayed());
        AssertJUnit.assertTrue("Add Ship To Party Page: Save button not displayed",save.isDisplayed());
        AssertJUnit.assertTrue("Add Ship To Party Page: Cancel button not displayed",cancel.isDisplayed());
    }
    
    public void waitForElement() {
        WebElement partyNumber = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(partyNumberField));
    }
    
}
