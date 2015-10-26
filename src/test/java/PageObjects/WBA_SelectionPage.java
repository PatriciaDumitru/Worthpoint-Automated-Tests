
package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WBA_SelectionPage {
    
    WebDriver driver;
    
    //Element locators
    static By mainImageLocator = By.cssSelector("body > div.wrapper > center > img");
    static By cceCircleLocator = By.cssSelector("body > div.wrapper > center > map > area:nth-child(2)");
    static By eCommCircleLocator = By.cssSelector("body > div.wrapper > center > map > area:nth-child(4)");

    public WBA_SelectionPage(WebDriver passedDriver) {
        //initalise driver
        driver = passedDriver;
    }
    
    public WebElement getMainImage() {
        //find and return element
        WebElement waitForImage = new WebDriverWait(driver,5).until(ExpectedConditions.visibilityOfElementLocated(mainImageLocator));
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
    
    public CCE_MainPage pressCce() {
        //Click CCE circle and return new CcePage instance
        WebElement waitForClickable = new WebDriverWait(driver,5).until(ExpectedConditions.elementToBeClickable(cceCircleLocator));
        driver.findElement(cceCircleLocator).click();
        return new CCE_MainPage(driver);
    }
    
    public Ecomm_MainPage pressEcomm() {
        //Click eComm circle and return new EcommPage instance
        WebElement waitForClickable = new WebDriverWait(driver,5).until(ExpectedConditions.elementToBeClickable(eCommCircleLocator));
        driver.findElement(eCommCircleLocator).click();
        return new Ecomm_MainPage(driver);
    }
    
    public void waitForElement() {
        WebElement wait = new WebDriverWait(driver,5).until(ExpectedConditions.visibilityOfElementLocated(mainImageLocator));
    }
    
}
