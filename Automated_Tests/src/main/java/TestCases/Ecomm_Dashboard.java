
package TestCases;

import AutomationFramework.Categories;
import AutomationFramework.DataItems;
import PageObjects.Ecomm_BackendFailedFilesPage;
import PageObjects.Ecomm_BackendInProcessPage;
import PageObjects.Ecomm_MainPage;
import PageObjects.Ecomm_FTPFailedFilesPage;
import PageObjects.Ecomm_MyReportsPage;
import PageObjects.Ecomm_OrderViewPage;
import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

@Category(Categories.eComm.class)
public class Ecomm_Dashboard {
    
    @Test
    public void BIP1() throws IOException {
        //new driver instance
            WebDriver driver = new ChromeDriver();
		
            //New eComm base test to handle log-in and navigation
            Ecomm_SUSST_Base baseTest = new Ecomm_SUSST_Base(driver);
            Ecomm_MainPage eCommPage = baseTest.SUSST_SetUp("Backend In Process Page BIP1: Page and filter checks, reset", "G_D_BIPL");
				
            Ecomm_BackendInProcessPage bipPage = eCommPage.clickBackendInProcess();
            bipPage.waitForLoad();
            
            //Take a screenshot
            File scrFile1 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile1,new File(DataItems.screenshotsFilepath+"\\EComm\\Dashboard\\Backend In Process Files\\1Backend In Process Page.png"));
		
            System.out.println("Backend In Process page reached.");
		
            bipPage.assertBaseElements();
		
            System.out.println("Checking title...");
            
            Assert.assertTrue("Backend In Process Files Page: Title not displayed as expected",bipPage.getBreadcrumbText2().equals("Backend In Process Files"));
            
            System.out.println("Title checked. Checking fields...");
		
            bipPage.checkFields();
            
            System.out.println("Fields checked. Entering filter criteria...");
            
            bipPage.setCustName(DataItems.custDetails[0]);
            
            //Take a screenshot
            File scrFile2 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile2,new File(DataItems.screenshotsFilepath+"\\EComm\\Dashboard\\Backend In Process Files\\2Filter criteria entered.png"));
            
            System.out.println("Criteria entered. Listing files...");
            
            bipPage.pressSearch();
            bipPage.waitForLoad();
            
            System.out.println("Files listed. Resetting filter...");
            
            bipPage.pressReset();
            bipPage.waitForLoad();
            
            System.out.println("Filter reset.");
            
            System.out.println("----------------------------------------------------");
            driver.close();
    }
    
    @Test
    public void BFF1() throws IOException {
        //new driver instance
        WebDriver driver = new ChromeDriver();
		
        //New eComm base test to handle log-in and navigation
        Ecomm_SUSST_Base baseTest = new Ecomm_SUSST_Base(driver);
        Ecomm_MainPage eCommPage = baseTest.SUSST_SetUp("Backend Failed Files Page BFF1: Page and filter checks, view, reset, download", "G_D_BFF_1");
				
        Ecomm_BackendFailedFilesPage bffPage = eCommPage.clickBackendFailedFiles();
        bffPage.waitForLoad();
            
        //Take a screenshot
        File scrFile1 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile1,new File(DataItems.screenshotsFilepath+"\\EComm\\Dashboard\\Backend Failed Files\\1Backend Failed Files Page.png"));
		
        System.out.println("Backend Failed Files page reached.");
		
        bffPage.assertBaseElements();
		
        System.out.println("Checking title...");
            
        Assert.assertTrue("Backend Failed Files Page: Title not displayed as expected",bffPage.getBreadcrumbText2().equals("Backend Failed Files"));
            
        System.out.println("Title checked. Checking fields...");
		
        bffPage.checkFields();
            
        System.out.println("Fields checked. Entering filter criteria...");
            
        bffPage.setCustName(DataItems.custDetails[0]);
        
        //Take a screenshot
        File scrFile2 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile2,new File(DataItems.screenshotsFilepath+"\\EComm\\Dashboard\\Backend Failed Files\\2Filter criteria entered.png"));
        
        System.out.println("Filter criteria entered. Lisitng files...");
        
        bffPage.pressSearch();
        bffPage.waitForLoad();
        
        //Take a screenshot
        File scrFile3 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile3,new File(DataItems.screenshotsFilepath+"\\EComm\\Dashboard\\Backend Failed Files\\3Files listed.png"));
        
        System.out.println("Files listed. Viewing top item...");
        
        Ecomm_OrderViewPage viewPage = bffPage.pressView();
        viewPage.waitForErrorTable();
        
        //Take a screenshot
        File scrFile4 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile4,new File(DataItems.screenshotsFilepath+"\\EComm\\Dashboard\\Backend Failed Files\\4View displayed.png"));
        
        System.out.println("View displayed. Closing view...");
        
        viewPage.closeView();
        viewPage.waitForInvisibility();
        
        System.out.println("View closed. Resetting filter...");
        
        bffPage.pressReset();
        bffPage.waitForLoad();
        
        System.out.println("Filter reset. Downloading top item...");
        
        bffPage.pressDownload();
        
        System.out.println("----------------------------------------------------");
        driver.close();
    }
    
    @Test
    public void FTPF1() throws IOException {
        //new driver instance
        WebDriver driver = new ChromeDriver();
		
        //New eComm base test to handle log-in and navigation
        Ecomm_SUSST_Base baseTest = new Ecomm_SUSST_Base(driver);
        Ecomm_MainPage eCommPage = baseTest.SUSST_SetUp("FTP Failed Files Page FTPF1: Page and filter checks, view, reset, download", "G_D_FTPFF_1");
				
        Ecomm_FTPFailedFilesPage ftpPage = eCommPage.clickFTPFailedFiles();
        ftpPage.waitForLoad();
            
        //Take a screenshot
        File scrFile1 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile1,new File(DataItems.screenshotsFilepath+"\\EComm\\Dashboard\\FTP Failed Files\\1FTP Failed Files Page.png"));
		
        System.out.println("FTP Failed Files page reached.");
		
        ftpPage.assertBaseElements();
		
        System.out.println("Checking title...");
            
        Assert.assertTrue("FTP Failed Files Page: Title not displayed as expected",ftpPage.getBreadcrumbText2().equals("FTP Failed Files"));
            
        System.out.println("Title checked. Checking fields...");
		
        ftpPage.checkFields();
            
        System.out.println("Fields checked. Entering filter criteria...");
            
        ftpPage.setCustName(DataItems.custDetails[0]);
        
        //Take a screenshot
        File scrFile2 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile2,new File(DataItems.screenshotsFilepath+"\\EComm\\Dashboard\\FTP Failed Files\\2Filter criteria entered.png"));
        
        System.out.println("Filter criteria entered. Lisitng files...");
        
        ftpPage.pressSearch();
        ftpPage.waitForLoad();
        
        //Take a screenshot
        File scrFile3 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile3,new File(DataItems.screenshotsFilepath+"\\EComm\\Dashboard\\FTP Failed Files\\3Files listed.png"));
        
        System.out.println("Files listed. Viewing top item...");
        
        Ecomm_OrderViewPage viewPage = ftpPage.pressView();
        viewPage.waitForErrorTable();
        
        //Take a screenshot
        File scrFile4 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile4,new File(DataItems.screenshotsFilepath+"\\EComm\\Dashboard\\FTP Failed Files\\4View displayed.png"));
        
        System.out.println("View displayed. Closing view...");
        
        viewPage.closeView();
        viewPage.waitForInvisibility();
        
        System.out.println("View closed. Resetting filter...");
        
        ftpPage.pressReset();
        ftpPage.waitForLoad();
        
        System.out.println("Filter reset. Downloading top item...");
        
        ftpPage.pressDownload();
        
        System.out.println("----------------------------------------------------");
        driver.close();
    }
}
