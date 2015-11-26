
package PageObjects;

import AutomationFramework.CommonTask;
import AutomationFramework.DataItems;
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
        return new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(DataItems.breadcrumbLocator));
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
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(saveButton));
        element.click();
        
        return new Mst_ShadeCardsPage(driver);
    }
    
    public void checkFields() {
        WebElement shadeCardName = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(shadeCardNameField));
        WebElement shadeCardCode = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(shadeCardCodeField));
        WebElement save = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(saveButton));
        WebElement cancel = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(cancelButton));
        
        AssertJUnit.assertTrue("Add Shade Card Page: Shade Card Name field not displayed",shadeCardName.isDisplayed());
        AssertJUnit.assertTrue("Add Shade Card Page: Shade Card Code field not displayed",shadeCardCode.isDisplayed());
        AssertJUnit.assertTrue("Add Shade Card Page: Save button not displayed",save.isDisplayed());
        AssertJUnit.assertTrue("Add Shade Card Page: Cancel button not displayed",cancel.isDisplayed());
    }
    
    public void waitForElement() {
        WebElement shadeCardCode = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(shadeCardCodeField));
    }
    
}
