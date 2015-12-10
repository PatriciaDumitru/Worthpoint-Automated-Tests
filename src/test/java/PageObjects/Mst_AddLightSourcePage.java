
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


public class Mst_AddLightSourcePage extends WBA_BasePage {
    
    By lightSourceField = By.id("LightSourceLightSource");
    By saveButton = By.cssSelector("#LightSourceAddForm > div.actions > ul > li:nth-child(1) > input[type=\"submit\"]");
    By cancelButton = By.cssSelector("#LightSourceAddForm > div.actions > ul > li:nth-child(2) > a");
    
    public Mst_AddLightSourcePage(WebDriver driver) {
        super(driver);
    }
    
    public WebElement getBreadcrumb() {
        return Wait.visible(driver,DataItems.breadcrumbLocator);
    }
    
    public Mst_AddLightSourcePage setLightSource(String item) {
        CommonTask.setInputField(driver, lightSourceField, item);
        return new Mst_AddLightSourcePage(driver);
    }
    
    public Mst_LightSourcesPage pressSave() {
        WebElement element = Wait.clickable(driver,saveButton);
        element.click();
        
        return new Mst_LightSourcesPage(driver);
    }
    
    public void checkFields() {
        WebElement lightSource = Wait.clickable(driver,lightSourceField);
        WebElement save = Wait.clickable(driver,saveButton);
        WebElement cancel = Wait.clickable(driver,cancelButton);
        
        AssertJUnit.assertTrue("Add Light Source Page: Hierarchy field not displayed",lightSource.isDisplayed());
        AssertJUnit.assertTrue("Add Light Source Page: Save button not displayed",save.isDisplayed());
        AssertJUnit.assertTrue("Add Light Source Page: Cancel button not displayed",cancel.isDisplayed());     
    }
    
    public void waitForElement() {
        WebElement lightSource = Wait.clickable(driver,lightSourceField);
    }
    
}
