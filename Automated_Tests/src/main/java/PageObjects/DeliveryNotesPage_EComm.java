
package PageObjects;

import AutomationFramework.CommonTask;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class DeliveryNotesPage_EComm extends BasePage {
    
    //Locators
    By custNameField = By.id("s2id_filterSapccDeliverynoteCustomerId");
    By delDateFromField = By.id("filterSapccDeliverynoteDeliveryDateFrom");
    By delDateToField = By.id("filterSapccDeliverynoteDeliveryDateTo");
    By matNumField = By.id("filterSapccDeliverynoteMaterial");
    By brandField = By.id("filterSapccDeliverynoteBrandId");
    By lengthField = By.id("filterSapccDeliverynoteLengthId");
    By requesterField = By.id("filterRequesterId");
    By delNotesField = By.id("filterSapccDeliverynoteDeliveryNo");
    By yourMatNumField = By.id("filterSapccDeliverynoteCustomerMaterialNo");
    By ticketField = By.id("filterSapccDeliverynoteTicketId");
    By custPOField = By.id("filterSapccDeliverynotePoNumber");
    By searchButton = By.cssSelector("#FilterDeliverynoteForm > div.grid_12 > table > tbody > tr:nth-child(7) > td > div > input");
    By resetButton = By.cssSelector("#FilterDeliverynoteForm > div.grid_12 > table > tbody > tr:nth-child(7) > td > a");
    By printButton = By.cssSelector("#content > div.tbl-toggle.cc_grid_outer > div > div.scrollTableContainer > table > tbody > tr:nth-child(3) > td:nth-child(1) > a");
    By viewButton = By.cssSelector("#content > div.tbl-toggle.cc_grid_outer > div > div.scrollTableContainer > table > tbody > tr:nth-child(3) > td:nth-child(2) > a");
    By exportButton = By.cssSelector("#content > div.tbl-toggle.cc_grid_outer > div > div.scrollTableContainer > div > a");
    
    public DeliveryNotesPage_EComm(WebDriver passedDriver) {
        super(passedDriver);
    }
    
    public WebElement getCustNameField() {
        return driver.findElement(custNameField);
    }
    
    public WebElement getDelDateFromField() {
        return driver.findElement(delDateFromField);
    }
    
    public WebElement getDelDateToField() {
        return driver.findElement(delDateToField);
    }
    
    public WebElement getMatNumField() {
        return driver.findElement(matNumField);
    }
    
    public WebElement getBrandField() {
        return driver.findElement(brandField);
    }
    
    public WebElement getLengthField() {
        return driver.findElement(lengthField);
    }
    
    public WebElement getRequesterField() {
        return driver.findElement(requesterField);
    }
    
    public WebElement getDelNotesField() {
        return driver.findElement(delNotesField);
    }
    
    public WebElement getYourMatNumField() {
        return driver.findElement(yourMatNumField);
    }
    
    public WebElement getTicketField() {
        return driver.findElement(ticketField);
    }
    
    public WebElement getCustPOField() {
        return driver.findElement(custPOField);
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
    
    public DeliveryNotesPage_EComm setCustName(String item) {
        CommonTask.setSearchField(driver, custNameField, item);
        return this;
    }
    
    public DeliveryNotesPage_EComm setDelNoteNo(String item) {
        CommonTask.setTextField(driver, delNotesField, item);
        return this;
    }
    
    public DeliveryNotesPage_EComm setRequester(String item) throws InterruptedException {
        CommonTask.setDropDownField(driver, requesterField, item);
        return this;
    }
    
    public DeliveryNotesPage_EComm setCustPO(String item) throws InterruptedException {
        CommonTask.setDropDownField(driver, custPOField, item);
        return this;
    }
    
    public DeliveryNotesPage_EComm pressSearch() {
        WebElement waitForClickable = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(searchButton));
        driver.findElement(searchButton).click();
        return this;
    }
    
    public DeliveryNotesPage_EComm pressReset() {
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
        //Wait for all items to be clickable
        WebElement waitForCustName = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(custNameField));
        WebElement waitForDelFrom = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(delDateFromField));
        WebElement waitForDelTo = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(delDateToField));
        WebElement waitForMatNum = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(matNumField));
        WebElement waitForBrand = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(brandField));
        WebElement waitForLength = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(lengthField));
        WebElement waitForRequester = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(requesterField));
        WebElement waitForDelNotes = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(delNotesField));
        WebElement waitForYourMat = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(yourMatNumField));
        WebElement waitForTicket = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(ticketField));
        WebElement waitForCustPO = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(custPOField));
        WebElement waitForSearch = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(searchButton));
        WebElement waitForReset = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(resetButton));
        WebElement waitForView = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(viewButton));
        WebElement waitForPrint = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(printButton));
        WebElement waitForExport = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(exportButton));
    
        //Assert all elements are displayed
        Assert.assertTrue("Delivery Notes Page: Customer Name Field not displayed correctly",getCustNameField().isDisplayed());
        Assert.assertTrue("Delivery Notes Page: Delivery Date From Field not displayed correctly",getDelDateFromField().isDisplayed());
        Assert.assertTrue("Delivery Notes Page: Delivery Date To Field not displayed correctly",getDelDateToField().isDisplayed());
        Assert.assertTrue("Delivery Notes Page: Material Number Field not displayed correctly",getMatNumField().isDisplayed());
        Assert.assertTrue("Delivery Notes Page: Brand Field not displayed correctly",getBrandField().isDisplayed());
        Assert.assertTrue("Delivery Notes Page: Length Field not displayed correctly",getLengthField().isDisplayed());
        Assert.assertTrue("Delivery Notes Page: Requester Field not displayed correctly",getRequesterField().isDisplayed());
        Assert.assertTrue("Delivery Notes Page: Delivery Notes Field not displayed correctly",getDelNotesField().isDisplayed());
        Assert.assertTrue("Delivery Notes Page: Your Material Number Field not displayed correctly",getYourMatNumField().isDisplayed());
        Assert.assertTrue("Delivery Notes Page: Ticket Field not displayed correctly",getTicketField().isDisplayed());
        Assert.assertTrue("Delivery Notes Page: Customer PO Field not displayed correctly",getCustPOField().isDisplayed());
        Assert.assertTrue("Delivery Notes Page: Search Button not displayed correctly",getSearchButton().isDisplayed());
        Assert.assertTrue("Delivery Notes Page: Reset Button not displayed correctly",getResetButton().isDisplayed());
        Assert.assertTrue("Delivery Notes Page: View Button not displayed correctly",getViewButton().isDisplayed());
        Assert.assertTrue("Delivery Notes Page: Print Button not displayed correctly",getPrintButton().isDisplayed());
        Assert.assertTrue("Delivery Notes Page: Export Button not displayed correctly",getExportButton().isDisplayed());
    
    }
}