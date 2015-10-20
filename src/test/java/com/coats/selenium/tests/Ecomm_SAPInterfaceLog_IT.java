package com.coats.selenium.tests;

import AutomationFramework.DataItems;
import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import PageObjects.Ecomm_MainPage;
import PageObjects.Ecomm_OrderViewPage;
import PageObjects.Ecomm_SAPInterfaceLogPage;
import com.coats.selenium.DriverFactory;
import org.testng.annotations.Test;

public class Ecomm_SAPInterfaceLog_IT extends DriverFactory {
	
    @Test //SAP Interface Log Page :: Page and filter checks, view and reset
    (groups = {"eComm"})
    public void SILM1() throws IOException, Exception {
		
        //New driver instance
        WebDriver driver = getDriver();
		
        //New eComm base test to handle log-in and navigation
        Ecomm_SUSST_Base baseTest = new Ecomm_SUSST_Base(driver);
        Ecomm_MainPage eCommPage = baseTest.SUSST_SetUp("SAP Interface Log SILM1: Page and filter checks, views and reset", "G_CoUA_SILM_1 to 4");
		
        Ecomm_SAPInterfaceLogPage logPage = eCommPage.clickSAPInterfaceLog();
        logPage.waitForLoad();
		
	//Take a screenshot
        File scrFile1 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile1,new File(DataItems.screenshotsFilepath+"\\EComm\\SAP Interface Log\\1SAP Interface Log.png"));
		
        System.out.println("SAP Interface Log page reached.");
        
        logPage.assertBaseElements();
        
        System.out.println("Checking fields...");
        
        logPage.checkFields();
        
        System.out.println("Fields checked. Entering filter criteria...");
        
        logPage.setBrand(DataItems.brand);
        logPage.setTicket(DataItems.ticket);
        logPage.setFinish(DataItems.finish);
        logPage.setSAPMessage(DataItems.sapMessage);
        
        //Take a screenshot
        File scrFile2 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile2,new File(DataItems.screenshotsFilepath+"\\EComm\\SAP Interface Log\\2Filter criteria entered.png"));
        
        System.out.println("Criteria entered. Searching...");
        
        logPage.pressSearch();
        logPage.waitForLoad();
        
        //Take a screenshot
        File scrFile3 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile3,new File(DataItems.screenshotsFilepath+"\\EComm\\SAP Interface Log\\3Records listed.png"));
        
        System.out.println("Records listed. Pressing reset...");
        
        logPage.pressReset();
        logPage.waitForLoad();
        
        //Take a screenshot
        File scrFile4 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile4,new File(DataItems.screenshotsFilepath+"\\EComm\\SAP Interface Log\\4Filter reset.png"));
        
        System.out.println("Filter reset. Viewing record...");
        
        Ecomm_OrderViewPage viewPage = logPage.pressView();
        viewPage.waitForContent();
        
        System.out.println("View displayed. Closing view...");
        
        //Take a screenshot
        File scrFile5 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile5,new File(DataItems.screenshotsFilepath+"\\EComm\\SAP Interface Log\\5View displayed.png"));
        
        viewPage.closeView();
        viewPage.waitForInvisibility();
        
        System.out.println("View closed. Pressing File Transfer View...");
        
        Ecomm_OrderViewPage ftView = logPage.pressFtView();
        ftView.waitForContent();
        ftView.switchTo();
        ftView.waitForFTData();
        
        System.out.println("File Transfer View displayed. Closing view...");
        
        //Take a screenshot
        File scrFile6 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile6,new File(DataItems.screenshotsFilepath+"\\EComm\\SAP Interface Log\\6File Transfer View.png"));
        
        ftView.closeView();
        
        System.out.println("View closed.");
		
	}

}
