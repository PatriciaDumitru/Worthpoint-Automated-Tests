
package PageObjects;

import AutomationFramework.CommonTask;
import AutomationFramework.TestSuite;
import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class AddOrderPage extends BasePage {
   
    //Locators
    public static By flashMessageLocator = By.id("flashMessage");
    static By shipToPartyField = By.id("SampleOrderShipToPartyId");
    static By busPrincipalField = By.id("SampleOrderBusinessPrincipalId");
    static By lightSource1Field = By.id("SampleOrderLsPrimaryId");
    static By lightSource2Field = By.id("SampleOrderLsSecondaryId");
    static By lightSource3Field = By.id("SampleOrderLsThirdId");
    static By newBuyerLink = By.cssSelector("#SampleOrderAddForm > div:nth-child(3) > table > tbody > tr:nth-child(1) > td:nth-child(3) > a");
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
    static By newLineButton = By.id("add_tab");
    static By submitOrderButton = By.id("submit");
    static By pendOrderButton = By.id("pending");
    static By cancelButton = By.cssSelector("#SampleOrderAddForm > div:nth-child(4) > div.actions > ul > li:nth-child(3) > a");

    
    public AddOrderPage(WebDriver passedDriver) {
       super(passedDriver);
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
    
    public AddOrderPage setShipToParty(String shipToParty) throws InterruptedException {
        CommonTask.setDropDownField(driver, shipToPartyField, shipToParty);
        return this;
    }
    
    public AddOrderPage setBusinessPrincipal(String businessPrincipal) throws InterruptedException {
        CommonTask.setDropDownField(driver, busPrincipalField, businessPrincipal);
        return this;
    }
    
    public AddOrderPage setArticle(String article, int lineNumber) {
        //Line numbers start from 0
        By articleLocator = By.id("s2id_SampleOrderLine"+lineNumber+"ArticleId");
        CommonTask.setSearchField(driver, articleLocator, article);
        return this;
    }
    
    public AddOrderPage setBrand(String brand, int lineNumber) throws InterruptedException {
        By brandFieldLocator = By.id("SampleOrderLine"+lineNumber+"BrandId");
        
        CommonTask.setDropDownField(driver, brandFieldLocator, brand);
        
        return this;
    }
    
    public AddOrderPage setTicket(String ticket, int lineNumber) throws InterruptedException {
        By ticketFieldLocator = By.id("SampleOrderLine"+lineNumber+"TicketId");
        
        CommonTask.setDropDownField(driver, ticketFieldLocator, ticket);
        
        return this;
    }
    
    public AddOrderPage setShadeCode(String shadeCode,int lineNumber) {
        //Line numbers start from 0
        By shadeCodeLocator = By.id("s2id_SampleOrderLine"+lineNumber+"ShadeId");
        CommonTask.setSearchField(driver,shadeCodeLocator,shadeCode);
        return this;
    }
    
    public AddOrderPage setMUMType(String mumType, int lineNumber) {
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
    
    public AddOrderPage setRequestType(String reqType, int lineNumber) {
        //Line numbers start from 0
        By colMatLocator = By.id("SampleOrderLine"+lineNumber+"RequestTypeId1");
        By sewingLocator = By.id("SampleOrderLine"+lineNumber+"RequestTypeId2");
        
        switch(reqType) {
            case "colMat": driver.findElement(colMatLocator).click(); break;
            case "sewing": driver.findElement(sewingLocator).click(); break;
        }       
        return this;
    }
    
    public AddOrderPage setPurposeType(String purpType, int lineNumber) throws InterruptedException {
        //Line numbers start from 0
        By purpLocator = By.id("SampleOrderLine"+lineNumber+"PurposeTypeId");
        CommonTask.setDropDownField(driver, purpLocator, purpType);
        return this;
    }
    
    public AddOrderPage setQuantity(int quantity, int lineNumber) {
        //Line numbers start from 0
        By quantityLocator = By.id("SampleOrderLine"+lineNumber+"OrderedQuantity");
        driver.findElement(quantityLocator).click();
        driver.findElement(quantityLocator).sendKeys(Integer.toString(quantity));
        return this;
    }
    
    public OrderStatusPage_CCE pressSubmit() {
        //Wait for button to be clickable and click
        WebElement waitForButton = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(submitOrderButton));
        
        driver.findElement(submitOrderButton).click();
        
        Alert alert = new WebDriverWait(driver,10).until(ExpectedConditions.alertIsPresent());
        alert.accept();
        
        return new OrderStatusPage_CCE(driver);
    }
    
    public AddOrderPage pressSubmitExceeded() {
        //Wait for button to be clickable and click
        WebElement waitForButton = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(submitOrderButton));
        
        driver.findElement(submitOrderButton).click();
        
        Alert alert = new WebDriverWait(driver,10).until(ExpectedConditions.alertIsPresent());
        alert.accept();
        
        return new AddOrderPage(driver);
    }
    
    public OrderStatusPage_CCE pressPendOrder() {
        
        //Wait for button to be clickable
        WebElement waitForButton = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(pendOrderButton));
        driver.findElement(pendOrderButton).click();
        
        Alert alert = new WebDriverWait(driver,10).until(ExpectedConditions.alertIsPresent());
        
        alert.accept();
        
        return new OrderStatusPage_CCE(driver);
        
    }
    
    public OrderSamplesPage pressCancel() {
        
        //Wait for element to be clickable
        WebElement waitForClickable = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(cancelButton));
        driver.findElement(cancelButton).click();
        
        return new OrderSamplesPage(driver);
    }
    
    public AddOrderPage pressNewLine(int lineNumber) {
        //Wait for button to be clickable
        WebElement waitForClickable = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(newLineButton));
        driver.findElement(newLineButton).click();
        
        //Locator for quantity field in new line
        By fieldLocator = By.id("SampleOrderLine"+lineNumber+"OrderedQuantity");
        
        //Wait for new line form to load
        WebElement waitForLoad = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(fieldLocator));
        
        return this;
    }
    
    public AddOrderPage pressCopy(int lineNumber) {
        
        By copyDataButton = By.id("copy_line_item_"+lineNumber);
        driver.findElement(copyDataButton).click();
        
        return this;
    }

    public void inputDetails(String shipTo, String buyer, String article, String shadeCode, String MUM, String request, String purpose, int quantity) throws InterruptedException {

        setShipToParty(shipTo);
        setBusinessPrincipal(buyer);
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
    
    public boolean checkFields() {
        
        try {
            //Wait for all fields to be clickable
            WebElement waitForShipTo = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(shipToPartyField));
            WebElement waitForBusPrinc = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(busPrincipalField));
            WebElement waitForLightSrc1 = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(lightSource1Field));
            WebElement waitForLightSrc2 = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(lightSource2Field));
            WebElement waitForLightSrc3 = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(lightSource3Field));
            WebElement waitForNewBuyerLink = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(newBuyerLink));
            WebElement waitForArticle = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(articleField));
            WebElement waitForBrand = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(brandField));
            WebElement waitForTicket = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(ticketField));
            WebElement waitForShadeCode = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(shadeCodeField));
            WebElement waitForCop = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(copButton));
            WebElement waitForCone = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(coneButton));
            WebElement waitForVicone = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(viconeButton));
            WebElement waitForColMat = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(colMatButton));
            WebElement waitForSewing = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(sewingButton));
            WebElement waitForDirEnYes = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(dirEnYesButton));
            WebElement waitForDirEnNo = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(dirEnNoButton));
            WebElement waitForPurpose = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(purposeField));
            WebElement waitForRequirements = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(requirementsField));
            WebElement waitForCustRef = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(custRefField));
            WebElement waitForFabRef = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(fabRefField));
            WebElement waitForQuantity = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(quantityField));
            WebElement waitForNewLine = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(newLineButton));
            WebElement waitForSubmit = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(submitOrderButton));
            WebElement waitForPend = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(pendOrderButton));
            WebElement waitForCancel = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(cancelButton));  
            
            //Assert they are all displayed
            Assert.assertTrue("Add Order page: Ship To Party field not displayed", getShipToField().isDisplayed());
            Assert.assertTrue("Add Order page: Business Principal field not displayed", getBusPrincipalField().isDisplayed());
            Assert.assertTrue("Add Order page: Lightsource 1 field not displayed", getLightSource1Field().isDisplayed());
            Assert.assertTrue("Add Order page: Lightsource 2 field not displayed", getLightSource2Field().isDisplayed());
            Assert.assertTrue("Add Order page: Lightsource 3 field not displayed", getLightSource3Field().isDisplayed());
            Assert.assertTrue("Add Order page: New Buyer Link not displayed", getNewBuyerLink().isDisplayed());
            Assert.assertTrue("Add Order page: Article field not displayed", getArticleField().isDisplayed());
            Assert.assertTrue("Add Order page: Brand field not displayed", getBrandField().isDisplayed());
            Assert.assertTrue("Add Order page: Ticket field not displayed", getTicketField().isDisplayed());
            Assert.assertTrue("Add Order page: Shade code field not displayed", getShadeCodeField().isDisplayed());
            Assert.assertTrue("Add Order page: Cop button not displayed", getCopButton().isDisplayed());
            Assert.assertTrue("Add Order page: Cone button not displayed", getConeButton().isDisplayed());
            Assert.assertTrue("Add Order page: Vicone button not displayed", getViconeButton().isDisplayed());
            Assert.assertTrue("Add Order page: Colour Match button not displayed", getColMatButton().isDisplayed());
            Assert.assertTrue("Add Order page: Sewing button not displayed", getSewingButton().isDisplayed());
            Assert.assertTrue("Add Order page: Direct Enrich (Yes) button not displayed", getDirEnYesButton().isDisplayed());
            Assert.assertTrue("Add Order page: Direct Enrich (No) button not displayed", getDirEnNoButton().isDisplayed());
            Assert.assertTrue("Add Order page: Purpose field not displayed", getPurposeField().isDisplayed());
            Assert.assertTrue("Add Order page: Requirements field not displayed", getRequirementsField().isDisplayed());
            Assert.assertTrue("Add Order page: Customer Reference field not displayed", getCustRefField().isDisplayed());
            Assert.assertTrue("Add Order page: Fabric Reference field not displayed", getFabRefField().isDisplayed());
            Assert.assertTrue("Add Order page: Quantity field not displayed", getQuantityField().isDisplayed());
            Assert.assertTrue("Add Order page: New line button not displayed", getNewLineButton().isDisplayed());
            Assert.assertTrue("Add Order page: Submit Order button not displayed", getSubmitOrderButton().isDisplayed());
            Assert.assertTrue("Add Order page: Pend Order button not displayed", getPendOrderButton().isDisplayed());
            Assert.assertTrue("Add Order page: Cancel button not displayed", getCancelButton().isDisplayed());
                  
            return true;
        } catch (Exception e) {
            return false;
        }
    
    }

}
