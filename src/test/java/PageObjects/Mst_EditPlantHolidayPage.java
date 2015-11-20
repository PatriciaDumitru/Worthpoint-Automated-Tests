
package PageObjects;

import AutomationFramework.CommonTask;
import AutomationFramework.DataItems;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.AssertJUnit;

public class Mst_EditPlantHolidayPage extends WBA_BasePage {
    
    //Locators
    By plantNameField = By.id("PlantsHolidayPlantId");
    By holidayDescField = By.id("PlantsHolidayHolidayDesc");
    By dateFromField = By.id("PlantsHolidayHolidayDateFrom");
    By saveButton = By.cssSelector("#PlantsHolidayEditForm > div.actions > ul > li:nth-child(1) > input[type=\"submit\"]");
    By cancelButton = By.cssSelector("#PlantsHolidayEditForm > div.actions > ul > li:nth-child(2) > a");
    
    public Mst_EditPlantHolidayPage(WebDriver driver) {
        super(driver);
    }
    
    public WebElement getBreadcrumb() {
        return new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.visibilityOfElementLocated(DataItems.breadcrumbLocator));
    }
    
    public Mst_EditPlantHolidayPage setPlantName(String item) throws InterruptedException {
        CommonTask.setDropDownField(driver, plantNameField, item);
        return new Mst_EditPlantHolidayPage(driver);
    }
    
    public Mst_EditPlantHolidayPage setHolidayDescription(String item) throws InterruptedException {
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(holidayDescField));
        element.clear();
        
        CommonTask.setInputField(driver, holidayDescField, item);
        return new Mst_EditPlantHolidayPage(driver);
    }
    
    public Mst_EditPlantHolidayPage setDateFrom(String item) throws InterruptedException {
        CommonTask.setInputField(driver, dateFromField, item);
        return new Mst_EditPlantHolidayPage(driver);
    }
    
    public Mst_PlantHolidaysPage pressSave() {
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(saveButton));
        element.click();
        return new Mst_PlantHolidaysPage(driver);
    }
    
    public void checkFields() {
        WebElement plantName = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(plantNameField));
        WebElement holidayDesc = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(holidayDescField));
        WebElement dateFrom = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(dateFromField));
        WebElement save = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(saveButton));
        WebElement cancel = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(cancelButton));
        
        AssertJUnit.assertTrue("Edit Plant Holiday Page: Plant Name field not displayed",plantName.isDisplayed());
        AssertJUnit.assertTrue("Edit Plant Holiday Page: Holiday Description field not displayed",holidayDesc.isDisplayed());
        AssertJUnit.assertTrue("Edit Plant Holiday Page: Date From field not displayed",dateFrom.isDisplayed());
        AssertJUnit.assertTrue("Edit Plant Holiday Page: Save button not displayed",save.isDisplayed());
        AssertJUnit.assertTrue("Edit Plant Holiday Page: Cancel button not displayed",cancel.isDisplayed());
    }
    
    public void waitForElement() {
        WebElement plantName = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(plantNameField));
    }
    
}
