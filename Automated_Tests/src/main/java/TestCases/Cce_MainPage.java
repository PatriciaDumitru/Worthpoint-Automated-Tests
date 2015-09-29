
package TestCases;

import AutomationFramework.CommonTask;
import AutomationFramework.TestSuite;
import PageObjects.BasePage;
import PageObjects.CcePage;
import PageObjects.ContinuePage;
import PageObjects.LoginPage;
import PageObjects.WbaSelectionPage;
import java.io.File;
import java.io.IOException;
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
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class Cce_MainPage {
    
    @Test
    public void CCE1() throws IOException {
        
        System.out.println("TEST: CCE HOME PAGE");
        System.out.println("Scenario ID (no ID)");
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
        
        //Chooce CCE
        CcePage ccePage = selectionPage.pressCce();
        
        //Wait for page to load
        Boolean waitForLoad = new WebDriverWait(driver,10).until(ExpectedConditions.titleIs(TestSuite.ccePageTitle));
        
        //Take a screenshot
        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile,new File(TestSuite.screenshotFolder+"\\CCE\\Main Page\\1Main page.png"));
        
        System.out.println("Asserting elements on CCE page...");
        
        //Assert that all elements on page load correctly
        ccePage.assertBaseElements();
        Assert.assertTrue(ccePage.getMainImage().isDisplayed());
        Assert.assertTrue(ccePage.getNavBar().isDisplayed());
        
        System.out.println("Assertions successful. Checking navigation bar...");
        
        //Check navigation bar headers
        //Expected text for navbar tabs, all to lower case in order
        String[] expectedHeadings = {"fce","orders","hub","inbox","confirm production","refill cabinet","reports","admin","new features"};
        //Expected subheadings for each navbar tab in order from left to right
        String[][] expectedSubMenu = new String[][] {
            {"task list","task - completed list","fce request"},
            {"order samples","order draft","manual enrich","order status","dn reprint","feedback","feedback completed","feedback awaiting"},
            {"hub sos","received hub"},
            {"inbox","inbox sap"},
            {},
            {},
            {"fce task status","order cycle time","total orders"},
            {"all user types","coats users","masters","lrm log","sap log"},
            {}
        };
        
        for (int i = 1; i <= expectedHeadings.length; i++) {
            By locator = By.cssSelector("#topnav > li:nth-child("+i+")");
            WebElement tab = driver.findElement(locator);
            //Assert that the navigation tab text is as expected
            Assert.assertTrue("Navigation tab text: '"+expectedHeadings[i-1]+"' not displayed as expected.",tab.getText().toLowerCase().equals(expectedHeadings[i-1]));
            
            //Check submenu items only if there is a submenu expected
            if (expectedSubMenu[i-1].length != 0) {
                
                //Click on tab to reveal submenu
                Actions hoverOver = new Actions(driver);
                hoverOver.moveToElement(driver.findElement(locator)).click().build().perform();
                //wait for submenu to be visible
                WebElement waitForMenu = new WebDriverWait(driver,5).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#topnav > li:nth-child("+i+") > div > div > ul > li:nth-child(1)")));
                
                for (int k = 1; k <= expectedSubMenu[i-1].length;k++) {
                    //Calculate locator for sub-menu tab and assert that the text is correctly displayed
                    By subLocator = By.cssSelector("#topnav > li:nth-child("+i+") > div > div > ul > li:nth-child("+k+")");
                    WebElement subTab = driver.findElement(subLocator);
                    Assert.assertTrue("Sub-menu tab text: '"+expectedSubMenu[i-1][k-1]+"' not displayed as expected.",subTab.getText().toLowerCase().equals(expectedSubMenu[i-1][k-1]));
                }
            }
        }
        
        System.out.println("Assertions successful.");
        
        System.out.println("----------------------------------------------------");
        
        driver.quit();
        
    } //Checks navigation bar works
    
    @Test
    public void CCE2() throws InterruptedException, IOException {
        System.out.println("TEST: CCE NAVIGATION TAB LINKS");
        System.out.println("Scenario ID: G_CCE_MS_02 to 23");
        
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
        
        //Chooce CCE
        CcePage ccePage = selectionPage.pressCce();
        
        System.out.println("CCE main page reached. Checking links...");
        
        //Wait for page to load
        Boolean waitForLoad = new WebDriverWait(driver,10).until(ExpectedConditions.titleIs(TestSuite.ccePageTitle));
        
        //Expected navigation bar headings
        String[] expectedHeadings = {"fce","orders","hub","inbox","confirm production","refill cabinet","reports","admin","new features"};
        //Expected subheadings for each navbar tab in order from left to right
        String[][] expectedSubMenu = new String[][] {
            {"task list","task - completed list","fce request"},
            {"order samples","order draft","manual enrich","order status","dn reprint","feedback","feedback completed","feedback awaiting"},
            {"hub sos","received hub"},
            {"inbox","inbox sap"},
            {},
            {},
            {"fce task status","order cycle time","total orders"},
            {"all user types","coats users","masters","lrm log","sap log"},
            {}
        };   
        
        //Counter for the number of pages
        int titleCounter = 0;
        
        //For each heading, check that the sub-tabs link to the correct pages and screenshot them
        for (int i = 0; i < expectedHeadings.length; i++) {
            //Create header locator using "i"
            By headerLocator = By.cssSelector("#topnav > li:nth-child("+(i+1)+")");
            WebElement heading = driver.findElement(headerLocator);
            
            System.out.println("Checking '" + expectedHeadings[i] + "' tabs...");
            
            //For headings with no subtabs, follow the shorter logic
            if (expectedSubMenu[i].length == 0) {
                //Click heading, take screenshot
                Actions action = new Actions(driver);
                action.click(heading).build().perform();
                
                String title = TestSuite.cceExpectedTitles[titleCounter];
                if (!title.equals("")) {
                    WebElement waitForBreadcrumb = new WebDriverWait(driver,10).until(ExpectedConditions.visibilityOfElementLocated(TestSuite.breadcrumbLocator));
                    WebElement breadcrumb = driver.findElement(TestSuite.breadcrumbLocator);
                    boolean waitForTitle = new WebDriverWait(driver,10).until(ExpectedConditions.textToBePresentInElement(breadcrumb, title));
                    System.out.println(title+ " page correctly linked.");
                    File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
                    String fileName = expectedHeadings[i];
                    FileUtils.copyFile(scrFile,new File(TestSuite.screenshotFolder+"\\CCE\\ALL PAGES\\"+i+fileName+".png"));
                }
                
                //Take a Screenshot
                File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
                FileUtils.copyFile(scrFile,new File(TestSuite.screenshotFolder+"\\CCE\\ALL PAGES\\"+i+expectedHeadings[i]+".png"));
                
                titleCounter++;
                
            } else {
                //For each sub-tab heading, click the header, click the sub-tab, take a screenshot
                for (int j = 0; j < expectedSubMenu[i].length; j++) {
                    //Click header
                    Actions action = new Actions(driver);
                    action.click(driver.findElement(headerLocator)).build().perform();
                    //wait for and click subtab
                    By subtabLocator = By.cssSelector("#topnav > li:nth-child("+(i+1)+") > div > div > ul > li:nth-child("+(j+1)+")");
                    WebElement waitForSubtab = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(subtabLocator));
                    action.click(driver.findElement(subtabLocator)).build().perform();

                    String title = TestSuite.cceExpectedTitles[titleCounter];
                    
                    if (!title.equals("")) {
                        WebElement waitForBreadcrumb = new WebDriverWait(driver,10).until(ExpectedConditions.visibilityOfElementLocated(TestSuite.breadcrumbLocator));
                        WebElement breadcrumb = driver.findElement(TestSuite.breadcrumbLocator);
                        boolean waitForTitle = new WebDriverWait(driver,10).until(ExpectedConditions.textToBePresentInElement(breadcrumb,title));
                        System.out.println(title+" page correctly linked.");
                        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
                        String[] fileName = expectedSubMenu[i][j].split("|");
                        FileUtils.copyFile(scrFile,new File(TestSuite.screenshotFolder+"\\CCE\\ALL PAGES\\"+i+fileName[i]+".png"));
                    } else {
                        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
                        FileUtils.copyFile(scrFile,new File(TestSuite.screenshotFolder+"\\CCE\\ALL PAGES\\"+i+expectedSubMenu[i][j]+".png"));
                    }
                    
                    
                    
                    //wait for page to load
                    /*for (int k = 0; k < 5; k++) {
                        if (BasePage.getBreadcrumbText().contains(expectedSubMenu[i][j])) {
                            System.out.println(expectedSubMenu[i][j] +" page correctly linked.");
                            break;
                        }
                        Thread.sleep(150);
                    }*/
                    


                   
                    titleCounter++;
                }
            }
            System.out.println("'" + expectedHeadings[i] + "' tabs checked.");
            
        }
        
        System.out.println("----------------------------------------------------");
        driver.quit();
    } //Check navigation tabs link correctly
    
}
