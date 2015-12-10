
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

public class Mst_AddHubPage extends WBA_BasePage {
    
    By hubNameField = By.id("HubHubName");
    By hubDescField = By.id("HubDescription");
    By saveButton = By.cssSelector("#HubAddForm > div.actions > ul > li:nth-child(1) > input[type=\"submit\"]");
    By cancelButton = By.cssSelector("#HubAddForm > div.actions > ul > li:nth-child(2) > a");
    
    public Mst_AddHubPage(WebDriver driver) {
        super(driver);
    }
    
    public WebElement getBreadcrumb() {
        return Wait.visible(driver,DataItems.breadcrumbLocator);
    }
    
    public Mst_AddHubPage setHubName(String item) {
        CommonTask.setInputField(driver, hubNameField, item);
        return new Mst_AddHubPage(driver);
    }
    
    public Mst_AddHubPage setHubDescription(String item) {
        CommonTask.setInputField(driver, hubDescField, item);
        return new Mst_AddHubPage(driver);
    }
    
    public Mst_HubsPage pressSave() {
        WebElement element = Wait.clickable(driver,saveButton);
        element.click();
        
        return new Mst_HubsPage(driver);
    }
    
    public Mst_HubsPage pressCancel() {
        WebElement element = Wait.clickable(driver,cancelButton);
        element.click();
        
        return new Mst_HubsPage(driver);
    }
    
    public void checkFields() {
        WebElement hubName = Wait.clickable(driver,hubNameField);
        WebElement hubDesc = Wait.clickable(driver,hubDescField);
        WebElement save = Wait.clickable(driver,saveButton);
        WebElement cancel = Wait.clickable(driver,cancelButton);
        
        AssertJUnit.assertTrue("Add Hub Page: Hub name field not displayed",hubName.isDisplayed());
        AssertJUnit.assertTrue("Add Hub Page: Hub description field not displayed",hubDesc.isDisplayed());
        AssertJUnit.assertTrue("Add Hub Page: Save button not displayed",save.isDisplayed());
        AssertJUnit.assertTrue("Add Hub Page: Cancel button not displayed",cancel.isDisplayed());
    }
    
    public void waitForElement() {
        WebElement hubDesc = Wait.clickable(driver,hubDescField);
    }
    
}
