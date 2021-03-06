
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

public class Mst_AddPlantHolidayPage extends WBA_BasePage {
    
    //Locators
    By plantNameField = By.id("PlantsHolidayPlantId");
    By holidayDescField = By.id("PlantsHolidayHolidayDesc");
    By dateFromField = By.id("PlantsHolidayHolidayDateFrom");
    By saveButton = By.cssSelector("#PlantsHolidayAddForm > div.actions > ul > li:nth-child(1) > input[type=\"submit\"]");
    By cancelButton = By.cssSelector("#PlantsHolidayAddForm > div.actions > ul > li:nth-child(2) > a");
    
    public Mst_AddPlantHolidayPage(WebDriver driver) {
        super(driver);
    }
    
    public WebElement getBreadcrumb() {
        return Wait.visible(driver,DataItems.breadcrumbLocator);
    }
    
    public Mst_AddPlantHolidayPage setPlantName(String item) throws InterruptedException {
        CommonTask.setDropDownField(driver, plantNameField, item);
        return new Mst_AddPlantHolidayPage(driver);
    }
    
    public Mst_AddPlantHolidayPage setHolidayDescription(String item) throws InterruptedException {
        CommonTask.setInputField(driver, holidayDescField, item);
        return new Mst_AddPlantHolidayPage(driver);
    }
    
    public Mst_AddPlantHolidayPage setDateFrom(String item) throws InterruptedException {
        CommonTask.setInputField(driver, dateFromField, item);
        return new Mst_AddPlantHolidayPage(driver);
    }
    
    public Mst_PlantHolidaysPage pressSave() {
        WebElement element = Wait.clickable(driver,saveButton);
        element.click();
        return new Mst_PlantHolidaysPage(driver);
    }
    
    public void checkFields() {
        WebElement plantName = Wait.clickable(driver,plantNameField);
        WebElement holidayDesc = Wait.clickable(driver,holidayDescField);
        WebElement dateFrom = Wait.clickable(driver,dateFromField);
        WebElement save = Wait.clickable(driver,saveButton);
        WebElement cancel = Wait.clickable(driver,cancelButton);
        
        AssertJUnit.assertTrue("Add Plant Holiday Page: Plant Name field not displayed",plantName.isDisplayed());
        AssertJUnit.assertTrue("Add Plant Holiday Page: Holiday Description field not displayed",holidayDesc.isDisplayed());
        AssertJUnit.assertTrue("Add Plant Holiday Page: Date From field not displayed",dateFrom.isDisplayed());
        AssertJUnit.assertTrue("Add Plant Holiday Page: Save button not displayed",save.isDisplayed());
        AssertJUnit.assertTrue("Add Plant Holiday Page: Cancel button not displayed",cancel.isDisplayed());
    }
    
    public void waitForElement() {
        WebElement plantName = Wait.clickable(driver,plantNameField);
    }
    
}
