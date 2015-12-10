
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

public class Mst_AddMaterialGroupPage extends WBA_BasePage {
    
    By materialGroupField = By.id("MaterialGroupMaterialGroup");
    By saveButton = By.cssSelector("#MaterialGroupAddForm > div.actions > ul > li:nth-child(1) > input[type=\"submit\"]");
    By cancelButton = By.cssSelector("#MaterialGroupAddForm > div.actions > ul > li:nth-child(2) > a");
    
    public Mst_AddMaterialGroupPage(WebDriver driver) {
        super(driver);
    }
    
    public WebElement getBreadcrumb() {
        return Wait.visible(driver,DataItems.breadcrumbLocator);
    }
    
    public Mst_AddMaterialGroupPage setMaterialGroup(String item) {
        CommonTask.setInputField(driver, materialGroupField, item);
        return new Mst_AddMaterialGroupPage(driver);
    }
    
    public Mst_MaterialGroupsPage pressSave() {
        WebElement element = Wait.clickable(driver,saveButton);
        element.click();
        
        return new Mst_MaterialGroupsPage(driver);
    }
    
    public void checkFields() {
        WebElement materialGroup = Wait.clickable(driver,materialGroupField);
        WebElement save = Wait.clickable(driver,saveButton);
        WebElement cancel = Wait.clickable(driver,cancelButton);
        
        AssertJUnit.assertTrue("Add Material Group Page: Material Group field not displayed",materialGroup.isDisplayed());
        AssertJUnit.assertTrue("Add Material Group Page: Save button not displayed",save.isDisplayed());
        AssertJUnit.assertTrue("Add Material Group Page: Cancel button not displayed",cancel.isDisplayed());
    }
    
    public void waitForElement() {
        WebElement materialGroup = Wait.clickable(driver,materialGroupField);
    }
    
}
