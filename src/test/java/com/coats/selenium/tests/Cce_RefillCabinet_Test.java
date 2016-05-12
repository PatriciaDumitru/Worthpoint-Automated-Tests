package com.coats.selenium.tests;

import AutomationFramework.DataItems;
import PageObjects.CCE_MainPage;
import PageObjects.CCE_RefillCabinetPage;
import PageObjects.CCE_RefillThreadListPage;
import com.coats.selenium.DriverFactory;
import org.testng.AssertJUnit;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

public class Cce_RefillCabinet_Test extends DriverFactory {
    
    @Test //Refill Cabinet Page :: Page and filter checks
    (groups = {})//Currently unused feature
    public void RC1() throws Exception {
        //New driver object to control browser
        WebDriver driver = getDriver();
        
        //New base object to handle log-in and set up
        Cce_Base base = new Cce_Base(driver);
        
        //Set up returns a CCE Page and outputs test details
        CCE_MainPage ccePage = base.setUp("Refill Cabinet RC1: Page checks, filter function", "C_CCE_RC_1-2");
        
        System.out.println("Navigating to Refill Cabinet...");
        
        CCE_RefillCabinetPage rcPage = ccePage.pressRefillCabinet();
        rcPage.waitForLoad();
        
        System.out.println("Refill Cabinet loaded.");
        
        //Assert base elements
        rcPage.assertBaseElements();
        
        System.out.println("Asserting page title is as expected...");
        
        AssertJUnit.assertTrue("Refill Cabinet Page: Title not as expected",rcPage.getBreadcrumb().getText().equals("Refill Cabinet"));
        
        System.out.println("Title correct. Checking fields...");
        
        rcPage.checkFields();
        
        System.out.println("Fields checked. Entering ship-to...");
        
        rcPage.setShipTo(DataItems.custDetails[1]);
        
        System.out.println("Ship-to entered. Checking cabinet name is as expected...");
        
        System.out.println(rcPage.getCabinetName());
        
        AssertJUnit.assertTrue("Refill Cabinet Page: Cabinet code not consistent with master data",rcPage.getCabinetName().equals(DataItems.cabinetName));
        
        System.out.println("Cabinet name correct. Pressing submit...");
        
        CCE_RefillThreadListPage rtPage = rcPage.pressSubmit();
        rtPage.waitForElement();
        
        System.out.println("Refill Thread List Page reached. Entering refill thread details...");
        
        rtPage.setBrand("astra");
        rtPage.setTicket("120");
        rtPage.setShade("C9700");
        rtPage.setMUMType("Cop");
        rtPage.setROQ("3");
        
        System.out.println("Details entered. Pressing add thread expecting alert...");
        
        rtPage.pressAddThreadExpectingAlert();
        
        System.out.println("Pressing save...");
        
        rtPage.pressSave();
        
        AssertJUnit.assertFalse("Refill Thread List Page: Fatal error upon saving", rtPage.checkForFatalError());
        
        System.out.println("Save pressed. TEST NEEDS TO BE EXTENDED - BLOCKED BY FATAL ERROR");
        
    }

}
