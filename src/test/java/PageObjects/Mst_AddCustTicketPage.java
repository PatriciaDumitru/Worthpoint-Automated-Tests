
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

public class Mst_AddCustTicketPage extends WBA_BasePage {
    
    //Locators
    By salesOrgField = By.id("CustomerTicketSalesOrgId");
    By custNameField = By.id("s2id_CustomerTicketCustomerId");
    By custTicketField = By.id("CustomerTicketCustomerTicket");
    By coatsTicketField = By.id("CustomerTicketTicketId");
    By saveButton = By.cssSelector("#CustomerTicketAddForm > div.actions > ul > li:nth-child(1) > input[type=\"submit\"]");
    By cancelButton = By.cssSelector("#CustomerTicketAddForm > div.actions > ul > li:nth-child(2) > a");
    
    public Mst_AddCustTicketPage(WebDriver driver) {
        super(driver);
    }
    
    public WebElement getBreadcrumb() {
        return Wait.visible(driver,DataItems.breadcrumbLocator);
    }
    
    public Mst_AddCustTicketPage setSalesOrg(String item) throws InterruptedException {
        CommonTask.setDropDownField(driver,salesOrgField,item);
        return new Mst_AddCustTicketPage(driver);
    }
    
    public Mst_AddCustTicketPage setCustomerName(String item) throws InterruptedException {
        CommonTask.setSearchField(driver,custNameField,item);
        return new Mst_AddCustTicketPage(driver);
    }
    
    public Mst_AddCustTicketPage setCustomerTicket(String item) throws InterruptedException {
        CommonTask.setInputField(driver,custTicketField,item);
        return new Mst_AddCustTicketPage(driver);
    }
    
    public Mst_AddCustTicketPage setCoatsTicket(String item) throws InterruptedException {
        CommonTask.setDropDownField(driver,coatsTicketField,item);
        return new Mst_AddCustTicketPage(driver);
    }
    
    public Mst_CustTicketsPage pressSave() {
        WebElement element = Wait.clickable(driver,saveButton);
        
        element.click();
        
        return new Mst_CustTicketsPage(driver);
    }
    
    public Mst_CustTicketsPage pressCancel() {
        WebElement element = Wait.clickable(driver,cancelButton);
        
        element.click();
        
        return new Mst_CustTicketsPage(driver);
    }
    
    public void checkFields() {
        //Wait for all elements to be clickable
        WebElement salesOrg = Wait.clickable(driver,salesOrgField);
        WebElement custName = Wait.clickable(driver,custNameField);
        WebElement custTicket = Wait.clickable(driver,custTicketField);
        WebElement coatsTicket = Wait.clickable(driver,coatsTicketField);
        WebElement saveBtn = Wait.clickable(driver,saveButton);
        WebElement cancelBtn = Wait.clickable(driver,cancelButton);
        
        //Assert all elements are displayed
        AssertJUnit.assertTrue("Add Customer Tickets Page: Sales Organisation Field not displayed",salesOrg.isDisplayed());
        AssertJUnit.assertTrue("Add Customer Tickets Page: Customer Name Field not displayed",custName.isDisplayed());
        AssertJUnit.assertTrue("Add Customer Tickets Page: Customer Ticket Field not displayed",custTicket.isDisplayed());
        AssertJUnit.assertTrue("Add Customer Tickets Page: Coats Ticket Field not displayed",coatsTicket.isDisplayed());
        AssertJUnit.assertTrue("Add Customer Tickets Page: Save Button not displayed",saveBtn.isDisplayed());
        AssertJUnit.assertTrue("Add Customer Tickets Page: Cancel Button not displayed",cancelBtn.isDisplayed());
    }
    
    public void waitForElement() {
        WebElement coatsTicket = Wait.clickable(driver,coatsTicketField);
    }
    
}
