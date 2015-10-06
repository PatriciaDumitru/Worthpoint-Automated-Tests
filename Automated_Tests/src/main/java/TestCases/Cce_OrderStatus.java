
package TestCases;

import AutomationFramework.*;
import PageObjects.CCE_MainPage;
import PageObjects.Ecomm_ExportDownloadPage;
import PageObjects.CCE_OrderSamplesPage;
import PageObjects.CCE_OrderStatusPage;
import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class Cce_OrderStatus {
    
    @Test //Order status page :: page and filter checks, reset function, export data
    public void OS1() throws IOException {
        //New driver object to control browser
        WebDriver driver = new ChromeDriver();
        
        //New base object to handle log-in and set up
        Cce_SOC_Base base = new Cce_SOC_Base(driver);
        
        //Set up returns a CCE Page and outputs test details
        CCE_MainPage ccePage = base.SUSST_SetUp("ORDER STATUS OS1: Export data", "G_CCE_OS_3");
        
        System.out.println("Navigating to Order Status...");
        
        CCE_OrderStatusPage orderStatus = ccePage.pressOrderStatus();
        orderStatus.waitForLoad();
        
        System.out.println("Order Status reached.");
        
        //Take a screenshot
        File scrFile1 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile1,new File(TestSuite.screenshotsFilepath+"\\CCE\\Orders\\Order Status\\1Order Status Page.png"));
        
        orderStatus.assertBaseElements();
        
        System.out.println("Checking fields...");
        
        orderStatus.checkFields();
        
        System.out.println("Fields checked. Entering filter criteria...");
        
        orderStatus.setCustName(TestSuite.custDetails[0]);
        orderStatus.setShadeCode(TestSuite.expShadeCode2);
        
        System.out.println("Criteria entered. Listing orders...");
        
        //Take a screenshot
        File scrFile2 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile2,new File(TestSuite.screenshotsFilepath+"\\CCE\\Orders\\Order Status\\2Filter criteria entered.png"));
        
        orderStatus.pressListOrders();
        orderStatus.waitForLoad();
        
        //Take a screenshot
        File scrFile3 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile3,new File(TestSuite.screenshotsFilepath+"\\CCE\\Orders\\Order Status\\3Orders listed.png"));
        
        System.out.println("Orders listed. Exporting data...");
        
        Ecomm_ExportDownloadPage dlPage = orderStatus.pressExport();
        dlPage.waitForDownloadCompletion();
        
        System.out.println("Exported. Resetting filter...");
        
        orderStatus.pressReset();
        orderStatus.waitForLoad();
        
        System.out.println("Filter reset.");
        
        //Take a screenshot
        File scrFile4 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile4,new File(TestSuite.screenshotsFilepath+"\\CCE\\Orders\\Order Status\\4Filter reset.png"));
        
        System.out.println("----------------------------------------------------");
        
        driver.close();
        
    }
        
    
}
