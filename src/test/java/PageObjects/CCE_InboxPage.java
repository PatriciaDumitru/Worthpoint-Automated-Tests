package PageObjects;

import AutomationFramework.CommonTask;
import AutomationFramework.DataItems;
import org.testng.AssertJUnit;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class CCE_InboxPage extends WBA_BasePage {
    
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
    By sendLRM = By.cssSelector("#SampleOrderLineInboxForm > table > tbody > tr:nth-child(2) > td:nth-child(15) > input(2)");
    By sendSAP = By.xpath("//*[@id=\"SampleOrderLineInboxForm\"]/table/tbody/tr[2]/td[16]/input[@value='sap']");
    By saveButton = By.cssSelector("#content > div.actions > ul:nth-child(2) > li:nth-child(1)");
    By cancelButton = By.cssSelector("#content > div.actions > ul:nth-child(2) > li:nth-child(2)");
    By flashMessage = By.id("flashMessage");
    By printButton = By.cssSelector("#content > div.actions > ul:nth-child(1) > li");
    
    public CCE_InboxPage(WebDriver passedDriver) {
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
        WebElement waitForCell = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.visibilityOfElementLocated(orderStatusCell));
        return driver.findElement(orderStatusCell).getText();
    }
    
    public CCE_InboxPage setOrderNo(String item) {
        CommonTask.setSearchField(driver, orderNoField, item);
        return this;
    }
    
    public CCE_InboxPage setFceName(String item) {
        CommonTask.setChoiceField(driver, fceNameField, item);
        return this;
    }
    
    public CCE_InboxPage setTicket(String item) {
        CommonTask.setChoiceField(driver, ticketField, item);
        return this;
    }
    
    public CCE_InboxPage setCustName(String item) {
        CommonTask.setChoiceFieldAlt(driver, custNameField, item);
        By customerNameChoice = By.cssSelector("#s2id_filterSampleOrderCustomerIdName > ul > li.select2-search-choice");
        WebElement wait = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.visibilityOfElementLocated(customerNameChoice));
        return this;
    }
    
    public CCE_InboxPage setShipToCode(String item) {
        CommonTask.setChoiceField(driver, shipToCodeField, item);
        return this;
    }
    
    public CCE_InboxPage setOrderType(String item) {
        CommonTask.setSearchField(driver, orderTypeField, item);
        return this;
    }
    
    public CCE_InboxPage setArticle(String item) {
        CommonTask.setSearchField(driver, articleField, item);
        return this;
    }
    
    public CCE_InboxPage setMumType(String item) {
        switch (item) {
            case "cone": CommonTask.setSearchField(driver, mumField, item);break;
            case "cop": CommonTask.setSearchField(driver, mumField, item);break;
            case "vicone": CommonTask.setSearchField(driver, mumField, item); break;
        }
        return this;
    }
    
    public CCE_InboxPage setCustCode(String item) {
        CommonTask.setChoiceField(driver, custCodeField, item);
        return this;
    }
    
    public CCE_InboxPage setRequester(String item) {
        CommonTask.setChoiceField(driver, requesterField, item);
        return this;
    }
    
    public CCE_InboxPage setShadeCode(String item) {
        CommonTask.setSearchField(driver, shadeCodeField, item);
        return this;
    }
    
    public CCE_InboxPage setShipToName(String item) {
        CommonTask.setChoiceField(driver, shipToNameField, item);
        return this;
    }
    
    public CCE_InboxPage pressListOrders() {
        WebElement waitForClickable = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(orderNoField));
        driver.findElement(orderNoField).submit();
        
        return this;
    }
    
    public CCE_InboxPage pressReset() {
        //Wait for clickable
        WebElement waitForClickable = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(resetButton));
        driver.findElement(resetButton).click();
        
        return this;
    }
    
    public void pressPrint() {
        //Wait for clickable
        WebElement waitForClickable = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(printButton));
        if (DataItems.printingEnabled) {
            driver.findElement(printButton).click();
            System.out.println("Print enabled, print pressed.");
        } else {
            System.out.println("Printing is not enabled, print was not pressed");
        }
        
    }
    
    public void switchTo() {
        WebDriver waitForFrame = new WebDriverWait(driver,DataItems.longWait).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(viewFrame));
    }
    
    public CCE_OrderViewPage pressView() {
        //wait for element to be present
        WebElement waitForClickable = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(viewButton));
        driver.findElement(viewButton).click();
        
        switchTo();
        
        return new CCE_OrderViewPage(driver);
    }
    
    public CCE_InboxPage closeView() {
        switchTo();
        driver.findElement(viewFrame).sendKeys(Keys.ESCAPE);
        
        Alert alert = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.alertIsPresent());
        alert.accept();
        
        return new CCE_InboxPage(driver);
    }
    
    public CCE_InboxPage pressLRM() {
        WebElement waitForClickable = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(sendLRM));
        driver.findElement(sendLRM).click();
        boolean waitForChecked = new WebDriverWait(driver,DataItems.shortWait).until(CommonTask.boxIsChecked(driver.findElement(sendLRM)));
        return this;
    }
    
    public CCE_InboxPage pressSAP() {
        WebElement waitForClickable = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(sendSAP));
        driver.findElement(sendSAP).click();
        boolean waitForChecked = new WebDriverWait(driver,DataItems.shortWait).until(CommonTask.boxIsChecked(driver.findElement(sendSAP)));
        return this;
    }
    
    public CCE_InboxPage pressSave() {
        WebElement waitForClickable = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(saveButton));       
        driver.findElement(saveButton).click();
        
        Alert alert = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.alertIsPresent());
        alert.accept();
        
        System.out.println("Save pressed. Alert received. Accepting alert...");
        
        WebElement waitForMessage = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.visibilityOfElementLocated(flashMessage));
        System.out.println("Alert accepted. Message appeared: ");
        
        String message = getFlashMessage();
        
        if (message.contains("has been updated")) {
            System.out.println("Save successful.");
        } else {
            System.out.println("Save unsuccessful. Unexpected message: "+message);
        }
        
        return this;
    }
    
    public CCE_InboxPage pressCancel() {
        WebElement waitForClickable = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(cancelButton));       
        driver.findElement(cancelButton).click();
        return this;
    }
    
    public void checkFields() {
        //Wait for all to be clickable
        WebElement waitForOrderNo = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(orderNoField));
        WebElement waitForFceName = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(fceNameField));
        WebElement waitForTicket = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(ticketField));
        WebElement waitForCustName = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(custNameField));
        WebElement waitForShipToCode = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(shipToCodeField));
        WebElement waitForOrderFrom = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(orderFromField));
        WebElement waitForOrderTo = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(orderToField));
        WebElement waitForOrderType = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(orderTypeField));
        WebElement waitForArticle = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(articleField));
        WebElement waitForMum = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(mumField));
        WebElement waitForCustCode = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(custCodeField));
        WebElement waitForBrand = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(brandField));
        WebElement waitForHub = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(hubField));
        WebElement waitForShadeCode = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(shadeCodeField));
        WebElement waitForRequester = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(requesterField));
        WebElement waitForShipToName = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(shipToNameField));
        WebElement waitForSapStatus = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(sapStatusField));
        WebElement waitForListButton = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(listOrdersButton));
        WebElement waitForResetButton = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(resetButton));
        WebElement waitForViewButton = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(resetButton));
        WebElement waitForSave = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(saveButton));
        WebElement waitForButton = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(cancelButton));
        WebElement waitForPrin = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(printButton));
        
        //Check all fields are displayed
        AssertJUnit.assertTrue("Inbox page: Order No Field not displayed correctly",getOrderNoField().isDisplayed());
        AssertJUnit.assertTrue("Inbox page: FCE Name Field not displayed correctly",getFceNameField().isDisplayed());
        AssertJUnit.assertTrue("Inbox page: Ticket Field not displayed correctly",getTicketField().isDisplayed());
        AssertJUnit.assertTrue("Inbox page: Customer name Field not displayed correctly",getCustNameField().isDisplayed());
        AssertJUnit.assertTrue("Inbox page: Ship To Code Field not displayed correctly",getShipToCodeField().isDisplayed());
        AssertJUnit.assertTrue("Inbox page: OrderDate From Field not displayed correctly",getOrderFromField().isDisplayed());
        AssertJUnit.assertTrue("Inbox page: OrderDate To Field not displayed correctly",getOrderToField().isDisplayed());
        AssertJUnit.assertTrue("Inbox page: OrderDate To Field not displayed correctly",getOrderToField().isDisplayed());
        AssertJUnit.assertTrue("Inbox page: Order Type Field not displayed correctly",getOrderTypeField().isDisplayed());
        AssertJUnit.assertTrue("Inbox page: MUM Type Field not displayed correctly",getMumField().isDisplayed());
        AssertJUnit.assertTrue("Inbox page: Article Field not displayed correctly",getArticleField().isDisplayed());
        AssertJUnit.assertTrue("Inbox page: Customer Code Field not displayed correctly",getCustCodeField().isDisplayed());
        AssertJUnit.assertTrue("Inbox page: Brand Field not displayed correctly",getBrandField().isDisplayed());
        AssertJUnit.assertTrue("Inbox page: Shade Code field not displayed correctly",getShadeCodeField().isDisplayed());
        AssertJUnit.assertTrue("Inbox page: Requester field not displayed correctly",getRequesterNameField().isDisplayed());
        AssertJUnit.assertTrue("Inbox page: Ship To Name field not displayed correctly",getShipToNameField().isDisplayed());
        AssertJUnit.assertTrue("Inbox page: SAP Status field not displayed correctly",getSAPStatusField().isDisplayed());              
        
    }
    
}
