
package PageObjects;

import AutomationFramework.CommonTask;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class TotalOrdersPage_CCE extends BasePage {
    
    //Locators
    By breadcrumb = By.cssSelector("#content > h2");
    By mumTypeBox = By.id("fields14");
    By articleBox = By.id("fields11");
    By brandBox = By.id("fields12");
    By ticketBox = By.id("fields13");
    By custRefBox = By.id("fields17");
    By qtyOrderedBox = By.id("fields18");
    By qtyProducedBox = By.id("fields19");
    By shadeCodeBox = By.id("fields36");
    By custNameBox = By.id("fields40");
    By requesterFirstBox = By.id("fields45");
    By requesterSecondBox = By.id("fields46");
    By busPrincBox = By.id("fields50");
    
    By orderNoField = By.id("s2id_filterSampleOrderId");
    By plantNameField = By.cssSelector("#s2id_filterSampleOrderLineDeliveryPlantId > ul > li > input");
    By custCodeField = By.cssSelector("#s2id_filterSampleOrderCustomerIdCode > ul > li > input");
    By requesterField = By.cssSelector("#s2id_filterSampleOrderRequesterId > ul > li > input");
    By orderTypeField = By.cssSelector("#s2id_filterSampleOrderLineOrderTypeId > ul > li > input");
    By requestTypeField = By.id("s2id_filterSampleOrderLineRequestTypeId");
    By brandField = By.cssSelector("#s2id_filterSampleOrderLineBrandId > ul > li > input");
    By cceSolutionField = By.id("s2id_filterSampleOrderDataCceSolutionId");
    By deliveredFromField = By.id("filterSampleOrderLineDeliveredDateFrom");
    By deliveredToField = By.id("filterSampleOrderLineDeliveredDateTo");
    By countryField = By.cssSelector("#s2id_filterSampleOrderCountryId > ul > li > input");
    By busPrincField = By.cssSelector("#s2id_filterSampleOrderBusinessPrincipalId > ul > li > input");
    By shipToNameField = By.cssSelector("#s2id_filterSampleOrderShipToPartyIdName > ul > li > input");
    By hubField = By.cssSelector("#s2id_filterSampleOrderHubId > ul > li > input");
    By orderStageField = By.cssSelector("#s2id_filterSampleOrderLineStageId > ul > li > input");
    By mumTypeField = By.cssSelector("#s2id_filterSampleOrderLineMumTypeId > ul > li > input");
    By ticketField = By.cssSelector("#s2id_filterSampleOrderLineTicketId > ul > li > input");
    By createdFromField = By.id("filterSampleOrderCreatedFrom");
    By createdToField = By.id("filterSampleOrderCreatedTo");
    By salesOrgField = By.cssSelector("#s2id_filterSampleOrderSalesOrgId > ul > li > input");
    By custNameField = By.cssSelector("#s2id_filterSampleOrderCustomerIdName > ul > li > input");
    By shipToCodeField = By.cssSelector("#s2id_filterSampleOrderShipToPartyIdCode > ul > li > input");
    By fceNameField = By.cssSelector("#s2id_filterSampleOrderFceId > ul > li > input");
    By scenarioField = By.cssSelector("#s2id_filterSampleOrderLineScenarioId > ul > li > input");
    By articleField = By.cssSelector("#s2id_filterSampleOrderLineArticleId > ul > li > input");
    By outstOfField = By.cssSelector("#s2id_filterSampleOrderRequestedEdc > input");
    By fabRefField = By.id("filterSampleOrderLineFabricReferenceColourName");
    
    By printButton = By.cssSelector("#FilterTotalOrderReportForm > div:nth-child(25) > div > ul > li:nth-child(1)");
    By exportButton = By.cssSelector("#FilterTotalOrderReportForm > div:nth-child(25) > div > ul > li:nth-child(2)");
    By resetButton = By.cssSelector("#FilterTotalOrderReportForm > div:nth-child(25) > div > ul > li:nth-child(3)");
    
    public TotalOrdersPage_CCE(WebDriver passedDriver) {
        super(passedDriver);
    }
    
    public WebElement getBreadcrumb() {
        WebElement wait = new WebDriverWait(driver,10).until(ExpectedConditions.visibilityOfElementLocated(breadcrumb));
        return driver.findElement(breadcrumb);
    }
    
    public WebElement getMUMTypeBox() {
        return driver.findElement(mumTypeBox);
    }
    
    public WebElement getArticleBox() {
        return driver.findElement(articleBox);
    }
    
    public WebElement getBrandBox() {
        return driver.findElement(brandBox);
    }
    
    public WebElement getTicketBox() {
        return driver.findElement(ticketBox);
    }
    
    public WebElement getCustRefBox() {
        return driver.findElement(custRefBox);
    }
    
    public WebElement getQtyProdBox() {
        return driver.findElement(qtyProducedBox);
    }
    
    public WebElement getQtyOrdBox() {
        return driver.findElement(qtyOrderedBox);
    }
    
    public WebElement getShadeCodeBox() {
        return driver.findElement(shadeCodeBox);
    }
    
    public WebElement getCustNameBox() {
        return driver.findElement(custNameBox);
    }
    
    public WebElement getRequesterFirstBox() {
        return driver.findElement(requesterFirstBox);
    }
    
    public WebElement getRequesterSecondBox() {
        return driver.findElement(requesterSecondBox);
    }
    
    public WebElement getBusPrincBox() {
        return driver.findElement(busPrincBox);
    }
    
    public WebElement getOrderNoField() {
        return driver.findElement(orderNoField);
    }
    
    public WebElement getPlantNameField() {
        return driver.findElement(plantNameField);
    }
    
    public WebElement getCustCodeField() {
        return driver.findElement(custCodeField);
    }
    
    public WebElement getRequesterField() {
        return driver.findElement(requesterField);
    }
    
    public WebElement getOrderTypeField() {
        return driver.findElement(orderTypeField);
    }
    
    public WebElement getRequestTypeField() {
        return driver.findElement(requestTypeField);
    }
    
    public WebElement getBrandField() {
        return driver.findElement(brandField);
    }
    
    public WebElement getTicketField() {
        return driver.findElement(ticketField);
    }
    
    public WebElement getCceSolutionField() {
        return driver.findElement(cceSolutionField);
    }
    
    public WebElement getDeliveredFromField() {
        return driver.findElement(deliveredFromField);
    }
    
    public WebElement getDeliveredToField() {
        return driver.findElement(deliveredToField);
    }
    
    public WebElement getCountryField() {
        return driver.findElement(countryField);
    }
    
    public WebElement getBusPrincField() {
        return driver.findElement(busPrincField);
    }
    
    public WebElement getShipToNameField() {
        return driver.findElement(shipToNameField);
    }
    
    public WebElement getHubField() {
        return driver.findElement(hubField);
    }
    
    public WebElement getMUMTypeField() {
        return driver.findElement(mumTypeField);
    }
    
    public WebElement getOrderStageField() {
        return driver.findElement(orderStageField);
    }
    
    public WebElement getCreatedFromField() {
        return driver.findElement(createdFromField);
    }
    
    public WebElement getCreatedToField() {
        return driver.findElement(createdToField);
    }
    
    public WebElement getSalesOrgField() {
        return driver.findElement(salesOrgField);
    }
    
    public WebElement getCustNameField() {
        return driver.findElement(custNameField);
    }
    
    public WebElement getShipToCodeField() {
        return driver.findElement(orderNoField);
    }
    
    public WebElement getFceNameField() {
        return driver.findElement(orderNoField);
    }
    
    public WebElement getScenarioField() {
        return driver.findElement(scenarioField);
    }
    
    public WebElement getArticleField() {
        return driver.findElement(articleField);
    }
    
    public WebElement getOutstOfField() {
        return driver.findElement(outstOfField);
    }
    
    public WebElement getFabRefField() {
        return driver.findElement(fabRefField);
    }
    
    public TotalOrdersPage_CCE tickMUMType() {
        WebElement waitForClickable = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(mumTypeBox));
        driver.findElement(mumTypeBox).click();
        return this;
    }
    
    public TotalOrdersPage_CCE tickArticle() {
        WebElement waitForClickable = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(articleBox));
        driver.findElement(articleBox).click();
        return this;
    }
    
    public TotalOrdersPage_CCE tickBrand() {
        WebElement waitForClickable = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(brandBox));
        driver.findElement(brandBox).click();
        return this;
    }
    
    public TotalOrdersPage_CCE tickTicket() {
        WebElement waitForClickable = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(ticketBox));
        driver.findElement(ticketBox).click();
        return this;
    }
    
    public TotalOrdersPage_CCE tickCustRef() {
        WebElement waitForClickable = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(custRefBox));
        driver.findElement(custRefBox).click();
        return this;
    }
    
    public TotalOrdersPage_CCE tickQtyProd() {
        WebElement waitForClickable = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(qtyProducedBox));
        driver.findElement(qtyProducedBox).click();
        return this;
    }
    
    public TotalOrdersPage_CCE tickQtyOrd() {
        WebElement waitForClickable = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(qtyOrderedBox));
        driver.findElement(qtyOrderedBox).click();
        return this;
    }
    
    public TotalOrdersPage_CCE tickShadeCode() {
        WebElement waitForClickable = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(shadeCodeBox));
        driver.findElement(shadeCodeBox).click();
        return this;
    }
    
    public TotalOrdersPage_CCE tickCustName() {
        WebElement waitForClickable = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(custNameBox));
        driver.findElement(custNameBox).click();
        return this;
    }
    
    public TotalOrdersPage_CCE tickRequesterFirst() {
        WebElement waitForClickable = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(requesterFirstBox));
        driver.findElement(requesterFirstBox).click();
        return this;
    }
    
    public TotalOrdersPage_CCE tickRequesterSecond() {
        WebElement waitForClickable = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(requesterSecondBox));
        driver.findElement(requesterSecondBox).click();
        return this;
    }
    
    public TotalOrdersPage_CCE tickBusPrinc() {
        WebElement waitForClickable = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(busPrincBox));
        driver.findElement(busPrincBox).click();
        return this;
    }
    
    public TotalOrdersPage_CCE setOrderNo(String item) {
        CommonTask.setSearchField(driver, orderNoField, item);
        return this;
    }
    
    public TotalOrdersPage_CCE setRequester(String item) {
        CommonTask.setChoiceField(driver, requesterField, item);
        return this;
    }
    
    public TotalOrdersPage_CCE setCustName(String item) {
        CommonTask.setChoiceField(driver, custNameField, item);
        return this;
    }
       
    public TotalOrdersPage_CCE setShipToName(String item) {
        CommonTask.setChoiceField(driver, shipToNameField, item);
        return this;
    }
      
    public TotalOrdersPage_CCE setMUMType(String item) {
        CommonTask.setChoiceField(driver, mumTypeField, item);
        return this;
    }
    
    public OrderViewPage_CCE pressPrint() {
        WebElement waitForClickable = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(printButton));
        driver.findElement(printButton).click();
        return new OrderViewPage_CCE(driver);
    }
    
    public ExportDownloadPage_CCE pressExport() {
        WebElement waitForClickable = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(exportButton));
        driver.findElement(exportButton).click();
        return new ExportDownloadPage_CCE(driver);
    }
    
    public TotalOrdersPage_CCE pressReset() {
        WebElement waitForClickable = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(resetButton));
        driver.findElement(resetButton).click();
        return new TotalOrdersPage_CCE(driver);
    }
    
    public void checkFields() {
        //Wait for all elements to be clickable
        WebElement waitForMUMbox = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(mumTypeBox));
        WebElement waitForArticlebox = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(articleBox));
        WebElement waitForBrandbox = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(brandBox));
        WebElement waitForTicketbox = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(ticketBox));
        WebElement waitForCustRefbox = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(custRefBox));
        WebElement waitForQtyProdbox = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(qtyProducedBox));
        WebElement waitForQtyOrdbox = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(qtyOrderedBox));
        WebElement waitForShadeCodebox = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(shadeCodeBox));
        WebElement waitForCustNamebox = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(custNameBox));
        WebElement waitForReqFirstbox = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(requesterFirstBox));
        WebElement waitForReqSecondbox = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(requesterSecondBox));
        WebElement waitForBusPrincbox = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(busPrincBox));
        WebElement waitForOrderNoField = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(orderNoField));
        WebElement waitForPlantNameField = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(plantNameField));
        WebElement waitForCustCodeField = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(custCodeField));
        WebElement waitForRequesterField = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(requesterField));
        WebElement waitForOrderTypeField = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(orderTypeField));
        WebElement waitForRequestTypeField = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(requestTypeField));
        WebElement waitForBrandField = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(brandField));
        WebElement waitForTicketField = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(ticketField));
        WebElement waitForCceSolutionField = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(cceSolutionField));
        WebElement waitForDeliveredFromField = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(deliveredFromField));
        WebElement waitForDeliveredToField = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(deliveredToField));
        WebElement waitForCountryField = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(countryField));
        WebElement waitForBusPrincField = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(busPrincField));
        WebElement waitForShipToNameField = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(shipToNameField));
        WebElement waitForHubField = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(hubField));
        WebElement waitForMUMTypeField = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(mumTypeField));
        WebElement waitForOrderStageField = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(orderStageField));
        WebElement waitForCreatedFromField = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(createdFromField));
        WebElement waitForCreatedToField = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(createdToField));
        WebElement waitForSalesOrgField = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(salesOrgField));
        WebElement waitForCustNameField = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(custNameField));
        WebElement waitForShipToCodeField = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(shipToCodeField));
        WebElement waitForFCENameField = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(fceNameField));
        WebElement waitForScenarioField = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(scenarioField));
        WebElement waitForArticleField = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(articleField));
        WebElement waitForOutstOfField = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(outstOfField));
        WebElement waitForFabRefField = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(fabRefField));
        
        //Assert all elements are displayed
        Assert.assertTrue("Total Orders Page: MUM Type checkbox not displayed",getMUMTypeBox().isDisplayed());
        Assert.assertTrue("Total Orders Page: Article checkbox not displayed",getArticleBox().isDisplayed());
        Assert.assertTrue("Total Orders Page: Brand checkbox not displayed",getBrandBox().isDisplayed());
        Assert.assertTrue("Total Orders Page: Ticket checkbox not displayed",getTicketBox().isDisplayed());
        Assert.assertTrue("Total Orders Page: Customer Reference checkbox not displayed",getCustRefBox().isDisplayed());
        Assert.assertTrue("Total Orders Page: Quantity Produced checkbox not displayed",getQtyProdBox().isDisplayed());
        Assert.assertTrue("Total Orders Page: Quantity Ordered checkbox not displayed",getQtyOrdBox().isDisplayed());
        Assert.assertTrue("Total Orders Page: Shade Code checkbox not displayed",getShadeCodeBox().isDisplayed());
        Assert.assertTrue("Total Orders Page: Customer name checkbox not displayed",getCustNameBox().isDisplayed());
        Assert.assertTrue("Total Orders Page: Requester first name checkbox not displayed",getRequesterFirstBox().isDisplayed());
        Assert.assertTrue("Total Orders Page: Requester second name checkbox not displayed",getRequesterSecondBox().isDisplayed());
        Assert.assertTrue("Total Orders Page: Business Principal checkbox not displayed",getBusPrincBox().isDisplayed());
        Assert.assertTrue("Total Orders Page: Order No field not displayed",getOrderNoField().isDisplayed());
        Assert.assertTrue("Total Orders Page: Plant name field not displayed",getPlantNameField().isDisplayed());
        Assert.assertTrue("Total Orders Page: Customer Code field not displayed",getCustCodeField().isDisplayed());
        Assert.assertTrue("Total Orders Page: Requester field not displayed",getRequesterField().isDisplayed());
        Assert.assertTrue("Total Orders Page: Requester field not displayed",getRequesterField().isDisplayed());
        Assert.assertTrue("Total Orders Page: Order Type field not displayed",getOrderTypeField().isDisplayed());
        Assert.assertTrue("Total Orders Page: Requeset Type field not displayed",getRequestTypeField().isDisplayed());
        Assert.assertTrue("Total Orders Page: Brand field not displayed",getBrandField().isDisplayed());
        Assert.assertTrue("Total Orders Page: Ticket field not displayed",getTicketField().isDisplayed());
        Assert.assertTrue("Total Orders Page: CCE Solution field not displayed",getCceSolutionField().isDisplayed());
        Assert.assertTrue("Total Orders Page: Delivered From Date field not displayed",getDeliveredFromField().isDisplayed());
        Assert.assertTrue("Total Orders Page: Delivered To Date field not displayed",getDeliveredToField().isDisplayed());
        Assert.assertTrue("Total Orders Page: Country field not displayed",getCountryField().isDisplayed());
        Assert.assertTrue("Total Orders Page: Business Principal field not displayed",getBusPrincField().isDisplayed());
        Assert.assertTrue("Total Orders Page: Ship To Party Name field not displayed",getShipToNameField().isDisplayed());
        Assert.assertTrue("Total Orders Page: Hub field not displayed",getHubField().isDisplayed());
        Assert.assertTrue("Total Orders Page: MUM Type field not displayed",getMUMTypeField().isDisplayed());
        Assert.assertTrue("Total Orders Page: Order Stage field not displayed",getOrderStageField().isDisplayed());
        Assert.assertTrue("Total Orders Page: Created From Date field not displayed",getCreatedFromField().isDisplayed());
        Assert.assertTrue("Total Orders Page: Created To Date field not displayed",getCreatedToField().isDisplayed());
        Assert.assertTrue("Total Orders Page: Sales Organisation field not displayed",getSalesOrgField().isDisplayed());
        Assert.assertTrue("Total Orders Page: Customer Name field not displayed",getCustNameField().isDisplayed());
        Assert.assertTrue("Total Orders Page: Ship To Party Code field not displayed",getShipToCodeField().isDisplayed());
        Assert.assertTrue("Total Orders Page: FCE Name field not displayed",getFceNameField().isDisplayed());
        Assert.assertTrue("Total Orders Page: Scenario field not displayed",getScenarioField().isDisplayed());
        Assert.assertTrue("Total Orders Page: Article field not displayed",getArticleField().isDisplayed());
        Assert.assertTrue("Total Orders Page: Order Outstanding Of field not displayed",getOutstOfField().isDisplayed());
        Assert.assertTrue("Total Orders Page: Fabric Reference field not displayed",getFabRefField().isDisplayed());
        
    }
    
}
