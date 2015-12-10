
package PageObjects;

import AutomationFramework.CommonTask;
import AutomationFramework.DataItems;
import AutomationFramework.Wait;
import static PageObjects.WBA_BasePage.driver;
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
        return Wait.visible(driver,DataItems.breadcrumbLocator);
        
    }
    
    public Mst_AddOrderTypePage setOrderType(String item) {
        CommonTask.setInputField(driver, orderTypeField, item);
        return new Mst_AddOrderTypePage(driver);
    }
    
    public Mst_OrderTypePage pressSave() {
        WebElement element = Wait.clickable(driver,saveButton);
        element.click();
        
        return new Mst_OrderTypePage(driver);
    }
    
    public Mst_OrderTypePage pressCancel() {
        WebElement element = Wait.clickable(driver,cancelButton);
        element.click();
        
        return new Mst_OrderTypePage(driver);
    }
    
    public void checkFields() {
        WebElement orderType = Wait.clickable(driver,orderTypeField);
        WebElement save = Wait.clickable(driver,saveButton);
        WebElement cancel = Wait.clickable(driver,cancelButton);
        
        AssertJUnit.assertTrue("Add Order Type Page: Order Type field not displayed",orderType.isDisplayed());
        AssertJUnit.assertTrue("Add Order Type Page: Save button not displayed",save.isDisplayed());
        AssertJUnit.assertTrue("Add Order Type Page: Cancel button not displayed",cancel.isDisplayed());
    }
    
    public void waitForElement() {
        WebElement orderType = Wait.clickable(driver,orderTypeField);
    }
    
}
