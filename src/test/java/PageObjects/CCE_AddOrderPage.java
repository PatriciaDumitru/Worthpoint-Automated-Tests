
package PageObjects;

import AutomationFramework.CommonTask;
import AutomationFramework.DataItems;
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
    static By cancelButton = By.cssSelector("#SampleOrderAddForm > div:nth-child(4) > div.actions > ul > li:nth-child(3) > a");

    //Fields which appear upon click "Yes" to Direct Enrich
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
        WebElement wait = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.presenceOfElementLocated(customerNameField));
        return driver.findElement(customerNameField).getText();
    }
    
    public String getRequesterName() {
        return driver.findElement(requesterField).getText();
    }
    
    public String getShipToParty() {
        WebElement wait = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.presenceOfElementLocated(shipToField));
        return driver.findElement(shipToField).getAttribute("text");
    }
    
    public String getArticle() {
        WebElement wait = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.presenceOfElementLocated(articleField2));
        return driver.findElement(articleField2).getText();
    }
    
    public WebElement getShipToField() {
        return driver.findElement(shipToPartyField);
    }
    
    public WebElement getBusPrincipalField() {
        return driver.findElement(busPrincipalField);
    }
    
    public WebElement getLightSource1Field() {
        return driver.findElement(lightSource1Field);
    }
    
    public WebElement getLightSource2Field() {
        return driver.findElement(lightSource2Field);
    }
    
    public WebElement getLightSource3Field() {
        return driver.findElement(lightSource3Field);
    }
    
    public WebElement getNewBuyerLink() {
        return driver.findElement(newBuyerLink);
    }
    
    public WebElement getArticleField() {
        return driver.findElement(articleField);
    }
    
    public WebElement getBrandField() {
        return driver.findElement(brandField);
    }
    
    public WebElement getTicketField() {
        return driver.findElement(ticketField);
    }
    
    public WebElement getShadeCodeField() {
        return driver.findElement(shadeCodeField);
    }
    
    public WebElement getCopButton() {
        return driver.findElement(copButton);
    }
    
    public WebElement getConeButton() {
        return driver.findElement(coneButton);
    }
    
    public WebElement getViconeButton() {
        return driver.findElement(viconeButton);
    }
    
    public WebElement getColMatButton() {
        return driver.findElement(colMatButton);
    }
    
    public WebElement getSewingButton() {
        return driver.findElement(sewingButton);
    }
    
    public WebElement getDirEnYesButton() {
        return driver.findElement(dirEnYesButton);
    }
    
    public WebElement getDirEnNoButton() {
        return driver.findElement(dirEnNoButton);
    }
    
    public WebElement getPurposeField() {
        return driver.findElement(purposeField);
    }
    
    public WebElement getRequirementsField() {
        return driver.findElement(requirementsField);
    }
    
    public WebElement getCustRefField() {
        return driver.findElement(custRefField);
    }
    
    public WebElement getFabRefField() {
        return driver.findElement(fabRefField);
    }
    
    public WebElement getQuantityField() {
        return driver.findElement(quantityField);
    }
    
    public WebElement getNewLineButton() {
        return driver.findElement(newLineButton);
    }
    
    public WebElement getSubmitOrderButton() {
        return driver.findElement(submitOrderButton);
    }
    
    public WebElement getPendOrderButton() {
        return driver.findElement(pendOrderButton);
    }
    
    public WebElement getCancelButton() {
        return driver.findElement(cancelButton);
    }
    
    public WebElement getEnrichCommentsField() {
        return driver.findElement(enrichCommentsField);
    }
    
    public WebElement getEnrichCompletedYesButton() {
        return driver.findElement(enrichCompletedYesButton);
    }
    
     public WebElement getEnrichCompletedNoButton() {
        return driver.findElement(enrichCompletedNoButton);
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
        return this;
    }
    
    public CCE_AddOrderPage setCustomerRef(int lineNumber) throws IOException {
        //Line numbers start from 0
        By refLocator = By.id("SampleOrderLine"+lineNumber+"CustomerReference");
        String PO = CommonTask.generatePO("noncontract");
        DataItems.lastUsedPO = PO;
        CommonTask.setInputField(driver, refLocator,PO);
        return this;
    }
    
    public CCE_AddOrderPage setQuantity(int quantity, int lineNumber) {
        //Line numbers start from 0
        By quantityLocator = By.id("SampleOrderLine"+lineNumber+"OrderedQuantity");
        driver.findElement(quantityLocator).click();
        driver.findElement(quantityLocator).sendKeys(Integer.toString(quantity));
        return this;
    }
    
    public String getCreationDate() {
        return driver.findElement(creationDateField).getText();
    }
    
    public CCE_OrderStatusPage pressSubmit() {
        //Wait for button to be clickable and click
        WebElement waitForButton = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(submitOrderButton));
        
        driver.findElement(submitOrderButton).click();
        
        Alert alert = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.alertIsPresent());
        alert.accept();
        
        return new CCE_OrderStatusPage(driver);
    }
    
    public CCE_AddOrderPage pressSubmitExceeded() {
        //Wait for button to be clickable and click
        WebElement waitForButton = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(submitOrderButton));
        
        driver.findElement(submitOrderButton).click();
        
        Alert alert = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.alertIsPresent());
        alert.accept();
        
        try {
            Alert alert2 = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.alertIsPresent());
            System.out.println("Alert appeared: " + alert2.getText());
            alert2.accept();        
        } catch(Exception e) {
            System.out.println("Quantity Exceeded alert did not appear");
        }
        
        return new CCE_AddOrderPage(driver);
    }
    
    public CCE_OrderStatusPage pressPendOrder() {
        
        //Wait for button to be clickable
        WebElement waitForButton = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(pendOrderButton));
        driver.findElement(pendOrderButton).click();
        
        Alert alert = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.alertIsPresent());
        
        alert.accept();
        
        return new CCE_OrderStatusPage(driver);
        
    }
    
    public CCE_OrderSamplesPage pressCancel() {
        
        WebElement wait = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.visibilityOfElementLocated(cancelButton));
        
        Actions action = new Actions(driver);
        action.moveToElement(driver.findElement(cancelButton)).build().perform();
        
        action.click(driver.findElement(cancelButton)).build().perform();
        
        return new CCE_OrderSamplesPage(driver);
    }
    
    public CCE_NewBuyerPage pressNewBuyer() {
        WebElement wait = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.visibilityOfElementLocated(newBuyerLink));
        driver.findElement(newBuyerLink).click();
        return new CCE_NewBuyerPage(driver);
    }
    
    public CCE_OutstandingDraftPage pressCancelToDrafts() {
        Actions action = new Actions(driver);
        action.moveToElement(driver.findElement(cancelButton)).build().perform();
        
        WebElement wait = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.visibilityOfElementLocated(cancelButton));
        
        action.click(driver.findElement(cancelButton)).build().perform();
        
        return new CCE_OutstandingDraftPage(driver);
    }
    
    public CCE_AddOrderPage pressNewLine(int lineNumber) {
        //Wait for button to be clickable
        WebElement waitForClickable = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(newLineButton));
        driver.findElement(newLineButton).click();
        
        //Wait for new line form to load
        WebElement waitForLoaded = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(quantityFieldLine2));
        
        return this;
    }
    
    public CCE_AddOrderPage pressCopy(int lineNumber) {
        
        WebElement wait = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.visibilityOfElementLocated(quantityFieldLine2));
        WebElement waitMore = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(quantityFieldLine2));
        
        By copyDataButton = By.id("copy_line_item_"+lineNumber);       
                WebElement waitForButton2 = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.visibilityOfElementLocated(copyDataButton));
        WebElement waitForButton = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(copyDataButton));
        
        Actions moveAndClick = new Actions(driver);
        moveAndClick.moveToElement(driver.findElement(copyDataButton)).build().perform();
        moveAndClick.click().build().perform();
        
        By purposeTypeField = By.id("SampleOrderLine"+lineNumber+"PurposeTypeId");
        
        boolean waitAgain = new WebDriverWait(driver,DataItems.shortWait).until(CommonTask.selectionToBePresent(purposeTypeField));
        
        boolean waitForChecked = new WebDriverWait(driver,DataItems.shortWait).until(CommonTask.boxIsChecked(driver.findElement(copyDataButton)));
        
        return this;
    }

    public void inputDetails(String shipTo, String buyer, String article, String shadeCode, String MUM, String request, String purpose, int quantity) throws InterruptedException {

        setShipToParty(shipTo);
        //setBusinessPrincipal(buyer);
        setArticle(article,0);
        setShadeCode(shadeCode,0);
        setMUMType(MUM,0);
        setRequestType(request,0);
        setPurposeType(purpose,0);
        setQuantity(quantity,0);
        
        //Business principal not set - as when account was changed to Global Admin, the value shown in the drop down field had (10000001) appended. Possible extension to figure out why 
        
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
        Boolean waitForDetails = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.not(ExpectedConditions.textToBePresentInElementLocated(purposeField, "Select")));
        return this;
    }
    
    public boolean checkFields() {
        
        try {
            //Wait for all fields to be clickable
            WebElement waitForShipTo = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(shipToPartyField));
            WebElement waitForBusPrinc = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(busPrincipalField));
            WebElement waitForLightSrc1 = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(lightSource1Field));
            WebElement waitForLightSrc2 = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(lightSource2Field));
            WebElement waitForLightSrc3 = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(lightSource3Field));
            WebElement waitForNewBuyerLink = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(newBuyerLink));
            WebElement waitForArticle = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(articleField));
            WebElement waitForBrand = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(brandField));
            WebElement waitForTicket = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(ticketField));
            WebElement waitForShadeCode = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(shadeCodeField));
            WebElement waitForCop = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(copButton));
            WebElement waitForCone = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(coneButton));
            WebElement waitForVicone = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(viconeButton));
            WebElement waitForColMat = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(colMatButton));
            WebElement waitForSewing = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(sewingButton));
            WebElement waitForDirEnYes = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(dirEnYesButton));
            WebElement waitForDirEnNo = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(dirEnNoButton));
            WebElement waitForPurpose = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(purposeField));
            WebElement waitForRequirements = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(requirementsField));
            WebElement waitForCustRef = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(custRefField));
            WebElement waitForFabRef = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(fabRefField));
            WebElement waitForQuantity = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(quantityField));
            WebElement waitForNewLine = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(newLineButton));
            WebElement waitForSubmit = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(submitOrderButton));
            WebElement waitForPend = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(pendOrderButton));
            WebElement waitForCancel = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(cancelButton));  
            
            //Assert they are all displayed
            AssertJUnit.assertTrue("Add Order page: Ship To Party field not displayed", getShipToField().isDisplayed());
            AssertJUnit.assertTrue("Add Order page: Business Principal field not displayed", getBusPrincipalField().isDisplayed());
            AssertJUnit.assertTrue("Add Order page: Lightsource 1 field not displayed", getLightSource1Field().isDisplayed());
            AssertJUnit.assertTrue("Add Order page: Lightsource 2 field not displayed", getLightSource2Field().isDisplayed());
            AssertJUnit.assertTrue("Add Order page: Lightsource 3 field not displayed", getLightSource3Field().isDisplayed());
            AssertJUnit.assertTrue("Add Order page: New Buyer Link not displayed", getNewBuyerLink().isDisplayed());
            AssertJUnit.assertTrue("Add Order page: Article field not displayed", getArticleField().isDisplayed());
            AssertJUnit.assertTrue("Add Order page: Brand field not displayed", getBrandField().isDisplayed());
            AssertJUnit.assertTrue("Add Order page: Ticket field not displayed", getTicketField().isDisplayed());
            AssertJUnit.assertTrue("Add Order page: Shade code field not displayed", getShadeCodeField().isDisplayed());
            AssertJUnit.assertTrue("Add Order page: Cop button not displayed", getCopButton().isDisplayed());
            AssertJUnit.assertTrue("Add Order page: Cone button not displayed", getConeButton().isDisplayed());
            AssertJUnit.assertTrue("Add Order page: Vicone button not displayed", getViconeButton().isDisplayed());
            AssertJUnit.assertTrue("Add Order page: Colour Match button not displayed", getColMatButton().isDisplayed());
            AssertJUnit.assertTrue("Add Order page: Sewing button not displayed", getSewingButton().isDisplayed());
            AssertJUnit.assertTrue("Add Order page: Direct Enrich (Yes) button not displayed", getDirEnYesButton().isDisplayed());
            AssertJUnit.assertTrue("Add Order page: Direct Enrich (No) button not displayed", getDirEnNoButton().isDisplayed());
            AssertJUnit.assertTrue("Add Order page: Purpose field not displayed", getPurposeField().isDisplayed());
            AssertJUnit.assertTrue("Add Order page: Requirements field not displayed", getRequirementsField().isDisplayed());
            AssertJUnit.assertTrue("Add Order page: Customer Reference field not displayed", getCustRefField().isDisplayed());
            AssertJUnit.assertTrue("Add Order page: Fabric Reference field not displayed", getFabRefField().isDisplayed());
            AssertJUnit.assertTrue("Add Order page: Quantity field not displayed", getQuantityField().isDisplayed());
            AssertJUnit.assertTrue("Add Order page: New line button not displayed", getNewLineButton().isDisplayed());
            AssertJUnit.assertTrue("Add Order page: Submit Order button not displayed", getSubmitOrderButton().isDisplayed());
            AssertJUnit.assertTrue("Add Order page: Pend Order button not displayed", getPendOrderButton().isDisplayed());
            AssertJUnit.assertTrue("Add Order page: Cancel button not displayed", getCancelButton().isDisplayed());
                  
            return true;
        } catch (Exception e) {
            return false;
        }
    
    }
    
    public void checkEnrichFields() {
        //Wait for all elements to be clickable
        WebElement waitForComments = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(enrichCommentsField));
        WebElement waitForCompYes = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(enrichCompletedYesButton));
        WebElement waitForCompNo = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(enrichCompletedNoButton));
        WebElement waitForHub = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(enrichHubButton));
        WebElement waitForLab = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(enrichLabButton));
        WebElement waitForWHS = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(enrichWHSButton));
        
        //Assert all elements are displayed
        AssertJUnit.assertTrue("Add Order Page: Direct Enrich feature: FCE Comments box not displayed",getEnrichCommentsField().isDisplayed());
        AssertJUnit.assertTrue("Add Order Page: Direct Enrich feature: Order Completed=Yes Button not displayed",getEnrichCompletedYesButton().isDisplayed());
        AssertJUnit.assertTrue("Add Order Page: Direct Enrich feature: Order Completed=No Button not displayed",getEnrichCompletedNoButton().isDisplayed());
        AssertJUnit.assertTrue("Add Order Page: Direct Enrich feature: Hub SOS Button not displayed",getEnrichHubButton().isDisplayed());
        AssertJUnit.assertTrue("Add Order Page: Direct Enrich feature: Lab SOS Button not displayed",getEnrichLabButton().isDisplayed());
        AssertJUnit.assertTrue("Add Order Page: Direct Enrich feature: WHS SOS Button not displayed",getEnrichWHSButton().isDisplayed());
    }
    
    public void waitForElement() {
        WebElement wait = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(articleField));
    }
    
    public boolean checkHidden(String type) {
        By locator;
        
        switch (type) {
            case "Cop": locator = copButton; break;
            case "Cone": locator = viconeButton; break;
            case "Vicone": locator = coneButton; break;
            default: System.out.println("Type not recognised."); return false;
        }
        
        try {
            WebElement wait = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.visibilityOfElementLocated(locator));
            return false;
        } catch (TimeoutException e) {
            return true;
        }
    }

}
