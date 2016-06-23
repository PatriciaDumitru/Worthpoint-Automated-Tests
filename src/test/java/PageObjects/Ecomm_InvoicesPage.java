package PageObjects;

import org.testng.AssertJUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import AutomationFramework.CommonTask;
import AutomationFramework.DataItems;
import AutomationFramework.Wait;

public class Ecomm_InvoicesPage extends WBA_BasePage {

	//Locators
	By custNameField = By.xpath("//*[@id=\"s2id_filterSapCcinvoiceCustomerId\"]/a");//By.id("s2id_filterSapccInvoiceCustomerId");
	By custPOField = By.xpath("//*[@id=\"filterSapCcinvoicePoNumber\"]");//By.id("filterSapCcInvoicePoNumber");//#filterSapCcinvoicePoNumber
	By salesOrgNameField = By.xpath("//*[@id=\"FilterInvoiceForm\"]/div[2]/table/tbody/tr[2]/td[1]");//("//*[@id=\"s2id_filterSapCcinvoiceSalesOrgId\"]/ul");
	By orderDateFromField = By.xpath("//*[@id=\"filterSapCcinvoiceCreatedDateFrom\"]");//By.id("filterSapccInvoiceCreatedDateFrom");
	By orderDateToField = By.xpath("//*[@id=\"filterSapCcinvoiceCreatedDateTo\"]");//By.id("filterSapccInvoiceCreatedDateTo");
	By custCodeField = By.xpath("//*[@id=\"FilterInvoiceForm\"]/div[2]/table/tbody/tr[1]/td[2]");//("//*[@id=\"filterSapCcinvoiceCustomerCode\"]");
	By yourMatNumField = By.xpath("//*[@id=\"filterSapCcinvoiceMaterial\"]");//By.id("filterSapccInvoiceMaterial");
	By paymentDateFromField = By.xpath("//*[@id=\"filterSapCcinvoicePaymentDateFrom\"]");//By.id("filterSapccInvoicePaymentDateFrom");
	By paymentDateToField = By.xpath("//*[@id=\"filterSapCcinvoicePaymentDateTo\"]");//By.id("filterSapccInvoicePaymentDateTo");
	By shipToPartyNameField = By.xpath("//*[@id=\"ship_to_party_id\"]");//By.id("ship_to_party_id");
	By invoiceNoField = By.xpath("//*[@id=\"filterSapCcinvoiceInvoiceNo\"]");//By.id("filterSapccInvoiceInvoiceNo");
	By sapOrderNoField = By.xpath("//*[@id=\"filterSapCcinvoiceSapOrderNo\"]");//By.id("filterSapccInvoiceSapOrderNo");
	By statusField = By.xpath("//*[@id=\"filterSapCcinvoiceStatus\"]");//By.id("filterSapccInvoiceStatus");
	By requesterField = By.xpath("//*[@id=\"filterSapCcinvoiceRequesterId\"]");//By.id("filterRequesterId");
	By payerNameField = By.xpath("//*[@id=\"filterSapCcinvoicePayerName\"]");
	By busPrinNameField = By.xpath("//*[@id=\"filterSapCcinvoiceBusinessPrincipleName\"]");
	By freeTextField = By.xpath("//*[@id=\"filterSapCcinvoiceFreetext\"]");
	By searchButton = By.xpath("//*[@id=\"FilterInvoiceForm\"]/div[2]/table/tbody/tr[10]/td/div/input");//By.cssSelector("#FilterInvoiceForm > div.grid_12 > table > tbody > tr:nth-child(7) > td > div > input");
	By resetButton = By.xpath("//*[@id=\"FilterInvoiceForm\"]/div[2]/table/tbody/tr[10]/td/a");//By.cssSelector("#FilterInvoiceForm > div.grid_12 > table > tbody > tr:nth-child(10) > td > a");
	By printButton = By.xpath("//*[@id=\"content\"]/div[2]/div/div[2]/table/tbody/tr[3]/td[1]/a/img");//By.cssSelector("#content > div.tbl-toggle.cc_grid_outer > div > div.scrollTableContainer > table > tbody > tr:nth-child(3) > td:nth-child(1) > a");
	By viewButton = By.xpath("//*[@id=\"content\"]/div[2]/div/div[2]/table/tbody/tr[3]/td[2]/a/span");//By.cssSelector("#content > div.tbl-toggle.cc_grid_outer > div > div.scrollTableContainer > table > tbody > tr:nth-child(3) > td:nth-child(2) > a");
	By exportButton = By.xpath("//*[@id=\"content\"]/div[2]/div/div[2]/div/a");//By.cssSelector("#content > div.tbl-toggle.cc_grid_outer > div > div.scrollTableContainer > div > a");

	By tableHeader = By.xpath("//*[@id=\"content\"]/div[2]/div/div[2]/table/tbody/tr[1]/th");//*[@id="content"]/div[2]/div/div[2]/table/tbody/tr[1]/th[1]
	public By noRecords = By.cssSelector("#content > div.tbl-toggle.cc_grid_outer > div > div.scrollTableContainer > div > div");
        public String shipToPart1 = "#content > div.tbl-toggle.cc_grid_outer > div > div.scrollTableContainer > table > tbody > tr:nth-child(";
        public String shipToPart2 = ") > td:nth-child(10)";
        
	public Ecomm_InvoicesPage(WebDriver passedDriver) {
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

	public Ecomm_InvoicesPage setCustName(String item) {
		CommonTask.setSearchField(driver, custNameField, item);
		return this;
	}
	
	public Ecomm_InvoicesPage setCustPO(String item) {
		CommonTask.setTextField(driver, custPOField, item);
		return this;
	}
	
	public Ecomm_InvoicesPage setYourMatNum(String item) {
		CommonTask.setTextField(driver, yourMatNumField, item);
		return this;
	}
	
	public Ecomm_InvoicesPage setShipToName(String item) throws InterruptedException {
		CommonTask.setDropDownField(driver, shipToPartyNameField, item);
		return this;
	}
	
	public Ecomm_InvoicesPage setInvoiceNo(String item) {
		CommonTask.setTextField(driver, invoiceNoField, item);
		return this;
	}
	
	public Ecomm_InvoicesPage setSAPOrderNo(String item) {
		CommonTask.setTextField(driver, sapOrderNoField, item);
		return this;
	}
	
	public Ecomm_InvoicesPage setStatus(String item) throws InterruptedException {
		CommonTask.setDropDownField(driver, statusField, item);
		return this;
	}
	
	public Ecomm_InvoicesPage setRequester(String item) throws InterruptedException {
		CommonTask.setDropDownField(driver, requesterField, item);
		return this;
	}

    public String getClearButtonLabel() {
        WebElement button = Wait.clickable(driver,resetButton);
        String label = driver.findElement(resetButton).getText();
        return label;
    }

    public StringBuilder getTableHeader() {
        WebElement button = Wait.clickable(driver,resetButton);

        StringBuilder header = new StringBuilder();
		int columnCount = driver.findElements(tableHeader).size();
		System.out.println("No of columns:"+columnCount);
        for (int i=0;i<columnCount;i++){
            By tableHeader = By.cssSelector("#content > div.tbl-toggle.cc_grid_outer > div > div.scrollTableContainer > table > tbody > tr:nth-child(1) > th:nth-child("+(i+1)+")");
            //#content > div.tbl-toggle.cc_grid_outer > div > div.scrollTableContainer > table > tbody > tr:nth-child(1) > th:nth-child(1)
            header = header.append(driver.findElement(tableHeader).getText());
            header = header.append("|");
        }
        return header;
    }
	
	public Ecomm_InvoicesPage pressSearch() {
		WebElement search = Wait.clickable(driver,searchButton);
		search.click();
		return this;
	}
	
	public Ecomm_InvoicesPage pressReset() {
		WebElement reset = Wait.clickable(driver,resetButton);
		reset.click();
		return this;
	}
	
	public Ecomm_OrderViewPage pressView() {
		WebElement view = Wait.clickable(driver,viewButton);
		view.click();
		return new Ecomm_OrderViewPage(driver);
	}
	
	public Ecomm_OrderViewPage pressPrint() {
		WebElement print = Wait.clickable(driver,printButton);
		print.click();
		return new Ecomm_OrderViewPage(driver);
	}
	
	public Ecomm_ExportDownloadPage pressExport() {
		WebElement export = Wait.clickable(driver,exportButton);
		export.click();
		return new Ecomm_ExportDownloadPage(driver);
	}
	
	public void checkFields_SUMST() {
            //Wait for all elements to be clickable
            //WebElement custName = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(custNameField));
            WebElement custPO = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(custPOField));
            WebElement dateFrom = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(orderDateFromField));
            WebElement dateTo = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(orderDateToField));
            WebElement ymn = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(yourMatNumField));
            WebElement paymentFrom = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(paymentDateFromField));
            WebElement paymentTo = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(paymentDateToField));
            WebElement shipToParty = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(shipToPartyNameField));
            WebElement invoice = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(invoiceNoField));
            WebElement sapOrderNo= new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(sapOrderNoField));
            WebElement status = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(statusField));
            WebElement requester = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(requesterField));
            WebElement search = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(searchButton));
            WebElement reset = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(resetButton));
            WebElement print = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(printButton));
            WebElement view = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(viewButton));
            WebElement export = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(exportButton));

            //Assert all elements are displayed
            //AssertJUnit.assertTrue("Invoices Page: Customer Name field not displayed correctly",custName.isDisplayed());
            AssertJUnit.assertTrue("Invoices Page: Customer PO field not displayed correctly",custPO.isDisplayed());
            AssertJUnit.assertTrue("Invoices Page: Order Date From field not displayed correctly",dateFrom.isDisplayed());
            AssertJUnit.assertTrue("Invoices Page: Order Date To field not displayed correctly",dateTo.isDisplayed());
            AssertJUnit.assertTrue("Invoices Page: Your Material Number field not displayed correctly",ymn.isDisplayed());
            AssertJUnit.assertTrue("Invoices Page: Payment Date From field not displayed correctly",paymentFrom.isDisplayed());
            AssertJUnit.assertTrue("Invoices Page: Payment Date To field not displayed correctly",paymentTo.isDisplayed());
            AssertJUnit.assertTrue("Invoices Page: Ship To Name field not displayed correctly",shipToParty.isDisplayed());
            AssertJUnit.assertTrue("Invoices Page: Invoice No Field not displayed correctly",invoice.isDisplayed());
            AssertJUnit.assertTrue("Invoices Page: SAP Order No Field not displayed correctly",sapOrderNo.isDisplayed());
            AssertJUnit.assertTrue("Invoices Page: Status Field not displayed correctly",status.isDisplayed());
            AssertJUnit.assertTrue("Invoices Page: Requester Field not displayed correctly",requester.isDisplayed());
            AssertJUnit.assertTrue("Invoices Page: Search button not displayed correctly",search.isDisplayed());
            AssertJUnit.assertTrue("Invoices Page: Reset Button not displayed correctly",reset.isDisplayed());
            AssertJUnit.assertTrue("Invoices Page: View Button not displayed correctly",view.isDisplayed());
            AssertJUnit.assertTrue("Invoices Page: Print Button not displayed correctly",print.isDisplayed());
            AssertJUnit.assertTrue("Invoices Page: Export Button not displayed correctly",export.isDisplayed());
	}
        
        public void checkFields_SUSST() {
            //Wait for all elements to be clickable
            WebElement custPO = Wait.clickable(driver,custPOField);
            WebElement dateFrom = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(orderDateFromField));
            WebElement dateTo = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(orderDateToField));
            WebElement ymn = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(yourMatNumField));
			WebElement cCode = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(custCodeField));
			WebElement payer = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(payerNameField));
			WebElement bpn = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(busPrinNameField));
			WebElement sOrg = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(salesOrgNameField));
			WebElement free = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(freeTextField));
            WebElement paymentFrom = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(paymentDateFromField));
            WebElement paymentTo= new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(paymentDateToField));
            WebElement shipToName = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(shipToPartyNameField));
            WebElement invoice = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(invoiceNoField));
            WebElement sapOrderNo = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(sapOrderNoField));
            WebElement status = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(statusField));
            WebElement search= new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(searchButton));
            WebElement reset= new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(resetButton));
            WebElement print = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(printButton));
            WebElement view = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(viewButton));
            WebElement export = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(exportButton));

            //Assert all elements are displayed
            AssertJUnit.assertTrue("Invoices Page: Customer PO field not displayed correctly",custPO.isDisplayed());
            AssertJUnit.assertTrue("Invoices Page: Order Date From field not displayed correctly",dateFrom.isDisplayed());
            AssertJUnit.assertTrue("Invoices Page: Order Date To field not displayed correctly",dateTo.isDisplayed());
            AssertJUnit.assertTrue("Invoices Page: Your Material Number field not displayed correctly",ymn.isDisplayed());
            AssertJUnit.assertTrue("Invoices Page: Payment Date From field not displayed correctly",paymentFrom.isDisplayed());
            AssertJUnit.assertTrue("Invoices Page: Payment Date To field not displayed correctly",paymentTo.isDisplayed());
            AssertJUnit.assertTrue("Invoices Page: Ship To Name field not displayed correctly",shipToName.isDisplayed());
            AssertJUnit.assertTrue("Invoices Page: Invoice No Field not displayed correctly",invoice.isDisplayed());
			AssertJUnit.assertTrue("Invoices Page: Customer Code field not displayed correctly",cCode.isDisplayed());
			AssertJUnit.assertTrue("Invoices Page: Payer Name field not displayed correctly",payer.isDisplayed());
			AssertJUnit.assertTrue("Invoices Page: Business Principle Name field not displayed correctly",bpn.isDisplayed());
			AssertJUnit.assertTrue("Invoices Page: Sales Org Name field not displayed correctly",sOrg.isDisplayed());
			AssertJUnit.assertTrue("Invoices Page: StyleNo./Production No. Field not displayed correctly",free.isDisplayed());
            AssertJUnit.assertTrue("Invoices Page: SAP Order No Field not displayed correctly",sapOrderNo.isDisplayed());
            AssertJUnit.assertTrue("Invoices Page: Status Field not displayed correctly",status.isDisplayed());
            AssertJUnit.assertTrue("Invoices Page: Search button not displayed correctly",search.isDisplayed());
            AssertJUnit.assertTrue("Invoices Page: Reset Button not displayed correctly",reset.isDisplayed());
            AssertJUnit.assertTrue("Invoices Page: View Button not displayed correctly",view.isDisplayed());
            AssertJUnit.assertTrue("Invoices Page: Print Button not displayed correctly",print.isDisplayed());
            AssertJUnit.assertTrue("Invoices Page: Export Button not displayed correctly",export.isDisplayed());
        }
        
        public void waitForElement() {
            WebElement wait = Wait.clickable(driver,shipToPartyNameField);
        }
	
}
