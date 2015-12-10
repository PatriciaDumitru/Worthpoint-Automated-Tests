
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

public class Mst_AddSalesOrgPage extends WBA_BasePage {
    
    //Locators
    By nameField = By.id("SalesOrgSalesOrgName");
    By descField = By.id("SalesOrgDescription");
    By sapInstanceField = By.id("SalesOrgSapInstance");
    By contractOrderField = By.id("SalesOrgOffOrder");
    By saveButton = By.id("save");
    By cancelButton = By.cssSelector("#SalesOrgAddForm > div.actions > ul > li:nth-child(2) > a");
    
    public Mst_AddSalesOrgPage(WebDriver driver) {
        super(driver);
    }
    
    public WebElement getBreadcrumb() {
        WebElement element = Wait.visible(driver,DataItems.breadcrumbLocator);
        return element;
    }
    
    public Mst_AddSalesOrgPage setSalesOrg(String item) {
        CommonTask.setInputField(driver, nameField, item);
        return new Mst_AddSalesOrgPage(driver);
    }
    
    public Mst_AddSalesOrgPage setDescription(String item) {
        CommonTask.setInputField(driver, descField, item);
        return new Mst_AddSalesOrgPage(driver);
    }
    
    public Mst_AddSalesOrgPage setSAPInstance(String item) {
        CommonTask.setInputField(driver, sapInstanceField, item);
        return new Mst_AddSalesOrgPage(driver);
    }
    
    public Mst_SalesOrgPage pressSave() {
        WebElement element = Wait.clickable(driver,saveButton);
        element.click();
        
        return new Mst_SalesOrgPage(driver);
    } 
    
    public Mst_SalesOrgPage pressCancel() {
        WebElement element = Wait.clickable(driver,cancelButton);
        element.click();
        
        return new Mst_SalesOrgPage(driver);
    } 
    
    public void checkFields() {
        //Wait for all elements to be clickable
        WebElement name = Wait.clickable(driver,nameField);
        WebElement desc = Wait.clickable(driver,descField);
        WebElement sapInstance = Wait.clickable(driver,sapInstanceField);
        WebElement co = Wait.clickable(driver,contractOrderField);
        WebElement save = Wait.clickable(driver,saveButton);
        WebElement cancel = Wait.clickable(driver,cancelButton);
        
        //Assert all elements are displayed
        AssertJUnit.assertTrue("Add Sales Org Page: Name field not displayed",name.isDisplayed());
        AssertJUnit.assertTrue("Add Sales Org Page: Description field not displayed",desc.isDisplayed());
        AssertJUnit.assertTrue("Add Sales Org Page: Contract Order field not displayed",co.isDisplayed());
        AssertJUnit.assertTrue("Add Sales Org Page: SAP Instance field not displayed",sapInstance.isDisplayed());
        AssertJUnit.assertTrue("Add Sales Org Page: Save button not displayed",save.isDisplayed());
        AssertJUnit.assertTrue("Add Sales Org Page: Cancel button not displayed",cancel.isDisplayed());
    }
    
    public void waitForElement() {
        WebElement sapInstance = Wait.clickable(driver,sapInstanceField);
    }

}
