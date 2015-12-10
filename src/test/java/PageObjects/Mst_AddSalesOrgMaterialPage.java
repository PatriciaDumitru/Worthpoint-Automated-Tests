
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

public class Mst_AddSalesOrgMaterialPage extends WBA_BasePage {
    
    By articleField = By.id("SalesOrgMaterialArticle");
    By salesOrgField = By.id("SalesOrgMaterialSalesOrgId");
    By plantField = By.id("SalesOrgMaterialPlantId");
    By brandField = By.id("SalesOrgMaterialBrandId");
    By ticketField = By.id("SalesOrgMaterialTicketId");
    By mumTypeField = By.id("SalesOrgMaterialMumTypeId");
    By lengthField = By.id("SalesOrgMaterialLengthId");
    By saveButton = By.cssSelector("#SalesOrgMaterialAddForm > div.actions > ul > li:nth-child(1) > input[type=\"submit\"]");
    By cancelButton = By.cssSelector("#SalesOrgMaterialAddForm > div.actions > ul > li:nth-child(2) > a");
    
    public Mst_AddSalesOrgMaterialPage(WebDriver driver) {
        super(driver);
    }
    
    public WebElement getBreadcrumb() {
        return Wait.visible(driver,DataItems.breadcrumbLocator);
    }
    
    public Mst_AddSalesOrgMaterialPage setArticle(String item) {
        CommonTask.setInputField(driver, articleField, item);
        return new Mst_AddSalesOrgMaterialPage(driver);
    }
    
    public Mst_AddSalesOrgMaterialPage setSalesOrg(String item) throws InterruptedException {
        CommonTask.setDropDownField(driver, salesOrgField, item);
        return new Mst_AddSalesOrgMaterialPage(driver);
    }
    
    public Mst_AddSalesOrgMaterialPage setPlant(String item) throws InterruptedException {
        CommonTask.setDropDownField(driver, plantField, item);
        return new Mst_AddSalesOrgMaterialPage(driver);
    }
    
    public Mst_AddSalesOrgMaterialPage setBrand(String item) throws InterruptedException {
        CommonTask.setDropDownField(driver, brandField, item);
        return new Mst_AddSalesOrgMaterialPage(driver);
    }
    
    public Mst_AddSalesOrgMaterialPage setTicket(String item) throws InterruptedException {
        CommonTask.setDropDownField(driver, ticketField, item);
        return new Mst_AddSalesOrgMaterialPage(driver);
    }
    
    public Mst_AddSalesOrgMaterialPage setMUMType(String item) throws InterruptedException {
        CommonTask.setDropDownField(driver, mumTypeField, item);
        return new Mst_AddSalesOrgMaterialPage(driver);
    }
    
    public Mst_AddSalesOrgMaterialPage setLength(String item) throws InterruptedException {
        CommonTask.setDropDownField(driver, lengthField, item);
        return new Mst_AddSalesOrgMaterialPage(driver);
    }
    
    public Mst_SalesOrgMaterialsPage pressSave() {
        WebElement element = Wait.clickable(driver,saveButton);
        element.click();
        
        return new Mst_SalesOrgMaterialsPage(driver);
    }
    
    public void checkFields() {
        WebElement article = Wait.clickable(driver,articleField);
        WebElement salesOrg = Wait.clickable(driver,salesOrgField);
        WebElement plant = Wait.clickable(driver,plantField);
        WebElement brand = Wait.clickable(driver,brandField);
        WebElement ticket = Wait.clickable(driver,ticketField);
        WebElement mumType = Wait.clickable(driver,mumTypeField);
        WebElement length = Wait.clickable(driver,lengthField);
        WebElement save = Wait.clickable(driver,saveButton);
        WebElement cancel = Wait.clickable(driver,cancelButton);
        
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
        WebElement mumType = Wait.clickable(driver,mumTypeField);
    }
    
}
