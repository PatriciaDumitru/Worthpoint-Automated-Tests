
package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class WBA_ForgotPasswordPage {
    
    WebDriver driver;
    
    //Element locators
    By emailFieldLocator = By.id("UserEmail");
    By recoverButtonLocator = By.cssSelector("#UserForgetpwdForm > div.submit > input");
    
    public WBA_ForgotPasswordPage(WebDriver passedDriver) {
        driver = passedDriver;
    }
    
    public WebElement getEmailField() {
        //find and return element
        return driver.findElement(emailFieldLocator);
    }
    
    public WebElement getRecoverButton() {
        //find and return element
        return driver.findElement(recoverButtonLocator);
    }
    
    public WBA_ForgotPasswordPage setEmail(String email) {
        //Wait for field to be clickable and enter e-mail
        WebElement waitForClickable = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(emailFieldLocator));
        Actions clickAndType = new Actions(driver);
        clickAndType.click(driver.findElement(emailFieldLocator)).sendKeys(email).build().perform();
        return this;
    }
    
    public WBA_ForgotPasswordPage pressRecover() {
        //Wait for field to be clickable and click
        WebElement waitForClickable = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(recoverButtonLocator));
        Actions clickRecover = new Actions(driver);
        clickRecover.click(driver.findElement(recoverButtonLocator)).build().perform();
        return this;
    }
    
    
    
}
