package PageObjects;

import AutomationFramework.DataItems;
import AutomationFramework.Wait;
import static PageObjects.WBA_BasePage.driver;
import java.awt.AWTException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class Ecomm_UploadOrderPage extends WBA_BasePage {
    
    //Page element locators
    By fileNameFieldLocator = By.id("filename");
    By fileNameOutputLocator = By.id("FileField");
    By realtimeRadioLocator = By.id("bulkuploadprocess1");
    By backendRadioLocator = By.id("bulkuploadprocess2");
    By uploadButtonLocator = By.cssSelector("#txtbox > div.btn_upload > input.btn-submit-upload");
    
    public Ecomm_UploadOrderPage(WebDriver driver) {
        super(driver);
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
    
    public Ecomm_UploadOrderPage setFilePath(String filePath) throws AWTException {
        //Wait for element to be available
        WebElement field = Wait.visible(driver,fileNameOutputLocator);
        //Type filepath
        driver.findElement(fileNameFieldLocator).sendKeys(filePath);

        //Click away to allow update
        By textLocator = By.cssSelector("#txtbox > p");
        driver.findElement(textLocator).click();
        
        return this;
    }
    
    public Ecomm_UploadOrderPage pressRealtime() {
        //Wait for element to be clickable
        WebElement btn = Wait.clickable(driver,realtimeRadioLocator);
        //New action to click button
        Actions clickRealtime = new Actions(driver);
        clickRealtime.click(btn).build().perform();
        
        return this;
    }
    
    public Ecomm_UploadOrderPage pressBackend() {
        //Wait for element to be clickable
        WebElement btn = Wait.clickable(driver,backendRadioLocator);
        //New action to click button
        Actions clickRealtime = new Actions(driver);
        clickRealtime.click(btn).build().perform();
        
        return this;
    }
    
    public Ecomm_MappingAlert pressUpload() {
        //Wait for button to be clickable
        WebElement upload = Wait.clickable(driver,uploadButtonLocator);
        //Click button
        Actions clickUpload = new Actions(driver);
        clickUpload.click(upload).build().perform();
        
        return new Ecomm_MappingAlert(driver);
    }
    
    public void waitForElement() {
        WebElement waitForUploadBtn = Wait.clickable(driver,uploadButtonLocator);
    }
    
}
