
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

public class Mst_EditBasicMaterialPage extends WBA_BasePage {
    
    By brandNameField = By.id("BasicMaterialBrandId");
    By materialGroupField = By.id("BasicMaterialMaterialGroupId");
    By productHierarchyField = By.id("BasicMaterialProductHierarchyId");
    By saveButton = By.cssSelector("#BasicMaterialEditForm > div.actions > ul > li:nth-child(1) > input[type=\"submit\"]");
    By cancelButton = By.cssSelector("#BasicMaterialEditForm > div.actions > ul > li:nth-child(2) > a");
    
    public Mst_EditBasicMaterialPage(WebDriver driver) {
        super(driver);
    }
    
    public WebElement getBreadcrumb() {
        return new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.visibilityOfElementLocated(DataItems.breadcrumbLocator));
    }
    
    public Mst_EditBasicMaterialPage setBrandName(String item) throws InterruptedException {
        CommonTask.setDropDownField(driver, brandNameField, item);
        return new Mst_EditBasicMaterialPage(driver);
    }
    
    public Mst_EditBasicMaterialPage setMaterialGroup(String item) throws InterruptedException {
        CommonTask.setDropDownField(driver, materialGroupField, item);
        return new Mst_EditBasicMaterialPage(driver);
    }
    
    public Mst_EditBasicMaterialPage setProductHierarchy(String item) throws InterruptedException {
        CommonTask.setDropDownField(driver, productHierarchyField, item);
        return new Mst_EditBasicMaterialPage(driver);
    }
    
    public Mst_BasicMaterialsPage pressSave() {
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(saveButton));
        element.click();
        
        return new Mst_BasicMaterialsPage(driver);
    }
    
    public Mst_BasicMaterialsPage pressCancel() {
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(cancelButton));
        element.click();
        
        return new Mst_BasicMaterialsPage(driver);
    }
    
    public void checkFields() {
        WebElement brandName = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(brandNameField));
        WebElement materialGroup = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(materialGroupField));
        WebElement productHierarchy = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(productHierarchyField));
        WebElement save = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(saveButton));
        WebElement cancel = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(cancelButton));
        
        AssertJUnit.assertTrue("Add Basic Materials Page: Brand name field not displayed",brandName.isDisplayed());
        AssertJUnit.assertTrue("Add Basic Materials Page: Material Group field not displayed",materialGroup.isDisplayed());
        AssertJUnit.assertTrue("Add Basic Materials Page: Product Hierarchy field not displayed",productHierarchy.isDisplayed());
        AssertJUnit.assertTrue("Add Basic Materials Page: Save button not displayed",save.isDisplayed());
        AssertJUnit.assertTrue("Add Basic Materials Page: Cancel button not displayed",cancel.isDisplayed());
    }
    
    public void waitForElement() {
        WebElement brandName = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(brandNameField));
    }
    
}
