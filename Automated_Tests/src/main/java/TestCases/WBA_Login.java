
package TestCases;

import AutomationFramework.Categories;
import AutomationFramework.DataItems;
import PageObjects.CCE_MainPage;
import PageObjects.WBA_ContinuePage;
import PageObjects.Ecomm_MainPage;
import PageObjects.WBA_LoginPage;
import PageObjects.WBA_SelectionPage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

@Category(Categories.General.class)
public class WBA_Login {
    
    @Test //Login Page :: Page checks
    public void L1() throws IOException {
        System.out.println("TEST: LOGIN PAGE: Check elements are displayed");
        
        WebDriver driver = new ChromeDriver();
        //navigate to QA site
        driver.get(DataItems.targetURL);
        
        //new LoginPage
        WBA_LoginPage liPage = new WBA_LoginPage(driver);
        
        //Take a screenshot
        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile,new File(DataItems.screenshotsFilepath+"\\Login and Forgot Password\\1Login Page.png"));
        
        System.out.println("Asserting elements are displayed...");
        
        //Assert that all expected elements are displayed
        Assert.assertTrue("Coats logo not displayed on Login page",liPage.getCoatsLogo().isDisplayed());
        Assert.assertTrue("Username field not displayed on Login page",liPage.getUsernameField().isDisplayed());
        Assert.assertTrue("Password field not dispalyed on Login page",liPage.getPasswordField().isDisplayed());
        Assert.assertTrue("Login Button not displayed on Login page",liPage.getLoginButton().isDisplayed());
        Assert.assertTrue("Forgot password link not displayed on Login page",liPage.getForgotPassword().isDisplayed());
        Assert.assertTrue("Remember me box not displayed on Login page",liPage.getRememberme().isDisplayed());
        
        System.out.println("Assertions successful.");
        
        System.out.println("----------------------------------------------------");
        
        //exit driver, closing chrome browser
        driver.close();
    }
 
    @Category(Categories.QuickSuite.class)
    @Test //Login Page :: Login using valid Coats user details, navigate to and from CCE and eComm
    public void L2() throws InterruptedException, IOException {
        System.out.println("TEST: LOGIN PAGE/CONTINUE PAGE/SELECTION PAGE: Check elements are displayed");
        
        //new driver to perform test
        WebDriver driver = new ChromeDriver();
        //navigate to QA site
        driver.get("https://qawcs.coatscolourexpress.com");
        //maximise the window
        driver.manage().window().maximize();
        
        System.out.println("Logging in...");
        
        //new login page
        WBA_LoginPage liPage = new WBA_LoginPage(driver);
        
        //login with valid coats details
        WBA_ContinuePage contPage = liPage.loginAs(DataItems.validCoatsUsername,DataItems.validCoatsPassword);
        
        System.out.println("Logged in. Asserting continue page elements are displayed...");
        
        //Wait for page to load
        WebElement waitForLoad = new WebDriverWait(driver,5).until(ExpectedConditions.visibilityOf(WBA_ContinuePage.getMainImage()));
        
        //Take a screenshot
        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile,new File(DataItems.screenshotsFilepath+"\\Login and Forgot Password\\2Continue Page.png"));
        
        //Assert that all Continue Page elements load correctly
        Assert.assertTrue(contPage.getWelcomeImage().isDisplayed());
        Assert.assertTrue(contPage.getMainImage().isDisplayed());
        Assert.assertTrue(contPage.getContinueImage().isDisplayed());
        
        System.out.println("Assertions successful. Continuing to selection page...");
        
        //Press continue to arrive at WBA selection page
        WBA_SelectionPage selectionPage = contPage.pressContinue();
        
        System.out.println("Selection page reached. Asserting elements are displayed...");
        
        //Take a screenshot
        File scrFile2 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile2,new File(DataItems.screenshotsFilepath+"\\Login and Forgot Password\\3Selection Page.png"));
        
        //Scroll down and take another screenshot
        Actions action = new Actions(driver);
        action.sendKeys(Keys.PAGE_DOWN).build().perform();
        File scrFile3 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile3,new File(DataItems.screenshotsFilepath+"\\Login and Forgot Password\\3Selection Page2.png"));
        
        //Assert that all WBA selection page elements load correctly
        Assert.assertTrue(selectionPage.getMainImage().isDisplayed());
        Assert.assertTrue(selectionPage.getCceCircle().isDisplayed());
        Assert.assertTrue(selectionPage.getEcommCircle().isDisplayed());
        
        System.out.println("Assertions successful. Selecting CCE...");

        //Press CCE
        CCE_MainPage ccePage = selectionPage.pressCce();
        boolean waitForLoad2 = new WebDriverWait(driver,5).until(ExpectedConditions.titleIs(DataItems.ccePageTitle));
        
        System.out.println("CCE main page reached. Asserting elements are displayed...");
        
        //Briefly assert that CCE Page elements load correctly, proving the CCE link has worked
        Assert.assertTrue(ccePage.getCoatsLogo().isDisplayed());
        Assert.assertTrue(ccePage.getNavBar().isDisplayed());
        Assert.assertTrue(ccePage.getMainImage().isDisplayed());
        
        System.out.println("Assertions successful. Navigating back to selection page...");
        
        //Go back to selection page
        driver.navigate().back();
        
        System.out.println("Selection page reached. Selecting eComm...");
        //Press eComm
        Ecomm_MainPage eCommPage = selectionPage.pressEcomm();
        
        //Wait for page to load
        boolean waitForLoad3 = new WebDriverWait(driver,5).until(ExpectedConditions.titleIs(DataItems.eCommPageTitle));
        
        System.out.println("eComm reached. Asserting elements are displayed...");
        
        //Briefly assert that eComm Page elements load correctly, proving the eComm link has worked
        eCommPage.assertBaseElements();
        Assert.assertTrue(eCommPage.getMainImage().isDisplayed());
        Assert.assertTrue(eCommPage.getNavBar().isDisplayed());
        
        System.out.println("----------------------------------------------------");
        
        //quit driver, closing chrome browser
        driver.close();
         
    }
     
}
