
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

public class Mst_EditBusinessPrincipalPage extends WBA_BasePage {
    
    By principalNoField = By.id("BusinessPrincipalBusinessPrincipalNo");
    By principalNameField = By.id("BusinessPrincipalBusinessPrincipalName");
    By lightSource1Field = By.id("BusinessPrincipalLightSource1stId");
    By saveButton = By.cssSelector("#BusinessPrincipalEditForm > div.actions > ul > li:nth-child(1) > input[type=\"submit\"]");
    By cancelButton = By.cssSelector("#BusinessPrincipalEditForm > div.actions > ul > li:nth-child(2) > a");
    
    public Mst_EditBusinessPrincipalPage(WebDriver driver) {
        super(driver);
    }
    
    public WebElement getBreadcrumb() {
        return new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.visibilityOfElementLocated(DataItems.breadcrumbLocator));
    }
    
    public Mst_AddBusinessPrincipalPage setPrincipalNo(String item) {
        CommonTask.setInputField(driver,principalNoField,item);
        return new Mst_AddBusinessPrincipalPage(driver);
    }
    
    public Mst_AddBusinessPrincipalPage setPrincipalName(String item) {
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(principalNameField));
        element.clear();
        
        CommonTask.setInputField(driver,principalNameField,item);
        return new Mst_AddBusinessPrincipalPage(driver);
    }
    
    public Mst_AddBusinessPrincipalPage setLightSource1(String item) throws InterruptedException {
        CommonTask.setDropDownField(driver,lightSource1Field,item);
        return new Mst_AddBusinessPrincipalPage(driver);
    }
    
    public Mst_BusinessPrincipalsPage pressSave() {
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(saveButton));
        element.click();
        
        return new Mst_BusinessPrincipalsPage(driver);
    }
    
    public Mst_BusinessPrincipalsPage pressCancel() {
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(cancelButton));
        element.click();
        
        return new Mst_BusinessPrincipalsPage(driver);
    }
    
    public void checkFields() {
        //Wait for all elements to be clickable
        WebElement princNo = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(principalNoField));
        WebElement princName = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(principalNameField));
        WebElement lightSource1 = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(lightSource1Field));
        WebElement save = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(saveButton));
        WebElement cancel = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(cancelButton));
        
        //Assert all elements are displayed
        AssertJUnit.assertTrue("Add Business Principal Page: Principal No Field not displayed",princNo.isDisplayed());
        AssertJUnit.assertTrue("Add Business Principal Page: Principal Name Field not displayed",princName.isDisplayed());
        AssertJUnit.assertTrue("Add Business Principal Page: 1st Light Source Field not displayed",lightSource1.isDisplayed());
        AssertJUnit.assertTrue("Add Business Principal Page: Save Field not displayed",save.isDisplayed());
        AssertJUnit.assertTrue("Add Business Principal Page: Cancel Field not displayed",cancel.isDisplayed());
    }
    
    public void waitForElement() {
        WebElement lightSource1 = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(lightSource1Field));
    }
    
}
