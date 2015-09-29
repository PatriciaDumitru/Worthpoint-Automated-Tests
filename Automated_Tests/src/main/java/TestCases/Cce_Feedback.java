
package TestCases;

import AutomationFramework.TestSuite;
import PageObjects.CcePage;
import PageObjects.FeedbackAwaitingPage_CCE;
import PageObjects.FeedbackCompletedPage_CCE;
import PageObjects.FeedbackPage_CCE;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class Cce_Feedback {
 
    @Test
    public void FB1() {
        //New driver object to control browser
        WebDriver driver = new ChromeDriver();
        
        //New base object to handle log-in and set up
        Cce_SOC_Base base = new Cce_SOC_Base(driver);
        
        //Set up returns a CCE Page and outputs test details
        CcePage ccePage = base.SUSST_SetUp("Feedback FB1: Single feedback, using an order number to start process", "G_CCE_FB_1");
        
        System.out.println("Navigating to Feedback...");
        
        FeedbackPage_CCE fbPage = ccePage.pressFeedback();
        
        System.out.println("Feedback page loaded. Checking title...");
        
        //Check title is displayed correctly
        Assert.assertTrue("Feedback Page: Title of page not displayed as expected", fbPage.getBreadcrumb().getText().equals("Orders | Feedback"));
        
        System.out.println("Title checked. Asserting base elements...");
        
        fbPage.assertBaseElements();
        
        System.out.println("Base elements displayed correclty. Checking fields...");
        
        fbPage.checkFields();
        
        System.out.println("Fields checked. Test complete.");
        
        System.out.println("----------------------------------------------------");
        
        driver.quit();
        
    } //Check fields
    
    @Test
    public void FB2() throws InterruptedException {
        //New driver object to control browser
        WebDriver driver = new ChromeDriver();
        
        //New base object to handle log-in and set up
        Cce_SOC_Base base = new Cce_SOC_Base(driver);
        
        //Set up returns a CCE Page and outputs test details
        CcePage ccePage = base.SUSST_SetUp("Feedback FB2: Satisfied feedback", "G_CCE_FB_1");
        
        System.out.println("Navigating to Feedback...");
        
        FeedbackPage_CCE fbPage = ccePage.pressFeedback();
        
        System.out.println("Feedback page loaded. Entering order no. and requester: ");
        
        fbPage.setOrderNo(TestSuite.sampOrderNo);      
        
        System.out.println("Criteria entered. Entering Yes/No: ");
        fbPage.pressYesSatisfied();
        
        System.out.println("Yes selected. Saving feedback...");
        
        fbPage.pressSave();
        
        System.out.println("Feedback saved.");
        
        System.out.println("----------------------------------------------------");
        
        driver.quit();
        
    } //Submit feedback process: satisfied
    
    @Test
    public void FB3() throws InterruptedException {
        //New driver object to control browser
        WebDriver driver = new ChromeDriver();
        
        //New base object to handle log-in and set up
        Cce_SOC_Base base = new Cce_SOC_Base(driver);
        
        //Set up returns a CCE Page and outputs test details
        CcePage ccePage = base.SUSST_SetUp("Feedback FB3: Dissatisfied feedback", "G_CCE_FB_1");
        
        System.out.println("Navigating to Feedback...");
        
        FeedbackPage_CCE fbPage = ccePage.pressFeedback();
        
        System.out.println("Feedback page loaded. Entering order no. and requester: ");
        
        fbPage.setOrderNo(TestSuite.sampOrderNo);      
        
        System.out.println("Criteria entered. (Satisfied?) Entering No: ");
        
        fbPage.pressNoSatisfied();
        
        System.out.println("No selected. (Rematch?) Entering No: ");
        
        fbPage.pressNoRematch();
        
        System.out.println("No selected. Providing reason...");
        
        fbPage.setReason("Too Dull");
        
        System.out.println("Reason set. Saving feedback...");
        
        fbPage.pressSave();
        
        System.out.println("Feedback saved.");
        
        System.out.println("----------------------------------------------------");
        
        driver.quit();
    } //Submit feedback process: dissatisfied
    
    @Test
    public void FBA1() throws InterruptedException {
        //New driver object to control browser
        WebDriver driver = new ChromeDriver();
        
        //New base object to handle log-in and set up
        Cce_SOC_Base base = new Cce_SOC_Base(driver);
        
        //Set up returns a CCE Page and outputs test details
        CcePage ccePage = base.SUSST_SetUp("Feedback FBA1: Feedback Awaiting page checks", "G_CCE_FBA_1");
        
        System.out.println("Navigating to Feedback Awaiting...");
        
        FeedbackAwaitingPage_CCE fbaPage = ccePage.pressFeedbackAwaiting();
        
        System.out.println("Feedback Awaiting page reached. Checking title...");
        
        Assert.assertTrue("Feedback Awaiting Page: Title not as expected",fbaPage.getBreadcrumb().equals("Orders | Feedback Awaiting"));
        
        System.out.println("Title checked. Asserting base elements...");
        
        fbaPage.assertBaseElements();
        
        System.out.println("Base elements asserted. Checking fields...");
        
        fbaPage.checkFields();
        
        System.out.println("Fields checked.");
        
        System.out.println("----------------------------------------------------");
        
        driver.quit();
        
        
    }
    
    @Test
    public void FBA2() throws InterruptedException {
        //New driver object to control browser
        WebDriver driver = new ChromeDriver();
        
        //New base object to handle log-in and set up
        Cce_SOC_Base base = new Cce_SOC_Base(driver);
        
        //Set up returns a CCE Page and outputs test details
        CcePage ccePage = base.SUSST_SetUp("Feedback FBA2: Filter functioning", "G_CCE_FBA_2");
        
        System.out.println("Navigating to Feedback Awaiting...");
        
        FeedbackAwaitingPage_CCE fbaPage = ccePage.pressFeedbackAwaiting();
        
        System.out.println("Feedback Awaiting page reached. Entering filter criteria...");
        
        fbaPage.setCustName(TestSuite.custDetails[0]);
        
        System.out.println("Criteria entered. Listing orders...");
        
        fbaPage.pressListOrders();
        
        System.out.println("Orders listed. Loading top item...");
        
        FeedbackPage_CCE fbPage = fbaPage.pressLoad();
        
        System.out.println("----------------------------------------------------");
        
        driver.quit();
        
        
    } //Filter orders and load
    
    @Test
    public void FBA3() {
        //New driver object to control browser
        WebDriver driver = new ChromeDriver();
        
        //New base object to handle log-in and set up
        Cce_SOC_Base base = new Cce_SOC_Base(driver);
        
        //Set up returns a CCE Page and outputs test details
        CcePage ccePage = base.SUSST_SetUp("Feedback FBA2: Filter functioning", "G_CCE_FBA_2");
        
        System.out.println("Navigating to Feedback Awaiting...");
        
        FeedbackAwaitingPage_CCE fbaPage = ccePage.pressFeedbackAwaiting();
        
        System.out.println("Feedback Awaiting page reached. Entering filter criteria...");
        
        fbaPage.setCustName(TestSuite.custDetails[0]);
        
        System.out.println("Criteria entered. Listing orders...");
        
        fbaPage.pressListOrders();
        
        System.out.println("Orders listed. Accepting top item with code '"+TestSuite.acceptCode+"'...");
        
        fbaPage.pressAccept(TestSuite.acceptCode);
        
        System.out.println("Accept pressed. Saving feedback...");
        
        FeedbackAwaitingPage_CCE fbPage = fbaPage.pressSave();
        
        System.out.println("----------------------------------------------------");
        
        driver.quit();
               
    } //Accept awaiting feedback
    
    @Test
    public void FC1() {
        //New driver object to control browser
        WebDriver driver = new ChromeDriver();
        
        //New base object to handle log-in and set up
        Cce_SOC_Base base = new Cce_SOC_Base(driver);
        
        //Set up returns a CCE Page and outputs test details
        CcePage ccePage = base.SUSST_SetUp("Feedback FC1: Check fields", "G_CCE_FC_1");
        
        System.out.println("Navigating to Feedback Completed...");
        
        FeedbackCompletedPage_CCE fcPage = ccePage.pressFeedbackCompleted();
        
        System.out.println("Feedback completed reached. Checking page title...");
        
        Assert.assertTrue("Feedback Completed Page: title not as expected",fcPage.getTitle().equals("Orders | Feedback Completed"));
        
        fcPage.assertBaseElements();
        
        System.out.println("Base elements asserted. Checking fields...");
        
        fcPage.checkFields();
        
        System.out.println("Fields checked.");
        
        System.out.println("----------------------------------------------------");
        
        driver.quit();
    } //Check fields
    
    @Test
    public void FC2() {
        //New driver object to control browser
        WebDriver driver = new ChromeDriver();
        
        //New base object to handle log-in and set up
        Cce_SOC_Base base = new Cce_SOC_Base(driver);
        
        //Set up returns a CCE Page and outputs test details
        CcePage ccePage = base.SUSST_SetUp("Feedback FC1: Check fields", "G_CCE_FC_1");
        
        System.out.println("Navigating to Feedback Completed...");
        
        FeedbackCompletedPage_CCE fcPage = ccePage.pressFeedbackCompleted();
        
        System.out.println("Feedback completed reached. Entering filter criteria...");
        
        fcPage.setCustName(TestSuite.custDetails[0]);
        fcPage.setRematch("No");
        fcPage.setShadeCode(TestSuite.expShadeCode2);
        
        System.out.println("Criteria entered. Listing orders...");
        
        fcPage.pressListOrders();
        
        System.out.println("Orders listed. Exporting orders to excel spreadsheet...");
        
        fcPage.exportAs("xlsx");
        
        System.out.println("File Exported.");
        
        System.out.println("----------------------------------------------------");
        
        
    } //Filter and export test
        
    
}
