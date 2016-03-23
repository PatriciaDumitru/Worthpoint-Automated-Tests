package PageObjects;

import AutomationFramework.CommonTask;
import AutomationFramework.DataItems;
import AutomationFramework.Wait;
import static PageObjects.WBA_BasePage.driver;
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
    By orderStatusCell = By.xpath(".//*[@id='SampleOrderLineInboxForm']/table/tbody/tr[2]/td[14]");
 
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
    
    public String getFlashMessage() {
        return driver.findElement(flashMessage).getText();
    }
    
    public String getOrderStatus() {
        //WebElement cell = orderStatusCell(driver);
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
        WebElement field = Wait.visible(driver,customerNameChoice);
        
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
        WebElement listOrders = Wait.clickable(driver,orderNoField);
        listOrders.submit();
        
        return this;
    }
    
    public CCE_InboxPage pressReset() {
        //Wait for clickable
        WebElement reset = Wait.clickable(driver,resetButton);
        reset.click();
        
        return this;
    }
    
    public void pressPrint() {
        //Wait for clickable
        WebElement print = Wait.clickable(driver,printButton);
        if (DataItems.printingEnabled) {
            print.click();
            System.out.println("Print enabled, print pressed.");
        } else {
            System.out.println("Printing is not enabled, print was not pressed");
        }
        
    }
    
    public void switchTo() {
        WebDriver frame = Wait.frame(driver,viewFrame);
    }
    
    public CCE_OrderViewPage pressView() {
        //wait for element to be present
        WebElement view = Wait.clickable(driver,viewButton);
        view.click();
        
        switchTo();
        
        return new CCE_OrderViewPage(driver);
    }
    
    public CCE_InboxPage closeView() {
        switchTo();
        driver.findElement(viewFrame).sendKeys(Keys.ESCAPE);
        
        Alert alert = Wait.alert(driver);
        alert.accept();
        
        return new CCE_InboxPage(driver);
    }
    
    public CCE_InboxPage pressLRM() {
        CommonTask.setCheckBox(driver,sendLRM);
        return this;
    }
    
    public CCE_InboxPage pressSAP() {
        CommonTask.setCheckBox(driver,sendSAP);
        return this;
    }
    
    public CCE_InboxPage pressSave() {
        WebElement save = Wait.clickable(driver,saveButton);       
        save.click();
        
        Alert alert = Wait.alert(driver);
        alert.accept();
        
        System.out.println("Save pressed. Alert received. Accepting alert...");
        
        WebElement messageField = Wait.visible(driver,flashMessage);
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
        WebElement cancel = Wait.clickable(driver,cancelButton);       
        cancel.click();
        
        return this;
    }
    
    public void checkFields() {
        //Wait for all to be clickable
        WebElement orderNo = Wait.clickable(driver,orderNoField);
        WebElement fceName = Wait.clickable(driver,fceNameField);
        WebElement ticket = Wait.clickable(driver,ticketField);
        WebElement custName = Wait.clickable(driver,custNameField);
        WebElement shipToCode = Wait.clickable(driver,shipToCodeField);
        WebElement orderFrom = Wait.clickable(driver,orderFromField);
        WebElement orderTo = Wait.clickable(driver,orderToField);
        WebElement orderType = Wait.clickable(driver,orderTypeField);
        WebElement article = Wait.clickable(driver,articleField);
        WebElement mum = Wait.clickable(driver,mumField);
        WebElement custCode = Wait.clickable(driver,custCodeField);
        WebElement brand = Wait.clickable(driver,brandField);
        WebElement hub = Wait.clickable(driver,hubField);
        WebElement shadeCode = Wait.clickable(driver,shadeCodeField);
        WebElement requester = Wait.clickable(driver,requesterField);
        WebElement shipToName = Wait.clickable(driver,shipToNameField);
        WebElement sapStatus = Wait.clickable(driver,sapStatusField);
        WebElement listButton = Wait.clickable(driver,listOrdersButton);
        WebElement reset = Wait.clickable(driver,resetButton);
        WebElement view = Wait.clickable(driver,viewButton);
        WebElement save = Wait.clickable(driver,saveButton);
        WebElement cancel = Wait.clickable(driver,cancelButton);
        WebElement print = Wait.clickable(driver,printButton);
                
        //Check all fields are displayed
        AssertJUnit.assertTrue("Inbox page: Order No Field not displayed correctly",orderNo.isDisplayed());
        AssertJUnit.assertTrue("Inbox page: FCE Name Field not displayed correctly",fceName.isDisplayed());
        AssertJUnit.assertTrue("Inbox page: Ticket Field not displayed correctly",ticket.isDisplayed());
        AssertJUnit.assertTrue("Inbox page: Customer name Field not displayed correctly",custName.isDisplayed());
        AssertJUnit.assertTrue("Inbox page: Ship To Code Field not displayed correctly",shipToCode.isDisplayed());
        AssertJUnit.assertTrue("Inbox page: OrderDate From Field not displayed correctly",orderFrom.isDisplayed());
        AssertJUnit.assertTrue("Inbox page: OrderDate To Field not displayed correctly",orderTo.isDisplayed());
        AssertJUnit.assertTrue("Inbox page: Order Type Field not displayed correctly",orderType.isDisplayed());
        AssertJUnit.assertTrue("Inbox page: Article Field not displayed correctly",article.isDisplayed());
        AssertJUnit.assertTrue("Inbox page: Customer Code Field not displayed correctly",custCode.isDisplayed());
        AssertJUnit.assertTrue("Inbox page: Brand Field not displayed correctly",brand.isDisplayed());
        AssertJUnit.assertTrue("Inbox page: Shade Code field not displayed correctly",shadeCode.isDisplayed());
        AssertJUnit.assertTrue("Inbox page: Requester field not displayed correctly",requester.isDisplayed());
        AssertJUnit.assertTrue("Inbox page: Ship To Name field not displayed correctly",shipToName.isDisplayed());
        AssertJUnit.assertTrue("Inbox page: SAP Status field not displayed correctly",sapStatus.isDisplayed());              
    }
    
}
