
package TestCases;

import AutomationFramework.TestSuite;
import PageObjects.WBA_ContinuePage;
import PageObjects.Ecomm_MainPage;
import PageObjects.WBA_LoginPage;
import PageObjects.Ecomm_ManualEntryPage;
import PageObjects.Ecomm_OrderConfirmationPage;
import PageObjects.Ecomm_OrderViewPage;
import PageObjects.Ecomm_OutstandingOrderDraftPage;
import PageObjects.Ecomm_OutstandingOrdersPage;
import PageObjects.Ecomm_OutstandingUploadDraftPage;
import PageObjects.Ecomm_UploadConfirmationPage;
import PageObjects.WBA_SelectionPage;
import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class Ecomm_OOD {
    
    @Test //Outstanding Order Drafts Page :: Complete an order from draft
    public void ODP2() throws IOException {
        WebDriver driver = new ChromeDriver();
        
        System.out.println("OUTSTANDING ORDER DRAFT TEST 2: Complete order from draft");
        System.out.println("Scenario ID: G_OP_ODP_3");
        
        //navigate to QA site
        driver.get(TestSuite.targetURL);
    
        //maximise browser window
        driver.manage().window().maximize();
        
        System.out.println("Logging in...");
    
        //new LoginPage
        WBA_LoginPage liPage = new WBA_LoginPage(driver);

        //log in
        WBA_ContinuePage cont = liPage.loginAs(TestSuite.validCoatsUsername,TestSuite.validCoatsPassword);

        System.out.println("Logged in. Continuing to selection page...");

        //press continue, select eComm
        WBA_SelectionPage selectPage = cont.pressContinue();

        System.out.println("Selection page loaded. eComm selected...");

        Ecomm_MainPage eCommPage = selectPage.pressEcomm();

        System.out.println("eComm page loaded. Selecting Outstanding Orders Draft page...");
        
        Ecomm_OutstandingOrderDraftPage draftPage = eCommPage.clickOutstandingDraft();
        
        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile,new File(TestSuite.screenshotsFilepath+"\\EComm\\Outstanding Orders\\Oustanding Orders Draft\\1Outstanding Orders Draft Page.png"));
                
        System.out.println("Draft page reached. Pressing edit draft...");
        
        Ecomm_ManualEntryPage manualEntry = draftPage.pressEdit();
        
        File scrFile2 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile2,new File(TestSuite.screenshotsFilepath+"\\EComm\\Outstanding Orders\\Oustanding Orders Draft\\2Edit pressed.png"));
        
        System.out.println("Edit draft pressed. Checking all mandatory customer details are entered...");
        
        //Add checks
        
        Ecomm_OrderConfirmationPage orderConf = manualEntry.pressNext();
        
        try {
            WebElement message = driver.findElement(By.id("flashMessage"));
            if (message.getText().contains("could not")) {
                System.out.println("***Draft submission failed due to missing fields. Please create at least 2 drafts with all mandatory fields and re-run the test.***");
                System.out.println("Test will now terminate.");
            }
            File scrFile4 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile4,new File(TestSuite.screenshotsFilepath+"\\EComm\\Outstanding Orders\\Oustanding Orders Draft\\3Next pressed.png"));
        } catch (Exception e) {
            Ecomm_OutstandingOrdersPage outstOrders = orderConf.pressSubmit();
            System.out.println("Order submitted.");
            File scrFile4 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile4,new File(TestSuite.screenshotsFilepath+"\\EComm\\Outstanding Orders\\Oustanding Orders Draft\\3Submit pressed.png"));
        }
        
        driver.close();
        
        System.out.println("----------------------------------------------------");
    }
    
    @Test //Outstanding Order Drafts Page :: Page and filter checks, view, edit, and cancel
    public void ODP1() throws IOException {
        WebDriver driver = new ChromeDriver();
        
        System.out.println("OUTSTANDING ORDER DRAFT TEST 1: Page check, search, view, edit, cancel draft");
        System.out.println("Scenario ID: G_OP_ODP_1 to 5");
        
        //navigate to QA site
        driver.get("https://qawcs.coatscolourexpress.com");
    
        //maximise browser window
        driver.manage().window().maximize();
        
        System.out.println("Logging in...");
    
        //new LoginPage
        WBA_LoginPage liPage = new WBA_LoginPage(driver);

        //log in
        WBA_ContinuePage cont = liPage.loginAs(TestSuite.validCoatsUsername,TestSuite.validCoatsPassword);

        System.out.println("Logged in. Continuing to selection page...");

        //press continue, select eComm
        WBA_SelectionPage selectPage = cont.pressContinue();

        System.out.println("Selection page loaded. eComm selected...");

        Ecomm_MainPage eCommPage = selectPage.pressEcomm();

        System.out.println("eComm page loaded. Asserting base elements...");
        
        Ecomm_OutstandingOrderDraftPage draftPage = eCommPage.clickOutstandingDraft();
                
        draftPage.assertBaseElements();
        
        System.out.println("Assertions successful. Entering PO number for search criteria...");
        
        //Take a screenshot
        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile,new File(TestSuite.screenshotsFilepath+"\\Outstanding Orders\\Oustanding Orders Draft\\4PO entered.png"));
        
        draftPage.setPONumber("AutoTestPO");
        
        draftPage.pressSearch();
        
        //Take a screenshot
        File scrFile2 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile2,new File(TestSuite.screenshotsFilepath+"\\Outstanding Orders\\Oustanding Orders Draft\\5Search pressed.png"));
        
        System.out.println("Search complete. Viewing draft...");
        
        draftPage.pressView();
        
        //Take a screenshot
        File scrFile3 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile3,new File(TestSuite.screenshotsFilepath+"\\Outstanding Orders\\Oustanding Orders Draft\\6View pressed.png"));
        
        //Close view
        Ecomm_OutstandingOrderDraftPage draftPage2 = draftPage.closeView();
        
        System.out.println("Draft view closed. Pressing edit draft...");
        
        Ecomm_ManualEntryPage manualEntry = draftPage2.pressEdit();
        
        System.out.println("Edit draft pressed. Navigating back to draft search page...");
        
        driver.navigate().back();
        
        System.out.println("Search page reached. Pressing cancel draft...");
        
        //Cancel a draft
        draftPage2.pressCancel();
        
        //Take a screenshot
        File scrFile4 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile4,new File(TestSuite.screenshotsFilepath+"\\Outstanding Orders\\Oustanding Orders Draft\\7Cancel pressed.png"));
        
        System.out.println("Draft cancelled.");
        
        driver.close();
            
        System.out.println("----------------------------------------------------");
    }
       
    @Test //Upload Order Drafts Page :: Page checks, edit, and cancel upload order draft
    public void UODP1() throws IOException {
        WebDriver driver = new ChromeDriver();
        
        System.out.println("OUTSTANDING UPLOAD DRAFT TEST: Page checks, edit, cancel upload order draft");
        System.out.println("Scenario ID: G_OP_UODP_1 to 4");
        
        //navigate to QA site
        driver.get("https://qawcs.coatscolourexpress.com");
    
        //maximise browser window
        driver.manage().window().maximize();
        
        System.out.println("Logging in...");
    
        //new LoginPage
        WBA_LoginPage liPage = new WBA_LoginPage(driver);

        //log in
        WBA_ContinuePage cont = liPage.loginAs(TestSuite.validCoatsUsername,TestSuite.validCoatsPassword);

        System.out.println("Logged in. Continuing to selection page...");

        //press continue, select eComm
        WBA_SelectionPage selectPage = cont.pressContinue();

        System.out.println("Selection page loaded. eComm selected...");

        Ecomm_MainPage eCommPage = selectPage.pressEcomm();

        System.out.println("eComm page loaded. Navigating to Outstanding Upload Drafts...");
        
        Ecomm_OutstandingUploadDraftPage upDraftPage = eCommPage.clickOutstandingUploadDraft();
                
        System.out.println("Outstanding Upload Drafts reached. Asserting base elements...");
        
        //Take a screenshot
        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile,new File(TestSuite.screenshotsFilepath+"\\Outstanding Orders\\Oustanding Upload Draft\\1Outstanding Upload Draft Page.png"));
        
        upDraftPage.assertBaseElements();
        
        System.out.println("Assertions successful. Entering Customer Name for search criteria...");
        
        //Take a screenshot
        File scrFile1 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile1,new File(TestSuite.screenshotsFilepath+"\\Outstanding Orders\\Oustanding Upload Draft\\2Customer Name entered.png"));
        
        upDraftPage.setCustomerName(TestSuite.custDetails[0]);
        
        upDraftPage.pressSearch();
        
        //Take a screenshot
        File scrFile2 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile2,new File(TestSuite.screenshotsFilepath+"\\Outstanding Orders\\Oustanding Upload Draft\\3Search pressed.png"));
        
        System.out.println("Search complete. Pressing edit...");
        
        Ecomm_OrderConfirmationPage upConf = upDraftPage.pressEdit();
        
        //Take a screenshot
        File scrFile3 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile3,new File(TestSuite.screenshotsFilepath+"\\Outstanding Orders\\Oustanding Upload Draft\\4Edit pressed.png"));
        
        System.out.println("Order confirmation page reached. Asserting base elements...");
        
        //Take a screenshot
        File scrFile4 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile4,new File(TestSuite.screenshotsFilepath+"\\Outstanding Orders\\Oustanding Upload Draft\\5Confirmation page.png"));
        
        upConf.assertBaseElements();
        
        System.out.println("Assertions successful. Navigating back to Outstanding Upload Drafts...");
        
        driver.navigate().back();
        
        System.out.println("Outstanding Upload Drafts page reached. Pressing delete draft...");
        
        upDraftPage.pressDelete();
                
        //Take a screenshot
        File scrFile5 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile5,new File(TestSuite.screenshotsFilepath+"\\Outstanding Orders\\Oustanding Upload Draft\\5Draft deleted.png"));
        
        System.out.println("Draft deleted.");
        
        driver.close();
        
        System.out.println("----------------------------------------------------");
        
    }
    
    
    
}
