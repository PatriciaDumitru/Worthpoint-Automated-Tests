
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

public class Mst_EditLightSourcePage extends WBA_BasePage {
    
    By lightSourceField = By.id("LightSourceLightSource");
    By saveButton = By.cssSelector("#LightSourceEditForm > div.actions > ul > li:nth-child(1) > input[type=\"submit\"]");
    By cancelButton = By.cssSelector("#LightSourceEditForm > div.actions > ul > li:nth-child(2) > a");
    
    public Mst_EditLightSourcePage(WebDriver driver) {
        super(driver);
    }
    
    public WebElement getBreadcrumb() {
        return new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.visibilityOfElementLocated(DataItems.breadcrumbLocator));
    }
    
    public Mst_EditLightSourcePage setLightSource(String item) {
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(lightSourceField));
        element.clear();
        
        CommonTask.setInputField(driver, lightSourceField, item);
        return new Mst_EditLightSourcePage(driver);
    }
    
    public Mst_LightSourcesPage pressSave() {
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(saveButton));
        element.click();
        
        return new Mst_LightSourcesPage(driver);
    }
    
    public void checkFields() {
        WebElement lightSource = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(lightSourceField));
        WebElement save = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(saveButton));
        WebElement cancel = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(cancelButton));
        
        AssertJUnit.assertTrue("Add Light Source Page: Hierarchy field not displayed",lightSource.isDisplayed());
        AssertJUnit.assertTrue("Add Light Source Page: Save button not displayed",save.isDisplayed());
        AssertJUnit.assertTrue("Add Light Source Page: Cancel button not displayed",cancel.isDisplayed());     
    }
    
    public void waitForElement() {
        WebElement lightSource = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(lightSourceField));
    }
    
}
