
package PageObjects;

import AutomationFramework.CommonTask;
import AutomationFramework.DataItems;
import AutomationFramework.Wait;
import static PageObjects.WBA_BasePage.driver;
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
        return Wait.visible(driver,DataItems.breadcrumbLocator2);
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
        WebElement btn = Wait.clickable(driver,searchButton);
        btn.click();
        
        return new Ecomm_DeniedOrderPage(driver);
    }
    
    public Ecomm_DeniedOrderPage pressReset() {
        WebElement btn = Wait.clickable(driver,resetButton);
        btn.click();
        
        return new Ecomm_DeniedOrderPage(driver);
    }
    
    public Ecomm_OrderViewPage pressView(int row) {
        By selector = By.cssSelector("#content > div.tbl-toggle > div > div.scrollTableContainer.scroll-pane > table > tbody:nth-child(2) > tr:nth-child("+row+") > td:nth-child(3) > a");
        WebElement viewBtn = Wait.clickable(driver,selector);
        viewBtn.click();
        
        return new Ecomm_OrderViewPage(driver);
    }
    
    public Ecomm_OutstandingOrderDraftPage pressCancel(int row) {
        By selector = By.cssSelector("#content > div.tbl-toggle > div > div.scrollTableContainer.scroll-pane > table > tbody:nth-child(2) > tr:nth-child("+row+") > td:nth-child(2) > a > span");
        WebElement btn = Wait.clickable(driver,selector);
        btn.click();
        
        try {
            Alert alert = Wait.alert(driver);
            alert.accept();
        } catch (TimeoutException e) {
            
        }
        
        return new Ecomm_OutstandingOrderDraftPage(driver);
    }
    
    public Ecomm_ManualEntryPage pressEdit(int row) {
        By selector = By.cssSelector("#content > div.tbl-toggle > div > div.scrollTableContainer.scroll-pane > table > tbody:nth-child(2) > tr:nth-child("+row+") > td:nth-child(1) > a > span");
        WebElement btn = Wait.clickable(driver,selector);
        btn.click();
        
        Alert alert = Wait.alert(driver);
        alert.accept();
        
        return new Ecomm_ManualEntryPage(driver);
    }

    public int findOrder(String orderNo) {
        for (int i = 1; i < 10; i++) {
            By selector = By.cssSelector("#content > div.tbl-toggle > div > div.scrollTableContainer.scroll-pane > table > tbody:nth-child(2) > tr:nth-child("+i+") > td:nth-child(5)");
            
            WebElement cell = Wait.visible(driver,selector);
            
            if (cell.getText().contains(orderNo)) {
                return i;
            }
        }
        return -1;
    }
    
    public String getOrderNo(int row) {
        By selector = By.cssSelector("#content > div.tbl-toggle > div > div.scrollTableContainer.scroll-pane > table > tbody:nth-child(2) > tr:nth-child("+row+") > td:nth-child(7)");
        WebElement cell = Wait.clickable(driver,selector);
        return cell.getText();
    }
    
    public void checkFields() {
        //Wait for all elements to be clickable
        WebElement orderNo = Wait.clickable(driver,orderNoField);
        WebElement custPO = Wait.clickable(driver,custPOField);
        WebElement search = Wait.clickable(driver,searchButton);
        WebElement reset = Wait.clickable(driver,resetButton);
        
        //Assert all elements are displayed
        AssertJUnit.assertTrue("Denied Order Page: Order No. field not displayed as expected",orderNo.isDisplayed());
        AssertJUnit.assertTrue("Denied Order Page: Customer PO field not displayed as expected",custPO.isDisplayed());
        AssertJUnit.assertTrue("Denied Order Page: Search Button not displayed as expected",search.isDisplayed());
        AssertJUnit.assertTrue("Denied Order Page: Reset Button not displayed as expected",reset.isDisplayed());
    }
    
    public void waitForElement() {
        WebElement wait = Wait.clickable(driver,custPOField);
    }
    
}
