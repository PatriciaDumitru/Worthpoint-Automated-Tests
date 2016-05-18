package com.coats.selenium.tests;

import AutomationFramework.DataItems;
import PageObjects.CCE_MainPage;
import PageObjects.CCE_HubSosPage;
import PageObjects.CCE_OrderViewPage;
import PageObjects.CCE_ReceivedHubPage;
import com.coats.selenium.DriverFactory;
import org.testng.AssertJUnit;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

public class Cce_Hub_Test extends DriverFactory {
    
    @Test //Hub SOS Page :: Page and filter checks
    (groups = {"CCE", "QuickTest"})
    public void HS1() throws Exception {
        //New driver object
        WebDriver driver = getDriver();
        
        //New base object to handle log-in and set up
        Cce_Base base = new Cce_Base(driver);
        
        //Set up returns a CCE Page and outputs test details
        CCE_MainPage ccePage = base.setUp("Hub SOS: Page and filter checks", "C_CCE_HS_1-4");
        
        System.out.println("Navigating to Hub SOS...");
        
        CCE_HubSosPage hsPage = ccePage.pressHubSos();
        hsPage.waitForElement();
        
        System.out.println("Hub SOS reached. Checking title...");
        
        AssertJUnit.assertTrue("Hub SOS Page: Title not displayed as expected",hsPage.getTitle().equals("Orders | Hub SOS"));
        
        System.out.println("Title checked. Asserting base elements...");
        
        hsPage.assertBaseElements();
        
        System.out.println("Base elements asseted. Checking fields...");
        
        hsPage.checkFields();
        
        System.out.println("Entering filter criteria...");
        
        hsPage.setCustName(DataItems.custDetails[0]);
        hsPage.setBrand(DataItems.expBrand);
        
        System.out.println("Criteria entered. Pressing list orders...");
        
        hsPage.pressListOrders();
        hsPage.waitForLoad();
        
        System.out.println("Orders listed. Checking for records...");
        
        if (hsPage.findRecords()) {
            System.out.println("Records found. Viewing first item...");
        
            CCE_OrderViewPage viewPage = hsPage.pressView();
            viewPage.waitForContent();

            System.out.println("View reached. Closing view...");

            hsPage.closeView();

            System.out.println("View closed.");
            
        } else {
            System.out.println("No records found under filter. Resetting...");
            hsPage.pressReset();
            hsPage.waitForElement();
            System.out.println("Filter reset");
        }
        
        System.out.println("Adjusting MUM type of first item...");

            String mumType = DataItems.viconeMUM;
            hsPage.adjustMUMType(mumType);

            System.out.println("MUM type adjusted to " + mumType+". Adjusting SOS assignment...");

            String assignment = "SOS Lab";
            hsPage.adjustAssignment(assignment);

            System.out.println("SOS assignment set. Saving changes...");

            hsPage.pressSave();
            hsPage.waitForLoad();
        
        
    } 
    
    @Test //Received Hub Page :: Page and filter checks, reset, view and send to customer
    (groups = {"CCE", "QuickTest"})
    public void RH1() throws Exception {
        //New driver object
        WebDriver driver = getDriver();
        
        //New base object to handle log-in and set up
        Cce_Base base = new Cce_Base(driver);
        
        //Set up returns a CCE Page and outputs test details
        CCE_MainPage ccePage = base.setUp("Receieved Hub: Page and filter checks", "C_CCE_RH_1-3");
        
        System.out.println("Navigating to Received Hub...");
        
        CCE_ReceivedHubPage rhPage = ccePage.pressReceivedHub();
        rhPage.waitForLoad();
        
        System.out.println("Received Hub reached. Checking title...");
        
        AssertJUnit.assertTrue("Received Hub Page: Title not displayed as expected",rhPage.getTitle().equals("Orders | Received Hub"));
        
        System.out.println("Title checked. Asserting base elements...");
        
        rhPage.assertBaseElements();
        
        System.out.println("Base elements asserted. Checking fields...");
        
        rhPage.checkFields();
        
        System.out.println("Fields checked. Entering filter criteria...");
        
        rhPage.setCustName(DataItems.custDetails[0]);
        
        System.out.println("Criteria entered. Listing orders...");
        
        rhPage.pressListOrders();
        rhPage.waitForLoad();
        
        System.out.println("Orders listed. Resetting filter...");
        
        rhPage.pressReset();
        rhPage.waitForLoad();
        
        System.out.println("Filter reset. Viewing first order...");
        
        if (!rhPage.checkForRecords()) {
            CCE_OrderViewPage orderView = rhPage.pressView();
            orderView.switchTo();
            orderView.waitForContent();

            System.out.println("View displayed. Closing view...");

            orderView.closeView();

            driver.navigate().refresh();

            System.out.println("Order view closed. Pressing send to customer for first item...");

            rhPage.pressSendToCust();

            System.out.println("Send to customer selected. Saving...");

            rhPage.pressSave();
            rhPage.waitForLoad();

        } else {
            System.out.println("No records found, test incomplete");
        }
        
        
        
    } 
    
}
