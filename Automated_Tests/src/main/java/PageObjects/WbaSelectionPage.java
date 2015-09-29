
package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class WbaSelectionPage {
    
    WebDriver driver;
    
    //Element locators
    static By mainImageLocator = By.cssSelector("body > div.wrapper > center > img");
    static By cceCircleLocator = By.cssSelector("body > div.wrapper > center > map > area:nth-child(1)");
    static By eCommCircleLocator = By.cssSelector("body > div.wrapper > center > map > area:nth-child(3)");
    
    public WbaSelectionPage(WebDriver passedDriver) {
        //initalise driver
        driver = passedDriver;
    }
    
    public WebElement getMainImage() {
        //find and return element
        return driver.findElement(mainImageLocator);
    }
    
    public WebElement getCceCircle() {
        //find and return element
        return driver.findElement(cceCircleLocator);
    }
    
    public WebElement getEcommCircle() {
        //find and return element
        return driver.findElement(eCommCircleLocator);
    }
    
    public CcePage pressCce() {
        //new action to press CCE button
        Actions pressCce = new Actions(driver);
        pressCce.click(getCceCircle()).build().perform();
        return new CcePage(driver);
    }
    
    public EcommPage pressEcomm() {
        //new action to click eComm
        Actions clickEcomm = new Actions(driver);
        clickEcomm.click(getEcommCircle()).build().perform();
        return new EcommPage(driver);
    }
    
}
