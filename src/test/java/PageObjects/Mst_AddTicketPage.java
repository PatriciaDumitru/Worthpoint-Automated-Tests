
package PageObjects;

import AutomationFramework.CommonTask;
import AutomationFramework.DataItems;
import AutomationFramework.Wait;
import static PageObjects.WBA_BasePage.driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.AssertJUnit;

public class Mst_AddTicketPage extends WBA_BasePage {
    
    //Locators
    By ticketNameField = By.id("TicketTicketName");
    By saveButton = By.cssSelector("#TicketAddForm > div.actions > ul > li:nth-child(1) > input[type=\"submit\"]");
    By cancelButton = By.cssSelector("#TicketAddForm > div.actions > ul > li:nth-child(2) > a");
    
    public Mst_AddTicketPage(WebDriver driver) {
        super(driver);
    }
    
    public WebElement getBreadcrumb() {
        return Wait.clickable(driver,DataItems.breadcrumbLocator);
    }
    
    public Mst_AddTicketPage setTicketName(String item) {
        CommonTask.setInputField(driver,ticketNameField,item);
        return new Mst_AddTicketPage(driver);
    }
    
    public Mst_TicketsPage pressSave() {
        WebElement element = Wait.clickable(driver,saveButton);
        element.click();
        
        return new Mst_TicketsPage(driver);
    }
    
    public Mst_TicketsPage pressCancel() {
        WebElement element = Wait.clickable(driver,cancelButton);
        element.click();
        
        return new Mst_TicketsPage(driver);
    }
    
    public void checkFields() {
        WebElement ticketName = Wait.clickable(driver,ticketNameField);
        WebElement save = Wait.clickable(driver,saveButton);
        WebElement cancel = Wait.clickable(driver,cancelButton);
        
        AssertJUnit.assertTrue("Add Ticket Page: Ticket name field not displayed",ticketName.isDisplayed());
        AssertJUnit.assertTrue("Add Ticket Page: Save button not displayed",save.isDisplayed());
        AssertJUnit.assertTrue("Add Ticket Page: Save button not displayed",cancel.isDisplayed());
    }
    
    public void waitForElement() {
        WebElement ticketName = Wait.clickable(driver,ticketNameField);
    }
    
    
}
