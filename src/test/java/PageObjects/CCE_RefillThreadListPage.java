
package PageObjects;

import AutomationFramework.CommonTask;
import AutomationFramework.DataItems;
import org.openqa.selenium.Alert;
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
public class CCE_RefillThreadListPage extends WBA_BasePage {
    
    By addThreadButton = By.id("addthread");
    By saveButton = By.cssSelector("#SampleOrderRefillCabinetForm > div.actions > ul > li:nth-child(1) > input[type=\"submit\"]");
    By cancelButton = By.cssSelector("#SampleOrderRefillCabinetForm > div.actions > ul > li:nth-child(2) > a");
    By brandField = By.id("CabinetsThread0BrandId");
    By ticketField = By.id("CabinetsThread0TicketId");
    By shadeField = By.id("CabinetsThread0ShadeId");
    By mumTypeField = By.id("CabinetsThread0MumTypeId");
    By reqQuantityField = By.id("CabinetsThread0Quantity");
    
    public CCE_RefillThreadListPage(WebDriver driver) {
        super(driver);
    }
    
    public WebElement getAddThreadButton() {
        return driver.findElement(addThreadButton);
    }
    
    public WebElement getSaveButton() {
        return driver.findElement(saveButton);
    }
    
    public WebElement getCancelButton() {
        return driver.findElement(cancelButton);
    }
    
    public WebElement getBrandField() {
        return driver.findElement(brandField);
    }
    
    public WebElement getTicketField() {
        return driver.findElement(ticketField);
    }
    
    public WebElement getShadeField() {
        return driver.findElement(shadeField);
    }
    
    public WebElement getMUMTypeField() {
        return driver.findElement(mumTypeField);
    }
    
    public WebElement getReqQuantityField() {
        return driver.findElement(reqQuantityField);
    }
    
    public CCE_RefillThreadListPage setBrand(String item) throws InterruptedException {
        CommonTask.setDropDownField(driver, brandField, item);
        return new CCE_RefillThreadListPage(driver);
    }
    
    public CCE_RefillThreadListPage setTicket(String item) throws InterruptedException {
        CommonTask.setDropDownField(driver, ticketField, item);
        return new CCE_RefillThreadListPage(driver);
    }
    
    public CCE_RefillThreadListPage setShade(String item) throws InterruptedException {
        CommonTask.setDropDownField(driver, shadeField, item);
        return new CCE_RefillThreadListPage(driver);
    }
    
    public CCE_RefillThreadListPage setMUMType(String item) throws InterruptedException {
        CommonTask.setDropDownField(driver, mumTypeField, item);
        return new CCE_RefillThreadListPage(driver);
    }
    
    public CCE_RefillThreadListPage setROQ(String item) throws InterruptedException {
        CommonTask.setInputField(driver, reqQuantityField, item);
        return new CCE_RefillThreadListPage(driver);
    }
    
    public CCE_RefillThreadListPage pressAddThreadExpectingAlert() {
        WebElement wait = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(addThreadButton));
        driver.findElement(addThreadButton).click();
        
        Alert alert = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.alertIsPresent());
        System.out.println("Alert appeared: " + alert.getText());
        alert.accept();
        
        return new CCE_RefillThreadListPage(driver);
    }
    
    public CCE_RefillThreadListPage pressSave() {
        WebElement wait = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(saveButton));
        driver.findElement(saveButton).click();
        
        Alert alert = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.alertIsPresent());
        alert.accept();
        
        return new CCE_RefillThreadListPage(driver);
    }
    
    public CCE_RefillCabinetPage pressCancel() {
        WebElement wait = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(cancelButton));
        driver.findElement(cancelButton).click();
        return new CCE_RefillCabinetPage(driver);
    }
    
    public void checkFields() {
        //Wait for all elements to be clickable
        WebElement addBtn = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(addThreadButton));
        WebElement saveBtn = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(saveButton));
        WebElement cancelBtn = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(cancelButton));
        WebElement brandFld = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(brandField));
        WebElement ticketFld = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(ticketField));
        WebElement shadeFld = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(shadeField));
        WebElement mumFld = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(mumTypeField));
        WebElement reqQuantityFld = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(reqQuantityField));
        
        //Assert all elements are displayed
        AssertJUnit.assertTrue("Refill Thread List Page: Add Thread button not displayed as expected",getAddThreadButton().isDisplayed());
        AssertJUnit.assertTrue("Refill Thread List Page: Save button not displayed as expected",getSaveButton().isDisplayed());
        AssertJUnit.assertTrue("Refill Thread List Page: Cancel button not displayed as expected",getCancelButton().isDisplayed());
        AssertJUnit.assertTrue("Refill Thread List Page: Brand field not displayed as expected",getBrandField().isDisplayed());
        AssertJUnit.assertTrue("Refill Thread List Page: Ticket field not displayed as expected",getTicketField().isDisplayed());
        AssertJUnit.assertTrue("Refill Thread List Page: Shade field not displayed as expected",getShadeField().isDisplayed());
        AssertJUnit.assertTrue("Refill Thread List Page: MUM Type field not displayed as expected",getMUMTypeField().isDisplayed());
        AssertJUnit.assertTrue("Refill Thread List Page: Required Order Quantity field not displayed as expected",getReqQuantityField().isDisplayed());
    }
    
    public void waitForElement() {
        WebElement wait = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(addThreadButton));
    }
    
    
}
