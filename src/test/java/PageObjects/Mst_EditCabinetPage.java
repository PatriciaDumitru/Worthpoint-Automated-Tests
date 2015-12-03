
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

public class Mst_EditCabinetPage extends WBA_BasePage {
    
    By salesOrgField = By.id("CabinetSalesOrgId");
    By cabinetCodeField = By.id("CabinetCabinetCode");
    By shipToPartyField = By.id("s2id_CabinetShipToPartyId");
    By addThreadButton = By.cssSelector("#CabinetEditForm > div:nth-child(4) > div.actions > ul > li > input[type=\"button\"]");
    By saveButton = By.cssSelector("#CabinetEditForm > div.actions > ul > li:nth-child(1) > input[type=\"submit\"]");
    By cancelButton = By.cssSelector("#CabinetEditForm > div.actions > ul > li:nth-child(2) > a");
    
    public Mst_EditCabinetPage(WebDriver driver) {
        super(driver);
    }
    
    public WebElement getBreadcrumb() {
        return new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.visibilityOfElementLocated(DataItems.breadcrumbLocator));
    }
    
    public Mst_EditCabinetPage setSalesOrg(String item) throws InterruptedException {
        CommonTask.setDropDownField(driver, salesOrgField, item);
        return new Mst_EditCabinetPage(driver);
    }
    
    public Mst_EditCabinetPage setCabinetCode(String item) throws InterruptedException {
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(cabinetCodeField));
        element.clear();
        
        CommonTask.setInputField(driver, cabinetCodeField, item);
        return new Mst_EditCabinetPage(driver);
    }
    
    public Mst_EditCabinetPage setShipToParty(String item) {
        CommonTask.setSearchField(driver, shipToPartyField, item);
        return new Mst_EditCabinetPage(driver);
    }
    
    public Mst_EditCabinetPage setBrand(String item, int row) throws InterruptedException {
        By locator = By.id("CabinetsThread"+row+"BrandId");
        CommonTask.setDropDownField(driver, locator, item);
        return new Mst_EditCabinetPage(driver);
    }
    
    public Mst_EditCabinetPage setTicket(String item, int row) throws InterruptedException {
        By locator = By.id("CabinetsThread"+row+"TicketId");
        CommonTask.setDropDownField(driver, locator, item);
        return new Mst_EditCabinetPage(driver);
    }
            
    public Mst_EditCabinetPage setShade(String item, int row) throws InterruptedException {
        By locator = By.id("s2id_CabinetsThread"+row+"ShadeId");
        CommonTask.setSearchField(driver, locator, item);
        return new Mst_EditCabinetPage(driver);
    }        
    
    public Mst_EditCabinetPage setMUMType(String item, int row) throws InterruptedException {
        By locator = By.id("CabinetsThread"+row+"MumTypeId");
        CommonTask.setDropDownField(driver, locator, item);
        return new Mst_EditCabinetPage(driver);
    }
    
    public Mst_EditCabinetPage setQuality(String item, int row) throws InterruptedException {
        By locator = By.id("CabinetsThread"+row+"Quantity");
        CommonTask.setInputField(driver, locator, item);
        return new Mst_EditCabinetPage(driver);
    }
    
    public Mst_EditCabinetPage pressAddThread() {
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(addThreadButton));
        element.click();
        
        return new Mst_EditCabinetPage(driver);
    }
    
    public Mst_CabinetsPage pressSave() {
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(saveButton));
        element.click();
        
        return new Mst_CabinetsPage(driver);
    }

    public void checkFields() {
        WebElement salesOrg = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(salesOrgField));
        WebElement cabinetCode = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(cabinetCodeField));
        WebElement shipToParty = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(shipToPartyField));
        WebElement addThread = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(addThreadButton));
        WebElement save = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(saveButton));
        WebElement cancel = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(cancelButton));
        
        AssertJUnit.assertTrue("Edit Cabinet Page: Sales Org field not displayed",salesOrg.isDisplayed());
        AssertJUnit.assertTrue("Edit Cabinet Page: Cabinet Code field not displayed",cabinetCode.isDisplayed());
        AssertJUnit.assertTrue("Edit Cabinet Page: Ship To Party field not displayed",shipToParty.isDisplayed());
        AssertJUnit.assertTrue("Edit Cabinet Page: Add Thread button not displayed",addThread.isDisplayed());
        AssertJUnit.assertTrue("Edit Cabinet Page: Save button not displayed",save.isDisplayed());
        AssertJUnit.assertTrue("Edit Cabinet Page: Cancel button not displayed",cancel.isDisplayed());
    }
    
    public void waitForElement() {
        WebElement cabinetCode = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(cabinetCodeField));
    }
    
}
