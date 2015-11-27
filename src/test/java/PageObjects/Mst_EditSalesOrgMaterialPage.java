
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

public class Mst_EditSalesOrgMaterialPage extends WBA_BasePage {
    
    By articleField = By.id("SalesOrgMaterialArticle");
    By salesOrgField = By.id("SalesOrgMaterialSalesOrgId");
    By plantField = By.id("SalesOrgMaterialPlantId");
    By brandField = By.id("SalesOrgMaterialBrandId");
    By ticketField = By.id("SalesOrgMaterialTicketId");
    By mumTypeField = By.id("SalesOrgMaterialMumTypeId");
    By lengthField = By.id("SalesOrgMaterialLengthId");
    By saveButton = By.cssSelector("#SalesOrgMaterialEditForm > div.actions > ul > li:nth-child(1) > input[type=\"submit\"]");
    By cancelButton = By.cssSelector("#SalesOrgMaterialEditForm > div.actions > ul > li:nth-child(2) > a");
    
    public Mst_EditSalesOrgMaterialPage(WebDriver driver) {
        super(driver);
    }
    
    public WebElement getBreadcrumb() {
        return new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(DataItems.breadcrumbLocator));
    }
    
    public Mst_EditSalesOrgMaterialPage setArticle(String item) {
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(articleField));
        element.clear();
        
        CommonTask.setInputField(driver, articleField, item);
        return new Mst_EditSalesOrgMaterialPage(driver);
    }
    
    public Mst_EditSalesOrgMaterialPage setSalesOrg(String item) throws InterruptedException {
        CommonTask.setDropDownField(driver, salesOrgField, item);
        return new Mst_EditSalesOrgMaterialPage(driver);
    }
    
    public Mst_EditSalesOrgMaterialPage setPlant(String item) throws InterruptedException {
        CommonTask.setDropDownField(driver, plantField, item);
        return new Mst_EditSalesOrgMaterialPage(driver);
    }
    
    public Mst_EditSalesOrgMaterialPage setBrand(String item) throws InterruptedException {
        CommonTask.setDropDownField(driver, brandField, item);
        return new Mst_EditSalesOrgMaterialPage(driver);
    }
    
    public Mst_EditSalesOrgMaterialPage setTicket(String item) throws InterruptedException {
        CommonTask.setDropDownField(driver, ticketField, item);
        return new Mst_EditSalesOrgMaterialPage(driver);
    }
    
    public Mst_EditSalesOrgMaterialPage setMUMType(String item) throws InterruptedException {
        CommonTask.setDropDownField(driver, mumTypeField, item);
        return new Mst_EditSalesOrgMaterialPage(driver);
    }
    
    public Mst_EditSalesOrgMaterialPage setLength(String item) throws InterruptedException {
        CommonTask.setDropDownField(driver, lengthField, item);
        return new Mst_EditSalesOrgMaterialPage(driver);
    }
    
    public Mst_SalesOrgMaterialsPage pressSave() {
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(saveButton));
        element.click();
        
        return new Mst_SalesOrgMaterialsPage(driver);
    }
    
    public void checkFields() {
        WebElement article = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(articleField));
        WebElement salesOrg = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(salesOrgField));
        WebElement plant = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(plantField));
        WebElement brand = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(brandField));
        WebElement ticket = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(ticketField));
        WebElement mumType = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(mumTypeField));
        WebElement length = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(lengthField));
        WebElement save = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(saveButton));
        WebElement cancel = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(cancelButton));
        
        AssertJUnit.assertTrue("Add Sales Org Material Page: Article field is not displayed",article.isDisplayed());
        AssertJUnit.assertTrue("Add Sales Org Material Page: Sales Org field is not displayed",salesOrg.isDisplayed());
        AssertJUnit.assertTrue("Add Sales Org Material Page: Plant field is not displayed",plant.isDisplayed());
        AssertJUnit.assertTrue("Add Sales Org Material Page: Brand field is not displayed",brand.isDisplayed());
        AssertJUnit.assertTrue("Add Sales Org Material Page: Ticket field is not displayed",ticket.isDisplayed());
        AssertJUnit.assertTrue("Add Sales Org Material Page: MUM Type field is not displayed",mumType.isDisplayed());
        AssertJUnit.assertTrue("Add Sales Org Material Page: Length field is not displayed",length.isDisplayed());
        AssertJUnit.assertTrue("Add Sales Org Material Page: Save field is not displayed",save.isDisplayed());
        AssertJUnit.assertTrue("Add Sales Org Material Page: Cancel field is not displayed",cancel.isDisplayed());
    }
    
    public void waitForElement() {
        WebElement mumType = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(mumTypeField));
    }
    
}
