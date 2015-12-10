/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

/**
 *
 * @author ukspjsykes
 */
public class Mst_AddCustLengthPage extends WBA_BasePage {
    
    //Locators
    By salesOrgField = By.id("CustomerLengthSalesOrgId");
    By custNameField = By.id("s2id_CustomerLengthCustomerId");
    By custLengthField = By.id("CustomerLengthCustomerLength");
    By coatsLengthField = By.id("CustomerLengthLengthId");
    By saveButton = By.cssSelector("#CustomerLengthAddForm > div.actions > ul > li:nth-child(1) > input[type=\"submit\"]");
    By cancelButton = By.cssSelector("#CustomerLengthAddForm > div.actions > ul > li:nth-child(2) > a");
    
    public Mst_AddCustLengthPage(WebDriver driver) {
        super(driver);
    }
    
    public WebElement getBreadcrumb() {
        return Wait.visible(driver,DataItems.breadcrumbLocator);
    }
    
    public Mst_AddCustLengthPage setSalesOrg(String item) throws InterruptedException {
        CommonTask.setDropDownField(driver, salesOrgField, item);
        return new Mst_AddCustLengthPage(driver);
    }
    
    public Mst_AddCustLengthPage setCustomerName(String item) throws InterruptedException {
        CommonTask.setSearchField(driver, custNameField, item);
        return new Mst_AddCustLengthPage(driver);
    }
    
    public Mst_AddCustLengthPage setCustomerLength(String item) throws InterruptedException {
        CommonTask.setInputField(driver, custLengthField, item);
        return new Mst_AddCustLengthPage(driver);
    }
    
    public Mst_AddCustLengthPage setCoatsLength(String item) throws InterruptedException {
        CommonTask.setDropDownField(driver, coatsLengthField, item);
        return new Mst_AddCustLengthPage(driver);
    }
    
    public Mst_CustLengthsPage pressSave() {
        WebElement element = Wait.clickable(driver,saveButton);
        element.click();
        
        return new Mst_CustLengthsPage(driver);
    }
    
    public Mst_CustLengthsPage pressCancel() {
        WebElement element = Wait.clickable(driver,cancelButton);
        element.click();
       
        return new Mst_CustLengthsPage(driver);
    }
    
    public void checkFields() {
        //Wait for all elements to be clickable
        WebElement salesOrg = Wait.clickable(driver,salesOrgField);
        WebElement custName = Wait.clickable(driver,custNameField);
        WebElement custLength = Wait.clickable(driver,custLengthField);
        WebElement coatsLength = Wait.clickable(driver,coatsLengthField);
        WebElement save = Wait.clickable(driver,saveButton);
        WebElement cancel = Wait.clickable(driver,cancelButton);
        
        //Assert all elements are displayed
        AssertJUnit.assertTrue("Add Customer Length Page: Sales Organisation Field not displayed",salesOrg.isDisplayed());
        AssertJUnit.assertTrue("Add Customer Length Page: Customer Name Field not displayed",custName.isDisplayed());
        AssertJUnit.assertTrue("Add Customer Length Page: Customer Length Field not displayed",custLength.isDisplayed());
        AssertJUnit.assertTrue("Add Customer Length Page: Coats Length Field not displayed",coatsLength.isDisplayed());
        AssertJUnit.assertTrue("Add Customer Length Page: Save button not displayed",save.isDisplayed());
        AssertJUnit.assertTrue("Add Customer Length Page: Cancel button not displayed",cancel.isDisplayed());
    }
    
    public void waitForElement() {
        WebElement coatsLength = Wait.clickable(driver,coatsLengthField);        
    }
    
}
