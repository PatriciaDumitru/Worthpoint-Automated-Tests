
package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
        WebDriver waitForFrame = new WebDriverWait(driver,10).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameLocator));
        return this;
    }
    
    public void waitForDownloadCompletion() {
        //switchTo();
        Boolean waitForInvisibility = new WebDriverWait(driver,60).ignoring(NoSuchElementException.class).until(ExpectedConditions.invisibilityOfElementLocated(frameLocator));      
    }
    
}
