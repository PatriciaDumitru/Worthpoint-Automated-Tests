
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

public class Mst_EditCustTicketPage extends WBA_BasePage {
    
    //Locators
    By salesOrgField = By.id("CustomerTicketSalesOrgId");
    By custNameField = By.id("s2id_CustomerTicketCustomerId");
    By custTicketField = By.id("CustomerTicketCustomerTicket");
    By coatsTicketField = By.id("CustomerTicketTicketId");
    By saveButton = By.cssSelector("#CustomerTicketEditForm > div.actions > ul > li:nth-child(1) > input[type=\"submit\"]");
    By cancelButton = By.cssSelector("#CustomerTicketEditForm > div.actions > ul > li:nth-child(2) > a");
    
    public Mst_EditCustTicketPage(WebDriver driver) {
        super(driver);
    }
    
    public WebElement getBreadcrumb() {
        return new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.visibilityOfElementLocated(DataItems.breadcrumbLocator));
    }
    
    public Mst_EditCustTicketPage setSalesOrg(String item) throws InterruptedException {
        CommonTask.setDropDownField(driver,salesOrgField,item);
        return new Mst_EditCustTicketPage(driver);
    }
    
    public Mst_EditCustTicketPage setCustomerName(String item) throws InterruptedException {
        CommonTask.setSearchField(driver,custNameField,item);
        return new Mst_EditCustTicketPage(driver);
    }
    
    public Mst_EditCustTicketPage setCustomerTicket(String item) throws InterruptedException {
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(custTicketField));
        element.clear();
        
        CommonTask.setInputField(driver,custTicketField,item);
        return new Mst_EditCustTicketPage(driver);
    }
    
    public Mst_EditCustTicketPage setCoatsTicket(String item) throws InterruptedException {
        CommonTask.setDropDownField(driver,coatsTicketField,item);
        return new Mst_EditCustTicketPage(driver);
    }
    
    public Mst_CustTicketsPage pressSave() {
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(saveButton));
        
        element.click();
        
        return new Mst_CustTicketsPage(driver);
    }
    
    public Mst_CustTicketsPage pressCancel() {
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(cancelButton));
        
        element.click();
        
        return new Mst_CustTicketsPage(driver);
    }
    
    public void checkFields() {
        //Wait for all elements to be clickable
        WebElement salesOrg = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(salesOrgField));
        WebElement custName = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(custNameField));
        WebElement custTicket = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(custTicketField));
        WebElement coatsTicket = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(coatsTicketField));
        WebElement saveBtn = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(saveButton));
        WebElement cancelBtn = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(cancelButton));
        
        //Assert all elements are displayed
        AssertJUnit.assertTrue("Edit Customer Tickets Page: Sales Organisation Field not displayed",salesOrg.isDisplayed());
        AssertJUnit.assertTrue("Edit Customer Tickets Page: Customer Name Field not displayed",custName.isDisplayed());
        AssertJUnit.assertTrue("Edit Customer Tickets Page: Customer Ticket Field not displayed",custTicket.isDisplayed());
        AssertJUnit.assertTrue("Edit Customer Tickets Page: Coats Ticket Field not displayed",coatsTicket.isDisplayed());
        AssertJUnit.assertTrue("Edit Customer Tickets Page: Save Button not displayed",saveBtn.isDisplayed());
        AssertJUnit.assertTrue("Edit Customer Tickets Page: Cancel Button not displayed",cancelBtn.isDisplayed());
    }
    
    public void waitForElement() {
        WebElement coatsTicket = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(coatsTicketField));
    }
    
}
