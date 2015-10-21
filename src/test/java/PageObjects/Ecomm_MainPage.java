
package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Ecomm_MainPage extends WBA_BasePage {
    
    //Page element locators
    static By contentImageLocator = By.cssSelector("#header > div:nth-child(6) > img");
    static By navBarLocator = By.id("topnav");
    static By mainImageLocator = By.cssSelector("#welcomefrm1 > center > img");
    
    public Ecomm_MainPage(WebDriver passedDriver) {
        super(passedDriver);
    }
    
    public static WebElement getContentImage() {
        //find and return element
        return driver.findElement(contentImageLocator);
    }
    
    public static WebElement getNavBar() {
        //find and return element
        return driver.findElement(navBarLocator);
    }
    
    public static WebElement getMainImage() {
        //find and return element
        return driver.findElement(mainImageLocator);
    }
    
    public static void hoverNavBar(String tabTitle) {
        By tabLocator = By.xpath("//a[contains(text(),'" + tabTitle+ "')]");
        //new action to hover over nav bar item
        Actions hoverItem = new Actions(driver);
        hoverItem.moveToElement(driver.findElement(tabLocator)).build().perform();     
    }
    
    
}
