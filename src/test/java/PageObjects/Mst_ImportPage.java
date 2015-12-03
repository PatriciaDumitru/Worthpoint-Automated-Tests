
package PageObjects;

import AutomationFramework.DataItems;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Mst_ImportPage extends WBA_BasePage {
    
    //Locators
    By filenameField = By.id("ImportImport-file");
    By uploadButton = By.cssSelector("#ImportImportForm > div.actions > ul > li:nth-child(1) > input[type=\"submit\"]");
    By cancelButton = By.cssSelector("#ImportImportForm > div.actions > ul > li:nth-child(2) > a");
    
    public Mst_ImportPage(WebDriver driver) {
        super(driver);
    }
    
    public WebElement getBreadcrumb() {
        return driver.findElement(DataItems.breadcrumbLocator);
    }
    
    public WebElement getFilenameField() {
        WebElement field = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(filenameField));
        return field;
    }
    
    public WebElement getUploadButton() {
        return driver.findElement(uploadButton);
    }
    
    public WebElement getCancelField() {
        return driver.findElement(cancelButton);
    }
    
    public Mst_CoatsUsersPage pressCancel() {
        WebElement button = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(cancelButton));
        button.click();
        
        return new Mst_CoatsUsersPage(driver);
    }
    
    public void waitForElement() {
        WebElement wait = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(uploadButton));
    }
    
    public void waitForButton() {
        WebElement button = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(filenameField));
    }
    
}
