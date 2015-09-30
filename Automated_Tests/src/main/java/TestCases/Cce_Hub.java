
package TestCases;

import AutomationFramework.TestSuite;
import PageObjects.CcePage;
import PageObjects.FeedbackPage_CCE;
import PageObjects.HubSosPage_CCE;
import PageObjects.OrderViewPage_CCE;
import PageObjects.ReceivedHubPage_CCE;
import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class Cce_Hub {
    
    @Ignore @Test
    public void HS1() throws InterruptedException, IOException {
        //New driver object
        WebDriver driver = new ChromeDriver();
        
        //New base object to handle log-in and set up
        Cce_SOC_Base base = new Cce_SOC_Base(driver);
        
        //Set up returns a CCE Page and outputs test details
        CcePage ccePage = base.SUSST_SetUp("Hub SOS: Page and filter checks", "C_CCE_HS_1-4");
        
        System.out.println("Navigating to Hub SOS...");
        
        HubSosPage_CCE hsPage = ccePage.pressHubSos();
        hsPage.waitForLoad();
        
        System.out.println("Hub SOS reached. Checking title...");
        
        //Take a screenshot
        File scrFile1 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile1,new File(TestSuite.screenshotFolder+"\\CCE\\Hub\\Hub SOS\\1Hub SOS Page.png"));
        
        Assert.assertTrue("Hub SOS Page: Title not displayed as expected",hsPage.getTitle().equals("Orders | Hub SOS"));
        
        System.out.println("Title checked. Asserting base elements...");
        
        hsPage.assertBaseElements();
        
        System.out.println("Base elements asseted. Checking fields...");
        
        hsPage.checkFields();
        
        System.out.println("Entering filter criteria...");
        
        hsPage.setCustName(TestSuite.custDetails[0]);
        hsPage.setBrand(TestSuite.expBrand);
        
        //Take a screenshot
        File scrFile2 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile2,new File(TestSuite.screenshotFolder+"\\CCE\\Hub\\Hub SOS\\2Filter criteria entered.png"));
        
        System.out.println("Criteria entered. Pressing list orders...");
        
        hsPage.pressListOrders();
        hsPage.waitForLoad();
        
        //Take a screenshot
        File scrFile3 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile3,new File(TestSuite.screenshotFolder+"\\CCE\\Hub\\Hub SOS\\3Orders listed.png"));
        
        System.out.println("Orders listed. Viewing first item...");
        
        OrderViewPage_CCE viewPage = hsPage.pressView();
        viewPage.waitForContent();
        
        //Take a screenshot
        File scrFile4 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile4,new File(TestSuite.screenshotFolder+"\\CCE\\Hub\\Hub SOS\\4Order view.png"));
        
        System.out.println("View reached. Closing view...");
        
        hsPage.closeView();
        
        System.out.println("View closed. Adjusting MUM type of first item...");
        
        String mumType = TestSuite.viconeMUM;
        hsPage.adjustMUMType(mumType);
        
         //Take a screenshot
        File scrFile5 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile5,new File(TestSuite.screenshotFolder+"\\CCE\\Hub\\Hub SOS\\5MUM type adjusted.png"));
        
        System.out.println("MUM type adjusted to " + mumType+". Adjusting SOS assignment...");
        
        String assignment = "SOS Lab";
        hsPage.adjustAssignment(assignment);
        
        //Take a screenshot
        File scrFile6 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile6,new File(TestSuite.screenshotFolder+"\\CCE\\Hub\\Hub SOS\\6SOS assignment adjusted.png"));
        
        System.out.println("SOS assignment set. Saving changes...");
        
        hsPage.pressSave();
        hsPage.waitForLoad();
        
        //Take a screenshot
        File scrFile7 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile7,new File(TestSuite.screenshotFolder+"\\CCE\\Hub\\Hub SOS\\7Changes saved.png"));
        
        System.out.println("----------------------------------------------------");
        
        driver.quit();
    } //Field checks, view check, adjust and save
    
    @Test public void RH1() throws IOException {
        //New driver object
        WebDriver driver = new ChromeDriver();
        
        //New base object to handle log-in and set up
        Cce_SOC_Base base = new Cce_SOC_Base(driver);
        
        //Set up returns a CCE Page and outputs test details
        CcePage ccePage = base.SUSST_SetUp("Receieved Hub: Page and filter checks", "C_CCE_RH_1-3");
        
        System.out.println("Navigating to Received Hub...");
        
        ReceivedHubPage_CCE rhPage = ccePage.pressReceivedHub();
        rhPage.waitForLoad();
        
        //Take a screenshot
        File scrFile1 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile1,new File(TestSuite.screenshotFolder+"\\CCE\\Hub\\Received Hub\\1Received Hub Page.png"));
        
        System.out.println("Received Hub reached. Checking title...");
        
        Assert.assertTrue("Received Hub Page: Title not displayed as expected",rhPage.getTitle().equals("Orders | Received Hub"));
        
        System.out.println("Title checked. Asserting base elements...");
        
        rhPage.assertBaseElements();
        
        System.out.println("Base elements asserted. Checking fields...");
        
        rhPage.checkFields();
        
        System.out.println("Fields checked. Entering filter criteria...");
        
        rhPage.setCustName(TestSuite.custDetails[0]);
        
         //Take a screenshot
        File scrFile2 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile2,new File(TestSuite.screenshotFolder+"\\CCE\\Hub\\Received Hub\\2Filter criteria entered.png"));
        
        System.out.println("Criteria entered. Listing orders...");
        
        rhPage.pressListOrders();
        rhPage.waitForLoad();
        
        //Take a screenshot
        File scrFile3 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile3,new File(TestSuite.screenshotFolder+"\\CCE\\Hub\\Received Hub\\3Orders listed.png"));
        
        System.out.println("Orders listed. Resetting filter...");
        
        rhPage.pressReset();
        rhPage.waitForLoad();
        
        //Take a screenshot
        File scrFile4 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile4,new File(TestSuite.screenshotFolder+"\\CCE\\Hub\\Received Hub\\4Filter reset.png"));
        
        System.out.println("Filter reset. Viewing first order...");
        
        OrderViewPage_CCE orderView = rhPage.pressView();
        orderView.switchTo();
        orderView.waitForContent();
        
        //Take a screenshot
        File scrFile5 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile5,new File(TestSuite.screenshotFolder+"\\CCE\\Hub\\Received Hub\\5Order View.png"));
        
        System.out.println("View displayed. Closing view...");
        
        orderView.closeView();
        
        System.out.println("Order view closed. Pressing send to customer for first item...");
        
        rhPage.pressSendToCust();
        
        //Take a screenshot
        File scrFile6 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile6,new File(TestSuite.screenshotFolder+"\\CCE\\Hub\\Received Hub\\6Send to Cust pressed.png"));
        
        System.out.println("Send to customer selected. Saving...");
        
        rhPage.pressSave();
        rhPage.waitForLoad();
        
         //Take a screenshot
        File scrFile7 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile7,new File(TestSuite.screenshotFolder+"\\CCE\\Hub\\Received Hub\\7Order saved.png"));
        
        System.out.println("----------------------------------------------------");
        
        driver.quit();
    } //Page checks, filter check, reset and view, send to customer
        
    
}
