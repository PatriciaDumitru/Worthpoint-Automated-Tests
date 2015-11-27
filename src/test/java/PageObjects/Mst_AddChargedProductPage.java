
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

public class Mst_AddChargedProductPage extends WBA_BasePage {
    
    By salesOrgField = By.id("ChargedProductSalesOrgId");
    By custNameField = By.id("s2id_ChargedProductCustomerId");
    By brandField = By.id("ChargedProductBrandId");
    By ticketField = By.id("ChargedProductTicketId");
    By mumTypeField = By.id("ChargedProductMumTypeId");
    By qtyField = By.id("ChargedProductMinChargedQty");
    By saveButton = By.id("submit");
    By cancelButton = By.cssSelector("#ChargedProductAddForm > div.actions > ul > li:nth-child(2) > a");
    
    public Mst_AddChargedProductPage(WebDriver driver) {
        super(driver);
    }
    
    public WebElement getBreadcrumb() {
        return new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.visibilityOfElementLocated(DataItems.breadcrumbLocator));
    }
    
    public Mst_AddChargedProductPage setSalesOrg(String item) throws InterruptedException {
        CommonTask.setDropDownField(driver,salesOrgField,item);
        return new Mst_AddChargedProductPage(driver);
    }
    
    public Mst_AddChargedProductPage setCustomerName(String item) throws InterruptedException {
        CommonTask.setSearchField(driver,custNameField,item);
        return new Mst_AddChargedProductPage(driver);
    }
     
    public Mst_AddChargedProductPage setBrand(String item) throws InterruptedException {
        CommonTask.setDropDownField(driver,brandField,item);
        return new Mst_AddChargedProductPage(driver);
    }
    
    public Mst_AddChargedProductPage setTicket(String item) throws InterruptedException {
        CommonTask.setDropDownField(driver,ticketField,item);
        return new Mst_AddChargedProductPage(driver);
    }
    
    public Mst_AddChargedProductPage setMUMType(String item) throws InterruptedException {
        CommonTask.setDropDownField(driver,mumTypeField,item);
        return new Mst_AddChargedProductPage(driver);
    }
    
    public Mst_AddChargedProductPage setQuantity(String item) throws InterruptedException {
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(qtyField));
        element.clear();
        
        CommonTask.setInputField(driver,qtyField,item);
        return new Mst_AddChargedProductPage(driver);
    }
    
    public Mst_ChargedProductsPage pressSave() {
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(saveButton));
        element.click();
        
        return new Mst_ChargedProductsPage(driver);
    }
    
    public void checkFields() {
        WebElement salesOrg = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(salesOrgField));
        WebElement custName = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(custNameField));
        WebElement brand = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(brandField));
        WebElement ticket = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(ticketField));
        WebElement mumType = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(mumTypeField));
        WebElement qty = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(qtyField));
        WebElement save = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(saveButton));
        WebElement cancel = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(cancelButton));
        
        AssertJUnit.assertTrue("Add Charged Product Page: Sales Organisation field not displayed",salesOrg.isDisplayed());
        AssertJUnit.assertTrue("Add Charged Product Page: Customer Name field not displayed",custName.isDisplayed());
        AssertJUnit.assertTrue("Add Charged Product Page: Brand field not displayed",brand.isDisplayed());
        AssertJUnit.assertTrue("Add Charged Product Page: Ticket field not displayed",ticket.isDisplayed());
        AssertJUnit.assertTrue("Add Charged Product Page: MUM Type field not displayed",ticket.isDisplayed());
        AssertJUnit.assertTrue("Add Charged Product Page: Quantity field not displayed",qty.isDisplayed());
        AssertJUnit.assertTrue("Add Charged Product Page: Save button not displayed",save.isDisplayed());
        AssertJUnit.assertTrue("Add Charged Product Page: Cancel button not displayed",save.isDisplayed());
    }
    
    public void waitForElement() {
        WebElement mumType = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(mumTypeField));
    }
    
}
