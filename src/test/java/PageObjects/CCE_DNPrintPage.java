
package PageObjects;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class CCE_DNPrintPage {
    
    WebDriver driver;
    
    //Locators
    By frameLocator = By.id("TB_iframeContent");
    By printButton = By.cssSelector("#tbl > tbody > tr:nth-child(9) > td:nth-child(2) > a");
    
    public CCE_DNPrintPage(WebDriver passedDriver) {
        driver = passedDriver;
    }
    
    public void switchTo() {
        WebDriver switchToFrame = new WebDriverWait(driver,5).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameLocator));
    }
    
    public CCE_DNReprintPage pressPrint() {
        //Switch to frame

        //Wait for button to be clickable
        WebElement waitForClickable = new WebDriverWait(driver,5).until(ExpectedConditions.elementToBeClickable(printButton));
        
        driver.findElement(printButton).click();
        
        return new CCE_DNReprintPage(driver);
    }
    
    public CCE_DNReprintPage pressClose() {
               
        Actions action = new Actions(driver);
        action.sendKeys(Keys.ESCAPE).build().perform();

        Alert alert = new WebDriverWait(driver,5).until(ExpectedConditions.alertIsPresent());
        alert.accept();
        
        return new CCE_DNReprintPage(driver);
    }
    
    
}
