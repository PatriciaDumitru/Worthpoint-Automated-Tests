
package PageObjects;

import AutomationFramework.CommonTask;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OutstandingPaymentsPage_EComm extends BasePage {
    
    //Locators
    By custNameField = By.id("s2id_filterSapccInvoiceCustomerId");
    By by30Button = By.cssSelector("#FilterPaymentForm > div.grid_12 > table > tbody > tr:nth-child(1) > td:nth-child(4) > label:nth-child(2)");
    By by60Button = By.cssSelector("#FilterPaymentForm > div.grid_12 > table > tbody > tr:nth-child(1) > td:nth-child(4) > label:nth-child(5)");
    By by90Button = By.cssSelector("#FilterPaymentForm > div.grid_12 > table > tbody > tr:nth-child(1) > td:nth-child(4) > label:nth-child(8)");
    By allButton = By.cssSelector("#FilterPaymentForm > div.grid_12 > table > tbody > tr:nth-child(1) > td:nth-child(4) > label:nth-child(11)");
    By searchButton = By.cssSelector("#FilterPaymentForm > div.grid_12 > table > tbody > tr:nth-child(2) > td > div > input");
    By resetButton = By.cssSelector("#FilterPaymentForm > div.grid_12 > table > tbody > tr:nth-child(2) > td > a");
    By viewButton = By.cssSelector("#content > div.tbl-toggle.cc_grid_outer > div > div.scrollTableContainer > table > tbody > tr:nth-child(3) > td:nth-child(1) > a");
    By exportButton = By.cssSelector("#content > div.tbl-toggle.cc_grid_outer > div > div.scrollTableContainer > div > a");
    
    public OutstandingPaymentsPage_EComm(WebDriver passedDriver) {
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
    
    public OutstandingPaymentsPage_EComm setCustName(String item) {
        CommonTask.setSearchField(driver, custNameField, item);
        return this;
    }
    
    public OutstandingPaymentsPage_EComm pressSearch() {
        WebElement waitForClickable = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(searchButton));
        driver.findElement(searchButton).click();
        return new OutstandingPaymentsPage_EComm(driver);
    }
    
    public OutstandingPaymentsPage_EComm pressReset() {
        WebElement waitForClickable = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(resetButton));
        driver.findElement(resetButton).click();
        return new OutstandingPaymentsPage_EComm(driver);
    }
    
    public OrderViewPage pressView() {
        WebElement waitForClickable = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(viewButton));
        driver.findElement(viewButton).click();
        return new OrderViewPage(driver);
    }
    
    public OutstandingPaymentsPage_EComm pressExport() {
        WebElement waitForClickable = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(exportButton));
        driver.findElement(exportButton).click();
        return new OutstandingPaymentsPage_EComm(driver);
    }
    
    public OutstandingPaymentsPage_EComm pressOverdue30() {
        WebElement waitForClickable = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(by30Button));
        driver.findElement(by30Button).click();
        return this;
    }
    
    public OutstandingPaymentsPage_EComm pressOverdue60() {
        WebElement waitForClickable = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(by60Button));
        driver.findElement(by60Button).click();
        return this;
    }
    
    public OutstandingPaymentsPage_EComm pressOverdue90() {
        WebElement waitForClickable = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(by90Button));
        driver.findElement(by90Button).click();
        return this;
    }
    
    public OutstandingPaymentsPage_EComm pressAll() {
        WebElement waitForClickable = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(allButton));
        driver.findElement(allButton).click();
        return this;
    }
    
    public void checkFields() {
        //Wait for all elements to be clickable
        WebElement waitForCustName = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(custNameField));
        WebElement waitFor30Button = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(by30Button));
        WebElement waitFor60Button = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(by60Button));
        WebElement waitFor90Button = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(by90Button));
        WebElement waitForAllButton = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(allButton));
        WebElement waitForSearchButton = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(searchButton));
        WebElement waitForResetButton = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(resetButton));
        WebElement waitForViewButton = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(viewButton));
        WebElement waitForExportButton = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(exportButton));
        
        //Assert all elements are displayed
        Assert.assertTrue("Outstanding Payments Page: Customer Name Field not displayed correctly",getCustNameField().isDisplayed());       
        Assert.assertTrue("Outstanding Payments Page: Overdue by 30 button not displayed correctly",get30Button().isDisplayed());       
        Assert.assertTrue("Outstanding Payments Page: Overdue by 60 button not displayed correctly",get60Button().isDisplayed());       
        Assert.assertTrue("Outstanding Payments Page: Overdue by 90 button not displayed correctly",get90Button().isDisplayed());       
        Assert.assertTrue("Outstanding Payments Page: All button not displayed correctly",getAllButton().isDisplayed());       
        Assert.assertTrue("Outstanding Payments Page: Search button not displayed correctly",getSearchButton().isDisplayed());       
        Assert.assertTrue("Outstanding Payments Page: Reset button not displayed correctly",getResetButton().isDisplayed());       
        Assert.assertTrue("Outstanding Payments Page: View button not displayed correctly",getViewButton().isDisplayed());       
        Assert.assertTrue("Outstanding Payments Page: Export button not displayed correctly",getExportButton().isDisplayed());       
    }
    
    
    
}
