
package PageObjects;

import AutomationFramework.CommonTask;
import AutomationFramework.DataItems;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.AssertJUnit;

public class Mst_EditCustShadePage extends WBA_BasePage {
    
    //Locators
    By salesOrgField = By.id("CustomerShadeSalesOrgId");
    By custNameField = By.id("s2id_CustomerShadeCustomerId");
    By custShadeNameField = By.id("CustomerShadeCustomerShadeName");
    By coatsShadeNameField = By.id("s2id_CustomerShadeShadeId");
    By saveButton = By.cssSelector("#CustomerShadeEditForm > div.actions > ul > li:nth-child(1) > input[type=\"submit\"]");
    By cancelButton = By.cssSelector("#CustomerShadeEditForm > div.actions > ul > li:nth-child(2) > a");
    
    public Mst_EditCustShadePage(WebDriver driver) {
        super(driver);
    }
    
    public Mst_EditCustShadePage setSalesOrg(String item) throws InterruptedException {
        CommonTask.setDropDownField(driver, salesOrgField, item);
        return new Mst_EditCustShadePage(driver);
    }
    
    public Mst_EditCustShadePage setCustomerShadeName(String item) {
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(custShadeNameField));
        element.clear();
        
        CommonTask.setInputField(driver, custShadeNameField, item);
        return new Mst_EditCustShadePage(driver);
    }
    
    public Mst_CustomerShadesPage pressSave() {
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(saveButton));
        element.click();
        
        return new Mst_CustomerShadesPage(driver);
    }
    
    public void checkFields() {
        //Wait for all elements to be clickable
        WebElement salesOrg = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(salesOrgField));
        WebElement custName = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(custNameField));
        WebElement custShadeName = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(custShadeNameField));
        WebElement coatsShadeName = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(coatsShadeNameField));
        WebElement saveBtn = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(saveButton));
        WebElement cancelBtn = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(cancelButton));
        
        //Assert all elements are displayed
        AssertJUnit.assertTrue("Edit Customer Shade Page: Sales Organisation Field not displayed",salesOrg.isDisplayed());
        AssertJUnit.assertTrue("Edit Customer Shade Page: Customer Name Field not displayed",custName.isDisplayed());
        AssertJUnit.assertTrue("Edit Customer Shade Page: Customer Shade Name Field not displayed",custShadeName.isDisplayed());
        AssertJUnit.assertTrue("Edit Customer Shade Page: Coats Shade Name Field not displayed",coatsShadeName.isDisplayed());
        AssertJUnit.assertTrue("Edit Customer Shade Page: Save button not displayed",saveBtn.isDisplayed());
        AssertJUnit.assertTrue("Edit Customer Shade Page: Cancel button not displayed",saveBtn.isDisplayed());
    }
    
    public void waitForElement() {
        WebElement custShadeName = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(custShadeNameField));
    }
    
}
