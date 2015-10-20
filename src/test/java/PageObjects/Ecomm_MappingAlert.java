
package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Ecomm_MappingAlert {
    
    WebDriver driver;
    
    //Locators
    By noButton = By.name("No");
    By yesButton = By.name("Yes");
    By messageBoxTitle = By.className("msgBoxTitle");
    
    public Ecomm_MappingAlert(WebDriver passedDriver) {
        driver = passedDriver;
    }
    
    public WebElement getBoxTitle() {
        return driver.findElement(messageBoxTitle);
    }
    
    public WebElement getNoButton() {
        return driver.findElement(noButton);
    }
    
    public WebElement getYesButton() {
        return driver.findElement(yesButton);
    }
    
    public Ecomm_MappingPage pressYes() {
        //Wait for alert title to have loaded
        boolean waitForTitle = new WebDriverWait(driver,10).until(ExpectedConditions.textToBePresentInElementLocated(messageBoxTitle, "Mapping Fields"));
        //Wait for button to be clickable
        WebElement waitForClickable = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(yesButton));
        //New action to click yes
        Actions clickYes = new Actions(driver);
        clickYes.click(driver.findElement(yesButton)).build().perform();
        
        return new Ecomm_MappingPage(driver);
    }
    
    public Ecomm_MappingPage pressNo() {
        //Wait for alert title to have loaded
        boolean waitForTitle = new WebDriverWait(driver,10).until(ExpectedConditions.textToBePresentInElementLocated(messageBoxTitle, "Mapping Fields"));
        //Wait for button to be clickable
        WebElement waitForClickable = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(noButton));
        //New action to click no
        Actions clickNo = new Actions(driver);
        clickNo.click(driver.findElement(noButton)).build().perform();
        
        return new Ecomm_MappingPage(driver);
    }
    
}