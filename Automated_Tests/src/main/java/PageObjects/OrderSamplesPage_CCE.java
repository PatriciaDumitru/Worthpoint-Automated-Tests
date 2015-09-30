
package PageObjects;

import AutomationFramework.CommonTask;
import AutomationFramework.TestSuite;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class OrderSamplesPage_CCE {
    
    WebDriver driver;
    
    //Locators
    static By custNameFieldLocator = By.id("s2id_customer_id");
    static By custCodeFieldLocator = By.id("s2id_customer_code");
    static By requestorNameFieldLocator = By.id("SampleOrderRequesterId");
    static By submitButtonLocator = By.cssSelector("#SampleOrderPromptForm > div.actions > ul > li:nth-child(1) > input[type=\"submit\"]");
    static By contentFrame = By.id("content");
    
    public OrderSamplesPage_CCE(WebDriver passedDriver) {
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
    
    public OrderSamplesPage_CCE setCustName(String custName) {
        CommonTask.setSearchField(driver,custNameFieldLocator,custName);
        return this;
    }
    
    public OrderSamplesPage_CCE setCustCode(String custCode) {
        CommonTask.setSearchField(driver, custCodeFieldLocator, custCode);
        return this;
    }
    
    public OrderSamplesPage_CCE setRequestor(String requestorName) throws InterruptedException {
        //Wait for entry in customer name
        Boolean waitForText = new WebDriverWait(driver,10).until(ExpectedConditions.textToBePresentInElementLocated(custCodeFieldLocator, TestSuite.custCode));
        
        CommonTask.setDropDownField(driver, requestorNameFieldLocator, requestorName);
        return this;
    }
    
    public AddOrderPage_CCE pressSubmit() {
        //wait for button to be clickable
        WebElement waitForButton = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(submitButtonLocator));
        //Click button
        driver.findElement(submitButtonLocator).click();
        return new AddOrderPage_CCE(driver);
    }
    
    public boolean checkFields() {
        
        try {
            //Wait for fields to be clickable
            WebElement waitForCustName = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(getCustNameField()));
            WebElement waitForCustCode = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(getCustCodeField()));
            WebElement waitForRequestor = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(getRequestorNameField()));        
            
            //Assert fields are displayed
            Assert.assertTrue("Order Samples page: Customer Name field not displayed", getCustNameField().isDisplayed());
            Assert.assertTrue("Order Samples page: Customer Code field not displayed", getCustCodeField().isDisplayed());
            Assert.assertTrue("Order Samples page: Requestor name field not displayed", getRequestorNameField().isDisplayed());
            
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public OrderSamplesPage_CCE waitForLoad() {
        WebElement waitForVisible = new WebDriverWait(driver,10).until(ExpectedConditions.visibilityOfElementLocated(contentFrame));
        return this;
    }

}
