
package PageObjects;

import AutomationFramework.CommonTask;
import org.junit.Assert;
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
    By confirmButton = By.xpath("//*[@id=\"SampleOrderLineConfirmProductionForm\"]/table/tbody/tr[3]/td[10]/input[@value='1']");
    By finalShadeField = By.cssSelector("#SampleOrderLineConfirmProductionForm > table > tbody > tr:nth-child(3) > td:nth-child(9)");
    By MUMTypeField = By.cssSelector("#SampleOrderLineConfirmProductionForm > table > tbody > tr:nth-child(3) > td:nth-child(12) > select");
    By qtyProdField = By.cssSelector("#SampleOrderLineConfirmProductionForm > table > tbody > tr:nth-child(3) > td:nth-child(14) > input");
    By sendToField = By.cssSelector("#SampleOrderLineConfirmProductionForm > table > tbody > tr:nth-child(3) > td:nth-child(15) > select");
    By dnButton = By.cssSelector("#SampleOrderLineConfirmProductionForm > table > tbody > tr:nth-child(3) > td:nth-child(5) > a");
    By saveButton = By.cssSelector("#content > div.actions > ul > li:nth-child(1)");
    By cancelButton = By.cssSelector("#content > div.actions > ul > li:nth-child(2)");
    By flashMessage = By.id("flashMessage");
    
    public CCE_ConfirmProductionPage(WebDriver passedDriver) {
        super(passedDriver);
    }
    
    public WebElement getBreadcrumb() {
        WebElement waitForBreadcrumb = new WebDriverWait(driver,10).until(ExpectedConditions.visibilityOfElementLocated(breadcrumb));
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
    
    public WebElement getFinalShadeField() {
        return driver.findElement(finalShadeField);
    }
    
    public WebElement getMUMTypeField() {
        return driver.findElement(MUMTypeField);
    }
    
    public WebElement getQtyProdField() {
        return driver.findElement(qtyProdField);
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
        CommonTask.setSearchField(driver, orderNoField, item);
        return this;
    }
    
    public CCE_ConfirmProductionPage setFceName(String item) {
        CommonTask.setChoiceField(driver, fceNameField, item);
        return this;
    }
    
    public CCE_ConfirmProductionPage setRequestType(String item) {
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
    
    public CCE_ConfirmProductionPage setFinalShade(String item) throws InterruptedException {
        CommonTask.setDropDownField(driver, finalShadeField, item);
        return this;
    }
    
    public CCE_ConfirmProductionPage setMUMType(String item) throws InterruptedException {
        CommonTask.setDropDownField(driver, MUMTypeField, item);
        return this;
    }
    
    public CCE_ConfirmProductionPage setQtyProd(String item) {
        //Wait for clickable
        WebElement waitForClickable = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(qtyProdField));
        driver.findElement(qtyProdField).click();
        driver.findElement(qtyProdField).sendKeys(Keys.DELETE);
        driver.findElement(qtyProdField).sendKeys(item);
        return this;
    }
    
    public CCE_ConfirmProductionPage setSendTo(String item) throws InterruptedException {
        CommonTask.setDropDownField(driver, sendToField, item);
        return this;
    }
    
    public CCE_ConfirmProductionPage pressListOrders() {
        //Wait for element
        WebElement waitForClickable = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(listOrdersButton));
        driver.findElement(listOrdersButton).submit();
        return this;
    }
    
    public CCE_ConfirmProductionPage pressReset() {
        //Wait for element
        WebElement waitForClickable = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(resetButton));
        driver.findElement(resetButton).click();
        return this;
    }
    
    public CCE_ConfirmProductionPage pressConfirm() {
        //Wait for element
        WebElement waitForClickable = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(confirmButton));
        driver.findElement(confirmButton).click();
        boolean waitForChecked = new WebDriverWait(driver,10).until(CommonTask.boxIsChecked(driver.findElement(confirmButton)));
        return this;
    }
    
    public CCE_OrderViewPage pressDnPrint() {
        //Wait for dn print button and press
        WebElement waitForClickable = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(dnButton));
        driver.findElement(dnButton).click();
        return new CCE_OrderViewPage(driver);
    }
    
    public CCE_ConfirmProductionPage pressSave() {
        WebElement waitForClickable = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(saveButton));
        driver.findElement(saveButton).click();
        Alert alert = new WebDriverWait(driver,10).until(ExpectedConditions.alertIsPresent());
        alert.accept();
        
        String message = driver.findElement(flashMessage).getText();
        if (message.contains("has been updated")) {
            System.out.println("Save successful.");
        } else {
            System.out.println("Save unsuccessful. Unexpected message received: "+message);
        }
        
        return this;
    }
    
    public CCE_ConfirmProductionPage pressCancel() {
        WebElement waitForClickable = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(cancelButton));
        driver.findElement(cancelButton).click();
        Alert alert = new WebDriverWait(driver,10).until(ExpectedConditions.alertIsPresent());
        alert.accept();
        return this;
    }
    
    public void checkFields() {
        //Wait for each element to be clickable
        WebElement waitForOrderNo = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(orderNoField));
        WebElement waitForFCE = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(fceNameField));
        WebElement waitForCustCode = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(custCodeField));
        WebElement waitForReqType = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(requestTypeField));
        WebElement waitForOrderFrom = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(orderFromField));
        WebElement waitForOrderTo = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(orderToField));
        WebElement waitForShipToName = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(shipToNameField));
        WebElement waitForShipToCode = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(shipToCodeField));
        WebElement waitForHub = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(hubField));
        WebElement waitForScenario = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(scenarioField));
        WebElement waitForSAPStatus = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(sapStatusField));
        WebElement waitForCustName = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(custNameField));
        WebElement waitForFinalSOS = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(finalSOSField));
        
        //Assert all elements are displayed correctly
        Assert.assertTrue("Confirm Production page: Order No Field not displayed correctly",getOrderNoField().isDisplayed());
        Assert.assertTrue("Confirm Production page: FCE Name Field not displayed correctly",getFceNameField().isDisplayed());
        Assert.assertTrue("Confirm Production page: Customer Code Field not displayed correctly",getCustCodeField().isDisplayed());
        Assert.assertTrue("Confirm Production page: Requester Type Field not displayed correctly",getReqTypeField().isDisplayed());
        Assert.assertTrue("Confirm Production page: Order Date From Field not displayed correctly",getOrderFromField().isDisplayed());
        Assert.assertTrue("Confirm Production page: Order Date To Field not displayed correctly",getOrderToField().isDisplayed());
        Assert.assertTrue("Confirm Production page: Ship To Name Field not displayed correctly",getShipToNameField().isDisplayed());
        Assert.assertTrue("Confirm Production page: Ship To Code Field not displayed correctly",getShipToCodeField().isDisplayed());
        Assert.assertTrue("Confirm Production page: Hub Field not displayed correctly",getHubField().isDisplayed());
        Assert.assertTrue("Confirm Production page: Scenario Field not displayed correctly",getScenarioField().isDisplayed());
        Assert.assertTrue("Confirm Production page: SAP Status Field not displayed correctly",getSapStatusField().isDisplayed());
        Assert.assertTrue("Confirm Production page: Customer name Field not displayed correctly",getCustNameField().isDisplayed());
        Assert.assertTrue("Confirm Production page: Final SOS Field not displayed correctly",getFinalSOSField().isDisplayed());
        Assert.assertTrue("Confirm Production page: Final Shade Field not displayed correctly",getFinalShadeField().isDisplayed());
        Assert.assertTrue("Confirm Production page: Confirm button not displayed correctly",getConfirmButton().isDisplayed());
        Assert.assertTrue("Confirm Production page: MUM Field not displayed correctly",getMUMTypeField().isDisplayed());
        Assert.assertTrue("Confirm Production page: Quantity Produced field not displayed correctly",getQtyProdField().isDisplayed());
        Assert.assertTrue("Confirm Production page: Send to field not displayed correctly",getSendToField().isDisplayed());
        Assert.assertTrue("Confirm Production page: Save button not displayed correctly",getSaveButton().isDisplayed());
        
    }
}
