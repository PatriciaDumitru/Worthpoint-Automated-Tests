
package PageObjects;

import AutomationFramework.DataItems;
import AutomationFramework.Wait;
import static PageObjects.WBA_BasePage.driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Ecomm_BackendProcessPage extends WBA_BasePage{
    
    //Locators
    By flashMessage = By.id("flashMessage");
    
    public Ecomm_BackendProcessPage(WebDriver driver) {
        super(driver);
    }
    
    public WebElement getBreadcrumb() {
        return driver.findElement(DataItems.breadcrumbLocator);
    }
    
    public void waitForElement() {
        WebElement wait = Wait.visible(driver,flashMessage);
    }
    
    public String getMessage() {
        WebElement message = Wait.visible(driver,flashMessage);
        return message.getText();
    }
    
}
