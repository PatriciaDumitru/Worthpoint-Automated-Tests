
package PageObjects;

import AutomationFramework.DataItems;
import AutomationFramework.Wait;
import static PageObjects.WBA_BasePage.driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

//Error page which appears after confirming mapping. Usually appears for Duplicate File Name error
public class Ecomm_UploadProcessPage extends WBA_BasePage{
    
    static By flashMessageLocator = By.id("flashMessage");
    static By breadcrumb = By.cssSelector("#content > h2");
    
    public Ecomm_UploadProcessPage(WebDriver passedDriver) {
        super(passedDriver);
    }
    
    public String getBreadcrumb() {
        return driver.findElement(breadcrumb).getText();
    }
    
    public WebElement waitForError() {
        WebElement wait = Wait.visible(driver,flashMessageLocator);
        return wait;
    }
    
}
