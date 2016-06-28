package com.coats.selenium.tests;

import AutomationFramework.DataItems;
import AutomationFramework.PreFlows;
import PageObjects.*;
import com.coats.selenium.DriverFactory;
import java.io.File;
import org.apache.commons.io.FileUtils;
import org.testng.AssertJUnit;
import org.openqa.selenium.Alert;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class Ecomm_Shade_Test extends DriverFactory {
    
    @Test //Shade Not Available Page :: Page and filter checks, view and edit
    (groups = {"eComm", "QuickTest"})
    public void SNA1() throws Exception {
        //new driver instance
        WebDriver driver = getDriver();
		
        //New eComm base test to handle log-in and navigation
        Ecomm_Base baseTest = new Ecomm_Base(driver);
        PageObjects.Ecomm_MainPage eCommPage = baseTest.setUp("Shade Not Available Page SNA1: Page and filter checks, view and edit", "UNKNOWN");

        eCommPage.waitForElement();

        System.out.println("Setup MasterData...");
        //Activate CallOffOrder for customer

        PreFlows pf = new PreFlows();
        pf.chooseTheOtherProfile(driver); //choose CCE for Master Data setup
        pf.enableApprovelCheckBoxForSalesOrgAndCust(driver, DataItems.salesOrgID51, DataItems.lifeEasyCustomer);
        pf.chooseTheOtherProfile(driver); //choose Ecomm for tests

        System.out.println("Navigating to Shade Not Available page...");
        
        Ecomm_ShadeNotAvailablePage snaPage = eCommPage.clickShadeNotAvailable();
        snaPage.waitForLoad();

        //Take a screenshot
        File scrFile1 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile1,new File(DataItems.screenshotsFilepath+"\\EComm\\Orders\\Shade Not Available\\1Shade Not Available Page.png"));

        System.out.println("Shade Not Available page reached. Checking title...");
        
        AssertJUnit.assertTrue("Shade Not Available Page: Title not as expected",snaPage.getBreadcrumbText2().equals("Orders | Shade Not Available"));
        
        snaPage.assertBaseElements();
        
        System.out.println("Title checked. Checking fields...");
        
        snaPage.checkFields();
        
        System.out.println("Fields checked. Entering filter criteria...");
        
        snaPage.setSalesOrg("ID51");
        snaPage.setCustName(DataItems.custDetails[0]);

        //Take a screenshot
        File scrFile2 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile2,new File(DataItems.screenshotsFilepath+"\\EComm\\Orders\\Shade Not Available\\2Filter criteria entered.png"));

        System.out.println("Criteria entered. Listing records...");
        
        snaPage.pressSearch();
        snaPage.waitForLoad();

        //Take a screenshot
        File scrFile3 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile3,new File(DataItems.screenshotsFilepath+"\\EComm\\Orders\\Shade Not Available\\3Records listed.png"));

        System.out.println("Records listed. Resetting filter...");
        
        snaPage.pressReset();
        snaPage.waitForLoad();

        //Take a screenshot
        File scrFile4 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile4,new File(DataItems.screenshotsFilepath+"\\EComm\\Orders\\Shade Not Available\\4Filter reset.png"));

        System.out.println("Filter reset. Finding order with single order line...");
        
        int row = snaPage.getSingleLineRecord();
        
        Ecomm_OrderViewPage viewPage = snaPage.pressView(row);
        viewPage.waitForContent();

        //Take a screenshot
        File scrFile5 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile5,new File(DataItems.screenshotsFilepath+"\\EComm\\Orders\\Shade Not Available\\5Order view displayed.png"));

        System.out.println("View displayed. Closing view...");
        
        viewPage.closeView();
        viewPage.waitForInvisibility();
        
        System.out.println("View closed. Editing item...");
        
        Ecomm_ShadeOrderConfirmationPage snaConf = snaPage.pressEdit(row);
        
        try {
            Alert alert = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.alertIsPresent());
            alert.accept();
            System.out.println("Alert appeared: " + alert.getText());
        } catch (Exception e) {
            System.out.println("No alert.");
        }
        
        snaConf.waitForLoad();

        //Take a screenshot
        File scrFile6 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile6,new File(DataItems.screenshotsFilepath+"\\EComm\\Orders\\Shade Not Available\\6Shade Not Available Order Confirmation Page.png"));

        System.out.println("Shade Not Available Order Confirmation Page reached. Checking fields...");
        
        snaConf.checkFields();
        
        System.out.println("Fields checked. Clicking edit...");
        
        Ecomm_OrderInformationPage infoPage = snaConf.pressEdit();
        infoPage.waitForContent();
        infoPage.switchTo();
        
        //Take a screenshot
        File scrFile7 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile7,new File(DataItems.screenshotsFilepath+"\\EComm\\Orders\\Shade Not Available\\7Order Information Page.png"));
        
        System.out.println("Information page reached. Setting shade code and required date...");
        
        infoPage.setShadeCode(DataItems.expShadeCode);
        infoPage.setRequiredDate();
        
        //Take a screenshot
        File scrFile8 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile8,new File(DataItems.screenshotsFilepath+"\\EComm\\Orders\\Shade Not Available\\8Shade code set.png"));
        
        System.out.println("Details set. Submitting order information...");
        
        infoPage.pressSubmit();
        infoPage.waitForInvisibility();
        
        System.out.println("Information submitted. Sending order for approval...");
        
        //Take a screenshot
        File scrFile9 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile9,new File(DataItems.screenshotsFilepath+"\\EComm\\Orders\\Shade Not Available\\9Information view closed.png"));

        Ecomm_PendingApprovalListPage outPage = snaConf.pressSend();
        outPage.waitForLoad();
        
        System.out.println("Order sent for approval.");
        
        //Take a screenshot
        File scrFile10 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile10,new File(DataItems.screenshotsFilepath+"\\EComm\\Orders\\Shade Not Available\\10Order submitted.png"));
    }
    
    @Test //Waiting for Shade Code Page :: Page and filter checks, view and edit
    (groups = {"eComm", "QuickTest"})
    public void WFS1() throws Exception {
        //new driver instance
        WebDriver driver = getDriver();
		
        //New eComm base test to handle log-in and navigation
        Ecomm_Base baseTest = new Ecomm_Base(driver);
        PageObjects.Ecomm_MainPage eCommPage = baseTest.setUp("Waiting for Shade Page WFS1: Page and filter checks, view and edit", "UNKNOWN");

        eCommPage.waitForElement();
        
        System.out.println("Navigating to Waiting for Shade page...");
        
        Ecomm_WaitingForShadePage wfsPage = eCommPage.clickWaitingForShade();
        wfsPage.waitForLoad();
        
        System.out.println("Waiting for shade page reached. Checking title...");
        
        AssertJUnit.assertTrue("Waiting For Shade Page: Title not as expected",wfsPage.getBreadcrumbText2().equals("Orders | Waiting For Shade Code"));
    
        System.out.println("Title checked.");
        
        wfsPage.assertBaseElements();
        
        System.out.println("Checking fields...");
        
        wfsPage.checkFields();
        
        System.out.println("Fields checked. Entering filter criteria...");
        
        wfsPage.setSalesOrg("ID51");
        wfsPage.setCustName(DataItems.custDetails[0]);
        
        System.out.println("Criteria entered. Listing records...");
        
        wfsPage.pressSearch();
        wfsPage.waitForLoad();
        
        System.out.println("Records listed. Resetting filter...");
        
        wfsPage.pressReset();
        wfsPage.waitForLoad();
        
        System.out.println("Filter reset.");
        
    }
    
}
