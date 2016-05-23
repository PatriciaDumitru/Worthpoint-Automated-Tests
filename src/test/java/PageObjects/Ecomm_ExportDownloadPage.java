
package PageObjects;

import AutomationFramework.DataItems;
import AutomationFramework.Wait;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class Ecomm_ExportDownloadPage {
    
    WebDriver driver;
    
    //Locators
    By frameLocator = By.xpath("TB_iframeContent");
    By completeDownloadMessageField = By.xpath(".//*[@id='popup_content']/div/table[1]/tbody/tr[1]/td/div/span");
    By yesButton = By.cssSelector("#popup_content > div > table:nth-child(2) > tbody > tr:nth-child(1) > td > div.actions > ul > li:nth-child(1)");
    By noButton = By.cssSelector("#popup_content > div > table:nth-child(2) > tbody > tr:nth-child(1) > td > div.actions > ul > li:nth-child(2)");
    
    public Ecomm_ExportDownloadPage(WebDriver passedDriver) {
        driver = passedDriver;
    }
    
    public Ecomm_ExportDownloadPage switchTo() {
        WebDriver waitForFrame = Wait.frame(driver,frameLocator);
        return this;
    }
    
    public void waitForDownloadCompletion() {
        Boolean waitForInvisibility = new WebDriverWait(driver,DataItems.downloadWait).ignoring(NoSuchElementException.class).until(ExpectedConditions.invisibilityOfElementLocated(frameLocator));
    }

    public void waitForDowloadCompletion() {

    }
    
    public void waitForContent() {
        WebDriver f = Wait.frame(driver,frameLocator);
    }
    
    public void pressYes() {
        //For My Report exports, "Yes" will send the file to e-mail
        switchTo();
        WebElement btn = Wait.clickable(driver,yesButton);
        btn.click();
    }
    
    public void pressNo() {
        //For My Report exports, "Yes" will send the file to e-mail
        switchTo();
        WebElement btn = Wait.clickable(driver,noButton);
        btn.click();
    }
}
