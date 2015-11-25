
package PageObjects;

import AutomationFramework.CommonTask;
import AutomationFramework.DataItems;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.AssertJUnit;

public class Mst_AddLengthPage extends WBA_BasePage {
    
    By lengthNameField = By.id("LengthLengthName");
    By saveButton = By.cssSelector("#LengthAddForm > div.actions > ul > li:nth-child(1) > input[type=\"submit\"]");
    By cancelButton = By.cssSelector("#LengthAddForm > div.actions > ul > li:nth-child(2) > a");
    
    public Mst_AddLengthPage(WebDriver driver) {
        super(driver);
    }
    
    public WebElement getBreadcrumb() {
        return new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.visibilityOfElementLocated(DataItems.breadcrumbLocator));
    }
    
    public Mst_AddLengthPage setLengthName(String item) {
        CommonTask.setInputField(driver, lengthNameField, item);
        return new Mst_AddLengthPage(driver);
    }
    
    public Mst_LengthsPage pressSave() {
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(saveButton));
        element.click();
        
        return new Mst_LengthsPage(driver);
    }
    
    public void checkFields() {
        WebElement lengthName = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(lengthNameField));
        WebElement save = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(saveButton));
        WebElement cancel = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(cancelButton));
        
        AssertJUnit.assertTrue("Add Length Page: Length Name field not displayed",lengthName.isDisplayed());
        AssertJUnit.assertTrue("Add Length Page: Save button not displayed",save.isDisplayed());
        AssertJUnit.assertTrue("Add Length Page: Cancel button not displayed",cancel.isDisplayed());
    }
    
    public void waitForElement() {
        WebElement lengthName = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(lengthNameField));
    }
    
}
