
package PageObjects;

import AutomationFramework.CommonTask;
import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class HubSosPage_CCE extends BasePage {
    
    //Locators
    By breadcrumb = By.cssSelector("#content > h2");
    By hub = By.id("s2id_filterSampleOrderHubId");
    By orderNoField = By.id("s2id_filterSampleOrderLineOrderId");
    By custNameField = By.id("s2id_filterSampleOrderCustomerId");
    By orderDateFromField = By.id("filterSampleOrderCreatedFrom");
    By orderDateToField = By.cssSelector("#FilterHubSosForm > table > tbody > tr:nth-child(4) > td:nth-child(4) > input");
    By ticketField = By.id("s2id_filterSampleOrderLineTicketId");
    By fceNameField = By.cssSelector("#s2id_filterSampleOrderFceId > ul > li > input");
    By brandField = By.cssSelector("#s2id_filterSampleOrderLineBrandId > ul > li > input");
    By mumTypeField = By.id("s2id_filterSampleOrderLineMumTypeId");
    By requesterField = By.id("s2id_filterSampleOrderRequesterId");
    By listOrdersButton = By.cssSelector("#FilterHubSosForm > div.actions > ul > li:nth-child(1)");
    By resetButton = By.cssSelector("#FilterHubSosForm > div.actions > ul > li:nth-child(2)");
    By mumTypeAdjust = By.cssSelector("#SampleOrderHubSosForm > div.flexi-grid > table > tbody > tr:nth-child(2) > td:nth-child(11) > div > select");
    By deliverButton = By.cssSelector("#SampleOrderHubSosForm > div.flexi-grid > table > tbody > tr:nth-child(2) > td:nth-child(13) > div > input");
    By warehouseButton = By.cssSelector("#SampleOrderHubSosForm > div.flexi-grid > table > tbody > tr:nth-child(2) > td:nth-child(14) > div > input");
    By labButton = By.cssSelector("#SampleOrderHubSosForm > div.flexi-grid > table > tbody > tr:nth-child(2) > td:nth-child(15) > div > input");
    By viewButton = By.cssSelector("#SampleOrderHubSosForm > div.flexi-grid > table > tbody > tr:nth-child(2) > td:nth-child(16) > a");
    By saveButton = By.cssSelector("#SampleOrderHubSosForm > div.actions > ul:nth-child(2) > li:nth-child(1)");
    By cancelButton = By.cssSelector("#SampleOrderHubSosForm > div.actions > ul:nth-child(2) > li:nth-child(2)");
    By filterForm = By.id("FilterHubSosForm");
    By flashMessage = By.id("flashMessage");
    By viewFrame = By.id("TB_iframeContent");
    
    public HubSosPage_CCE(WebDriver passedDriver) {
        super(passedDriver);
    }
    
    public String getTitle() {
        WebElement waitForVis = new WebDriverWait(driver,10).until(ExpectedConditions.visibilityOfElementLocated(breadcrumb));
        
        return driver.findElement(breadcrumb).getText();
    }
    
    public WebElement getHubField() {
        return driver.findElement(hub);
    }
    
    public WebElement getOrderNoField() {
        return driver.findElement(orderNoField);
    }
    
    public WebElement getCustNameField() {
        return driver.findElement(custNameField);
    }
    
    public WebElement getOrderDateFromField() {
        return driver.findElement(orderDateFromField);
    }
    
    public WebElement getOrderDateToField() {
        return driver.findElement(orderDateToField);
    }
    
    public WebElement getTicketField() {
        return driver.findElement(ticketField);
    }
    
    public WebElement getFceNameField() {
        return driver.findElement(fceNameField);
    }
    
    public WebElement getBrandField() {
        return driver.findElement(brandField);
    }
    
    public WebElement getMumTypeField() {
        return driver.findElement(mumTypeField);
    }
    
    public WebElement getRequesterField() {
        return driver.findElement(requesterField);
    }
    
    public WebElement getListOrdersButton() {
        return driver.findElement(listOrdersButton);
    }
    
    public WebElement getResetButton() {
        return driver.findElement(resetButton);
    }
    
    public WebElement getSaveButton() {
        return driver.findElement(saveButton);
    }
    
    public WebElement getCancelButton() {
        return driver.findElement(cancelButton);
    }
    
    public HubSosPage_CCE setOrderNo(String item) {
        CommonTask.setSearchField(driver, orderNoField, item);
        return this;
    }
    
    public HubSosPage_CCE setTicket(String item) {
        CommonTask.setSearchField(driver, ticketField, item);
        return this;
    }
    
    public HubSosPage_CCE setCustName(String item) {
        CommonTask.setSearchField(driver, custNameField, item);
        return this;
    }
    
    public HubSosPage_CCE setOrderDateFrom(String item) {
        CommonTask.setSearchField(driver, orderDateFromField, item);
        return this;
    }
    
    public HubSosPage_CCE setOrderDateTo(String item) {
        CommonTask.setSearchField(driver, orderDateToField, item);
        return this;
    }
    
    public HubSosPage_CCE setRequester(String item) {
        CommonTask.setSearchField(driver, requesterField, item);
        return this;
    }
    
    public HubSosPage_CCE setMUMType(String item) {
        CommonTask.setSearchField(driver, mumTypeField, item);
        return this;
    }
    
    public HubSosPage_CCE setFceName(String item) {
        CommonTask.setChoiceField(driver, fceNameField, item);
        return this;
    }
    
    public HubSosPage_CCE setBrand(String item) {
        CommonTask.setChoiceField(driver, brandField, item);
        return this;
    }
    
    public HubSosPage_CCE setHub(String item) {
        CommonTask.setSearchField(driver, hub, item);
        return this;
    }
    
    public HubSosPage_CCE pressListOrders() {
        //Wait for button
        WebElement waitForClickable = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(listOrdersButton));
        
        //submit form
        driver.findElement(filterForm).submit();
        
        return this;
    }
    
    public HubSosPage_CCE pressReset() {
        //Wait for reset button
        WebElement waitForClickable = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(resetButton));
        
        driver.findElement(resetButton).click();
        
        return this;
    }
    
    public HubSosPage_CCE adjustMUMType(String mumType) throws InterruptedException {
        CommonTask.setDropDownField(driver, mumTypeAdjust, mumType);
        
        return this;
    }
    
    public HubSosPage_CCE adjustAssignment(String assignment) {
        switch (assignment) {
            case "Deliver to Customer": pressDeliver(); break;
            case "SOS Warehouse": pressWarehouse(); break;
            case "SOS Lab": pressLab(); break;
        }
        
        return this;
    }
    
    public void pressDeliver() {
        //Wait for element
        WebElement waitForClickable = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(deliverButton));
        
        driver.findElement(deliverButton).click();
        
        boolean waitForChecked = new WebDriverWait(driver,10).until(CommonTask.boxIsChecked(driver.findElement(deliverButton)));
    }
    
    public void pressWarehouse() {
        //Wait for element
        WebElement waitForClickable = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(warehouseButton));
        
        driver.findElement(warehouseButton).click();
        
        boolean waitForChecked = new WebDriverWait(driver,10).until(CommonTask.boxIsChecked(driver.findElement(warehouseButton)));
    }
    
    public void pressLab() {
        //Wait for element
        WebElement waitForClickable = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(labButton));
        
        driver.findElement(labButton).click();
        
        boolean waitForChecked = new WebDriverWait(driver,10).until(CommonTask.boxIsChecked(driver.findElement(labButton)));
    }
    
    public OrderViewPage_CCE pressView() {
        //Wait for button
        WebElement waitForClickable = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(viewButton));
        
        driver.findElement(viewButton).click();
        
        //switch to frame
        switchTo();
        
        return new OrderViewPage_CCE(driver);
    }
    
    public void switchTo() {
        WebDriver waitForFrame = new WebDriverWait(driver,10).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(viewFrame));
    }
    
    public HubSosPage_CCE closeView() {
        Actions action = new Actions(driver);
        action.sendKeys(Keys.ESCAPE).build().perform();
        
        Alert alert = new WebDriverWait(driver,10).until(ExpectedConditions.alertIsPresent());
        alert.accept();
        
        Alert alert2 = new WebDriverWait(driver,10).until(ExpectedConditions.alertIsPresent());
        alert.dismiss();
        
        return new HubSosPage_CCE(driver);
    }
        
    public HubSosPage_CCE pressSave() {
        WebElement waitForClickable = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(saveButton));
        
        driver.findElement(saveButton).click();
        
        Alert alert = new WebDriverWait(driver,10).until(ExpectedConditions.alertIsPresent());
        alert.accept();
        
        String message = driver.findElement(flashMessage).getText();
        
        if (message.contains("Order has been")) {
            System.out.println("Save successful. Message received: "+message);
        } else {
            System.out.println("***Save unsuccessful/unexpected message received: "+message +"***");
        }
        
        return this;
        
    }
    
    public void checkFields() {
        //Wait for all to be clickable
        WebElement waitForOrderNo = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(orderNoField));
        WebElement waitForTicket = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(ticketField));
        WebElement waitForCustName = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(custNameField));
        WebElement waitForOrderFrom = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(orderDateFromField));
        WebElement waitForOrderTo = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(orderDateToField));
        WebElement waitForRequester = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(requesterField));
        WebElement waitForMUM = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(mumTypeField));
        WebElement waitForFce = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(fceNameField));
        WebElement waitForBrand = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(brandField));        
        WebElement waitForHub = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(hub));
        WebElement waitForListOrders = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(listOrdersButton));
        WebElement waitForReset = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(resetButton));
        
        //Assert all elements are displayed
        Assert.assertTrue("Hub SOS Page: Order No Field not displayed correctly",getOrderNoField().isDisplayed());
        Assert.assertTrue("Hub SOS Page: Hub Field not displayed correctly",getHubField().isDisplayed());
        Assert.assertTrue("Hub SOS Page: Customer Name Field not displayed correctly",getCustNameField().isDisplayed());
        Assert.assertTrue("Hub SOS Page: Order Date From Field not displayed correctly",getOrderDateFromField().isDisplayed());
        Assert.assertTrue("Hub SOS Page: Order Date To Field not displayed correctly",getOrderDateToField().isDisplayed());
        Assert.assertTrue("Hub SOS Page: Ticket Field not displayed correctly",getTicketField().isDisplayed());
        Assert.assertTrue("Hub SOS Page: FCE Field not displayed correctly",getFceNameField().isDisplayed());
        Assert.assertTrue("Hub SOS Page: MUM Type Field not displayed correctly",getMumTypeField().isDisplayed());
        Assert.assertTrue("Hub SOS Page: Requester Field not displayed correctly",getRequesterField().isDisplayed());
        Assert.assertTrue("Hub SOS Page: Brand Field not displayed correctly",getBrandField().isDisplayed());
        
        
    }
    
}
