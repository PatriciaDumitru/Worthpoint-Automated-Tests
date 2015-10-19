package PageObjects;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import AutomationFramework.CommonTask;

public class Ecomm_SAPInterfaceLogPage extends WBA_BasePage {

	//Locators
	By custPOField = By.id("filterBulkOrderPoNumber");
	By createDateFromField = By.id("filterBulkOrderCreatedFrom");
	By yourMatNumField = By.id("filterBulkOrderLineCustomerMaterialNo");
	By ticketField = By.id("s2id_filterBulkOrderLineTicketId");
	By finishField = By.id("s2id_filterBulkOrderLineFinishId");
	By sapMsgField = By.id("s2id_filterSapLogResponseCode");
	By orderNoField = By.id("s2id_filterBulkOrderId");
	By brandField = By.id("s2id_filterBulkOrderLineBrandId");
	By articleField = By.id("s2id_filterBulkOrderLineArticleId");
	By lengthField = By.id("s2id_filterBulkOrderLineLengthId");
	By shadeCodeField = By.id("s2id_filterBulkOrderLineShadeId");
	By salesOrgField = By.id("s2id_filterBulkOrderSalesOrgId");
	By searchButton = By.cssSelector("#FilterSapLogForm > div.grid_12 > table > tbody > tr:nth-child(7) > td > div > input");
	By resetButton = By.cssSelector("#FilterSapLogForm > div.grid_12 > table > tbody > tr:nth-child(7) > td > a");
	By viewButton = By.cssSelector("#content > div.tbl-toggle > div > div.scrollTableContainer.scroll-pane > table > tbody:nth-child(2) > tr:nth-child(1) > td:nth-child(1) > a");
	By ftViewButton = By.cssSelector("#content > div.tbl-toggle > div > div.scrollTableContainer.scroll-pane > table > tbody:nth-child(2) > tr:nth-child(1) > td:nth-child(10) > a");
	
	
	public Ecomm_SAPInterfaceLogPage(WebDriver passedDriver) {
		super(passedDriver);
	}
	
	public WebElement getCustPOField() {
		//Find and return element
		return driver.findElement(custPOField);
	}
	
	public WebElement getCreateDateFromField() {
		//Find and return element
		return driver.findElement(createDateFromField);
	}
	
	public WebElement getYourMatNumField() {
		//Find and return element
		return driver.findElement(yourMatNumField);
	}
	
	public WebElement getTicketField() {
		//Find and return element
		return driver.findElement(ticketField);
	}
	
	public WebElement getFinishField() {
		//Find and return element
		return driver.findElement(finishField);
	}
	
	public WebElement getSAPMessageField() {
		//Find and return element
		return driver.findElement(sapMsgField);
	}
	
	public WebElement getOrderNoField() {
		//Find and return element
		return driver.findElement(orderNoField);
	}
	
	public WebElement getBrandField() {
		//Find and return element
		return driver.findElement(brandField);
	}
	
	public WebElement getArticleField() {
		//Find and return element
		return driver.findElement(articleField);
	}

	public WebElement getLengthField() {
		//Find and return element
		return driver.findElement(lengthField);
	}
	
	public WebElement getShadeCodeField() {
		//Find and return element
		return driver.findElement(shadeCodeField);
	}
	
	public WebElement getSalesOrgField() {
		//Find and return element
		return driver.findElement(salesOrgField);
	}
	
	public WebElement getSearchButton() {
		//Find and return element
		return driver.findElement(searchButton);
	}
	
	public WebElement getResetButton() {
		//Find and return element
		return driver.findElement(resetButton);
	}
	
	public WebElement getViewButton() {
		//Find and return element
		return driver.findElement(viewButton);
	}
	
	public WebElement getFTViewButton() {
		//Find and return element
		return driver.findElement(ftViewButton);
	}
	
	public Ecomm_SAPInterfaceLogPage setCustPO(String item) {
		CommonTask.setTextField(driver, custPOField, item);
		
		return this;
	}
	
	public Ecomm_SAPInterfaceLogPage setYourMatNum(String item) {
		CommonTask.setTextField(driver, yourMatNumField, item);
		
		return this;
	}
	
	public Ecomm_SAPInterfaceLogPage setTicket(String item) {
		CommonTask.setSearchField(driver, ticketField, item);
		
		return this;
	}
	
	public Ecomm_SAPInterfaceLogPage setFinish(String item) {
		CommonTask.setSearchField(driver, finishField, item);
		
		return this;
	}
	
	public Ecomm_SAPInterfaceLogPage setSAPMessage(String item) {
		CommonTask.setSearchField(driver, sapMsgField, item);
		
		return this;
	}
	
	public Ecomm_SAPInterfaceLogPage setOrderNo(String item) {
		CommonTask.setSearchField(driver, orderNoField, item);
		
		return this;
	}
	
	public Ecomm_SAPInterfaceLogPage setBrand(String item) {
		CommonTask.setSearchField(driver, brandField, item);
		
		return this;
	}
	
	public Ecomm_SAPInterfaceLogPage setArticle(String item) {
		CommonTask.setTextField(driver, articleField, item);
		
		return this;
	}
	
	public Ecomm_SAPInterfaceLogPage setLength(String item) {
		CommonTask.setTextField(driver, lengthField, item);
		
		return this;
	}
	
	public Ecomm_SAPInterfaceLogPage setShadeCode(String item) {
		CommonTask.setTextField(driver, shadeCodeField, item);
		
		return this;
	}

	public Ecomm_SAPInterfaceLogPage setSalesOrg(String item) {
		CommonTask.setTextField(driver, salesOrgField, item);
		
		return this;
	}

	public Ecomm_SAPInterfaceLogPage pressSearch() {
		WebElement waitForClickable = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(searchButton));
		driver.findElement(searchButton).click();
		
		this.waitForLoad();
		
		return new Ecomm_SAPInterfaceLogPage(driver);
	}
	
	public Ecomm_SAPInterfaceLogPage pressReset() {
		WebElement waitForClickable = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(resetButton));
		driver.findElement(resetButton).click();
		
		this.waitForLoad();
		
		return new Ecomm_SAPInterfaceLogPage(driver);
	}

	public Ecomm_OrderViewPage pressView() {
		WebElement waitForClickable = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(viewButton));
		driver.findElement(viewButton).click();
		
		return new Ecomm_OrderViewPage(driver);
	}

	public Ecomm_OrderViewPage pressFtView() {
		WebElement waitForClickable = new WebDriverWait(driver,5).until(ExpectedConditions.elementToBeClickable(ftViewButton));
		driver.findElement(ftViewButton).click();
		
		return new Ecomm_OrderViewPage(driver);
	}

	public void checkFields() {
		//Wait for all fields to be clickable
		WebElement waitForCustPO = new WebDriverWait(driver,5).until(ExpectedConditions.elementToBeClickable(custPOField));
		WebElement waitForCreationFrom = new WebDriverWait(driver,5).until(ExpectedConditions.elementToBeClickable(createDateFromField));
		WebElement waitForYMN = new WebDriverWait(driver,5).until(ExpectedConditions.elementToBeClickable(yourMatNumField));
		WebElement waitForTicket = new WebDriverWait(driver,5).until(ExpectedConditions.elementToBeClickable(ticketField));
		WebElement waitForFinish = new WebDriverWait(driver,5).until(ExpectedConditions.elementToBeClickable(finishField));
		WebElement waitForSAPMsg = new WebDriverWait(driver,5).until(ExpectedConditions.elementToBeClickable(sapMsgField));
		WebElement waitForOrderNo = new WebDriverWait(driver,5).until(ExpectedConditions.elementToBeClickable(orderNoField));
		WebElement waitForSalesOrg = new WebDriverWait(driver,5).until(ExpectedConditions.elementToBeClickable(salesOrgField));
		WebElement waitForBrand = new WebDriverWait(driver,5).until(ExpectedConditions.elementToBeClickable(brandField));
		WebElement waitForArticle = new WebDriverWait(driver,5).until(ExpectedConditions.elementToBeClickable(articleField));
		WebElement waitForLength = new WebDriverWait(driver,5).until(ExpectedConditions.elementToBeClickable(lengthField));
		WebElement waitForShadeCode = new WebDriverWait(driver,5).until(ExpectedConditions.elementToBeClickable(shadeCodeField));
		WebElement waitForSearch = new WebDriverWait(driver,5).until(ExpectedConditions.elementToBeClickable(searchButton));
		WebElement waitForReset = new WebDriverWait(driver,5).until(ExpectedConditions.elementToBeClickable(resetButton));
		WebElement waitForView = new WebDriverWait(driver,5).until(ExpectedConditions.elementToBeClickable(viewButton));
		WebElement waitForFtView = new WebDriverWait(driver,5).until(ExpectedConditions.elementToBeClickable(ftViewButton));
		
		//Assert all elements are displayed
		Assert.assertTrue("SAP Interface Log Page: Customer PO Field not displayed",getCustPOField().isDisplayed());
		Assert.assertTrue("SAP Interface Log Page: Creation Date From Field not displayed",getCreateDateFromField().isDisplayed());
		Assert.assertTrue("SAP Interface Log Page: Your Material Number Field not displayed",getYourMatNumField().isDisplayed());
		Assert.assertTrue("SAP Interface Log Page: Ticket Field not displayed",getTicketField().isDisplayed());
		Assert.assertTrue("SAP Interface Log Page: Finish Field not displayed",getFinishField().isDisplayed());
		Assert.assertTrue("SAP Interface Log Page: SAP Message Field not displayed",getSAPMessageField().isDisplayed());
		Assert.assertTrue("SAP Interface Log Page: Order No Field not displayed",getOrderNoField().isDisplayed());
		Assert.assertTrue("SAP Interface Log Page: Brand Field not displayed",getBrandField().isDisplayed());
		Assert.assertTrue("SAP Interface Log Page: Sales Organisation Field not displayed",getSalesOrgField().isDisplayed());
		Assert.assertTrue("SAP Interface Log Page: Article Field not displayed",getArticleField().isDisplayed());
		Assert.assertTrue("SAP Interface Log Page: Length Field not displayed",getLengthField().isDisplayed());
		Assert.assertTrue("SAP Interface Log Page: Shade Code Field not displayed",getShadeCodeField().isDisplayed());
		Assert.assertTrue("SAP Interface Log Page: Search Button not displayed",getSearchButton().isDisplayed());
		Assert.assertTrue("SAP Interface Log Page: Reset Button not displayed",getResetButton().isDisplayed());
		Assert.assertTrue("SAP Interface Log Page: View Button not displayed",getViewButton().isDisplayed());
		Assert.assertTrue("SAP Interface Log Page: File Transfer View Button not displayed",getFTViewButton().isDisplayed());
	}
	
}
