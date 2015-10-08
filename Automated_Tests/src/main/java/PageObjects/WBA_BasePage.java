
package PageObjects;

import AutomationFramework.TestSuite;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

//This class is a superclass from which other WBA page classes inherit from. 
//It manages elements common to most pages (coats logo, sliding text bar, logout link, footer)

public class WBA_BasePage {
    
    //Declare WebDriver
    protected static WebDriver driver;
    
    //Page element locators
    static By todayLabelLocator = By.cssSelector("#header > div.top > span.left > span.today");
    static By dateLabelLocator = By.cssSelector("#header > div.top > span.left > span.date");
    static By accessTypeLocator = By.id("access_type");
    static By languageMenuLocator = By.id("lang-menu");
    static By welcomeLabelLocator = By.cssSelector("#header > div.top > span.right > span.welcome");
    static By userNameLocator = By.cssSelector("#header > div.top > span.right > span.username > a");
    static By logoutLabelLocator = By.cssSelector("#header > div.top > span.right > span.logout > a");
    static By slidingTextLocator = By.cssSelector("#slides > div > div > span:nth-child(1)");
    static By coatsLogoLocator = By.cssSelector("#header > a > img");
    public static By breadcrumbLocator = By.cssSelector("#content > h2");
    public static By breadcrumbLocator2 = By.cssSelector("#list_page_breadcrumb > h1");
    public static By breadcrumbLocator3 = By.cssSelector("#list_page_breadcrumb > h2");
    static By footerLocator = By.id("footer");
    private Object ExepectedConditions;
    static By contentFrame = By.id("content");
    
    public WBA_BasePage(WebDriver passedDriver) {
        driver = passedDriver;
    }
    
    public static WebElement getTodayLabel() {
        //find and return element
        return driver.findElement(todayLabelLocator);
    }
    
    public static WebElement getDateLabel() {
        //find and return element
        return driver.findElement(dateLabelLocator);
    }
    
    public static WebElement getAccessTypeSelector() {
        //find and return element
        return driver.findElement(accessTypeLocator);
    }
    
    public static WebElement getLanguageMenu() {
        //find and return element
        return driver.findElement(languageMenuLocator);
    }
    
    public static WebElement getWelcomeLabel() {
        //find and return element
        return driver.findElement(welcomeLabelLocator);
    }
    
    public static WebElement getUserName() {
        //find and return element
        return driver.findElement(userNameLocator);
    }
    
    public static WebElement getLogoutLabel() {
        //find and return element
        return driver.findElement(logoutLabelLocator);
    }
    
    public static WebElement getTextSlider() {
        //find and return element
        return driver.findElement(slidingTextLocator);
    }
    
    public static WebElement getCoatsLogo() {
        //find and return element
        return driver.findElement(coatsLogoLocator);
    }
    
    public static WebElement getFooter() {
        //find and return element
        return driver.findElement(footerLocator);
    }
    
    public static String getBreadcrumbText() {
        //find and return element and text
        return driver.findElement(breadcrumbLocator).getText();
    }
    
    public static String getBreadcrumbText2() {
        return driver.findElement(breadcrumbLocator2).getText();
    }
    
    public static String getBreadcrumbText3() {
        return driver.findElement(breadcrumbLocator3).getText();
    }
    
    public void assertBaseElements() {
        System.out.println("Asserting base elements...");

        //Wait for elements to be visible
        WebElement waitForElements = new WebDriverWait(driver,10).until(ExpectedConditions.visibilityOf(getFooter()));

        //Assert that all elements belonging to the base page (listed in the locators) are displayed correctly
        Assert.assertTrue("Base page: 'Today' label not displayed",getTodayLabel().isDisplayed());
        Assert.assertTrue("Base page: Date label not displayed",getDateLabel().isDisplayed());       
        DateFormat df = new SimpleDateFormat("EEEE, dd MMM yyyy"); //Assert today's date in matching format to label
        String date = df.format(new Date());
        Assert.assertTrue("Base page: Date in header does not match current date",date.equals(getDateLabel().getText()));
        Assert.assertTrue("Base page: CCE/eComm access selector not displayed",getAccessTypeSelector().isDisplayed());
        Assert.assertTrue("Base page: language menu not displayed",getLanguageMenu().isDisplayed());
        Assert.assertTrue("Base page: 'Welcome' label not displayed",getWelcomeLabel().getText().equals("Welcome"));
        Assert.assertTrue("Base page: User's name does not match expected value",getUserName().getText().equals(TestSuite.expectedUserName));
        Assert.assertTrue("Base page: 'Logout' label not displayed",getLogoutLabel().isDisplayed());
        Assert.assertTrue("Base page: Coats logo not displayed",getCoatsLogo().isDisplayed());
        Assert.assertTrue("Base page: Footer not displayed",getFooter().isDisplayed());
    
        System.out.println("Assertions successful.");
    }
    
    public void waitForLoad() {
        WebElement waitForContent = new WebDriverWait(driver,10).until(ExpectedConditions.visibilityOfElementLocated(contentFrame));
    }
    
}
