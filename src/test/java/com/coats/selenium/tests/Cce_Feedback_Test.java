package com.coats.selenium.tests;

import AutomationFramework.DataItems;
import PageObjects.CCE_MainPage;
import PageObjects.CCE_FeedbackAwaitingPage;
import PageObjects.CCE_FeedbackCompletedPage;
import PageObjects.CCE_FeedbackPage;
import com.coats.selenium.DriverFactory;
import static com.coats.selenium.DriverFactory.getDriver;
import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.testng.AssertJUnit;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Cce_Feedback_Test extends DriverFactory {
    
    @Test //Feedback Page :: Page and fields check
    (groups = {"CCE"})
    public void FB1() throws IOException, Exception {
        //New driver object to control browser
        WebDriver driver = getDriver();
        
        //New base object to handle log-in and set up
        Cce_Base base = new Cce_Base(driver);
        
        //Set up returns a CCE Page and outputs test details
        CCE_MainPage ccePage = base.setUp("Feedback FB1: Single feedback, using an order number to start process", "G_CCE_FB_1");
        
        System.out.println("Navigating to Feedback...");
        
        CCE_FeedbackPage fbPage = ccePage.pressFeedback();
        fbPage.waitForLoad();
        
        //Take a screenshot
        File scrFile1 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile1,new File(DataItems.screenshotsFilepath+"\\CCE\\Orders\\Feedback\\1Feedback Page.png"));
        
        System.out.println("Feedback page loaded. Checking title...");
        
        //Check title is displayed correctly
        AssertJUnit.assertTrue("Feedback Page: Title of page not displayed as expected", fbPage.getBreadcrumb().getText().equals("Orders | Feedback"));
        
        System.out.println("Title checked.");
        
        fbPage.assertBaseElements();
        
        System.out.println("Checking fields...");
        
        fbPage.checkFields();
        
        System.out.println("Fields checked. Test complete.");
        
    }
    
    @Test //Feedback Page :: Submit feedback process: satisfied
    (groups = {"CCE"})
    public void FB2() throws InterruptedException, IOException, Exception {
        //New driver object to control browser
        WebDriver driver = getDriver();
        
        //New base object to handle log-in and set up
        Cce_Base base = new Cce_Base(driver);
        
        //Set up returns a CCE Page and outputs test details
        CCE_MainPage ccePage = base.setUp("Feedback FB2: Satisfied feedback", "G_CCE_FB_1");
        
        System.out.println("Navigating to Feedback Awaiting to find appropriate Order No...");
        
        CCE_FeedbackAwaitingPage fbaPage = ccePage.pressFeedbackAwaiting();
        fbaPage.waitForElement();
        
        System.out.println("Feedback Awaiting page reached. Finding order no...");
        
        String orderNo = fbaPage.findOrderAwaitingFeedback(DataItems.custDetails[0]);
        
        if (!orderNo.equals("not found")) {
            DataItems.sampOrderNo = orderNo;
        }
        
        System.out.println("Navigating to Feedback...");
        
        CCE_FeedbackPage fbPage = ccePage.pressFeedback();
        
        System.out.println("Feedback page loaded. Entering order no. and requester: ");
        
        fbPage.setOrderNo(DataItems.sampOrderNo);       
        fbPage.setRequester(DataItems.custDetails[2]);
        
        System.out.println("Criteria entered. Entering Yes/No: ");
        fbPage.pressYesSatisfied();
        
        fbPage.setRequester(DataItems.sampRequester);
        
        //Take a screenshot
        File scrFile2 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile2,new File(DataItems.screenshotsFilepath+"\\CCE\\Orders\\Feedback\\2Criteria entered.png"));
        
        System.out.println("Yes selected. Saving feedback...");
        
        fbPage.pressSave();
        fbPage.waitForLoad();
        
        //Take a screenshot
        File scrFile3 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile3,new File(DataItems.screenshotsFilepath+"\\CCE\\Orders\\Feedback\\3Feedback saved.png"));
        
        System.out.println("Feedback saved.");
        
    }
    
    @Test //Feedback Page :: Submit feedback process: dissatisfied
    (groups = {"CCE"})
    public void FB3() throws InterruptedException, IOException, Exception {
        //New driver object to control browser
        WebDriver driver = getDriver();
        
        //New base object to handle log-in and set up
        Cce_Base base = new Cce_Base(driver);
        
        //Set up returns a CCE Page and outputs test details
        CCE_MainPage ccePage = base.setUp("Feedback FB3: Dissatisfied feedback", "G_CCE_FB_1");
        
        System.out.println("Navigating to Feedback Awaiting to find appropriate Order No...");
        
        CCE_FeedbackAwaitingPage fbaPage = ccePage.pressFeedbackAwaiting();
        fbaPage.waitForElement();
        
        System.out.println("Feedback Awaiting page reached. Finding order no...");
        
        String orderNo = fbaPage.findOrderAwaitingFeedback(DataItems.custDetails[0]);
        
        if (!orderNo.equals("not found")) {
            DataItems.sampOrderNo = orderNo;
        }
        
        System.out.println("Navigating to Feedback...");
        
        CCE_FeedbackPage fbPage = ccePage.pressFeedback();
        
        System.out.println("Feedback page loaded. Entering order no. and requester: ");
        
        fbPage.setOrderNo(DataItems.sampOrderNo);   
        fbPage.setRequester(DataItems.custDetails[2]);
        
        System.out.println("Criteria entered. (Satisfied?) Entering No: ");
        
        fbPage.pressNoSatisfied();
        
        //Take a screenshot
        File scrFile4 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile4,new File(DataItems.screenshotsFilepath+"\\CCE\\Orders\\Feedback\\4Not satisfied.png"));
        
        System.out.println("No selected. (Rematch?) Entering No: ");
        
        fbPage.pressNoRematch();
        
        //Take a screenshot
        File scrFile5 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile5,new File(DataItems.screenshotsFilepath+"\\CCE\\Orders\\Feedback\\5No rematch.png"));
        
        System.out.println("No selected. Providing reason...");
        
        fbPage.setReason("Too Dull");
        
        //Take a screenshot
        File scrFile6 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile6,new File(DataItems.screenshotsFilepath+"\\CCE\\Orders\\Feedback\\6Reason set.png"));
        
        System.out.println("Reason set. Saving feedback...");
        
        fbPage.pressSave();
        fbPage.waitForLoad();
        
        //Take a screenshot
        File scrFile7 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile4,new File(DataItems.screenshotsFilepath+"\\CCE\\Orders\\Feedback\\7Feedback saved.png"));
        
        System.out.println("Feedback saved.");
        
    } 
    
    @Test //Feedback Awaiting Page :: Page and fields check
    (groups = {"CCE"})
    public void FBA1() throws InterruptedException, IOException, Exception {
        //New driver object to control browser
        WebDriver driver = getDriver();
        
        //New base object to handle log-in and set up
        Cce_Base base = new Cce_Base(driver);
        
        //Set up returns a CCE Page and outputs test details
        CCE_MainPage ccePage = base.setUp("Feedback FBA1: Feedback Awaiting page checks", "G_CCE_FBA_1");
        
        System.out.println("Navigating to Feedback Awaiting...");
        
        CCE_FeedbackAwaitingPage fbaPage = ccePage.pressFeedbackAwaiting();
        fbaPage.waitForLoad();
        
        //Take a screenshot
        File scrFile1 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile1,new File(DataItems.screenshotsFilepath+"\\CCE\\Orders\\Feedback Awaiting\\1Feedback awaiting page.png"));
        
        System.out.println("Feedback Awaiting page reached. Checking title...");
        
        AssertJUnit.assertTrue("Feedback Awaiting Page: Title not as expected",fbaPage.getBreadcrumb().equals("Orders | Feedback Awaiting"));
        
        System.out.println("Title checked.");
        
        fbaPage.assertBaseElements();
        
        System.out.println("Checking fields...");
        
        fbaPage.checkFields();
        
        System.out.println("Fields checked.");
        
    }
    
    @Test //Feedback Awaiting Page :: Filter orders and load
    (groups = {"CCE"}) 
    public void FBA2() throws InterruptedException, IOException, Exception {
        //New driver object to control browser
        WebDriver driver = getDriver();
        
        //New base object to handle log-in and set up
        Cce_Base base = new Cce_Base(driver);
        
        //Set up returns a CCE Page and outputs test details
        CCE_MainPage ccePage = base.setUp("Feedback FBA2: Filter functioning", "G_CCE_FBA_2");
        
        System.out.println("Navigating to Feedback Awaiting...");
        
        CCE_FeedbackAwaitingPage fbaPage = ccePage.pressFeedbackAwaiting();
        
        System.out.println("Feedback Awaiting page reached. Entering filter criteria...");
        
        fbaPage.setCustName(DataItems.custDetails[0]);
        
        //Take a screenshot
        File scrFile2 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile2,new File(DataItems.screenshotsFilepath+"\\CCE\\Orders\\Feedback Awaiting\\2Filter criteria entered.png"));
        
        System.out.println("Criteria entered. Listing orders...");
        
        fbaPage.pressListOrders();
        fbaPage.waitForLoad();
        
        //Take a screenshot
        File scrFile3 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile3,new File(DataItems.screenshotsFilepath+"\\CCE\\Orders\\Feedback Awaiting\\3Orders listed.png"));
        
        System.out.println("Orders listed. Loading top item...");
        
        CCE_FeedbackPage fbPage = fbaPage.pressLoad();
        fbPage.waitForLoad();
        
        //Take a screenshot
        File scrFile1 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile1,new File(DataItems.screenshotsFilepath+"\\CCE\\Orders\\Feedback Awaiting\\4Feedback page loaded.png"));
        
    }
    
    @Test //Feedback Awaiting Page :: Accept awaiting feedback with code
    (groups = {"CCE"})
    public void FBA3() throws IOException, Exception {
        //New driver object to control browser
        WebDriver driver = getDriver();
        
        //New base object to handle log-in and set up
        Cce_Base base = new Cce_Base(driver);
        
        //Set up returns a CCE Page and outputs test details
        CCE_MainPage ccePage = base.setUp("Feedback FBA2: Filter functioning", "G_CCE_FBA_2");
        
        System.out.println("Navigating to Feedback Awaiting...");
        
        CCE_FeedbackAwaitingPage fbaPage = ccePage.pressFeedbackAwaiting();
        
        System.out.println("Feedback Awaiting page reached. Entering filter criteria...");
        
        fbaPage.setCustName(DataItems.custDetails[0]);
        
        System.out.println("Criteria entered. Listing orders...");
        
        fbaPage.pressListOrders();
        
        System.out.println("Orders listed. Accepting top item with code '"+DataItems.acceptCode+"'...");
        
        fbaPage.pressAccept(DataItems.acceptCode);
        
        //Take a screenshot
        File scrFile5 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile5,new File(DataItems.screenshotsFilepath+"\\CCE\\Orders\\Feedback Awaiting\\5Accept code entered.png"));
        
        System.out.println("Accept pressed. Saving feedback...");
        
        CCE_FeedbackAwaitingPage fbPage = fbaPage.pressSave();
        fbPage.waitForLoad();
        
        //Take a screenshot
        File scrFile1 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile1,new File(DataItems.screenshotsFilepath+"\\CCE\\Orders\\Feedback Awaiting\\6Accept saved.png"));
               
    }
    
    @Test //Feedback Completed Page :: Page and field checks
    (groups = {"CCE"})
    public void FC1() throws IOException, Exception {
        //New driver object to control browser
        WebDriver driver = getDriver();
        
        //New base object to handle log-in and set up
        Cce_Base base = new Cce_Base(driver);
        
        //Set up returns a CCE Page and outputs test details
        CCE_MainPage ccePage = base.setUp("Feedback FC1: Check fields", "G_CCE_FC_1");
        
        System.out.println("Navigating to Feedback Completed...");
        
        CCE_FeedbackCompletedPage fcPage = ccePage.pressFeedbackCompleted();
        fcPage.waitForLoad();
        
        //Take a screenshot
        File scrFile1 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile1,new File(DataItems.screenshotsFilepath+"\\CCE\\Orders\\Feedback Completed\\1Feedback completed page.png"));
        
        System.out.println("Feedback completed reached. Checking page title...");
        
        AssertJUnit.assertTrue("Feedback Completed Page: title not as expected",fcPage.getTitle().equals("Orders | Feedback Completed"));
        
        fcPage.assertBaseElements();
        
        System.out.println("Checking fields...");
        
        fcPage.checkFields();
        
        System.out.println("Fields checked.");
        
    }
    
    @Test //Feedback Completed Page :: Filter and export test
    (groups = {"CCE"})
    public void FC2() throws IOException, Exception {
        //New driver object to control browser
        WebDriver driver = getDriver();
        
        //New base object to handle log-in and set up
        Cce_Base base = new Cce_Base(driver);
        
        //Set up returns a CCE Page and outputs test details
        CCE_MainPage ccePage = base.setUp("Feedback FC1: Check fields", "G_CCE_FC_1");
        
        System.out.println("Navigating to Feedback Completed...");
        
        CCE_FeedbackCompletedPage fcPage = ccePage.pressFeedbackCompleted();
        
        System.out.println("Feedback completed reached. Entering filter criteria...");
        
        fcPage.setCustName(DataItems.custDetails[0]);
        fcPage.setRematch("No");
        fcPage.setShadeCode(DataItems.expShadeCode2);
        
        //Take a screenshot
        File scrFile2 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile2,new File(DataItems.screenshotsFilepath+"\\CCE\\Orders\\Feedback Completed\\2Filter criteria entered.png"));
        
        System.out.println("Criteria entered. Listing orders...");
        
        fcPage.pressListOrders();
        fcPage.waitForLoad();
        
        //Take a screenshot
        File scrFile3 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile3,new File(DataItems.screenshotsFilepath+"\\CCE\\Orders\\Feedback Completed\\3Orders listed.png"));
        
        System.out.println("Orders listed. Exporting orders to excel spreadsheet...");
        
        fcPage.exportAs("xlsx");
        
        System.out.println("File Exported.");

    } 

}
