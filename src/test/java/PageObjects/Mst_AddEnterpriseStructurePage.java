
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

public class Mst_AddEnterpriseStructurePage extends WBA_BasePage {
    
    //Locators
    By regionField = By.id("EnterpriseStructureRegionId");
    By countryCodeField = By.id("EnterpriseStructureCountryId");
    By salesOrgField = By.id("EnterpriseStructureSalesOrgId");
    By hubField = By.id("EnterpriseStructureHubId");
    By saveButton = By.cssSelector("#EnterpriseStructureAddForm > div.actions > ul > li:nth-child(1) > input[type=\"submit\"]");
    By cancelButton = By.cssSelector("#EnterpriseStructureAddForm > div.actions > ul > li:nth-child(2) > a");
    
    public Mst_AddEnterpriseStructurePage(WebDriver driver) {
        super(driver);
    }
    
    public WebElement getBreadcrumb() {
        return new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.visibilityOfElementLocated(DataItems.breadcrumbLocator));
    }
    
    public Mst_AddEnterpriseStructurePage setRegion(String item) throws InterruptedException {
        CommonTask.setDropDownField(driver, regionField, item);
        return new Mst_AddEnterpriseStructurePage(driver);
    }
    
    public Mst_AddEnterpriseStructurePage setCountryCode(String item) throws InterruptedException {
        CommonTask.setDropDownField(driver, countryCodeField, item);
        return new Mst_AddEnterpriseStructurePage(driver);
    }
    
    public Mst_AddEnterpriseStructurePage setSalesOrg(String item) throws InterruptedException {
        CommonTask.setDropDownField(driver, salesOrgField, item);
        return new Mst_AddEnterpriseStructurePage(driver);
    }
    
    public Mst_AddEnterpriseStructurePage setHub(String item) throws InterruptedException {
        CommonTask.setDropDownField(driver, hubField, item);
        return new Mst_AddEnterpriseStructurePage(driver);
    }
    
    public Mst_EnterpriseStructurePage pressSave() throws InterruptedException {
        WebElement element = Wait.clickable(driver,saveButton);
        element.click();
        
        return new Mst_EnterpriseStructurePage(driver);
    }
    
    public void waitForElement() {
        WebElement salesOrg = Wait.clickable(driver,salesOrgField);
    }
    
    public void checkFields() {
        WebElement region = Wait.clickable(driver,regionField);
        WebElement countryCode = Wait.clickable(driver,countryCodeField);
        WebElement salesOrg = Wait.clickable(driver,salesOrgField);
        WebElement hub = Wait.clickable(driver,hubField);
        WebElement save = Wait.clickable(driver,saveButton);
        WebElement cancel = Wait.clickable(driver,cancelButton);
        
        AssertJUnit.assertTrue("Add Enterprise Structure Page: Region field not displayed",region.isDisplayed());
        AssertJUnit.assertTrue("Add Enterprise Structure Page: Country Code field not displayed",countryCode.isDisplayed());
        AssertJUnit.assertTrue("Add Enterprise Structure Page: Sales Organisation field not displayed",salesOrg.isDisplayed());
        AssertJUnit.assertTrue("Add Enterprise Structure Page: Hub field not displayed",hub.isDisplayed());
        AssertJUnit.assertTrue("Add Enterprise Structure Page: Save button not displayed",save.isDisplayed());
        AssertJUnit.assertTrue("Add Enterprise Structure Page: Cancel button not displayed",cancel.isDisplayed());
        
    }
    
}
