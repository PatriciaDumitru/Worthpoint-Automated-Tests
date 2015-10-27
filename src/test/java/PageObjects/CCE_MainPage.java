
package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CCE_MainPage extends WBA_BasePage {
    
    //page element locators
    static By logoLocator = By.cssSelector("#header > a > img");
    static By navBarLocator = By.id("topnav");
    static By mainImageLocator = By.cssSelector("#welcomefrm > img");
    
    //heading locators
    static By navbarFrameLocator = By.cssSelector("#navigation > div");

    
    public CCE_MainPage(WebDriver passedDriver) {
        //Intialise driver
        super(passedDriver);
        
    }
    
    public WebElement getNavBar() {
        //find and return element
        return driver.findElement(navBarLocator);
    }
    
    public WebElement getMainImage() {
        //find and return element
        return driver.findElement(mainImageLocator);
    }
    
}
