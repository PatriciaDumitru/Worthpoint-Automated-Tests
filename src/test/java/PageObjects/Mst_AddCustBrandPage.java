
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

public class Mst_AddCustBrandPage extends WBA_BasePage {
    
    //Locators
    By salesOrgField = By.id("CustomerBrandSalesOrgId");
    By custNameField = By.id("s2id_CustomerBrandCustomerId");
    By custBrandField = By.id("CustomerBrandCustomerBrandName");
    By coatsBrandField = By.id("CustomerBrandBrandId");
    By saveButton = By.cssSelector("#CustomerBrandAddForm > div.actions > ul > li:nth-child(1) > input[type=\"submit\"]");
    By cancelButton = By.cssSelector("#CustomerBrandAddForm > div.actions > ul > li:nth-child(2) > a");

    public Mst_AddCustBrandPage(WebDriver driver) {
        super(driver);
    }
    
    public WebElement getBreadcrumb() {
        return Wait.visible(driver,DataItems.breadcrumbLocator);
    }
    
    public Mst_AddCustBrandPage setSalesOrg(String item) throws InterruptedException {
        CommonTask.setDropDownField(driver, salesOrgField, item);
        return new Mst_AddCustBrandPage(driver);
    }
    
    public Mst_AddCustBrandPage setCustomerName(String item) throws InterruptedException {
        CommonTask.setSearchField(driver, custNameField, item);
        return new Mst_AddCustBrandPage(driver);
    }
    
    public Mst_AddCustBrandPage setCustomerBrand(String item) throws InterruptedException {
        CommonTask.setInputField(driver, custBrandField, item);
        return new Mst_AddCustBrandPage(driver);
    }
    
    public Mst_AddCustBrandPage setCoatsBrand(String item) throws InterruptedException {
        CommonTask.setDropDownField(driver, coatsBrandField, item);
        return new Mst_AddCustBrandPage(driver);
    }
    
    public Mst_CustBrandsPage pressSave() {
        WebElement element = Wait.clickable(driver,saveButton);
        element.click();
        
        return new Mst_CustBrandsPage(driver);
    }
    
    public void checkFields() {
        //Wait for all elements to be clickable
        WebElement salesOrg = Wait.clickable(driver,salesOrgField);
        WebElement custName = Wait.clickable(driver,custNameField);
        WebElement custBrand = Wait.clickable(driver,custBrandField);
        WebElement coatsBrand = Wait.clickable(driver,coatsBrandField);
        WebElement save = Wait.clickable(driver,saveButton);
        WebElement cancel = Wait.clickable(driver,cancelButton);
        
        //Assert all elements are displayed
        AssertJUnit.assertTrue("Add Customer Brand Page: Sales Org Field not displayed",salesOrg.isDisplayed());
        AssertJUnit.assertTrue("Add Customer Brand Page: Customer Name Field not displayed",custName.isDisplayed());
        AssertJUnit.assertTrue("Add Customer Brand Page: Customer Brand Field not displayed",custBrand.isDisplayed());
        AssertJUnit.assertTrue("Add Customer Brand Page: Coats Brand Field not displayed",coatsBrand.isDisplayed());
        AssertJUnit.assertTrue("Add Customer Brand Page: Save button not displayed",save.isDisplayed());
        AssertJUnit.assertTrue("Add Customer Brand Page: Cancel button not displayed",cancel.isDisplayed());
    }
    
    public void waitForElement(){
        WebElement coatsBrand = Wait.clickable(driver,coatsBrandField);
    }
    
}

