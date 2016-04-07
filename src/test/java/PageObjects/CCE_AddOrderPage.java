
package PageObjects;

import AutomationFramework.CommonTask;
import AutomationFramework.DataItems;
import AutomationFramework.Wait;
import java.io.IOException;
import org.testng.AssertJUnit;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class CCE_AddOrderPage extends WBA_BasePage {
   
    //Locators
    public static By flashMessageLocator = By.id("flashMessage");
    static By shipToPartyField = By.id("SampleOrderShipToPartyId");
    static By busPrincipalField = By.id("SampleOrderBusinessPrincipalId");
    static By lightSource1Field = By.id("SampleOrderLsPrimaryId");
    static By lightSource2Field = By.id("SampleOrderLsSecondaryId");
    static By lightSource3Field = By.id("SampleOrderLsThirdId");
    static By newBuyerLink = By.cssSelector("#SampleOrderAddForm > div:nth-child(3) > table > tbody > tr:nth-child(1) > td:nth-child(3) > a");
    static By creationDateField = By.cssSelector("#SampleOrderAddForm > div:nth-child(2) > table > tbody > tr:nth-child(1) > td:nth-child(4)");
    
    //Locators for returning details
    static By customerNameField = By.cssSelector("#SampleOrderEditForm > div:nth-child(2) > table > tbody > tr:nth-child(1) > td:nth-child(2)");
    static By requesterField = By.cssSelector("#SampleOrderEditForm > div:nth-child(2) > table > tbody > tr:nth-child(2) > td:nth-child(2)");
    static By shipToField = By.id("ship_to_party_name");
    static By articleField2 = By.cssSelector("#s2id_SampleOrderLine0ArticleId > a > span.select2-chosen");
    
    //Locators for fields in line 0, to check they are clickable
    static By articleField = By.id("SampleOrderLine0ArticleId");
    static By brandField = By.id("SampleOrderLine0BrandId");
    static By ticketField = By.id("SampleOrderLine0TicketId");
    static By shadeCodeField = By.id("s2id_SampleOrderLine0ShadeId");
    static By copButton = By.id("SampleOrderLine0MumTypeId10");
    static By coneButton = By.id("SampleOrderLine0MumTypeId20");
    static By viconeButton = By.id("SampleOrderLine0MumTypeId30");
    static By colMatButton = By.id("SampleOrderLine0RequestTypeId1");
    static By sewingButton = By.id("SampleOrderLine0RequestTypeId2");
    static By dirEnYesButton = By.id("SampleOrderLine0IsDirectEnrich1");
    static By dirEnNoButton = By.id("SampleOrderLine0IsDirectEnrich0");
    static By purposeField = By.id("SampleOrderLine0PurposeTypeId");
    static By requirementsField = By.id("SampleOrderLine0Requirements");
    static By custRefField = By.id("SampleOrderLine0CustomerReference");
    static By fabRefField = By.id("SampleOrderLine0FabricReferenceColourName");
    static By quantityField = By.id("SampleOrderLine0OrderedQuantity");  
    static By quantityFieldLine2 = By.id("SampleOrderLine1OrderedQuantity");
    static By newLineButton = By.id("add_tab");
    static By submitOrderButton = By.id("submit");
    static By pendOrderButton = By.id("pending");
    static By cancelButton = By.cssSelector("#SampleOrderAddForm > div:nth-child(5) > div.actions > ul > li:nth-child(3) > a");
    static By cancelToDraftsButton = By.cssSelector("#SampleOrderEditForm > div:nth-child(4) > div.actions > ul > li:nth-child(3) > a"); //cancel button has different locator when reached from draft
    
    //Fields which appear upon click "Yes" to Direct Enrich
    static By customerSwatchYes = By.id("SampleOrderLine0IsMeasureable1");
    static By customerSwatchNo = By.id("SampleOrderLine0IsMeasureable0");
    static By enrichCommentsField = By.id("SampleOrderLine0FceComments");
    static By enrichCompletedYesButton = By.id("SampleOrderLine0IsOrderCompleted1");
    static By enrichCompletedNoButton = By.id("SampleOrderLine0IsOrderCompleted0");
    static By enrichHubButton = By.id("SampleOrderLine0SosId30");
    static By enrichLabButton = By.id("SampleOrderLine0SosId50");
    static By enrichWHSButton = By.id("SampleOrderLine0SosId40");
    
    public CCE_AddOrderPage(WebDriver passedDriver) {
       super(passedDriver);
    } 
    
    public String getCustomerName() {
        WebElement field = Wait.visible(driver,customerNameField);
        return field.getText();
    }
    
    public String getRequesterName() {
        return driver.findElement(requesterField).getText();
    }
    
    public String getShipToParty() {
        WebElement field = Wait.visible(driver, shipToPartyField);
        return field.getAttribute("text");
    }
    
    public String getArticle() {
        WebElement field = Wait.visible(driver, articleField2);
        return field.getText();
    }
    
    public WebElement getTicketField() {
        return driver.findElement(ticketField);
    }

    public WebElement getShadeCodeField() {
        return driver.findElement(shadeCodeField);
    }
    
    public WebElement getDirEnYesButton() {
        return driver.findElement(dirEnYesButton);
    }
    
    public WebElement getDirEnNoButton() {
        return driver.findElement(dirEnNoButton);
    }

    public WebElement setCustSwatchYesButton() {
        return driver.findElement(customerSwatchYes);
    }

    public WebElement setCustSwatchNoButton() {
        return driver.findElement(customerSwatchNo);
    }
    
    public WebElement getPurposeField() {
        return driver.findElement(purposeField);
    }
    
    public WebElement getQuantityField() {
        return driver.findElement(quantityField);
    }
     
    public WebElement getEnrichHubButton() {
        return driver.findElement(enrichHubButton);
    }
    
    public WebElement getEnrichLabButton() {
        return driver.findElement(enrichLabButton);
    }
    
    public WebElement getEnrichWHSButton() {
        return driver.findElement(enrichWHSButton);
    }

    public CCE_AddOrderPage setDirectEnrichYes() throws InterruptedException {
        CommonTask.setCheckBox(driver, dirEnYesButton);
        return this;
    }

    public CCE_AddOrderPage setDirectEnrichNo() throws InterruptedException {
        CommonTask.setCheckBox(driver, dirEnNoButton);
        return this;
    }

    public CCE_AddOrderPage setCustomerSwatchYes() throws InterruptedException {
        CommonTask.setCheckBox(driver, customerSwatchYes);
        return this;
    }

    public CCE_AddOrderPage setCustomerSwatchNo() throws InterruptedException {
        CommonTask.setCheckBox(driver, customerSwatchNo);
        return this;
    }

    public CCE_AddOrderPage setShipToParty(String shipToParty) throws InterruptedException {
        CommonTask.setDropDownField(driver, shipToPartyField, shipToParty);
        return this;
    }
    
    public CCE_AddOrderPage setBusinessPrincipal(String businessPrincipal) throws InterruptedException {
        CommonTask.setDropDownField(driver, busPrincipalField, businessPrincipal);
        return this;
    }
    
    public CCE_AddOrderPage setArticle(String article, int lineNumber) {
        //Line numbers start from 0
        By articleLocator = By.id("s2id_SampleOrderLine"+lineNumber+"ArticleId");
        CommonTask.setSearchField(driver, articleLocator, article);
        return this;
    }
    
    public CCE_AddOrderPage setBrand(String brand, int lineNumber) throws InterruptedException {
        By brandFieldLocator = By.id("SampleOrderLine"+lineNumber+"BrandId");
        
        CommonTask.setDropDownField(driver, brandFieldLocator, brand);
        
        return this;
    }
    
    public CCE_AddOrderPage setTicket(String ticket, int lineNumber) throws InterruptedException {
        By ticketFieldLocator = By.id("SampleOrderLine"+lineNumber+"TicketId");
        
        CommonTask.setDropDownField(driver, ticketFieldLocator, ticket);
        
        return this;
    }
    
    public CCE_AddOrderPage setShadeCode(String shadeCode,int lineNumber) {
        //Line numbers start from 0
        By shadeCodeLocator = By.id("s2id_SampleOrderLine"+lineNumber+"ShadeId");
        CommonTask.setSearchField(driver,shadeCodeLocator,shadeCode);
        return this;
    }
    
    public CCE_AddOrderPage setMUMType(String mumType, int lineNumber) {
        //line numbers start from 0
        By copLocator = By.id("SampleOrderLine"+lineNumber+"MumTypeId10");
        By coneLocator = By.id("SampleOrderLine"+lineNumber+"MumTypeId20");
        By viconeLocator = By.id("SampleOrderLine"+lineNumber+"MumTypeId30");
                
        switch(mumType) {
            case "Cop": driver.findElement(copLocator).click(); break;
            case "Cone": driver.findElement(coneLocator).click(); break;
            case "Vicone": driver.findElement(viconeLocator).click(); break;
        }
        return this;      
    }
    
    public CCE_AddOrderPage setRequestType(String reqType, int lineNumber) {
        //Line numbers start from 0
        By colMatLocator = By.id("SampleOrderLine"+lineNumber+"RequestTypeId1");
        By sewingLocator = By.id("SampleOrderLine"+lineNumber+"RequestTypeId2");
        
        switch(reqType) {
            case "colMat": driver.findElement(colMatLocator).click(); break;
            case "sewing": driver.findElement(sewingLocator).click(); break;
        }       
        return this;
    }
    
    public CCE_AddOrderPage setPurposeType(String purpType, int lineNumber) throws InterruptedException {
        //Line numbers start from 0
        By purpLocator = By.id("SampleOrderLine"+lineNumber+"PurposeTypeId");
        CommonTask.setDropDownField(driver, purpLocator, purpType);
        
        //Click purpose type field to close select box
        WebElement field = driver.findElement(purpLocator);
        field.click();
        
        return this;
    }
    
    public CCE_AddOrderPage setCustomerRef(int lineNumber) throws IOException {
        //Line numbers start from 0
        By refLocator = By.id("SampleOrderLine"+lineNumber+"CustomerReference");
        
        WebElement element = Wait.clickable(driver,refLocator);
        element.clear();
        
        String PO = CommonTask.generatePO("noncontract");
        DataItems.lastUsedPO = PO;
        CommonTask.setInputField(driver, refLocator,PO);
        return this;
    }
    
    public CCE_AddOrderPage setQuantity(int quantity, int lineNumber) {
        //Line numbers start from 0
        By quantityLocator = By.id("SampleOrderLine"+lineNumber+"OrderedQuantity");
        CommonTask.setInputField(driver, quantityLocator, String.valueOf(quantity));
        
        return this;
    }
    
    public String getCreationDate() {
        return driver.findElement(creationDateField).getText();
    }
    
    public CCE_OrderStatusPage pressSubmit() {
        //Wait for button to be clickable and click
        WebElement submitOrderBtn = Wait.clickable(driver, submitOrderButton);
        
        submitOrderBtn.click();
        
        Alert alert = Wait.alert(driver);
        alert.accept();
        
        return new CCE_OrderStatusPage(driver);
    }
    
    public CCE_AddOrderPage pressSubmitExceeded() {
        //Press submit expecting alert - as the sample quantity limit has been exceeded

        //Wait for button to be clickable and click
        WebElement button = Wait.clickable(driver, submitOrderButton);
        
        button.click();
        
        Alert alert = Wait.alert(driver);
        alert.accept();
        
        try {
            Alert alert2 = Wait.alert(driver);
            System.out.println("Alert appeared: " + alert2.getText());
            alert2.accept();        
        } catch(Exception e) {
            System.out.println("Quantity Exceeded alert did not appear");
        }
        
        return new CCE_AddOrderPage(driver);
    }
    
    public CCE_OrderStatusPage pressPendOrder() {
        
        //Wait for button to be clickable
        WebElement button = Wait.clickable(driver,pendOrderButton);
        button.click();
        
        Alert alert = Wait.alert(driver);
        
        alert.accept();
        
        return new CCE_OrderStatusPage(driver);
        
    }
    
    public CCE_OrderSamplesPage pressCancel() {
        
        WebElement cancel = Wait.visible(driver,cancelButton);
        
        Actions action = new Actions(driver);
        action.moveToElement(cancel).build().perform();
        
        action.click(cancel).build().perform();
        
        return new CCE_OrderSamplesPage(driver);
    }
    
    public CCE_NewBuyerPage pressNewBuyer() {
        WebElement newBuyer = Wait.visible(driver,newBuyerLink);
        newBuyer.click();
        return new CCE_NewBuyerPage(driver);
    }
    
    public CCE_OutstandingDraftPage pressCancelToDrafts() {
        //Press the cancel button, expecting re-direction to oustanding drafts page
        
        WebElement element = Wait.clickable(driver,cancelToDraftsButton);
        
        Actions action = new Actions(driver);
        action.moveToElement(element).build().perform();
        action.click().build().perform();
        
        return new CCE_OutstandingDraftPage(driver);
    }
    
    public CCE_AddOrderPage pressNewLine(int lineNumber) {
        //Wait for button to be clickable
        WebElement newLine = Wait.clickable(driver,newLineButton);

        Actions action = new Actions(driver);
        action.click(newLine).build().perform();
        
        //Wait for new line form to load
        Wait.clickable(driver,quantityFieldLine2);
        
        return this;
    }
    
    public CCE_AddOrderPage pressNewLineAlt(int lineNumber) {
        //Alternative method to press new line which can be used when interating over line creation.
        
        By newLineButton = By.id("add_tab");
        WebElement element = Wait.clickable(driver,newLineButton);
        element.click();
        
        By qtyField = By.id("SampleOrderLine"+lineNumber+"OrderedQuantity");
        WebElement wait = Wait.clickable(driver,qtyField);
        return this;
    }
    
    public CCE_AddOrderPage pressCopy(int lineNumber) {
        //Clicking copy can sometimes be unstable for unknown reasons
        
        //Wait for the quantity field to be available
        WebElement wait = Wait.visible(driver,quantityFieldLine2);
        WebElement waitMore = Wait.clickable(driver,quantityFieldLine2);
        
        //Wait for the copy button to be available
        By copyDataButton = By.id("copy_line_item_"+lineNumber);       
        WebElement waitForButton2 = Wait.visible(driver,copyDataButton);
        WebElement copyButton = Wait.clickable(driver,copyDataButton);
        
        //Move to the copy data button and click
        Actions moveAndClick = new Actions(driver);
        moveAndClick.moveToElement(copyButton).build().perform();
        moveAndClick.click().build().perform();
        
        //Wait for the purpose type in the next line to be updated
        By purposeTypeField = By.id("SampleOrderLine"+lineNumber+"PurposeTypeId");
        boolean waitAgain = Wait.selectionPresent(driver, purposeTypeField);
        
        //Wait for the copy box to be checked
        Wait.checked(driver,copyDataButton);
        
        return this;
    }
    
    public CCE_AddOrderPage pressCopyAlt(int lineNumber) {
        //Alternative Copy method used when many copy iterations are performed
        
        //Wait for quantity field to be available
        By qtyField = By.id("SampleOrderLine"+lineNumber+"OrderedQuantity");
        WebElement qty = Wait.visible(driver,qtyField);
        
        //Wait for copy button to be available
        By copyButton = By.id("copy_line_item_"+lineNumber);
        WebElement copy = Wait.clickable(driver,copyButton);
        
        //Move over the copy button and click
        Actions action = new Actions(driver);
        action.moveToElement(copy).build().perform();
        action.click().build().perform();
        
        //Wait for the purpose type field to be updated
        By purposeTypeField = By.id("SampleOrderLine"+lineNumber+"PurposeTypeId");
        WebElement purposeType = Wait.clickable(driver,purposeTypeField);
        
        //Wait for the copy button to be checked
        Wait.checked(driver,copyButton);
        
        return this;
    }

    public void inputDetails(String shipTo, String buyer, String article, String shadeCode, String MUM, String request, String purpose, int quantity) throws InterruptedException {        
        
        setShipToParty(shipTo);
        setBusinessPrincipal(buyer); //This method may not be required - it is generally easier to set each line individually
        setArticle(article,0);
        setShadeCode(shadeCode,0);
        setMUMType(MUM,0);
        setRequestType(request,0);
        setPurposeType(purpose,0);
        setQuantity(quantity,0);

    }
    
    public void inputAdditionalLines (String article, String shadeCode,String MUM, String request, String purpose, int quantity, int lineNumber) throws InterruptedException {
        
        setArticle(article,lineNumber);
        setShadeCode(shadeCode,lineNumber);
        setMUMType(MUM,lineNumber);
        setRequestType(request,lineNumber);
        setPurposeType(purpose,lineNumber);
        setQuantity(1,lineNumber);
        
    }
    
    public CCE_AddOrderPage waitForCopy() {
        //Wait for copied data to update in fields
        Boolean waitForDetails = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.not(ExpectedConditions.textToBePresentInElementLocated(purposeField, "Select")));
        return this;
    }
    
    public CCE_AddOrderPage waitForCopy(int row) {
        By purposeField = By.id("SampleOrderLine"+row+"PurposeTypeId");
        boolean wait = Wait.selectionToBe(driver, purposeField, DataItems.protoPurpose);
        return this;
    }
    
    public boolean checkFields() {
        
        try {
            //Wait for all fields to be clickable
            WebElement shipTo = Wait.clickable(driver,shipToField);
            WebElement busPrinc = Wait.clickable(driver,busPrincipalField);
            WebElement lightSrc1 = Wait.clickable(driver,lightSource1Field);
            WebElement lightSrc2 = Wait.clickable(driver,lightSource2Field);
            WebElement lightSrc3 = Wait.clickable(driver,lightSource3Field);
            WebElement newBuyer = Wait.visible(driver,newBuyerLink);
            WebElement article = Wait.clickable(driver,articleField);
            WebElement brand = Wait.clickable(driver,brandField);
            WebElement ticket = Wait.clickable(driver,ticketField);
            WebElement shadeCode = Wait.clickable(driver,shadeCodeField);
            WebElement cop = Wait.clickable(driver,copButton);
            WebElement cone = Wait.clickable(driver,coneButton);
            WebElement vicone = Wait.clickable(driver,viconeButton);
            WebElement colMat = Wait.clickable(driver,colMatButton);
            WebElement sewing = Wait.clickable(driver,sewingButton);
            WebElement dirEnYes = Wait.clickable(driver,dirEnYesButton);
            WebElement dirEnNo = Wait.clickable(driver,dirEnNoButton);
            WebElement purpose = Wait.clickable(driver,purposeField);
            WebElement requirements = Wait.clickable(driver,requirementsField);
            WebElement custRef = Wait.clickable(driver,custRefField);
            WebElement fabRef = Wait.clickable(driver,fabRefField);
            WebElement quantity = Wait.clickable(driver,quantityField);
            WebElement newLine = Wait.clickable(driver,newLineButton);
            WebElement submit = Wait.clickable(driver,submitOrderButton);
            WebElement pend = Wait.clickable(driver,pendOrderButton);
            WebElement cancel = Wait.clickable(driver,cancelButton);  
            
            //Assert they are all displayed
            AssertJUnit.assertTrue("Add Order page: Ship To Party field not displayed", shipTo.isDisplayed());
            AssertJUnit.assertTrue("Add Order page: Business Principal field not displayed", busPrinc.isDisplayed());
            AssertJUnit.assertTrue("Add Order page: Lightsource 1 field not displayed", lightSrc1.isDisplayed());
            AssertJUnit.assertTrue("Add Order page: Lightsource 2 field not displayed", lightSrc2.isDisplayed());
            AssertJUnit.assertTrue("Add Order page: Lightsource 3 field not displayed", lightSrc3.isDisplayed());
            AssertJUnit.assertTrue("Add Order page: New Buyer Link not displayed", newBuyer.isDisplayed());
            AssertJUnit.assertTrue("Add Order page: Article field not displayed", article.isDisplayed());
            AssertJUnit.assertTrue("Add Order page: Brand field not displayed", brand.isDisplayed());
            AssertJUnit.assertTrue("Add Order page: Ticket field not displayed", ticket.isDisplayed());
            AssertJUnit.assertTrue("Add Order page: Shade code field not displayed", shadeCode.isDisplayed());
            AssertJUnit.assertTrue("Add Order page: Cop button not displayed", cop.isDisplayed());
            AssertJUnit.assertTrue("Add Order page: Cone button not displayed", cone.isDisplayed());
            AssertJUnit.assertTrue("Add Order page: Vicone button not displayed", vicone.isDisplayed());
            AssertJUnit.assertTrue("Add Order page: Colour Match button not displayed", colMat.isDisplayed());
            AssertJUnit.assertTrue("Add Order page: Sewing button not displayed", sewing.isDisplayed());
            AssertJUnit.assertTrue("Add Order page: Direct Enrich (Yes) button not displayed", dirEnYes.isDisplayed());
            AssertJUnit.assertTrue("Add Order page: Direct Enrich (No) button not displayed", dirEnNo.isDisplayed());
            AssertJUnit.assertTrue("Add Order page: Purpose field not displayed", purpose.isDisplayed());
            AssertJUnit.assertTrue("Add Order page: Requirements field not displayed", requirements.isDisplayed());
            AssertJUnit.assertTrue("Add Order page: Customer Reference field not displayed", custRef.isDisplayed());
            AssertJUnit.assertTrue("Add Order page: Fabric Reference field not displayed", fabRef.isDisplayed());
            AssertJUnit.assertTrue("Add Order page: Quantity field not displayed", quantity.isDisplayed());
            AssertJUnit.assertTrue("Add Order page: New line button not displayed", newLine.isDisplayed());
            AssertJUnit.assertTrue("Add Order page: Submit Order button not displayed", submit.isDisplayed());
            AssertJUnit.assertTrue("Add Order page: Pend Order button not displayed", pend.isDisplayed());
            AssertJUnit.assertTrue("Add Order page: Cancel button not displayed", cancel.isDisplayed());
                  
            return true;
        } catch (Exception e) {
            return false;
        }
    
    }
    
    public void checkEnrichFields() {
        //Check the fields related to the Direct Enrich feature appear (click "Yes" to direct enrich to open this)

        //Wait for all elements to be clickable
        WebElement comments = Wait.clickable(driver, enrichCommentsField);
        WebElement enrichCompYes = Wait.clickable(driver,enrichCompletedYesButton);
        WebElement enrichCompNo = Wait.clickable(driver,enrichCompletedNoButton);
        WebElement hub = Wait.clickable(driver,enrichHubButton);
        WebElement lab = Wait.clickable(driver,enrichLabButton);
        WebElement whs = Wait.clickable(driver,enrichWHSButton);
        
        //Assert all elements are displayed
        AssertJUnit.assertTrue("Add Order Page: Direct Enrich feature: FCE Comments box not displayed",comments.isDisplayed());
        AssertJUnit.assertTrue("Add Order Page: Direct Enrich feature: Order Completed=Yes Button not displayed",enrichCompYes.isDisplayed());
        AssertJUnit.assertTrue("Add Order Page: Direct Enrich feature: Order Completed=No Button not displayed",enrichCompNo.isDisplayed());
        AssertJUnit.assertTrue("Add Order Page: Direct Enrich feature: Hub SOS Button not displayed",hub.isDisplayed());
        AssertJUnit.assertTrue("Add Order Page: Direct Enrich feature: Lab SOS Button not displayed",lab.isDisplayed());
        AssertJUnit.assertTrue("Add Order Page: Direct Enrich feature: WHS SOS Button not displayed",whs.isDisplayed());
    }
    
    public void waitForElement() {
        WebElement wait = Wait.clickable(driver,articleField);
    }
    
    public void waitForElement(int row) {
        By articleField = By.id("SampleOrderLine"+row+"ArticleId");
        WebElement wait = Wait.clickable(driver,articleField);
    }
    
    public boolean checkHidden(String type) {
        //Some MUM types will be hidden for specific materials based on the Sales Org Materials Master Data. This will check that the required type is hidden
        
        By locator;
        
        switch (type) {
            case "Cop": locator = copButton; break;
            case "Cone": locator = viconeButton; break;
            case "Vicone": locator = coneButton; break;
            default: System.out.println("Type not recognised."); return false;
        }
        
        try {
            WebElement wait = Wait.visible(driver,locator);
            return false;
        } catch (TimeoutException e) {
            return true;
        }
    }

}
