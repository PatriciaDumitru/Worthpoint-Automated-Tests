
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

public class Mst_AddLengthOfferPage extends WBA_BasePage {
    
    By brandField = By.id("LengthOfferBrandId");
    By ticketField = By.id("LengthOfferTicketId");
    By plantField = By.id("LengthOfferPlantId");
    By uomField = By.id("LengthOfferUomId");
    By measStandardField = By.id("LengthOfferMeasurementStandardId");
    By copField = By.id("LengthOfferCopLengthId");
    By coneField = By.id("LengthOfferConeLengthId");
    By viconeField = By.id("LengthOfferViconeLengthId");
    By saveButton = By.cssSelector("#LengthOfferAddForm > div.actions > ul > li:nth-child(1) > input[type=\"submit\"]");
    By cancelButton = By.cssSelector("#LengthOfferAddForm > div.actions > ul > li:nth-child(2) > a");
    
    public Mst_AddLengthOfferPage(WebDriver driver) {
        super(driver);
    }
    
    public WebElement getBreadcrumb() {
        return Wait.visible(driver,DataItems.breadcrumbLocator);
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
        WebElement element = Wait.clickable(driver,saveButton);
        element.click();
        
        return new Mst_LengthOffersPage(driver);
    }
    
    public void checkFields() {
        WebElement brand = Wait.clickable(driver,brandField);
        WebElement ticket = Wait.clickable(driver,ticketField);
        WebElement plant = Wait.clickable(driver,plantField);
        WebElement uom = Wait.clickable(driver,uomField);
        WebElement measStan = Wait.clickable(driver,measStandardField);
        WebElement cop = Wait.clickable(driver,copField);
        WebElement cone = Wait.clickable(driver,coneField);
        WebElement vicone = Wait.clickable(driver,viconeField);
        WebElement save = Wait.clickable(driver,saveButton);
        WebElement cancel = Wait.clickable(driver,cancelButton);
        
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
        WebElement measStan = Wait.clickable(driver,measStandardField);        
    }
    
}
