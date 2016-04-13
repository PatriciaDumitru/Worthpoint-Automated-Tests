
package com.coats.selenium.tests;

import AutomationFramework.DataItems;
import PageObjects.WBA_ContinuePage;
import PageObjects.WBA_LoginPage;
import PageObjects.WBA_SelectionPage;
import com.coats.selenium.DriverFactory;
import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.testng.AssertJUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Ecomm_MainPage_Test extends DriverFactory {

    @Test //EComm Page :: Page checks, navigation bar check
    (groups = {"eComm", "QuickTest"})
    public void ECOMM1() throws IOException, Exception {
        System.out.println("TEST: ECOMM HOME PAGE");
        System.out.println("Scenario ID: (no ID)");

        //New driver to perform test
        WebDriver driver = getDriver();

        //Navigate to site
        driver.get(DataItems.targetURL);

        //Maxmimise window
        driver.manage().window().maximize();

        System.out.println("Logging in...");

        //new login page to allow login
        WBA_LoginPage loginPage = new WBA_LoginPage(driver);

        //Login and press continue
        WBA_ContinuePage contPage = loginPage.loginAs(DataItems.validCoatsUsername2,DataItems.validCoatsPassword);//"samson.vetharaj@coats.com"
        WBA_SelectionPage selectionPage = contPage.pressContinue();

        System.out.println("Logged in. Continuing to selection page...");

        //Choose eComm
        PageObjects.Ecomm_MainPage eCommPage = selectionPage.pressEcomm();

        System.out.println("Selection page loaded. eComm selected...");

        //Wait for page to load
        boolean waitForLoad = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.titleIs(DataItems.eCommPageTitle));

        System.out.println("eComm page loaded.");

        //Take a screenshot
        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile,new File(DataItems.screenshotsFilepath+"\\EComm\\Main Page\\1Ecomm page.png"));

        //Assert that all elements on eComm page load correctly (from top left to bottom right)
        eCommPage.assertBaseElements();

        System.out.println("Asserting additional elements...");

        AssertJUnit.assertTrue(PageObjects.Ecomm_MainPage.getContentImage().isDisplayed());
        AssertJUnit.assertTrue(PageObjects.Ecomm_MainPage.getNavBar().isDisplayed());
        AssertJUnit.assertTrue(PageObjects.Ecomm_MainPage.getMainImage().isDisplayed());

        System.out.println("Assertions successful. Checking navigation bar...");

        //Hover over each navigation tab and check that all drop down items are as expected
        //expected headings in order of appearance, all to lower case
        String[] expectedHeadings = {"orders","outstanding orders","sap interface log","reports","dashboard","new features"};
        //expected submenu items in order of appearance, all to lower case.
        //Those tabs without submenus must have empty arrays (for example, SAP interface log)
        String[][] expectedSubMenu = new String[][] {
            {"manual entry","upload orders","from existing sample order","from existing bulk order","shade not available","waiting for shade code"},
            {"outstanding orders list","outstanding orders draft list","outstanding upload draft","courier tracking update","pending approval list","denied order","upload draft with errors"},
            {},
            {"invoices","delivery notes","summary of purchase","outstanding payments","my reports","coats user report","order approval history"},
            {"real upload failed files","backend in process files","backend failed files","ftp failed files","ad-hoc order update","failed contract order"},
            {}
        };

        for (int i = 1; i <= expectedHeadings.length; i++) {
            //check tab text
            By locator = By.cssSelector("#topnav > li:nth-child("+i+")");
            WebElement tab = driver.findElement(locator);
            AssertJUnit.assertTrue("Navigation tab text: " + expectedHeadings[i-1] + " not displayed as expected.",tab.getText().toLowerCase().equals(expectedHeadings[i-1]));
            //check submenu, only if the tab has expected submenu items
            if (expectedSubMenu[i-1].length != 0) {
                //hover over tab and click to reveal submenu
                Actions hoverOverTab = new Actions(driver);
                hoverOverTab.moveToElement(tab).click().build().perform();
                //wait for submenu to be visible
                WebElement waitForMenu = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#topnav > li:nth-child("+i+") > div > div > ul > li:nth-child(1)")));
                //cycle through expected submenu items
                for (int k = 0; k < expectedSubMenu[i-1].length; k++) {
                    locator = By.cssSelector("#topnav > li:nth-child("+i+") > div > div > ul > li:nth-child("+(k+1)+")");
                    WebElement subTab = driver.findElement(locator);
                    AssertJUnit.assertTrue("Sub-menu text: '" + expectedSubMenu[i-1][k] + "' not displayed as expected.", subTab.getText().toLowerCase().equals(expectedSubMenu[i-1][k]));
                }
            }

        }

        System.out.println("Assertions successful.");

    }
/*
    //Ecomm Page:: Navbar link checks
    @Test (groups = {"eComm"})
    public void ECOMM2() throws IOException, Exception {
        //New driver instance
        WebDriver driver = getDriver();

        //New eComm base test to handle log-in and navigation
        Ecomm_Base baseTest = new Ecomm_Base(driver);
        PageObjects.Ecomm_MainPage eCommPage = baseTest.setUp("eComm Main Page ECOMM2: Navigation link checks", "no ID");

        String[][] expectedSubMenu = new String[][] {
            {"Manual Entry","Upload Orders","From Existing Sample Order","From Existing Bulk Order","Shade Not Available","Waiting For Shade Code"},
            {"Outstanding Orders List","Outstanding Orders Draft List","Outstanding Upload Draft","Courier Tracking Update","Pending Approval List","Denied Order","Upload Draft With Errors"},
            {},
            {"Invoices","Delivery Notes","Summary Of Purchase","Outstanding Payments","My Reports","Coats User Report","Privacy Policy And Term & Condition","Order Approval History"},
            {"Real Upload Failed Files","Backend In Process Files","Backend Failed Files","FTP Failed Files","Failed Contract Order"},
            {}
        };

        int pageCount = 0;

        for (int headCount = 0; headCount < expectedSubMenu.length; headCount++) {

            By headLocator = By.cssSelector("#topnav > li:nth-child("+(headCount+1)+")");
            WebElement waitForHead = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(headLocator));

            if (expectedSubMenu[headCount].length == 0) {
                String expectedTitle = DataItems.eCommExpectedTitles[0][pageCount];
                driver.findElement(headLocator).click();

                By breadcrumbLocator = null;

                switch (DataItems.eCommExpectedTitles[1][pageCount]) {
                    case "1": breadcrumbLocator = DataItems.breadcrumbLocator; break;
                    case "2": breadcrumbLocator = DataItems.breadcrumbLocator2; break;
                    case "3": breadcrumbLocator = DataItems.breadcrumbLocator3; break;
                    case "4": breadcrumbLocator = DataItems.breadcrumbLocator4; break;
                    default: break;
                }

                if (!(breadcrumbLocator == null)) {
                    WebElement wait = new WebDriverWait(driver,8).until(ExpectedConditions.visibilityOfElementLocated(breadcrumbLocator));
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
                    FileUtils.copyFile(scrFile1,new File(DataItems.screenshotsFilepath+"\\EComm\\ALL PAGES\\"+fileName+".png"));

                    AssertJUnit.assertTrue(expectedTitle + " page: Link or title incorrect",actualTitle.equals(expectedTitle));
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
                    FileUtils.copyFile(scrFile1,new File(DataItems.screenshotsFilepath+"\\EComm\\ALL PAGES\\"+fileName+".png"));
                }

                pageCount++;

            } else {

                for (int subCount = 0; subCount < expectedSubMenu[headCount].length; subCount++) {

                    String expectedTitle = DataItems.eCommExpectedTitles[0][pageCount];

                    By subTabLocator = By.cssSelector("#topnav > li:nth-child("+(headCount+1)+") > div > div > ul > li:nth-child("+(subCount+1)+")");

                    driver.findElement(headLocator).click();

                    WebElement waitForMenu = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(subTabLocator));

                    driver.findElement(subTabLocator).click();

                    By breadcrumbLocator = null;

                    switch (DataItems.eCommExpectedTitles[1][pageCount]) {
                        case "1": breadcrumbLocator = DataItems.breadcrumbLocator; break;
                        case "2": breadcrumbLocator = DataItems.breadcrumbLocator2; break;
                        case "3": breadcrumbLocator = DataItems.breadcrumbLocator3; break;
                        case "4": breadcrumbLocator = DataItems.breadcrumbLocator4; break;
                        default: break;
                    }

                    if (!(breadcrumbLocator == null)) {
                        WebElement wait = new WebDriverWait(driver,8).until(ExpectedConditions.visibilityOfElementLocated(breadcrumbLocator));
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
                    FileUtils.copyFile(scrFile1,new File(DataItems.screenshotsFilepath+"\\EComm\\ALL PAGES\\"+fileName+".png"));

                    AssertJUnit.assertTrue(expectedTitle + " page: Link or title incorrect",actualTitle.equals(expectedTitle));


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
                        FileUtils.copyFile(scrFile1,new File(DataItems.screenshotsFilepath+"\\EComm\\ALL PAGES\\"+fileName+".png"));
                    }

                    pageCount++;

            }

        }

    }

    }
    */

}
