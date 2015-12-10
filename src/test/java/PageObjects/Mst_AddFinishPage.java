
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

public class Mst_AddFinishPage extends WBA_BasePage {
    
    By finishNameField = By.id("FinishFinishName");
    By saveButton = By.cssSelector("#FinishAddForm > div.actions > ul > li:nth-child(1) > input[type=\"submit\"]");
    By cancelButton = By.cssSelector("#FinishAddForm > div.actions > ul > li:nth-child(2) > a");
    
    public Mst_AddFinishPage(WebDriver driver) {
        super(driver);
    }
    
    public WebElement getBreadcrumb() {
        return Wait.visible(driver,DataItems.breadcrumbLocator);
    }
    
    public Mst_AddFinishPage setFinishName(String item) {
        CommonTask.setInputField(driver, finishNameField, item);
        return new Mst_AddFinishPage(driver);
    }
    
    public Mst_FinishesPage pressSave() {
        WebElement element = Wait.clickable(driver,saveButton);
        element.click();
        
        return new Mst_FinishesPage(driver);
    }
    
    public Mst_FinishesPage pressCancel() {
        WebElement element = Wait.clickable(driver,cancelButton);
        element.click();
        
        return new Mst_FinishesPage(driver);
    }
    
    public void checkFields() {
        WebElement finishName = Wait.clickable(driver,finishNameField);
        WebElement save = Wait.clickable(driver,saveButton);
        WebElement cancel = Wait.clickable(driver,cancelButton);
        
        AssertJUnit.assertTrue("Add Finish Page: Finish Name field not displayed",finishName.isDisplayed());
        AssertJUnit.assertTrue("Add Finish Page: Save button not displayed",save.isDisplayed());
        AssertJUnit.assertTrue("Add Finish Page: Cancel button not displayed",cancel.isDisplayed());
    }
    
    public void waitForElement() {
        WebElement finishName = Wait.clickable(driver,finishNameField);
    }
    
}
