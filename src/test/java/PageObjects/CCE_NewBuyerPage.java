
package PageObjects;

import AutomationFramework.CommonTask;
import AutomationFramework.DataItems;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.AssertJUnit;

public class CCE_NewBuyerPage extends WBA_BasePage {
    
    //Locators
    By titleLocator = By.cssSelector("#SampleOrderBuyerAddForm > div.frm > div.title");
    By custNameField = By.cssSelector("#s2id_customer_id > a");
    By buyerNameField = By.cssSelector("#SampleOrderBuyerAddForm > div.frm > div:nth-child(2) > table.order-details > tbody > tr:nth-child(3) > td > input");
    By buyerDescField = By.cssSelector("#SampleOrderBuyerAddForm > div.frm > div:nth-child(2) > table.order-details > tbody > tr:nth-child(4) > td > input");
    By submitButton = By.cssSelector("#SampleOrderBuyerAddForm > div.frm > div:nth-child(2) > div > ul > li:nth-child(1) > input[type=\"submit\"]");
    By cancelButton = By.cssSelector("#SampleOrderBuyerAddForm > div.frm > div:nth-child(2) > div > ul > li:nth-child(2)");
    
    public CCE_NewBuyerPage(WebDriver driver) {
        super(driver);
    }
    
    public WebElement getTitleField() {
        WebElement wait = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.visibilityOfElementLocated(titleLocator));
        return driver.findElement(titleLocator);
    }
    
    public WebElement getCustomerNameField() {
        WebElement wait = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(custNameField));
        return driver.findElement(custNameField);
    }
    
    public WebElement getBuyerNameField() {
        return driver.findElement(buyerNameField);
    }
    
    public WebElement getBuyerDescField() {
        return driver.findElement(buyerDescField);
    }
    
    public WebElement getSubmitButton() {
        return driver.findElement(submitButton);
    }
    
    public WebElement getCancelButton() {
        return driver.findElement(cancelButton);
    }
    
    public void checkFields() {
        //Wait for all elements to be clickable
        WebElement custName = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(custNameField));
        WebElement buyerName = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(buyerNameField));
        WebElement buyerDesc = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(buyerDescField));
        WebElement submit = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(submitButton));
        WebElement cancel = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(cancelButton));
        
        //Assert all elements are displayed
        AssertJUnit.assertTrue("New Buyer Page: Customer name field not displayed correctly",getCustomerNameField().isDisplayed());
        AssertJUnit.assertTrue("New Buyer Page: Buyer name field not displayed correctly",getBuyerNameField().isDisplayed());
        AssertJUnit.assertTrue("New Buyer Page: Buyer description field not displayed correctly",getBuyerDescField().isDisplayed());
        AssertJUnit.assertTrue("New Buyer Page: Submit button not displayed correctly",getSubmitButton().isDisplayed());
        AssertJUnit.assertTrue("New Buyer Page: Cancel button not displayed correctly",getCancelButton().isDisplayed());
    }
    
    public void waitForElement() {
        WebElement wait = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(buyerNameField));
    }
    
    public CCE_NewBuyerPage setCustomerName(String item) {
        //CANNOT BE PERFORMED USING CommonTask.setSearchField AS PRESSING ENTER SUBIMTS FORM EARLY
        
        By searchLocator = By.cssSelector("#select2-drop > div > input");
        By resultLocator = By.cssSelector("#select2-drop > ul > li");
        
        WebElement waitForClickable = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(buyerNameField));
        
        Actions action = new Actions(driver);
        action.click(driver.findElement(custNameField)).build().perform();
        
        WebElement waitForSearch = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.presenceOfElementLocated(searchLocator));
        action.sendKeys(driver.findElement(searchLocator),item).build().perform();
        //Wait for search result to load
        Boolean waitForResult = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.textToBePresentInElementLocated(resultLocator, item));
        
        action.click(driver.findElement(resultLocator)).build().perform();
        
        return new CCE_NewBuyerPage(driver);
    }
    
    public CCE_NewBuyerPage setBuyerName(String item) {
        CommonTask.setInputField(driver, buyerNameField, item);
        return new CCE_NewBuyerPage(driver);
    }
    
    public CCE_NewBuyerPage setBuyerDesc(String item) {
        CommonTask.setInputField(driver, buyerDescField, item);
        return new CCE_NewBuyerPage(driver);
    }
    
    public CCE_AddOrderPage pressSubmit() {
        WebElement wait = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(submitButton));
        driver.findElement(submitButton).click();
        return new CCE_AddOrderPage(driver);
    }
    
    public CCE_AddOrderPage pressCancel() {
        WebElement wait = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(cancelButton));
        driver.findElement(cancelButton).click();
        return new CCE_AddOrderPage(driver);
    }
    
}
