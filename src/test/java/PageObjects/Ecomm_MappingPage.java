package PageObjects;

import AutomationFramework.CommonTask;
import AutomationFramework.DataItems;
import org.testng.AssertJUnit;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Ecomm_MappingPage extends WBA_BasePage {
    
    //Locators
    By salesOrgFieldLocator = By.id("BulkOrderSalesOrgId");
    By salesOrgLabelLocator = By.cssSelector("#mapping_grid > table > tbody > tr:nth-child(1) > td:nth-child(1) > label");
    By custNameFieldLocator = By.cssSelector("#s2id_BulkOrderCustomerId > a > span.select2-chosen");
    By custNameLabelLocator = By.cssSelector("#mapping_grid > table > tbody > tr:nth-child(2) > td:nth-child(1) > label");
    By custNameResultLocator = By.cssSelector("#select2-drop > ul > li > div > span");
    By articleFieldLocator = By.id("BulkOrderLineArticleId");
    By articleLabelLocator = By.cssSelector("#mapping_grid > table > tbody > tr:nth-child(3) > td:nth-child(1) > label");
    By articleOptionLocator = By.cssSelector("#BulkOrderLineArticleId > option:nth-child(2)");
    By ticketFieldLocator = By.id("BulkOrderLineTicketId");
    By ticketLabelLocator = By.cssSelector("#mapping_grid > table > tbody > tr:nth-child(4) > td:nth-child(1) > label");
    By finishFieldLocator = By.id("BulkOrderLineFinishId");
    By finishLabelLocator = By.cssSelector("#mapping_grid > table > tbody > tr:nth-child(5) > td:nth-child(1) > label");
    By shadeCodeFieldLocator = By.id("BulkOrderLineShadeId");
    By shadeCodeLabelLocator = By.cssSelector("#mapping_grid > table > tbody > tr:nth-child(6) > td:nth-child(1) > label");
    By requiredDateFieldLocator = By.id("BulkOrderLineRequiredDate");
    By requiredDateLabelLocator = By.cssSelector("#mapping_grid > table > tbody > tr:nth-child(7) > td:nth-child(1) > label");
    By quantityFieldLocator = By.id("BulkOrderLineOrderedQuantity");
    By quantityLabelLocator = By.cssSelector("#mapping_grid > table > tbody > tr:nth-child(8) > td:nth-child(1) > label");
    By styleFieldLocator = By.id("BulkOrderStyle");
    By styleLabelLocator = By.cssSelector("#mapping_grid > table > tbody > tr.headerinfo > td:nth-child(1) > label");
    By styleNoFieldLocator = By.id("BulkOrderLineProdStyleNo");
    By styleNoLabelLocator = By.cssSelector("#mapping_grid > table > tbody > tr.lineinfo > td:nth-child(1) > label");
    By contractPONoLabelLocator = By.cssSelector("#mapping_grid > table > tbody > tr.contractinfo > td:nth-child(1) > label");
    By contractPONoLocator = By.id("BulkOrderLineContract");
    By subAccountFieldLocator = By.id("BulkOrderPayerId");
    By subAccountLabelLocator = By.cssSelector("#mapping_grid > table > tbody > tr.headerinfopay > td:nth-child(1) > label");
    By lineRefLocator = By.id("BulkOrderLineContract");
    By shipToPartyFieldLocator = By.id("BulkOrderShipToPartyId");
    By shipToPartyLabelLocator = By.cssSelector("#mapping_grid > table > tbody > tr:nth-child(1) > td:nth-child(3) > label");
    By yourMaterialNumberFieldLocator = By.id("BulkOrderLineCustomerMaterialNo");
    By yourMaterialNumberLabelLocator = By.cssSelector("#mapping_grid > table > tbody > tr:nth-child(2) > td:nth-child(3) > label");
    By brandFieldLocator = By.id("BulkOrderLineBrandId");
    By brandLabelLocator = By.cssSelector("#mapping_grid > table > tbody > tr:nth-child(3) > td:nth-child(3) > label");
    By lengthFieldLocator = By.id("BulkOrderLineLengthId");
    By lengthLabelLocator = By.cssSelector("#mapping_grid > table > tbody > tr:nth-child(4) > td:nth-child(3) > label");
    By buyersFieldLocator = By.id("BulkOrderBuyerId");
    By buyersLabelLocator = By.cssSelector("#mapping_grid > table > tbody > tr:nth-child(5) > td:nth-child(3) > label");
    By customerPOFieldLocator = By.id("BulkOrderPoNumber");
    By customerPOLabelLocator = By.cssSelector("#mapping_grid > table > tbody > tr:nth-child(6) > td:nth-child(3) > label");
    By requestorNameFieldLocator = By.id("BulkOrderRequesterId");
    By requestorNameLabelLocator = By.cssSelector("#mapping_grid > table > tbody > tr:nth-child(7) > td:nth-child(3) > label");
    By warehouseInstFieldLocator = By.id("BulkOrderWarehouseInstruction");
    By warehouseInstLabelLocator = By.cssSelector("#mapping_grid > table > tbody > tr:nth-child(8) > td:nth-child(3) > label");
    By buyerNumberFieldLocator = By.id("BulkOrderBuyerSalesOrderno");
    By buyerNumberLabelLocator = By.cssSelector("#mapping_grid > table > tbody > tr:nth-child(9) > td:nth-child(3) > label");
    By otherInformationFieldLocator = By.id("BulkOrderLineOtherinfo");
    By otherInformationLabelLocator = By.cssSelector("#mapping_grid > table > tbody > tr.lineinfo > td:nth-child(3) > label");
    By lineRefFieldLocator = By.id("BulkOrderLineLineReference");
    By customerPriceFieldLocator = By.id("BulkOrderLineCustomerPrice");
    By customerPriceLabelLocator = By.cssSelector("#mapping_grid > table > tbody > tr.headerinfopay > td:nth-child(3) > label");
    By confirmButtonLocator = By.id("trigger");
    By frameLocator = By.id("file_mapping");
    
    public Ecomm_MappingPage(WebDriver passedDriver) {
        super(passedDriver);
    }
    
    public WebElement getSalesOrgLabel() {
        return driver.findElement(salesOrgLabelLocator);
    }
    
    public WebElement getSalesOrgField() {
        return driver.findElement(salesOrgFieldLocator);
    }
    
    public WebElement getCustNameLabel() {
        return driver.findElement(custNameLabelLocator);
    }
    
    public WebElement getCustNameField() {
        return driver.findElement(custNameFieldLocator);
    }
    
    public WebElement getArticleLabel() {
        return driver.findElement(articleLabelLocator);
    }
    
    public WebElement getArticleField() {
        return driver.findElement(articleFieldLocator);
    }
    
    public WebElement getTicketLabel() {
        return driver.findElement(ticketLabelLocator);
    }
    
    public WebElement getTicketField() {
        return driver.findElement(ticketFieldLocator);
    }
    
    public WebElement getFinishLabel() {
        return driver.findElement(finishLabelLocator);
    }
    
    public WebElement getFinishField() {
        return driver.findElement(finishFieldLocator);
    }
    
    public WebElement getShadeCodeLabel() {
        return driver.findElement(shadeCodeLabelLocator);
    }
    
    public WebElement getShadeCodeField() {
        return driver.findElement(shadeCodeFieldLocator);
    }
    
    public WebElement getRequiredDateLabel() {
        return driver.findElement(requiredDateLabelLocator);
    }
    
    public WebElement getRequiredDateField() {
        return driver.findElement(requiredDateFieldLocator);
    }
    
    public WebElement getQuantityLabel() {
        return driver.findElement(quantityLabelLocator);
    }
    
    public WebElement getQuantityField() {
        return driver.findElement(quantityFieldLocator);
    }
    
    public WebElement getStyleLabel() {
        return driver.findElement(styleLabelLocator);
    }
    
    public WebElement getStyleField() {
        return driver.findElement(styleFieldLocator);
    }
    
    public WebElement getStyleNoLabel() {
        return driver.findElement(styleNoLabelLocator);
    }
    
    public WebElement getStyleNoField() {
        return driver.findElement(styleNoFieldLocator);
    }
    
    public WebElement getShipToPartyLabel() {
        return driver.findElement(shipToPartyLabelLocator);
    }
    
    public WebElement getShipToPartyField() {
        return driver.findElement(shipToPartyFieldLocator);
    }
    
    public WebElement getYourMaterialNumberLabel() {
        return driver.findElement(yourMaterialNumberLabelLocator);
    }
    
    public WebElement getYourMaterialNumberField() {
        return driver.findElement(yourMaterialNumberFieldLocator);
    }
    
    public WebElement getBrandLabel() {
        return driver.findElement(brandLabelLocator);
    }
    
    public WebElement getBrandField() {
        return driver.findElement(brandFieldLocator);
    }
    
    public WebElement getLengthLabel() {
        return driver.findElement(lengthLabelLocator);
    }
    
    public WebElement getLengthField() {
        return driver.findElement(lengthFieldLocator);
    }
    
    public WebElement getBuyersLabel() {
        return driver.findElement(buyersLabelLocator);
    }
    
    public WebElement getBuyersField() {
        return driver.findElement(buyersFieldLocator);
    }
    
    public WebElement getCustomerPOLabel() {
        return driver.findElement(customerPOLabelLocator);
    }
    
    public WebElement getCustomerPOField() {
        return driver.findElement(customerPOFieldLocator);
    }
    
    public WebElement getRequestorNameLabel() {
        return driver.findElement(requestorNameLabelLocator);
    }
    
    public WebElement getRequestorNameField() {
        return driver.findElement(requestorNameFieldLocator);
    }
    
    public WebElement getWarehouseInstLabel() {
        return driver.findElement(warehouseInstLabelLocator);
    }
    
    public WebElement getWarehouseInstField() {
        return driver.findElement(warehouseInstFieldLocator);
    }
    
    public WebElement getBuyerNumberLabel() {
        return driver.findElement(buyerNumberLabelLocator);
    }
    
    public WebElement getBuyerNumberField() {
        return driver.findElement(buyerNumberFieldLocator);
    }
    
    public WebElement getOtherInformationLabel() {
        return driver.findElement(otherInformationLabelLocator);
    }
    
    public WebElement getOtherInformationField() {
        return driver.findElement(otherInformationFieldLocator);
    }
    
    public WebElement getCustomerPriceLabel() {
        return driver.findElement(customerPriceLabelLocator);
    }
    
    public WebElement getCustomerPriceField() {
        return driver.findElement(customerPriceFieldLocator);
    }
    
    public WebElement confirmButtonLocator() {
        return driver.findElement(confirmButtonLocator);
    }
    
    public Ecomm_OrderConfirmationPage pressConfirm() {
        //wait for button to be clickable
        WebElement waitForClickable = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(confirmButtonLocator));
        //New action to click confirm
        Actions clickConfirm = new Actions(driver);
        clickConfirm.click(driver.findElement(confirmButtonLocator)).build().perform();
        //Confirm alert
        Alert alert = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.alertIsPresent());
        alert.accept();
        
        Alert alert2 = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.alertIsPresent());
        alert2.accept();
        
        return new Ecomm_OrderConfirmationPage(driver);
    }
    
    public Ecomm_OrderConfirmationPage pressConfirmMOQ() {
        //wait for button to be clickable
        WebElement waitForClickable = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(confirmButtonLocator));
        //New action to click confirm
        Actions clickConfirm = new Actions(driver);
        clickConfirm.click(driver.findElement(confirmButtonLocator)).build().perform();
        //Confirm alert
        Alert alert = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.alertIsPresent());
        alert.accept();

        Alert alert2 = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.alertIsPresent());
        alert2.accept();
        
        try {
            Alert alert3 = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.alertIsPresent());
            AssertJUnit.assertTrue("Order Confirmation Page: Alert for MOQ regarding rounded quantity not displayed as expected",alert3.getText().contains("order quantity has been rounded"));
            System.out.println("Alert received, as expected");
            System.out.println("Alert Message: " + alert3.getText());
            alert3.accept();
        } catch (TimeoutException e) {
            
        }
        
        return new Ecomm_OrderConfirmationPage(driver);
    }
    
    public Ecomm_ErrorPage pressConfirmExpectingError() {
        //wait for button to be clickable
        WebElement waitForClickable = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(confirmButtonLocator));
        //New action to click confirm
        Actions clickConfirm = new Actions(driver);
        clickConfirm.click(driver.findElement(confirmButtonLocator)).build().perform();
        //Confirm alert
        Alert alert = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.alertIsPresent());
        alert.accept();
        
        try {
            Alert alert2 = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.alertIsPresent());
            alert2.accept();
        } catch (TimeoutException e) {
            
        }
        
        return new Ecomm_ErrorPage(driver);
    }
    
    public Ecomm_BackendProcessPage pressConfirmForBackend() {
        //wait for button to be clickable
        WebElement waitForClickable = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(confirmButtonLocator));
        //New action to click confirm
        Actions clickConfirm = new Actions(driver);
        clickConfirm.click(driver.findElement(confirmButtonLocator)).build().perform();
        //Confirm alert
        Alert alert = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.alertIsPresent());
        alert.accept();
        
        return new Ecomm_BackendProcessPage(driver);
    }
    
    public Ecomm_MappingPage setMapping(String[][] mapping) {
        //Wait for page to load
        WebElement waitForLoad = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(confirmButtonLocator));
        
        Actions inputKeys = new Actions(driver);
        
        //Check and set fields
        
        //Sales Organisation
        AssertJUnit.assertTrue("Mapping page: Sales organisation field not displayed", this.getSalesOrgField().isDisplayed());
        AssertJUnit.assertTrue("Mapping page: Sales Organisation label incorrectly displayed",this.getSalesOrgLabel().getText().equals("Sales Organization"));
        inputKeys.click(this.getSalesOrgField()).build().perform();
        inputKeys.sendKeys(DataItems.salesOrganisation+Keys.ENTER).build().perform();
        
        //Customer Name
        AssertJUnit.assertTrue("Mapping page: Customer name field not displayed",this.getCustNameField().isDisplayed());
        AssertJUnit.assertTrue("Mapping page: Custome name label incorrectly displayed",this.getCustNameLabel().getText().equals(mapping[0][0]));
        inputKeys.click(this.getCustNameField()).build().perform();
        inputKeys.sendKeys(DataItems.custDetails[0]).build().perform();
        //Wait for result to appear and press enter
        WebElement waitForResult = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.visibilityOfElementLocated(custNameResultLocator));
        boolean waitForText = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.textToBePresentInElementLocated(custNameResultLocator, DataItems.custDetails[0]));
        inputKeys.sendKeys(Keys.ENTER);
        
        //Article
        AssertJUnit.assertTrue("Mapping page: Article field not displayed",this.getArticleField().isDisplayed());
        AssertJUnit.assertTrue("Mapping page: Article label incorrectly displayed",this.getArticleLabel().getText().equals(mapping[1][0]));
        inputKeys.click(this.getArticleField()).build().perform();
        WebElement waitForOptions = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.visibilityOfElementLocated(articleOptionLocator));
        inputKeys.sendKeys(mapping[1][1]).build().perform();
        //Wait for field to update
        Boolean waitForUpdate = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.textToBePresentInElementLocated(articleFieldLocator, mapping[1][1]));
        
        //Ticket
        AssertJUnit.assertTrue("Mapping page: Ticket field not displayed",this.getTicketField().isDisplayed());
        AssertJUnit.assertTrue("Mapping page: Ticket label incorrectly displayed",this.getTicketLabel().getText().equals(mapping[2][0]));
        inputKeys.click(this.getTicketField()).build().perform();
        inputKeys.sendKeys(mapping[2][1]).build().perform();
        
        //Finish
        AssertJUnit.assertTrue("Mapping page: Finish field not displayed",this.getFinishField().isDisplayed());
        AssertJUnit.assertTrue("Mapping page: Finish label incorrectly displayed",this.getFinishLabel().getText().equals(mapping[3][0]));
        inputKeys.click(this.getFinishField()).build().perform();
        inputKeys.sendKeys(mapping[3][1]).build().perform();
        
        //Shade code
        AssertJUnit.assertTrue("Mapping page: Shade code field not displayed",this.getShadeCodeField().isDisplayed());
        AssertJUnit.assertTrue("Mapping page: Shade code label incorrectly displayed",this.getShadeCodeLabel().getText().equals(mapping[4][0]));
        inputKeys.click(this.getShadeCodeField()).build().perform();
        inputKeys.sendKeys(mapping[4][1]).build().perform();
        
        //Required date
        AssertJUnit.assertTrue("Mapping page: Required Date field not displayed",this.getRequiredDateField().isDisplayed());
        AssertJUnit.assertTrue("Mapping page: Required Date label incorrectly displayed",this.getRequiredDateLabel().getText().equals(mapping[5][0]));
        inputKeys.click(this.getRequiredDateField()).build().perform();
        inputKeys.sendKeys(mapping[5][1]).build().perform();
        
        //Qty
        AssertJUnit.assertTrue("Mapping page: Quantity field not displayed",this.getQuantityField().isDisplayed());
        AssertJUnit.assertTrue("Mapping page: Quantity label incorrectly displayed",this.getQuantityLabel().getText().equals(mapping[6][0]));
        inputKeys.click(this.getQuantityField()).build().perform();
        inputKeys.sendKeys(mapping[6][1]).build().perform();
        
        //Style
        AssertJUnit.assertTrue("Mapping page: Style field not displayed",this.getStyleField().isDisplayed());
        AssertJUnit.assertTrue("Mapping page: Style label incorrectly displayed",this.getStyleLabel().getText().equals(mapping[7][0]));
        inputKeys.click(this.getStyleField()).build().perform();
        inputKeys.sendKeys(mapping[7][1]).build().perform();
        
        //Style No.
        AssertJUnit.assertTrue("Mapping page: Style No field not displayed",this.getStyleNoField().isDisplayed());
        AssertJUnit.assertTrue("Mapping page: Style No. label incorrectly displayed",this.getStyleNoLabel().getText().equals(mapping[8][0]));
        inputKeys.click(this.getStyleNoField()).build().perform();
        inputKeys.sendKeys(mapping[8][1]).build().perform();       
        
        //Ship to party name
        AssertJUnit.assertTrue("Mapping page: Ship to party name field not displayed",this.getShipToPartyField().isDisplayed());
        AssertJUnit.assertTrue("Mapping page: Ship to party name label incorrectly displayed",this.getShipToPartyLabel().getText().equals(mapping[10][0]));
        inputKeys.click(this.getShipToPartyField()).build().perform();
        inputKeys.sendKeys(mapping[10][1]).build().perform();
        
        //Your material number
        AssertJUnit.assertTrue("Mapping page: Your material number field not displayed",this.getYourMaterialNumberField().isDisplayed());
        AssertJUnit.assertTrue("Mapping page: Your material number label incorrectly displayed",this.getYourMaterialNumberLabel().getText().equals(mapping[11][0]));
        inputKeys.click(this.getYourMaterialNumberField()).build().perform();
        inputKeys.sendKeys(mapping[11][1]).build().perform();
        
        //Brand
        AssertJUnit.assertTrue("Mapping page: Brand field not displayed",this.getBrandField().isDisplayed());
        AssertJUnit.assertTrue("Mapping page: Brand label incorrectly displayed",this.getBrandLabel().getText().equals(mapping[12][0]));
        inputKeys.click(this.getBrandField()).build().perform();
        inputKeys.sendKeys(mapping[12][1]).build().perform();
        
        //Length
        AssertJUnit.assertTrue("Mapping page: Length field not displayed",this.getLengthField().isDisplayed());
        AssertJUnit.assertTrue("Mapping page: Length label incorrectly displayed",this.getLengthLabel().getText().equals(mapping[13][0]));
        inputKeys.click(this.getLengthField()).build().perform();
        inputKeys.sendKeys(mapping[13][1]).build().perform();
        
        //Buyers
        AssertJUnit.assertTrue("Mapping page: Buyers field not displayed",this.getBuyersField().isDisplayed());
        AssertJUnit.assertTrue("Mapping page: Buyers label incorrectly displayed",this.getBuyersLabel().getText().equals(mapping[14][0]));
        inputKeys.click(this.getBuyersField()).build().perform();
        inputKeys.sendKeys(mapping[14][1]).build().perform();
        
        //Customer PO No
        AssertJUnit.assertTrue("Mapping page: Customer PO field not displayed",this.getCustomerPOField().isDisplayed());
        AssertJUnit.assertTrue("Mapping page: Customer PO label incorrectly displayed",this.getCustomerPOLabel().getText().equals(mapping[15][0]));
        inputKeys.click(this.getCustomerPOField()).build().perform();
        inputKeys.sendKeys(mapping[15][1]).build().perform();
        
        //Requestor name
        AssertJUnit.assertTrue("Mapping page: Requestor field not displayed",this.getRequestorNameField().isDisplayed());
        AssertJUnit.assertTrue("Mapping page: Requestor label incorrectly displayed",this.getRequestorNameLabel().getText().equals(mapping[16][0]));
        inputKeys.click(this.getRequestorNameField()).build().perform();
        inputKeys.sendKeys(mapping[16][1]).build().perform();
        
        //Warehouse instruction
        AssertJUnit.assertTrue("Mapping page: Warehouse instruction field not displayed",this.getWarehouseInstField().isDisplayed());
        AssertJUnit.assertTrue("Mapping page: Warehouse instruction label incorrectly displayed",this.getWarehouseInstLabel().getText().equals(mapping[17][0]));
        inputKeys.click(this.getWarehouseInstField()).build().perform();
        inputKeys.sendKeys(mapping[17][1]).build().perform();
        
        //Buyer Order Sales Number
        AssertJUnit.assertTrue("Mapping page: Buyer order sales number field not displayed",this.getBuyerNumberField().isDisplayed());
        AssertJUnit.assertTrue("Mapping page: Buyer order sales number label incorrectly displayed",this.getBuyerNumberLabel().getText().equals(mapping[18][0]));
        inputKeys.click(this.getBuyerNumberField()).build().perform();
        inputKeys.sendKeys(mapping[18][1]).build().perform();
        
        //Other Information
        AssertJUnit.assertTrue("Mapping page: Other Information field not displayed",this.getOtherInformationField().isDisplayed());
        AssertJUnit.assertTrue("Mapping page: Other Information label incorrectly displayed",this.getOtherInformationLabel().getText().equals(mapping[19][0]));
        inputKeys.click(this.getOtherInformationField()).build().perform();
        inputKeys.sendKeys(mapping[19][1]).build().perform();
        
        //Customer Price
        AssertJUnit.assertTrue("Mapping page: Customer price field not displayed",this.getCustomerPriceField().isDisplayed());
        AssertJUnit.assertTrue("Mapping page: Customer price label incorrectly displayed",this.getCustomerPriceLabel().getText().equals(mapping[20][0]));
        inputKeys.click(this.getCustomerPriceField()).build().perform();
        inputKeys.sendKeys(mapping[20][1]).build().perform();
        
        //Remove focus from final field to complete input
        inputKeys.click(this.getCustomerPriceLabel()).build().perform();
        
        return this;
    }
    
    public Ecomm_MappingPage setMappingNew(String[][] mapping, boolean contract, boolean subaccount, boolean requestor) {
        WebElement confirmBtn = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(confirmButtonLocator));
        
        WebElement article = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(articleFieldLocator));
        AssertJUnit.assertTrue("Mapping Page: Article field not in expected position",driver.findElement(articleLabelLocator).getText().equals(mapping[0][0]));
        Select articleSelect = new Select(article);
        article.click();
        articleSelect.selectByVisibleText(mapping[0][1]);
        
        WebElement ticket = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(ticketFieldLocator));
        AssertJUnit.assertTrue("Mapping Page: Ticket field not in expected position",driver.findElement(ticketLabelLocator).getText().equals(mapping[1][0]));
        Select ticketSelect = new Select(ticket);
        ticket.click();
        ticketSelect.selectByVisibleText(mapping[1][1]);
        
        WebElement finish = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(finishFieldLocator));
        AssertJUnit.assertTrue("Mapping Page: Finish field not in expected position",driver.findElement(finishLabelLocator).getText().equals(mapping[2][0]));
        Select finishSelect = new Select(finish);
        finish.click();
        finishSelect.selectByVisibleText(mapping[2][1]);
        
        WebElement shade = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(shadeCodeFieldLocator));
        AssertJUnit.assertTrue("Mapping Page: Shade Code field not in expected position",driver.findElement(shadeCodeLabelLocator).getText().equals(mapping[3][0]));
        Select shadeSelect = new Select(shade);
        shade.click();
        shadeSelect.selectByVisibleText(mapping[3][1]);
        
        WebElement reqDate = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(requiredDateFieldLocator));
        AssertJUnit.assertTrue("Mapping Page: Required Date field not in expected position",driver.findElement(requiredDateLabelLocator).getText().equals(mapping[4][0]));
        Select reqDateSelect = new Select(reqDate);
        reqDate.click();
        reqDateSelect.selectByVisibleText(mapping[4][1]);
        
        WebElement qty = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(quantityFieldLocator));
        AssertJUnit.assertTrue("Mapping Page: Quantity field not in expected position",driver.findElement(quantityLabelLocator).getText().equals(mapping[5][0]));
        Select qtySelect = new Select(qty);
        qty.click();
        qtySelect.selectByVisibleText(mapping[5][1]);
        
        WebElement style = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(styleFieldLocator));
        AssertJUnit.assertTrue("Mapping Page: Style field not in expected position",driver.findElement(styleLabelLocator).getText().equals(mapping[6][0]));
        Select styleSelect = new Select(style);
        style.click();
        styleSelect.selectByVisibleText(mapping[6][1]);
        
        WebElement styleNo = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(styleNoFieldLocator));
        AssertJUnit.assertTrue("Mapping Page: Style No. field not in expected position",driver.findElement(styleNoLabelLocator).getText().equals(mapping[7][0]));
        Select styleNoSelect = new Select(styleNo);
        styleNo.click();
        styleNoSelect.selectByVisibleText(mapping[7][1]);
        
        if (contract) {
            WebElement contractPO = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(contractPONoLocator));
            AssertJUnit.assertTrue("Mapping Page: Contract PO No. field not in expected position",driver.findElement(contractPONoLabelLocator).getText().equals(mapping[8][0]));
            Select contractPOSelect = new Select(contractPO);
            contractPO.click();
            contractPOSelect.selectByVisibleText(mapping[8][1]);
        }
        
        WebElement customerPrice = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(customerPriceFieldLocator));
        AssertJUnit.assertTrue("Mapping Page: Customer Price field not in expected position",driver.findElement(customerPriceLabelLocator).getText().equals(mapping[9][0]));
        Select customerPriceSelect = new Select(customerPrice);
        customerPrice.click();
        customerPriceSelect.selectByVisibleText(mapping[9][1]);
        
        if (subaccount) {
            WebElement subAcc = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(subAccountFieldLocator));
            AssertJUnit.assertTrue("Mapping Page: Sub Account field not in expected position",driver.findElement(subAccountLabelLocator).getText().equals(mapping[10][0]));
            Select subAccountSelect = new Select(subAcc);
            subAcc.click();
            subAccountSelect.selectByVisibleText(mapping[10][1]);
        }
        
        WebElement shipTo = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(shipToPartyFieldLocator));
        AssertJUnit.assertTrue("Mapping Page: Ship To Party field not in expected position",driver.findElement(shipToPartyLabelLocator).getText().equals(mapping[11][0]));
        Select shipToSelect = new Select(shipTo);
        shipTo.click();
        shipToSelect.selectByVisibleText(mapping[11][1]);
        
        WebElement yourMatNum = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(yourMaterialNumberFieldLocator));
        AssertJUnit.assertTrue("Mapping Page: Your Material Number field not in expected position",driver.findElement(yourMaterialNumberLabelLocator).getText().equals(mapping[12][0]));
        Select yourMatNumSelect = new Select(yourMatNum);
        yourMatNum.click();
        yourMatNumSelect.selectByVisibleText(mapping[12][1]);
        
        WebElement brand = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(brandFieldLocator));
        AssertJUnit.assertTrue("Mapping Page: Brand field not in expected position",driver.findElement(brandLabelLocator).getText().equals(mapping[13][0]));
        Select brandSelect = new Select(brand);
        brand.click();
        brandSelect.selectByVisibleText(mapping[13][1]);
        
        WebElement length = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(lengthFieldLocator));
        AssertJUnit.assertTrue("Mapping Page: Length field not in expected position",driver.findElement(lengthLabelLocator).getText().equals(mapping[14][0]));
        Select lengthSelect = new Select(length);
        length.click();
        lengthSelect.selectByVisibleText(mapping[14][1]);
        
        WebElement buyers = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(buyersFieldLocator));
        AssertJUnit.assertTrue("Mapping Page: Buyers field not in expected position",driver.findElement(buyersLabelLocator).getText().equals(mapping[15][0]));
        Select buyersSelect = new Select(buyers);
        buyers.click();
        buyersSelect.selectByVisibleText(mapping[15][1]);
        
        WebElement customerPO = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(customerPOFieldLocator));
        AssertJUnit.assertTrue("Mapping Page: Customer PO No. field not in expected position",driver.findElement(customerPOLabelLocator).getText().equals(mapping[16][0]));
        Select customerPOSelect = new Select(customerPO);
        customerPO.click();
        customerPOSelect.selectByVisibleText(mapping[16][1]);
        
        if (requestor) {
            WebElement requestorField = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(requestorNameFieldLocator));
            AssertJUnit.assertTrue("Mapping Page: Requestor Name field not in expected position",driver.findElement(requestorNameLabelLocator).getText().equals(mapping[17][0]));
            Select requestorSelect = new Select(requestorField);
            requestorField.click();
            requestorSelect.selectByVisibleText(mapping[17][1]);
        }
        
        WebElement warehouseInst = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(warehouseInstFieldLocator));
        AssertJUnit.assertTrue("Mapping Page: Warehouse Instructions field not in expected position",driver.findElement(warehouseInstLabelLocator).getText().equals(mapping[18][0]));
        Select warehouseInstSelect = new Select(warehouseInst);
        warehouseInst.click();
        warehouseInstSelect.selectByVisibleText(mapping[18][1]);
        
        WebElement buyerNo = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(buyerNumberFieldLocator));
        AssertJUnit.assertTrue("Mapping Page: Buyer Order Sales Number field not in expected position",driver.findElement(buyerNumberLabelLocator).getText().equals(mapping[19][0]));
        Select buyerNumberSelect = new Select(buyerNo);
        buyerNo.click();
        buyerNumberSelect.selectByVisibleText(mapping[19][1]);
        
        WebElement otherInfo = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(otherInformationFieldLocator));
        AssertJUnit.assertTrue("Mapping Page: Other Information field not in expected position",driver.findElement(otherInformationLabelLocator).getText().equals(mapping[20][0]));
        Select otherInfoSelect = new Select(otherInfo);
        otherInfo.click();
        otherInfoSelect.selectByVisibleText(mapping[20][1]);
        
        return new Ecomm_MappingPage(driver);
    }
    
    public Ecomm_MappingPage setMappingNotCustomer(String[][] mapping) {
        //Wait for page to load
        WebElement waitForLoad = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(confirmButtonLocator));
        
        Actions inputKeys = new Actions(driver);
        
        //Check and set fields
        
        //Sales Organisation
        AssertJUnit.assertTrue("Mapping page: Sales Organisation label incorrectly displayed",this.getSalesOrgLabel().getText().equals("Sales Organization"));
        inputKeys.click(this.getSalesOrgField()).build().perform();
        
        //Customer Name
        AssertJUnit.assertTrue("Mapping page: Custome name label incorrectly displayed",this.getCustNameLabel().getText().equals(mapping[0][0]));

        //Article
        AssertJUnit.assertTrue("Mapping page: Article field not displayed",this.getArticleField().isDisplayed());
        AssertJUnit.assertTrue("Mapping page: Article label incorrectly displayed",this.getArticleLabel().getText().equals(mapping[1][0]));
        inputKeys.click(this.getArticleField()).build().perform();
        WebElement waitForOptions = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.visibilityOfElementLocated(articleOptionLocator));
        inputKeys.sendKeys(mapping[1][1]).build().perform();
        //Wait for field to update
        Boolean waitForUpdate = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.textToBePresentInElementLocated(articleFieldLocator, mapping[1][1]));
        
        //Ticket
        AssertJUnit.assertTrue("Mapping page: Ticket field not displayed",this.getTicketField().isDisplayed());
        AssertJUnit.assertTrue("Mapping page: Ticket label incorrectly displayed",this.getTicketLabel().getText().equals(mapping[2][0]));
        inputKeys.click(this.getTicketField()).build().perform();
        inputKeys.sendKeys(mapping[2][1]).build().perform();
        
        //Finish
        AssertJUnit.assertTrue("Mapping page: Finish field not displayed",this.getFinishField().isDisplayed());
        AssertJUnit.assertTrue("Mapping page: Finish label incorrectly displayed",this.getFinishLabel().getText().equals(mapping[3][0]));
        inputKeys.click(this.getFinishField()).build().perform();
        inputKeys.sendKeys(mapping[3][1]).build().perform();
        
        //Shade code
        AssertJUnit.assertTrue("Mapping page: Shade code field not displayed",this.getShadeCodeField().isDisplayed());
        AssertJUnit.assertTrue("Mapping page: Shade code label incorrectly displayed",this.getShadeCodeLabel().getText().equals(mapping[4][0]));
        inputKeys.click(this.getShadeCodeField()).build().perform();
        inputKeys.sendKeys(mapping[4][1]).build().perform();
        
        //Required date
        AssertJUnit.assertTrue("Mapping page: Required Date field not displayed",this.getRequiredDateField().isDisplayed());
        AssertJUnit.assertTrue("Mapping page: Required Date label incorrectly displayed",this.getRequiredDateLabel().getText().equals(mapping[5][0]));
        inputKeys.click(this.getRequiredDateField()).build().perform();
        inputKeys.sendKeys(mapping[5][1]).build().perform();
        
        //Qty
        AssertJUnit.assertTrue("Mapping page: Quantity field not displayed",this.getQuantityField().isDisplayed());
        AssertJUnit.assertTrue("Mapping page: Quantity label incorrectly displayed",this.getQuantityLabel().getText().equals(mapping[6][0]));
        inputKeys.click(this.getQuantityField()).build().perform();
        inputKeys.sendKeys(mapping[6][1]).build().perform();
        
        //Style
        AssertJUnit.assertTrue("Mapping page: Style field not displayed",this.getStyleField().isDisplayed());
        AssertJUnit.assertTrue("Mapping page: Style label incorrectly displayed",this.getStyleLabel().getText().equals(mapping[7][0]));
        inputKeys.click(this.getStyleField()).build().perform();
        inputKeys.sendKeys(mapping[7][1]).build().perform();
        
        //Style No.
        AssertJUnit.assertTrue("Mapping page: Style No field not displayed",this.getStyleNoField().isDisplayed());
        AssertJUnit.assertTrue("Mapping page: Style No. label incorrectly displayed",this.getStyleNoLabel().getText().equals(mapping[8][0]));
        inputKeys.click(this.getStyleNoField()).build().perform();
        inputKeys.sendKeys(mapping[8][1]).build().perform();       
        
        //Ship to party name
        AssertJUnit.assertTrue("Mapping page: Ship to party name field not displayed",this.getShipToPartyField().isDisplayed());
        AssertJUnit.assertTrue("Mapping page: Ship to party name label incorrectly displayed",this.getShipToPartyLabel().getText().equals(mapping[10][0]));
        inputKeys.click(this.getShipToPartyField()).build().perform();
        inputKeys.sendKeys(mapping[10][1]).build().perform();
        
        //Your material number
        AssertJUnit.assertTrue("Mapping page: Your material number field not displayed",this.getYourMaterialNumberField().isDisplayed());
        AssertJUnit.assertTrue("Mapping page: Your material number label incorrectly displayed",this.getYourMaterialNumberLabel().getText().equals(mapping[11][0]));
        inputKeys.click(this.getYourMaterialNumberField()).build().perform();
        inputKeys.sendKeys(mapping[11][1]).build().perform();
        
        //Brand
        AssertJUnit.assertTrue("Mapping page: Brand field not displayed",this.getBrandField().isDisplayed());
        AssertJUnit.assertTrue("Mapping page: Brand label incorrectly displayed",this.getBrandLabel().getText().equals(mapping[12][0]));
        inputKeys.click(this.getBrandField()).build().perform();
        inputKeys.sendKeys(mapping[12][1]).build().perform();
        
        //Length
        AssertJUnit.assertTrue("Mapping page: Length field not displayed",this.getLengthField().isDisplayed());
        AssertJUnit.assertTrue("Mapping page: Length label incorrectly displayed",this.getLengthLabel().getText().equals(mapping[13][0]));
        inputKeys.click(this.getLengthField()).build().perform();
        inputKeys.sendKeys(mapping[13][1]).build().perform();
        
        //Buyers
        AssertJUnit.assertTrue("Mapping page: Buyers field not displayed",this.getBuyersField().isDisplayed());
        AssertJUnit.assertTrue("Mapping page: Buyers label incorrectly displayed",this.getBuyersLabel().getText().equals(mapping[14][0]));
        inputKeys.click(this.getBuyersField()).build().perform();
        inputKeys.sendKeys(mapping[14][1]).build().perform();
        
        //Customer PO No
        AssertJUnit.assertTrue("Mapping page: Customer PO field not displayed",this.getCustomerPOField().isDisplayed());
        AssertJUnit.assertTrue("Mapping page: Customer PO label incorrectly displayed",this.getCustomerPOLabel().getText().equals(mapping[15][0]));
        inputKeys.click(this.getCustomerPOField()).build().perform();
        inputKeys.sendKeys(mapping[15][1]).build().perform();
        
        //Requestor name
        AssertJUnit.assertTrue("Mapping page: Requestor label incorrectly displayed",this.getRequestorNameLabel().getText().equals(mapping[16][0]));
        
        //Warehouse instruction
        AssertJUnit.assertTrue("Mapping page: Warehouse instruction field not displayed",this.getWarehouseInstField().isDisplayed());
        AssertJUnit.assertTrue("Mapping page: Warehouse instruction label incorrectly displayed",this.getWarehouseInstLabel().getText().equals(mapping[17][0]));
        inputKeys.click(this.getWarehouseInstField()).build().perform();
        inputKeys.sendKeys(mapping[17][1]).build().perform();
        
        //Buyer Order Sales Number
        AssertJUnit.assertTrue("Mapping page: Buyer order sales number field not displayed",this.getBuyerNumberField().isDisplayed());
        AssertJUnit.assertTrue("Mapping page: Buyer order sales number label incorrectly displayed",this.getBuyerNumberLabel().getText().equals(mapping[18][0]));
        inputKeys.click(this.getBuyerNumberField()).build().perform();
        inputKeys.sendKeys(mapping[18][1]).build().perform();
        
        //Other Information
        AssertJUnit.assertTrue("Mapping page: Other Information field not displayed",this.getOtherInformationField().isDisplayed());
        AssertJUnit.assertTrue("Mapping page: Other Information label incorrectly displayed",this.getOtherInformationLabel().getText().equals(mapping[19][0]));
        inputKeys.click(this.getOtherInformationField()).build().perform();
        inputKeys.sendKeys(mapping[19][1]).build().perform();
        
        //Customer Price
        AssertJUnit.assertTrue("Mapping page: Customer price field not displayed",this.getCustomerPriceField().isDisplayed());
        AssertJUnit.assertTrue("Mapping page: Customer price label incorrectly displayed",this.getCustomerPriceLabel().getText().equals(mapping[20][0]));
        inputKeys.click(this.getCustomerPriceField()).build().perform();
        inputKeys.sendKeys(mapping[20][1]).build().perform();
        
        //Remove focus from final field to complete input
        inputKeys.click(this.getCustomerPriceLabel()).build().perform();
        
        return this;
    }
    
    public Ecomm_MappingPage setSalesOrg(String item) throws InterruptedException {
        CommonTask.setDropDownField(driver, salesOrgFieldLocator, item);
        return this;
    }
    
    public Ecomm_MappingPage setCustomerName(String item) {
        CommonTask.setSearchField(driver, custNameFieldLocator, item);
        return this;
    }
    
    public Ecomm_MappingPage setArticle(String item) throws InterruptedException {
        CommonTask.setDropDownField(driver,articleFieldLocator,item);
        return this;
    }
    
    public Ecomm_MappingPage setTicket(String item) throws InterruptedException {
        CommonTask.setDropDownField(driver,ticketFieldLocator,item);
        return this;
    }
    
    public Ecomm_MappingPage setFinish(String item) throws InterruptedException {
        CommonTask.setDropDownField(driver,finishFieldLocator,item);
        return this;
    }
    
    public Ecomm_MappingPage setShadeCode(String item) throws InterruptedException {
        CommonTask.setDropDownField(driver,shadeCodeFieldLocator,item);
        return this;
    }
    
    public Ecomm_MappingPage setRequiredDate(String item) throws InterruptedException {
        CommonTask.setDropDownField(driver,requiredDateFieldLocator,item);
        return this;
    }
    
    public Ecomm_MappingPage setQty(String item) throws InterruptedException {
        CommonTask.setDropDownField(driver,quantityFieldLocator,item);
        return this;
    }
    
    public Ecomm_MappingPage setStyle(String item) throws InterruptedException {
        CommonTask.setDropDownField(driver,styleFieldLocator,item);
        return this;
    }
    
    public Ecomm_MappingPage setStyleNo(String item) throws InterruptedException {
        CommonTask.setDropDownField(driver,styleNoFieldLocator,item);
        return this;
    }
    
    public Ecomm_MappingPage setContractPO(String item) throws InterruptedException {
        CommonTask.setDropDownField(driver,contractPONoLocator,item);
        return this;
    }
    
    public Ecomm_MappingPage setSubAcct(String item) throws InterruptedException {
        CommonTask.setDropDownField(driver,subAccountFieldLocator,item);
        return this;
    }
    
    public Ecomm_MappingPage setShipToParty(String item) throws InterruptedException {
        CommonTask.setDropDownField(driver,shipToPartyFieldLocator,item);
        return this;
    }
    
    public Ecomm_MappingPage setYourMatNum(String item) throws InterruptedException {
        CommonTask.setDropDownField(driver,yourMaterialNumberFieldLocator,item);
        return this;
    }
    
    public Ecomm_MappingPage setBrand(String item) throws InterruptedException {
        CommonTask.setDropDownField(driver,brandFieldLocator,item);
        return this;
    }
    
    public Ecomm_MappingPage setLength(String item) throws InterruptedException {
        CommonTask.setDropDownField(driver,lengthFieldLocator,item);
        return this;
    }
    
    public Ecomm_MappingPage setBuyers(String item) throws InterruptedException {
        CommonTask.setDropDownField(driver,buyersFieldLocator,item);
        return this;
    }
    
    public Ecomm_MappingPage setCustPO(String item) throws InterruptedException {
        CommonTask.setDropDownField(driver,customerPOFieldLocator,item);
        return this;
    }
    
    public Ecomm_MappingPage setRequestor(String item) throws InterruptedException {
        CommonTask.setDropDownField(driver,requestorNameFieldLocator,item);
        return this;
    }
    
    public Ecomm_MappingPage setWarehouseInst(String item) throws InterruptedException {
        CommonTask.setDropDownField(driver,warehouseInstFieldLocator,item);
        return this;
    }
    
    public Ecomm_MappingPage setBuyerNumber(String item) throws InterruptedException {
        CommonTask.setDropDownField(driver,buyerNumberFieldLocator,item);
        return this;
    }
    
    public Ecomm_MappingPage setOtherInfo(String item) throws InterruptedException {
        CommonTask.setDropDownField(driver,otherInformationFieldLocator,item);
        return this;
    }
    
    public Ecomm_MappingPage setLineRef(String item) throws InterruptedException {
        CommonTask.setDropDownField(driver,lineRefFieldLocator,item);
        return this;
    }
    
    public Ecomm_MappingPage setCustPrice(String item) throws InterruptedException {
        CommonTask.setDropDownField(driver,customerPriceFieldLocator,item);
        return this;
    }
    
    public void checkFieldsContractOrder() {
        //Wait for all elements to be clickable
        WebElement article = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(articleFieldLocator));
        WebElement ticket = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(ticketFieldLocator));
        WebElement shadeCode = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(shadeCodeFieldLocator));
        WebElement qty = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(quantityFieldLocator));
        WebElement contractPO = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(contractPONoLocator));
        WebElement lineRef = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(contractPONoLocator));
        WebElement shipTo = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(shipToPartyFieldLocator));
        WebElement length = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(lengthFieldLocator));
        WebElement confirm = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(confirmButtonLocator));

        //Assert all elements are displayed
        AssertJUnit.assertTrue("Mapping Page (contract order): Article field not displayed",article.isDisplayed());
        AssertJUnit.assertTrue("Mapping Page (contract order): Ticket field not displayed",ticket.isDisplayed());
        AssertJUnit.assertTrue("Mapping Page (contract order): Shade Code field not displayed",shadeCode.isDisplayed());
        AssertJUnit.assertTrue("Mapping Page (contract order): Quantity field not displayed",qty.isDisplayed());
        AssertJUnit.assertTrue("Mapping Page (contract order): Contract PO No. field not displayed",contractPO.isDisplayed());
        AssertJUnit.assertTrue("Mapping Page (contract order): Line Reference field not displayed",lineRef.isDisplayed());
        AssertJUnit.assertTrue("Mapping Page (contract order): Ship To Party field not displayed",shipTo.isDisplayed());
        AssertJUnit.assertTrue("Mapping Page (contract order): Length field not displayed",length.isDisplayed());
        AssertJUnit.assertTrue("Mapping Page (contract order): Confirm button not displayed",confirm.isDisplayed());
        
    }
    
    public void waitForElement() {
        WebElement wait = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.visibilityOfElementLocated(frameLocator));
    }
    
}
