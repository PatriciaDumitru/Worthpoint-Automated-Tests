
package PageObjects;

import AutomationFramework.TestSuite;
import java.awt.AWTException;
import java.awt.Robot;
import javax.swing.KeyStroke;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class UploadOrderPage extends BasePage {
    
    //Page element locators
    By fileNameFieldLocator = By.id("filename");
    By fileNameOutputLocator = By.id("FileField");
    By realtimeRadioLocator = By.id("bulkuploadprocess1");
    By backendRadioLocator = By.id("bulkuploadprocess2");
    By uploadButtonLocator = By.cssSelector("#txtbox > div.btn_upload > input.btn-submit-upload");
    
    public UploadOrderPage(WebDriver driver) {
        super(driver);
    }
    
    public WebElement getFileNameField() {
        //find and return element
        return driver.findElement(fileNameFieldLocator);
    }
    
    public WebElement getFileNameOutputField() {
        //find and return element
        return driver.findElement(fileNameOutputLocator);
    }
    
    public WebElement getRealtimeRadio() {
        //find and return element
        return driver.findElement(realtimeRadioLocator);
    }
    
    public WebElement getBackendRadio() {
        //find and return element
        return driver.findElement(backendRadioLocator);
    }
    
    public WebElement getUploadButton() {
        //find and return element
        return driver.findElement(uploadButtonLocator);
    }
    
    public UploadOrderPage setFilePath(String filePath) throws AWTException {
        //Wait for element to be available
        WebElement waitForVisible = new WebDriverWait(driver,10).until(ExpectedConditions.visibilityOfElementLocated(fileNameOutputLocator));
        //new action to click field
        Actions clickField = new Actions(driver);
        //clickField.click(driver.findElement(fileNameFieldLocator)).build().perform();
        driver.findElement(fileNameFieldLocator).sendKeys(TestSuite.uploadOrderPath);      
        //wait for txt to appear in field
        //boolean waitForText = new WebDriverWait(driver,10).until(ExpectedConditions.textToBePresentInElementLocated(fileNameOutputLocator, TestSuite.uploadOrderPath));
        
        return this;
    }
    
    public UploadOrderPage pressRealtime() {
        //Wait for element to be clickable
        WebElement waitForClickable = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(realtimeRadioLocator));
        //New action to click button
        Actions clickRealtime = new Actions(driver);
        clickRealtime.click(driver.findElement(realtimeRadioLocator));
        
        return this;
    }
    
    public UploadOrderPage pressBackend() {
        //Wait for element to be clickable
        WebElement waitForClickable = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(backendRadioLocator));
        //New action to click button
        Actions clickRealtime = new Actions(driver);
        clickRealtime.click(driver.findElement(backendRadioLocator)).build().perform();
        
        return this;
    }
    
    public MappingAlert pressUpload() {
        //Wait for button to be clickable
        WebElement waitForClickable = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(uploadButtonLocator));
        //Click button
        Actions clickUpload = new Actions(driver);
        clickUpload.click(driver.findElement(uploadButtonLocator)).build().perform();
        
        return new MappingAlert(driver);
    }
    
}
