
package TestCases;

import AutomationFramework.TestSuite;
import PageObjects.CcePage;
import PageObjects.ExportDownloadPage_CCE;
import PageObjects.LRMLogPage_CCE;
import PageObjects.OrderViewPage_CCE;
import PageObjects.TotalOrdersPage_CCE;
import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class Cce_LRMLog {
    
    @Test
    public void LRM1() throws IOException {
        //New driver object
        WebDriver driver = new ChromeDriver();
        
        //New base object to handle log-in and set up
        Cce_SOC_Base base = new Cce_SOC_Base(driver);
        
        //Set up returns a CCE Page and outputs test details
        CcePage ccePage = base.SUSST_SetUp("LRM Log: Page and filter checks, view, resend, export", "C_CCE_LRM_1-5");
        
        System.out.println("Navigating to LRM Log Page...");
        
        LRMLogPage_CCE lrmPage = ccePage.pressLRMLog();
        lrmPage.waitForLoad();
        
        //Take a screenshot
        File scrFile1 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile1,new File(TestSuite.screenshotFolder+"\\CCE\\Admin\\LRM Log\\1LRM Log Page.png"));
        
        System.out.println("LRM Log reached. Checking title...");
        
        Assert.assertTrue("LRM Log Page: Title not displayed as expected",lrmPage.getBreadcrumb().getText().equals("Lrm Log"));
        
        System.out.println("Title checked.");
        
        lrmPage.assertBaseElements();
        
        System.out.println("Checking fields...");
        
        lrmPage.checkFields();
        
        System.out.println("Fields checked. Entering filter criteria...");
        
        lrmPage.setShadeCode(TestSuite.expShadeCode2);
        lrmPage.setErrorMessage("Yes");
        
        //Take a screenshot
        File scrFile2 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile2,new File(TestSuite.screenshotFolder+"\\CCE\\Admin\\LRM Log\\2Filter criteria entered.png"));
        
        System.out.println("Criteria entered. List orders....");

        lrmPage.pressSearch();
        lrmPage.waitForLoad();
        
        //Take a screenshot
        File scrFile3 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile3,new File(TestSuite.screenshotFolder+"\\CCE\\Admin\\LRM Log\\3Records listed.png"));
        
         System.out.println("Orders listed. Pressing view...");
        
        OrderViewPage_CCE viewPage = lrmPage.pressView();
        viewPage.switchTo();
        viewPage.waitForContent();
        
        //Take a screenshot
        File scrFile4 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile4,new File(TestSuite.screenshotFolder+"\\CCE\\Admin\\LRM Log\\4View displayed.png"));
        
        System.out.println("View displayed. Closing view...");
        
        viewPage.closeView();
        viewPage.waitForInvisibility();
        
        //Take a screenshot
        File scrFile5 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile5,new File(TestSuite.screenshotFolder+"\\CCE\\Admin\\LRM Log\\5View closed.png"));
        
        System.out.println("View closed. Pressing re-send...");
        
        lrmPage.pressResend();
        lrmPage.waitForLoad();
        
        //Take a screenshot
        File scrFile6 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile6,new File(TestSuite.screenshotFolder+"\\CCE\\Admin\\LRM Log\\6Order resent.png"));
        
        System.out.println("Re-sent. Re-entering critera and listing orders...");
        
        lrmPage.setShadeCode(TestSuite.expShadeCode2);
        lrmPage.setErrorMessage("Yes");
        lrmPage.pressSearch();
        
        //Take a screenshot
        File scrFile7 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile7,new File(TestSuite.screenshotFolder+"\\CCE\\Admin\\LRM Log\\7Filter criteria entered.png"));
        
        System.out.println("Orders listed. Exporting records...");
        
        ExportDownloadPage_CCE dlPage = lrmPage.pressExport();
        dlPage.waitForDownloadCompletion();
        
        System.out.println("Export complete. Resetting filter...");
        
        lrmPage.pressReset();
        lrmPage.waitForLoad();
        
        //Take a screenshot
        File scrFile8 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile8,new File(TestSuite.screenshotFolder+"\\CCE\\Admin\\LRM Log\\8Filter reset.png"));
        
        System.out.println("Filter reset.");
        
        System.out.println("----------------------------------------------------");
        
        driver.quit();
              
    }
    
}
