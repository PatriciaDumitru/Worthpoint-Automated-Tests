
package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class Ecomm_ExportDownloadPage {
    
    WebDriver driver;
    
    //Locators
    By frameLocator = By.id("TB_iframeContent");
    By yesButton = By.cssSelector("#popup_content > div > table:nth-child(2) > tbody > tr:nth-child(1) > td > div.actions > ul > li:nth-child(1)");
    By noButton = By.cssSelector("#popup_content > div > table:nth-child(2) > tbody > tr:nth-child(1) > td > div.actions > ul > li:nth-child(2)");
    
    public Ecomm_ExportDownloadPage(WebDriver passedDriver) {
        driver = passedDriver;
    }
    
    public Ecomm_ExportDownloadPage switchTo() {
        WebDriver waitForFrame = new WebDriverWait(driver,8).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameLocator));
        return this;
    }
    
    public void waitForDownloadCompletion() {
        //switchTo();
        Boolean waitForInvisibility = new WebDriverWait(driver,60).ignoring(NoSuchElementException.class).until(ExpectedConditions.invisibilityOfElementLocated(frameLocator));      
    }
    
    public void pressYes() {
        //For My Report exports, "Yes" will send the file to e-mail
        switchTo();
        WebElement wait = new WebDriverWait(driver,5).until(ExpectedConditions.elementToBeClickable(yesButton));
        driver.findElement(yesButton).click();
    }
    
    public void pressNo() {
        //For My Report exports, "Yes" will send the file to e-mail
        switchTo();
        WebElement wait = new WebDriverWait(driver,5).until(ExpectedConditions.elementToBeClickable(noButton));
        driver.findElement(noButton).click();
    }
}
