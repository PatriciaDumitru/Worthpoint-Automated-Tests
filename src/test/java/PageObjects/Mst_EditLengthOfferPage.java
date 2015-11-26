
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

public class Mst_EditLengthOfferPage extends WBA_BasePage {
    
    By brandField = By.id("LengthOfferBrandId");
    By ticketField = By.id("LengthOfferTicketId");
    By plantField = By.id("LengthOfferPlantId");
    By uomField = By.id("LengthOfferUomId");
    By measStandardField = By.id("LengthOfferMeasurementStandardId");
    By copField = By.id("LengthOfferCopLengthId");
    By coneField = By.id("LengthOfferConeLengthId");
    By viconeField = By.id("LengthOfferViconeLengthId");
    By saveButton = By.cssSelector("#LengthOfferEditForm > div.actions > ul > li:nth-child(1) > input[type=\"submit\"]");
    By cancelButton = By.cssSelector("#LengthOfferEditForm > div.actions > ul > li:nth-child(2) > a");
    
    public Mst_EditLengthOfferPage(WebDriver driver) {
        super(driver);
    }
    
    public WebElement getBreadcrumb() {
        return new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.visibilityOfElementLocated(breadcrumbLocator));
    }
    
    public Mst_AddLengthOfferPage setBrand(String item) throws InterruptedException {
        CommonTask.setDropDownField(driver,brandField,item);
        return new Mst_AddLengthOfferPage(driver);
    }
    
    public Mst_AddLengthOfferPage setTicket(String item) throws InterruptedException {
        CommonTask.setDropDownField(driver,ticketField,item);
        return new Mst_AddLengthOfferPage(driver);
    }
    
    public Mst_AddLengthOfferPage setPlant(String item) throws InterruptedException {
        CommonTask.setDropDownField(driver,plantField,item);
        return new Mst_AddLengthOfferPage(driver);
    }
    
    public Mst_AddLengthOfferPage setUOM(String item) throws InterruptedException {
        CommonTask.setDropDownField(driver,uomField,item);
        return new Mst_AddLengthOfferPage(driver);
    }
    
    public Mst_AddLengthOfferPage setMeasurementStandard(String item) throws InterruptedException {
        CommonTask.setDropDownField(driver,measStandardField,item);
        return new Mst_AddLengthOfferPage(driver);
    }
    
    public Mst_AddLengthOfferPage setCop(String item) throws InterruptedException {
        CommonTask.setDropDownField(driver,copField,item);
        return new Mst_AddLengthOfferPage(driver);
    }
    
    public Mst_AddLengthOfferPage setCone(String item) throws InterruptedException {
        CommonTask.setDropDownField(driver,coneField,item);
        return new Mst_AddLengthOfferPage(driver);
    }
    
    public Mst_AddLengthOfferPage setVicone(String item) throws InterruptedException {
        CommonTask.setDropDownField(driver,viconeField,item);
        return new Mst_AddLengthOfferPage(driver);
    }
    
    public Mst_LengthOffersPage pressSave() {
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(saveButton));
        element.click();
        
        return new Mst_LengthOffersPage(driver);
    }
    
    public void checkFields() {
        WebElement brand = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(brandField));
        WebElement ticket = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(ticketField));
        WebElement plant = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(plantField));
        WebElement uom = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(uomField));
        WebElement measStan = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(measStandardField));
        WebElement cop = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(copField));
        WebElement cone = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(coneField));
        WebElement vicone = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(viconeField));
        WebElement save = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(saveButton));
        WebElement cancel = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(cancelButton));
        
        AssertJUnit.assertTrue("Add Length Offer Page: Brand field not displayed",brand.isDisplayed());
        AssertJUnit.assertTrue("Add Length Offer Page: Ticket field not displayed",ticket.isDisplayed());
        AssertJUnit.assertTrue("Add Length Offer Page: Plant field not displayed",plant.isDisplayed());
        AssertJUnit.assertTrue("Add Length Offer Page: UOM field not displayed",uom.isDisplayed());
        AssertJUnit.assertTrue("Add Length Offer Page: Measurement Standard field not displayed",measStan.isDisplayed());
        AssertJUnit.assertTrue("Add Length Offer Page: Cop length field not displayed",cop.isDisplayed());
        AssertJUnit.assertTrue("Add Length Offer Page: Cone length field not displayed",cone.isDisplayed());
        AssertJUnit.assertTrue("Add Length Offer Page: Vicone length field not displayed",vicone.isDisplayed());
        AssertJUnit.assertTrue("Add Length Offer Page: Save button not displayed",save.isDisplayed());
        AssertJUnit.assertTrue("Add Length Offer Page: Cancel button not displayed",cancel.isDisplayed());
    }
    
    public void waitForElement() {
        WebElement measStan = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(measStandardField));        
    }
    
}
