
package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class WbaSelectionPage {
    
    WebDriver driver;
    
    //Element locators
    static By mainImageLocator = By.cssSelector("body > div.wrapper > center > img");
    static By cceCircleLocator = By.cssSelector("body > div.wrapper > center > map > area:nth-child(2)");
    static By eCommCircleLocator = By.cssSelector("body > div.wrapper > center > map > area:nth-child(4)");

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
        //Click CCE circle and return new CcePage instance
        driver.findElement(cceCircleLocator).click();
        return new CcePage(driver);
    }
    
    public EcommPage pressEcomm() {
        //Click eComm circle and return new EcommPage instance
        driver.findElement(eCommCircleLocator).click();
        return new EcommPage(driver);
    }
    
}
