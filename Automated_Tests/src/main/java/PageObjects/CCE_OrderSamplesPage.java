
package PageObjects;

import AutomationFramework.CommonTask;
import AutomationFramework.DataItems;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class CCE_OrderSamplesPage {
    
    WebDriver driver;
    
    //Locators
    static By custNameFieldLocator = By.id("s2id_customer_id");
    static By custCodeFieldLocator = By.id("s2id_customer_code");
    static By requestorNameFieldLocator = By.id("SampleOrderRequesterId");
    static By submitButtonLocator = By.cssSelector("#SampleOrderPromptForm > div.actions > ul > li:nth-child(1) > input[type=\"submit\"]");
    static By contentFrame = By.id("content");
    
    public CCE_OrderSamplesPage(WebDriver passedDriver) {
        driver = passedDriver;
    }
    
    public WebElement getCustNameField() {
        return driver.findElement(custNameFieldLocator);
    }
    
    public WebElement getCustCodeField() {
        return driver.findElement(custCodeFieldLocator);
    }
    
    public WebElement getRequestorNameField() {
        return driver.findElement(requestorNameFieldLocator);
    }
    
    public CCE_OrderSamplesPage setCustName(String custName) {
        CommonTask.setSearchField(driver,custNameFieldLocator,custName);
        return this;
    }
    
    public CCE_OrderSamplesPage setCustCode(String custCode) {
        CommonTask.setSearchField(driver, custCodeFieldLocator, custCode);
        return this;
    }
    
    public CCE_OrderSamplesPage setRequestor(String requestorName) throws InterruptedException {
        //Wait for entry in customer name
        Boolean waitForText = new WebDriverWait(driver,5).until(ExpectedConditions.textToBePresentInElementLocated(custCodeFieldLocator, DataItems.custCode));
        
        CommonTask.setDropDownField(driver, requestorNameFieldLocator, requestorName);
        return this;
    }
    
    public CCE_AddOrderPage pressSubmit() {
        //wait for button to be clickable
        WebElement waitForButton = new WebDriverWait(driver,5).until(ExpectedConditions.elementToBeClickable(submitButtonLocator));
        //Click button
        driver.findElement(submitButtonLocator).click();
        return new CCE_AddOrderPage(driver);
    }
    
    public boolean checkFields() {
        
        try {
            //Wait for fields to be clickable
            WebElement waitForCustName = new WebDriverWait(driver,5).until(ExpectedConditions.elementToBeClickable(getCustNameField()));
            WebElement waitForCustCode = new WebDriverWait(driver,5).until(ExpectedConditions.elementToBeClickable(getCustCodeField()));
            WebElement waitForRequestor = new WebDriverWait(driver,5).until(ExpectedConditions.elementToBeClickable(getRequestorNameField()));        
            
            //Assert fields are displayed
            Assert.assertTrue("Order Samples page: Customer Name field not displayed", getCustNameField().isDisplayed());
            Assert.assertTrue("Order Samples page: Customer Code field not displayed", getCustCodeField().isDisplayed());
            Assert.assertTrue("Order Samples page: Requestor name field not displayed", getRequestorNameField().isDisplayed());
            
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public CCE_OrderSamplesPage waitForLoad() {
        WebElement waitForVisible = new WebDriverWait(driver,10).until(ExpectedConditions.visibilityOfElementLocated(contentFrame));
        return this;
    }

}
