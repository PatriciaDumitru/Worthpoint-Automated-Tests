
package TestCases;

import AutomationFramework.TestSuite;
import PageObjects.ContinuePage;
import PageObjects.EcommPage;
import PageObjects.LoginPage;
import PageObjects.WbaSelectionPage;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class Ecomm_MainPage {
    
    @Test
    public void ECOMM1() throws IOException {
        System.out.println("TEST: ECOMM HOME PAGE");
        System.out.println("Scenario ID: (no ID)");
        
        //New driver to perform test
        WebDriver driver = new ChromeDriver();
        
        //Navigate to site
        driver.get(TestSuite.targetURL);
        
        //Maxmimise window
        driver.manage().window().maximize();
        
        System.out.println("Logging in...");
        
        //new login page to allow login
        LoginPage loginPage = new LoginPage(driver);
        
        //Login and press continue
        ContinuePage contPage = loginPage.loginAs(TestSuite.validCoatsUsername,TestSuite.validCoatsPassword);
        WbaSelectionPage selectionPage = contPage.pressContinue();
        
        System.out.println("Logged in. Continuing to selection page...");
        
        //Choose eComm
        EcommPage eCommPage = selectionPage.pressEcomm();
        
        System.out.println("Selection page loaded. eComm selected...");
        
        //Wait for page to load
        boolean waitForLoad = new WebDriverWait(driver,10).until(ExpectedConditions.titleIs(TestSuite.eCommPageTitle));
        
        System.out.println("eComm page loaded.");
        
        //Take a screenshot
        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile,new File(TestSuite.screenshotFolder+"\\EComm\\Main Page\\1Ecomm page.png"));
        
        System.out.println("Asserting elements on eComm page");
        
        //Assert that all elements on eComm page load correctly (from top left to bottom right)
        eCommPage.assertBaseElements();
        Assert.assertTrue(EcommPage.getContentImage().isDisplayed());
        Assert.assertTrue(EcommPage.getNavBar().isDisplayed());
        Assert.assertTrue(EcommPage.getMainImage().isDisplayed());
        
        System.out.println("Assertions successful. Checking navigation bar...");
        
        //Hover over each navigation tab and check that all drop down items are as expected
        //expected headings in order of appearance, all to lower case
        String[] expectedHeadings = {"orders","outstanding orders","sap interface log","reports","dashboard","new features"};
        //expected submenu items in order of appearance, all to lower case. 
        //Those tabs without submenus must have empty arrays (for example, SAP interface log)
        String[][] expectedSubMenu = new String[][] {
            {"manual entry","upload orders","from existing bulk order","shade not available","waiting for shade code"},
            {"outstanding orders list","outstanding orders draft list","outstanding upload draft","pending approval list","denied order"},
            {},
            {"invoices","delivery notes","summary of purchase","outstanding payments","my reports","privacy policy and term & condition"},
            {"real upload failed files","backend in process files","backend failed files","ftp failed files"},
            {}                
        };
        
        for (int i = 1; i <= expectedHeadings.length; i++) {
            //check tab text
            By locator = By.cssSelector("#topnav > li:nth-child("+i+")");
            WebElement tab = driver.findElement(locator);
            Assert.assertTrue("Navigation tab text: " + expectedHeadings[i-1] + " not displayed as expected.",tab.getText().toLowerCase().equals(expectedHeadings[i-1]));
            //check submenu, only if the tab has expected submenu items   
            if (expectedSubMenu[i-1].length != 0) {
                //hover over tab and click to reveal submenu
                Actions hoverOverTab = new Actions(driver);
                hoverOverTab.moveToElement(tab).click().build().perform();
                //wait for submenu to be visible
                WebElement waitForMenu = new WebDriverWait(driver,5).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#topnav > li:nth-child("+i+") > div > div > ul > li:nth-child(1)")));
                //cycle through expected submenu items 
                for (int k = 0; k < expectedSubMenu[i-1].length; k++) {
                    locator = By.cssSelector("#topnav > li:nth-child("+i+") > div > div > ul > li:nth-child("+(k+1)+")");
                    WebElement subTab = driver.findElement(locator);
                    Assert.assertTrue("Sub-menu text: '" + expectedSubMenu[i-1][k] + "' not displayed as expected.", subTab.getText().toLowerCase().equals(expectedSubMenu[i-1][k]));
                }
            } 
            
        }
        
        System.out.println("Assertions successful.");
        System.out.println("----------------------------------------------------");
        
        driver.quit();
        

    } //Checks navigation headers are correct
    
    @Test
    public void ECOMM2() {
        
    } //Checks navigation tabs link to correct pages
}
