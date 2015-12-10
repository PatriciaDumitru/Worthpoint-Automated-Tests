
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

public class Mst_AddQuantityFactorPage extends WBA_BasePage {
    
    By salesOrgField = By.id("QuantityFactorSalesOrgId");
    By plantNameField = By.id("QuantityFactorPlantId");
    By mumTypeField = By.id("QuantityFactorMumTypeId");
    By brandField = By.id("QuantityFactorBrandId");
    By ticketField = By.id("QuantityFactorTicketId");
    By labQtyField = By.id("QuantityFactorQtyForSapOrder");
    By cceQtyField = By.id("QuantityFactorQtyForBulkOrder");
    By saveButton = By.cssSelector("#QuantityFactorAddForm > div.actions > ul > li:nth-child(1) > input[type=\"submit\"]");
    By cancelButton = By.cssSelector("#QuantityFactorAddForm > div.actions > ul > li:nth-child(2) > a");
    
    public Mst_AddQuantityFactorPage(WebDriver driver) {
        super(driver);
    }
    
    public WebElement getBreadcrumb() {
        return Wait.visible(driver,DataItems.breadcrumbLocator);
    }
    
    public Mst_AddQuantityFactorPage setSalesOrg(String item) throws InterruptedException {
        CommonTask.setDropDownField(driver,salesOrgField,item);
        return new Mst_AddQuantityFactorPage(driver);
    }
    
    public Mst_AddQuantityFactorPage setPlantName(String item) throws InterruptedException {
        CommonTask.setDropDownField(driver,plantNameField,item);
        return new Mst_AddQuantityFactorPage(driver);
    }
    
    public Mst_AddQuantityFactorPage setMUMType(String item) throws InterruptedException {
        CommonTask.setDropDownField(driver,mumTypeField,item);
        return new Mst_AddQuantityFactorPage(driver);
    }
    
    public Mst_AddQuantityFactorPage setBrand(String item) throws InterruptedException {
        CommonTask.setDropDownField(driver,brandField,item);
        return new Mst_AddQuantityFactorPage(driver);
    }
    
    public Mst_AddQuantityFactorPage setTicket(String item) throws InterruptedException {
        CommonTask.setDropDownField(driver,ticketField,item);
        return new Mst_AddQuantityFactorPage(driver);
    }
    
    public Mst_AddQuantityFactorPage setLabQty(String item) throws InterruptedException {
        WebElement element = Wait.clickable(driver,labQtyField);
        element.clear();
        
        CommonTask.setInputField(driver,labQtyField,item);
        return new Mst_AddQuantityFactorPage(driver);
    }
    
    public Mst_AddQuantityFactorPage setCCEQty(String item) throws InterruptedException {
        WebElement element = Wait.clickable(driver,cceQtyField);
        element.clear();
        
        CommonTask.setInputField(driver,cceQtyField,item);
        return new Mst_AddQuantityFactorPage(driver);
    }
    
    public Mst_QuantityFactorsPage pressSave() {
        WebElement element = Wait.clickable(driver,saveButton);
        element.click();
        
        return new Mst_QuantityFactorsPage(driver);
    }
    
    public void checkFields() {
        WebElement salesOrg = Wait.clickable(driver,salesOrgField);
        WebElement plantName = Wait.clickable(driver,plantNameField);
        WebElement mumType = Wait.clickable(driver,mumTypeField);
        WebElement brand = Wait.clickable(driver,brandField);
        WebElement ticket = Wait.clickable(driver,ticketField);
        WebElement labQty = Wait.clickable(driver,labQtyField);
        WebElement cceQty = Wait.clickable(driver,cceQtyField);
        WebElement save = Wait.clickable(driver,saveButton);
        WebElement cancel = Wait.clickable(driver,cancelButton);
    
        AssertJUnit.assertTrue("Add Quantity Factor Page: Sales Org field not displayed",salesOrg.isDisplayed());
        AssertJUnit.assertTrue("Add Quantity Factor Page: Plant Name field not displayed",plantName.isDisplayed());
        AssertJUnit.assertTrue("Add Quantity Factor Page: MUM Type field not displayed",mumType.isDisplayed());
        AssertJUnit.assertTrue("Add Quantity Factor Page: Brand field not displayed",brand.isDisplayed());
        AssertJUnit.assertTrue("Add Quantity Factor Page: Ticket field not displayed",ticket.isDisplayed());
        AssertJUnit.assertTrue("Add Quantity Factor Page: Lab QTYfield not displayed",labQty.isDisplayed());
        AssertJUnit.assertTrue("Add Quantity Factor Page: CCE QTY field not displayed",cceQty.isDisplayed());
        AssertJUnit.assertTrue("Add Quantity Factor Page: Save button not displayed",save.isDisplayed());
        AssertJUnit.assertTrue("Add Quantity Factor Page: Cancel button not displayed",cancel.isDisplayed());
    }
    
    public void waitForElement() {
        WebElement plantName = Wait.clickable(driver,plantNameField);
    }
    
}
