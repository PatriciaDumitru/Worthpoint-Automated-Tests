
package com.coats.selenium.tests;

import AutomationFramework.CommonTask;
import AutomationFramework.DataItems;
import PageObjects.Mst_AddCustLengthPage;
import PageObjects.Mst_CustLengthsPage;
import PageObjects.WBA_BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.AssertJUnit;

public class Mst_EditCustLengthPage extends WBA_BasePage {
    
    //Locators
    By salesOrgField = By.id("CustomerLengthSalesOrgId");
    By custNameField = By.id("s2id_CustomerLengthCustomerId");
    By custLengthField = By.id("CustomerLengthCustomerLength");
    By coatsLengthField = By.id("CustomerLengthLengthId");
    By saveButton = By.cssSelector("#CustomerLengthEditForm > div.actions > ul > li:nth-child(1) > input[type=\"submit\"]");
    By cancelButton = By.cssSelector("#CustomerLengthEditForm > div.actions > ul > li:nth-child(2) > a");
    
    public Mst_EditCustLengthPage(WebDriver driver) {
        super(driver);
    }
    
    public WebElement getBreadcrumb() {
        return new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.visibilityOfElementLocated(DataItems.breadcrumbLocator));
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
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(custLengthField));
        element.clear();
        CommonTask.setInputField(driver, custLengthField, item);
        return new Mst_AddCustLengthPage(driver);
    }
    
    public Mst_AddCustLengthPage setCoatsLength(String item) throws InterruptedException {
        CommonTask.setDropDownField(driver, coatsLengthField, item);
        return new Mst_AddCustLengthPage(driver);
    }
    
    public Mst_CustLengthsPage pressSave() {
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(saveButton));
        element.click();
        
        return new Mst_CustLengthsPage(driver);
    }
    
    public Mst_CustLengthsPage pressCancel() {
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(cancelButton));
        element.click();
        
        return new Mst_CustLengthsPage(driver);
    }
    
    public void checkFields() {
        //Wait for all elements to be clickable
        WebElement salesOrg = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(salesOrgField));
        WebElement custName = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(custNameField));
        WebElement custLength = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(custLengthField));
        WebElement coatsLength = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(coatsLengthField));
        WebElement save = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(saveButton));
        WebElement cancel = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(cancelButton));
        
        //Assert all elements are displayed
        AssertJUnit.assertTrue("Add Customer Length Page: Sales Organisation Field not displayed",salesOrg.isDisplayed());
        AssertJUnit.assertTrue("Add Customer Length Page: Customer Name Field not displayed",custName.isDisplayed());
        AssertJUnit.assertTrue("Add Customer Length Page: Customer Length Field not displayed",custLength.isDisplayed());
        AssertJUnit.assertTrue("Add Customer Length Page: Coats Length Field not displayed",coatsLength.isDisplayed());
        AssertJUnit.assertTrue("Add Customer Length Page: Save button not displayed",save.isDisplayed());
        AssertJUnit.assertTrue("Add Customer Length Page: Cancel button not displayed",cancel.isDisplayed());
    }
    
    public void waitForElement() {
        WebElement coatsLength = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(coatsLengthField));        
    }
    
}
