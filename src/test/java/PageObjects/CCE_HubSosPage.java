
package PageObjects;

import AutomationFramework.CommonTask;
import AutomationFramework.Wait;
import org.testng.AssertJUnit;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;


public class CCE_HubSosPage extends WBA_BasePage {
    
    //Locators
    By breadcrumb = By.cssSelector("#content > h2");
    By hubField = By.id("s2id_filterSampleOrderHubId");
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
    By noRecords = By.cssSelector("#SampleOrderHubSosForm > div.flexi-grid > div");
    
    public CCE_HubSosPage(WebDriver passedDriver) {
        super(passedDriver);
    }
    
    public String getTitle() {
        WebElement breadcrumbField = Wait.visible(driver,breadcrumb);
        
        return driver.findElement(breadcrumb).getText();
    }
    
    public WebElement getHubField() {
        return driver.findElement(hubField);
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
    
    public boolean findRecords() {
        boolean records;
        
        try {
            WebElement wait = Wait.visible(driver,noRecords);
            records = true;
        } catch (Exception e){
            records = false;
        }
        
        return records;
    }
    
    public CCE_HubSosPage setOrderNo(String item) {
        CommonTask.setSearchField(driver, orderNoField, item);
        return this;
    }
    
    public CCE_HubSosPage setTicket(String item) {
        CommonTask.setSearchField(driver, ticketField, item);
        return this;
    }
    
    public CCE_HubSosPage setCustName(String item) {
        CommonTask.setSearchField(driver, custNameField, item);
        return this;
    }
    
    public CCE_HubSosPage setOrderDateFrom(String item) {
        CommonTask.setSearchField(driver, orderDateFromField, item);
        return this;
    }
    
    public CCE_HubSosPage setOrderDateTo(String item) {
        CommonTask.setSearchField(driver, orderDateToField, item);
        return this;
    }
    
    public CCE_HubSosPage setRequester(String item) {
        CommonTask.setSearchField(driver, requesterField, item);
        return this;
    }
    
    public CCE_HubSosPage setMUMType(String item) {
        CommonTask.setSearchField(driver, mumTypeField, item);
        return this;
    }
    
    public CCE_HubSosPage setFceName(String item) {
        CommonTask.setChoiceField(driver, fceNameField, item);
        return this;
    }
    
    public CCE_HubSosPage setBrand(String item) {
        CommonTask.setChoiceField(driver, brandField, item);
        return this;
    }
    
    public CCE_HubSosPage setHub(String item) {
        CommonTask.setSearchField(driver, hubField, item);
        return this;
    }
    
    public CCE_HubSosPage pressListOrders() {
        //Wait for button
        WebElement listOrders = Wait.clickable(driver,listOrdersButton);
        
        //submit form
        driver.findElement(filterForm).submit();
        
        return this;
    }
    
    public CCE_HubSosPage pressReset() {
        //Wait for reset button
        WebElement reset = Wait.clickable(driver,resetButton);
        
        reset.click();
        
        return this;
    }
    
    public CCE_HubSosPage adjustMUMType(String mumType) throws InterruptedException {
        CommonTask.setDropDownField(driver, mumTypeAdjust, mumType);
        
        return this;
    }
    
    public CCE_HubSosPage adjustAssignment(String assignment) {
        switch (assignment) {
            case "Deliver to Customer": pressDeliver(); break;
            case "SOS Warehouse": pressWarehouse(); break;
            case "SOS Lab": pressLab(); break;
        }
        
        return this;
    }
    
    public void pressDeliver() {
        //Wait for element
        WebElement deliver = Wait.clickable(driver,deliverButton);
        
        deliver.click();
        
        boolean waitForChecked = Wait.checked(driver,deliverButton);
    }
    
    public void pressWarehouse() {
        CommonTask.setCheckBox(driver,warehouseButton);
    }
    
    public void pressLab() {
        CommonTask.setCheckBox(driver,labButton);
    }
    
    public CCE_OrderViewPage pressView() {
        //Wait for button
        WebElement view = Wait.clickable(driver,viewButton);
        
        view.click();
        
        //switch to frame
        switchTo();
        
        return new CCE_OrderViewPage(driver);
    }
    
    public void switchTo() {
        WebDriver frame = Wait.frame(driver,viewFrame);
    }
    
    public CCE_HubSosPage closeView() {
        Actions action = new Actions(driver);
        action.sendKeys(Keys.ESCAPE).build().perform();
        
        Alert alert = Wait.alert(driver);
        alert.accept();
        
        Alert alert2 = Wait.alert(driver);
        alert.dismiss();
        
        return new CCE_HubSosPage(driver);
    }
        
    public CCE_HubSosPage pressSave() {
        WebElement save = Wait.clickable(driver,saveButton);
        
        save.click();
        
        Alert alert = Wait.alert(driver);
        alert.accept();
        
        WebElement messageField = Wait.visible(driver,flashMessage);
        
        String message = messageField.getText();
        
        if (message.contains("Order has been")) {
            System.out.println("Save successful. Message received: "+message);
        } else {
            System.out.println("***Save unsuccessful/unexpected message received: "+message +"***");
        }
        
        return this;
        
    }
    
    public void checkFields() {
        //Wait for all to be clickable
        WebElement orderNo = Wait.clickable(driver,orderNoField);
        WebElement ticket = Wait.clickable(driver,ticketField);
        WebElement custName = Wait.clickable(driver,custNameField);
        WebElement orderFrom = Wait.clickable(driver,orderDateFromField);
        WebElement orderTo = Wait.clickable(driver,orderDateToField);
        WebElement requester = Wait.clickable(driver,requesterField);
        WebElement mumType = Wait.clickable(driver,mumTypeField);
        WebElement fce = Wait.clickable(driver,fceNameField);
        WebElement brand = Wait.clickable(driver,brandField);        
        WebElement hub = Wait.clickable(driver,hubField);
        WebElement listOrders = Wait.clickable(driver,listOrdersButton);
        WebElement reset = Wait.clickable(driver,resetButton);
        
        //Assert all elements are displayed
        AssertJUnit.assertTrue("Hub SOS Page: Order No Field not displayed correctly",orderNo.isDisplayed());
        AssertJUnit.assertTrue("Hub SOS Page: Hub Field not displayed correctly",hub.isDisplayed());
        AssertJUnit.assertTrue("Hub SOS Page: Customer Name Field not displayed correctly",custName.isDisplayed());
        AssertJUnit.assertTrue("Hub SOS Page: Order Date From Field not displayed correctly",orderFrom.isDisplayed());
        AssertJUnit.assertTrue("Hub SOS Page: Order Date To Field not displayed correctly",orderTo.isDisplayed());
        AssertJUnit.assertTrue("Hub SOS Page: Ticket Field not displayed correctly",ticket.isDisplayed());
        AssertJUnit.assertTrue("Hub SOS Page: FCE Field not displayed correctly",fce.isDisplayed());
        AssertJUnit.assertTrue("Hub SOS Page: MUM Type Field not displayed correctly",mumType.isDisplayed());
        AssertJUnit.assertTrue("Hub SOS Page: Requester Field not displayed correctly",requester.isDisplayed());
        AssertJUnit.assertTrue("Hub SOS Page: Brand Field not displayed correctly",brand.isDisplayed());  
    }
    
    public void waitForElement() {
        WebElement wait = Wait.clickable(driver,custNameField);
    }
    
    public String findOrder(String orderNo) {
        String found = null;
        for (int i = 2; i < 6; i++) {
            By orderNoCell = By.cssSelector("#SampleOrderHubSosForm > div.flexi-grid > table > tbody > tr:nth-child("+i+") > td:nth-child(5)");
            WebElement cell = Wait.visible(driver,orderNoCell);
            if (cell.getText().equals(orderNo)) {
                found = "Hub SOS";
            }
        }
        return found;   
    }
    
}
