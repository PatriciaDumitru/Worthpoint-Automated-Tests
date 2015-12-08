
package PageObjects;

import AutomationFramework.DataItems;
import AutomationFramework.Wait;
import static PageObjects.WBA_BasePage.driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

//This class is used after "Upload" is pressed in eComm > Upload Order, and displays the question "Do you want to go with Existing Mapping?" 

public class Ecomm_MappingAlert {
    
    WebDriver driver;
    
    //Locators
    By noButton = By.xpath("//input[following::input[@value='Yes']]");
    By yesButton = By.xpath("//input[@value='Yes']");
    By messageBoxTitle = By.xpath("//*[contains(concat(\" \", normalize-space(@class), \" \"), \" msgBoxTitle \")]");
    
    public Ecomm_MappingAlert(WebDriver passedDriver) {
        driver = passedDriver;
    }
    
    public WebElement getBoxTitle() {
        return driver.findElement(messageBoxTitle);
    }
    
    public WebElement getNoButton() {
        return driver.findElement(noButton);
    }
    
    public WebElement getYesButton() {
        return driver.findElement(yesButton);
    }
    
    public Ecomm_MappingPage pressYes() {
        //Wait for alert title to have loaded
        WebElement title = Wait.visible(driver,messageBoxTitle);
        //Wait for button to be clickable
        WebElement btn = Wait.clickable(driver,yesButton);
        //New action to click yes
        Actions clickYes = new Actions(driver);
        clickYes.click(btn).build().perform();
        
        return new Ecomm_MappingPage(driver);
    }
    
    public Ecomm_MappingPage pressNo() {
        //Wait for alert title to have loaded
        WebElement title = Wait.visible(driver,messageBoxTitle);
        //Wait for button to be clickable
        WebElement btn = Wait.clickable(driver,noButton);
        //New action to click no
        Actions clickNo = new Actions(driver);
        clickNo.click(btn).build().perform();
        
        return new Ecomm_MappingPage(driver);
    }
    
}