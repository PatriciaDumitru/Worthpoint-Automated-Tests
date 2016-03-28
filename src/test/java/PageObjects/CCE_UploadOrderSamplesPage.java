package PageObjects;

import AutomationFramework.CommonTask;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Created by dion on 11.03.2016.
 */
public class CCE_UploadOrderSamplesPage extends WBA_BasePage {

    //Locators
    static By browseFileLocator = By.id("BrowserVisible");


    public CCE_UploadOrderSamplesPage(WebDriver passedDriver) {
        super(passedDriver);
    }

    public WebElement getBrowseFile() {
        //find and return element
        return driver.findElement(browseFileLocator);
    }


}
