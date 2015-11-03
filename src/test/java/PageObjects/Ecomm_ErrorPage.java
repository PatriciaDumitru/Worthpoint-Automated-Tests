
package PageObjects;

import AutomationFramework.DataItems;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class Ecomm_ErrorPage extends WBA_BasePage {
    
    //Locators
    By errorBar = By.id("flashMessage");
    
    public Ecomm_ErrorPage(WebDriver driver) {
        super(driver);
    }
    
    public void waitForError() {
        WebElement wait = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.visibilityOfElementLocated(errorBar));
    }
    
    public boolean ensureErrorPage(String error) {
        String errorDisplayed = getError();
        
        return errorDisplayed.contains(error);
    }
    
    public String getError() {
        return driver.findElement(errorBar).getText();
    }
    
}
