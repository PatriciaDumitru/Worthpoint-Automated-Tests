
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

public class Mst_EditHubPage extends WBA_BasePage {
    
    By hubNameField = By.id("HubHubName");
    By hubDescField = By.id("HubDescription");
    By saveButton = By.cssSelector("#HubEditForm > div.actions > ul > li:nth-child(1) > input[type=\"submit\"]");
    By cancelButton = By.cssSelector("#HubEditForm > div.actions > ul > li:nth-child(2) > a");
    
    public Mst_EditHubPage(WebDriver driver) {
        super(driver);
    }
    
    public WebElement getBreadcrumb() {
        return new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.visibilityOfElementLocated(DataItems.breadcrumbLocator));
    }
    
    public Mst_EditHubPage setHubName(String item) {
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(hubNameField));
        element.clear();
        
        CommonTask.setInputField(driver, hubNameField, item);
        return new Mst_EditHubPage(driver);
    }
    
    public Mst_EditHubPage setHubDescription(String item) {
        CommonTask.setInputField(driver, hubDescField, item);
        return new Mst_EditHubPage(driver);
    }
    
    public Mst_HubsPage pressSave() {
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(saveButton));
        element.click();
        
        return new Mst_HubsPage(driver);
    }
    
    public Mst_HubsPage pressCancel() {
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(cancelButton));
        element.click();
        
        return new Mst_HubsPage(driver);
    }
    
    public void checkFields() {
        WebElement hubName = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(hubNameField));
        WebElement hubDesc = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(hubDescField));
        WebElement save = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(saveButton));
        WebElement cancel = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(cancelButton));
        
        AssertJUnit.assertTrue("Edit Hub Page: Hub name field not displayed",hubName.isDisplayed());
        AssertJUnit.assertTrue("Edit Hub Page: Hub description field not displayed",hubDesc.isDisplayed());
        AssertJUnit.assertTrue("Edit Hub Page: Save button not displayed",save.isDisplayed());
        AssertJUnit.assertTrue("Edit Hub Page: Cancel button not displayed",cancel.isDisplayed());
    }
    
    public void waitForElement() {
        WebElement hubDesc = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(hubDescField));
    }
    
}
