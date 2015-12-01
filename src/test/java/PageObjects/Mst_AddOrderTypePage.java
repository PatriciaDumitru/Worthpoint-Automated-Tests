
package PageObjects;

import AutomationFramework.CommonTask;
import AutomationFramework.DataItems;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.AssertJUnit;

public class Mst_AddOrderTypePage extends WBA_BasePage {
    
    By orderTypeField = By.id("TypeOrderOrderTypeName");
    By saveButton = By.cssSelector("#TypeOrderAddForm > div.actions > ul > li:nth-child(1) > input[type=\"submit\"]");
    By cancelButton = By.cssSelector("#TypeOrderAddForm > div.actions > ul > li:nth-child(2) > a");
    
    public Mst_AddOrderTypePage(WebDriver driver) {
        super(driver);
    }
    
    public WebElement getBreadcrumb() {
        return new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.visibilityOfElementLocated(DataItems.breadcrumbLocator));
        
    }
    
    public Mst_AddOrderTypePage setOrderType(String item) {
        CommonTask.setInputField(driver, orderTypeField, item);
        return new Mst_AddOrderTypePage(driver);
    }
    
    public Mst_OrderTypePage pressSave() {
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(saveButton));
        element.click();
        
        return new Mst_OrderTypePage(driver);
    }
    
    public Mst_OrderTypePage pressCancel() {
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(cancelButton));
        element.click();
        
        return new Mst_OrderTypePage(driver);
    }
    
    public void checkFields() {
        WebElement orderType = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(orderTypeField));
        WebElement save = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(saveButton));
        WebElement cancel = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(cancelButton));
        
        AssertJUnit.assertTrue("Add Order Type Page: Order Type field not displayed",orderType.isDisplayed());
        AssertJUnit.assertTrue("Add Order Type Page: Save button not displayed",save.isDisplayed());
        AssertJUnit.assertTrue("Add Order Type Page: Cancel button not displayed",cancel.isDisplayed());
    }
    
    public void waitForElement() {
        WebElement orderType = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(orderTypeField));
    }
    
}
