package com.coats.selenium.tests;

import AutomationFramework.DataItems;
import org.openqa.selenium.WebDriver;
import PageObjects.Ecomm_MainPage;
import PageObjects.Ecomm_OrderViewPage;
import PageObjects.Ecomm_SAPInterfaceLogPage;
import com.coats.selenium.DriverFactory;
import org.testng.annotations.Test;

public class Ecomm_SAPInterfaceLog_Test extends DriverFactory {
    
    @Test //SAP Interface Log Page :: Page and filter checks, view and reset
    (groups = {"eComm", "QuickTest"})
    public void SILM1() throws Exception {
		
        //New driver instance
        WebDriver driver = getDriver();
		
        //New eComm base test to handle log-in and navigation
        Ecomm_Base baseTest = new Ecomm_Base(driver);
        Ecomm_MainPage eCommPage = baseTest.setUp("SAP Interface Log SILM1: Page and filter checks, views and reset", "G_CoUA_SILM_1 to 4");
		
        Ecomm_SAPInterfaceLogPage logPage = eCommPage.clickSAPInterfaceLog();
        logPage.waitForLoad();
		
        System.out.println("SAP Interface Log page reached.");
        
        logPage.assertBaseElements();
        
        System.out.println("Checking fields...");
        
        logPage.checkFields();
        
        System.out.println("Fields checked. Entering filter criteria...");
        
        logPage.setBrand(DataItems.brand);
        logPage.setTicket(DataItems.ticket);
        logPage.setFinish(DataItems.finish);
        logPage.setSAPMessage(DataItems.sapMessage);
        
        System.out.println("Criteria entered. Searching...");
        
        logPage.pressSearch();
        logPage.waitForLoad();

        System.out.println("Records listed. Pressing reset...");
        
        logPage.pressReset();
        logPage.waitForLoad();
        
        System.out.println("Filter reset. Viewing record...");
        
        Ecomm_OrderViewPage viewPage = logPage.pressView();
        viewPage.waitForContent();
        
        System.out.println("View displayed. Closing view...");
        
        viewPage.closeView();
        viewPage.waitForInvisibility();
        
        System.out.println("View closed. Pressing File Transfer View...");
        
        Ecomm_OrderViewPage ftView = logPage.pressFtView();
        ftView.waitForContent();
        ftView.switchTo();
        ftView.waitForFTData();
        
        System.out.println("File Transfer View displayed. Closing view...");
        
        ftView.closeView();
        
        System.out.println("View closed.");
		
	}

}
