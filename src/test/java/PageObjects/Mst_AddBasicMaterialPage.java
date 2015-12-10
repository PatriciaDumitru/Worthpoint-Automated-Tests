
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

public class Mst_AddBasicMaterialPage extends WBA_BasePage {
    
    By brandNameField = By.id("BasicMaterialBrandId");
    By materialGroupField = By.id("BasicMaterialMaterialGroupId");
    By productHierarchyField = By.id("BasicMaterialProductHierarchyId");
    By saveButton = By.cssSelector("#BasicMaterialAddForm > div.actions > ul > li:nth-child(1) > input[type=\"submit\"]");
    By cancelButton = By.cssSelector("#BasicMaterialAddForm > div.actions > ul > li:nth-child(2) > a");
    
    public Mst_AddBasicMaterialPage(WebDriver driver) {
        super(driver);
    }
    
    public WebElement getBreadcrumb() {
        return Wait.visible(driver,DataItems.breadcrumbLocator);
    }
    
    public Mst_AddBasicMaterialPage setBrandName(String item) throws InterruptedException {
        CommonTask.setDropDownField(driver, brandNameField, item);
        return new Mst_AddBasicMaterialPage(driver);
    }
    
    public Mst_AddBasicMaterialPage setMaterialGroup(String item) throws InterruptedException {
        CommonTask.setDropDownField(driver, materialGroupField, item);
        return new Mst_AddBasicMaterialPage(driver);
    }
    
    public Mst_AddBasicMaterialPage setProductHierarchy(String item) throws InterruptedException {
        CommonTask.setDropDownField(driver, productHierarchyField, item);
        return new Mst_AddBasicMaterialPage(driver);
    }
    
    public Mst_BasicMaterialsPage pressSave() {
        WebElement element = Wait.clickable(driver,saveButton);
        element.click();
        
        return new Mst_BasicMaterialsPage(driver);
    }
    
    public Mst_BasicMaterialsPage pressCancel() {
        WebElement element = Wait.clickable(driver,cancelButton);
        element.click();
        
        return new Mst_BasicMaterialsPage(driver);
    }
    
    public void checkFields() {
        WebElement brandName = Wait.clickable(driver,brandNameField);
        WebElement materialGroup = Wait.clickable(driver,materialGroupField);
        WebElement productHierarchy = Wait.clickable(driver,productHierarchyField);
        WebElement save = Wait.clickable(driver,saveButton);
        WebElement cancel = Wait.clickable(driver,cancelButton);
        
        AssertJUnit.assertTrue("Add Basic Materials Page: Brand name field not displayed",brandName.isDisplayed());
        AssertJUnit.assertTrue("Add Basic Materials Page: Material Group field not displayed",materialGroup.isDisplayed());
        AssertJUnit.assertTrue("Add Basic Materials Page: Product Hierarchy field not displayed",productHierarchy.isDisplayed());
        AssertJUnit.assertTrue("Add Basic Materials Page: Save button not displayed",save.isDisplayed());
        AssertJUnit.assertTrue("Add Basic Materials Page: Cancel button not displayed",cancel.isDisplayed());
    }
    
    public void waitForElement() {
        WebElement brandName = Wait.clickable(driver,brandNameField);
    }
    
}
