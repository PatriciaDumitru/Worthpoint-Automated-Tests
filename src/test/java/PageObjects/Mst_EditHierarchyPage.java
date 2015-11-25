
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

public class Mst_EditHierarchyPage extends WBA_BasePage {
    
    By hierarchyField = By.id("ProductHierarchyProductHierarchy");
    By saveButton = By.cssSelector("#ProductHierarchyEditForm > div.actions > ul > li:nth-child(1) > input[type=\"submit\"]");
    By cancelButton = By.cssSelector("#ProductHierarchyEditForm > div.actions > ul > li:nth-child(2) > a");
    
    public Mst_EditHierarchyPage(WebDriver driver) {
        super(driver);
    }
    
    public WebElement getBreadcrumb() {
        return new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.visibilityOfElementLocated(DataItems.breadcrumbLocator));
    }
    
    public Mst_EditHierarchyPage setHierarchy(String item) {
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(hierarchyField));
        element.clear();
        
        CommonTask.setInputField(driver, hierarchyField, item);
        return new Mst_EditHierarchyPage(driver);
    }
    
    public Mst_HierarchyPage pressSave() {
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(saveButton));
        element.click();
        
        return new Mst_HierarchyPage(driver);
    }
    
    public void checkFields() {
        WebElement hierarchy = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(hierarchyField));
        WebElement save = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(saveButton));
        WebElement cancel = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(cancelButton));
        
        AssertJUnit.assertTrue("Edit Hierarchy Page: Hierarchy field not displayed",hierarchy.isDisplayed());
        AssertJUnit.assertTrue("Edit Hierarchy Page: Save button not displayed",save.isDisplayed());
        AssertJUnit.assertTrue("Edit Hierarchy Page: Cancel button not displayed",cancel.isDisplayed());     
    }
    
    public void waitForElement() {
        WebElement hierarchy = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(hierarchyField));
    }
    
}
