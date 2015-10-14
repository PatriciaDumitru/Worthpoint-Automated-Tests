
package TestCases;

import AutomationFramework.Categories;
import AutomationFramework.TestSuiteOLD;
import PageObjects.CCE_MainPage;
import PageObjects.Ecomm_ExportDownloadPage;
import PageObjects.CCE_FCETaskStatusPage;
import PageObjects.CCE_OrderViewPage;
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

@Category(Categories.CCE.class)
public class Cce_FCETaskStatus {
    
    @Test //FCE Task Status Page :: Page and filter checks, print records and export
    public void TS1() throws IOException {
        //New driver object
        WebDriver driver = new ChromeDriver();
        
        //New base object to handle log-in and set up
        Cce_SOC_Base base = new Cce_SOC_Base(driver);
        
        //Set up returns a CCE Page and outputs test details
        CCE_MainPage ccePage = base.SUSST_SetUp("FCE Task Status: Page and filter checks, print, and export", "C_CCE_FCETR_1-4");
        
        System.out.println("Navigating to FCE Task Status...");
        
        CCE_FCETaskStatusPage fcePage = ccePage.pressFCETaskStatus();
        fcePage.waitForLoad();
        
        //Take a screenshot
        File scrFile1 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile1,new File(TestSuiteOLD.screenshotsFilepath+"\\CCE\\Reports\\FCE Task Status\\1FCE Task status page.png"));
        
        System.out.println("FCE Task Status reached. Checking title...");
        
        Assert.assertTrue("FCE Task Status Page: Title not displayed as expected",fcePage.getBreadcrumb().getText().equals("Reports | FCE Task Status"));
        
        System.out.println("Title checked.");
        
        fcePage.assertBaseElements();
        
        System.out.println("Checking fields...");
        
        fcePage.checkFields();
        
        System.out.println("Fields checked. Entering filter criteria...");
        
        fcePage.setSalesOrg("ID51");
        fcePage.setTaskType("Visit");
        fcePage.setTaskStatus("Completed");
        fcePage.setHub("IDH004");
        
        //Take a screenshot
        File scrFile2 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile2,new File(TestSuiteOLD.screenshotsFilepath+"\\CCE\\Reports\\FCE Task Status\\2Filter criteria entered.png"));
        
        System.out.println("Criteria entered. Printing records...");
        
        CCE_OrderViewPage viewPage = fcePage.pressPrint();
        viewPage.waitForLoad();
        
        //Take a screenshot
        File scrFile3 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile3,new File(TestSuiteOLD.screenshotsFilepath+"\\CCE\\Reports\\FCE Task Status\\3View records.png"));
        
        System.out.println("Records displayed. Closing view...");

        viewPage.closeView();
        //wait for frame to completely disappear
        viewPage.waitForInvisibility();
        
        System.out.println("View closed. Exporting records to excel...");
        
        Ecomm_ExportDownloadPage exportPage = fcePage.pressExport();
        exportPage.waitForDownloadCompletion();
        
        System.out.println("Export pressed, download completed.");
        
        System.out.println("----------------------------------------------------");
        driver.close();
    }
    
    @Test //FCE Task Status Page :: Filter reset
    public void TS2() throws IOException {
        //New driver object
        WebDriver driver = new ChromeDriver();
        
        //New base object to handle log-in and set up
        Cce_SOC_Base base = new Cce_SOC_Base(driver);
        
        //Set up returns a CCE Page and outputs test details
        CCE_MainPage ccePage = base.SUSST_SetUp("FCE Task Status: Page and filter checks, print, and export", "C_CCE_FCETR_1-4");
        
        System.out.println("Navigating to FCE Task Status...");
        
        CCE_FCETaskStatusPage fcePage = ccePage.pressFCETaskStatus();
        
        System.out.println("FCE Task Status reached. Entering filter criteria...");
        
        fcePage.setSalesOrg("ID51");
        fcePage.setTaskStatus("Completed");
        
        //Take a screenshot
        File scrFile4 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile4,new File(TestSuiteOLD.screenshotsFilepath+"\\CCE\\Reports\\FCE Task Status\\4Filter criteria entered.png"));
        
        System.out.println("Criteria entered. Pressing reset...");
        
        fcePage.pressReset();
        fcePage.waitForLoad();
        
        //Take a screenshot
        File scrFile2 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile2,new File(TestSuiteOLD.screenshotsFilepath+"\\CCE\\Reports\\FCE Task Status\\5Filter reset.png"));
        
        System.out.println("Filter reset.");
                
        System.out.println("----------------------------------------------------");
        
        driver.close();               
        
    }
    
}
