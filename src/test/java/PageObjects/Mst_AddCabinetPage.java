
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

public class Mst_AddCabinetPage extends WBA_BasePage {
    
    By salesOrgField = By.id("CabinetSalesOrgId");
    By cabinetCodeField = By.id("CabinetCabinetCode");
    By shipToPartyField = By.id("s2id_CabinetShipToPartyId");
    By addThreadButton = By.cssSelector("#CabinetAddForm > div:nth-child(3) > div.actions > ul > li > input[type=\"button\"]");
    By saveButton = By.cssSelector("#CabinetAddForm > div.actions > ul > li:nth-child(1) > input[type=\"submit\"]");
    By cancelButton = By.cssSelector("#CabinetAddForm > div.actions > ul > li:nth-child(2) > a");
    
    public Mst_AddCabinetPage(WebDriver driver) {
        super(driver);
    }
    
    public WebElement getBreadcrumb() {
        return Wait.visible(driver,DataItems.breadcrumbLocator);
    }
    
    public Mst_AddCabinetPage setSalesOrg(String item) throws InterruptedException {
        CommonTask.setDropDownField(driver, salesOrgField, item);
        return new Mst_AddCabinetPage(driver);
    }
    
    public Mst_AddCabinetPage setCabinetCode(String item) throws InterruptedException {
        WebElement element = Wait.clickable(driver,cabinetCodeField);
        element.clear();
        
        CommonTask.setInputField(driver, cabinetCodeField, item);
        return new Mst_AddCabinetPage(driver);
    }
    
    public Mst_AddCabinetPage setShipToParty(String item) {
        CommonTask.setSearchField(driver, shipToPartyField, item);
        return new Mst_AddCabinetPage(driver);
    }
    
    public Mst_AddCabinetPage setBrand(String item, int row) throws InterruptedException {
        By locator = By.id("CabinetsThread"+row+"BrandId");
        CommonTask.setDropDownField(driver, locator, item);
        return new Mst_AddCabinetPage(driver);
    }
    
    public Mst_AddCabinetPage setTicket(String item, int row) throws InterruptedException {
        By locator = By.id("CabinetsThread"+row+"TicketId");
        CommonTask.setDropDownField(driver, locator, item);
        return new Mst_AddCabinetPage(driver);
    }
            
    public Mst_AddCabinetPage setShade(String item, int row) throws InterruptedException {
        By locator = By.id("s2id_CabinetsThread"+row+"ShadeId");
        CommonTask.setSearchField(driver, locator, item);
        return new Mst_AddCabinetPage(driver);
    }        
    
    public Mst_AddCabinetPage setMUMType(String item, int row) throws InterruptedException {
        By locator = By.id("CabinetsThread"+row+"MumTypeId");
        CommonTask.setDropDownField(driver, locator, item);
        return new Mst_AddCabinetPage(driver);
    }
    
    public Mst_AddCabinetPage setQuantity(String item, int row) throws InterruptedException {
        By locator = By.id("CabinetsThread"+row+"Quantity");
        CommonTask.setInputField(driver, locator, item);
        return new Mst_AddCabinetPage(driver);
    }
    
    public Mst_AddCabinetPage pressAddThread() {
        WebElement element = Wait.clickable(driver,addThreadButton);
        element.click();
        
        return new Mst_AddCabinetPage(driver);
    }
    
    public Mst_CabinetsPage pressSave() {
        WebElement element = Wait.clickable(driver,saveButton);
        element.click();
        
        return new Mst_CabinetsPage(driver);
    }

    public void checkFields() {
        WebElement salesOrg = Wait.clickable(driver,salesOrgField);
        WebElement cabinetCode = Wait.clickable(driver,cabinetCodeField);
        WebElement shipToParty = Wait.clickable(driver,shipToPartyField);
        WebElement addThread = Wait.clickable(driver,addThreadButton);
        WebElement save = Wait.clickable(driver,saveButton);
        WebElement cancel = Wait.clickable(driver,cancelButton);
        
        AssertJUnit.assertTrue("Add Cabinet Page: Sales Org field not displayed",salesOrg.isDisplayed());
        AssertJUnit.assertTrue("Add Cabinet Page: Cabinet Code field not displayed",cabinetCode.isDisplayed());
        AssertJUnit.assertTrue("Add Cabinet Page: Ship To Party field not displayed",shipToParty.isDisplayed());
        AssertJUnit.assertTrue("Add Cabinet Page: Add Thread button not displayed",addThread.isDisplayed());
        AssertJUnit.assertTrue("Add Cabinet Page: Save button not displayed",save.isDisplayed());
        AssertJUnit.assertTrue("Add Cabinet Page: Cancel button not displayed",cancel.isDisplayed());
    }
    
    public void waitForElement() {
        WebElement cabinetCode = Wait.clickable(driver,cabinetCodeField);
    }
    
}
