
package PageObjects;

import AutomationFramework.DataItems;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WBA_ContinuePage {
    
    static WebDriver driver;
    
    //Locators for the page elements
    static By welcomeImageLocator = By.cssSelector("#wrapper > div.logo1 > a > img");
    static By mainImageLocator = By.cssSelector("#wrapper > div:nth-child(4) > a:nth-child(1) > img");
    static By continueImageLocator = By.cssSelector("#wrapper > div:nth-child(4) > a:nth-child(2) > img");
    static By continueImageDuringMaintenance = By.cssSelector("#wrapper > div:nth-child(4) > a > img");

    static By agreeTermsAndCondLocator = By.xpath("/html/body/div[2]/div[11]/div/button[1]");

    public WBA_ContinuePage(WebDriver passedDriver) {
        //Initialise driver
        driver = passedDriver;
    }
    
    public static WebElement getWelcomeImage() {
        //find and return element
        WebElement waitForImage = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.visibilityOfElementLocated(welcomeImageLocator));
        return driver.findElement(welcomeImageLocator);
    }
    
    public static WebElement getMainImage() {
        //find and return element
        WebElement waitForImage = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.visibilityOfElementLocated(mainImageLocator));
        return driver.findElement(mainImageLocator);       
    }
    
    public static WebElement getContinueImage() {
        //find and return element
        WebElement waitForImage = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.visibilityOfElementLocated(continueImageDuringMaintenance));
        return driver.findElement(continueImageDuringMaintenance);
    }


    
    public WBA_SelectionPage pressContinue() {
        WebElement wait = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(continueImageLocator));

        //new action to click continue button
        Actions clickContinue = new Actions(driver);
        clickContinue.click(getContinueImage()).build().perform();
        return new WBA_SelectionPage(driver);
    }

/*    public WBA_SelectionPage pressContinueTerms() {
        WebElement wait = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(agreeTermsAndCondLocator));

        //new action to click continue button
        Actions clickContinue = new Actions(driver);
        clickContinue.click(getContinueImage()).build().perform();
        return new WBA_SelectionPage(driver);
    }

    public static WebElement getAgreeTermsAndCondLocator() {
        //find and return element
        WebElement waitForImage = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.visibilityOfElementLocated(continueImageDuringMaintenance));
        return driver.findElement(agreeTermsAndCondLocator);
    }
    */
    
}
