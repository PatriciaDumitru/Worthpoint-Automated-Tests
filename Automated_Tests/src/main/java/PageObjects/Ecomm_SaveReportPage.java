
package PageObjects;

import AutomationFramework.CommonTask;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Ecomm_SaveReportPage {
    
    WebDriver driver;
    
    //Locators
    By frameLocator = By.id("TB_iframeContent");
    By titleField = By.id("variant");
    By saveButton = By.id("submit1");
    
    public Ecomm_SaveReportPage(WebDriver passedDriver) {
        driver = passedDriver;
    }
    
    public Ecomm_SaveReportPage setTitle(String item) {
        switchTo();
        WebElement waitForField = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(titleField));
        driver.findElement(titleField).sendKeys(item);
        return this;
    }
    
    public void switchTo() {
        WebDriver wait = new WebDriverWait(driver,10).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameLocator));
    }
    
    public void waitForContent() {
        WebElement wait = new WebDriverWait(driver,10).until(ExpectedConditions.visibilityOfElementLocated(frameLocator));
    }
    
    public void waitForInvisibility() {
        Boolean wait = new WebDriverWait(driver,10).until(ExpectedConditions.invisibilityOfElementLocated(frameLocator));
    }
    
    public void pressSave() {
        WebElement waitForButton = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(saveButton));
        driver.findElement(saveButton).click();
        waitForInvisibility();
    }
    
    public void closeView() {
        //Press esc
    	Actions pressEsc = new Actions(driver);
    	pressEsc.sendKeys(Keys.ESCAPE).build().perform();
    	//Accept alert
    	Alert alert = new WebDriverWait(driver,10).until(ExpectedConditions.alertIsPresent());
    	alert.accept();
    	
    	this.waitForInvisibility();
    }
    
}
