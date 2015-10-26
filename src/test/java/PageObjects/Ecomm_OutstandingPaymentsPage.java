
package PageObjects;

import AutomationFramework.CommonTask;
import org.testng.AssertJUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Ecomm_OutstandingPaymentsPage extends WBA_BasePage {
    
    //Locators
    By custNameField = By.id("s2id_filterSapccInvoiceCustomerId");
    By by30Button = By.cssSelector("#FilterPaymentForm > div.grid_12 > table > tbody > tr:nth-child(1) > td:nth-child(4) > label:nth-child(2)");
    By by60Button = By.cssSelector("#FilterPaymentForm > div.grid_12 > table > tbody > tr:nth-child(1) > td:nth-child(4) > label:nth-child(5)");
    By by90Button = By.cssSelector("#FilterPaymentForm > div.grid_12 > table > tbody > tr:nth-child(1) > td:nth-child(4) > label:nth-child(8)");
    By by90Box = By.xpath("//*[@id=\"FilterPaymentForm\"]/div[2]/table/tbody/tr[1]/td[2]/input[3]");
    By allButton = By.cssSelector("#FilterPaymentForm > div.grid_12 > table > tbody > tr:nth-child(1) > td:nth-child(4) > label:nth-child(11)");
    By searchButton = By.cssSelector("#FilterPaymentForm > div.grid_12 > table > tbody > tr:nth-child(2) > td > div > input");
    By resetButton = By.cssSelector("#FilterPaymentForm > div.grid_12 > table > tbody > tr:nth-child(2) > td > a");
    By viewButton = By.cssSelector("#content > div.tbl-toggle.cc_grid_outer > div > div.scrollTableContainer > table > tbody > tr:nth-child(3) > td:nth-child(1) > a");
    By exportButton = By.cssSelector("#content > div.tbl-toggle.cc_grid_outer > div > div.scrollTableContainer > div > a");
    
    public Ecomm_OutstandingPaymentsPage(WebDriver passedDriver) {
        super(passedDriver);
    }
    
    public WebElement getCustNameField() {
        return driver.findElement(custNameField);
    }
    
    public WebElement get30Button() {
        return driver.findElement(by30Button);
    }
    
    public WebElement get60Button() {
        return driver.findElement(by60Button);
    }
    
    public WebElement get90Button() {
        return driver.findElement(by90Button);
    }
    
    public WebElement getAllButton() {
        return driver.findElement(allButton);
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
    
    public Ecomm_OutstandingPaymentsPage setCustName(String item) {
        CommonTask.setSearchField(driver, custNameField, item);
        return this;
    }
    
    public Ecomm_OutstandingPaymentsPage pressSearch() {
        WebElement waitForClickable = new WebDriverWait(driver,5).until(ExpectedConditions.elementToBeClickable(searchButton));
        driver.findElement(searchButton).click();
        return new Ecomm_OutstandingPaymentsPage(driver);
    }
    
    public Ecomm_OutstandingPaymentsPage pressReset() {
        WebElement waitForClickable = new WebDriverWait(driver,5).until(ExpectedConditions.elementToBeClickable(resetButton));
        driver.findElement(resetButton).click();
        return new Ecomm_OutstandingPaymentsPage(driver);
    }
    
    public Ecomm_OrderViewPage pressView() {
        WebElement waitForClickable = new WebDriverWait(driver,5).until(ExpectedConditions.elementToBeClickable(viewButton));
        driver.findElement(viewButton).click();
        return new Ecomm_OrderViewPage(driver);
    }
    
    public Ecomm_ExportDownloadPage pressExport() {
        WebElement waitForClickable = new WebDriverWait(driver,5).until(ExpectedConditions.elementToBeClickable(exportButton));
        driver.findElement(exportButton).click();
        return new Ecomm_ExportDownloadPage(driver);
    }
    
    public Ecomm_OutstandingPaymentsPage pressOverdue30() {
        WebElement waitForClickable = new WebDriverWait(driver,5).until(ExpectedConditions.elementToBeClickable(by30Button));
        driver.findElement(by30Button).click();
        return this;
    }
    
    public Ecomm_OutstandingPaymentsPage pressOverdue60() {
        WebElement waitForClickable = new WebDriverWait(driver,5).until(ExpectedConditions.elementToBeClickable(by60Button));
        driver.findElement(by60Button).click();
        return this;
    }
    
    public Ecomm_OutstandingPaymentsPage pressOverdue90() {
        WebElement waitForClickable = new WebDriverWait(driver,5).until(ExpectedConditions.elementToBeClickable(by90Button));
        driver.findElement(by90Button).click();
        boolean waitForChecked = new WebDriverWait(driver,5).until(CommonTask.boxIsChecked(driver.findElement(by90Box)));
        return this;
    }
    
    public Ecomm_OutstandingPaymentsPage pressAll() {
        WebElement waitForClickable = new WebDriverWait(driver,5).until(ExpectedConditions.elementToBeClickable(allButton));
        driver.findElement(allButton).click();
        return this;
    }
    
    public void checkFields() {
        //Wait for all elements to be clickable
        WebElement waitForCustName = new WebDriverWait(driver,5).until(ExpectedConditions.elementToBeClickable(custNameField));
        WebElement waitFor30Button = new WebDriverWait(driver,5).until(ExpectedConditions.elementToBeClickable(by30Button));
        WebElement waitFor60Button = new WebDriverWait(driver,5).until(ExpectedConditions.elementToBeClickable(by60Button));
        WebElement waitFor90Button = new WebDriverWait(driver,5).until(ExpectedConditions.elementToBeClickable(by90Button));
        WebElement waitForAllButton = new WebDriverWait(driver,5).until(ExpectedConditions.elementToBeClickable(allButton));
        WebElement waitForSearchButton = new WebDriverWait(driver,5).until(ExpectedConditions.elementToBeClickable(searchButton));
        WebElement waitForResetButton = new WebDriverWait(driver,5).until(ExpectedConditions.elementToBeClickable(resetButton));
        WebElement waitForViewButton = new WebDriverWait(driver,5).until(ExpectedConditions.elementToBeClickable(viewButton));
        WebElement waitForExportButton = new WebDriverWait(driver,5).until(ExpectedConditions.elementToBeClickable(exportButton));
        
        //Assert all elements are displayed
        AssertJUnit.assertTrue("Outstanding Payments Page: Customer Name Field not displayed correctly",getCustNameField().isDisplayed());       
        AssertJUnit.assertTrue("Outstanding Payments Page: Overdue by 30 button not displayed correctly",get30Button().isDisplayed());       
        AssertJUnit.assertTrue("Outstanding Payments Page: Overdue by 60 button not displayed correctly",get60Button().isDisplayed());       
        AssertJUnit.assertTrue("Outstanding Payments Page: Overdue by 90 button not displayed correctly",get90Button().isDisplayed());       
        AssertJUnit.assertTrue("Outstanding Payments Page: All button not displayed correctly",getAllButton().isDisplayed());       
        AssertJUnit.assertTrue("Outstanding Payments Page: Search button not displayed correctly",getSearchButton().isDisplayed());       
        AssertJUnit.assertTrue("Outstanding Payments Page: Reset button not displayed correctly",getResetButton().isDisplayed());       
        AssertJUnit.assertTrue("Outstanding Payments Page: View button not displayed correctly",getViewButton().isDisplayed());       
        AssertJUnit.assertTrue("Outstanding Payments Page: Export button not displayed correctly",getExportButton().isDisplayed());       
    }
    
    
    
}
