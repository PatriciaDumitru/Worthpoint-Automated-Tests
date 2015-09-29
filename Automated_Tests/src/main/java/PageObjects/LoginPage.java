
package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
    
    static WebDriver driver;
    
    public LoginPage(WebDriver passedDriver) {
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
    
    public LoginPage typeUsername(String username) {
        //new action to send keys to username field
        Actions typeUsername = new Actions(driver);
        typeUsername.sendKeys(getUsernameField(), username).build().perform();
        return this;
    }
    
    public LoginPage typePassword(String password) {
        //new action to send keys to password field
        Actions typePassword = new Actions(driver);
        typePassword.sendKeys(getPasswordField(),password).build().perform();
        return this;
    }
    
    public ContinuePage pressLogin() {
        //new action to press login button
        Actions clickLogin = new Actions(driver);
        clickLogin.click(getLoginButton()).build().perform();
        return new ContinuePage(driver);
    }
    
    public ContinuePage loginAs(String username, String password) {
        //type username, password, and press login
        typeUsername(username);
        typePassword(password);
        return pressLogin();
    }
    
    public LoginPage loginAsFailure(String username, String password) {
        //enter username, password, and press login expecting failure
        typeUsername(username);
        typePassword(password);
        return pressLoginExpectingFailure(); 
    }
    
    public LoginPage pressLoginExpectingFailure() {
        //new action to press login
        Actions clickLogin = new Actions(driver);
        clickLogin.click(getLoginButton()).build().perform();
        //return login page, as the login is expected to fail due to incorrect details
        return new LoginPage(driver);
    }
    
    public ForgotPasswordPage pressForgotPassword() {
        //Wait for link element to be clickable
        WebElement waitForLink = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(forgotPasswordLocator));
        //New action to press forgot password
        Actions clickForgotPassword = new Actions(driver);
        clickForgotPassword.click(driver.findElement(forgotPasswordLocator)).build().perform();
        return new ForgotPasswordPage(driver);
    }
    

}
