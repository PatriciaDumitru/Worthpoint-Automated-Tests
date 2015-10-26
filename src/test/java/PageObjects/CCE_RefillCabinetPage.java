
package PageObjects;

import AutomationFramework.CommonTask;
import org.testng.AssertJUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class CCE_RefillCabinetPage extends WBA_BasePage {
    
    //Locators
    By breadcrumb = By.cssSelector("#content > h2");
    By shipToPartyField = By.id("s2id_SampleOrderShipToPartyId");
    By cabinetNameField = By.id("SampleOrderCabinetId");
    By submitButton = By.cssSelector("#SampleOrderRefillCabinetForm > div.actions > ul > li:nth-child(1)");
    By cancelButton = By.cssSelector("#SampleOrderRefillCabinetForm > div.actions > ul > li:nth-child(2)");
    
    public CCE_RefillCabinetPage(WebDriver passedDriver) {
        super(passedDriver);
    }
    
    public WebElement getBreadcrumb() {
        WebElement waitForBreadcrumb = new WebDriverWait(driver,8).until(ExpectedConditions.visibilityOfElementLocated(breadcrumb));
        return driver.findElement(breadcrumb);
    }
    
    public WebElement getShipToField() {
        return driver.findElement(shipToPartyField);
    }
    
    public WebElement getCabinetNameField() {
        return driver.findElement(cabinetNameField);
    }
    
    public WebElement getSubmitButton() {
        return driver.findElement(submitButton);
    }
    
    public WebElement getCancelButton() {
        return driver.findElement(cancelButton);
    }
    
    public CCE_RefillCabinetPage setShipTo(String item) {
        CommonTask.setSearchField(driver, shipToPartyField, item);
        return this;
    }
    
    public CCE_RefillCabinetPage setCabinetName(String item) {
        CommonTask.setSearchField(driver, cabinetNameField, item);
        return this;
    }
    
    public CCE_RefillCabinetPage pressCancel() {
        WebElement waitForButton = new WebDriverWait(driver,5).until(ExpectedConditions.elementToBeClickable(cancelButton));
        driver.findElement(cancelButton).click();
        return this;
    }
    
    public void checkFields() {
        //Wait for all fields to be clickable
        WebElement waitForShipTo = new WebDriverWait(driver,5).until(ExpectedConditions.elementToBeClickable(shipToPartyField));
        WebElement waitForCabinetName = new WebDriverWait(driver,5).until(ExpectedConditions.elementToBeClickable(cabinetNameField));
        WebElement waitForSubmit = new WebDriverWait(driver,5).until(ExpectedConditions.elementToBeClickable(submitButton));
        WebElement waitForCancel = new WebDriverWait(driver,5).until(ExpectedConditions.elementToBeClickable(cancelButton));
        
        //Assert all elements are displayed
        AssertJUnit.assertTrue("Refill Cabinet Page: Ship to field not displayed correctly",getShipToField().isDisplayed());
        AssertJUnit.assertTrue("Refill Cabinet Page: Cabinet name field not displayed correctly",getCabinetNameField().isDisplayed());
        AssertJUnit.assertTrue("Refill Cabinet Page: Submit button not displayed correctly",getSubmitButton().isDisplayed());
        AssertJUnit.assertTrue("Refill Cabinet Page: Cancel button not displayed correctly",getCancelButton().isDisplayed());
        
    }
    
}
