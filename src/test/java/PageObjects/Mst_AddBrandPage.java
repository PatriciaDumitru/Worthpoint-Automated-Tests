
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

public class Mst_AddBrandPage extends WBA_BasePage {
    
    By brandNameField = By.id("BrandBrandName");
    By saveButton = By.cssSelector("#BrandAddForm > div.actions > ul > li:nth-child(1) > input[type=\"submit\"]");
    By cancelButton = By.cssSelector("#BrandAddForm > div.actions > ul > li:nth-child(2) > a");
    
    public Mst_AddBrandPage(WebDriver driver) {
        super(driver);
    }
    
    public WebElement getBreadcrumb() {
        return Wait.visible(driver,DataItems.breadcrumbLocator);
    }
    
    public Mst_EditBrandPage setBrandName(String item) {
        WebElement element = Wait.clickable(driver,brandNameField);
        element.clear();
        
        CommonTask.setInputField(driver,brandNameField,item);
        return new Mst_EditBrandPage(driver);
    }
    
    public Mst_BrandsPage pressSave() {
        WebElement element = Wait.clickable(driver,saveButton);
        element.click();
        return new Mst_BrandsPage(driver);
    }
    
    public Mst_BrandsPage pressCancel() {
        WebElement element = Wait.clickable(driver,cancelButton);
        element.click();
        return new Mst_BrandsPage(driver);
    }
    
    public void waitForElement() {
        WebElement brandName = Wait.clickable(driver,brandNameField);
    }
    
    public void checkFields() {
        WebElement brandName = Wait.clickable(driver,brandNameField);
        WebElement save = Wait.clickable(driver,saveButton);
        WebElement cancel = Wait.clickable(driver,cancelButton);
        
        AssertJUnit.assertTrue("Add Brand page: Brand name field not displayed",brandName.isDisplayed());
        AssertJUnit.assertTrue("Add Brand page: Save button not displayed",save.isDisplayed());
        AssertJUnit.assertTrue("Add Brand page: Cancel button not displayed",cancel.isDisplayed());   
    }
    
}
