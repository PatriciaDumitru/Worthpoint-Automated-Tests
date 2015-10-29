/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PageObjects;

import AutomationFramework.CommonTask;
import AutomationFramework.DataItems;
import java.io.IOException;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.AssertJUnit;

/**
 *
 * @author ukspjsykes
 */
public class CCE_EnrichOrderPage extends WBA_BasePage {
    
    By articleLocator = By.id("s2id_SampleOrderLine0ArticleId");
    By brandLocator = By.id("temp0BrandName");
    By ticketLocator = By.id("temp0TicketName");
    By shadeCodeLocator = By.id("s2id_SampleOrderLine0ShadeId");
    By copButton = By.id("SampleOrderLine0MumTypeId10");
    By coneButton = By.id("SampleOrderLine0MumTypeId20");
    By viconeButton = By.id("SampleOrderLine0MumTypeId30");
    By colMatButton = By.id("SampleOrderLine0RequestTypeId1");
    By sewingButton = By.id("SampleOrderLine0RequestTypeId2");
    By purposeTypeLocator = By.id("SampleOrderLine0PurposeTypeId");
    By customerRefLocator = By.id("SampleOrderLine0CustomerReference");
    By quantityLocator = By.id("SampleOrderLine0OrderedQuantity");
    By hubButton = By.id("SampleOrderLine0SosId30");
    By labButton = By.id("SampleOrderLine0SosId50");
    By whsButton = By.id("SampleOrderLine0SosId40");
    By enrichButton = By.cssSelector("#tabs-0 > div > ul > li > input[type=\"submit\"]");
    By enrichAllButton = By.id("pending");
    By cancelButton = By.id("#SampleOrderEnrichForm > div:nth-child(4) > div.actions > ul > li:nth-child(2) > a");
    
    public CCE_EnrichOrderPage(WebDriver driver) {
        super(driver);
    }
    
    public WebElement getBreadcrumb() {
        return driver.findElement(DataItems.breadcrumbLocator);
    }
    
    public WebElement getArticleField() {
        return driver.findElement(articleLocator);
    }
    
    public WebElement getBrandField() {
        return driver.findElement(brandLocator);
    }
    
    public WebElement getTicketField() {
        return driver.findElement(ticketLocator);
    }
    
    public WebElement getShadeCodeField() {
        return driver.findElement(shadeCodeLocator);
    }
    
    public WebElement getCopButton() {
        return driver.findElement(copButton);
    }
    
    public WebElement getConeButton() {
        return driver.findElement(coneButton);
    }
    
    public WebElement getViconeButton() {
        return driver.findElement(viconeButton);
    }
    
    public WebElement getColMatButton() {
        return driver.findElement(colMatButton);
    }
    
    public WebElement getSewingButton() {
        return driver.findElement(sewingButton);
    }
    
    public WebElement getPurposeTypeField() {
        return driver.findElement(purposeTypeLocator);
    }
    
    public WebElement getCustomerRefField() {
        return driver.findElement(customerRefLocator);
    }
    
    public WebElement getHubButton() {
        return driver.findElement(hubButton);
    }
    
    public WebElement getLabButton() {
        return driver.findElement(labButton);
    }
    
    public WebElement getWHSButton() {
        return driver.findElement(whsButton);
    }
    
    public WebElement getEnrichButton() {
        return driver.findElement(enrichButton);
    }
    
    public WebElement getEnrichAllButton() {
        return driver.findElement(enrichAllButton);
    }
    
    public WebElement getCancelButton() {
        return driver.findElement(cancelButton);
    }
    
    public WebElement getQuantityField() {
        return driver.findElement(quantityLocator);
    }
    
    public CCE_EnrichOrderPage setCustomerRef() throws IOException {
        String PO = CommonTask.generatePO("noncontract");
        DataItems.lastUsedPO = PO;
        CommonTask.setInputField(driver, customerRefLocator, PO);
        return new CCE_EnrichOrderPage(driver);
    }
    
    public CCE_EnrichOrderPage pressHub() {
        WebElement wait = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(hubButton));
        driver.findElement(hubButton).click();
        return new CCE_EnrichOrderPage(driver);
    }
    
    public CCE_EnrichOrderPage pressLab() {
        WebElement wait = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(labButton));
        driver.findElement(labButton).click();
        return new CCE_EnrichOrderPage(driver);
    }
    
    public CCE_EnrichOrderPage pressWHS() {
        WebElement wait = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(whsButton));
        driver.findElement(whsButton).click();
        return new CCE_EnrichOrderPage(driver);
    }
    
    public CCE_EnrichOrderPage pressVicone() {
        WebElement wait = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(viconeButton));
        driver.findElement(viconeButton);
        return new CCE_EnrichOrderPage(driver);
    }
    
    public CCE_ManualEnrichPage pressEnrich() {
        WebElement wait = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(enrichButton));
        driver.findElement(enrichButton).click();
        
        Alert alert = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.alertIsPresent());
        alert.accept();
        
        return new CCE_ManualEnrichPage(driver);
    }
    
    public CCE_ManualEnrichPage pressEnrichAll() {
        WebElement wait = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(enrichAllButton));
        driver.findElement(enrichAllButton).click();
        
        Alert alert = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.alertIsPresent());
        alert.accept();
        
        return new CCE_ManualEnrichPage(driver);
    }
    
    public CCE_EnrichOrderPage pressCancel() {
        WebElement wait = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(cancelButton));
        driver.findElement(cancelButton).click();
        
        return new CCE_EnrichOrderPage(driver);
    }
    
    public void checkFields() {
        //Wait for all elements to be clickable
        WebElement article = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(articleLocator));
        WebElement brand = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(brandLocator));
        WebElement ticket = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(ticketLocator));
        WebElement shadeCode = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(shadeCodeLocator));
        WebElement cop = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(copButton));
        WebElement cone = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(coneButton));
        WebElement vicone = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(viconeButton));
        WebElement colourMatching = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(colMatButton));
        WebElement sewing = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(sewingButton));
        WebElement purpType = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(purposeTypeLocator));
        WebElement customerRef = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(customerRefLocator));
        WebElement quantity = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(quantityLocator));
        WebElement hub = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(hubButton));
        WebElement lab = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(labButton));
        WebElement whs = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(whsButton));
        WebElement enrich = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(enrichButton));
        WebElement enrichAll = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(enrichAllButton));
        WebElement cancel = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(cancelButton));
    
        //Assert all elements are displayd
        AssertJUnit.assertTrue("Enrich Order Page: Article field not displayed correctly",getArticleField().isDisplayed());
        AssertJUnit.assertTrue("Enrich Order Page: Brand field not displayed correctly",getBrandField().isDisplayed());
        AssertJUnit.assertTrue("Enrich Order Page: Shade Code field not displayed correctly",getShadeCodeField().isDisplayed());
        AssertJUnit.assertTrue("Enrich Order Page: Ticket field not displayed correctly",getTicketField().isDisplayed());
        AssertJUnit.assertTrue("Enrich Order Page: Cop button not displayed correctly",getCopButton().isDisplayed());
        AssertJUnit.assertTrue("Enrich Order Page: Cone button not displayed correctly",getConeButton().isDisplayed());
        AssertJUnit.assertTrue("Enrich Order Page: Vicone button not displayed correctly",getViconeButton().isDisplayed());
        AssertJUnit.assertTrue("Enrich Order Page: Colour Matching button not displayed correctly",getColMatButton().isDisplayed());
        AssertJUnit.assertTrue("Enrich Order Page: Sewing button not displayed correctly",getSewingButton().isDisplayed());
        AssertJUnit.assertTrue("Enrich Order Page: Purpose Type field not displayed correctly",getPurposeTypeField().isDisplayed());
        AssertJUnit.assertTrue("Enrich Order Page: Customer Ref field not displayed correctly",getCustomerRefField().isDisplayed());
        AssertJUnit.assertTrue("Enrich Order Page: Quantity field not displayed correctly",getQuantityField().isDisplayed());
        AssertJUnit.assertTrue("Enrich Order Page: Hub SOS Button not displayed correctly",getHubButton().isDisplayed());
        AssertJUnit.assertTrue("Enrich Order Page: Warehouse SOS Button not displayed correctly",getWHSButton().isDisplayed());
    }
    
    public void waitForElement() {
        WebElement wait = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(articleLocator));
    }
}
