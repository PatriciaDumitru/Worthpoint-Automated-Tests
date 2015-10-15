
package PageObjects;

import AutomationFramework.Categories;
import AutomationFramework.CommonTask;
import AutomationFramework.DataItems;
import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
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
    By contractPONoLocator = By.id("BulkOrderLineContract");
    By subAccountFieldLocator = By.id("BulkOrderPayerId");
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
        WebElement waitForClickable = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(confirmButtonLocator));
        //New action to click confirm
        Actions clickConfirm = new Actions(driver);
        clickConfirm.click(driver.findElement(confirmButtonLocator)).build().perform();
        //Confirm alert
        Alert alert = new WebDriverWait(driver,10).until(ExpectedConditions.alertIsPresent());
        alert.accept();
        
        Alert alert2 = new WebDriverWait(driver,10).until(ExpectedConditions.alertIsPresent());
        alert2.accept();
        
        return new Ecomm_OrderConfirmationPage(driver);
    }
    
    public Ecomm_MappingPage setMapping(String[][] mapping) {
        //Wait for page to load
        WebElement waitForLoad = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(confirmButtonLocator));
        
        Actions inputKeys = new Actions(driver);
        
        //Check and set fields
        
        //Sales Organisation
        Assert.assertTrue("Mapping page: Sales organisation field not displayed", this.getSalesOrgField().isDisplayed());
        Assert.assertTrue("Mapping page: Sales Organisation label incorrectly displayed",this.getSalesOrgLabel().getText().equals("Sales Organization"));
        inputKeys.click(this.getSalesOrgField()).build().perform();
        inputKeys.sendKeys(DataItems.salesOrganisation+Keys.ENTER).build().perform();
        
        //Customer Name
        Assert.assertTrue("Mapping page: Customer name field not displayed",this.getCustNameField().isDisplayed());
        Assert.assertTrue("Mapping page: Custome name label incorrectly displayed",this.getCustNameLabel().getText().equals(mapping[0][0]));
        inputKeys.click(this.getCustNameField()).build().perform();
        inputKeys.sendKeys(DataItems.custDetails[0]).build().perform();
        //Wait for result to appear and press enter
        WebElement waitForResult = new WebDriverWait(driver,10).until(ExpectedConditions.visibilityOfElementLocated(custNameResultLocator));
        boolean waitForText = new WebDriverWait(driver,10).until(ExpectedConditions.textToBePresentInElementLocated(custNameResultLocator, DataItems.custDetails[0]));
        inputKeys.sendKeys(Keys.ENTER);
        
        //Article
        Assert.assertTrue("Mapping page: Article field not displayed",this.getArticleField().isDisplayed());
        Assert.assertTrue("Mapping page: Article label incorrectly displayed",this.getArticleLabel().getText().equals(mapping[1][0]));
        inputKeys.click(this.getArticleField()).build().perform();
        WebElement waitForOptions = new WebDriverWait(driver,10).until(ExpectedConditions.visibilityOfElementLocated(articleOptionLocator));
        inputKeys.sendKeys(mapping[1][1]).build().perform();
        //Wait for field to update
        Boolean waitForUpdate = new WebDriverWait(driver,10).until(ExpectedConditions.textToBePresentInElementLocated(articleFieldLocator, mapping[1][1]));
        
        //Ticket
        Assert.assertTrue("Mapping page: Ticket field not displayed",this.getTicketField().isDisplayed());
        Assert.assertTrue("Mapping page: Ticket label incorrectly displayed",this.getTicketLabel().getText().equals(mapping[2][0]));
        inputKeys.click(this.getTicketField()).build().perform();
        inputKeys.sendKeys(mapping[2][1]).build().perform();
        
        //Finish
        Assert.assertTrue("Mapping page: Finish field not displayed",this.getFinishField().isDisplayed());
        Assert.assertTrue("Mapping page: Finish label incorrectly displayed",this.getFinishLabel().getText().equals(mapping[3][0]));
        inputKeys.click(this.getFinishField()).build().perform();
        inputKeys.sendKeys(mapping[3][1]).build().perform();
        
        //Shade code
        Assert.assertTrue("Mapping page: Shade code field not displayed",this.getShadeCodeField().isDisplayed());
        Assert.assertTrue("Mapping page: Shade code label incorrectly displayed",this.getShadeCodeLabel().getText().equals(mapping[4][0]));
        inputKeys.click(this.getShadeCodeField()).build().perform();
        inputKeys.sendKeys(mapping[4][1]).build().perform();
        
        //Required date
        Assert.assertTrue("Mapping page: Required Date field not displayed",this.getRequiredDateField().isDisplayed());
        Assert.assertTrue("Mapping page: Required Date label incorrectly displayed",this.getRequiredDateLabel().getText().equals(mapping[5][0]));
        inputKeys.click(this.getRequiredDateField()).build().perform();
        inputKeys.sendKeys(mapping[5][1]).build().perform();
        
        //Qty
        Assert.assertTrue("Mapping page: Quantity field not displayed",this.getQuantityField().isDisplayed());
        Assert.assertTrue("Mapping page: Quantity label incorrectly displayed",this.getQuantityLabel().getText().equals(mapping[6][0]));
        inputKeys.click(this.getQuantityField()).build().perform();
        inputKeys.sendKeys(mapping[6][1]).build().perform();
        
        //Style
        Assert.assertTrue("Mapping page: Style field not displayed",this.getStyleField().isDisplayed());
        Assert.assertTrue("Mapping page: Style label incorrectly displayed",this.getStyleLabel().getText().equals(mapping[7][0]));
        inputKeys.click(this.getStyleField()).build().perform();
        inputKeys.sendKeys(mapping[7][1]).build().perform();
        
        //Style No.
        Assert.assertTrue("Mapping page: Style No field not displayed",this.getStyleNoField().isDisplayed());
        Assert.assertTrue("Mapping page: Style No. label incorrectly displayed",this.getStyleNoLabel().getText().equals(mapping[8][0]));
        inputKeys.click(this.getStyleNoField()).build().perform();
        inputKeys.sendKeys(mapping[8][1]).build().perform();       
        
        //Ship to party name
        Assert.assertTrue("Mapping page: Ship to party name field not displayed",this.getShipToPartyField().isDisplayed());
        Assert.assertTrue("Mapping page: Ship to party name label incorrectly displayed",this.getShipToPartyLabel().getText().equals(mapping[10][0]));
        inputKeys.click(this.getShipToPartyField()).build().perform();
        inputKeys.sendKeys(mapping[10][1]).build().perform();
        
        //Your material number
        Assert.assertTrue("Mapping page: Your material number field not displayed",this.getYourMaterialNumberField().isDisplayed());
        Assert.assertTrue("Mapping page: Your material number label incorrectly displayed",this.getYourMaterialNumberLabel().getText().equals(mapping[11][0]));
        inputKeys.click(this.getYourMaterialNumberField()).build().perform();
        inputKeys.sendKeys(mapping[11][1]).build().perform();
        
        //Brand
        Assert.assertTrue("Mapping page: Brand field not displayed",this.getBrandField().isDisplayed());
        Assert.assertTrue("Mapping page: Brand label incorrectly displayed",this.getBrandLabel().getText().equals(mapping[12][0]));
        inputKeys.click(this.getBrandField()).build().perform();
        inputKeys.sendKeys(mapping[12][1]).build().perform();
        
        //Length
        Assert.assertTrue("Mapping page: Length field not displayed",this.getLengthField().isDisplayed());
        Assert.assertTrue("Mapping page: Length label incorrectly displayed",this.getLengthLabel().getText().equals(mapping[13][0]));
        inputKeys.click(this.getLengthField()).build().perform();
        inputKeys.sendKeys(mapping[13][1]).build().perform();
        
        //Buyers
        Assert.assertTrue("Mapping page: Buyers field not displayed",this.getBuyersField().isDisplayed());
        Assert.assertTrue("Mapping page: Buyers label incorrectly displayed",this.getBuyersLabel().getText().equals(mapping[14][0]));
        inputKeys.click(this.getBuyersField()).build().perform();
        inputKeys.sendKeys(mapping[14][1]).build().perform();
        
        //Customer PO No
        Assert.assertTrue("Mapping page: Customer PO field not displayed",this.getCustomerPOField().isDisplayed());
        Assert.assertTrue("Mapping page: Customer PO label incorrectly displayed",this.getCustomerPOLabel().getText().equals(mapping[15][0]));
        inputKeys.click(this.getCustomerPOField()).build().perform();
        inputKeys.sendKeys(mapping[15][1]).build().perform();
        
        //Requestor name
        Assert.assertTrue("Mapping page: Requestor field not displayed",this.getRequestorNameField().isDisplayed());
        Assert.assertTrue("Mapping page: Requestor label incorrectly displayed",this.getRequestorNameLabel().getText().equals(mapping[16][0]));
        inputKeys.click(this.getRequestorNameField()).build().perform();
        inputKeys.sendKeys(mapping[16][1]).build().perform();
        
        //Warehouse instruction
        Assert.assertTrue("Mapping page: Warehouse instruction field not displayed",this.getWarehouseInstField().isDisplayed());
        Assert.assertTrue("Mapping page: Warehouse instruction label incorrectly displayed",this.getWarehouseInstLabel().getText().equals(mapping[17][0]));
        inputKeys.click(this.getWarehouseInstField()).build().perform();
        inputKeys.sendKeys(mapping[17][1]).build().perform();
        
        //Buyer Order Sales Number
        Assert.assertTrue("Mapping page: Buyer order sales number field not displayed",this.getBuyerNumberField().isDisplayed());
        Assert.assertTrue("Mapping page: Buyer order sales number label incorrectly displayed",this.getBuyerNumberLabel().getText().equals(mapping[18][0]));
        inputKeys.click(this.getBuyerNumberField()).build().perform();
        inputKeys.sendKeys(mapping[18][1]).build().perform();
        
        //Other Information
        Assert.assertTrue("Mapping page: Other Information field not displayed",this.getOtherInformationField().isDisplayed());
        Assert.assertTrue("Mapping page: Other Information label incorrectly displayed",this.getOtherInformationLabel().getText().equals(mapping[19][0]));
        inputKeys.click(this.getOtherInformationField()).build().perform();
        inputKeys.sendKeys(mapping[19][1]).build().perform();
        
        //Customer Price
        Assert.assertTrue("Mapping page: Customer price field not displayed",this.getCustomerPriceField().isDisplayed());
        Assert.assertTrue("Mapping page: Customer price label incorrectly displayed",this.getCustomerPriceLabel().getText().equals(mapping[20][0]));
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
    
}
