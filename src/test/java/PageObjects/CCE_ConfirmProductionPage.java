
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


public class CCE_ConfirmProductionPage extends WBA_BasePage {
    
    //Locators
    By breadcrumb = By.cssSelector("#content > h2");
    By orderNoField = By.id("s2id_filterSampleOrderLineOrderId");
    By fceNameField = By.id("s2id_filterSampleOrderFceId");
    By custCodeField = By.cssSelector("#s2id_filterSampleOrderCustomerIdCode > ul > li > input");
    By requestTypeField = By.id("s2id_filterSampleOrderLineRequestTypeId");
    By custNameField = By.cssSelector("#s2id_filterSampleOrderCustomerIdName > ul > li > input");
    By shipToNameField = By.cssSelector("#s2id_filterSampleOrderShipToPartyIdName > ul > li > input");
    By shipToCodeField = By.id("s2id_filterSampleOrderShipToPartyIdCode");
    By hubField = By.cssSelector("#s2id_filterSampleOrderHubId > ul > li > input");
    By scenarioField = By.id("s2id_filterSampleOrderLineScenarioId");
    By orderFromField = By.id("filterSampleOrderCreatedFrom");
    By orderToField = By.id("filterSampleOrderCreatedTo");
    By sapStatusField = By.id("s2id_filterSampleOrderLineSapOrderStatusId");
    By finalSOSField = By.id("s2id_filterSampleOrderLineSosId");
    By listOrdersButton = By.cssSelector("#FilterConfirmProductionForm > div.actions > ul > li:nth-child(1)");
    By resetButton = By.cssSelector("#FilterConfirmProductionForm > div.actions > ul > li:nth-child(2)");
    By confirmButton = By.xpath("//*[@id=\"SampleOrderLineConfirmProductionForm\"]/table/tbody/tr[3]/td[10]/input[2]");
    By MUMTypeField = By.cssSelector("#SampleOrderLineConfirmProductionForm > table > tbody > tr:nth-child(3) > td:nth-child(12) > select");
    By qtyProdField = By.cssSelector("#SampleOrderLineConfirmProductionForm > table > tbody > tr:nth-child(3) > td:nth-child(14) > input");
    By sendToField = By.cssSelector("#SampleOrderLineConfirmProductionForm > table > tbody > tr:nth-child(3) > td:nth-child(15) > select");
    By dnButton = By.cssSelector("#SampleOrderLineConfirmProductionForm > table > tbody > tr:nth-child(3) > td:nth-child(5) > a");
    By packageLabelButton = By.cssSelector("#SampleOrderLineConfirmProductionForm > table > tbody > tr:nth-child(3) > td:nth-child(16) > a");
    By saveButton = By.id("save");
    By cancelButton = By.cssSelector("#content > div.actions > ul > li:nth-child(2) > a");
    By flashMessage = By.id("flashMessage");
    
    public CCE_ConfirmProductionPage(WebDriver passedDriver) {
        super(passedDriver);
    }
    
    public WebElement getBreadcrumb() {
        WebElement waitForBreadcrumb = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.visibilityOfElementLocated(breadcrumb));
        return driver.findElement(breadcrumb);
    }
    
    public WebElement getOrderNoField() {
        return driver.findElement(orderNoField);
    }
    
    public WebElement getFceNameField() {
        return driver.findElement(fceNameField);
    }
    
    public WebElement getCustCodeField() {
        return driver.findElement(custCodeField);
    }
    
    public WebElement getRequestTypeField() {
        return driver.findElement(requestTypeField);
    }
    
    public WebElement getCustNameField() {
        return driver.findElement(custNameField);
    }
    
    public WebElement getShipToNameField() {
        return driver.findElement(shipToNameField);
    }
    
    public WebElement getShipToCodeField() {
        return driver.findElement(shipToCodeField);
    }
    
    public WebElement getReqTypeField() {
        return driver.findElement(requestTypeField);
    }
    
    public WebElement getHubField() {
        return driver.findElement(hubField);
    }
        
    public WebElement getScenarioField() {
        return driver.findElement(scenarioField);
    }
    
    public WebElement getOrderFromField() {
        return driver.findElement(orderFromField);
    }
    
    public WebElement getOrderToField() {
        return driver.findElement(orderToField);
    }
    
    public WebElement getSapStatusField() {
        return driver.findElement(sapStatusField);
    }
    
    public WebElement getFinalSOSField() {
        return driver.findElement(finalSOSField);
    }
    
    public WebElement getListOrdersButton() {
        return driver.findElement(listOrdersButton);
    }
    
    public WebElement getResetButton() {
        return driver.findElement(resetButton);
    }
    
    public WebElement getConfirmButton() {
        return driver.findElement(confirmButton);
    }
    
    public WebElement getMUMTypeField() {
        return driver.findElement(MUMTypeField);
    }
    
    public WebElement getQtyProdField() {
        return driver.findElement(qtyProdField);
    }
    
    public WebElement getPackageLabelButton() {
        return driver.findElement(packageLabelButton);
    }
    
    public WebElement getSendToField() {
        return driver.findElement(sendToField);
    }
    
    public WebElement getSaveButton() {
        return driver.findElement(saveButton);
    }
    
    public WebElement getCancelButton() {
        return driver.findElement(cancelButton);
    }
    
    public CCE_ConfirmProductionPage setOrderNo(String item) {
        //Place value in Order No Field
        CommonTask.setSearchField(driver, orderNoField, item);
        return this;
    }
    
    public CCE_ConfirmProductionPage setFceName(String item) {
        //Place value in FCE Name Field
        CommonTask.setChoiceField(driver, fceNameField, item);
        return this;
    }
    
    public CCE_ConfirmProductionPage setRequestType(String item) {
        //Place value in Request Type Field
        CommonTask.setSearchField(driver, requestTypeField, item);
        return this;
    }
    
    public CCE_ConfirmProductionPage setShipToName(String item) {
        CommonTask.setChoiceField(driver, shipToNameField, item);
        return this;
    }
    
    public CCE_ConfirmProductionPage setShipToCode(String item) {
        CommonTask.setChoiceField(driver, shipToCodeField, item);
        return this;
    }
    
    public CCE_ConfirmProductionPage setHub(String item) {
        CommonTask.setChoiceField(driver, hubField, item);
        return this;
    }
    
    public CCE_ConfirmProductionPage setScenario(String item) {
        CommonTask.setSearchField(driver, scenarioField, item);
        return this;
    }
    
    public CCE_ConfirmProductionPage setCustName(String item) {
        CommonTask.setChoiceField(driver, custNameField, item);
        return this;
    }
    
    public CCE_ConfirmProductionPage setFinalSOS(String item) {
        CommonTask.setSearchField(driver, finalSOSField, item);
        return this;
    }
    
    public CCE_ConfirmProductionPage setMUMType(String item) throws InterruptedException {
        CommonTask.setDropDownField(driver, MUMTypeField, item);
        return this;
    }
    
    public CCE_ConfirmProductionPage setQtyProd(String item) {
        //Wait for element
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(qtyProdField));
        element.click();
        //clear field
        element.sendKeys(Keys.DELETE);
        //set value
        element.sendKeys(item);
        return this;
    }
    
    public CCE_ConfirmProductionPage setSendTo(String item) throws InterruptedException {
        CommonTask.setDropDownField(driver, sendToField, item);
        return this;
    }
    
    public CCE_ConfirmProductionPage pressListOrders() {
        //Wait for element
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(listOrdersButton));
        element.submit();
        return this;
    }
    
    public CCE_ConfirmProductionPage pressReset() {
        //Wait for element
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(resetButton));
        element.click();
        return this;
    }
    
    public CCE_ConfirmProductionPage pressConfirm() {
        //Wait for element
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(confirmButton));
        //Check box
        element.click();
        //Wait for box to be checked
        boolean waitForChecked = new WebDriverWait(driver,DataItems.shortWait).until(CommonTask.boxIsChecked(driver.findElement(confirmButton)));
        return this;
    }
    
    public CCE_OrderViewPage pressDnPrint() {
        //Print button appears once confirm box has been checked for a record
        
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(dnButton));
        
        //Click icon to open overlay
        element.click();
        
        return new CCE_OrderViewPage(driver);
    }
    
    public CCE_ConfirmProductionPage pressSave() {
        WebElement save = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(saveButton));
        save.click();
        
        Alert alert = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.alertIsPresent());
        alert.accept();
        
        //Wait for confirmation message and ensure the message is as expected
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.visibilityOfElementLocated(flashMessage));
        
        String message = element.getText();
        if (message.contains("has been updated")) {
            System.out.println("Save successful.");
        } else {
            System.out.println("Save unsuccessful. Unexpected message received: "+message);
        }
        
        return this;
    }
    
    public CCE_ConfirmProductionPage pressCancel() {
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(cancelButton));
        element.click();
        
        try {
            Alert alert = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.alertIsPresent());
            alert.accept(); 
        } catch (Exception e) {
            System.out.println("No alert upon cancel.");
        }

        return this;
    }
    
    public boolean findOrder(String orderNo) {
        //Find an order in the table by its Order No. 
        
        //i starts at 3, as the first row has row number 3. i stops at 8 as an arbitrary value, before the last record. Extension: use getRecordCount() to replace "8"
        for (int i = 3; i < 8; i++) {
            By orderNoCell = By.cssSelector("#SampleOrderLineConfirmProductionForm > table > tbody > tr:nth-child("+i+") > td:nth-child(4)");
            
            WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.visibilityOfElementLocated(orderNoCell));
            
            if (element.getText().equals(orderNo)) {
                //return true if the Order No matches
                return true;
            }
        }
        //Return false if no match has been found
        return false;
    }
    
    public CCE_ConfirmProductionPage acceptSave() {
        Alert alert = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.alertIsPresent());
        alert.accept();
        return new CCE_ConfirmProductionPage(driver);
    }
    
    public void checkFields() {
        //Wait for each element to be clickable
        WebElement waitForOrderNo = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(orderNoField));
        WebElement waitForFCE = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(fceNameField));
        WebElement waitForCustCode = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(custCodeField));
        WebElement waitForReqType = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(requestTypeField));
        WebElement waitForOrderFrom = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(orderFromField));
        WebElement waitForOrderTo = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(orderToField));
        WebElement waitForShipToName = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(shipToNameField));
        WebElement waitForShipToCode = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(shipToCodeField));
        WebElement waitForHub = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(hubField));
        WebElement waitForScenario = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(scenarioField));
        WebElement waitForSAPStatus = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(sapStatusField));
        WebElement waitForCustName = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(custNameField));
        WebElement waitForFinalSOS = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(finalSOSField));
        
        //Assert all elements are displayed correctly
        AssertJUnit.assertTrue("Confirm Production page: Order No Field not displayed correctly",getOrderNoField().isDisplayed());
        AssertJUnit.assertTrue("Confirm Production page: FCE Name Field not displayed correctly",getFceNameField().isDisplayed());
        AssertJUnit.assertTrue("Confirm Production page: Customer Code Field not displayed correctly",getCustCodeField().isDisplayed());
        AssertJUnit.assertTrue("Confirm Production page: Requester Type Field not displayed correctly",getReqTypeField().isDisplayed());
        AssertJUnit.assertTrue("Confirm Production page: Order Date From Field not displayed correctly",getOrderFromField().isDisplayed());
        AssertJUnit.assertTrue("Confirm Production page: Order Date To Field not displayed correctly",getOrderToField().isDisplayed());
        AssertJUnit.assertTrue("Confirm Production page: Ship To Name Field not displayed correctly",getShipToNameField().isDisplayed());
        AssertJUnit.assertTrue("Confirm Production page: Ship To Code Field not displayed correctly",getShipToCodeField().isDisplayed());
        AssertJUnit.assertTrue("Confirm Production page: Hub Field not displayed correctly",getHubField().isDisplayed());
        AssertJUnit.assertTrue("Confirm Production page: Scenario Field not displayed correctly",getScenarioField().isDisplayed());
        AssertJUnit.assertTrue("Confirm Production page: SAP Status Field not displayed correctly",getSapStatusField().isDisplayed());
        AssertJUnit.assertTrue("Confirm Production page: Customer name Field not displayed correctly",getCustNameField().isDisplayed());
        AssertJUnit.assertTrue("Confirm Production page: Final SOS Field not displayed correctly",getFinalSOSField().isDisplayed());
        AssertJUnit.assertTrue("Confirm Production page: Confirm button not displayed correctly",getConfirmButton().isDisplayed());
        AssertJUnit.assertTrue("Confirm Production page: MUM Field not displayed correctly",getMUMTypeField().isDisplayed());
        AssertJUnit.assertTrue("Confirm Production page: Quantity Produced field not displayed correctly",getQtyProdField().isDisplayed());
        AssertJUnit.assertTrue("Confirm Production page: Send to field not displayed correctly",getSendToField().isDisplayed());
        AssertJUnit.assertTrue("Confirm Production page: Save button not displayed correctly",getSaveButton().isDisplayed());
        
    }
    
    public void waitForElement() {
        WebElement wait = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(orderNoField));
    }
}
