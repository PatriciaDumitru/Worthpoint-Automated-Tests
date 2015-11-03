
package PageObjects;

import AutomationFramework.CommonTask;
import AutomationFramework.DataItems;
import static PageObjects.WBA_BasePage.driver;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


public class Ecomm_OrderConfirmationPage extends WBA_BasePage {

    //Heading Locators
    static By orderConfHeadingLocator = By.cssSelector("#content > div > h1");
    static By custInfoHeadingLocator = By.cssSelector("#BulkOrderOrderConfirmForm > div:nth-child(3) > div.tbl-title > h1");
    
    //Field heading locators
    static By custNameHeadingLocator = By.cssSelector("#BulkOrderOrderConfirmForm > div:nth-child(3) > table > tbody > tr:nth-child(1) > th:nth-child(1) > label");
    static By creationDateHeadingLocator = By.cssSelector("#BulkOrderOrderConfirmForm > div:nth-child(3) > table > tbody > tr:nth-child(1) > th:nth-child(3) > label");
    static By customerPoNoHeadingLocator = By.cssSelector("#BulkOrderOrderConfirmForm > div:nth-child(4) > table > tbody > tr:nth-child(1) > th:nth-child(1) > label");
    static By requestorNameHeadingLocator = By.cssSelector("#BulkOrderOrderConfirmForm > div:nth-child(4) > table > tbody > tr:nth-child(2) > th:nth-child(1) > label");
    static By shipToPartyHeadingLocator = By.cssSelector("#BulkOrderOrderConfirmForm > div:nth-child(4) > table > tbody > tr:nth-child(3) > th > label");
    static By buyersHeadingLocator = By.cssSelector("#BulkOrderOrderConfirmForm > div:nth-child(4) > table > tbody > tr:nth-child(4) > th:nth-child(1) > label");
    static By subAccountHeadingLocator = By.cssSelector("#BulkOrderOrderConfirmForm > div:nth-child(4) > table > tbody > tr:nth-child(5) > th > label");
    static By netValueHeadingLocator = By.cssSelector("#BulkOrderOrderConfirmForm > div:nth-child(4) > table > tbody > tr:nth-child(1) > th:nth-child(3) > label");
    static By shipToAddressHeadingLocator = By.cssSelector("#BulkOrderOrderConfirmForm > div:nth-child(4) > table > tbody > tr:nth-child(2) > th:nth-child(3) > label");
    static By currencyHeadingLocator = By.cssSelector("#BulkOrderOrderConfirmForm > div:nth-child(4) > table > tbody > tr:nth-child(4) > th:nth-child(3) > label");
    
    //Field locators
    static By custNameField = By.cssSelector("#BulkOrderOrderConfirmForm > div:nth-child(3) > table > tbody > tr:nth-child(1) > td:nth-child(2)");
    static By custUploadPOField = By.id("po_number_0");
    static By customerPOField = By.id("BulkOrderPoNumber");
    static By uploadCustomerPOField = By.id("po_number_0");
    static By creationDateField = By.cssSelector("#BulkOrderOrderConfirmForm > div:nth-child(3) > table > tbody > tr:nth-child(1) > td:nth-child(4)");
    static By requestorField = By.id("requester_id_0");
    static By shipToPartyField = By.id("ship_to_party_id_0");
    static By buyersField = By.cssSelector("#s2id_BuyerId_0 > a > span.select2-chosen");
    static By subAccountField = By.id("payer_id0");
    static By netValueField = By.id("net_value_0");
    static By shipToAddressField = By.id("ship_to_party_address_0");
    static By currencyField = By.id("currency_0");
    
    //Table cell locators
    static By yourMatNumCell = By.cssSelector("#remove_0 > td:nth-child(4)");
    static By orderedQtyCell = By.cssSelector("#remove_0 > td:nth-child(6)");
    static By requiredDateCell = By.cssSelector("#remove_0 > td:nth-child(13)");
      
    //Button locators
    static By submitButtonLocator = By.id("submit1");
    static By cancelButtonLocator = By.id("cancel1");
    static By saveDraftButtonLocator = By.id("drafts");
    static By backButtonLocator = By.id("backLink");
    static By sendForApprovalButton = By.cssSelector("#BulkOrderOrderConfirmForm > div:nth-child(7) > div:nth-child(2) > input");
    
    static By linesWithErrorButton = By.cssSelector("#BulkOrderOrderConfirmForm > div:nth-child(4) > div.grid_12 > a");
    static By errorLine = By.cssSelector("#BulkOrderLineViewUplodErrorListForm > div.grid_12 > div.grid_12 > div.tbl-toggle > div.scrollTableContainer.scroll-pane > table > tbody > tr:nth-child(1) > td:nth-child(6)");
    
    //Edit order overlay locator
    static By editOverlayLocator = By.id("TB_window");
    
    static By flashMessageLocator = By.id("flashMessage");
    
    static By frameLocator = By.id("TB_iframeContent");
    
    
    public Ecomm_OrderConfirmationPage(WebDriver passedDriver) {
        super(passedDriver);
    }
    
    public WebElement getOrderConfHeading() {
        //find and return element
        return driver.findElement(orderConfHeadingLocator);
    }
    
    public WebElement getCustInfoHeading() {
        //find and return element
        return driver.findElement(custInfoHeadingLocator);
    }
    
    public WebElement getCustNameHeading() {
        //find and return element
        return driver.findElement(custNameHeadingLocator);
    }
    
    public WebElement getCustNameField() {
        //find and return element
        return driver.findElement(custNameField);
    }
    
    public WebElement getCreationDateHeading() {
        //find and return element
        return driver.findElement(creationDateHeadingLocator);
    }
    
    public WebElement getCreationDateField() {
        //find and return element
        return driver.findElement(creationDateField);
    }
    
    public WebElement getCustPoNumHeading() {
        //find and return element
        return driver.findElement(customerPoNoHeadingLocator);
    }
    
    public WebElement getUploadCustPOField() {
        return driver.findElement(custUploadPOField);
    }
    
    public WebElement getCustPoField() {
        //find and return element
        return driver.findElement(customerPOField);
    }
    
    public WebElement getCustUploadPOField() {
        return driver.findElement(uploadCustomerPOField);
    }
    
    public WebElement getRequestorHeading() {
        //find and return element
        return driver.findElement(requestorNameHeadingLocator);
    }
    
    public WebElement getRequestorField() {
        //find and return element
        return driver.findElement(requestorField);
    }
    
    public WebElement getShipToPartyHeading() {
        //find and return element
        return driver.findElement(shipToPartyHeadingLocator);
    }
    
    public WebElement getShipToPartyField() {
        //find and return element
        return driver.findElement(shipToPartyField);
    }
    
    public WebElement getBuyersHeading() {
        //find and return element
        return driver.findElement(buyersHeadingLocator);
    }
    
    public WebElement getBuyersField() {
        //find and return element
        return driver.findElement(buyersField);
    }
    
    public WebElement getSubAccountHeading() {
        //find and return element
        return driver.findElement(subAccountHeadingLocator);
    }
    
    public WebElement getSubAccountField() {
        //find and return element
        return driver.findElement(subAccountField);
    }
    
    public WebElement getNetValueHeading() {
        //find and return element
        return driver.findElement(netValueHeadingLocator);
    }
    
    public WebElement getNetValueField() {
        //find and return element
        return driver.findElement(netValueField);
    }
    
    public WebElement getCurrencyHeading() {
        //find and return element
        return driver.findElement(currencyHeadingLocator);
    }
    
    public WebElement getCurrencyField() {
        //find and return element
        return driver.findElement(currencyField);
    }
    
    public WebElement getShipToAddressHeading() {
        //find and return element
        return driver.findElement(shipToAddressHeadingLocator);
    }
    
    public WebElement getShipToAddressField() {
        //find and return element
        return driver.findElement(shipToAddressField);
    }
    
    public WebElement getYourMatNumCell() {
        //find and return element
        return driver.findElement(yourMatNumCell);
    }
    
    public WebElement getOrderedQtyCell() {
        //find and return element
        return driver.findElement(orderedQtyCell);
    }
    
    public WebElement getRequiredDateCell() {
        return driver.findElement(requiredDateCell);
    }
    
    public String getCustomerName() {
        return getCustNameField().getText();
    }
    
    public String getSubAccount() {
        WebElement wait = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(getSubAccountField()));
        Select select = new Select(getSubAccountField());
        return select.getFirstSelectedOption().getText();
    }
    
    public String getPONumber() {
        return getCustPoField().getText();
    }
    
    public String getUploadPONumber() {
        return getUploadCustPOField().getAttribute("value");
    }
    
    public String getRequester() {
        Boolean waitForSelection = new WebDriverWait(driver,DataItems.shortWait).until(CommonTask.selectionToBePresent(requestorField));
        Select select = new Select(driver.findElement(requestorField));
        return select.getFirstSelectedOption().getText();
    }
    
    public String getYourMatNum() {
        return getYourMatNumCell().getText();
    }
    
    public int getOrderedQty() {
        return Integer.valueOf(getOrderedQtyCell().getText());
    }
    
    public String getRequiredDate() {
        return getRequiredDateCell().getText();
    }
    
    public WebElement getCancelButton() {
        return driver.findElement(cancelButtonLocator);
    }
    
    public String getShipToParty() {
        Boolean waitForSelection = new WebDriverWait(driver,DataItems.shortWait).until(CommonTask.selectionToBePresent(shipToPartyField));
        Select select = new Select(driver.findElement(shipToPartyField));
        return select.getFirstSelectedOption().getText();
    }
    
    public String getBuyers() {
        WebElement waitForClickable = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(buyersField));
        return driver.findElement(buyersField).getText();
    }
    
    public WebElement getSendForApprovalButton() {
        return driver.findElement(sendForApprovalButton);
    }
    
    public Ecomm_OrderEditPage pressEditLine(int lineNumber) {
        //Create locator for edit button in correct line. Line numbers start from 0
        By editBtnLocator = By.cssSelector("#remove_"+lineNumber+" > td:nth-child(1) > a");
        //New action to press button
        WebElement waitForClickable = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(editBtnLocator));
        Actions clickEdit = new Actions(driver);
        clickEdit.click(driver.findElement(editBtnLocator)).build().perform();
        //Wait for overlay to load
        WebElement waitForOverlay = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.visibilityOf(driver.findElement(editOverlayLocator)));
        return new Ecomm_OrderEditPage(driver);
    }
    
    public Ecomm_OutstandingOrdersPage pressSubmit() {
        //Wait for element to be clickable
        WebElement waitForClickable = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(submitButtonLocator));
        driver.findElement(submitButtonLocator).click();  
        
        try {
            //wait for alert and confirm
            Alert alert = new WebDriverWait(driver,DataItems.longWait).until(ExpectedConditions.alertIsPresent());
            alert.accept();
        } catch (Exception e) {
            
        }

        //Sometimes unexpected alerts appear. Catch these and accept by default
        boolean alertPresence;
        try {
            Alert alert2 = new WebDriverWait(driver,DataItems.shorterWait).until(ExpectedConditions.alertIsPresent());
            driver.switchTo().alert();
            String alertText = alert2.getText();
            alertPresence = true;
            System.out.println("Additional alert appeared while confirming: " + alertText);
        } catch (Exception e) {
            alertPresence = false;
        }

        if (alertPresence) {
            driver.switchTo().alert().accept();
        }
        
        return new Ecomm_OutstandingOrdersPage(driver);
    }
    
    public Ecomm_UploadProcessPage pressSubmitExpectingFailure() {
        //Wait for element to be clickable
        WebElement waitForClickable = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.visibilityOfElementLocated(submitButtonLocator));
        driver.findElement(submitButtonLocator).click();  
        
        Alert alert2 = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.alertIsPresent());
        alert2.accept();
        
        try {
            //wait for alert and confirm
            Alert alert = new WebDriverWait(driver,DataItems.longWait).until(ExpectedConditions.alertIsPresent());
            alert.accept();
        } catch (Exception e) {
            
        }
        
        return new Ecomm_UploadProcessPage(driver);
        
    }
    
    public Ecomm_OrderConfirmationPage pressSubmitExpectingReturn() {
        //Wait for element to be clickable
        WebElement waitForClickable = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.visibilityOfElementLocated(submitButtonLocator));
        driver.findElement(submitButtonLocator).click();  
        
        Alert alert = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.alertIsPresent());
        alert.accept();
        
        try {
            Alert alert2 = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.alertIsPresent());
            alert2.accept();
        } catch (TimeoutException t) {
            
        }
        
        return new Ecomm_OrderConfirmationPage(driver);
    }
    
    public Ecomm_OutstandingOrderDraftPage pressSaveDraft() {
        WebElement waitForButton = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(saveDraftButtonLocator));
        driver.findElement(saveDraftButtonLocator).click();
        
        Alert alert = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.alertIsPresent());
        alert.accept();
        
        return new Ecomm_OutstandingOrderDraftPage(driver);
    }
    
    public Ecomm_OutstandingUploadDraftPage pressSaveUploadDraft() {
        WebElement waitForButton = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(saveDraftButtonLocator));
        driver.findElement(saveDraftButtonLocator).click();
        
        Alert alert = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.alertIsPresent());
        alert.accept();
        
        return new Ecomm_OutstandingUploadDraftPage(driver);
    }
    
    public Ecomm_PendingApprovalListPage pressSendForApproval() {
        WebElement wait = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(sendForApprovalButton));
        driver.findElement(sendForApprovalButton).click();
        return new Ecomm_PendingApprovalListPage(driver);
    }
    
    public WebElement waitForError() {
        return new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.visibilityOfElementLocated(flashMessageLocator));
    }
    
    public void waitForElement() {
        WebElement wait = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.visibilityOfElementLocated(orderConfHeadingLocator));
    }
    
    public Ecomm_ManualEntryPage pressCancel() {
        WebElement waitForClickable = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(cancelButtonLocator));
        driver.findElement(cancelButtonLocator).click();
        
        return new Ecomm_ManualEntryPage(driver);
    }
    
    public Ecomm_UploadOrderPage pressCancelUpload() {
        WebElement waitForClickable = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(cancelButtonLocator));
        driver.findElement(cancelButtonLocator).click();
        
        return new Ecomm_UploadOrderPage(driver);
    }
    
    public Ecomm_OrderConfirmationPage setRequestor(String requester) throws InterruptedException {
        //Wait for field to be clickable
        WebElement waitForField = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(requestorField));
        
        if (requester.equals("Select")) {
            CommonTask.clearDropDownField(driver, requestorField);
        } else {
            CommonTask.setDropDownField(driver,requestorField,requester);
        }     
        
        return new Ecomm_OrderConfirmationPage(driver);
    }
    
    public Ecomm_OrderConfirmationPage setShipToParty(String shipToParty) throws InterruptedException {
        WebElement wait = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(shipToPartyField));
        
        if (shipToParty.equals("Select")) {
            CommonTask.clearDropDownField(driver, shipToPartyField);
        } else {
            CommonTask.setDropDownField(driver, shipToPartyField, shipToParty);
        }
        
        return this;
    }
    
    public Ecomm_OutstandingOrderDraftPage pressCancelToDrafts() {
        WebElement wait = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(cancelButtonLocator));
        driver.findElement(cancelButtonLocator).click();
        return new Ecomm_OutstandingOrderDraftPage(driver);
    }
    
    public boolean checkSendForApproval() {
        By approvalButton = By.cssSelector("#BulkOrderOrderConfirmForm > div:nth-child(7) > div:nth-child(2) > input");
        
        WebElement wait = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(approvalButton));
        
        if (driver.findElement(approvalButton).getAttribute("value").equals("Send for Approval")) {
            return true;
        } else {
            return false;
        }
    }
    
    public boolean viewErrors() {
        
        boolean errors;
        
        try {
            WebElement wait = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(linesWithErrorButton));
            errors = true;
            driver.findElement(linesWithErrorButton).click();
            WebDriver wait2 = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameLocator));
            WebElement wait3 = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(errorLine));
            System.out.println(driver.findElement(errorLine).getText());
        } catch (TimeoutException e) {
            errors = false;
        }
        return errors;
    }
    
    public boolean errorAppears() {
        try {
            WebElement flashMessage = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.visibilityOfElementLocated(flashMessageLocator));
            System.out.println("Error: " + flashMessage.getText());
            return true;
        } catch (TimeoutException t) {
            System.out.println(t.toString());
            return false;
        }
        
    }
            
    
}
