package PageObjects;

import AutomationFramework.CommonTask;
import AutomationFramework.Wait;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.AssertJUnit;

/**
 * Created by Daniel Ion on 31.03.2016.
 */
//This page appears after the mapping alert has been submitted, and allows the user to map the uploaded file
public class CCE_MappingPage extends WBA_BasePage {

    //Locators
    By shipToPartyNameFieldLocator = By.id("SampleOrderShipToPartyId");
    By requesterNameFieldLocator = By.id("SampleOrderRequesterId");
    By businessPrincipalFieldLocator = By.id("SampleOrderBusinessPrincipalId");
    By firstLightSourceFieldLocator = By.id("SampleOrderLsPrimaryId");
    By secondLightSourceFieldLocator = By.id("SampleOrderLsSecondaryId");
    By thirdLightSourceFieldLocator = By.id("SampleOrderLsThirdId");
    By articleeeFieldLocator = By.id("SampleOrderLineArticleId");
    By brandFieldLocator = By.id("SampleOrderLineBrandId");
    By ticketFieldLocator = By.id("SampleOrderLineTicketId");
    By shadeCodeFieldLocator = By.id("SampleOrderLineShadeId");
    By MUMTypeFieldLocator = By.id("SampleOrderLineMumTypeId");
    By qtyFieldLocator = By.id("SampleOrderLineOrderedQuantity");
    By requestTypeFieldLocator = By.id("SampleOrderLineRequestTypeId");
    By purposeTypeFieldLocator = By.id("SampleOrderLinePurposeTypeId");
    By requirementsFieldLocator = By.id("SampleOrderLineRequirements");
    By customerReferenceFieldLocator = By.id("SampleOrderLineCustomerReference");
    By fabricReferenceNameFieldLocator = By.id("SampleOrderLineFabricReferenceColourName");
    By directEnrichFieldLocator = By.id("SampleOrderLineIsDirectEnrich");
    By startingShadeFieldLocator = By.id("SampleOrderLineStartingShadeId");
    By FCECommentsFieldLocator = By.id("SampleOrderLineFceComments");
    By sourceOfSupplyFieldLocator = By.id("SampleOrderLineSosId");

    By shipToPartyLabelLocator = By.cssSelector("#mapping_grid > table > tbody > tr:nth-child(4) > td:nth-child(1) > label");
    By requesterNameLabelLocator = By.cssSelector("#mapping_grid > table > tbody > tr:nth-child(3) > td:nth-child(1) > label");
    By businessPrincipalLabelLocator = By.cssSelector("#mapping_grid > table > tbody > tr:nth-child(5) > td:nth-child(1) > label");
    By firstLightSourceLabelLocator = By.cssSelector("#mapping_grid > table > tbody > tr:nth-child(6) > td:nth-child(1) > label");
    By secondLightSourceLabelLocator = By.cssSelector("#mapping_grid > table > tbody > tr:nth-child(7) > td:nth-child(1) > label");
    By thirdLightSourceLabelLocator = By.cssSelector("#mapping_grid > table > tbody > tr:nth-child(8) > td:nth-child(1) > label");
    By articleeeLabelLocator = By.cssSelector("#mapping_grid > table > tbody > tr:nth-child(1) > td:nth-child(3) > label");
    By brandLabelLocator = By.cssSelector("#mapping_grid > table > tbody > tr:nth-child(2) > td:nth-child(3) > label");
    By ticketLabelLocator = By.cssSelector("#mapping_grid > table > tbody > tr:nth-child(3) > td:nth-child(3) > label");
    By shadeCodeLabelLocator = By.cssSelector("#mapping_grid > table > tbody > tr:nth-child(4) > td:nth-child(3) > label");
    By MUMTypeLabelLocator = By.cssSelector("#mapping_grid > table > tbody > tr:nth-child(5) > td:nth-child(3) > label");
    By qtyFieldLabelLocator = By.cssSelector("#mapping_grid > table > tbody > tr:nth-child(10) > td:nth-child(1) > label");
    By requestTypeLabelLocator = By.cssSelector("#mapping_grid > table > tbody > tr:nth-child(6) > td:nth-child(3) > label");
    By purposeTypeLabelLocator = By.cssSelector("#mapping_grid > table > tbody > tr:nth-child(7) > td:nth-child(3) > label");
    By requirementsLabelLocator = By.cssSelector("#mapping_grid > table > tbody > tr:nth-child(8) > td:nth-child(3) > label");
    By customerReferenceLabelLocator = By.cssSelector("#mapping_grid > table > tbody > tr:nth-child(9) > td:nth-child(1) > label");
    By fabricReferenceNameLabelLocator = By.cssSelector("#mapping_grid > table > tbody > tr:nth-child(9) > td:nth-child(3) > label");
    By directEnrichLabelLocator = By.cssSelector("#mapping_grid > table > tbody > tr:nth-child(10) > td:nth-child(3) > label");
    By startingShadeLabelLocator = By.cssSelector("#mapping_grid > table > tbody > tr:nth-child(11) > td:nth-child(1) > label");
    By FCECommentsLabelLocator = By.cssSelector("#mapping_grid > table > tbody > tr:nth-child(11) > td:nth-child(3) > label");
    By sourceOfSupplyLabelLocator = By.cssSelector("#mapping_grid > table > tbody > tr:nth-child(12) > td:nth-child(1) > label");

    By confirmButtonLocator = By.id("trigger");
    By frameLocator = By.id("file_mapping");

    By salesOrgFieldLocator = By.id("SampleOrderSalesOrgId");
    By custNameFieldLocator = By.cssSelector("#s2id_SampleOrderCustomerId > a > span.select2-chosen");


    public CCE_MappingPage(WebDriver passedDriver) {
        super(passedDriver);
    }

    public WebElement getReqNameLabel() {
        return driver.findElement(requesterNameLabelLocator);
    }

    public WebElement getReqNameField() {
        return driver.findElement(requesterNameFieldLocator);
    }

    public WebElement getBusinessPrincipaLabel() {
        return driver.findElement(businessPrincipalLabelLocator);
    }

    public WebElement getBusinessPrincipaField() {
        return driver.findElement(businessPrincipalFieldLocator);
    }

    public WebElement getTicketLabel() {
        return driver.findElement(ticketLabelLocator);
    }

    public WebElement getTicketField() {
        return driver.findElement(ticketFieldLocator);
    }

    public WebElement getShadeCodeLabel() {
        return driver.findElement(shadeCodeLabelLocator);
    }

    public WebElement getShadeCodeField() {
        return driver.findElement(shadeCodeFieldLocator);
    }

    public WebElement getFirstLightSourceLabel() {
        return driver.findElement(firstLightSourceLabelLocator);
    }

    public WebElement getFirstLightSourceField() {
        return driver.findElement(firstLightSourceFieldLocator);
    }

    public WebElement getShipToPartyLabel() {
        return driver.findElement(shipToPartyLabelLocator);
    }

    public WebElement getShipToPartyField() {
        return driver.findElement(shipToPartyNameFieldLocator);
    }

    public WebElement getBrandLabel() {
        return driver.findElement(brandLabelLocator);
    }

    public WebElement getBrandField() {
        return driver.findElement(brandFieldLocator);
    }


    public WebElement confirmButtonLocator() {
        return driver.findElement(confirmButtonLocator);
    }

    public CCE_UploadOrderSamplesPage pressConfirm() {
        //wait for button to be clickable
        WebElement confirm = Wait.clickable(driver, confirmButtonLocator);
        //New action to click confirm
        Actions clickConfirm = new Actions(driver);
        clickConfirm.click(confirm).build().perform();
        //Confirm alert
        Alert alert = Wait.alert(driver);
        alert.accept();

        return new CCE_UploadOrderSamplesPage(driver);
    }


    public CCE_MappingPage setMappingNewUOF(String[][] mapping) {
        WebElement confirmBtn = Wait.clickable(driver, confirmButtonLocator);

        WebElement shipToParty = Wait.clickable(driver, shipToPartyNameFieldLocator);
        AssertJUnit.assertTrue("Mapping Page: Ship to party field not in expected position", driver.findElement(shipToPartyLabelLocator).getText().equals(mapping[0][0]));
        Select shipToPartySelect = new Select(shipToParty);
        shipToParty.click();
        shipToPartySelect.selectByVisibleText(mapping[0][1]);

        WebElement requesterName = Wait.clickable(driver, requesterNameFieldLocator);
        AssertJUnit.assertTrue("Mapping Page: Requester Name field not in expected position", driver.findElement(requesterNameLabelLocator).getText().equals(mapping[1][0]));
        Select requesterNameSelect = new Select(requesterName);
        requesterName.click();
        requesterNameSelect.selectByVisibleText(mapping[1][1]);

        WebElement businessPrincipal = Wait.clickable(driver, businessPrincipalFieldLocator);
        //System.out.println(driver.findElement(businessPrincipalLabelLocator).getText().equals(mapping[2][0]));
        //AssertJUnit.assertTrue("Mapping Page: Business Principal field not in expected position", driver.findElement(businessPrincipalLabelLocator).getText().equals(mapping[2][0]));
        Select businessPrincipalSelect = new Select(businessPrincipal);
        businessPrincipal.click();
        businessPrincipalSelect.selectByVisibleText(mapping[2][1]);

        WebElement firstLightSource = Wait.clickable(driver, firstLightSourceFieldLocator);
        AssertJUnit.assertTrue("Mapping Page: 1st Light Source field not in expected position", driver.findElement(firstLightSourceLabelLocator).getText().equals(mapping[3][0]));
        Select firstLightSourceSelect = new Select(firstLightSource);
        firstLightSource.click();
        firstLightSourceSelect.selectByVisibleText(mapping[3][1]);

        WebElement secondLightSource = Wait.clickable(driver, secondLightSourceFieldLocator);
        AssertJUnit.assertTrue("Mapping Page: 2nd Light Source field not in expected position", driver.findElement(secondLightSourceLabelLocator).getText().equals(mapping[4][0]));
        Select secondLightSourceSelect = new Select(secondLightSource);
        secondLightSource.click();
        secondLightSourceSelect.selectByVisibleText(mapping[4][1]);

        WebElement thirdLightSource = Wait.clickable(driver, thirdLightSourceFieldLocator);
        AssertJUnit.assertTrue("Mapping Page: 3rd Light Source field not in expected position", driver.findElement(thirdLightSourceLabelLocator).getText().equals(mapping[5][0]));
        Select thirdLightSourceSelect = new Select(thirdLightSource);
        thirdLightSource.click();
        thirdLightSourceSelect.selectByVisibleText(mapping[5][1]);

        WebElement articleee = Wait.clickable(driver, articleeeFieldLocator);
        //AssertJUnit.assertTrue("Mapping Page: Article field not in expected position", driver.findElement(articleeeLabelLocator).getText().equals(mapping[6][0]));
        Select articleeeSelect = new Select(articleee);
        articleee.click();
        articleeeSelect.selectByVisibleText(mapping[6][1]);

        WebElement brand = Wait.clickable(driver, brandFieldLocator);
        AssertJUnit.assertTrue("Mapping Page: Brand field not in expected position", driver.findElement(brandLabelLocator).getText().equals(mapping[7][0]));
        Select brandNoSelect = new Select(brand);
        brand.click();
        brandNoSelect.selectByVisibleText(mapping[7][1]);

        WebElement ticket = Wait.clickable(driver, ticketFieldLocator);
        AssertJUnit.assertTrue("Mapping Page: Ticket field not in expected position", driver.findElement(ticketLabelLocator).getText().equals(mapping[8][0]));
        Select ticketPOSelect = new Select(ticket);
        ticket.click();
        ticketPOSelect.selectByVisibleText(mapping[8][1]);

        WebElement shadeCode = Wait.clickable(driver, shadeCodeFieldLocator);
        AssertJUnit.assertTrue("Mapping Page: Shade Code field not in expected position", driver.findElement(shadeCodeLabelLocator).getText().equals(mapping[9][0]));
        Select shadeCodeSelect = new Select(shadeCode);
        shadeCode.click();
        shadeCodeSelect.selectByVisibleText(mapping[9][1]);

        WebElement MUMType = Wait.clickable(driver, MUMTypeFieldLocator);
        AssertJUnit.assertTrue("Mapping Page: MUM Type field not in expected position", driver.findElement(MUMTypeLabelLocator).getText().equals(mapping[10][0]));
        Select MUMTypeSelect = new Select(MUMType);
        MUMType.click();
        MUMTypeSelect.selectByVisibleText(mapping[10][1]);

        WebElement qty = Wait.clickable(driver, qtyFieldLocator);
        //AssertJUnit.assertTrue("Mapping Page: Quantity field not in expected position", driver.findElement(qtyFieldLabelLocator).getText().equals(mapping[11][0]));
        Select qtySelect = new Select(qty);
        qty.click();
        qtySelect.selectByVisibleText(mapping[11][1]);

        WebElement requestType = Wait.clickable(driver, requestTypeFieldLocator);
        //AssertJUnit.assertTrue("Mapping Page: Request Type field not in expected position", driver.findElement(requestTypeLabelLocator).getText().equals(mapping[12][0]));
        Select requestTypeSelect = new Select(requestType);
        requestType.click();
        requestTypeSelect.selectByVisibleText(mapping[12][1]);

        WebElement purposeType = Wait.clickable(driver, purposeTypeFieldLocator);
        //AssertJUnit.assertTrue("Mapping Page: Purpose Type field not in expected position", driver.findElement(purposeTypeLabelLocator).getText().equals(mapping[13][0]));
        Select purposeTypeSelect = new Select(purposeType);
        purposeType.click();
        purposeTypeSelect.selectByVisibleText(mapping[13][1]);

        WebElement requirements = Wait.clickable(driver, requirementsFieldLocator);
        AssertJUnit.assertTrue("Mapping Page: Requirements field not in expected position", driver.findElement(requirementsLabelLocator).getText().equals(mapping[14][0]));
        Select requirementsSelect = new Select(requirements);
        requirements.click();
        requirementsSelect.selectByVisibleText(mapping[14][1]);

        WebElement customerReference = Wait.clickable(driver, customerReferenceFieldLocator);
        AssertJUnit.assertTrue("Mapping Page: Customer Reference field not in expected position", driver.findElement(customerReferenceLabelLocator).getText().equals(mapping[15][0]));
        Select customerReferenceSelect = new Select(customerReference);
        customerReference.click();
        customerReferenceSelect.selectByVisibleText(mapping[15][1]);

        WebElement fabricReferenceName = Wait.clickable(driver, fabricReferenceNameFieldLocator);
        AssertJUnit.assertTrue("Mapping Page: Fabric Reference Name field not in expected position", driver.findElement(fabricReferenceNameLabelLocator).getText().equals(mapping[16][0]));
        Select fabricReferenceNameSelect = new Select(fabricReferenceName);
        fabricReferenceName.click();
        fabricReferenceNameSelect.selectByVisibleText(mapping[16][1]);

        WebElement directEnrich = Wait.clickable(driver, directEnrichFieldLocator);
        AssertJUnit.assertTrue("Mapping Page: Direct Enrich field not in expected position", driver.findElement(directEnrichLabelLocator).getText().equals(mapping[17][0]));
        Select directEnrichSelect = new Select(directEnrich);
        directEnrich.click();
        directEnrichSelect.selectByVisibleText(mapping[17][1]);

        WebElement startingShade = Wait.clickable(driver, startingShadeFieldLocator);
        AssertJUnit.assertTrue("Mapping Page: Starting Shade field not in expected position", driver.findElement(startingShadeLabelLocator).getText().equals(mapping[18][0]));
        Select startingShadeSelect = new Select(startingShade);
        startingShade.click();
        startingShadeSelect.selectByVisibleText(mapping[18][1]);

        WebElement FCEComments = Wait.clickable(driver, FCECommentsFieldLocator);
        AssertJUnit.assertTrue("Mapping Page: FCE Comments field not in expected position", driver.findElement(FCECommentsLabelLocator).getText().equals(mapping[19][0]));
        Select FCECommentsSelect = new Select(FCEComments);
        FCEComments.click();
        FCECommentsSelect.selectByVisibleText(mapping[19][1]);

        WebElement sourceOfSupply = Wait.clickable(driver, sourceOfSupplyFieldLocator);
        AssertJUnit.assertTrue("Mapping Page: Other Information field not in expected position", driver.findElement(sourceOfSupplyLabelLocator).getText().equals(mapping[20][0]));
        Select sourceOfSupplySelect = new Select(sourceOfSupply);
        sourceOfSupply.click();
        sourceOfSupplySelect.selectByVisibleText(mapping[20][1]);

/*        WebElement lineRef = Wait.clickable(driver, customerLineReferenceLocator);
        //AssertJUnit.assertTrue("Mapping Page: Line Reference field not in expected position",driver.findElement(customerLineReferenceLocator).getText().equals(mapping[21][0]));
        Select lineRefSelect = new Select(lineRef);
        lineRef.click();
        lineRefSelect.selectByVisibleText(mapping[21][1]);*/

        return new CCE_MappingPage(driver);
    }


    public void waitForElement() {
        WebElement wait = Wait.visible(driver, frameLocator);
    }


    public CCE_MappingPage setSalesOrg(String item) throws InterruptedException {
        CommonTask.setDropDownField(driver, salesOrgFieldLocator, item);
        return this;
    }

    public CCE_MappingPage setCustomerName(String item) {
        CommonTask.setSearchField(driver, custNameFieldLocator, item);
        return this;
    }

}
