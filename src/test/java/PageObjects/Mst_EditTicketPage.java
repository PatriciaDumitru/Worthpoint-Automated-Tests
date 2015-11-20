
package PageObjects;

import AutomationFramework.CommonTask;
import AutomationFramework.DataItems;
import static PageObjects.WBA_BasePage.driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.AssertJUnit;



public class Mst_EditTicketPage extends WBA_BasePage {
    
    //Locators
    By ticketNameField = By.id("TicketTicketName");
    By saveButton = By.cssSelector("#TicketEditForm > div.actions > ul > li:nth-child(1) > input[type=\"submit\"]");
    By cancelButton = By.cssSelector("#TicketEditForm > div.actions > ul > li:nth-child(2) > a");
    
    public Mst_EditTicketPage(WebDriver driver) {
        super(driver);
    }
    
    public WebElement getBreadcrumb() {
        return new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.visibilityOfElementLocated(DataItems.breadcrumbLocator));
    }
    
    public Mst_AddTicketPage setTicketName(String item) {
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(ticketNameField));
        element.clear();
        
        CommonTask.setInputField(driver,ticketNameField,item);
        return new Mst_AddTicketPage(driver);
    }
    
    public Mst_TicketsPage pressSave() {
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(saveButton));
        element.click();
        
        return new Mst_TicketsPage(driver);
    }
    
    public Mst_TicketsPage pressCancel() {
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(cancelButton));
        element.click();
        
        return new Mst_TicketsPage(driver);
    }
    
    public void checkFields() {
        WebElement ticketName = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(ticketNameField));
        WebElement save = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(saveButton));
        WebElement cancel = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(cancelButton));
        
        AssertJUnit.assertTrue("Add Ticket Page: Ticket name field not displayed",ticketName.isDisplayed());
        AssertJUnit.assertTrue("Add Ticket Page: Save button not displayed",save.isDisplayed());
        AssertJUnit.assertTrue("Add Ticket Page: Save button not displayed",cancel.isDisplayed());
    }
    
    public void waitForElement() {
        WebElement ticketName = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(ticketNameField));
    }
    
}
