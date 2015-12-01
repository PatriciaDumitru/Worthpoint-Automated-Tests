
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

public class Mst_AddSupplyPlantPage extends WBA_BasePage {
    
    By deliveryPlantField = By.id("SupplyPlantDeliveryPlantId");
    By articleField = By.id("s2id_SupplyPlantArticleId");
    By brandField = By.id("SupplyPlantBrandId");
    By ticketField = By.id("SupplyPlantTicketId");
    By mumTypeField = By.id("SupplyPlantMumTypeId");
    By supplyPlantField = By.id("SupplyPlantSupplyPlantId");
    By saveButton = By.cssSelector("#SupplyPlantAddForm > div.actions > ul > li:nth-child(1) > input[type=\"submit\"]");
    By cancelButton = By.cssSelector("#SupplyPlantAddForm > div.actions > ul > li:nth-child(2) > a");
    
    public Mst_AddSupplyPlantPage(WebDriver driver) {
        super(driver);
    }
    
    public WebElement getBreadcrumb() {
        return new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(DataItems.breadcrumbLocator));
    }
    
    public Mst_AddSupplyPlantPage setDeliveryPlant(String item) throws InterruptedException {
        CommonTask.setDropDownField(driver, deliveryPlantField, item);
        return new Mst_AddSupplyPlantPage(driver);
    }
    
    public Mst_AddSupplyPlantPage setBrand(String item) throws InterruptedException {
        CommonTask.setDropDownField(driver, brandField, item);
        return new Mst_AddSupplyPlantPage(driver);
    }
    
    public Mst_AddSupplyPlantPage setTicket(String item) throws InterruptedException {
        CommonTask.setDropDownField(driver, ticketField, item);
        return new Mst_AddSupplyPlantPage(driver);
    }
    
    public Mst_AddSupplyPlantPage setMUMType(String item) throws InterruptedException {
        CommonTask.setDropDownField(driver, mumTypeField, item);
        return new Mst_AddSupplyPlantPage(driver);
    }
    
    public Mst_AddSupplyPlantPage setSupplyPlant(String item) throws InterruptedException {
        CommonTask.setDropDownField(driver, supplyPlantField, item);
        return new Mst_AddSupplyPlantPage(driver);
    }
    
    public Mst_SupplyPlantsPage pressSave() {
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(saveButton));
        element.click();
        
        return new Mst_SupplyPlantsPage(driver);
    }
    
    public void checkFields() {
        WebElement delPlant = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(deliveryPlantField));
        WebElement article = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(articleField));
        WebElement brand = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(brandField));
        WebElement ticket = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(ticketField));
        WebElement mumType = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(mumTypeField));
        WebElement supplyPlant = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(supplyPlantField));
        WebElement save = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(saveButton));
        WebElement cancel = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(cancelButton));
        
        AssertJUnit.assertTrue("Add Supply Plant Page: Delivery Plant field not displayed",delPlant.isDisplayed());
        AssertJUnit.assertTrue("Add Supply Plant Page: Article field not displayed",article.isDisplayed());
        AssertJUnit.assertTrue("Add Supply Plant Page: Brand field not displayed",brand.isDisplayed());
        AssertJUnit.assertTrue("Add Supply Plant Page: Ticket field not displayed",ticket.isDisplayed());
        AssertJUnit.assertTrue("Add Supply Plant Page: MUM Type field not displayed",mumType.isDisplayed());
        AssertJUnit.assertTrue("Add Supply Plant Page: Supply Plant field not displayed",supplyPlant.isDisplayed());
        AssertJUnit.assertTrue("Add Supply Plant Page: Save button not displayed",save.isDisplayed());
        AssertJUnit.assertTrue("Add Supply Plant Page: Cancel button not displayed",cancel.isDisplayed());
    }
    
    public void waitForElement() {
        WebElement delPlant = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(deliveryPlantField));
    }
    
    
}
