
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

public class Mst_AddShadePage extends WBA_BasePage {
    
    //Locators
    By shadeCardField = By.id("ShadeShadeCardId");
    By shadeNameField = By.id("ShadeShadeName");
    By shadeCodeField = By.id("ShadeShadeCode");
    By redValueField = By.id("ShadeColorRatioR");
    By greenValueField = By.id("ShadeColorRatioG");
    By blueValueField = By.id("ShadeColorRatioB");
    By standardTypeField = By.id("ShadeStandardType");
    By typeCodeField = By.id("ShadeTypeCode");
    By saveButton = By.cssSelector("#ShadeAddForm > div.actions > ul > li:nth-child(1) > input[type=\"submit\"]");
    By cancelButton = By.cssSelector("#ShadeAddForm > div.actions > ul > li:nth-child(2) > a");
    By useShadeButton = By.id("ShadeIsShadeInUse");
    By statusActiveBox = By.id("ShadeStatusId1");
    By statusInactiveBox = By.id("ShadeStatusId2");
    
    public Mst_AddShadePage(WebDriver driver) {
        super(driver);
    }
    
    public WebElement getBreadcrumb() {
        WebElement element = Wait.visible(driver,DataItems.breadcrumbLocator);
        return element;
    }
    
    public Mst_AddShadePage setShadeCard(String item) throws InterruptedException {
        CommonTask.setDropDownField(driver,shadeCardField,item);
        return new Mst_AddShadePage(driver);
    }
    
    public Mst_AddShadePage setShadeName(String item) throws InterruptedException {
        CommonTask.setInputField(driver,shadeNameField,item);
        return new Mst_AddShadePage(driver);
    }
    
    public Mst_AddShadePage setShadeCode(String item) throws InterruptedException {
        CommonTask.setInputField(driver,shadeCodeField,item);
        return new Mst_AddShadePage(driver);
    }
    
    public Mst_AddShadePage setRedValue(String item) throws InterruptedException {
        CommonTask.setInputField(driver,redValueField,item);
        return new Mst_AddShadePage(driver);
    }
    
    public Mst_AddShadePage setGreenValue(String item) throws InterruptedException {
        CommonTask.setInputField(driver,greenValueField,item);
        return new Mst_AddShadePage(driver);
    }
    
    public Mst_AddShadePage setBlueValue(String item) throws InterruptedException {
        CommonTask.setInputField(driver,blueValueField,item);
        return new Mst_AddShadePage(driver);
    }
    
    public Mst_AddShadePage setStandardType(String item) throws InterruptedException {
        CommonTask.setInputField(driver,standardTypeField,item);
        return new Mst_AddShadePage(driver);
    }
    
    public Mst_AddShadePage setTypeCode(String item) throws InterruptedException {
        CommonTask.setInputField(driver,typeCodeField,item);
        return new Mst_AddShadePage(driver);
    }
    
    public Mst_ShadesPage pressSave() {
        WebElement element = Wait.clickable(driver,saveButton);
        element.click();
        
        return new Mst_ShadesPage(driver);
    }
    
    public Mst_ShadesPage pressCancel() {
        WebElement element = Wait.clickable(driver,cancelButton);
        element.click();
        
        return new Mst_ShadesPage(driver);
    }
    
    public void checkFields() {
        //Wait for all elements to be clickable
        WebElement shadeCard = Wait.clickable(driver,shadeCardField);
        WebElement shadeName = Wait.clickable(driver,shadeNameField);
        WebElement shadeCode = Wait.clickable(driver,shadeCodeField);
        WebElement redValue = Wait.clickable(driver,redValueField);
        WebElement greenValue = Wait.clickable(driver,greenValueField);
        WebElement blueValue = Wait.clickable(driver,blueValueField);
        WebElement standardType = Wait.clickable(driver,standardTypeField);
        WebElement typeCode = Wait.clickable(driver,typeCodeField);
        WebElement save = Wait.clickable(driver,saveButton);
        WebElement cancel = Wait.clickable(driver,cancelButton);
        WebElement useShade = Wait.clickable(driver,useShadeButton);
        WebElement statusActive = Wait.clickable(driver,statusActiveBox);
        WebElement statusInactive = Wait.clickable(driver,statusInactiveBox);
        
        //Assert all elements are displayed
        AssertJUnit.assertTrue("Add Shade Page: Shade Card field not displayed",shadeCard.isDisplayed());
        AssertJUnit.assertTrue("Add Shade Page: Shade Name field not displayed",shadeName.isDisplayed());
        AssertJUnit.assertTrue("Add Shade Page: Shade Code field not displayed",shadeCode.isDisplayed());
        AssertJUnit.assertTrue("Add Shade Page: Red Value field not displayed",redValue.isDisplayed());
        AssertJUnit.assertTrue("Add Shade Page: Green Value field not displayed",greenValue.isDisplayed());
        AssertJUnit.assertTrue("Add Shade Page: Blue Value field not displayed",blueValue.isDisplayed());
        AssertJUnit.assertTrue("Add Shade Page: Standard Type field not displayed",standardType.isDisplayed());
        AssertJUnit.assertTrue("Add Shade Page: Type Code field not displayed",typeCode.isDisplayed());
        AssertJUnit.assertTrue("Add Shade Page: Use Shade field not displayed",useShade.isDisplayed());
        AssertJUnit.assertTrue("Add Shade Page: Status Active checkbox not displayed",statusActive.isDisplayed());
        AssertJUnit.assertTrue("Add Shade Page: Status Inactive checkbox not displayed",statusInactive.isDisplayed());
        AssertJUnit.assertTrue("Add Shade Page: Save field not displayed",save.isDisplayed());
        AssertJUnit.assertTrue("Add Shade Page: Cancel field not displayed",cancel.isDisplayed());

        
    }
    
    public void waitForElement() {
        WebElement wait = Wait.clickable(driver,typeCodeField);
    }
    
}
