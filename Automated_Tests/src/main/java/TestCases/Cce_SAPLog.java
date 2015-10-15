
package TestCases;

import AutomationFramework.Categories;
import AutomationFramework.DataItems;
import PageObjects.CCE_MainPage;
import PageObjects.Ecomm_ExportDownloadPage;
import PageObjects.CCE_LRMLogPage;
import PageObjects.CCE_OrderViewPage;
import PageObjects.CCE_SAPLogPage;
import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

@Category(Categories.CCE.class)
public class Cce_SAPLog {
    
    @Test //SAP Log Page :: Page and filter checks, search, reset, and export
    public void SAP1() throws IOException {
        //New driver object
        WebDriver driver = new ChromeDriver();
        
        //New base object to handle log-in and set up
        Cce_SOC_Base base = new Cce_SOC_Base(driver);
        
        //Set up returns a CCE Page and outputs test details
        CCE_MainPage ccePage = base.SUSST_SetUp("SAP Log: Page and filter checks, search, reset, export", "C_CCE_A_SAP_1-5");
        
        System.out.println("Navigating to SAP Log Page...");
        
        CCE_SAPLogPage sapPage = ccePage.pressSAPLog();
        sapPage.waitForLoad();
        
        //Take a screenshot
        File scrFile1 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile1,new File(DataItems.screenshotsFilepath+"\\CCE\\Admin\\SAP Log\\1SAP log page.png"));
        
        System.out.println("SAP Log reached. Checking title...");
        
        Assert.assertTrue("SAP Log Page: Title not displayed as expected",sapPage.getBreadcrumb().getText().equals("SAP Log"));
        
        System.out.println("Title checked.");
        
        sapPage.assertBaseElements();
        
        System.out.println("Checking fields...");
        
        sapPage.checkFields();
        
        System.out.println("Fields checked. Entering filter criteria...");
        
        sapPage.setCustName(DataItems.custDetails[0]);
        sapPage.setMUMType(DataItems.copMUM);
        sapPage.setSapStatus("Created in SAP");
        
        //Take a screenshot
        File scrFile2 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile2,new File(DataItems.screenshotsFilepath+"\\CCE\\Admin\\SAP Log\\2Filter criteria entered.png"));
        
        System.out.println("Filter criteria entered. Pressing search...");
        
        sapPage.pressSearch();
        sapPage.waitForLoad();
        
        //Take a screenshot
        File scrFile3 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile3,new File(DataItems.screenshotsFilepath+"\\CCE\\Admin\\SAP Log\\3Records listed.png"));
        
        System.out.println("Records listed. Viewing top item...");
        
        CCE_OrderViewPage viewPage = sapPage.pressView();
        viewPage.switchTo();
        viewPage.waitForContent();
        
        //Take a screenshot
        File scrFile4 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile4,new File(DataItems.screenshotsFilepath+"\\CCE\\Admin\\SAP Log\\4Record view.png"));
        
        System.out.println("View displayed. Closing view...");
        
        viewPage.closeView();
        viewPage.waitForInvisibility();
        
        System.out.println("View closed. Exporting records...");
        
        Ecomm_ExportDownloadPage dlPage = sapPage.pressExport();
        dlPage.waitForDownloadCompletion();
        
        System.out.println("Records exported. Resetting filter...");
        
        sapPage.pressReset();
        sapPage.waitForLoad();
        
        //Take a screenshot
        File scrFile5 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile5,new File(DataItems.screenshotsFilepath+"\\CCE\\Admin\\SAP Log\\5Filter reset.png"));
        
        System.out.println("Filter reset.");
        
        System.out.println("----------------------------------------------------");
        driver.close();
        driver.quit();
    }
}
