
package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WBA_LoginPage {
    
    static WebDriver driver;
    
    public WBA_LoginPage(WebDriver passedDriver) {
        //Initialise driver
        driver = passedDriver;
    }
    
    static By coatsLogoLocator = By.cssSelector("#header > div.logo > a > img");
    static By usernameFieldLocator = By.id("UserUsername");
    static By passwordFieldLocator = By.id("UserPassword");
    static By loginButtonLocator = By.cssSelector("#login-bottom > div.submit > input");
    static By forgotPasswordLocator = By.cssSelector("#forgot-password > div > ul > li > a");
    static By remembermeLocator = By.className("remember");
    
    public static WebElement getCoatsLogo() {
        //find and return element
        return driver.findElement(coatsLogoLocator);       
    }
    
    public static WebElement getUsernameField() {
        //find and return element
        return driver.findElement(usernameFieldLocator);
    }
    
    public static WebElement getPasswordField() {
        //find and return element
        return driver.findElement(passwordFieldLocator);
    }
    
    public static WebElement getLoginButton() {
        //find and return element
        return driver.findElement(loginButtonLocator);
    }
    
    public static WebElement getForgotPassword() {
        //find and return element
        return driver.findElement(forgotPasswordLocator);
    }
    
    public static WebElement getRememberme() {
        //find and return element
        return driver.findElement(remembermeLocator);
    }
    
    public WBA_LoginPage typeUsername(String username) {
        //new action to send keys to username field
        Actions typeUsername = new Actions(driver);
        typeUsername.sendKeys(getUsernameField(), username).build().perform();
        return this;
    }
    
    public WBA_LoginPage typePassword(String password) {
        //new action to send keys to password field
        Actions typePassword = new Actions(driver);
        typePassword.sendKeys(getPasswordField(),password).build().perform();
        return this;
    }
    
    public WBA_ContinuePage pressLogin() {
        //new action to press login button
        Actions clickLogin = new Actions(driver);
        clickLogin.click(getLoginButton()).build().perform();
        return new WBA_ContinuePage(driver);
    }
    
    public WBA_ContinuePage loginAs(String username, String password) {
        //type username, password, and press login
        typeUsername(username);
        typePassword(password);
        return pressLogin();
    }
    
    public WBA_LoginPage loginAsFailure(String username, String password) {
        //enter username, password, and press login expecting failure
        typeUsername(username);
        typePassword(password);
        return pressLoginExpectingFailure(); 
    }
    
    public WBA_LoginPage pressLoginExpectingFailure() {
        //new action to press login
        Actions clickLogin = new Actions(driver);
        clickLogin.click(getLoginButton()).build().perform();
        //return login page, as the login is expected to fail due to incorrect details
        return new WBA_LoginPage(driver);
    }
    
    public WBA_ForgotPasswordPage pressForgotPassword() {
        //Wait for link element to be clickable
        WebElement waitForLink = new WebDriverWait(driver,5).until(ExpectedConditions.elementToBeClickable(forgotPasswordLocator));
        //New action to press forgot password
        Actions clickForgotPassword = new Actions(driver);
        clickForgotPassword.click(driver.findElement(forgotPasswordLocator)).build().perform();
        return new WBA_ForgotPasswordPage(driver);
    }
    

}
