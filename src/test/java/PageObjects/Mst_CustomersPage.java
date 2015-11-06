
package PageObjects;

import AutomationFramework.CommonTask;
import AutomationFramework.DataItems;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.AssertJUnit;

public class Mst_CustomersPage extends WBA_BasePage {
    
    By customerNameField = By.id("filterCustomerCustomerName");
    By salesOrgField = By.id("s2id_filterCustomerSalesOrgId");
    By searchButton = By.cssSelector("#FilterIndexForm > div.actions > ul > li:nth-child(1) > input[type=\"submit\"]");
    By resetButton = By.cssSelector("#FilterIndexForm > div.actions > ul > li:nth-child(2) > a");
    By importButton = By.cssSelector("#content > div.actions > ul > li:nth-child(1) > a");
    By exportButton = By.cssSelector("#export-menu > a");
    By newCustomerButton = By.cssSelector("#content > div.actions > ul > li:nth-child(3) > a");
    
    public Mst_CustomersPage(WebDriver driver) {
        super(driver);
    }
    
    public WebElement getCustomerNameField() {
        WebElement field = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(customerNameField));
        return field;
    }
    
    public WebElement getSalesOrgField() {
        return driver.findElement(salesOrgField);
    }
    
    public WebElement getSearchButton() {
        return driver.findElement(searchButton);
    }
    
    public WebElement getResetButton() {
        return driver.findElement(resetButton);
    }
    
    public WebElement getImportButton() {
        return driver.findElement(importButton);
    }
    
    public WebElement getExportButton() {
        return driver.findElement(exportButton);
    }
    
    public WebElement getNewCustomerButton() {
        return driver.findElement(newCustomerButton);
    }
    
    public Mst_CustomersPage setCustomerName(String item) {
        CommonTask.setInputField(driver,customerNameField,item);
        return new Mst_CustomersPage(driver);
    } 
    
    public int findCustomer(String item) {
        for (int i = 2; i < 10; i++) {
            By nameCell = By.cssSelector("#content > div.flexi-grid > table > tbody > tr:nth-child("+i+") > td:nth-child(4)");
            WebElement cell = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.visibilityOfElementLocated(nameCell));
            
            if (cell.getText().equals(item)) {
                return i;
            } 
        }
        return -1;
    }
    
    public Mst_CustomersPage pressSearch() {
        WebElement field = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(searchButton));
        field.click();
        
        return new Mst_CustomersPage(driver);
    }
    
    public Mst_CustomersPage pressReset() {
        WebElement field = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(resetButton));
        field.click();
        
        return new Mst_CustomersPage(driver);
    }
     
    public Mst_AddCustomerPage pressNewCustomer() {
        WebElement field = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(newCustomerButton));
        field.click();
        
        return new Mst_AddCustomerPage(driver);
    }
    
    public Mst_EditCustomerPage pressEdit(int row) {
        By locator = By.cssSelector("#content > div.flexi-grid > table > tbody > tr:nth-child("+row+") > td.actions > a:nth-child(1)");
        WebElement btn = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(locator));
    
        btn.click();
        
        return new Mst_EditCustomerPage(driver);
    }
    
    public void waitForElement() {
        WebElement wait = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(customerNameField));
    }
    
    public void checkFields() {
        //Wait for all elements to be clickable
        WebElement custName = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(customerNameField));
        WebElement salesOrg = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(salesOrgField));
        WebElement search = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(searchButton));
        WebElement reset = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(resetButton));
        WebElement importB = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(importButton));
        WebElement export = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(exportButton));
        WebElement newCust = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(newCustomerButton));
        
        //Assert all elements are displayed
        AssertJUnit.assertTrue("Customers Master Page: Customer Name field not displayed correctly", custName.isDisplayed());
        AssertJUnit.assertTrue("Customers Master Page: Sales Organisation field not displayed correctly", salesOrg.isDisplayed());
        AssertJUnit.assertTrue("Customers Master Page: Search field not displayed correctly", search.isDisplayed());
        AssertJUnit.assertTrue("Customers Master Page: Reset field not displayed correctly", reset.isDisplayed());
        AssertJUnit.assertTrue("Customers Master Page: Import field not displayed correctly", importB.isDisplayed());
        AssertJUnit.assertTrue("Customers Master Page: Export field not displayed correctly", export.isDisplayed());
        AssertJUnit.assertTrue("Customers Master Page: New Customer field not displayed correctly", newCust.isDisplayed());
    }
    
}
