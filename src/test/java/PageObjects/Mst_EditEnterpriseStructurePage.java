
package PageObjects;

import AutomationFramework.CommonTask;
import AutomationFramework.DataItems;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.AssertJUnit;

public class Mst_EditEnterpriseStructurePage extends WBA_BasePage {
    
    //Locators
    By regionField = By.id("EnterpriseStructureRegionId");
    By countryCodeField = By.id("EnterpriseStructureCountryId");
    By salesOrgField = By.id("EnterpriseStructureSalesOrgId");
    By hubField = By.id("EnterpriseStructureHubId");
    By saveButton = By.cssSelector("#EnterpriseStructureEditForm > div.actions > ul > li:nth-child(1) > input[type=\"submit\"]");
    By cancelButton = By.cssSelector("#EnterpriseStructureEditForm > div.actions > ul > li:nth-child(2) > a");
    
    public Mst_EditEnterpriseStructurePage(WebDriver driver) {
        super(driver);
    }
    
    public WebElement getBreadcrumb() {
        return new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.visibilityOfElementLocated(DataItems.breadcrumbLocator));
    }
    
    public Mst_EditEnterpriseStructurePage setRegion(String item) throws InterruptedException {
        CommonTask.setDropDownField(driver, regionField, item);
        return new Mst_EditEnterpriseStructurePage(driver);
    }
    
    public Mst_EditEnterpriseStructurePage setCountryCode(String item) throws InterruptedException {
        CommonTask.setDropDownField(driver, countryCodeField, item);
        return new Mst_EditEnterpriseStructurePage(driver);
    }
    
    public Mst_EditEnterpriseStructurePage setSalseOrg(String item) throws InterruptedException {
        CommonTask.setDropDownField(driver, salesOrgField, item);
        return new Mst_EditEnterpriseStructurePage(driver);
    }
    
    public Mst_EditEnterpriseStructurePage setHub(String item) throws InterruptedException {
        CommonTask.setDropDownField(driver, hubField, item);
        return new Mst_EditEnterpriseStructurePage(driver);
    }
    
    public Mst_EnterpriseStructurePage pressSave() throws InterruptedException {
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(saveButton));
        element.click();
        
        return new Mst_EnterpriseStructurePage(driver);
    }
    
    public void waitForElement() {
        WebElement salesOrg = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(salesOrgField));
    }
    
    public void checkFields() {
        WebElement region = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(regionField));
        WebElement countryCode = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(countryCodeField));
        WebElement salesOrg = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(salesOrgField));
        WebElement hub = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(hubField));
        WebElement save = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(saveButton));
        WebElement cancel = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(cancelButton));
        
        AssertJUnit.assertTrue("Edit Enterprise Structure Page: Region field not displayed",region.isDisplayed());
        AssertJUnit.assertTrue("Edit Enterprise Structure Page: Country Code field not displayed",countryCode.isDisplayed());
        AssertJUnit.assertTrue("Edit Enterprise Structure Page: Sales Organisation field not displayed",salesOrg.isDisplayed());
        AssertJUnit.assertTrue("Edit Enterprise Structure Page: Hub field not displayed",hub.isDisplayed());
        AssertJUnit.assertTrue("Edit Enterprise Structure Page: Save button not displayed",save.isDisplayed());
        AssertJUnit.assertTrue("Edit Enterprise Structure Page: Cancel button not displayed",cancel.isDisplayed());
        
    }
    
}
