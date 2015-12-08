
package PageObjects;

import AutomationFramework.CommonTask;
import AutomationFramework.DataItems;
import AutomationFramework.Wait;
import static PageObjects.WBA_BasePage.driver;
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
        WebElement btn = Wait.clickable(driver,addThreadButton);
        btn.click();
        
        Alert alert = Wait.alert(driver);
        System.out.println("Alert appeared: " + alert.getText());
        alert.accept();
        
        return new CCE_RefillThreadListPage(driver);
    }
    
    public CCE_RefillThreadListPage pressSave() {
        WebElement save = Wait.clickable(driver,saveButton);
        save.click();
        
        Alert alert = Wait.alert(driver);
        alert.accept();
        
        return new CCE_RefillThreadListPage(driver);
    }
    
    public CCE_RefillCabinetPage pressCancel() {
        WebElement cancel = Wait.clickable(driver,cancelButton);
        cancel.click();
        return new CCE_RefillCabinetPage(driver);
    }
    
    public void checkFields() {
        //Wait for all elements to be clickable
        WebElement addBtn = Wait.clickable(driver,addThreadButton);
        WebElement saveBtn = Wait.clickable(driver,saveButton);
        WebElement cancelBtn =Wait.clickable(driver,cancelButton);
        WebElement brandFld = Wait.clickable(driver,brandField);
        WebElement ticketFld = Wait.clickable(driver,ticketField);
        WebElement shadeFld = Wait.clickable(driver,shadeField);
        WebElement mumFld = Wait.clickable(driver,mumTypeField);
        WebElement reqQuantityFld = Wait.clickable(driver,reqQuantityField);
        
        //Assert all elements are displayed
        AssertJUnit.assertTrue("Refill Thread List Page: Add Thread button not displayed as expected",addBtn.isDisplayed());
        AssertJUnit.assertTrue("Refill Thread List Page: Save button not displayed as expected",saveBtn.isDisplayed());
        AssertJUnit.assertTrue("Refill Thread List Page: Cancel button not displayed as expected",cancelBtn.isDisplayed());
        AssertJUnit.assertTrue("Refill Thread List Page: Brand field not displayed as expected",brandFld.isDisplayed());
        AssertJUnit.assertTrue("Refill Thread List Page: Ticket field not displayed as expected",ticketFld.isDisplayed());
        AssertJUnit.assertTrue("Refill Thread List Page: Shade field not displayed as expected",shadeFld.isDisplayed());
        AssertJUnit.assertTrue("Refill Thread List Page: MUM Type field not displayed as expected",mumFld.isDisplayed());
        AssertJUnit.assertTrue("Refill Thread List Page: Required Order Quantity field not displayed as expected",reqQuantityFld.isDisplayed());
    }
    
    public void waitForElement() {
        WebElement wait = Wait.clickable(driver,addThreadButton);
    }
    
    
}
