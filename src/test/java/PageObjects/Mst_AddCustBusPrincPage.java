
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

public class Mst_AddCustBusPrincPage extends WBA_BasePage {
    
    //Locators
    By salesOrgField = By.id("CustomerBusinessPrincipalSalesOrgId");
    By custNameField = By.id("s2id_CustomerBusinessPrincipalCustomerId");
    By principalNameField = By.id("CustomerBusinessPrincipalCustomerBusinessPrincipalName");
    By coatsPrincipalNameField = By.id("CustomerBusinessPrincipalBusinessPrincipalId");
    By saveButton = By.cssSelector("#CustomerBusinessPrincipalAddForm > div.actions > ul > li:nth-child(1) > input[type=\"submit\"]");
    By cancelButton = By.cssSelector("#CustomerBusinessPrincipalAddForm > div.actions > ul > li:nth-child(2) > a");
    
    public Mst_AddCustBusPrincPage(WebDriver driver) {
        super(driver);
    }
    
    public WebElement getBreadcrumb() {
        return Wait.clickable(driver,DataItems.breadcrumbLocator);
    }
    
    public Mst_AddCustBusPrincPage setSalesOrg(String item) throws InterruptedException {
        CommonTask.setDropDownField(driver, salesOrgField, item);
        return new Mst_AddCustBusPrincPage(driver);
    }
    
    public Mst_AddCustBusPrincPage setCustomerName(String item) throws InterruptedException {
        CommonTask.setSearchField(driver, custNameField, item);
        return new Mst_AddCustBusPrincPage(driver);
    }
    
    public Mst_AddCustBusPrincPage setCustomerBusPrinc(String item) throws InterruptedException {
        CommonTask.setInputField(driver, principalNameField, item);
        return new Mst_AddCustBusPrincPage(driver);
    }
    
    public Mst_AddCustBusPrincPage setCoatsBusPrinc(String item) throws InterruptedException {
        CommonTask.setDropDownField(driver, coatsPrincipalNameField, item);
        return new Mst_AddCustBusPrincPage(driver);
    }
    
    public Mst_CustBusinessPrincipalPage pressSave() {
        WebElement element = Wait.clickable(driver,saveButton);
        element.click();
        return new Mst_CustBusinessPrincipalPage(driver);
    }
    
    public Mst_CustBusinessPrincipalPage pressCancel() {
        WebElement element = Wait.clickable(driver,cancelButton);
        element.click();
        return new Mst_CustBusinessPrincipalPage(driver);
    }
    
    public void checkFields() {
        //Wait for all elements to be clickable
        WebElement salesOrg = Wait.clickable(driver,salesOrgField);
        WebElement custName = Wait.clickable(driver,custNameField);
        WebElement principalName = Wait.clickable(driver,principalNameField);
        WebElement coatsPrincipal = Wait.clickable(driver,coatsPrincipalNameField);
        WebElement saveBtn = Wait.clickable(driver,saveButton);
        WebElement cancel = Wait.clickable(driver,cancelButton);
        
        //Assert all elements are displayed
        AssertJUnit.assertTrue("Add Customer Business Principal Page: Sales Org Field not displayed",salesOrg.isDisplayed());
        AssertJUnit.assertTrue("Add Customer Business Principal Page: Customer Name Field not displayed",custName.isDisplayed());
        AssertJUnit.assertTrue("Add Customer Business Principal Page: Principal Name Field not displayed",principalName.isDisplayed());
        AssertJUnit.assertTrue("Add Customer Business Principal Page: Coats Principal Name Field not displayed",coatsPrincipal.isDisplayed());
        AssertJUnit.assertTrue("Add Customer Business Principal Page: Save button not displayed",saveBtn.isDisplayed());
        AssertJUnit.assertTrue("Add Customer Business Principal Page: Cancel button not displayed",cancel.isDisplayed());
    }
    
    public void waitForElement() {
        WebElement coatsPrincipal = Wait.clickable(driver,coatsPrincipalNameField);
    }
    
}
