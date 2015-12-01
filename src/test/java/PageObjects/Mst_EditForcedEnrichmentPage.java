
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

public class Mst_EditForcedEnrichmentPage extends WBA_BasePage {
    
    By salesOrgField = By.id("ForcedEnrichmentProductSalesOrgId");
    By custNameField = By.id("s2id_ForcedEnrichmentProductCustomerId");
    By brandField = By.id("ForcedEnrichmentProductBrandId");
    By ticketField = By.id("ForcedEnrichmentProductTicketId");
    By mumTypeField = By.id("ForcedEnrichmentProductMumTypeId");
    By saveButton = By.cssSelector("#ForcedEnrichmentProductEditForm > div.actions > ul > li:nth-child(1) > input[type=\"submit\"]");
    By cancelButton = By.cssSelector("#ForcedEnrichmentProductEditForm > div.actions > ul > li:nth-child(2) > a");
    
    public Mst_EditForcedEnrichmentPage(WebDriver driver) {
        super(driver);
    }
    
    public WebElement getBreadcrumb() {
        return new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(DataItems.breadcrumbLocator));
    }
    
    public Mst_EditForcedEnrichmentPage setSalesOrg(String item) throws InterruptedException {
        CommonTask.setDropDownField(driver, salesOrgField, item);
        return new Mst_EditForcedEnrichmentPage(driver);
    }
 
    public Mst_EditForcedEnrichmentPage setCustomerName(String item) throws InterruptedException {
        CommonTask.setSearchField(driver, custNameField, item);
        return new Mst_EditForcedEnrichmentPage(driver);
    }
    
    public Mst_EditForcedEnrichmentPage setBrand(String item) throws InterruptedException {
        CommonTask.setDropDownField(driver, brandField, item);
        return new Mst_EditForcedEnrichmentPage(driver);
    }
    
    public Mst_EditForcedEnrichmentPage setTicket(String item) throws InterruptedException {
        CommonTask.setDropDownField(driver, ticketField, item);
        return new Mst_EditForcedEnrichmentPage(driver);
    }
    
    public Mst_EditForcedEnrichmentPage setMUMType(String item) throws InterruptedException {
        CommonTask.setDropDownField(driver, mumTypeField, item);
        return new Mst_EditForcedEnrichmentPage(driver);
    }
    
    public Mst_ForcedEnrichmentPage pressSave() {
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(saveButton));
        element.click();
        
        return new Mst_ForcedEnrichmentPage(driver);
    }
    
    public void checkFields() {
        WebElement salesOrg = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(salesOrgField));
        WebElement custName = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(custNameField));
        WebElement brand = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(brandField));
        WebElement ticket = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(ticketField));
        WebElement mumType = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(mumTypeField));
    
        AssertJUnit.assertTrue("Add Forced Enrichment Product Page: Sales Org field not displayed",salesOrg.isDisplayed());
        AssertJUnit.assertTrue("Add Forced Enrichment Product Page: Customer Name field not displayed",custName.isDisplayed());
        AssertJUnit.assertTrue("Add Forced Enrichment Product Page: Brand field not displayed",brand.isDisplayed());
        AssertJUnit.assertTrue("Add Forced Enrichment Product Page: Ticket field not displayed",ticket.isDisplayed());
        AssertJUnit.assertTrue("Add Forced Enrichment Product Page: MUM Type field not displayed",mumType.isDisplayed());
    }
    
    public void waitForElement() {
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(mumTypeField));
    }
    
}
