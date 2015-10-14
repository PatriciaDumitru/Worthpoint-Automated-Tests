
package TestCases;

import AutomationFramework.TestSuite;
import PageObjects.WBA_ContinuePage;
import PageObjects.WBA_LoginPage;
import PageObjects.WBA_SelectionPage;
import com.google.common.base.Verify;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.Ignore;
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
    
    @Test //EComm Page :: Page checks, navigation bar check
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
        WBA_LoginPage loginPage = new WBA_LoginPage(driver);
        
        //Login and press continue
        WBA_ContinuePage contPage = loginPage.loginAs(TestSuite.validCoatsUsername,TestSuite.validCoatsPassword);
        WBA_SelectionPage selectionPage = contPage.pressContinue();
        
        System.out.println("Logged in. Continuing to selection page...");
        
        //Choose eComm
        PageObjects.Ecomm_MainPage eCommPage = selectionPage.pressEcomm();
        
        System.out.println("Selection page loaded. eComm selected...");
        
        //Wait for page to load
        boolean waitForLoad = new WebDriverWait(driver,10).until(ExpectedConditions.titleIs(TestSuite.eCommPageTitle));
        
        System.out.println("eComm page loaded.");
        
        //Take a screenshot
        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile,new File(TestSuite.screenshotsFilepath+"\\EComm\\Main Page\\1Ecomm page.png"));
        
        //Assert that all elements on eComm page load correctly (from top left to bottom right)
        eCommPage.assertBaseElements();
        
        System.out.println("Asserting additional elements...");	
        
        Assert.assertTrue(PageObjects.Ecomm_MainPage.getContentImage().isDisplayed());
        Assert.assertTrue(PageObjects.Ecomm_MainPage.getNavBar().isDisplayed());
        Assert.assertTrue(PageObjects.Ecomm_MainPage.getMainImage().isDisplayed());
        
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
        
        driver.close();   

    }
    
    @Test //EComm Page :: Link checks (only works up to Reports due to breadcrumb locator)
    public void ECOMM2() throws IOException {
        //New driver instance
        WebDriver driver = new ChromeDriver();
		
        //New eComm base test to handle log-in and navigation
        Ecomm_SUSST_Base baseTest = new Ecomm_SUSST_Base(driver);
        PageObjects.Ecomm_MainPage eCommPage = baseTest.SUSST_SetUp("eComm Main Page ECOMM2: Navigation link checks", "no ID");
        
        String[][] expectedSubMenu = new String[][] {
            {"Manual Entry","Upload Orders","From Existing Bulk Order","Shade Not Available","Waiting For Shade Code"},
            {"Outstanding Orders List","Outstanding Orders Draft List","Outstanding Upload Draft","Pending Approval List","Denied Order"},
            {},
            {"Invoices","Delivery Notes","Summary Of Purcchase","Outstanding Payments","My Reports","Privacy Policy And Term & Condition"},
            {"Real Upload Failed Files","Backend In Process Files","Backend Failed Files","FTP Failed Files"},
            {}            
        };   
        
        int pageCount = 0;
        
        for (int headCount = 0; headCount < expectedSubMenu.length; headCount++) {
            
            By headLocator = By.cssSelector("#topnav > li:nth-child("+(headCount+1)+")");
            WebElement waitForHead = new WebDriverWait(driver,5).until(ExpectedConditions.elementToBeClickable(headLocator));
            
            if (expectedSubMenu[headCount].length == 0) {
                String expectedTitle = TestSuite.eCommExpectedTitles[0][pageCount];
                driver.findElement(headLocator).click();
                
                By breadcrumbLocator = null;
                
                switch (TestSuite.eCommExpectedTitles[1][pageCount]) {
                    case "1": breadcrumbLocator = TestSuite.breadcrumbLocator; break;
                    case "2": breadcrumbLocator = TestSuite.breadcrumbLocator2; break;
                    case "3": breadcrumbLocator = TestSuite.breadcrumbLocator3; break;
                    default: break;
                }
                
                if (!(breadcrumbLocator == null)) {
                    String actualTitle = driver.findElement(breadcrumbLocator).getText();
                    
                    String fileName = "";
                    if (expectedTitle.contains("|")) {
                        String[] parts = expectedTitle.split("\\|");
                        fileName = pageCount + "" + parts[1];
                    } else {
                        fileName = pageCount + "" + expectedTitle;
                    }
                    
                    //Take a screenshot
                    File scrFile1 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
                    FileUtils.copyFile(scrFile1,new File(TestSuite.screenshotsFilepath+"\\EComm\\ALL PAGES\\"+fileName+".png"));
                    
                    Assert.assertTrue(expectedTitle + " page: Link or title incorrect",actualTitle.equals(expectedTitle));
                } else {
                    
                    String fileName = "";
                    if (expectedTitle.contains("|")) {
                        String[] parts = expectedTitle.split("\\|");
                        fileName = pageCount + "" + parts[1];
                    } else {
                        fileName = pageCount + "" + expectedTitle;
                    }
                    
                    //Take a screenshot
                    File scrFile1 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
                    FileUtils.copyFile(scrFile1,new File(TestSuite.screenshotsFilepath+"\\EComm\\ALL PAGES\\"+fileName+".png"));
                }
         
                pageCount++;
                
            } else {
                
                for (int subCount = 0; subCount < expectedSubMenu[headCount].length; subCount++) {
                    
                    String expectedTitle = TestSuite.eCommExpectedTitles[0][pageCount];
                    
                    By subTabLocator = By.cssSelector("#topnav > li:nth-child("+(headCount+1)+") > div > div > ul > li:nth-child("+(subCount+1)+")");
                    
                    driver.findElement(headLocator).click();
                    
                    WebElement waitForMenu = new WebDriverWait(driver,5).until(ExpectedConditions.elementToBeClickable(subTabLocator));
                    
                    driver.findElement(subTabLocator).click();
                    
                    By breadcrumbLocator = null;
                
                    switch (TestSuite.eCommExpectedTitles[1][pageCount]) {
                        case "1": breadcrumbLocator = TestSuite.breadcrumbLocator; break;
                        case "2": breadcrumbLocator = TestSuite.breadcrumbLocator2; break;
                        case "3": breadcrumbLocator = TestSuite.breadcrumbLocator3; break;
                        default: break;
                    }
                
                    if (!(breadcrumbLocator == null)) {
                        String actualTitle = driver.findElement(breadcrumbLocator).getText();
                    
                        String fileName = "";
                        if (expectedTitle.contains("|")) {
                            String[] parts = expectedTitle.split("\\|");
                            fileName = pageCount + "" + parts[1];
                        } else {
                            fileName = pageCount + "" + expectedTitle;
                        }
                    
                    //Take a screenshot
                    File scrFile1 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
                    FileUtils.copyFile(scrFile1,new File(TestSuite.screenshotsFilepath+"\\EComm\\ALL PAGES\\"+fileName+".png"));
                    
                    Assert.assertTrue(expectedTitle + " page: Link or title incorrect",actualTitle.equals(expectedTitle));
                    
                    } else {
                        String fileName = "";
                        if (expectedTitle.contains("|")) {
                            String[] parts = expectedTitle.split("\\|");
                            fileName = pageCount + "" + parts[1];
                        } else {
                            fileName = pageCount + "" + expectedTitle;
                        }
                    
                        //Take a screenshot
                        File scrFile1 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
                        FileUtils.copyFile(scrFile1,new File(TestSuite.screenshotsFilepath+"\\EComm\\ALL PAGES\\"+fileName+".png"));
                    }
                    
                    pageCount++;
                
            }
            
        }
   
    }
                
        driver.close();
        driver.quit();
        
        System.out.println("----------------------------------------------------");
        
    }
}


