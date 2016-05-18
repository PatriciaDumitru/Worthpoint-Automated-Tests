
package PageObjects;

import AutomationFramework.CommonTask;
import AutomationFramework.Wait;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;


public class CCE_DNReprintPage extends WBA_BasePage {
    
    //Locators
    By breadcrumbLocator = By.cssSelector("#content > h2");
    By orderNumberField = By.id("s2id_filterSampleOrderLineOrderId");
    By shipToPartyNameField = By.id("s2id_autogen3");
    By shipToPartyCodeField = By.id("s2id_autogen2");
    By requesterNameField = By.id("s2id_autogen4");
    By orderDateFromField = By.id("filterSampleOrderCreatedFrom");
    By orderDateToField = By.id("filterSampleOrderCreatedTo");
    By listOrdersButton = By.cssSelector("#FilterDeliveryNotesForm > div.actions > ul > li:nth-child(1)");
    By resetButton = By.cssSelector("#FilterDeliveryNotesForm > div.actions > ul > li:nth-child(2) > a");
    By confirmButton = By.xpath("//*[@id=\"content\"]/div[2]/table/tbody/tr[2]/td[13]/input[@value='1']");
    By printIcon = By.cssSelector("#content > div.flexi-grid > table > tbody > tr:nth-child(2) > td:nth-child(5) > a > img");
    By formLocator = By.id("FilterDeliveryNotesForm");
    
    public CCE_DNReprintPage(WebDriver passedDriver) {
        super(passedDriver);
    }
    
    public WebElement getBreadcrumb() {
        return driver.findElement(breadcrumbLocator);
    }
    
    public WebElement getOrderNumberField() {
        return driver.findElement(orderNumberField);
    }
    
    public WebElement getShipToPartyNameField() {
        return driver.findElement(shipToPartyNameField);
    }
    
    public WebElement getShipToPartyCodeField() {
        return driver.findElement(shipToPartyCodeField);
    }
    
    public WebElement getRequesterNameField() {
        return driver.findElement(requesterNameField);
    }
    
    public CCE_DNReprintPage setOrderNumber(String orderNumber) {
        CommonTask.setSearchField(driver, orderNumberField, orderNumber);
        return this;
    }
    
    public CCE_DNReprintPage setShipToPartyName(String shipToName) {
        CommonTask.setChoiceField(driver,shipToPartyNameField,shipToName);
        return this;
    }
    
    public CCE_DNReprintPage setShipToPartyCode(String shipToCode) {
        CommonTask.setChoiceField(driver,shipToPartyCodeField,shipToCode);
        return this;
    }
    
    public CCE_DNReprintPage setRequesterName(String requester) {
        CommonTask.setChoiceField(driver,requesterNameField,requester);
        return this;
    }
    
    public void pressList() {       
        //Submit form to list results
        driver.findElement(formLocator).submit();
        
        //Wait for confirm box to appear before continuing
        WebElement box = Wait.clickable(driver,confirmButton);  
    }
    
    public void pressReset() {
        //Wait for button to be clickable
        WebElement button = Wait.clickable(driver,resetButton);
        
        button.click();
    }
    
    public void checkFields() {
        
        //Wait for all fields to be clickable
        WebElement orderNo = Wait.clickable(driver,orderNumberField);
        WebElement shipToPartyCode = Wait.clickable(driver,shipToPartyCodeField); 
        WebElement shipToPartyName = Wait.clickable(driver,shipToPartyNameField);
        WebElement requester = Wait.clickable(driver,requesterNameField);
        WebElement listOrders = Wait.clickable(driver,listOrdersButton);
        WebElement reset = Wait.clickable(driver,resetButton); 
        WebElement dateFrom = Wait.clickable(driver,orderDateFromField);
        WebElement dateTo = Wait.clickable(driver,orderDateToField);
        
        //Assert elements are displayed
        Assert.assertTrue(orderNo.isDisplayed(),"DN Reprint page: Order number field not displayed");
        Assert.assertTrue(shipToPartyCode.isDisplayed(),"DN Reprint page: Ship To Party Code field not displayed");
        Assert.assertTrue(shipToPartyName.isDisplayed(),"DN Reprint page: Ship To Party Name field not displayed");
        Assert.assertTrue(requester.isDisplayed(),"DN Reprint page: Requester Name field not displayed");
        Assert.assertTrue(dateFrom.isDisplayed(),"DN Reprint page: Order Date From field not displayed");
        Assert.assertTrue(dateTo.isDisplayed(),"DN Reprint page: Order Date To field not displayed");
        Assert.assertTrue(listOrders.isDisplayed(),"DN Reprint page: List Orders Button not displayed");
        Assert.assertTrue(reset.isDisplayed(),"DN Reprint page: Reset Button not displayed");
               
    }
    
    public void pressConfirm() {
        //Wait for checkbox
        WebElement confirm = Wait.visible(driver,confirmButton);
        
        confirm.click();
        
        //Wait for print icon to appear
        WebElement icon = Wait.visible(driver, printIcon);
        
        //Wait for box to be checked
        Boolean checked = Wait.checked(driver, confirmButton);
    }
    
    public CCE_DNPrintPage pressPrint() {
        //Wait for icon
        WebElement icon = Wait.clickable(driver,printIcon);
        
        icon.click();
        
        return new CCE_DNPrintPage(driver);
    }
    
}
