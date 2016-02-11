
package PageObjects;

import AutomationFramework.CommonTask;
import AutomationFramework.DataItems;
import AutomationFramework.Wait;
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
import org.testng.AssertJUnit;


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
    static By adjQtyCell = By.xpath("//*[contains(@style,'background-color:red')]");
    static By requiredDateCell = By.cssSelector("#remove_0 > td:nth-child(13)");
    static By coatsMaterialCell = By.cssSelector("#remove_0 > td:nth-child(5)");
    static By contractPOCell = By.cssSelector("#order_table > table > thead > tr:nth-child(1) > th:nth-child(15) > label");
    static By SAPContractNoCell = By.cssSelector("#remove_0 > td:nth-child(14)");
    static By lineRefCell = By.cssSelector("#order_table > table > thead > tr:nth-child(1) > th:nth-child(14) > label");
    static By uomCell = By.cssSelector("#remove_0 > td:nth-child(8)");  
    
    //Button locators
    static By submitButtonLocator = By.id("submit1");
    static By cancelButtonLocator = By.id("cancel1");
    static By saveDraftButtonLocator = By.id("drafts");
    static By backButtonLocator = By.id("backLink");
    static By sendForApprovalButton = By.cssSelector("#BulkOrderOrderConfirmForm > div:nth-child(7) > div:nth-child(2) > input");
    
    static By linesWithErrorButton = By.cssSelector("#BulkOrderOrderConfirmForm > div:nth-child(4) > div.grid_12 > a");
    static By lineWithErrorButton2 = By.linkText("Line with Error");

    static By errorLine = By.cssSelector("#BulkOrderLineViewUplodErrorListForm > div.grid_12 > div.grid_12 > div.tbl-toggle > div.scrollTableContainer.scroll-pane > table > tbody > tr:nth-child(1) > td:nth-child(6)");
    
    //Edit order overlay locator
    static By editOverlayLocator = By.id("TB_window");
    
    static By flashMessageLocator = By.id("flashMessage");
    
    static By frameLocator = By.id("TB_iframeContent");
    
    
    public Ecomm_OrderConfirmationPage(WebDriver passedDriver) {
        super(passedDriver);
    }
    
    public WebElement getCustNameField() {
        //find and return element
        return driver.findElement(custNameField);
    }
    
    public WebElement getUploadCustPOField() {
        return driver.findElement(custUploadPOField);
    }
    
    public WebElement getCustPoField() {
        //find and return element
        return Wait.visible(driver,customerPOField);
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
    
    public WebElement getYourMatNumCell() {
        //find and return element
        return driver.findElement(yourMatNumCell);
    }
    
    public WebElement getOrderedQtyCell() {
        //find and return element
        return driver.findElement(orderedQtyCell);
    }
    
    public WebElement getAdjustedQtyCell() {
        WebElement cell = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.visibilityOfElementLocated(adjQtyCell));
        return cell;
    }
    
    public WebElement getRequiredDateCell() {
        return driver.findElement(requiredDateCell);
    }
    
    public boolean findContractPOCell() {
        try {
            WebElement element = Wait.visible(driver,contractPOCell);
            return element.getText().contains("Contract PO");
        } catch (Exception e) {
            return false;
        }

    }
    
    public boolean findSAPContractNoCell() {
        
        try {
            WebElement element = Wait.visible(driver,SAPContractNoCell);
            return element.getText().contains("SAP Contract");
        } catch (Exception e) {
            return false;
        }
        
        
    }
    
    public boolean findLineRefCell() {
        try {
            WebElement element = Wait.visible(driver,lineRefCell);
            return element.getText().contains("Line");
        } catch (Exception e) {
            return false;
        }

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
        WebElement wait = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.visibilityOf(getYourMatNumCell()));
        return getYourMatNumCell().getText();
    }
    
    public int getOrderedQty() {
        return Integer.valueOf(getOrderedQtyCell().getText());
    }
    
    public int getAdjustedQty() {
        String qty = getAdjustedQtyCell().getText();
        return Integer.valueOf(qty);
    }
    
    public String getRequiredDate() {
        return getRequiredDateCell().getText();
    }
    
    public String getCoatsMaterial() {
        WebElement cell = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.visibilityOfElementLocated(coatsMaterialCell));
        return cell.getText();
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
    
    public Ecomm_OutstandingOrdersPage pressSubmit() {
        //Wait for element to be clickable
        WebElement submit = Wait.clickable(driver,submitButtonLocator);
        submit.click();  
        
        try {
            //wait for alert and confirm
            Alert alert = Wait.alert(driver);
            alert.accept();
        } catch (Exception e) {
            
        }

        //Sometimes unexpected alerts appear. Catch these and accept by default
        boolean alertPresence;
        try {
            Alert alert2 = Wait.alert(driver);
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
        WebElement element = Wait.clickable(driver,submitButtonLocator);
        element.click();
        
        try {
            //wait for alert and confirm
            Alert alert = Wait.alert(driver,DataItems.longWait);
            alert.accept();
        } catch (Exception e) {
            
        }
        
        return new Ecomm_UploadProcessPage(driver);
        
    }
    
    public Ecomm_OrderConfirmationPage pressSubmitExpectingReturn() {
        //Wait for element to be clickable
        WebElement submit = Wait.visible(driver,submitButtonLocator);
        submit.click();  
        
        Alert alert = Wait.alert(driver);
        alert.accept();
        
        try {
            Alert alert2 = Wait.alert(driver);
            alert2.accept();
        } catch (TimeoutException t) {
            
        }
        
        return new Ecomm_OrderConfirmationPage(driver);
    }
    
    public Ecomm_OutstandingOrderDraftPage pressSaveDraft() {
        WebElement button = Wait.clickable(driver,saveDraftButtonLocator);
        button.click();
        
        Alert alert = Wait.alert(driver);
        alert.accept();
        
        return new Ecomm_OutstandingOrderDraftPage(driver);
    }
    
    public Ecomm_OutstandingUploadDraftPage pressSaveUploadDraft() {
        WebElement save = Wait.clickable(driver,saveDraftButtonLocator);
        save.click();
        
        Alert alert = Wait.alert(driver);
        alert.accept();
        
        return new Ecomm_OutstandingUploadDraftPage(driver);
    }
    
    public Ecomm_PendingApprovalListPage pressSendForApproval() {
        WebElement button = Wait.clickable(driver,sendForApprovalButton);
        button.click();
        
        try {
            Alert alert = Wait.alert(driver);
            alert.accept();
        } catch (TimeoutException t) {
            
        }
        
        return new Ecomm_PendingApprovalListPage(driver);
    }
    
    public WebElement waitForError() {
        return Wait.visible(driver,flashMessageLocator);
    }
    
    public void waitForElement() {
        WebElement wait = Wait.visible(driver,orderConfHeadingLocator);
    }
    
    public Ecomm_ManualEntryPage pressCancel() {
        WebElement button = Wait.clickable(driver,cancelButtonLocator);
        button.click();
        
        return new Ecomm_ManualEntryPage(driver);
    }
    
    public Ecomm_UploadOrderPage pressCancelUpload() {
        WebElement cancel = Wait.clickable(driver,cancelButtonLocator);
        cancel.click();
        
        return new Ecomm_UploadOrderPage(driver);
    }
    
    public Ecomm_OrderConfirmationPage setRequestor(String requester) throws InterruptedException {
        //Wait for field to be clickable
        WebElement waitForField = Wait.clickable(driver,requestorField);
        
        if (requester.equals("Select")) {
            CommonTask.clearDropDownField(driver, requestorField);
        } else {
            CommonTask.setDropDownField(driver,requestorField,requester);
        }     
        
        return new Ecomm_OrderConfirmationPage(driver);
    }
    
    public Ecomm_OrderConfirmationPage setShipToParty(String shipToParty) throws InterruptedException {
        WebElement wait = Wait.clickable(driver,shipToPartyField);
        
        if (shipToParty.equals("Select")) {
            CommonTask.clearDropDownField(driver, shipToPartyField);
        } else {
            CommonTask.setDropDownField(driver, shipToPartyField, shipToParty);
        }
        
        return this;
    }
    
    public Ecomm_OutstandingOrderDraftPage pressCancelToDrafts() {
        WebElement cancel = Wait.clickable(driver,cancelButtonLocator);
        cancel.click();
        return new Ecomm_OutstandingOrderDraftPage(driver);
    }
    
    public boolean checkSendForApproval() {
        By approvalButton = By.cssSelector("#BulkOrderOrderConfirmForm > div:nth-child(7) > div:nth-child(2) > input");
        
        WebElement wait = Wait.clickable(driver,approvalButton);
        
        if (driver.findElement(approvalButton).getAttribute("value").equals("Send for Approval")) {
            return true;
        } else {
            return false;
        }
    }
    
    public boolean viewErrors() {
        
        boolean errors;
        
        try {
           WebElement element = Wait.clickable(driver,linesWithErrorButton);
            errors = true;
        } catch (TimeoutException e) {
            errors = false;
        }
        
        if (errors) {
            WebElement element = driver.findElement(linesWithErrorButton);
            
            Actions action = new Actions(driver);
            action.moveToElement(element).build().perform();
            action.click().build().perform();
            
            WebDriver wait2 = Wait.frame(driver,frameLocator);
            WebElement error = Wait.clickable(driver,errorLine);
            System.out.println(error.getText());
        }
        
        return errors;
    }
    
    public boolean errorAppears() {
        try {
            WebElement flashMessage = Wait.visible(driver,flashMessageLocator);
            System.out.println("Error: " + flashMessage.getText());
            return true;
        } catch (TimeoutException t) {
            System.out.println(t.toString());
            return false;
        }
        
    }
    
    public boolean checkContractMaterialDetails() {
        //When a contract order is made without material details, the details are copied from SAP. This method checks the details are as expected
        
        WebElement materialCell = Wait.visible(driver,coatsMaterialCell);
        String[] materialParts = materialCell.getText().split("-");
        
        WebElement uomElement = Wait.visible(driver,uomCell);
        String uom = uomElement.getText();
        
        WebElement sapNo = Wait.visible(driver,SAPContractNoCell);
        String sapConNo = sapNo.getText();
        
        AssertJUnit.assertTrue("Order Confirmation Page: For contract order, Coats article does not appear in line details as expected",materialParts[0].equals(DataItems.conOrdArticle));
        AssertJUnit.assertTrue("Order Confirmation Page: For contract order, Coats shade does not appear in line details as expected",materialParts[1].equals(DataItems.conOrdShadeCode));
        AssertJUnit.assertTrue("Order Confirmation Page: For contract order, UOM does not appear in line details as expected",uom.equals("Cone"));
        //AssertJUnit.assertTrue("Order Confirmation Page: For contract order, SAP Contract No. does not appear in line details as expected",sapConNo.equals(DataItems.conOrdPO));
        
        return true;
    }

}
