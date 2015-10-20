package com.coats.selenium.tests;

import AutomationFramework.DataItems;
import PageObjects.CCE_MainPage;
import PageObjects.WBA_ContinuePage;
import PageObjects.WBA_LoginPage;
import PageObjects.WBA_SelectionPage;
import com.coats.selenium.DriverFactory;
import com.google.common.base.Verify;
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
import org.testng.annotations.Test;

public class Cce_MainPage_IT extends DriverFactory {
    
    @Test //CCE Main Page :: Navigation bar check
    public void CCE1() throws IOException, Exception {
        
        System.out.println("TEST: CCE HOME PAGE");
        System.out.println("Scenario ID (no ID)");
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
        WBA_ContinuePage contPage = loginPage.loginAs(DataItems.validCoatsUsername,DataItems.validCoatsPassword);
        WBA_SelectionPage selectionPage = contPage.pressContinue();
        
        System.out.println("Logged in. Continuing to selection page...");
        
        //Chooce CCE
        CCE_MainPage ccePage = selectionPage.pressCce();
        
        //Wait for page to load
        Boolean waitForLoad = new WebDriverWait(driver,5).until(ExpectedConditions.titleIs(DataItems.ccePageTitle));
        
        //Take a screenshot
        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile,new File(DataItems.screenshotsFilepath+"\\CCE\\Main Page\\1Main page.png"));
        
        System.out.println("Asserting elements on CCE page...");
        
        //Assert that all elements on page load correctly
        ccePage.assertBaseElements();
        AssertJUnit.assertTrue(ccePage.getMainImage().isDisplayed());
        AssertJUnit.assertTrue(ccePage.getNavBar().isDisplayed());
        
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
            AssertJUnit.assertTrue("Navigation tab text: '"+expectedHeadings[i-1]+"' not displayed as expected.",tab.getText().toLowerCase().equals(expectedHeadings[i-1]));
            
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
                    AssertJUnit.assertTrue("Sub-menu tab text: '"+expectedSubMenu[i-1][k-1]+"' not displayed as expected.",subTab.getText().toLowerCase().equals(expectedSubMenu[i-1][k-1]));
                }
            }
        }
        
        System.out.println("Assertions successful.");
        
    }

    @Test //CCE Main Page :: Navigation bar check links 
    public void CCE2() throws InterruptedException, IOException, Exception {
        System.out.println("TEST: CCE NAVIGATION TAB LINKS");
        System.out.println("Scenario ID: G_CCE_MS_02 to 23");
        
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
        WBA_ContinuePage contPage = loginPage.loginAs(DataItems.validCoatsUsername,DataItems.validCoatsPassword);
        WBA_SelectionPage selectionPage = contPage.pressContinue();
        
        System.out.println("Logged in. Continuing to selection page...");
        
        //Choose CCE
        CCE_MainPage ccePage = selectionPage.pressCce();
        
        System.out.println("CCE main page reached. Checking links...");
        
        //Wait for page to load
        Boolean waitForLoad = new WebDriverWait(driver,5).until(ExpectedConditions.titleIs(DataItems.ccePageTitle));
        
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
            
        int pageCounter = 0;
        
        for (int count = 0; count < expectedSubMenu.length; count++) {
            //Generate selector for each header
            By headerLocator = By.cssSelector("#topnav > li:nth-child("+(count+1)+")");
            WebElement header = driver.findElement(headerLocator);
            
            if (expectedSubMenu[count].length == 0) {
                
                //Get expected title
                String expectedTitle = DataItems.cceExpectedTitles[pageCounter];
                
                if (!expectedTitle.equals("")) {
                    header.click();
                    WebElement waitForBreadcrumb = new WebDriverWait(driver,5).until(ExpectedConditions.visibilityOfElementLocated(DataItems.breadcrumbLocator));
                    String actualTitle = driver.findElement(DataItems.breadcrumbLocator).getText();
                    
                    Verify.verify(expectedTitle.equals(actualTitle),"***"+expectedTitle + " page title not displayed as expected, or page incorrectly linked***");
                    
                    String fileName;
                    if (expectedTitle.contains("|")) {
                        String[] titleParts = expectedTitle.split("\\|");
                        fileName = titleParts[1];
                    } else {
                        fileName = expectedTitle;
                    }
                    
                    File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
                    FileUtils.copyFile(scrFile,new File(DataItems.screenshotsFilepath+"\\CCE\\ALL PAGES\\"+pageCounter+fileName+".png"));
                    
                    pageCounter++;
                } else {
                    header.click();
                    String fileName;
                    if (expectedTitle.contains("|")) {
                        String[] titleParts = expectedTitle.split("\\|");
                        fileName = titleParts[1];
                    } else {
                        fileName = expectedTitle;
                    }
                    
                    File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
                    FileUtils.copyFile(scrFile,new File(DataItems.screenshotsFilepath+"\\CCE\\ALL PAGES\\"+pageCounter+fileName+".png"));
                    
                    pageCounter++;
                }         
            } else {
                
                for (int subCount = 0; subCount < expectedSubMenu[count].length; subCount++) {
                    
                    //Get expected title
                    String expectedTitle = DataItems.cceExpectedTitles[pageCounter];
                    
                    if (!expectedTitle.equals("")) {
                        //Generate subtab locator
                        By subtabLocator = By.cssSelector("#topnav > li:nth-child("+(count+1)+") > div > div > ul > li:nth-child("+(subCount+1)+")");
                        //Click header
                        WebElement waitForHeader = new WebDriverWait(driver,5).until(ExpectedConditions.visibilityOfElementLocated(headerLocator));
                        driver.findElement(headerLocator).click();
                        //Wait for sub tab to appear and click
                        WebElement subtab = new WebDriverWait(driver,5).until(ExpectedConditions.visibilityOfElementLocated(subtabLocator));
                        driver.findElement(subtabLocator).click();
                        //Wait for title to display
                        WebElement breadcrumb = new WebDriverWait(driver,5).until(ExpectedConditions.visibilityOfElementLocated(DataItems.breadcrumbLocator));
                        String actualTitle = driver.findElement(DataItems.breadcrumbLocator).getText();
                        Verify.verify(expectedTitle.equals(actualTitle),"***"+expectedTitle+" page title not displayed as expected, or page incorrectly linked***");
                        System.out.println(expectedTitle+" page correctly linked");

                        String fileName;
                        if (expectedTitle.contains("|")) {
                            String[] titleParts = expectedTitle.split("\\|");
                            fileName = titleParts[1];
                        } else {
                            fileName = expectedTitle;
                        }

                        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
                        FileUtils.copyFile(scrFile,new File(DataItems.screenshotsFilepath+"\\CCE\\ALL PAGES\\"+pageCounter+fileName+".png"));

                        
                    }
                    
                    pageCounter++;
                    
                }
            }
            
        }
            
    }
    
}
