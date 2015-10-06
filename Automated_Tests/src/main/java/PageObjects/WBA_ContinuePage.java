
package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class WBA_ContinuePage {
    
    static WebDriver driver;
    
    //Locators for the page elements
    static By welcomeImageLocator = By.cssSelector("#wrapper > div.logo1 > a > img");
    static By mainImageLocator = By.cssSelector("#wrapper > div:nth-child(4) > a:nth-child(1) > img");
    static By continueImageLocator = By.cssSelector("#wrapper > div:nth-child(4) > a:nth-child(2) > img");
    
    public WBA_ContinuePage(WebDriver passedDriver) {
        //Initialise driver
        driver = passedDriver;
    }
    
    public static WebElement getWelcomeImage() {
        //find and return element
        return driver.findElement(welcomeImageLocator);
    }
    
    public static WebElement getMainImage() {
        //find and return element
        return driver.findElement(mainImageLocator);       
    }
    
    public static WebElement getContinueImage() {
        //find and return element
        return driver.findElement(continueImageLocator);
    }
    
    public WBA_SelectionPage pressContinue() {
        //new action to click continue button
        Actions clickContinue = new Actions(driver);
        clickContinue.click(getContinueImage()).build().perform();
        return new WBA_SelectionPage(driver);
    }
    
    
}
