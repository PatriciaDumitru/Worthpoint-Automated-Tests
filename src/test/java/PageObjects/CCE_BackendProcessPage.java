
package PageObjects;

import AutomationFramework.DataItems;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CCE_BackendProcessPage extends WBA_BasePage{
    
    public CCE_BackendProcessPage(WebDriver driver) {
        super(driver);
    }
    
    public WebElement getBreadcrumb() {
        return driver.findElement(DataItems.breadcrumbLocator);
    }
    
}
