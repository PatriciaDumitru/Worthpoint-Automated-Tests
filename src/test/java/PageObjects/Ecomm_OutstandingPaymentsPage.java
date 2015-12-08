
package PageObjects;

import AutomationFramework.CommonTask;
import AutomationFramework.DataItems;
import AutomationFramework.Wait;
import static PageObjects.WBA_BasePage.driver;
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
    By by30ButtonSUSST = By.cssSelector("#FilterPaymentForm > div.grid_12 > table > tbody > tr:nth-child(1) > td:nth-child(5) > label:nth-child(2)");
    By by60ButtonSUSST = By.cssSelector("#FilterPaymentForm > div.grid_12 > table > tbody > tr:nth-child(1) > td:nth-child(5) > label:nth-child(5)");
    By by90ButtonSUSST = By.cssSelector("#FilterPaymentForm > div.grid_12 > table > tbody > tr:nth-child(1) > td:nth-child(5) > label:nth-child(8)");
    By allButton = By.cssSelector("#FilterPaymentForm > div.grid_12 > table > tbody > tr:nth-child(1) > td:nth-child(4) > label:nth-child(11)");
    By allButtonSUSST = By.cssSelector("#FilterPaymentForm > div.grid_12 > table > tbody > tr:nth-child(1) > td:nth-child(5) > label:nth-child(11)");
    By searchButton = By.cssSelector("#FilterPaymentForm > div.grid_12 > table > tbody > tr:nth-child(2) > td > div > input");
    By resetButton = By.cssSelector("#FilterPaymentForm > div.grid_12 > table > tbody > tr:nth-child(2) > td > a");
    By viewButton = By.cssSelector("#content > div.tbl-toggle.cc_grid_outer > div > div.scrollTableContainer > table > tbody > tr:nth-child(3) > td:nth-child(1) > a");
    By exportButton = By.cssSelector("#content > div.tbl-toggle.cc_grid_outer > div > div.scrollTableContainer > div > a");
    By noRecords = By.cssSelector("#content > div.tbl-toggle.cc_grid_outer > div > div.scrollTableContainer > div > div");
    
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
    
    public WebElement get30ButtonSUSST() {
        return driver.findElement(by30ButtonSUSST);
    }
    
    public WebElement get60ButtonSUSST() {
        return driver.findElement(by60ButtonSUSST);
    }
    
    public WebElement get90ButtonSUSST() {
        return driver.findElement(by90ButtonSUSST);
    }
    
    public WebElement getAllButtonSUSST() {
        return driver.findElement(allButtonSUSST);
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
        WebElement search = Wait.clickable(driver,searchButton);
        search.click();
        return new Ecomm_OutstandingPaymentsPage(driver);
    }
    
    public Ecomm_OutstandingPaymentsPage pressReset() {
        WebElement reset = Wait.clickable(driver,resetButton);
        reset.click();
        return new Ecomm_OutstandingPaymentsPage(driver);
    }
    
    public Ecomm_OrderViewPage pressView() {
        WebElement view = Wait.clickable(driver,viewButton);
        view.click();
        return new Ecomm_OrderViewPage(driver);
    }
    
    public Ecomm_ExportDownloadPage pressExport() {
        WebElement export = Wait.clickable(driver,exportButton);
        export.click();
        return new Ecomm_ExportDownloadPage(driver);
    }
    
    public Ecomm_OutstandingPaymentsPage pressOverdue30() {
        WebElement btn = Wait.clickable(driver,by30Button);
        btn.click();
        return this;
    }
    
    public Ecomm_OutstandingPaymentsPage pressOverdue30SUSST() {
        //Locator is different for SUSST users, hence this method was created
        WebElement btn = Wait.clickable(driver,by30ButtonSUSST);
        btn.click();
        return this;
    }
    
    public Ecomm_OutstandingPaymentsPage pressOverdue60() {
        WebElement btn = Wait.clickable(driver,by60Button);
        btn.click();
        return this;
    }
    
    public Ecomm_OutstandingPaymentsPage pressOverdue60SUSST() {
        WebElement btn = Wait.clickable(driver,by60ButtonSUSST);
        btn.click();
        return this;
    }
    
    public Ecomm_OutstandingPaymentsPage pressOverdue90() {
        WebElement btn = Wait.clickable(driver,by90Button);
        btn.click();
        boolean waitForChecked = Wait.checked(driver,by90Box);
        return this;
    }
    
    public Ecomm_OutstandingPaymentsPage pressOverdue90SUSST() {
        WebElement btn = Wait.clickable(driver,by90ButtonSUSST);
        btn.click();

        return this;
    }
    
    public Ecomm_OutstandingPaymentsPage pressAll() {
        WebElement all = Wait.clickable(driver,allButton);
        all.click();
        return this;
    }
    
    public void checkFields_SUMST() {
        //Wait for all elements to be clickable
        WebElement custName = Wait.clickable(driver,custNameField);
        WebElement button30 = Wait.clickable(driver,by30Button);
        WebElement button60 = Wait.clickable(driver,by60Button);
        WebElement button90 = Wait.clickable(driver,by90Button);
        WebElement all = Wait.clickable(driver,allButton);
        WebElement search = Wait.clickable(driver,searchButton);
        WebElement reset = Wait.clickable(driver,resetButton);
        WebElement view = Wait.clickable(driver,viewButton);
        WebElement export = Wait.clickable(driver,exportButton);
        
        //Assert all elements are displayed
        AssertJUnit.assertTrue("Outstanding Payments Page: Customer Name Field not displayed correctly",custName.isDisplayed());       
        AssertJUnit.assertTrue("Outstanding Payments Page: Overdue by 30 button not displayed correctly",button30.isDisplayed());       
        AssertJUnit.assertTrue("Outstanding Payments Page: Overdue by 60 button not displayed correctly",button60.isDisplayed());       
        AssertJUnit.assertTrue("Outstanding Payments Page: Overdue by 90 button not displayed correctly",button90.isDisplayed());       
        AssertJUnit.assertTrue("Outstanding Payments Page: All button not displayed correctly",all.isDisplayed());       
        AssertJUnit.assertTrue("Outstanding Payments Page: Search button not displayed correctly",search.isDisplayed());       
        AssertJUnit.assertTrue("Outstanding Payments Page: Reset button not displayed correctly",reset.isDisplayed());       
        AssertJUnit.assertTrue("Outstanding Payments Page: View button not displayed correctly",view.isDisplayed());       
        AssertJUnit.assertTrue("Outstanding Payments Page: Export button not displayed correctly",export.isDisplayed());       
    }
    
    public void checkFields_SUSST() {
        //Wait for all elements to be clickable
        WebElement button30 = Wait.clickable(driver,by30ButtonSUSST);
        WebElement button60 = Wait.clickable(driver,by60ButtonSUSST);
        WebElement button90 = Wait.clickable(driver,by90ButtonSUSST);
        WebElement all = Wait.clickable(driver,allButtonSUSST);
        WebElement search = Wait.clickable(driver,searchButton);
        WebElement reset = Wait.clickable(driver,resetButton);
        WebElement view = Wait.clickable(driver,viewButton);
        WebElement export = Wait.clickable(driver,exportButton);
        
        //Assert all elements are displayed       
        AssertJUnit.assertTrue("Outstanding Payments Page: Overdue by 30 button not displayed correctly",button30.isDisplayed());       
        AssertJUnit.assertTrue("Outstanding Payments Page: Overdue by 60 button not displayed correctly",button60.isDisplayed());       
        AssertJUnit.assertTrue("Outstanding Payments Page: Overdue by 90 button not displayed correctly",button90.isDisplayed());       
        AssertJUnit.assertTrue("Outstanding Payments Page: All button not displayed correctly",all.isDisplayed());       
        AssertJUnit.assertTrue("Outstanding Payments Page: Search button not displayed correctly",search.isDisplayed());       
        AssertJUnit.assertTrue("Outstanding Payments Page: Reset button not displayed correctly",reset.isDisplayed());       
        AssertJUnit.assertTrue("Outstanding Payments Page: View button not displayed correctly",view.isDisplayed());       
        AssertJUnit.assertTrue("Outstanding Payments Page: Export button not displayed correctly",export.isDisplayed());
    }
    
    public boolean recordsPresent() {
        try {
            WebElement wait = Wait.visible(driver,noRecords);
            return false;
        } catch (Exception e) {
            return true;
        }
    }
    
    
}
