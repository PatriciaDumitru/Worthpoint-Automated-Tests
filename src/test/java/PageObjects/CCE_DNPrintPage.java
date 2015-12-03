
package PageObjects;

import AutomationFramework.DataItems;
import AutomationFramework.Wait;
import static PageObjects.WBA_BasePage.driver;
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
        WebDriver switchToFrame = Wait.frame(driver,frameLocator);
    }
    
    public CCE_DNReprintPage pressPrint() {
        //Wait for button to be clickable
        WebElement print = Wait.clickable(driver,printButton);
        
        print.click();
        
        return new CCE_DNReprintPage(driver);
    }
    
    public CCE_DNReprintPage pressClose() {
               
        Actions action = new Actions(driver);
        action.sendKeys(Keys.ESCAPE).build().perform();

        Alert alert = Wait.alert(driver);
        alert.accept();
        
        return new CCE_DNReprintPage(driver);
    }
    
    
}
