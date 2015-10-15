
package TestCases;

import AutomationFramework.Categories;
import AutomationFramework.DataItems;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import PageObjects.Ecomm_MainPage;
import PageObjects.Ecomm_OrderViewPage;
import PageObjects.Ecomm_ProductAvailabilityCheckPage;
import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.experimental.categories.Category;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

@Category(Categories.eComm.class)
public class Ecomm_ProductAvailabilityCheck {
    
    @Test //Product Availability Check Page :: Page and field checks, make check using YMN
    public void PAC1() throws IOException, InterruptedException {
        
        WebDriver driver = new ChromeDriver();
        
        Ecomm_SUSST_Base base = new Ecomm_SUSST_Base(driver);
        Ecomm_MainPage eCommPage = base.SUSST_SetUp("Product Availability Check Page: Page and field checks, make request with YMN","G_PAC_SUSST_1",DataItems.validCustUsername,DataItems.validCustPassword);
    
        System.out.println("Navigating to Product Availability Check Page...");
        
        Ecomm_ProductAvailabilityCheckPage pacPage = eCommPage.clickProductAvailabilityCheck();
        pacPage.waitForLoad();
        
        //Take a screenshot
        File scrFile1 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile1,new File(DataItems.screenshotsFilepath+"\\EComm\\Product Availability Check\\1Product Availability Check Page.png"));
        
        System.out.println("Page reached.");
        
        pacPage.assertBaseElements();
        
        System.out.println("Checking title...");

        Assert.assertTrue("Product Availability Check Page: Title not as expected",driver.findElement(DataItems.breadcrumbLocator2).getText().equals("Product Availability Check"));
    
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
        
        driver.close();
        driver.quit();
        
        System.out.println("----------------------------------------------------");
    
    }
    
    @Test //Product Availability Check Page :: Make check using article
    public void PAC2() throws IOException, InterruptedException {
        WebDriver driver = new ChromeDriver();
        
        Ecomm_SUSST_Base base = new Ecomm_SUSST_Base(driver);
        Ecomm_MainPage eCommPage = base.SUSST_SetUp("Product Availability Check Page: Page and field checks, make request with YMN","G_PAC_SUSST_1",DataItems.validCustUsername,DataItems.validCustPassword);
    
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
        
        //Take a screenshot
        File scrFile3 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile3,new File(DataItems.screenshotsFilepath+"\\EComm\\Product Availability Check\\6Search view.png"));
        
        System.out.println("View displayed. Closing view...");
        
        viewPage.closeView();
        viewPage.waitForInvisibility();
        driver.switchTo().defaultContent();
        
        System.out.println("View closed.");
        
        driver.close();
        driver.quit();
        
        System.out.println("----------------------------------------------------");
    }
    
}
