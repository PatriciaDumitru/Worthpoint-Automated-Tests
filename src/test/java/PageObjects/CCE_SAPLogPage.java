
package PageObjects;

import AutomationFramework.CommonTask;
import AutomationFramework.DataItems;
import AutomationFramework.Wait;
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
        return Wait.visible(driver,breadcrumb);
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
        WebElement search = Wait.clickable(driver,searchButton);
        search.click();
        return this;
    }
    
    public CCE_SAPLogPage pressReset() {
        WebElement reset = Wait.clickable(driver,resetButton);
        reset.click();
        return this;
    }
    
    public CCE_OrderViewPage pressView() {
        WebElement view = Wait.clickable(driver,viewButton);
        view.click();
        return new CCE_OrderViewPage(driver);
    }
    
    public Ecomm_ExportDownloadPage pressExport() {
        WebElement export = Wait.clickable(driver,exportButton);
        export.click();
        return new Ecomm_ExportDownloadPage(driver);
    }
    
    public void checkFields() {
        //Wait for all elements to be clickable
        WebElement salesOrg = Wait.clickable(driver,salesOrgField);
        WebElement custCode = Wait.clickable(driver,custCodeField);
        WebElement article = Wait.clickable(driver,articleField);
        WebElement orderNo = Wait.clickable(driver,orderNoField);
        WebElement busPrinc = Wait.clickable(driver,busPrincField);
        WebElement orderFrom = Wait.clickable(driver,orderDateFromField);
        WebElement orderTo = Wait.clickable(driver,orderDateToField);
        WebElement custName = Wait.clickable(driver,custNameField);
        WebElement shadeCode = Wait.clickable(driver,shadeCodeField);
        WebElement mum = Wait.clickable(driver,mumTypeField);
        WebElement sapStatus = Wait.clickable(driver,sapStatusField);
        WebElement sapSO = Wait.clickable(driver,sapSOField);
        WebElement orderLine = Wait.clickable(driver,orderLineField);
        WebElement shipToCode = Wait.clickable(driver,shipToCodeField);
        WebElement search = Wait.clickable(driver,searchButton);
        WebElement reset = Wait.clickable(driver,resetButton);
        WebElement export = Wait.clickable(driver,exportButton);
    
        //Assert all elements are displayed
        AssertJUnit.assertTrue("SAP Log Page: Sales Organisation Field not displayed",salesOrg.isDisplayed());
        AssertJUnit.assertTrue("SAP Log Page: Customer Code Field not displayed",custCode.isDisplayed());
        AssertJUnit.assertTrue("SAP Log Page: Article Field not displayed",article.isDisplayed());
        AssertJUnit.assertTrue("SAP Log Page: Order No Field not displayed",orderNo.isDisplayed());
        AssertJUnit.assertTrue("SAP Log Page: Business Principal Field not displayed",busPrinc.isDisplayed());
        AssertJUnit.assertTrue("SAP Log Page: Order Date From Field not displayed",orderFrom.isDisplayed());
        AssertJUnit.assertTrue("SAP Log Page: Order Date To Field not displayed",orderTo.isDisplayed());
        AssertJUnit.assertTrue("SAP Log Page: Customer Name Field not displayed",custName.isDisplayed());
        AssertJUnit.assertTrue("SAP Log Page: Shade Code Field not displayed",shadeCode.isDisplayed());
        AssertJUnit.assertTrue("SAP Log Page: MUM Type Field not displayed",mum.isDisplayed());
        AssertJUnit.assertTrue("SAP Log Page: SAP Status Field not displayed",sapStatus.isDisplayed());
        AssertJUnit.assertTrue("SAP Log Page: SAP SO# Field not displayed",sapStatus.isDisplayed());
        AssertJUnit.assertTrue("SAP Log Page: Order Line Field not displayed",orderLine.isDisplayed());
        AssertJUnit.assertTrue("SAP Log Page: Ship To Party Code Field not displayed",shipToCode.isDisplayed());
        AssertJUnit.assertTrue("SAP Log Page: Search button not displayed",search.isDisplayed());
        AssertJUnit.assertTrue("SAP Log Page: Reset button not displayed",reset.isDisplayed());
        AssertJUnit.assertTrue("SAP Log Page: Export button not displayed",export.isDisplayed());
    }
    
    public void waitForElement() {
        WebElement wait = Wait.clickable(driver,searchButton);
    }
    
    public String getOrderStatus(int row) {
        By stageCell = By.cssSelector("#content > div.flexi-grid > table > tbody > tr:nth-child("+row+") > td:nth-child(16)");
        
        return driver.findElement(stageCell).getText();
    }
    
    public String findOrder(String orderNo) {
        for (int i = 2; i < 8; i++) {
            By orderNoCell = By.cssSelector("#content > div.flexi-grid > table > tbody > tr:nth-child("+i+") > td:nth-child(3)");
            WebElement cell = Wait.visible(driver,orderNoCell);
            String cellValue = cell.getText();
            if (cellValue.equals(orderNo)) {
                return getOrderStatus(i);
            }
        }
        return null;
    }
}
