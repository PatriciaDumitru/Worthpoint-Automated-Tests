
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

public class Mst_AddOurStockPage extends WBA_BasePage {
    
    By salesOrgField = By.id("OurStockSalesOrgId");
    By plantField = By.id("OurStockPlantId");
    By articleField = By.id("s2id_OurStockArticleId");
    By brandField = By.id("OurStockBrandId");
    By ticketField = By.id("OurStockTicketId");
    By mumTypeField = By.id("OurStockMumTypeId");
    By shadeField = By.id("s2id_OurStockShadeId");
    By quantityField = By.id("OurStockQuantity");
    By saveButton = By.cssSelector("#OurStockAddForm > div.actions > ul > li:nth-child(1) > input[type=\"submit\"]");
    By cancelButton = By.cssSelector("#OurStockAddForm > div.actions > ul > li:nth-child(2) > a");
    
    public Mst_AddOurStockPage(WebDriver driver) {
        super(driver);
    }
    
    public WebElement getBreadcrumb() {
        return Wait.visible(driver,DataItems.breadcrumbLocator);
    }
    
    public Mst_AddOurStockPage setSalesOrg(String item) throws InterruptedException {
        CommonTask.setDropDownField(driver, salesOrgField, item);
        return new Mst_AddOurStockPage(driver);
    }
    
    public Mst_AddOurStockPage setPlant(String item) throws InterruptedException {
        CommonTask.setDropDownField(driver, plantField, item);
        return new Mst_AddOurStockPage(driver);
    }
    
    public Mst_AddOurStockPage setArticle(String item) throws InterruptedException {
        CommonTask.setSearchField(driver, articleField, item);
        return new Mst_AddOurStockPage(driver);
    }
    
    public Mst_AddOurStockPage setBrand(String item) throws InterruptedException {
        CommonTask.setDropDownField(driver, brandField, item);
        return new Mst_AddOurStockPage(driver);
    }
    
    public Mst_AddOurStockPage setTicket(String item) throws InterruptedException {
        CommonTask.setDropDownField(driver, ticketField, item);
        return new Mst_AddOurStockPage(driver);
    }
    
    public Mst_AddOurStockPage setMUMType(String item) throws InterruptedException {
        CommonTask.setDropDownField(driver, mumTypeField, item);
        return new Mst_AddOurStockPage(driver);
    }
    
    public Mst_AddOurStockPage setShade(String item) throws InterruptedException {
        CommonTask.setSearchField(driver, shadeField, item);
        return new Mst_AddOurStockPage(driver);
    }
    
    public Mst_AddOurStockPage setQuantity(String item) throws InterruptedException {
        WebElement element = Wait.clickable(driver,quantityField);
        element.clear();
        
        CommonTask.setInputField(driver, quantityField, item);
        return new Mst_AddOurStockPage(driver);
    }
    
    public Mst_WarehouseStocksPage pressSave() {
        WebElement element = Wait.clickable(driver,saveButton);
        element.click();
        
        return new Mst_WarehouseStocksPage(driver);
    }
    
    public void checkFields() {
        WebElement salesOrg = Wait.clickable(driver,salesOrgField);
        WebElement plant = Wait.clickable(driver,plantField);
        WebElement article = Wait.clickable(driver,articleField);
        WebElement brand = Wait.clickable(driver,brandField);
        WebElement ticket = Wait.clickable(driver,ticketField);
        WebElement mumType = Wait.clickable(driver,mumTypeField);
        WebElement shade = Wait.clickable(driver,shadeField);
        WebElement quantity = Wait.clickable(driver,quantityField);
        WebElement save = Wait.clickable(driver,saveButton);
        WebElement cancel = Wait.clickable(driver,saveButton);
        
        AssertJUnit.assertTrue("Add Our Stock Page: Sales Org field not displayed",salesOrg.isDisplayed());
        AssertJUnit.assertTrue("Add Our Stock Page: Plant field not displayed",plant.isDisplayed());
        AssertJUnit.assertTrue("Add Our Stock Page: Article field not displayed",article.isDisplayed());
        AssertJUnit.assertTrue("Add Our Stock Page: Brand field not displayed",brand.isDisplayed());
        AssertJUnit.assertTrue("Add Our Stock Page: Ticket field not displayed",ticket.isDisplayed());
        AssertJUnit.assertTrue("Add Our Stock Page: MUM Type field not displayed",mumType.isDisplayed());
        AssertJUnit.assertTrue("Add Our Stock Page: Shade field not displayed",shade.isDisplayed());
        AssertJUnit.assertTrue("Add Our Stock Page: Save field not displayed",save.isDisplayed());
        AssertJUnit.assertTrue("Add Our Stock Page: Cancel field not displayed",cancel.isDisplayed());
    }
    
    public void waitForElement() {
        WebElement mumType = Wait.clickable(driver,mumTypeField);
    }    
    
}
