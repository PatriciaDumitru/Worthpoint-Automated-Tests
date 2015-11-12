
package PageObjects;

import AutomationFramework.DataItems;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class CCE_ExportDownloadPage {
    
    WebDriver driver;
    
    //Locators
    By frameLocator = By.id("TB_iframeContent");
    
    public CCE_ExportDownloadPage(WebDriver passedDriver) {
        driver = passedDriver;
    }
    
    public CCE_ExportDownloadPage switchTo() {
        WebDriver waitForFrame = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameLocator));
        return this;
    }
    
    public void waitForElement() {
        
    }
    
    public void waitForDownloadCompletion() {
        //switchTo();
        Boolean waitForInvisibility = new WebDriverWait(driver,DataItems.downloadWait).ignoring(NoSuchElementException.class).until(ExpectedConditions.invisibilityOfElementLocated(frameLocator));      
    }
    
    public void closeView() {
        Actions action = new Actions(driver);
        action.sendKeys(Keys.ESCAPE);
        
        Alert alert = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.alertIsPresent());
        alert.accept();

    }
    
    public void waitForInvisibility() {
        
    }
    
}
