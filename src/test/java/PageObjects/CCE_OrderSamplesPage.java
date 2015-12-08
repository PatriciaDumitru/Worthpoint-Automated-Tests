
package PageObjects;

import AutomationFramework.CommonTask;
import AutomationFramework.DataItems;
import AutomationFramework.Wait;
import org.testng.AssertJUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class CCE_OrderSamplesPage extends WBA_BasePage {
    
    //Locators
    static By custNameField = By.id("s2id_customer_id");
    static By custCodeField = By.id("s2id_customer_code");
    static By requestorNameField = By.id("SampleOrderRequesterId");
    static By submitButton = By.cssSelector("#SampleOrderPromptForm > div.actions > ul > li:nth-child(1) > input[type=\"submit\"]");
    static By contentFrame = By.id("content");
    
    public CCE_OrderSamplesPage(WebDriver passedDriver) {
        super(passedDriver);
    }
    
    public CCE_OrderSamplesPage setCustName(String custName) {
        CommonTask.setSearchField(driver,custNameField,custName);
        return this;
    }
    
    public CCE_OrderSamplesPage setCustCode(String custCode) {
        CommonTask.setSearchField(driver, custCodeField, custCode);
        return this;
    }
    
    public CCE_OrderSamplesPage setRequestor(String requestorName) throws InterruptedException {
        //Wait for entry in customer name
        Boolean waitForText = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.textToBePresentInElementLocated(custCodeField, DataItems.custCode));
        
        CommonTask.setDropDownField(driver, requestorNameField, requestorName);
        return this;
    }
    
    public CCE_AddOrderPage pressSubmit() {
        //wait for button to be clickable
        WebElement button = Wait.clickable(driver,submitButton);
        //Click button
        button.click();
        return new CCE_AddOrderPage(driver);
    }
    
    public boolean checkFields() {
        
        try {
            //Wait for fields to be clickable
            WebElement custName = Wait.clickable(driver,custNameField);
            WebElement custCode = Wait.clickable(driver,custCodeField);
            WebElement requestor = Wait.clickable(driver,requestorNameField);        
            
            //Assert fields are displayed
            AssertJUnit.assertTrue("Order Samples page: Customer Name field not displayed", custName.isDisplayed());
            AssertJUnit.assertTrue("Order Samples page: Customer Code field not displayed", custCode.isDisplayed());
            AssertJUnit.assertTrue("Order Samples page: Requestor name field not displayed", requestor.isDisplayed());
            
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public CCE_OrderSamplesPage waitForLoad2() {
        WebElement wait = Wait.visible(driver,contentFrame);
        return this;
    }

    public void waitForElement() {
        WebElement wait = Wait.clickable(driver,custNameField);
    }
}
