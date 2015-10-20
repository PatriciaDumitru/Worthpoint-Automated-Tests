
package PageObjects;

import AutomationFramework.CommonTask;
import org.testng.AssertJUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class Ecomm_MyReportsPage extends WBA_BasePage {
    
    //Locators
    By selectAllButton = By.id("Filter#");
    By createdDateButton = By.id("fields3");
    By poNumberButton = By.id("fields4");
    By articleButton = By.id("fields11");
    By brandButton = By.id("fields12");
    By ticketButton = By.id("fields13");
    By shadeCodeButton = By.id("fields14");
    By invoiceNoButton = By.id("fields26");
    By deliveryNoButton = By.id("fields33");
    By orderNoField = By.id("s2id_filterBulkOrderId");
    By custNameField = By.id("s2id_filterBulkOrderCustomerId");
    By createDateFromField = By.id("filterBulkOrderCreatedFrom");
    By createDateToField = By.id("filterBulkOrderCreatedTo");
    By custCodeField = By.id("filterCustomerCustomerCode");
    By saveMyReportButton = By.cssSelector("#FilterMyReportsForm > div.actions > ul > li:nth-child(1)");
    By printButton = By.cssSelector("#FilterMyReportsForm > div.actions > ul > li:nth-child(2)");
    By exportButton = By.cssSelector("#FilterMyReportsForm > div.actions > ul > li:nth-child(3)");
    By resetButton = By.cssSelector("#FilterMyReportsForm > div.actions > ul > li:nth-child(4)");
    By flashMessage = By.id("flashMessage");
    
    public Ecomm_MyReportsPage(WebDriver driver) {
        super(driver);
    }
    
    public WebElement getSelectAllButton() {
        return driver.findElement(selectAllButton);
    }
    
    public WebElement getCreatedDateButton() {
        return driver.findElement(createdDateButton);
    }
    
    public WebElement getPONumberButton() {
        return driver.findElement(poNumberButton);
    }
    
    public WebElement getArticleButton() {
        return driver.findElement(articleButton);
    }
    
    public WebElement getBrandButton() {
        return driver.findElement(brandButton);
    }
    
    public WebElement getTicketButton() {
        return driver.findElement(ticketButton);
    }
    
    public WebElement getShadeCodeButton() {
        return driver.findElement(shadeCodeButton);
    }
    
    public WebElement getInvoiceNoButton() {
        return driver.findElement(invoiceNoButton);
    }
    
    public WebElement getDeliveryNoButton() {
        return driver.findElement(deliveryNoButton);
    }
    
    public WebElement getOrderNoField() {
        return driver.findElement(orderNoField);
    }
    
    public WebElement getCustNameField() {
        return driver.findElement(orderNoField);
    }
    
    public WebElement getCreateFromField() {
        return driver.findElement(createDateFromField);
    }
    
    public WebElement getCreateToField() {
        return driver.findElement(createDateToField);
    }
    
    public WebElement getCustCodeField() {
        return driver.findElement(custCodeField);
    }
    
    public WebElement getSaveMyReportButton() {
        return driver.findElement(saveMyReportButton);
    }
    
    public WebElement getPrintButton() {
        return driver.findElement(printButton);
    }
    
    public WebElement getExportButton() {
        return driver.findElement(exportButton);
    }
    
    public WebElement getResetButton() {
        return driver.findElement(resetButton);
    }
    
    public WebElement getFlashMessage() {
        return driver.findElement(flashMessage);
    }
    
    public Ecomm_MyReportsPage setSelectAll() {
        CommonTask.setCheckBox(driver, selectAllButton);
        return this;
    }
    
    public Ecomm_MyReportsPage setCreatedDate() {
        CommonTask.setCheckBox(driver, createdDateButton);
        return this;
    }
    
    public Ecomm_MyReportsPage setPONumber() {
        CommonTask.setCheckBox(driver, poNumberButton);
        return this;
    }
    
    public Ecomm_MyReportsPage setArticle() {
        CommonTask.setCheckBox(driver, articleButton);
        return this;
    }
    
    public Ecomm_MyReportsPage setBrand() {
        CommonTask.setCheckBox(driver, brandButton);
        return this;
    }
    
    public Ecomm_MyReportsPage setTicket() {
        CommonTask.setCheckBox(driver, ticketButton);
        return this;
    }
    
    public Ecomm_MyReportsPage setShadeCode() {
        CommonTask.setCheckBox(driver, shadeCodeButton);
        return this;
    }
    
    public Ecomm_MyReportsPage setInvoiceNo() {
        CommonTask.setCheckBox(driver, invoiceNoButton);
        return this;
    }
    
    public Ecomm_MyReportsPage setDeliveryNo() {
        CommonTask.setCheckBox(driver, deliveryNoButton);
        return this;
    }
    
    public Ecomm_MyReportsPage setOrderNo(String item) {
        CommonTask.setSearchField(driver,orderNoField,item);
        return this;
    }
    
    public Ecomm_MyReportsPage setCustName(String item) {
        CommonTask.setSearchField(driver,custNameField,item);
        return this;
    }
    
    public Ecomm_MyReportsPage setCustCode(String item) {
        CommonTask.setTextField(driver,custCodeField,item);
        return this;
    }
    
    public Ecomm_OrderViewPage pressPrint() {
        WebElement waitForClickable = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(printButton));
        driver.findElement(printButton).click();
        return new Ecomm_OrderViewPage(driver);
    }
    
    public Ecomm_SaveReportPage pressSaveReport() {
        WebElement wait = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(saveMyReportButton));
        driver.findElement(saveMyReportButton).click();
        return new Ecomm_SaveReportPage(driver);
    }
 
    public Ecomm_ExportDownloadPage pressExport() {
        WebElement wait = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(exportButton));
        driver.findElement(exportButton).click();
        return new Ecomm_ExportDownloadPage(driver);
    }
    
    public Ecomm_MyReportsPage pressReset() {
        WebElement wait = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(resetButton));
        driver.findElement(resetButton).click();
        return this;
    }
    
    public void checkFields() {
        //Wait for all elements to be clickable 
        WebElement waitForOrderNo = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(orderNoField));
        WebElement waitForCustName = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(custNameField));
        WebElement waitForCustCode = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(custCodeField));
        WebElement waitForDateFrom = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(createDateFromField));
        WebElement waitForDateTo = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(createDateToField));
        WebElement waitForSelectAll = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(selectAllButton));
        WebElement waitForCreatedDate = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(createdDateButton));
        WebElement waitForPONumber = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(poNumberButton));
        WebElement waitForArticle = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(articleButton));
        WebElement waitForBrand = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(brandButton));
        WebElement waitForTicket = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(ticketButton));
        WebElement waitForshadecode = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(shadeCodeButton));
        WebElement waitForInvoiceNo = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(invoiceNoButton));
        WebElement waitForDelNo = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(deliveryNoButton));
    
        //Assert all elements are displayed
        AssertJUnit.assertTrue("My Reports Page: Order No Field not displayed",getOrderNoField().isDisplayed());
        AssertJUnit.assertTrue("My Reports Page: Customer Name Field not displayed",getCustNameField().isDisplayed());
        AssertJUnit.assertTrue("My Reports Page: Customer Code Field not displayed",getCustCodeField().isDisplayed());
        AssertJUnit.assertTrue("My Reports Page: Created Date From Field not displayed",getCreateFromField().isDisplayed());
        AssertJUnit.assertTrue("My Reports Page: Created Date To Field not displayed",getCreateToField().isDisplayed());
        AssertJUnit.assertTrue("My Reports Page: Select All Field not displayed",getSelectAllButton().isDisplayed());
        AssertJUnit.assertTrue("My Reports Page: Created Date Field not displayed",getCreatedDateButton().isDisplayed());
        AssertJUnit.assertTrue("My Reports Page: PO Number Field not displayed",getPONumberButton().isDisplayed());
        AssertJUnit.assertTrue("My Reports Page: Article Field not displayed",getArticleButton().isDisplayed());
        AssertJUnit.assertTrue("My Reports Page: Brand Field not displayed",getBrandButton().isDisplayed());
        AssertJUnit.assertTrue("My Reports Page: Ticket Field not displayed",getTicketButton().isDisplayed());
        AssertJUnit.assertTrue("My Reports Page: Shade Code Field not displayed",getShadeCodeButton().isDisplayed());
        AssertJUnit.assertTrue("My Reports Page: Invoice No Field not displayed",getInvoiceNoButton().isDisplayed());
        AssertJUnit.assertTrue("My Reports Page: Delivery No Field not displayed",getDeliveryNoButton().isDisplayed());
        AssertJUnit.assertTrue("My Reports Page: Print button not displayed",getPrintButton().isDisplayed());
        AssertJUnit.assertTrue("My Reports Page: Export button not displayed",getExportButton().isDisplayed());
        AssertJUnit.assertTrue("My Reports Page: Save My Report button not displayed",getSaveMyReportButton().isDisplayed());
        AssertJUnit.assertTrue("My Reports Page: Reset button not displayed",getResetButton().isDisplayed());
    }
    
}
