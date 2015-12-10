
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

public class Mst_AddPlantPage extends WBA_BasePage {
    
    //Locators
    By plantNameField = By.id("PlantPlantName");
    By plantDescField = By.id("PlantDescription");
    By leadTimeP1Field = By.id("PlantCustPlatinum");
    By leadTimeP2Field = By.id("PlantCustGold");
    By leadTimeP3Field = By.id("PlantCustSilver");
    By leadTimeP4Field = By.id("PlantCustBronze");
    By saveButton = By.cssSelector("#PlantAddForm > div.actions > ul > li:nth-child(1) > input[type=\"submit\"]");
    By resetButton = By.cssSelector("#PlantAddForm > div.actions > ul > li:nth-child(2) > a");
    
    public Mst_AddPlantPage(WebDriver driver) {
        super(driver);
    }
    
    public WebElement getBreadcrumb() {
        return Wait.visible(driver,DataItems.breadcrumbLocator);
    }
    
    public Mst_EditPlantPage setPlantName(String item) {
        WebElement element = Wait.clickable(driver,plantNameField);
        element.clear();
        
        CommonTask.setInputField(driver, plantNameField, item);
        return new Mst_EditPlantPage(driver);
    }
    
    public Mst_EditPlantPage setPlantDesc(String item) {
        CommonTask.setInputField(driver, plantDescField, item);
        return new Mst_EditPlantPage(driver);
    }
    
    public Mst_EditPlantPage setLeadTime1(String item) {
        CommonTask.setInputField(driver, leadTimeP1Field, item);
        return new Mst_EditPlantPage(driver);
    }
    
    public Mst_EditPlantPage setLeadTime2(String item) {
        CommonTask.setInputField(driver, leadTimeP2Field, item);
        return new Mst_EditPlantPage(driver);
    }
    
    public Mst_EditPlantPage setLeadTime3(String item) {
        CommonTask.setInputField(driver, leadTimeP3Field, item);
        return new Mst_EditPlantPage(driver);
    }
    
    public Mst_EditPlantPage setLeadTime4(String item) {
        CommonTask.setInputField(driver, leadTimeP4Field, item);
        return new Mst_EditPlantPage(driver);
    }
    
    public Mst_PlantsPage pressSave() {
        WebElement element = Wait.clickable(driver,saveButton);
        element.click();
        
        return new Mst_PlantsPage(driver);
    }

    public void checkFields() {
        WebElement plantName = Wait.clickable(driver,plantNameField);
        WebElement plantDesc = Wait.clickable(driver,plantDescField);
        WebElement platinum = Wait.clickable(driver,leadTimeP1Field);
        WebElement gold = Wait.clickable(driver,leadTimeP2Field);
        WebElement silver = Wait.clickable(driver,leadTimeP3Field);
        WebElement bronze = Wait.clickable(driver,leadTimeP4Field);
        WebElement save = Wait.clickable(driver,saveButton);
        WebElement reset = Wait.clickable(driver,resetButton);
        
        AssertJUnit.assertTrue("Edit Plant Page: Plant Name Field not displayed",plantName.isDisplayed());
        AssertJUnit.assertTrue("Edit Plant Page: Plant Description Field not displayed",plantDesc.isDisplayed());
        AssertJUnit.assertTrue("Edit Plant Page: Lead Time Priority 1 Field not displayed",platinum.isDisplayed());
        AssertJUnit.assertTrue("Edit Plant Page: Lead Time Priority 2 Field not displayed",gold.isDisplayed());
        AssertJUnit.assertTrue("Edit Plant Page: Lead Time Priority 3 Field not displayed",silver.isDisplayed());
        AssertJUnit.assertTrue("Edit Plant Page: Lead Time Priority 4 Field not displayed",bronze.isDisplayed());
        AssertJUnit.assertTrue("Edit Plant Page: Save button not displayed",save.isDisplayed());
        AssertJUnit.assertTrue("Edit Plant Page: Reset button not displayed",reset.isDisplayed());
    }
    
    public void waitForElement() {
        WebElement plantDesc = Wait.clickable(driver,plantDescField);
    }
    
}
