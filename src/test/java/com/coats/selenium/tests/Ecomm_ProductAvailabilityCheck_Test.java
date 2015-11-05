
package com.coats.selenium.tests;

import AutomationFramework.DataItems;
import org.openqa.selenium.WebDriver;
import PageObjects.Ecomm_MainPage;
import PageObjects.Ecomm_OrderViewPage;
import PageObjects.Ecomm_ProductAvailabilityCheckPage;
import com.coats.selenium.DriverFactory;
import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.testng.AssertJUnit;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Ecomm_ProductAvailabilityCheck_Test extends DriverFactory {
    
    @Test //Product Availability Check Page :: Page and field checks, make check using YMN
    (groups = {"eComm"})
    public void PAC1() throws IOException, InterruptedException, Exception {
        
        WebDriver driver = getDriver();
        
        Ecomm_Base base = new Ecomm_Base(driver);
        Ecomm_MainPage eCommPage = base.setUp("Product Availability Check Page: Page and field checks, make request with YMN","G_PAC_SUSST_1",DataItems.validCustUsername,DataItems.validCustPassword);
    
        System.out.println("Navigating to Product Availability Check Page...");
        
        Ecomm_ProductAvailabilityCheckPage pacPage = eCommPage.clickProductAvailabilityCheck();
        pacPage.waitForLoad();
        
        //Take a screenshot
        File scrFile1 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile1,new File(DataItems.screenshotsFilepath+"\\EComm\\Product Availability Check\\1Product Availability Check Page.png"));
        
        System.out.println("Page reached.");
        
        pacPage.assertBaseElements();
        
        System.out.println("Checking title...");

        AssertJUnit.assertTrue("Product Availability Check Page: Title not as expected",driver.findElement(DataItems.breadcrumbLocator2).getText().equals("Product Availability Check"));
    
        System.out.println("Title as expected. Checking fields...");
        
        pacPage.checkFields();
        
        System.out.println("Fields checked. Entering details...");
        
        pacPage.setYourMatNum(DataItems.yourMatNum);
        pacPage.setBrand(DataItems.expBrand);
        pacPage.setShadeCode(DataItems.expShadeCode);
        pacPage.setQty("1");
        
        //Take a screenshot
        File scrFile2 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile2,new File(DataItems.screenshotsFilepath+"\\EComm\\Product Availability Check\\2Details entered.png"));
        
        System.out.println("Details entered. Searching for product...");
        
        Ecomm_OrderViewPage viewPage = pacPage.pressSearch();
        viewPage.switchTo();
        viewPage.waitForProductInfo();
        
        String information = viewPage.getInformation();
        
        System.out.println("Information: " + information);
        
        //Take a screenshot
        File scrFile3 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile3,new File(DataItems.screenshotsFilepath+"\\EComm\\Product Availability Check\\3Search view.png"));
        
        System.out.println("View displayed. Closing view...");
        
        viewPage.closeView();
        viewPage.waitForInvisibility();
        driver.switchTo().defaultContent();
        
        //Take a screenshot
        File scrFile4 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile4,new File(DataItems.screenshotsFilepath+"\\EComm\\Product Availability Check\\4View closed.png"));
        
        System.out.println("View closed.");
    
    }
    
    @Test //Product Availability Check Page :: Make check using article
    (groups = {"eComm"})
    public void PAC2() throws IOException, InterruptedException, Exception {
        WebDriver driver = getDriver();
        
        Ecomm_Base base = new Ecomm_Base(driver);
        Ecomm_MainPage eCommPage = base.setUp("Product Availability Check PAC2: Page and field checks, make request with article","G_PAC_SUSST_2",DataItems.validCustUsername,DataItems.validCustPassword);
    
        System.out.println("Navigating to Product Availability Check Page...");
        
        Ecomm_ProductAvailabilityCheckPage pacPage = eCommPage.clickProductAvailabilityCheck();
        pacPage.waitForLoad();
        
        System.out.println("Page reached. Entering details...");
        
        pacPage.setArticle(DataItems.conOrdArticle);
        pacPage.setShadeCode(DataItems.expShadeCode);
        pacPage.setQty("1");
        
        //Take a screenshot
        File scrFile2 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile2,new File(DataItems.screenshotsFilepath+"\\EComm\\Product Availability Check\\5Details entered.png"));
        
        System.out.println("Details entered. Searching for product...");
        
        Ecomm_OrderViewPage viewPage = pacPage.pressSearch();
        viewPage.switchTo();
        viewPage.waitForProductInfo();
        
        String information = viewPage.getInformation();
        
        System.out.println("Information: " + information);
        
        //Take a screenshot
        File scrFile3 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile3,new File(DataItems.screenshotsFilepath+"\\EComm\\Product Availability Check\\6Search view.png"));
        
        System.out.println("View displayed. Closing view...");
        
        viewPage.closeView();
        viewPage.waitForInvisibility();
        driver.switchTo().defaultContent();
        
        System.out.println("View closed.");

    }
    
    @Test //Product Availability Check Page :: Make check using brand/ticket/finish/length/shade
    (groups = {"eComm"})
    public void PAC3() throws IOException, InterruptedException, Exception {
        WebDriver driver = getDriver();
        
        Ecomm_Base base = new Ecomm_Base(driver);
        Ecomm_MainPage eCommPage = base.setUp("Product Availability Check PAC3: Page and field checks, make request with brand/ticket/etc","G_PAC_SUSST_3",DataItems.validCustUsername,DataItems.validCustPassword);
    
        System.out.println("Navigating to Product Availability Check Page...");
        
        Ecomm_ProductAvailabilityCheckPage pacPage = eCommPage.clickProductAvailabilityCheck();
        pacPage.waitForLoad();
        
        System.out.println("Page reached. Entering details...");
        
        pacPage.setBrand("astra");
        pacPage.setShadeCode(DataItems.expShadeCode);
        pacPage.setTicket("120");       
        pacPage.setQty("1");
        
        System.out.println("Details entered. Searching for product...");
        
        Ecomm_OrderViewPage viewPage = pacPage.pressSearch();
        viewPage.switchTo();
        viewPage.waitForProductInfo();
        
        String information = viewPage.getInformation();
        
        System.out.println("Information: " + information);
        
        System.out.println("View displayed. Closing view...");
        
        viewPage.closeView();
        viewPage.waitForInvisibility();
        driver.switchTo().defaultContent();
        
        System.out.println("View closed.");

    }
}
