
package PageObjects;

import AutomationFramework.CommonTask;
import AutomationFramework.Wait;
import org.testng.AssertJUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CCE_TotalOrdersPage extends WBA_BasePage {
    
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
    
    public CCE_TotalOrdersPage(WebDriver passedDriver) {
        super(passedDriver);
    }
    
    public WebElement getBreadcrumb() {
        return Wait.visible(driver,breadcrumb);   
    }
    
    public WebElement getTicketBox() {
        return driver.findElement(ticketBox);
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
    
    public CCE_TotalOrdersPage tickMUMType() {
        WebElement mum = Wait.clickable(driver,mumTypeBox);
        mum.click();
        return this;
    }
    
    public CCE_TotalOrdersPage tickArticle() {
        WebElement article = Wait.clickable(driver,articleBox);
        article.click();
        return this;
    }
    
    public CCE_TotalOrdersPage tickBrand() {
        WebElement item = Wait.clickable(driver,brandBox);
        item.click();
        return this;
    }
    
    public CCE_TotalOrdersPage tickTicket() {
        WebElement item = Wait.clickable(driver,ticketBox);
        item.click();
        return this;
    }
    
    public CCE_TotalOrdersPage tickCustRef() {
        WebElement item = Wait.clickable(driver,custRefBox);
        item.click();
        return this;
    }
    
    public CCE_TotalOrdersPage tickQtyProd() {
        WebElement item = Wait.clickable(driver,qtyProducedBox);
        item.click();
        return this;
    }
    
    public CCE_TotalOrdersPage tickQtyOrd() {
        WebElement item = Wait.clickable(driver,qtyOrderedBox);
        item.click();
        return this;
    }
    
    public CCE_TotalOrdersPage tickShadeCode() {
        WebElement item = Wait.clickable(driver,shadeCodeBox);
        item.click();
        return this;
    }
    
    public CCE_TotalOrdersPage tickCustName() {
        WebElement item = Wait.clickable(driver,custNameBox);
        item.click();
        return this;
    }
    
    public CCE_TotalOrdersPage tickRequesterFirst() {
        WebElement item = Wait.clickable(driver,requesterFirstBox);
        item.click();
        return this;
    }
    
    public CCE_TotalOrdersPage tickRequesterSecond() {
        WebElement item = Wait.clickable(driver,requesterSecondBox);
        item.click();
        return this;
    }
    
    public CCE_TotalOrdersPage tickBusPrinc() {
        WebElement item = Wait.clickable(driver,busPrincBox);
        item.click();
        return this;
    }
    
    public CCE_TotalOrdersPage setOrderNo(String item) {
        CommonTask.setSearchField(driver, orderNoField, item);
        return this;
    }
    
    public CCE_TotalOrdersPage setRequester(String item) {
        CommonTask.setChoiceField(driver, requesterField, item);
        return this;
    }
    
    public CCE_TotalOrdersPage setCustName(String item) {
        CommonTask.setChoiceField(driver, custNameField, item);
        return this;
    }
       
    public CCE_TotalOrdersPage setShipToName(String item) {
        CommonTask.setChoiceField(driver, shipToNameField, item);
        return this;
    }
      
    public CCE_TotalOrdersPage setMUMType(String item) {
        CommonTask.setChoiceField(driver, mumTypeField, item);
        return this;
    }
    
    public CCE_OrderViewPage pressPrint() {
        WebElement print = Wait.clickable(driver,printButton);
        print.click();
        return new CCE_OrderViewPage(driver);
    }
    
    public Ecomm_ExportDownloadPage pressExport() {
        WebElement export = Wait.clickable(driver,exportButton);
        export.click();
        return new Ecomm_ExportDownloadPage(driver);
    }
    
    public CCE_TotalOrdersPage pressReset() {
        WebElement reset = Wait.clickable(driver,resetButton);
        reset.click();
        return new CCE_TotalOrdersPage(driver);
    }
    
    public void checkFields() {
        //Wait for all elements to be clickable
        WebElement mum = Wait.clickable(driver,mumTypeBox);
        WebElement article = Wait.clickable(driver,articleBox);
        WebElement brand = Wait.clickable(driver,brandBox);
        WebElement ticket = Wait.clickable(driver,ticketBox);
        WebElement custRef = Wait.clickable(driver,custRefBox);
        WebElement qtyProd = Wait.clickable(driver,qtyProducedBox);
        WebElement qtyOrd = Wait.clickable(driver,qtyOrderedBox);
        WebElement shadeCode = Wait.clickable(driver,shadeCodeBox);
        WebElement custName = Wait.clickable(driver,custNameBox);
        WebElement reqFirst = Wait.clickable(driver,requesterFirstBox);
        WebElement reqSecond = Wait.clickable(driver,requesterSecondBox);
        WebElement busPrinc = Wait.clickable(driver,busPrincBox);
        WebElement orderNo = Wait.clickable(driver,orderNoField);
        WebElement plantName = Wait.clickable(driver,plantNameField);
        WebElement custCode = Wait.clickable(driver,custCodeField);
        WebElement requester = Wait.clickable(driver,requesterField);
        WebElement orderType = Wait.clickable(driver,orderTypeField);
        WebElement requestType = Wait.clickable(driver,requestTypeField);
        WebElement brandFld = Wait.clickable(driver,brandField);
        WebElement ticketFld = Wait.clickable(driver,ticketField);
        WebElement cceSolution = Wait.clickable(driver,cceSolutionField);
        WebElement delFrom = Wait.clickable(driver,deliveredFromField);
        WebElement delTo = Wait.clickable(driver,deliveredToField);
        WebElement country = Wait.clickable(driver,countryField);
        WebElement busPrincFld = Wait.clickable(driver,busPrincField);
        WebElement shipToName = Wait.clickable(driver,shipToNameField);
        WebElement hub = Wait.clickable(driver,hubField);
        WebElement mumType = Wait.clickable(driver,mumTypeField);
        WebElement orderStage = Wait.clickable(driver,orderStageField);
        WebElement createdFrom = Wait.clickable(driver,createdFromField);
        WebElement createdTo = Wait.clickable(driver,createdToField);
        WebElement salesOrg = Wait.clickable(driver,salesOrgField);
        WebElement custNameFld = Wait.clickable(driver,custNameField);
        WebElement shipToCode = Wait.clickable(driver,shipToCodeField);
        WebElement fce = Wait.clickable(driver,fceNameField);
        WebElement scenario = Wait.clickable(driver,scenarioField);
        WebElement articleFld = Wait.clickable(driver,articleField);
        WebElement outstOf = Wait.clickable(driver,outstOfField);
        WebElement fabRef = Wait.clickable(driver,fabRefField);
        
        //Assert all elements are displayed
        AssertJUnit.assertTrue("Total Orders Page: MUM Type checkbox not displayed",mum.isDisplayed());
        AssertJUnit.assertTrue("Total Orders Page: Article checkbox not displayed",article.isDisplayed());
        AssertJUnit.assertTrue("Total Orders Page: Brand checkbox not displayed",brand.isDisplayed());
        AssertJUnit.assertTrue("Total Orders Page: Ticket checkbox not displayed",ticket.isDisplayed());
        AssertJUnit.assertTrue("Total Orders Page: Customer Reference checkbox not displayed",custRef.isDisplayed());
        AssertJUnit.assertTrue("Total Orders Page: Quantity Produced checkbox not displayed",qtyProd.isDisplayed());
        AssertJUnit.assertTrue("Total Orders Page: Quantity Ordered checkbox not displayed",qtyOrd.isDisplayed());
        AssertJUnit.assertTrue("Total Orders Page: Shade Code checkbox not displayed",shadeCode.isDisplayed());
        AssertJUnit.assertTrue("Total Orders Page: Customer name checkbox not displayed",custName.isDisplayed());
        AssertJUnit.assertTrue("Total Orders Page: Requester first name checkbox not displayed",reqFirst.isDisplayed());
        AssertJUnit.assertTrue("Total Orders Page: Requester second name checkbox not displayed",reqSecond.isDisplayed());
        AssertJUnit.assertTrue("Total Orders Page: Business Principal checkbox not displayed",busPrinc.isDisplayed());
        AssertJUnit.assertTrue("Total Orders Page: Order No field not displayed",orderNo.isDisplayed());
        AssertJUnit.assertTrue("Total Orders Page: Plant name field not displayed",plantName.isDisplayed());
        AssertJUnit.assertTrue("Total Orders Page: Customer Code field not displayed",custCode.isDisplayed());
        AssertJUnit.assertTrue("Total Orders Page: Requester field not displayed",requester.isDisplayed());
        AssertJUnit.assertTrue("Total Orders Page: Order Type field not displayed",orderType.isDisplayed());
        AssertJUnit.assertTrue("Total Orders Page: Request Type field not displayed",requestType.isDisplayed());
        AssertJUnit.assertTrue("Total Orders Page: Brand field not displayed",brandFld.isDisplayed());
        AssertJUnit.assertTrue("Total Orders Page: Ticket field not displayed",ticketFld.isDisplayed());
        AssertJUnit.assertTrue("Total Orders Page: CCE Solution field not displayed",cceSolution.isDisplayed());
        AssertJUnit.assertTrue("Total Orders Page: Delivered From Date field not displayed",delFrom.isDisplayed());
        AssertJUnit.assertTrue("Total Orders Page: Delivered To Date field not displayed",delTo.isDisplayed());
        AssertJUnit.assertTrue("Total Orders Page: Country field not displayed",country.isDisplayed());
        AssertJUnit.assertTrue("Total Orders Page: Business Principal field not displayed",busPrincFld.isDisplayed());
        AssertJUnit.assertTrue("Total Orders Page: Ship To Party Name field not displayed",shipToName.isDisplayed());
        AssertJUnit.assertTrue("Total Orders Page: Hub field not displayed",hub.isDisplayed());
        AssertJUnit.assertTrue("Total Orders Page: MUM Type field not displayed",mumType.isDisplayed());
        AssertJUnit.assertTrue("Total Orders Page: Order Stage field not displayed",orderStage.isDisplayed());
        AssertJUnit.assertTrue("Total Orders Page: Created From Date field not displayed",createdFrom.isDisplayed());
        AssertJUnit.assertTrue("Total Orders Page: Created To Date field not displayed",createdTo.isDisplayed());
        AssertJUnit.assertTrue("Total Orders Page: Sales Organisation field not displayed",salesOrg.isDisplayed());
        AssertJUnit.assertTrue("Total Orders Page: Customer Name field not displayed",custNameFld.isDisplayed());
        AssertJUnit.assertTrue("Total Orders Page: Ship To Party Code field not displayed",shipToCode.isDisplayed());
        AssertJUnit.assertTrue("Total Orders Page: FCE Name field not displayed",fce.isDisplayed());
        AssertJUnit.assertTrue("Total Orders Page: Scenario field not displayed",scenario.isDisplayed());
        AssertJUnit.assertTrue("Total Orders Page: Article field not displayed",articleFld.isDisplayed());
        AssertJUnit.assertTrue("Total Orders Page: Order Outstanding Of field not displayed",outstOf.isDisplayed());
        AssertJUnit.assertTrue("Total Orders Page: Fabric Reference field not displayed",fabRef.isDisplayed());  
    }
    
}
