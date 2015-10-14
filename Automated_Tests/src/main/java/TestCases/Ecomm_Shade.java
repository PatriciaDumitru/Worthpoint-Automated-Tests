
package TestCases;

import AutomationFramework.Categories;
import AutomationFramework.TestSuiteOLD;
import PageObjects.Ecomm_OrderInformationPage;
import PageObjects.Ecomm_OrderViewPage;
import PageObjects.Ecomm_OutstandingOrdersPage;
import PageObjects.Ecomm_PendingApprovalListPage;
import PageObjects.Ecomm_ShadeOrderConfirmationPage;
import PageObjects.Ecomm_ShadeNotAvailablePage;
import PageObjects.Ecomm_WaitingForShadePage;
import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.openqa.selenium.Alert;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

@Category(Categories.eComm.class)
public class Ecomm_Shade {
    
    @Test //Shade Not Available Page :: Page and filter checks, view and edit
    public void SNA1() throws IOException {
        //new driver instance
        WebDriver driver = new ChromeDriver();
		
        //New eComm base test to handle log-in and navigation
        Ecomm_SUSST_Base baseTest = new Ecomm_SUSST_Base(driver);
        PageObjects.Ecomm_MainPage eCommPage = baseTest.SUSST_SetUp("Shade Not Available Page SNA1: Page and filter checks, view and edit", "UNKNOWN");
        
        System.out.println("Navigating to Shade Not Available page...");
        
        Ecomm_ShadeNotAvailablePage snaPage = eCommPage.clickShadeNotAvailable();
        snaPage.waitForLoad();
        
        //Take a screenshot
        File scrFile1 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile1,new File(TestSuiteOLD.screenshotsFilepath+"\\EComm\\Orders\\Shade Not Available\\1Shade Not Available Page.png"));
        
        System.out.println("Shade Not Available page reached. Checking title...");
        
        Assert.assertTrue("Shade Not Available Page: Title not as expected",snaPage.getBreadcrumbText2().equals("Orders | Shade Not Available"));
        
        snaPage.assertBaseElements();
        
        System.out.println("Title checked. Checking fields...");
        
        snaPage.checkFields();
        
        System.out.println("Fields checked. Entering filter criteria...");
        
        snaPage.setSalesOrg("ID50");
        snaPage.setCustName(TestSuiteOLD.custDetails[0]);
        
        //Take a screenshot
        File scrFile2 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile2,new File(TestSuiteOLD.screenshotsFilepath+"\\EComm\\Orders\\Shade Not Available\\2Filter criteria entered.png"));
        
        System.out.println("Criteria entered. Listing records...");
        
        snaPage.pressSearch();
        snaPage.waitForLoad();
        
        //Take a screenshot
        File scrFile3 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile3,new File(TestSuiteOLD.screenshotsFilepath+"\\EComm\\Orders\\Shade Not Available\\3Records listed.png"));
        
        System.out.println("Records listed. Resetting filter...");
        
        snaPage.pressReset();
        snaPage.waitForLoad();
        
        //Take a screenshot
        File scrFile4 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile4,new File(TestSuiteOLD.screenshotsFilepath+"\\EComm\\Orders\\Shade Not Available\\4Filter reset.png"));
        
        System.out.println("Filter reset. Viewing top record...");
        
        Ecomm_OrderViewPage viewPage = snaPage.pressView();
        viewPage.waitForContent();
        
        //Take a screenshot
        File scrFile5 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile5,new File(TestSuiteOLD.screenshotsFilepath+"\\EComm\\Orders\\Shade Not Available\\5Order view displayed.png"));
        
        System.out.println("View displayed. Closing view...");
        
        viewPage.closeView();
        viewPage.waitForInvisibility();
        
        System.out.println("View closed. Editing top item...");
        
        Ecomm_ShadeOrderConfirmationPage snaConf = snaPage.pressEdit();
        
        try {
            Alert alert = new WebDriverWait(driver,5).until(ExpectedConditions.alertIsPresent());
            alert.accept();
            System.out.println("Alert appeared: " + alert.getText());
        } catch (Exception e) {
            System.out.println("No alert.");
        }
        
        snaConf.waitForLoad();
        
        //Take a screenshot
        File scrFile6 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile6,new File(TestSuiteOLD.screenshotsFilepath+"\\EComm\\Orders\\Shade Not Available\\6Shade Not Available Order Confirmation Page.png"));
        
        System.out.println("Shade Not Available Order Confirmation Page reached. Checking fields...");
        
        snaConf.checkFields();
        
        System.out.println("Fields checked. Clicking edit...");
        
        Ecomm_OrderInformationPage infoPage = snaConf.pressEdit();
        infoPage.waitForContent();
        infoPage.switchTo();
        
        //Take a screenshot
        File scrFile7 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile7,new File(TestSuiteOLD.screenshotsFilepath+"\\EComm\\Orders\\Shade Not Available\\7Order Information Page.png"));
        
        System.out.println("Information page reached. Setting shade code and required date...");
        
        infoPage.setShadeCode(TestSuiteOLD.expShadeCode);
        infoPage.setRequiredDate();
        
        //Take a screenshot
        File scrFile8 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile8,new File(TestSuiteOLD.screenshotsFilepath+"\\EComm\\Orders\\Shade Not Available\\8Shade code set.png"));
        
        System.out.println("Details set. Submitting order information...");
        
        infoPage.pressSubmit();
        infoPage.waitForInvisibility();
        
        System.out.println("Information submitted. Sending order for approval...");
        
        //Take a screenshot
        File scrFile9 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile9,new File(TestSuiteOLD.screenshotsFilepath+"\\EComm\\Orders\\Shade Not Available\\9Information view closed.png"));
        
        Ecomm_OutstandingOrdersPage outPage = snaConf.pressSubmit();
        outPage.waitForLoad();
        
        System.out.println("Order sent for approval.");
        
        //Take a screenshot
        File scrFile10 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile10,new File(TestSuiteOLD.screenshotsFilepath+"\\EComm\\Orders\\Shade Not Available\\10Order submitted.png"));
        
        System.out.println("----------------------------------------------------");
        
        driver.close();
        driver.quit();
        
    }
    
    @Test //Waiting for Shade Code Page :: Page and filter checks, view and edit
    public void WFS1() {
        //new driver instance
        WebDriver driver = new ChromeDriver();
		
        //New eComm base test to handle log-in and navigation
        Ecomm_SUSST_Base baseTest = new Ecomm_SUSST_Base(driver);
        PageObjects.Ecomm_MainPage eCommPage = baseTest.SUSST_SetUp("Waiting for Shade Page WFS1: Page and filter checks, view and edit", "UNKNOWN");
        
        System.out.println("Navigating to Waiting for Shade page...");
        
        Ecomm_WaitingForShadePage wfsPage = eCommPage.clickWaitingForShade();
        wfsPage.waitForLoad();
        
        System.out.println("Waiting for shade page reached. Checking title...");
        
        Assert.assertTrue("Waiting For Shade Page: Title not as expected",wfsPage.getBreadcrumbText2().equals("Orders | Waiting For Shade Code"));
    
        System.out.println("Title checked.");
        
        wfsPage.assertBaseElements();
        
        System.out.println("Checking fields...");
        
        wfsPage.checkFields();
        
        System.out.println("Fields checked. Entering filter criteria...");
        
        wfsPage.setSalesOrg("ID50");
        wfsPage.setCustName(TestSuiteOLD.custDetails[0]);
        
        System.out.println("Criteria entered. Listing records...");
        
        wfsPage.pressSearch();
        wfsPage.waitForLoad();
        
        System.out.println("Records listed. Resetting filter...");
        
        wfsPage.pressReset();
        wfsPage.waitForLoad();
        
        System.out.println("Filter reset.");
        
        System.out.println("----------------------------------------------------");
        
        driver.close();
        driver.quit();
    }
    
}
