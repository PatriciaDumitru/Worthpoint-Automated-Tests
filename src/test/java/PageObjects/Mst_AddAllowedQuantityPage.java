
package PageObjects;

import AutomationFramework.CommonTask;
import AutomationFramework.DataItems;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.AssertJUnit;

public class Mst_AddAllowedQuantityPage extends WBA_BasePage {
    
    By custNameField = By.id("s2id_AllowedQuantityCustomerId");
    By brandField = By.id("AllowedQuantityBrandId");
    By ticketField = By.id("AllowedQuantityTicketId");
    By mumTypeField = By.id("AllowedQuantityMumTypeId");
    By shadeField = By.id("s2id_AllowedQuantityShadeId");
    By qtyField = By.id("AllowedQuantityQuantity");
    By saveButton = By.cssSelector("#AllowedQuantityAddForm > div.actions > ul > li:nth-child(1) > input[type=\"submit\"]");
    By cancelButton = By.cssSelector("#AllowedQuantityAddForm > div.actions > ul > li:nth-child(2) > a");
    
    public Mst_AddAllowedQuantityPage(WebDriver driver) {
        super(driver);
    }
    
    public WebElement getBreadcrumb() {
        return new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.visibilityOfElementLocated(DataItems.breadcrumbLocator));
    }
    
    public Mst_AddAllowedQuantityPage setCustomerName(String item) {
        CommonTask.setSearchField(driver, custNameField, item);
        return new Mst_AddAllowedQuantityPage(driver);
    }
    
    public Mst_AddAllowedQuantityPage setBrand(String item) throws InterruptedException {
        CommonTask.setDropDownField(driver, brandField, item);
        return new Mst_AddAllowedQuantityPage(driver);
    }
    
    public Mst_AddAllowedQuantityPage setTicket(String item) throws InterruptedException {
        CommonTask.setDropDownField(driver, ticketField, item);
        return new Mst_AddAllowedQuantityPage(driver);
    }
    
    public Mst_AddAllowedQuantityPage setMUMType(String item) throws InterruptedException {
        CommonTask.setDropDownField(driver, mumTypeField, item);
        return new Mst_AddAllowedQuantityPage(driver);
    }
    
    public Mst_AddAllowedQuantityPage setShade(String item) {
        CommonTask.setSearchField(driver, shadeField, item);
        return new Mst_AddAllowedQuantityPage(driver);
    }
    
    public Mst_AddAllowedQuantityPage setQty(String item) {
        CommonTask.setInputField(driver, qtyField, item);
        return new Mst_AddAllowedQuantityPage(driver);
    }
    
    public Mst_AllowedQuantitiesPage pressSave() {
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(saveButton));
        element.click();
        
        return new Mst_AllowedQuantitiesPage(driver);
    }
    
    public void checkFields() {
        WebElement custName = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(custNameField));
        WebElement brand = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(brandField));
        WebElement ticket = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(ticketField));
        WebElement mumType = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(mumTypeField));
        WebElement shade = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(shadeField));
        WebElement qty = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(qtyField));
    
        AssertJUnit.assertTrue("Add Allowed Quantity Page: Customer name field not displayed",custName.isDisplayed());
        AssertJUnit.assertTrue("Add Allowed Quantity Page: Brand field not displayed",brand.isDisplayed());
        AssertJUnit.assertTrue("Add Allowed Quantity Page: Ticket field not displayed",ticket.isDisplayed());
        AssertJUnit.assertTrue("Add Allowed Quantity Page: MUM Type field not displayed",mumType.isDisplayed());
        AssertJUnit.assertTrue("Add Allowed Quantity Page: Shade field not displayed",shade.isDisplayed());
        AssertJUnit.assertTrue("Add Allowed Quantity Page: Quantity field not displayed",qty.isDisplayed());
    }
    
    public void waitForElement() {
        WebElement qty = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(qtyField));
    }
    
}
