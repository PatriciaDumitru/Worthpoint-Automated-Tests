package PageObjects;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import AutomationFramework.CommonTask;

public class InvoicesPage_EComm extends BasePage {

	//Locators
	By custNameField = By.id("s2id_filterSapccInvoiceCustomerId");
	By custPOField = By.id("filterSapccInvoicePoNumber");
	By orderDateFromField = By.id("filterSapccInvoiceCreatedDateFrom");
	By orderDateToField = By.id("filterSapccInvoiceCreatedDateTo");
	By yourMatNumField = By.id("filterSapccInvoiceMaterial");
	By paymentDateFromField = By.id("filterSapccInvoicePaymentDateFrom");
	By paymentDateToField = By.id("filterSapccInvoicePaymentDateTo");
	By shipToPartyNameField = By.id("ship_to_party_id");
	By invoiceNoField = By.id("filterSapccInvoiceInvoiceNo");
	By sapOrderNoField = By.id("filterSapccInvoiceSapOrderNo");
	By statusField = By.id("filterSapccInvoiceStatus");
	By requesterField = By.id("filterRequesterId");
	By searchButton = By.cssSelector("#FilterInvoiceForm > div.grid_12 > table > tbody > tr:nth-child(7) > td > div > input");
	By resetButton = By.cssSelector("#FilterInvoiceForm > div.grid_12 > table > tbody > tr:nth-child(7) > td > a");
	By printButton = By.cssSelector("#content > div.tbl-toggle.cc_grid_outer > div > div.scrollTableContainer > table > tbody > tr:nth-child(3) > td:nth-child(1) > a");
	By viewButton = By.cssSelector("#content > div.tbl-toggle.cc_grid_outer > div > div.scrollTableContainer > table > tbody > tr:nth-child(3) > td:nth-child(2) > a");
	By exportButton = By.cssSelector("#content > div.tbl-toggle.cc_grid_outer > div > div.scrollTableContainer > div > a");
	
	public InvoicesPage_EComm(WebDriver passedDriver) {
		super(passedDriver);
	}
	
	public WebElement getCustNameField() {
		return driver.findElement(custNameField);
	}
	
	public WebElement getCustPOField() {
		return driver.findElement(custPOField);
	}
	
	public WebElement getOrderDateFromField() {
		return driver.findElement(orderDateFromField);
	}
	
	public WebElement getOrderDateToField() {
		return driver.findElement(orderDateToField);
	}
	
	public WebElement getYourMatNumField() {
		return driver.findElement(yourMatNumField);
	}
	
	public WebElement getPaymentDateFromField() {
		return driver.findElement(paymentDateFromField);
	}
	
	public WebElement getPaymentDateToField() {
		return driver.findElement(paymentDateToField);
	}
	
	public WebElement getShipToNameField() {
		return driver.findElement(shipToPartyNameField);
	}
	
	public WebElement getInvoiceField() {
		return driver.findElement(invoiceNoField);
	}
	
	public WebElement getSAPOrderNoField() {
		return driver.findElement(sapOrderNoField);
	}
	
	public WebElement getStatusField() {
		return driver.findElement(statusField);
	}
	
	public WebElement getRequesterField() {
		return driver.findElement(requesterField);
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
	
	public WebElement getPrintButton() {
		return driver.findElement(printButton);
	}
	
	public WebElement getExportButton() {
		return driver.findElement(exportButton);
	}

	public InvoicesPage_EComm setCustName(String item) {
		CommonTask.setSearchField(driver, custNameField, item);
		return this;
	}
	
	public InvoicesPage_EComm setCustPO(String item) {
		CommonTask.setTextField(driver, custPOField, item);
		return this;
	}
	
	public InvoicesPage_EComm setYourMatNum(String item) {
		CommonTask.setTextField(driver, yourMatNumField, item);
		return this;
	}
	
	public InvoicesPage_EComm setShipToName(String item) throws InterruptedException {
		CommonTask.setDropDownField(driver, shipToPartyNameField, item);
		return this;
	}
	
	public InvoicesPage_EComm setInvoiceNo(String item) {
		CommonTask.setTextField(driver, invoiceNoField, item);
		return this;
	}
	
	public InvoicesPage_EComm setSAPOrderNo(String item) {
		CommonTask.setTextField(driver, sapOrderNoField, item);
		return this;
	}
	
	public InvoicesPage_EComm setStatus(String item) throws InterruptedException {
		CommonTask.setDropDownField(driver, statusField, item);
		return this;
	}
	
	public InvoicesPage_EComm setRequester(String item) throws InterruptedException {
		CommonTask.setDropDownField(driver, requesterField, item);
		return this;
	}
	
	public InvoicesPage_EComm pressSearch() {
		WebElement waitForClickable = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(searchButton));
		driver.findElement(searchButton).click();
		return this;
	}
	
	public InvoicesPage_EComm pressReset() {
		WebElement waitForClickable = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(resetButton));
		driver.findElement(resetButton).click();
		return this;
	}
	
	public OrderViewPage pressView() {
		WebElement waitForClickable = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(viewButton));
		driver.findElement(viewButton).click();
		return new OrderViewPage(driver);
	}
	
	public OrderViewPage pressPrint() {
		WebElement waitForClickable = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(printButton));
		driver.findElement(printButton).click();
		return new OrderViewPage(driver);
	}
	
	public ExportDownloadPage pressExport() {
		WebElement waitForClickable = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(exportButton));
		driver.findElement(exportButton).click();
		return new ExportDownloadPage(driver);
	}
	
	public void checkFields() {
		//Wait for all elements to be clickable
        WebElement waitForCustName = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(custNameField));
        WebElement waitForCustPO = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(custPOField));
        WebElement waitForOrderFrom = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(orderDateFromField));
        WebElement waitForOrderTo = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(orderDateToField));
        WebElement waitForYMN = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(yourMatNumField));
        WebElement waitForPaymentFrom= new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(paymentDateFromField));
        WebElement waitForPaymentTo= new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(paymentDateToField));
        WebElement waitForShipToName= new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(shipToPartyNameField));
        WebElement waitForInvoice= new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(invoiceNoField));
        WebElement waitForSAPOrderNo= new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(sapOrderNoField));
        WebElement waitForStatus= new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(statusField));
        WebElement waitForRequester= new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(requesterField));
        WebElement waitForSearch= new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(searchButton));
        WebElement waitForReset= new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(resetButton));
        WebElement waitForPrint= new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(printButton));
        WebElement waitForView= new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(viewButton));
        
        //Assert all elements are displayed
        Assert.assertTrue("Invoices Page: Customer Name field not displayed correctly",getCustNameField().isDisplayed());
        Assert.assertTrue("Invoices Page: Customer PO field not displayed correctly",getCustPOField().isDisplayed());
        Assert.assertTrue("Invoices Page: Order Date From field not displayed correctly",getOrderDateFromField().isDisplayed());
        Assert.assertTrue("Invoices Page: Order Date To field not displayed correctly",getOrderDateToField().isDisplayed());
        Assert.assertTrue("Invoices Page: Your Material Number field not displayed correctly",getYourMatNumField().isDisplayed());
        Assert.assertTrue("Invoices Page: Payment Date From field not displayed correctly",getPaymentDateFromField().isDisplayed());
        Assert.assertTrue("Invoices Page: Payment Date To field not displayed correctly",getPaymentDateToField().isDisplayed());
        Assert.assertTrue("Invoices Page: Ship To Name field not displayed correctly",getShipToNameField().isDisplayed());
        Assert.assertTrue("Invoices Page: Invoice No Field not displayed correctly",getInvoiceField().isDisplayed());
        Assert.assertTrue("Invoices Page: SAP Order No Field not displayed correctly",getSAPOrderNoField().isDisplayed());
        Assert.assertTrue("Invoices Page: Status Field not displayed correctly",getStatusField().isDisplayed());
        Assert.assertTrue("Invoices Page: Requester Field not displayed correctly",getRequesterField().isDisplayed());
        Assert.assertTrue("Invoices Page: Search button not displayed correctly",getSearchButton().isDisplayed());
        Assert.assertTrue("Invoices Page: Reset Button not displayed correctly",getResetButton().isDisplayed());
        Assert.assertTrue("Invoices Page: View Button not displayed correctly",getViewButton().isDisplayed());
        Assert.assertTrue("Invoices Page: Print Button not displayed correctly",getPrintButton().isDisplayed());
        Assert.assertTrue("Invoices Page: Export Button not displayed correctly",getExportButton().isDisplayed());
	}

	
}
