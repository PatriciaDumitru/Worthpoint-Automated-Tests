
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
        return Wait.visible(driver,DataItems.breadcrumbLocator);
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
        WebElement element = Wait.clickable(driver,qtyField);
        element.clear();
        
        CommonTask.setInputField(driver,qtyField,item);
        return new Mst_AddChargedProductPage(driver);
    }
    
    public Mst_ChargedProductsPage pressSave() {
        WebElement element = Wait.clickable(driver,saveButton);
        element.click();
        
        return new Mst_ChargedProductsPage(driver);
    }
    
    public void checkFields() {
        WebElement salesOrg = Wait.clickable(driver,salesOrgField);
        WebElement custName = Wait.clickable(driver,custNameField);
        WebElement brand = Wait.clickable(driver,brandField);
        WebElement ticket = Wait.clickable(driver,ticketField);
        WebElement mumType = Wait.clickable(driver,mumTypeField);
        WebElement qty = Wait.clickable(driver,qtyField);
        WebElement save = Wait.clickable(driver,saveButton);
        WebElement cancel = Wait.clickable(driver,cancelButton);
        
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
        WebElement mumType = Wait.clickable(driver,mumTypeField);
    }


}
