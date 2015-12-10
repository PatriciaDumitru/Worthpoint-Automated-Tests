
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

public class Mst_AddShadeCardPage extends WBA_BasePage {
    
    By shadeCardNameField = By.id("ShadeCardShadeCardName");
    By shadeCardCodeField = By.id("ShadeCardShadeCardCode");
    By saveButton = By.cssSelector("#ShadeCardAddForm > div.actions > ul > li:nth-child(1) > input[type=\"submit\"]");
    By cancelButton = By.cssSelector("#ShadeCardAddForm > div.actions > ul > li:nth-child(2) > a");
    
    public Mst_AddShadeCardPage(WebDriver driver) {
        super(driver);
    }
    
    public WebElement getBreadcrumb() {
        return Wait.clickable(driver,DataItems.breadcrumbLocator);
    }
    
    public Mst_AddShadeCardPage setShadeCardName(String item) {
        CommonTask.setInputField(driver, shadeCardNameField, item);
        return new Mst_AddShadeCardPage(driver);
    }
    
    public Mst_AddShadeCardPage setShadeCardCode(String item) {
        CommonTask.setInputField(driver, shadeCardCodeField, item);
        return new Mst_AddShadeCardPage(driver);
    }
    
    public Mst_ShadeCardsPage pressSave() {
        WebElement element = Wait.clickable(driver,saveButton);
        element.click();
        
        return new Mst_ShadeCardsPage(driver);
    }
    
    public void checkFields() {
        WebElement shadeCardName = Wait.clickable(driver,shadeCardNameField);
        WebElement shadeCardCode = Wait.clickable(driver,shadeCardCodeField);
        WebElement save = Wait.clickable(driver,saveButton);
        WebElement cancel = Wait.clickable(driver,cancelButton);
        
        AssertJUnit.assertTrue("Add Shade Card Page: Shade Card Name field not displayed",shadeCardName.isDisplayed());
        AssertJUnit.assertTrue("Add Shade Card Page: Shade Card Code field not displayed",shadeCardCode.isDisplayed());
        AssertJUnit.assertTrue("Add Shade Card Page: Save button not displayed",save.isDisplayed());
        AssertJUnit.assertTrue("Add Shade Card Page: Cancel button not displayed",cancel.isDisplayed());
    }
    
    public void waitForElement() {
        WebElement shadeCardCode = Wait.clickable(driver,shadeCardCodeField);
    }
    
}
