
package PageObjects;

import AutomationFramework.CommonTask;
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
        WebElement field = Wait.clickable(driver,titleField);
        field.sendKeys(item);
        return this;
    }
    
    public void switchTo() {
        WebDriver wait = Wait.frame(driver,frameLocator);
    }
    
    public void waitForContent() {
        WebElement wait = Wait.visible(driver,frameLocator);
    }
    
    public void waitForInvisibility() {
        Boolean wait = Wait.invisible(driver,frameLocator);
    }
    
    public void pressSave() {
        WebElement save = Wait.clickable(driver,saveButton);
        save.click();
        waitForInvisibility();
    }
    
    public void closeView() {
        //Press esc
    	Actions pressEsc = new Actions(driver);
    	pressEsc.sendKeys(Keys.ESCAPE).build().perform();
    	//Accept alert
    	Alert alert = Wait.alert(driver);
    	alert.accept();
    	
    	this.waitForInvisibility();
    }
    
}
