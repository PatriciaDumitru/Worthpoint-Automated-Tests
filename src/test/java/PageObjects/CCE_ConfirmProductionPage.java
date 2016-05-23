
package PageObjects;

import AutomationFramework.CommonTask;
import AutomationFramework.Wait;
import org.testng.AssertJUnit;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


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
    By finalShade = By.xpath("//*[@id=\"SampleOrderLineConfirmProductionForm\"]/table/tbody/tr[3]/td[9]/input");//*[@id="SampleOrderLineConfirmProductionForm"]/table/tbody/tr[3]/td[9]
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
        return Wait.visible(driver, breadcrumb);
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
        WebElement element = Wait.clickable(driver,qtyProdField);
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
        WebElement element = Wait.clickable(driver,listOrdersButton);
        element.submit();
        return this;
    }
    
    public CCE_ConfirmProductionPage pressReset() {
        //Wait for element
        WebElement element = Wait.clickable(driver,resetButton);
        element.click();
        return this;
    }

    public void setFinalShade(String item){
        /**
         * Created by Stefan
         */
        //Wait for element
        WebElement element = Wait.clickable(driver,finalShade);
        if (element.getText().isEmpty()){
            element.sendKeys(item);
        }
    }
    
    public CCE_ConfirmProductionPage pressConfirm() {
        //Wait for element
        WebElement element = Wait.clickable(driver,confirmButton);
        //Check box
        element.click();
        //Wait for box to be checked
        boolean waitForChecked = Wait.checked(driver,confirmButton);
        return this;
    }
    
    public CCE_OrderViewPage pressDnPrint() {
        //Print button appears once confirm box has been checked for a record
        
        WebElement element = Wait.clickable(driver,dnButton);
        
        //Click icon to open overlay
        element.click();
        
        return new CCE_OrderViewPage(driver);
    }
    
    public CCE_ConfirmProductionPage pressSave() {
        WebElement save = Wait.clickable(driver,saveButton);
        save.click();
        
        Alert alert = Wait.alert(driver);
        alert.accept();
        
        //Wait for confirmation message and ensure the message is as expected
        WebElement element = Wait.visible(driver,flashMessage);
        
        String message = element.getText();
        if (message.contains("has been updated")) {
            System.out.println("Save successful.");
        } else {
            System.out.println("Save unsuccessful. Unexpected message received: "+message);
        }
        
        return this;
    }
    
    public CCE_ConfirmProductionPage pressCancel() {
        WebElement element = Wait.clickable(driver,cancelButton);
        element.click();
        
        try {
            Alert alert = Wait.alert(driver);
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
            
            WebElement element = Wait.visible(driver,orderNoCell);
            
            if (element.getText().equals(orderNo)) {
                //return true if the Order No matches
                return true;
            }
        }
        //Return false if no match has been found
        return false;
    }
    
    public CCE_ConfirmProductionPage acceptSave() {
        Alert alert = Wait.alert(driver);
        alert.accept();
        return new CCE_ConfirmProductionPage(driver);
    }
    
    public void checkFields() {
        //Wait for each element to be clickable
        WebElement orderNo = Wait.clickable(driver,orderNoField);
        WebElement fce = Wait.clickable(driver,fceNameField);
        WebElement custCode = Wait.clickable(driver,custCodeField);
        WebElement reqType = Wait.clickable(driver,requestTypeField);
        WebElement orderFrom = Wait.clickable(driver,orderFromField);
        WebElement orderTo = Wait.clickable(driver,orderToField);
        WebElement shipTo = Wait.clickable(driver,shipToNameField);
        WebElement shipToCode = Wait.clickable(driver,shipToCodeField);
        WebElement hub = Wait.clickable(driver,hubField);
        WebElement scenario = Wait.clickable(driver,scenarioField);
        WebElement sapStatus = Wait.clickable(driver,sapStatusField);
        WebElement custName = Wait.clickable(driver,custNameField);
        WebElement finalSOS = Wait.clickable(driver,finalSOSField);
        WebElement confirm = Wait.clickable(driver,confirmButton);
        WebElement mumType = Wait.visible(driver,MUMTypeField);
        WebElement qtyProd = Wait.visible(driver,qtyProdField);
        WebElement sendTo = Wait.visible(driver,sendToField);
        WebElement save = Wait.clickable(driver,saveButton);
        
        //Assert all elements are displayed correctly
        AssertJUnit.assertTrue("Confirm Production page: Order No Field not displayed correctly",orderNo.isDisplayed());
        AssertJUnit.assertTrue("Confirm Production page: FCE Name Field not displayed correctly",fce.isDisplayed());
        AssertJUnit.assertTrue("Confirm Production page: Customer Code Field not displayed correctly",custCode.isDisplayed());
        AssertJUnit.assertTrue("Confirm Production page: Requester Type Field not displayed correctly",reqType.isDisplayed());
        AssertJUnit.assertTrue("Confirm Production page: Order Date From Field not displayed correctly",orderFrom.isDisplayed());
        AssertJUnit.assertTrue("Confirm Production page: Order Date To Field not displayed correctly",orderTo.isDisplayed());
        AssertJUnit.assertTrue("Confirm Production page: Ship To Name Field not displayed correctly",shipTo.isDisplayed());
        AssertJUnit.assertTrue("Confirm Production page: Ship To Code Field not displayed correctly",shipToCode.isDisplayed());
        AssertJUnit.assertTrue("Confirm Production page: Hub Field not displayed correctly",hub.isDisplayed());
        AssertJUnit.assertTrue("Confirm Production page: Scenario Field not displayed correctly",scenario.isDisplayed());
        AssertJUnit.assertTrue("Confirm Production page: SAP Status Field not displayed correctly",sapStatus.isDisplayed());
        AssertJUnit.assertTrue("Confirm Production page: Customer name Field not displayed correctly",custName.isDisplayed());
        AssertJUnit.assertTrue("Confirm Production page: Final SOS Field not displayed correctly",finalSOS.isDisplayed());
        AssertJUnit.assertTrue("Confirm Production page: Confirm button not displayed correctly",confirm.isDisplayed());
        AssertJUnit.assertTrue("Confirm Production page: MUM Field not displayed correctly",mumType.isDisplayed());
        AssertJUnit.assertTrue("Confirm Production page: Quantity Produced field not displayed correctly",qtyProd.isDisplayed());
        AssertJUnit.assertTrue("Confirm Production page: Send to field not displayed correctly",sendTo.isDisplayed());
        AssertJUnit.assertTrue("Confirm Production page: Save button not displayed correctly",save.isDisplayed());
        
    }
    
    public void waitForElement() {
        WebElement wait = Wait.clickable(driver,orderNoField);
    }
}
