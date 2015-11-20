
package PageObjects;

import AutomationFramework.CommonTask;
import AutomationFramework.DataItems;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.AssertJUnit;

public class Mst_AddBrandPage extends WBA_BasePage {
    
    By brandNameField = By.id("BrandBrandName");
    By saveButton = By.cssSelector("#BrandAddForm > div.actions > ul > li:nth-child(1) > input[type=\"submit\"]");
    By cancelButton = By.cssSelector("#BrandAddForm > div.actions > ul > li:nth-child(2) > a");
    
    public Mst_AddBrandPage(WebDriver driver) {
        super(driver);
    }
    
    public WebElement getBreadcrumb() {
        return new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.visibilityOfElementLocated(DataItems.breadcrumbLocator));
    }
    
    public Mst_EditBrandPage setBrandName(String item) {
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(brandNameField));
        element.clear();
        
        CommonTask.setInputField(driver,brandNameField,item);
        return new Mst_EditBrandPage(driver);
    }
    
    public Mst_BrandsPage pressSave() {
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(saveButton));
        element.click();
        return new Mst_BrandsPage(driver);
    }
    
    public Mst_BrandsPage pressCancel() {
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(cancelButton));
        element.click();
        return new Mst_BrandsPage(driver);
    }
    
    public void waitForElement() {
        WebElement brandName = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(brandNameField));
    }
    
    public void checkFields() {
        WebElement brandName = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(brandNameField));
        WebElement save = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(saveButton));
        WebElement cancel = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(cancelButton));
        
        AssertJUnit.assertTrue("Add Brand page: Brand name field not displayed",brandName.isDisplayed());
        AssertJUnit.assertTrue("Add Brand page: Save button not displayed",save.isDisplayed());
        AssertJUnit.assertTrue("Add Brand page: Cancel button not displayed",cancel.isDisplayed());   
    }
    
}
