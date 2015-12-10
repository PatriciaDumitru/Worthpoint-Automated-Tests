
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

public class Mst_AddForcedEnrichmentPage extends WBA_BasePage {
    
    By salesOrgField = By.id("ForcedEnrichmentProductSalesOrgId");
    By custNameField = By.id("s2id_ForcedEnrichmentProductCustomerId");
    By brandField = By.id("ForcedEnrichmentProductBrandId");
    By ticketField = By.id("ForcedEnrichmentProductTicketId");
    By mumTypeField = By.id("ForcedEnrichmentProductMumTypeId");
    By saveButton = By.cssSelector("#ForcedEnrichmentProductAddForm > div.actions > ul > li:nth-child(1) > input[type=\"submit\"]");
    By cancelButton = By.cssSelector("#ForcedEnrichmentProductAddForm > div.actions > ul > li:nth-child(2) > a");
    
    public Mst_AddForcedEnrichmentPage(WebDriver driver) {
        super(driver);
    }
    
    public WebElement getBreadcrumb() {
        return Wait.visible(driver,DataItems.breadcrumbLocator);
    }
    
    public Mst_AddForcedEnrichmentPage setSalesOrg(String item) throws InterruptedException {
        CommonTask.setDropDownField(driver, salesOrgField, item);
        return new Mst_AddForcedEnrichmentPage(driver);
    }
 
    public Mst_AddForcedEnrichmentPage setCustomerName(String item) throws InterruptedException {
        CommonTask.setSearchField(driver, custNameField, item);
        return new Mst_AddForcedEnrichmentPage(driver);
    }
    
    public Mst_AddForcedEnrichmentPage setBrand(String item) throws InterruptedException {
        CommonTask.setDropDownField(driver, brandField, item);
        return new Mst_AddForcedEnrichmentPage(driver);
    }
    
    public Mst_AddForcedEnrichmentPage setTicket(String item) throws InterruptedException {
        CommonTask.setDropDownField(driver, ticketField, item);
        return new Mst_AddForcedEnrichmentPage(driver);
    }
    
    public Mst_AddForcedEnrichmentPage setMUMType(String item) throws InterruptedException {
        CommonTask.setDropDownField(driver, mumTypeField, item);
        return new Mst_AddForcedEnrichmentPage(driver);
    }
    
    public Mst_ForcedEnrichmentPage pressSave() {
        WebElement element = Wait.clickable(driver,saveButton);
        element.click();
        
        return new Mst_ForcedEnrichmentPage(driver);
    }
    
    public void checkFields() {
        WebElement salesOrg = Wait.clickable(driver,salesOrgField);
        WebElement custName = Wait.clickable(driver,custNameField);
        WebElement brand = Wait.clickable(driver,brandField);
        WebElement ticket = Wait.clickable(driver,ticketField);
        WebElement mumType = Wait.clickable(driver,mumTypeField);
    
        AssertJUnit.assertTrue("Add Forced Enrichment Product Page: Sales Org field not displayed",salesOrg.isDisplayed());
        AssertJUnit.assertTrue("Add Forced Enrichment Product Page: Customer Name field not displayed",custName.isDisplayed());
        AssertJUnit.assertTrue("Add Forced Enrichment Product Page: Brand field not displayed",brand.isDisplayed());
        AssertJUnit.assertTrue("Add Forced Enrichment Product Page: Ticket field not displayed",ticket.isDisplayed());
        AssertJUnit.assertTrue("Add Forced Enrichment Product Page: MUM Type field not displayed",mumType.isDisplayed());
    }
    
    public void waitForElement() {
        WebElement element = Wait.clickable(driver,mumTypeField);
    }
    
}
