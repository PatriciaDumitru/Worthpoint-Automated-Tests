
package PageObjects;

import AutomationFramework.CommonTask;
import AutomationFramework.DataItems;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.AssertJUnit;

public class Mst_EditOurStockPage extends WBA_BasePage {
    
    By salesOrgField = By.id("OurStockSalesOrgId");
    By plantField = By.id("OurStockPlantId");
    By articleField = By.id("s2id_OurStockArticleId");
    By brandField = By.id("OurStockBrandId");
    By ticketField = By.id("OurStockTicketId");
    By mumTypeField = By.id("OurStockMumTypeId");
    By shadeField = By.id("s2id_OurStockShadeId");
    By quantityField = By.id("OurStockQuantity");
    By saveButton = By.cssSelector("#OurStockEditForm > div.actions > ul > li:nth-child(1) > input[type=\"submit\"]");
    By cancelButton = By.cssSelector("#OurStockEditForm > div.actions > ul > li:nth-child(2) > a");
    
    public Mst_EditOurStockPage(WebDriver driver) {
        super(driver);
    }
    
    public WebElement getBreadcrumb() {
        return new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.visibilityOfElementLocated(DataItems.breadcrumbLocator));
    }
    
    public Mst_EditOurStockPage setSalesOrg(String item) throws InterruptedException {
        CommonTask.setDropDownField(driver, salesOrgField, item);
        return new Mst_EditOurStockPage(driver);
    }
    
    public Mst_EditOurStockPage setPlant(String item) throws InterruptedException {
        CommonTask.setDropDownField(driver, plantField, item);
        return new Mst_EditOurStockPage(driver);
    }
    
    public Mst_EditOurStockPage setArticle(String item) throws InterruptedException {
        CommonTask.setSearchField(driver, articleField, item);
        return new Mst_EditOurStockPage(driver);
    }
    
    public Mst_EditOurStockPage setBrand(String item) throws InterruptedException {
        CommonTask.setDropDownField(driver, brandField, item);
        return new Mst_EditOurStockPage(driver);
    }
    
    public Mst_EditOurStockPage setTicket(String item) throws InterruptedException {
        CommonTask.setDropDownField(driver, ticketField, item);
        return new Mst_EditOurStockPage(driver);
    }
    
    public Mst_EditOurStockPage setMUMType(String item) throws InterruptedException {
        CommonTask.setDropDownField(driver, mumTypeField, item);
        return new Mst_EditOurStockPage(driver);
    }
    
    public Mst_EditOurStockPage setShade(String item) throws InterruptedException {
        CommonTask.setSearchField(driver, shadeField, item);
        return new Mst_EditOurStockPage(driver);
    }
    
    public Mst_EditOurStockPage setQuantity(String item) throws InterruptedException {
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(quantityField));
        element.clear();
        
        CommonTask.setInputField(driver, quantityField, item);
        return new Mst_EditOurStockPage(driver);
    }
    
    public Mst_WarehouseStocksPage pressSave() {
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(saveButton));
        element.click();
        
        return new Mst_WarehouseStocksPage(driver);
    }
    
    public void checkFields() {
        WebElement salesOrg = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(salesOrgField));
        WebElement plant = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(plantField));
        WebElement article = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(articleField));
        WebElement brand = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(brandField));
        WebElement ticket = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(ticketField));
        WebElement mumType = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(mumTypeField));
        WebElement shade = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(shadeField));
        WebElement quantity = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(quantityField));
        WebElement save = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(saveButton));
        WebElement cancel = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(saveButton));
        
        AssertJUnit.assertTrue("Edit Our Stock Page: Sales Org field not displayed",salesOrg.isDisplayed());
        AssertJUnit.assertTrue("Edit Our Stock Page: Plant field not displayed",plant.isDisplayed());
        AssertJUnit.assertTrue("Edit Our Stock Page: Article field not displayed",article.isDisplayed());
        AssertJUnit.assertTrue("Edit Our Stock Page: Brand field not displayed",brand.isDisplayed());
        AssertJUnit.assertTrue("Edit Our Stock Page: Ticket field not displayed",ticket.isDisplayed());
        AssertJUnit.assertTrue("Edit Our Stock Page: MUM Type field not displayed",mumType.isDisplayed());
        AssertJUnit.assertTrue("Edit Our Stock Page: Shade field not displayed",shade.isDisplayed());
        AssertJUnit.assertTrue("Edit Our Stock Page: Save field not displayed",save.isDisplayed());
        AssertJUnit.assertTrue("Edit Our Stock Page: Cancel field not displayed",cancel.isDisplayed());
    }
    
    public void waitForElement() {
        WebElement mumType = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(mumTypeField));
    }    
}
