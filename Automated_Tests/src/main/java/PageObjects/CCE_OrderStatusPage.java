
package PageObjects;

import AutomationFramework.CommonTask;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class CCE_OrderStatusPage extends WBA_BasePage {
    
    //Locators
    By orderNoField = By.id("s2id_filterSampleOrderLineOrderId");
    By custNameField = By.id("s2id_filterSampleOrderCustomerId");
    By orderStageField = By.id("s2id_filterSampleOrderLineStageId");
    By requestTypeField = By.id("s2id_filterSampleOrderLineRequestTypeId");
    By busPrincipalField = By.id("s2id_filterSampleOrderBusinessPrincipalId");
    By hubField = By.id("s2id_filterSampleOrderHubId");
    By requesterField = By.id("s2id_filterSampleOrderRequesterId");
    By scenarioField = By.id("s2id_filterSampleOrderLineScenarioId");
    By orderDateFromField = By.id("filterSampleOrderCreatedFrom");
    By orderDateToField = By.id("filterSampleOrderCreatedTo");
    By fceNameField = By.id("s2id_filterSampleOrderFceId");
    By fabRefField = By.id("filterSampleOrderLineFabricReferenceColourName");
    By shadeCodeField = By.id("s2id_filterSampleOrderLineShadeId");
    By currentSosField = By.id("s2id_filterSampleOrderLineSosId");
    By custRefField = By.id("filterSampleOrderFreetext");
    By listOrdersButton = By.cssSelector("#FilterOutstandingForm > div.actions > ul > li:nth-child(1)");
    By resetButton = By.cssSelector("#FilterOutstandingForm > div.actions > ul > li:nth-child(2) > a");
    By filterForm = By.id("FilterOutstandingForm");
    By exportButton = By.cssSelector("#content > div.actions > ul > li");
    By flashMessage = By.id("flashMessage");
    By contentFrame = By.id("content");
    
    public CCE_OrderStatusPage(WebDriver passedDriver) {
        super(passedDriver);
    }
    
    public WebElement getOrderNoField() {
        return driver.findElement(orderNoField);
    }
    
    public WebElement getCustNameField() {
        return driver.findElement(custNameField);
    }
    
    public WebElement getOrderStageField() {
        return driver.findElement(orderStageField);
    }
    
    public WebElement getRequestTypeField() {
        return driver.findElement(requestTypeField);
    }
    
    public WebElement getBusPrincipalField() {
        return driver.findElement(busPrincipalField);
    }
    
    public WebElement getHubField() {
        return driver.findElement(hubField);
    }
    
    public WebElement getRequesterField() {
        return driver.findElement(requesterField);
    }
    
    public WebElement getScenarioField() {
        return driver.findElement(scenarioField);
    }
    
    public WebElement getOrderFromField() {
        return driver.findElement(orderDateFromField);
    }
    
    public WebElement getOrderToField() {
        return driver.findElement(orderDateToField);
    }
    
    public WebElement getFceNameField() {
        return driver.findElement(fceNameField);
    }
    
    public WebElement getFabRefField() {
        return driver.findElement(fabRefField);
    }
    
    public WebElement getShadeCodeField() {
        return driver.findElement(shadeCodeField);
    }
    
    public WebElement getCurrentSosField() {
        return driver.findElement(currentSosField);
    }
    
    public WebElement getCustRefField() {
        return driver.findElement(custRefField);
    }
    
    public CCE_OrderStatusPage setOrderNo(String item) {
        CommonTask.setSearchField(driver, orderNoField, item);
        
        return this;
    } 
    
    public CCE_OrderStatusPage setCustName(String item) {
        CommonTask.setSearchField(driver, custNameField, item);
        
        return this;
    } 
    
    public CCE_OrderStatusPage setOrderStage(String item) {
        CommonTask.setSearchField(driver, orderStageField, item);
        
        return this;
    } 
    
    public CCE_OrderStatusPage setShadeCode(String item) {
        CommonTask.setSearchField(driver, shadeCodeField, item);
        
        return this;
    } 
    
    public void checkFields() {
        //Wait for all elements to be clickable
        WebElement waitForOrderNo = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(orderNoField));
        WebElement waitForCustName = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(custNameField));
        WebElement waitForOrderStage = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(orderStageField));
        WebElement waitForRequestType = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(requestTypeField));
        WebElement waitForBusPrin = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(busPrincipalField));
        WebElement waitForHub = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(hubField));
        WebElement waitForRequestor = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(requesterField));
        WebElement waitForScenario = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(scenarioField));
        WebElement waitForOrderFrom = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(orderDateFromField));
        WebElement waitForOrderTo = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(orderDateToField));
        WebElement waitForFce = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(fceNameField));
        WebElement waitForFabRef = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(fabRefField));
        WebElement waitForShadeCode = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(shadeCodeField));
        WebElement waitForCurrentSOS = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(currentSosField));
        WebElement waitForCustRef = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(custRefField));
        
        //Assert all elements are displayed
        Assert.assertTrue("Order Status Page: Order No Field not displayed correctly",getOrderNoField().isDisplayed());
        Assert.assertTrue("Order Status: Order No Field not displayed correctly",getCustNameField().isDisplayed());
        Assert.assertTrue("Order Status: Order Stage Field not displayed correctly",getOrderStageField().isDisplayed());
        Assert.assertTrue("Order Status: Request Type Field not displayed correctly",getRequestTypeField().isDisplayed());
        Assert.assertTrue("Order Status: Business Principal Field not displayed correctly",getBusPrincipalField().isDisplayed());
        Assert.assertTrue("Order Status: Hub Field not displayed correctly",getHubField().isDisplayed());
        Assert.assertTrue("Order Status: Requester Field not displayed correctly",getRequesterField().isDisplayed());
        Assert.assertTrue("Order Status: Scenario Field not displayed correctly",getScenarioField().isDisplayed());
        Assert.assertTrue("Order Status: Order From Date Field not displayed correctly",getOrderFromField().isDisplayed());
        Assert.assertTrue("Order Status: Order To Date Field not displayed correctly",getOrderToField().isDisplayed());
        Assert.assertTrue("Order Status: FCE Name Field not displayed correctly",getFceNameField().isDisplayed());
        Assert.assertTrue("Order Status: Fabric Reference Field not displayed correctly",getFabRefField().isDisplayed());
        Assert.assertTrue("Order Status: Shade Code Field not displayed correctly",getShadeCodeField().isDisplayed());
        Assert.assertTrue("Order Status: Current SOS Field not displayed correctly",getCurrentSosField().isDisplayed());    
    }
    
    public CCE_OrderStatusPage pressListOrders() {
        //Wait for element to be clickable
        WebElement waitForClickable = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(listOrdersButton));
        
        driver.findElement(filterForm).submit();
        
        return this;
    }
    
    public CCE_OrderStatusPage pressReset() {
        WebElement waitForClickable = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(resetButton));
        driver.findElement(resetButton).click();
        return this;
    }
    
    public Ecomm_ExportDownloadPage pressExport() {
        WebElement waitForClickable = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(exportButton));
        
        Actions action = new Actions(driver);
        action.click(driver.findElement(exportButton)).build().perform();
        
        return new Ecomm_ExportDownloadPage(driver);
    }
    
    //Line numbers start from 2
    public CCE_OrderViewPage pressView(int lineNumber) {
        //Form locator
        By viewButtonLocator = By.cssSelector("#content > div.flexi-grid > table > tbody > tr:nth-child("+lineNumber+") > td:nth-child(16) > a");
        //Wait for button to be clickable
        WebElement waitForClickable = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(viewButtonLocator));
        //Click button
        driver.findElement(viewButtonLocator).click();
        
        return new CCE_OrderViewPage(driver);
    }
    
    
    public CCE_OrderStatusPage waitForMessage() {
        WebElement waitForVisibility = new WebDriverWait(driver,10).until(ExpectedConditions.visibilityOfElementLocated(flashMessage));
        return this;
    }
    
    public String getOrderStage(String orderNo) {
        boolean found = false;
        int i = 1;
        
        while (!found) {
            By orderNoLocator = By.cssSelector("#content > div.flexi-grid > table > tbody > tr:nth-child("+(i+1)+") > td:nth-child(5)");
            WebElement waitForCell = new WebDriverWait(driver,10).until(ExpectedConditions.presenceOfElementLocated(orderNoLocator));
            
            if (driver.findElement(orderNoLocator).getText().equals(orderNo)) {
                found = true;
            } else {
                i++;
            }
        } 
        
        By orderStageLocator = By.cssSelector("#content > div.flexi-grid > table > tbody > tr:nth-child("+(i+1)+") > td:nth-child(12)");
        return driver.findElement(orderStageLocator).getText();
        
        
    }
    
    
}