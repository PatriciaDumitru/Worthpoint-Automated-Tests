
package PageObjects;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class DNPrintPage_CCE {
    
    WebDriver driver;
    
    //Locators
    By frameLocator = By.id("TB_iframeContent");
    By printButton = By.cssSelector("#tbl > tbody > tr:nth-child(9) > td:nth-child(2) > a");
    
    public DNPrintPage_CCE(WebDriver passedDriver) {
        driver = passedDriver;
    }
    
    public void switchTo() {
        WebDriver switchToFrame = new WebDriverWait(driver,10).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameLocator));
    }
    
    public DNReprintPage_CCE pressPrint() {
        //Switch to frame

        //Wait for button to be clickable
        WebElement waitForClickable = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(printButton));
        
        driver.findElement(printButton).click();
        
        return new DNReprintPage_CCE(driver);
    }
    
    public DNReprintPage_CCE pressClose() {
               
        Actions action = new Actions(driver);
        action.sendKeys(Keys.ESCAPE).build().perform();

        Alert alert = new WebDriverWait(driver,10).until(ExpectedConditions.alertIsPresent());
        alert.accept();
        
        return new DNReprintPage_CCE(driver);
    }
    
    
}
