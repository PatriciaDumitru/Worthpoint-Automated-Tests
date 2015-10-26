
package PageObjects;

import AutomationFramework.CommonTask;
import com.google.common.base.Verify;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class CCE_DNReprintPage extends WBA_BasePage {
    
    //Locators
    By breadcrumbLocator = By.cssSelector("#content > h2");
    By orderNumberLocator = By.id("s2id_filterSampleOrderLineOrderId");
    By shipToPartyNameLocator = By.id("s2id_autogen3");
    By shipToPartyCodeLocator = By.id("s2id_autogen2");
    By requesterNameLocator = By.id("s2id_autogen4");
    By orderDateFromLocator = By.id("filterSampleOrderCreatedFrom");
    By orderDateToLocator = By.id("filterSampleOrderCreatedTo");
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
        return driver.findElement(orderNumberLocator);
    }
    
    public WebElement getShipToPartyNameField() {
        return driver.findElement(shipToPartyNameLocator);
    }
    
    public WebElement getShipToPartyCodeField() {
        return driver.findElement(shipToPartyCodeLocator);
    }
    
    public WebElement getRequesterNameField() {
        return driver.findElement(requesterNameLocator);
    }
    
    public CCE_DNReprintPage setOrderNumber(String orderNumber) {
        CommonTask.setSearchField(driver, orderNumberLocator, orderNumber);
        return this;
    }
    
    public CCE_DNReprintPage setShipToPartyName(String shipToName) {
        CommonTask.setChoiceField(driver,shipToPartyNameLocator,shipToName);
        return this;
    }
    
    public CCE_DNReprintPage setShipToPartyCode(String shipToCode) {
        CommonTask.setChoiceField(driver,shipToPartyCodeLocator,shipToCode);
        return this;
    }
    
    public CCE_DNReprintPage setRequesterName(String requester) {
        CommonTask.setChoiceField(driver,requesterNameLocator,requester);
        return this;
    }
    
    public void pressList() {       
        driver.findElement(formLocator).submit();
        
        //Wait for confirm box to be clickable
        WebElement waitForBox = new WebDriverWait(driver,5).until(ExpectedConditions.elementToBeClickable(confirmButton));
        
    }
    
    public void pressReset() {
        //Wait for button to be clickable
        WebElement waitForButton = new WebDriverWait(driver,5).until(ExpectedConditions.elementToBeClickable(resetButton));
        
        driver.findElement(resetButton).click();
    }
    
    public void checkFields() {
        
        //Wait for all fields to be clickable
        WebElement waitForOrderNo = new WebDriverWait(driver,5).until(ExpectedConditions.elementToBeClickable(orderNumberLocator));
        WebElement waitForShipToCode = new WebDriverWait(driver,5).until(ExpectedConditions.elementToBeClickable(shipToPartyCodeLocator));
        WebElement waitForShipToName = new WebDriverWait(driver,5).until(ExpectedConditions.elementToBeClickable(shipToPartyNameLocator));
        WebElement waitForRequester = new WebDriverWait(driver,5).until(ExpectedConditions.elementToBeClickable(requesterNameLocator));
        WebElement waitForListOrders = new WebDriverWait(driver,5).until(ExpectedConditions.elementToBeClickable(listOrdersButton));
        WebElement waitForReset = new WebDriverWait(driver,5).until(ExpectedConditions.elementToBeClickable(resetButton));       
        
        //Verify elements are displayed
        Verify.verify(driver.findElement(orderNumberLocator).isDisplayed(),"DN Reprint page: Order number field not displayed");
        Verify.verify(driver.findElement(shipToPartyCodeLocator).isDisplayed(),"DN Reprint page: Ship To Party Code field not displayed");
        Verify.verify(driver.findElement(shipToPartyNameLocator).isDisplayed(),"DN Reprint page: Ship To Party Name field not displayed");
        Verify.verify(driver.findElement(requesterNameLocator).isDisplayed(),"DN Reprint page: Requester Name field not displayed");
        Verify.verify(driver.findElement(orderDateFromLocator).isDisplayed(),"DN Reprint page: Order Date From field not displayed");
        Verify.verify(driver.findElement(orderDateToLocator).isDisplayed(),"DN Reprint page: Order Date To field not displayed");
        Verify.verify(driver.findElement(listOrdersButton).isDisplayed(),"DN Reprint page: List Orders Button not displayed");
        Verify.verify(driver.findElement(resetButton).isDisplayed(),"DN Reprint page: Reset Button not displayed");
               
    }
    
    public void pressConfirm() {
        //Wait for checkbox
        WebElement waitForVisible = new WebDriverWait(driver,5).until(ExpectedConditions.visibilityOfElementLocated(confirmButton));
        
        driver.findElement(confirmButton).click();
        
        //Wait for print icon to appear
        WebElement waitForIcon = new WebDriverWait(driver,5).until(ExpectedConditions.visibilityOfElementLocated(printIcon));
        
        //Wait for box to be checked
        Boolean waitForChecked = new WebDriverWait(driver,5).until(CommonTask.boxIsChecked(driver.findElement(confirmButton)));
    }
    
    public CCE_DNPrintPage pressPrint() {
        //Wait for icon
        WebElement waitForIcon = new WebDriverWait(driver,5).until(ExpectedConditions.elementToBeClickable(printIcon));
        
        driver.findElement(printIcon).click();
        
        return new CCE_DNPrintPage(driver);
    }
    
}
