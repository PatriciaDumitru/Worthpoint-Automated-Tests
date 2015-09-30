
package TestCases;

import AutomationFramework.TestSuite;
import PageObjects.CcePage;
import PageObjects.DNPrintPage_CCE;
import PageObjects.DNReprintPage_CCE;
import com.google.common.base.Verify;
import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Cce_DNReprint {
    
    @Test
    public void DR1() throws IOException {
        //New driver object to control browser
        WebDriver driver = new ChromeDriver();
        
        //New base object to handle log-in and set up
        Cce_SOC_Base base = new Cce_SOC_Base(driver);
        
        //Set up returns a CCE Page and outputs test details
        CcePage ccePage = base.SUSST_SetUp("DN Reprint DR1: User can select all fields", "G_CCE_DR_1");
        
        System.out.println("Navigating to DN Reprint...");
        
        DNReprintPage_CCE dnReprint = ccePage.pressDNReprint();
        dnReprint.waitForLoad();
        
        //Take a screenshot
        File scrFile1 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile1,new File(TestSuite.screenshotFolder+"\\CCE\\DN Reprint\\1DN Reprint page.png"));
        
        System.out.println("DN Reprint loaded.");
        
        //Assert base elements
        dnReprint.assertBaseElements();
        
        System.out.println("Asserting page title is as expected...");
        
        //Assert page title is correct
        Verify.verify(dnReprint.getBreadcrumb().getText().equals("Orders | Delivery Notes"));
        
        System.out.println("Page title correct. Checking fields are displayed as expected...");
        
        dnReprint.checkFields();
        
        System.out.println("Test complete.");
        
        System.out.println("----------------------------------------------------");
        
        driver.quit();
        
    } //Check fields
    
    @Test
    public void DR2() throws IOException {
                
        //New driver object to control browser
        WebDriver driver = new ChromeDriver();
        
        //New base object to handle log-in and set up
        Cce_SOC_Base base = new Cce_SOC_Base(driver);
        
        //Set up returns a CCE Page and outputs test details
        CcePage ccePage = base.SUSST_SetUp("DN Reprint DR1: User can select all fields", "G_CCE_DR_2");
        
        System.out.println("Navigating to DN Reprint...");
        
        DNReprintPage_CCE dnReprint = ccePage.pressDNReprint();
        
        System.out.println("DN Reprint loaded. Entering filter criteria...");
        
        dnReprint.setShipToPartyName(TestSuite.custDetails[1]);
        
        //Take a screenshot
        File scrFile2 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile2,new File(TestSuite.screenshotFolder+"\\CCE\\DN Reprint\\2Filter criteria entered.png"));
        
        System.out.println("Criteria entered. Listing orders...");
        
        dnReprint.pressList();
        dnReprint.waitForLoad();
        
        //Take a screenshot
        File scrFile3 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile3,new File(TestSuite.screenshotFolder+"\\CCE\\DN Reprint\\3Orders listed.png"));
        
        System.out.println("Orders listed. Pressing confirm...");
        
        dnReprint.pressConfirm();
        
        //Take a screenshot
        File scrFile4 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile4,new File(TestSuite.screenshotFolder+"\\CCE\\DN Reprint\\4Confirm pressed.png"));
        
        System.out.println("Confirm pressed. Pressing print...");
        
        DNPrintPage_CCE printPage = dnReprint.pressPrint();
        
        System.out.println("Print pressed. Displaying delivery notes...");
        
        //Ensure frame is selected
        printPage.switchTo();
        
        System.out.println("Delivery notes displayed.");
        
        //Take a screenshot
        File scrFile5 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile5,new File(TestSuite.screenshotFolder+"\\CCE\\DN Reprint\\5DN displayed.png"));
        
        if (TestSuite.printingEnabled) {           
            dnReprint = printPage.pressPrint();
            System.out.println("Delivery notes sent to printer.");
        } else {
            dnReprint = printPage.pressClose();
            System.out.println("Deliveyr notes view closed.");
        }
        
        System.out.println("Test complete.");
        
        System.out.println("----------------------------------------------------");
        
        driver.quit();

    } //Filter and confirm
    
    
}
