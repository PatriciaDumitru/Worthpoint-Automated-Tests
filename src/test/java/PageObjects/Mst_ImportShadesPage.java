
package PageObjects;

import AutomationFramework.DataItems;
import static PageObjects.WBA_BasePage.driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.AssertJUnit;

public class Mst_ImportShadesPage extends WBA_BasePage {
    
    By importButton = By.id("ImportImport-file");
    By cancelButton = By.xpath("//*[@id=\"ImportImportForm\"]/div[3]/ul/li[2]/a");
    By choseFileButton = By.id("ImportImport-file");
    
    public Mst_ImportShadesPage(WebDriver driver) {
        super(driver);
    }
    
    public WebElement getBreadcrumb() {
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.visibilityOfElementLocated(DataItems.breadcrumbLocator));
        return element;
    }
    
    public void waitForElement() {
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(importButton));
    }
    
    public void checkFields() {
        WebElement importBtn = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(importButton));
        WebElement cancelBtn = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(cancelButton));
        WebElement choseFileBtn = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(choseFileButton));
        AssertJUnit.assertTrue("Import Shades Page: Chose File button not displayed",choseFileBtn.isDisplayed());
        AssertJUnit.assertTrue("Import Shades Page: Import button not displayed",importBtn.isDisplayed());
        AssertJUnit.assertTrue("Import Shades Page: Cancel button not displayed",cancelBtn.isDisplayed());
    }
    
}
