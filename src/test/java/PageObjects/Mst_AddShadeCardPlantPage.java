
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

public class Mst_AddShadeCardPlantPage extends WBA_BasePage {
    
    By shadeCardCodeField = By.id("ShadeCardsPlantShadeCardId");
    By plantNameField = By.id("ShadeCardsPlantPlantId");
    By brandField = By.id("s2id_autogen1");
    By saveButton = By.cssSelector("#ShadeCardsPlantAddForm > div.actions > ul > li:nth-child(1) > input[type=\"submit\"]");
    By cancelButton = By.cssSelector("#ShadeCardsPlantAddForm > div.actions > ul > li:nth-child(2) > a");
    
    public Mst_AddShadeCardPlantPage(WebDriver driver) {
        super(driver);
    }
    
    public WebElement getBreadcrumb() {
        return Wait.visible(driver,DataItems.breadcrumbLocator);
    }
    
    public Mst_AddShadeCardPlantPage setShadeCardCode(String item) throws InterruptedException {
        CommonTask.setDropDownField(driver, shadeCardCodeField, item);
        return new Mst_AddShadeCardPlantPage(driver);
    }
    
    public Mst_AddShadeCardPlantPage setPlantName(String item) throws InterruptedException {
        CommonTask.setDropDownField(driver, plantNameField, item);
        return new Mst_AddShadeCardPlantPage(driver);
    }
    
    public Mst_AddShadeCardPlantPage setBrand(String item) throws InterruptedException {
        CommonTask.setChoiceField(driver, brandField, item);
        return new Mst_AddShadeCardPlantPage(driver);
    }
    
    public Mst_ShadeCardPlantsPage pressSave() {
        WebElement element = Wait.clickable(driver,saveButton);
        element.click();
        
        return new Mst_ShadeCardPlantsPage(driver);
    }
    
    public void checkFields() {
        WebElement shadeCardCode = Wait.clickable(driver,shadeCardCodeField);
        WebElement shadeCardName = Wait.clickable(driver,plantNameField);
        WebElement brand = Wait.clickable(driver,brandField);
        WebElement save= Wait.clickable(driver,saveButton);
        WebElement cancel = Wait.clickable(driver,cancelButton);
        
        AssertJUnit.assertTrue("Add Shade Card Plant Page: Shade Card Code field not displayed",shadeCardCode.isDisplayed());
        AssertJUnit.assertTrue("Add Shade Card Plant Page: Shade Card Name field not displayed",shadeCardName.isDisplayed());
        AssertJUnit.assertTrue("Add Shade Card Plant Page: Brand field not displayed",brand.isDisplayed());
        AssertJUnit.assertTrue("Add Shade Card Plant Page: Search button not displayed",save.isDisplayed());
        AssertJUnit.assertTrue("Add Shade Card Plant Page: Cancel button not displayed",cancel.isDisplayed());
    }
    
    public void waitForElement() {
        WebElement element = Wait.clickable(driver,plantNameField);
    }
    
}
