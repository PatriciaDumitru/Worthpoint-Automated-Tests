package PageObjects;

import AutomationFramework.CommonTask;
import AutomationFramework.Wait;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.awt.*;

/**
 * Created by dion on 11.03.2016.
 */
public class CCE_UploadOrderSamplesPage extends WBA_BasePage {

    //Locators
    static By browseFileLocator = By.id("BrowserVisible");
    By fileNameFieldLocator = By.id("filename");
    By fileNameOutputLocator = By.id("FileField");
    By uploadButtonLocator = By.cssSelector("#txtbox > div.btn_upload");
    //static By orderConfHeadingLocator = By.cssSelector("#content > div > h1");


    public CCE_UploadOrderSamplesPage(WebDriver passedDriver) {
        super(passedDriver);
    }

    public WebElement getBrowseFile() {
        //find and return element
        return driver.findElement(browseFileLocator);
    }

    public CCE_UploadOrderSamplesPage setFilePath (String filePath) throws AWTException {
        //Wait for element to be available
        WebElement field = Wait.visible (driver,fileNameOutputLocator);
        //Type filepath
        driver.findElement(fileNameFieldLocator).sendKeys(filePath);

        //Click away to allow update
        By textLocator = By.cssSelector("#txtbox > p");
        driver.findElement(textLocator).click();

        return this;
    }

    public CCE_MappingAlert pressUpload() {
        //Wait for button to be clickable
        WebElement upload = Wait.clickable(driver,uploadButtonLocator);
        //Click button
        Actions clickUpload = new Actions(driver);
        clickUpload.click(upload).build().perform();

        return new CCE_MappingAlert(driver);
    }

   /* public void waitForElement() {
        WebElement wait = Wait.visible(driver,orderConfHeadingLocator);
    }*/


}
