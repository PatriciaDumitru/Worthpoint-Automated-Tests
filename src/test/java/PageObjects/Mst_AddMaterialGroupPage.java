
package PageObjects;

import AutomationFramework.CommonTask;
import AutomationFramework.DataItems;
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
        return new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.visibilityOfElementLocated(DataItems.breadcrumbLocator));
    }
    
    public Mst_AddMaterialGroupPage setMaterialGroup(String item) {
        CommonTask.setInputField(driver, materialGroupField, item);
        return new Mst_AddMaterialGroupPage(driver);
    }
    
    public Mst_MaterialGroupsPage pressSave() {
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(saveButton));
        element.click();
        
        return new Mst_MaterialGroupsPage(driver);
    }
    
    public void checkFields() {
        WebElement materialGroup = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(materialGroupField));
        WebElement save = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(saveButton));
        WebElement cancel = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(cancelButton));
        
        AssertJUnit.assertTrue("Add Material Group Page: Material Group field not displayed",materialGroup.isDisplayed());
        AssertJUnit.assertTrue("Add Material Group Page: Save button not displayed",save.isDisplayed());
        AssertJUnit.assertTrue("Add Material Group Page: Cancel button not displayed",cancel.isDisplayed());
    }
    
    public void waitForElement() {
        WebElement materialGroup = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(materialGroupField));
    }
    
}
