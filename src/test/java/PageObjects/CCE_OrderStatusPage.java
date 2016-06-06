
package PageObjects;

import AutomationFramework.CommonTask;
import AutomationFramework.Wait;
import org.testng.AssertJUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;


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
    By listOrdersButton = By.cssSelector("#FilterOutstandingForm > div.actions > ul > li:nth-child(1) > input[type=\"submit\"]");
    By resetButton = By.cssSelector("#FilterOutstandingForm > div.actions > ul > li:nth-child(2) > a");
    By filterForm = By.id("FilterOutstandingForm");
    By exportButton = By.cssSelector("#content > div.actions > ul > li > a");
    By flashMessage = By.id("flashMessage");
    By contentFrame = By.id("content");
    
    public CCE_OrderStatusPage(WebDriver passedDriver) {
        super(passedDriver);
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
        WebElement orderNo = Wait.clickable(driver,orderNoField);
        WebElement custName = Wait.clickable(driver,custNameField);
        WebElement orderStage = Wait.clickable(driver,orderStageField);
        WebElement requestType = Wait.clickable(driver,requestTypeField);
        WebElement busPrinc = Wait.clickable(driver,busPrincipalField);
        WebElement hub = Wait.clickable(driver,hubField);
        WebElement requestor = Wait.clickable(driver,requesterField);
        WebElement scenario = Wait.clickable(driver,scenarioField);
        WebElement orderFrom = Wait.clickable(driver,orderDateFromField);
        WebElement orderTo = Wait.clickable(driver,orderDateToField);
        WebElement fce = Wait.clickable(driver,fceNameField);
        WebElement fabRef = Wait.clickable(driver,fabRefField);
        WebElement shadeCode = Wait.clickable(driver,shadeCodeField);
        WebElement currentSOS = Wait.clickable(driver,currentSosField);
        WebElement custRef = Wait.clickable(driver,custRefField);
        
        //Assert all elements are displayed
        AssertJUnit.assertTrue("Order Status Page: Order No Field not displayed correctly",orderNo.isDisplayed());
        AssertJUnit.assertTrue("Order Status: Order No Field not displayed correctly",custName.isDisplayed());
        AssertJUnit.assertTrue("Order Status: Order Stage Field not displayed correctly",orderStage.isDisplayed());
        AssertJUnit.assertTrue("Order Status: Request Type Field not displayed correctly",requestType.isDisplayed());
        AssertJUnit.assertTrue("Order Status: Business Principal Field not displayed correctly",busPrinc.isDisplayed());
        AssertJUnit.assertTrue("Order Status: Hub Field not displayed correctly",hub.isDisplayed());
        AssertJUnit.assertTrue("Order Status: Requester Field not displayed correctly",requestor.isDisplayed());
        AssertJUnit.assertTrue("Order Status: Scenario Field not displayed correctly",scenario.isDisplayed());
        AssertJUnit.assertTrue("Order Status: Order From Date Field not displayed correctly",orderFrom.isDisplayed());
        AssertJUnit.assertTrue("Order Status: Order To Date Field not displayed correctly",orderTo.isDisplayed());
        AssertJUnit.assertTrue("Order Status: FCE Name Field not displayed correctly",fce.isDisplayed());
        AssertJUnit.assertTrue("Order Status: Fabric Reference Field not displayed correctly",fabRef.isDisplayed());
        AssertJUnit.assertTrue("Order Status: Shade Code Field not displayed correctly",shadeCode.isDisplayed());
        AssertJUnit.assertTrue("Order Status: Current SOS Field not displayed correctly",currentSOS.isDisplayed());    
        
        //Check pagination
        CommonTask.checkPagination(driver);
    }
    
    public CCE_OrderStatusPage pressListOrders() {
        //Wait for element to be clickable
        WebElement listOrders = Wait.clickable(driver,listOrdersButton);
        
        //Submit form
        driver.findElement(filterForm).submit();
        
        return this;
    }
    
    public CCE_OrderStatusPage listOrders() {
        //Alternative method to list orders (using a click, not a submit)
        WebElement element = Wait.clickable(driver,listOrdersButton);
        
        Actions action = new Actions(driver);
        action.moveToElement(element).build().perform();
        
        action.click().build().perform();
        
        return new CCE_OrderStatusPage(driver);
    }
    
    public CCE_OrderStatusPage pressReset() {
        WebElement reset = Wait.clickable(driver,resetButton); 
        reset.click();
        return this;
    }
    
    public Ecomm_ExportDownloadPage pressExport() {
        WebElement export = Wait.clickable(driver,exportButton);
        
        Actions action = new Actions(driver);
        action.click(export).build().perform();
        
        return new Ecomm_ExportDownloadPage(driver);
    }
    
    //Line numbers start from 2
    public CCE_OrderViewPage pressView(int lineNumber) {
        //Form locator
        By viewButtonLocator = By.cssSelector("#content > div.flexi-grid > table > tbody > tr:nth-child("+lineNumber+") > td:nth-child(16) > a");
        //Wait for button to be clickable
        WebElement viewBtn = Wait.clickable(driver,viewButtonLocator);
        //Click button
        viewBtn.click();
        
        return new CCE_OrderViewPage(driver);
    }
    
    public CCE_OrderStatusPage waitForMessage() {
        WebElement wait = Wait.clickable(driver,flashMessage);
        return this;
    }
    
    public void waitForElement() {
        WebElement wait = Wait.clickable(driver,exportButton);
    }

    By flashmessage = By.cssSelector("#flashMessage");
    public String getTextFromFlash() {
        String a = driver.findElement(flashmessage).getText();
        String[] str = a.split(" < ");
        String two = str[1];
        String[] str2 = two.split(" > ");
        String three = str2[1];
        System.out.println(three);
        return three;

    }
    
    public String getOrderStage(String orderNo) {
        
        if (!checkForRecords()) {
            return "not found";
        }

        //By headerLocator = By.cssSelector("#content > div.flexi-grid > table > tbody > tr:nth-child(1) > th:nth-child(5)");
        //WebElement header = Wait.visible(driver, headerLocator);
        //AssertJUnit.assertTrue("Order Status Page: Order No. column has moved, update locators",header.getText().trim().equals("Order No."));
        
        By recordsField = By.cssSelector("#content > div.flexi-grid > dl > dt > span.left");
        int count = getRecordCount(recordsField);
        int tableCount = (count > 10) ? 10: count;
        
        for (int i = 2; i < (tableCount+2); i++) {
            
            By orderNoLocator = By.cssSelector("#content > div.flexi-grid > table > tbody > tr:nth-child("+i+") > td:nth-child(5)");            
            WebElement cell = Wait.presence(driver,orderNoLocator);
            
            if (cell.getText().trim().equals(orderNo)) {
                
                By stageHeaderLocator = By.cssSelector("#content > div.flexi-grid > table > tbody > tr:nth-child(1) > th:nth-child(12)");
                WebElement stageHeader = driver.findElement(stageHeaderLocator);
                
                AssertJUnit.assertTrue("Order Status Page: Order Stage column has moved, update locators",stageHeader.getText().equals("Order Stage"));
        
                By orderStageLocator = By.cssSelector("#content > div.flexi-grid > table > tbody > tr:nth-child("+i+") > td:nth-child(12)");
                
                return driver.findElement(orderStageLocator).getText();
            }     
        }
        return "not found";
    }
    
    public String getOrderNo(String PO) {
        
        for (int i = 2; i < 6; i++) {
            CCE_OrderViewPage viewPage = pressView(i);
            viewPage.switchTo();
            viewPage.waitForContent();
            
            String custRef = viewPage.getCustomerRef().trim();
            System.out.println("Cust ref: " + custRef);
            System.out.println("PO: " + PO);
            
            if (PO.equals(custRef)) {
                String orderNo = viewPage.getOrderNumber();
                viewPage.closeView();
                viewPage.waitForInvisibility();
                return orderNo;
            }
            viewPage.closeView();
            viewPage.waitForInvisibility();
        }    
        return null;
    }
    
    public String getCurrentSOS(String orderNo) {
        for (int i = 2; i < 8; i++) {
            By orderNoCell = By.cssSelector("#content > div.flexi-grid > table > tbody > tr:nth-child("+i+") > td:nth-child(5)");
            
            WebElement cell = Wait.visible(driver,orderNoCell);
            
            if (cell.getText().equals(orderNo)) {
                By currentSOSCell = By.cssSelector("#content > div.flexi-grid > table > tbody > tr:nth-child("+i+") > td:nth-child(10)");
                return driver.findElement(currentSOSCell).getText();
            }
            
        }
        
        return "not found";
    }
    
    public String getOrderNoAlert() {
        //Returns the order no. output after draft creation in the alert (flash message)      
        WebElement element = Wait.visible(driver, flashMessage);
        String text = element.getText();
        
        return text.substring(text.indexOf("<")+1, text.indexOf(">")).trim();
    }
    
}
