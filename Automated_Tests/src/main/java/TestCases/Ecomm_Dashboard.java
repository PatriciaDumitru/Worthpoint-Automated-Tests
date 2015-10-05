
package TestCases;

import AutomationFramework.TestSuite;
import PageObjects.BackendInProcessPage_EComm;
import PageObjects.EcommPage;
import PageObjects.MyReportsPage_EComm;
import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Ecomm_Dashboard {
    
    @Test
    public void BIP1() throws IOException {
        //new driver instance
            WebDriver driver = new ChromeDriver();
		
            //New eComm base test to handle log-in and navigation
            Ecomm_SUSST_Base baseTest = new Ecomm_SUSST_Base(driver);
            EcommPage eCommPage = baseTest.SUSST_SetUp("Backend In Process Page BIP1: Page and filter checks, reset", "G_D_BIPL");
				
            BackendInProcessPage_EComm bipPage = eCommPage.clickBackendInProcess();
            bipPage.waitForLoad();
            
            //Take a screenshot
            File scrFile1 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile1,new File(TestSuite.screenshotsFilepath+"\\EComm\\Dashboard\\Backend In Process Files\\1Backend In Process Page.png"));
		
            System.out.println("Backend In Process page reached.");
		
            bipPage.assertBaseElements();
		
            System.out.println("Checking title...");
            
            Assert.assertTrue("Backend In Process Files Page: Title not displayed as expected",bipPage.getBreadcrumbText().equals("Backend In Process Files"));
            
            System.out.println("Title checked. Checking fields...");
		
            bipPage.checkFields();
            
            System.out.println("Fields checked. Entering filter criteria...");
            
            bipPage.setCustName(TestSuite.custDetails[0]);
            
            //Take a screenshot
            File scrFile2 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile2,new File(TestSuite.screenshotsFilepath+"\\EComm\\Dashboard\\Backend In Process Files\\2Filter criteria entered.png"));
            
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
    
}
