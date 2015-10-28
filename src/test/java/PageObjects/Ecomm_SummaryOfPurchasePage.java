
package PageObjects;

import AutomationFramework.CommonTask;
import AutomationFramework.DataItems;
import static PageObjects.WBA_BasePage.driver;
import org.testng.AssertJUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Ecomm_SummaryOfPurchasePage extends WBA_BasePage {
    
    //Locators
    By custNameField = By.id("s2id_filterSapccPurchaseCustomerId");
    By custPOField = By.id("filterSapccPurchasePoNumber");
    By matNumField = By.id("filterSapccPurchaseMaterial");
    By brandField = By.id("filterSapccPurchaseBrandId");
    By lengthField = By.id("filterSapccPurchaseLengthId");
    By purchaseDateFromField = By.id("filterSapccPurchasePoDateFrom");
    By purchaseDateToField = By.id("filterSapccPurchasePoDateTo");
    By yourMatNumField = By.id("filterSapccPurchaseCustomerMaterialNo");
    By ticketField = By.id("filterSapccPurchaseTicketId");
    By requesterField = By.id("filterRequesterId");
    By searchButton = By.cssSelector("#FilterPurchaseForm > div.grid_12 > table > tbody > tr:nth-child(6) > td > div > input");
    By resetButton = By.cssSelector("#FilterPurchaseForm > div.grid_12 > table > tbody > tr:nth-child(6) > td > a");
    By viewButton = By.cssSelector("#content > div.tbl-toggle.cc_grid_outer > div > div.scrollTableContainer > table > tbody > tr:nth-child(3) > td:nth-child(1) > a");
    By exportButton = By.cssSelector("#content > div.tbl-toggle.cc_grid_outer > div > div.scrollTableContainer > div > a");
    public String brandPart1 = "#content > div.tbl-toggle.cc_grid_outer > div > div.scrollTableContainer > table > tbody > tr:nth-child(";
    public String brandPart2 = ") > td:nth-child(5)";
    public By noRecords = By.cssSelector("#content > div.tbl-toggle.cc_grid_outer > div > div.scrollTableContainer > div > div");
    
    public Ecomm_SummaryOfPurchasePage(WebDriver passedDriver) {
        super(passedDriver);
    }
    
    public WebElement getCustNameField() {
        return driver.findElement(custNameField);
    }
    
    public WebElement getCustPOField() {
        return driver.findElement(custPOField);
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
    
    public WebElement getPurchaseFromField() {
        return driver.findElement(purchaseDateFromField);
    }
    
    public WebElement getPurchaseToField() {
        return driver.findElement(purchaseDateToField);
    }
    
    public WebElement getYourMatNumField() {
        return driver.findElement(yourMatNumField);
    }
    
    public WebElement getTicketField() {
        return driver.findElement(ticketField);
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
    
    public WebElement getExportButton() {
        return driver.findElement(exportButton);
    }
    
    public Ecomm_SummaryOfPurchasePage setCustName(String item) {
        CommonTask.setSearchField(driver,custNameField,item);
        return this;
    }
    
    public Ecomm_SummaryOfPurchasePage setBrand(String item) throws InterruptedException {
        CommonTask.setDropDownField(driver,brandField,item);
        return this;
    }
    
    public Ecomm_SummaryOfPurchasePage setRequester(String item) throws InterruptedException {
        CommonTask.setDropDownField(driver,requesterField,item);
        return this;
    }
    
    public Ecomm_SummaryOfPurchasePage pressSearch() {
        WebElement waitForClickable = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(searchButton));
        driver.findElement(searchButton).click();
        return this;
    }
    
    public Ecomm_SummaryOfPurchasePage pressReset() {
        WebElement waitForClickable = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(resetButton));
        driver.findElement(resetButton).click();
        return this;
    }
    
    public Ecomm_OrderViewPage pressView() {
        WebElement waitForClickable = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(viewButton));
        driver.findElement(viewButton).click();
        return new Ecomm_OrderViewPage(driver);
    }
    
    public Ecomm_ExportDownloadPage pressExport() {
        WebElement waitForClickable = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(searchButton));
        driver.findElement(searchButton).click();
        return new Ecomm_ExportDownloadPage(driver);
    }
    
    public void checkFields_SUMST() {
        //Wait for all elements to be clickable
        WebElement waitForCustName = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(custNameField));
        WebElement waitForCustPO = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(custPOField));
        WebElement waitForBrand = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(brandField));
        WebElement waitForLength = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(lengthField));
        WebElement waitForMatNum = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(matNumField));
        WebElement waitForPurFrom = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(purchaseDateFromField));
        WebElement waitForPurTo = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(purchaseDateToField));
        WebElement waitForRequester = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(requesterField));
        WebElement waitForYourMat = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(yourMatNumField));
        WebElement waitForTicket = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(ticketField));
        WebElement waitForSearch = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(searchButton));
        WebElement waitForReset = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(resetButton));
        WebElement waitForView = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(viewButton));
        WebElement waitForExport = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(exportButton));
    
        //Assert all elements are displayed
        AssertJUnit.assertTrue("Summary of Purchase Page: Customer Name field not displayed correctly",getCustNameField().isDisplayed());
        AssertJUnit.assertTrue("Summary of Purchase Page: Customer PO field not displayed correctly",getCustPOField().isDisplayed());
        AssertJUnit.assertTrue("Summary of Purchase Page: Brand field not displayed correctly",getBrandField().isDisplayed());
        AssertJUnit.assertTrue("Summary of Purchase Page: Length field not displayed correctly",getLengthField().isDisplayed());
        AssertJUnit.assertTrue("Summary of Purchase Page: Material Number field not displayed correctly",getMatNumField().isDisplayed());
        AssertJUnit.assertTrue("Summary of Purchase Page: Purchase Date From field not displayed correctly",getPurchaseFromField().isDisplayed());
        AssertJUnit.assertTrue("Summary of Purchase Page: Purchase Date To field not displayed correctly",getPurchaseToField().isDisplayed());
        AssertJUnit.assertTrue("Summary of Purchase Page: Requester field not displayed correctly",getRequesterField().isDisplayed());
        AssertJUnit.assertTrue("Summary of Purchase Page: Your Material Number field not displayed correctly",getYourMatNumField().isDisplayed());
        AssertJUnit.assertTrue("Summary of Purchase Page: Ticket field not displayed correctly",getTicketField().isDisplayed());
        AssertJUnit.assertTrue("Summary of Purchase Page: Search button not displayed correctly",getSearchButton().isDisplayed());
        AssertJUnit.assertTrue("Summary of Purchase Page: Reset button not displayed correctly",getResetButton().isDisplayed());
        AssertJUnit.assertTrue("Summary of Purchase Page: View button not displayed correctly",getViewButton().isDisplayed());
        AssertJUnit.assertTrue("Summary of Purchase Page: Export button not displayed correctly",getExportButton().isDisplayed());
    }
    
    public void checkFields_SUSST() {
        //Wait for all elements to be clickable
        WebElement waitForCustPO = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(custPOField));
        WebElement waitForBrand = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(brandField));
        WebElement waitForLength = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(lengthField));
        WebElement waitForMatNum = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(matNumField));
        WebElement waitForPurFrom = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(purchaseDateFromField));
        WebElement waitForPurTo = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(purchaseDateToField));
        WebElement waitForYourMat = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(yourMatNumField));
        WebElement waitForTicket = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(ticketField));
        WebElement waitForSearch = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(searchButton));
        WebElement waitForReset = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(resetButton));
        WebElement waitForView = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(viewButton));
        WebElement waitForExport = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(exportButton));
    
        //Assert all elements are displayed
        AssertJUnit.assertTrue("Summary of Purchase Page: Customer PO field not displayed correctly",getCustPOField().isDisplayed());
        AssertJUnit.assertTrue("Summary of Purchase Page: Brand field not displayed correctly",getBrandField().isDisplayed());
        AssertJUnit.assertTrue("Summary of Purchase Page: Length field not displayed correctly",getLengthField().isDisplayed());
        AssertJUnit.assertTrue("Summary of Purchase Page: Material Number field not displayed correctly",getMatNumField().isDisplayed());
        AssertJUnit.assertTrue("Summary of Purchase Page: Purchase Date From field not displayed correctly",getPurchaseFromField().isDisplayed());
        AssertJUnit.assertTrue("Summary of Purchase Page: Purchase Date To field not displayed correctly",getPurchaseToField().isDisplayed());
        AssertJUnit.assertTrue("Summary of Purchase Page: Your Material Number field not displayed correctly",getYourMatNumField().isDisplayed());
        AssertJUnit.assertTrue("Summary of Purchase Page: Ticket field not displayed correctly",getTicketField().isDisplayed());
        AssertJUnit.assertTrue("Summary of Purchase Page: Search button not displayed correctly",getSearchButton().isDisplayed());
        AssertJUnit.assertTrue("Summary of Purchase Page: Reset button not displayed correctly",getResetButton().isDisplayed());
        AssertJUnit.assertTrue("Summary of Purchase Page: View button not displayed correctly",getViewButton().isDisplayed());
        AssertJUnit.assertTrue("Summary of Purchase Page: Export button not displayed correctly",getExportButton().isDisplayed());
    }
    
}
