
package com.coats.selenium.tests;

import AutomationFramework.DataItems;
import org.openqa.selenium.WebDriver;
import PageObjects.Ecomm_MainPage;
import PageObjects.Ecomm_OrderViewPage;
import PageObjects.Ecomm_ProductAvailabilityCheckPage;
import com.coats.selenium.DriverFactory;

import org.testng.AssertJUnit;
import org.testng.annotations.Test;

public class Ecomm_ProductAvailabilityCheck_Test extends DriverFactory {
    
    @Test //Product Availability Check Page :: Page and field checks, make check using YMN
    (groups = {"eComm", "QuickTest"})
    public void PAC1() throws Exception {
        
        WebDriver driver = getDriver();
        
        Ecomm_Base base = new Ecomm_Base(driver);
        Ecomm_MainPage eCommPage = base.setUp("Product Availability Check Page: Page and field checks, make request with YMN","G_PAC_SUSST_1",DataItems.susstUsername,DataItems.susstPassword);

        eCommPage.waitForElement();
    
        System.out.println("Navigating to Product Availability Check Page...");
        
        Ecomm_ProductAvailabilityCheckPage pacPage = eCommPage.clickProductAvailabilityCheck();
        pacPage.waitForLoad();
        
        System.out.println("Page reached.");
        
        pacPage.assertBaseElements();
        
        System.out.println("Checking title...");

        AssertJUnit.assertTrue("Product Availability Check Page: Title not as expected",driver.findElement(DataItems.breadcrumbLocator2).getText().equals("Product Availability Check"));
    
        System.out.println("Title as expected. Checking fields...");
        
        pacPage.checkFields();
        
        System.out.println("Fields checked. Entering details...");
        
        pacPage.setYourMatNum(DataItems.yourMatNum);
        pacPage.setQty("1");
        
        System.out.println("Details entered. Searching for product...");

        pacPage.waitForBrand2();
        
        Ecomm_OrderViewPage viewPage = pacPage.pressSearch();

        /*
        Alert promptAlert  = driver.switchTo().alert();
        String alertText = promptAlert.getText();
        System.out.println("Alert text is " + alertText);
        //Send some text to the alert
        promptAlert.sendKeys("Accepting the alert");
        promptAlert.dismiss();
        */

       // pacPage.pressSearch();

        viewPage.switchTo();
        viewPage.waitForProductInfo();

        String information = viewPage.getInformation();

        System.out.println("Information: " + information);
        System.out.println("Available Quantity: " + viewPage.getAvailableQty());


        System.out.println("View displayed. Closing view...");

        viewPage.closeView();
        driver.navigate().refresh();
        viewPage.waitForInvisibility();
        driver.switchTo().defaultContent();
        
        System.out.println("View closed.");
    
    }
    
    @Test //Product Availability Check Page :: Make check using article
    (groups = {"eComm"})
    public void PAC2() throws Exception {
        WebDriver driver = getDriver();
        
        Ecomm_Base base = new Ecomm_Base(driver);
        Ecomm_MainPage eCommPage = base.setUp("Product Availability Check PAC2: Page and field checks, make request with article","G_PAC_SUSST_2",DataItems.validCustUsername,DataItems.validCustPassword);

        eCommPage.waitForElement();
    
        System.out.println("Navigating to Product Availability Check Page...");
        
        Ecomm_ProductAvailabilityCheckPage pacPage = eCommPage.clickProductAvailabilityCheck();
        pacPage.waitForLoad();
        
        System.out.println("Page reached. Entering details...");
        
        pacPage.setArticle(DataItems.conOrdArticle);
        pacPage.setShadeCode(DataItems.expShadeCode);
        pacPage.setQty("1");
        
        System.out.println("Details entered. Searching for product...");
        
        Ecomm_OrderViewPage viewPage = pacPage.pressSearch();
        viewPage.switchTo();
        viewPage.waitForProductInfo();
        
        String information = viewPage.getInformation();
        
        System.out.println("Information: " + information);
        System.out.println("Available Quantity: " + viewPage.getAvailableQty());
        
        System.out.println("View displayed. Closing view...");
        
        viewPage.closeView();
        driver.navigate().refresh();
        viewPage.waitForInvisibility();
        driver.switchTo().defaultContent();
        
        System.out.println("View closed.");

    }
    
    @Test //Product Availability Check Page :: Make check using brand/ticket/finish/length/shade
    (groups = {"eComm"})
    public void PAC3() throws Exception {
        WebDriver driver = getDriver();
        
        Ecomm_Base base = new Ecomm_Base(driver);
        Ecomm_MainPage eCommPage = base.setUp("Product Availability Check PAC3: Page and field checks, make request with brand/ticket/etc","G_PAC_SUSST_3",DataItems.validCustUsername,DataItems.validCustPassword);

        eCommPage.waitForElement();
    
        System.out.println("Navigating to Product Availability Check Page...");
        
        Ecomm_ProductAvailabilityCheckPage pacPage = eCommPage.clickProductAvailabilityCheck();
        pacPage.waitForLoad();
        
        System.out.println("Page reached. Entering details...");
        
        pacPage.setBrand(DataItems.expBrand);
        pacPage.setShadeCode(DataItems.expShadeCode);
        pacPage.setTicket("120");       
        pacPage.setQty("1");
        pacPage.setLength("5000");
        pacPage.setFinish(DataItems.expFinish);

        
        System.out.println("Details entered. Searching for product...");

        Ecomm_OrderViewPage viewPage = pacPage.pressSearch();

        //driver.navigate().refresh();

        viewPage.switchTo();
        viewPage.waitForProductInfo();
        
        String information = viewPage.getInformation();
        
        System.out.println("Information: " + information);
        System.out.println("Available Quantity: " + viewPage.getAvailableQty());
        
        System.out.println("View displayed. Closing view...");
        
        viewPage.closeView();
        driver.navigate().refresh();
        viewPage.waitForInvisibility();
        driver.switchTo().defaultContent();
        
        System.out.println("View closed.");

    }
}
