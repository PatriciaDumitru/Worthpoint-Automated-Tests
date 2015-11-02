package com.coats.selenium.tests;

import AutomationFramework.DataItems;
import PageObjects.CCE_MainPage;
import PageObjects.WBA_ContinuePage;
import PageObjects.Ecomm_MainPage;
import PageObjects.WBA_LoginPage;
import PageObjects.WBA_SelectionPage;
import com.coats.selenium.DriverFactory;
import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.testng.AssertJUnit;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class WBA_Login_Test extends DriverFactory {
    
    //Login Page :: Page checks
    @Test 
    (groups = {"General"})
    public void L1() throws IOException, Exception {
        System.out.println("TEST: LOGIN PAGE: Check elements are displayed");
        
        WebDriver driver = getDriver();
        //navigate to QA site
        driver.get(DataItems.targetURL);
        
        //new LoginPage
        WBA_LoginPage liPage = new WBA_LoginPage(driver);
        
        //Take a screenshot
        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile,new File(DataItems.screenshotsFilepath+"\\Login and Forgot Password\\1Login Page.png"));
        
        System.out.println("Asserting elements are displayed...");
        
        //Assert that all expected elements are displayed
        AssertJUnit.assertTrue("Coats logo not displayed on Login page",liPage.getCoatsLogo().isDisplayed());
        AssertJUnit.assertTrue("Username field not displayed on Login page",liPage.getUsernameField().isDisplayed());
        AssertJUnit.assertTrue("Password field not dispalyed on Login page",liPage.getPasswordField().isDisplayed());
        AssertJUnit.assertTrue("Login Button not displayed on Login page",liPage.getLoginButton().isDisplayed());
        AssertJUnit.assertTrue("Forgot password link not displayed on Login page",liPage.getForgotPassword().isDisplayed());
        AssertJUnit.assertTrue("Remember me box not displayed on Login page",liPage.getRememberme().isDisplayed());
        
        System.out.println("Assertions successful.");

    }
  
    @Test //Login Page :: Login using valid Coats user details, navigate to and from CCE and eComm
    (groups = {"QuickTest","General"})
    public void L2() throws InterruptedException, IOException, Exception {
        System.out.println("TEST: LOGIN PAGE/CONTINUE PAGE/SELECTION PAGE: Check elements are displayed");
        
        //new driver to perform test
        WebDriver driver = getDriver();
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
        WebElement waitForLoad = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.visibilityOf(WBA_ContinuePage.getMainImage()));
        
        //Take a screenshot
        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile,new File(DataItems.screenshotsFilepath+"\\Login and Forgot Password\\2Continue Page.png"));
        
        //Assert that all Continue Page elements load correctly
        AssertJUnit.assertTrue(contPage.getWelcomeImage().isDisplayed());
        AssertJUnit.assertTrue(contPage.getMainImage().isDisplayed());
        AssertJUnit.assertTrue(contPage.getContinueImage().isDisplayed());
        
        System.out.println("Assertions successful. Continuing to selection page...");
        
        //Press continue to arrive at WBA selection page
        WBA_SelectionPage selectionPage = contPage.pressContinue();
        selectionPage.waitForElement();
        
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
        AssertJUnit.assertTrue(selectionPage.getMainImage().isDisplayed());
        AssertJUnit.assertTrue(selectionPage.getCceCircle().isDisplayed());
        AssertJUnit.assertTrue(selectionPage.getEcommCircle().isDisplayed());
        
        System.out.println("Assertions successful. Selecting CCE...");

        //Press CCE
        CCE_MainPage ccePage = selectionPage.pressCce();
        boolean waitForLoad2 = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.titleIs(DataItems.ccePageTitle));
        
        System.out.println("CCE main page reached. Asserting elements are displayed...");
        
        //Briefly assert that CCE Page elements load correctly, proving the CCE link has worked
        AssertJUnit.assertTrue(ccePage.getCoatsLogo().isDisplayed());
        AssertJUnit.assertTrue(ccePage.getNavBar().isDisplayed());
        AssertJUnit.assertTrue(ccePage.getMainImage().isDisplayed());
        
        System.out.println("Assertions successful. Navigating back to selection page...");
        
        //Go back to selection page
        driver.navigate().back();
        
        System.out.println("Selection page reached. Selecting eComm...");
        //Press eComm
        Ecomm_MainPage eCommPage = selectionPage.pressEcomm();
        
        //Wait for page to load
        boolean waitForLoad3 = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.titleIs(DataItems.eCommPageTitle));
        
        System.out.println("eComm reached. Asserting elements are displayed...");
        
        //Briefly assert that eComm Page elements load correctly, proving the eComm link has worked
        eCommPage.assertBaseElements();
        AssertJUnit.assertTrue(eCommPage.getMainImage().isDisplayed());
        AssertJUnit.assertTrue(eCommPage.getNavBar().isDisplayed());       
         
    }
     
    @Test //Login Page :: Login using invlaid username
    (groups = {"QuickTest","General"})
    public void L3() throws Exception {
        System.out.println("TEST Login Page L3: Login with invalid username");
        System.out.println("Scenario ID: GE_UL_L_2");
        
        //new driver to perform test
        WebDriver driver = getDriver();
        //navigate to QA site
        driver.get("https://qawcs.coatscolourexpress.com");
        //maximise the window
        driver.manage().window().maximize();
        
        System.out.println("Logging in...");
        
        //new login page
        WBA_LoginPage liPage = new WBA_LoginPage(driver);
        
        //login with valid coats details
        liPage.typeUsername(DataItems.invalidUsername);
        liPage.typePassword(DataItems.invalidPassword);
        liPage.pressLoginExpectingFailure();
        
        System.out.println("Log-in attempted. Asserting continue page elements are not displayed...");
        
        boolean success;
        
        try {
            WebElement waitForLoad = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.visibilityOf(WBA_ContinuePage.getMainImage()));
            success = true;
        } catch (Exception e) {
            success = false;
        }
        
        AssertJUnit.assertFalse("Login page: Login page allowed invalid details to be used",success);
        
        AssertJUnit.assertTrue("Login page: Login unsuccessful message not displayed",liPage.getFailedLoginMessage().contains("Incorrect"));
        
        System.out.println("Login failed, as expected");
        
    }
    
    @Test //Login Page :; Login using invalid password
    (groups = {"General"})
    public void L4() throws Exception {
        System.out.println("TEST: Login Page L4: Attempt to login with wrong password");
        System.out.println("Scenario ID: GE_UL_L_3");
        //new driver to perform test
        WebDriver driver = getDriver();
        //navigate to QA site
        driver.get("https://qawcs.coatscolourexpress.com");
        //maximise the window
        driver.manage().window().maximize();
        
        System.out.println("Logging in...");
        
        //new login page
        WBA_LoginPage liPage = new WBA_LoginPage(driver);
        
        //login with valid coats details
        liPage.typeUsername(DataItems.validCoatsUsername);
        liPage.typePassword(DataItems.invalidPassword);
        liPage.pressLoginExpectingFailure();
        
        System.out.println("Log-in attempted. Asserting continue page elements are not displayed...");
        
        boolean success;
        
        try {
            WebElement waitForLoad = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.visibilityOf(WBA_ContinuePage.getMainImage()));
            success = true;
        } catch (Exception e) {
            success = false;
        }
        
        AssertJUnit.assertFalse("Login page: Login page allowed invalid details to be used",success);
        
        AssertJUnit.assertTrue("Login page: Login unsuccessful message not displayed",liPage.getFailedLoginMessage().contains("Incorrect"));
        
        System.out.println("Login failed, as expected");
        
    }
}
