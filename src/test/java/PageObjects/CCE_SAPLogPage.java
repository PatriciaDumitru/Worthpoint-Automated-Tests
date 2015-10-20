
package PageObjects;

import AutomationFramework.CommonTask;
import org.testng.AssertJUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CCE_SAPLogPage extends WBA_BasePage {
    
    //Locators
    By breadcrumb = By.cssSelector("#content > h2");
    By salesOrgField = By.cssSelector("#s2id_filterSampleOrderSalesOrgId > ul > li > input");
    By custCodeField = By.cssSelector("#s2id_filterSampleOrderCustomerIdCode > ul > li > input");
    By articleField = By.cssSelector("#s2id_filterSampleOrderLineArticleId > ul > li > input");
    By busPrincField = By.cssSelector("#s2id_filterSampleOrderBusinessPrincipalId > ul > li > input");
    By orderDateFromField = By.id("filterSapLogUpdatedFrom");
    By orderDateToField = By.id("filterSapLogUpdatedTo");
    By orderNoField = By.id("s2id_filterSapLogOrderId");
    By custNameField = By.id("s2id_filterSampleOrderCustomerIdName");
    By shadeCodeField = By.id("s2id_filterSampleOrderLineShadeId");
    By mumTypeField = By.id("s2id_filterSampleOrderLineMumTypeId");
    By sapStatusField = By.id("s2id_filterSampleOrderLineSapOrderStatusId");
    By sapSOField = By.id("filterSampleOrderLineSapSoNumber");
    By orderLineField = By.id("filterSapLogLineNo");
    By shipToCodeField = By.cssSelector("#s2id_filterSampleOrderShipToPartyId > ul > li > input");
    By searchButton = By.cssSelector("#FilterSapLogForm > div.actions > ul > li:nth-child(1)");
    By resetButton = By.cssSelector("#FilterSapLogForm > div.actions > ul > li:nth-child(2)");
    By viewButton = By.cssSelector("#content > div.flexi-grid > table > tbody > tr:nth-child(2) > td:nth-child(17)");
    By exportButton = By.cssSelector("#content > div.actions > ul > li");
    
    public CCE_SAPLogPage(WebDriver passedDriver) {
        super(passedDriver);
    }
    
    public WebElement getBreadcrumb() {
        WebElement waitForBreadcrumb = new WebDriverWait(driver,10).until(ExpectedConditions.visibilityOfElementLocated(breadcrumb));
        return driver.findElement(breadcrumb);
    }
    
    public WebElement getSalesOrgField() {
        return driver.findElement(salesOrgField);
    }
    
    public WebElement getCustCodeField() {
        return driver.findElement(custCodeField);
    }
    
    public WebElement getArticleField() {
        return driver.findElement(articleField);
    }
    
    public WebElement getBusPrincField() {
        return driver.findElement(busPrincField);
    }
    
    public WebElement getOrderDateFromField() {
        return driver.findElement(orderDateFromField);
    }
    
    public WebElement getOrderDateToField() {
        return driver.findElement(orderDateToField);
    }
    
    public WebElement getCustNameField() {
        return driver.findElement(custNameField);
    }
    
    public WebElement getShadeCodeField() {
        return driver.findElement(shadeCodeField);
    }
    
    public WebElement getMUMTypeField() {
        return driver.findElement(mumTypeField);
    }
    
    public WebElement getSapStatusField() {
        return driver.findElement(sapStatusField);
    }
    
    public WebElement getSapSOField() {
        return driver.findElement(sapSOField);
    }
    
    public WebElement getOrderNoField() {
        return driver.findElement(orderNoField);
    }
    
    public WebElement getOrderLineField() {
        return driver.findElement(orderLineField);
    }
    
    public WebElement getShipToCodeField() {
        return driver.findElement(shipToCodeField);
    }
    
    public WebElement getSearchButton() {
        return driver.findElement(searchButton);
    }
    
    public WebElement getResetButton() {
        return driver.findElement(resetButton);
    }
    
    public WebElement getViewButton() {
        return driver.findElement(viewButton);
    }
    
    public WebElement getExportButton() {
        return driver.findElement(exportButton);
    }
    
    public CCE_SAPLogPage setSalesOrg(String item) {
        CommonTask.setChoiceField(driver, salesOrgField, item);
        return this;
    }
    
    public CCE_SAPLogPage setArticle(String item) {
        CommonTask.setChoiceField(driver, articleField, item);
        return this;
    }
    
    public CCE_SAPLogPage setBusPrinc(String item) {
        CommonTask.setChoiceField(driver, busPrincField, item);
        return this;
    }
    
    public CCE_SAPLogPage setOrderNo(String item) {
        CommonTask.setSearchField(driver, orderNoField, item);
        return this;
    }
    
    public CCE_SAPLogPage setCustName(String item) {
        CommonTask.setSearchField(driver, custNameField, item);
        return this;
    }
    
    public CCE_SAPLogPage setShadeCode(String item) {
        CommonTask.setSearchField(driver, shadeCodeField, item);
        return this;
    }
    
    public CCE_SAPLogPage setMUMType(String item) {
        CommonTask.setSearchField(driver, mumTypeField, item);
        return this;
    }
    
    public CCE_SAPLogPage setSapStatus(String item) {
        CommonTask.setSearchField(driver, sapStatusField, item);
        return this;
    }
    
    public CCE_SAPLogPage pressSearch() {
        WebElement waitForClickable = new WebDriverWait(driver,5).until(ExpectedConditions.elementToBeClickable(searchButton));
        driver.findElement(searchButton).click();
        return this;
    }
    
    public CCE_SAPLogPage pressReset() {
        WebElement waitForClickable = new WebDriverWait(driver,5).until(ExpectedConditions.elementToBeClickable(resetButton));
        driver.findElement(resetButton).click();
        return this;
    }
    
    public CCE_OrderViewPage pressView() {
        WebElement waitForClickable = new WebDriverWait(driver,5).until(ExpectedConditions.elementToBeClickable(viewButton));
        driver.findElement(viewButton).click();
        return new CCE_OrderViewPage(driver);
    }
    
    public Ecomm_ExportDownloadPage pressExport() {
        WebElement waitForClickable = new WebDriverWait(driver,5).until(ExpectedConditions.elementToBeClickable(exportButton));
        driver.findElement(exportButton).click();
        return new Ecomm_ExportDownloadPage(driver);
    }
    
    public void checkFields() {
        //Wait for all elements to be clickable
        WebElement waitForSalesOrg = new WebDriverWait(driver,5).until(ExpectedConditions.elementToBeClickable(salesOrgField));
        WebElement waitForCustCode = new WebDriverWait(driver,5).until(ExpectedConditions.elementToBeClickable(custCodeField));
        WebElement waitForArticle = new WebDriverWait(driver,5).until(ExpectedConditions.elementToBeClickable(articleField));
        WebElement waitForOrderNo = new WebDriverWait(driver,5).until(ExpectedConditions.elementToBeClickable(orderNoField));
        WebElement waitForBusprinc = new WebDriverWait(driver,5).until(ExpectedConditions.elementToBeClickable(busPrincField));
        WebElement waitForOrderDateFrom = new WebDriverWait(driver,5).until(ExpectedConditions.elementToBeClickable(orderDateFromField));
        WebElement waitForOrderDateTo = new WebDriverWait(driver,5).until(ExpectedConditions.elementToBeClickable(orderDateToField));
        WebElement waitForCustName = new WebDriverWait(driver,5).until(ExpectedConditions.elementToBeClickable(custNameField));
        WebElement waitForShadeCode = new WebDriverWait(driver,5).until(ExpectedConditions.elementToBeClickable(shadeCodeField));
        WebElement waitForMUM = new WebDriverWait(driver,5).until(ExpectedConditions.elementToBeClickable(mumTypeField));
        WebElement waitForSapStatus = new WebDriverWait(driver,5).until(ExpectedConditions.elementToBeClickable(sapStatusField));
        WebElement waitForSapSO = new WebDriverWait(driver,5).until(ExpectedConditions.elementToBeClickable(sapSOField));
        WebElement waitForOrderLine = new WebDriverWait(driver,5).until(ExpectedConditions.elementToBeClickable(orderLineField));
        WebElement waitForShipToCode = new WebDriverWait(driver,5).until(ExpectedConditions.elementToBeClickable(shipToCodeField));
        WebElement waitForSearch = new WebDriverWait(driver,5).until(ExpectedConditions.elementToBeClickable(searchButton));
        WebElement waitForReset = new WebDriverWait(driver,5).until(ExpectedConditions.elementToBeClickable(resetButton));
        WebElement waitForExport = new WebDriverWait(driver,5).until(ExpectedConditions.elementToBeClickable(exportButton));
    
        //Assert all elements are displayed
        AssertJUnit.assertTrue("SAP Log Page: Sales Organisation Field not displayed",getSalesOrgField().isDisplayed());
        AssertJUnit.assertTrue("SAP Log Page: Customer Code Field not displayed",getCustCodeField().isDisplayed());
        AssertJUnit.assertTrue("SAP Log Page: Article Field not displayed",getArticleField().isDisplayed());
        AssertJUnit.assertTrue("SAP Log Page: Order No Field not displayed",getOrderNoField().isDisplayed());
        AssertJUnit.assertTrue("SAP Log Page: Business Principal Field not displayed",getBusPrincField().isDisplayed());
        AssertJUnit.assertTrue("SAP Log Page: Order Date From Field not displayed",getOrderDateFromField().isDisplayed());
        AssertJUnit.assertTrue("SAP Log Page: Order Date To Field not displayed",getOrderDateToField().isDisplayed());
        AssertJUnit.assertTrue("SAP Log Page: Customer Name Field not displayed",getCustNameField().isDisplayed());
        AssertJUnit.assertTrue("SAP Log Page: Shade Code Field not displayed",getShadeCodeField().isDisplayed());
        AssertJUnit.assertTrue("SAP Log Page: MUM Type Field not displayed",getMUMTypeField().isDisplayed());
        AssertJUnit.assertTrue("SAP Log Page: SAP Status Field not displayed",getSapStatusField().isDisplayed());
        AssertJUnit.assertTrue("SAP Log Page: SAP SO# Field not displayed",getSapSOField().isDisplayed());
        AssertJUnit.assertTrue("SAP Log Page: Order Line Field not displayed",getOrderLineField().isDisplayed());
        AssertJUnit.assertTrue("SAP Log Page: Ship To Party Code Field not displayed",getShipToCodeField().isDisplayed());
        AssertJUnit.assertTrue("SAP Log Page: Search button not displayed",getSearchButton().isDisplayed());
        AssertJUnit.assertTrue("SAP Log Page: Reset button not displayed",getResetButton().isDisplayed());
        AssertJUnit.assertTrue("SAP Log Page: Export button not displayed",getExportButton().isDisplayed());
    }
}
