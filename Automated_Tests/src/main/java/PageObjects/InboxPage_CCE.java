
package PageObjects;

import AutomationFramework.CommonTask;
import AutomationFramework.TestSuite;
import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class InboxPage_CCE extends BasePage {
    
    //locators
    By breadcrumb = By.cssSelector("#content > h2");
    By orderNoField = By.id("s2id_filterSampleOrderLineOrderId");
    By fceNameField = By.id("s2id_filterSampleOrderFceId");
    By ticketField = By.id("s2id_filterSampleOrderLineTicketId");
    By custNameField = By.cssSelector("#s2id_filterSampleOrderCustomerIdName > ul > li > input");
    By shipToCodeField = By.id("s2id_filterSampleOrderShipToPartyIdCode");
    By orderFromField = By.id("filterSampleOrderCreatedFrom");
    By orderToField = By.id("filterSampleOrderCreatedTo");
    By orderTypeField = By.id("s2id_filterSampleOrderLineOrderTypeId");
    By articleField = By.id("s2id_filterSampleOrderLineArticleId");
    By mumField = By.id("s2id_filterSampleOrderLineMumTypeId");
    By custCodeField = By.id("s2id_filterSampleOrderCustomerIdCode");
    By hubField = By.id("s2id_filterSampleOrderHubId");
    By brandField = By.id("s2id_filterSampleOrderLineBrandId");
    By shadeCodeField = By.id("s2id_filterSampleOrderLineShadeId");
    By shipToNameField = By.id("s2id_filterSampleOrderShipToPartyIdName");
    By sapStatusField = By.id("s2id_filterSampleOrderLineSapOrderStatusId");
    By requesterField = By.cssSelector("#s2id_filterSampleOrderRequesterId > ul > li > input");
    By viewFrame = By.id("TB_iframeContent");
    By orderStatusCell = By.cssSelector("#SampleOrderLineInboxForm > table > tbody > tr:nth-child(2) > td:nth-child(14)");
 
    By listOrdersButton = By.cssSelector("#FilterInboxForm > div.actions > ul > li:nth-child(1)");
    By resetButton = By.cssSelector("#FilterInboxForm > div.actions > ul > li:nth-child(2)");
    By viewButton = By.cssSelector("#SampleOrderLineInboxForm > table > tbody > tr:nth-child(2) > td:nth-child(17)");
    By sendLRM = By.cssSelector("#SampleOrderLineInboxForm > table > tbody > tr:nth-child(2) > td:nth-child(15)");
    By sendSAP = By.cssSelector("#SampleOrderLineInboxForm > table > tbody > tr:nth-child(2) > td:nth-child(16)");
    By saveButton = By.cssSelector("#content > div.actions > ul:nth-child(2) > li:nth-child(1)");
    By cancelButton = By.cssSelector("#content > div.actions > ul:nth-child(2) > li:nth-child(2)");
    By flashMessage = By.id("flashMessage");
    By printButton = By.cssSelector("#content > div.actions > ul:nth-child(1) > li");
    
    public InboxPage_CCE(WebDriver passedDriver) {
        super(passedDriver);
    }
    
    public WebElement getBreadcrumb() {
        return driver.findElement(breadcrumb);
    }
    
    public WebElement getOrderNoField() {
        return driver.findElement(orderNoField);
    }
    
    public WebElement getFceNameField() {
        return driver.findElement(fceNameField);
    }
    
    public WebElement getTicketField() {
        return driver.findElement(ticketField);
    }
    
    public WebElement getCustNameField() {
        return driver.findElement(custNameField);
    }
    
    public WebElement getShipToCodeField() {
        return driver.findElement(shipToCodeField);
    }
    
    public WebElement getOrderFromField() {
        return driver.findElement(orderFromField);
    }
    
    public WebElement getOrderToField() {
        return driver.findElement(orderToField);
    }
    
    public WebElement getOrderTypeField() {
        return driver.findElement(orderTypeField);
    }
    
    public WebElement getArticleField() {
        return driver.findElement(articleField);
    }
    
    public WebElement getMumField() {
        return driver.findElement(mumField);
    }
    
    public WebElement getCustCodeField() {
        return driver.findElement(custCodeField);
    }
    
    public WebElement getBrandField() {
        return driver.findElement(brandField);
    }
    
    public WebElement getHubField() {
        return driver.findElement(hubField);
    }
    
    public WebElement getShadeCodeField() {
        return driver.findElement(shadeCodeField);
    }
    
    public WebElement getShipToNameField() {
        return driver.findElement(shipToNameField);
    }
    
    public WebElement getSAPStatusField() {
        return driver.findElement(sapStatusField);
    }
    
    public WebElement getRequesterNameField() {
        return driver.findElement(requesterField);
    }
    
    public String getFlashMessage() {
        return driver.findElement(flashMessage).getText();
    }
    
    public String getOrderStatus() {
        WebElement waitForCell = new WebDriverWait(driver,10).until(ExpectedConditions.visibilityOfElementLocated(orderStatusCell));
        return driver.findElement(orderStatusCell).getText();
    }
    
    public InboxPage_CCE setOrderNo(String item) {
        CommonTask.setSearchField(driver, orderNoField, item);
        return this;
    }
    
    public InboxPage_CCE setFceName(String item) {
        CommonTask.setChoiceField(driver, fceNameField, item);
        return this;
    }
    
    public InboxPage_CCE setTicket(String item) {
        CommonTask.setChoiceField(driver, ticketField, item);
        return this;
    }
    
    public InboxPage_CCE setCustName(String item) {
        CommonTask.setChoiceFieldAlt(driver, custNameField, item);
        return this;
    }
    
    public InboxPage_CCE setShipToCode(String item) {
        CommonTask.setChoiceField(driver, shipToCodeField, item);
        return this;
    }
    
    public InboxPage_CCE setOrderType(String item) {
        CommonTask.setSearchField(driver, orderTypeField, item);
        return this;
    }
    
    public InboxPage_CCE setArticle(String item) {
        CommonTask.setSearchField(driver, articleField, item);
        return this;
    }
    
    public InboxPage_CCE setMumType(String item) {
        switch (item) {
            case "cone": CommonTask.setSearchField(driver, mumField, item);break;
            case "cop": CommonTask.setSearchField(driver, mumField, item);break;
            case "vicone": CommonTask.setSearchField(driver, mumField, item); break;
        }
        return this;
    }
    
    public InboxPage_CCE setCustCode(String item) {
        CommonTask.setChoiceField(driver, custCodeField, item);
        return this;
    }
    
    public InboxPage_CCE setRequester(String item) {
        CommonTask.setChoiceField(driver, requesterField, item);
        return this;
    }
    
    public InboxPage_CCE setShadeCode(String item) {
        CommonTask.setSearchField(driver, shadeCodeField, item);
        return this;
    }
    
    public InboxPage_CCE setShipToName(String item) {
        CommonTask.setChoiceField(driver, shipToNameField, item);
        return this;
    }
    
    public InboxPage_CCE pressListOrders() {
        WebElement waitForClickable = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(orderNoField));
        driver.findElement(orderNoField).submit();
        
        return this;
    }
    
    public InboxPage_CCE pressReset() {
        //Wait for clickable
        WebElement waitForClickable = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(resetButton));
        driver.findElement(resetButton).click();
        
        return this;
    }
    
    public void pressPrint() {
        //Wait for clickable
        WebElement waitForClickable = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(printButton));
        if (TestSuite.printingEnabled) {
            driver.findElement(printButton).click();
            System.out.println("Print enabled, print pressed.");
        } else {
            System.out.println("Printing is not enabled, print was not pressed");
        }
        
    }
    
    public void switchTo() {
        WebDriver waitForFrame = new WebDriverWait(driver,10).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(viewFrame));
    }
    
    public OrderViewPage_CCE pressView() {
        //wait for element to be present
        WebElement waitForClickable = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(viewButton));
        driver.findElement(viewButton).click();
        
        switchTo();
        
        return new OrderViewPage_CCE(driver);
    }
    
    public InboxPage_CCE closeView() {
        switchTo();
        driver.findElement(viewFrame).sendKeys(Keys.ESCAPE);
        
        Alert alert = new WebDriverWait(driver,10).until(ExpectedConditions.alertIsPresent());
        alert.accept();
        
        return new InboxPage_CCE(driver);
    }
    
    public InboxPage_CCE pressLRM() {
        WebElement waitForClickable = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(sendLRM));
        driver.findElement(sendLRM).click();
        return this;
    }
    
    public InboxPage_CCE pressSAP() {
        WebElement waitForClickable = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(sendSAP));
        driver.findElement(sendSAP).click();
        return this;
    }
    
    public InboxPage_CCE pressSave() {
        WebElement waitForClickable = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(saveButton));       
        driver.findElement(saveButton).click();
        
        Alert alert = new WebDriverWait(driver,10).until(ExpectedConditions.alertIsPresent());
        alert.accept();
        
        System.out.println("Save pressed. Alert received. Accepting alert...");
        
        WebElement waitForMessage = new WebDriverWait(driver,10).until(ExpectedConditions.visibilityOfElementLocated(flashMessage));
        System.out.println("Alert accepted. Message appeared: ");
        
        String message = getFlashMessage();
        
        if (message.contains("has been updated")) {
            System.out.println("Save successful.");
        } else {
            System.out.println("Save unsuccessful. Unexpected message: "+message);
        }
        
        return this;
    }
    
    public InboxPage_CCE pressCancel() {
        WebElement waitForClickable = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(cancelButton));       
        driver.findElement(cancelButton).click();
        return this;
    }
    
    public void checkFields() {
        //Wait for all to be clickable
        WebElement waitForOrderNo = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(orderNoField));
        WebElement waitForFceName = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(fceNameField));
        WebElement waitForTicket = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(ticketField));
        WebElement waitForCustName = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(custNameField));
        WebElement waitForShipToCode = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(shipToCodeField));
        WebElement waitForOrderFrom = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(orderFromField));
        WebElement waitForOrderTo = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(orderToField));
        WebElement waitForOrderType = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(orderTypeField));
        WebElement waitForArticle = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(articleField));
        WebElement waitForMum = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(mumField));
        WebElement waitForCustCode = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(custCodeField));
        WebElement waitForBrand = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(brandField));
        WebElement waitForHub = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(hubField));
        WebElement waitForShadeCode = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(shadeCodeField));
        WebElement waitForRequester = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(requesterField));
        WebElement waitForShipToName = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(shipToNameField));
        WebElement waitForSapStatus = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(sapStatusField));
        WebElement waitForListButton = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(listOrdersButton));
        WebElement waitForResetButton = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(resetButton));
        WebElement waitForViewButton = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(resetButton));
        WebElement waitForLRM = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(sendLRM));
        WebElement waitForSAP = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(sendSAP));
        WebElement waitForSave = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(saveButton));
        WebElement waitForButton = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(cancelButton));
        WebElement waitForPrin = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(printButton));
        
        //Check all fields are displayed
        Assert.assertTrue("Inbox page: Order No Field not displayed correctly",getOrderNoField().isDisplayed());
        Assert.assertTrue("Inbox page: FCE Name Field not displayed correctly",getFceNameField().isDisplayed());
        Assert.assertTrue("Inbox page: Ticket Field not displayed correctly",getTicketField().isDisplayed());
        Assert.assertTrue("Inbox page: Customer name Field not displayed correctly",getCustNameField().isDisplayed());
        Assert.assertTrue("Inbox page: Ship To Code Field not displayed correctly",getShipToCodeField().isDisplayed());
        Assert.assertTrue("Inbox page: OrderDate From Field not displayed correctly",getOrderFromField().isDisplayed());
        Assert.assertTrue("Inbox page: OrderDate To Field not displayed correctly",getOrderToField().isDisplayed());
        Assert.assertTrue("Inbox page: OrderDate To Field not displayed correctly",getOrderToField().isDisplayed());
        Assert.assertTrue("Inbox page: Order Type Field not displayed correctly",getOrderTypeField().isDisplayed());
        Assert.assertTrue("Inbox page: MUM Type Field not displayed correctly",getMumField().isDisplayed());
        Assert.assertTrue("Inbox page: Article Field not displayed correctly",getArticleField().isDisplayed());
        Assert.assertTrue("Inbox page: Customer Code Field not displayed correctly",getCustCodeField().isDisplayed());
        Assert.assertTrue("Inbox page: Brand Field not displayed correctly",getBrandField().isDisplayed());
        Assert.assertTrue("Inbox page: Shade Code field not displayed correctly",getShadeCodeField().isDisplayed());
        Assert.assertTrue("Inbox page: Requester field not displayed correctly",getRequesterNameField().isDisplayed());
        Assert.assertTrue("Inbox page: Ship To Name field not displayed correctly",getShipToNameField().isDisplayed());
        Assert.assertTrue("Inbox page: SAP Status field not displayed correctly",getSAPStatusField().isDisplayed());              
        
    }
    
}
