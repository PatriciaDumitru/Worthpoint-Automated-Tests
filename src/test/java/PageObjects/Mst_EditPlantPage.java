
package PageObjects;

import AutomationFramework.CommonTask;
import AutomationFramework.DataItems;
import AutomationFramework.Wait;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.AssertJUnit;

public class Mst_EditPlantPage extends WBA_BasePage {
    
    //Locators
    By plantNameField = By.id("PlantPlantName");
    By plantDescField = By.id("PlantDescription");
    By leadTimeP1Field = By.id("PlantCustPlatinum");
    By leadTimeP2Field = By.id("PlantCustGold");
    By leadTimeP3Field = By.id("PlantCustSilver");
    By leadTimeP4Field = By.id("PlantCustBronze");
    By weekendField = By.id("s2id_PlantWeekend");
    By saveButton = By.cssSelector("#PlantEditForm > div.actions > ul > li:nth-child(1) > input[type=\"submit\"]");
    By resetButton = By.cssSelector("#PlantEditForm > div.actions > ul > li:nth-child(2) > a");
    
    public Mst_EditPlantPage(WebDriver driver) {
        super(driver);
    }
    
    public WebElement getBreadcrumb() {
        return new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.visibilityOfElementLocated(DataItems.breadcrumbLocator));
    }
    
    public Mst_EditPlantPage setPlantName(String item) {
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(plantNameField));
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
        
    public Mst_PlantsPage addDay(String day) {
        CommonTask.setChoiceField(driver, weekendField, day);
        return new Mst_PlantsPage(driver);
    }
    
    public Mst_PlantsPage pressSave() {
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(saveButton));
        element.click();
        
        return new Mst_PlantsPage(driver);
    }

    public void checkFields() {
        WebElement plantName = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(plantNameField));
        WebElement plantDesc = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(plantDescField));
        WebElement platinum = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(leadTimeP1Field));
        WebElement gold = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(leadTimeP2Field));
        WebElement silver = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(leadTimeP3Field));
        WebElement bronze = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(leadTimeP4Field));
        WebElement save = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(saveButton));
        WebElement reset = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(resetButton));
        
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
        WebElement plantDesc = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(plantDescField));
    }

    //For workaround
    /*public void clickOnX(){
        By ex = By.xpath("/*//*[@id=\"s2id_PlantWeekend\"]/ul/li[1]/a");
        WebElement ex2 = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(ex));
        driver.findElement(ex).click();
    }*/
}
