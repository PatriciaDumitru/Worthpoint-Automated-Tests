/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PageObjects;

import AutomationFramework.CommonTask;
import AutomationFramework.DataItems;
import AutomationFramework.Wait;
import java.io.IOException;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.AssertJUnit;

/**
 *
 * @author ukspjsykes
 */
public class CCE_EnrichOrderPage extends WBA_BasePage {
    
    By businessPrincipalField = By.id("SampleOrderBusinessPrincipalId");
    By articleField = By.id("s2id_SampleOrderLine0ArticleId");
    By brandField = By.id("temp0BrandName");
    By ticketField = By.id("temp0TicketName");
    By shadeCodeField = By.id("s2id_SampleOrderLine0ShadeId");
    By copButton = By.id("SampleOrderLine0MumTypeId10");
    By coneButton = By.id("SampleOrderLine0MumTypeId20");
    By viconeButton = By.id("SampleOrderLine0MumTypeId30");
    By colMatButton = By.id("SampleOrderLine0RequestTypeId1");
    By sewingButton = By.id("SampleOrderLine0RequestTypeId2");
    By purposeTypeField = By.id("SampleOrderLine0PurposeTypeId");
    By customerRefField = By.id("SampleOrderLine0CustomerReference");
    By quantityField = By.id("SampleOrderLine0OrderedQuantity");
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
        return driver.findElement(articleField);
    }
    
    public WebElement getBrandField() {
        return driver.findElement(brandField);
    }
    
    public WebElement getTicketField() {
        return driver.findElement(ticketField);
    }
    
    public WebElement getShadeCodeField() {
        return driver.findElement(shadeCodeField);
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
        return driver.findElement(purposeTypeField);
    }
    
    public WebElement getCustomerRefField() {
        return driver.findElement(customerRefField);
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
        return driver.findElement(quantityField);
    }
    
    public CCE_EnrichOrderPage setCustomerRef() throws IOException {
        WebElement field = Wait.clickable(driver,customerRefField);
        field.clear();
        
        String PO = CommonTask.generatePO("enrich");
        DataItems.lastUsedPO = PO;
        CommonTask.setInputField(driver, customerRefField, PO);
        return new CCE_EnrichOrderPage(driver);
    }
    
    public CCE_EnrichOrderPage pressHub() {
        WebElement hub = Wait.clickable(driver,hubButton);
        hub.click();
        return new CCE_EnrichOrderPage(driver);
    }
    
    public CCE_EnrichOrderPage pressLab() {
        WebElement lab = Wait.clickable(driver,labButton);
        lab.click();
        return new CCE_EnrichOrderPage(driver);
    }
    
    public CCE_EnrichOrderPage pressWHS() {
        WebElement whs = Wait.clickable(driver,whsButton);
        whs.click();
        return new CCE_EnrichOrderPage(driver);
    }
    
    public CCE_EnrichOrderPage pressVicone() {
        WebElement vicone = Wait.clickable(driver,viconeButton);
        vicone.click();
        return new CCE_EnrichOrderPage(driver);
    }
    
    public CCE_ManualEnrichPage pressEnrich() {
        WebElement enrich = Wait.clickable(driver,enrichButton);
        enrich.click();
        
        Alert alert = Wait.alert(driver);
        alert.accept();
        
        return new CCE_ManualEnrichPage(driver);
    }
    
    public CCE_ManualEnrichPage pressEnrichAll() {
        WebElement enrichAll = Wait.clickable(driver,enrichAllButton);
        enrichAll.click();
        
        Alert alert = Wait.alert(driver);
        alert.accept();
        
        return new CCE_ManualEnrichPage(driver);
    }
    
    public CCE_EnrichOrderPage pressCancel() {
        WebElement cancel = Wait.clickable(driver,cancelButton);
        cancel.click();
        
        return new CCE_EnrichOrderPage(driver);
    }
    
    public void checkFields() {
        //Wait for all elements to be clickable
        WebElement article = Wait.clickable(driver,articleField);
        WebElement brand = Wait.clickable(driver,brandField);
        WebElement ticket = Wait.clickable(driver,ticketField);
        WebElement shadeCode = Wait.clickable(driver,shadeCodeField);
        WebElement cop = Wait.clickable(driver,copButton);
        WebElement cone = Wait.clickable(driver,coneButton);
        WebElement vicone = Wait.clickable(driver,viconeButton);
        WebElement colourMatching = Wait.clickable(driver,colMatButton);
        WebElement sewing = Wait.clickable(driver,sewingButton);
        WebElement purpType = Wait.clickable(driver,purposeTypeField);
        WebElement customerRef = Wait.clickable(driver,customerRefField);
        WebElement quantity = Wait.clickable(driver,quantityField);
        WebElement hub = Wait.clickable(driver,hubButton);
        WebElement lab = Wait.clickable(driver,labButton);
        WebElement whs = Wait.clickable(driver,whsButton);
        WebElement enrich = Wait.clickable(driver,enrichButton);
        WebElement enrichAll = Wait.clickable(driver,enrichAllButton);
        WebElement cancel = Wait.clickable(driver,cancelButton);
    
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
        WebElement wait = Wait.clickable(driver,businessPrincipalField);
    }
}
