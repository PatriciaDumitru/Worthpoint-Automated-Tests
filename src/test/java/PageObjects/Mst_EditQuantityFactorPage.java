
package PageObjects;

import AutomationFramework.CommonTask;
import AutomationFramework.DataItems;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.AssertJUnit;

public class Mst_EditQuantityFactorPage extends WBA_BasePage {
    
    By salesOrgField = By.id("QuantityFactorSalesOrgId");
    By plantNameField = By.id("QuantityFactorPlantId");
    By mumTypeField = By.id("QuantityFactorMumTypeId");
    By brandField = By.id("QuantityFactorBrandId");
    By ticketField = By.id("QuantityFactorTicketId");
    By labQtyField = By.id("QuantityFactorQtyForSapOrder");
    By cceQtyField = By.id("QuantityFactorQtyForBulkOrder");
    By saveButton = By.cssSelector("#QuantityFactorEditForm > div.actions > ul > li:nth-child(1) > input[type=\"submit\"]");
    By cancelButton = By.cssSelector("#QuantityFactorEditForm > div.actions > ul > li:nth-child(2) > a");
    
    public Mst_EditQuantityFactorPage(WebDriver driver) {
        super(driver);
    }
    
    public WebElement getBreadcrumb() {
        return new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.visibilityOfElementLocated(DataItems.breadcrumbLocator));
    }
    
    public Mst_EditQuantityFactorPage setSalesOrg(String item) throws InterruptedException {
        CommonTask.setDropDownField(driver,salesOrgField,item);
        return new Mst_EditQuantityFactorPage(driver);
    }
    
    public Mst_EditQuantityFactorPage setPlantName(String item) throws InterruptedException {
        CommonTask.setDropDownField(driver,plantNameField,item);
        return new Mst_EditQuantityFactorPage(driver);
    }
    
    public Mst_EditQuantityFactorPage setMUMType(String item) throws InterruptedException {
        CommonTask.setDropDownField(driver,mumTypeField,item);
        return new Mst_EditQuantityFactorPage(driver);
    }
    
    public Mst_EditQuantityFactorPage setBrand(String item) throws InterruptedException {
        CommonTask.setDropDownField(driver,brandField,item);
        return new Mst_EditQuantityFactorPage(driver);
    }
    
    public Mst_EditQuantityFactorPage setTicket(String item) throws InterruptedException {
        CommonTask.setDropDownField(driver,ticketField,item);
        return new Mst_EditQuantityFactorPage(driver);
    }
    
    public Mst_EditQuantityFactorPage setLabQty(String item) throws InterruptedException {
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(labQtyField));
        element.clear();
        
        CommonTask.setInputField(driver,labQtyField,item);
        return new Mst_EditQuantityFactorPage(driver);
    }
    
    public Mst_EditQuantityFactorPage setCCEQty(String item) throws InterruptedException {
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(cceQtyField));
        element.clear();
        
        CommonTask.setInputField(driver,cceQtyField,item);
        return new Mst_EditQuantityFactorPage(driver);
    }
    
    public Mst_QuantityFactorsPage pressSave() {
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(saveButton));
        element.click();
        
        return new Mst_QuantityFactorsPage(driver);
    }
    
    public void checkFields() {
        WebElement salesOrg = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(salesOrgField));
        WebElement plantName = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(plantNameField));
        WebElement mumType = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(mumTypeField));
        WebElement brand = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(brandField));
        WebElement ticket = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(ticketField));
        WebElement labQty = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(labQtyField));
        WebElement cceQty = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(cceQtyField));
        WebElement save = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(saveButton));
        WebElement cancel = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(cancelButton));
    
        AssertJUnit.assertTrue("Edit Quantity Factor Page: Sales Org field not displayed",salesOrg.isDisplayed());
        AssertJUnit.assertTrue("Edit Quantity Factor Page: Plant Name field not displayed",plantName.isDisplayed());
        AssertJUnit.assertTrue("Edit Quantity Factor Page: MUM Type field not displayed",mumType.isDisplayed());
        AssertJUnit.assertTrue("Edit Quantity Factor Page: Brand field not displayed",brand.isDisplayed());
        AssertJUnit.assertTrue("Edit Quantity Factor Page: Ticket field not displayed",ticket.isDisplayed());
        AssertJUnit.assertTrue("Edit Quantity Factor Page: Lab QTYfield not displayed",labQty.isDisplayed());
        AssertJUnit.assertTrue("Edit Quantity Factor Page: CCE QTY field not displayed",cceQty.isDisplayed());
        AssertJUnit.assertTrue("Edit Quantity Factor Page: Save button not displayed",save.isDisplayed());
        AssertJUnit.assertTrue("Edit Quantity Factor Page: Cancel button not displayed",cancel.isDisplayed());
    }
    
    public void waitForElement() {
        WebElement plantName = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(plantNameField));
    }
    
}
