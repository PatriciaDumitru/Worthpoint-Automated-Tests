
package PageObjects;

import AutomationFramework.CommonTask;
import AutomationFramework.DataItems;
import AutomationFramework.Wait;
import static PageObjects.WBA_BasePage.driver;
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
        WebElement title = Wait.visible(driver,titleLocator);
        return title;
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
        WebElement custName = Wait.clickable(driver,custNameField);
        WebElement buyerName = Wait.clickable(driver,buyerNameField);
        WebElement buyerDesc = Wait.clickable(driver,buyerDescField);
        WebElement submit = Wait.clickable(driver,submitButton);
        WebElement cancel = Wait.clickable(driver,cancelButton);
        
        //Assert all elements are displayed
        AssertJUnit.assertTrue("New Buyer Page: Customer name field not displayed correctly",custName.isDisplayed());
        AssertJUnit.assertTrue("New Buyer Page: Buyer name field not displayed correctly",buyerName.isDisplayed());
        AssertJUnit.assertTrue("New Buyer Page: Buyer description field not displayed correctly",buyerDesc.isDisplayed());
        AssertJUnit.assertTrue("New Buyer Page: Submit button not displayed correctly",submit.isDisplayed());
        AssertJUnit.assertTrue("New Buyer Page: Cancel button not displayed correctly",cancel.isDisplayed());
    }
    
    public void waitForElement() {
        WebElement wait = Wait.clickable(driver,buyerNameField);
    }
    
    public CCE_NewBuyerPage setCustomerName(String item) {
        //CANNOT BE PERFORMED USING CommonTask.setSearchField AS PRESSING ENTER SUBIMTS FORM EARLY
        
        By searchLocator = By.cssSelector("#select2-drop > div > input");
        By resultLocator = By.cssSelector("#select2-drop > ul > li");
        
        WebElement buyerName = Wait.clickable(driver,buyerNameField);
        
        Actions action = new Actions(driver);
        action.click(buyerName).build().perform();
        
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
        WebElement submit = Wait.clickable(driver,submitButton);
        submit.click();
        return new CCE_AddOrderPage(driver);
    }
    
    public CCE_AddOrderPage pressCancel() {
        WebElement cancel = Wait.clickable(driver,cancelButton);
        cancel.click();
        return new CCE_AddOrderPage(driver);
    }
    
}
