
package PageObjects;

import AutomationFramework.CommonTask;
import AutomationFramework.DataItems;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.AssertJUnit;

public class Ecomm_DeniedOrderPage extends WBA_BasePage {
    
    //Locators
    By orderNoField = By.id("s2id_filterBulkOrderId");
    By custPOField = By.id("filterBulkOrderPoNumber");
    By searchButton = By.cssSelector("#FilterDeniedorderForm > div.grid_12 > table > tbody > tr:nth-child(5) > td > div > input");
    By resetButton = By.cssSelector("#FilterDeniedorderForm > div.grid_12 > table > tbody > tr:nth-child(5) > td > a");
    
    public Ecomm_DeniedOrderPage(WebDriver driver) {
        super(driver);
    }
    
    public WebElement getBreadcrumb() {
        WebElement breadcrumb = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.visibilityOf(driver.findElement(DataItems.breadcrumbLocator2)));
        return breadcrumb;
    }
    
    public WebElement getOrderNoField() {
        WebElement field = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(orderNoField));
        return field;
    }
    
    public WebElement getCustomerPOField() {
        WebElement field = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(custPOField));
        return field;
    }
    
    public WebElement getSearchButton() {
        WebElement field = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(searchButton));
        return field;
    }
    
    public WebElement getResetButton() {
        WebElement field = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(resetButton));
        return field;
    }
    
    public Ecomm_DeniedOrderPage setOrderNo(String item) {
        CommonTask.setInputField(driver, orderNoField, item);
        return new Ecomm_DeniedOrderPage(driver);
    }
    
    public Ecomm_DeniedOrderPage setCustomerPO(String item) {
        CommonTask.setInputField(driver, custPOField, item);
        return new Ecomm_DeniedOrderPage(driver);
    }
    
    public Ecomm_DeniedOrderPage pressSearch() {
        WebElement btn = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(searchButton));
        btn.click();
        
        return new Ecomm_DeniedOrderPage(driver);
    }
    
    public Ecomm_DeniedOrderPage pressReset() {
        WebElement btn = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(resetButton));
        btn.click();
        
        return new Ecomm_DeniedOrderPage(driver);
    }
    
    public Ecomm_OrderViewPage pressView(int row) {
        By selector = By.cssSelector("#content > div.tbl-toggle > div > div.scrollTableContainer.scroll-pane > table > tbody:nth-child(2) > tr:nth-child("+row+") > td:nth-child(3) > a");
        WebElement viewBtn = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(selector));
        viewBtn.click();
        
        return new Ecomm_OrderViewPage(driver);
    }
    
    public Ecomm_OutstandingOrderDraftPage pressCancel(int row) {
        By selector = By.cssSelector("#content > div.tbl-toggle > div > div.scrollTableContainer.scroll-pane > table > tbody:nth-child(2) > tr:nth-child("+row+") > td:nth-child(2) > a > span");
        WebElement btn = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(selector));
        btn.click();
        
        try {
            Alert alert = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.alertIsPresent());
            alert.accept();
        } catch (TimeoutException e) {
            
        }
        
        return new Ecomm_OutstandingOrderDraftPage(driver);
    }
    
    public Ecomm_ManualEntryPage pressEdit(int row) {
        By selector = By.cssSelector("#content > div.tbl-toggle > div > div.scrollTableContainer.scroll-pane > table > tbody:nth-child(2) > tr:nth-child("+row+") > td:nth-child(1) > a > span");
        WebElement btn = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(selector));
        btn.click();
        
        Alert alert = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.alertIsPresent());
        alert.accept();
        
        return new Ecomm_ManualEntryPage(driver);
    }

    public int findOrder(String orderNo) {
        for (int i = 1; i < 10; i++) {
            By selector = By.cssSelector("#content > div.tbl-toggle > div > div.scrollTableContainer.scroll-pane > table > tbody:nth-child(2) > tr:nth-child("+i+") > td:nth-child(5)");
            
            WebElement cell = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.visibilityOfElementLocated(selector));
            
            if (cell.getText().contains(orderNo)) {
                return i;
            }
        }
        return -1;
    }
    
    public String getOrderNo(int row) {
        By selector = By.cssSelector("#content > div.tbl-toggle > div > div.scrollTableContainer.scroll-pane > table > tbody:nth-child(2) > tr:nth-child("+row+") > td:nth-child(7)");
        WebElement cell = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.visibilityOfElementLocated(selector));
        return cell.getText();
    }
    
    public void checkFields() {
        //Wait for all elements to be clickable
        WebElement orderNo = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(orderNoField));
        WebElement custPO = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(custPOField));
        WebElement search = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(searchButton));
        WebElement reset = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(resetButton));
        
        //Assert all elements are displayed
        AssertJUnit.assertTrue("Denied Order Page: Order No. field not displayed as expected",orderNo.isDisplayed());
        AssertJUnit.assertTrue("Denied Order Page: Customer PO field not displayed as expected",custPO.isDisplayed());
        AssertJUnit.assertTrue("Denied Order Page: Search Button not displayed as expected",search.isDisplayed());
        AssertJUnit.assertTrue("Denied Order Page: Reset Button not displayed as expected",reset.isDisplayed());
    }
    
    public void waitForElement() {
        WebElement wait = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(custPOField));
    }
    
}
