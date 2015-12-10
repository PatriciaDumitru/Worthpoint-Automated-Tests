
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

public class Mst_AddCustShadePage extends WBA_BasePage {
    
    //Locators
    By salesOrgField = By.id("CustomerShadeSalesOrgId");
    By custNameField = By.id("s2id_CustomerShadeCustomerId");
    By custShadeField = By.id("CustomerShadeCustomerShadeName");
    By coatsShadeField = By.id("s2id_CustomerShadeShadeId");
    By saveButton = By.cssSelector("#CustomerShadeAddForm > div.actions > ul > li:nth-child(1) > input[type=\"submit\"]");
    By cancelButton = By.cssSelector("#CustomerShadeAddForm > div.actions > ul > li:nth-child(2) > a");
    
    public Mst_AddCustShadePage(WebDriver driver) {
        super(driver);
    }
    
    public WebElement getBreadcrumb() {
        return Wait.visible(driver,DataItems.breadcrumbLocator);
    }
    
    public Mst_AddCustShadePage setSalesOrg(String item) throws InterruptedException {
        CommonTask.setDropDownField(driver, salesOrgField, item);
        return new Mst_AddCustShadePage(driver);
    }
    
    public Mst_AddCustShadePage setCustomerName(String item) {
        CommonTask.setSearchField(driver, custNameField, item);
        return new Mst_AddCustShadePage(driver);
    }
    
    public Mst_AddCustShadePage setCustomerShade(String item) {
        CommonTask.setInputField(driver, custShadeField, item);
        return new Mst_AddCustShadePage(driver);
    }
    
    public Mst_AddCustShadePage setCoatsShade(String item) {
        CommonTask.setSearchField(driver, coatsShadeField, item);
        return new Mst_AddCustShadePage(driver);
    }
    
    public Mst_AddCustShadePage pressSave() {
        WebElement element = Wait.clickable(driver,saveButton);
        element.click();
        
        return new Mst_AddCustShadePage(driver);
    }
    
    public Mst_AddCustShadePage pressCancel() {
        WebElement element = Wait.clickable(driver,cancelButton);
        element.click();
        
        return new Mst_AddCustShadePage(driver);
    }
     
    public void checkFields() {
        //Wait for all elements to be clickable
        WebElement salesOrg = Wait.clickable(driver,salesOrgField);
        WebElement custName = Wait.clickable(driver,custNameField);
        WebElement custShade = Wait.clickable(driver,custShadeField);
        WebElement coatsShade = Wait.clickable(driver,coatsShadeField);
        WebElement save = Wait.clickable(driver,saveButton);
        WebElement cancel = Wait.clickable(driver,cancelButton);
        
        //Assert all elements are displayed
        AssertJUnit.assertTrue("Add Customer Shade: Sales Organisation field not displayed",salesOrg.isDisplayed());
        AssertJUnit.assertTrue("Add Customer Shade: Customer Name field not displayed",custName.isDisplayed());
        AssertJUnit.assertTrue("Add Customer Shade: Customer Shade field not displayed",custShade.isDisplayed());
        AssertJUnit.assertTrue("Add Customer Shade: Coats shade field not displayed",coatsShade.isDisplayed());
        AssertJUnit.assertTrue("Add Customer Shade: Save button not displayed",save.isDisplayed());
        AssertJUnit.assertTrue("Add Customer Shade: Cancel button not displayed",cancel.isDisplayed());
    }
    
    public void waitForElement() {
        WebElement element = Wait.clickable(driver,coatsShadeField);
    }
    
}
