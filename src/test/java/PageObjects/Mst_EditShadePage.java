
package PageObjects;

import AutomationFramework.CommonTask;
import AutomationFramework.DataItems;
import static PageObjects.WBA_BasePage.driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.AssertJUnit;

public class Mst_EditShadePage extends WBA_BasePage {
    
    //Locators
    By shadeCardField = By.id("ShadeShadeCardId");
    By shadeNameField = By.id("ShadeShadeName");
    By shadeCodeField = By.id("ShadeShadeCode");
    By redValueField = By.id("ShadeColorRatioR");
    By greenValueField = By.id("ShadeColorRatioG");
    By blueValueField = By.id("ShadeColorRatioB");
    By standardTypeField = By.id("ShadeStandardType");
    By typeCodeField = By.id("ShadeTypeCode");
    By saveButton = By.cssSelector("#ShadeEditForm > div.actions > ul > li:nth-child(1) > input[type=\"submit\"]");
    By cancelButton = By.cssSelector("#ShadeEditForm > div.actions > ul > li:nth-child(2) > a");
    
    public Mst_EditShadePage(WebDriver driver) {
        super(driver);
    }
    
    public WebElement getBreadcrumb() {
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.visibilityOfElementLocated(DataItems.breadcrumbLocator));
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
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(shadeCodeField));
        element.clear();
        
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
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(saveButton));
        element.click();
        
        return new Mst_ShadesPage(driver);
    }
    
    public Mst_ShadesPage pressCancel() {
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(cancelButton));
        element.click();
        
        return new Mst_ShadesPage(driver);
    }
    
    public void checkFields() {
        //Wait for all elements to be clickable
        WebElement shadeCard = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(shadeCardField));
        WebElement shadeName = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(shadeNameField));
        WebElement shadeCode = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(shadeCodeField));
        WebElement redValue = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(redValueField));
        WebElement greenValue = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(greenValueField));
        WebElement blueValue = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(blueValueField));
        WebElement standardType = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(standardTypeField));
        WebElement typeCode = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(typeCodeField));
        WebElement save = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(saveButton));
        WebElement cancel = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(cancelButton));
        
        //Assert all elements are displayed
        AssertJUnit.assertTrue("Add Shade Page: Shade Card field not displayed",shadeCard.isDisplayed());
        AssertJUnit.assertTrue("Add Shade Page: Shade Name field not displayed",shadeName.isDisplayed());
        AssertJUnit.assertTrue("Add Shade Page: Shade Code field not displayed",shadeCode.isDisplayed());
        AssertJUnit.assertTrue("Add Shade Page: Red Value field not displayed",redValue.isDisplayed());
        AssertJUnit.assertTrue("Add Shade Page: Green Value field not displayed",greenValue.isDisplayed());
        AssertJUnit.assertTrue("Add Shade Page: Blue Value field not displayed",blueValue.isDisplayed());
        AssertJUnit.assertTrue("Add Shade Page: Standard Type field not displayed",standardType.isDisplayed());
        AssertJUnit.assertTrue("Add Shade Page: Type Code field not displayed",typeCode.isDisplayed());
        AssertJUnit.assertTrue("Add Shade Page: Save field not displayed",save.isDisplayed());
        AssertJUnit.assertTrue("Add Shade Page: Cancel field not displayed",cancel.isDisplayed());
        
    }
    
    public void waitForElement() {
        WebElement wait = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(typeCodeField));
    }    
}
