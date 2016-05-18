
package PageObjects;

import AutomationFramework.CommonTask;
import AutomationFramework.DataItems;
import AutomationFramework.Wait;
import org.testng.AssertJUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
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
        return Wait.visible(driver,breadcrumb);
    }
    
    public CCE_RefillCabinetPage setShipTo(String item) {
        CommonTask.setSearchField(driver, shipToPartyField, item);
        return this;
    }
    
    public CCE_RefillCabinetPage setCabinetName(String item) {
        CommonTask.setSearchField(driver, cabinetNameField, item);
        return this;
    }
    
    public String getCabinetName() {
        Select select = new Select(driver.findElement(cabinetNameField));
        try {
            boolean wait = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.textToBePresentInElementLocated(cabinetNameField, DataItems.cabinetName));
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return select.getFirstSelectedOption().getText();
    }
    
    public CCE_RefillThreadListPage pressSubmit() {
        WebElement btn = Wait.clickable(driver,submitButton);
        btn.click();
        return new CCE_RefillThreadListPage(driver);
    }
    
    public CCE_RefillCabinetPage pressCancel() {
        WebElement cancel = Wait.clickable(driver,cancelButton);
        cancel.click();
        return this;
    }
    
    public void checkFields() {
        //Wait for all fields to be clickable
        WebElement shipTo = Wait.clickable(driver,shipToPartyField);
        WebElement cabinet = Wait.clickable(driver,cabinetNameField);
        WebElement submit = Wait.clickable(driver,submitButton);
        WebElement cancel = Wait.clickable(driver,cancelButton);
        
        //Assert all elements are displayed
        AssertJUnit.assertTrue("Refill Cabinet Page: Ship to field not displayed correctly",shipTo.isDisplayed());
        AssertJUnit.assertTrue("Refill Cabinet Page: Cabinet name field not displayed correctly",cabinet.isDisplayed());
        AssertJUnit.assertTrue("Refill Cabinet Page: Submit button not displayed correctly",submit.isDisplayed());
        AssertJUnit.assertTrue("Refill Cabinet Page: Cancel button not displayed correctly",cancel.isDisplayed());
        
    }
    
}
